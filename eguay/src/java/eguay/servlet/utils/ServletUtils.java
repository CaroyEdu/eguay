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
        return integerListToLong(getIdsFromChecked(request));
    }
    
    public static List<Integer> getIdsFromChecked(HttpServletRequest request) {
        String checked = request.getParameter("check");
        List<Integer> ids = null;
        
        if(checked != null){
            ids = new LinkedList<>();
        
            for(String id : checked.split(",")){
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
    
    private static List<Long> integerListToLong(List<Integer> integers){
        return integers.stream().map(Long::new).collect(Collectors.toUnmodifiableList());
    }
}
