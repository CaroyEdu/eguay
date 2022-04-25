<%-- 
    Document   : myProducts
    Created on : 23-abr-2022, 15:59:48
    Author     : jean-
--%>

<%@page import="eguay.entity.Auction"%>
<%@page import="java.util.List"%>
<%@page import="eguay.entity.Users"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>EGUAY - Mis Subastas</title>
    </head>
    
    <jsp:include page="userConnectedCheck.jsp"/>
    <jsp:include page="cabecera.jsp"/>
    <%
        Users user = (Users) session.getAttribute("user");
        List<Auction> userAuctions = user.getAuctionList2();
    %>
    
    <body>
        <%
            if(userAuctions.isEmpty())
            {
                %>
                <h1>Aún no ha puesto en venta ningún artículo.</h1>
                <%
            }else{
                %>
                <table>
                    <tr>
                        <th>Título</th>
                        <th>Activo</th>
                        <th>Editar Subasta</th>
                        <th>Borrar Subasta</th>
                    </tr>
                    <%
                        for(Auction a : userAuctions){
                            %>
                            <tr>
                            <td><%= a.getTitle() %></td>
                            <td><%= a.getActive() %></td>
                            <td><a href="AddProductServlet?id=<%= a.getAuctionid() %>">Editar</a></td>
                            <td><a href="DeleteAuctionServlet?id=<%= a.getAuctionid() %>">Borrar</a></td>
                            </tr>
                            <%
                        }
                    %>
                </table>
                <%
            }
        %>
    </body>
</html>
