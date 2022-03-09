package be.thomasmore.party.controllers;

import be.thomasmore.party.model.Venue;
import be.thomasmore.party.repositories.VenueRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.Optional;

@Controller
public class VenueController {

    private Logger logger = LoggerFactory.getLogger(VenueController.class);

    @Autowired
    private VenueRepository venueRepository;

    @GetMapping({"/venuedetails", "/venuedetails/{id}"})
    public String venueDetails(Model model, @PathVariable(required = false) Integer id) {
        if (id==null) return "venuedetails";
        Optional<Venue> optionalVenue = venueRepository.findById(id);
        Optional<Venue> optionalPrev = venueRepository.findFirstByIdLessThanOrderByIdDesc(id);
        Optional<Venue> optionalNext = venueRepository.findFirstByIdGreaterThanOrderById(id);
        if (optionalVenue.isPresent()) {
            model.addAttribute("venue", optionalVenue.get());
        }
        if (optionalPrev.isPresent()) {
            model.addAttribute("prev", optionalPrev.get().getId());
        } else {
            model.addAttribute("prev", venueRepository.findFirstByOrderByIdDesc().get().getId());
        }
        if (optionalNext.isPresent()) {
            model.addAttribute("next", optionalNext.get().getId());
        } else {
            model.addAttribute("next", venueRepository.findFirstByOrderByIdAsc().get().getId());
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
        model.addAttribute("count", venueRepository.count());
        return "venuelist";
    }

    @GetMapping("/venuelist/filter")
    public String filter(Model model, @RequestParam(required = false) Integer minimumCapacity,
                         @RequestParam(required = false) Integer maximumCapacity){
        Iterable<Venue> allVenues = null;
        if (minimumCapacity!=null && maximumCapacity!=null) {
            allVenues = venueRepository.findByCapacityIsBetween(minimumCapacity, maximumCapacity);
        } else if(minimumCapacity!=null) {
            allVenues = venueRepository.findByCapacityGreaterThanEqual(minimumCapacity);
        } else if(maximumCapacity!=null) {
            allVenues = venueRepository.findByCapacityLessThanEqual(maximumCapacity);
        } else {
            allVenues = venueRepository.findAll();
        }
        model.addAttribute("venues", allVenues);
        int nrVenues = ((Collection<Venue>) allVenues).size();
        model.addAttribute("count", nrVenues);

        model.addAttribute("showFilter", true);
        model.addAttribute("minCapacity", minimumCapacity);
        model.addAttribute("maxCapacity", maximumCapacity);
        return "venuelist";
    }
}