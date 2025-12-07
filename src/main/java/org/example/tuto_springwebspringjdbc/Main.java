package org.example.tuto_springwebspringjdbc;
import java.util.Arrays;
import java.util.List;

import org.example.tuto_springwebspringjdbc.config.JdbcConfig;
import org.example.tuto_springwebspringjdbc.model.Article;
import org.example.tuto_springwebspringjdbc.model.ArticleDAO;
import org.example.tuto_springwebspringjdbc.model.IDAO;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {

        // Load the application context
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(JdbcConfig.class);

        IDAO<Article> articleDAO = context.getBean(ArticleDAO.class);

        List<Article> articles = articleDAO.getAll();

        Arrays.asList(articles).forEach(System.out::println);

//        for(Article article: articles)
//        {
//            System.out.println(article);
//        }
    }
}
