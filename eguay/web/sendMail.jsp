<%-- 
    Document   : sendMail
    Created on : May 15, 2022, 2:21:18 PM
    Author     : pedro
--%>

<%@page import="eguay.dto.GroupDTO"%>
<%@page import="eguay.dto.AuctionDTO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <jsp:include page="cabecera.jsp"/>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Enviar Correo</title>
    </head>
    <body>
        <%
            List<AuctionDTO> auctions = (List<AuctionDTO>) request.getAttribute("auctions");
            List<GroupDTO> groups = (List<GroupDTO>) request.getAttribute("groups");
        %>
        <h1>Enviar Correo</h1>

        <form action="SendMail">
            <h2>Subastas</h2>
            <table>
                <tr>
                    <th>Subasta</th>
                    <th>Seleccionada</th>
                </tr>
                <%
                    for (AuctionDTO auction : auctions){
                %>
                <tr>
                    <td><%=auction.getName()%></td>
                    <td><input type="checkbox" name="selectedAuction" value="<%=auction.getId()%>"/></td>
                </tr>
                <%
                    }
                %>
            </table>
            
            <h2>Grupos</h2>
            <table>
                <tr>
                    <th>Grupo</th>
                    <th>Seleccionado</th>
                </tr>
                <%
                    for (GroupDTO group : groups){
                %>
                <tr>
                    <td><%=group.getName()%></td>
                    <td><input type="checkbox" name="selectedGroup" value="<%=group.getId()%>"/></td>
                </tr>
                <%
                    }
                %>
            </table>
            
            <h2>Envío</h2>
            <input type="text" name="asunto" required/>
            <input type="submit" value="Enviar"/>
        </form>
    </body>
</html>
