/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mwai.demo.services;

import com.mwai.demo.dao.PersonDao;
import com.mwai.demo.model.Person;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author HP
 */
@Service
public class PersonService{
    private final PersonDao personDao;

    @Autowired
    public PersonService(@Qualifier("postgres") PersonDao personDao) {
        this.personDao = personDao;
    }
    
    public int addPerson(Person person){
       return personDao.insertPerson(person);
    }
    
    public List<Person> getAllPeople(){
        return personDao.returnAllPeople();
    }
    
    public Optional<Person> getPersonById(UUID id){
        return personDao.selectPersionById(id);
    }
    
    public int deletePersonById(UUID id){
        return personDao.deletePersonById(id);
    }
    
    public int updatePersonById(UUID id,Person newPerson){
        return personDao.updatePerson(id, newPerson);
    }
}
