package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.sql.Connection;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import logic.Administracion;
import logic.Equipo;
import logic.JugCampo;
import logic.Jugador;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import java.awt.Color;
import java.awt.Font;
import java.awt.Label;
import java.awt.Panel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import rsbuttom.RSButtonMetro;
import java.awt.SystemColor;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import javax.swing.ListSelectionModel;
import javax.swing.border.BevelBorder;
import javax.swing.ImageIcon;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class VerEquipo extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JTable table;
    public static DefaultTableModel model;
    public static DefaultTableModel model2;
    private static DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    public static Object[] fila;
    private int index = 0;
    private static int MiEquipo;
    private JLabel lblNombre;
    private JLabel lblManager;
    private JLabel lblEstadio;
    private JLabel lblCampeonatos;
    private JLabel lblJuegosGanados;
    private JLabel lblJuegosPerdidos;
    private JLabel labelJuegosJugados;
    private JLabel lblWR;
    private JLabel lblNombreEquipo;
    private JLabel LogoEquipo;
    private RSButtonMetro btnmtrModificar;
    private RSButtonMetro btnmtrEliminar;
    private RSButtonMetro btnmtrRegistrarLesion;
    private RSButtonMetro btnmtrVerJugador;
    private JTable tablePartidos;
    private NumberFormat formatter = new DecimalFormat(".###");

    public VerEquipo(int e) {
        setResizable(false);
        MiEquipo = e + 1;
        setTitle("Interfaz de Equipo");
        setBounds(100, 100, 909, 504);
        setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new BorderLayout(0, 0));
        {
            JPanel panel = new JPanel();
            contentPanel.add(panel, BorderLayout.CENTER);
            panel.setLayout(null);

            JPanel panel_1 = new JPanel();
            panel_1.setBounds(10, 11, 133, 102);
            panel.add(panel_1);
            panel_1.setLayout(null);

            LogoEquipo = new JLabel("");
            LogoEquipo.setBounds(0, 0, 133, 102);
            panel_1.add(LogoEquipo);

            lblNombreEquipo = new JLabel("");
            lblNombreEquipo.setFont(new Font("Leelawadee", Font.BOLD, 17));
            lblNombreEquipo.setBounds(167, 38, 290, 51);
            panel.add(lblNombreEquipo);

            try {
                Connection db = DriverManager.getConnection("jdbc:sqlserver://192.168.77.24:1433;database=proyectoLigaBeisbol_grupo3", "jhernandez", "Junior2000");
                Statement st = db.createStatement();
                ResultSet rs;
                rs = st.executeQuery("select Equipo.nombre_equipo from Equipo where Equipo.codigo_equipo = '" + MiEquipo + "'");

                while (rs.next()) {

                    lblNombreEquipo.setText(rs.getString("nombre_equipo"));

                }
            } catch (SQLException a) {
                System.out.println("Error " + a.getMessage());
            }

            JPanel panel_2 = new JPanel();
            panel_2.setToolTipText("Seleccione un jugador del roster");
            panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
            panel_2.setBounds(709, 70, 174, 337);
            panel.add(panel_2);
            panel_2.setLayout(null);

            btnmtrModificar = new RSButtonMetro();
            btnmtrModificar.setEnabled(false);
            btnmtrModificar.setIcon(new ImageIcon(VerEquipo.class.getResource("/imgiconos/Modificar Jug.png")));
            btnmtrModificar.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                    if (table.getSelectedRow() >= 0) {
                        RegJugador regj = null;
                        try {
                            regj = new RegJugador(index, MiEquipo, true);
                        } catch (SQLException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        regj.setModal(true);
                        regj.setVisible(true);
                        loadTable();
                    } else {
                        JOptionPane.showMessageDialog(null, "No has seleccionado un jugador del roster.", "Aviso", JOptionPane.WARNING_MESSAGE);
                    }
                }
            });
            btnmtrModificar.setFont(new Font("Dialog", Font.BOLD, 15));
            btnmtrModificar.setBounds(2, 2, 170, 35);
            btnmtrModificar.setText("Modificar Jugador");
            btnmtrModificar.setForeground(Color.GRAY);
            btnmtrModificar.setColorTextNormal(Color.BLACK);
            btnmtrModificar.setColorPressed(Color.DARK_GRAY);
            btnmtrModificar.setColorNormal(SystemColor.menu);
            btnmtrModificar.setColorHover(Color.GRAY);
            btnmtrModificar.setBackground(SystemColor.menu);
            panel_2.add(btnmtrModificar);

            btnmtrEliminar = new RSButtonMetro();
            btnmtrEliminar.setEnabled(false);
            btnmtrEliminar.setIcon(new ImageIcon(VerEquipo.class.getResource("/imgiconos/Eliminar jug.png")));
            btnmtrEliminar.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (table.getSelectedRow() >= 0) {

                        try {
                            Connection db = DriverManager.getConnection("jdbc:sqlserver://192.168.77.24:1433;database=proyectoLigaBeisbol_grupo3", "jhernandez", "Junior2000");
                            Statement st = db.createStatement();
                            ResultSet rs;

                            PreparedStatement ts = db.prepareStatement("DELETE FROM Jugador WHERE nombre = ?");
                            ts.setString(1, table.getValueAt(table.getSelectedRow(), 1).toString());
                            ts.executeQuery();

                            JOptionPane.showMessageDialog(null, "El jugador ha sido eliminado.", "Informaci�n", JOptionPane.INFORMATION_MESSAGE);
                            
                        } catch (SQLException a) {
                            System.out.println("Error " + a.getMessage());
                        }

                        loadTable();
                    } else {
            btnmtrEliminar.setColorNormal(SystemColor.menu);
            btnmtrEliminar.setColorHover(Color.GRAY);
                        JOptionPane.showMessageDialog(null, "No has seleccionado un jugador del roster.", "Aviso", JOptionPane.WARNING_MESSAGE);
                    }
                }
            });
            btnmtrEliminar.setFont(new Font("Dialog", Font.BOLD, 15));
            btnmtrEliminar.setBounds(2, 42, 170, 35);
            btnmtrEliminar.setText("Eliminar Jugador  ");
            btnmtrEliminar.setForeground(Color.GRAY);
            btnmtrEliminar.setColorTextNormal(Color.BLACK);
            btnmtrEliminar.setColorPressed(Color.DARK_GRAY);
            btnmtrEliminar.setBackground(SystemColor.menu);
            panel_2.add(btnmtrEliminar);

            btnmtrRegistrarLesion = new RSButtonMetro();
            btnmtrRegistrarLesion.setEnabled(false);
            btnmtrRegistrarLesion.setIcon(new ImageIcon(VerEquipo.class.getResource("/imgiconos/Lesion jug.png")));
            btnmtrRegistrarLesion.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (table.getSelectedRow() >= 0) {
                        index = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());
                        RegLesion regl = new RegLesion(index, MiEquipo); //OBTENER SELECTED ROW
                        regl.setModal(true);
                        regl.setVisible(true);
                        loadTable();
                    } else {
                        JOptionPane.showMessageDialog(null, "No has seleccionado un jugador del roster.", "Aviso", JOptionPane.WARNING_MESSAGE);
                    }
                }
            });
            btnmtrRegistrarLesion.setText("Registrar Lesion  ");
            btnmtrRegistrarLesion.setForeground(Color.GRAY);
            btnmtrRegistrarLesion.setFont(new Font("Dialog", Font.BOLD, 15));
            btnmtrRegistrarLesion.setColorTextNormal(Color.BLACK);
            btnmtrRegistrarLesion.setColorPressed(Color.DARK_GRAY);
            btnmtrRegistrarLesion.setColorNormal(SystemColor.menu);
            btnmtrRegistrarLesion.setColorHover(Color.GRAY);
            btnmtrRegistrarLesion.setBackground(SystemColor.menu);
            btnmtrRegistrarLesion.setBounds(2, 82, 170, 35);
            panel_2.add(btnmtrRegistrarLesion);

            btnmtrVerJugador = new RSButtonMetro();
            btnmtrVerJugador.setEnabled(false);
            btnmtrVerJugador.setIcon(new ImageIcon(VerEquipo.class.getResource("/imgiconos/users-icon.png")));
            btnmtrVerJugador.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (table.getSelectedRow() >= 0) {
                        VerJugador vj = new VerJugador(index, MiEquipo);
                        vj.setModal(true);
                        vj.setVisible(true);
                        loadTable();
                    } else {
                        JOptionPane.showMessageDialog(null, "No has seleccionado un jugador del roster.", "Aviso", JOptionPane.WARNING_MESSAGE);
                    }
                }
            });
            btnmtrVerJugador.setText("Ver jugador           ");
            btnmtrVerJugador.setForeground(Color.GRAY);
            btnmtrVerJugador.setFont(new Font("Dialog", Font.BOLD, 15));
            btnmtrVerJugador.setColorTextNormal(Color.BLACK);
            btnmtrVerJugador.setColorPressed(Color.DARK_GRAY);
            btnmtrVerJugador.setColorNormal(SystemColor.menu);
            btnmtrVerJugador.setColorHover(Color.GRAY);
            btnmtrVerJugador.setBackground(SystemColor.menu);
            btnmtrVerJugador.setBounds(2, 122, 170, 35);
            panel_2.add(btnmtrVerJugador);

            JPanel panel_3 = new JPanel();
            panel_3.setBounds(10, 138, 667, 270);
            panel.add(panel_3);
            panel_3.setLayout(null);

            JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
            tabbedPane.setBounds(0, 0, 667, 270);
            panel_3.add(tabbedPane);

            JPanel Panelinformacion = new JPanel();
            tabbedPane.addTab("Informacion", null, Panelinformacion, null);
            Panelinformacion.setLayout(null);

            JPanel panel_4 = new JPanel();
            panel_4.setBounds(12, 13, 638, 216);
            Panelinformacion.add(panel_4);
            panel_4.setLayout(null);

            JLabel labelManager = new JLabel("Manager:");
            labelManager.setFont(new Font("Dialog", Font.BOLD, 15));
            labelManager.setBounds(12, 129, 113, 45);
            panel_4.add(labelManager);

            JLabel labelEstadio = new JLabel("Estadio:");
            labelEstadio.setFont(new Font("Dialog", Font.BOLD, 15));
            labelEstadio.setBounds(310, 129, 113, 45);
            panel_4.add(labelEstadio);

            JLabel labelNombre = new JLabel("Nombre:");
            labelNombre.setFont(new Font("Dialog", Font.BOLD, 15));
            labelNombre.setBounds(12, 38, 113, 45);
            panel_4.add(labelNombre);

            Label label = new Label("Estado:");
            label.setFont(new Font("Dialog", Font.BOLD, 15));
            label.setBounds(310, 38, 111, 45);
            panel_4.add(label);

            lblNombre = new JLabel("");
            lblNombre.setFont(new Font("Dialog", Font.BOLD, 15));
            lblNombre.setBounds(115, 38, 213, 45);
            panel_4.add(lblNombre);

            lblManager = new JLabel("");
            lblManager.setFont(new Font("Dialog", Font.BOLD, 15));
            lblManager.setBounds(115, 129, 213, 45);
            panel_4.add(lblManager);

            lblEstadio = new JLabel("");
            lblEstadio.setFont(new Font("Dialog", Font.BOLD, 15));
            lblEstadio.setBounds(427, 129, 191, 45);
            panel_4.add(lblEstadio);

            lblCampeonatos = new JLabel("");
            lblCampeonatos.setFont(new Font("Dialog", Font.BOLD, 15));
            lblCampeonatos.setBounds(427, 38, 99, 45);
            panel_4.add(lblCampeonatos);

            try {
                Connection db = DriverManager.getConnection("jdbc:sqlserver://192.168.77.24:1433;database=proyectoLigaBeisbol_grupo3", "jhernandez", "Junior2000");
                Statement st = db.createStatement();
                ResultSet rs;
                rs = st.executeQuery("select Equipo.nombre_equipo, Equipo.manager, Equipo.estadio,Equipo.estado from Equipo where Equipo.codigo_equipo = '" + MiEquipo + "'");

                while (rs.next()) {

                    lblNombre.setText(rs.getString("nombre_equipo"));
                    lblManager.setText(rs.getString("manager"));
                    lblEstadio.setText(rs.getString("estadio"));
                    lblCampeonatos.setText(rs.getString("estado"));

                }
            } catch (SQLException a) {
                System.out.println("Error " + a.getMessage());
            }

            JPanel PanelRoster = new JPanel();
            tabbedPane.addTab("Roster del Equipo", null, PanelRoster, null);
            PanelRoster.setLayout(new BorderLayout(0, 0));

            JScrollPane scrollPane = new JScrollPane();
            PanelRoster.add(scrollPane, BorderLayout.CENTER);

            String[] header = {"codigo", "Nombre", "Pocision", "Pais de Origen", "Fisico"};
            model = new DefaultTableModel();
            model.setColumnIdentifiers(header);
            table = new JTable();
            table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            table.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (table.getSelectedRow() >= 0) {
                        btnmtrModificar.setEnabled(true);
                        btnmtrEliminar.setEnabled(true);
                        btnmtrRegistrarLesion.setEnabled(true);
                        btnmtrVerJugador.setEnabled(true);
                        index = table.getSelectedRow();
                    }
                }
            });
            table.setModel(model);
            scrollPane.setViewportView(table);

            loadTable();

            JPanel PanelHistorial = new JPanel();
            tabbedPane.addTab("Historial de partidos", null, PanelHistorial, null);
            PanelHistorial.setLayout(new BorderLayout(0, 0));

            JScrollPane scrollPane_1 = new JScrollPane();
            PanelHistorial.add(scrollPane_1, BorderLayout.CENTER);

            String header2[] = {"Local", "Visitante", "Estadio", "Hora", "Fecha"};
            model2 = new DefaultTableModel();
            model2.setColumnIdentifiers(header2);
            tablePartidos = new JTable();
            tablePartidos.setModel(model2);
            scrollPane_1.setViewportView(tablePartidos);
            loadTablePartidos();

            JLabel lblControlRoster = new JLabel("Control Roster");
            lblControlRoster.setFont(new Font("Dialog", Font.BOLD, 15));
            lblControlRoster.setBounds(744, 41, 112, 16);
            panel.add(lblControlRoster);

