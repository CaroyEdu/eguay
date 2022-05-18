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
 * @author Pedro Antonio Benito Rojano
 */
@Stateless
public class CategoryService {
    @EJB CategoryFacade categoryFacade;

    // Queries
    
    public List<CategoryDTO> getAllCategories(){
        List<Category> categories = categoryFacade.findAll();
        return CategoryService.toDTO(categories);
    }
    
    public List<CategoryDTO> getAllCategoriesDTO(){
        List<Category> categories = categoryFacade.findAll();
        return Category.toDTO(categories) ; 
    }
    
    // Logic
    public static List<CategoryDTO> toDTO(List<Category> categories){
        List<CategoryDTO> dtos = new ArrayList<>(categories.size());
        
        for(Category category : categories){
            dtos.add(category.toDTO());
        }
        
        return dtos;
    }
}
