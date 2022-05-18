/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eguay.servlet.administration.usuarios;

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
 * @author carlos
 */
@WebServlet(name = "AdminDeleteUsuarioServlet", urlPatterns = {"/Admin/Usuarios/Delete"})
public class AdminDeleteUsuarioServlet extends AdminServlet {
    @EJB AdminService as;
    
    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        as.deleteUser(id);
        response.sendRedirect(request.getContextPath() + "/Admin/Usuarios?msg=usuario+" + id + "+borrado+correctamente");
    }
}
