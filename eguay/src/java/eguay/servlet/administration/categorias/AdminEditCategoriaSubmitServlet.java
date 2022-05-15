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
 * @author carlos
 */
@WebServlet(name = "AdminEditCategoriaSubmitServlet", urlPatterns = {"/Admin/Categorias/SubmitEdit"})
public class AdminEditCategoriaSubmitServlet extends AdminServlet {
    @EJB AdminService as;
    
    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long categoryId = Long.parseLong(request.getParameter("categoryId"));
        String name = request.getParameter("name");
        CategoryDTO c = new CategoryDTO();
        c.setId(categoryId);
        c.setName(name);
        as.saveCategory(c);
        response.sendRedirect(request.getContextPath() + "/Admin/Categorias?msg=categoria+" + c.getName() + "+editada+correctamente");
    }
}