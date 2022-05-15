/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package eguay.service;

import eguay.dao.GroupsFacade;
import eguay.dao.UsersFacade;
import eguay.dto.GroupDTO;
import eguay.dto.UserDTO;
import eguay.entity.Groups;
import eguay.entity.Users;
import eguay.services.ServletUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
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
    
    public List<GroupDTO> getAllGroupsDTO() {
        return toDTO(getAllGroups());
    }
    
    public List<Groups> getAllGroups() {
        return groupsFacade.findAll();
    }

    public GroupDTO getGroupDTO(long groupId) {
        return this.groupsFacade.find(groupId).toDTO();
    }
    
    public Groups getGroup(long groupId) {
        return this.groupsFacade.find(groupId);
    }
    
    public Groups getGroup(GroupDTO group) {
        return getGroup(group.getId());
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
    
    public boolean contains(GroupDTO groupDTO, UserDTO userDTO){
        Groups group = getGroup(groupDTO.getId());
        Users user = userService.getUser(userDTO.getId());
        return group.getUsersList().contains(user);
    }
    
    private void createUserListIfDontExist(Groups group){
        if(group.getUsersList() == null)
                group.setUsersList(new LinkedList<>());
    }
    
    public static List<GroupDTO> toDTO(List<Groups> groups){
        List<GroupDTO> dtos = new ArrayList<>(groups.size());
        
        for(Groups group : groups){
            dtos.add(group.toDTO());
        }
        
        return dtos;
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
    
    public void newGroupFromSelectedUsers(HttpServletRequest request, HttpServletResponse response, String groupIdLabel, String groupNameLabel, String userCheckedLabel) throws IOException, ServletException {
        ServletUtils<Users> servletUtils;
        String formName, originalGroupName = null;
        Integer originalGroupId;
        //Long id;
        List<Integer> usersIds;
        List<Users> users;
        Groups newGroup, originalGroup;
        newGroup = new Groups();
        servletUtils = new ServletUtils<>();
        
        originalGroupId = ServletUtils.getId(request, "id");
        if(originalGroupId != null){
            originalGroup = getGroup(originalGroupId);
            originalGroupName = originalGroup.getName();
        }
        
        formName = request.getParameter(groupNameLabel);
        if(originalGroupId != null && formName.equals(originalGroupName)){
            newGroup.setName(originalGroupName + "2");
        }else{
            newGroup.setName(formName);
        }
        
        usersIds = ServletUtils.getIdsFromChecked(request, userCheckedLabel);
        users = servletUtils.getObjectsFromIds(usersIds, usersFacade);
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

    public List<UserDTO> getUsersInGroup(GroupDTO groupDTO) {
        Groups group = getGroup(groupDTO.getId());
        return userService.toDTO(group.getUsersList());
    }

    public Map<UserDTO, Boolean> GetUsersInGroupMap(GroupDTO groupDTO) {
        HashMap<UserDTO, Boolean> map = new HashMap<>();
        
        Groups group = getGroup(groupDTO);
        List<UserDTO> allUsers = userService.getAllUsersDTO();
        List<UserDTO> usersInGroup = UserService.toDTO(group.getUsersList());
        
        for(UserDTO user : allUsers){
            map.put(user, usersInGroup.contains(user));
        }
        
        return map;        
    }

    public void updateGroup(Long groupId, String name, List<Integer> userIds) {
        Groups group = getGroup(groupId);
        List<Users> users = userService.getUsersByIds(userIds);
        
        group.setName(name);
        group.setUsersList(users);
        
        groupsFacade.edit(group);
    }
}
