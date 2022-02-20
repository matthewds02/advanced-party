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

    String clientCode = "";

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
        generateClientCode();

        return "newClient";
    }

    @GetMapping("/secretCode")
    public String showSecretCode(Model model) {

        Optional<Client> optionalClient = clientRepository.findById(1);
        if (optionalClient.isPresent()) {
            model.addAttribute("client", optionalClient.get());
        }
        else {
            model.addAttribute("notFound", "deze klant kan niet gevonden worden");
        }

        if (optionalClient.get().getGender().equals("M")) {
            String gender = "Meneer";
            model.addAttribute("gender", gender);
        }
        else {
            String gender = "Mevrouw";
            model.addAttribute("gender", gender);
        }
        model.addAttribute("code", clientCode);

        return "showSecretCode";
    }

    public void generateClientCode() {
        Optional<Client> optionalClient = clientRepository.findById(1);

        String naam = optionalClient.get().getClientName();
        String eersteLetters = naam.substring(0, 2);
        String laatsteLetters = naam.substring(naam.length()-1);
        String geboortedag = optionalClient.get().getBirthdate().substring(0, 2);

        Random rand = new Random();
        String geboortejaar = optionalClient.get().getBirthdate();
        int birthdateToInt=Integer.parseInt(geboortejaar.substring(geboortejaar.length()-4));
        int int_random = rand.nextInt(birthdateToInt);
        clientCode = eersteLetters + laatsteLetters + geboortedag + int_random;
    }
}
