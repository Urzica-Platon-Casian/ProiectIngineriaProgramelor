/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pos.posproject.ejb;

/**
 *
 * @author stupa
 */
import com.pos.posproject.common.UserDetails;
import com.pos.posproject.entity.User;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author stupa
 */
@Stateless
public class UserBean {

    private static final Logger LOG = Logger.getLogger(UserBean.class.getName());
    @PersistenceContext
    private EntityManager em;
    
    public UserDetails findById(Integer userId){
        User user=em.find(User.class, userId);
        return new UserDetails(user.getId(),user.getFirstname(),user.getLastname(),user.getUsername(),user.getPosition());
    }

    public void createUser(String username, String first_name,String last_name, String passwordSha256, String position) {
        User user = new User();
        user.setUsername(username);
        user.setFirstname(first_name);
        user.setLastname(last_name);
       
        user.setPassword(passwordSha256);
        user.setPosition(position);

        em.persist(user);
    }

    public Collection<String> findUsernames(Collection<Integer> userIds) {
        LOG.info("findUsernames");
        List<String> usernames = (List<String>) em.createQuery("SELECT u.username FROM User u WHERE u.id IN ?1")
                .setParameter(1, userIds)
                .getResultList();
        return usernames;
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
            UserDetails userDetails = new UserDetails(user.getId(),
                    user.getUsername(),
                    user.getFirstname(),
                    user.getLastname(),
                    user.getPosition());
            detailsList.add(userDetails);
        }
        return detailsList;
    }

    public void update(int userId, String username, String first_name, String last_name, String position) {
        LOG.info("updateUser");
        User user=em.find(User.class, userId);
        user.setFirstname(first_name);
        user.setLastname(last_name);
        user.setUsername(username);
        user.setPosition(position);
    }

    public void deleteUsersByIds(Collection<Integer> ids) {
        LOG.info("deleteUsersByIds");
        for(Integer id:ids){
            User user=em.find(User.class, id);
            em.remove(user);
        }
        
    }
}
