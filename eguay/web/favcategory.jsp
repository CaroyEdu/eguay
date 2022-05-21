<%-- 
    Document   : favcategory
    Created on : Apr 8, 2022, 10:38:15 AM
    Author     : Parsa zendehdel nobari
--%>

<%@page import="eguay.dto.CategoryDTO"%>
<%@page import="eguay.dto.UserDTO"%>
<%@page import="eguay.entity.Users"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="eguay.entity.Category"%>
<%@page import="java.util.List"%>
<jsp:include page="cabecera.jsp"/>
<!DOCTYPE html>
<html>
<body>

<h1>Show Checkboxes</h1>
    
      
                
<form method="POST" action="RegisterFavCategory">                
 <%
      List<CategoryDTO> categories = (List) request.getAttribute("categoryAllList");
        UserDTO user = (UserDTO) session.getAttribute("user");
        List<CategoryDTO> favCategories = null ;
        favCategories = (List<CategoryDTO>) request.getAttribute("userFav");
      for(CategoryDTO category : categories){
          if((favCategories != null) && (favCategories.contains(category))) {
      %>
      
  <input type="checkbox" id="<%= category.getId().toString()%>" name="<%= category.getId().toString()%>" value="<%= category.getName() %>" checked=checked>
  <label for="<%= category.getId().toString() %>"> <%= category.getName() %> </label><br>
  
  <% }else{ %>
  
  <input type="checkbox" id="<%= category.getId().toString()%>" name="<%= category.getId().toString()%>" value="<%= category.getName() %>" >
  <label for="<%= category.getId().toString() %>"> <%= category.getName() %> </label><br>
  
  <% }} %>
  <input type="submit" value="Submit">
  
</form>
      
      


</body>
</html>
