/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package eguay.service;

import eguay.entity.Rol;
import eguay.dto.RolDTO;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author pedro
 */
@Stateless
public class RolService {

    public static List<RolDTO> toDTO(List<Rol> rols){
        List<RolDTO> dtos = new ArrayList<RolDTO>(rols.size());
        
        for(Rol rol : rols){
            dtos.add(rol.toDTO());
        }
        
        return dtos;
    }
}
