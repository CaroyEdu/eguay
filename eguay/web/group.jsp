<%-- 
    Document   : group
    Created on : Apr 19, 2022, 5:30:33 PM
    Author     : pedro
--%>

<%@page import="java.util.Map"%>
<%@page import="javax.ejb.EJB"%>
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
        boolean nuevoGrupo = false;
        GroupDTO group;
        List<UserDTO> users = null;
        Map<UserDTO, Boolean> usersMap = null;
        
        group = (GroupDTO) request.getAttribute("group");
        nuevoGrupo = group == null;
        
        if(nuevoGrupo){
            users = (List<UserDTO>) request.getAttribute("users");
        }else{
            usersMap = (Map<UserDTO, Boolean>) request.getAttribute("usersMap");
        }
    %>
    <body>
        <form>
            <%
                if (nuevoGrupo) {
            %>
            Nombre del Grupo: <input type="text" name="name" required/>
            <table>
                <tr>
                    <th>Usuarios</th>
                    <th>Seleccionados</th>
                </tr>
            <%
                    for(UserDTO user : users){
            %>
                <tr>
                    <td><%=user.getName()%></td>
                    <td><input type="checkbox" name="selectedUser" value="<%=user.getId()%>"/></td>
                </tr>
            <%
                    }
            %>
            </table>
            <button type="submit" formaction="NewGroupFromSelectedUsers">Crear grupo</button>
            <%
                }else{
            %>
            Nombre del Grupo: <input type="text" name="name" value="<%=group.getName()%>" required/>
            <button type="submit" formaction="CreateNewGroup">+ Crear Nuevo Grupo</button>
            <input type="text" name="id" value="<%=group.getId()%>" hidden/>
            <table>
                <tr>
                    <th>Usuarios</th>
                    <th>Seleccionados</th>
                </tr>
            <%
                    for (Map.Entry<UserDTO, Boolean> entry :  usersMap.entrySet()) {
                        UserDTO user = entry.getKey();
                        Boolean checked = entry.getValue();
            %>
                <tr>
                    <td><%=user.getName()%></td>
                    <td><input type="checkbox" name="selectedUser" value="<%=user.getId()%>" <%=checked ? "checked" : ""%>/></td>
                </tr>
            <%
                    }
            %>
            </table>
            <button type="submit" formaction="UpdateGroup">Actualizar Grupo</button>
            <button type="submit" formaction="NewGroupFromSelectedUsers">Nuevo grupo con los usuarios seleccionados</button>
            <%      
                }
            %>
            <button type="submit" formaction="ShowCategoryList">Añadir usuarios por categoría</button>
            <button type="submit" formaction="ShowCategoryList">Añadir usuarios por categoría</button>
        </form>
    </body>
</html>
