package joystickmx.itson.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import jakarta.validation.ConstraintViolationException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import joystickmx.itson.DTO.CategoriaDTO;
import joystickmx.itson.DTO.VideojuegoDTO;
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
@WebServlet(name = "EditarVideojuegoServlet", urlPatterns = {"/admin/productos/editar"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 15
)
public class EditarVideojuegoServlet extends HttpServlet {

    private static final String UPLOAD_DIR = "/imgs";
    private static final Logger LOG = Logger.getLogger(EditarVideojuegoServlet.class.getName());

    private String obtenerValorPart(Part part) throws IOException {
        if (part == null) {
            return "";
        }
        try (InputStream inputStream = part.getInputStream(); Scanner scanner = new Scanner(inputStream, StandardCharsets.UTF_8.name())) {
            return scanner.useDelimiter("\\A").hasNext() ? scanner.next() : "";
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

        String nombre = request.getParameter("nombre");

        try {
            if (nombre == null || nombre.isEmpty()) {
                throw new NegocioException("Nombre de videojuego no proporcionado.");
            }

            VideojuegoDTO videojuego = FachadaBO.buscarVideojuegoPorNombeExacto(nombre);

            if (videojuego == null) {
                throw new NegocioException("Videojuego no encontrado con el nombre: " + nombre);
            }

            List<CategoriaDTO> categorias = FachadaBO.buscarTodasCategorias();

            request.setAttribute("videojuego", videojuego);
            request.setAttribute("categoriasDisponibles", categorias);

            request.getRequestDispatcher("/WEB-INF/admin/productos/editar.jsp").forward(request, response);

        } catch (NegocioException e) {
            LOG.log(Level.SEVERE, "Error al cargar edición", e);
            response.sendRedirect(request.getContextPath() + "/home?error=" + e.getMessage());
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

        request.setCharacterEncoding("UTF-8");

        String idStr = request.getParameter("idVideojuego");

        try {
            if (idStr == null) {
                throw new NegocioException("ID nulo en POST");
            }
            Long id = Long.parseLong(idStr);

            VideojuegoDTO videojuegoOriginal = FachadaBO.buscarVideojuegoPorId(id);

            String nombre = obtenerValorPart(request.getPart("nombre"));
            String descripcion = obtenerValorPart(request.getPart("descripcion"));
            String plataforma = obtenerValorPart(request.getPart("plataforma"));
            String desarrollador = obtenerValorPart(request.getPart("desarrollador"));
            String precioStr = obtenerValorPart(request.getPart("precio"));
            String existenciasStr = obtenerValorPart(request.getPart("existencias"));
            String fechaStr = obtenerValorPart(request.getPart("lanzamiento"));
            String categoriaNombre = obtenerValorPart(request.getPart("categoria"));

            float precio = (precioStr.isEmpty()) ? 0.0f : Float.parseFloat(precioStr);
            int existencias = (existenciasStr.isEmpty()) ? 0 : Integer.parseInt(existenciasStr);

            videojuegoOriginal.setPrecio(precio);
            videojuegoOriginal.setExistencias(existencias);

            Part filePart = request.getPart("imagenFile");
            String nuevaUrlImagen = videojuegoOriginal.getUrlImagen();

            if (filePart != null && filePart.getSize() > 0 && filePart.getSubmittedFileName() != null && !filePart.getSubmittedFileName().isEmpty()) {
                String fileName = filePart.getSubmittedFileName();
                String uploadPath = getServletContext().getRealPath(UPLOAD_DIR);
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) {
                    uploadDir.mkdirs();
                }

                filePart.write(uploadPath + File.separator + fileName);
                nuevaUrlImagen = UPLOAD_DIR + "/" + fileName;
            }

            if (categoriaNombre != null && !categoriaNombre.isEmpty()) {
                CategoriaDTO cat = FachadaBO.buscarCategoriaPorNombre(categoriaNombre);
                if (cat != null) {
                    List<CategoriaDTO> nuevaLista = new ArrayList<>();
                    nuevaLista.add(cat);
                    videojuegoOriginal.setCategorias(nuevaLista);
                }
            }

            videojuegoOriginal.setNombre(nombre);
            videojuegoOriginal.setPlataforma(plataforma);
            videojuegoOriginal.setDesarrollador(desarrollador);
            videojuegoOriginal.setPrecio(Float.parseFloat(precioStr));
            videojuegoOriginal.setExistencias(Integer.parseInt(existenciasStr));
            videojuegoOriginal.setFechaLanzamiento(LocalDate.parse(fechaStr));
            videojuegoOriginal.setUrlImagen(nuevaUrlImagen);
            videojuegoOriginal.setDescripcion(descripcion);

            FachadaBO.actualizarVideojuego(videojuegoOriginal);

            response.sendRedirect(request.getContextPath() + "/home?mensaje=Juego+actualizado");

        } catch (Exception e) {
            LOG.log(Level.SEVERE, "Error al actualizar videojuego", e);

            String mensajeError = obtenerMensajeAmigable(e);

            request.setAttribute("error", mensajeError);
            doGet(request, response);
        }
    }

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

        return "Error al actualizar el producto: " + (msg != null ? msg : "Error desconocido");
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
