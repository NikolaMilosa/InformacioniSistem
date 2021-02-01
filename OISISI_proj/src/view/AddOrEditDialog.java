package view;

import javax.swing.JDialog;

import model.GlobalConstants;

public class AddOrEditDialog extends JDialog{
	
	public static int addMode = 1;
	public static int editMode = 2;
	
	public AddOrEditDialog(int mode) {
		super();
		
		this.setResizable(false);
		this.setSize(GlobalConstants.aoedw,GlobalConstants.aoedh);
		this.setModal(true);
		this.setLocationRelativeTo(GlavniProzor.getGlavniProzor());
		
		int tab = GlavniProzor.getTabbedPane().getSelectedIndex();
		
		switch(tab) {
		case 0 : 
			//za studente
			if(mode == addMode)
				this.setTitle(GlobalConstants.studAdd);
			else 
				this.setTitle(GlobalConstants.editStud);
		
			this.add(new AddOrEditStudent(mode, this));
			break;
		case 1 :
			//za profesore
			if(mode == addMode)
				this.setTitle(GlobalConstants.profAdd);
			else
				this.setTitle(GlobalConstants.profEdit);
			this.add(new AddOrEditProfesor(mode, this));
			break;
		case 2 :
			// za predmete
			if(mode == addMode)
				this.setTitle(GlobalConstants.predAdd);
			else
				this.setTitle(GlobalConstants.predEdit);
			this.add(new AddOrEditPredmet(mode, this));
				
			break;
		}
		
		
	}

}
