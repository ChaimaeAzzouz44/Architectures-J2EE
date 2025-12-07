package org.example.tuto_springwebspringjdbc.controllers;


import org.example.tuto_springwebspringjdbc.model.Article;
import org.example.tuto_springwebspringjdbc.model.ArticleDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/myapp/article/")  //http://localhost:8080/myapp/article/lister
public class ArticleController {
    @Autowired

    private ArticleDAO service;
    // Liste tous les articles
    @GetMapping("lister")
    public String lister(Model model) {
        model.addAttribute("list", service.getAll());
        return "List-articles";
    }
    @PostMapping("/create")
    public String create(@ModelAttribute("article") Article article) {
        service.add(article);
        return "redirect:/myapp/article/lister";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("article") Article article) {
        service.update(article);
        return "redirect:/myapp/article/lister";
    }

    @GetMapping("/delete/{code}")
    public String delete(@PathVariable(value = "code") String code, Model model) {
        service.delete(code);
        return "redirect:/myapp/article/lister";
    }


}

