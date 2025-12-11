package joystickmx.itson.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import joystickmx.itson.DTO.DireccionDTO;
import joystickmx.itson.DTO.UsuarioDTO;
import joystickmx.itson.DTO.UsuarioRegistroDTO;
import joystickmx.itson.Factory.FactoryBO;
import joystickmx.negocio.exception.NegocioException;

/**
 *
 * @author Ariel Eduardo Borbon Izaguirre ID: 00000252116
 * @author Sebastián Bórquez Huerta ID: 00000252115
 * @author Leonardo Flores Leyva ID: 00000252390
 * @author Yuri Germán García López ID: 00000252583
 */
@WebServlet(name = "EditarPerfilServlet", urlPatterns = {"/perfil/editar", "/admin/perfil/editar"})
public class EditarPerfilServlet extends HttpServlet {

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

        // Validar sesión
        if (session == null || session.getAttribute("usuario") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        // Determinar qué JSP cargar según ruta
        String path = request.getServletPath();

        if (path.equals("/admin/perfil/editar")) {
            request.getRequestDispatcher("/WEB-INF/admin/perfil/editar.jsp")
                    .forward(request, response);
        } else {
            request.getRequestDispatcher("/WEB-INF/user/perfil/editar.jsp")
                    .forward(request, response);
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

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("usuario") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        try {
            UsuarioDTO usuarioSesion = (UsuarioDTO) session.getAttribute("usuario");
            String email = usuarioSesion.getEmail();
            String rol = usuarioSesion.getRol();

            String nombres = request.getParameter("nombres");
            String apPaterno = request.getParameter("apellidoPaterno");
            String apMaterno = request.getParameter("apellidoMaterno");
            String telefono = request.getParameter("telefono");
            
            String contrasena = request.getParameter("contrasenia");

            DireccionDTO dirDTO = new DireccionDTO();
            dirDTO.setCalle(request.getParameter("calle"));
            dirDTO.setNumero(request.getParameter("numero"));
            dirDTO.setColonia(request.getParameter("colonia"));

            UsuarioRegistroDTO actualizacion = new UsuarioRegistroDTO();
            actualizacion.setEmail(email);
            actualizacion.setNombres(nombres);
            actualizacion.setApellidoPaterno(apPaterno);
            actualizacion.setApellidoMaterno(apMaterno);
            actualizacion.setTelefono(telefono);
            actualizacion.setDireccion(dirDTO);
            actualizacion.setEstadoUsuario(usuarioSesion.getEstadoUsuario());

            if (contrasena != null && !contrasena.trim().isEmpty()) {
                actualizacion.setContrasenia(contrasena);
            }

            UsuarioDTO actualizado = FactoryBO.actualizarUsuario(actualizacion);

            session.setAttribute("usuario", actualizado);

            session.setAttribute("successMessage", "¡Perfil actualizado con éxito!");

            if ("admin".equals(rol)) {
                response.sendRedirect(request.getContextPath() + "/admin/perfil");
            } else {
                response.sendRedirect(request.getContextPath() + "/perfil");
            }

        } catch (NegocioException e) {

            request.setAttribute("errorMessage", e.getMessage());

            UsuarioDTO usuarioSesion = (UsuarioDTO) session.getAttribute("usuario");

            if ("admin".equals(usuarioSesion.getRol())) {
                request.getRequestDispatcher("/WEB-INF/admin/perfil/editar.jsp")
                        .forward(request, response);
            } else {
                request.getRequestDispatcher("/WEB-INF/user/perfil/editar.jsp")
                        .forward(request, response);
            }
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