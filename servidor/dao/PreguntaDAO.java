package dao;

import dominio.Pregunta;
import servidor.conexion.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PreguntaDAO {

    public boolean guardar(Pregunta pregunta) throws SQLException {
        String sql = "INSERT INTO pregunta "
                + "(enunciado, opcion_a, opcion_b, opcion_c, opcion_d, respuesta_correcta, id_categoria) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = obtenerConexion();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            asignarDatos(statement, pregunta);
            int filasAfectadas = statement.executeUpdate();

            if (filasAfectadas > 0) {
                try (ResultSet keys = statement.getGeneratedKeys()) {
                    if (keys.next()) {
                        pregunta.setId(keys.getInt(1));
                    }
                }
                return true;
            }
            return false;
        }
    }

    public boolean actualizar(Pregunta pregunta) throws SQLException {
        String sql = "UPDATE pregunta SET enunciado = ?, opcion_a = ?, opcion_b = ?, "
                + "opcion_c = ?, opcion_d = ?, respuesta_correcta = ?, id_categoria = ? WHERE id = ?";

        try (Connection connection = obtenerConexion();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            asignarDatos(statement, pregunta);
            statement.setInt(8, pregunta.getId());
            return statement.executeUpdate() > 0;
        }
    }

    public boolean eliminar(int id) throws SQLException {
        String sql = "DELETE FROM pregunta WHERE id = ?";

        try (Connection connection = obtenerConexion();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        }
    }

    public Pregunta obtenerPorId(int id) throws SQLException {
        String sql = "SELECT id, enunciado, opcion_a, opcion_b, opcion_c, opcion_d, "
                + "respuesta_correcta, id_categoria FROM pregunta WHERE id = ?";

        try (Connection connection = obtenerConexion();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next() ? crearPregunta(resultSet) : null;
            }
        }
    }

    public List<Pregunta> listar() throws SQLException {
        String sql = "SELECT id, enunciado, opcion_a, opcion_b, opcion_c, opcion_d, "
                + "respuesta_correcta, id_categoria FROM pregunta";
        List<Pregunta> preguntas = new ArrayList<>();

        try (Connection connection = obtenerConexion();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                preguntas.add(crearPregunta(resultSet));
            }
        }
        return preguntas;
    }

    private Connection obtenerConexion() throws SQLException {
        Connection connection = Conexion.conectar();
        if (connection == null) {
            throw new SQLException("No se pudo establecer conexión con la base de datos.");
        }
        return connection;
    }

    private void asignarDatos(PreparedStatement statement, Pregunta pregunta) throws SQLException {
        statement.setString(1, pregunta.getEnunciado());
        statement.setString(2, pregunta.getOpcion_a());
        statement.setString(3, pregunta.getOpcion_b());
        statement.setString(4, pregunta.getOpcion_c());
        statement.setString(5, pregunta.getOpcion_d());
        statement.setString(6, pregunta.getRespuesta_correcta());
        statement.setInt(7, pregunta.getId_categoria());
    }

    private Pregunta crearPregunta(ResultSet resultSet) throws SQLException {
        return new Pregunta(
                resultSet.getInt("id"),
                resultSet.getString("enunciado"),
                resultSet.getString("opcion_a"),
                resultSet.getString("opcion_b"),
                resultSet.getString("opcion_c"),
                resultSet.getString("opcion_d"),
                resultSet.getString("respuesta_correcta"),
                resultSet.getInt("id_categoria"));
    }
}
