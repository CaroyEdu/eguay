/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eguay.servlet;

import eguay.dao.AuctionFacade;
import eguay.dao.UsersFacade;
import eguay.entity.Auction;
import eguay.entity.Users;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jean-
 */
@WebServlet(name = "AddProductForSaleServlet", urlPatterns = {"/AddProductForSaleServlet"})
public class AddProductForSaleServlet extends HttpServlet {
    
    @EJB AuctionFacade auctionFacade;
    @EJB UsersFacade usersFacade;

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
        
        Auction auction = new Auction();
        String str;
        
        str = (String)request.getParameter("title");
        auction.setTitle(str);
        
        str = (String)request.getParameter("description");
        auction.setDescription(str);
        
        str = (String)request.getParameter("fotourl");
        auction.setFotourl(str);
        
        Float startPrice = Float.parseFloat(request.getParameter("startprice"));
        auction.setStartprice(startPrice);
        
        Users user = new Users();
        List<Users> users = usersFacade.findAll();
        for(Users u : users)
        {
            if(u.getUserid() == 1)
            {
                user = u;
            }
        }
        auction.setSellerid(user);
        
        // Conseguimos la fecha de hoy en formato yyyy/MM/dd
        Calendar now = new GregorianCalendar();
        Date nowDate = now.getTime();
        auction.setStartdate(nowDate);
        /*
        List<Auction> list = auctionFacade.findAll();
        int id = 0;
        for(Auction a : list)
        {
            id = a.getAuctionid()+1;
        }
        auction.setAuctionid(id);
        */
        // Creamos el objeto y lo insertamos en la base de datos
        auctionFacade.create(auction);
        
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
