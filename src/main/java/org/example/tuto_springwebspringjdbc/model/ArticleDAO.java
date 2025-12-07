package org.example.tuto_springwebspringjdbc.model;

import org.springframework.jdbc.core.JdbcTemplate;
import java.util.List;



public class ArticleDAO implements IDAO<Article>{

    private JdbcTemplate jdbcTemplate;

    public ArticleDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Article> getAll() {
        return jdbcTemplate.query(        "SELECT * FROM article",
                new ArticleRowMapper());
    }

    @Override
    public Article getById(int id) {
        String sql = "SELECT * FROM article WHERE code = ?";
        return jdbcTemplate.queryForObject(
                sql,
                new Object[]{id},
                new ArticleRowMapper()
        );
    }

    @Override
    public void add(Article article) {
        String sql = "INSERT INTO article (code, designation, prix) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, article.getCode(), article.getDesignation(), article.getPrix());
    }


    @Override
    public void update(Article article) {

        String sql = "UPDATE article SET designation = ?, prix = ? WHERE code = ?";
        jdbcTemplate.update(sql, article.getDesignation(), article.getPrix(), article.getCode());
    }

    @Override
    public void delete(String code) {
        String sql = "DELETE FROM article WHERE code = ?";
        jdbcTemplate.update(sql, code);
    }
}























/*
@Component
public class ArticleImpl implements IArticle {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<Article> rowMapper = (rs, rowNum) -> {
        Article article = new Article();
        article.setCode(rs.getString("code"));
        article.setDesignation(rs.getString("designation"));
        article.setPrix(rs.getDouble("prix"));
        return article;
    };

    @Override
    public List<Article> getArticles() {
        String sql = "SELECT * FROM article";
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public Article getArticleByCode(String code) {
        String sql = "SELECT * FROM article WHERE code = ?";
        return jdbcTemplate.queryForObject(sql, rowMapper, code);
    }

    @Override
    public void addArticle(Article article) {
        String sql = "INSERT INTO article (code, designation, prix) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, article.getCode(), article.getDesignation(), article.getPrix());
    }

    @Override
    public void updateArticle(Article article) {
        String sql = "UPDATE article SET designation = ?, prix = ? WHERE code = ?";
        jdbcTemplate.update(sql, article.getDesignation(), article.getPrix(), article.getCode());
    }

    @Override
    public void deleteArticle(String code) {
        String sql = "DELETE FROM article WHERE code = ?";
        jdbcTemplate.update(sql, code);
    }
}*/
