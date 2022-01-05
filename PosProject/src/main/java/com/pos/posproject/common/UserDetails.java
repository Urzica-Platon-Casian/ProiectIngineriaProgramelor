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
    private String first_name;
    private String last_name;
    private String username;
    private String position;

    public UserDetails(Integer id, String username, String first_name,String last_name, String position) {
        this.id = id;
        this.username = username;
        this.first_name=first_name;
        this.last_name=last_name;
        this.position = position;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
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