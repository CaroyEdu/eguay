/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package eguay.servlet;

import eguay.dao.GroupsFacade;
import eguay.entity.Groups;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author pedro
 */
@WebServlet(name = "NewGroupFromSelectedGroups", urlPatterns = {"/NewGroupFromSelectedGroups"})
public class NewGroupFromSelectedGroups extends HttpServlet {
    
    @EJB GroupsFacade groupsFacade;

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
        
        createNewGroupFromSelected(request);
        response.sendRedirect("ShowGroupList");
    }
    
    private void createNewGroupFromSelected(HttpServletRequest request) {
        List<Long> groupsIds;
        List<Groups> selectedGroups;
        Groups newGroup = new Groups();
        
        groupsIds = getIdsFromCheckedGroups(request);
        selectedGroups = getGroupsFromIds(groupsIds);
        newGroup.addAllGroups(selectedGroups);
        
        if(!newGroup.getUsersList().isEmpty())
            groupsFacade.create(newGroup);
    }

    private List<Long> getIdsFromCheckedGroups(HttpServletRequest request) {
        String checkedGroupsIds = request.getParameter("groupCheck");
        List<Long> groupIds = new LinkedList<>();
        
        for(String id : checkedGroupsIds.split(",")){
            groupIds.add(Long.valueOf(id));
        }
        
        return groupIds;
    }

    private List<Groups> getGroupsFromIds(List<Long> groupsIds) {
        List<Groups> groups = new LinkedList<>();
        
        for(Long groupId : groupsIds){
            groups.add(groupsFacade.find(groupId));
        }
        
        return groups;
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
