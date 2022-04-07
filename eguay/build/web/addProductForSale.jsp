<%-- 
    Document   : addProduct
    Created on : 28-mar-2022, 10:52:53
    Author     : jean-
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.GregorianCalendar"%>
<%@page import="java.util.Calendar"%>
<%@page import="eguay.entity.Category"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>eguay - Añadir Producto</title>
    </head>
    
    <%
        List<Category> categoryList = (List) request.getAttribute("categoryList");
        Calendar now = new GregorianCalendar();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        %>
        <jsp:include page="userConnectedCheck.jsp"/>
    
    <body>
        <h1>Página para añadir un producto en venta!</h1>
        
        <h3>Información del producto</h3>
        <div>
            <form name="addForm" method="POST" action="AddProductForSaleServlet">
                Título:<input type="text" name="title" value="" required/><br/>
                Descripción:<input type="text" name="description" value=""/><br/>
                URL Foto:<input type="text" name="fotourl" value=""/><br/>
                Precio Inicial:<input type="text" name="startprice" value="" required/><br/>
                <br/>Categoría:
                <select name="category">
                    <%  for(Category category : categoryList)
                        {
                    %>
                    <option value="<%= category.getCategoryid()%>"><%= category.getName()%></option>
                    <%
                        }
                    %>
                </select>
                <br/>
                Elige cómo cerrar tu puja:<br/>
                <input type="checkbox" id="closePrice" name="checkBoxClosePrice"/>
                Cerrar cuando llegue a <input type="text" name="inputClosePrice"/> $.
                <br/>
                
                <input type="checkbox" id="closeDate" name="checkBoxCloseDate"/>
                Cerrar cuando llegue a la fecha <input type="date" name="inputCloseDate" min="<%= sdf.format(now.getTime()) %>"/> <input type="time" name="inputCloseDateTime" />
                <br/>
                
                <input type="checkbox" id="closeNumberOfBids" name="checkBoxCloseNumberOfBids"/>
                Cerrar cuando llegue a: <input type="text" name="inputCloseNumberOfBids"/> pujas.
                <br/>
                <input type="submit" value="Añadir"/>
            </form>
        </div>
    </body>
</html>
