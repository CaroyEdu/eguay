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
    
    <% 
        List<Auction> auctionList = (List) request.getAttribute("auctionList");
    %>
    <jsp:include page="cabecera.jsp"/>
    
    <body>
        <h1>¡Busca un producto!</h1>
        <div class="container">
            <form>
                <input type="text" name="searchbar" id="searchbar"/>
                <input type="submit" value="Buscar" id="submitbutton"/>
            </form>
        </div>
        <table class="table-container">
            <tr>
                <th>Título</th>
                <th>Descripción</th>
                <th>Foto</th>
                <th>Precio</th>
            </tr>
            <%for(Auction a : auctionList)
                {
            %>
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
