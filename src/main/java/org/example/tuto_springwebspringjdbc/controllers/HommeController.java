package org.example.tuto_springwebspringjdbc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HommeController {

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("message", "J'apprends Spring Boot");
        return "index";
    }
    //on peut avoir meme non de methode = sudefinition (les arguments sont different)
    @GetMapping("/{name}")
    public String home(@PathVariable String name, Model model) {
        model.addAttribute("message", "Salam Cher "+name);
        return "index";
    }
}
