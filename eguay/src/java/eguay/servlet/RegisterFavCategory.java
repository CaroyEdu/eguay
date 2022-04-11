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
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
        List<Category> categoryFavList = new ArrayList();
        List<Category> categoryList = categoryFacade.findAll();
        for(Category category : categoryList ){
            String check = (String)request.getParameter(category.getCategoryid().toString()); 
            if(check != null )
            {
               categoryFavList.add(category);
               category.getUsersList().add(user);
             }
        }
        user.setCategoryList(categoryFavList);
        
        
         out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet NewServlet</title>");            
            out.println("</head>");
            out.println("<body>");
      
            out.println("<h1>Servlet NewServlet at " + user.getUsername()+ "</h1>");
            
        for(Category category : user.getCategoryList() ){
            
            out.println("<h1>Servlet NewServlet at " + "kskks"+ "</h1>");
             
        }
        
        
       // categoryList.add(0, new Category(Long.parseLong(str)));
       // auction.setCategoryList(categoryList);
            out.println("</body>");
            out.println("</html>");
       
    }
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
