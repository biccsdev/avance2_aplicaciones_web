# Guía Rápida: Servlets Necesarios

Esta es una guía rápida para saber qué servlets crear y qué rutas se deben manejar.

---

## 🔐 Autenticación

### LoginServlet
```
Ruta: /login
Métodos: GET, POST
```

**GET**: Mostrar formulario de login
- Forward a: `/login.jsp`

**POST**: Procesar login
- Parámetros: `email`, `password`
- Validar credenciales
- Crear sesión según tipo de usuario (admin/usuario)
- Redirect según rol:
  - Admin → `/admin/panel-menu`
  - Usuario → `/`

### LogoutServlet
```
Ruta: /logout
Método: POST
```
- Invalidar sesión
- Redirect a: `/login`

---

## 🛍️ Productos (Admin)

### ProductoGestionarServlet
```
Ruta: /admin/productos/gestionar
Método: GET
```
- Parámetros opcionales: `estado`, `id`
- Obtener lista de productos o producto específico
- Forward a: `/WEB-INF/admin/productos/gestionar.jsp`

### ProductoCrearServlet
```
Ruta: /admin/productos/crear
Métodos: GET, POST
```

**GET**: Mostrar formulario
- Forward a: `/WEB-INF/admin/productos/crear.jsp`

**POST**: Crear producto
- Parámetros: `nombre`, `plataforma`, `desarrollador`, `precio`, `existencias`, `lanzamiento`, `genero`
- Guardar en BD
- Redirect a: `/admin/productos/gestionar`

### ProductoActualizarServlet
```
Ruta: /admin/productos/actualizar
Método: POST
```
- Parámetros: `id`, `nombre`, `plataforma`, etc.
- Actualizar en BD
- Redirect a: `/admin/productos/gestionar?estado=editar&id=X`

### ProductoEliminarServlet
```
Ruta: /admin/productos/eliminar
Método: POST
```
- Parámetro: `id`
- Eliminar de BD
- Redirect a: `/admin/productos/gestionar?estado=eliminado`

---

## 👥 Usuarios (Admin)

### UsuarioGestionarServlet
```
Ruta: /admin/usuarios/gestionar
Método: GET
```
- Parámetros opcionales: `estado`, `id`, `nombre` (filtro)
- Obtener lista de usuarios
- Forward a: `/WEB-INF/admin/usuarios/gestionar.jsp`

### UsuarioEliminarServlet
```
Ruta: /admin/usuarios/eliminar
Método: POST
```
- Parámetro: `id`
- Eliminar usuario de BD
- Redirect a: `/admin/usuarios/gestionar?estado=eliminado`

### UsuarioActivarServlet
```
Ruta: /admin/usuarios/activar
Método: POST
```
- Parámetro: `id`
- Cambiar estado a activo
- Redirect back

### UsuarioDesactivarServlet
```
Ruta: /admin/usuarios/desactivar
Método: POST
```
- Parámetro: `id`
- Cambiar estado a inactivo
- Redirect back

---

## 📦 Pedidos (Admin)

### PedidoAdminListaServlet
```
Ruta: /admin/pedidos
Método: GET
```
- Parámetro opcional: `orderNumber`
- Obtener lista de pedidos
- Forward a: `/WEB-INF/admin/pedidos/lista.jsp`

### PedidoAdminDetalleServlet
```
Ruta: /admin/pedidos/detalle
Método: GET
```
- Parámetro: `id`
- Obtener detalle de pedido
- Forward a: `/WEB-INF/admin/pedidos/detalle.jsp`

### PedidoActualizarEstadoServlet
```
Ruta: /admin/pedidos/actualizar-estado
Método: POST
```
- Parámetros: `id`, `estado`
- Actualizar estado del pedido
- Redirect back

---

## ⭐ Reseñas (Admin)

### ResenasModerarServlet
```
Ruta: /admin/resenas/moderar
Método: GET
```
- Parámetros opcionales: `producto`, `calificacion`
- Obtener reseñas para moderar
- Forward a: `/WEB-INF/admin/resenas/moderar.jsp`

### ResenaAprobarServlet
```
Ruta: /admin/resenas/aprobar
Método: POST
```
- Parámetro: `id`
- Aprobar reseña
- Redirect back

### ResenaEliminarServlet
```
Ruta: /admin/resenas/eliminar
Método: POST
```
- Parámetro: `id`
- Eliminar reseña
- Redirect back

---

## 👤 Perfil (Admin)

### PerfilAdminServlet
```
Ruta: /admin/perfil
Método: GET
```
- Obtener datos del admin en sesión
- Forward a: `/WEB-INF/admin/perfil/ver.jsp`

### PerfilAdminEditarServlet
```
Ruta: /admin/perfil/editar
Métodos: GET, POST
```

**GET**: Mostrar formulario
- Forward a: `/WEB-INF/admin/perfil/editar.jsp`

**POST**: Actualizar perfil
- Parámetros: `nombre`, `apellido`, `email`, `telefono`, passwords
- Actualizar en BD y sesión
- Redirect a: `/admin/perfil`

