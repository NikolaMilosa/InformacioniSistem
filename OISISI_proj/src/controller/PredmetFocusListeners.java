package controller;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.BorderFactory;
import javax.swing.JTextField;

import model.GlobalConstants;
import view.AddOrEditPredmet;

public class PredmetFocusListeners implements FocusListener {

	@Override
	public void focusGained(FocusEvent e) {
		JTextField txt = (JTextField) e.getComponent();
		
		txt.setForeground(Color.BLACK);
		txt.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
	}

	@Override
	public void focusLost(FocusEvent e) {
		JTextField txt = (JTextField) e.getComponent();
		
		String name = txt.getName();
		String input = txt.getText();
		
		if(!regularInput(name, input)) {
			txt.setBorder(BorderFactory.createLineBorder(Color.RED));
			txt.setForeground(Color.RED);
		} else {
			txt.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		}
		
		enableOrDisableButton();
		
		if(txt.getText().trim().equals("") && !txt.getName().equals(GlobalConstants.profLab)) {
			txt.setBorder(BorderFactory.createLineBorder(Color.RED));
		}
		
	}

	private boolean regularInput(String name, String input) {

		if(name.equals(GlobalConstants.sifraLab))
			return true;
		if(name.equals(GlobalConstants.nazivLab))
			return Checker.isValidNamePred(input);
		
		if(name.equals(GlobalConstants.espbLab))
			return Checker.isValidEspb(input);
		
		return false;
	}
	
	
	public static void enableOrDisableButton() {
		boolean enableButton = true;
		
		if(AddOrEditPredmet.tSifra.getText().trim().equals(""))
			enableButton = false;
		
		if(!Checker.isValidNamePred(AddOrEditPredmet.tNaziv.getText()) || AddOrEditPredmet.tNaziv.getText().trim().equals("")) {
			AddOrEditPredmet.tNaziv.setForeground(Color.RED);
			enableButton = false;
		}
		
		if(!Checker.isValidEspb(AddOrEditPredmet.tEspb.getText()) || AddOrEditPredmet.tEspb.getText().trim().equals("")) {
			AddOrEditPredmet.tEspb.setForeground(Color.RED);
			enableButton = false;
		}
		
		AddOrEditPredmet.potvrdi.setEnabled(enableButton);
	}
}
