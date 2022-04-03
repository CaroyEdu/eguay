<%-- 
    Document   : cabecera
    Created on : 30-mar-2022, 18:24:19
    Author     : jean-
--%>

<%@page import="eguay.entity.Users"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    Users user = (Users) session.getAttribute("user");
%>

<%
  if(user != null)
  {
%>
    <a href="DisconnectServlet">Desconexión</a>
<%
  } else
{   
%>
    <a href="LoginServlet">Login</a>
<%
  }
%>
