package com.bgps.labs.daos;

import com.bgps.labs.models.Mark;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class MarkJdbc {
    private final JdbcTemplate jdbcTemplate;

    public MarkJdbc(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Mark get(int id){
        return jdbcTemplate.queryForObject("select * from mark where id = ?", this::mapMark, id);
    }

    public Mark search(String name){
        return jdbcTemplate.queryForObject("select * from mark where name = ?", Mark.class, name);
    }

    private Mark mapMark(ResultSet rs, int i) throws SQLException
    {
        return new Mark(
            rs.getInt("id"),
            rs.getString("name"),
            rs.getString("value")
        );
    }
}
