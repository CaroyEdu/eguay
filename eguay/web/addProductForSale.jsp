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
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.0/jquery.min.js"></script>
        <link rel="stylesheet" href="css/styleaddproduct.css"/>
    </head>
    
    <%
        List<Category> categoryList = (List) request.getSession().getAttribute("categoryList");
        Calendar now = new GregorianCalendar();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        %>
        <jsp:include page="userConnectedCheck.jsp"/>
        <jsp:include page="cabecera.jsp"/>
    
    <body>
        
        <div class="flex-container">
            <h1>Introduce información sobre tu producto:</h1>
        </div>
        
        <div class="flex-container">
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
                <input type="checkbox" id="closePrice" name="checkBoxClosePrice" onclick="checkInput()"/>
                Cerrar cuando llegue a <input type="text" id="inputClosePrice" name="inputClosePrice"/> $.
                <br/>
                
                <input type="checkbox" id="closeDate" name="checkBoxCloseDate"/>
                Cerrar cuando llegue a la fecha <input type="date" name="inputCloseDate" id="inputCloseDate1" min="<%= sdf.format(now.getTime()) %>"/> <input type="time" id="inputCloseDate2" name="inputCloseDateTime" />
                <br/>
                
                <input type="checkbox" id="closeNumberOfBids" name="checkBoxCloseNumberOfBids"/>
                Cerrar cuando llegue a: <input type="text" id="inputCloseNumberOfBids" name="inputCloseNumberOfBids"/> pujas.
                <br/>
                <input type="submit" id="checkBtn" value="Añadir"/>
            </form>
        </div>
        <script>
            document.getElementById("closePrice").addEventListener('change', function(){
                document.getElementById("inputClosePrice").required = this.checked;
            });
            document.getElementById("closeDate").addEventListener('change', function(){
                document.getElementById("inputCloseDate1").required = this.checked;
                document.getElementById("inputCloseDate2").required = this.checked;
            });
            document.getElementById("closeNumberOfBids").addEventListener('change', function(){
                document.getElementById("inputCloseNumberOfBids").required = this.checked;
            });
            document.getElementById("addForm").onsubmit = function() {check()};
            function check(){
                alert("test");
                var closePriceCheck = document.getElementById("closePrice");
                var closeDateCheck = document.getElementById("closePrice");
                var closeNumberOfBidsCheck = document.getElementById("closePrice");
                if(!closePriceCheck.checked && !closeDateCheck.checked && !closeNumberOfBidsCheck)
                {
                    document.getElementById("inputClosePrice").required = true;
                }else{
                    document.getElementById("inputClosePrice").required = false;
                }
            }
        </script>
        
        <script type="text/javascript">
        $(document).ready(function () {
            $('#checkBtn').click(function() {
              checked = $("input[type=checkbox]:checked").length;

              if(!checked) {
                alert("ERROR: Debes seleccionar al menos una opción de cierre de puja.");
                return false;
              }

            });
        });
        </script>
    </body>
</html>
