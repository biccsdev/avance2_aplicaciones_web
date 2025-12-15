package joystickmx.itson.servlets;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import joystickmx.itson.DTO.DireccionDTO;
import joystickmx.itson.DTO.UsuarioDTO;
import joystickmx.itson.DTO.UsuarioRegistroDTO;
import joystickmx.itson.Fachada.FachadaBO;

/**
 *
 * @author Ariel Eduardo Borbón Izaguirre ID: 00000252116
 * @author Sebastián Bórquez Huerta ID: 00000252115
 * @author Leonardo Flores Leyva ID: 00000252390
 * @author Yuri Germán García López ID: 00000252583
 * @author Victor Gerardo Torres García ID: 205869
 */
@WebServlet(name = "RegisterServlet", urlPatterns = {"/register"})
public class RegisterServlet extends HttpServlet {

    private static final String REGEX_SOLO_LETRAS = "^[a-zA-ZÁÉÍÓÚáéíóúñÑÜü\\s]+$";
    private static final String REGEX_SOLO_NUMEROS = "^[0-9]+$";

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
        HttpSession session = request.getSession(true);
        if (session.getAttribute("usuario") != null) {
            response.sendRedirect(request.getContextPath() + "/home");
            return;
        }
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
        String apellidoPaterno = request.getParameter("apellidoPaterno");
        String apellidoMaterno = request.getParameter("apellidoMaterno"); // Opcional
        String colonia = request.getParameter("colonia");
        String calle = request.getParameter("calle");
        String numero = request.getParameter("numero");
        String telefono = request.getParameter("telefono");

        String error = null;

        if (esVacio(email) || esVacio(password) || esVacio(nombre)
                || esVacio(apellidoPaterno) || esVacio(colonia) || esVacio(calle)
                || esVacio(numero) || esVacio(telefono)) {
            error = "Todos los campos marcados con (*) son obligatorios.";
        } else if (!nombre.matches(REGEX_SOLO_LETRAS)
                || !apellidoPaterno.matches(REGEX_SOLO_LETRAS)
                || (!esVacio(apellidoMaterno) && !apellidoMaterno.matches(REGEX_SOLO_LETRAS))) {
            error = "Los nombres y apellidos solo pueden contener letras.";
        } else if (!colonia.matches(REGEX_SOLO_LETRAS)) {
            error = "La colonia solo puede contener letras y espacios.";
        } else if (colonia.trim().length() < 5) {
            error = "La colonia debe tener al menos 5 letras.";
        } else if (!numero.matches(REGEX_SOLO_NUMEROS)) {
            error = "El número exterior solo debe contener dígitos.";
        } else if (numero.length() < 3 || numero.length() > 4) {
            error = "El número exterior debe tener entre 3 y 4 dígitos.";
        } else if (!telefono.matches(REGEX_SOLO_NUMEROS)) {
            error = "El teléfono solo debe contener números.";
        } else if (telefono.length() != 10) {
            error = "El teléfono debe tener exactamente 10 dígitos.";
        } else if (password.length() < 4) {
            error = "La contraseña debe tener al menos 4 caracteres.";
        }

        if (error != null) {
            request.setAttribute("error", error);
            request.getRequestDispatcher("/register.jsp").forward(request, response);
            return;
        }

        try {
            FachadaBO.registrarCliente(new UsuarioRegistroDTO(
                    nombre,
                    apellidoPaterno,
                    apellidoMaterno,
                    email,
                    telefono,
                    password,
                    new DireccionDTO(calle, numero, colonia))
            );

            UsuarioDTO usuario = FachadaBO.buscarUsuarioPorEmail(email);

            HttpSession session = request.getSession(true);
            session.setAttribute("usuario", usuario);
            session.setAttribute("rol", "cliente");

            response.sendRedirect(request.getContextPath() + "/home");

        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/register.jsp").forward(request, response);
        }
    }

    // Método auxiliar para limpiar el if
    private boolean esVacio(String texto) {
        return texto == null || texto.trim().isEmpty();
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
