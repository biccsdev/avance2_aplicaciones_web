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

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String requestURI = httpRequest.getRequestURI();
        String contextPath = httpRequest.getContextPath();
        String path = requestURI.substring(contextPath.length());

        HttpSession session = httpRequest.getSession(false);


        boolean isPublicResource = PUBLIC_RESOURCES.stream().anyMatch(prefix -> path.startsWith(prefix));
        if (isPublicResource) {
            chain.doFilter(request, response);
            return;
        }

        boolean isPublicPath = PUBLIC_PATHS.contains(path);
        if (isPublicPath) {
            chain.doFilter(request, response);
            return;
        }

        if (path.equals("/") || path.equals("/index.jsp")) {
            chain.doFilter(request, response);
            return;
        }

        if (session == null || session.getAttribute("usuario") == null) {
            httpResponse.sendRedirect(contextPath + "/login");
            return;
        }

        String userRole = (String) session.getAttribute("rol");

        if ("admin".equals(userRole)) {
            chain.doFilter(request, response);
            return;
        }

        boolean isAdminPath = ADMIN_PATHS.stream().anyMatch(prefix -> path.startsWith(prefix));
        if ("cliente".equals(userRole) && isAdminPath) {
            httpResponse.sendRedirect(contextPath + "/");
            return;
        }

        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void destroy() {}
}