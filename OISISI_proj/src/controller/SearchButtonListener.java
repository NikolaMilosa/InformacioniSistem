package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import view.*;

public class SearchButtonListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		Toolbar.srchField.setForeground(Color.black);
		
		int tab = GlavniProzor.getTabbedPane().getSelectedIndex();
		
		switch(tab) {
		case 0:
			//Search za studente
			prikaziStudente();
			break;
		case 1:
			//Search za profesora
			prikaziProfesore();
			break;
		case 2:
			//search za predmete
			prikaziPredmete();
			break;
		}
		
	}
	
	private void prikaziStudente() {
		String text = Toolbar.srchField.getText();
		text = text.trim();
		text = text.toLowerCase();
		String parts[] = text.split(" ");
		ArrayList<String> foundStudents = new ArrayList<String>();
		foundStudents = GlavniProzor.getControllerStudent().pretraziStud(parts);
		if(foundStudents.size() == 0)
			Toolbar.srchField.setForeground(Color.red);
		else
			Toolbar.srchField.setForeground(Color.black);
		
		TabelaStudenti.table.izlistajStudente(foundStudents);
	}

	//Implementirane funkcije za prikaz :
	void prikaziProfesore() {
		String text = Toolbar.srchField.getText();
		text = text.trim();
		text = text.toLowerCase();
		String words[] = text.split(" ");
		ArrayList<String> foundP = new ArrayList<String>();
		if(words.length != 1 && words.length != 2) {
			TabelaProfesora.azurirajTabelu();
			Toolbar.srchField.setForeground(Color.red);
			return;
		}
			
		else {
			foundP = GlavniProzor.getControllerProfesor().pretraziProf(words);
		}
		
		TabelaProfesora.izlistajProfesore(foundP);
	}
	
	void prikaziPredmete() {
		String text = Toolbar.srchField.getText();
		text = text.trim();
		text = text.toLowerCase();
		ArrayList<String> foundP = new ArrayList<String>();
		if(text.indexOf(" ") != -1) {
			TabelaPredmeti.azurirajTabelu();
			Toolbar.srchField.setForeground(Color.red);
			return;
		}
		else {
			foundP = GlavniProzor.getControllerPredmet().pretraziPred(text);
		}
		
		TabelaPredmeti.izlistajPredmete(foundP);
	}
}

