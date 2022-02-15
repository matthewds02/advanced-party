package be.thomasmore.party.controllers;

import be.thomasmore.party.model.Venue;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

@Controller
public class VenueController {

    @GetMapping("/venuedetails")
    public String venueDetails(Model model) {
        Venue venue = new Venue("VenueZonderNaam","www.venuezondernaam.org");
        model.addAttribute("venue",venue);

        int capacity = 100;
        boolean foodProvided = true;
        boolean indoor = true;
        boolean outdoor = false;
        boolean freeParking = false;
        double distance = 1.0;
        String city = "Antwerpen";




        return "venuedetails";
    }
}