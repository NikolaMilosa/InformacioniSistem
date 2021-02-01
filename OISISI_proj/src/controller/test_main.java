package controller;

//importi :
import model.*;
import view.*;

public class test_main {

	public static int language = 1;             //Srpski - 1, Engleski - 2;
	
	public static void main(String[] args) {		
		
		//Glavni prozor :
		
		GlobalConstants.setLangSerbian();
		GlavniProzor glavniProzor = new GlavniProzor();
		glavniProzor.setVisible(true);
		
	}

}
