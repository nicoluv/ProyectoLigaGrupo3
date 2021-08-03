package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.toedter.calendar.JDayChooser;
import com.toedter.calendar.JDateChooser;
import com.toedter.components.JSpinField;

import logic.Administracion;
import logic.Equipo;
import logic.EstadPitcher;
import logic.Estadistica;
import logic.JugCampo;
import logic.Jugador;
import logic.Pitcher;

import com.toedter.components.JLocaleChooser;
import javax.swing.JComboBox;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.border.TitledBorder;
import javax.swing.border.BevelBorder;

public class RegJugador extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JTextField txtNombre;
    private JComboBox cbxPais;
    private JComboBox cbxLanzamiento;
    private JComboBox cbxBateo;
    private JComboBox cbxPosicion;
    private JComboBox cbxEquipo;
    private JSpinner spnPeso;
    private JSpinner spnAltura;
    private JSpinner spnNumero;
    private JSpinner spnHR;
    private JSpinner spnAB;
    private JSpinner spnBB;
    private JSpinner spn2B;
    private JSpinner spn3B;
    private JSpinner spnD;
    private JSpinner spnH;
    private JSpinner spnSO;
    private JSpinner spnH_Pitch;
    private JSpinner spnCL;
    private JSpinner spnBB_Pitch;
    private JSpinner spnHR_Pitch;
    private JSpinner spnSO_Pitch;
    private JSpinner spnD_Pitch;
    private JPanel panel_estadPitcher;
    private JPanel panel_estadisticas;
    private JLabel lblImagen;
    private JDateChooser fechaNacimiento;
    private ArrayList<String> nomEquipos = new ArrayList<String>();
    private int index;
    private static int MiEquipo, MiJugador;
    private boolean modi = false;
    private static File imgjug;
    private BufferedImage imagen;

    public RegJugador(int j, int e, boolean mod) throws SQLException {
        MiJugador = j;
        MiEquipo = e;
        modi = mod;

        if (mod == true) {
            setTitle("Modificar jugador");
        } else {
            setTitle("Registrar jugador");
        }
        setResizable(false);
        setBounds(-12, -36, 812, 370);
        setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new BorderLayout(0, 0));
        {
            JPanel panel = new JPanel();
            contentPanel.add(panel, BorderLayout.CENTER);
            panel.setLayout(null);

            JLabel lblNombre = new JLabel("Nombre del jugador:");
            lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 11));
            lblNombre.setBounds(12, 12, 98, 16);
            panel.add(lblNombre);

            txtNombre = new JTextField();
            txtNombre.addKeyListener(new KeyAdapter() {
                @Override
                public void keyTyped(KeyEvent e) {
                    char validar = e.getKeyChar();

                    if (Character.isDigit(validar)) {
                        e.consume();
                        JOptionPane.showMessageDialog(null, "Solo se permiten letras.");
                    }
                }
            });
            txtNombre.setBounds(137, 10, 204, 20);
            panel.add(txtNombre);
            txtNombre.setColumns(10);

            JLabel lblFechaDeNacimiento = new JLabel("Fecha de Nacimiento: ");
            lblFechaDeNacimiento.setFont(new Font("Tahoma", Font.PLAIN, 11));
            lblFechaDeNacimiento.setBounds(12, 44, 124, 16);
            panel.add(lblFechaDeNacimiento);

            fechaNacimiento = new JDateChooser();
            fechaNacimiento.setBounds(137, 42, 162, 20);
            panel.add(fechaNacimiento);

            JLabel lblPasDeOrigen = new JLabel("Pa\u00EDs:");
            lblPasDeOrigen.setFont(new Font("Tahoma", Font.PLAIN, 11));
            lblPasDeOrigen.setBounds(359, 12, 34, 16);
            panel.add(lblPasDeOrigen);

            cbxPais = new JComboBox();
            cbxPais.setFont(new Font("Tahoma", Font.PLAIN, 11));
            cbxPais.setModel(new DefaultComboBoxModel(new String[]{"<Seleccionar>", "Afganist\u00E1n ", "Akrotiri ", "Albania ", "Alemania ", "Andorra ", "Angola ", "Anguila ", "Ant\u00E1rtida ", "Antigua y Barbuda ", "Antillas Neerlandesas ", "Arabia Saud\u00ED ", "Arctic Ocean ", "Argelia ", "Argentina ", "Armenia ", "Aruba ", "Ashmore andCartier Islands ", "Atlantic Ocean ", "Australia ", "Austria ", "Azerbaiy\u00E1n ", "Bahamas ", "Bahr\u00E1in ", "Bangladesh ", "Barbados ", "B\u00E9lgica ", "Belice ", "Ben\u00EDn ", "Bermudas ", "Bielorrusia ", "Birmania Myanmar ", "Bolivia ", "Bosnia y Hercegovina ", "Botsuana ", "Brasil ", "Brun\u00E9i ", "Bulgaria ", "Burkina Faso ", "Burundi ", "But\u00E1n ", "Cabo Verde ", "Camboya ", "Camer\u00FAn ", "Canad\u00E1 ", "Chad ", "Chile ", "China ", "Chipre ", "Clipperton Island ", "Colombia ", "Comoras ", "Congo ", "Coral Sea Islands ", "Corea del Norte ", "Corea del Sur ", "Costa de Marfil ", "Costa Rica ", "Croacia ", "Cuba ", "Dhekelia ", "Dinamarca ", "Dominica ", "Ecuador ", "Egipto ", "El Salvador ", "El Vaticano ", "Emiratos \u00C1rabes Unidos ", "Eritrea ", "Eslovaquia ", "Eslovenia ", "Espa\u00F1a ", "Estados Unidos ", "Estonia ", "Etiop\u00EDa ", "Filipinas ", "Finlandia ", "Fiyi ", "Francia ", "Gab\u00F3n ", "Gambia ", "Gaza Strip ", "Georgia ", "Ghana ", "Gibraltar ", "Granada ", "Grecia ", "Groenlandia ", "Guam ", "Guatemala ", "Guernsey ", "Guinea ", "Guinea Ecuatorial ", "Guinea-Bissau ", "Guyana ", "Hait\u00ED ", "Honduras ", "Hong Kong ", "Hungr\u00EDa ", "India ", "Indian Ocean ", "Indonesia ", "Ir\u00E1n ", "Iraq ", "Irlanda ", "Isla Bouvet ", "Isla Christmas ", "Isla Norfolk ", "Islandia ", "Islas Caim\u00E1n ", "Islas Cocos ", "Islas Cook ", "Islas Feroe ", "Islas Heard y McDonald ", "Islas Malvinas ", "Islas Marianas del Norte ", "IslasMarshall ", "Islas Pitcairn ", "Islas Salom\u00F3n ", "Islas Turcas y Caicos ", "Islas V\u00EDrgenes Americanas ", "Islas V\u00EDrgenes Brit\u00E1nicas ", "Israel ", "Italia ", "Jamaica ", "Jan Mayen ", "Jap\u00F3n ", "Jersey ", "Jordania ", "Kazajist\u00E1n ", "Kenia ", "Kirguizist\u00E1n ", "Kiribati ", "Kuwait ", "Laos ", "Lesoto ", "Letonia ", "L\u00EDbano ", "Liberia ", "Libia ", "Liechtenstein ", "Lituania ", "Luxemburgo ", "Macao ", "Macedonia ", "Madagascar ", "Malasia ", "Malaui ", "Maldivas ", "Mal\u00ED ", "Malta ", "Man, Isle of ", "Marruecos ", "Mauricio ", "Mauritania ", "Mayotte ", "M\u00E9xico ", "Micronesia ", "Moldavia ", "M\u00F3naco ", "Mongolia ", "Montserrat ", "Mozambique ", "Namibia ", "Nauru ", "Navassa Island ", "Nepal ", "Nicaragua ", "N\u00EDger ", "Nigeria ", "Niue ", "Noruega ", "Nueva Caledonia ", "Nueva Zelanda ", "Om\u00E1n ", "Pacific Ocean ", "Pa\u00EDses Bajos ", "Pakist\u00E1n ", "Palaos ", "Panam\u00E1 ", "Pap\u00FAa-Nueva Guinea ", "Paracel Islands ", "Paraguay ", "Per\u00FA ", "Polinesia Francesa ", "Polonia ", "Portugal ", "Puerto Rico ", "Qatar ", "Reino Unido ", "Rep\u00FAblica Centroafricana ", "Rep\u00FAblica Checa ", "Rep. Democr\u00E1tica del Congo ", "Rep\u00FAblica Dominicana ", "Ruanda ", "Rumania ", "Rusia ", "S\u00E1hara Occidental ", "Samoa ", "Samoa Americana ", "San Crist\u00F3bal y Nieves ", "San Marino ", "San Pedro y Miquel\u00F3n ", "Santa Helena ", "Santa Luc\u00EDa ", "Santo Tom\u00E9 y Pr\u00EDncipe ", "Senegal ", "Seychelles ", "Sierra Leona ", "Singapur ", "Siria ", "Somalia ", "Southern Ocean ", "Spratly Islands ", "Sri Lanka ", "Suazilandia ", "Sud\u00E1frica ", "Sud\u00E1n ", "Suecia ", "Suiza ", "Surinam ", "Svalbard y Jan Mayen ", "Tailandia ", "Taiw\u00E1n ", "Tanzania ", "Tayikist\u00E1n ", "Timor Oriental ", "Togo ", "Tokelau ", "Tonga ", "Trinidad y Tobago ", "T\u00FAnez ", "Turkmenist\u00E1n ", "Turqu\u00EDa ", "Tuvalu ", "Ucrania ", "Uganda ", "Uni\u00F3n Europea ", "Uruguay ", "Uzbekist\u00E1n ", "Vanuatu ", "Venezuela ", "Vietnam ", "Wake Island ", "Wallis y Futuna ", "Yemen ", "Yibuti ", "Zambia ", "Zimbabue "}));
            cbxPais.setBounds(421, 10, 162, 20);
            panel.add(cbxPais);

            JLabel lblLanzamiento = new JLabel("Lanzamiento:");
            lblLanzamiento.setFont(new Font("Tahoma", Font.PLAIN, 11));
            lblLanzamiento.setBounds(12, 104, 83, 16);
            panel.add(lblLanzamiento);

            cbxLanzamiento = new JComboBox();
            cbxLanzamiento.setFont(new Font("Tahoma", Font.PLAIN, 11));
            cbxLanzamiento.setModel(new DefaultComboBoxModel(new String[]{"<Seleccionar>", "Izquierdo", "Derecho"}));
            cbxLanzamiento.setBounds(137, 102, 162, 20);
            panel.add(cbxLanzamiento);

            cbxBateo = new JComboBox();
            cbxBateo.setFont(new Font("Tahoma", Font.PLAIN, 11));
            cbxBateo.setModel(new DefaultComboBoxModel(new String[]{"<Seleccionar>", "Izquierdo", "Derecho"}));
            cbxBateo.setBounds(137, 130, 162, 20);
            panel.add(cbxBateo);

            JLabel lblBateo = new JLabel("Bateo:");
            lblBateo.setFont(new Font("Tahoma", Font.PLAIN, 11));
            lblBateo.setBounds(12, 132, 42, 16);
            panel.add(lblBateo);

            JLabel lblPosicin = new JLabel("Posici\u00F3n:");
            lblPosicin.setFont(new Font("Tahoma", Font.PLAIN, 11));
            lblPosicin.setBounds(359, 44, 55, 16);
            panel.add(lblPosicin);

            cbxPosicion = new JComboBox();
            /*cbxPosicion.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                    index = cbxPosicion.getSelectedIndex();

                    if (index == 8) {
                        panel_estadisticas.setVisible(false);
                        panel_estadPitcher.setVisible(true);
                    } else {
                        panel_estadisticas.setVisible(true);
                        panel_estadPitcher.setVisible(false);
                    }
                }
            });*/
            cbxPosicion.setFont(new Font("Tahoma", Font.PLAIN, 11));
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                Connection con = DriverManager.getConnection("jdbc:sqlserver://192.168.77.24:1433;database=proyectoLigaBeisbol_grupo3", "jhernandez", "Junior2000");
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM Posicion");
                cbxPosicion.addItem("<Seleccionar>");
                while (rs.next()) {
                    String fname = rs.getString("descripcion");
                    cbxPosicion.addItem(fname);
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(NewClass.class.getName()).log(Level.SEVERE, null, ex);
            }

            cbxPosicion.setBounds(421, 42, 162, 20);
            panel.add(cbxPosicion);

            JLabel lblEquipo = new JLabel("Equipo:");
            lblEquipo.setFont(new Font("Tahoma", Font.PLAIN, 11));
            lblEquipo.setBounds(359, 132, 42, 16);
            panel.add(lblEquipo);

            /*for (Equipo i : Administracion.getInstancia().getMisEquipos()) {
                nomEquipos.add(i.getNombre());
            }*/
            //cbxEquipo = new JComboBox(nomEquipos.toArray());
            cbxEquipo = new JComboBox();
            cbxEquipo.setFont(new Font("Tahoma", Font.PLAIN, 11));
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                Connection con = DriverManager.getConnection("jdbc:sqlserver://192.168.77.24:1433;database=proyectoLigaBeisbol_grupo3", "jhernandez", "Junior2000");
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM Equipo");
                cbxEquipo.addItem("<Seleccionar>");
                while (rs.next()) {
                    String fname = rs.getString("nombre_equipo");
                    cbxEquipo.addItem(fname);
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(NewClass.class.getName()).log(Level.SEVERE, null, ex);
            }
            cbxEquipo.setBounds(421, 130, 162, 20);
            panel.add(cbxEquipo);

            panel_estadisticas = new JPanel();
            panel_estadisticas.setBorder(new TitledBorder(null, "Estad\u00EDsticas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
            panel_estadisticas.setBounds(12, 172, 571, 103);
            panel.add(panel_estadisticas);
            panel_estadisticas.setLayout(null);

            JLabel lblAB = new JLabel("AB");
            lblAB.setBounds(57, 31, 27, 14);
            panel_estadisticas.add(lblAB);
            lblAB.setFont(new Font("Tahoma", Font.BOLD, 11));

            spnAB = new JSpinner();
            spnAB.setFont(new Font("Tahoma", Font.PLAIN, 11));
            spnAB.setBounds(45, 48, 47, 23);
            panel_estadisticas.add(spnAB);
            spnAB.setModel(new SpinnerNumberModel(0, 0, 999, 1));

            JLabel lblH = new JLabel("H");
            lblH.setBounds(180, 31, 15, 14);
            panel_estadisticas.add(lblH);
            lblH.setFont(new Font("Tahoma", Font.BOLD, 11));

            spnH = new JSpinner();
            spnH.setFont(new Font("Tahoma", Font.PLAIN, 11));
            spnH.setBounds(163, 48, 47, 23);
            panel_estadisticas.add(spnH);
            spnH.setModel(new SpinnerNumberModel(0, 0, 999, 1));

            JLabel lbl2B = new JLabel("2B");
            lbl2B.setBounds(237, 31, 17, 14);
            panel_estadisticas.add(lbl2B);
            lbl2B.setFont(new Font("Tahoma", Font.BOLD, 11));

            spn2B = new JSpinner();
            spn2B.setFont(new Font("Tahoma", Font.PLAIN, 11));
            spn2B.setBounds(222, 48, 47, 23);
            panel_estadisticas.add(spn2B);
            spn2B.setModel(new SpinnerNumberModel(0, 0, 999, 1));

            JLabel lbl3B = new JLabel("3B");
            lbl3B.setBounds(296, 31, 17, 14);
            panel_estadisticas.add(lbl3B);
            lbl3B.setFont(new Font("Tahoma", Font.BOLD, 11));

            spn3B = new JSpinner();
            spn3B.setFont(new Font("Tahoma", Font.PLAIN, 11));
            spn3B.setBounds(281, 48, 47, 23);
            panel_estadisticas.add(spn3B);
            spn3B.setModel(new SpinnerNumberModel(0, 0, 999, 1));

            JLabel lblHR = new JLabel("HR");
            lblHR.setBounds(417, 31, 17, 14);
            panel_estadisticas.add(lblHR);
            lblHR.setFont(new Font("Tahoma", Font.BOLD, 11));

            spnHR = new JSpinner();
            spnHR.setFont(new Font("Tahoma", Font.PLAIN, 11));
            spnHR.setBounds(405, 48, 47, 23);
            panel_estadisticas.add(spnHR);
            spnHR.setModel(new SpinnerNumberModel(0, 0, 999, 1));

            JLabel lblBB = new JLabel("BB");
            lblBB.setBounds(353, 31, 17, 14);
            panel_estadisticas.add(lblBB);
            lblBB.setFont(new Font("Tahoma", Font.BOLD, 11));

            spnBB = new JSpinner();
            spnBB.setFont(new Font("Tahoma", Font.PLAIN, 11));
            spnBB.setBounds(340, 48, 47, 23);
            panel_estadisticas.add(spnBB);
            spnBB.setModel(new SpinnerNumberModel(0, 0, 999, 1));

            JLabel lblD = new JLabel("D");
            lblD.setBounds(122, 31, 15, 14);
            panel_estadisticas.add(lblD);
            lblD.setFont(new Font("Tahoma", Font.BOLD, 11));

            spnD = new JSpinner();
            spnD.setFont(new Font("Tahoma", Font.PLAIN, 11));
            spnD.setBounds(104, 48, 47, 23);
            panel_estadisticas.add(spnD);
            spnD.setModel(new SpinnerNumberModel(0, 0, 999, 1));

            spnSO = new JSpinner();
            spnSO.setModel(new SpinnerNumberModel(0, 0, 999, 1));
            spnSO.setFont(new Font("Tahoma", Font.PLAIN, 11));
            spnSO.setBounds(464, 48, 47, 23);
            panel_estadisticas.add(spnSO);

            JLabel lblSo = new JLabel("SO");
            lblSo.setFont(new Font("Tahoma", Font.BOLD, 11));
            lblSo.setBounds(477, 31, 17, 14);
            panel_estadisticas.add(lblSo);

            panel_estadPitcher = new JPanel();
            panel_estadPitcher.setBounds(12, 172, 571, 103);
            panel.add(panel_estadPitcher);
            panel_estadPitcher.setLayout(null);
            panel_estadPitcher.setBorder(new TitledBorder(null, "Estad\u00EDsticas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
            panel_estadPitcher.setVisible(false);

            JLabel lblH_Pitch = new JLabel("H");
            lblH_Pitch.setFont(new Font("Tahoma", Font.BOLD, 11));
            lblH_Pitch.setBounds(116, 31, 27, 14);
            panel_estadPitcher.add(lblH_Pitch);

            spnH_Pitch = new JSpinner();
            spnH_Pitch.setModel(new SpinnerNumberModel(0, 0, 999, 1));
            spnH_Pitch.setFont(new Font("Tahoma", Font.PLAIN, 11));
            spnH_Pitch.setBounds(104, 48, 47, 23);
            panel_estadPitcher.add(spnH_Pitch);

            JLabel lblCL = new JLabel("CL");
            lblCL.setFont(new Font("Tahoma", Font.BOLD, 11));
            lblCL.setBounds(239, 31, 15, 14);
            panel_estadPitcher.add(lblCL);

            spnCL = new JSpinner();
            spnCL.setModel(new SpinnerNumberModel(0, 0, 999, 1));
            spnCL.setFont(new Font("Tahoma", Font.PLAIN, 11));
            spnCL.setBounds(222, 48, 47, 23);
            panel_estadPitcher.add(spnCL);

            JLabel lblBB_Pitch = new JLabel("BB");
            lblBB_Pitch.setFont(new Font("Tahoma", Font.BOLD, 11));
            lblBB_Pitch.setBounds(296, 31, 17, 14);
            panel_estadPitcher.add(lblBB_Pitch);

            spnBB_Pitch = new JSpinner();
            spnBB_Pitch.setModel(new SpinnerNumberModel(0, 0, 999, 1));
            spnBB_Pitch.setFont(new Font("Tahoma", Font.PLAIN, 11));
            spnBB_Pitch.setBounds(281, 48, 47, 23);
            panel_estadPitcher.add(spnBB_Pitch);

            JLabel lblHR_Pitcher = new JLabel("HR");
            lblHR_Pitcher.setFont(new Font("Tahoma", Font.BOLD, 11));
            lblHR_Pitcher.setBounds(355, 31, 17, 14);
            panel_estadPitcher.add(lblHR_Pitcher);

            spnHR_Pitch = new JSpinner();
            spnHR_Pitch.setModel(new SpinnerNumberModel(0, 0, 999, 1));
            spnHR_Pitch.setFont(new Font("Tahoma", Font.PLAIN, 11));
            spnHR_Pitch.setBounds(340, 48, 47, 23);
            panel_estadPitcher.add(spnHR_Pitch);

            JLabel lblSO = new JLabel("SO");
            lblSO.setFont(new Font("Tahoma", Font.BOLD, 11));
            lblSO.setBounds(412, 31, 17, 14);
            panel_estadPitcher.add(lblSO);

            spnSO_Pitch = new JSpinner();
            spnSO_Pitch.setModel(new SpinnerNumberModel(0, 0, 999, 1));
            spnSO_Pitch.setFont(new Font("Tahoma", Font.PLAIN, 11));
            spnSO_Pitch.setBounds(399, 48, 47, 23);
            panel_estadPitcher.add(spnSO_Pitch);

            JLabel lblD_Pitch = new JLabel("D");
            lblD_Pitch.setFont(new Font("Tahoma", Font.BOLD, 11));
            lblD_Pitch.setBounds(181, 31, 15, 14);
            panel_estadPitcher.add(lblD_Pitch);

            spnD_Pitch = new JSpinner();
            spnD_Pitch.setModel(new SpinnerNumberModel(0, 0, 999, 1));
            spnD_Pitch.setFont(new Font("Tahoma", Font.PLAIN, 11));
            spnD_Pitch.setBounds(163, 48, 47, 23);
            panel_estadPitcher.add(spnD_Pitch);

            JLabel lblPeso = new JLabel("Peso (KG):");
            lblPeso.setFont(new Font("Tahoma", Font.PLAIN, 11));
            lblPeso.setBounds(12, 76, 51, 16);
            panel.add(lblPeso);

            JLabel lblAltura = new JLabel("Altura (CM):");
            lblAltura.setFont(new Font("Tahoma", Font.PLAIN, 11));
            lblAltura.setBounds(359, 76, 68, 16);
            panel.add(lblAltura);

            spnPeso = new JSpinner();
            spnPeso.setFont(new Font("Tahoma", Font.PLAIN, 11));
            spnPeso.setModel(new SpinnerNumberModel(new Integer(80), new Integer(80), null, new Integer(1)));
            spnPeso.setBounds(137, 74, 162, 20);
            panel.add(spnPeso);

            spnAltura = new JSpinner();
            spnAltura.setFont(new Font("Tahoma", Font.PLAIN, 11));
            spnAltura.setModel(new SpinnerNumberModel(new Integer(163), new Integer(163), null, new Integer(1)));
            spnAltura.setBounds(421, 74, 162, 20);
            panel.add(spnAltura);

            JLabel lblNmero = new JLabel("N\u00FAmero #:");
            lblNmero.setFont(new Font("Tahoma", Font.PLAIN, 11));
            lblNmero.setBounds(359, 103, 55, 16);
            panel.add(lblNmero);

            JButton btnCargarImagen = new JButton("Cargar Imagen");
            btnCargarImagen.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                    JFileChooser fc = new JFileChooser();
                    fc.setDialogTitle("Buscar imagen");

                    if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                        String nomImagen;
                        File arch = new File(fc.getSelectedFile().toString());

                        //rsscalelabel.RSScaleLabel.setScaleLabel(lblImagen, fc.getSelectedFile().toString());
                        try {

                            imagen = ImageIO.read(arch);
                            nomImagen = "imgjugadores/" + txtNombre.getText() + ".png";
                            ImageIO.write(imagen, "png", new File(nomImagen));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                }
            });
            btnCargarImagen.setFont(new Font("Tahoma", Font.PLAIN, 11));
            btnCargarImagen.setBounds(643, 192, 110, 26);
            panel.add(btnCargarImagen);

            JPanel panel_imagen = new JPanel();
            panel_imagen.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
            panel_imagen.setBounds(619, 30, 156, 138);
            panel.add(panel_imagen);
            panel_imagen.setLayout(null);

            lblImagen = new JLabel("");
            lblImagen.setBounds(0, 0, 156, 138);
            panel_imagen.add(lblImagen);

            spnNumero = new JSpinner();
            spnNumero.setModel(new SpinnerNumberModel(0, 0, 999, 1));
            spnNumero.setFont(new Font("Tahoma", Font.PLAIN, 11));
            spnNumero.setBounds(421, 101, 162, 20);
            panel.add(spnNumero);
        }
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                JButton btnRegistrar = new JButton("Registrar");
                btnRegistrar.setFont(new Font("Tahoma", Font.PLAIN, 11));
                btnRegistrar.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String nom, fecha, lanz, bat, pais;
                        int pos, equipo;
                        boolean val = false;
                        Date date;
                        int peso, altura, AB, D, H, HR, doble, triple, BB, SO, num;
                        int H_Pitch, D_Pitch, CL, BB_Pitch, HR_Pitch, SO_Pitch;

                        nom = txtNombre.getText();
                        date = fechaNacimiento.getDate();
                        Calendar cal = Calendar.getInstance();

                        /*DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
						fecha = format.format(date);*/
                        lanz = cbxLanzamiento.getSelectedItem().toString();
                        bat = cbxBateo.getSelectedItem().toString();
                        pais = cbxPais.getSelectedItem().toString();
                        pos = cbxPosicion.getSelectedIndex();
                        equipo = cbxEquipo.getSelectedIndex();
                        peso = Integer.parseInt(spnPeso.getValue().toString());
                        altura = Integer.parseInt(spnAltura.getValue().toString());
                        num = Integer.parseInt(spnNumero.getValue().toString());

                        //Estadisticas del Jugador de Campo
                        AB = Integer.parseInt(spnAB.getValue().toString());
                        D = Integer.parseInt(spnD.getValue().toString());
                        H = Integer.parseInt(spnH.getValue().toString());
                        HR = Integer.parseInt(spnHR.getValue().toString());
                        doble = Integer.parseInt(spn2B.getValue().toString());
                        triple = Integer.parseInt(spn3B.getValue().toString());
                        BB = Integer.parseInt(spnBB.getValue().toString());
                        SO = Integer.parseInt(spnSO.getValue().toString());

                        //Estadisticas del Pitcher
                        H_Pitch = Integer.parseInt(spnH_Pitch.getValue().toString());
                        D_Pitch = Integer.parseInt(spnD_Pitch.getValue().toString());
                        CL = Integer.parseInt(spnCL.getValue().toString());
                        BB_Pitch = Integer.parseInt(spnBB_Pitch.getValue().toString());
                        HR_Pitch = Integer.parseInt(spnHR_Pitch.getValue().toString());
                        SO_Pitch = Integer.parseInt(spnSO_Pitch.getValue().toString());

                        try {
                            Connection db = DriverManager.getConnection("jdbc:sqlserver://192.168.77.24:1433;database=proyectoLigaBeisbol_grupo3", "jhernandez", "Junior2000");
                            Statement st = db.createStatement();

                            ResultSet rs;
                            rs = st.executeQuery("SELECT * FROM Jugador WHERE nombre='" + nom + "' AND numero='" + num + "'");
                            if (nom.isEmpty() || date == null || cbxLanzamiento.getSelectedIndex() == 0 || cbxBateo.getSelectedIndex() == 0
                                    || cbxPais.getSelectedIndex() == 0 || cbxPosicion.getSelectedIndex() == 0 || peso == 0 || altura == 0) {
                                JOptionPane.showMessageDialog(null, "Has dejado campos vac�os o sin seleccionar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                            } else if (fechaNacimiento.getDate().getYear() >= cal.get(Calendar.YEAR)
                                    || (cal.get(Calendar.YEAR) - fechaNacimiento.getDate().getYear()) < 16) {
                                JOptionPane.showMessageDialog(null, "El jugador debe tener una fecha de nacimiento equivalente a 16 a�os o m�s.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                            } else if (rs.isBeforeFirst()) {
                                JOptionPane.showMessageDialog(null, "Ya existe un jugador con ese nombre y numero, se pasara a ser modificado", "Informaci�n", JOptionPane.INFORMATION_MESSAGE);
                                Modificar();
                            } else {
                                JOptionPane.showMessageDialog(null, "Se ha creado el jugador correctamente", "Informaci�n", JOptionPane.INFORMATION_MESSAGE);
                                Estadistica_campo(AB,D,H,HR,doble,triple,BB,SO);
                                rs = st.executeQuery("INSERT INTO Jugador(nombre, fecha_nacimento, lanzamiento, bateo, pais, peso, altura, codigo_posc, codigo_equipo, numero)  " + "VALUES ('" + nom + "','" + new java.sql.Date(cal.getTimeInMillis()) + "','" + lanz + "','" + bat + "','" + pais + "','" + peso + "','" + altura + "','" + pos + "','" + equipo + "','" + num + "')");
                                
                                
                            }
                        } catch (SQLException a) {
                            System.out.println("Error " + a.getMessage());
                        }

                        /*if (modi == false) {
                            val = Administracion.getInstancia().buscarNumJug(Administracion.getInstancia().getMisEquipos().get(cbxEquipo.getSelectedIndex()), num);
                        }

                        if (nom.isEmpty() || date == null || cbxLanzamiento.getSelectedIndex() == 0 || cbxBateo.getSelectedIndex() == 0
                                || cbxPais.getSelectedIndex() == 0 || cbxPosicion.getSelectedIndex() == 0 || peso == 0 || altura == 0) {
                            JOptionPane.showMessageDialog(null, "Has dejado campos vac�os o sin seleccionar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                        } else if (fechaNacimiento.getCalendar().get(Calendar.YEAR) >= cal.get(Calendar.YEAR)
                                || (cal.get(Calendar.YEAR) - fechaNacimiento.getCalendar().get(Calendar.YEAR)) < 16) {
                            JOptionPane.showMessageDialog(null, "El jugador debe tener una fecha de nacimiento equivalente a 16 a�os o m�s.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                        } else if (val == true) {
                            JOptionPane.showMessageDialog(null, "Ya hay un jugador con este n�mero.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                        } else if (index != 8) {
                            int edad = (cal.get(Calendar.YEAR) - fechaNacimiento.getCalendar().get(Calendar.YEAR));
                            JugCampo jc = new JugCampo(nom, date, peso, altura, lanz, bat, pais, pos, equipo, null, num, edad);
                            estad = new Estadistica(AB, D, H, HR, doble, triple, BB, SO, 0, 0, 0);

                            if (AB > 0 && H > 0) {
                                estad.AVG(H, AB);
                            }

                            jc.setEstad(estad);

                            if (modi == true) {
                                Administracion.getInstancia().getMisEquipos().get(MiEquipo).getJugadores().set(MiJugador, jc);
                                Administracion.getInstancia().Guardar(Administracion.getInstancia());

                                if (imgjug.exists()) {
                                    try {
                                        imagen = ImageIO.read(imgjug);
                                        imgjug.delete();
                                        imgjug = new File("imgjugadores/" + Administracion.getInstancia().getMisEquipos().get(MiEquipo).getJugadores().get(MiJugador).getNombre() + ".png");
                                        ImageIO.write(imagen, "png", new File(imgjug.toString()));
                                    } catch (IOException e1) {
                                        // TODO Auto-generated catch block
                                        e1.printStackTrace();
                                    }
                                }

                                JOptionPane.showMessageDialog(null, "Se modific� el jugador con exito.");
                                dispose();
                            } else {
                                int input;
                                Administracion.getInstancia().getMisEquipos().get(cbxEquipo.getSelectedIndex()).getJugadores().add(jc);
                                Administracion.getInstancia().Guardar(Administracion.getInstancia());
                                JOptionPane.showMessageDialog(null, "Se registr� el jugador con exito.");
                                input = JOptionPane.showConfirmDialog(null, "�Desea registrar otro jugador?", "Confirmaci�n", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);

                                if (input == 0) {
                                    VaciarCampos();
                                } else {
                                    dispose();
                                }
                            }

                        } else {
                            int edad = (cal.get(Calendar.YEAR) - fechaNacimiento.getCalendar().get(Calendar.YEAR));
                            Pitcher pit = new Pitcher(nom, date, peso, altura, lanz, bat, pais, pos, equipo, null, num, edad);
                            estadPit = new EstadPitcher(0, H_Pitch, D_Pitch, CL, HR_Pitch, BB_Pitch, SO_Pitch, 0, 0, 0, 0);
                            pit.setEstad(estadPit);

                            if (modi == true) {
                                Administracion.getInstancia().getMisEquipos().get(MiEquipo).getJugadores().set(MiJugador, pit);
                                Administracion.getInstancia().Guardar(Administracion.getInstancia());
                                if (imgjug.exists()) {
                                    try {
                                        imagen = ImageIO.read(imgjug);
                                        imgjug.delete();
                                        imgjug = new File("imgjugadores/" + Administracion.getInstancia().getMisEquipos().get(MiEquipo).getJugadores().get(MiJugador).getNombre() + ".png");
                                        ImageIO.write(imagen, "png", new File(imgjug.toString()));
                                    } catch (IOException e1) {
                                        e1.printStackTrace();
                                    }
                                }

                                JOptionPane.showMessageDialog(null, "Se modific� el jugador con exito.");
                                dispose();
                            } else {
                                int input;
                                Administracion.getInstancia().getMisEquipos().get(cbxEquipo.getSelectedIndex()).getJugadores().add(pit);
                                Administracion.getInstancia().Guardar(Administracion.getInstancia());
                                JOptionPane.showMessageDialog(null, "Se registr� el jugador con exito.");
                                input = JOptionPane.showConfirmDialog(null, "�Desea registrar otro jugador?", "Confirmaci�n", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);

                                if (input == 0) {
                                    VaciarCampos();
                                } else {
                                    dispose();
                                }
                            }

                        }*/
                    }
                });
                btnRegistrar.setActionCommand("");
                buttonPane.add(btnRegistrar);
                getRootPane().setDefaultButton(btnRegistrar);
            }
            {
                JButton btnModificar = new JButton("Modificar");
                btnModificar.setFont(new Font("Tahoma", Font.PLAIN, 11));
                buttonPane.add(btnModificar);
                btnModificar.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String nom, fecha, lanz, bat, pais;
                        int pos;
                        boolean val = false;
                        Date date;
                        int peso, altura, num;

                        nom = txtNombre.getText();
                        date = fechaNacimiento.getDate();
                        Calendar cal = Calendar.getInstance();

                        /*DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
						fecha = format.format(date);*/
                        lanz = cbxLanzamiento.getSelectedItem().toString();
                        bat = cbxBateo.getSelectedItem().toString();
                        pais = cbxPais.getSelectedItem().toString();
                        pos = cbxPosicion.getSelectedIndex();
                        peso = Integer.parseInt(spnPeso.getValue().toString());
                        altura = Integer.parseInt(spnAltura.getValue().toString());
                        num = Integer.parseInt(spnNumero.getValue().toString());
                        try {
                            Connection db = DriverManager.getConnection("jdbc:sqlserver://192.168.77.24:1433;database=proyectoLigaBeisbol_grupo3", "jhernandez", "Junior2000");
                            Statement st = db.createStatement();
                            ResultSet rs;
                            rs = st.executeQuery("SELECT * FROM Jugador WHERE nombre='" + nom + "' AND numero='" + num + "'");
                            if (nom.isEmpty() || date == null || cbxLanzamiento.getSelectedIndex() == 0 || cbxBateo.getSelectedIndex() == 0
                                    || cbxPais.getSelectedIndex() == 0 || cbxPosicion.getSelectedIndex() == 0 || peso == 0 || altura == 0) {
                                JOptionPane.showMessageDialog(null, "Has dejado campos vac�os o sin seleccionar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                            } else if (fechaNacimiento.getDate().getYear() >= cal.get(Calendar.YEAR)
                                    || (cal.get(Calendar.YEAR) - fechaNacimiento.getDate().getYear()) < 16) {
                                JOptionPane.showMessageDialog(null, "El jugador debe tener una fecha de nacimiento equivalente a 16 a�os o m�s.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                            } else if (rs.isBeforeFirst() == false) {
                                JOptionPane.showMessageDialog(null, "Este jugador no existe", "Informaci�n", JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(null, "Se ha actualizado el jugador correctamente", "Informaci�n", JOptionPane.INFORMATION_MESSAGE);
                                PreparedStatement ts = db.prepareStatement("UPDATE Jugador SET numero = ?, fecha_nacimento = ?, peso = ?, altura = ?, bateo = ?, lanzamiento = ?, pais = ?, codigo_posc =  ? WHERE nombre = ?");
                                ts.setInt(1, num);
                                ts.setDate(2, new java.sql.Date(cal.getTimeInMillis()));
                                ts.setInt(3, peso);
                                ts.setInt(4, altura);
                                ts.setString(5, bat);
                                ts.setString(6, lanz);
                                ts.setString(7, pais);
                                ts.setInt(8, pos);
                                ts.setString(9, nom);
                                ts.executeUpdate();
                            }
                        } catch (SQLException a) {
                            System.out.println("Error " + a.getMessage());
                        }
                    }
                });
            }
            {
                JButton btnCancelar = new JButton("Cancelar");
                btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 11));
                btnCancelar.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                    }
                });
                btnCancelar.setActionCommand("Cancel");
                buttonPane.add(btnCancelar);
            }

        }
    }

    public void Estadistica_campo(int AB, int D, int H, int HR, int doble, int triple, int BB, int SO) {
        try {
            Connection db = DriverManager.getConnection("jdbc:sqlserver://192.168.77.24:1433;database=proyectoLigaBeisbol_grupo3", "jhernandez", "Junior2000");
            Statement st = db.createStatement();
            ResultSet rs;
            rs = st.executeQuery("SELECT MAX(codigo_jugador) as todo FROM Jugador");
            while (rs.next()) {
                int fcod = rs.getInt("todo") + 1;
                System.out.println(fcod);
                PreparedStatement ts = db.prepareStatement("INSERT INTO Estadistica(AB, D, H, DOSB, TRESB, BB, HR, SO, codigo_jugador) VALUES (?,?,?,?,?,?,?,?,?)");
                ts.setInt(1, AB);
                ts.setInt(2, D);
                ts.setInt(3, H);
                ts.setInt(4, HR);
                ts.setInt(5, doble);
                ts.setInt(6, triple);
                ts.setInt(7, BB);
                ts.setInt(8, SO);
                ts.setInt(9, fcod);
                ts.executeQuery();
            }

        } catch (SQLException a) {
            System.out.println("Error " + a.getMessage());
        }
    }

    public void Modificar() {
        String nom;
        int num;
        setTitle("Modificar jugador");
        nom = txtNombre.getText();
        num = Integer.parseInt(spnNumero.getValue().toString());
        try {
            Connection db = DriverManager.getConnection("jdbc:sqlserver://192.168.77.24:1433;database=proyectoLigaBeisbol_grupo3", "jhernandez", "Junior2000");
            Statement st = db.createStatement();
            ResultSet rs;
            rs = st.executeQuery("SELECT * FROM Jugador WHERE nombre='" + nom + "' AND numero='" + num + "'");
            while (rs.next()) {
                String fname = rs.getString("nombre");
                Date ffecha = rs.getDate("fecha_nacimento");
                int fpeso = rs.getInt("peso");
                String fbat = rs.getString("bateo");
                String flaz = rs.getString("lanzamiento");
                String fpais = rs.getString("pais");
                int faltura = rs.getInt("altura");
                int fpos = rs.getInt("codigo_posc");
                int fequipo = rs.getInt("codigo_equipo");
                int fnumero = rs.getInt("numero");
                txtNombre.setText(fname);
                cbxPais.setSelectedItem(fpais);
                fechaNacimiento.setDate(ffecha);
                cbxPosicion.setSelectedIndex(fpos);
                spnPeso.setValue(fpeso);
                spnAltura.setValue(faltura);
                cbxLanzamiento.setSelectedItem(flaz);
                spnNumero.setValue(fnumero);
                cbxBateo.setSelectedItem(fbat);
                cbxEquipo.setSelectedIndex(fequipo);
                cbxEquipo.setEnabled(false);
            }
        } catch (SQLException a) {
            System.out.println("Error " + a.getMessage());
        }

        /* if (Administracion.getInstancia().getMisEquipos().get(MiEquipo).getJugadores().get(MiJugador) instanceof JugCampo) {
            Jugador jugc = (JugCampo) Administracion.getInstancia().getMisEquipos().get(MiEquipo).getJugadores().get(MiJugador);
            spnHR.setValue(((JugCampo) jugc).getEstad().getHR());
            spnAB.setValue(((JugCampo) jugc).getEstad().getAB());
            spnBB.setValue(((JugCampo) jugc).getEstad().getBB());
            spn2B.setValue(((JugCampo) jugc).getEstad().getDobles());
            spn3B.setValue(((JugCampo) jugc).getEstad().getTriples());
            spnD.setValue(((JugCampo) jugc).getEstad().getD());
            spnH.setValue(((JugCampo) jugc).getEstad().getH());
            spnSO.setValue(((JugCampo) jugc).getEstad().getPonches());
        } else {
            Jugador jugp = (Pitcher) Administracion.getInstancia().getMisEquipos().get(MiEquipo).getJugadores().get(MiJugador);
            spnHR_Pitch.setValue(((Pitcher) jugp).getEstad().getJonronPitch());
            spnBB_Pitch.setValue(((Pitcher) jugp).getEstad().getBBPitch());
            spnD_Pitch.setValue(((Pitcher) jugp).getEstad().getCarrPitch());
            spnH_Pitch.setValue(((Pitcher) jugp).getEstad().getHitsPitch());
            spnSO_Pitch.setValue(((Pitcher) jugp).getEstad().getPonches());
            spnCL.setValue(((Pitcher) jugp).getEstad().getCarrLimpias());
        }

        imgjug = new File("imgjugadores/" + Administracion.getInstancia().getMisEquipos().get(MiEquipo).getJugadores().get(MiJugador).getNombre() + ".png");

        if (imgjug.exists()) {
            //rsscalelabel.RSScaleLabel.setScaleLabel(lblImagen, imgjug.toString());
        }
         */
    }

    public void VaciarCampos() {
        txtNombre.setText("");
        cbxPais.setSelectedIndex(0);
        fechaNacimiento.setDate(null);
        cbxPosicion.setSelectedIndex(0);
        spnPeso.setValue(80);
        spnAltura.setValue(163);
        cbxLanzamiento.setSelectedIndex(0);
        spnNumero.setValue(0);
        cbxBateo.setSelectedIndex(0);
        cbxEquipo.setSelectedIndex(0);

        spnAB.setValue(0);
        spnD.setValue(0);
        spn2B.setValue(0);
        spn3B.setValue(0);
        spnBB.setValue(0);
        spnH.setValue(0);
        spnSO.setValue(0);
        spnHR.setValue(0);

        spnBB_Pitch.setValue(0);
        spnCL.setValue(0);
        spnD_Pitch.setValue(0);
        spnH_Pitch.setValue(0);
        spnHR_Pitch.setValue(0);
        spnSO_Pitch.setValue(0);

        lblImagen.setIcon(null);
    }
}
