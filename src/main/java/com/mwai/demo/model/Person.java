/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mwai.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.UUID;

/**
 *
 * @author HP
 */
public class Person {
    private final UUID uuid;
    private final String name;
    
    public Person(@JsonProperty("id") UUID uuid,@JsonProperty("name") String name){
        this.uuid = uuid;
        this.name = name;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }
    
}
