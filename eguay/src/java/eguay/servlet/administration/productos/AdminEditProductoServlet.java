/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eguay.servlet.administration.productos;

import eguay.dto.AuctionDTO;
import eguay.dto.CategoryDTO;
import eguay.service.AdminService;
import eguay.service.CategoryService;
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
@WebServlet(name = "AdminEditProductoServlet", urlPatterns = {"/Admin/Productos/Edit"})
public class AdminEditProductoServlet extends AdminServlet {
    @EJB AdminService as;
    @EJB CategoryService cs;
    
    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        long id = Long.parseLong(request.getParameter("id"));
        AuctionDTO producto = as.getProduct(id);
        List<CategoryDTO> categorias = cs.getAllCategoriesDTO();
        request.setAttribute("categorias", categorias);
        request.setAttribute("producto", producto);
        request.getRequestDispatcher("../../admin/editProduct.jsp").forward(request, response);
    }
}
