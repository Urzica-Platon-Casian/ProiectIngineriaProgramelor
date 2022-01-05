/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pos.posproject.common;

import com.pos.posproject.enums.UserRoles;

/**
 *
 * @author stupa
 */
public class UserDetails implements java.io.Serializable {

    private Integer id;
    private String firstName;
    private String lastName;
    private String username;
    private String position;

    public UserDetails(Integer id, String firstName, String lastName, String username, String position) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.position = position;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    
    
    

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPosition() {
        return position;
    }    
}