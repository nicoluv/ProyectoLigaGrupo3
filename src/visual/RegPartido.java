package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.toedter.calendar.JDateChooser;

import logic.Administracion;
import logic.Equipo;
import logic.Jugador;
import logic.Partido;

import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.border.BevelBorder;

public class RegPartido extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JDateChooser dateChooser;
	private JComboBox cbxHora;
	private JComboBox cbxLocal;
	private JComboBox cbxVisitante;
	
	public RegPartido() {
		
		setResizable(false);
		setTitle("Registrar  Partido");
		setBounds(100, 100, 610, 192);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			
			JLabel lblFechaDelPartido = new JLabel("Fecha del partido:");
			lblFechaDelPartido.setFont(new Font("Tahoma", Font.PLAIN, 11));
			lblFechaDelPartido.setBounds(10, 25, 114, 14);
			panel.add(lblFechaDelPartido);
			
			dateChooser = new JDateChooser();
			dateChooser.setBounds(134, 25, 134, 20);
			panel.add(dateChooser);
			
			JLabel lblHoraDelPartido = new JLabel("Hora del partido:");
			lblHoraDelPartido.setFont(new Font("Tahoma", Font.PLAIN, 11));
			lblHoraDelPartido.setBounds(10, 60, 93, 16);
			panel.add(lblHoraDelPartido);
			
			cbxHora = new JComboBox();
			cbxHora.setModel(new DefaultComboBoxModel(new String[] {"8:00 a.m.", "9:00 a.m.", "10:00 a.m.", "11:00 a.m.", "12:00 p.m.", "1:00 p.m.", "2:00 p.m.", "3:00 p.m.", "4:00 p.m.", "5:00 p.m.", "6:00 p.m.", "7:00 p.m.", "8:00 p.m."}));
			cbxHora.setFont(new Font("Tahoma", Font.PLAIN, 11));
			cbxHora.setBounds(134, 58, 134, 20);
			panel.add(cbxHora);
			
			JLabel lblEquipoLocal = new JLabel("Equipo local:");
			lblEquipoLocal.setFont(new Font("Tahoma", Font.PLAIN, 11));
			lblEquipoLocal.setBounds(337, 24, 75, 16);
			panel.add(lblEquipoLocal);
			
			ArrayList<String> nomEquipos = new ArrayList<String>();
			nomEquipos.add("<Seleccionar>");
			
			for (Equipo i : Administracion.getInstancia().getMisEquipos()) {
				nomEquipos.add(i.getNombre());
			}
			
			cbxLocal = new JComboBox(nomEquipos.toArray());
			cbxLocal.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(cbxLocal.getSelectedIndex() != 0) {
						String aux = cbxLocal.getSelectedItem().toString();
						cbxVisitante.setEnabled(true);
						cbxVisitante.removeAllItems();
						
						for (String i : nomEquipos) {
							if(i != aux) {
								cbxVisitante.addItem(i);
							}
						}
					}
					else {
						cbxVisitante.setSelectedIndex(0);
						cbxVisitante.setEnabled(false);
					}
				}
			});
			cbxLocal.setFont(new Font("Tahoma", Font.PLAIN, 11));
			cbxLocal.setBounds(440, 22, 134, 20);
			panel.add(cbxLocal);
			
			JLabel lblEquipoVisitante = new JLabel("Equipo visitante:");
			lblEquipoVisitante.setFont(new Font("Tahoma", Font.PLAIN, 11));
			lblEquipoVisitante.setBounds(337, 60, 93, 16);
			panel.add(lblEquipoVisitante);
			
			cbxVisitante = new JComboBox();
			cbxVisitante.addItem("<Seleccionar>");
			cbxVisitante.setEnabled(false);
			cbxVisitante.setFont(new Font("Tahoma", Font.PLAIN, 11));
			cbxVisitante.setBounds(440, 58, 134, 20);
			panel.add(cbxVisitante);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnRegistrar = new JButton("Registrar");
				btnRegistrar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						Date fecha;
						String hora, estadio;
						Equipo local, visitante;
						
						fecha = dateChooser.getDate();
						hora = cbxHora.getSelectedItem().toString();
						local = Administracion.getInstancia().buscarEquipo(cbxLocal.getSelectedItem().toString());
						visitante = Administracion.getInstancia().buscarEquipo(cbxVisitante.getSelectedItem().toString());
						
						if(fecha == null || hora.isEmpty() || local == null || visitante == null) {
							JOptionPane.showMessageDialog(null, "Has dejado campos vac�os.","Aviso",JOptionPane.WARNING_MESSAGE);
						}
						else {
							int cont1 = 0,cont2 = 0;
							
							for (Jugador i : local.getMisJugadores()) {
								if(i.isEstado() == true) {
									cont1++;
								}
							}
							
							for (Jugador i : visitante.getMisJugadores()) {
								if(i.isEstado() == true) {
									cont2++;
								}
							}
							
							if(cont1 >= 9 && cont2 >= 9) {
								estadio = local.getEstadio();
								Partido p = new Partido(visitante,local,estadio,0,0,0,0,0,0,hora,fecha);
								Administracion.getInstancia().getMisPartidos().add(p);
								Administracion.getInstancia().Guardar(Administracion.getInstancia());
								JOptionPane.showMessageDialog(null, "Se registr� el partido con �xito.","Informaci�n",JOptionPane.INFORMATION_MESSAGE);
							}
							else {
								JOptionPane.showMessageDialog(null, "Uno de los equipos no tiene jugadores activos suficientes.","Aviso",JOptionPane.WARNING_MESSAGE);
							}
						}
					}
				});
				btnRegistrar.setFont(new Font("Tahoma", Font.PLAIN, 11));
				btnRegistrar.setActionCommand("OK");
				buttonPane.add(btnRegistrar);
				getRootPane().setDefaultButton(btnRegistrar);
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
}
