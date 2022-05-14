<%-- 
    Document   : group
    Created on : Apr 19, 2022, 5:30:33 PM
    Author     : pedro
--%>

<%@page import="eguay.dto.UserDTO"%>
<%@page import="eguay.dto.GroupDTO"%>
<%@page import="eguay.service.GroupService"%>
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
        GroupService groupService = new GroupService();
        
        boolean nuevoGrupo = false;
        GroupDTO group = null;
        List<UserDTO> users;

        users = (List<UserDTO>) request.getAttribute("users");
        group = (GroupDTO) request.getAttribute("group");

        nuevoGrupo = group == null;
    %>
    <body>
        <form>
            <%
                if (!nuevoGrupo) {
            %>
            <input type="text" name="id" value="<%=group.getId()%>" hidden/>
            Nombre del Grupo: <input type="text" name="name" value="<%=group.getName()%>" required/>
            <button type="submit" formaction="CreateNewGroup">+ Crear Nuevo Grupo</button>
            <%
                } else{
            %>
            Nombre del Grupo: <input type="text" name="name" required/>
            <%
                }
            %>
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
                    <td><input type="checkbox" name="selectedUser" value="<%=user.getUserid()%>" <%=!nuevoGrupo && groupService.contains(group, user) ? "checked" : ""%>/></td>
                </tr>
                <%
                    }
                %>
            </table>
            <%
                if (!nuevoGrupo) {
            %>
            <button type="submit" formaction="UptateGroup">Actualizar Grupo</button>
            <button type="submit" formaction="NewGroupFromSelectedUsers">Nuevo grupo con los usuarios seleccionados</button>
            <%
                } else{
            %>
            <button type="submit" formaction="NewGroupFromSelectedUsers">Crear grupo</button>
            <%
                }
            %>
            <button type="submit" formaction="ShowCategoryList">Añadir usuarios por categoría</button>
        </form>
    </body>
</html>
