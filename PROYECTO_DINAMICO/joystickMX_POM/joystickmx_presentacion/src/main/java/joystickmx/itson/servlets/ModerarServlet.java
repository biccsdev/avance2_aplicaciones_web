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
        try {
            // En caso de que seleccione un videojuego en el catálogo
            if(request.getParameter("idVideojuego") != null){
                String idVideojuego = request.getParameter("idVideojuego");
                // Extrae el ID del videojuego
                Long idVideojuegoLong = Long.valueOf(idVideojuego);
                // Busca el videojuego
                VideojuegoDTO videojuego = FactoryBO.buscarVideojuegoPorId(idVideojuegoLong);
                // Busca las resenas del videojuego
                List<ResenaDTO> resenas = FactoryBO.buscarResenasPorVideojuego(idVideojuegoLong);
                // Lista con los detalles de la reseña
                List<VideojuegoResenaDTO> resenasVideojuegos = new ArrayList<>();
                
                for(ResenaDTO resena: resenas){
                    VideojuegoResenaDTO resenaVideojuego = new VideojuegoResenaDTO();
                    UsuarioDTO cliente = FactoryBO.buscarClientePorId(resena.getIdCliente());
                    
                    resenaVideojuego.setResena(resena);
                    resenaVideojuego.setNombreJugador(cliente.getNombres());
                    resenaVideojuego.setNombreVideojuego(String.format("%s (%s)", videojuego.getNombre(), videojuego.getPlataforma()));
                    resenaVideojuego.setUrlImagen(videojuego.getUrlImagen());
                    
                    resenasVideojuegos.add(resenaVideojuego);
                }
                // Agrega ambos valores a la petición
                request.setAttribute("videojuego", videojuego);
                request.setAttribute("resenas", resenasVideojuegos);
                // Envía la petición al JSP
                request.getRequestDispatcher("/WEB-INF/admin/resenas/moderar.jsp").forward(request, response);
            } else if(request.getParameter("nombreVideojuego") != null){
                
                String nombreVideojuego = request.getParameter("nombreVideojuego");
                List<ResenaDTO> resenas = FactoryBO.buscarResenasPorNombreVideojuego(nombreVideojuego);
                // Lista con los detalles de la reseña
                List<VideojuegoResenaDTO> resenasVideojuegos = new ArrayList<>();
                for(ResenaDTO resena: resenas){
                    
                    VideojuegoResenaDTO resenaVideojuego = new VideojuegoResenaDTO();
                    
                    UsuarioDTO cliente = FactoryBO.buscarClientePorId(resena.getIdCliente());
                    
                    VideojuegoDTO videojuego = FactoryBO.buscarVideojuegoPorId(resena.getIdVideojuego());
                    
                    resenaVideojuego.setResena(resena);
                    resenaVideojuego.setNombreJugador(cliente.getNombres());
                    resenaVideojuego.setNombreVideojuego(String.format("%s (%s)", videojuego.getNombre(), videojuego.getPlataforma()));
                    resenaVideojuego.setUrlImagen(videojuego.getUrlImagen());
                    
                    resenasVideojuegos.add(resenaVideojuego);
                }
                request.setAttribute("resenas", resenasVideojuegos);
                request.getRequestDispatcher("/WEB-INF/admin/resenas/moderar.jsp").forward(request, response);
                
            } else if(request.getParameter("calificacion") != null){
                String calificacion = request.getParameter("calificacion");
                Float calificacionLong = Float.valueOf(calificacion);
                List<ResenaDTO> resenas = FactoryBO.buscarResenasPorCalificacion(calificacionLong);
                // Lista con los detalles de la reseña
                List<VideojuegoResenaDTO> resenasVideojuegos = new ArrayList<>();
                for(ResenaDTO resena: resenas){
                    
                    VideojuegoResenaDTO resenaVideojuego = new VideojuegoResenaDTO();
                    
                    UsuarioDTO cliente = FactoryBO.buscarClientePorId(resena.getIdCliente());
                    
                    VideojuegoDTO videojuego = FactoryBO.buscarVideojuegoPorId(resena.getIdVideojuego());
                    
                    resenaVideojuego.setResena(resena);
                    resenaVideojuego.setNombreJugador(cliente.getNombres());
                    resenaVideojuego.setNombreVideojuego(String.format("%s (%s)", videojuego.getNombre(), videojuego.getPlataforma()));
                    resenaVideojuego.setUrlImagen(videojuego.getUrlImagen());
                    
                    resenasVideojuegos.add(resenaVideojuego);
                }
                request.setAttribute("resenas", resenasVideojuegos);
                request.getRequestDispatcher("/WEB-INF/admin/resenas/moderar.jsp").forward(request, response);
            } else{
                List<ResenaDTO> resenas = FactoryBO.buscarTodasLasResenas();
                // Lista con los detalles de la reseña
                List<VideojuegoResenaDTO> resenasVideojuegos = new ArrayList<>();
                for(ResenaDTO resena: resenas){
                    
                    VideojuegoResenaDTO resenaVideojuego = new VideojuegoResenaDTO();
                    
                    UsuarioDTO cliente = FactoryBO.buscarClientePorId(resena.getIdCliente());
                    
                    VideojuegoDTO videojuego = FactoryBO.buscarVideojuegoPorId(resena.getIdVideojuego());
                    
                    resenaVideojuego.setNombreJugador(cliente.getNombres());
                    resenaVideojuego.setNombreVideojuego(videojuego.getNombre());
                    resenaVideojuego.setUrlImagen(videojuego.getUrlImagen());
                    
                    resenasVideojuegos.add(resenaVideojuego);
                }
                request.setAttribute("resenas", resenasVideojuegos);
                request.getRequestDispatcher("/WEB-INF/admin/resenas/moderar.jsp").forward(request, response);
            }
        } catch (ServletException | IOException | NumberFormatException | NegocioException e) {
            request.setAttribute("mensaje", "Error durante la consulta del videojuego.");
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