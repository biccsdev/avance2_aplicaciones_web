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
 * @author Ariel Eduardo Borbon Izaguirre ID: 00000252116
 * @author Sebastián Bórquez Huerta ID: 00000252115
 * @author Leonardo Flores Leyva ID: 00000252390
 * @author Yuri Germán García López ID: 00000252583
 */
@WebFilter(filterName = "AuthFilter", urlPatterns = {"/*"})
public class AuthFilter implements Filter {

    private static final List<String> PUBLIC_PATHS = Arrays.asList(
            "/login",
            "/login.jsp",
            "/register",
            "/register.jsp",
            "/logout"
    );

    private static final List<String> PUBLIC_RESOURCES = Arrays.asList(
            "/css/",
            "/imgs/",
            "/js/"
    );

    private static final List<String> ADMIN_PATHS = Arrays.asList(
            "/admin/"
    );

    private static final List<String> CLIENT_PATHS = Arrays.asList(
            "/user/",
            "/carrito",
            "/perfil",
            "/pedidos"
    );

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);
        String contextPath = req.getContextPath();
        String path = req.getRequestURI().substring(contextPath.length());

        // 1. Revisamos si es un recurso estático (usando la lista)
        boolean isStaticResource = PUBLIC_RESOURCES.stream().anyMatch(path::startsWith);

        // 2. Revisamos si es una página pública (usando la lista)
        // ¡AÑADÍ /home A TU LISTA!
        boolean isPublicPage = PUBLIC_PATHS.contains(path) || path.equals("/home");

        if (isStaticResource || isPublicPage) {
            // Es público o es CSS/IMG, déjalo pasar
            chain.doFilter(request, response);
            return; // Salimos del filtro
        }

        // --- Si llegamos aquí, es una página protegida ---
        // 3. Revisar si el usuario está logueado
        if (session == null || session.getAttribute("usuario") == null) {
            // No está logueado, redirigir a login
            res.sendRedirect(contextPath + "/login");
            return;
        }

        // --- Si llegamos aquí, el usuario SÍ está logueado ---
        // 4. (Opcional pero recomendado) Revisar roles
        String rol = (String) session.getAttribute("rol");

        // Si es admin, puede ver todo
        if (rol.equals("ADMIN")) {
            chain.doFilter(request, response);
            return;
        }

        // Si es cliente, revisamos si intenta entrar a /admin/
        if (rol.equals("CLIENTE") && ADMIN_PATHS.stream().anyMatch(path::startsWith)) {
            // Es un cliente intentando entrar al panel de admin
            res.sendRedirect(contextPath + "/home"); // Lo mandamos al home
            return;
        }

        // Es un cliente accediendo a una página de cliente (ej /carrito)
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }
}
