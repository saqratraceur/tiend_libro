package ct.tienda_libros.vista;

import ct.tienda_libros.modelo.Libro;
import ct.tienda_libros.servicio.LibroServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

@Component
public class LibroForm extends JFrame {
    LibroServicio libroServicio;
    private JPanel panel;
    private JTable jTblLibros;
    private JTextField txtLibro;
    private JTextField txtAutor;
    private JTextField txtPrecio;
    private JTextField txtExistencias;
    private JButton btnAgregar;
    private JButton btnModificar;
    private JButton btnEliminar;
    private DefaultTableModel tablaModeloLibros;

    @Autowired
    public LibroForm(LibroServicio libroServicio){
        this.libroServicio = libroServicio;
         iniciarForma();
        btnAgregar.addActionListener(e -> agregarLibro());
    }

    public void iniciarForma(){
        setContentPane(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(900,700);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension tamanioPantalla = toolkit.getScreenSize();
        int x = (tamanioPantalla.width - getWidth()/2);
        int y = (tamanioPantalla.height - getHeight() /2);
        setLocation(x, y);

    }

    private void agregarLibro(){
        //Leer los valores del formulario

        if(txtLibro.getText().equals("")){
            mostrarMensaje("Proporcion el nombre de libro");
            txtLibro.requestFocusInWindow();
            return;
        }
        var nombreLibro = txtLibro.getText();
        var autor = txtAutor.getText();
        var precio = Double.parseDouble(txtPrecio.getText());
        var existencias = Integer.parseInt(txtExistencias.getText());
        // Crear el objeto libro
        var libro = new Libro(null, nombreLibro, autor, precio, existencias);
        //libro.setNombreLibro(nombreLibro);
        //libro.setAutor(autor);
        //libro.setPrecio(precio);
        //libro.setExistencias(existencias);
        this.libroServicio.guardarLibro(libro);
        mostrarMensaje("Se agrego el libro");
        //Limpiar el formulario
        limpiarFormulario();
        listarLibros();

    }

    private void limpiarFormulario(){
        txtLibro.setText("");
        txtAutor.setText("");
        txtPrecio.setText("");
        txtExistencias.setText("");


    }

    private void mostrarMensaje(String mensaje){
        JOptionPane.showConfirmDialog(this, mensaje);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        this.tablaModeloLibros = new DefaultTableModel(0, 5);
        String[] cabeceros = {"Id", "Libro", "Autor", "Precio", "Existencias"};
        this.tablaModeloLibros.setColumnIdentifiers(cabeceros);
        // Instanciar el objeto JTable
        this.jTblLibros = new JTable(tablaModeloLibros);

        listarLibros();

    }

    private void listarLibros(){
        //limpiar la tabla
        tablaModeloLibros.setRowCount(0);
        //obtener los libros, por el objeto servicio obtenemos todos los datos de servicios
        var libros = libroServicio.ListarLibros();
        libros.forEach((libro)->{
            Object[] renglonLibro = {
                    libro.getIdLibro(),
                    libro.getNombreLibro(),
                    libro.getAutor(),
                    libro.getPrecio(),
                    libro.getExistencias()
            };
            this.tablaModeloLibros.addRow(renglonLibro);
        });

    }
}
