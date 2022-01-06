/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pos.posproject.ejb;

import com.pos.posproject.entity.PayByCard;
import com.pos.posproject.entity.Payment;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Rori
 */
@Stateless
public class PayByCardBean {

   @PersistenceContext
    private EntityManager em;
   
   public void createPayByCard(String cardNumber, String expirationCardDate, 
           String cvv, Integer paymentId)
   {
       PayByCard payByCard = new PayByCard();
       Payment payment = em.find(Payment.class, paymentId);
       payByCard.setCardNumber(cardNumber);
       payByCard.setExpirationCardDate(expirationCardDate);
       payByCard.setCvv(cvv);
       payByCard.setPayment(payment);
       payment.setPayByCard(payByCard);
       
       em.persist(payByCard);
   }
}
