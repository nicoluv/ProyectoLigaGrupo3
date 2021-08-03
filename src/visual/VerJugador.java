package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.omg.CORBA.PUBLIC_MEMBER;

import logic.Administracion;
import logic.JugCampo;
import logic.Pitcher;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VerJugador extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JTable table;
    public static Object[] fila;
    private static int MiJugador;
    public static DefaultTableModel model;
    private static int MiEquipo;
    private JLabel lblNombreApellido;
    private JLabel lblEdaad;
    private JLabel lblPais;
    private JLabel lblAltura;
    private JLabel lblPosicion;
    private JLabel lblBatea;
    private JLabel lblLanza;
    private JLabel lblEquipo;
    private JLabel lblNumero;
    private static JLabel lblFoto;

    public VerJugador(int i, int e) {
        setResizable(false);
        setTitle("Perfil del Jugador");
        MiJugador = i;
        MiEquipo = e;
        setBounds(100, 100, 716, 400);
        setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new BorderLayout(0, 0));
        {
            JPanel panel = new JPanel();
            panel.setBorder(new LineBorder(new Color(0, 0, 0)));
            contentPanel.add(panel, BorderLayout.CENTER);
            panel.setLayout(null);

            JPanel panel_1 = new JPanel() {
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Graphics2D g2 = (Graphics2D) g;
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    GradientPaint g3 = new GradientPaint(0, 0, getBackground().CYAN.brighter(), 0, getHeight(), getBackground().BLUE.brighter().brighter().brighter());
                    g2.setPaint(g3);
                    g2.fillRect(0, 0, getWidth(), getHeight());
                }
            };
            panel_1.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
            panel_1.setBounds(10, 37, 682, 168);
            panel.add(panel_1);
            panel_1.setLayout(null);

            lblFoto = new JLabel("");
            lblFoto.setBounds(480, 12, 190, 144);
            panel_1.add(lblFoto);

            lblNombreApellido = new JLabel("");
            lblNombreApellido.setFont(new Font("Tahoma", Font.BOLD, 15));
            lblNombreApellido.setBounds(12, 18, 251, 26);
            panel_1.add(lblNombreApellido);

            JLabel lblEdad = new JLabel("Edad:");
            lblEdad.setFont(new Font("Tahoma", Font.BOLD, 15));
            lblEdad.setBounds(12, 62, 55, 16);
            panel_1.add(lblEdad);

            lblEdaad = new JLabel("");
            lblEdaad.setFont(new Font("Tahoma", Font.BOLD, 15));
            lblEdaad.setBounds(68, 62, 55, 16);
            panel_1.add(lblEdaad);

            JLabel labelPais = new JLabel("Pais:");
            labelPais.setFont(new Font("Tahoma", Font.BOLD, 15));
            labelPais.setBounds(12, 96, 55, 16);
            panel_1.add(labelPais);

            lblPais = new JLabel("");
            lblPais.setFont(new Font("Tahoma", Font.BOLD, 15));
            lblPais.setBounds(68, 96, 195, 16);
            panel_1.add(lblPais);

            JLabel labelAltura = new JLabel("Altura:");
            labelAltura.setFont(new Font("Tahoma", Font.BOLD, 15));
            labelAltura.setBounds(274, 62, 55, 16);
            panel_1.add(labelAltura);

            lblAltura = new JLabel("");
            lblAltura.setFont(new Font("Tahoma", Font.BOLD, 15));
            lblAltura.setBounds(349, 62, 55, 16);
            panel_1.add(lblAltura);

            JLabel labelPocision = new JLabel("Posici\u00F3n:");
            labelPocision.setFont(new Font("Tahoma", Font.BOLD, 15));
            labelPocision.setBounds(274, 96, 80, 16);
            panel_1.add(labelPocision);

            lblPosicion = new JLabel("");
            lblPosicion.setFont(new Font("Tahoma", Font.BOLD, 15));
            lblPosicion.setBounds(349, 96, 123, 16);
            panel_1.add(lblPosicion);

            JLabel labelBatea = new JLabel("Batea:");
            labelBatea.setFont(new Font("Tahoma", Font.BOLD, 15));
            labelBatea.setBounds(12, 130, 55, 16);
            panel_1.add(labelBatea);

            JLabel labelLanzamiento = new JLabel("Lanzamiento:");
            labelLanzamiento.setFont(new Font("Tahoma", Font.BOLD, 15));
            labelLanzamiento.setBounds(274, 130, 100, 16);
            panel_1.add(labelLanzamiento);

            lblBatea = new JLabel("");
            lblBatea.setFont(new Font("Tahoma", Font.BOLD, 15));
            lblBatea.setBounds(68, 130, 94, 16);
            panel_1.add(lblBatea);

            lblLanza = new JLabel("");
            lblLanza.setFont(new Font("Tahoma", Font.BOLD, 15));
            lblLanza.setBounds(382, 130, 100, 16);
            panel_1.add(lblLanza);

            JPanel panel_2 = new JPanel();
            panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
            panel_2.setBounds(10, 257, 682, 41);
            panel.add(panel_2);
            panel_2.setLayout(new BorderLayout(0, 0));
            {
                JScrollPane scrollPane = new JScrollPane();
                panel_2.add(scrollPane, BorderLayout.CENTER);

                model = new DefaultTableModel();
                String[] headerPitcher = {"Juegos Iniciados", "Hits", "Carreras", "Jonrones", "Ponches", "Carreras Limpias", "PromCL"};
                String[] headerCampo = {"Al Bate", "Carreras", "Hits", "Errores", "2B", "Juegos Jugados", "AVG"};
                if (Administracion.getInstancia().getMisEquipos().get(MiEquipo).getMisJugadores().get(MiJugador) instanceof JugCampo) {
                    model.setColumnIdentifiers(headerCampo);
                } else if (Administracion.getInstancia().getMisEquipos().get(MiEquipo).getMisJugadores().get(MiJugador) instanceof Pitcher) {
                    model.setColumnIdentifiers(headerPitcher);
                }

                table = new JTable();
                table.setRowSelectionAllowed(false);
                scrollPane.setViewportView(table);
                table.setModel(model);
                loadtable();
            }

            try {
                Connection db = DriverManager.getConnection("jdbc:sqlserver://192.168.77.24:1433;database=proyectoLigaBeisbol_grupo3", "jhernandez", "Junior2000");
                Statement st = db.createStatement();
                ResultSet rs;
                rs = st.executeQuery("SELECT DISTINCT Jugador.*, DATEDIFF(YEAR, (Jugador.fecha_nacimento), GETDATE()) as Edad, Posicion.descripcion FROM Jugador, Posicion WHERE Posicion.codigo_posc = Jugador.codigo_posc AND codigo_Jugador='" + MiJugador + "'");

                while (rs.next()) {
                    String fname = rs.getString("nombre");
                    Date ffecha = rs.getDate("fecha_nacimento");
                    int fpeso = rs.getInt("peso");
                    String fbat = rs.getString("bateo");
                    String flaz = rs.getString("lanzamiento");
                    String fpais = rs.getString("pais");
                    int faltura = rs.getInt("altura");
                    String fpos = rs.getString("descripcion");
                    int fnumero = rs.getInt("numero");
                    int fedad = rs.getInt("Edad");
                    lblNombreApellido.setText(fname);
                    lblAltura.setText((String.valueOf(faltura)) + "'");
                    lblEdaad.setText((String.valueOf(fedad)));
                    lblBatea.setText(fbat);
                    lblLanza.setText(flaz);
                    lblPais.setText(fpais);
                    lblPosicion.setText(fpos);
                    lblNumero.setText(String.valueOf(fnumero));
                }
            } catch (SQLException a) {
                System.out.println("Error " + a.getMessage());
            }

            JLabel lblNmero = new JLabel("N\u00FAmero:");
            lblNmero.setFont(new Font("Tahoma", Font.BOLD, 15));
            lblNmero.setBounds(275, 24, 79, 16);
            panel_1.add(lblNmero);

            lblNumero = new JLabel("");
            lblNumero.setFont(new Font("Tahoma", Font.BOLD, 15));
            lblNumero.setBounds(349, 24, 55, 16);
            panel_1.add(lblNumero);

            lblNumero.setText(String.valueOf(Administracion.getInstancia().getMisEquipos().get(MiEquipo).getMisJugadores().get(MiJugador).getNumero()));

            JLabel lblEstadisticasTemporadaRegular = new JLabel("Estadisticas Temporada Regular");
            lblEstadisticasTemporadaRegular.setFont(new Font("Tahoma", Font.BOLD, 15));
            lblEstadisticasTemporadaRegular.setBounds(10, 217, 314, 28);
            panel.add(lblEstadisticasTemporadaRegular);

            lblEquipo = new JLabel("");
            lblEquipo.setFont(new Font("Tahoma", Font.BOLD, 15));
            lblEquipo.setBounds(10, 9, 435, 22);
            panel.add(lblEquipo);

            lblEquipo.setText(Administracion.getInstancia().getMisEquipos().get(MiEquipo).getNombre());
        }
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);

            JButton btnModificar = new JButton("Modificar");
            btnModificar.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    RegJugador regj = null;
                    try {
                        regj = new RegJugador(MiJugador, MiEquipo, true);
                    } catch (SQLException ex) {
                        Logger.getLogger(VerJugador.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    regj.setModal(true);
                    regj.setVisible(true);
                    RecargarDatos();
                }
            });
            btnModificar.setFont(new Font("Tahoma", Font.PLAIN, 11));
            buttonPane.add(btnModificar);
            {
                JButton okButton = new JButton("Historial de lesiones");
                okButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
                okButton.setEnabled(false);
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
//{"Al Bate", "Carreras", "Hits", "Errores", "2B", "Juegos Jugados", "AVG"};
//{"Juegos Iniciados", "Hits", "Carreras", "Jonrones", "Ponches", "Victorias", "PromCL"};

    public static void loadtable() {
        model.setRowCount(0);
        fila = new Object[model.getColumnCount()];
        NumberFormat formatter = new DecimalFormat(".###");
        if (Administracion.getInstancia().getMisEquipos().get(MiEquipo).getMisJugadores().get(MiJugador) instanceof JugCampo) {
            JugCampo aux = (JugCampo) Administracion.getInstancia().getMisEquipos().get(MiEquipo).getMisJugadores().get(MiJugador);
            fila[0] = aux.getEstad().getAB();
            fila[1] = aux.getEstad().getD();
            fila[2] = aux.getEstad().getH();
            fila[3] = aux.getEstad().getErrores();
            fila[4] = aux.getEstad().getDobles();
            fila[5] = aux.getEstad().getJuegosJug();

            if (aux.getEstad().getAB() > 0 && aux.getEstad().getH() > 0) {
                aux.getEstad().AVG(aux.getEstad().getH(), aux.getEstad().getAB());
                fila[6] = formatter.format(aux.getEstad().getAVG());
            } else {
                fila[6] = 0;
            }

            model.addRow(fila);
        } else if (Administracion.getInstancia().getMisEquipos().get(MiEquipo).getMisJugadores().get(MiJugador) instanceof Pitcher) {
            Pitcher aux = (Pitcher) Administracion.getInstancia().getMisEquipos().get(MiEquipo).getMisJugadores().get(MiJugador);
            fila[0] = aux.getEstad().getJuegosIni();
            fila[1] = aux.getEstad().getHitsPitch();
            fila[2] = aux.getEstad().getCarrPitch();
            fila[3] = aux.getEstad().getJonronPitch();
            fila[4] = aux.getEstad().getPonches();
            fila[5] = aux.getEstad().getCarrLimpias();

            if (aux.getEstad().getEntradasJugadas() > 0 && aux.getEstad().getCarrLimpias() > 0) {
                aux.getEstad().PromCL();
                fila[6] = formatter.format(aux.getEstad().getPromCL());
            } else {
                fila[6] = 0;
            }

            model.addRow(fila);
        }

        //File imgjug = new File("imgjugadores/" + Administracion.getInstancia().getMisEquipos().get(MiEquipo).getJugadores().get(MiJugador).getNombre() + ".png");

        /*if (imgjug.exists()) {
            rsscalelabel.RSScaleLabel.setScaleLabel(lblFoto, imgjug.toString());
        }*/
    }

    public void RecargarDatos() {
        try {
            Connection db = DriverManager.getConnection("jdbc:sqlserver://192.168.77.24:1433;database=proyectoLigaBeisbol_grupo3", "jhernandez", "Junior2000");
            Statement st = db.createStatement();
            ResultSet rs;
            rs = st.executeQuery("SELECT DISTINCT Jugador.*, DATEDIFF(YEAR, (Jugador.fecha_nacimento), GETDATE()) as Edad, Posicion.descripcion FROM Jugador, Posicion WHERE Posicion.codigo_posc = Jugador.codigo_posc AND codigo_Jugador='" + MiJugador + "'");

            while (rs.next()) {
                String fname = rs.getString("nombre");
                Date ffecha = rs.getDate("fecha_nacimento");
                int fpeso = rs.getInt("peso");
                String fbat = rs.getString("bateo");
                String flaz = rs.getString("lanzamiento");
                String fpais = rs.getString("pais");
                int faltura = rs.getInt("altura");
                String fpos = rs.getString("descripcion");
                int fnumero = rs.getInt("numero");
                int fedad = rs.getInt("Edad");
                lblNombreApellido.setText(fname);
                lblAltura.setText((String.valueOf(faltura)) + "'");
                lblEdaad.setText((String.valueOf(fedad)));
                lblBatea.setText(fbat);
                lblLanza.setText(flaz);
                lblPais.setText(fpais);
                lblPosicion.setText(fpos);
                lblNumero.setText(String.valueOf(fnumero));
            }
        } catch (SQLException a) {
            System.out.println("Error " + a.getMessage());
        }

        loadtable();
    }
}
