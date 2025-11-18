package joystickmx.itson.servlets;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import joystickmx.itson.DTO.CategoriaDTO;
import joystickmx.itson.DTO.VideojuegoDTO;
import joystickmx.itson.Factory.FactoryBO;
import joystickmx.negocio.exception.NegocioException;
/**
 * CrearProductosServlet - Maneja la creación de productos (videojuegos) por parte del administrador
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
        try {
            //Obtener todas las categorías
            List<CategoriaDTO> categoriasDisponibles = FactoryBO.buscarTodasCategorias();
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
            //La descripción no aparece en el formulario
            String descripcion = "Descripción pendiente...";
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
            CategoriaDTO categoriaEncontrada = FactoryBO.buscarCategoriaPorNombre(categoriaNombre); 
            
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
            FactoryBO.crearVideojuego(nuevoVideojuego);
            //Redirigir al panel principal para administrador
            response.sendRedirect(contextPath + "/admin/panel-menu?success=Producto+creado+exitosamente");

        } catch (NumberFormatException e) {
            error = "Error: El precio y el stock deben ser números válidos.";
            LOG.log(Level.WARNING, error, e);
            
        } catch (Exception e) {
            error = "Error al crear el producto: " + e.getMessage();
            LOG.log(Level.SEVERE, error, e);
        }

        //Si hay algún error se recarga la página con el mensaje
        if (error != null) {
            request.setAttribute("error", error);
            try {
                 request.setAttribute("categoriasDisponibles", FactoryBO.buscarTodasCategorias());
            } catch(Exception ex) {
                 LOG.log(Level.WARNING, "Fallo al recargar categorías tras error.");
            }
            request.getRequestDispatcher("/WEB-INF/admin/productos/crear.jsp").forward(request, response);
        }
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