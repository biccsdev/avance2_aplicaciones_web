package joystickmx.itson.servlets;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.logging.Level;
import java.util.logging.Logger;
import joystickmx.itson.DTO.CategoriaDTO;
import joystickmx.itson.DTO.UsuarioDTO;
import joystickmx.itson.Fachada.FachadaBO;
import joystickmx.itson.RellenoBD.RellenoBD;
import joystickmx.negocio.exception.NegocioException;

/**
 *
 * @author Ariel Eduardo Borbon Izaguirre ID: 00000252116
 * @author Sebastián Bórquez Huerta ID: 00000252115
 * @author Leonardo Flores Leyva ID: 00000252390
 * @author Yuri Germán García López ID: 00000252583
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Muestra la página de login.
     *
     * @param request
     * @param response
     * @throws jakarta.servlet.ServletException
     * @throws java.io.IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession(true);
            if (session.getAttribute("usuario") != null) {
                response.sendRedirect(request.getContextPath() + "/home");
                return;
            }

            CategoriaDTO categoria = FachadaBO.buscarCategoriaPorNombre("Acción y Aventuras");
            
            if (categoria == null) {
                RellenoBD.llenarBD();
            }
            
            request.getRequestDispatcher("/login.jsp").forward(request, response); 
        } catch (NegocioException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Procesa el intento de login.
     *
     * @param request
     * @param response
     * @throws jakarta.servlet.ServletException
     * @throws java.io.IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            UsuarioDTO usuario = FachadaBO.login(email, password); 

            HttpSession session = request.getSession(true);
            session.setAttribute("usuario", usuario);

            if (usuario.getRol().equals("admin")) {
                session.setAttribute("rol", "admin");
            } else if (usuario.getRol().equals("cliente")) {
                session.setAttribute("rol", "cliente");
            } else {
                session.setAttribute("rol", "DESCONOCIDO");
            }

            response.sendRedirect(request.getContextPath() + "/home");

        } catch (NegocioException e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/login.jsp").forward(request, response);
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
