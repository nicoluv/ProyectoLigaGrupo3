package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logic.Administracion;
import logic.Equipo;
import logic.Jugador;
import logic.Pitcher;

import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static visual.Simulacion.modelPitcherLocal;

public class CambiarPitcher extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JTable table;
    public static DefaultTableModel model;
    public static Object[] fila;
    private JButton okButton;
    private static int index = 0;
    private static int MiEquipo;

    public CambiarPitcher(int e) {
        MiEquipo = e;
        setTitle("Cambio de Pitcher");
        setBounds(100, 100, 659, 370);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new BorderLayout(0, 0));
        {
            JPanel panel = new JPanel();
            panel.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Pitchers del Equipo", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
            contentPanel.add(panel, BorderLayout.CENTER);
            panel.setLayout(null);
            {
                JScrollPane scrollPane = new JScrollPane();
                scrollPane.setBounds(12, 25, 611, 248);
                panel.add(scrollPane);

                String[] header = {"codigo","Nombre", "Numero", "Carreras", "Hits", "Jonrones", "PromCL", "Estado"};
                model = new DefaultTableModel();
                model.setColumnIdentifiers(header);
                table = new JTable();
                table.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (table.getSelectedRow() >= 0) {
                            okButton.setEnabled(true);
                            index = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());
                        }
                    }
                });
                table.setModel(model);
                scrollPane.setViewportView(table);
                loadTable();
            }
        }
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                okButton = new JButton("Seleccionar");
                okButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        index = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());
                        if ("Lesionado".equals(table.getValueAt(table.getSelectedRow(), 7).toString())) {
                            JOptionPane.showMessageDialog(null, "El Pitcher se encuentra lesionado");
                        } else {
                            dispose();
                        }

                    }
                });
                okButton.setActionCommand("OK");
                buttonPane.add(okButton);
                getRootPane().setDefaultButton(okButton);
            }
            {
                JButton cancelButton = new JButton("Cerrar");
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

    public static void loadTable() {
        model.setRowCount(0);
        fila = new Object[model.getColumnCount()];
        try {
            Connection db = DriverManager.getConnection("jdbc:sqlserver://192.168.77.24:1433;database=proyectoLigaBeisbol_grupo3", "jhernandez", "Junior2000");
            Statement st = db.createStatement();
            ResultSet rs;
            rs = st.executeQuery("SELECT Partido.* FROM Equipo, Partido ");
            while (rs.next()) {
                int fcod = rs.getInt("codigo_equipoLocal");
                int fcod2 = rs.getInt("codigo_equipoVisitante");
                if (fcod == MiEquipo) {
                    try {
                        ResultSet res;
                        res = st.executeQuery("SELECT Jugador.*, EstadisticaPitcher.*, Partido.carrera_local FROM Jugador, EstadisticaPitcher, Equipo, Partido WHERE Jugador.codigo_jugador = EstadisticaPitcher.codigo_jugador AND Jugador.codigo_equipo = Equipo.codigo_equipo AND Partido.codigo_equipoLocal = Equipo.codigo_equipo AND Partido.codigo_equipoLocal = Jugador.codigo_equipo AND Jugador.codigo_equipo =  " + MiEquipo + "");
                        while (res.next()) {
                            int fcodito = res.getInt("codigo_jugador");
                            String fname = res.getString("nombre");
                            int fnum = res.getInt("numero");
                            int fcl = res.getInt("CL");
                            int fhr = res.getInt("HR");
                            int fd = res.getInt("D");
                            int festado = res.getInt("estado_fisico");
                            fila[0] = fcodito;
                            fila[1] = fname;
                            fila[2] = fnum;
                            fila[3] = fcl;
                            fila[4] = fd;
                            fila[5] = fhr;
                            fila[6] = fcl+fhr+fd/3;
                            if(festado == 0){
                                fila[7] ="Lesionado";
                            }
                            else
                            {
                                fila[7] ="En forma";
                            }
                            
                            model.addRow(fila);
                        }
                    } catch (SQLException a) {
                        System.out.println("Error " + a.getMessage());
                    }
                } else if (fcod2 == MiEquipo) {
                    try {        
                        ResultSet res;
                        res = st.executeQuery("SELECT Jugador.*, EstadisticaPitcher.*, Partido.carrera_visitante FROM Jugador, EstadisticaPitcher, Equipo, Partido WHERE Jugador.codigo_jugador = EstadisticaPitcher.codigo_jugador AND Jugador.codigo_equipo = Equipo.codigo_equipo AND Partido.codigo_equipoVisitante = Equipo.codigo_equipo AND Partido.codigo_equipoVisitante = Jugador.codigo_equipo AND Jugador.codigo_equipo = " + MiEquipo + "");
                        while (res.next()) {
                            int fcodito = res.getInt("codigo_jugador");
                            String fname = res.getString("nombre");
                            int fnum = res.getInt("numero");
                            int fcl = res.getInt("CL");
                            int fhr = res.getInt("HR");
                            int fd = res.getInt("D");
                            int festado = res.getInt("estado_fisico");
                            fila[0] = fcodito;
                            fila[1] = fname;
                            fila[2] = fnum;
                            fila[3] = fcl;
                            fila[4] = fd;
                            fila[5] = fhr;
                            fila[6] = fcl+fhr+fd/3;
                            if(festado == 0){
                                fila[7] ="Lesionado";
                            }
                            else
                            {
                                fila[7] ="En forma";
                            }
                            model.addRow(fila);
                        }
                    } catch (SQLException a) {
                        System.out.println("Error " + a.getMessage());
                    }
                }
            }
        } catch (SQLException a) {
            System.out.println("Error " + a.getMessage());
        }

    }
    public static int retorno() {
        return index;
    }

}
