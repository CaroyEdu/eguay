<%-- 
    Document   : userConnectedCheck
    Created on : 04-abr-2022, 9:40:05
    Author     : jean-
--%>

<%@page import="eguay.entity.Users"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% 
        Users user = (Users) session.getAttribute("user");
        if(user == null)
        {
    %>
    <jsp:forward page="LoginServlet"/>
    <%
        }
    %>
