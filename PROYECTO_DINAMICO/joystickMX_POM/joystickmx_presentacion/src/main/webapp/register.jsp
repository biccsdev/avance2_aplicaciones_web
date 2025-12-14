<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">

    <head>
      <meta charset="utf-8">
      <meta name="viewport" content="width=device-width, initial-scale=1, viewport-fit=cover">
      <title>Crear cuenta · JoystickMX</title>
      <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
      <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/imgs/icono_app.png">
      <link rel="stylesheet" href="${pageContext.request.contextPath}/css/global.css">
      <link rel="stylesheet" href="${pageContext.request.contextPath}/css/register.css">
    </head>

    <body class="app-bg">
      <div class="login-page">
        <header class="brand-header center">
          <div class="header-left">
            <img class="logo" src="${pageContext.request.contextPath}/imgs/logo.png" alt="Logo de JoystickMX">
          </div>
        </header>

        <main>
          <section class="container-center login-card">
            <div class="auth-card">
              <div class="divider" aria-hidden="true"></div>
              <h2 class="auth-title">Crea tu cuenta</h2>

              <form id="registro" class="form" action="${pageContext.request.contextPath}/register" method="post">
                <div>
                  <label class="form-label" for="registro">Correo electrónico<span aria-hidden="true">*</span></label>
                  <input class="input" id="email" name="email" type="email" autocomplete="email"
                    placeholder="Ingresa tu correo electrónico" required>
                </div>

                <div>
                  <label class="form-label" for="registro">Contraseña<span aria-hidden="true">*</span></label>
                  <input class="input" id="password" name="password" type="password" autocomplete="new-password"
                    placeholder="••••••••" required>
                </div>

                <div class="form-label">
                  <label class="form-label" for="registro">Nombre<span aria-hidden="true">*</span></label>
                  <input class="input" id="nombre" name="nombre" type="text" autocomplete="given-name"
                    placeholder="Ingresa tus nombre(s)" required>
                </div>

                <div class="form-label">
                  <label class="form-label" for="registro">Apellido Paterno<span aria-hidden="true">*</span></label>
                  <input class="input" id="apellidoPaterno" name="apellidoPaterno" type="text" autocomplete="family-name"
                    placeholder="Ingresa tu apeliido paterno" required>
                </div>

                <div class="form-label">
                  <label class="form-label" for="registro">Apellido Materno<span aria-hidden="true">*</span></label>
                  <input class="input" id="apellidoMaterno" name="apellidoMaterno" type="text" autocomplete="family-name"
                    placeholder="Ingresa tu apellido materno" required>
                </div>

                <div class="field-row">
                  <div class="field">
                    <label class="form-label" for="registro">Colonia<span aria-hidden="true">*</span></label>
                    <input class="input" id="colonia" name="colonia" type="text" placeholder="Colonia" required>
                  </div>
                  <div class="field">
                    <label class="form-label" for="registro">Calle<span aria-hidden="true">*</span></label>
                    <input class="input" id="calle" name="calle" type="text" placeholder="Calle" required>
                  </div>
                </div>

                <div>
                  <label class="form-label" for="registro">Número exterior<span aria-hidden="true">*</span></label>
                  <input class="input" id="numero" name="numero" type="text" inputmode="numeric"
                    placeholder="Número exterior" required>
                </div>

                <div>
                  <label class="form-label" for="registro">Teléfono (Opcional)</label>
                  <input class="input" id="telefono" name="telefono" type="tel" inputmode="tel" autocomplete="tel"
                    placeholder="+52">
                </div>

                <div class="terms">
                  <input id="terms" name="terms" type="checkbox" required>
                  <label for="registro">Acepto los términos y condiciones de servicio de JoystickMX</label>
                </div>

                <button class="btn btn-primary mt-4" type="submit">Registrarse</button>

                <p class="text-center text-muted mt-4">
                  Ya tienes una cuenta?
                  <a class="link" href="${pageContext.request.contextPath}/login">Inicia sesión</a>
                </p>
              </form>
            </div>
          </section>
        </main>
      </div>
    </body>
    
</html>