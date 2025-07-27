<%--
  Created by IntelliJ IDEA.
  User: Christian
  Date: 25/07/2025
  Time: 06:57 p. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
    <title>Iniciar Sesión | Citas</title>
</head>

<body>
<main>
    <section class="login-form p-4">
        <div class="container">
            <div class="row justify-content-center align-items-center">
                <!-- Columna de imagen -->
                <div class="col-lg-6 d-none d-lg-block mb-4 mb-lg-0 img-container">
                    <img src="${pageContext.request.contextPath}/resources/media/login-img.avif" alt="login-image" class="img-fluid">
                </div>

                <!-- Columna de formulario -->
                <div class="col-lg-5 col-xl-4">
                    <div class="form-container">
                        <form>
                            <!-- Email -->
                            <div class="form-outline mb-4">
                                <input type="email" id="form3Example3" class="form-control form-control-lg"
                                       placeholder="Ingresa un correo electrónico valido" />
                                <label class="form-label" for="form3Example3">Correo electrónico</label>
                            </div>

                            <!-- Contraseña -->
                            <div class="form-outline mb-3">
                                <input type="password" id="form3Example4" class="form-control form-control-lg"
                                       placeholder="Ingresa tu contraseña" />
                                <label class="form-label" for="form3Example4">Contraseña</label>
                            </div>

                            <div class="text-center text-lg-start mt-4 pt-2">
                                <button type="button" class="btn btn-primary btn-lg w-100 py-3">Iniciar Sesión</button>
                                <p class="small mt-2 pt-1 mb-0">¿No tienes una cuenta?<a href="/registrarse/registro.html"
                                                                                         class="link-danger"> Registrate</a></p>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </section>
</main>
<footer class="bg-light text-black py-4">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-8 text-center">
                <h5>LosAbstractos</h5>
                <p class="mb-3">© 2025 Todos los derechos reservados.</p>
                <div>
                    <a href="#" class="text-black me-3"><i class="fab fa-facebook-f"></i></a>
                    <a href="#" class="text-black me-3"><i class="fab fa-twitter"></i></a>
                    <a href="#" class="text-black"><i class="fab fa-instagram"></i></a>
                </div>
            </div>
        </div>
    </div>
</footer>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>