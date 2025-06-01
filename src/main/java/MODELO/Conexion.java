/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO;

import java.sql.*;
import java.time.LocalDate;
import java.time.ZoneId;

import VISTA.InicioSesion.LogInVista;
import VISTA.InicioSesion.RegistroVista;

/**
 * Clase de conexión y autenticación adaptada al proyecto Fútbol-5.
 * Usa MySQL como base de datos y asume la existencia de las tablas:
 *   - USUARIO(usuario_id, username, password_hash, tipo_usuario)
 *   - JUGADOR(jugador_id, nombre, frecuente, modo_participacion, infracciones, 
 *             calificacion_promedio, calificacion_ultimo_partido)
 *   - ADMINISTRADOR(administrador_id)
 *
 * También interactúa con la clase Singleton para almacenar el usuario actual.
 */
public class Conexion {

    private String usuario;
    private String clave;

    public Conexion() { }

    public Conexion(String usuario, String clave) {
        this.usuario = usuario;
        this.clave = clave;
    }

    // ----------------------------------------
    // Getters y Setters
    // ----------------------------------------
    public String getUsuario() {
        return usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    // ----------------------------------------
    // 1. Método para obtener conexión JDBC
    // ----------------------------------------
    public Connection obtenerConexion() throws SQLException, ClassNotFoundException {
        // Driver JDBC moderno para MySQL
        Class.forName("com.mysql.cj.jdbc.Driver");
        // URL de conexión: localhost, puerto 3306, schema "futbol5db"
        // Ajusta el nombre de la base de datos, usuario y contraseña según tu entorno
        String url = "jdbc:mysql://localhost:3306/futbol5db?useSSL=false&serverTimezone=UTC";
        String user = "root";
        String pass = ""; // pon aquí la contraseña real si no es vacía
        return DriverManager.getConnection(url, user, pass);
    }

    // ----------------------------------------
    // 2. Método para verificar login
    //    Busca en la tabla USUARIO (username, password_hash)
    //    Si coincide, carga datos en Singleton según tipo_usuario.
    // ----------------------------------------
    public boolean ingresar() {
        String sql = "SELECT usuario_id, tipo_usuario FROM USUARIO WHERE username = ? AND password_hash = ?";

        try (Connection con = obtenerConexion();
             PreparedStatement ps = con.prepareStatement(
                     sql,
                     ResultSet.TYPE_FORWARD_ONLY,
                     ResultSet.CONCUR_READ_ONLY)) {

            ps.setString(1, this.usuario);
            ps.setString(2, this.clave);

            ResultSet rs = ps.executeQuery();
            if (rs.first()) {
                int id = rs.getInt("usuario_id");
                String tipo = rs.getString("tipo_usuario"); // 'J' o 'A'

                // Dependiendo del tipo, inicializamos el Singleton con Jugador o Administrador
                if ("J".equalsIgnoreCase(tipo)) {
                    // Cargar datos del jugador para el Singleton
                    Jugador j = cargarDatosJugador(id);
                    if (j != null) {
                        Singleton.getInstance().setUsuario(j);
                        return true;
                    }
                } else if ("A".equalsIgnoreCase(tipo)) {
                    // Cargar datos del administrador para el Singleton
                    Administrador a = cargarDatosAdministrador(id);
                    if (a != null) {
                        Singleton.getInstance().setUsuario(a);
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Error en autenticación: " + e.getMessage());
        }
        return false;
    }

    // ----------------------------------------
    // 3. Carga los datos de un Jugador dado su ID
    //    Lee la tabla JUGADOR y devuelve el objeto Jugador completo.
    // ----------------------------------------
    private Jugador cargarDatosJugador(int jugadorId) {
        String sql = "SELECT nombre, frecuente, modo_participacion, infracciones, calificacion_promedio, " +
                     "calificacion_ultimo_partido FROM JUGADOR WHERE jugador_id = ?";

        try (Connection con = obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, jugadorId);
            ResultSet rs = ps.executeQuery();
            if (rs.first()) {
                Jugador j = new Jugador();
                j.setId(jugadorId);
                j.setNombre(rs.getString("nombre"));
                j.setFrecuente(rs.getBoolean("frecuente"));
                j.setModoParticipacion( ModoParticipacion.valueOf(rs.getString("modo_participacion")) );
                j.setInfracciones(rs.getInt("infracciones"));
                j.setCalificacionPromedio(rs.getFloat("calificacion_promedio"));
                j.setCalificacionUltimoPartido(rs.getInt("calificacion_ultimo_partido"));
                // Puedes cargar también lista de calificaciones si lo deseas
                return j;
            }
        } catch (Exception e) {
            System.err.println("Error al cargar datos de Jugador: " + e.getMessage());
        }
        return null;
    }

    // ----------------------------------------
    // 4. Carga los datos de un Administrador dado su ID
    //    Lee la tabla ADMINISTRADOR (podrías extender con más campos si existen)
    // ----------------------------------------
    private Administrador cargarDatosAdministrador(int adminId) {
        String sql = "SELECT administrador_id FROM ADMINISTRADOR WHERE administrador_id = ?";

        try (Connection con = obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, adminId);
            ResultSet rs = ps.executeQuery();
            if (rs.first()) {
                Administrador a = new Administrador();
                a.setId(adminId);
                // Si tu clase Administrador tiene más atributos, cárgalos aquí
                return a;
            }
        } catch (Exception e) {
            System.err.println("Error al cargar datos de Administrador: " + e.getMessage());
        }
        return null;
    }

    // ----------------------------------------
    // 5. Método para registrar un nuevo Jugador
    //    Inserta en USUARIO y en JUGADOR.
    // ----------------------------------------
    public boolean registrarJugador(String username, String passHash, Jugador j) {
        String sqlUsuario = "INSERT INTO USUARIO (username, password_hash, tipo_usuario) VALUES (?, ?, 'J')";
        String sqlJugador = "INSERT INTO JUGADOR (jugador_id, nombre, frecuente, modo_participacion, infracciones, " +
                            "calificacion_promedio, calificacion_ultimo_partido) VALUES (?, ?, ?, ?, ?, ?, ?)";

        Connection con = null;
        PreparedStatement psUsuario = null;
        PreparedStatement psJugador = null;

        try {
            con = obtenerConexion();
            con.setAutoCommit(false); // inicio transacción

            // 1) Insertar en USUARIO
            psUsuario = con.prepareStatement(sqlUsuario, Statement.RETURN_GENERATED_KEYS);
            psUsuario.setString(1, username);
            psUsuario.setString(2, passHash);
            psUsuario.executeUpdate();

            ResultSet rsKeys = psUsuario.getGeneratedKeys();
            if (rsKeys.first()) {
                int newUserId = rsKeys.getInt(1);

                // 2) Insertar en JUGADOR con el mismo ID
                psJugador = con.prepareStatement(sqlJugador);
                psJugador.setInt(1, newUserId);
                psJugador.setString(2, j.getNombre());
                psJugador.setBoolean(3, j.isFrecuente());
                psJugador.setString(4, j.getModo().name());
                psJugador.setInt(5, j.getInfracciones());
                psJugador.setFloat(6, j.getCalificacionPromedio());
                psJugador.setInt(7, j.getCalificacionUltimoPartido());
                psJugador.executeUpdate();

                con.commit();
                return true;
            } else {
                con.rollback();
            }
        } catch (Exception e) {
            try { if (con != null) con.rollback(); } catch (SQLException ex) { /* ignorar */ }
            System.err.println("Error al registrar Jugador: " + e.getMessage());
        } finally {
            try { if (psJugador != null) psJugador.close(); } catch (SQLException ex) { /* ignorar */ }
            try { if (psUsuario != null) psUsuario.close(); } catch (SQLException ex) { /* ignorar */ }
            try { if (con != null) con.setAutoCommit(true); con.close(); } catch (SQLException ex) { /* ignorar */ }
        }
        return false;
    }

    // ----------------------------------------
    // 6. Método para registrar resultados de un partido
    //    Inserta en PARTIDO, luego crea 2 registros en EQUIPO y asigna jugadores en EQUIPO_JUGADOR
    // ----------------------------------------
    public boolean guardarPartido(Partidos p, EquipoRojo rojo, EquipoAzul azul) {
        String sqlPartido = "INSERT INTO PARTIDO (fecha_partido, formacion_id, resultado_rojo, resultado_azul, confirmado) " +
                            "VALUES (?, ?, ?, ?, ?)";
        String sqlEquipo = "INSERT INTO EQUIPO (partido_id, tipo_equipo) VALUES (?, ?)";
        String sqlEquipoJugador = "INSERT INTO EQUIPO_JUGADOR (equipo_id, jugador_id) VALUES (?, ?)";

        Connection con = null;
        PreparedStatement psPartido = null;
        PreparedStatement psEquipo = null;
        PreparedStatement psEquipoJugador = null;

        try {
            con = obtenerConexion();
            con.setAutoCommit(false);

            // 1) Insertar en PARTIDO
            psPartido = con.prepareStatement(sqlPartido, Statement.RETURN_GENERATED_KEYS);
            psPartido.setTimestamp(1, Timestamp.valueOf(p.getFecha().atStartOfDay(ZoneId.systemDefault())));
            psPartido.setInt(2, p.getFormacion().getId());
            psPartido.setInt(3, p.getResultadoRojo());
            psPartido.setInt(4, p.getResultadoAzul());
            psPartido.setBoolean(5, p.isConfirmado());
            psPartido.executeUpdate();

            ResultSet rsPartido = psPartido.getGeneratedKeys();
            if (!rsPartido.first()) {
                con.rollback();
                return false;
            }
            int newPartidoId = rsPartido.getInt(1);

            // 2) Insertar Equipo Rojo
            psEquipo = con.prepareStatement(sqlEquipo, Statement.RETURN_GENERATED_KEYS);
            psEquipo.setInt(1, newPartidoId);
            psEquipo.setString(2, "ROJO");
            psEquipo.executeUpdate();
            ResultSet rsRojo = psEquipo.getGeneratedKeys();
            if (!rsRojo.first()) {
                con.rollback();
                return false;
            }
            int newEquipoRojoId = rsRojo.getInt(1);

            // 3) Insertar Equipo Azul
            psEquipo.setInt(1, newPartidoId);
            psEquipo.setString(2, "AZUL");
            psEquipo.executeUpdate();
            ResultSet rsAzul = psEquipo.getGeneratedKeys();
            if (!rsAzul.first()) {
                con.rollback();
                return false;
            }
            int newEquipoAzulId = rsAzul.getInt(1);

            // 4) Asignar jugadores al Equipo Rojo
            psEquipoJugador = con.prepareStatement(sqlEquipoJugador);
            for (Jugador j : rojo.getJugadores()) {
                psEquipoJugador.setInt(1, newEquipoRojoId);
                psEquipoJugador.setInt(2, j.getId());
                psEquipoJugador.executeUpdate();
            }

            // 5) Asignar jugadores al Equipo Azul
            for (Jugador j : azul.getJugadores()) {
                psEquipoJugador.setInt(1, newEquipoAzulId);
                psEquipoJugador.setInt(2, j.getId());
                psEquipoJugador.executeUpdate();
            }

            con.commit();
            return true;

        } catch (Exception e) {
            try { if (con != null) con.rollback(); } catch (SQLException ex) { /* ignorar */ }
            System.err.println("Error al guardar Partido: " + e.getMessage());
        } finally {
            try { if (psEquipoJugador != null) psEquipoJugador.close(); } catch (SQLException ex) { /* ignorar */ }
            try { if (psEquipo != null) psEquipo.close(); } catch (SQLException ex) { /* ignorar */ }
            try { if (psPartido != null) psPartido.close(); } catch (SQLException ex) { /* ignorar */ }
            try { if (con != null) con.setAutoCommit(true); con.close(); } catch (SQLException ex) { /* ignorar */ }
        }
        return false;
    }

    // ----------------------------------------
    // 7. Obtener detalles de un Jugador
    //    (ejemplo de método adicional para consulta)
    // ----------------------------------------
    public Jugador obtenerJugadorPorId(int id) {
        return cargarDatosJugador(id);
    }

    // ----------------------------------------
    // 8. Obtener detalles de un Partido
    //    (ejemplo de método adicional para consulta)
    // ----------------------------------------
    public Partidos obtenerPartidoPorId(int id) {
        String sql = "SELECT * FROM PARTIDO WHERE partido_id = ?";
        try (Connection con = obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.first()) {
                Partidos p = new Partidos();
                p.setPartidoId(id);
                p.setFecha(rs.getDate("fecha_partido").toLocalDate());
                p.setResultadoRojo(rs.getInt("resultado_rojo"));
                p.setResultadoAzul(rs.getInt("resultado_azul"));
                p.setConfirmado(rs.getBoolean("confirmado"));
                // Cargar Formacion
                Formaciones f = new Formaciones();
                f.setId(rs.getInt("formacion_id"));
                p.setFormacion(f);
                // Aquí podrías cargar EquipoRojo y EquipoAzul si lo deseas
                return p;
            }
        } catch (Exception e) {
            System.err.println("Error al obtener Partido: " + e.getMessage());
        }
        return null;
    }
}

