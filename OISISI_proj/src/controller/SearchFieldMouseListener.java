package controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import model.GlobalConstants;
import view.GlavniProzor;
import view.Toolbar;

public class SearchFieldMouseListener implements MouseListener{

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		int tab = GlavniProzor.getTabbedPane().getSelectedIndex();
		
		switch(tab) {
		case 0:
			//Student toolTip
			Toolbar.srchField.setToolTipText(GlobalConstants.srchStudToolTip);
			break;
		case 1:
			//Profesor toolTip
			Toolbar.srchField.setToolTipText(GlobalConstants.srchProfToolTip);
			break;
		case 2:
			//Predmet toolTip
			Toolbar.srchField.setToolTipText(GlobalConstants.srchPredToolTip);
			break;
		}
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
