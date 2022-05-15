/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eguay.servlet.administration.categorias;

import eguay.servlet.administration.AdminServlet;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author carlo
 */
@WebServlet(name = "AdminCreateCategoriaServlet", urlPatterns = {"/Admin/Categorias/Nuevo"})
public class AdminCreateCategoriaServlet extends AdminServlet {
    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("../../admin/newCategory.jsp").forward(request, response); 
    }
}
