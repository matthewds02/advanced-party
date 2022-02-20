package be.thomasmore.party.controllers;

import be.thomasmore.party.model.Client;
import be.thomasmore.party.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.Random;

@Controller
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    @GetMapping("/client")
    public String greetingNewClient(Model model) {

        Optional<Client> optionalClient = clientRepository.findById(1);
        if (optionalClient.isPresent()) {
            model.addAttribute("client", optionalClient.get());
        }

        if (optionalClient.get().getGender().equals("M")) {
            String gender = "meneer";
            model.addAttribute("gender", gender);
        }
        else {
            String gender = "mevrouw";
            model.addAttribute("gender", gender);
        }

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("hh:mm:ss");
        String hour = now.format(format);
        model.addAttribute("hour", hour);
        int test = now.getHour();
        if (test < 12) {
            String voormiddag = "goedemorgen";
            model.addAttribute("middag", voormiddag);
        }
        else if (test > 12 && test < 17) {
            String namiddag = "goedemiddag";
            model.addAttribute("middag", namiddag);
        }
        else {
            String namiddag = "goedenavond";
            model.addAttribute("middag", namiddag);
        }

        return "newClient";
    }

    public void generateClientCode() {
        Optional<Client> optionalClient = clientRepository.findById(1);

        String eersteLetters = optionalClient.get().getClientName().substring(0, 2);
        String laatsteLetters = optionalClient.get().getClientName().substring(1, 1);
        String geboortedag = optionalClient.get().getBirthdate().substring(0, 2);

        Random rand = new Random();
        int birthdateToInt=Integer.parseInt(optionalClient.get().getBirthdate());
        int int_random = rand.nextInt(birthdateToInt);
    }
}
