package controller;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.BorderFactory;
import javax.swing.JTextField;

import model.GlobalConstants;
import view.AddOrEditStudent;

public class StudentFocusListeners implements FocusListener {
	
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
		String input = txt.getText().trim();
		
		if(!regularInput(name, input)) {
			txt.setBorder(BorderFactory.createLineBorder(Color.RED));
			txt.setForeground(Color.RED);
		} else {
			txt.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		}
		
		enableOrDisableButton();
		
		if(txt.getText().trim().equals("")) 
			txt.setBorder(BorderFactory.createLineBorder(Color.RED));
	}
	
	public static boolean regularInput(String name, String input) {

		if(name.equals(GlobalConstants.imeLab) || name.equals(GlobalConstants.przLab))
			return Checker.isNameOrSurename(input);
		if(name.equals(GlobalConstants.drLab))
			return Checker.isValidDate(input,1);
		if(name.equals(GlobalConstants.adrStanLab))
			return Checker.isValidAdrress(input);
		if(name.equals(GlobalConstants.konTelLab))
			return Checker.isValidNumber(input, 0);
		if(name.equals(GlobalConstants.emailLab))
			return Checker.isValidEmail(input);
		if(name.equals(GlobalConstants.indexLab)) {
			if(!AddOrEditStudent.tGodUpisa.getText().equals("")) {
				return (Checker.isValidIndex(input) && input.substring(input.length()-4).equals(AddOrEditStudent.tGodUpisa.getText().trim()));
			} else {
				return Checker.isValidIndex(input);
			} 
		}
		if(name.equals(GlobalConstants.upisLab)) {
			if(!AddOrEditStudent.tBrIndexa.getText().equals("")) {
				return (Checker.isValidYear(input) && AddOrEditStudent.tBrIndexa.getText().trim().substring(AddOrEditStudent.tBrIndexa.getText().trim().length()-4).equals(input));
			}
			else {
				return Checker.isValidYear(input);
			}
		}
		
		return false;
	}
	
	public static void enableOrDisableButton() {
		boolean enableButton = true;
		
		if(!Checker.isNameOrSurename(AddOrEditStudent.tIme.getText().trim())  || AddOrEditStudent.tIme.getText().trim().equals("")) {
			AddOrEditStudent.tIme.setForeground(Color.RED);
			enableButton = false;
		}
		if(!Checker.isNameOrSurename(AddOrEditStudent.tPrezime.getText().trim()) || AddOrEditStudent.tPrezime.getText().trim().equals("")) {
			AddOrEditStudent.tPrezime.setForeground(Color.RED);
			enableButton = false;
		}
		if(!Checker.isValidDate(AddOrEditStudent.tDatRodj.getText().trim(),1) || AddOrEditStudent.tDatRodj.getText().trim().equals("")) {
			AddOrEditStudent.tDatRodj.setForeground(Color.RED);
			enableButton = false;
		}
		if(!Checker.isValidAdrress(AddOrEditStudent.tAdrStan.getText().trim()) || AddOrEditStudent.tAdrStan.getText().trim().equals("")) {
			AddOrEditStudent.tAdrStan.setForeground(Color.RED);
			enableButton = false;
		}
		if(!Checker.isValidNumber(AddOrEditStudent.tBrTel.getText().trim(),0) || AddOrEditStudent.tBrTel.getText().trim().equals("")) {
			AddOrEditStudent.tBrTel.setForeground(Color.RED);
			enableButton = false;
		}
		if(!Checker.isValidEmail(AddOrEditStudent.tEmail.getText().trim()) || AddOrEditStudent.tEmail.getText().trim().equals("")) {
			AddOrEditStudent.tEmail.setForeground(Color.RED);
			enableButton = false;
		}
		if(!regularInput(GlobalConstants.indexLab, AddOrEditStudent.tBrIndexa.getText().trim()) || AddOrEditStudent.tBrIndexa.getText().trim().equals("")) {
			AddOrEditStudent.tBrIndexa.setForeground(Color.RED);
			enableButton = false;
		} else {
			AddOrEditStudent.tBrIndexa.setBorder(BorderFactory.createLineBorder(Color.GRAY));
			AddOrEditStudent.tBrIndexa.setForeground(Color.BLACK);
		}
		if(!regularInput(GlobalConstants.upisLab, AddOrEditStudent.tGodUpisa.getText().trim()) || AddOrEditStudent.tGodUpisa.getText().trim().equals("")) {
			AddOrEditStudent.tGodUpisa.setForeground(Color.RED);
			enableButton = false;
		} else {
			AddOrEditStudent.tGodUpisa.setBorder(BorderFactory.createLineBorder(Color.GRAY));
			AddOrEditStudent.tGodUpisa.setForeground(Color.BLACK);
		}
		AddOrEditStudent.potvrdi.setEnabled(enableButton);
	}
	

}
