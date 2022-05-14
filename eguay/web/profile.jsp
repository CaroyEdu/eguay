<%-- 
    Document   : profile
    Created on : 04-abr-2022, 9:30:46
    Author     : jean-
--%>

<%@page import="eguay.entity.Users"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    
    <jsp:include page="userConnectedCheck.jsp"/>
    <jsp:include page="cabecera.jsp"/>
    
    <body>
        <h1>Perfil</h1>
        <form>
      <input type="button" onclick="window.location.href='AddFavCategoryServlet';" value="Editar Categorias Favorias" />
      <input type="button" onclick="window.location.href='EditFavAuctionServlet';" value="Editar pujas favoritas" />
      <input type="button" onclick="window.location.href='CheckPurchasedAuctionsServlet';" value="Pujas Compradas" /> 
      <input type="button" onclick="window.location.href='MyProductsServlet';" value="Mis Subastas" /> 
    </form>
</div>
    </body>
</html>
