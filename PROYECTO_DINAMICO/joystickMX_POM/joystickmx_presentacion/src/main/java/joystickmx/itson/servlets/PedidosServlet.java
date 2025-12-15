package joystickmx.itson.servlets;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
/**
 * 
 * @author Ariel Eduardo Borbón Izaguirre ID: 00000252116
 * @author Sebastián Bórquez Huerta ID: 00000252115
 * @author Leonardo Flores Leyva ID: 00000252390
 * @author Yuri Germán García López ID: 00000252583
 * @author Victor Gerardo Torres García ID: 205869
 */
@WebServlet(name = "PedidosServlet", urlPatterns = {"/pedidos", "/pedidos/detalle", "/pedidos/resena"})
public class PedidosServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String path = request.getServletPath();

        switch (path) {
            case "/pedidos":
                // Muestra la lista de pedidos (el JS se encarga de cargar los datos)
                request.getRequestDispatcher("/WEB-INF/public/pedidos/lista.jsp").forward(request, response);
                break;
                
            case "/pedidos/detalle":
                // Muestra el detalle del pedido
                request.getRequestDispatcher("/WEB-INF/public/pedidos/detalle.jsp").forward(request, response);
                break;
                
            case "/pedidos/resena":
                // Muestra el formulario de reseña
                request.getRequestDispatcher("/WEB-INF/public/pedidos/resena.jsp").forward(request, response);
                break;
                
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                break;
        }
    }
}