package com.pos.posproject.ejb;

import com.pos.posproject.common.PaymentDetails;
import com.pos.posproject.entity.Payment;
import com.pos.posproject.entity.Sale;
import com.pos.posproject.enums.PaymentMethods;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Rori
 */
@Stateless
public class PaymentBean {

    @PersistenceContext
    private EntityManager em;

    public Integer setPayment(Double amount, int saleId) {
        Sale sale = em.find(Sale.class, saleId);
        Integer paymentId = sale.getPayment().getId();
        Payment payment = em.find(Payment.class, paymentId);

        payment.setAmount(amount);
        return payment.getId();
    }

    public Integer createPayment(int saleId, Double total, Double amount, PaymentMethods paymentMethod) {
        Payment payment = new Payment();
        payment.setDate(new Date());
        payment.setMethod(paymentMethod);
        payment.setTotal(total);
        payment.setAmount(amount);

        Sale sale = em.find(Sale.class, saleId);
        payment.setSale(sale);
        sale.setPayment(payment);

        em.persist(payment);
        return payment.getId();
    }

    public PaymentDetails findById(Integer paymentId) {
        Payment payment = em.find(Payment.class, paymentId);
        if (payment.getMethod() == PaymentMethods.CARD) {
            return new PaymentDetails(payment.getId(), payment.getTotal(),
                    payment.getAmount(), payment.getDate(), "BY CARD");
        }
        if (payment.getMethod() == PaymentMethods.CASH) {
            return new PaymentDetails(payment.getId(), payment.getTotal(),
                    payment.getAmount(), payment.getDate(), "BY CASH");
        }
        return null;
    }

    public PaymentDetails findBySaleId(Integer saleId) {
        PaymentDetails paymentDetails = null;
        Sale sale = em.find(Sale.class, saleId);
        if (sale.getPayment() != null) {
            Integer paymentId = sale.getPayment().getId();
            TypedQuery<Payment> typedQuery = em.createQuery("SELECT c FROM Payment c where c.id = :id", Payment.class)
                    .setParameter("id", paymentId);
            List<Payment> payments = typedQuery.getResultList();
            if (!payments.isEmpty()) {
                for (Payment payment : payments) {
                    if (payment.getMethod() == PaymentMethods.CARD) {
                        return new PaymentDetails(payment.getId(), payment.getTotal(),
                                payment.getAmount(), payment.getDate(), "BY CARD");
                    }
                    if (payment.getMethod() == PaymentMethods.CASH) {
                        return new PaymentDetails(payment.getId(), payment.getTotal(),
                                payment.getAmount(), payment.getDate(), "BY CASH");
                    }
                }
            }
        }
        return paymentDetails;
    }

}
