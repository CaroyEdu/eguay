/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eguay.dto;

import java.util.Objects;

/**
 *
 * @author Pedro Antonio Benito Rojano
 */
public class RolDTO {
    // DB
    private Long id;
    
    // Conceptual
    private String name;
    
    public RolDTO(){       
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
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final RolDTO other = (RolDTO) obj;
        return Objects.equals(this.id, other.getId());
    }
}
