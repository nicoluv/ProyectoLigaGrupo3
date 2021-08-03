package visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Login extends JDialog {

    private JPanel contentPane;
    private JTextField txtUsuario;
    private JPasswordField passwordField;

    public Login() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        setTitle("Iniciar sesi\u00F3n");
        setResizable(false);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 319, 422);
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.CENTER);
        panel.setLayout(null);

        JLabel lblUsuario = new JLabel("Usuario:");
        lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lblUsuario.setBounds(48, 184, 46, 14);
        panel.add(lblUsuario);

        txtUsuario = new JTextField();
        txtUsuario.setBounds(120, 181, 138, 20);
        panel.add(txtUsuario);
        txtUsuario.setColumns(10);

        JLabel lblContrasea = new JLabel("Contrase\u00F1a:");
        lblContrasea.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lblContrasea.setBounds(48, 209, 60, 14);
        panel.add(lblContrasea);

        JButton btnEntrar = new JButton("Entrar");
        btnEntrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Connection db = DriverManager.getConnection("jdbc:sqlserver://192.168.77.24:1433;database=proyectoLigaBeisbol_grupo3", "jhernandez", "Junior2000");
                    String user, password;
                    user = txtUsuario.getText();
                    password = String.valueOf(passwordField.getPassword());
                    Statement st = db.createStatement();
                    ResultSet rs = st.executeQuery("SELECT * FROM Usuario WHERE nombre_usuario='" + user + "' AND contrasenia='" + password + "'");
                    if (user.isEmpty() || password.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Ha dejado campos vac�os.", "Aviso", JOptionPane.WARNING_MESSAGE);
                    } else if (rs.isBeforeFirst()) {
                        JOptionPane.showMessageDialog(null, "Ha iniciado sesi�n correctamente", "Informaci�n", JOptionPane.INFORMATION_MESSAGE);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "El usuario o contrase�a es incorrecto", "Aviso", JOptionPane.WARNING_MESSAGE);
                        JOptionPane.showMessageDialog(null, "User: " + user + "Pass: " + password, "Aviso", JOptionPane.WARNING_MESSAGE);
                    }
                } catch (SQLException a) {
                    System.out.println("Error " + a.getMessage());
                }
            }
        });
        btnEntrar.setFont(new Font("Tahoma", Font.PLAIN, 11));
        btnEntrar.setBounds(170, 252, 89, 23);
        panel.add(btnEntrar);

        JButton btnRegistrar = new JButton("Registrar");
        btnRegistrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Connection db = DriverManager.getConnection("jdbc:sqlserver://192.168.77.24:1433;database=proyectoLigaBeisbol_grupo3", "jhernandez", "Junior2000");
                    String user, password;
                    user = txtUsuario.getText();
                    password = String.valueOf(passwordField.getPassword());
                    Statement st = db.createStatement();
                    ResultSet rs = st.executeQuery("SELECT * FROM Usuario WHERE nombre_usuario='" + user + "' AND contrasenia='" + password + "'");
                    if (user.isEmpty() || password.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Ha dejado campos vac�os.", "Aviso", JOptionPane.WARNING_MESSAGE);
                    } else if (rs.isBeforeFirst() == false) {
                        JOptionPane.showMessageDialog(null, "Se ha creado el usuario correctamente", "Informaci�n", JOptionPane.INFORMATION_MESSAGE);
                        rs = st.executeQuery("INSERT INTO Usuario(nombre_usuario, contrasenia)  " + "VALUES ('" + user + "', '" + password + "')");

                    } else {
                        JOptionPane.showMessageDialog(null, "El usuario ya existe", "Aviso", JOptionPane.WARNING_MESSAGE);
                        JOptionPane.showMessageDialog(null, "User: " + user + "Pass: " + password, "Aviso", JOptionPane.WARNING_MESSAGE);
                    }
                } catch (SQLException a) {
                    System.out.println("Error " + a.getMessage());
                }
            }
        });
        btnRegistrar.setFont(new Font("Tahoma", Font.PLAIN, 11));
        btnRegistrar.setBounds(45, 252, 89, 23);
        panel.add(btnRegistrar);

        passwordField = new JPasswordField();
        passwordField.setBounds(120, 205, 138, 20);
        panel.add(passwordField);

        JLabel lblFoto = new JLabel("");
        lblFoto.setIcon(new ImageIcon(Login.class.getResource("/imgiconos/User-blue-icon.png")));
        lblFoto.setBounds(88, 12, 125, 125);
        panel.add(lblFoto);
    }
}
