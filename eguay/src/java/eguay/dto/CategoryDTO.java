/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eguay.dto;

import eguay.entity.Category;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author pedro
 */
public class CategoryDTO {
    // DB
    private Long id;
    
    // Conceptual
    private String name;

    public CategoryDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
