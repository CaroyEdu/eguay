<%-- 
    Document   : mailbox
    Created on : May 15, 2022, 11:57:31 AM
    Author     : pedro
--%>

<%@page import="eguay.dto.AuctionDTO"%>
<%@page import="eguay.dto.MailDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Correo</title>
    </head>
    <body>
        <%
            List<MailDTO> mails = (List<MailDTO>) request.getAttribute("mails");
        %>
        
        <h1>Correo</h1>
        
        <table>
            <tr>
                <th>Asunto</th>
                <th>Producto</th>
                <th>Vendedor</th>
                <th>Precio</th> 
                <th>Categoria</th>
                <th>Estado</th>
            </tr>
            <%
                for(MailDTO mail : mails){
                    for(AuctionDTO auction : mail.getAuctions()){
            %>
            <tr>
                <td><%=mail.getSubject()%></td>
                <td><a href="ProductServlet?id=<%=auction.getId()%>"><%=auction.getName()%></a></td> 
                <td><%=mail%></td>  
                <td><%=mail%></td> 
                <td><%=mail%></td> 
                <td><%=mail%></td>
            </tr>    
            <%      }
                }
            %>
        </table>
    </body>
</html>
