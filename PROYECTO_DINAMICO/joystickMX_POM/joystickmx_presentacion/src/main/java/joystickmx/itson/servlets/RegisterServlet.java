package joystickmx.itson.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import joystickmx.itson.DTO.AdministradorDTO;
import joystickmx.itson.DTO.ClienteDTO;
import joystickmx.itson.DTO.DireccionDTO;
import joystickmx.itson.DTO.UsuarioDTO;
import joystickmx.itson.DTO.UsuarioRegistroDTO;
import joystickmx.itson.Factory.FactoryBO;

/**
 *
 * @author PC WHITE WOLF
 */
@WebServlet(name = "RegisterServlet", urlPatterns = {"/register"})
public class RegisterServlet extends HttpServlet {

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
        // Obtiene la sesión de la petición
        HttpSession session = request.getSession(true);
        // Verifica que no haya una sesión iniciada.
        if(session.getAttribute("usuario") != null){
            response.sendRedirect(request.getContextPath() + "/home");
            return;
        }
        // Si no hay una sesión asociada a la petición, se manda a la página del registro.
        request.getRequestDispatcher("/register.jsp").forward(request, response);
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
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String nombre = request.getParameter("nombre");
        String apellidoMaterno = request.getParameter("apellidoPaterno");
        String apellidoPaterno = request.getParameter("apellidoMaterno");
        String colonia = request.getParameter("colonia");
        String calle = request.getParameter("calle");
        String numero = request.getParameter("numero");
        String telefono = request.getParameter("telefono");
        try {
            // Se registra el cliente en la base de datos
            FactoryBO.registrarCliente(new UsuarioRegistroDTO(
                    nombre, 
                    apellidoPaterno, 
                    apellidoMaterno, 
                    email, 
                    telefono, 
                    password, 
                    new DireccionDTO(calle, numero, colonia))
            );
            // Si el registro es exitoso (no lanzó excepción), se obtiene el usuario registrado
            UsuarioDTO usuario = FactoryBO.buscarUsuarioPorEmail(email);
            
            // Se agrega el rol del cliente y su información a la sesión.
            HttpSession session = request.getSession(true);
            session.setAttribute("usuario", usuario);
            session.setAttribute("rol", "cliente");
            // Se manda al catálogo de videojuegos.
            response.sendRedirect(request.getContextPath() + "/home");
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/register.jsp").forward(request, response);
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