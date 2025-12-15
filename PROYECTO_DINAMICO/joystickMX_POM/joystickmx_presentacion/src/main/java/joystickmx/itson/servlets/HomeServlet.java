package joystickmx.itson.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import joystickmx.itson.DTO.VideojuegoDTO;
import joystickmx.itson.Fachada.FachadaBO;
import joystickmx.negocio.exception.NegocioException;

/**
 *
 * @author Ariel Eduardo Borbón Izaguirre ID: 00000252116
 * @author Sebastián Bórquez Huerta ID: 00000252115
 * @author Leonardo Flores Leyva ID: 00000252390
 * @author Yuri Germán García López ID: 00000252583
 * @author Victor Gerardo Torres García ID: 205869
 */
@WebServlet(name = "HomeServlet", urlPatterns = {"/home", ""})
public class HomeServlet extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Maneja las peticiones HTTP <code>GET</code>. Recibe los parámetros de
     * filtrado desde la URL o el formulario de búsqueda, consulta la lógica de
     * negocio y envía la lista de videojuegos al JSP.
     *
     * @param request objeto de solicitud del servlet.
     * @param response objeto de respuesta del servlet.
     * @throws ServletException si ocurre un error específico del servlet.
     * @throws IOException si ocurre un error de entrada/salida.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String errorRedirect = request.getParameter("error");
        if (errorRedirect != null && !errorRedirect.isEmpty()) {
            request.setAttribute("error", errorRedirect);
        }

        String busqueda = request.getParameter("busqueda");
        String precioMinStr = request.getParameter("precioMin");
        String precioMaxStr = request.getParameter("precioMax");
        String categoriaStr = request.getParameter("categoria");
        String plataforma = request.getParameter("plataforma");

        Float precioMin = null;
        Float precioMax = null;
        Long idCategoria = null;

        try {
            if (precioMinStr != null && !precioMinStr.isEmpty()) {
                precioMin = Float.parseFloat(precioMinStr);
            }
            if (precioMaxStr != null && !precioMaxStr.isEmpty()) {
                precioMax = Float.parseFloat(precioMaxStr);
            }
            if (categoriaStr != null && !categoriaStr.isEmpty()) {
                idCategoria = Long.parseLong(categoriaStr);
            }

            if (plataforma != null && (plataforma.isEmpty() || plataforma.equals("Todas"))) {
                plataforma = null;
            }
            if (busqueda != null && busqueda.trim().isEmpty()) {
                busqueda = null;
            }

        } catch (NumberFormatException e) {
            System.err.println("Error al convertir filtros numéricos: " + e.getMessage());
        }

        List<VideojuegoDTO> videojuegos;

        try {
            videojuegos = FachadaBO.filtrarVideojuegos(
                    busqueda,
                    precioMin,
                    precioMax,
                    idCategoria,
                    plataforma
            );

            request.setAttribute("videojuegos", videojuegos);

        } catch (NegocioException e) {
            request.setAttribute("error", "Error al cargar el catálogo: " + e.getMessage());
            request.setAttribute("videojuegos", new ArrayList<VideojuegoDTO>());
        }

        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    /**
     * Maneja las peticiones HTTP <code>POST</code>. Redirige el flujo al método
     * doGet, ya que la búsqueda generalmente se maneja vía GET.
     *
     * @param request objeto de solicitud del servlet.
     * @param response objeto de respuesta del servlet.
     * @throws ServletException si ocurre un error específico del servlet.
     * @throws IOException si ocurre un error de entrada/salida.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    /**
     * Retorna una descripción breve del servlet.
     *
     */
    @Override
    public String getServletInfo() {
        return "Servlet que maneja la carga de la página principal y el catálogo de videojuegos.";
    }

}
