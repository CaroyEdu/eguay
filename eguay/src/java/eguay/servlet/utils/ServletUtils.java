/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eguay.servlet.utils;

import eguay.dao.AbstractFacade;
import eguay.entity.Groups;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author pedro
 */
public class ServletUtils<T>{
    public ServletUtils(){}
    
    public static List<Long> getIdsFromChecked(HttpServletRequest request) {
        String checked = request.getParameter("check");
        List<Long> ids = new LinkedList<>();
        
        for(String id : checked.split(",")){
            ids.add(Long.valueOf(id));
        }
        
        return ids;
    }

    public List<T> getObjectsFromIds(List<Long> ids, AbstractFacade facade) {
        List<T> objects = new LinkedList<>();
        
        for(Long id : ids){
            objects.add((T) facade.find(id));
        }
        
        return objects;
    }
}
