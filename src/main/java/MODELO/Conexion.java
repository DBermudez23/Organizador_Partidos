package MODELO;

import java.sql.*;
import java.time.ZoneId;

/**
 * Clase de conexión y autenticación para el proyecto Fútbol-5.
 * 
 * - Base de datos: partidos
 * - Tabla usuario(usuario_id, username, password_hash, tipo_usuario)
 * - Tabla jugador  (jugador_id, nombre, frecuente, modo_participacion, infracciones,
 *                   calificacion_promedio, calificacion_ultimo_partido)
 * - Tabla administrador(administrador_id, nombre, …)
 *
 * Cuando loguea, si tipo_usuario='J', carga un Jugador en el Singleton;
 * si tipo_usuario='A', carga un Administrador en el Singleton.
 */
public class Conexion {

    // Estos dos campos se asignan justo antes de invocar ingresar():
    private String usuario; // el username que ingresa el usuario
    private String clave;   // el password que ingresa el usuario

    public Conexion() { }

    public Conexion(String usuario, String clave) {
        this.usuario = usuario;
        this.clave = clave;
    }

    // -------------------------------
    // Getters / Setters (opcionales)
    // -------------------------------
    public String getUsuario() {
        return usuario;
    }
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    public String getClave() {
        return clave;
    }
    public void setClave(String clave) {
        this.clave = clave;
    }

    // ----------------------------------------
    // 1. Abre la conexión JDBC
    // ----------------------------------------
    public Connection obtenerConexion() throws SQLException, ClassNotFoundException {
        // 1.1. Cargar driver (versión moderna)
        Class.forName("com.mysql.cj.jdbc.Driver");

        // 1.2. URL apuntando a la base "partidos" en localhost:3306
        String url = "jdbc:mysql://localhost:3306/partidos?useSSL=false&serverTimezone=UTC";
        String user = "root";
        String pass = ""; // si tu usuario root tiene contraseña, ponla aquí

        return DriverManager.getConnection(url, user, pass);
    }

    // ----------------------------------------
    // 2. Método que verifica login y carga el Singleton
    // ----------------------------------------
    public boolean ingresar() {
        // Ojo: “usuario” y “clave” ya fueron asignados en el constructor o con setUsuario/ setClave.
        String sql = 
            "SELECT usuario_id, tipo_usuario " +
            "FROM usuario " +
            "WHERE username = ? AND password_hash = ?";

        try (Connection con = obtenerConexion();
             PreparedStatement ps = con.prepareStatement(
                 sql,
                 ResultSet.TYPE_FORWARD_ONLY,
                 ResultSet.CONCUR_READ_ONLY
             ))
        {
            // Para depurar, imprimimos en consola exactamente qué vamos a ejecutar:
            System.out.println("[DEBUG] Intentando login con: username='" 
                + this.usuario + "', password='" + this.clave + "'");

            ps.setString(1, this.usuario);
            ps.setString(2, this.clave);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                // Si entró aquí, hay al menos una fila en la consulta
                int id = rs.getInt("usuario_id");
                String tipo = rs.getString("tipo_usuario"); // 'J' o 'A'
                System.out.println("[DEBUG] Encontró fila: id=" + id + ", tipo=" + tipo);

                if ("J".equalsIgnoreCase(tipo)) {
                    // Cargar datos completos desde la tabla jugador
                    Jugador j = cargarDatosJugador(id);
                    if (j != null) {
                        Singleton.getInstance().setUsuario(j);
                        return true;
                    } else {
                        System.err.println("[WARN] No se pudo cargar datos de Jugador con id=" + id);
                    }
                }
                else if ("A".equalsIgnoreCase(tipo)) {
                    // Cargar datos completos desde la tabla administrador
                    Administrador a = cargarDatosAdministrador(id);
                    if (a != null) {
                        Singleton.getInstance().setUsuario(a);
                        return true;
                    } else {
                        System.err.println("[WARN] No se pudo cargar datos de Administrador con id=" + id);
                    }
                }
                else {
                    System.err.println("[WARN] Tipo de usuario inesperado: " + tipo);
                }
            }
            else {
                // No devolvió ninguna fila
                System.out.println("[DEBUG] No se encontró combinación username/password.");
            }
        }
        catch (SQLException | ClassNotFoundException e) {
            System.err.println("[ERROR] Excepción en ingresar(): " + e.getMessage());
            e.printStackTrace();
        }

