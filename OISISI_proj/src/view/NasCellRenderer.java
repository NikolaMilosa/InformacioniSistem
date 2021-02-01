package view;


import java.awt.Component;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

public class NasCellRenderer extends DefaultTableCellRenderer{
	
	public static final int studentRenderer = 0;
	public static final int profesorRenderer = 1;
	public static final int predmetRenderer = 2;
	
	private int mod;
	
	public NasCellRenderer() {
		super();
	}
	
	public NasCellRenderer(int mode) {
		super();
		this.mod = mode;
	}
	
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		
		switch(mod) {
		case NasCellRenderer.studentRenderer:{
			if(column == 3 || column == 4 ||column == 5)
				setHorizontalAlignment(SwingConstants.CENTER);
			else
				setHorizontalAlignment(SwingConstants.LEFT);
			break;
		}
		case NasCellRenderer.profesorRenderer:{
			this.setHorizontalAlignment(SwingConstants.LEFT);
			break;
		}
		case NasCellRenderer.predmetRenderer:{
			if(column == 2 || column == 3 || column == 4)
				this.setHorizontalAlignment(SwingConstants.CENTER);
			else
				this.setHorizontalAlignment(SwingConstants.LEFT);
			break;
		}
		}
		
		return this;
	}
}
