/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pos.posproject.ejb;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author stupa
 */
public class InvoiceBean {
    private Set<Integer> userIds = new HashSet<Integer>();

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public Set<Integer> getUserIds() {
        return userIds;
    }

    public void setUserIds(Set<Integer> userIds) {
        this.userIds = userIds;
    }
    
}
