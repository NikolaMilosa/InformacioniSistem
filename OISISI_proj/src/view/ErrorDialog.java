package view;

import javax.swing.*;

import model.GlobalConstants;

public class ErrorDialog{
	
	public ErrorDialog(String message) {
		JOptionPane.showMessageDialog(new JFrame(), message, GlobalConstants.errName ,JOptionPane.ERROR_MESSAGE);
	}
}
