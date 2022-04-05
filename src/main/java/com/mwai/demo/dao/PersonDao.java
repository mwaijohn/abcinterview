/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mwai.demo.dao;

import com.mwai.demo.model.Person;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 *
 * @author HP
 */
public interface PersonDao {
    int insertPerson(UUID uuid,Person person);
    
    default int insertPerson(Person person){
        UUID uuid = UUID.randomUUID();
        return insertPerson(uuid,person);
    }
    
    List<Person> returnAllPeople();
    
    Optional<Person> selectPersionById(UUID id);
    
    int deletePersonById(UUID id);
    
    int updatePerson(UUID id,Person person);
}
