package com.pos.posproject.ejb;

import com.pos.posproject.common.CashierDetails;
import com.pos.posproject.entity.Cashier;
import com.pos.posproject.entity.User;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author upcas
 */
@Stateless
public class CashierBean {

    private static final Logger LOG = Logger.getLogger(CashierBean.class.getName());

    @PersistenceContext
    private EntityManager em;

    public CashierDetails findById(Integer cashierId) {
        Cashier cashier = em.find(Cashier.class, cashierId);
        return new CashierDetails(cashier.getId(), cashier.getFirstname(), cashier.getLastname(), cashier.getUsername(), cashier.getPosition(), cashier.getValidation());
    }

    public void createCashier(String cashiername, String first_name, String last_name, String passwordSha256, String position, Boolean validation) {
        Cashier cashier = new Cashier();
        cashier.setUsername(cashiername);
        cashier.setFirstname(first_name);
        cashier.setLastname(last_name);
        cashier.setPassword(passwordSha256);
        cashier.setPosition(position);
        cashier.setValidation(validation);
        em.persist(cashier);
    }

    public void updateCashier(Integer id, String firstName, String lastName,
            String username, String position) {
        LOG.info("updateCashier");
        Cashier user = em.find(Cashier.class, id);
        user.setFirstname(firstName);
        user.setLastname(lastName);
        user.setUsername(username);
        user.setPosition(position);
    }

    public void deleteCashiersByIds(List<Integer> ids) {
        LOG.info("deleteCashiersByIds");
        for (Integer id : ids) {
            Cashier cashier = em.find(Cashier.class, id);
            em.remove(cashier);
        }
    }

    public List<CashierDetails> getAllCashiers() {
        LOG.info("getAllCashiers");
        try {
            Query query = em.createQuery("SELECT u FROM Cashier u where u.position = 'CASHIER'");
            List<Cashier> users = (List<Cashier>) query.getResultList();
            return copyUsersToDetails(users);
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }

    private List<CashierDetails> copyUsersToDetails(List<Cashier> cashiers) {
        List<CashierDetails> detailsList = new ArrayList<>();
        for (Cashier cashier : cashiers) {
            CashierDetails userDetails = new CashierDetails(cashier.getId(), cashier.getFirstname(),
                    cashier.getLastname(), cashier.getUsername(), cashier.getPosition(), cashier.getValidation());
            detailsList.add(userDetails);
        }
        return detailsList;
    }

    public CashierDetails findCashierById(Integer cashierId) {
        LOG.info("findCashierById");
        Cashier cashier = em.find(Cashier.class, cashierId);
        return new CashierDetails(cashier.getId(), cashier.getFirstname(), cashier.getLastname(),
                cashier.getUsername(), cashier.getPosition(), cashier.getValidation());
    }

    public void makeCashierValid(Integer id, Boolean validation) {
        LOG.info("makeCashierValid");
        Cashier cashier = em.find(Cashier.class, id);
        cashier.setValidation(validation);
        User user = new User();
        user.setFirstname(cashier.getFirstname());
        user.setUsername(cashier.getUsername());
        user.setLastname(cashier.getLastname());
        user.setPassword(cashier.getPassword());
        user.setPosition(cashier.getPosition());
        user.setValidation(cashier.getValidation());
        em.persist(user);
    }

    public void updateCashierStatus(Integer id, Boolean validation) {
        LOG.info("updateCashierStatus");
        Cashier cashier = em.find(Cashier.class, id);
        cashier.setValidation(validation);
    }
}
