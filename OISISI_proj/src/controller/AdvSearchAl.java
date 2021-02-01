package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.GlobalConstants;
import view.AdvSearDialog;
import view.ErrorDialog;
import view.GlavniProzor;


public class AdvSearchAl implements ActionListener{
	
	AdvSearDialog adv;
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(GlavniProzor.getTabbedPane().getSelectedIndex() == 2) {
			adv = new AdvSearDialog();
			adv.setVisible(true);
		}
		else {
			@SuppressWarnings("unused")
			ErrorDialog err = new ErrorDialog(GlobalConstants.errAdvSearProf);
		}

	}

}
