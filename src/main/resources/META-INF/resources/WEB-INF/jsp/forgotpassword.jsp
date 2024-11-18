<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>


<title>Recuperar contraseña</title>
</head>

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



<body>
	<!-- Section: Design Block -->
<section class="text-center text-lg-start">
  

  <!-- Jumbotron -->
  <div class="container py-4">
    <div class="row g-0 align-items-center">
      <div class="col-lg-6 mb-5 mb-lg-0">
        <div class="card cascading-right bg-body-tertiary" style="
            backdrop-filter: blur(30px);
            ">
          <div class="card-body p-5 shadow-5 text-center">
            <h2 class="fw-bold mb-5">Recuperar Contraseña</h2>
            <div id="errorMessage" class="divider d-flex align-items-center my-4 text-danger">
            	<p class="text-center fw-bold mx-3 mb-0">${errorMessage}</p>
            </div>
            <div id="errorMessage" class="divider d-flex align-items-center my-4 text-success">
            	<p class="text-center fw-bold mx-3 mb-0">${successMessage}</p>
            </div>
            <form action="sendEmail" method="post">
            
              <div data-mdb-input-init class="form-outline mb-4">
                <input type="email" id="email" oninvalid="setCustomValidity('Favor ingrese un correo valido')"  
                    oninput= "this.setCustomValidity('')" class="form-control" name="email" required/>
                <label class="form-label" for="form3Example3">Correo Electrónico</label>
              </div>

              
              

              <!-- Submit button -->
              <button type="submit" data-mdb-button-init data-mdb-ripple-init class="btn btn-primary btn-block mb-4">
                Recuperar
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