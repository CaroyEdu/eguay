/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eguay.servlet.administration.categorias;

import eguay.dto.CategoryDTO;
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
@WebServlet(name = "AdminCreateCategoriaSubmitServlet", urlPatterns = {"/Admin/Categorias/SubmitCreate"})
public class AdminCreateCategoriaSubmitServlet extends AdminServlet {
    @EJB AdminService as;
    
    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        CategoryDTO c = new CategoryDTO();
        c.setName(name);
        as.createCategory(c);
        response.sendRedirect(request.getContextPath() + "/Admin/Categorias?msg=categoria+" + c.getName() + "+creada+correctamente");
    }
}