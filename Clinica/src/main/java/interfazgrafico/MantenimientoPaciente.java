package interfazgrafico;

import com.sun.tools.javac.Main;
import entidadesdenegocio.Paciente;
import logicadenegocio.PacienteBL;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

public class MantenimientoPaciente extends JFrame{

    private JTextField txtId;
    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtEdad;
    private JTextField txtExpediente;
    private JTextField txtDireccion;
    private JButton btnNuevo;
    private JButton btnGuardar;
    private JButton btnModificar;
    private JButton btnEliminar;
    private JButton btnCancelar;
    private JButton btnSalir;
    private JRadioButton rdbID;
    private JRadioButton rdbApellido;
    private JRadioButton rdbExpediente;
    private JTextField txtCriterio;
    private JButton btnBuscar;
    private JTable jtPaciente;
    private JPanel jpPrincipal;
    private ButtonGroup CriterioBusqueda;

    //estas son instancias propias
    ArrayList<Paciente> listaPaciente;
    Paciente patient;
    PacienteBL pacienteBL = new PacienteBL();

    public static void main(String[] args) throws SQLException {
        new MantenimientoPaciente();
    }


    public MantenimientoPaciente() throws SQLException {
        inicializar();
        actualizarFrom();

        btnSalir.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.exit(0);
            }
        });

        btnCancelar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                try{
                    actualizarFrom();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });
        btnNuevo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                txtNombre.setEnabled(true);
                txtApellido.setEnabled(true);
                txtEdad.setEnabled(true);
                txtExpediente.setEnabled(true);
                txtDireccion.setEnabled(true);
                btnGuardar.setEnabled(true);
                btnNuevo.setEnabled(false);
                btnCancelar.setEnabled(true);
            }
        });
        btnGuardar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                patient= new Paciente();
                patient.setNombre(txtNombre.getText());
                patient.setApellido(txtApellido.getText());
                patient.setEdad(Integer.parseInt(txtEdad.getText()));
                patient.setExpediente(Integer.parseInt(txtExpediente.getText()));
                patient.setDireccion(txtDireccion.getText());
                try {
                    pacienteBL.guardar(patient);
                    JOptionPane.showConfirmDialog(null, "se guardo con exito");
                    actualizarFrom();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        btnModificar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                patient = new Paciente();
                patient.setId(Integer.parseInt(txtId.getText()));
                patient.setNombre(txtNombre.getText());
                patient.setApellido(txtApellido.getText());
                patient.setEdad(Integer.parseInt(txtEdad.getText()));
                patient.setExpediente(Integer.parseInt(txtExpediente.getText()));
                patient.setDireccion(txtDireccion.getText());
                try {
                   pacienteBL.modificar(patient);
                    JOptionPane.showConfirmDialog(null, "se modifico con exito");
                    actualizarFrom();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        jtPaciente.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int fila = jtPaciente.getSelectedRow();
                txtId.setText(jtPaciente.getValueAt(fila, 0).toString());
                txtNombre.setText(jtPaciente.getValueAt(fila, 1).toString());
                txtApellido.setText(jtPaciente.getValueAt(fila, 2).toString());
                txtEdad.setText( jtPaciente.getValueAt(fila, 3).toString());
                txtExpediente.setText(jtPaciente.getValueAt(fila,4).toString());
                txtDireccion.setText(jtPaciente.getValueAt(fila,5).toString());

                txtNombre.setEnabled(true);
                txtApellido.setEnabled(true);
                txtEdad.setEnabled(true);
                txtExpediente.setEnabled(true);
                txtDireccion.setEnabled(true);


                btnNuevo.setEnabled(false);
                btnModificar.setEnabled(true);
                btnEliminar.setEnabled(true);
                btnCancelar.setEnabled(true);
            }

        });
        btnEliminar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                patient.setId(Integer.parseInt(txtId.getText()));
                try{
                    pacienteBL.eliminar(patient);
                    JOptionPane.showMessageDialog(null,"se elimino correctamente");
                    actualizarFrom();
                }catch (SQLException ex){
                    throw new RuntimeException(ex);
                }
            }
        });
        btnBuscar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (txtCriterio.getText().equals("") || (!rdbID.isSelected() && !rdbApellido.isSelected() && !rdbExpediente.isSelected())){
                    JOptionPane.showMessageDialog(null,"seleccione un criterio de busqueda o escriba el valor a buscar");

                }
                patient = new Paciente();
                if (rdbID.isSelected()){
                    patient.setId(Integer.parseInt(txtCriterio.getText()));
                    try{
                        llenarTabla(pacienteBL.obteberDatosFiltrados(patient));

                    }catch (SQLException ex){
                        throw new RuntimeException(ex);
                    }
                }
                if (rdbApellido.isSelected()){
                    patient.setApellido(txtCriterio.getText());
                    try {
                        llenarTabla(pacienteBL.obteberDatosFiltrados(patient));
                    }catch (SQLException ex){
                        throw new RuntimeException(ex);
                    }
                }
                if (rdbExpediente.isSelected()){
                    patient.setExpediente(Integer.parseInt(txtCriterio.getText()));
                    try{
                        llenarTabla(pacienteBL.obteberDatosFiltrados(patient));
                    }catch (SQLException ex){
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
    }

    void inicializar(){
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(600,700);
        setTitle("Control de Paciente");
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);

        CriterioBusqueda = new ButtonGroup();
        CriterioBusqueda.add(rdbID);
        CriterioBusqueda.add(rdbApellido);
        CriterioBusqueda.add(rdbExpediente);

        setContentPane(jpPrincipal);
        setVisible(true);
    }

    void llenarTabla(ArrayList<Paciente> paciente){
        Object[] objects = new Object[6];
        listaPaciente = new ArrayList<>();
        String[] encabezado = {"ID", "NOMBRE", "APELLIDO", "EDAD" , "EXPEDIENTE","DIRECCION"};
        DefaultTableModel tm = new DefaultTableModel(null, encabezado);
        listaPaciente.addAll(paciente);
        for (Paciente item : listaPaciente){
            objects[0] = item.getId();
            objects[1] = item.getNombre();
            objects[2] = item.getApellido();
            objects[3] = item.getEdad();
            objects[4] = item.getExpediente();
            objects[5] = item.getDireccion();
            tm.addRow(objects);
        }
        jtPaciente.setModel(tm);
    }

    void actualizarFrom() throws SQLException {
        txtId.setText("");
        txtNombre.setText("");
        txtApellido.setText("");
        txtEdad.setText("");
        txtExpediente.setText("");
        txtDireccion.setText("");


        txtId.setEnabled(false);
        txtNombre.setEnabled(false);
        txtApellido.setEnabled(false);
        txtEdad.setEnabled(false);
        txtExpediente.setEnabled(false);
        txtDireccion.setEnabled(false);

        btnNuevo.setEnabled(true);
        btnGuardar.setEnabled(false);
        btnModificar.setEnabled(false);
        btnEliminar.setEnabled(false);

        txtCriterio.setText("");
        CriterioBusqueda.clearSelection();

        llenarTabla(PacienteBL.obtenerTodos());
    }
}
