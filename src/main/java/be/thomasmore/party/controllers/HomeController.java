package be.thomasmore.party.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.DayOfWeek;

@Controller
public class HomeController {
    @GetMapping({"/", "/home"})
    public String home(Model model) {
        int myCalculatedValue = 34 * 62;
        model.addAttribute("myCalculatedValue", myCalculatedValue);
        return "home";
    }
    @GetMapping("/about")
    public String about(Model model) {
        String myName = "Matthew";
        String myCity = "Schoten";
        String myStreet = "Zandstappenstraat 22";
        model.addAttribute("myName", myName);
        model.addAttribute("myCity", myCity);
        model.addAttribute("myStreet", myStreet);
        return "about";
    }

    @GetMapping("/pay")
    public String pay(Model model) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formatDateTime = now.format(format);
        LocalDateTime future = now.plusYears(0).plusMonths(0).plusDays(30);
        String formatFutureDateTime = future.format(format);
        String dateToday = "De datum van vandaag is " + formatDateTime;
        String betaalDatum = "De uiterste datum van betalen is " + formatFutureDateTime;
        model.addAttribute("dateToday", dateToday);
        model.addAttribute("betaalDatum", betaalDatum);
        /* test a hardcoded date
        LocalDateTime wekend = LocalDateTime.of(2022, Month.FEBRUARY, 27, 19, 30, 40);
        (change     DayOfWeek d = now.getDayOfWeek();       to          DayOfWeek d = wekend.getDayOfWeek();)*/
        DayOfWeek d = now.getDayOfWeek();
        Boolean weekend = d == DayOfWeek.SATURDAY || d == DayOfWeek.SUNDAY;
        model.addAttribute("weekend", weekend);
        return "pay";
    }
}