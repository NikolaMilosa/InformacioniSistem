package view;

//Importi : 

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import model.GlobalConstants;
import model.Profesor;
import controller.ControllerProfesor;

import java.util.*;

public class TabelaProfesora extends JTable { 
	
	private static Object[] colNames = {GlobalConstants.brLicKartCol,GlobalConstants.imeCol, GlobalConstants.przCol, GlobalConstants.titulaCol,GlobalConstants.zvanjeCol};
	static DefaultTableModel model;
	static ControllerProfesor controllerProfesor;
	public static TabelaProfesora inst;
	
	public TabelaProfesora() {
		colNames[0] = GlobalConstants.brLicKartCol;
		colNames[1] = GlobalConstants.imeCol;
		colNames[2] = GlobalConstants.przCol;
		colNames[3] = GlobalConstants.titulaCol;
		colNames[4] = GlobalConstants.zvanjeCol;
		
		inst = this;
		controllerProfesor = GlavniProzor.getControllerProfesor();
		inst.getTableHeader().setReorderingAllowed(false);
		inst.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		tableInitialize(inst);
		
		//Test primer!
		
		azurirajTabelu();
	}
	
	//Inicijalizacija modela tabele :
	public static void tableInitialize(TabelaProfesora t) {
		model = new DefaultTableModel(){
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		model.setColumnIdentifiers(colNames);
		t.setModel(model);
		
		t.setRowHeight(35);
		t.setAutoCreateRowSorter(true);
		t.setFont(t.getFont().deriveFont(16F));

		//Poravnanje :
		NasCellRenderer poravnanje = new NasCellRenderer(NasCellRenderer.profesorRenderer);
		for(int i = 0; i < colNames.length; i++)
			t.getColumnModel().getColumn(i).setCellRenderer(poravnanje);
	}
	
	//Metoda za izlistavanje profesora : 
	
	public static void azurirajTabelu() {
		ArrayList<Profesor> listaProfesora = controllerProfesor.getListaProfesora();
		
		tableInitialize(inst);
		
		String brLicKart,ime,prezime,titula,zvanje;
		Object[] row = {"", "", "", "",""};
		
		for(Profesor p : listaProfesora) {
			brLicKart = p.getBrLicKart();
			ime = p.getIme();
			prezime = p.getPrezime();
			titula = p.getTitula();
			zvanje = p.getZvanje();
			
			row[0] = brLicKart;
			row[1] = ime;
			row[2] = prezime;
			row[3] = titula;
			row[4] = zvanje;
			
			model.addRow(row);
		}
	}
	
	public static void izlistajProfesore(ArrayList<String> foundBrLic) {
		
		ArrayList<Profesor> listaProfesora = controllerProfesor.getListaProfesora();
		
		tableInitialize(inst);
		
		String brLicKart,ime,prezime,titula,zvanje;
		Object[] row = {"", "", "", "",""};
		for(Profesor p : listaProfesora) {
			for(String br : foundBrLic) {
				if(br.equals(p.getBrLicKart())) {
					brLicKart = p.getBrLicKart();
					ime = p.getIme();
					prezime = p.getPrezime();
					titula = p.getTitula();
					zvanje = p.getZvanje();
					
					row[0] = brLicKart;
					row[1] = ime;
					row[2] = prezime;
					row[3] = titula;
					row[4] = zvanje;
					
					model.addRow(row);
				}
					
			}
		}
	}
	
}
