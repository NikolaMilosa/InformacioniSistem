package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import model.GlobalConstants;

public class HelpDialog extends JDialog {
	private JLabel label1, label2, label3, label4, label5, label6, label7, label8, label9,
				   label10, label11, label12, label13, label14, label15, label16, label17,
				   label18, label19, label20, label21, label22, label23, label24, label25,
				   label26, label27, label28, label29, label30, label31;
	private JPanel panel;
	private JPanel buttom;
	
	public HelpDialog() {
		super();
		
		label1 = new JLabel(GlobalConstants.helpLab1);
    	label2 = new JLabel(GlobalConstants.helpLab2);
    	label3 = new JLabel(new ImageIcon(GlobalConstants.menuBarImg));
    	label4 = new JLabel(GlobalConstants.helpLab4);
    	label5 = new JLabel(GlobalConstants.helpLab5, new ImageIcon(GlobalConstants.addImg), SwingConstants.LEFT);
    	label6 = new JLabel(GlobalConstants.helpLab6, new ImageIcon(GlobalConstants.closeImg), SwingConstants.LEADING);
    	label7 = new JLabel(GlobalConstants.helpLab7);
    	label8 = new JLabel(GlobalConstants.helpLab8, new ImageIcon(GlobalConstants.editImg), SwingConstants.LEFT);
    	label9 = new JLabel(GlobalConstants.helpLab9, new ImageIcon(GlobalConstants.delImg), SwingConstants.LEFT);
    	label10 = new JLabel(GlobalConstants.helpLab10);
    	label11 = new JLabel(GlobalConstants.helpLab11, new ImageIcon(GlobalConstants.helpImg), SwingConstants.LEFT);
    	label12 = new JLabel(GlobalConstants.helpLab12, new ImageIcon(GlobalConstants.aboutImg), SwingConstants.LEFT);
    	label13 = new JLabel(GlobalConstants.helpLab13);
    	label14 = new JLabel(GlobalConstants.helpLab14,  new ImageIcon(GlobalConstants.languageImg), SwingConstants.LEFT);
    	label15 = new JLabel(GlobalConstants.helpLab15, new ImageIcon(GlobalConstants.advSearImg), SwingConstants.LEFT);
    	label16 = new JLabel(GlobalConstants.helpLab16);
    	label17 = new JLabel(GlobalConstants.helpLab17);
    	label18 = new JLabel(new ImageIcon(GlobalConstants.toolBarImg));
    	label19 = new JLabel(GlobalConstants.helpLab19, new ImageIcon(GlobalConstants.addImg), SwingConstants.LEFT);
    	label20 = new JLabel(GlobalConstants.helpLab20, new ImageIcon(GlobalConstants.editImg), SwingConstants.LEFT);
    	label21 = new JLabel(GlobalConstants.helpLab21, new ImageIcon(GlobalConstants.delImg), SwingConstants.LEFT);
    	label22 = new JLabel(GlobalConstants.helpLab22, new ImageIcon(GlobalConstants.advSearImg), SwingConstants.LEFT);
    	label23 = new JLabel(GlobalConstants.helpLab23);
    	label24 = new JLabel(GlobalConstants.helpLab24);
    	label25 = new JLabel(new ImageIcon(GlobalConstants.searchFieldImg));
    	label26 = new JLabel(GlobalConstants.helpLab26);
    	label27 = new JLabel(GlobalConstants.helpLab27);
    	label28 = new JLabel(GlobalConstants.helpLab28);
    	label29 = new JLabel(GlobalConstants.helpLab29);
    	label30 = new JLabel(GlobalConstants.helpLab30);
    	label31 = new JLabel(GlobalConstants.helpLab31);
		JLabel[] arr = {label1, label2, label3, label4, label5, label6, label7,
						label8, label9, label10, label11, label12, label13, label14,
						label15, label16, label17, label18, label19, label20, label21, label22, label23, label24, label25,
						label26, label27, label28, label29, label30, label31};
		
		Font font = new Font("Arial", Font.PLAIN, 15);
	
		for(JLabel l : arr) {
			l.setFont(font);
		}
		
		panel = new JPanel();
	    panel.setLayout(new GridLayout(0,1));
		
	   for (JLabel l : arr) {
		   panel.add(l);
	    }
	    
	    JScrollPane scrollPane = new JScrollPane();
	    scrollPane.setViewportView(panel);
	    getContentPane().add(scrollPane, BorderLayout.CENTER);
	    scrollPane.getVerticalScrollBar().setUnitIncrement(16);
	    
		buttom = new JPanel();
		JButton ok = new JButton("OK");
		ok.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		
		buttom.add(ok);
		
	    setModal(true);
	    setPreferredSize(new Dimension(850, 600));
	    setResizable(false);
	    setTitle(GlobalConstants.helpTitle);
	}
}
