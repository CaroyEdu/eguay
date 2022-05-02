/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eguay.services;

import eguay.dao.GroupsFacade;
import eguay.dao.UsersFacade;
import eguay.entity.Groups;
import eguay.entity.Users;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author pedro
 */
public class GroupServices {    
    public static void createNewGroupFromSelectedGroups(HttpServletRequest request, String groupCheckedLabel, GroupsFacade groupsFacade) {
        List<Long> groupsIds;
        List<Groups> selectedGroups;
        Groups newGroup = new Groups();
        ServletUtils<Groups> servletUtils = new ServletUtils<>();
        
        groupsIds = servletUtils.getIdsFromCheckedLong(request, groupCheckedLabel);
        selectedGroups = servletUtils.getObjectsFromIdsLong(groupsIds, groupsFacade);
        newGroup.addAllGroups(selectedGroups);
        
        if(!newGroup.getUsersList().isEmpty())
            groupsFacade.create(newGroup);
    }  
    
    public static void newGroupFromSelectedUsers(HttpServletRequest request, HttpServletResponse response, String groupNameLabel, String userCheckedLabel, GroupsFacade groupsFacade, UsersFacade usersFacade) throws IOException, ServletException {
        ServletUtils<Users> servletUtils;
        String name;
        //Long id;
        List<Integer> usersIds;
        List<Users> users;
        Groups newGroup, group;
        newGroup = new Groups();
        servletUtils = new ServletUtils<>();
        name = request.getParameter(groupNameLabel);
        usersIds = ServletUtils.getIdsFromChecked(request, userCheckedLabel);
        users = servletUtils.getObjectsFromIds(usersIds, usersFacade);
        newGroup.setName(name);
        newGroup.setUsersList(users);
        groupsFacade.create(newGroup);
        for(Users user : users){
            user.addToGroup(newGroup);
            usersFacade.edit(user);
        }
        if(newGroup.getGroupid() != null){
            request.setAttribute("users", usersFacade.findAll());
            request.setAttribute("group", newGroup);
            request.getRequestDispatcher("group.jsp").forward(request, response);
        }else{
            response.sendRedirect("ShowGroupList");
        }
    }
    
    public static void removeSelectedGroups(HttpServletRequest request, String groupCheckedLabel, GroupsFacade groupsFacade) {
        List<Long> groupsIds;
        List<Groups> selectedGroups;
        ServletUtils<Groups> servletUtils = new ServletUtils<>();
        
        groupsIds = servletUtils.getIdsFromCheckedLong(request, groupCheckedLabel);
        selectedGroups = servletUtils.getObjectsFromIdsLong(groupsIds, groupsFacade);
        
        for(Groups group : selectedGroups){
            groupsFacade.remove(group);
        }
    }
}
