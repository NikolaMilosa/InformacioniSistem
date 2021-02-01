package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import controller.AddButtonListener;
import controller.test_main;
import controller.AdvSearchAl;
import controller.ControllerPredmet;
import controller.ControllerProfesor;
import controller.ControllerStudent;
import controller.DeleteButtonListener;
import controller.EditButtonListener;
import model.GlobalConstants;

public class MyMenuBar extends JMenuBar {

	ControllerProfesor cProf;
	ControllerPredmet cPred;
	ControllerStudent cStud;
	
	public MyMenuBar() {
		
		JMenu mFile = new JMenu(GlobalConstants.menuFile);
		JMenuItem miNew = new JMenuItem(GlobalConstants.menuNew, new ImageIcon(GlobalConstants.addImg));
		miNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		miNew.addActionListener(new AddButtonListener());
		JMenuItem miClose = new JMenuItem(GlobalConstants.menuClose, new ImageIcon(GlobalConstants.closeImg));
		miClose.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
		miClose.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String [] options = {GlobalConstants.yesOpt,GlobalConstants.noOpt};
				int code = JOptionPane.showOptionDialog(null, GlobalConstants.upitZatvaranjeMF, GlobalConstants.upitZatvaranjeTitle, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null);

		         if(code == JOptionPane.YES_OPTION){
		        	System.exit(0);
		         }
			}
		});
		
		mFile.setMnemonic(KeyEvent.VK_F);
		miNew.setMnemonic(KeyEvent.VK_N);
		mFile.add(miNew);
		mFile.addSeparator();
		miClose.setMnemonic(KeyEvent.VK_C);
		mFile.add(miClose);
		
		JMenu mEdit = new JMenu(GlobalConstants.menuEdit);
		JMenuItem miEdit = new JMenuItem(GlobalConstants.menuEdit, new ImageIcon(GlobalConstants.editImg));
		miEdit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
		miEdit.addActionListener(new EditButtonListener());
		JMenuItem miDelete = new JMenuItem(GlobalConstants.menuDelete, new ImageIcon(GlobalConstants.delImg));
		miDelete.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
		miDelete.addActionListener(new DeleteButtonListener());
		
		mEdit.setMnemonic(KeyEvent.VK_E);
		miEdit.setMnemonic(KeyEvent.VK_E);
		mEdit.add(miEdit);
		mEdit.addSeparator();
		miDelete.setMnemonic(KeyEvent.VK_D);
		mEdit.add(miDelete);
		
		JMenu mHelp = new JMenu(GlobalConstants.menuHelp);
		JMenuItem miHelp = new JMenuItem(GlobalConstants.menuHelp, new ImageIcon(GlobalConstants.helpImg));
		miHelp.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.CTRL_MASK));
		miHelp.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				HelpDialog help = new HelpDialog();
			    help.pack();
			    help.setLocationRelativeTo(null);
			    help.setVisible(true);
			}
		});
		
		
		JMenuItem miAbout = new JMenuItem(GlobalConstants.menuAbout, new ImageIcon(GlobalConstants.aboutImg));
		miAbout.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
		miAbout.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {	
				AboutDialog about = new AboutDialog();
				about.pack();
				about.setLocationRelativeTo(null);
				about.setVisible(true);
			}
		});

		mHelp.setMnemonic(KeyEvent.VK_H);
		miHelp.setMnemonic(KeyEvent.VK_H);
		mHelp.add(miHelp);
		mHelp.addSeparator();
		miAbout.setMnemonic(KeyEvent.VK_A);
		mHelp.add(miAbout);
		
		JMenu mAdvanced = new JMenu(GlobalConstants.menuAdvanced);
		JMenu mLanguage = new JMenu(GlobalConstants.menuLanguage);
		mLanguage.setIcon(new ImageIcon(GlobalConstants.languageImg));
		JMenuItem miSearch = new JMenuItem(GlobalConstants.menuSearch, new ImageIcon(GlobalConstants.advSearImg));
		miSearch.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		miSearch.addActionListener(new AdvSearchAl());
		
		mAdvanced.setMnemonic(KeyEvent.VK_A);
		mLanguage.setMnemonic(KeyEvent.VK_L);
		miSearch.setMnemonic(KeyEvent.VK_S);
		mAdvanced.add(mLanguage);
		JMenuItem miiSerbian = new JMenuItem(GlobalConstants.menuSerbian, new ImageIcon(GlobalConstants.serbianImg));
		miiSerbian.setMnemonic(KeyEvent.VK_S);
		miiSerbian.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK));
		miiSerbian.addActionListener(new MySerbLangListener());
		mLanguage.add(miiSerbian);
		mLanguage.addSeparator();
		JMenuItem miiEnglish = new JMenuItem(GlobalConstants.menuEnglish, new ImageIcon(GlobalConstants.englishImg));
		miiEnglish.setMnemonic(KeyEvent.VK_E);
		miiEnglish.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.CTRL_MASK));
		miiEnglish.addActionListener(new MyEnglLangListener());
		mLanguage.add(miiEnglish);
		mAdvanced.addSeparator();
		mAdvanced.add(miSearch);
		
		add(mFile);
		add(mEdit);
		add(mHelp);
		add(mAdvanced);
	}
	
	class MySerbLangListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(test_main.language != 1) {
				GlobalConstants.setLangSerbian();
				test_main.language = 1;
				
				GlavniProzor.serialize();
				GlavniProzor.inst.setVisible(false);
				GlavniProzor.inst = new GlavniProzor();
				GlavniProzor.inst.setVisible(true);
			}
		}
		
	}
	
	class MyEnglLangListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(test_main.language != 2) {
				GlobalConstants.setLangEnglish();
				test_main.language = 2;
				
				GlavniProzor.serialize();
				GlavniProzor.inst.setVisible(false);
				GlavniProzor.inst = new GlavniProzor();
				GlavniProzor.inst.setVisible(true);
			}
			
		}
		
	}
}
