/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package eguay.servlet.Group;

import eguay.dao.GroupsFacade;
import eguay.dao.UsersFacade;
import eguay.dto.CategoryDTO;
import eguay.dto.GroupDTO;
import eguay.service.CategoryService;
import eguay.service.GroupService;
import eguay.service.UserService;
import eguay.services.ServletUtils;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Pedro Antonio Benito Rojano
 */
@WebServlet(name = "ShowSelectedGroup", urlPatterns = {"/ShowSelectedGroup"})
public class ShowSelectedGroup extends HttpServlet {

    @EJB UserService userService;
    @EJB GroupService groupService;
    @EJB CategoryService categoryService;

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
        
        Long groupId = (Long) request.getAttribute("id"); 
        
        if(groupId == null){
            groupId = ServletUtils.getIdLong(request, "id");
        }
        GroupDTO group = groupService.getGroup(groupId);
        
        List<CategoryDTO> categoryList = categoryService.getAllCategories();
        request.setAttribute("categoryList", categoryList);
        
        request.setAttribute("group", group);
        request.setAttribute("usersMap", groupService.GetUsersInGroupMap(group));
        request.getRequestDispatcher("group/group.jsp").forward(request, response);
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
