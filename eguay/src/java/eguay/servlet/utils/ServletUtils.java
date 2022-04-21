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
    
    public void loadListToRequest(HttpServletRequest request, AbstractFacade facade, String label){
        request.setAttribute(label, facade.findAll());
    }
    
    public static List<Long> getIdsFromCheckedLong(HttpServletRequest request) {        
        String[] checkedList = request.getParameterValues("check");
        List<Long> ids = null;
        
        if(checkedList != null){
            ids = new LinkedList<>();
            for(String id : checkedList){
                ids.add(Long.valueOf(id));
            }
        }
        
        return ids;
    }
    
    public static List<Integer> getIdsFromChecked(HttpServletRequest request) {
        String[] checkedList = request.getParameterValues("check");
        List<Integer> ids = null;
        
        if(checkedList != null){
            ids = new LinkedList<>();
            for(String id : checkedList){
                ids.add(Integer.valueOf(id));
            }
        }
        
        return ids;
    }

    public List<T> getObjectsFromIds(List<Integer> ids, AbstractFacade facade) {
        List<T> objects = null;
        
        if(ids != null && !ids.isEmpty()){
            objects = new LinkedList<>();
        
            for(Integer id : ids){
                objects.add((T) facade.find(id));
            }
        }
        
        return objects;
    }
    
    public List<T> getObjectsFromIdsLong(List<Long> ids, AbstractFacade facade) {
        List<T> objects = new LinkedList<>();
        
        for(Long id : ids){
            objects.add((T) facade.find(id));
        }
        
        return objects;
    }
}
