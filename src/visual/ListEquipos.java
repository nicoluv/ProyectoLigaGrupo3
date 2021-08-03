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
				String[] header = {"Nombre", "Estadio", "Provincia"};
				model = new DefaultTableModel();
				model.setColumnIdentifiers(header);
				
				table = new JTable();
				table.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						if(table.getSelectedRow()>=0){
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
						
						if(Administracion.getInstancia().getMisEquipos().size() == 0 || table.getSelectedRow() < 0) {
							JOptionPane.showMessageDialog(null, "No hay elementos seleccionados.","Aviso",JOptionPane.INFORMATION_MESSAGE);
						}
						else {
							int input = JOptionPane.showConfirmDialog(null, "¿Seguro que desea eliminar el equipo?","Confirmación",JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
							
							if(input == 0) {
								Administracion.getInstancia().getMisEquipos().remove(index);
								Administracion.getInstancia().Guardar(Administracion.getInstancia());
								JOptionPane.showMessageDialog(null, "El equipo ha sido eliminado.","Información",JOptionPane.INFORMATION_MESSAGE);
								loadTable();
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
		for (int i = 0; i < Administracion.getInstancia().getMisEquipos().size(); i++) {
			
			fila[0] = Administracion.getInstancia().getMisEquipos().get(i).getNombre();
			fila[1] = Administracion.getInstancia().getMisEquipos().get(i).getEstadio();
			fila[2] = Administracion.getInstancia().getMisEquipos().get(i).getProvincia();
			
			model.addRow(fila);
		}
		
	}
}
