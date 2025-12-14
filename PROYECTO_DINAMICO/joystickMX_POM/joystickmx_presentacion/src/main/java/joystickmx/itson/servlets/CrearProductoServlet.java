package joystickmx.itson.servlets;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import jakarta.validation.ConstraintViolationException;
import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import joystickmx.itson.DTO.CategoriaDTO;
import joystickmx.itson.DTO.VideojuegoDTO;
import joystickmx.itson.Fachada.FachadaBO;
import joystickmx.negocio.exception.NegocioException;

/**
 * CrearProductosServlet - Maneja la creación de productos (videojuegos) por
 * parte del administrador
 *
 * @author Ariel Eduardo Borbon Izaguirre ID: 00000252116
 * @author Sebastián Bórquez Huerta ID: 00000252115
 * @author Leonardo Flores Leyva ID: 00000252390
 * @author Yuri Germán García López ID: 00000252583
 */
@WebServlet(name = "CrearProductoServlet", urlPatterns = {"/admin/productos/crear"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 15
)
public class CrearProductoServlet extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(CrearProductoServlet.class.getName());
    private static final String UPLOAD_DIR = "/imgs";

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
            //Obtener todas las categorías
            List<CategoriaDTO> categoriasDisponibles = FachadaBO.buscarTodasCategorias();
            request.setAttribute("categoriasDisponibles", categoriasDisponibles);

        } catch (NegocioException e) {
            LOG.log(Level.WARNING, "Error al cargar las categorías para el formulario", e.getMessage());
            request.setAttribute("error", "Error al cargar las vategorías" + e.getMessage());
        }
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
        String error = null;

        try {
            String nombre = request.getParameter("nombre");

            String descripcion = request.getParameter("descripcion");
            if (descripcion == null || descripcion.trim().isEmpty()) {
                descripcion = "Sin descripción";
            }
            String plataforma = request.getParameter("plataforma");
            String desarrollador = request.getParameter("desarrollador");
            String categoriaNombre = request.getParameter("categoria");
            LocalDate fechaLanzamiento = LocalDate.parse(request.getParameter("lanzamiento"));
            String precioStr = request.getParameter("precio").replace(",", ".");

            Float precio = Float.valueOf(precioStr);
            Integer stock = Integer.valueOf(request.getParameter("existencias"));

            //Manejar toda la lógica para guardar la imagen
            String imagenUrlDB = "imgs/iconoImagen.png";
            Part filePart = request.getPart("imagenFile");

            if (filePart != null && filePart.getSize() > 0) {
                String fileName = filePart.getSubmittedFileName();
                String uploadPath = getServletContext().getRealPath(UPLOAD_DIR);
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) {
                    uploadDir.mkdirs();
                }

                String finalName = fileName;
                filePart.write(uploadPath + File.separator + finalName);

                //Asignar el formato específico de la url para guardarlo en la base de datos
                imagenUrlDB = UPLOAD_DIR + "/" + finalName;
            }

            //Buscar y validar las categorías
            List<CategoriaDTO> categorias = new ArrayList<>();
            CategoriaDTO categoriaEncontrada = FachadaBO.buscarCategoriaPorNombre(categoriaNombre);

            if (categoriaEncontrada == null) {
                throw new NegocioException("La categoría seleccionada no es válida: " + categoriaNombre);
            }
            categorias.add(categoriaEncontrada);

            //Construir y persistir el videojuego
            VideojuegoDTO nuevoVideojuego = new VideojuegoDTO();
            nuevoVideojuego.setNombre(nombre);
            nuevoVideojuego.setDescripcion(descripcion);
            nuevoVideojuego.setPlataforma(plataforma);
            nuevoVideojuego.setDesarrollador(desarrollador);
            nuevoVideojuego.setCategorias(categorias);
            nuevoVideojuego.setFechaLanzamiento(fechaLanzamiento);
            nuevoVideojuego.setUrlImagen(imagenUrlDB);
            nuevoVideojuego.setPrecio(precio);
            nuevoVideojuego.setExistencias(stock);
            nuevoVideojuego.setHabilitado(true);
            //Crear el videojuego
            FachadaBO.crearVideojuego(nuevoVideojuego);
            //Redirigir al panel principal para administrador
            response.sendRedirect(contextPath + "/admin/panel-menu?success=Producto+creado+exitosamente");

        } catch (NumberFormatException e) {
            error = "Error: El precio y el stock deben ser números válidos.";
            LOG.log(Level.WARNING, error, e);

        } catch (Exception e) {
            error = obtenerMensajeAmigable(e);
            LOG.log(Level.SEVERE, "Error en creación de producto", e);
        }

        if (error != null) {
            request.setAttribute("error", error);
            try {
                request.setAttribute("categoriasDisponibles", FachadaBO.buscarTodasCategorias());
            } catch (Exception ex) {
                LOG.log(Level.WARNING, "Fallo al recargar categorías tras error.");
            }
            request.getRequestDispatcher("/WEB-INF/admin/productos/crear.jsp").forward(request, response);
        }
    }

    /**
     * Método auxiliar para "escarbar" en la excepción y encontrar el mensaje de
     * validación real.
     */
    private String obtenerMensajeAmigable(Exception e) {
        Throwable causa = e;

        while (causa != null) {
            if (causa instanceof ConstraintViolationException) {
                ConstraintViolationException cve = (ConstraintViolationException) causa;
                if (!cve.getConstraintViolations().isEmpty()) {
                    return cve.getConstraintViolations().iterator().next().getMessage();
                }
            }
            causa = causa.getCause();
        }

        String msg = e.getMessage();
        if (msg != null && msg.contains(":")) {
            return msg.substring(msg.lastIndexOf(":") + 1).trim();
        }

        return "Error al crear el producto: " + (msg != null ? msg : "Error desconocido");
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Servlet para crear productos";
    }// </editor-fold>

}
