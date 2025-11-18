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
import joystickmx.itson.DTO.PedidoDTO;
import joystickmx.itson.Factory.FactoryBO;
import joystickmx.negocio.exception.NegocioException;

/**
 *
 * @author sonic
 */
@WebServlet(name = "DetallePedidoServlet", urlPatterns = {"/admin/pedidos/detalles"})
public class DetallePedidoServlet extends HttpServlet {
    
    private static final Logger LOG = Logger.getLogger(DetallePedidoServlet.class.getName());

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
            out.println("<title>Servlet DetallePedidoServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DetallePedidoServlet at " + request.getContextPath() + "</h1>");
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

        String idParam = request.getParameter("id");
        Long pedidoId = null;
        String errorMessage = null;
        PedidoDTO pedido = null;

        try {
            // 2. Validar y convertir el ID
            if (idParam == null || idParam.isEmpty()) {
                throw new NegocioException("No se proporcionó un ID de pedido.");
            }
            pedidoId = Long.parseLong(idParam);

            // 3. Llamar a la lógica de negocio (la crearemos en el Paso 2)
            pedido = FactoryBO.buscarPedidoPorId(pedidoId);

            if (pedido == null) {
                throw new NegocioException("Pedido no encontrado con ID: " + pedidoId);
            }

        } catch (NumberFormatException e) {
            LOG.log(Level.WARNING, "ID de pedido con formato inválido", e);
            errorMessage = "El ID del pedido no es válido.";
        } catch (NegocioException e) {
            LOG.log(Level.SEVERE, "Error al buscar pedido", e);
            errorMessage = e.getMessage();
        }

        // 4. Decidir a dónde ir
        if (errorMessage != null) {
            // Si hubo un error, regresar a la lista con un mensaje
            request.getSession().setAttribute("errorMessage", errorMessage);
            response.sendRedirect(request.getContextPath() + "/admin/pedidos/gestionar");
        } else {
            // Si todo salió bien, enviar el DTO al JSP de detalle
            request.setAttribute("pedido", pedido);
            request.getRequestDispatcher("/WEB-INF/admin/pedidos/detalle.jsp").forward(request, response);
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
