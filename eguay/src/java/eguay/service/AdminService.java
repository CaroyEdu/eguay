/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eguay.service;

import eguay.dao.RolFacade;
import eguay.dao.UsersFacade;
import eguay.dto.UserDTO;
import eguay.entity.Rol;
import eguay.entity.Users;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author carlos
 */
@Stateless
public class AdminService {
    @EJB UsersFacade userFacade;
    @EJB RolFacade rolFacade;
    
    public List<UserDTO> getAllUsers(){
        return Users.toDTO(userFacade.getAllOrdered());
    }
    
    public List<UserDTO> filterUsers(String username) {
        return Users.toDTO(userFacade.filter(username));
    }
    
    public void createUser(String username, String name, String surname,
            String address, String city, String email, String country,
            String password, Date birthday, int sex, List<Integer> roleIds){
        List<Rol> roles = roleIds.stream()
                .map((rId) -> rolFacade.find(rId))
                .collect(Collectors.toList());
        
        Users user = new Users();
        user.setName(name);
        user.setAddress(address);
        user.setSurname(surname);
        user.setCity(city);
        user.setEmail(email);
        user.setCountry(country);
        user.setPassword(password);
        user.setUsername(username);
        user.setBirthyear(birthday);
        user.setSex(sex);
        user.setRolList(roles);
        
        userFacade.create(user);
    }

    public UserDTO getUser(int id) {
        return userFacade.find(id).toDTO();
    }

    public void saveUser(UserDTO user) {
        List<Rol> roles = user.getRoleIds().stream()
            .map((rId) -> rolFacade.find(rId))
            .collect(Collectors.toList());
        Users u = userFacade.find(user.getId());
        u.setUsername(user.getUsername());
        u.setName(user.getName());
        u.setSurname(user.getSurname());
        u.setAddress(user.getAddress());
        u.setCity(user.getCity());
        u.setEmail(user.getEmail());
        u.setCountry(user.getCountry());
        u.setPassword(user.getPassword());
        u.setBirthyear(user.getBirthyear());
        u.setSex(user.getSex());
        u.setRolList(roles);
        userFacade.edit(u);
    }

    public void deleteUser(Integer id) {
        Users u = userFacade.find(id);
        userFacade.remove(u);
    }

    public void createUser(UserDTO user) {
        List<Rol> roles = user.getRoleIds().stream()
            .map((rId) -> rolFacade.find(rId))
            .collect(Collectors.toList());
        Users u = new Users();
        u.setUsername(user.getUsername());
        u.setName(user.getName());
        u.setSurname(user.getSurname());
        u.setAddress(user.getAddress());
        u.setCity(user.getCity());
        u.setEmail(user.getEmail());
        u.setCountry(user.getCountry());
        u.setPassword(user.getPassword());
        u.setBirthyear(user.getBirthyear());
        u.setSex(user.getSex());
        u.setRolList(roles);
        userFacade.create(u);
    }
}
