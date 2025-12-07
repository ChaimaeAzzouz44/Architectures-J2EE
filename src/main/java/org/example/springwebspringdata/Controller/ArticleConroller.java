package org.example.springwebspringdata.Controller;

import org.example.springwebspringdata.Model.Article;
import org.example.springwebspringdata.Repository.ArticleRepository;
import org.example.springwebspringdata.Service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/app/article/")  //http://localhost:8084/app/article/lister
public class ArticleConroller {

        @Autowired
        private ArticleService service;

        @GetMapping("lister")
        public String lister(Model model) {
            model.addAttribute("list", service.getAll());
            return "articles";
        }

        @PostMapping("/create")
        public String create(@ModelAttribute("article") Article article) {
            service.create(article);
            return "redirect:/app/article/lister";
        }

        @PostMapping("/update")
        public String update(@ModelAttribute("article") Article article) {
            service.update(article);
            return "redirect:/app/article/lister";
        }

        @GetMapping("/delete/{code}")
        public String delete(@PathVariable( value = "code") String code, Model model) {
            service.delete(code);
            return "redirect:/app/article/lister";
        }


}
