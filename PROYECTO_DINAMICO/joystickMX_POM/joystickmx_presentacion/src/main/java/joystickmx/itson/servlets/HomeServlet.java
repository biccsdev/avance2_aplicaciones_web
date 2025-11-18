package joystickmx.itson.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import joystickmx.itson.DTO.VideojuegoDTO;
import joystickmx.itson.Factory.FactoryBO;
import joystickmx.negocio.exception.NegocioException;

/**
 *
 * @author PC Gamer
 */
@WebServlet(name = "HomeServlet", urlPatterns = {"/home", ""})
public class HomeServlet extends HttpServlet {
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
            out.println("<title>Servlet HomeServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet HomeServlet at " + request.getContextPath() + "</h1>");
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
            videojuegos = FactoryBO.filtrarVideojuegos(
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
        doGet(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Servlet que maneja la carga de la página principal y el catálogo de videojuegos.";
    }

}
