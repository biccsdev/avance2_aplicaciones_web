/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
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
import joystickmx.itson.DTO.UsuarioDTO;
import joystickmx.itson.Factory.FactoryBO;
import joystickmx.negocio.exception.NegocioException;

/**
 *
 * @author PC Gamer
 */
@WebServlet(name = "GestionarUsuariosServlet", urlPatterns = {"/gestionar"})
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

        try {

            List<UsuarioDTO> listaUsuarios = FactoryBO.buscarClientesExistentes();      //esto cuando el metodo estaba roto si funcionaba, pero ahora que ya essta bien no funciona y no abre la pagina

            request.setAttribute("listaUsuarios", listaUsuarios);

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error al cargar la lista de usuarios", e);
            request.setAttribute("error", "Error al cargar los datos: " + e.getMessage());
        }

        request.getRequestDispatcher("/WEB-INF/admin/usuarios/gestionar.jsp")
                .forward(request, response);
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
                    FactoryBO.activarUsuario(correoUsuario);
                    break;

                case "desactivar":
                    FactoryBO.desactivarUsuario(correoUsuario);
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
            response.sendRedirect(request.getContextPath() + "/gestionar");
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
