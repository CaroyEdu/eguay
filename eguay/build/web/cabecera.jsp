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
 
    <head>
        <link rel="stylesheet" href="css/cabecerastyle.css"/>
        <style>
        @import url('https://fonts.googleapis.com/css2?family=Poppins&display=swap');
        </style>
        <link rel="icon" type="image/x-icon" href="img/favicon.ico">
    </head>

<body>
    <div class="container">
        <div class="navbar">
            <div>
                <img src="img/logo.png" width="125px">
            </div>
            <nav>
                <ul>
                    <%
                        if(user != null)
                        {
                    %>
                    <li><a href="AddProductServlet">Añadir Subasta</a></li>
                    <li><a href="">Mi Perfil</a>
                    <li><a href="DisconnectServlet">Desconexión</a></li>
                    <%
                      } else
                    {   
                    %>
                    <li><a href="LoginServlet">Login</a></li>
                    <li><a href="AddUserServlet">Registrar</a></li>
                    <%
                      }
                    %>
                </ul>
            </nav>
        </div>
    </div>
</body>
