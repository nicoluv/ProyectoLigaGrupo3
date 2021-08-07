package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.toedter.calendar.JDateChooser;

import logic.Administracion;
import logic.Equipo;
import logic.Jugador;
import logic.Partido;

import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.border.BevelBorder;

public class RegPartido extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JDateChooser dateChooser;
    private JComboBox cbxHora;
    private JComboBox cbxLocal;
    private JComboBox cbxVisitante;

    public RegPartido() {

        setResizable(false);
        setTitle("Registrar  Partido");
        setBounds(100, 100, 610, 192);
        setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new BorderLayout(0, 0));
        {
            JPanel panel = new JPanel();
            contentPanel.add(panel, BorderLayout.CENTER);
            panel.setLayout(null);

            JLabel lblFechaDelPartido = new JLabel("Fecha del partido:");
            lblFechaDelPartido.setFont(new Font("Tahoma", Font.PLAIN, 11));
            lblFechaDelPartido.setBounds(10, 25, 114, 14);
            panel.add(lblFechaDelPartido);

            dateChooser = new JDateChooser();
            dateChooser.setBounds(134, 25, 134, 20);
            panel.add(dateChooser);

            JLabel lblHoraDelPartido = new JLabel("Hora del partido:");
            lblHoraDelPartido.setFont(new Font("Tahoma", Font.PLAIN, 11));
            lblHoraDelPartido.setBounds(10, 60, 93, 16);
            panel.add(lblHoraDelPartido);

            cbxHora = new JComboBox();
            cbxHora.setModel(new DefaultComboBoxModel(new String[]{"8:00 a.m.", "9:00 a.m.", "10:00 a.m.", "11:00 a.m.", "12:00 p.m.", "1:00 p.m.", "2:00 p.m.", "3:00 p.m.", "4:00 p.m.", "5:00 p.m.", "6:00 p.m.", "7:00 p.m.", "8:00 p.m."}));
            cbxHora.setFont(new Font("Tahoma", Font.PLAIN, 11));
            cbxHora.setBounds(134, 58, 134, 20);
            panel.add(cbxHora);

            JLabel lblEquipoLocal = new JLabel("Equipo local:");
            lblEquipoLocal.setFont(new Font("Tahoma", Font.PLAIN, 11));
            lblEquipoLocal.setBounds(337, 24, 75, 16);
            panel.add(lblEquipoLocal);

            cbxLocal = new JComboBox();
            cbxLocal.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (cbxLocal.getSelectedIndex() != 0) {
                        cbxVisitante.setEnabled(true);
                        cbxVisitante.removeAllItems();
                        try {
                            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                            Connection con = DriverManager.getConnection("jdbc:sqlserver://192.168.77.24:1433;database=proyectoLigaBeisbol_grupo3", "jhernandez", "Junior2000");
                            Statement st = con.createStatement();
                            ResultSet rs = st.executeQuery("SELECT * FROM Equipo");
                            cbxVisitante.addItem("<Seleccionar>");
                            while (rs.next()) {
                                String fname = rs.getString("nombre_equipo");
                                cbxVisitante.addItem(fname);
                            }
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(NewClass.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                    }
                }
            });
            cbxLocal.setFont(new Font("Tahoma", Font.PLAIN, 11));
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                Connection con = DriverManager.getConnection("jdbc:sqlserver://192.168.77.24:1433;database=proyectoLigaBeisbol_grupo3", "jhernandez", "Junior2000");
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM Equipo");
                cbxLocal.addItem("<Seleccionar>");
                while (rs.next()) {
                    String fname = rs.getString("nombre_equipo");
                    cbxLocal.addItem(fname);
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(NewClass.class.getName()).log(Level.SEVERE, null, ex);
            }
            cbxLocal.setBounds(440, 22, 134, 20);
            panel.add(cbxLocal);

            JLabel lblEquipoVisitante = new JLabel("Equipo visitante:");
            lblEquipoVisitante.setFont(new Font("Tahoma", Font.PLAIN, 11));
            lblEquipoVisitante.setBounds(337, 60, 93, 16);
            panel.add(lblEquipoVisitante);

            cbxVisitante = new JComboBox();
            cbxVisitante.addItem("<Seleccionar>");
            cbxVisitante.setEnabled(false);
            cbxVisitante.setFont(new Font("Tahoma", Font.PLAIN, 11));
            cbxVisitante.setBounds(440, 58, 134, 20);
            panel.add(cbxVisitante);
        }
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                JButton btnRegistrar = new JButton("Registrar");
                btnRegistrar.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent arg0) {
                        try {
                            Connection db = DriverManager.getConnection("jdbc:sqlserver://192.168.77.24:1433;database=proyectoLigaBeisbol_grupo3", "jhernandez", "Junior2000");
                            Date date = dateChooser.getDate();
                            long d = date.getTime(); //guardamos en un long el tiempo
                            java.sql.Date fechita = new java.sql.Date(d);// parseamos al formato del sql  
                            String hora, estado;
                            int Visita = cod_eq(cbxVisitante.getSelectedItem().toString());
                            int Local = cod_eq(cbxLocal.getSelectedItem().toString());
                            estado = "Pendiente";
                            //fecha = dateChooser.getDate();
                            hora = cbxHora.getSelectedItem().toString();
                            JOptionPane.showMessageDialog(null, "Se ha creado el partido correctamente", "Informaci�n", JOptionPane.INFORMATION_MESSAGE);
                            PreparedStatement ts = db.prepareStatement("INSERT INTO Partido(codigo_equipoLocal, codigo_equipoVisitante, estado, hora, fecha_partido, carrera_local, carrera_visitante) VALUES (?,?,?,?,?,0,0)");
                            ts.setInt(1, Local);
                            ts.setInt(2, Visita);
                            ts.setString(3, estado);
                            ts.setString(4, hora);
                            ts.setDate(5, fechita);
                            ts.executeQuery();
                        } catch (SQLException a) {
                            System.out.println("Error " + a.getMessage());
                        }
                    }
                });
                btnRegistrar.setFont(new Font("Tahoma", Font.PLAIN, 11));
                btnRegistrar.setActionCommand("OK");
                buttonPane.add(btnRegistrar);
                getRootPane().setDefaultButton(btnRegistrar);
            }
            {
                JButton btnCancelar = new JButton("Cancelar");
                btnCancelar.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                    }
                });
                btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 11));
                btnCancelar.setActionCommand("Cancel");
                buttonPane.add(btnCancelar);
            }
        }

    }

    public int cod_eq(String s) {
        int fcod = 0;
        try {
            Connection db = DriverManager.getConnection("jdbc:sqlserver://192.168.77.24:1433;database=proyectoLigaBeisbol_grupo3", "jhernandez", "Junior2000");
            Statement st = db.createStatement();
            ResultSet rs;
            rs = st.executeQuery("SELECT codigo_equipo FROM Equipo WHERE nombre_equipo = '" + s + "'");
            while (rs.next()) {
                fcod = rs.getInt("codigo_equipo");
            }

        } catch (SQLException a) {
            System.out.println("Error " + a.getMessage());
        }
        return fcod;
    }
}
