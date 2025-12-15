package joystickmx.itson.RellenoBD;

import jakarta.persistence.EntityManager;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import joystickmx.itson.BO.CategoriaBO;
import joystickmx.itson.DAOS.CategoriaDAO;
import joystickmx.itson.DTO.CarritoDTO;
import joystickmx.itson.DTO.CategoriaDTO;
import joystickmx.itson.DTO.DireccionDTO;
import joystickmx.itson.DTO.ItemCarritoDTO;
import joystickmx.itson.DTO.PagoDTO;
import joystickmx.itson.DTO.ResenaDTO;
import joystickmx.itson.DTO.UsuarioDTO;
import joystickmx.itson.DTO.UsuarioRegistroDTO;
import joystickmx.itson.DTO.VideojuegoDTO;
import joystickmx.itson.Fachada.FachadaBO;
import joystickmx.itson.conexion.Conexion;

/**
 *
 * @author Ariel Eduardo Borbón Izaguirre ID: 00000252116
 * @author Sebastián Bórquez Huerta ID: 00000252115
 * @author Leonardo Flores Leyva ID: 00000252390
 * @author Yuri Germán García López ID: 00000252583
 * @author Victor Gerardo Torres García ID: 205869
 */
public class RellenoBD {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        llenarBD();
    }

    public static void llenarBD() {
        EntityManager em = Conexion.crearConexion();
        try {
            em.getTransaction().begin();

            //Crear categorias para juegos
            CategoriaDTO categoriaAccionAventuras = new CategoriaDTO();
            categoriaAccionAventuras.setNombre("Acción y Aventuras");
            categoriaAccionAventuras.setDescripcion(
                    "Es aquel donde exploras escenarios, enfrentas enemigos, avanzas en "
                    + "una historia y resuelves desafíos, mezclando ritmo dinámico "
                    + "con elementos narrativos y de descubrimiento."
            );

            CategoriaDTO categoriaMundoAbierto = new CategoriaDTO();
            categoriaMundoAbierto.setNombre("Mundo Abierto");
            categoriaMundoAbierto.setDescripcion(
                    "Es aquel que ofrece un escenario amplio y "
                    + "libremente explorable, donde el jugador puede decidir a dónde ir, "
                    + "qué hacer y en qué orden hacerlo."
            );

            CategoriaDTO categoriaCarreras = new CategoriaDTO();
            categoriaCarreras.setNombre("Carreras");
            categoriaCarreras.setDescripcion(
                    "Consiste en conducir y competir a gran velocidad, "
                    + "buscando superar a otros o mejorar tus propios tiempos"
            );

            CategoriaDTO categoriaSurvivalHorror = new CategoriaDTO();
            categoriaSurvivalHorror.setNombre("Survival Horror");
            categoriaSurvivalHorror.setDescripcion(
                    "Está diseñado para generar tensión, miedo "
                    + "y una constante sensación de vulnerabilidad, combinando elementos "
                    + "de supervivencia con terror"
            );

            CategoriaDTO categoriaFPS = new CategoriaDTO();
            categoriaFPS.setNombre("Juego de Disparos en Primera Persona");
            categoriaFPS.setDescripcion(
                    "Es un juego donde luchas directamente desde la vista "
                    + "del protagonista, usando armas para enfrentarte a enemigos en "
                    + "entornos dinámicos y llenos de acción."
            );

            CategoriaDTO categoriaRPG = new CategoriaDTO();
            categoriaRPG.setNombre("Juego de Rol por Turnos (RPG)");
            categoriaRPG.setDescripcion(
                    "Es un juego donde evolucionas a tu personaje, tomas decisiones y "
                    + "vives una historia, combinando combate, exploración y "
                    + "desarrollo profundo"
            );

            new CategoriaBO(new CategoriaDAO()).crearCategoria(categoriaAccionAventuras);
            System.out.println("Categoría persistida: " + categoriaAccionAventuras.getNombre());

            new CategoriaBO(new CategoriaDAO()).crearCategoria(categoriaMundoAbierto);
            System.out.println("Categoría persistida: " + categoriaMundoAbierto.getNombre());

            new CategoriaBO(new CategoriaDAO()).crearCategoria(categoriaCarreras);
            System.out.println("Categoría persistida: " + categoriaCarreras.getNombre());

            new CategoriaBO(new CategoriaDAO()).crearCategoria(categoriaSurvivalHorror);
            System.out.println("Categoría persistida: " + categoriaSurvivalHorror.getNombre());

            new CategoriaBO(new CategoriaDAO()).crearCategoria(categoriaFPS);
            System.out.println("Categoría persistida: " + categoriaFPS.getNombre());

            new CategoriaBO(new CategoriaDAO()).crearCategoria(categoriaRPG);
            System.out.println("Categoría persistida: " + categoriaRPG.getNombre());

            em.getTransaction().commit();

            em.getTransaction().begin();

            categoriaAccionAventuras = new CategoriaBO(new CategoriaDAO()).buscarPorNombre(categoriaAccionAventuras.getNombre());
            categoriaMundoAbierto = new CategoriaBO(new CategoriaDAO()).buscarPorNombre(categoriaMundoAbierto.getNombre());
            categoriaCarreras = new CategoriaBO(new CategoriaDAO()).buscarPorNombre(categoriaCarreras.getNombre());
            categoriaSurvivalHorror = new CategoriaBO(new CategoriaDAO()).buscarPorNombre(categoriaSurvivalHorror.getNombre());
            categoriaFPS = new CategoriaBO(new CategoriaDAO()).buscarPorNombre(categoriaFPS.getNombre());
            categoriaRPG = new CategoriaBO(new CategoriaDAO()).buscarPorNombre(categoriaRPG.getNombre());

            em.getTransaction().commit();

            //Crear videojuegos
            VideojuegoDTO juegoRDR2 = new VideojuegoDTO();
            juegoRDR2.setNombre("Red Dead Redemption 2");
            juegoRDR2.setDescripcion("La épica historia de Arthur Morgan y la banda de forajidos de Dutch Van der Linde.");
            juegoRDR2.setPrecio(299.0f);
            juegoRDR2.setExistencias(18);
            juegoRDR2.setDesarrollador("Rockstar Games");
            juegoRDR2.setFechaLanzamiento(LocalDate.of(2018, 10, 26));
            juegoRDR2.setPlataforma("Xbox One");
            juegoRDR2.setHabilitado(true);
            juegoRDR2.setUrlImagen("/imgs/rdr2-xbox-one.jpg");

            List<CategoriaDTO> categoriasRDR2 = new ArrayList<>();
            categoriasRDR2.add(categoriaAccionAventuras);
            categoriasRDR2.add(categoriaMundoAbierto);
            juegoRDR2.setCategorias(categoriasRDR2);

            VideojuegoDTO juegoRDR2PS4 = new VideojuegoDTO();
            juegoRDR2PS4.setNombre("Red Dead Redemption 2 - PS4");
            juegoRDR2PS4.setDescripcion("La épica historia de Arthur Morgan y la banda de forajidos de Dutch Van der Linde.");
            juegoRDR2PS4.setPrecio(299.0f);
            juegoRDR2PS4.setExistencias(28);
            juegoRDR2PS4.setDesarrollador("Rockstar Games");
            juegoRDR2PS4.setFechaLanzamiento(LocalDate.of(2018, 10, 26));
            juegoRDR2PS4.setPlataforma("Playstation 4");
            juegoRDR2PS4.setHabilitado(true);
            juegoRDR2PS4.setUrlImagen("/imgs/rdr2-ps4.jpg");

            List<CategoriaDTO> categoriasRDR2PS4 = new ArrayList<>();
            categoriasRDR2PS4.add(categoriaAccionAventuras);
            categoriasRDR2PS4.add(categoriaMundoAbierto);
            juegoRDR2PS4.setCategorias(categoriasRDR2PS4);

            VideojuegoDTO juegoGTAV = new VideojuegoDTO();
            juegoGTAV.setNombre("Grand Theft Auto V");
            juegoGTAV.setDescripcion("Las locas aventuras y atracos de Michael, Franklin y Trevor en la quinta entrega de la saga Grand Theft Auto.");
            juegoGTAV.setPrecio(299.0f);
            juegoGTAV.setExistencias(77);
            juegoGTAV.setDesarrollador("Rockstar Games");
            juegoGTAV.setFechaLanzamiento(LocalDate.of(2013, 9, 17));
            juegoGTAV.setPlataforma("Xbox One");
            juegoGTAV.setHabilitado(true);
            juegoGTAV.setUrlImagen("/imgs/gtav-xbox-one.jpg");

            List<CategoriaDTO> categoriasGTAV = new ArrayList<>();
            categoriasGTAV.add(categoriaAccionAventuras);
            categoriasGTAV.add(categoriaMundoAbierto);
            juegoGTAV.setCategorias(categoriasGTAV);

            VideojuegoDTO juegoGTAVPS4 = new VideojuegoDTO();
            juegoGTAVPS4.setNombre("Grand Theft Auto V - PS4");
            juegoGTAVPS4.setDescripcion("Las locas aventuras y atracos de Michael, Franklin y Trevor en la quinta entrega de la saga Grand Theft Auto.");
            juegoGTAVPS4.setPrecio(299.0f);
            juegoGTAVPS4.setExistencias(36);
            juegoGTAVPS4.setDesarrollador("Rockstar Games");
            juegoGTAVPS4.setFechaLanzamiento(LocalDate.of(2013, 9, 17));
            juegoGTAVPS4.setPlataforma("Playstation 4");
            juegoGTAVPS4.setHabilitado(true);
            juegoGTAVPS4.setUrlImagen("/imgs/gtav-ps4.jpg");

            List<CategoriaDTO> categoriasGTAVPS4 = new ArrayList<>();
            categoriasGTAVPS4.add(categoriaAccionAventuras);
            categoriasGTAVPS4.add(categoriaMundoAbierto);
            juegoGTAVPS4.setCategorias(categoriasGTAVPS4);

            VideojuegoDTO juegoGOW = new VideojuegoDTO();
            juegoGOW.setNombre("God of War Ragnarok");
            juegoGOW.setDescripcion("La épica saga nórdica de Kratos y Atreus.");
            juegoGOW.setPrecio(1299.50f);
            juegoGOW.setExistencias(100);
            juegoGOW.setDesarrollador("Santa Monica Studio");
            juegoGOW.setFechaLanzamiento(LocalDate.of(2022, 11, 9));
            juegoGOW.setPlataforma("PlayStation 5");
            juegoGOW.setHabilitado(true);
            juegoGOW.setUrlImagen("/imgs/gow-ps5.jpeg");

            List<CategoriaDTO> categoriasGOW = new ArrayList<>();
            categoriasGOW.add(categoriaAccionAventuras);
            categoriasGOW.add(categoriaRPG);
            juegoGOW.setCategorias(categoriasGOW);

            VideojuegoDTO juegoFH5 = new VideojuegoDTO();
            juegoFH5.setNombre("Forza Horizon 5");
            juegoFH5.setDescripcion("La mejor saga de carreras de mundo abierto está de regreso en Forza Horizon 5.");
            juegoFH5.setPrecio(799.0f);
            juegoFH5.setExistencias(80);
            juegoFH5.setDesarrollador("Playground Games");
            juegoFH5.setFechaLanzamiento(LocalDate.of(2021, 11, 9));
            juegoFH5.setPlataforma("Xbox Series X/S");
            juegoFH5.setHabilitado(true);
            juegoFH5.setUrlImagen("/imgs/fh5-xbox-series-xs.jpg");

            List<CategoriaDTO> categoriasFH5 = new ArrayList<>();
            categoriasFH5.add(categoriaCarreras);
            categoriasFH5.add(categoriaMundoAbierto);
            juegoFH5.setCategorias(categoriasFH5);

            VideojuegoDTO juegoAW2 = new VideojuegoDTO();
            juegoAW2.setNombre("Alan Wake 2");
            juegoAW2.setDescripcion("Alan Wake está de regreso en esta atrapante secuela, llena de misterio y terror.");
            juegoAW2.setPrecio(799.0f);
            juegoAW2.setExistencias(61);
            juegoAW2.setDesarrollador("Remedy Entertainment");
            juegoAW2.setFechaLanzamiento(LocalDate.of(2023, 10, 27));
            juegoAW2.setPlataforma("Xbox Series X/S");
            juegoAW2.setHabilitado(true);
            juegoAW2.setUrlImagen("/imgs/aw2-xbox-series-xs.jpg");

            List<CategoriaDTO> categoriasAW2 = new ArrayList<>();
            categoriasAW2.add(categoriaSurvivalHorror);
            juegoAW2.setCategorias(categoriasAW2);

            VideojuegoDTO juegoAW2PS5 = new VideojuegoDTO();
            juegoAW2PS5.setNombre("Alan Wake 2 - Deluxe Edition");
            juegoAW2PS5.setDescripcion("Alan Wake está de regreso en esta atrapante secuela, llena de misterio y terror.");
            juegoAW2PS5.setPrecio(1599.0f);
            juegoAW2PS5.setExistencias(12);
            juegoAW2PS5.setDesarrollador("Remedy Entertainment");
            juegoAW2PS5.setFechaLanzamiento(LocalDate.of(2023, 10, 27));
            juegoAW2PS5.setPlataforma("Playstation 5");
            juegoAW2PS5.setHabilitado(true);
            juegoAW2PS5.setUrlImagen("/imgs/aw2-deluxe-ps5.jpg");

            List<CategoriaDTO> categoriasAW2PS5 = new ArrayList<>();
            categoriasAW2PS5.add(categoriaSurvivalHorror);
            juegoAW2PS5.setCategorias(categoriasAW2PS5);

            VideojuegoDTO juegoU4 = new VideojuegoDTO();
            juegoU4.setNombre("Uncharted 4 - A Thieft's End");
            juegoU4.setDescripcion("Nathan Drake está de vuelta en la última entrega de la galardonada saga de Playstation Uncharted.");
            juegoU4.setPrecio(299.0f);
            juegoU4.setExistencias(7);
            juegoU4.setDesarrollador("Naughty Dog");
            juegoU4.setFechaLanzamiento(LocalDate.of(2016, 5, 10));
            juegoU4.setPlataforma("Playstation 4");
            juegoU4.setHabilitado(true);
            juegoU4.setUrlImagen("/imgs/ucharted4-ps4.jpg");

            List<CategoriaDTO> categoriasU4 = new ArrayList<>();
            categoriasU4.add(categoriaAccionAventuras);
            juegoU4.setCategorias(categoriasU4);

            VideojuegoDTO juegoHMCC = new VideojuegoDTO();
            juegoHMCC.setNombre("Halo - The Master Chief Collection");
            juegoHMCC.setDescripcion("El jefe maestro está de vuelta en esta épica colección de toda la saga Halo.");
            juegoHMCC.setPrecio(299.0f);
            juegoHMCC.setExistencias(24);
            juegoHMCC.setDesarrollador("Halo Studios");
            juegoHMCC.setFechaLanzamiento(LocalDate.of(2014, 11, 14));
            juegoHMCC.setPlataforma("Xbox One");
            juegoHMCC.setHabilitado(true);
            juegoHMCC.setUrlImagen("/imgs/hmcc-xbox-one.jpg");

            List<CategoriaDTO> categoriasHMCC = new ArrayList<>();
            categoriasHMCC.add(categoriaFPS);
            juegoHMCC.setCategorias(categoriasHMCC);

            VideojuegoDTO juegoSR = new VideojuegoDTO();
            juegoSR.setNombre("Saints Row: The Third");
            juegoSR.setDescripcion("La tercera entrega de Saints Row, ahora mucho más humorística y loca.");
            juegoSR.setPrecio(199.0f);
            juegoSR.setExistencias(3);
            juegoSR.setDesarrollador("Deep Silver");
            juegoSR.setFechaLanzamiento(LocalDate.of(2011, 11, 15));
            juegoSR.setPlataforma("Xbox 360");
            juegoSR.setHabilitado(true);
            juegoSR.setUrlImagen("/imgs/sr3-xbox-360.jpg");

            List<CategoriaDTO> categoriasSR = new ArrayList<>();
            categoriasSR.add(categoriaAccionAventuras);
            categoriasSR.add(categoriaMundoAbierto);
            juegoSR.setCategorias(categoriasSR);

            VideojuegoDTO juegoSRPS3 = new VideojuegoDTO();
            juegoSRPS3.setNombre("Saints Row: The Third - PS3");
            juegoSRPS3.setDescripcion("La tercera entrega de Saints Row, ahora mucho más humorística y loca.");
            juegoSRPS3.setPrecio(199.0f);
            juegoSRPS3.setExistencias(1);
            juegoSRPS3.setDesarrollador("Deep Silver");
            juegoSRPS3.setFechaLanzamiento(LocalDate.of(2011, 11, 15));
            juegoSRPS3.setPlataforma("Playstation 3");
            juegoSRPS3.setHabilitado(true);
            juegoSRPS3.setUrlImagen("/imgs/sr3-ps3.jpg");

            List<CategoriaDTO> categoriasSRPS3 = new ArrayList<>();
            categoriasSRPS3.add(categoriaAccionAventuras);
            categoriasSRPS3.add(categoriaMundoAbierto);
            juegoSRPS3.setCategorias(categoriasSRPS3);

            VideojuegoDTO juegoER = new VideojuegoDTO();
            juegoER.setNombre("Elden Ring");
            juegoER.setDescripcion("La nueva entrega de los desarrolladores de la saga Dark Souls, ahora en un mundo abierto inmersivo.");
            juegoER.setPrecio(499.0f);
            juegoER.setExistencias(54);
            juegoER.setDesarrollador("From Software");
            juegoER.setFechaLanzamiento(LocalDate.of(2022, 2, 25));
            juegoER.setPlataforma("Xbox Series X/S");
            juegoER.setHabilitado(true);
            juegoER.setUrlImagen("/imgs/elden-ring-xbox-series-xs.jpg");

            List<CategoriaDTO> categoriasER = new ArrayList<>();
            categoriasER.add(categoriaAccionAventuras);
            categoriasER.add(categoriaMundoAbierto);
            categoriasER.add(categoriaRPG);
            juegoER.setCategorias(categoriasER);

            VideojuegoDTO juegoERPS5 = new VideojuegoDTO();
            juegoERPS5.setNombre("Elden Ring - PS5");
            juegoERPS5.setDescripcion("La nueva entrega de los desarrolladores de la saga Dark Souls, ahora en un mundo abierto inmersivo.");
            juegoERPS5.setPrecio(499.0f);
            juegoERPS5.setExistencias(32);
            juegoERPS5.setDesarrollador("From Software");
            juegoERPS5.setFechaLanzamiento(LocalDate.of(2022, 2, 25));
            juegoERPS5.setPlataforma("Playstation 5");
            juegoERPS5.setHabilitado(true);
            juegoERPS5.setUrlImagen("/imgs/elden-ring-ps5.jpg");

            List<CategoriaDTO> categoriasERPS5 = new ArrayList<>();
            categoriasERPS5.add(categoriaAccionAventuras);
            categoriasERPS5.add(categoriaMundoAbierto);
            categoriasERPS5.add(categoriaRPG);
            juegoERPS5.setCategorias(categoriasERPS5);

            VideojuegoDTO juegoDKB = new VideojuegoDTO();
            juegoDKB.setNombre("Donkey Kong Bananza");
            juegoDKB.setDescripcion("La nueva entrega en 3D del gorila más amado del mundo de los videojuegos.");
            juegoDKB.setPrecio(1999.0f);
            juegoDKB.setExistencias(32);
            juegoDKB.setDesarrollador("Nintendo");
            juegoDKB.setFechaLanzamiento(LocalDate.of(2025, 7, 17));
            juegoDKB.setPlataforma("Nintendo Switch 2");
            juegoDKB.setHabilitado(true);
            juegoDKB.setUrlImagen("/imgs/dkb-ns2.jpg");

            List<CategoriaDTO> categoriasDKB = new ArrayList<>();
            categoriasDKB.add(categoriaAccionAventuras);
            juegoDKB.setCategorias(categoriasDKB);

            VideojuegoDTO juegoMKW = new VideojuegoDTO();
            juegoMKW.setNombre("Mario Kart World");
            juegoMKW.setDescripcion("Mario y sus amigos están de regreso en esta nueva entrega de la saga de Karts más querida por todos.");
            juegoMKW.setPrecio(1999.0f);
            juegoMKW.setExistencias(87);
            juegoMKW.setDesarrollador("Nintendo");
            juegoMKW.setFechaLanzamiento(LocalDate.of(2025, 6, 5));
            juegoMKW.setPlataforma("Nintendo Switch 2");
            juegoMKW.setHabilitado(true);
            juegoMKW.setUrlImagen("/imgs/mkw-ns2.jpg");

            List<CategoriaDTO> categoriasMKW = new ArrayList<>();
            categoriasMKW.add(categoriaCarreras);
            categoriasMKW.add(categoriaMundoAbierto);
            juegoMKW.setCategorias(categoriasMKW);

            FachadaBO.crearVideojuego(juegoRDR2);
            System.out.println("Videojuego persistido: " + juegoRDR2.getNombre());

            FachadaBO.crearVideojuego(juegoRDR2PS4);
            System.out.println("Videojuego persistido: " + juegoRDR2PS4.getNombre());

            FachadaBO.crearVideojuego(juegoGTAV);
            System.out.println("Videojuego persistido: " + juegoGTAV.getNombre());

            FachadaBO.crearVideojuego(juegoGTAVPS4);
            System.out.println("Videojuego persistido: " + juegoGTAVPS4.getNombre());

            FachadaBO.crearVideojuego(juegoGOW);
            System.out.println("Videojuego persistido: " + juegoGOW.getNombre());

            FachadaBO.crearVideojuego(juegoFH5);
            System.out.println("Videojuego persistido: " + juegoFH5.getNombre());

            FachadaBO.crearVideojuego(juegoAW2);
            System.out.println("Videojuego persistido: " + juegoAW2.getNombre());

            FachadaBO.crearVideojuego(juegoAW2PS5);
            System.out.println("Videojuego persistido: " + juegoAW2PS5.getNombre());

            FachadaBO.crearVideojuego(juegoU4);
            System.out.println("Videojuego persistido: " + juegoU4.getNombre());

            FachadaBO.crearVideojuego(juegoHMCC);
            System.out.println("Videojuego persistido: " + juegoHMCC.getNombre());

            FachadaBO.crearVideojuego(juegoSR);
            System.out.println("Videojuego persistido: " + juegoSR.getNombre());

            FachadaBO.crearVideojuego(juegoSRPS3);
            System.out.println("Videojuego persistido: " + juegoSRPS3.getNombre());

            FachadaBO.crearVideojuego(juegoER);
            System.out.println("Videojuego persistido: " + juegoER.getNombre());

            FachadaBO.crearVideojuego(juegoERPS5);
            System.out.println("Videojuego persistido: " + juegoERPS5.getNombre());

            FachadaBO.crearVideojuego(juegoDKB);
            System.out.println("Videojuego persistido: " + juegoDKB.getNombre());

            FachadaBO.crearVideojuego(juegoMKW);
            System.out.println("Videojuego persistido: " + juegoMKW.getNombre());

            System.out.println("\n¡ÉXITO! ¡¡¡Se insertaron todos los videojuegos!!!!.");

            //Crear clientes
            DireccionDTO dir1 = new DireccionDTO("Av. Miguel Aleman", "69", "Obregon");
            DireccionDTO dir2 = new DireccionDTO("Calle José José", "89", "Nogales");
            DireccionDTO dir3 = new DireccionDTO("Av. Nuevo León", "876", "Jecopaco");
            DireccionDTO dirAdmin = new DireccionDTO("Av. Monte Real", "666", "Chiapas");

            UsuarioRegistroDTO cliente1 = new UsuarioRegistroDTO();
            cliente1.setNombres("Sebastián");
            cliente1.setApellidoPaterno("Martínez");
            cliente1.setApellidoMaterno("Lopez");
            cliente1.setEmail("sebukis@gmail.com");
            cliente1.setContrasenia("12345");
            cliente1.setTelefono("5511223344");
            cliente1.setDireccion(dir1);

            UsuarioRegistroDTO cliente2 = new UsuarioRegistroDTO();
            cliente2.setNombres("Leonardo");
            cliente2.setApellidoPaterno("Tequida");
            cliente2.setApellidoMaterno("Pérez");
            cliente2.setEmail("leonardo@gmail.com");
            cliente2.setContrasenia("itson");
            cliente2.setTelefono("585665675657");
            cliente2.setDireccion(dir2);

            UsuarioRegistroDTO cliente3 = new UsuarioRegistroDTO();
            cliente3.setNombres("Ariel");
            cliente3.setApellidoPaterno("Lopez");
            cliente3.setApellidoMaterno("Quezada");
            cliente3.setEmail("ariels@gmail.com");
            cliente3.setContrasenia("1234");
            cliente3.setTelefono("6442587642");
            cliente3.setDireccion(dir3);

            FachadaBO.registrarCliente(cliente1);
            FachadaBO.registrarCliente(cliente2);
            FachadaBO.registrarCliente(cliente3);
            System.out.println("Cliente persistido: " + cliente1.getEmail());
            System.out.println("Cliente persistido: " + cliente2.getEmail());
            System.out.println("Cliente persistido: " + cliente3.getEmail());

            //Crear administradores
            UsuarioRegistroDTO admin = new UsuarioRegistroDTO();
            admin.setNombres("Nathan");
            admin.setApellidoPaterno("Drake");
            admin.setApellidoMaterno("Flores");
            admin.setEmail("admin@gmail.com");
            admin.setContrasenia("adminpass");
            admin.setTelefono("354627484");
            admin.setDireccion(dirAdmin);

            FachadaBO.registrarAdministrador(admin);
            System.out.println("Admin persistido: " + admin.getEmail());

            UsuarioDTO cliente1Registrado = FachadaBO.buscarUsuarioPorEmail(cliente1.getEmail());
            UsuarioDTO cliente1Registrado2 = FachadaBO.buscarUsuarioPorEmail(cliente2.getEmail());
            UsuarioDTO cliente1Registrado3 = FachadaBO.buscarUsuarioPorEmail(cliente3.getEmail());

            List<VideojuegoDTO> videojuegosEncontrados = FachadaBO.buscarVideojuegosActivos();

            VideojuegoDTO v1 = videojuegosEncontrados.getFirst();
            VideojuegoDTO v2 = videojuegosEncontrados.get(2);
            VideojuegoDTO v3 = videojuegosEncontrados.get(14);

            ResenaDTO r1 = new ResenaDTO();
            r1.setCalificacion(5.0f);
            r1.setTitulo("Obra maestra");
            r1.setComentario("Me encantó");
            r1.setFechaResena(LocalDate.now());
            r1.setIdCliente(cliente1Registrado.getIdUsuario());
            r1.setIdVideojuego(v1.getIdVideojuego());

            ResenaDTO r2 = new ResenaDTO();
            r2.setCalificacion(4.0f);
            r2.setTitulo("Tamal");
            r2.setComentario("Un poco sobrevalorado.");
            r2.setFechaResena(LocalDate.now());
            r2.setIdCliente(cliente1Registrado2.getIdUsuario());
            r2.setIdVideojuego(v1.getIdVideojuego());

            ResenaDTO r7 = new ResenaDTO();
            r7.setCalificacion(4.0f);
            r7.setTitulo("XD");
            r7.setComentario("Me gustó un poco más el primero.");
            r7.setFechaResena(LocalDate.now());
            r7.setIdCliente(cliente1Registrado3.getIdUsuario());
            r7.setIdVideojuego(v1.getIdVideojuego());

            ResenaDTO r3 = new ResenaDTO();
            r3.setCalificacion(5.0f);
            r3.setTitulo("Lo amé");
            r3.setComentario("La industria necesita más juegos como éste.");
            r3.setFechaResena(LocalDate.now());
            r3.setIdCliente(cliente1Registrado3.getIdUsuario());
            r3.setIdVideojuego(v3.getIdVideojuego());

            ResenaDTO r4 = new ResenaDTO();
            r4.setCalificacion(3.0f);
            r4.setTitulo("Meh");
            r4.setComentario("Me gustó más el 4");
            r4.setFechaResena(LocalDate.now());
            r4.setIdCliente(cliente1Registrado.getIdUsuario());
            r4.setIdVideojuego(v2.getIdVideojuego());

            ResenaDTO r5 = new ResenaDTO();
            r5.setCalificacion(5.0f);
            r5.setTitulo("GOD");
            r5.setComentario("El mejor GTA de todos");
            r5.setFechaResena(LocalDate.now());
            r5.setIdCliente(cliente1Registrado3.getIdUsuario());
            r5.setIdVideojuego(v2.getIdVideojuego());

            ResenaDTO r6 = new ResenaDTO();
            r6.setCalificacion(5.0f);
            r6.setTitulo("Donkey Kong está de vuelta!");
            r6.setComentario("Como fan de Donkey Kong desde que tengo memoria, creí que ya había visto todo: "
                    + "desde las plataformas vertiginosas del clásico arcade hasta las maravillas tropicales "
                    + "de Donkey Kong Country. Pero Donkey Kong Bananza no solo me sorprendió… me dejó "
                    + "absolutamente maravillado.");
            r6.setFechaResena(LocalDate.now());
            r6.setIdCliente(cliente1Registrado2.getIdUsuario());
            r6.setIdVideojuego(v3.getIdVideojuego());

            FachadaBO.crearResena(r1);
            FachadaBO.crearResena(r2);
            FachadaBO.crearResena(r3);
            FachadaBO.crearResena(r4);
            FachadaBO.crearResena(r5);
            FachadaBO.crearResena(r6);
            FachadaBO.crearResena(r7);

            //Crear un pedido
            PagoDTO pago1 = new PagoDTO();
            pago1.setEstadoPago("CONFIRMADO");
            pago1.setFechaPago(LocalDateTime.MAX);
            pago1.setMonto(76.4f);
            pago1.setMetodoPago("TARJETA");

            DireccionDTO direccionEnvio = new DireccionDTO();
            direccionEnvio.setCalle(cliente1.getDireccion().getCalle());
            direccionEnvio.setColonia(cliente1.getDireccion().getColonia());
            direccionEnvio.setNumero(cliente1.getDireccion().getNumero());

            ItemCarritoDTO item1 = new ItemCarritoDTO();
            item1.setCantidad(2);
            item1.setIdVideojuego(FachadaBO.buscarVideojuegoPorNombeExacto(juegoERPS5.getNombre()).getIdVideojuego());

            CarritoDTO carroEncontrado = FachadaBO.buscarCarritoPorCliente(FachadaBO.buscarUsuarioPorEmail(cliente1.getEmail()).getIdUsuario());

            FachadaBO.agregarItemACarrito(carroEncontrado.getIdCarrito(), item1);

            CarritoDTO carrito = FachadaBO.buscarCarritoPorCliente(FachadaBO.buscarUsuarioPorEmail(cliente1.getEmail()).getIdUsuario());

            System.out.println(carrito.getFechaCreacion());
            System.out.println(carrito.getIdCarrito());
            System.out.println(carrito.getItems());

            
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("¡ERROR! La transacción falló:");
            e.printStackTrace();
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }
}

