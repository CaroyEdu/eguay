/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eguay.servlet.administration;

import eguay.dto.UserDTO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author carlos
 */
public abstract class AdminServlet extends HttpServlet {
    
        /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected abstract void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if(!checkAdmin(request, response)) {
           return;
        }
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if(!checkAdmin(request, response)) {
            return;
        }
        processRequest(request, response);
    }
    
    protected boolean checkAdmin(HttpServletRequest request, HttpServletResponse response) 
                throws ServletException, IOException { 
        // TODO: Change attribute name
        UserDTO user = (UserDTO) request.getSession().getAttribute("userDTO");
        
        boolean is = user != null && user.isAdmin();
        
        if(!is) {
            response.sendRedirect(request.getContextPath() + "/IndexServlet");
        }
        
        return is; 
    }
}
