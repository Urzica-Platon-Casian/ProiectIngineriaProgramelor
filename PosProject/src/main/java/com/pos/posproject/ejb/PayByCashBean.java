/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pos.posproject.ejb;

import com.pos.posproject.entity.LineItem;
import com.pos.posproject.entity.PayByCash;
import com.pos.posproject.entity.Payment;
import com.pos.posproject.entity.Product;
import com.pos.posproject.entity.Sale;
import java.util.Collection;
import java.util.List;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Rori
 */
@Stateless
public class PayByCashBean {

    @PersistenceContext
    private EntityManager em;

    public void createPayByCash(Double change, Integer paymentId) {
        PayByCash payByCash = new PayByCash();
        Payment payment = em.find(Payment.class, paymentId);
        payByCash.setChange(change);
        payByCash.setPayment(payment);
        payment.setPayByCash(payByCash);

        em.persist(payByCash);
    }
}
