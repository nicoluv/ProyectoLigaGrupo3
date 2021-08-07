package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import java.awt.Font;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.DefaultComboBoxModel;
import javax.swing.border.TitledBorder;

import logic.Administracion;
import logic.Lesion;

import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RegLesion extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JTextField txtAtendido;
    private int MiJugador;
    private int MiEquipo;
    private JComboBox cbxTipo;
    private JComboBox jAtendedor;
    private JTextPane txtDescripcion;
    private int nom;

    public RegLesion(int i, int e) {

        MiJugador = i;
        MiEquipo = e;
        setTitle("Registrar Lesion");
        setBounds(100, 100, 614, 255);
        setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new BorderLayout(0, 0));
        {
            JPanel panel = new JPanel();
            panel.setBorder(new TitledBorder(null, "Datos de lesion", TitledBorder.LEADING, TitledBorder.TOP, null, null));
            contentPanel.add(panel, BorderLayout.CENTER);
            panel.setLayout(null);

            JLabel lblTipoDeLesion = new JLabel("Tipo de Lesion:");
            lblTipoDeLesion.setFont(new Font("Tahoma", Font.PLAIN, 11));
            lblTipoDeLesion.setBounds(16, 30, 95, 16);
            panel.add(lblTipoDeLesion);

            cbxTipo = new JComboBox();
            cbxTipo.setFont(new Font("Tahoma", Font.PLAIN, 11));
            cbxTipo.setModel(new DefaultComboBoxModel(new String[]{"<Seleccione>", "Lesion Muscular", "Lesion de Tendon", "Fractura Abierta", "Fractura Cerrada", "Contusion", "Ampolla"}));
            cbxTipo.setBounds(92, 25, 141, 25);
            panel.add(cbxTipo);

            JLabel lblAtendidoPor = new JLabel("Atendido por:");
            lblAtendidoPor.setFont(new Font("Tahoma", Font.PLAIN, 11));
            lblAtendidoPor.setBounds(245, 30, 75, 16);
            panel.add(lblAtendidoPor);

            JComboBox jAtendedor = new JComboBox();
            jAtendedor.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try {
                        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                        Connection con = DriverManager.getConnection("jdbc:sqlserver://192.168.77.24:1433;database=proyectoLigaBeisbol_grupo3", "jhernandez", "Junior2000");
                        Statement st = con.createStatement();
                        ResultSet rs = st.executeQuery("SELECT * FROM Medico WHERE codigo_med = '" + jAtendedor.getSelectedIndex() + "'");
                        while (rs.next()) {
                            String fespecialidad = rs.getString("especialidad");
                            txtAtendido.setText(fespecialidad);
                        }
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(NewClass.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    nom = jAtendedor.getSelectedIndex();
                    
                }
            });
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                Connection con = DriverManager.getConnection("jdbc:sqlserver://192.168.77.24:1433;database=proyectoLigaBeisbol_grupo3", "jhernandez", "Junior2000");
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM Medico");
                jAtendedor.addItem("<Seleccionar>");
                while (rs.next()) {
                    String fname = rs.getString("nombre_medico");
                    String fapellido = rs.getString("apellido_medico");
                    jAtendedor.addItem("" + fname + " " + fapellido);
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(NewClass.class.getName()).log(Level.SEVERE, null, ex);
            }
            jAtendedor.setBounds(322, 27, 115, 22);
            panel.add(jAtendedor);

            txtAtendido = new JTextField();
            txtAtendido.setEditable(false);
            txtAtendido.setBounds(447, 28, 131, 21);
            panel.add(txtAtendido);
            txtAtendido.setColumns(10);

            JLabel lblDescripcion = new JLabel("Descripcion:");
            lblDescripcion.setFont(new Font("Tahoma", Font.PLAIN, 11));
            lblDescripcion.setBounds(16, 68, 81, 16);
            panel.add(lblDescripcion);

            JPanel panel_1 = new JPanel();
            panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
            panel_1.setBounds(16, 96, 562, 62);
            panel.add(panel_1);
            panel_1.setLayout(new BorderLayout(0, 0));

            txtDescripcion = new JTextPane();
            panel_1.add(txtDescripcion, BorderLayout.CENTER);

        }
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                JButton btnRegistrar = new JButton("Registrar");
                btnRegistrar.setFont(new Font("Tahoma", Font.PLAIN, 11));
                btnRegistrar.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String tipo = cbxTipo.getSelectedItem().toString();
                        //String nombre = jAtendedor.getSelectedItem().toString();
                        String descrip = txtDescripcion.getText();
                        Calendar cal = Calendar.getInstance();

                        int reposo = 0;
                        if (cbxTipo.getSelectedIndex() == 1) {
                            reposo = 15;
                        } else if (cbxTipo.getSelectedIndex() == 2) {
                            reposo = 20;
                        } else if (cbxTipo.getSelectedIndex() == 3) {
                            reposo = 45;
                        } else if (cbxTipo.getSelectedIndex() == 4) {
                            reposo = 90;
                        } else if (cbxTipo.getSelectedIndex() == 5) {
                            reposo = 80;
                        } else if (cbxTipo.getSelectedIndex() == 6) {
                            reposo = 18;
                        }
                        if (!tipo.isEmpty() && nom!=0 && !descrip.isEmpty()) {

                            try {
                                Connection db = DriverManager.getConnection("jdbc:sqlserver://192.168.77.24:1433;database=proyectoLigaBeisbol_grupo3", "jhernandez", "Junior2000");
                                Statement st = db.createStatement();
                                PreparedStatement ts = db.prepareStatement("INSERT INTO Lesion(descripcion,codigo_medico,fecha_lesion,tipo_lesion, codigo_jugador_lesion,reposo     )  " + "VALUES ('" + descrip + "','" + nom + "','" + new java.sql.Date(cal.getTimeInMillis()) + "','" + tipo + "'," + MiJugador + "," + reposo + ")");
                                ts.executeUpdate();

                                PreparedStatement ts2 = db.prepareStatement("UPDATE Jugador SET estado_fisico = 0 WHERE Jugador.codigo_Jugador = " + MiJugador);
                                ts2.executeUpdate();

                            } catch (SQLException a) {
                                System.out.println("Error " + a.getMessage());
                            }

                            JOptionPane.showMessageDialog(null, "Se registr� la lesi�n con �xito.", "Informaci�n", JOptionPane.INFORMATION_MESSAGE);
                            dispose();
                        } else {
                            JOptionPane.showMessageDialog(null, "Revise los campos.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                        }

                    }
                });
                btnRegistrar.setActionCommand("OK");
                buttonPane.add(btnRegistrar);
                getRootPane().setDefaultButton(btnRegistrar);
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
