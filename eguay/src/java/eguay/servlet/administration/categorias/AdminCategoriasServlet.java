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
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author carlo
 */
@WebServlet(name = "AdminCategoriasServlet", urlPatterns = {"/Admin/Categorias"})
public class AdminCategoriasServlet extends AdminServlet {
    @EJB AdminService as;
    
    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       String category = request.getParameter("category");
        
        List<CategoryDTO> categories;
        if(category != null) {
            categories = as.filterCategories(category);
        } else {
            categories = as.getAllCategories();
        }
        
        request.setAttribute("categories", categories);
        request.getRequestDispatcher("../admin/categories.jsp").forward(request, response); 
    }
}