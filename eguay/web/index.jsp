<%-- 
    Document   : index
    Created on : 28-mar-2022, 10:54:36
    Author     : jean-
--%>

<%@page import="eguay.entity.Users"%>
<%@page import="java.util.List"%>
<%@page import="eguay.entity.Auction"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>eguay - Página de Inicio</title>
    </head>
    
    <% List<Auction> auctionList = (List) request.getAttribute("auctionList");
        Users user = (Users) session.getAttribute("user");
    %>
    
    <body>
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
        <h1>eguay</h1>
        
        <%
          if(user != null)
          {
        %>
        <h4>Bienvenido, <%= user.getName() %></h4>
        <a href="AddProductServlet">¿Quieres añadir un producto?</a>
        <%
          }
        %>
        
        <h3>Lista de productos en venta</h3>
        <table>
            <tr>
                <th>Título</th>
                <th>Descripción</th>
                <th>Foto</th>
                <th>Precio</th>
            </tr>
            <%for(Auction a : auctionList)
                {
            %>
            <br/>
            <tr>
                <td><%= a.getTitle() %></td>
                <td><%= a.getDescription() %></td>
                <td><img src="<%= a.getFotourl() %>" height="100"/></td>
                <td><%= a.getStartprice() %></td>
            <tr>
            <%
                }
            %>
        </table>
    </body>
</html>
