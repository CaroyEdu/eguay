<%-- 
    Document   : myProducts
    Created on : 23-abr-2022, 15:59:48
    Author     : Roy Caro Jean Edouard
--%>

<%@page import="eguay.dto.AuctionDTO"%>
<%@page import="eguay.dto.UserDTO"%>
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
        UserDTO user = (UserDTO) session.getAttribute("user");
        List<AuctionDTO> userAuctions = (List) request.getAttribute("userAuctions");
        String error = (String) request.getAttribute("error");
    %>
    
    <body>
        <%
            if(userAuctions.isEmpty())
            {
                %>
                <br/>
                <h1 class="wrapper">Aún no ha puesto en venta ningún artículo.</h1>
                <%
            }else{
                %>
                <br/>
                <br/>
                <% if(error!=null){ %>
                <label style="color:red"class="wrapper"><%=error%></label>
                <% } %>
                <div>
                    <form method="POST" action="MyProductsServlet" class="wrapper">
                        <input class="search" placeholder="Filtrar subastas..." type="text" name="searchbar">
                    </form>
                </div>
                <table border="1px" width="90%" style="margin:  0 5% 0 5%">
                    <tr>
                        <th width="50%">Título</th>
                        <th width="10%">Activo</th>
                        <th width="20%">Editar Subasta</th>
                        <th width="20%">Borrar Subasta</th>
                    </tr>
                    <%
                        for(AuctionDTO a : userAuctions){
                            %>
                            <tr>
                            <td><%= a.getName()%></td>
                            <td><%= a.getActive() %></td>
                            <td><a href="AddProductServlet?id=<%= a.getId() %>" style="padding: 2px 5px 2px 5px; color: white; background-color: #333; margin-left: 50%">X</a></td>
                            <td><a href="DeleteAuctionServlet?id=<%= a.getId() %>&userid=<%= user.getId() %>" style="padding: 2px 5px 2px 5px; color: white; background-color: #333; margin-left: 50%">X</a></td>
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
