/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package eguay.service;

import eguay.dao.GroupsFacade;
import eguay.dao.UsersFacade;
import eguay.entity.Groups;
import eguay.entity.Users;
import eguay.services.ServletUtils;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.StringJoiner;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author pedro
 */
@Stateless
public class GroupService {
    @EJB GroupsFacade groupsFacade;
    @EJB UsersFacade usersFacade;
    
    @EJB UserService userService;
    
    // Query
    
    public List<Groups> getAllGroups() {
        return (List<Groups>) this.groupsFacade.findAll();
    }

    public Groups getGroup(long groupId) {
        return (Groups) this.groupsFacade.find(groupId);
    }
    
    // Extra functionalities
    
    public void add(Groups group, Users user){
        createUserListIfDontExist(group);
        List<Users> userList = group.getUsersList();
        userList.add(user);
        group.setUsersList(userList);
    }
     
    public void addAll(Groups group, List<Users> users){
        if(users != null && !users.isEmpty()){
            createUserListIfDontExist(group);
            List<Users> userList = group.getUsersList();
            userList.addAll(users);
            group.setUsersList(userList);
        }
    }
    
    public void addAllUsersInGroups(Groups group, List<Groups> groups){
        if(groups!= null && !groups.isEmpty()){
            createUserListIfDontExist(group);
            for(Groups groupToAdd : groups){
                addAll(group, groupToAdd.getUsersList());
            }
        }
    }
    
    public boolean contains(Groups group, Users user){
        return group.getUsersList().contains(user);
    }
    
    private void createUserListIfDontExist(Groups group){
        if(group.getUsersList() == null)
                group.setUsersList(new LinkedList<>());
    }

    // Logic
    
    public void createNewGroupFromSelectedGroups(HttpServletRequest request, String groupCheckedLabel) {
        List<Long> groupsIds;
        List<Groups> selectedGroups;
        Groups newGroup = new Groups();
        ServletUtils<Groups> servletUtils = new ServletUtils<>();
        
        groupsIds = servletUtils.getIdsFromCheckedLong(request, groupCheckedLabel);
        selectedGroups = servletUtils.getObjectsFromIdsLong(groupsIds, groupsFacade);
        
        newGroup.setName(concatGroupNames(selectedGroups));
        addAllUsersInGroups(newGroup, selectedGroups);
        
        if(!newGroup.getUsersList().isEmpty())
            groupsFacade.create(newGroup);
    }  
    
    public void newGroupFromSelectedUsers(HttpServletRequest request, HttpServletResponse response, String groupNameLabel, String userCheckedLabel) throws IOException, ServletException {
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
            userService.addToGroup(user, newGroup);
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
    
    public void removeSelectedGroups(HttpServletRequest request, String groupCheckedLabel) {
        List<Long> groupsIds;
        List<Groups> selectedGroups;
        ServletUtils<Groups> servletUtils = new ServletUtils<>();
        
        groupsIds = servletUtils.getIdsFromCheckedLong(request, groupCheckedLabel);
        selectedGroups = servletUtils.getObjectsFromIdsLong(groupsIds, groupsFacade);
        
        for(Groups group : selectedGroups){
            groupsFacade.remove(group);
        }
    }
    
    private String concatGroupNames(List<Groups> groups){
        StringJoiner sj = new StringJoiner("-");
        
        for(Groups group : groups){
            sj.add(group.getName());
        }
        
        return sj.toString();
    }
}
