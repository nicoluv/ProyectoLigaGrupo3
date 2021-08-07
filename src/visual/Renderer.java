package visual;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class Renderer extends DefaultTableCellRenderer {	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		// TODO Auto-generated method stub
		super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		if(table.getValueAt(row, 2).equals("Lesionado")) {
			this.setBackground(Color.RED);
		}
		else if(table.getValueAt(row, 2).equals("En Forma")) {
			this.setBackground(Color.WHITE);
		}
		return this;
	}
}