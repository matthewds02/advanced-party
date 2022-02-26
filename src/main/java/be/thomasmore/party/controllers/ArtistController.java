package be.thomasmore.party.controllers;

import be.thomasmore.party.model.Artist;
import be.thomasmore.party.repositories.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class ArtistController {
    @Autowired
    private ArtistRepository artistRepository;

    @GetMapping({"/artistdetails", "/artistdetails/{id}"})
    public String artistDetails(Model model, @PathVariable(required = false) Integer id) {
        if (id==null) return "artistdetails";
        Optional<Artist> optionalVenue = artistRepository.findById(id);
        Optional<Artist> optionalPrev = artistRepository.findFirstByIdLessThanOrderByIdDesc(id);
        Optional<Artist> optionalNext = artistRepository.findFirstByIdGreaterThanOrderById(id);
        if (optionalVenue.isPresent()) {
            model.addAttribute("artist", optionalVenue.get());
        }
        if (optionalPrev.isPresent()) {
            model.addAttribute("prev", optionalPrev.get().getId());
        } else {
            model.addAttribute("prev", artistRepository.findFirstByOrderByIdDesc().get().getId());
        }
        if (optionalNext.isPresent()) {
            model.addAttribute("next", optionalNext.get().getId());
        } else {
            model.addAttribute("next", artistRepository.findFirstByOrderByIdAsc().get().getId());
        }
        return "artistdetails";
    }

    @GetMapping("/artistlist")
    public String venueList(Model model) {
        Iterable<Artist> allArtists = artistRepository.findAll();
        model.addAttribute("artists", allArtists);
        return "artistlist";
    }
}
