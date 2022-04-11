
<%-- 
    Document   : login
    Created on : Apr 5, 2022, 3:29:09 PM
    Author     : parsa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!doctype html>
<html lang="en">
  <head>
  	<title>eguay - Inicio</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

	<link href="https://fonts.googleapis.com/css?family=Lato:300,400,700&display=swap" rel="stylesheet">

	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
	
	<link rel="stylesheet" href="css/stylesignup.css">

	</head>
	<body>
	<section class="ftco-section">
		<div class="container">
			<div class="row justify-content-center">
				
			</div>
			<div class="row justify-content-center">
				
					<div class="wrap d-md-flex">
					
						<div class="container-fluid">
			      	<div class="d-flex">
			      		<div class="w-100">
			      			<h3 class="mb-4">Iniciar Sesión</h3>
			      		</div>
			      	</div>
							<form method="POST" action="SignupServlet" class="signin-form" >
			      		<div class="form-group mb-3">
			      			<label class="label" for="name">Usuario</label>
			      			<input name="username" type="text" class="form-control" placeholder="username" required>
                                                
			      		</div>
                            <div class="form-group mb-3">
			      			<label class="label" for="name">Nombre</label>
			      			<input name="name" type="text" class="form-control" placeholder="username" required>

			      		</div>
                            <div class="form-group mb-3">
			      			<label class="label" for="name">Apellido</label>
			      			<input name="surname" type="text" class="form-control" placeholder="username" required>
			      		</div>
                                                            
                            <div class="form-group mb-3">
			      			<label class="label" for="name">Domicilio</label>
			      			<input name="address" type="text" class="form-control" placeholder="username" required>  
			      		</div>
                                                            
                            <div class="form-group mb-3">
			      			<label class="label" for="name">Ciudad</label>
			      			<input name="city" type="text" class="form-control" placeholder="username" required>  
			      		</div>   
                            <div class="form-group mb-3">
			      			<label class="label" for="name">Fecha de nacimiento</label>
			      			<input name="birthday" type="date" class="form-control" placeholder="username" required>  
			      		</div> 
                        <div class="input-group">
                            <label class="label">sexo</label>
                            <div class="rs-select2 js-select-simple select--no-search">
                                <select name="sex">
                                    <option  selected="selected">Sexo</option>
                                    <option>Hombre</option>
                                    <option>Mujer</option>
                                    <option>No especificar</option>
                                </select>
                                <div class="select-dropdown"></div>
                            </div>
                        </div>                   
		            <div class="form-group mb-3">
		            	<label class="label" for="password">Contraseña</label>
		              <input name="password" type="password" class="form-control" placeholder="password" required>
		            </div>
                            <div class="form-group mb-3">
		            	<label class="label" for="password">Repetir Contraseña</label>
		              <input name="password" type="password" class="form-control" placeholder="password" required>
		            </div>                                 
		          <input type="submit" value="Añadir"/>
		          </form>
		        </div>
		      </div>
				</div>
			</div>
		</div>
	</section>

	<script src="js/jquery.min.js"></script>
  <script src="js/popper.js"></script>
  <script src="js/bootstrap.min.js"></script>
  <script src="js/main.js"></script>

	</body>
</html>