        return false;
    }

    // ----------------------------------------
    // 3. Busca en tabla jugador por jugador_id
    //    (devuelve un Jugador con todos sus campos)
    // ----------------------------------------
    private Jugador cargarDatosJugador(int jugadorId) {
        String sql = 
            "SELECT nombre, frecuente, modo_participacion, infracciones, " +
            "       calificacion_promedio, calificacion_ultimo_partido " +
            "FROM jugador " +
            "WHERE jugador_id = ?";

        try (Connection con = obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql))
        {
            ps.setInt(1, jugadorId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Jugador j = new Jugador();
                j.setId(jugadorId);
                j.setNombre(rs.getString("nombre"));
                j.setFrecuente(rs.getBoolean("frecuente"));
                j.setModoParticipacion(
                    ModoParticipacion.valueOf(rs.getString("modo_participacion"))
                );
                j.setInfracciones(rs.getInt("infracciones"));
                j.setCalificacionPromedio(rs.getFloat("calificacion_promedio"));
                j.setCalificacionUltimoPartido(rs.getFloat("calificacion_ultimo_partido"));
                return j;
            }
        }
        catch (SQLException | ClassNotFoundException e) {
            System.err.println("[ERROR] al cargar datos de Jugador: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    // ----------------------------------------
    // 4. Busca en tabla administrador por administrador_id
    //    (devuelve un Administrador con todos sus campos)
    // ----------------------------------------
    private Administrador cargarDatosAdministrador(int adminId) {
        String sql = 
            "SELECT administrador_id, nombre " +
            "FROM administrador " +
            "WHERE administrador_id = ?";

        try (Connection con = obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql))
        {
            ps.setInt(1, adminId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Administrador a = new Administrador();
                a.setId(adminId);
                a.setNombre(rs.getString("nombre")); 
                return a;
            }
        }
        catch (SQLException | ClassNotFoundException e) {
            System.err.println("[ERROR] al cargar datos de Administrador: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 3. Registramos un nuevo Jugador: 
     *    - Insertar en USUARIO (username, password_hash, 'J')
     *    - Recuperar el ID generado
     *    - Insertar en JUGADOR con ese mismo ID
     */
    public boolean registrarJugador(String username, String passHash, Jugador j) {
        String sqlUsuario = "INSERT INTO usuario (username, password_hash, tipo_usuario) VALUES (?, ?, 'J')";
        String sqlJugador = "INSERT INTO jugador (jugador_id, nombre, frecuente, modo_participacion, infracciones, " +
                            "calificacion_promedio, calificacion_ultimo_partido) VALUES (?, ?, ?, ?, ?, ?, ?)";
        Connection con = null;
        PreparedStatement psUsuario = null;
        PreparedStatement psJugador = null;

        try {
            con = obtenerConexion();
            con.setAutoCommit(false);

            // 1) Insertar en USUARIO
            psUsuario = con.prepareStatement(sqlUsuario, Statement.RETURN_GENERATED_KEYS);
            psUsuario.setString(1, username);
            psUsuario.setString(2, passHash);
            psUsuario.executeUpdate();

            ResultSet rsKeys = psUsuario.getGeneratedKeys();
            if (rsKeys.next()) {
                int newUserId = rsKeys.getInt(1);

                // 2) Insertar en JUGADOR con el mismo ID
                psJugador = con.prepareStatement(sqlJugador);
                psJugador.setInt(1, newUserId);
                psJugador.setString(2, j.getNombre());
                psJugador.setBoolean(3, j.isFrecuente());
                psJugador.setString(4, j.getModoParticipacion().name());
                psJugador.setInt(5, j.getInfracciones());
                psJugador.setFloat(6, j.getCalificacionPromedio());
                psJugador.setFloat(7, j.getCalificacionUltimoPartido());
                psJugador.executeUpdate();

                con.commit();
                return true;
            } else {
                con.rollback();
            }
        } catch (SQLException | ClassNotFoundException e) {
            try {
                if (con != null) con.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                if (psJugador != null) psJugador.close();
                if (psUsuario != null) psUsuario.close();
                if (con != null) {
                    con.setAutoCommit(true);
                    con.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }
}
