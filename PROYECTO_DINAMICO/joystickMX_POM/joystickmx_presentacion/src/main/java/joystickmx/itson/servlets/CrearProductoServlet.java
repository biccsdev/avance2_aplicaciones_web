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
    fileSizeThreshold = 1024 * 1024 * 1, //Valor agregado para almacenar 1 MB
    maxFileSize = 1024 * 1024 * 10,      //Valor agregado para almacenar  10 MB
    maxRequestSize = 1024 * 1024 * 15    //Valor agregado para almacenar  15 MB
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
            //La descripción no aparece en el formulario
            String descripcion = "Descripción pendiente..."; 
            String plataforma = request.getParameter("plataforma");
            String desarrollador = request.getParameter("desarrollador");
            String genero = request.getParameter("genero");
            LocalDate fechaLanzamiento = LocalDate.parse(request.getParameter("lanzamiento"));

            String precioStr = request.getParameter("precio");
            String stockStr = request.getParameter("existencias");

            if (precioStr != null) {
                precioStr = precioStr.replace(",", ".");
            }

            //Lógica para guardar la imagen
            String imagenUrlDB = "imgs/iconoImagen.png";
            Part filePart = request.getPart("imagenFile");
            
            if (filePart != null && filePart.getSize() > 0) {
                //Obtener el nombre del archivo
                String fileName = filePart.getSubmittedFileName();
                //Obtener ruta de la carpeta de las imágenes "imgs" en el servidor
                String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIR;
                //Si no existe el directorio entonces se crea
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) {
                    uploadDir.mkdirs();
                }
                //Guardar el archivo
                String finalName = fileName;
                filePart.write(uploadPath + File.separator + finalName);
                //Le damos el formato necesario a la url "/imgs/nombreImagen.jps"
                imagenUrlDB = UPLOAD_DIR + "/" + finalName;
            }

            Float precio = Float.parseFloat(precioStr);
            Integer stock = Integer.parseInt(stockStr);

            VideojuegoDTO nuevoVideojuego = new VideojuegoDTO();
            nuevoVideojuego.setNombre(nombre);
            nuevoVideojuego.setDescripcion(descripcion);
            nuevoVideojuego.setPlataforma(plataforma);
            nuevoVideojuego.setDesarrollador(desarrollador);
            
            //Esto lo hice para que no se creara una categoría nueva cada vez que se registra un producto
            //Porque en teoría ya deberían de estar registradas las categorías
            List<CategoriaDTO> categorias = new ArrayList<>();
            CategoriaDTO categoriaEncontrada = FactoryBO.buscarCategoriaPorNombre(genero); 

            //No se si debería quitar esta validación
            if (categoriaEncontrada != null) {
                categorias.add(categoriaEncontrada);
            } else {
                throw new NegocioException("La categoría seleccionada no es válida: " + genero);
            }

            nuevoVideojuego.setCategorias(categorias);
            nuevoVideojuego.setFechaLanzamiento(fechaLanzamiento);
            nuevoVideojuego.setUrlImagen(imagenUrlDB); 
            nuevoVideojuego.setPrecio(precio);
            nuevoVideojuego.setExistencias(stock);
            nuevoVideojuego.setHabilitado(true); //En cuanto se crea el producto se habilita para que salga (eso no se si después lo podemos cambiar)

            FactoryBO.crearVideojuego(nuevoVideojuego);

            response.sendRedirect(contextPath + "/admin/panel-menu");

        } catch (NumberFormatException e) {
            LOG.log(Level.WARNING, "Error de formato en número", e);
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
        return "Servlet para crear productos";
    }// </editor-fold>

}