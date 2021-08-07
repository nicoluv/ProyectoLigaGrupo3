package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import logic.Administracion;
import logic.Equipo;

import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.border.BevelBorder;

public class RegEquipo extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JTextField txtNombre;
    private JTextField txtProvincia;
    private JTextField txtEstadio;
    private JTextField txtManager;
    private BufferedImage imagen;


    public RegEquipo() {
        setResizable(false);
        setTitle("Registrar Equipo");
        setBounds(100, 100, 466, 288);
        setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new BorderLayout(0, 0));
        {
            JPanel panel = new JPanel();
            panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Datos del equipo", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
            contentPanel.add(panel, BorderLayout.CENTER);
            panel.setLayout(null);

            JLabel lblNombre = new JLabel("Nombre:");
            lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 11));
            lblNombre.setBounds(10, 45, 46, 14);
            panel.add(lblNombre);

            txtNombre = new JTextField();
            txtNombre.setBounds(66, 42, 179, 20);
            panel.add(txtNombre);
            txtNombre.setColumns(10);

            JLabel lblProvincia = new JLabel("Estado:");
            lblProvincia.setFont(new Font("Tahoma", Font.PLAIN, 11));
            lblProvincia.setBounds(10, 110, 62, 14);
            panel.add(lblProvincia);

            txtProvincia = new JTextField();
            txtProvincia.setBounds(66, 107, 179, 20);
            panel.add(txtProvincia);
            txtProvincia.setColumns(10);

            JLabel lblEstadio = new JLabel("Estadio:");
            lblEstadio.setFont(new Font("Tahoma", Font.PLAIN, 11));
            lblEstadio.setBounds(10, 78, 46, 14);
            panel.add(lblEstadio);

            txtEstadio = new JTextField();
            txtEstadio.setBounds(66, 75, 179, 20);
            panel.add(txtEstadio);
            txtEstadio.setColumns(10);

            JLabel lblManager = new JLabel("Manager:");
            lblManager.setFont(new Font("Tahoma", Font.PLAIN, 11));
            lblManager.setBounds(10, 142, 46, 14);
            panel.add(lblManager);

            txtManager = new JTextField();
            txtManager.setBounds(66, 139, 179, 20);
            panel.add(txtManager);
            txtManager.setColumns(10);

            JPanel panel_imagen = new JPanel();
            panel_imagen.setLayout(null);
            panel_imagen.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
            panel_imagen.setBounds(272, 21, 156, 138);
            panel.add(panel_imagen);

            JLabel lblImagen = new JLabel("");
            lblImagen.setBounds(0, 0, 156, 138);
            panel_imagen.add(lblImagen);

            JButton btnCargarImagen = new JButton("Cargar Imagen");
            btnCargarImagen.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                    JFileChooser fc = new JFileChooser();
                    fc.setDialogTitle("Buscar imagen");

                    if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                        String nomImagen;
                        File arch = new File(fc.getSelectedFile().toString());
                        rsscalelabel.RSScaleLabel.setScaleLabel(lblImagen, fc.getSelectedFile().toString());
                        try {
                            imagen = ImageIO.read(arch);
                            nomImagen = "imgequipos/" + txtNombre.getText() + ".png";
                            ImageIO.write(imagen, "png", new File(nomImagen));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                }
            });
            btnCargarImagen.setFont(new Font("Tahoma", Font.PLAIN, 11));
            btnCargarImagen.setBounds(299, 170, 106, 23);
            panel.add(btnCargarImagen);
        }
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                JButton okButton = new JButton("Aceptar");
                okButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
                okButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String nombre = txtNombre.getText();
                            String estadio = txtEstadio.getText();
                            String estado = txtProvincia.getText();
                            String manager = txtManager.getText();
                            ByteArrayOutputStream baos = new ByteArrayOutputStream();
                            ImageIO.write(imagen, "jpg", baos);
                            baos.flush();
                            byte[] immAsBytes = baos.toByteArray();
                            baos.close();
                        try {
                            Connection db = DriverManager.getConnection("jdbc:sqlserver://192.168.77.24:1433;database=proyectoLigaBeisbol_grupo3", "jhernandez", "Junior2000");
                            if (!nombre.equalsIgnoreCase("") && !estadio.equalsIgnoreCase("") && !estadio.equalsIgnoreCase("")) {
                                JOptionPane.showMessageDialog(null, "Se ha creado el equipo correctamente", "Informaci�n", JOptionPane.INFORMATION_MESSAGE);
                                PreparedStatement ts = db.prepareStatement("INSERT INTO Equipo(nombre_equipo, estadio, estado, manager, imagen_equipo) VALUES (?,?,?,?,?)");
                                ts.setString(1, nombre);
                                ts.setString(2, estadio);
                                ts.setString(3, estado);
                                ts.setString(4, manager);
                                ByteArrayInputStream bais = new ByteArrayInputStream(immAsBytes);
                                ts.setBinaryStream(5, bais, immAsBytes.length);
                                ts.executeQuery();
                            } else {
                                JOptionPane.showMessageDialog(null, "Por favor revisar los datos", "Aviso", JOptionPane.WARNING_MESSAGE);
                            }
                        } catch (SQLException a) {
                            System.out.println("Error " + a.getMessage());
                        }
                    }
                });
                okButton.setActionCommand("OK");
                buttonPane.add(okButton);
                getRootPane().setDefaultButton(okButton);
            }
            {
                JButton cancelButton = new JButton("Cancelar");
                cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
                cancelButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                    }
                });
                cancelButton.setActionCommand("Cancel");
                buttonPane.add(cancelButton);
            }
        }
    }
}
