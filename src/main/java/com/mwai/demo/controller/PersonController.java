/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mwai.demo.controller;

import com.mwai.demo.model.Person;
import com.mwai.demo.services.PersonService;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author HP
 */
@Controller
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }
    
    //show  add user view
    @RequestMapping("/add")
    public String createPersonView() {
        return "add";
    }
    
    //handle new user creation
    @RequestMapping("/save")
    public String addPersion(Model model,@RequestParam String name) {
        Person person = new Person(UUID.randomUUID(),name);
        personService.addPerson(person);
        model.addAttribute("name",name);
        return "add";
    }
    
    //list saved persons
    @GetMapping("/persons")
    public String getAllPeople(Model model) {
        List<Person> persons = personService.getAllPeople();
        model.addAttribute("persons", persons);
        return "index";
    }
    
    //show a single person
    @GetMapping("/person/{id}")
    public String getPersonById(Model model,@PathVariable("id") UUID id) {
        Person person = personService.getPersonById(id).orElse(null);
        model.addAttribute("person", person);
        return "view";
    }
    
    //update person
    @RequestMapping("/update")
    public String updatePersonById(Model model,@RequestParam String name,@RequestParam String id) {
        Person person = new Person(UUID.fromString(id),name);
        int result = personService.updatePersonById(UUID.fromString(id), person);
        model.addAttribute("person", person);
        return "view";
    }
    
    //delete a person
    @GetMapping("/person/delete/{id}")
    public String deletePersonById(@PathVariable("id") UUID id) {
        personService.deletePersonById(id);
        return "index";
    }
}
