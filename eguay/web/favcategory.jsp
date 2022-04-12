<%-- 
    Document   : favcategory
    Created on : Apr 8, 2022, 10:38:15 AM
    Author     : parsa
--%>

<%@page import="eguay.entity.Users"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="eguay.entity.Category"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
<body>

<h1>Show Checkboxes</h1>
    
      
                
<form method="POST" action="RegisterFavCategory">                
 <%
      List<Category> categories = (List) request.getAttribute("categoryAllList");
        Users user = (Users) session.getAttribute("user");
        List<Category> favCategories = user.getCategoryList();
      for(Category category : categories){
          if((favCategories != null) && (favCategories.contains(category))) {
      %>
      
  <input type="checkbox" id="<%= category.getCategoryid().toString()%>" name="<%= category.getCategoryid().toString()%>" value="<%= category.getName() %>" checked=checked>
  <label for="<%= category.getCategoryid().toString() %>"> <%= category.getName() %> </label><br>
  
  <% }else{ %>
  
  <input type="checkbox" id="<%= category.getCategoryid().toString()%>" name="<%= category.getCategoryid().toString()%>" value="<%= category.getName() %>" >
  <label for="<%= category.getCategoryid().toString() %>"> <%= category.getName() %> </label><br>
  
  <% }} %>
  <input type="submit" value="Submit">
  
</form>
      
      


</body>
</html>
