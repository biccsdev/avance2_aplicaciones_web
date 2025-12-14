package joystickmx.itson.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import joystickmx.itson.DTO.UsuarioDTO;
import joystickmx.itson.Fachada.FachadaBO;
import joystickmx.negocio.exception.NegocioException;

/**
 *
 * @author PC Gamer
 */
@WebServlet(name = "GestionarUsuariosServlet", urlPatterns = {"/admin/usuarios"})
public class GestionarUsuariosServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(GestionarUsuariosServlet.class.getName());

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

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("usuario") == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }
        UsuarioDTO user = (UsuarioDTO) session.getAttribute("usuario");
        if (!"admin".equals(user.getRol())) {
            response.sendRedirect(request.getContextPath() + "/home");
            return;
        }

        String busqueda = request.getParameter("busqueda_usuario");

        List<UsuarioDTO> listaUsuarios;

        try {
            if (busqueda != null && !busqueda.trim().isEmpty()) {
                listaUsuarios = FachadaBO.buscarClientesPorNombreNoEliminados(busqueda.trim());
                
                if (listaUsuarios.isEmpty() || listaUsuarios == null) {
                    listaUsuarios = FachadaBO.buscarClientesPorNombre(busqueda.trim());
                }
                
                
            } else {
                listaUsuarios = FachadaBO.buscarClientesExistentes();
            }

            request.setAttribute("listaUsuarios", listaUsuarios);

        } catch (NegocioException e) {
            request.setAttribute("error", "Error al cargar usuarios: " + e.getMessage());
            request.setAttribute("listaUsuarios", new ArrayList<UsuarioDTO>());
        }

        request.getRequestDispatcher("/WEB-INF/admin/usuarios/gestionar.jsp").forward(request, response);
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

        String action = request.getParameter("action");
        String correoUsuario = request.getParameter("correo");

        String error = null;

        try {
            if (action == null || correoUsuario == null) {
                throw new NegocioException("Accion o Correo de usuario no especificado.");
            }

            switch (action) {
                case "activar":
                    FachadaBO.activarUsuario(correoUsuario);
                    break;

                case "desactivar":
                    FachadaBO.desactivarUsuario(correoUsuario);
                    break;

                case "eliminar":
                    response.sendRedirect(
                            request.getContextPath() + "/admin/usuarios/confirmar-eliminar?email=" + correoUsuario
                    );
                    return;

            }

        } catch (NegocioException e) {
            error = e.getMessage();
        }

        if (error == null) {
            response.sendRedirect(request.getContextPath() + "/admin/usuarios");
        } else {
            request.setAttribute("error", error);
            doGet(request, response);
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