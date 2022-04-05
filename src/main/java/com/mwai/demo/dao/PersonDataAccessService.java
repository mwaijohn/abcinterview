/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mwai.demo.dao;

import com.mwai.demo.model.Person;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author HP
 */
@Repository("postgres")
public class PersonDataAccessService implements PersonDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertPerson(UUID uuid, Person person) {
        String sql = "INSERT INTO person (id,name) VALUES(?,?)";
        Object[] args = new Object[]{uuid,person.getName()};

        return jdbcTemplate.update(sql, args);
    }

    @Override
    public List<Person> returnAllPeople() {
        String sql = "SELECT id,name FROM person";
        return jdbcTemplate.query(sql, (resultSet, i) -> {
            UUID id = UUID.fromString(resultSet.getString("id"));
            String name = resultSet.getString("name");
            return new Person(id, name);
        });
    }

    @Override
    public Optional<Person> selectPersionById(UUID id) {
        String sql = "SELECT id,name FROM person WHERE id = ?";
        Person person = jdbcTemplate.queryForObject(sql, new Object[]{id}, (resultSet, i) -> {
            UUID personId = UUID.fromString(resultSet.getString("id"));
            String name = resultSet.getString("name");
            return new Person(personId, name);
        });

        return Optional.ofNullable(person);
    }

    @Override
    public int deletePersonById(UUID id) {
        String sql = "DELETE FROM person WHERE id = ?";
        Object[] args = new Object[]{id};

        return jdbcTemplate.update(sql, args);
    }

    @Override
    public int updatePerson(UUID id, Person person) {
        String sql = "UPDATE person SET name = ? WHERE id = ?";
        Object[] args = new Object[]{person.getName(), id};

        return jdbcTemplate.update(sql, args);
    }

}
