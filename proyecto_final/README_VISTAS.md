# JoystickMX - Documentación de Vistas JSP

## Estructura del Proyecto

### Directorios Principales

```
src/main/webapp/
├── WEB-INF/
│   ├── includes/           # Componentes reutilizables
│   │   ├── header.jsp      # Header unificado (se adapta automáticamente)
│   │   └── footer.jsp
│   ├── admin/              # Vistas de administración
│   │   ├── panel-menu.jsp
│   │   ├── productos/
│   │   │   ├── gestionar.jsp  (Estados: lista, editar, confirmar, eliminado)
│   │   │   └── crear.jsp
│   │   ├── usuarios/
│   │   │   └── gestionar.jsp  (Estados: lista, confirmar, eliminado)
│   │   ├── pedidos/
│   │   │   ├── lista.jsp
│   │   │   └── detalle.jsp
│   │   ├── resenas/
│   │   │   └── moderar.jsp
│   │   └── perfil/
│   │       ├── ver.jsp
│   │       └── editar.jsp
│   └── user/               # Vistas de usuario
│       ├── carrito/
│       │   ├── ver.jsp
│       │   └── pago.jsp
│       ├── pedidos/
│       │   ├── lista.jsp
│       │   ├── detalle.jsp
│       │   └── resena.jsp
│       └── perfil/
│           ├── ver.jsp
│           └── editar.jsp
├── css/                    # Archivos CSS
├── imgs/                   # Imágenes
├── index.jsp              # Catálogo de videojuegos
└── login.jsp              # Login (compartido admin/usuario)
```

## Rutas de Acceso (URLs Esperadas)

### Públicas
- `/` o `/index.jsp` - Catálogo de videojuegos
- `/login` - Inicio de sesión

### Admin
- `/admin/panel-menu` - Dashboard de administrador
- `/admin/productos/gestionar` - Gestión de productos
  - `?estado=lista` - Lista de productos (default)
  - `?estado=editar&id=X` - Editar producto
  - `?estado=confirmar&id=X` - Confirmar eliminación
  - `?estado=eliminado` - Mensaje de éxito
- `/admin/productos/crear` - Crear nuevo producto
- `/admin/usuarios/gestionar` - Gestión de usuarios
  - `?estado=lista` - Lista de usuarios (default)
  - `?estado=confirmar&id=X` - Confirmar eliminación
  - `?estado=eliminado` - Mensaje de éxito
- `/admin/pedidos` - Lista de pedidos
- `/admin/pedidos/detalle?id=X` - Detalle de pedido
- `/admin/resenas/moderar` - Moderar reseñas
- `/admin/perfil` - Perfil de administrador
- `/admin/perfil/editar` - Editar perfil admin

### Usuario
- `/carrito` - Ver carrito de compras
- `/carrito/pago` - Pantalla de pago
- `/pedidos` - Mis pedidos
- `/pedidos/detalle?id=X` - Detalle de pedido
- `/pedidos/resena?id=X` - Dejar reseña
- `/perfil` - Ver perfil
- `/perfil/editar` - Editar perfil

## Variables de Sesión Esperadas

### Para Usuarios
```jsp
${sessionScope.usuario.nombre}
${sessionScope.usuario.apellido}
${sessionScope.usuario.email}
${sessionScope.usuario.telefono}
```

### Para Administradores
```jsp
${sessionScope.adminUser.nombre}
${sessionScope.adminUser.apellido}
${sessionScope.adminUser.email}
```

## Datos Dummy Implementados

Todas las vistas incluyen datos estáticos (dummy) para permitir su visualización sin conexión a base de datos:

- **Usuarios**: Sebastian Borquez, Ariel Rodriguez, Victor Torres
- **Productos**: 10+ videojuegos con imágenes
- **Pedidos**: Pedidos #67, #68, #69 con diferentes estados
- **Reseñas**: 3 reseñas de ejemplo

## Estados Consolidados

### Gestión de Productos (`admin/productos/gestionar.jsp`)
Usa parámetro `estado` para mostrar diferentes vistas:
1. **lista** (default): Muestra todos los productos con botón editar
2. **editar**: Formulario de edición con botones "Guardar" y "Eliminar"
3. **confirmar**: Formulario deshabilitado + modal de confirmación
4. **eliminado**: Mensaje de éxito con icono

### Gestión de Usuarios (`admin/usuarios/gestionar.jsp`)
Usa parámetro `estado` para mostrar diferentes vistas:
1. **lista** (default): Lista de usuarios con acciones
2. **confirmar**: Usuario seleccionado + modal de confirmación
3. **eliminado**: Mensaje de éxito con icono

## Formularios Implementados

### POST Forms
- Login: `POST /login`
- Crear producto: `POST /admin/productos/crear`
- Actualizar producto: `POST /admin/productos/actualizar`
- Eliminar producto: `POST /admin/productos/eliminar`
- Actualizar estado pedido: `POST /admin/pedidos/actualizar-estado`
- Aprobar reseña: `POST /admin/resenas/aprobar`
- Eliminar reseña: `POST /admin/resenas/eliminar`
- Crear pedido: `POST /pedidos/crear`
- Guardar reseña: `POST /pedidos/guardar-resena`
- Logout: `POST /logout`

### GET Forms
- Búsqueda de pedidos (admin y usuario)
- Filtros de reseñas

## Includes Reutilizables

### Header Admin (`/WEB-INF/includes/header-admin.jsp`)
```jsp
<jsp:include page="/WEB-INF/includes/header-admin.jsp"/>
```
Incluye: Logo, búsqueda, botón panel admin, perfil admin

### Header Usuario (`/WEB-INF/includes/header-user.jsp`)
```jsp
<jsp:include page="/WEB-INF/includes/header-user.jsp"/>
```
Incluye: Logo, búsqueda, carrito, pedidos, perfil usuario

## CSS Necesarios

Todos los CSS fueron copiados a `webapp/css/`:
- `global.css` - Sistema de diseño base
- `header.css` - Estilos del header
- Archivos específicos por módulo

## Imágenes Necesarias

Todas las imágenes fueron copiadas a `webapp/imgs/`:
- Logos y iconos
- Portadas de videojuegos
- Iconos de usuario, carrito, etc.

## Próximos Pasos (Backend)

1. Crear Servlets para manejar las rutas listadas
2. Implementar Filtros para proteger rutas admin
3. Crear BOs (Business Objects) para lógica de negocio
4. Implementar DAOs para persistencia
5. Conectar formularios con servlets
6. Agregar validaciones server-side
7. Implementar manejo de sesiones
8. Crear base de datos y poblarla con datos

## Notas Importantes

- Todas las vistas usan JSTL y Expression Language
- Los datos dummy permiten visualizar las vistas sin backend
- Los formularios están preparados para POST/GET según corresponda
- Las rutas usan `${pageContext.request.contextPath}` para ser relocalizables
- Se incluyen placeholders para mensajes de error y éxito
- Estados consolidados reducen la cantidad de archivos JSP necesarios

