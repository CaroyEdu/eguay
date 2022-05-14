/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eguay.service;

import eguay.dao.CategoryFacade;
import eguay.dto.CategoryDTO;
import eguay.entity.Category;
import java.util.ArrayList;
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

    // Queries
    
    public List<Category> getAllCategories(){
        
        List<Category> categories = categoryFacade.findAll();
        return categories ; 
    }
    
    // Logic
    public static List<CategoryDTO> toDTO(List<Category> categories){
        List<CategoryDTO> dtos = new ArrayList<CategoryDTO>(categories.size());
        
        for(Category category : categories){
            dtos.add(category.toDTO());
        }
        
        return dtos;
    }
}
