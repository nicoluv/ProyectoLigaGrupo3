package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import logic.Administracion;
import logic.Equipo;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ListJugadores extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JTable table;
    private ArrayList<String> NombresEquipos = new ArrayList<String>();
    public static JComboBox cbxEquipos;
    public static DefaultTableModel model;
    public static Object[] fila;
    private int index = 0;
    private JButton btnVerJugador;
    private JButton btnEliminar;

    /**
     * Launch the application.
     */
    /**
     * Create the dialog.
     */
    public ListJugadores() throws SQLException {
        setResizable(false);
        setTitle("Lista de Jugadores");

        setBounds(100, 100, 909, 521);
        setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new BorderLayout(0, 0));
        {
            JPanel panel = new JPanel();
            contentPanel.add(panel, BorderLayout.CENTER);
            panel.setLayout(null);

            JScrollPane scrollPane = new JScrollPane();
            scrollPane.setBounds(10, 50, 871, 380);
            panel.add(scrollPane);

            String[] header = {"Codigo", "Nombre", "Posici�n", "Pais de Origen", "Altura"};
            model = new DefaultTableModel();
            model.setColumnIdentifiers(header);
            table = new JTable();
            table.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (table.getSelectedRow() >= 0) {
                        btnVerJugador.setEnabled(true);
                        btnEliminar.setEnabled(true);
                        index = table.getSelectedRow();
                    }
                }
            });
            table.setModel(model);
            scrollPane.setViewportView(table);

            JLabel lblJugadoresPorEquipo = new JLabel("Jugadores por equipo:");
            lblJugadoresPorEquipo.setFont(new Font("Tahoma", Font.PLAIN, 11));
            lblJugadoresPorEquipo.setBounds(10, 20, 156, 14);
            panel.add(lblJugadoresPorEquipo);

            cbxEquipos = new JComboBox(NombresEquipos.toArray());
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                Connection con = DriverManager.getConnection("jdbc:sqlserver://192.168.77.24:1433;database=proyectoLigaBeisbol_grupo3", "jhernandez", "Junior2000");
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM Equipo");
                while (rs.next()) {
                    String fname = rs.getString("nombre_equipo");
                    cbxEquipos.addItem(fname);
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(NewClass.class.getName()).log(Level.SEVERE, null, ex);
            }
            cbxEquipos.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    loadTable(cod_eq(cbxEquipos.getSelectedItem().toString()));
                }
            });
            cbxEquipos.setFont(new Font("Tahoma", Font.PLAIN, 11));
            cbxEquipos.setBounds(124, 14, 156, 25);
            panel.add(cbxEquipos);

            loadTable(cod_eq(cbxEquipos.getSelectedItem().toString()));
        }
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                btnVerJugador = new JButton("Ver Jugador");
                btnVerJugador.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        int p = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());
               
                        VerJugador vj = new VerJugador(p, cbxEquipos.getSelectedIndex());
                        vj.setModal(true);
                        vj.setVisible(true);
                        loadTable(cod_eq(cbxEquipos.getSelectedItem().toString()));
                    }
                });

                btnEliminar = new JButton("Eliminar");
                btnEliminar.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        int input = JOptionPane.showConfirmDialog(null, "�Seguro que desea eliminar el jugador?", "Confirmaci�n", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);

                        if (input == 0) {
                            try {
                                Connection db = DriverManager.getConnection("jdbc:sqlserver://192.168.77.24:1433;database=proyectoLigaBeisbol_grupo3", "jhernandez", "Junior2000");
                                Statement st = db.createStatement();
                                ResultSet rs;

                                JOptionPane.showMessageDialog(null, "El jugador ha sido eliminado.", "Informaci�n", JOptionPane.INFORMATION_MESSAGE);

                                PreparedStatement ts = db.prepareStatement("DELETE FROM Jugador WHERE nombre = ?");
                                ts.setString(1, table.getValueAt(table.getSelectedRow(), 1).toString());
                                ts.executeQuery();
                                //System.out.println(table.getValueAt(table.getSelectedRow(), 1).toString());

                            } catch (SQLException a) {
                                System.out.println("Error " + a.getMessage());
                            }
                            loadTable(cod_eq(cbxEquipos.getSelectedItem().toString()));

                        }
                    }
                });
                btnEliminar.setFont(new Font("Tahoma", Font.PLAIN, 11));
                buttonPane.add(btnEliminar);
                btnVerJugador.setFont(new Font("Tahoma", Font.PLAIN, 11));
                btnEliminar.setEnabled(false);
                btnVerJugador.setEnabled(false);
                btnVerJugador.setActionCommand("OK");
                buttonPane.add(btnVerJugador);
                getRootPane().setDefaultButton(btnVerJugador);
            }
            {
                JButton cancelButton = new JButton("Cancelar");
                cancelButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                    }
                });
                cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
                cancelButton.setActionCommand("Cancel");
                buttonPane.add(cancelButton);
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

    public static void loadTable(int s) {
        model.setRowCount(0);
        fila = new Object[model.getColumnCount()];
        try {
            Connection db = DriverManager.getConnection("jdbc:sqlserver://192.168.77.24:1433;database=proyectoLigaBeisbol_grupo3", "jhernandez", "Junior2000");
            Statement st = db.createStatement();
            ResultSet rs;
            rs = st.executeQuery("SELECT Jugador.codigo_Jugador, Jugador.nombre, Jugador.pais, Jugador.altura, Posicion.descripcion FROM Jugador, Posicion WHERE Jugador.codigo_posc = Posicion.codigo_posc AND Jugador.codigo_equipo = '" + s + "'");
            while (rs.next()) {
                String fcodigo = rs.getString("codigo_Jugador");
                String fname = rs.getString("nombre");
                String fpais = rs.getString("pais");
                int faltura = rs.getInt("altura");
                String fpos = rs.getString("descripcion");
                fila[0] = fcodigo;
                fila[1] = fname;
                fila[2] = fpos;
                fila[3] = fpais;
                fila[4] = faltura;

                model.addRow(fila);
            }
        } catch (SQLException a) {
            System.out.println("Error " + a.getMessage());
        }

    }
}
