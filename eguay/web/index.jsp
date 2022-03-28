<%-- 
    Document   : index
    Created on : 28-mar-2022, 10:54:36
    Author     : jean-
--%>

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
    
    %>
    
    <body>
        <a href="LoginServlet">Login</a>
        <a href="AddProductServlet">Añadir producto en venta</a>
        <h1>eguay</h1>
        
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
