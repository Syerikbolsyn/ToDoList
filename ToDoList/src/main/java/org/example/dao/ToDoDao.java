package org.example.dao;

import org.example.models.ToDo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class ToDoDao {
    private static int ID_LIST = 0;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ToDoDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public List<ToDo> index() {
        return jdbcTemplate.query("select * from todo", new BeanPropertyRowMapper<>(ToDo.class));
    }
    public ToDo show(int id) {
        return jdbcTemplate.query("SELECT * FROM Todo WHERE id = ?", new Object[]{id},
                new BeanPropertyRowMapper<>(ToDo.class)).stream().findAny().orElse(null);
    }

    public void save(ToDo toDo) {
        jdbcTemplate.update("INSERT INTO Todo VALUES (?,?,?,?)", toDo.getTask(), toDo.getDisc(), toDo.getStage(), ++ID_LIST);
    }
    public void update(int id, ToDo updateToDo) {
        jdbcTemplate.update("UPDATE Todo SET task = ?, disc = ?, stage = ? WHERE id=?", updateToDo.getTask(), updateToDo.getDisc(), updateToDo.getStage(), id);
    }
    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Todo WHERE id=?", id);
    }


}
