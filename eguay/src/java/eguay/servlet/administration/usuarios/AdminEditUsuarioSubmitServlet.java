/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eguay.servlet.administration.usuarios;

import eguay.dto.UserDTO;
import eguay.service.AdminService;
import eguay.servlet.administration.AdminServlet;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author carlos
 */
@WebServlet(name = "AdminEditUsuarioSubmitServlet", urlPatterns = {"/Admin/Usuarios/SubmitEdit"})
public class AdminEditUsuarioSubmitServlet extends AdminServlet {
    @EJB AdminService as;
    
    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String username = request.getParameter("username");
            String name = request.getParameter("name");
            String surname = request.getParameter("surname");
            String address = request.getParameter("address");
            String city = request.getParameter("city");
            String email = request.getParameter("email");
            String country = request.getParameter("country");
            String password = request.getParameter("password");
            String birthday = request.getParameter("birthday");
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            int userId = Integer.parseInt(request.getParameter("userId"));
            int sex = Integer.parseInt(request.getParameter("sex"));
            String[] roles = request.getParameterValues("roleIds");
            if(roles == null) {
                roles = new String[] {};
            }
            List<Long> roleIds = new ArrayList<>();
            for(String id : roles) {
                roleIds.add(Long.parseLong(id));
            }
            
            roleIds.add(0l);
            Date birthdayDate = sdf.parse(birthday);
            
            UserDTO user = new UserDTO();
            user.setUsername(username);
            user.setName(name);
            user.setSurname(surname);
            user.setAddress(address);
            user.setCity(city);
            user.setEmail(email);
            user.setCountry(country);
            user.setPassword(password);
            user.setBirthyear(birthdayDate);
            user.setId(userId);
            user.setSex(sex);
            user.setRoleIds(roleIds);
            
            as.saveUser(user);
            response.sendRedirect(request.getContextPath() + "/Admin/Usuarios?msg=usuario+" + user.getUsername() + "+editado+correctamente");
        } catch (ParseException ex) {
            Logger.getLogger(AdminCreateUsuarioServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
