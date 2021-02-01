package controller;

import view.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditButtonListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		
		int tab = GlavniProzor.getTabbedPane().getSelectedIndex();
		AddOrEditDialog aoed;
		
		int rowCheck, rowCheckPred, rowCheckStud;
		rowCheck = TabelaProfesora.inst.getSelectedRow();
		rowCheckPred = TabelaPredmeti.inst.getSelectedRow();
		rowCheckStud = TabelaStudenti.table.getSelectedRow();
		if(rowCheck == -1 && rowCheckPred == -1 && rowCheckStud == -1)
			return;
		
		switch(tab) {
		case 0:
			//Edit za studenta
			if(rowCheckStud != -1) {
				aoed = new AddOrEditDialog(AddOrEditDialog.editMode);
				aoed.pack();
				aoed.setVisible(true);
			}
			break;
		case 1:
			//Edit za profesore
			if(rowCheck != -1) {
				aoed = new AddOrEditDialog(AddOrEditDialog.editMode);
				aoed.setVisible(true);
			}
			break;
		case 2:
			//Edit za predmete
			if(rowCheckPred != -1) {
				aoed = new AddOrEditDialog(AddOrEditDialog.editMode);
				aoed.pack();
				aoed.setVisible(true);
			}
			break;
		}
		
	}

}
