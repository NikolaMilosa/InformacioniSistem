package controller;

import view.*;
import view.TabelaStudenti.Index;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import model.GlobalConstants;

public class DeleteButtonListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		
		int tab = GlavniProzor.getTabbedPane().getSelectedIndex();
		
		
		switch(tab) {
		case 0 :
			//brisanje studenta
			int selectedStud = TabelaStudenti.table.getSelectedRow();
			
			if(selectedStud != -1) {
				String [] options = {GlobalConstants.yesOpt, GlobalConstants.noOpt};
				int code = JOptionPane.showOptionDialog(GlavniProzor.getGlavniProzor(), GlobalConstants.upitBrisanjeStud, GlobalConstants.upitBrisanjeStudTitle, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null);
			
				if(code == JOptionPane.YES_OPTION) {
					String indexStudenta = ((Index) TabelaStudenti.table.getValueAt(selectedStud, 0)).toString();
					GlavniProzor.getControllerPredmet().obrisiStudentaIzSvihListaPolozenih(indexStudenta);
					GlavniProzor.getControllerPredmet().obrisiStudentaIzSvihListaNepolozenih(indexStudenta);
					GlavniProzor.getControllerStudent().obrisiStudenta(indexStudenta);
					TabelaStudenti.table.updateTable();
					GlavniProzor.serialize();
				}
			}
			break;
		case 1:
			//brisanje profesora
			int selectedProf = TabelaProfesora.inst.getSelectedRow();

			if(selectedProf != -1) {
				String [] options = {GlobalConstants.yesOpt, GlobalConstants.noOpt};
				int code = JOptionPane.showOptionDialog(GlavniProzor.getGlavniProzor(), GlobalConstants.upitBrisanjeProf, GlobalConstants.upitBrisanjeProfTitle, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null);

				if(code == JOptionPane.YES_OPTION) {
					String brLicKart = (String)TabelaProfesora.inst.getValueAt(selectedProf, 0);
					GlavniProzor.getControllerPredmet().obrisiProfSaSvihPredmeta(brLicKart);
					GlavniProzor.getControllerProfesor().ukloniProfesora(brLicKart);
					TabelaProfesora.azurirajTabelu();
					GlavniProzor.serialize();
				}
			}
			break;
		case 2:
			//brisanje predmeta
			int selectedPred = TabelaPredmeti.inst.getSelectedRow();

			if(selectedPred != -1) {
				String [] options = {GlobalConstants.yesOpt, GlobalConstants.noOpt};
				int code = JOptionPane.showOptionDialog(GlavniProzor.getGlavniProzor(), GlobalConstants.upitBrisanjePred, GlobalConstants.upitBrisanjePredTitle, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null);
			
				if(code == JOptionPane.YES_OPTION) {
					String delSifPred = (String)TabelaPredmeti.inst.getValueAt(selectedPred, 0);
					GlavniProzor.getControllerPredmet().obrisiPredmet(delSifPred);
					TabelaPredmeti.azurirajTabelu();
					GlavniProzor.serialize();
				}
			}
			break;
		}
		
	}

}
