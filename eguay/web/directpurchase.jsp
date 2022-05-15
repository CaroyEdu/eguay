<%-- 
    Document   : directpurchase.jsp
    Created on : Apr 14, 2022, 12:25:50 PM
    Author     : Parsa zendehdel nobari
--%>

<%@page import="eguay.entity.Users"%>
<%@page import="eguay.entity.Auction"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    
    <%
        Auction auction = (Auction)request.getAttribute("auction");
        Users user = (Users) session.getAttribute("user");
    %>
    
    <body>
        
        <h1>El Precio Final del producto es <%= auction.getCloseprice() %></h1>
        <br>
        <h1>Para el usuario <%= user.getName() + " " + user.getSurname() %></h1>
        <br>
        <h1>Con el address <%= user.getAddress() %></h1>
        <br>
        <button onclick="window.location.href='FinalizeDirectPurchase?id=<%= auction.getAuctionid() %>';">
         Finalizar el pago </button>
    </body>
</html>
