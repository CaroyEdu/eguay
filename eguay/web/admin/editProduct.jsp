<%-- 
    Document   : editProduct
    Created on : 15-may-2022, 21:19:44
    Author     : carlo
--%>

<%@page import="java.util.Date"%>
<%@page import="eguay.dto.CategoryDTO"%>
<%@page import="eguay.dto.AuctionDTO"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% request.setAttribute("page-name", "Productos"); %>
<% AuctionDTO producto = (AuctionDTO) request.getAttribute("producto"); %>
<% List<CategoryDTO> categories = (List<CategoryDTO>) request.getAttribute("categorias"); %>
<% SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm"); %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- CSS only -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
        <!-- JavaScript Bundle with Popper -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.2/font/bootstrap-icons.css">
        <title>Admin Productos</title>
    </head>
    <body>
        <div class="container-fluid">
            <div class="row flex-nowrap">
                <div class="col-auto col-md-3 col-xl-2 px-sm-2 px-0 bg-dark">
                <jsp:include page="components/header.jsp"/>
                </div>
                <div class="col py-3">
                    <h1 class="mb-4">Editar producto</h1>
                    <form action="SubmitEdit" method="POST">
                        <input type="text" class="form-control" value="<%= producto.getId() %>"  name="productId" hidden>
                        <h3>Datos usuario</h3>
                        <div class="row mb-3">
                          <label for="id" class="col-sm-2 col-form-label">Producto Id</label>
                          <div class="col-sm-6">  
                            <input type="text" class="form-control" value="<%= producto.getId() %>" disabled="true">
                          </div>
                        </div>
                        <div class="row mb-3">
                          <label for="id" class="col-sm-2 col-form-label">Titulo</label>
                          <div class="col-sm-6">  
                            <input type="text" class="form-control" value="<%= producto.getName() %>"  name="name">
                          </div>
                        </div>
                        <div class="row mb-3">
                          <label for="id" class="col-sm-2 col-form-label">Descripcion</label>
                          <div class="col-sm-6">  
                            <input type="text" class="form-control" value="<%= producto.getDescripcion() %>"  name="description">
                          </div>
                        </div>
                        <div class="row mb-3">
                          <label for="id" class="col-sm-2 col-form-label">URL Foto</label>
                          <div class="col-sm-6">  
                            <input type="text" class="form-control" value="<%= producto.getUrlFoto() %>"  name="fotoUrl">
                          </div>
                        </div>
                        <div class="row mb-3">
                          <label for="id" class="col-sm-2 col-form-label">Precio inicial</label>
                          <div class="col-sm-6">  
                            <input type="number" class="form-control" value="<%= producto.getStartPrice() %>"  name="startPrice">
                          </div>
                        </div>
                        
                        <div class="row mb-3">
                          <label class="col-sm-2 col-form-label">Categoria</label>
                          <div class="col-sm-6">  
                            <select class="form-select" name="category">
                                <% for(CategoryDTO c : categories) { %>
                                <option <%= c.getName().equals(producto.getCategory()) ? "selected" : "" %> value="<%= c.getId() %>"><%= c.getName() %></option>
                                <% } %>
                            </select>
                          </div>
                        </div>
                        <div class="row mb-3">
                          <label for="id" class="col-form-label">Cerrar cuando llegue a</label>
                          <div class="input-group col-sm-6">
                            <div class="input-group-text">
                              <input name="closePriceEnabled" class="form-check-input mt-0" type="checkbox" <%= producto.getClosePrice() != null ? "checked" : "" %> value="true">
                            </div>
                            <input name="closePrice" type="text" class="form-control" value="<%= producto.getClosePrice() %>" />
                          </div>
                        </div>
                          <div class="row mb-3">
                          <label for="id" class="col-form-label">Cerrar cuando llegue a pujas</label>
                          <div class="input-group col-sm-6">
                            <div class="input-group-text">
                              <input name="closeBidsEnabled" class="form-check-input mt-0" type="checkbox" <%= producto.getCloseNumberofBids()!= null ? "checked" : "" %> value="true">
                            </div>
                            <input name="closeBids" type="text" class="form-control" value="<%= producto.getCloseNumberofBids() != null ? producto.getCloseNumberofBids() : 10 %>" />
                          </div>
                        </div>
                        <div class="row mb-3">
                          <label for="id" class="col-form-label">Cerrar cuando llegue a la fecha</label>
                          <div class="input-group col-sm-6">
                            <div class="input-group-text">
                              <input name="closeDateEnabled" class="form-check-input mt-0" type="checkbox" <%= producto.getCloseDate()!= null ? "checked" : "" %> value="true">
                            </div>
                              <input name="closeDate" type="text" class="form-control" value="<%= producto.getCloseDate() != null ? sdf.format(producto.getCloseDate()) : sdf.format(new Date())%>" />
                          </div>
                        </div>
                        <button type="submit" class="btn btn-primary mt-4">Guardar</button>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
