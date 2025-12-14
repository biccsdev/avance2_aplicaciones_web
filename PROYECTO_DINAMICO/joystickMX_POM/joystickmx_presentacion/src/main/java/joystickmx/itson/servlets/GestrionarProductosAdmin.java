package joystickmx.itson.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.List;
import joystickmx.itson.DTO.VideojuegoDTO;
import joystickmx.itson.Fachada.FachadaBO;
import joystickmx.negocio.exception.NegocioException;
/**
 * GestrionarProductosAdmin

Maneja la edición y eliminación de videojuegos por parte del administrador
 *
 * @author Ariel Eduardo Borbon Izaguirre ID: 00000252116
 * @author Sebastián Bórquez Huerta ID: 00000252115
 * @author Leonardo Flores Leyva ID: 00000252390
 * @author Yuri Germán García López ID: 00000252583
 */
@WebServlet(name = "ProductosAdminServlet", urlPatterns = {"/editar-productos"})
public class GestrionarProductosAdmin extends HttpServlet {

    //Nomás la puse como prueba esta ruta
    private final String GESTIONAR_PRODUCTO_JSP = "/WEB-INF/admin/productos/gestionar.jsp";

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
        
        String path = request.getServletPath();
        String estado = request.getParameter("estado");

        try {
            request.setAttribute("categoriasDisponibles", FachadaBO.buscarTodasCategorias());
            
            if (path.equals("/admin/productos/gestionar")) {
                
                if (estado == null || estado.equals("lista") || estado.equals("eliminado")) {

                    List<VideojuegoDTO> listaVideojuegos = FachadaBO.buscarVideojuegosActivos(); 
                    request.setAttribute("videojuegos", listaVideojuegos);
                    request.setAttribute("estado", estado != null ? estado : "lista");
                    
                } else if (estado.equals("editar") || estado.equals("confirmar")) {
                    Long idVideojuego = Long.parseLong(request.getParameter("id"));
                    VideojuegoDTO videojuego = FachadaBO.buscarVideojuegoPorId(idVideojuego);

                    if (videojuego == null) {
                        throw new NegocioException("Producto no encontrado.");
                    }
                    request.setAttribute("videojuego", videojuego);
                    request.setAttribute("estado", estado); 
                }

            } else if (path.equals("/admin/productos/eliminar")) { 
                Long idVideojuego = Long.parseLong(request.getParameter("id"));
                response.sendRedirect(request.getContextPath() + "/admin/productos/gestionar?estado=eliminado");
                return;
            }
            request.getRequestDispatcher(GESTIONAR_PRODUCTO_JSP).forward(request, response);

        } catch (NegocioException | NumberFormatException e) {
            request.setAttribute("error", "Error en la gestión de productos: " + e.getMessage());
            try {
                 request.setAttribute("videojuegos", FachadaBO.buscarVideojuegosActivos());
            } catch (NegocioException ex) {
                 request.setAttribute("error", "Error crítico: No se pudieron cargar los productos después de un fallo.");
            }
            request.getRequestDispatcher(GESTIONAR_PRODUCTO_JSP).forward(request, response);
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
        
        Long idVideojuego = null;
        
        try {
            idVideojuego = Long.parseLong(request.getParameter("id"));
            VideojuegoDTO dto = FachadaBO.buscarVideojuegoPorId(idVideojuego);
            if (dto == null) {
                 throw new NegocioException("Producto a actualizar no encontrado.");
            }

            dto.setNombre(request.getParameter("nombre"));
            dto.setPlataforma(request.getParameter("plataforma"));
            dto.setDesarrollador(request.getParameter("desarrollador"));

            dto.setPrecio(Float.parseFloat(request.getParameter("precio")));
            dto.setExistencias(Integer.parseInt(request.getParameter("existencias")));
            dto.setFechaLanzamiento(LocalDate.parse(request.getParameter("lanzamiento")));

            String nombreCategoria = request.getParameter("genero");


            FachadaBO.actualizarVideojuego(dto);
          
            response.sendRedirect(request.getContextPath() + "/admin/productos/gestionar?estado=editar&id=" + idVideojuego + "&success=true");
            
        } catch (NegocioException e) {
            request.setAttribute("error", "Error al actualizar el producto: " + e.getMessage());
            
        } catch (NumberFormatException e) {
             request.setAttribute("error", "Error de formato en los datos: Verifique precio, existencias y fecha");
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