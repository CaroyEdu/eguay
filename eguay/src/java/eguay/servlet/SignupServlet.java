/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eguay.servlet;

import eguay.dao.UsersFacade;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import eguay.entity.Users;
import eguay.service.UserService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Parsa zendehdel nobari
 */
@WebServlet(name = "SignupServlet", urlPatterns = {"/SignupServlet"})
public class SignupServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @EJB UsersFacade usersFacade;
    @EJB UserService usersService;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        Users user = new Users();
        String username = (String)request.getParameter("username");
        String name = (String)request.getParameter("name");
        String surname = (String)request.getParameter("surname");
        String address = (String)request.getParameter("address");
        String city = (String)request.getParameter("city");
        String email = (String)request.getParameter("email");
        String country = (String)request.getParameter("country");
        String password = (String)request.getParameter("password");
        Date birthdayDate = null;
        String birthday = (String) request.getParameter("birthday");
        try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                birthdayDate = sdf.parse(birthday);
               
            } catch (ParseException ex) {
                Logger.getLogger(AddProductForSaleServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        int sexId = 0 ; 
        String sex = (String)request.getParameter("sex");
        if(sex=="Hombre")sexId =1; 
        else if (sex=="Mujer")sexId=2;
        else sexId = 3;
        
        usersService.createUser(user, username, name, surname, address, city, email, country, password, birthdayDate, sexId);
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
