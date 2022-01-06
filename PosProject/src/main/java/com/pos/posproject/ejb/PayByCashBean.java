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

    public Double getChange(Integer saleId) {
        Double change = 0.0;
        try {
            Sale sale = em.find(Sale.class, saleId);
            Integer paymentId = sale.getPayment().getId();
            TypedQuery<PayByCash> typedQuery = em.createQuery("SELECT c FROM PayByCash c where c.payment.id = :id", PayByCash.class)
                    .setParameter("id", paymentId);
            List<PayByCash> payByCashPayment = typedQuery.getResultList();
            if (payByCashPayment.size() == 1) {
                for (PayByCash item : payByCashPayment) {
                    return change = item.getChange();
                }
            }
        } catch (Exception ex) {
            throw new EJBException(ex);
        }

        return change;
    }
}
