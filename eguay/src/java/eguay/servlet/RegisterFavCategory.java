/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eguay.servlet;

import eguay.dao.CategoryFacade;
import eguay.entity.Category;
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
@WebServlet(name = "RegisterFavCategory", urlPatterns = {"/RegisterFavCategory"})
public class RegisterFavCategory extends HttpServlet {
@EJB CategoryFacade categoryFacade;
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
        
        
        //str = (String)request.getParameter("category");
        HttpSession session = request.getSession();
        Users user = (Users) session.getAttribute("user");
            /* TODO output your page here. You may use following sample code. */
        List<Category> categoryFavList = user.getCategoryList(); 
        if(categoryFavList == null) categoryFavList = new ArrayList() ; 
        List<Category> categoryList = categoryFacade.findAll();
        for(Category category : categoryList ){
            String check = (String)request.getParameter(category.getCategoryid().toString()); 
            if(check != null )
            {
                if(!categoryFavList.contains(category)){  
                    categoryFavList.add(category);
                    category.getUsersList().add(user);
                    categoryFacade.edit(category);
                }
             }
        }
        response.sendRedirect("AddFavCategoryServlet");
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
