package joystickmx.itson.filters;

import java.io.IOException;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

/**
 * AuthFilter - Filtro de autenticación
 *
 * @author Ariel Eduardo Borbón Izaguirre ID: 00000252116
 * @author Sebastián Bórquez Huerta ID: 00000252115
 * @author Leonardo Flores Leyva ID: 00000252390
 * @author Yuri Germán García López ID: 00000252583
 * @author Victor Gerardo Torres García ID: 205869
 */
@WebFilter(filterName = "AuthFilter", urlPatterns = {"/*"})
public class AuthFilter implements Filter {

    private static final List<String> PUBLIC_PATHS = Arrays.asList(
            "/login",
            "/register",
            "/logout",
            "/home",
            "/videojuego"
    );

    private static final List<String> PUBLIC_RESOURCES = Arrays.asList(
            "/css/",
            "/imgs/",
            "/JavaScript/",
            "/resources/"
    );

    private static final List<String> ADMIN_PATHS = Arrays.asList(
            "/admin/",
            "/eliminarUsuarioConfirmar"
    );

    private static final List<String> CLIENT_PATHS = Arrays.asList(
            "/user/",
            "/carrito/",
            "/perfil/",
            "/pedidos", 
            "/resources/"
    );

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);
        String contextPath = req.getContextPath();
        String path = req.getRequestURI().substring(contextPath.length());

        boolean isStaticResource = PUBLIC_RESOURCES.stream().anyMatch(path::startsWith);
        boolean isPublicPage = PUBLIC_PATHS.contains(path);

        if (isStaticResource || isPublicPage) {
            // Es público o es CSS/IMG, déjalo pasar
            chain.doFilter(request, response);
            return;
        }   
         // ---------------------------- A PARTIR DE AQUÍ COMENTAREAS -------------------------------------
        else {
            // Revisa si es una página accesible para un cliente
            boolean isClientPath = CLIENT_PATHS.stream().anyMatch(path::startsWith) || isStaticResource;
            // Revisa si es una página exclusiva para un administrador
            boolean isAdminPath = ADMIN_PATHS.stream().anyMatch(path::startsWith);
            // Revisa si es una página accesible para un administrador (acceso completo)
            boolean fullAccess = isClientPath || isAdminPath;

            // --- Si llegamos aquí, es una página protegida ---
            // 3. Revisar si el usuario está logueado
            if (session == null || session.getAttribute("usuario") == null) {
                // No está logueado, redirigir a login
                res.sendRedirect(contextPath + "/login");
                return;
            }

            // 4. (Opcional pero recomendado) Revisar roles
            String rol = (String) session.getAttribute("rol");

            // Si es admin y la ruta no contiene extensión de archivo de una página, puede ver todo
            if (rol.toUpperCase().equals("ADMIN") && fullAccess) {
                chain.doFilter(request, response);
                return;
            }

            // Si es cliente y la ruta es para clientes, puede proceder
            if (rol.toUpperCase().equals("CLIENTE") && isClientPath) {
                chain.doFilter(request, response);
                return;
            }
            // Si el usuario intenta acceder a una página con extensión de archivo, lo regresa al home
            res.sendRedirect(contextPath + "/home"); // Lo mandamos al home
        }
        // ---------------------------- HASTA AQUÍ COMENTAREAS -------------------------------------

        // ------------------------ A PARTIR DE AQUÍ DESCOMENTAREAS --------------------------------
        // --- Si llegamos aquí, es una página protegida ---
        // 3. Revisar si el usuario está logueado
//        if (session == null || session.getAttribute("usuario") == null) {
//            // No está logueado, redirigir a login
//            res.sendRedirect(contextPath + "/login");
//            return;
//        }
//        
//        // 4. (Opcional pero recomendado) Revisar roles
//        String rol = (String) session.getAttribute("rol");
//
//        // Si es admin, puede ver todo
//        if (rol.toUpperCase().equals("ADMIN")) {
//            chain.doFilter(request, response);
//            return;
//        }
//
//        // Si es cliente, revisamos si intenta entrar a /admin/
//        if (rol.toUpperCase().equals("CLIENTE") && ADMIN_PATHS.stream().anyMatch(path::startsWith)) {
//            // Es un cliente intentando entrar al panel de admin
//            res.sendRedirect(contextPath + "/home"); // Lo mandamos al home
//            return;
//        }
//
//        // Es un cliente accediendo a una página de cliente (ej /carrito)
//        chain.doFilter(request, response);
        // ------------------------ HASTA AQUÍ DESCOMENTAREAS --------------------------------
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void destroy() {}
}