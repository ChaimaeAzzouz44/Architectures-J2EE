package org.example.webjava_sc1.Dao;

import org.example.webjava_sc1.Model.Article;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ArticleDao {
    private List<Article> ArticleList = new ArrayList<>();

    public void Initiate() {
        ArticleList.add(new Article("123", "Article1", 100));
        ArticleList.add(new Article("456", "Article2",200));
        ArticleList.add(new Article("789", "Article4", 150));
    }
    public void Create(Article article){
        ArticleList.add(article);
    }
    public boolean update(String Code, String newDesignation, double newPrix) {
        for (Article a : ArticleList) {
            if (a.getCode().equals(Code)) {
                a.setDesignation(newDesignation);
                a.setPrix(newPrix);
                return true;
            }
        }
        return false;
    }
    public void Delete(Article article){
        ArticleList.remove(article);
    }
    public List<Article> getAlL(){
        return ArticleList;
    }
    public Optional<Article> findByCode(String code) {
        return ArticleList.stream().filter(a -> a.getCode().equals(code)).findFirst();
    }


}
