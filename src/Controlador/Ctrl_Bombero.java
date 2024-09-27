package Controlador;

import Modelo.Bombero;
import Vista.FrmBombero;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ctrl_Bombero implements ActionListener {

    private FrmBombero vista;
    private Bombero modelo;

    // Constructor que recibe la vista y el modelo
    public Ctrl_Bombero(FrmBombero Vista, Bombero Modelo) {
        this.vista = Vista;
        this.modelo = Modelo;

        // Inicializar la conexión y cargar datos en la tabla
        init();

        // Agregar listeners a los botones
        vista.btnAgregar.addActionListener(this);
        vista.btnEliminar.addActionListener(this);
        vista.btnActualizar.addActionListener(this);
        vista.btnLimpiar.addActionListener(this);
        
        // Agregar listener para seleccionar un bombero desde la tabla
        vista.jtblBomberos.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                modelo.cargarDatosTabla(vista); // Cargar datos seleccionados en el formulario
            }
        });
    }

    // Método para inicializar configuraciones
    private void init() {
        modelo.Mostrar(vista.jtblBomberos); // Mostrar los bomberos en la tabla
    }

    // Método para limpiar los campos del formulario
    private void limpiarCampos() {
        vista.txtNombre.setText("");
        vista.txtEdad.setText("");
        vista.txtPeso.setText("");
        vista.txtCorreo.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Guardar bombero
        if (e.getSource() == vista.btnAgregar) {
            // Obtener datos desde el formulario
            modelo.setNombre_bombero(vista.txtNombre.getText());
            modelo.setEdad_bombero(Integer.parseInt(vista.txtEdad.getText()));
            modelo.setPeso_bombero(Double.parseDouble(vista.txtPeso.getText()));
            modelo.setCorreo_bombero(vista.txtCorreo.getText());

            modelo.Guardar();  // Guardar bombero en la base de datos
            modelo.Mostrar(vista.jtblBomberos);  // Actualizar tabla con los datos guardados
            limpiarCampos();  // Limpiar los campos después de guardar
        }

        // Eliminar bombero
        if (e.getSource() == vista.btnEliminar) {
            modelo.Eliminar(vista.jtblBomberos);  // Eliminar el bombero seleccionado
            modelo.Mostrar(vista.jtblBomberos);  // Actualizar la tabla después de eliminar
            limpiarCampos();  // Limpiar campos después de eliminar
        }

        // Actualizar bombero
        if (e.getSource() == vista.btnActualizar) {
            // Obtener datos desde el formulario
            modelo.setNombre_bombero(vista.txtNombre.getText());
            modelo.setEdad_bombero(Integer.parseInt(vista.txtEdad.getText()));
            modelo.setPeso_bombero(Double.parseDouble(vista.txtPeso.getText()));
            modelo.setCorreo_bombero(vista.txtCorreo.getText());

            modelo.Actualizar(vista.jtblBomberos);  // Actualizar el bombero en la base de datos
            modelo.Mostrar(vista.jtblBomberos);  // Actualizar la tabla con los datos modificados
            limpiarCampos();  // Limpiar campos después de actualizar
        }

        // Limpiar campos manualmente
        if (e.getSource() == vista.btnLimpiar) {
            limpiarCampos();  // Llamar al método de limpiar campos
        }
    }
}
