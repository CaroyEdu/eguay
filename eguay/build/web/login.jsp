<%-- 
    Document   : index
    Created on : 23-mar-2022, 16:06:27
    Author     : jean-
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>eguay - Inicio</title>
    </head>
    <body>
        <h1>Conexión</h1>
        <div>
            <form method="POST" action="CheckLoginServlet">
                Usuario:<input type="text" name="username" value=""/>
                Contraseña:<input type="password" name="password" value=""/>
                <input type="submit" value="Conexión"/>
            </form>
        </div>
        <a href="IndexServlet">Volver al Inicio</a>
    </body>
</html>
