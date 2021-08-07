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
import static visual.ListJugadores.cbxEquipos;
import static visual.ListJugadores.fila;
import static visual.ListJugadores.loadTable;
import static visual.ListJugadores.model;

public class ListEquipos extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JTable table;
    public static DefaultTableModel model;
    public static Object[] fila;
    private JButton btnEliminar;
    private int index;

    public ListEquipos() {
        setTitle("Lista de equipos");

        setBounds(100, 100, 884, 510);
        setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new BorderLayout(0, 0));
        {
            JScrollPane scrollPane = new JScrollPane();
            contentPanel.add(scrollPane, BorderLayout.CENTER);
            {
                String[] header = {"codigo_equipo", "Estadio", "Estado", "manager", "nombre_equipo"};
                model = new DefaultTableModel();
                model.setColumnIdentifiers(header);

                table = new JTable();
                table.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent arg0) {
                        if (table.getSelectedRow() >= 0) {
                            btnEliminar.setEnabled(true);
                            index = table.getSelectedRow();
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
                btnEliminar = new JButton("Eliminar");
                btnEliminar.setEnabled(false);
                btnEliminar.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {

                        if (table.getSelectedRow() < 0) {
                            JOptionPane.showMessageDialog(null, "No hay elementos seleccionados.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            int input = JOptionPane.showConfirmDialog(null, "�Seguro que desea eliminar el equipo?", "Confirmaci�n", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);

                            if (input == 0) {

                                try {
                                    Connection db = DriverManager.getConnection("jdbc:sqlserver://192.168.77.24:1433;database=proyectoLigaBeisbol_grupo3", "jhernandez", "Junior2000");
                                    Statement st = db.createStatement();
                                    ResultSet rs;

                                    JOptionPane.showMessageDialog(null, "El equipo ha sido eliminado.", "Informaci�n", JOptionPane.INFORMATION_MESSAGE);
                                    dispose();
                                    PreparedStatement ts = db.prepareStatement("DELETE FROM Equipo WHERE codigo_equipo = ?");
                                    ts.setString(1, table.getValueAt(table.getSelectedRow(), table.getSelectedColumn()).toString());
                                    ts.executeQuery();
                                    loadTable();
                                } catch (SQLException a) {
                                    System.out.println("Error " + a.getMessage());
                                }
//                               
                            }
                        }
                    }
                });
                btnEliminar.setFont(new Font("Tahoma", Font.PLAIN, 11));
                buttonPane.add(btnEliminar);
            }
            {
                JButton btnVerEquipo = new JButton("Ver Equipo");
                btnVerEquipo.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        VerEquipo VE = new VerEquipo(index);
                        VE.setModal(true);
                        VE.setVisible(true);
                    }
                });
                btnVerEquipo.setFont(new Font("Tahoma", Font.PLAIN, 11));
                btnVerEquipo.setActionCommand("OK");
                buttonPane.add(btnVerEquipo);
                getRootPane().setDefaultButton(btnVerEquipo);
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

    public static void loadTable() {
        model.setRowCount(0);

        fila = new Object[model.getColumnCount()];

        try {
            Connection db = DriverManager.getConnection("jdbc:sqlserver://192.168.77.24:1433;database=proyectoLigaBeisbol_grupo3", "jhernandez", "Junior2000");
            Statement st = db.createStatement();
            ResultSet rs;
            rs = st.executeQuery("select Equipo.codigo_equipo,Equipo.estadio,Equipo.estado,Equipo.manager, Equipo.nombre_equipo from Equipo");
            while (rs.next()) {
                int ecodigo = rs.getInt("codigo_equipo");
                String eestadio = rs.getString("estadio");
                String eestado = rs.getString("estado");
                String emanager = rs.getString("manager");
                String enombre_equipo = rs.getString("nombre_equipo");

                fila[0] = ecodigo;
                fila[1] = eestadio;
                fila[2] = eestado;
                fila[3] = emanager;
                fila[4] = enombre_equipo;

                model.addRow(fila);

            }
        } catch (SQLException a) {
            System.out.println("Error " + a.getMessage());
        }

    }
}
