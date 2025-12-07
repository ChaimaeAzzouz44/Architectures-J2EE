package org.example.springwebspringdata.Service;

import org.example.springwebspringdata.Model.Article;
import org.example.springwebspringdata.Repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    public List<Article> getAll() {return articleRepository.findAll();}
    public Article getById(String id){
        return articleRepository.findById(id).get();
    }
    public void create(Article article) {
        articleRepository.save(article);
    }
    public void update(Article article) {
        articleRepository.save(article);
    }
    public void delete(String id) {
        articleRepository.deleteById(id);
    }

}
