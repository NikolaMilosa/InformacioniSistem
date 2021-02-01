package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import model.*;
import view.*;

public class AddPredToProfListener implements ActionListener{

	Profesor currProf;
	JDialog addPredDialog;
	
	public AddPredToProfListener(Profesor p){
		currProf = p;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		addPredDialog = new AddPredToProfDialog(currProf);
	}
}
