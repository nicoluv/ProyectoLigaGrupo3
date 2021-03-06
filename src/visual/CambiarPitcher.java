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
				
				
				String[] header = {"Nombre", "Numero", "Carreras", "Hits", "Jonrones", "PromCL", "Estado"};
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
				okButton = new JButton("Seleccionar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String nombre = (String) table.getValueAt(index, 0);
						index = Administracion.getInstancia().findJugador(MiEquipo, nombre);
						if(Administracion.getInstancia().getMisEquipos().get(MiEquipo).getMisJugadores().get(index).isEstado()) {
							dispose();
						}
						else {
							JOptionPane.showMessageDialog(null, "El Pitcher se encuentra lesionado");
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
		for (Jugador jug : Administracion.getInstancia().getMisEquipos().get(MiEquipo).getMisJugadores()) {
			if(jug instanceof Pitcher) {
				fila[0] = jug.getNombre();
				fila[1] = jug.getNumero();
				fila[2] = ((Pitcher) jug).getEstad().getCarrPitch();
				fila[3] = ((Pitcher) jug).getEstad().getHitsPitch();
				fila[4] = ((Pitcher) jug).getEstad().getJonronPitch();
				fila[5] = ((Pitcher) jug).getEstad().getPromCL();
				if(jug.isEstado()) {
					fila[6] = "En Forma";
				}
				else {
					fila[6] = "Lesionado";
				}
				
				model.addRow(fila);
 			}
		}
		
	}
	
	public static int retorno() {
		return index;
	}






	
}