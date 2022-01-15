package com.pos.posproject.ejb;

/**
 *
 * @author stupa
 */
import com.pos.posproject.common.UserDetails;
import com.pos.posproject.entity.User;
import java.util.ArrayList;
import java.util.List;
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

    public UserDetails findUserByUsername(String username) {
        LOG.info("findUserByUsername");
        try {
            TypedQuery query = em.createQuery(
                    "SELECT u FROM User u where u.username = :username", User.class)
                    .setParameter("username", username);
            List<User> users = query.getResultList();
            if (users.size() == 1) {
                for (User user : users) {
                    return new UserDetails(user.getId(), user.getFirstname(), user.getLastname(),
                            user.getUsername(), user.getPosition(), user.getValidation());
                }
            }
        } catch (Exception ex) {
            throw new EJBException(ex);

        }
        return null;
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
    
    public void deleteByUsername(String username) {
        LOG.info("deleteByUsername");
        try {
            TypedQuery query = em.createQuery(
                    "SELECT u FROM User u where u.username = :username", User.class)
                    .setParameter("username", username);
            List<User> users = query.getResultList();
            if (users.size() == 1) {
                for (User user : users) {                
                    em.remove(user);
                }
            }
        } catch (Exception ex) {
            throw new EJBException(ex);

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
}
