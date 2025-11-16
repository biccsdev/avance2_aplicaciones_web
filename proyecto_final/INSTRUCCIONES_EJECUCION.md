# 🚀 Instrucciones para Ejecutar el Proyecto

## ✅ Cambios Aplicados

Se han agregado las **dependencias JSTL** necesarias al `pom.xml`:
- `jakarta.servlet.jsp.jstl-api` (3.0.0)
- `jakarta.servlet.jsp.jstl` implementation (3.0.1)

---

## 📋 Pasos para Ejecutar desde NetBeans

### 1. Recargar el Proyecto Maven

**Opción A (Recomendada):**
1. Clic derecho en el proyecto `proyecto_final`
2. Selecciona **"Build with Dependencies"** o **"Clean and Build"**
3. Espera a que Maven descargue las dependencias (verás el progreso abajo)

**Opción B:**
1. Clic derecho en el proyecto
2. **"Reload POM"** o **"Reload Project"**
3. Luego clic derecho → **"Clean and Build"**

### 2. Verificar Dependencias

En NetBeans:
1. Expande tu proyecto en el árbol
2. Ve a **"Dependencies"** o **"Bibliotecas"**
3. Deberías ver:
   - ✅ `jakarta.servlet.jsp.jstl-api-3.0.0.jar`
   - ✅ `jakarta.servlet.jsp.jstl-3.0.1.jar`

### 3. Ejecutar el Proyecto

1. Asegúrate de tener un servidor configurado (GlassFish/Payara)
2. Clic derecho en el proyecto → **"Run"**
3. O presiona **F6**

### 4. Resultado Esperado

El navegador debería abrir en:
```
http://localhost:8080/proyecto_final/
```

**Deberías ver:**
- ✅ Header con logo "JoystickMX" y barra de búsqueda
- ✅ Sidebar izquierdo con filtros (Precio, Plataforma, Género)
- ✅ Grid de videojuegos con imágenes:
  - Red Dead Redemption 2
  - GTA V
  - God of War Ragnarok
  - Forza Horizon 5
  - Alan Wake 2
  - Elden Ring
  - Y más...
- ✅ Precios visibles
- ✅ Botones "Agregar al carrito"

---

## 🐛 Troubleshooting

### Problema 1: Página en Blanco
**Causa**: JSTL no está cargado
**Solución**:
1. Verifica que las dependencias se descargaron
2. Clean and Build de nuevo
3. Reinicia el servidor

### Problema 2: Error 404 en CSS/Imágenes
**Causa**: Rutas incorrectas
**Solución**:
1. Verifica que `css/` e `imgs/` estén en `webapp/`
2. Presiona Ctrl+F5 para limpiar caché del navegador

### Problema 3: Error JSTL Tag
**Síntoma**: Ves `${...}` literal en la página
**Solución**:
```xml

<web-app version="6.0" ...>
```

### Problema 4: El servidor no arranca
**Solución**:
1. Click derecho en el servidor en la pestaña "Services"
2. Stop Server
3. Start Server
4. Vuelve a hacer Run del proyecto

---

## 🔍 Verificar que Funciona

### En el Navegador (F12 → Console)

**NO** deberías ver errores como:
- ❌ `jakarta.tags.core cannot be resolved`
- ❌ `c:forEach is undefined`
- ❌ `404` en archivos CSS

**SÍ** deberías ver:
- ✅ Página renderizada con diseño completo
- ✅ CSS cargados (Network tab muestra 200 OK)
- ✅ Imágenes visibles

### Vista Rápida de la Consola

Si hay errores, copia el mensaje completo y podremos solucionarlo.

---

## 🎯 Páginas Disponibles para Probar

### Públicas (Accesibles directamente)

```
http://localhost:8080/proyecto_final/
http://localhost:8080/proyecto_final/login.jsp
```

### Protegidas (Requieren forward desde servlet)

Las siguientes **NO** deberían ser accesibles directamente (403/404):
- `/WEB-INF/admin/panel-menu.jsp`
- `/WEB-INF/admin/productos/gestionar.jsp`
- `/WEB-INF/user/carrito/ver.jsp`

**Esto es correcto** - están protegidas por diseño.

---

## ⚠️ Limitaciones Actuales

El proyecto solo tiene **vistas (frontend)**:
- ✅ Páginas JSP con diseño completo
- ✅ CSS y diseño responsive
- ✅ Datos dummy para visualización

**NO tiene (backend pendiente):**
- ❌ Servlets (controladores)
- ❌ Conexión a base de datos
- ❌ Lógica de negocio
- ❌ Autenticación funcional

### Lo que NO funcionará:
- Hacer clic en "Agregar al carrito"
- Login
- Formularios
- Navegación entre páginas

### Lo que SÍ verás:
- Diseño completo
- Datos de ejemplo
- Imágenes
- Estilos CSS
- Layout responsive

---

## 📝 Próximos Pasos

Para hacer la app funcional, tu equipo debe implementar:

1. **Servlets** - Ver `SERVLETS_NECESARIOS.md`
2. **Entidades JPA** - Crear clases de modelo
3. **DAOs** - Acceso a base de datos
4. **Base de Datos** - MySQL/PostgreSQL
5. **Lógica de Negocio** - Business Objects

---

## 💡 Comandos Útiles en Terminal

Si quieres usar Maven desde terminal:

```bash
# Navegar al proyecto
cd /home/biccs/NetBeansProjects/avance2_aplicaciones_web/proyecto_final

# Limpiar
mvn clean

# Compilar
mvn compile

# Empaquetar en WAR
mvn package

# El WAR estará en:
# target/proyecto_final-1.0-SNAPSHOT.war
```
