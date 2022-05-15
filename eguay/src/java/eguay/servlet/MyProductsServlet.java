/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eguay.servlet;

import eguay.dao.AuctionFacade;
import eguay.entity.Auction;
import eguay.entity.Category;
import eguay.entity.Users;
import eguay.service.AuctionService;
import eguay.service.CategoryService;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Roy Caro Jean Edouard
 */
@WebServlet(name = "MyProductsServlet", urlPatterns = {"/MyProductsServlet"})
public class MyProductsServlet extends HttpServlet {
    
    @EJB CategoryService categoryService;
    @EJB AuctionService auctionService; 
    @EJB AuctionFacade auctionFacade;
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        HttpSession session = request.getSession();
        Users user = (Users) session.getAttribute("user");
        String filter = (String) request.getParameter("searchbar");
        
        List<Category> categoryList =  categoryService.getAllCategories();
        List<Auction> userAuctions = auctionService.filterAuctionOrederedByUser(user.getUserid());
        
        if(filter==null)
        {
            request.setAttribute("userAuctions", userAuctions);
        }else{
            List<Auction> auctionList = auctionService.filterAuctionByUser(filter, user.getUserid());
            if(auctionList.isEmpty())
            {
                request.setAttribute("userAuctions", userAuctions);
                request.setAttribute("error", "No se ha encontrado ninguna subasta con este filtro. Se ha devuelto el listado completo de sus subastas.");
            }else{
                request.setAttribute("userAuctions", auctionList);
            }
        }
        request.setAttribute("categoryList", categoryList);
        
        request.getRequestDispatcher("myProducts.jsp").forward(request, response);
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
