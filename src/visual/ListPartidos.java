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

                String[] header = {"Local", "Visitante", "Estadio", "Hora", "Fecha", "Estado", "Marcador", "Ganador"};
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
                        if (Administracion.getInstancia().getMisPartidos().size() != 0) {
                            int input = JOptionPane.showConfirmDialog(null, "�Seguro que desea eliminar el partido?", "Confirmaci�n", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);

                            if (input == 0 || Administracion.getInstancia().getMisPartidos().size() > 0) {
                                Administracion.getInstancia().getMisPartidos().remove(index);
                                Administracion.getInstancia().Guardar(Administracion.getInstancia());
                                JOptionPane.showMessageDialog(null, "El partido ha sido eliminado.", "Informaci�n", JOptionPane.INFORMATION_MESSAGE);
                                loadTable();
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "No has seleccionado un partido.", "Aviso", JOptionPane.WARNING_MESSAGE);
                        }
                    }
                });
                {
                    btnJugarPartido = new JButton("Jugar partido");
                    btnJugarPartido.setEnabled(false);
                    btnJugarPartido.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            if (Administracion.getInstancia().getMisPartidos().size() > 0) {
                                int indexLocal, indexVis;

                                indexLocal = Administracion.getInstancia().findEquipo(Administracion.getInstancia().getMisPartidos().get(index).getLocal().getNombre());
                                indexVis = Administracion.getInstancia().findEquipo(Administracion.getInstancia().getMisPartidos().get(index).getVisitante().getNombre());
                                if (Administracion.getInstancia().getMisEquipos().get(indexLocal).getJugadores().size() >= 9 && Administracion.getInstancia().getMisEquipos().get(indexVis).getJugadores().size() >= 9) {
                                    if (Administracion.getInstancia().getMisPartidos().get(index).isEstado()) {
                                        Simulacion sim = new Simulacion(indexLocal, indexVis, index);
                                        sim.setModal(true);
                                        sim.setVisible(true);
                                        Administracion.getInstancia().getMisPartidos().get(index).setEstado(false);
                                        loadTable();
                                    } else {
                                        JOptionPane.showMessageDialog(null, "El partido ya se jugo.", "Aviso", JOptionPane.WARNING_MESSAGE);

                                    }
                                } else {
                                    JOptionPane.showMessageDialog(null, "Necesita un minimo de 9 jugadores en cada equipo", "Aviso", JOptionPane.WARNING_MESSAGE);
                                }

                            } else {
                                JOptionPane.showMessageDialog(null, "No has seleccionado un partido.", "Aviso", JOptionPane.WARNING_MESSAGE);
                            }
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

    public String estadi(String s) {
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
        Date date;
        String fecha;

        fila = new Object[model.getColumnCount()];
        
        try {
            Connection db = DriverManager.getConnection("jdbc:sqlserver://192.168.77.24:1433;database=proyectoLigaBeisbol_grupo3", "jhernandez", "Junior2000");
            Statement st = db.createStatement();
            ResultSet rs;
            rs = st.executeQuery("SELECT * FROM Partido WHERE Jugador.codigo_posc = Posicion.codigo_posc AND Jugador.codigo_equipo = '" + s + "'");
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

        for (int i = 0; i < Administracion.getInstancia().getMisPartidos().size(); i++) {
            fila[0] = Administracion.getInstancia().getMisPartidos().get(i).getLocal().getNombre();
            fila[1] = Administracion.getInstancia().getMisPartidos().get(i).getVisitante().getNombre();
            fila[2] = Administracion.getInstancia().getMisPartidos().get(i).getEstadio();
            fila[3] = Administracion.getInstancia().getMisPartidos().get(i).getHora();

            date = Administracion.getInstancia().getMisPartidos().get(i).getFecha();
            fecha = format.format(date);

            fila[4] = fecha;
            if (Administracion.getInstancia().getMisPartidos().get(i).isEstado() == true) {
                fila[5] = "Pendiente";
            } else {
                fila[5] = "Finalizado";
            }
            if (Administracion.getInstancia().getMisPartidos().get(i).isEstado() == true) {
                fila[6] = "0 - 0";
            } else {
                fila[6] = Administracion.getInstancia().getMisPartidos().get(i).getCarrLoc() + " - " + Administracion.getInstancia().getMisPartidos().get(i).getCarrVis();

            }
            if (Administracion.getInstancia().getMisPartidos().get(i).isEstado() == true) {
                fila[7] = "Por Definir";
            } else {
                fila[7] = Administracion.getInstancia().getMisPartidos().get(i).getGanador();
            }

            model.addRow(fila);
        }

    }

}
