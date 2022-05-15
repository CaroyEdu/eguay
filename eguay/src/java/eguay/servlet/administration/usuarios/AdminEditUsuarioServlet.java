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
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author carlo
 */
@WebServlet(name = "AdminEditUsuarioServlet", urlPatterns = {"/Admin/Usuarios/Edit"})
public class AdminEditUsuarioServlet extends AdminServlet {
    @EJB AdminService as;
    
    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        UserDTO user = as.getUser(id);
        request.setAttribute("user", user);
        request.getRequestDispatcher("../../admin/editUser.jsp").forward(request, response);
    }
}
