/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eguay.servlet;

import eguay.dto.AuctionDTO;
import eguay.dto.CategoryDTO;
import eguay.dto.UserDTO;
import eguay.entity.Auction;
import eguay.service.AuctionService;
import eguay.service.UserService;
import eguay.service.MailService;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(name = "AddProductForSaleServlet", urlPatterns = {"/AddProductForSaleServlet"})
public class AddProductForSaleServlet extends HttpServlet {
    
    @EJB AuctionService auctionService;
    @EJB UserService userService;
    @EJB MailService mailService;

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
        
        // Primero cogemos la sesión actual para saber quién es el usuario que está añadiendo el producto
        HttpSession session = request.getSession();
        
        AuctionDTO auction = new AuctionDTO();
        String str;
        String check;
        
        // Definimos el usuario, título, descrición, URL de la foto y precio inicial
        UserDTO user = (UserDTO) session.getAttribute("user");
        auction.setSellerID(Long.parseLong(user.getId().toString()));
        str = (String)request.getParameter("title");
        auction.setName(str);
        str = (String)request.getParameter("description");
        auction.setDescription(str);
        str = (String)request.getParameter("fotourl");
        auction.setFotourl(str);
        Float startPrice = Float.parseFloat(request.getParameter("startprice"));
        auction.setStartPrice(startPrice);
        check = (String) request.getParameter("checkBoxClosePrice");
        str = (String) request.getParameter("inputClosePrice");
        if(check != null && check.equals("on"))
        {
            if(!str.equals(""))
            {
                auction.setClosePrice(Float.parseFloat(str));
            }
        }
        
        check = (String) request.getParameter("checkBoxCloseNumberOfBids");
        str = (String) request.getParameter("inputCloseNumberOfBids");
        if(check != null && check.equals("on"))
        {
            if(!str.equals(""))
            {
                auction.setCloseNumberofBids(Integer.parseInt(str));
            }
        }
        
        check = (String) request.getParameter("checkBoxCloseDate");
        str = (String) request.getParameter("inputCloseDate");
        String time = (String) request.getParameter("inputCloseDateTime");
        if(check != null && check.equals("on"))
        {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String dateInString = str + " " + time + ":00";
            try {
                Date date = sdf.parse(dateInString);
                auction.setCloseDate(date);
            } catch (ParseException ex) {
                Logger.getLogger(AddProductForSaleServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        // Conseguimos la fecha de hoy en formato yyyy/MM/dd
        Calendar now = new GregorianCalendar();
        Date nowDate = now.getTime();
        auction.setStartDate(nowDate);
        auction.setActive(true);
        
        // Añadimos las diferentes categorías como una lista
        str = (String)request.getParameter("category");
        List<CategoryDTO> categoryList = new ArrayList();
        CategoryDTO category = new CategoryDTO();
        category.setId(Long.parseLong(str));
        categoryList.add(category);
        auction.setCategoryList(categoryList);
        
        // Creamos/Editamos el objeto y lo insertamos en la base de datos
        String auctionid = request.getParameter("auctionid");
        if(auctionid.equals("")){
            auctionService.createAuction(auction);
            
    /*        List<AuctionDTO> usersSubmitedAuctions = user.getAuctions();
            if(usersSubmitedAuctions == null) usersSubmitedAuctions = new ArrayList();
            usersSubmitedAuctions.add(auction);
            user.setAuctions(usersSubmitedAuctions);     */
            
            userService.AddAuctionToOwner(user,auction);
        }else{
            auction.setId(Long.parseLong(auctionid));
            this.auctionService.editAuction(auction);
        }
        
        // Una vez creado e insertado el objeto, nos volvemos a la página de inicio
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
