package visual;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class JpanelGraphs extends JPanel {



	private Image background;

	public void paintComponent(Graphics g) {

		int width = this.getSize().width;
		int height = this.getSize().height;
		g.setColor(Color.RED);

		if (this.background != null) {
			g.drawImage(this.background, 0, 0, width, height, null);
		}

		super.paintComponent(g);
	}


}