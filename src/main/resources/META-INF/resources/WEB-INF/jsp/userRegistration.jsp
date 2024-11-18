<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<script type="text/javascript" src="js/jquery-3.7.1.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	console.log("ready!");
	$("#email").blur(function(){
		$.ajax({
			type:"POST",
			url:"findByEmail",
			data: {"email" :$("#email").val(),"_csrf":$("#csrf").val()},
			success: function(emailExists){
				if(emailExists){
					$("#errorMessage").html("Usuario ya existe.");
				}else{
					$("#errorMessage").html("");
				}	
			}
			
		});
	});
});

</script>

<title>Registro de Usuario</title>
</head>
<body>
	<!-- Section: Design Block -->
<section class="text-center text-lg-start">
  <style>
    .cascading-right {
      margin-right: -50px;
    }

    @media (max-width: 991.98px) {
      .cascading-right {
        margin-right: 0;
      }
    }
  </style>

  <!-- Jumbotron -->
  <div class="container py-4">
    <div class="row g-0 align-items-center">
      <div class="col-lg-6 mb-5 mb-lg-0">
        <div class="card cascading-right bg-body-tertiary" style="
            backdrop-filter: blur(30px);
            ">
          <div class="card-body p-5 shadow-5 text-center">
            <h2 class="fw-bold mb-5">Registrese</h2>
            <div id="errorMessage" class="divider d-flex align-items-center my-4 text-danger">
            	<p class="text-center fw-bold mx-3 mb-0">${errorMessage}</p>
            </div>
            <form action="registerUser" method="post">
              <!-- 2 column grid layout with text inputs for the first and last names -->
              <div class="row">
                <div class="col-md-6 mb-4">
                  <div data-mdb-input-init class="form-outline">
                    <input type="text" id="form3Example1" oninvalid="setCustomValidity('Favor ingrese su Nombre')" 
                    oninput= "this.setCustomValidity('')" class="form-control" name="firstName" required/>
                    <label class="form-label" for="form3Example1">Nombre</label>
                  </div>
                </div>
                <div class="col-md-6 mb-4">
                  <div data-mdb-input-init class="form-outline">
                    <input type="text" id="form3Example2" oninvalid="setCustomValidity('Favor ingrese su Apellido')"  
                    oninput= "this.setCustomValidity('')" class="form-control" name="lastName" required/>
                    <label class="form-label" for="form3Example2">Apellido</label>
                  </div>
                </div>
              </div>

              <!-- Email input -->
              <div data-mdb-input-init class="form-outline mb-4">
                <input type="email" id="email" oninvalid="setCustomValidity('Favor ingrese un correo valido')"  
                    oninput= "this.setCustomValidity('')" class="form-control" name="email" required/>
                <label class="form-label" for="form3Example3">Correo Electrónico</label>
              </div>

              <!-- Password input -->
              <div data-mdb-input-init class="form-outline mb-4">
                <input type="password" id="form3Example4" oninvalid="setCustomValidity('Favor ingrese una password')"  
                    oninput= "this.setCustomValidity('')" class="form-control" name="password" required/>
                <label class="form-label" for="form3Example4">Password</label>
              </div>

              

              <!-- Submit button -->
              <button type="submit" data-mdb-button-init data-mdb-ripple-init class="btn btn-primary btn-block mb-4">
                Registrarse
              </button>

              <!-- Register buttons -->
             <input id="csrf" name="_csrf" type="hidden" value="${_csrf.token }" />
            </form>
          </div>
        </div>
      </div>

      <div class="col-lg-6 mb-5 mb-lg-0">
        <img src="images/Galtor2.png" class=" ml-5 w-100 rounded-4 shadow-4"
          alt="" />
      </div>
    </div>
  </div>
  <!-- Jumbotron -->
</section>
<!-- Section: Design Block -->
</body>
</html>