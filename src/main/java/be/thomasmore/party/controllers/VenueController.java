package be.thomasmore.party.controllers;

import be.thomasmore.party.model.Venue;
import be.thomasmore.party.repositories.VenueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class VenueController {
    @Autowired
    private VenueRepository venueRepository;

    @GetMapping({"/venuedetails", "/venuedetails/{id}"})
    public String venueDetails(Model model, @PathVariable(required = false) Integer id) {
        if (id==null) return "venuedetails";
        Optional<Venue> optionalVenue = venueRepository.findById(id);
        if (optionalVenue.isPresent()) {
            model.addAttribute("venue", optionalVenue.get());
        }

        /* first one is to get data into website from database and 'data.sql'file
           second one is to use hardcoded data from constructor in 'Venue.java' for the website
        Venue venue = new Venue("VenueZonderNaam","www.venuezondernaam.org");
        model.addAttribute("venue", venue);*/

        return "venuedetails";
    }

    @GetMapping("/venuelist")
    public String venueList(Model model) {
        Iterable<Venue> allVenues = venueRepository.findAll();
        model.addAttribute("venues", allVenues);
        return "venuelist";
    }

    @GetMapping("/venuelist/outdoor/{antwoord}")
    public String venueListOutdoorYesNo(Model model, @PathVariable boolean antwoord) {
        Iterable<Venue> venues = venueRepository.findByOutdoor(antwoord);
        model.addAttribute("venues", venues);
        return "venuelist";
    }
}