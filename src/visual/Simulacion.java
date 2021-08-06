package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logic.Administracion;
import logic.JugCampo;
import logic.Jugador;
import logic.Pitcher;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import static visual.ListJugadores.fila;
import static visual.ListJugadores.model;

public class Simulacion extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private static JTextField txtIning1Visitante;
    private static JTextField txtIning2Visitante;
    private static JTextField txtIning3Visitante;
    private static JTextField txtIning4Visitante;
    private static JTextField txtIning5Visitante;
    private static JTextField txtIning6Visitante;
    private static JTextField txtIning7Visitante;
    private static JTextField txtIning8Visitante;
    private static JTextField txtIning9Visitante;
    private static JTextField txtIning1Local;
    private static JTextField txtIning2Local;
    private static JTextField txtIning3Local;
    private static JTextField txtIning4Local;
    private static JTextField txtIning5Local;
    private static JTextField txtIning6Local;
    private static JTextField txtIning7Local;
    private static JTextField txtIning8Local;
    private static JTextField txtIning9Local;
    private JLabel label;
    private static JTextField txtTotalCarrerasLocal;
    private static JTextField txtTotalHitsLocal;
    private static JTextField txtTotalErroresLocal;
    private static JTextField txtTotalCarrerasVisitante;
    private static JTextField txtTotalHitsVisitante;
    private static JTextField txtTotalErroresVisitante;
    private JLabel lblCHE;
    private JPanel PanelCampoLocal;
    private JPanel PanelCampoVisitante;
    private JPanel PanelVisitante;
    private static JTable TableCampoLocal;
    private static JTable TableCampoVisitante;
    private static Object[] fila;
    public static DefaultTableModel modelCampoLocal;
    public static DefaultTableModel modelCampoVisitante;
    private static int Local;
    private static int Visitante;
    private static int EntradaAux = 1;
    private static int index;
    private JButton btnHit;
    private JButton btnCarrera;
    private JButton btnHomerun;
    private JButton btnError;
    private JPanel PanelPitcherLocal;
    private JPanel PanelPitcherVisitante;
    private JScrollPane scrollPane_3;
    private JScrollPane scrollPane_2;
    private static JTable TablePitcherLocal;
    private static JTable TablePitcherVisitante;
    public static DefaultTableModel modelPitcherLocal;
    public static DefaultTableModel modelPitcherVisitante;
    private JLabel NombreEquipoLocal;
    private JLabel NombreEquipoVisitante;
    private JLabel FlechaDerecha;
    private JLabel FlechaIzquierda;
    private JLabel labelEntradaActual;
    private JLabel LabelLogoLocal;
    private JLabel labelLogoVisitante;

    public static int Hits = 0;
    public static int CarrerasTotalesLocal = 0;
    public static int CarrerasTotalesVisitante = 0;
    public static int ErroresTotalLocal = 0;
    public static int ErroresTotalVisitante = 0;
    public static int HitsTotalLocales = 0;
    public static int HitsTotalVisitantes = 0;
    public static int Errores = 0;
    public static int Carreras = 0;

    private JButton btnCambiarPitcherVisitante;
    private JButton btnCambiarPitcherLocal;
    private static int NuevoPitcher;
    private static int NuevoPitcherVis;
    private static int MiPartido;

    public Simulacion(int L, int V, int G) {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Hits = 0;
                CarrerasTotalesLocal = 0;
                CarrerasTotalesVisitante = 0;
                ErroresTotalLocal = 0;
                ErroresTotalVisitante = 0;
                HitsTotalLocales = 0;
                HitsTotalVisitantes = 0;
                Errores = 0;
                Carreras = 0;
                EntradaAux = 1;
            }
        });
        setTitle("Simulacion");
        Local = L;
        Visitante = V;
        MiPartido = G;
        NuevoPitcher = Administracion.getInstancia().findPitcher(Local);
        NuevoPitcherVis = Administracion.getInstancia().findPitcher(Visitante);
        setBounds(100, 100, 883, 769);
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
            panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
            panel_1.setBounds(12, 510, 833, 162);
            panel.add(panel_1);
            panel_1.setLayout(null);

            JPanel panel_2 = new JPanel();
            panel_2.setBorder(new LineBorder(Color.LIGHT_GRAY));
            panel_2.setBounds(12, 14, 657, 60);
            panel_1.add(panel_2);
            panel_2.setLayout(null);

            JLabel lblLocal = new JLabel("Local");
            lblLocal.setFont(new Font("Dialog", Font.BOLD, 18));
            lblLocal.setBounds(12, 12, 74, 36);
            panel_2.add(lblLocal);

            JPanel panel_5 = new JPanel();
            panel_5.setLayout(null);
            panel_5.setBounds(115, 12, 530, 36);
            panel_2.add(panel_5);

            txtIning1Local = new JTextField();
            txtIning1Local.setFont(new Font("Dialog", Font.PLAIN, 14));
            txtIning1Local.setText("0");
            txtIning1Local.setHorizontalAlignment(SwingConstants.CENTER);
            txtIning1Local.setBackground(Color.WHITE);
            txtIning1Local.setEditable(false);
            txtIning1Local.setColumns(10);
            txtIning1Local.setBounds(20, 0, 36, 36);
            panel_5.add(txtIning1Local);

            txtIning2Local = new JTextField();
            txtIning2Local.setFont(new Font("Dialog", Font.PLAIN, 14));
            txtIning2Local.setHorizontalAlignment(SwingConstants.CENTER);
            txtIning2Local.setText("0");
            txtIning2Local.setBackground(Color.WHITE);
            txtIning2Local.setEditable(false);
            txtIning2Local.setColumns(10);
            txtIning2Local.setBounds(76, 0, 36, 36);
            panel_5.add(txtIning2Local);

            txtIning3Local = new JTextField();
            txtIning3Local.setFont(new Font("Dialog", Font.PLAIN, 14));
            txtIning3Local.setHorizontalAlignment(SwingConstants.CENTER);
            txtIning3Local.setText("0");
            txtIning3Local.setBackground(Color.WHITE);
            txtIning3Local.setEditable(false);
            txtIning3Local.setColumns(10);
            txtIning3Local.setBounds(132, 0, 36, 36);
            panel_5.add(txtIning3Local);

            txtIning4Local = new JTextField();
            txtIning4Local.setFont(new Font("Dialog", Font.PLAIN, 14));
            txtIning4Local.setHorizontalAlignment(SwingConstants.CENTER);
            txtIning4Local.setText("0");
            txtIning4Local.setBackground(Color.WHITE);
            txtIning4Local.setEditable(false);
            txtIning4Local.setColumns(10);
            txtIning4Local.setBounds(188, 0, 36, 36);
            panel_5.add(txtIning4Local);

            txtIning5Local = new JTextField();
            txtIning5Local.setFont(new Font("Dialog", Font.PLAIN, 14));
            txtIning5Local.setHorizontalAlignment(SwingConstants.CENTER);
            txtIning5Local.setText("0");
            txtIning5Local.setBackground(Color.WHITE);
            txtIning5Local.setEditable(false);
            txtIning5Local.setColumns(10);
            txtIning5Local.setBounds(244, 0, 36, 36);
            panel_5.add(txtIning5Local);

            txtIning6Local = new JTextField();
            txtIning6Local.setFont(new Font("Dialog", Font.PLAIN, 14));
            txtIning6Local.setHorizontalAlignment(SwingConstants.CENTER);
            txtIning6Local.setText("0");
            txtIning6Local.setBackground(Color.WHITE);
            txtIning6Local.setEditable(false);
            txtIning6Local.setColumns(10);
            txtIning6Local.setBounds(300, 0, 36, 36);
            panel_5.add(txtIning6Local);

            txtIning7Local = new JTextField();
            txtIning7Local.setFont(new Font("Dialog", Font.PLAIN, 14));
            txtIning7Local.setHorizontalAlignment(SwingConstants.CENTER);
            txtIning7Local.setText("0");
            txtIning7Local.setBackground(Color.WHITE);
            txtIning7Local.setEditable(false);
            txtIning7Local.setColumns(10);
            txtIning7Local.setBounds(356, 0, 36, 36);
            panel_5.add(txtIning7Local);

            txtIning8Local = new JTextField();
            txtIning8Local.setFont(new Font("Dialog", Font.PLAIN, 14));
            txtIning8Local.setHorizontalAlignment(SwingConstants.CENTER);
            txtIning8Local.setText("0");
            txtIning8Local.setBackground(Color.WHITE);
            txtIning8Local.setEditable(false);
            txtIning8Local.setColumns(10);
            txtIning8Local.setBounds(412, 0, 36, 36);
            panel_5.add(txtIning8Local);

            txtIning9Local = new JTextField();
            txtIning9Local.setFont(new Font("Dialog", Font.PLAIN, 14));
            txtIning9Local.setHorizontalAlignment(SwingConstants.CENTER);
            txtIning9Local.setText("0");
            txtIning9Local.setBackground(Color.WHITE);
            txtIning9Local.setEditable(false);
            txtIning9Local.setColumns(10);
            txtIning9Local.setBounds(468, 0, 36, 36);
            panel_5.add(txtIning9Local);
            {
                JPanel panel_3 = new JPanel();
                panel_3.setBorder(new LineBorder(Color.LIGHT_GRAY));
                panel_3.setBounds(12, 88, 657, 60);
                panel_1.add(panel_3);
                panel_3.setLayout(null);

                JPanel panel_4 = new JPanel();
                panel_4.setBounds(115, 12, 530, 36);
                panel_3.add(panel_4);
                panel_4.setLayout(null);

                txtIning1Visitante = new JTextField();
                txtIning1Visitante.setFont(new Font("Dialog", Font.PLAIN, 14));
                txtIning1Visitante.setHorizontalAlignment(SwingConstants.CENTER);
                txtIning1Visitante.setText("0");
                txtIning1Visitante.setBackground(Color.WHITE);
                txtIning1Visitante.setEditable(false);
                txtIning1Visitante.setColumns(10);
                txtIning1Visitante.setBounds(20, 0, 36, 36);
                panel_4.add(txtIning1Visitante);

                txtIning2Visitante = new JTextField();
                txtIning2Visitante.setFont(new Font("Dialog", Font.PLAIN, 14));
                txtIning2Visitante.setHorizontalAlignment(SwingConstants.CENTER);
                txtIning2Visitante.setText("0");
                txtIning2Visitante.setBackground(Color.WHITE);
                txtIning2Visitante.setEditable(false);
                txtIning2Visitante.setColumns(10);
                txtIning2Visitante.setBounds(76, 0, 36, 36);
                panel_4.add(txtIning2Visitante);

                txtIning3Visitante = new JTextField();
                txtIning3Visitante.setFont(new Font("Dialog", Font.PLAIN, 14));
                txtIning3Visitante.setHorizontalAlignment(SwingConstants.CENTER);
                txtIning3Visitante.setText("0");
                txtIning3Visitante.setBackground(Color.WHITE);
                txtIning3Visitante.setEditable(false);
                txtIning3Visitante.setColumns(10);
                txtIning3Visitante.setBounds(132, 0, 36, 36);
                panel_4.add(txtIning3Visitante);

                txtIning4Visitante = new JTextField();
                txtIning4Visitante.setFont(new Font("Dialog", Font.PLAIN, 14));
                txtIning4Visitante.setHorizontalAlignment(SwingConstants.CENTER);
                txtIning4Visitante.setText("0");
                txtIning4Visitante.setBackground(Color.WHITE);
                txtIning4Visitante.setEditable(false);
                txtIning4Visitante.setColumns(10);
                txtIning4Visitante.setBounds(188, 0, 36, 36);
                panel_4.add(txtIning4Visitante);

                txtIning5Visitante = new JTextField();
                txtIning5Visitante.setFont(new Font("Dialog", Font.PLAIN, 14));
                txtIning5Visitante.setHorizontalAlignment(SwingConstants.CENTER);
                txtIning5Visitante.setText("0");
                txtIning5Visitante.setBackground(Color.WHITE);
                txtIning5Visitante.setEditable(false);
                txtIning5Visitante.setColumns(10);
                txtIning5Visitante.setBounds(244, 0, 36, 36);
                panel_4.add(txtIning5Visitante);

                txtIning6Visitante = new JTextField();
                txtIning6Visitante.setFont(new Font("Dialog", Font.PLAIN, 14));
                txtIning6Visitante.setHorizontalAlignment(SwingConstants.CENTER);
                txtIning6Visitante.setText("0");
                txtIning6Visitante.setBackground(Color.WHITE);
                txtIning6Visitante.setEditable(false);
                txtIning6Visitante.setColumns(10);
                txtIning6Visitante.setBounds(300, 0, 36, 36);
                panel_4.add(txtIning6Visitante);

                txtIning7Visitante = new JTextField();
                txtIning7Visitante.setFont(new Font("Dialog", Font.PLAIN, 14));
                txtIning7Visitante.setHorizontalAlignment(SwingConstants.CENTER);
                txtIning7Visitante.setText("0");
                txtIning7Visitante.setBackground(Color.WHITE);
                txtIning7Visitante.setEditable(false);
                txtIning7Visitante.setColumns(10);
                txtIning7Visitante.setBounds(356, 0, 36, 36);
                panel_4.add(txtIning7Visitante);

                txtIning8Visitante = new JTextField();
                txtIning8Visitante.setFont(new Font("Dialog", Font.PLAIN, 14));
                txtIning8Visitante.setHorizontalAlignment(SwingConstants.CENTER);
                txtIning8Visitante.setText("0");
                txtIning8Visitante.setBackground(Color.WHITE);
                txtIning8Visitante.setEditable(false);
                txtIning8Visitante.setColumns(10);
                txtIning8Visitante.setBounds(412, 0, 36, 36);
                panel_4.add(txtIning8Visitante);

                txtIning9Visitante = new JTextField();
                txtIning9Visitante.setFont(new Font("Dialog", Font.PLAIN, 14));
                txtIning9Visitante.setHorizontalAlignment(SwingConstants.CENTER);
                txtIning9Visitante.setText("0");
                txtIning9Visitante.setBackground(Color.WHITE);
                txtIning9Visitante.setEditable(false);
                txtIning9Visitante.setColumns(10);
                txtIning9Visitante.setBounds(468, 0, 36, 36);
                panel_4.add(txtIning9Visitante);

                JLabel lblVisitante = new JLabel("Visitante");
                lblVisitante.setFont(new Font("Dialog", Font.BOLD, 18));
                lblVisitante.setBounds(12, 12, 85, 36);
                panel_3.add(lblVisitante);
            }
            {
                JPanel panel_3 = new JPanel();
                panel_3.setBorder(new LineBorder(Color.LIGHT_GRAY));
                panel_3.setBounds(681, 14, 140, 136);
                panel_1.add(panel_3);
                panel_3.setLayout(null);

                txtTotalCarrerasLocal = new JTextField();
                txtTotalCarrerasLocal.setFont(new Font("Dialog", Font.PLAIN, 14));
                txtTotalCarrerasLocal.setHorizontalAlignment(SwingConstants.CENTER);
                txtTotalCarrerasLocal.setText("0");
                txtTotalCarrerasLocal.setBackground(Color.WHITE);
                txtTotalCarrerasLocal.setEditable(false);
                txtTotalCarrerasLocal.setColumns(10);
                txtTotalCarrerasLocal.setBounds(8, 12, 36, 36);
                panel_3.add(txtTotalCarrerasLocal);

                txtTotalHitsLocal = new JTextField();
                txtTotalHitsLocal.setFont(new Font("Dialog", Font.PLAIN, 14));
                txtTotalHitsLocal.setHorizontalAlignment(SwingConstants.CENTER);
                txtTotalHitsLocal.setText("0");
                txtTotalHitsLocal.setBackground(Color.WHITE);
                txtTotalHitsLocal.setEditable(false);
                txtTotalHitsLocal.setColumns(10);
                txtTotalHitsLocal.setBounds(52, 12, 36, 36);
                panel_3.add(txtTotalHitsLocal);

                txtTotalErroresLocal = new JTextField();
                txtTotalErroresLocal.setFont(new Font("Dialog", Font.PLAIN, 14));
                txtTotalErroresLocal.setHorizontalAlignment(SwingConstants.CENTER);
                txtTotalErroresLocal.setText("0");
                txtTotalErroresLocal.setBackground(Color.WHITE);
                txtTotalErroresLocal.setEditable(false);
                txtTotalErroresLocal.setColumns(10);
                txtTotalErroresLocal.setBounds(96, 12, 36, 36);
                panel_3.add(txtTotalErroresLocal);

                txtTotalCarrerasVisitante = new JTextField();
                txtTotalCarrerasVisitante.setFont(new Font("Dialog", Font.PLAIN, 14));
                txtTotalCarrerasVisitante.setHorizontalAlignment(SwingConstants.CENTER);
                txtTotalCarrerasVisitante.setText("0");
                txtTotalCarrerasVisitante.setBackground(Color.WHITE);
                txtTotalCarrerasVisitante.setEditable(false);
                txtTotalCarrerasVisitante.setColumns(10);
                txtTotalCarrerasVisitante.setBounds(8, 85, 36, 36);
                panel_3.add(txtTotalCarrerasVisitante);

                txtTotalHitsVisitante = new JTextField();
                txtTotalHitsVisitante.setFont(new Font("Dialog", Font.PLAIN, 14));
                txtTotalHitsVisitante.setHorizontalAlignment(SwingConstants.CENTER);
                txtTotalHitsVisitante.setText("0");
                txtTotalHitsVisitante.setBackground(Color.WHITE);
                txtTotalHitsVisitante.setEditable(false);
                txtTotalHitsVisitante.setColumns(10);
                txtTotalHitsVisitante.setBounds(52, 85, 36, 36);
                panel_3.add(txtTotalHitsVisitante);

                txtTotalErroresVisitante = new JTextField();
                txtTotalErroresVisitante.setFont(new Font("Dialog", Font.PLAIN, 14));
                txtTotalErroresVisitante.setHorizontalAlignment(SwingConstants.CENTER);
                txtTotalErroresVisitante.setText("0");
                txtTotalErroresVisitante.setBackground(Color.WHITE);
                txtTotalErroresVisitante.setEditable(false);
                txtTotalErroresVisitante.setColumns(10);
                txtTotalErroresVisitante.setBounds(96, 85, 36, 36);
                panel_3.add(txtTotalErroresVisitante);
            }

            PanelCampoLocal = new JPanel();
            PanelCampoLocal.setBounds(12, 100, 225, 322);
            panel.add(PanelCampoLocal);
            PanelCampoLocal.setLayout(new BorderLayout(0, 0));
            PanelCampoLocal.setVisible(false);

            JScrollPane scrollPane = new JScrollPane();
            PanelCampoLocal.add(scrollPane, BorderLayout.CENTER);

            ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            String[] header = {"Nombre", "Carreras", "Errores"};

            modelCampoLocal = new DefaultTableModel();
            modelCampoLocal.setColumnIdentifiers(header);
            TableCampoLocal = new JTable();
            TableCampoLocal.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (TableCampoLocal.getSelectedRow() >= 0) {
                        btnCarrera.setEnabled(true);
                        btnError.setEnabled(true);
                        btnHit.setEnabled(true);
                        btnHomerun.setEnabled(true);
                        index = TableCampoLocal.getSelectedRow();
                    } else {
                        btnCarrera.setEnabled(false);
                        btnError.setEnabled(false);
                        btnHit.setEnabled(false);
                        btnHomerun.setEnabled(false);
                    }
                }
            });
            scrollPane.setViewportView(TableCampoLocal);
            TableCampoLocal.setModel(modelCampoLocal);

            PanelCampoVisitante = new JPanel();
            PanelCampoVisitante.setBounds(620, 100, 225, 322);
            panel.add(PanelCampoVisitante);
            PanelCampoVisitante.setLayout(new BorderLayout(0, 0));

            JScrollPane scrollPane_1 = new JScrollPane();
            PanelCampoVisitante.add(scrollPane_1, BorderLayout.CENTER);

            modelCampoVisitante = new DefaultTableModel();
            modelCampoVisitante.setColumnIdentifiers(header);
            TableCampoVisitante = new JTable();
            TableCampoVisitante.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (TableCampoVisitante.getSelectedRow() >= 0) {
                        btnCarrera.setEnabled(true);
                        btnError.setEnabled(true);
                        btnHit.setEnabled(true);
                        btnHomerun.setEnabled(true);
                        index = TableCampoVisitante.getSelectedRow();
                    } else {
                        btnCarrera.setEnabled(false);
                        btnError.setEnabled(false);
                        btnHit.setEnabled(false);
                        btnHomerun.setEnabled(false);
                    }
                }
            });
            scrollPane_1.setViewportView(TableCampoVisitante);
            TableCampoVisitante.setModel(modelCampoVisitante);

            PanelVisitante = new JPanel();
            PanelVisitante.setBorder(new TitledBorder(null, "Panel de control", TitledBorder.LEADING, TitledBorder.TOP, null, null));
            PanelVisitante.setBounds(285, 108, 285, 112);
            panel.add(PanelVisitante);
            PanelVisitante.setLayout(null);

            btnHit = new JButton("1 Hit");
            btnHit.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (TableCampoLocal.getSelectedRow() >= 0 || TableCampoVisitante.getSelectedRow() >= 0) {
                        try {
                            ActualizarHits();
                        } catch (SQLException ex) {
                            Logger.getLogger(Simulacion.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        try {
                            ActualizarHitsPitcher();
                        } catch (SQLException ex) {
                            Logger.getLogger(Simulacion.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        try {
                            ActualizarAB();
                        } catch (SQLException ex) {
                            Logger.getLogger(Simulacion.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        if (EntradaAux % 2 == 0) {
                            HitsTotalLocales++;
                            LoadCampoLocal();
                            LoadPitcherVisitante();
                        } else {
                            HitsTotalVisitantes++;
                            LoadCampoVisitante();
                            LoadPitcherLocal();
                        }
                        ActualizarTablero();
                    } else {
                        JOptionPane.showMessageDialog(null, "No ha seleccionado un jugador.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            });
            btnHit.setEnabled(false);
            btnHit.setBounds(29, 20, 98, 26);
            PanelVisitante.add(btnHit);

            btnCarrera = new JButton("1 Carrera");
            btnCarrera.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (TableCampoLocal.getSelectedRow() >= 0 || TableCampoVisitante.getSelectedRow() >= 0) {
                        try {
                            Carreras++;
                            ActualizarCarreras();
                            ActualizarCarrerasPitcher();
                            ActualizarAB();
                            ActualizarCarrerasLimpias();
                            if (EntradaAux % 2 == 0) {
                                HitsTotalLocales++;
                                CarrerasTotalesLocal++;
                                LoadCampoLocal();
                                LoadPitcherVisitante();
                            } else {
                                HitsTotalVisitantes++;
                                CarrerasTotalesVisitante++;
                                LoadCampoVisitante();
                                LoadPitcherLocal();
                            }
                            ActualizarTablero();
                        } catch (SQLException ex) {
                            Logger.getLogger(Simulacion.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "No ha seleccionado un jugador.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            });
            btnCarrera.setEnabled(false);
            btnCarrera.setBounds(29, 66, 98, 26);
            PanelVisitante.add(btnCarrera);

            btnHomerun = new JButton("1 Homerun");
            btnHomerun.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (TableCampoLocal.getSelectedRow() >= 0 || TableCampoVisitante.getSelectedRow() >= 0) {
                        try {
                            Carreras++;
                            ActualizarJonrones();
                            ActualizarCarreras();
                            ActualizarCarrerasPitcher();
                            ActualizarJonronesPitcher();
                            ActualizarAB();
                            ActualizarCarrerasLimpias();
                            if (EntradaAux % 2 == 0) {
                                HitsTotalLocales++;
                                CarrerasTotalesLocal++;
                                LoadCampoLocal();
                                LoadPitcherVisitante();
                            } else {
                                HitsTotalVisitantes++;
                                CarrerasTotalesVisitante++;
                                LoadCampoVisitante();
                                LoadPitcherLocal();
                            }
                            ActualizarTablero();
                        } catch (SQLException ex) {
                            Logger.getLogger(Simulacion.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "No has seleccionado un jugador.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            });
            btnHomerun.setEnabled(false);
            btnHomerun.setBounds(156, 20, 98, 26);
            PanelVisitante.add(btnHomerun);

            btnError = new JButton("1 Error");
            btnError.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (TableCampoLocal.getSelectedRow() >= 0 || TableCampoVisitante.getSelectedRow() >= 0) {
                        try {
                            ActualizarErrores();
                            if (EntradaAux % 2 == 0) {
                                ErroresTotalLocal++;
                                LoadCampoLocal();
                                LoadPitcherVisitante();
                            } else {
                                ErroresTotalVisitante++;
                                LoadCampoVisitante();
                                LoadPitcherLocal();
                            }
                            ActualizarTablero();
                        } catch (SQLException ex) {
                            Logger.getLogger(Simulacion.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "No has seleccionado un jugador.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                    }
                }

            });
            btnError.setEnabled(false);
            btnError.setBounds(156, 66, 98, 26);
            PanelVisitante.add(btnError);

            JButton btnCambioDeTurno = new JButton("Cambio De Turno");
            btnCambioDeTurno.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    Carreras = 0;
                    ActualizarEntradasPitcher();
                    if (EntradaAux < 18) {
                        if (EntradaAux % 2 == 0) {
                            PanelCampoLocal.setVisible(false);
                            PanelPitcherLocal.setVisible(true);
                            PanelCampoVisitante.setVisible(true);
                            PanelPitcherVisitante.setVisible(false);
                            btnCambiarPitcherLocal.setVisible(true);
                            btnCambiarPitcherVisitante.setVisible(false);
                            EntradaAux++;
                            LoadCampoLocal();
                            LoadCampoVisitante();
                            LoadPitcherLocal();
                            LoadPitcherVisitante();
                            FlechaDerecha.setVisible(true);
                            FlechaIzquierda.setVisible(false);
                            ActualizarTablero();
                        } else if (EntradaAux % 2 != 0) {
                            PanelCampoLocal.setVisible(true);
                            PanelPitcherLocal.setVisible(false);
                            PanelCampoVisitante.setVisible(false);
                            PanelPitcherVisitante.setVisible(true);
                            btnCambiarPitcherLocal.setVisible(false);
                            btnCambiarPitcherVisitante.setVisible(true);
                            EntradaAux++;
                            LoadCampoLocal();
                            LoadCampoVisitante();
                            LoadPitcherLocal();
                            LoadPitcherVisitante();
                            FlechaDerecha.setVisible(false);
                            FlechaIzquierda.setVisible(true);
                            ActualizarTablero();
                        }
                    } else if (EntradaAux == 18) {
                        try {
                            Connection db = DriverManager.getConnection("jdbc:sqlserver://192.168.77.24:1433;database=proyectoLigaBeisbol_grupo3", "jhernandez", "Junior2000");
                            if (Integer.parseInt(modelCampoLocal.getValueAt(1, 1).toString()) > Integer.parseInt(modelCampoVisitante.getValueAt(1, 1).toString()) || Integer.parseInt(modelPitcherLocal.getValueAt(1, 1).toString()) > Integer.parseInt(modelPitcherVisitante.getValueAt(1, 1).toString())) {
                                JOptionPane.showMessageDialog(null, "El partido ha terminado ha ganado el equipo local!");
                                PreparedStatement sts = db.prepareStatement("UPDATE Partido SET estado = finalizado WHERE codigo_partido = ? ");
                                sts.setInt(1, MiPartido);
                                sts.executeUpdate();
                                dispose();
                                
                            } else if (Integer.parseInt(modelCampoLocal.getValueAt(1, 1).toString()) < Integer.parseInt(modelCampoVisitante.getValueAt(1, 1).toString()) || Integer.parseInt(modelPitcherLocal.getValueAt(1, 1).toString()) < Integer.parseInt(modelPitcherVisitante.getValueAt(1, 1).toString())) {
                                JOptionPane.showMessageDialog(null, "El partido ha terminado, ha ganado el equipo visitante!");
                                PreparedStatement sts = db.prepareStatement("UPDATE Partido SET estado = finalizado WHERE codigo_partido = ?");
                                sts.setInt(1, MiPartido);
                                sts.executeUpdate();
                                dispose();
                            } else if (Integer.parseInt(modelCampoLocal.getValueAt(1, 1).toString()) == Integer.parseInt(modelCampoVisitante.getValueAt(1, 1).toString()) || Integer.parseInt(modelPitcherLocal.getValueAt(1, 1).toString()) == Integer.parseInt(modelPitcherVisitante.getValueAt(1, 1).toString())) {
                                JOptionPane.showMessageDialog(null, "El partido ha terminado empate!");
                                PreparedStatement sts = db.prepareStatement("UPDATE Partido SET estado = finalizado WHERE codigo_partido = ?");
                                sts.setInt(1, MiPartido);
                                sts.executeUpdate();
                                dispose();
                            }
                        } catch (SQLException ex) {
                            Logger.getLogger(Simulacion.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            });
            btnCambioDeTurno.setBounds(359, 358, 137, 26);
            panel.add(btnCambioDeTurno);

            NombreEquipoLocal = new JLabel("");
            NombreEquipoLocal.setHorizontalAlignment(SwingConstants.CENTER);
            NombreEquipoLocal.setFont(new Font("Dialog", Font.BOLD, 18));
            NombreEquipoLocal.setBounds(12, 62, 225, 26);
            panel.add(NombreEquipoLocal);

            NombreEquipoVisitante = new JLabel("");
            NombreEquipoVisitante.setHorizontalAlignment(SwingConstants.CENTER);
            NombreEquipoVisitante.setFont(new Font("Dialog", Font.BOLD, 18));
            NombreEquipoVisitante.setBounds(620, 62, 225, 26);
            panel.add(NombreEquipoVisitante);

            PanelPitcherLocal = new JPanel();
            PanelPitcherLocal.setBounds(12, 100, 225, 322);
            panel.add(PanelPitcherLocal);
            PanelPitcherLocal.setLayout(new BorderLayout(0, 0));

            scrollPane_2 = new JScrollPane();
            PanelPitcherLocal.add(scrollPane_2, BorderLayout.CENTER);

            String[] header2 = {"Nombre", "Carreras", "Jonrones"};
            modelPitcherLocal = new DefaultTableModel();
            modelPitcherLocal.setColumnIdentifiers(header2);
            TablePitcherLocal = new JTable();
            TablePitcherLocal.setRowSelectionAllowed(false);
            scrollPane_2.setViewportView(TablePitcherLocal);
            TablePitcherLocal.setModel(modelPitcherLocal);

            PanelPitcherVisitante = new JPanel();
            PanelPitcherVisitante.setBounds(620, 100, 225, 322);
            panel.add(PanelPitcherVisitante);
            PanelPitcherVisitante.setLayout(new BorderLayout(0, 0));
            PanelPitcherVisitante.setVisible(false);

            scrollPane_3 = new JScrollPane();
            PanelPitcherVisitante.add(scrollPane_3, BorderLayout.CENTER);

            modelPitcherVisitante = new DefaultTableModel();
            modelPitcherVisitante.setColumnIdentifiers(header2);
            TablePitcherVisitante = new JTable();
            TablePitcherVisitante.setRowSelectionAllowed(false);
            scrollPane_3.setViewportView(TablePitcherVisitante);
            TablePitcherVisitante.setModel(modelPitcherVisitante);

            FlechaIzquierda = new JLabel("\r\n");
            FlechaIzquierda.setIcon(new ImageIcon(Simulacion.class.getResource("/imgiconos/flechaizq.png")));
            FlechaIzquierda.setBackground(Color.WHITE);
            FlechaIzquierda.setBounds(255, 246, 64, 64);
            panel.add(FlechaIzquierda);
            FlechaIzquierda.setVisible(false);

            FlechaDerecha = new JLabel("");
            FlechaDerecha.setIcon(new ImageIcon(Simulacion.class.getResource("/imgiconos/flechaDere.png")));
            FlechaDerecha.setBounds(538, 246, 64, 64);
            panel.add(FlechaDerecha);

            JLabel lblNewLabel = new JLabel("Al Bate");
            lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 15));
            lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
            lblNewLabel.setBounds(373, 246, 103, 16);
            panel.add(lblNewLabel);

            labelEntradaActual = new JLabel("");
            labelEntradaActual.setHorizontalAlignment(SwingConstants.CENTER);
            labelEntradaActual.setFont(new Font("Dialog", Font.BOLD, 15));
            labelEntradaActual.setBounds(331, 284, 195, 26);
            panel.add(labelEntradaActual);

            btnCambiarPitcherLocal = new JButton("Cambiar Pitcher");
            btnCambiarPitcherLocal.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    CambiarPitcher CP = new CambiarPitcher(Local);
                    CP.setModal(true);
                    CP.setVisible(true);
                    NuevoPitcher = CambiarPitcher.retorno();
                    LoadPitcherL(NuevoPitcher);
                }
            });
            btnCambiarPitcherLocal.setBounds(68, 434, 126, 26);
            panel.add(btnCambiarPitcherLocal);
            btnCambiarPitcherLocal.setVisible(true);

            btnCambiarPitcherVisitante = new JButton("Cambiar Pitcher");
            btnCambiarPitcherVisitante.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    CambiarPitcher CP = new CambiarPitcher(Visitante);
                    CP.setModal(true);
                    CP.setVisible(true);
                    NuevoPitcherVis = CambiarPitcher.retorno();
                    LoadPitcherV(NuevoPitcherVis);
                }
            });
            btnCambiarPitcherVisitante.setBounds(679, 434, 126, 26);
            panel.add(btnCambiarPitcherVisitante);

            label = new JLabel("    1          2          3        4         5          6         7         8         9");
            label.setBounds(148, 472, 506, 26);
            panel.add(label);
            label.setFont(new Font("Dialog", Font.BOLD, 18));

            lblCHE = new JLabel("   C       H      E");
            lblCHE.setBounds(696, 472, 137, 26);
            panel.add(lblCHE);
            lblCHE.setFont(new Font("Dialog", Font.BOLD, 18));

            LabelLogoLocal = new JLabel("");
            LabelLogoLocal.setBounds(206, 12, 87, 76);
            panel.add(LabelLogoLocal);

            labelLogoVisitante = new JLabel("");
            labelLogoVisitante.setBounds(567, 12, 87, 76);
            panel.add(labelLogoVisitante);

            JLabel lblNewLabel_1 = new JLabel("Local");
            lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 18));
            lblNewLabel_1.setBounds(97, 12, 64, 26);
            panel.add(lblNewLabel_1);

            JLabel lblVisitante_1 = new JLabel("Visitante");
            lblVisitante_1.setFont(new Font("Dialog", Font.BOLD, 18));
            lblVisitante_1.setBounds(691, 12, 87, 26);
            panel.add(lblVisitante_1);
            btnCambiarPitcherVisitante.setVisible(false);

        }
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                JButton cancelButton = new JButton("Cancelar ");
                cancelButton.setActionCommand("Cancel");
                buttonPane.add(cancelButton);
            }
        }

        try {
            Connection db = DriverManager.getConnection("jdbc:sqlserver://192.168.77.24:1433;database=proyectoLigaBeisbol_grupo3", "jhernandez", "Junior2000");
            Statement st = db.createStatement();
            ResultSet rs;
            rs = st.executeQuery("SELECT Equipolo.nombre_equipo as EL, Equipovi.nombre_equipo as EV  FROM Equipo as Equipolo, Equipo as Equipovi WHERE Equipolo.codigo_equipo = " + Local + "AND Equipovi.codigo_equipo = " + Visitante + "");
            while (rs.next()) {
                String fname1 = rs.getString("EL");
                String fname2 = rs.getString("EV");
                NombreEquipoLocal.setText(fname1);
                NombreEquipoVisitante.setText(fname2);
            }
        } catch (SQLException a) {
            System.out.println("Error " + a.getMessage());
        }
        LoadCampoLocal();
        LoadCampoVisitante();
        LoadPitcherLocal();
        LoadPitcherVisitante();
        ActualizarTablero();

        File imgjug = new File("imgequipos/" + Administracion.getInstancia().getMisEquipos().get(Local).getNombre() + ".png");
        File imgjug2 = new File("imgequipos/" + Administracion.getInstancia().getMisEquipos().get(Visitante).getNombre() + ".png");

        if (imgjug.exists()) {
            //    rsscalelabel.RSScaleLabel.setScaleLabel(LabelLogoLocal, imgjug.toString());
        }

        if (imgjug2.exists()) {
            //  rsscalelabel.RSScaleLabel.setScaleLabel(labelLogoVisitante, imgjug2.toString());
        }
    }

    protected void ActualizarTablero() {
        if (EntradaAux == 1) {
            txtIning1Visitante.setText(String.valueOf(Carreras));
            labelEntradaActual.setText("Inicio del 1ro Inning");
        } else if (EntradaAux == 2) {
            txtIning1Local.setText(String.valueOf(Carreras));
            labelEntradaActual.setText("Parte baja del 1ro");
        } else if (EntradaAux == 3) {
            txtIning2Visitante.setText(String.valueOf(Carreras));
            labelEntradaActual.setText("Inicio del 2do Inning");
        } else if (EntradaAux == 4) {
            txtIning2Local.setText(String.valueOf(Carreras));
            labelEntradaActual.setText("Parte baja del 2do");
        } else if (EntradaAux == 5) {
            txtIning3Visitante.setText(String.valueOf(Carreras));
            labelEntradaActual.setText("Inicio del 3ro Inning");
        } else if (EntradaAux == 6) {
            txtIning3Local.setText(String.valueOf(Carreras));
            labelEntradaActual.setText("Parte baja del 3ro");
        } else if (EntradaAux == 7) {
            txtIning4Visitante.setText(String.valueOf(Carreras));
            labelEntradaActual.setText("Inicio del 4to Inning");
        } else if (EntradaAux == 8) {
            txtIning4Local.setText(String.valueOf(Carreras));
            labelEntradaActual.setText("Parte baja del 4to");
        } else if (EntradaAux == 9) {
            txtIning5Visitante.setText(String.valueOf(Carreras));
            labelEntradaActual.setText("Inicio del 5to Inning");
        } else if (EntradaAux == 10) {
            txtIning5Local.setText(String.valueOf(Carreras));
            labelEntradaActual.setText("Parte baja del 5to");
        } else if (EntradaAux == 11) {
            txtIning6Visitante.setText(String.valueOf(Carreras));
            labelEntradaActual.setText("Inicio del 6to Inning");
        } else if (EntradaAux == 12) {
            txtIning6Local.setText(String.valueOf(Carreras));
            labelEntradaActual.setText("Parte baja del 6to");
        } else if (EntradaAux == 13) {
            txtIning7Visitante.setText(String.valueOf(Carreras));
            labelEntradaActual.setText("Inicio del 7mo Inning");
        } else if (EntradaAux == 14) {
            txtIning7Local.setText(String.valueOf(Carreras));
            labelEntradaActual.setText("Parte baja del 7mo");
        } else if (EntradaAux == 15) {
            txtIning8Visitante.setText(String.valueOf(Carreras));
            labelEntradaActual.setText("Inicio del 8tavo Inning");
        } else if (EntradaAux == 16) {
            txtIning8Local.setText(String.valueOf(Carreras));
            labelEntradaActual.setText("Parte baja del 8vo");
        } else if (EntradaAux == 17) {
            txtIning9Visitante.setText(String.valueOf(Carreras));
            labelEntradaActual.setText("Inicio del 9no Inning");
        } else if (EntradaAux == 18) {
            txtIning9Local.setText(String.valueOf(Carreras));
            labelEntradaActual.setText("Parte baja del 9no");
        }

        txtTotalCarrerasLocal.setText(String.valueOf(CarrerasTotalesLocal));
        txtTotalCarrerasVisitante.setText(String.valueOf(CarrerasTotalesVisitante));
        txtTotalErroresLocal.setText(String.valueOf(ErroresTotalLocal));
        txtTotalErroresVisitante.setText(String.valueOf(ErroresTotalVisitante));
        txtTotalHitsLocal.setText(String.valueOf(HitsTotalLocales));
        txtTotalHitsVisitante.setText(String.valueOf(HitsTotalVisitantes));

    }

    protected void ActualizarEntradasPitcher() {
        /* if (EntradaAux % 2 != 0) {
            String aux = (String) TablePitcherLocal.getValueAt(0, 0);
            int help = Administracion.getInstancia().findJugador(Local, aux);
            int help2 = ((Pitcher) Administracion.getInstancia().getMisEquipos().get(Local).getJugadores().get(help)).getEstad().getEntradasJugadas();
            ((Pitcher) Administracion.getInstancia().getMisEquipos().get(Local).getJugadores().get(help)).getEstad().setEntradasJugadas(help2 + 1);
        } else if (EntradaAux % 2 == 0) {
            String aux = (String) TablePitcherVisitante.getValueAt(0, 0);
            int help = Administracion.getInstancia().findJugador(Visitante, aux);
            int help2 = ((Pitcher) Administracion.getInstancia().getMisEquipos().get(Visitante).getJugadores().get(help)).getEstad().getEntradasJugadas();
            ((Pitcher) Administracion.getInstancia().getMisEquipos().get(Visitante).getJugadores().get(help)).getEstad().setEntradasJugadas(help2 + 1);
        }*/
    }

    protected void ActualizarHitsPitcher() throws SQLException {
        Connection db = DriverManager.getConnection("jdbc:sqlserver://192.168.77.24:1433;database=proyectoLigaBeisbol_grupo3", "jhernandez", "Junior2000");
        if (EntradaAux % 2 == 0) {
            int aux = cod_jug((String) TableCampoLocal.getValueAt(TableCampoLocal.getSelectedRow(), 0));
            PreparedStatement sts = db.prepareStatement("UPDATE EstadisticaPitcher SET D = ? FROM EstadisticaPitcher WHERE EstadisticaPitcher.codigo_jugador = ?");
            sts.setInt(1, 1);
            sts.setInt(2, aux);
            sts.executeUpdate();
        } else if (EntradaAux % 2 != 0) {
            int aux = cod_jug((String) TableCampoVisitante.getValueAt(TableCampoVisitante.getSelectedRow(), 0));
            PreparedStatement sts = db.prepareStatement("UPDATE EstadisticaPitcher SET D = ? FROM EstadisticaPitcher WHERE EstadisticaPitcher.codigo_jugador = ?");
            sts.setInt(1, 1);
            sts.setInt(2, aux);
            sts.executeUpdate();
        }
    }

    protected void ActualizarCarrerasPitcher() throws SQLException {
        Connection db = DriverManager.getConnection("jdbc:sqlserver://192.168.77.24:1433;database=proyectoLigaBeisbol_grupo3", "jhernandez", "Junior2000");
        if (EntradaAux % 2 == 0) {
            int aux = cod_jug((String) TableCampoLocal.getValueAt(TableCampoLocal.getSelectedRow(), 0));
            PreparedStatement sts = db.prepareStatement("UPDATE EstadisticaPitcher SET CL = ? FROM EstadisticaPitcher WHERE EstadisticaPitcher.codigo_jugador = ?");
            sts.setInt(1, 1);
            sts.setInt(2, aux);
            sts.executeUpdate();
        } else if (EntradaAux % 2 != 0) {
            int aux = cod_jug((String) TableCampoVisitante.getValueAt(TableCampoVisitante.getSelectedRow(), 0));
            PreparedStatement sts = db.prepareStatement("UPDATE EstadisticaPitcher SET CL = ? FROM EstadisticaPitcher WHERE EstadisticaPitcher.codigo_jugador = ?");
            sts.setInt(1, 1);
            sts.setInt(2, aux);
            sts.executeUpdate();
        }
    }

    protected void ActualizarJonronesPitcher() throws SQLException {
        Connection db = DriverManager.getConnection("jdbc:sqlserver://192.168.77.24:1433;database=proyectoLigaBeisbol_grupo3", "jhernandez", "Junior2000");
        if (EntradaAux % 2 == 0) {
            int aux = cod_jug((String) TableCampoLocal.getValueAt(TableCampoLocal.getSelectedRow(), 0));
            PreparedStatement sts = db.prepareStatement("UPDATE EstadisticaPitcher SET HR = ? FROM EstadisticaPitcher WHERE EstadisticaPitcher.codigo_jugador = ?");
            sts.setInt(1, 1);
            sts.setInt(2, aux);
            sts.executeUpdate();
            PreparedStatement ts = db.prepareStatement("UPDATE Partido SET carrera_local = ? FROM partido WHERE Partido.codigo_equipoLocal = ? AND Partido.codigo_partido = ?");
            ts.setInt(1, Integer.parseInt(TableCampoLocal.getValueAt(TableCampoLocal.getSelectedRow(), 1).toString()) + 1);
            ts.setInt(2, Local);
            ts.setInt(3, MiPartido);
            ts.executeUpdate();
        } else if (EntradaAux % 2 != 0) {
            int aux = cod_jug((String) TableCampoVisitante.getValueAt(TableCampoVisitante.getSelectedRow(), 0));
            PreparedStatement sts = db.prepareStatement("UPDATE EstadisticaPitcher SET HR = ? FROM EstadisticaPitcher WHERE EstadisticaPitcher.codigo_jugador = ?");
            sts.setInt(1, 1);
            sts.setInt(2, aux);
            sts.executeUpdate();
            PreparedStatement ts = db.prepareStatement("UPDATE Partido SET carrera_visitante = ? FROM partido WHERE Partido.codigo_equipoVisitante = ? AND Partido.codigo_partido = ?");
            ts.setInt(1, Integer.parseInt(TableCampoVisitante.getValueAt(TableCampoVisitante.getSelectedRow(), 1).toString()) + 1);
            ts.setInt(2, Visitante);
            ts.setInt(3, MiPartido);
            ts.executeUpdate();
        }
    }

    protected void ActualizarCarrerasLimpias() throws SQLException {
        Connection db = DriverManager.getConnection("jdbc:sqlserver://192.168.77.24:1433;database=proyectoLigaBeisbol_grupo3", "jhernandez", "Junior2000");
        if (EntradaAux % 2 == 0) {
            int aux = cod_jug((String) TableCampoLocal.getValueAt(TableCampoLocal.getSelectedRow(), 0));
            PreparedStatement sts = db.prepareStatement("UPDATE Estadistica SET BB = ? FROM Estadistica WHERE Estadistica.codigo_jugador = ?");
            sts.setInt(1, 1);
            sts.setInt(2, aux);
            sts.executeUpdate();
        } else if (EntradaAux % 2 != 0) {
            int aux = cod_jug((String) TableCampoVisitante.getValueAt(TableCampoVisitante.getSelectedRow(), 0));
            PreparedStatement sts = db.prepareStatement("UPDATE Estadistica SET BB = ? FROM Estadistica WHERE Estadistica.codigo_jugador = ?");
            sts.setInt(1, 1);
            sts.setInt(2, aux);
            sts.executeUpdate();
        }
    }

    protected void ActualizarAB() throws SQLException {

        Connection db = DriverManager.getConnection("jdbc:sqlserver://192.168.77.24:1433;database=proyectoLigaBeisbol_grupo3", "jhernandez", "Junior2000");
        if (EntradaAux % 2 == 0) {
            int aux = cod_jug((String) TableCampoLocal.getValueAt(TableCampoLocal.getSelectedRow(), 0));
            PreparedStatement sts = db.prepareStatement("UPDATE Estadistica SET AB = ? FROM Estadistica WHERE Estadistica.codigo_jugador = ?");
            sts.setInt(1, 1);
            sts.setInt(2, aux);
            sts.executeUpdate();
        } else if (EntradaAux % 2 != 0) {
            int aux = cod_jug((String) TableCampoVisitante.getValueAt(TableCampoVisitante.getSelectedRow(), 0));
            PreparedStatement sts = db.prepareStatement("UPDATE Estadistica SET AB = ? FROM Estadistica WHERE Estadistica.codigo_jugador = ?");
            sts.setInt(1, 1);
            sts.setInt(2, aux);
            sts.executeUpdate();
        }

    }

    protected void ActualizarHits() throws SQLException {

        Connection db = DriverManager.getConnection("jdbc:sqlserver://192.168.77.24:1433;database=proyectoLigaBeisbol_grupo3", "jhernandez", "Junior2000");
        if (EntradaAux % 2 == 0) {
            int aux = cod_jug((String) TableCampoLocal.getValueAt(TableCampoLocal.getSelectedRow(), 0));
            PreparedStatement sts = db.prepareStatement("UPDATE Estadistica SET D = ? FROM Estadistica WHERE Estadistica.codigo_jugador = ?");
            sts.setInt(1, 1);
            sts.setInt(2, aux);
            sts.executeUpdate();
        } else if (EntradaAux % 2 != 0) {
            int aux = cod_jug((String) TableCampoVisitante.getValueAt(TableCampoVisitante.getSelectedRow(), 0));
            PreparedStatement sts = db.prepareStatement("UPDATE Estadistica SET D = ? FROM Estadistica WHERE Estadistica.codigo_jugador = ?");
            sts.setInt(1, 1);
            sts.setInt(2, aux);
            sts.executeUpdate();
        }
    }

    protected void ActualizarCarreras() throws SQLException {
        Connection db = DriverManager.getConnection("jdbc:sqlserver://192.168.77.24:1433;database=proyectoLigaBeisbol_grupo3", "jhernandez", "Junior2000");
        if (EntradaAux % 2 == 0) {
            int aux = cod_jug((String) TableCampoLocal.getValueAt(TableCampoLocal.getSelectedRow(), 0));
            PreparedStatement ts = db.prepareStatement("UPDATE Partido SET carrera_local = ? FROM partido WHERE Partido.codigo_equipoLocal = ? AND Partido.codigo_partido = ?");
            ts.setInt(1, Integer.parseInt(TableCampoLocal.getValueAt(TableCampoLocal.getSelectedRow(), 1).toString()) + 1);
            ts.setInt(2, Local);
            ts.setInt(3, MiPartido);
            ts.executeUpdate();
        } else if (EntradaAux % 2 != 0) {
            int aux = cod_jug((String) TableCampoVisitante.getValueAt(TableCampoVisitante.getSelectedRow(), 0));
            PreparedStatement ts = db.prepareStatement("UPDATE Partido SET carrera_visitante = ? FROM partido WHERE Partido.codigo_equipoVisitante = ? AND Partido.codigo_partido = ?");
            ts.setInt(1, Integer.parseInt(TableCampoVisitante.getValueAt(TableCampoVisitante.getSelectedRow(), 1).toString()) + 1);
            ts.setInt(2, Visitante);
            ts.setInt(3, MiPartido);
            ts.executeUpdate();
        }

    }

    protected void ActualizarJonrones() throws SQLException {
        Connection db = DriverManager.getConnection("jdbc:sqlserver://192.168.77.24:1433;database=proyectoLigaBeisbol_grupo3", "jhernandez", "Junior2000");
        if (EntradaAux % 2 == 0) {
            int aux = cod_jug((String) TableCampoLocal.getValueAt(TableCampoLocal.getSelectedRow(), 0));
            PreparedStatement sts = db.prepareStatement("UPDATE Estadistica SET HR = ? FROM Estadistica WHERE Estadistica.codigo_jugador = ?");
            sts.setInt(1, 1);
            sts.setInt(2, aux);
            sts.executeUpdate();
            PreparedStatement ts = db.prepareStatement("UPDATE Partido SET carrera_local = ? FROM partido WHERE Partido.codigo_equipoLocal = ? AND Partido.codigo_partido = ?");
            ts.setInt(1, Integer.parseInt(TableCampoLocal.getValueAt(TableCampoLocal.getSelectedRow(), 1).toString()) + 1);
            ts.setInt(2, Local);
            ts.setInt(3, MiPartido);
            ts.executeUpdate();
        } else if (EntradaAux % 2 != 0) {
            int aux = cod_jug((String) TableCampoVisitante.getValueAt(TableCampoVisitante.getSelectedRow(), 0));
            PreparedStatement sts = db.prepareStatement("UPDATE Estadistica SET HR = ? FROM Estadistica WHERE Estadistica.codigo_jugador = ?");
            sts.setInt(1, 1);
            sts.setInt(2, aux);
            sts.executeUpdate();
            PreparedStatement ts = db.prepareStatement("UPDATE Partido SET carrera_visitante = ? FROM partido WHERE Partido.codigo_equipoVisitante = ? AND Partido.codigo_partido = ?");
            ts.setInt(1, Integer.parseInt(TableCampoVisitante.getValueAt(TableCampoVisitante.getSelectedRow(), 1).toString()) + 1);
            ts.setInt(2, Visitante);
            ts.setInt(3, MiPartido);
            ts.executeUpdate();
        }

    }

    protected void ActualizarErrores() throws SQLException {
        Connection db = DriverManager.getConnection("jdbc:sqlserver://192.168.77.24:1433;database=proyectoLigaBeisbol_grupo3", "jhernandez", "Junior2000");
        if (EntradaAux % 2 == 0) {
            int aux = cod_jug((String) TableCampoLocal.getValueAt(TableCampoLocal.getSelectedRow(), 0));
            PreparedStatement sts = db.prepareStatement("UPDATE Estadistica SET H = ? FROM Estadistica WHERE Estadistica.codigo_jugador = ?");
            sts.setInt(1, Integer.parseInt(TableCampoLocal.getValueAt(TableCampoLocal.getSelectedRow(), 2).toString()) + 1);
            sts.setInt(2, aux);
            sts.executeUpdate();
            if (Integer.parseInt(TableCampoLocal.getValueAt(TableCampoLocal.getSelectedRow(), 2).toString()) == 3) {
                JOptionPane.showMessageDialog(null, "Cambie de bateador.", "Informaci�n", JOptionPane.INFORMATION_MESSAGE);
                EntradaAux++;
            }
        } else if (EntradaAux % 2 != 0) {
            if (Integer.parseInt(TableCampoVisitante.getValueAt(TableCampoVisitante.getSelectedRow(), 2).toString()) == 3) {
                JOptionPane.showMessageDialog(null, "Cambie de bateador.", "Informaci�n", JOptionPane.INFORMATION_MESSAGE);
                EntradaAux++;
            } else {
                int aux = cod_jug((String) TableCampoVisitante.getValueAt(TableCampoVisitante.getSelectedRow(), 0));
                PreparedStatement sts = db.prepareStatement("UPDATE Estadistica SET H = ? FROM Estadistica WHERE Estadistica.codigo_jugador = ?");
                sts.setInt(1, Integer.parseInt(TableCampoVisitante.getValueAt(TableCampoVisitante.getSelectedRow(), 2).toString()) + 1);
                sts.setInt(2, aux);
                sts.executeUpdate();
            }
        }

    }

    public static void LoadCampoLocal() {
        modelCampoLocal.setRowCount(0);
        fila = new Object[modelCampoLocal.getColumnCount()];
        try {
            Connection db = DriverManager.getConnection("jdbc:sqlserver://192.168.77.24:1433;database=proyectoLigaBeisbol_grupo3", "jhernandez", "Junior2000");
            Statement st = db.createStatement();
            ResultSet rs;
            rs = st.executeQuery("SELECT Jugador.*, Estadistica.*, Partido.carrera_local FROM Jugador, Estadistica, Equipo, Partido WHERE Jugador.codigo_jugador = Estadistica.codigo_jugador AND Jugador.codigo_equipo = Equipo.codigo_equipo AND Partido.codigo_equipoLocal = Equipo.codigo_equipo AND Partido.codigo_equipoLocal = Jugador.codigo_equipo AND Jugador.codigo_equipo = " + Local + " AND Partido.codigo_partido =  " + MiPartido + "");
            while (rs.next()) {
                String fname = rs.getString("nombre");
                int fd = rs.getInt("carrera_local");
                int fh = rs.getInt("H");
                fila[0] = fname;
                fila[1] = fd;
                fila[2] = fh;
                modelCampoLocal.addRow(fila);
            }
        } catch (SQLException a) {
            System.out.println("Error " + a.getMessage());
        }
    }

    public static void LoadCampoVisitante() {
        modelCampoVisitante.setRowCount(0);
        fila = new Object[modelCampoVisitante.getColumnCount()];
        try {
            Connection db = DriverManager.getConnection("jdbc:sqlserver://192.168.77.24:1433;database=proyectoLigaBeisbol_grupo3", "jhernandez", "Junior2000");
            Statement st = db.createStatement();
            ResultSet rs;
            rs = st.executeQuery("SELECT Jugador.*, Estadistica.*, Partido.carrera_visitante FROM Jugador, Estadistica, Equipo, Partido WHERE Jugador.codigo_jugador = Estadistica.codigo_jugador AND Jugador.codigo_equipo = Equipo.codigo_equipo AND Partido.codigo_equipoVisitante = Equipo.codigo_equipo AND Partido.codigo_equipoVisitante = Jugador.codigo_equipo AND Jugador.codigo_equipo =  " + Visitante + " AND Partido.codigo_partido =  " + MiPartido + "");
            while (rs.next()) {
                String fname = rs.getString("nombre");
                int fd = rs.getInt("carrera_visitante");
                int fh = rs.getInt("H");
                fila[0] = fname;
                fila[1] = fd;
                fila[2] = fh;
                modelCampoVisitante.addRow(fila);
            }
        } catch (SQLException a) {
            System.out.println("Error " + a.getMessage());
        }
    }

    public static void LoadPitcherLocal() {
        modelPitcherLocal.setRowCount(0);
        fila = new Object[modelPitcherLocal.getColumnCount()];
        try {
            Connection db = DriverManager.getConnection("jdbc:sqlserver://192.168.77.24:1433;database=proyectoLigaBeisbol_grupo3", "jhernandez", "Junior2000");
            Statement st = db.createStatement();
            ResultSet rs;
            rs = st.executeQuery("SELECT Jugador.*, EstadisticaPitcher.*, Partido.carrera_local FROM Jugador, EstadisticaPitcher, Equipo, Partido WHERE Jugador.codigo_jugador = EstadisticaPitcher.codigo_jugador AND Jugador.codigo_equipo = Equipo.codigo_equipo AND Partido.codigo_equipoLocal = Equipo.codigo_equipo AND Partido.codigo_equipoLocal = Jugador.codigo_equipo AND Jugador.codigo_equipo =  " + Local + " AND Partido.codigo_partido =  " + MiPartido + "");
            while (rs.next()) {
                String fname = rs.getString("nombre");
                int fhr = rs.getInt("HR");
                int fcl = rs.getInt("carrera_local");
                fila[0] = fname;
                fila[1] = fcl;
                fila[2] = fhr;;
                modelPitcherLocal.addRow(fila);
            }
        } catch (SQLException a) {
            System.out.println("Error " + a.getMessage());
        }

    }

    public static void LoadPitcherL(int b) {
        modelPitcherLocal.setRowCount(0);
        fila = new Object[modelPitcherLocal.getColumnCount()];
        try {
            Connection db = DriverManager.getConnection("jdbc:sqlserver://192.168.77.24:1433;database=proyectoLigaBeisbol_grupo3", "jhernandez", "Junior2000");
            Statement st = db.createStatement();
            ResultSet rs;
            rs = st.executeQuery("SELECT Jugador.*, EstadisticaPitcher.*, Partido.carrera_local FROM Jugador, EstadisticaPitcher, Equipo, Partido WHERE Jugador.codigo_jugador = EstadisticaPitcher.codigo_jugador AND Jugador.codigo_equipo = Equipo.codigo_equipo AND Partido.codigo_equipoLocal = Equipo.codigo_equipo AND Partido.codigo_equipoLocal = Jugador.codigo_equipo AND Jugador.codigo_equipo =  " + Local + " AND Jugador.codigo_jugador =  " + b + " AND Partido.codigo_partido =  " + MiPartido + "");
            while (rs.next()) {
                String fname = rs.getString("nombre");
                int fhr = rs.getInt("HR");
                int fcl = rs.getInt("carrera_local");
                fila[0] = fname;
                fila[1] = fcl;
                fila[2] = fhr;;
                modelPitcherLocal.addRow(fila);
            }
        } catch (SQLException a) {
            System.out.println("Error " + a.getMessage());
        }

    }

    public static void LoadPitcherV(int b) {
        modelPitcherVisitante.setRowCount(0);
        fila = new Object[modelPitcherVisitante.getColumnCount()];
        try {
            Connection db = DriverManager.getConnection("jdbc:sqlserver://192.168.77.24:1433;database=proyectoLigaBeisbol_grupo3", "jhernandez", "Junior2000");
            Statement st = db.createStatement();
            ResultSet rs;
            rs = st.executeQuery("SELECT Jugador.*, EstadisticaPitcher.*, Partido.carrera_visitante FROM Jugador, EstadisticaPitcher, Equipo, Partido WHERE Jugador.codigo_jugador = EstadisticaPitcher.codigo_jugador AND Jugador.codigo_equipo = Equipo.codigo_equipo AND Partido.codigo_equipoVisitante = Equipo.codigo_equipo AND Partido.codigo_equipoVisitante = Jugador.codigo_equipo AND Jugador.codigo_equipo =  " + Visitante + " AND Jugador.codigo_jugador =  " + b + " AND Partido.codigo_partido =  " + MiPartido + "");
            while (rs.next()) {
                String fname = rs.getString("nombre");
                int fhr = rs.getInt("HR");
                int fcl = rs.getInt("carrera_visitante");
                fila[0] = fname;
                fila[1] = fcl;
                fila[2] = fhr;;
                modelPitcherVisitante.addRow(fila);
            }
        } catch (SQLException a) {
            System.out.println("Error " + a.getMessage());
        }
    }

    public int cod_jug(String s) {
        int fcod = 0;
        try {
            Connection db = DriverManager.getConnection("jdbc:sqlserver://192.168.77.24:1433;database=proyectoLigaBeisbol_grupo3", "jhernandez", "Junior2000");
            Statement st = db.createStatement();
            ResultSet rs;
            rs = st.executeQuery("SELECT codigo_Jugador FROM Jugador WHERE nombre = '" + s + "'");
            while (rs.next()) {
                fcod = rs.getInt("codigo_Jugador");
            }

        } catch (SQLException a) {
            System.out.println("Error " + a.getMessage());
        }
        return fcod;
    }

    public static void LoadPitcherVisitante() {
        modelPitcherVisitante.setRowCount(0);
        fila = new Object[modelPitcherVisitante.getColumnCount()];
        try {
            Connection db = DriverManager.getConnection("jdbc:sqlserver://192.168.77.24:1433;database=proyectoLigaBeisbol_grupo3", "jhernandez", "Junior2000");
            Statement st = db.createStatement();
            ResultSet rs;
            rs = st.executeQuery("SELECT Jugador.*, EstadisticaPitcher.*, Partido.carrera_visitante FROM Jugador, EstadisticaPitcher, Equipo, Partido WHERE Jugador.codigo_jugador = EstadisticaPitcher.codigo_jugador AND Jugador.codigo_equipo = Equipo.codigo_equipo AND Partido.codigo_equipoVisitante = Equipo.codigo_equipo AND Partido.codigo_equipoVisitante = Jugador.codigo_equipo AND Jugador.codigo_equipo =  " + Visitante + " AND Partido.codigo_partido =  " + MiPartido + "");
            while (rs.next()) {
                String fname = rs.getString("nombre");
                int fhr = rs.getInt("HR");
                int fcl = rs.getInt("carrera_visitante");
                fila[0] = fname;
                fila[1] = fcl;
                fila[2] = fhr;;
                modelPitcherVisitante.addRow(fila);
            }
        } catch (SQLException a) {
            System.out.println("Error " + a.getMessage());
        }
    }
}
