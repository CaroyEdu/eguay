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
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author carlos
 */
@WebServlet(name = "AdminUsuariosServlet", urlPatterns = {"/Admin/Usuarios", "/Admin/Usuarios/"})
public class AdminUsuariosServlet extends AdminServlet {
    @EJB AdminService as;
    
    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        
        List<UserDTO> users;
        if(username != null) {
            users = as.filterUsers(username);
        } else {
            users = as.getAllUsers();
        }
        
        request.setAttribute("users", users);
        request.getRequestDispatcher("../admin/users.jsp").forward(request, response); 
    }
}
