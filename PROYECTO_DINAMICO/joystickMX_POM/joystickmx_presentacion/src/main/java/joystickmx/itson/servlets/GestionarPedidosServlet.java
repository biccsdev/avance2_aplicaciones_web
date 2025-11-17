/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package joystickmx.itson.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import joystickmx.itson.DTO.PedidoDTO;
import joystickmx.itson.Factory.FactoryBO;
import joystickmx.negocio.exception.NegocioException;

/**
 *
 * @author Usuario
 */
@WebServlet(name = "GestionarPedidosServlet", urlPatterns = {"/admin/pedidos/gestionar"})
public class GestionarPedidosServlet extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(GestionarPedidosServlet.class.getName());

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
            out.println("<title>Servlet PedidosAdminServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PedidosAdminServlet at " + request.getContextPath() + "</h1>");
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

        // --- Lógica para mensajes (¡Importante!) ---
        // Revisa si hay un mensaje de éxito viniendo de una redirección (POST)
        String successMessage = (String) request.getSession().getAttribute("successMessage");
        if (successMessage != null) {
            // Lo quitamos de la sesión para que no se muestre de nuevo
            request.getSession().removeAttribute("successMessage");
            // Lo ponemos en el request para que el JSP lo pueda leer esta vez
            request.setAttribute("successMessage", successMessage);
        }

        // Obtenemos el mensaje de error (si un POST falló y nos reenvió aquí)
        String errorMessage = (String) request.getAttribute("errorMessage");

        // --- Fin de Lógica para mensajes ---
        List<PedidoDTO> listaPedidos = null;
        try {
            // Asegúrate que tu método en FactoryBO se llame así
            listaPedidos = FactoryBO.obtenerPedidos();

        } catch (NegocioException e) {
            LOG.log(Level.SEVERE, "Error al obtener la lista de pedidos", e);
            if (errorMessage == null) { // Solo si no hay ya un error de un POST
                errorMessage = "Error al cargar la lista de pedidos: " + e.getMessage();
            }
        }

        // Pasamos los datos (o el error) al JSP
        request.setAttribute("listaPedidos", listaPedidos);
        if (errorMessage != null) {
            request.setAttribute("errorMessage", errorMessage);
        }

        // Redirigimos al JSP
        request.getRequestDispatcher("/WEB-INF/admin/pedidos/lista.jsp")
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

        // 1. Obtenemos los parámetros del formulario (tu JSP los envía así)
        String action = request.getParameter("action");
        String pedidoIdStr = request.getParameter("pedidoId");
        String nuevoEstado = request.getParameter("nuevoEstado"); // PENDIENTE, ENVIADO, etc.

        String errorMessage = null;
        Long pedidoId = null;

        try {
            // 2. Validamos que la acción sea la que esperamos del formulario
            if ("cambiarEstado".equals(action)) {

                // Validamos y convertimos el ID
                if (pedidoIdStr == null || pedidoIdStr.isEmpty()) {
                    throw new NegocioException("ID de pedido no proporcionado.");
                }
                try {
                    pedidoId = Long.parseLong(pedidoIdStr);
                } catch (NumberFormatException e) {
                    throw new NegocioException("ID de pedido no válido: " + pedidoIdStr);
                }

                // 3. ¡AQUÍ ESTÁ LA LÓGICA!
                // Un switch sobre el estado seleccionado en el dropdown
                switch (nuevoEstado.toUpperCase()) {
                    case "PENDIENTE":
                        FactoryBO.pedidoPendiente(pedidoId);
                        break;
                    case "ENVIADO":
                        FactoryBO.pedidoEnviado(pedidoId);
                        break;
                    case "ENTREGADO":
                        FactoryBO.pedidoEntregado(pedidoId);
                        break;
                    case "CANCELADO":
                        FactoryBO.pedidoCancelado(pedidoId);
                        break;
                    default:
                        throw new NegocioException("Estado desconocido: " + nuevoEstado);
                }

            } else {
                throw new NegocioException("Acción de formulario no reconocida.");
            }

        } catch (NegocioException e) {
            // 4. Manejo de errores de negocio o validación
            LOG.log(Level.SEVERE, "Error de negocio en GestionarPedidosServlet", e);
            errorMessage = e.getMessage();
        } catch (Exception e) {
            // 5. Manejo de cualquier otro error inesperado
            LOG.log(Level.SEVERE, "Error inesperado en GestionarPedidosServlet", e);
            errorMessage = "Ocurrió un error inesperado. Por favor, intente de nuevo.";
        }

        // 6. Lógica final de redirección
        if (errorMessage == null) {
            // ÉXITO: Redirigir de vuelta a la lista (Patrón Post-Redirect-Get)

            // Usamos la sesión para mostrar un mensaje de éxito DESPUÉS de redirigir
            String successMessage = "Estado del pedido " + pedidoId + " actualizado a '" + nuevoEstado + "'.";
            request.getSession().setAttribute("successMessage", successMessage);

            response.sendRedirect(request.getContextPath() + "/admin/pedidos/gestionar");
        } else {
            // ERROR: Reenviar al JSP (vía doGet) para mostrar el error
            request.setAttribute("errorMessage", errorMessage);
            // doGet se encargará de recargar la lista de pedidos y mostrar el error
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
