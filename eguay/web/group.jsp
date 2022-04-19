<%-- 
    Document   : group
    Created on : Apr 19, 2022, 5:30:33 PM
    Author     : pedro
--%>

<%@page import="eguay.entity.Users"%>
<%@page import="java.util.List"%>
<%@page import="eguay.entity.Groups"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <jsp:include page="cabecera.jsp"/>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Grupo</title>
    </head>
    <%
        boolean nuevoGrupo = false;
        Groups group = null;
        List<Users> users;

        users = (List<Users>) request.getAttribute("users");
        group = (Groups) request.getAttribute("group");

        nuevoGrupo = group == null;
    %>
    <body>
        <h1><%=nuevoGrupo ? "Nuevo Grupo" : group.getName()%></h1>
        <form>
            <%
                if (!nuevoGrupo) {
            %>
            <input type="text" name="id" value="<%=group.getGroupid()%>"/>
            <%
                }
            %>
            Nombre del Grupo <input type="text" name="name" required/>
            <table>
                <tr>
                    <th>Usuarios</th>
                    <th>Seleccionados</th>
                </tr>
                <%
                    for (Users user : (List<Users>) request.getAttribute("users")) {
                %>
                <tr>
                    <td><%=user.getName()%></td>
                    <td><input type="checkbox" name="check" value="<%=user.getUserid()%>" <%=!nuevoGrupo && group.contains(user) ? "checked" : ""%>/></td>
                </tr>
                <%
                    }
                %>
            </table>
            <button type="submit" formaction="UptateGroup" <%=nuevoGrupo ? "hidden" : ""%>>Actualizar Grupo</button>
            <button type="submit" formaction="NewGroupFromSelectedUsers">Nuevo Grupo con los usuarios seleccionados</button>
            <button type="submit" formaction="ShowCategoryList">Añadir usuarios por categoría</button>
        </form>
    </body>
</html>
