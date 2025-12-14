package joystickmx.itson.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import joystickmx.itson.DTO.CategoriaDTO;
import joystickmx.itson.DTO.VideojuegoDTO;
import joystickmx.itson.Fachada.FachadaBO;
import joystickmx.negocio.exception.NegocioException;

/**
 *
 * @author Ariel Eduardo Borbon Izaguirre ID: 00000252116
 * @author Sebastián Bórquez Huerta ID: 00000252115
 * @author Leonardo Flores Leyva ID: 00000252390
 * @author Yuri Germán García López ID: 00000252583
 */
@WebServlet(name = "ConfirmarEliminarVideojuegoServlet", urlPatterns = {"/admin/productos/confirmar-eliminar"})
public class ConfirmarEliminarVideojuegoServlet extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(ConfirmarEliminarVideojuegoServlet.class.getName());

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idStr = request.getParameter("idVideojuego");

        try {
            if (idStr == null || idStr.isEmpty()) {
                throw new NegocioException("ID de videojuego no proporcionado.");
            }
            Long idVideojuego = Long.parseLong(idStr);

            VideojuegoDTO videojuego = FachadaBO.buscarVideojuegoPorId(idVideojuego);
            if (videojuego == null) {
                throw new NegocioException("El videojuego no existe.");
            }

            List<CategoriaDTO> categorias = FachadaBO.buscarTodasCategorias();

            request.setAttribute("videojuego", videojuego);
            request.setAttribute("categoriasDisponibles", categorias);

            request.getRequestDispatcher("/WEB-INF/admin/productos/confirmarEliminar.jsp").forward(request, response);

        } catch (Exception e) {
            LOG.log(Level.SEVERE, "Error al preparar eliminación", e);
            response.sendRedirect(request.getContextPath() + "/home?error=" + e.getMessage());
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idStr = request.getParameter("idVideojuego");

        try {
            if (idStr == null) {
                throw new NegocioException("ID no recibido para eliminar.");
            }
            Long idVideojuego = Long.parseLong(idStr);

            VideojuegoDTO videojuego = FachadaBO.buscarVideojuegoPorId(idVideojuego);

            FachadaBO.deshabilitarVideojuego(idVideojuego);

            request.setAttribute("videojuego", videojuego);

            request.setAttribute("categoriasDisponibles", FachadaBO.buscarTodasCategorias());

            request.getRequestDispatcher("/WEB-INF/admin/productos/productoEliminado.jsp").forward(request, response);

        } catch (Exception e) {
            LOG.log(Level.SEVERE, "Error al eliminar videojuego", e);
            response.sendRedirect(request.getContextPath() + "/home?error=No+se+pudo+eliminar");
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}