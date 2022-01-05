package com.pos.posproject.ejb;

/**
 *
 * @author stupa
 */
import com.pos.posproject.common.UserDetails;
import com.pos.posproject.entity.User;
import com.pos.posproject.enums.UserRoles;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author stupa
 */
@Stateless
public class UserBean {

    private static final Logger LOG = Logger.getLogger(UserBean.class.getName());
    @PersistenceContext
    private EntityManager em;

    public UserDetails findById(Integer userId) {
        User user = em.find(User.class, userId);
        return new UserDetails(user.getId(), user.getFirstname(), user.getLastname(), user.getUsername(), user.getPosition(), user.getValidation());
    }

    public void createUser(String username, String first_name, String last_name, String passwordSha256, String position, Boolean validation) {
        User user = new User();
        user.setUsername(username);
        user.setFirstname(first_name);
        user.setLastname(last_name);
        user.setPassword(passwordSha256);
        user.setPosition(position);
        user.setValidation(validation);
        em.persist(user);
    }

    public UserDetails findUserById(Integer userId) {
        LOG.info("findUserById");
        User user = em.find(User.class, userId);
        return new UserDetails(user.getId(), user.getFirstname(), user.getLastname(),
                user.getUsername(), user.getPosition(), user.getValidation());
    }

    public List<UserDetails> getAllUsers() {
        LOG.info("getAllUsers");
        try {
            Query query = em.createQuery("SELECT u FROM User u");
            List<User> users = (List<User>) query.getResultList();
            return copyUsersToDetails(users);
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }

    public List<UserDetails> getAllCashiers() {
        LOG.info("getAllCashiers");
        try {
            Query query = em.createQuery("SELECT u FROM User u where u.position = 'CASHIER'");
            List<User> users = (List<User>) query.getResultList();
            return copyUsersToDetails(users);
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }

    private List<UserDetails> copyUsersToDetails(List<User> users) {
        List<UserDetails> detailsList = new ArrayList<>();
        for (User user : users) {
            UserDetails userDetails = new UserDetails(user.getId(), user.getFirstname(),
                    user.getLastname(), user.getUsername(), user.getPosition(), user.getValidation());
            detailsList.add(userDetails);
        }
        return detailsList;
    }

    public void deleteUsersByIds(List<Integer> ids) {
        LOG.info("deleteUsersByIds");
        for (Integer id : ids) {
            User user = em.find(User.class, id);
            em.remove(user);
        }
    }

    public void updateUser(Integer id, String firstName, String lastName,
            String username, String position) {
        LOG.info("updateUser");
        User user = em.find(User.class, id);
        user.setFirstname(firstName);
        user.setLastname(lastName);
        user.setUsername(username);
        user.setPosition(position);
    }
    
    public void updateCashierStatus(Integer id, Boolean validation)
    {
        LOG.info("updateCashierStatus");
        User user = em.find(User.class, id);
        user.setValidation(validation);
    }

    public User checkLogin(String username, String password) {
        User currentUser = null;
        try {
            TypedQuery query = em.createQuery(
                    "SELECT u FROM User u where u.username = :username and u.password = :password", User.class)
                    .setParameter("username", username)
                    .setParameter("password", password);
            List<User> users = query.getResultList();
            if (users.size() == 1) {
                for (User user : users) {
                    if (user.getValidation() == true) {
                        return user;
                    }
                }
            }
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
        return currentUser;
    }
}
