package joystickmx.itson.servlets;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
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
@WebServlet(name = "VideojuegoServlet", urlPatterns = {"/videojuego"})
public class VideojuegoServlet extends HttpServlet {


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
        String nombre = request.getParameter("nombre");
        
        String mensajeError = "El videojuego no está disponible o ha sido eliminado.";
        
        if(nombre != null && !nombre.trim().isEmpty()) {
            try {
                VideojuegoDTO videojuego = FachadaBO.buscarVideojuegoPorNombeExacto(nombre);
                
                if (videojuego != null && Boolean.TRUE.equals(videojuego.isHabilitado())) {    
                    request.getRequestDispatcher("/videojuego/detalleVideojuego.jsp").forward(request, response);
                    
                } else {
                    response.sendRedirect(request.getContextPath() + "/home?error=" + URLEncoder.encode(mensajeError, StandardCharsets.UTF_8));
                }
                
            } catch (NegocioException e) {
                e.printStackTrace();
                response.sendRedirect(request.getContextPath() + "/home?error=" + URLEncoder.encode(mensajeError, StandardCharsets.UTF_8));
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/home");
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
        doGet(request, response);
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