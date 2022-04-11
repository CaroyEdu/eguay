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
    
      
                
<form method="POST" action="RegisterFavCategory">                
 <%
      List<Category> categories = (List) request.getAttribute("categoryFavList");
      for(Category category : categories){
      %>
      
  <input type="checkbox" id="<%= category.getCategoryid().toString()%>" name="<%= category.getCategoryid().toString()%>" value="<%= category.getName() %>">
  <label for="<%= category.getCategoryid().toString() %>"> <%= category.getName() %> </label><br>
  
  <% } %>
  <input type="submit" value="Submit">
</form>
      
      
</form

</body>
</html>
