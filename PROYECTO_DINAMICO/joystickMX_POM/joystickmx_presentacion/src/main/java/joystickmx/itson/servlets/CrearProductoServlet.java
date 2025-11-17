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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import joystickmx.itson.DTO.CategoriaDTO;
import joystickmx.itson.DTO.VideojuegoDTO;
import joystickmx.itson.Factory.FactoryBO;

/**
 *
 * @author hola
 */
@WebServlet(name = "CrearProductoServlet", urlPatterns = {"/admin/productos/crear"})
public class CrearProductoServlet extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(CrearProductoServlet.class.getName());

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
            out.println("<title>Servlet CrearProductoServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CrearProductoServlet at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("/WEB-INF/admin/productos/crear.jsp").forward(request, response);
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

        request.setCharacterEncoding("UTF-8");
        String contextPath = request.getContextPath();

        try {
            String nombre = request.getParameter("nombre");
            String descripcion = request.getParameter("descripcion");
            String plataforma = request.getParameter("plataforma");
            String desarrollador = request.getParameter("desarrollador");
            String genero = request.getParameter("genero");
            LocalDate fechaLanzamiento = LocalDate.parse(request.getParameter("fechaLanzamiento"));
            String imagenUrl = request.getParameter("imagenUrl");

            String precioStr = request.getParameter("precio");
            String stockStr = request.getParameter("stock");

            if (precioStr != null) {
                precioStr = precioStr.replace(",", ".");
            }

            if (stockStr != null) {
                stockStr = stockStr.replace(",", "").replace(".", "");
            }

            Float precio = Float.parseFloat(precioStr);
            Integer stock = Integer.parseInt(stockStr);

            VideojuegoDTO nuevoVideojuego = new VideojuegoDTO();
            nuevoVideojuego.setNombre(nombre);
            nuevoVideojuego.setDescripcion(descripcion);
            nuevoVideojuego.setPlataforma(plataforma);
            nuevoVideojuego.setDesarrollador(desarrollador);
            List<CategoriaDTO> categorias = new ArrayList<>();

            CategoriaDTO categoria1 = new CategoriaDTO();
            categoria1.setNombre(genero);

            categorias.add(categoria1);
            nuevoVideojuego.setCategorias(categorias);
            nuevoVideojuego.setFechaLanzamiento(fechaLanzamiento);
            nuevoVideojuego.setUrlImagen(imagenUrl);

            nuevoVideojuego.setPrecio(precio);
            nuevoVideojuego.setExistencias(stock);

            FactoryBO.crearVideojuego(nuevoVideojuego);

            response.sendRedirect(contextPath + "/admin/productos/gestionar?exito=true");

        } catch (NumberFormatException e) {
            LOG.log(Level.WARNING, "Error de formato en número (precio o stock)", e);
            request.setAttribute("error", "Error: El precio y el stock deben ser números válidos.");
            doGet(request, response); 

        } catch (Exception e) {
            LOG.log(Level.SEVERE, "Error al crear el producto", e);
            request.setAttribute("error", "Error al crear el producto: " + e.getMessage());
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
