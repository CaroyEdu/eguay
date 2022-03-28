/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eguay.dao;

import eguay.entity.Groups;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jean-
 */
@Stateless
public class GroupsFacade extends AbstractFacade<Groups> {

    @PersistenceContext(unitName = "eguayPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GroupsFacade() {
        super(Groups.class);
    }
    
}
