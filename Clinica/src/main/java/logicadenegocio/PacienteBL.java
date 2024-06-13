package logicadenegocio;

import accesoadatos.PacienteDAL;
import entidadesdenegocio.Paciente;

import java.sql.SQLException;
import java.util.ArrayList;

public class PacienteBL {
    public int guardar(Paciente paciente) throws SQLException {
        return PacienteDAL.guardar(paciente);
    }

    public int modificar(Paciente paciente) throws SQLException{
        return PacienteDAL.modificar(paciente);
    }

    public int eliminar(Paciente paciente) throws SQLException{
        return PacienteDAL.eliminar(paciente);
    }

    public static ArrayList<Paciente> obtenerTodos() throws SQLException{
        return PacienteDAL.obtenerTodos();
    }

    public ArrayList<Paciente> obteberDatosFiltrados(Paciente paciente) throws SQLException{
        return PacienteDAL.obtenerDatosFiltrados(paciente);
    }
}