//            int victorias = Administracion.getInstancia().getMisEquipos().get(MiEquipo).getJuegosGanados();
//            int derrotas = Administracion.getInstancia().getMisEquipos().get(MiEquipo).getJuegosPerdidos();
//
//            if (victorias == 0 && derrotas == 0) {
//                lblWR.setText("0 %");
//            } else if (victorias != 0 && derrotas == 0) {
//                lblWR.setText("100 %");
//            } else if (victorias != 0 && derrotas != 0) {
//                float aux = (100) / (victorias + derrotas);
//                int WR = (int) (victorias * aux);
//                lblWR.setText(String.valueOf(WR + "%"));
//            }

        }
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                JButton cancelButton = new JButton("Cerrar");
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

    public void loadTable() {
        model.setRowCount(0);

        fila = new Object[model.getColumnCount()];

        try {
            Connection db = DriverManager.getConnection("jdbc:sqlserver://192.168.77.24:1433;database=proyectoLigaBeisbol_grupo3", "jhernandez", "Junior2000");
            Statement st = db.createStatement();
            ResultSet rs;
            rs = st.executeQuery("select Jugador.codigo_Jugador, Jugador.nombre, Jugador.codigo_posc, Jugador.pais,Jugador.estado_fisico  from Jugador,Equipo WHERE Jugador.codigo_equipo = Equipo.codigo_equipo and Equipo.codigo_equipo = '" + MiEquipo + "'");
            while (rs.next()) {
                fila[0] = rs.getInt("codigo_Jugador");
                fila[1] = rs.getString("nombre");
                fila[2] = rs.getInt("codigo_posc");
                fila[3] = rs.getString("pais");

                if (rs.getInt("estado_fisico") == 1) {
                    fila[4] = "En forma";

                } else {
                    fila[4] = "Lesionado";
                }
                model.addRow(fila);
            }
        } catch (SQLException a) {
            System.out.println("Error " + a.getMessage());
        }

    }

    public void loadTablePartidos() {
        model2.setRowCount(0);
    

        fila = new Object[model.getColumnCount()];

        try {
            Connection db = DriverManager.getConnection("jdbc:sqlserver://192.168.77.24:1433;database=proyectoLigaBeisbol_grupo3", "jhernandez", "Junior2000");
            Statement st = db.createStatement();
            ResultSet rs;
            rs = st.executeQuery("select Partido.codigo_equipoLocal,Partido.codigo_equipoVisitante,Equipo.estadio,Partido.hora,Partido.fecha_partido,Partido.estado from Partido,Equipo WHERE (Equipo.codigo_equipo = Partido.codigo_equipoLocal or Equipo.codigo_equipo = Partido.codigo_equipoVisitante) and Equipo.codigo_equipo =  '" + MiEquipo + "'");
            while (rs.next()) {
                fila[0] = rs.getInt("codigo_equipoLocal");
                fila[1] = rs.getInt("codigo_equipoVisitante");
                fila[2] = rs.getString("estadio");
                fila[3] = rs.getString("hora");
                fila[4] = rs.getDate("fecha_partido");
  

                model2.addRow(fila);
            }
        } catch (SQLException a) {
            System.out.println("Error " + a.getMessage());
        }


    }

//    public float AVGTotal() {
//        float suma = 0;
//        for (Jugador i : Administracion.getInstancia().getMisEquipos().get(MiEquipo).getMisJugadores()) {
//            if (i instanceof JugCampo) {
//                suma += ((JugCampo) i).getEstad().getAVG();
//            }
//        }
//        return suma;
//    }
}
