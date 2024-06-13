package entidadesdenegocio;

public class Paciente {
    private int Id;
    private String Nombre;
    private String Apellido;
    private int Edad;
    private int Expediente;
    private String Direccion;

    public Paciente(){}

    public Paciente(int id, String nombre, String apellido, int edad, int expediente, String direccion) {
        Id = id;
        Nombre = nombre;
        Apellido = apellido;
        Edad = edad;
        Expediente = expediente;
        Direccion = direccion;
    }

    public int getId() {
        return Id;
    }

    public String getNombre() {
        return Nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public int getEdad() {
        return Edad;
    }

    public int getExpediente() {
        return Expediente;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setId(int id) {
        Id = id;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public void setApellido(String apellido) {
        Apellido = apellido;
    }

    public void setEdad(int edad) {
        Edad = edad;
    }

    public void setExpediente(int expediente) {
        Expediente = expediente;
    }

    public void setDireccion(String direccion) {
        Direccion = direccion;
    }
}
