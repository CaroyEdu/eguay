/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eguay.servlet.administration.usuarios;

import eguay.service.AdminService;
import eguay.servlet.administration.AdminServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author carlo
 */
@WebServlet(name = "AdminCreateUsuarioServlet", urlPatterns = {"/Admin/Usuarios/Create"})
public class AdminCreateUsuarioServlet extends AdminServlet {
    @EJB AdminService as;
    
    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String username = request.getParameter("username");
            String name = request.getParameter("name");
            String surname = request.getParameter("surname");
            String address = request.getParameter("address");
            String city = request.getParameter("city");
            String email = request.getParameter("email");
            String country = request.getParameter("country");
            String password = request.getParameter("password");
            String birthday = request.getParameter("birthday");
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            int sex = Integer.parseInt(request.getParameter("sex"));
            String[] roles = request.getParameterValues("roleIds");

            List<Integer> roleIds = new ArrayList<>();
            for(String id : roles) {
                roleIds.add(Integer.parseInt(id));
            }
            Date birthdayDate = sdf.parse(birthday);
            as.createUser(username, name, surname, address, city, email, country, password, birthdayDate, sex, roleIds);
            response.sendRedirect("../?msg=usuario+creado+correctamente");
        } catch (ParseException ex) {
            Logger.getLogger(AdminCreateUsuarioServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
