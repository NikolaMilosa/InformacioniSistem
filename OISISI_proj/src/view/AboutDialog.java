package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.GlobalConstants;

public class AboutDialog extends JDialog {
	
	private JLabel label1, label2, label3, label4, label5, label6, label7, label8, label9, label10;
	private JPanel panel;
	
	public AboutDialog() {
		label1 = new JLabel(GlobalConstants.aboutLab1);
		label2 = new JLabel(GlobalConstants.aboutLab2);
		label3 = new JLabel();
		label4 = new JLabel(GlobalConstants.aboutLab4);
		label5 = new JLabel(GlobalConstants.aboutLab5);
		label6 = new JLabel(GlobalConstants.aboutLab6);
		label7 = new JLabel();
		label8 = new JLabel(GlobalConstants.aboutLab8);
		label9 = new JLabel(GlobalConstants.aboutLab9);
		label10 = new JLabel(GlobalConstants.aboutLab10);
		
		JLabel[] labels = {label1, label2, label3, label4, label5, label6, label7, label8, label9, label10};
		
		Font font = new Font("Arial", Font.ITALIC, 16);
		
		for(JLabel l : labels) {
			l.setFont(font);
		}
		
		panel = new JPanel();
		panel.setLayout(new GridLayout(0,1));
		for(JLabel l : labels)
			panel.add(l);
		
		setModal(true);
		add(panel, BorderLayout.CENTER);
	    setTitle(GlobalConstants.aboutTitle);
		setResizable(false);
			
	}
}