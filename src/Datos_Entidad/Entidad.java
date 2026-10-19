package Datos_Entidad;

import java.sql.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Entidad extends JFrame implements ActionListener {

    JLabel Titulo, Id, Nombre, Identidad, Cp, Telefono;
    JTextField txtId, txtNombre, txtIdentidad, txtCp, txtTelefono;
    JButton btnCrear, btnConsultar, btnActualizar, btnEliminar;

    JMenuBar menuBar;
    JMenu menuBC, Archivo;
    JMenuItem Crear, Consultar, Actualizar, Eliminar;

    Entidad() {
        this.setBounds(150, 50, 800, 600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);

        Titulo = new JLabel("Datos de la entidad");
        Titulo.setBounds(250, 10, 200, 30);
        this.add(Titulo);

        menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);

        Archivo = new JMenu("Archivo");
        menuBar.add(Archivo);

        menuBC = new JMenu("Base de datos");
        menuBar.add(menuBC);

        Crear = new JMenuItem("Crear");
        menuBC.add(Crear);

        Consultar = new JMenuItem("Consultar");
        menuBC.add(Consultar);

        Actualizar = new JMenuItem("Actualizar");
        menuBC.add(Actualizar);

        Eliminar = new JMenuItem("eliminar");
        menuBC.add(Eliminar);

        Id = new JLabel("ID:");
        Id.setBounds(150, 50, 100, 25);
        this.add(Id);
        txtId = new JTextField();
        txtId.setBounds(250, 50, 200, 25);
        this.add(txtId);

        Nombre = new JLabel("Nombre:");
        Nombre.setBounds(150, 85, 100, 25);
        this.add(Nombre);
        txtNombre = new JTextField();
        txtNombre.setBounds(250, 85, 200, 25);
        this.add(txtNombre);

        Identidad = new JLabel("Identidad:");
        Identidad.setBounds(150, 120, 100, 25);
        this.add(Identidad);
        txtIdentidad = new JTextField();
        txtIdentidad.setBounds(250, 120, 200, 25);
        this.add(txtIdentidad);

        Cp = new JLabel("C.P.:");
        Cp.setBounds(150, 155, 100, 25);
        this.add(Cp);
        txtCp = new JTextField();
        txtCp.setBounds(250, 155, 200, 25);
        this.add(txtCp);

        Telefono = new JLabel("Teléfono:");
        Telefono.setBounds(150, 190, 100, 25);
        this.add(Telefono);
        txtTelefono = new JTextField();
        txtTelefono.setBounds(250, 190, 200, 25);
        this.add(txtTelefono);

        // jButton xd :v
        this.btnCrear = new JButton("crear");
        this.btnCrear.setBounds(95,250, 150, 30);
        this.add(this.btnCrear);
        this.btnCrear.addActionListener(this);

        this.btnConsultar = new JButton("Consultar");
        this.btnConsultar.setBounds(250, 250, 150, 30);
        this.add(this.btnConsultar);
        this.btnConsultar.addActionListener(this);

        this.btnActualizar = new JButton("Actualizar");
        this.btnActualizar.setBounds(405, 250, 150, 30);
        this.add(this.btnActualizar);
        this.btnActualizar.addActionListener(this);

        this.btnEliminar = new JButton("eliminar");
        this.btnEliminar.setBounds(560, 250, 150, 30);
        this.add(this.btnEliminar);
        this.btnEliminar.addActionListener(this);

        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == this.btnCrear) {
            System.out.println(this.txtNombre.getText());
            this.btnCrear();
        }

        if (e.getSource() == this.btnConsultar) {
            System.out.println("Consultar");
            this.btnConsultar();
        }

        if (e.getSource() == this.btnActualizar) {
            System.out.println("Actualizar");
            this.btnActualizar();
        }

        if (e.getSource() == this.btnEliminar) {
            System.out.println("Eliminar");
            this.btnEliminar();
        }

    }

    public void btnCrear() {

        try {
            Connection cnx;
            PreparedStatement ps;

            cnx = DriverManager.getConnection("jdbc:mysql://localhost/polizas", "root", "");
            ps = cnx.prepareStatement("INSERT INTO Persona (id,nombre, identidad, cp, telefono) VALUES (?, ?, ?, ?, ?)");
            ps.setInt(1, Integer.parseInt(this.txtId.getText()));
            ps.setString(2, this.txtNombre.getText());
            ps.setString(3, this.txtIdentidad.getText());
            ps.setString(4, this.txtCp.getText());
            ps.setString(5, this.txtTelefono.getText());
            ps.execute();
        } catch (SQLException ex) {
            System.getLogger(Entidad.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }

    public void btnConsultar() {
        try {
            Connection cnx;
            PreparedStatement ps;
            ResultSet rs;

            cnx = DriverManager.getConnection("jdbc:mysql://localhost/polizas", "root", "");
            ps = cnx.prepareStatement("select * from persona");
            rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + ("    ") + "Nombre :" + rs.getString("nombre") + ("  ") + "Identidad :" + rs.getString("Identidad") + ("  ") + "Cp:" + rs.getString("Cp") + ("  ") + "Telefono:" + rs.getString("Telefono"));
            }

            System.out.println("Conecion exitosa");
            cnx.close();

        } catch (SQLException ex) {
            System.out.println("Error" + ex.getSQLState() + ":" + ex.getMessage());
        }
    }

    public void btnActualizar() {
        
         try {
            Connection cnx;
            PreparedStatement ps;

            cnx = DriverManager.getConnection("jdbc:mysql://localhost/polizas", "root", "");
            ps = cnx.prepareStatement("update persona set nombre = ?,Identidad = ?,Cp = ? , Telefono = ? where id = ?");
            ps.setString(1, this.txtNombre.getText());
            ps.setString(2, this.txtIdentidad.getText());
            ps.setString(3, this.txtCp.getText());
            ps.setString(4, this.txtTelefono.getText());
             ps.setInt(5, Integer.parseInt(this.txtId.getText()));
            ps.executeUpdate();

        } catch (SQLException ex) {
            System.getLogger(Entidad.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
    

    public void btnEliminar() {
         try {

            Connection cnx;
            PreparedStatement ps;

            cnx = DriverManager.getConnection("jdbc:mysql://localhost/polizas", "root", "");
            ps = cnx.prepareStatement("DELETE FROM persona WHERE id = ?");
            ps.setInt(1, Integer.parseInt(this.txtId.getText()));

            ps.executeUpdate();
            cnx.close();
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        Entidad Poliza = new Entidad();
    }
}
