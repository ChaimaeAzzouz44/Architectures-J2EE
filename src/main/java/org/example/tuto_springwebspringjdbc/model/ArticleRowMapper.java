package org.example.tuto_springwebspringjdbc.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class ArticleRowMapper implements RowMapper<Article> {
    @Override
    public Article mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Article article = new Article();
        article.setCode(resultSet.getString("code"));
        article.setDesignation(resultSet.getString("designation"));
        article.setPrix(resultSet.getDouble("prix"));
        return article;
    }
}