package joystickmx.itson.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import joystickmx.itson.DTO.UsuarioDTO;
import joystickmx.itson.Factory.FactoryBO;

/**
 *
 * @author Ariel Eduardo Borbon Izaguirre ID: 00000252116
 * @author Sebastián Bórquez Huerta ID: 00000252115
 * @author Leonardo Flores Leyva ID: 00000252390
 * @author Yuri Germán García López ID: 00000252583
 */
@WebServlet(name = "EliminarUsuarioServlet", urlPatterns = {"/eliminarUsuarioConfirmar"})
public class EliminarUsuarioServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(EliminarUsuarioServlet.class.getName());

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

        String email = request.getParameter("email");

        if (email == null || email.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/admin/usuarios/gestionar");
            return;
        }

        try {
            UsuarioDTO usuario = FactoryBO.buscarUsuarioPorEmail(email);

            if (usuario == null) {
                throw new RuntimeException("Usuario no encontrado");
            }

            request.setAttribute("usuario", usuario);

            request.getRequestDispatcher("/WEB-INF/admin/usuarios/confirmar-eliminar.jsp")
                    .forward(request, response);

        } catch (Exception e) {
            response.sendRedirect(request.getContextPath() + "/admin/usuarios/gestionar?error=true");
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

        String email = request.getParameter("email");
        String contextPath = request.getContextPath();

        if (email == null || email.isEmpty()) {
            response.sendRedirect(contextPath + "/admin/usuarios/gestionar?error=falta_email");
            return;
        }

        try {
            FactoryBO.eliminarUsuario(email);

            response.sendRedirect(contextPath + "/admin/usuarios/usuario-eliminado?email=" + email);

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error al eliminar usuario", e);
            response.sendRedirect(contextPath + "/admin/usuarios/gestionar?error=true");
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