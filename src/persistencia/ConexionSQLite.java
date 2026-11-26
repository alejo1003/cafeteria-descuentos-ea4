package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConexionSQLite {

    private static final String DRIVER = "org.sqlite.JDBC";
    // Crea o conecta a un archivo local cafeteria.db en la carpeta del proyecto
    private static final String URL = "jdbc:sqlite:cafeteria.db";

    public static String probarConexionInicial() {
        try {
            // Carga explícita del driver para manejar ClassNotFoundException si falta el JAR
            Class.forName(DRIVER);

            // try-with-resources garantiza el cierre automático de Connection, Statement y ResultSet
            try (Connection conn = DriverManager.getConnection(URL);
                 PreparedStatement stmt = conn.prepareStatement("SELECT 1");
                 ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    return "Conexión JDBC a SQLite exitosa (" + URL + ").";
                }
                return "Conexión establecida, pero no se obtuvo respuesta de prueba.";
            }

        } catch (ClassNotFoundException ex) {
            return "Error de conexión JDBC: no se encontró el driver de SQLite (" + ex.getMessage() + ").";
        } catch (SQLException ex) {
            return "Error SQL al conectar con la base de datos: " + ex.getMessage();
        }
    }
}
