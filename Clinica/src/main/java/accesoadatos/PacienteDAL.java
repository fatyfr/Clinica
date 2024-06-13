package accesoadatos;

import entidadesdenegocio.Paciente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PacienteDAL {
    public static int guardar(Paciente paciente) throws SQLException {
        int result = 0;
        try {
            String sql = "INSERT INTO Pacientes (Nombre, Apellido,Edad,Expediente,Direccion) VALUES(?, ?, ?, ?,?)";
            Connection conexion = ComunDB.obtenerConexion();
            PreparedStatement ps = ComunDB.crearPreparedStatement(conexion, sql);
            ps.setString(1, paciente.getNombre());
            ps.setString(2, paciente.getApellido());
            ps.setInt(3, paciente.getEdad());
            ps.setInt(4, paciente.getExpediente());
            ps.setString(5, paciente.getDireccion());
            result = ps.executeUpdate();
            conexion.close();
            ps.close();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return result;
    }
    // método que permite modificar un registro existente
    public static int modificar(Paciente paciente) throws SQLException{
        int result = 0;
        try {
            String sql = "UPDATE Pacientes SET   Nombre = ?, Apellido = ?, Edad = ?, Expediente = ?, Direccion = ? WHERE Id = ?";
            Connection conexion = ComunDB.obtenerConexion();
            PreparedStatement ps = ComunDB.crearPreparedStatement(conexion, sql);
            ps.setString(1, paciente.getNombre());
            ps.setString(2, paciente.getApellido());
            ps.setInt(3, paciente.getEdad());
            ps.setInt(4, paciente.getExpediente());
            ps.setString(5, paciente.getDireccion());
            result = ps.executeUpdate();
            conexion.close();
            ps.close();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return result;
    }
    // método que permite eliminar un registro existente
    public static int eliminar(Paciente paciente) throws SQLException{
        int result = 0;
        try {
            String sql = "DELETE FROM Pacientes WHERE Id = ?";
            Connection conexion = ComunDB.obtenerConexion();
            PreparedStatement ps = ComunDB.crearPreparedStatement(conexion, sql);
            ps.setInt(1, paciente.getId());
            result = ps.executeUpdate();
            conexion.close();
            ps.close();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return result;
    }
    // método que muestra todos los registros de la tabla
    public static ArrayList<Paciente> obtenerTodos() throws SQLException {
        ArrayList<Paciente> lista = new ArrayList<>();
        Paciente paciente;
        try{
            String sql = "SELECT Id, Nombre, Apellido, Edad, Expediente, Direccion FROM Pacientes";
            Connection conexion = ComunDB.obtenerConexion();
            PreparedStatement ps = ComunDB.crearPreparedStatement(conexion, sql);
            ResultSet rs = ComunDB.obtenerResultSet(ps);
            while (rs.next()){
                paciente = new Paciente(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getString(6));
                lista.add(paciente);
            }
            conexion.close();
            ps.close();
            rs.close();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return lista;

    }
    // método para consultar mediante criterios
    public static ArrayList<Paciente> obtenerDatosFiltrados(Paciente paciente) throws SQLException{
        ArrayList<Paciente> lista = new ArrayList<>();
        Paciente est;
        try{
            String sql = "SELECT id, nombre, apellido, edad, expediente,direccion FROM Pacientes WHERE id = ? or apellido like'%" + paciente.getApellido() + "%' or edad like'%" + paciente.getEdad() + "%' or expediente like'&" + paciente.getExpediente() + "%' or direccion like '&" + paciente.getDireccion() +"%'";
            Connection connection = ComunDB.obtenerConexion();
            PreparedStatement ps = ComunDB.crearPreparedStatement(connection, sql);
            ps.setInt(1, paciente.getId());
            ResultSet rs = ComunDB.obtenerResultSet(ps);
            while (rs.next()){
                est = new Paciente (rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getString(6));
                lista.add(est);
            }
            connection.close();
            ps.close();
            rs.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return lista;
    }

}
