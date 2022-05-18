/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eguay.dao;

import eguay.entity.Category;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Automatico
 */
@Stateless
public class CategoryFacade extends AbstractFacade<Category> {

    @PersistenceContext(unitName = "eguayPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CategoryFacade() {
        super(Category.class);
    }
    
    public List<Category> getAllOrdered() {
        return this.em.createQuery("SELECT c FROM Category c ORDER BY c.categoryid").getResultList();
    }
    
    public List<Category> filter(String name) {
        return this.em.createQuery("SELECT c FROM Category c WHERE c.name LIKE :name ORDER BY c.categoryid")
                .setParameter("name", "%" + name + "%")
                .getResultList();
    }
    
}