---

## 🏠 Catálogo (Usuario)

### CatalogoServlet
```
Ruta: /
Método: GET
```
- Parámetros opcionales: filtros de búsqueda, precio, plataforma, género
- Obtener productos disponibles
- Forward a: `/index.jsp`

---

## 🛒 Carrito (Usuario)

### CarritoVerServlet
```
Ruta: /carrito
Método: GET
```
- Obtener carrito de sesión
- Forward a: `/WEB-INF/user/carrito/ver.jsp`

### CarritoAgregarServlet
```
Ruta: /carrito/agregar
Método: POST
```
- Parámetros: `productoId`, `cantidad`
- Agregar a carrito en sesión
- Redirect a: `/carrito`

### CarritoEliminarServlet
```
Ruta: /carrito/eliminar
Método: POST
```
- Parámetro: `productoId`
- Eliminar del carrito
- Redirect a: `/carrito`

### CarritoPagoServlet
```
Ruta: /carrito/pago
Método: GET
```
- Validar carrito no vacío
- Forward a: `/WEB-INF/user/carrito/pago.jsp`

---

## 📦 Pedidos (Usuario)

### PedidoCrearServlet
```
Ruta: /pedidos/crear
Método: POST
```
- Parámetros: `metodoPago`, datos de tarjeta si aplica
- Crear pedido desde carrito
- Limpiar carrito
- Redirect a: `/pedidos`

### PedidoListaServlet
```
Ruta: /pedidos
Método: GET
```
- Parámetro opcional: `orderNumber`
- Obtener pedidos del usuario
- Forward a: `/WEB-INF/user/pedidos/lista.jsp`

### PedidoDetalleServlet
```
Ruta: /pedidos/detalle
Método: GET
```
- Parámetro: `id`
- Validar que el pedido pertenece al usuario
- Forward a: `/WEB-INF/user/pedidos/detalle.jsp`

### PedidoResenaServlet
```
Ruta: /pedidos/resena
Métodos: GET, POST
```

**GET**: Mostrar formulario
- Parámetro: `id` (pedido)
- Obtener productos del pedido
- Forward a: `/WEB-INF/user/pedidos/resena.jsp`

**POST**: Guardar reseña
- Parámetros: `pedidoId`, `productoId`, `calificacion`, `comentario`
- Guardar en BD
- Redirect a: `/pedidos`

---

## 👤 Perfil (Usuario)

### PerfilUsuarioServlet
```
Ruta: /perfil
Método: GET
```
- Obtener datos del usuario en sesión
- Forward a: `/WEB-INF/user/perfil/ver.jsp`

### PerfilUsuarioEditarServlet
```
Ruta: /perfil/editar
Métodos: GET, POST
```

**GET**: Mostrar formulario
- Forward a: `/WEB-INF/user/perfil/editar.jsp`

**POST**: Actualizar perfil
- Parámetros: datos personales, dirección, passwords
- Actualizar en BD y sesión
- Redirect a: `/perfil`

---

## 🔒 Filtros de Seguridad

### AdminAuthFilter
```java
@WebFilter("/admin/*")
```
- Verificar sesión con rol admin
- Si no: redirect a `/login`

### UserAuthFilter
```java
@WebFilter({"/carrito/*", "/pedidos/*", "/perfil/*"})
```
- Verificar sesión de usuario
- Si no: redirect a `/login`

---

## 📋 Resumen de Servlets

### Total: ~25-30 Servlets

**Por módulo:**
- Autenticación: 2
- Productos Admin: 4
- Usuarios Admin: 4
- Pedidos Admin: 3
- Reseñas Admin: 3
- Perfil Admin: 2
- Catálogo: 1
- Carrito: 4
- Pedidos Usuario: 4
- Perfil Usuario: 2

**Filtros:** 2

---

## 💡 Consejos

1. **Usa un servlet base**: Crea una clase `BaseServlet` con métodos comunes
2. **Centraliza validaciones**: Crea clases `Validator` para reutilizar
3. **Maneja errores**: Usa try-catch y setea atributos de error en request
4. **Session management**: Crea constantes para keys de sesión
5. **Logging**: Agrega logs en operaciones críticas
6. **Transacciones**: Usa transacciones en operaciones de BD múltiples

---

## 🎯 Prioridad de Implementación

### Fase 1 (Crítico)
1. LoginServlet + Filtros
2. CatalogoServlet
3. ProductoGestionarServlet (admin)
4. ProductoCrearServlet (admin)

### Fase 2 (Importante)
5. CarritoServlet (agregar, ver, eliminar)
6. PedidoCrearServlet
7. PedidoListaServlet
8. UsuarioGestionarServlet

### Fase 3 (Complementario)
9. Servlets de perfil (admin y usuario)
10. PedidoAdminServlets
11. ResenaServlets
12. Actualización de estados

---

**Nota**: Esta guía asume arquitectura Servlet → BO → DAO

