<%-- 
    Document   : directpurchase.jsp
    Created on : Apr 14, 2022, 12:25:50 PM
    Author     : Parsa zendehdel nobari
--%>

<%@page import="eguay.dto.UserDTO"%>
<%@page import="eguay.dto.AuctionDTO"%>
<%@page import="eguay.entity.Users"%>
<%@page import="eguay.entity.Auction"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="cabecera.jsp"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    
    <%
        AuctionDTO auction = (AuctionDTO)request.getAttribute("auction");
        UserDTO user = (UserDTO) session.getAttribute("user");
    %>
    
    <body>
        <br>
        
        <h2 style="padding-left: 100px;">El Precio Final del producto: <%= auction.getClosePrice() %></h2>
        <br>
        <h2 style="padding-left: 100px;">Para el usuario: <%= user.getName() + " " + user.getSurname() %></h2>
        <br>
        <h2 style="padding-left: 100px;">Con el address: <%= user.getAddress() %></h2>
        <br>
        <hr>
        <br>
        <button style="margin-left: 100px;" onclick="window.location.href='FinalizeDirectPurchase?id=<%= auction.getId() %>';">
         Finalizar el pago </button>
    </body>
</html>
