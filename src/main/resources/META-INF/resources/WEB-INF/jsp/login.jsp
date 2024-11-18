<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<title>Demo Spring boot Login</title>
</head>
<body>
	<section class="vh-100">
  <div class="container-fluid h-custom">
    <div class="row d-flex justify-content-center align-items-center h-100">
      <div class="col-md-9 col-lg-6 col-xl-5">
        <img src="images/Galtor2.png"
          class="img-fluid" alt="Sample image">
      </div>
      <div class="col-md-8 col-lg-6 col-xl-4 offset-xl-1">
        <form action="/login" method="post">
        
          <div class="d-flex flex-row align-items-center justify-content-center justify-content-lg-start">
            
          </div>

          <div class="divider d-flex align-items-center my-4">
            <p class="text-center fw-bold mx-3 mb-0">LOGIN</p>
          </div>
          
          
          <div class="divider d-flex align-items-center my-4 text-danger">
            <p class="text-center fw-bold mx-3 mb-0">${errorMessage}</p>
          </div>
          
          <div class="divider d-flex align-items-center my-4 text-success">
             <p class="text-center fw-bold mx-3 mb-0">${logoutMessage}</p>
          </div>

          <!-- Email input -->
          <div data-mdb-input-init class="form-outline mb-4">
            <input type="text" id="form3Example3" name="username" class="form-control form-control-lg"
              placeholder="Ingrese un correo válido" required />
            <label class="form-label" for="form3Example3">Correo Electrónico</label>
          </div>

          <!-- Password input -->
          <div data-mdb-input-init class="form-outline mb-3">
            <input type="password" id="form3Example4" name= "password" class="form-control form-control-lg"
              placeholder="Ingrese su Contraseña" required/>
            <label class="form-label" for="form3Example4">Contraseña</label>
          </div>

          <div class="d-flex justify-content-between align-items-center">
            <!-- Checkbox -->
            <div class="form-check mb-0">
              <input class="form-check-input me-2" type="checkbox" value="" id="form2Example3" />
              <label class="form-check-label" for="form2Example3">
                Recordarme
              </label>
            </div>
            <a href="viewForgotPassword" class="text-body">Olvidó la contraseña?</a>
          </div>

          <div class="text-center text-lg-start mt-4 pt-2">
            <button  type="submit" data-mdb-button-init data-mdb-ripple-init class="btn btn-secondary btn-lg"
              style="padding-left: 2.5rem; padding-right: 2.5rem;">Login</button>
            <p class="small fw-bold mt-2 pt-1 mb-0">No tiene una cuenta? <a href="/userRegistrationPage"
                class="link-danger">Registrarse</a></p>
          </div>
          
			<input name="_csrf" type="hidden" value="${_csrf.token }" />
        </form>
      </div>
    </div>
  </div>
  <div
    class="d-flex flex-column flex-md-row text-center text-md-start justify-content-between py-4 px-4 px-xl-5 bg-secondary">
    <!-- Copyright -->
    <div class="text-white mb-3 mb-md-0">
      Galtor Login Demo.
    </div>
    <!-- Copyright -->

  </div>
</section>
</body>
</html>