<%-- 
    Document   : directpurchase.jsp
    Created on : Apr 14, 2022, 12:25:50 PM
    Author     : parsa
--%>

<%@page import="eguay.dto.UserDTO"%>
<%@page import="eguay.dto.AuctionDTO"%>
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
        AuctionDTO auction = (AuctionDTO)request.getAttribute("auction");
        UserDTO user = (UserDTO) session.getAttribute("user");
    %>
    
    <body>
        
        <h1>El Precio Final del producto es <%= auction.getClosePrice() %></h1>
        <br>
        <h1>Para el usuario <%= user.getName() + " " + user.getSurname() %></h1>
        <br>
        <h1>Con el address <%= user.getAddress() %></h1>
        <br>
        <button onclick="window.location.href='FinalizeDirectPurchase?id=<%= auction.getId() %>';">
         Finalizar el pago </button>
    </body>
</html>
