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
        <title>EGUAY - Inicio</title>
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
            
        <div class="title">
            <p>¡Últimos productos!</p>
        </div>
            <%
                int cantidad = 0;
                for(Auction a : auctionList)
                {
                    if(cantidad == 0)
                    {
            %>
            <div class="flex-container">
                <%
                    }
                %>
                <div class="card">
                    <img src="<%= a.getFotourl() %>" style="width:100%">
                    <h4><%= a.getTitle() %></h4>
                    <p class="description"><%= a.getDescription() %></p>
                    <p class="price">$<%= a.getStartprice() %></p>
                    <p><button>Pujar</button></p>
                </div>
            <%
                cantidad++;
                    if(cantidad == 6)
                    {
            %>
            </div>
            <%
                    cantidad = 0;
                    }
                }
                if(cantidad > 0){
            %>
            </div>
            <%
                }
            %>
            <div class="title">
            <p>Categoría: Belleza</p>
        </div>
            <%
                cantidad = 0;
                for(Auction a : auctionList)
                {
                    if(a.getCategoryList().get(0).getCategoryid()==1)
                    {
                    if(cantidad == 0)
                    {
            %>
            <div class="flex-container">
                <%
                    }
                %>
                <div class="card">
                    <img src="<%= a.getFotourl() %>" style="width:100%">
                    <h4><%= a.getTitle() %></h4>
                    <p class="description"><%= a.getDescription() %></p>
                    <p class="price">$<%= a.getStartprice() %></p>
                    <p><button>Pujar</button></p>
                </div>
            <%
                cantidad++;
                    if(cantidad == 6)
                    {
            %>
            </div>
            <%
                    cantidad = 0;
                    }
                }}
            %>
    </body>
</html>
