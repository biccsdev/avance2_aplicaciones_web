package joystickmx.itson.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import joystickmx.itson.DTO.ResenaDTO;
import joystickmx.itson.DTO.UsuarioDTO;
import joystickmx.itson.DTO.VideojuegoDTO;
import joystickmx.itson.DTO.VideojuegoResenaDTO;
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
@WebServlet(name = "ModerarServlet", urlPatterns = {"/admin/moderar"})
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
        try {
            // En caso de que seleccione un videojuego en el catálogo
            if(request.getParameter("idVideojuego") != null){
                String idVideojuego = request.getParameter("idVideojuego");
                // Extrae el ID del videojuego
                Long idVideojuegoLong = Long.valueOf(idVideojuego);
                // Busca el videojuego
                VideojuegoDTO videojuego = FachadaBO.buscarVideojuegoPorId(idVideojuegoLong);
                // Busca las resenas del videojuego
                List<ResenaDTO> resenas = FachadaBO.buscarResenasPorVideojuego(idVideojuegoLong);
                // Lista con los detalles de la reseña
                List<VideojuegoResenaDTO> resenasVideojuegos = obtenerResenas(resenas);
                
                // Agrega ambos valores a la petición
                request.setAttribute("videojuego", videojuego);
                request.setAttribute("resenas", resenasVideojuegos);
                // Envía la petición al JSP
                request.getRequestDispatcher("/WEB-INF/admin/resenas/moderar.jsp").forward(request, response);
            } else if(request.getParameter("nombreVideojuego") != null){
                
                String nombreVideojuego = request.getParameter("nombreVideojuego");
                List<ResenaDTO> resenas = FachadaBO.buscarResenasPorNombreVideojuego(nombreVideojuego);
                // Lista con los detalles de la reseña
                List<VideojuegoResenaDTO> resenasVideojuegos = obtenerResenas(resenas);
                
                request.setAttribute("resenas", resenasVideojuegos);
                request.getRequestDispatcher("/WEB-INF/admin/resenas/moderar.jsp").forward(request, response);
                
            } else if(request.getParameter("calificacion") != null){
                String calificacion = request.getParameter("calificacion");
                Float calificacionLong = Float.valueOf(calificacion);
                List<ResenaDTO> resenas = FachadaBO.buscarResenasPorCalificacion(calificacionLong);
                // Lista con los detalles de la reseña
                List<VideojuegoResenaDTO> resenasVideojuegos = obtenerResenas(resenas);
                
                request.setAttribute("resenas", resenasVideojuegos);
                request.getRequestDispatcher("/WEB-INF/admin/resenas/moderar.jsp").forward(request, response);
            } else{
                List<ResenaDTO> resenas = FachadaBO.buscarTodasLasResenas();
                // Lista con los detalles de la reseña
                List<VideojuegoResenaDTO> resenasVideojuegos = obtenerResenas(resenas);
                
                request.setAttribute("resenas", resenasVideojuegos);
                request.getRequestDispatcher("/WEB-INF/admin/resenas/moderar.jsp").forward(request, response);
            }
        } catch (ServletException | IOException | NumberFormatException | NegocioException e) {
            request.setAttribute("mensaje", "Error durante la consulta de las reseñas.");
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
        if(request.getParameter("idResena") != null){
            Long idResena = Long.valueOf(request.getParameter("idResena"));
            try {
                FachadaBO.eliminarResenaPorId(idResena);
                request.setAttribute("mensaje", "Reseña eliminada con éxito.");
                doGet(request, response);
            } catch (NegocioException ex) {
                request.setAttribute("mensaje", "Error durante la eliminación de la reseña.");
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            }
        }
    }
    
    private List<VideojuegoResenaDTO> obtenerResenas(List<ResenaDTO> resenas) throws NegocioException{
        List<VideojuegoResenaDTO> resenasVideojuegos = new ArrayList<>();
        for(ResenaDTO resena: resenas){

            VideojuegoResenaDTO resenaVideojuego = new VideojuegoResenaDTO();

            UsuarioDTO cliente = FachadaBO.buscarClientePorId(resena.getIdCliente());

            VideojuegoDTO videojuego = FachadaBO.buscarVideojuegoPorId(resena.getIdVideojuego());

            resenaVideojuego.setResena(resena);
            resenaVideojuego.setNombreJugador(cliente.getNombres());
            resenaVideojuego.setNombreVideojuego(videojuego.getNombre());
            resenaVideojuego.setUrlImagen(videojuego.getUrlImagen());

            resenasVideojuegos.add(resenaVideojuego);
        }
        return resenasVideojuegos;
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