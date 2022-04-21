/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eguay.servlet;

import eguay.dao.AuctionFacade;
import eguay.dao.BidFacade;
import eguay.entity.Auction;
import eguay.entity.Bid;
import eguay.entity.Users;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
 * @author parsa
 */
@WebServlet(name = "FinalizeBidServlet", urlPatterns = {"/FinalizeBidServlet"})
public class FinalizeBidServlet extends HttpServlet {
    
    @EJB BidFacade bidFacade ; 
    @EJB AuctionFacade auctionFacade ; 
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
        Long id = Long.parseLong((String)request.getParameter("id"));
        Auction auction = auctionFacade.find(id);
        HttpSession session = request.getSession();
        Users user = (Users) session.getAttribute("user");
        
        Bid newBid = new Bid();
        newBid.setAuctionid(auction);
        
        String BidAmount = (String)request.getParameter("Bid");
        
        newBid.setBid(Integer.parseInt(BidAmount));
        
        newBid.setBiderid(user);
        
        bidFacade.create(newBid);
        
        
        response.sendRedirect("IndexServlet");
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
