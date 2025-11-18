package joystickmx.itson.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import joystickmx.itson.DTO.ResenaDTO;
import joystickmx.itson.DTO.VideojuegoDTO;
import joystickmx.itson.Factory.FactoryBO;
import joystickmx.negocio.exception.NegocioException;

/**
 *
 * @author PC WHITE WOLF
 */
@WebServlet(name = "ModerarServlet", urlPatterns = {"/moderar"})
public class ModerarServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ModerarServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ModerarServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

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
        String idVideojuego = request.getParameter("idVideojuego");
        try {
            // Extrae el ID del videojuego
            Long idVideojuegoLong = Long.valueOf(idVideojuego);
            // Busca el videojuego
            VideojuegoDTO videojuego = FactoryBO.buscarVideojuegoPorId(idVideojuegoLong);
            // Busca las resenas del videojuego
            List<ResenaDTO> resenas = FactoryBO.buscarResenasPorVideojuego(idVideojuegoLong);
            // Agrega ambos valores a la petición
            request.setAttribute("videojuego", videojuego);
            request.setAttribute("resenas", resenas);
            // Envía la petición al JSP
            request.getRequestDispatcher("/WEB-INF/admin/resenas/moderar.jsp").forward(request, response);
        } catch (ServletException | IOException | NumberFormatException | NegocioException e) {
            request.setAttribute("error", "Error durante la consulta del videojuego.");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
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
        processRequest(request, response);
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