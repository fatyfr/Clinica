import accesoadatos.PacienteDAL;
import entidadesdenegocio.Paciente;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class PacienteDALTest {
    @Test
    public void guardarTest() throws SQLException {
        Paciente patient = new Paciente();
        patient.setNombre("Juan Carlos");
        patient.setApellido("Pérez López");
        patient.setEdad(20);
        patient.setExpediente(22008);
        patient.setDireccion("Sonsonate");

        int esperado = 1;
        int actual = PacienteDAL.guardar(patient);
        Assertions.assertEquals(esperado, actual);
    }

    @Test
    public void modificarTest() throws SQLException {
        Paciente patient = new Paciente();
        patient.setNombre("Juan Carlos");
        patient.setApellido("Pérez López");
        patient.setEdad(20);
        patient.setExpediente(22008);
        patient.setDireccion("Sonsonate");

        int esperado = 1;
        int actual = PacienteDAL.modificar(patient);
        Assertions.assertEquals(esperado, actual);
    }

    @Test
    public void eliminarTest() throws SQLException{
        Paciente patient = new Paciente();
        patient.setId(2);

        int esperado = 1;
        int actual = PacienteDAL.eliminar(patient);
        Assertions.assertEquals(esperado, actual);
    }

    @Test
    public void obtenerTodosTest() throws SQLException{
        int actual = PacienteDAL.obtenerTodos().size();
        Assertions.assertNotEquals(0, actual);
    }

    @Test
    public void obtenerDatosFiltradosTest() throws SQLException{
       Paciente patient = new Paciente();

        patient.setId(0);
        patient.setNombre("");
        patient.setApellido("");
        patient.setEdad(0);
        patient.setExpediente(0);
        patient.setDireccion("");

        int actual = PacienteDAL.obtenerDatosFiltrados(patient).size();
        Assertions.assertNotEquals(0, actual);
    }
}
