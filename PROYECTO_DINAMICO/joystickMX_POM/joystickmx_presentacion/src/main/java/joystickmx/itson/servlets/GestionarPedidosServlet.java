package joystickmx.itson.servlets;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import joystickmx.itson.DTO.PedidoDTO;
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
@WebServlet(name = "GestionarPedidosServlet", urlPatterns = {"/admin/pedidos/gestionar"})
public class GestionarPedidosServlet extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(GestionarPedidosServlet.class.getName());

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

        String successMessage = (String) request.getSession().getAttribute("successMessage");
        if (successMessage != null) {
            request.getSession().removeAttribute("successMessage");
            request.setAttribute("successMessage", successMessage);
        }
        String errorMessage = (String) request.getAttribute("errorMessage");

        String filtroNombre = request.getParameter("filtroNombrePedidos");

        List<PedidoDTO> listaPedidos = null;
        try {

            if (filtroNombre != null && !filtroNombre.trim().isEmpty()) {
                listaPedidos = FachadaBO.buscarPedidosPorNombreClienteParcial(filtroNombre.trim());

                request.setAttribute("filtroAplicado", filtroNombre);

            } else {
                listaPedidos = FachadaBO.obtenerPedidos();
            }

        } catch (NegocioException e) {
            LOG.log(Level.SEVERE, "Error al obtener la lista de pedidos", e);
            if (errorMessage == null) {
                errorMessage = "Error al cargar la lista de pedidos: " + e.getMessage();
            }
        }

        request.setAttribute("listaPedidos", listaPedidos);
        if (errorMessage != null) {
            request.setAttribute("errorMessage", errorMessage);
        }

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

        String action = request.getParameter("action");
        String pedidoIdStr = request.getParameter("pedidoId");
        String nuevoEstado = request.getParameter("nuevoEstado");

        String errorMessage = null;
        Long pedidoId = null;

        try {
            if ("cambiarEstado".equals(action)) {

                if (pedidoIdStr == null || pedidoIdStr.isEmpty()) {
                    throw new NegocioException("ID de pedido no proporcionado.");
                }
                try {
                    pedidoId = Long.parseLong(pedidoIdStr);
                } catch (NumberFormatException e) {
                    throw new NegocioException("ID de pedido no válido: " + pedidoIdStr);
                }

                switch (nuevoEstado.toUpperCase()) {
                    case "PENDIENTE":
                        FachadaBO.pedidoPendiente(pedidoId);
                        break;
                    case "ENVIADO":
                        FachadaBO.pedidoEnviado(pedidoId);
                        break;
                    case "ENTREGADO":
                        FachadaBO.pedidoEntregado(pedidoId);
                        break;
                    case "CANCELADO":
                        FachadaBO.pedidoCancelado(pedidoId);
                        break;
                    default:
                        throw new NegocioException("Estado desconocido: " + nuevoEstado);
                }

            } else {
                throw new NegocioException("Acción de formulario no reconocida.");
            }

        } catch (NegocioException e) {
            LOG.log(Level.SEVERE, "Error de negocio en GestionarPedidosServlet", e);
            errorMessage = e.getMessage();
        } catch (Exception e) {
            LOG.log(Level.SEVERE, "Error inesperado en GestionarPedidosServlet", e);
            errorMessage = "Ocurrió un error inesperado. Por favor, intente de nuevo.";
        }
        if (errorMessage == null) {
            String successMessage = "Estado del pedido " + pedidoId + " actualizado a '" + nuevoEstado + "'.";
            request.getSession().setAttribute("successMessage", successMessage);

            response.sendRedirect(request.getContextPath() + "/admin/pedidos/gestionar");
        } else {
            request.setAttribute("errorMessage", errorMessage);
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