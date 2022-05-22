/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eguay.servlet.administration.productos;

import eguay.dto.AuctionDTO;
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
@WebServlet(name = "AdminProductosServlet", urlPatterns = {"/Admin/Productos"})
public class AdminProductosServlet extends AdminServlet {
    @EJB AdminService as;
    
    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       String product = request.getParameter("product");
        
        List<AuctionDTO> products;
        if(product != null) {
            products = as.filterProducts(product);
        } else {
            products = as.getAllProducts();
        }
        
        request.setAttribute("products", products);
        request.getRequestDispatcher("../admin/products.jsp").forward(request, response); 
    }
}
