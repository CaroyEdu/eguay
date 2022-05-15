/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eguay.dao;

import eguay.entity.Auction;
import eguay.entity.Category;
import eguay.entity.Users;
import eguay.service.UserService;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Roy Caro Jean Edouard 33% Parsa zendehdel nobari 34% Pedro Antonio Benito Rojano 33%
 */
@Stateless
public class UsersFacade extends AbstractFacade<Users> {

    @PersistenceContext(unitName = "eguayPU")
    private EntityManager em;
    
    @EJB
    UserService userService;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsersFacade() {
        super(Users.class);
    }
    
    public List<Users> getAllOrdered() {
        return this.em.createQuery("SELECT u FROM Users u ORDER BY u.userid").getResultList();
    }
    
        public List<Users> filter(String username) {
        return this.em.createQuery("SELECT u FROM Users u WHERE u.username LIKE :username ORDER BY u.userid")
                .setParameter("username", "%" + username + "%")
                .getResultList();
    }
    
    public Users userLogin(String username, String password)
    {
        Query q;
        q = this.em.createQuery("SELECT u FROM Users u WHERE u.username = :username AND u.password = :password");
        q.setParameter("username", username);
        q.setParameter("password", password);
        List<Users> userList = q.getResultList();
        if(userList == null || userList.isEmpty())
        {
            return null;
        }
        else
        {
            return userList.get(0);
        }
    }
    
    public Users getUserByID(Long id)
    {
        Query q;
        q = this.em.createQuery("SELECT u FROM Users u WHERE u.userid = :id");
        q.setParameter("id", id);
        return (Users) q.getSingleResult();
    }
    
    public List<Auction> findPurchasedAuctionsByTitleAndUser(String title, Users userid)
    {
        Query q;
        q = this.em.createQuery("SELECT c FROM Auction c WHERE c.usersList1 = :userid AND c.title LIKE :title");
        q.setParameter("title", '%' + title + '%');
        q.setParameter("userid", userid);
        return q.getResultList();
    }
    
    public List<Auction> findFavAuctionsByTitleAndUser(String title, Users userid)
    {
        Query q;
        q = this.em.createQuery("SELECT c FROM Auction c WHERE c.usersList = :userid AND c.title LIKE :title");
        q.setParameter("title", '%' + title + '%');
        q.setParameter("userid", userid);
        return q.getResultList();
    }

    public List<Users> findAll(List<Integer> userIds) {
        return this.em.createQuery("SELECT u FROM Users u WHERE u.userid IN :userIds")
                .setParameter("userIds", userIds)
                .getResultList();
    }
    
    public Users findMarketing(){
        return (Users) this.em.createQuery("SELECT u FROM Users u WHERE u.username = 'marketing'")
                .getSingleResult();
    }
}
