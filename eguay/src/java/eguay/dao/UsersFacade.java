/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eguay.dao;

import eguay.entity.Users;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author jean-
 */
@Stateless
public class UsersFacade extends AbstractFacade<Users> {

    @PersistenceContext(unitName = "eguayPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsersFacade() {
        super(Users.class);
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
    
}
