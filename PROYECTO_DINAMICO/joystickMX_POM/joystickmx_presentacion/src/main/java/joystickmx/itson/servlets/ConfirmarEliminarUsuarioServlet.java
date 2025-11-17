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
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import joystickmx.itson.DTO.UsuarioDTO;
import joystickmx.itson.Factory.FactoryBO;

/**
 *
 * @author PC Gamer
 */
@WebServlet(name = "ConfirmarEliminarUsuarioServlet", urlPatterns = {"/admin/usuarios/confirmar-eliminar"})
public class ConfirmarEliminarUsuarioServlet extends HttpServlet {  

    private static final Logger LOG = Logger.getLogger(ConfirmarEliminarUsuarioServlet.class.getName());

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
            out.println("<title>Servlet ConfirmarEliminarUsuarioServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ConfirmarEliminarUsuarioServlet at " + request.getContextPath() + "</h1>");
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
        
        String idUsuarioStr = request.getParameter("id");
        String contextPath = request.getContextPath();

        if (idUsuarioStr == null || idUsuarioStr.isEmpty()) {
            response.sendRedirect(contextPath + "/admin/usuarios/gestionar");
            return;
        }

        try {
            Long idUsuario = Long.valueOf(idUsuarioStr);
            UsuarioDTO usuario = FactoryBO.buscarClientePorId(idUsuario);
            
            if (usuario == null) {
                response.sendRedirect(contextPath + "/admin/usuarios/gestionar?error=no_encontrado");
                return;
            }
            
            request.setAttribute("usuario", usuario);
            request.getRequestDispatcher("/WEB-INF/admin/usuarios/confirmarEliminarUsuario.jsp")
                   .forward(request, response);

        } catch (Exception e) {
            LOG.log(Level.SEVERE, "Error al buscar usuario para confirmar eliminación", e);
            response.sendRedirect(contextPath + "/admin/usuarios/gestionar?error=true");
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
