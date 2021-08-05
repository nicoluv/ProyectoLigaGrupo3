package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import logic.Administracion;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Font;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.border.BevelBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static visual.ListJugadores.fila;
import static visual.ListJugadores.model;

public class ListPartidos extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JTable table;
    public static DefaultTableModel model;
    public static Object[] fila;
    private static DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    private JButton btnEliminar;
    private JButton btnJugarPartido;
    private int index;

    public ListPartidos() {
        setTitle("Lista de partidos");
        setBounds(100, 100, 955, 518);
        setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new BorderLayout(0, 0));
        {
            JScrollPane scrollPane = new JScrollPane();
            contentPanel.add(scrollPane, BorderLayout.CENTER);
            {

                String[] header = {"Cod","Local", "Visitante", "Estadio", "Hora", "Fecha", "Estado", "Marcador", "Ganador"};
                model = new DefaultTableModel();
                model.setColumnIdentifiers(header);
                table = new JTable();
                table.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent arg0) {
                        btnEliminar.setEnabled(true);
                        btnJugarPartido.setEnabled(true);
                        index = table.getSelectedRow();
                    }
                });
                table.setModel(model);
                scrollPane.setViewportView(table);
                loadTable();
            }
        }
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                btnEliminar = new JButton("Eliminar");
                btnEliminar.setEnabled(false);
                btnEliminar.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        int input = JOptionPane.showConfirmDialog(null, "�Seguro que desea eliminar el jugador?", "Confirmaci�n", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);

                        if (input == 0) {
                            try {
                                Connection db = DriverManager.getConnection("jdbc:sqlserver://192.168.77.24:1433;database=proyectoLigaBeisbol_grupo3", "jhernandez", "Junior2000");
                                Statement st = db.createStatement();
                                ResultSet rs;

                                JOptionPane.showMessageDialog(null, "El jugador ha sido eliminado.", "Informaci�n", JOptionPane.INFORMATION_MESSAGE);

                                PreparedStatement ts = db.prepareStatement("DELETE FROM Partido WHERE codigo_partido = ?");
                                ts.setInt(1, Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString()));
                                ts.executeQuery();
                                //System.out.println(table.getValueAt(table.getSelectedRow(), 1).toString());

                            } catch (SQLException a) {
                                System.out.println("Error " + a.getMessage());
                            }
                            loadTable();

                        }
                    }
                });
                {
                    btnJugarPartido = new JButton("Jugar partido");
                    btnJugarPartido.setEnabled(false);
                    btnJugarPartido.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            Simulacion sim = new Simulacion(cod_eq(table.getValueAt(table.getSelectedRow(), 1).toString()), cod_eq(table.getValueAt(table.getSelectedRow(), 2).toString()), index);
                                        sim.setModal(true);
                                        sim.setVisible(true);
                                        Administracion.getInstancia().getMisPartidos().get(index).setEstado(false);
                                        loadTable();
                                   
                        }
                    });
                    btnJugarPartido.setFont(new Font("Tahoma", Font.PLAIN, 11));
                    buttonPane.add(btnJugarPartido);
                }
                btnEliminar.setFont(new Font("Tahoma", Font.PLAIN, 11));
                btnEliminar.setActionCommand("OK");
                buttonPane.add(btnEliminar);
                getRootPane().setDefaultButton(btnEliminar);
            }
            {
                JButton btnCancelar = new JButton("Cancelar");
                btnCancelar.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent arg0) {
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
    
    public static String estadi(String s) {
        String fcod = null;
        try {
            Connection db = DriverManager.getConnection("jdbc:sqlserver://192.168.77.24:1433;database=proyectoLigaBeisbol_grupo3", "jhernandez", "Junior2000");
            Statement st = db.createStatement();
            ResultSet rs;
            rs = st.executeQuery("SELECT estadio FROM Equipo WHERE nombre_equipo = '" + s + "'");
            while (rs.next()) {
                fcod = rs.getString("estadio");
            }

        } catch (SQLException a) {
            System.out.println("Error " + a.getMessage());
        }
        return fcod;
    }
    

        public static void loadTable() {
        model.setRowCount(0);
        fila = new Object[model.getColumnCount()];

        try {
            Connection db = DriverManager.getConnection("jdbc:sqlserver://192.168.77.24:1433;database=proyectoLigaBeisbol_grupo3", "jhernandez", "Junior2000");
            Statement st = db.createStatement();
            ResultSet rs;
            rs = st.executeQuery("SELECT Partido.*, Equipolo.nombre_equipo as EL, Equipovi.nombre_equipo as EV  FROM Partido, Equipo as Equipolo, Equipo as Equipovi WHERE Equipolo.codigo_equipo = Partido.codigo_equipoLocal AND Equipovi.codigo_equipo = Partido.codigo_equipoVisitante");
            while (rs.next()) {
                int fcodigo = rs.getInt("codigo_partido");
                String fname1 = rs.getString("EL");
                String fname2 = rs.getString("EV");
                String festadio = estadi(fname1);
                String fhora= rs.getString("hora");
                String festado = rs.getString("estado");
                String ffecha = rs.getDate("fecha_partido").toString();
                fila[0] = fcodigo;
                fila[1] = fname1;
                fila[2] = fname2;
                fila[3] = festadio;
                fila[4] = fhora;
                fila[5] = ffecha;
                fila[6] = festado;
                fila[7] = "0 - 0";
                fila[8] = "Por Definir";

                model.addRow(fila);
            }
        } catch (SQLException a) {
            System.out.println("Error " + a.getMessage());
        }

    }

}
