package Modelo;

import Vista.FrmBombero;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Bombero {
    String id_bombero;
    String nombre_bombero;
    int edad_bombero;
    double peso_bombero;
    String correo_bombero;

    // Getters and Setters
    public String getId_bombero() {
        return id_bombero;
    }

    public void setId_bombero(String id_bombero) {
        this.id_bombero = id_bombero;
    }

    public String getNombre_bombero() {
        return nombre_bombero;
    }

    public void setNombre_bombero(String nombre_bombero) {
        this.nombre_bombero = nombre_bombero;
    }

    public int getEdad_bombero() {
        return edad_bombero;
    }

    public void setEdad_bombero(int edad_bombero) {
        this.edad_bombero = edad_bombero;
    }

    public double getPeso_bombero() {
        return peso_bombero;
    }

    public void setPeso_bombero(double peso_bombero) {
        this.peso_bombero = peso_bombero;
    }

    public String getCorreo_bombero() {
        return correo_bombero;
    }

    public void setCorreo_bombero(String correo_bombero) {
        this.correo_bombero = correo_bombero;
    }

    // Método para guardar bombero (Insertar)
    public void Guardar() {
        Connection conexion = Conexion.getConexion();

        try {
            PreparedStatement addBombero = conexion.prepareStatement(
                "INSERT INTO tbBombero (nombre_bombero, edad_bombero, peso_bombero, correo_bombero) VALUES (?, ?, ?, ?)");

            addBombero.setString(1, getNombre_bombero());
            addBombero.setInt(2, getEdad_bombero());
            addBombero.setDouble(3, getPeso_bombero());
            addBombero.setString(4, getCorreo_bombero());

            addBombero.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Error en el modelo: método Guardar " + ex);
        }
    }

    // Método para actualizar bombero
    public void Actualizar(JTable tabla) {
        Connection conexion = Conexion.getConexion();

        try {
            PreparedStatement updateBombero = conexion.prepareStatement(
                "UPDATE tbBombero SET nombre_bombero = ?, edad_bombero = ?, peso_bombero = ?, correo_bombero = ? WHERE id_bombero = ?");

            updateBombero.setString(1, getNombre_bombero());
            updateBombero.setInt(2, getEdad_bombero());
            updateBombero.setDouble(3, getPeso_bombero());
            updateBombero.setString(4, getCorreo_bombero());
            updateBombero.setString(5, getId_bombero());

            updateBombero.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Error en el modelo: método Actualizar " + ex);
        }
    }

    // Método para limpiar los campos en la vista
    public void limpiar(FrmBombero vista) {
        vista.txtNombre.setText("");
        vista.txtEdad.setText("");
        vista.txtPeso.setText("");
        vista.txtCorreo.setText("");
    }

    // Método para eliminar bombero 
    public void Eliminar(JTable tabla) {
        Connection conexion = Conexion.getConexion();
        try {
            PreparedStatement deleteBombero = conexion.prepareStatement(
                "DELETE FROM tbBombero WHERE id_bombero = ?");

            deleteBombero.setString(1, getId_bombero());

            deleteBombero.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Error en el modelo: método Eliminar " + ex);
        }
    }

    // Método para mostrar todos los bomberos en la tabla
    public void Mostrar(JTable tabla) {
        Connection conexion = Conexion.getConexion();
        DefaultTableModel modeloBomberos = new DefaultTableModel();
        modeloBomberos.setColumnIdentifiers(new Object[]{"id_bombero", "nombre_bombero", "edad_bombero", "peso_bombero", "correo_bombero"});
        try {
            Statement statement = conexion.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM tbBombero");
            while (rs.next()) {
                modeloBomberos.addRow(new Object[]{
                    rs.getString("id_bombero"),
                    rs.getString("nombre_bombero"),
                    rs.getInt("edad_bombero"),
                    rs.getDouble("peso_bombero"),
                    rs.getString("correo_bombero")
                });
            }
            tabla.setModel(modeloBomberos);
        } catch (Exception e) {
            System.out.println("Error en el modelo: método Mostrar " + e);
        }
    }

    // Método para cargar datos de la tabla en la vista
    public void cargarDatosTabla(FrmBombero vista) {
        int filaSeleccionada = vista.jtblBomberos.getSelectedRow();
        
        if (filaSeleccionada != -1) {
            String id_bombero = vista.jtblBomberos.getValueAt(filaSeleccionada, 0).toString();            
            String nombre_bombero = vista.jtblBomberos.getValueAt(filaSeleccionada, 1).toString();
            String edad_bombero = vista.jtblBomberos.getValueAt(filaSeleccionada, 2).toString();
            String peso_bombero = vista.jtblBomberos.getValueAt(filaSeleccionada, 3).toString();
            String correo_bombero = vista.jtblBomberos.getValueAt(filaSeleccionada, 4).toString();

            setId_bombero(id_bombero);
                        
            vista.txtNombre.setText(nombre_bombero);
            vista.txtEdad.setText(edad_bombero);
            vista.txtPeso.setText(peso_bombero);
            vista.txtCorreo.setText(correo_bombero);
        }
    }
}
