<%-- 
    Document   : favcategory
    Created on : Apr 8, 2022, 10:38:15 AM
    Author     : parsa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="eguay.entity.Category"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
<body>

<h1>Show Checkboxes</h1>
    
      
                
<form action="/index.html">                
 <%
      List<Category> categories = (List) request.getAttribute("categoryFavList");
      for(Category category : categories){
      %>
      
  <input type="checkbox" id="<%= category.getCategoryid()%>" name="<%= category.getName()%>" value="<%= category.getName() %>">
  <label for="<%= category.getCategoryid() %>"> <%= category.getName() %> </label><br>
  
  <% } %>
  <input type="submit" value="Submit">
</form>
      
      
</form

</body>
</html>
