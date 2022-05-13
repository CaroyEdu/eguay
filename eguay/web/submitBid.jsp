<%-- 
    Document   : submitBid
    Created on : Apr 19, 2022, 3:29:33 PM
    Author     : parsa
--%>

<%@page import="eguay.entity.Users"%>
<%@page import="eguay.entity.Auction"%>
<%@page import="eguay.entity.Bid"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <%
        
        Bid bid = (Bid) request.getAttribute("highestBid");
        Auction auction = (Auction) request.getAttribute("auction");
        Users user = (Users) session.getAttribute("user");
        
    %>
    <body>
        <form name="submitBid" method="POST" action="FinalizeBidServlet?id=<%=auction.getAuctionid()%>">
        <% if (bid == null) { %>
        <h1>No hay puja, Sé primero en pujar este producto </h1><br>
        <h2> La puja debe ser mayor que el precio inicial : <%= auction.getStartprice()%> </h2>
             <br> <br>
             
             <h3> Nueva puja <h3> <br>
             Puja:<input type="number" name="Bid" value="<%= auction.getStartprice()+ 1 %>" required/><br/>
             <br>
             <p> Para el usuario <%= user.getUsername()%> con el nombre <%= user.getName() + "\t" + user.getSurname() + "\t" %> con la direccion <%= user.getAddress() %> y el correro <%= user.getEmail() %> </p> 

             
        <% }else { %> 
        <h1>Puja mas alta es : <%= bid.getBid() %> del usuario : <%= bid.getBiderid().getUsername()%> </h1><br>
         
             <h2> La puja debe ser mayor que <%= bid.getBid() %> </h2>
             <br> <br>
             <h3> Nueva puja <h3> <br>
                     Puja:<input type=number name="Bid" value="<%= bid.getBid() + 1 %>" min="<%= bid.getBid() + 1 %>" step="0.01" required/><br/>
             <br>
             <p> Para el usuario <%= user.getUsername()%> con el nombre <%= user.getName() + "\t" + user.getSurname() + "\t" %> con la direccion <%= user.getAddress() %> y el correro <%= user.getEmail() %> </p> 
        <% } %>
        <input type="submit" id="checkBtn" value="Añadir Puja"/>
         </form>
    </body>
</html>
