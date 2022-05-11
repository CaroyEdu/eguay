/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eguay.service;

import eguay.dao.CategoryFacade;
import eguay.entity.Category;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author parsa
 */
@Stateless
public class CategoryService {
@EJB CategoryFacade categoryFacade;
    public List<Category> getAllCategories(){
        
        List<Category> categories = categoryFacade.findAll();
        return categories ; 
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
