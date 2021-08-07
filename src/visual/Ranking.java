package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
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

import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static visual.ListEquipos.fila;
import static visual.ListEquipos.model;

public class Ranking extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	public static DefaultTableModel model;
	public static Object[] fila;
	public static ArrayList<Equipo> Ranking = new ArrayList();
	private JButton okButton;
	private int index;


	public Ranking() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				Ranking.clear();
			}
		});
		setTitle("Tabla de Reporte de Pocisiones ");
		setBounds(100, 100, 811, 477);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Posiciones Temporada Regular", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			{
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setBounds(12, 25, 759, 349);
				panel.add(scrollPane);
				
		
				
				String[] header = {"Nombre", "Manager", "Juegos Jugados", "Juegos Ganados", "Juegos Perdidos"};
				model = new DefaultTableModel();
				model.setColumnIdentifiers(header);
				table = new JTable();
				table.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						if(table.getSelectedRow()>= 0) {
							okButton.setEnabled(true);
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
				okButton = new JButton("Perfil del Equipo");
				okButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						index = table.getSelectedRow();
						VerEquipo VE = new VerEquipo(index);
						VE.setModal(true);
						VE.setVisible(true);
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cerrar");
				cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Ranking.clear();
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}


	public static void loadTable() {
		// TODO Auto-generated method stub
		model.setRowCount(0);
		fila = new Object[model.getColumnCount()];
                
                        try {
            Connection db = DriverManager.getConnection("jdbc:sqlserver://192.168.77.24:1433;database=proyectoLigaBeisbol_grupo3", "jhernandez", "Junior2000");
            Statement st = db.createStatement();
            ResultSet rs;
            rs = st.executeQuery("select Equipo.nombre_equipo, Equipo.manager, count(CASE WHEN Equipo.codigo_equipo = Partido.codigo_equipoLocal or Equipo.codigo_equipo = Partido.codigo_equipoVisitante THEN 1 END) Juegos_jugados,Sum(CASE WHEN Equipo.codigo_equipo = Partido.codigo_equipoLocal and Partido.carrera_local > Partido.carrera_visitante THEN 1\n" +
"WHEN Equipo.codigo_equipo = Partido.codigo_equipoVisitante and Partido.carrera_local < Partido.carrera_visitante THEN 1\n" +
"END) as Juegos_Ganados, Sum(CASE WHEN Equipo.codigo_equipo = Partido.codigo_equipoVisitante and Partido.carrera_local > Partido.carrera_visitante THEN 1\n" +
"WHEN Equipo.codigo_equipo = Partido.codigo_equipoLocal and Partido.carrera_local < Partido.carrera_visitante THEN 1\n" +
"END) as Juegos_Perdidos from Equipo, Partido Group by nombre_equipo, manager");
            while (rs.next()) {
         
                
                String enombre_equipo = rs.getString("nombre_equipo");
                String emanager = rs.getString("manager");
                int played = rs.getInt("Juegos_jugados");
                int winner = rs.getInt("Juegos_Ganados");
                int looser = rs.getInt("Juegos_Perdidos");
                

                fila[0] = enombre_equipo;
                fila[1] = emanager;
                fila[2] = played;
                fila[3] = winner;
                fila[4] = looser;

                model.addRow(fila);
   
            }
        } catch (SQLException a) {
            System.out.println("Error " + a.getMessage());
        }
                
                
                
		}
	}
