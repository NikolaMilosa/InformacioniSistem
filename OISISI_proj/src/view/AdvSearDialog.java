package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controller.AdvSearchWorkPred;
import model.GlobalConstants;

public class AdvSearDialog extends JDialog {

	JButton ok,cancel;
	JTextField field;
	JPanel help1,help2,help3;
	JSeparator sep1;
	JLabel exp;
	int tab;
	
	public static AdvSearDialog inst;
	
	public AdvSearDialog() {
		super();
		
		inst = this;
		
		Image img = new ImageIcon(GlobalConstants.advSearImg).getImage();
		
		this.setIconImage(img);
		this.setModal(true);
		this.setSize(500,150);
		this.setLocationRelativeTo(GlavniProzor.inst);
		this.setResizable(false);
		this.setLayout(new BorderLayout());
		
		exp = new JLabel(GlobalConstants.labAdvSear);
		help1 = new JPanel();
		help1.add(exp);
		this.add(help1,BorderLayout.NORTH);
		
		field = new JTextField();
		field.setPreferredSize(new Dimension(450,30));
		help2 = new JPanel();
		help2.add(field);
		this.add(help2, BorderLayout.CENTER);
		
		ok = new JButton(GlobalConstants.btnOkName);
		ok.addActionListener(new MyPotvrdiListener());
		cancel = new JButton(GlobalConstants.btnCncName);
		cancel.addActionListener(new MyOdustaniListener());
		
		help3 = new JPanel();
		help3.add(ok);
		sep1 = new JSeparator(SwingConstants.VERTICAL);
		sep1.setSize(new Dimension(5,5));
		help3.add(sep1);
		help3.add(cancel);
		this.add(help3,BorderLayout.SOUTH);
		
		tab = GlavniProzor.getTabbedPane().getSelectedIndex();
		
		if(tab == 0) {
			//Student pretraga
		} else if (tab == 2) {
			//Predmet pretraga 
			this.setTitle(GlobalConstants.advTitlePred);
			this.field.setToolTipText(GlobalConstants.advSearchToolTipPred);
		} 
	}
	
	public AdvSearDialog getter() {
		return this;
	}
	
	class MyOdustaniListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {

			getter().setVisible(false);
			
		}
		
	}
	
	class MyPotvrdiListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			AdvSearchWorkPred asw;
			
			int tab = GlavniProzor.inst.getTabbedPane().getSelectedIndex();
			if(tab == 2)
				asw = new AdvSearchWorkPred(field.getText());
			
		}
		
	}
}
