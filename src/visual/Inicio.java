package visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logic.Administracion;

import java.awt.Color;
import java.awt.Dialog.ModalExclusionType;

import javax.swing.JButton;
import rsbuttom.RSButtonMetro;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import java.awt.Toolkit;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Inicio extends JFrame {

	private JPanel contentPane;
	private JPanel panel_izq;
	private JPanel panel_Nuevo;
	private JPanel panel_Gestion;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Inicio frame = new Inicio();
					frame.setVisible(true);
					Login log = new Login();
					log.setModal(true);
					log.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public Inicio() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Inicio.class.getResource("/imgiconos/LogoFondo.png")));
		
		Administracion x = Administracion.getInstancia().Cargar();
		
		if(x != null) {
			Administracion.setAdministracion(x);
		}
		
		setTitle("Gesti\u00F3n de estad\u00EDsticas de b\u00E9isbol");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1048, 689);
		setExtendedState(MAXIMIZED_BOTH);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		
		JPanel panel_superior = new JPanel();
		panel_superior.setBackground(new Color(0, 0, 128));
		panel_superior.setLayout(null);
		
		RSButtonMetro btnNuevo = new RSButtonMetro();
		btnNuevo.setText("Nuevo");
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				panel_Gestion.setVisible(false);
				panel_Nuevo.setVisible(true);
				
				
				
				try {
					Thread.sleep(150);
				}
				catch(InterruptedException e1){
					System.out.println("Thread Interrupted");
				}
				
				}
			}
		);
		btnNuevo.setToolTipText("Nuevo");
		btnNuevo.setColorHover(new Color(0, 102, 204));
		btnNuevo.setColorPressed(new Color(0, 0, 128));
		btnNuevo.setColorNormal(new Color(0, 0, 128));
		btnNuevo.setBackground(new Color(0, 0, 128));
		btnNuevo.setBounds(0, 0, 58, 52);
		panel_superior.add(btnNuevo);
		
		panel_izq = new JPanel();
		panel_izq.setBackground(SystemColor.menu);
		panel_izq.setLayout(null);
		
		JLabel labelLogo = new JLabel("");
		labelLogo.setIcon(new ImageIcon(Inicio.class.getResource("/imgiconos/FondoInicio.png")));
		labelLogo.setHorizontalAlignment(SwingConstants.CENTER);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addComponent(panel_superior, GroupLayout.DEFAULT_SIZE, 1022, Short.MAX_VALUE)
				.addGroup(gl_panel.createSequentialGroup()
					.addComponent(panel_izq, GroupLayout.PREFERRED_SIZE, 171, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(labelLogo, GroupLayout.DEFAULT_SIZE, 831, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addComponent(panel_superior, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_izq, GroupLayout.DEFAULT_SIZE, 581, Short.MAX_VALUE)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(labelLogo, GroupLayout.PREFERRED_SIZE, 570, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())))
		);
		
		panel_Nuevo = new JPanel();
		panel_Nuevo.setBackground(SystemColor.menu);
		panel_Nuevo.setBounds(0, 0, 170, 268);
		panel_izq.add(panel_Nuevo);
		panel_Nuevo.setLayout(null);
		
		RSButtonMetro btnmtrPartido = new RSButtonMetro();
		btnmtrPartido.setIcon(new ImageIcon(Inicio.class.getResource("/imgiconos/Nuevo Partido.png")));
		btnmtrPartido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(Administracion.getInstancia().getMisEquipos().size() >= 2) {
					RegPartido regp = new RegPartido();
					regp.setModal(true);
					regp.setVisible(true);
				}
				else {
					JOptionPane.showMessageDialog(null, "Se necesitan 2 o m�s equipos creados.");
				}
			}
		});
		btnmtrPartido.setBounds(0, 105, 170, 35);
		panel_Nuevo.add(btnmtrPartido);
		btnmtrPartido.setText("Partido ");
		btnmtrPartido.setForeground(Color.GRAY);
		btnmtrPartido.setColorTextNormal(Color.GRAY);
		btnmtrPartido.setColorPressed(Color.DARK_GRAY);
		btnmtrPartido.setColorNormal(SystemColor.menu);
		btnmtrPartido.setColorHover(Color.GRAY);
		btnmtrPartido.setBackground(SystemColor.menu);
		
		RSButtonMetro btnEquipo = new RSButtonMetro();
		btnEquipo.setIcon(new ImageIcon(Inicio.class.getResource("/imgiconos/Equipo.png")));
		btnEquipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegEquipo regE = new RegEquipo();
				regE.setModal(true);
				regE.setVisible(true);
			}
		});
		btnEquipo.setBounds(0, 70, 170, 35);
		panel_Nuevo.add(btnEquipo);
		btnEquipo.setText("Equipo  ");
		btnEquipo.setForeground(Color.GRAY);
		btnEquipo.setColorTextNormal(Color.GRAY);
		btnEquipo.setColorPressed(Color.DARK_GRAY);
		btnEquipo.setColorNormal(SystemColor.menu);
		btnEquipo.setColorHover(Color.GRAY);
		btnEquipo.setBackground(SystemColor.menu);
		
		RSButtonMetro btnJugador = new RSButtonMetro();
		btnJugador.setIcon(new ImageIcon(Inicio.class.getResource("/imgiconos/Nuevo jug2.png")));
		btnJugador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(Administracion.getInstancia().getMisEquipos().size() != 0) {
					RegJugador regj = null;
                                    try {
                                        regj = new RegJugador(0,0,false);
                                    } catch (SQLException ex) {
                                        Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
                                    }
					regj.setModal(true);
					regj.setVisible(true);
				}
				else {
					JOptionPane.showMessageDialog(null, "A�n no hay equipos creados.");
				}
			}
		});
		btnJugador.setBounds(0, 36, 170, 35);
		panel_Nuevo.add(btnJugador);
		btnJugador.setColorTextNormal(new Color(128, 128, 128));
		btnJugador.setForeground(new Color(128, 128, 128));
		btnJugador.setText("Jugador");
		btnJugador.setColorPressed(Color.DARK_GRAY);
		btnJugador.setColorNormal(SystemColor.menu);
		btnJugador.setColorHover(Color.GRAY);
		btnJugador.setBackground(SystemColor.menu);
		
		JLabel lblNuevo = new JLabel("Nuevo");
		lblNuevo.setBounds(10, 11, 63, 14);
		panel_Nuevo.add(lblNuevo);
		lblNuevo.setForeground(new Color(128, 128, 128));
		lblNuevo.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		panel_Gestion = new JPanel();
		panel_Gestion.setBounds(0, 0, 170, 268);
		panel_izq.add(panel_Gestion);
		panel_Gestion.setLayout(null);
		panel_Gestion.setVisible(false);
		
		RSButtonMetro btnmtrPartidos = new RSButtonMetro();
		btnmtrPartidos.setIcon(new ImageIcon(Inicio.class.getResource("/imgiconos/Gest Partidos.png")));
		btnmtrPartidos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(Administracion.getInstancia().getMisPartidos().size() != 0) {
					ListPartidos lp = new ListPartidos();
					lp.setModal(true);
					lp.setVisible(true);
				}
				else {
					JOptionPane.showMessageDialog(null, "No hay partidos creados.");
				}
			}
		});
		btnmtrPartidos.setBounds(0, 105, 170, 35);
		panel_Gestion.add(btnmtrPartidos);
		btnmtrPartidos.setText("Partidos     ");
		btnmtrPartidos.setForeground(Color.GRAY);
		btnmtrPartidos.setColorTextNormal(Color.GRAY);
		btnmtrPartidos.setColorPressed(Color.DARK_GRAY);
		btnmtrPartidos.setColorNormal(SystemColor.menu);
		btnmtrPartidos.setColorHover(Color.GRAY);
		btnmtrPartidos.setBackground(SystemColor.menu);
		
		RSButtonMetro btnmtrEquipos = new RSButtonMetro();
		btnmtrEquipos.setIcon(new ImageIcon(Inicio.class.getResource("/imgiconos/Gest equipos.png")));
		btnmtrEquipos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListEquipos le = new ListEquipos();
				le.setModal(true);
				le.setVisible(true);
			}
		});
		btnmtrEquipos.setBounds(0, 70, 170, 35);
		panel_Gestion.add(btnmtrEquipos);
		btnmtrEquipos.setText("Equipos     ");
		btnmtrEquipos.setForeground(Color.GRAY);
		btnmtrEquipos.setColorTextNormal(Color.GRAY);
		btnmtrEquipos.setColorPressed(Color.DARK_GRAY);
		btnmtrEquipos.setColorNormal(SystemColor.menu);
		btnmtrEquipos.setColorHover(Color.GRAY);
		btnmtrEquipos.setBackground(SystemColor.menu);
		
		RSButtonMetro btnmtrJugadores = new RSButtonMetro();
		btnmtrJugadores.setIcon(new ImageIcon(Inicio.class.getResource("/imgiconos/users-icon.png")));
		btnmtrJugadores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Administracion.getInstancia().getMisEquipos().size() != 0) {
					ListJugadores lj = null;
                                    try {
                                        lj = new ListJugadores();
                                    } catch (SQLException ex) {
                                        Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
                                    }
					lj.setModal(true);
					lj.setVisible(true);
				}
				else {
					JOptionPane.showMessageDialog(null, "A�n no hay equipos creados.");
				}
			}
		});
		btnmtrJugadores.setBounds(0, 36, 170, 35);
		panel_Gestion.add(btnmtrJugadores);
		btnmtrJugadores.setText("Jugadores");
		btnmtrJugadores.setForeground(Color.GRAY);
		btnmtrJugadores.setColorTextNormal(Color.GRAY);
		btnmtrJugadores.setColorPressed(Color.DARK_GRAY);
		btnmtrJugadores.setColorNormal(SystemColor.menu);
		btnmtrJugadores.setColorHover(Color.GRAY);
		btnmtrJugadores.setBackground(SystemColor.menu);
		
		JLabel lblGestin = new JLabel("Gestionar");
		lblGestin.setBounds(10, 11, 83, 14);
		panel_Gestion.add(lblGestin);
		lblGestin.setForeground(Color.GRAY);
		lblGestin.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		RSButtonMetro btnmtrLesiones = new RSButtonMetro();
		btnmtrLesiones.setIcon(new ImageIcon(Inicio.class.getResource("/imgiconos/Gest lesiones.png")));
		btnmtrLesiones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GestionarLesiones gesl = new GestionarLesiones();
				gesl.setModal(true);
				gesl.setVisible(true);
			}
		});
		btnmtrLesiones.setText("Lesiones      ");
		btnmtrLesiones.setForeground(Color.GRAY);
		btnmtrLesiones.setColorTextNormal(Color.GRAY);
		btnmtrLesiones.setColorPressed(Color.DARK_GRAY);
		btnmtrLesiones.setColorNormal(SystemColor.menu);
		btnmtrLesiones.setColorHover(Color.GRAY);
		btnmtrLesiones.setBackground(SystemColor.menu);
		btnmtrLesiones.setBounds(0, 138, 170, 35);
		panel_Gestion.add(btnmtrLesiones);
		
		RSButtonMetro btnGestion = new RSButtonMetro();
		btnGestion.setText("Gestionar");
		btnGestion.setToolTipText("Gestionar");
		btnGestion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				panel_Nuevo.setVisible(false);
				panel_Gestion.setVisible(true);
				
				
				try {
					Thread.sleep(150);
				}
				catch(InterruptedException e1){
					System.out.println("Thread Interrupted");
				}
				

			}
		});
		btnGestion.setColorHover(new Color(0, 102, 204));
		btnGestion.setBackground(new Color(0, 0, 128));
		btnGestion.setColorPressed(new Color(0, 0, 128));
		btnGestion.setColorNormal(new Color(0, 0, 128));
		btnGestion.setBounds(63, 1, 84, 51);
		panel_superior.add(btnGestion);
		
		RSButtonMetro btnmtrRanking = new RSButtonMetro();
		btnmtrRanking.setText("Ranking");
		btnmtrRanking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Ranking rank = new Ranking();
				rank.setModal(true);
				rank.setVisible(true);
			}
		});
		btnmtrRanking.setToolTipText("Posiciones");
		btnmtrRanking.setColorPressed(new Color(0, 0, 128));
		btnmtrRanking.setColorNormal(new Color(0, 0, 128));
		btnmtrRanking.setColorHover(new Color(0, 102, 204));
		btnmtrRanking.setBackground(new Color(0, 0, 128));
		btnmtrRanking.setBounds(152, 0, 66, 51);
		panel_superior.add(btnmtrRanking);
		panel.setLayout(gl_panel);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 808, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(2))
		);
		contentPane.setLayout(gl_contentPane);
		
	}
}
