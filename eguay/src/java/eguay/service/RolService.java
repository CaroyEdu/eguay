/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package eguay.service;

import eguay.dao.RolFacade;
import eguay.entity.Rol;
import eguay.dto.RolDTO;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author pedro
 */
@Stateless
public class RolService {
    @EJB RolFacade rolFacade;
    
    public List<RolDTO> findAll() {
        return Rol.toDTO(rolFacade.findAll());
    }
}
