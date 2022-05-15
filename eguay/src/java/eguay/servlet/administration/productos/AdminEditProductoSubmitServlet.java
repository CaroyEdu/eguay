/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eguay.servlet.administration.productos;

import eguay.dto.AuctionDTO;
import eguay.servlet.administration.usuarios.*;
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
@WebServlet(name = "AdminEditProductoSubmitServlet", urlPatterns = {"/Admin/Productos/SubmitEdit"})
public class AdminEditProductoSubmitServlet extends AdminServlet {
    @EJB AdminService as;
    
    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            Long productId = Long.parseLong(request.getParameter("productId"));
            Long categoryId = Long.parseLong(request.getParameter("category"));
            String name = request.getParameter("name");
            String description = request.getParameter("description");
            String fotoUrl = request.getParameter("fotoUrl");
            
            Float startPrice = Float.parseFloat(request.getParameter("startPrice"));
            
            
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm");
            
            AuctionDTO auction = new AuctionDTO();
            auction.setId(productId);
            auction.setCategoryId(categoryId);
            auction.setName(name);
            auction.setDescripcion(description);
            auction.setUrlFoto(fotoUrl);
            auction.setStartPrice(startPrice);
            auction.setStartDate(new Date());
            boolean closePriceEnabled = Boolean.parseBoolean(request.getParameter("closePriceEnabled"));
            boolean closeBidsEnabled = Boolean.parseBoolean(request.getParameter("closeBidsEnabled"));
            boolean closeDateEnabled = Boolean.parseBoolean(request.getParameter("closeDateEnabled"));
            
            if(closePriceEnabled) {
                auction.setClosePrice(Float.parseFloat(request.getParameter("closePrice")));
            }
            if(closeBidsEnabled) {
                auction.setCloseNumberofBids(Integer.parseInt(request.getParameter("closeBids")));
            }
            if(closeDateEnabled) {
                Date d = sdf.parse(request.getParameter("closeDate"));
                auction.setCloseDate(d);
            }
            
            
            as.saveProduct(auction);
            response.sendRedirect(request.getContextPath() + "/Admin/Productos?msg=producto+" + auction.getName() + "+editado+correctamente");
        } catch (ParseException ex) {
            Logger.getLogger(AdminCreateUsuarioServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
