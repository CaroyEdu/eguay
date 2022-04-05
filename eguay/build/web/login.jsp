<%-- 
    Document   : index
    Created on : 23-mar-2022, 16:06:27
    Author     : jean-
--%>


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
	
	<link rel="stylesheet" href="css/style.css">

	</head>
	<body>
	<section class="ftco-section">
		<div class="container">
			<div class="row justify-content-center">
				<div class="col-md-6 text-center mb-5">
					<h2 class="heading-section">Eguay</h2>
				</div>
			</div>
			<div class="row justify-content-center">
				<div class="col-md-12 col-lg-10">
					<div class="wrap d-md-flex">
						<div class="img" style="background-image: url(img/bg-1.jpg);">
			      </div>
						<div class="login-wrap p-4 p-md-5">
			      	<div class="d-flex">
			      		<div class="w-100">
			      			<h3 class="mb-4">Iniciar Sesión</h3>
			      		</div>
			      	</div>
							<form method="POST" action="CheckLoginServlet" class="signin-form" >
			      		<div class="form-group mb-3">
			      			<label class="label" for="name">Usuario</label>
			      			<input name="username" type="text" class="form-control" placeholder="username" required>
                                                
			      		</div>
		            <div class="form-group mb-3">
		            	<label class="label" for="password">Contraseña</label>
		              <input name="password" type="password" class="form-control" placeholder="password" required>
		            </div>
		            <div class="form-group">
		            	<button type="submit" class="form-control btn btn-primary rounded submit px-3">Iniciar</button>
		            </div>
		            <div class="form-group d-md-flex">
		            	<div class="w-50 text-left">
			            	<label class="checkbox-wrap checkbox-primary mb-0">Recuerdame 
									  <input type="checkbox" checked>
									  <span class="checkmark"></span>
										</label>
									</div>
                                
                                 <a href="IndexServlet">Volver al Inicio</a>
		            </div>
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


