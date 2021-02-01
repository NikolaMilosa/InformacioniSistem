package view;

import java.util.ArrayList;

//Importi : 
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import controller.ControllerPredmet;
import model.GlobalConstants;
import model.Predmet;
import model.Profesor;

public class TabelaPredmeti extends JTable { 
	
	private static Object[] colNames = {GlobalConstants.spr, GlobalConstants.npr, GlobalConstants.espb,GlobalConstants.god, GlobalConstants.sem};
	static DefaultTableModel model;
	static DefaultTableModel modelProf;
	static DefaultTableModel modelStudNepo;
	static ControllerPredmet controllerPredmet;
	public static TabelaPredmeti inst;
	public static TabelaPredmeti instProf;
	public static TabelaPredmeti instStudNepo;
	
	private static Object[] colNamesProf = {GlobalConstants.spr, GlobalConstants.npr, GlobalConstants.god, GlobalConstants.sem};
	
	public TabelaPredmeti(int sluc) {
		
		colNames[0] = GlobalConstants.spr;
		colNames[1] = GlobalConstants.npr;
		colNames[2] = GlobalConstants.espb;
		colNames[3] = GlobalConstants.god;
		colNames[4] = GlobalConstants.sem;
		
		colNamesProf[0] = GlobalConstants.spr;
		colNamesProf[1] = GlobalConstants.npr;
		colNamesProf[2] = GlobalConstants.god;
		colNamesProf[3] = GlobalConstants.sem;
		switch(sluc) {
		case 0:
			inst = this;
			break;
		case 1:
			instProf = this;
			break;
		case 2:
			instStudNepo = this;
			break;
		}
		this.getTableHeader().setReorderingAllowed(false);
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		controllerPredmet = GlavniProzor.getControllerPredmet();
		
		azurirajTabelu();
	}
	
	//Inicijalizacija modela tabele :
	public static void tableInitialize(TabelaPredmeti t, int sluc) {		
		if(sluc == 0) {
			model = new DefaultTableModel(new Object[0][], colNames) {
				Class[] types = { String.class, String.class, Integer.class, String.class , String.class};
				
				@Override
	            public Class getColumnClass(int columnIndex) {
	                return this.types[columnIndex];
	            }
				
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
			NasCellRenderer poravnanje = new NasCellRenderer(NasCellRenderer.predmetRenderer);
				for(int i = 0; i < colNames.length; i++)
					t.getColumnModel().getColumn(i).setCellRenderer(poravnanje);
		} else if(sluc == 1) {
			modelProf = new DefaultTableModel(new Object[0][], colNamesProf) {
				
				Class[] types = { String.class, String.class, Integer.class, String.class };
				
				@Override
	            public Class getColumnClass(int columnIndex) {
	                return this.types[columnIndex];
	            }
				
				@Override
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			};
			modelProf.setColumnIdentifiers(colNamesProf);
			t.setModel(modelProf);
			t.setRowHeight(20);
			t.setAutoCreateRowSorter(true);
			t.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
			
			//Poravnanje : 
			NasCellRenderer poravnanje = new NasCellRenderer(NasCellRenderer.predmetRenderer);
			for(int i = 0; i < colNamesProf.length; i++)
				t.getColumnModel().getColumn(i).setCellRenderer(poravnanje);
		} else if(sluc == 2) {
			modelStudNepo = new DefaultTableModel(new Object[0][], colNames) {
				
				Class[] types = { String.class, String.class, Integer.class, String.class , String.class};
				
				@Override
	            public Class getColumnClass(int columnIndex) {
	                return this.types[columnIndex];
	            }
				
				@Override
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			};
			modelStudNepo.setColumnIdentifiers(colNames);
			t.setModel(modelStudNepo);
			t.setRowHeight(20);
			t.setAutoCreateRowSorter(true);
			
			//Poravnanje :
			NasCellRenderer poravnanje = new NasCellRenderer(NasCellRenderer.predmetRenderer);
			for(int i = 0; i < colNamesProf.length; i++)
				t.getColumnModel().getColumn(i).setCellRenderer(poravnanje);
		}
	}
	
	//Metoda za aruriranje tabele predmeta kod studenta :
	
	public static void azurirajTabeluStudNepo(String brInd) {
		ArrayList<Predmet> listaPredmeta = GlavniProzor.getControllerStudent().nadjiStudenta(brInd).getNepolozeniIspiti();
			
		tableInitialize(instStudNepo,2);
			
		String sifra,naziv,godina,semestar;
		int espb;
		Object[] row = {"", "", 0,"",""};
		for(Predmet p : listaPredmeta) {
			sifra = p.getSifPred();
			naziv = p.getNaziv();
			espb = p.getEspbBod();
			godina = p.outGodIzv(p.getGodIzv());
			semestar = ""+p.getSemestar();
				
			row[0] = sifra;
			row[1] = naziv;
			row[2] = espb;
			row[3] = godina;
			row[4] = semestar;
				
			modelStudNepo.addRow(row);
		}
	}
	
		//Metoda za izlistavanje profesora : 
		
	public static void azurirajTabelu() {
		ArrayList<Predmet> listaPredmeta = controllerPredmet.getListaPredmeta();
			
		tableInitialize(inst,0);
		
		String sifra,naziv,godina,semestar;
		int espb;
		Object[] row = {"", "", 0, "", ""};
		
		for(Predmet p : listaPredmeta) {
			sifra = p.getSifPred();
			naziv = p.getNaziv();
			espb = p.getEspbBod();
			godina = p.outGodIzv(p.getGodIzv());
			semestar = ""+p.getSemestar();
				
			row[0] = sifra;
			row[1] = naziv;
			row[2] = espb;
			row[3] = godina;
			row[4] = semestar;
				
			model.addRow(row);
		}
	}
	
	//Metode za ispis predmeta jednog profesora :
	public static void azurirajTabeluProf(Profesor p) {
			
		tableInitialize(instProf,1);
		
		String sifra,naziv,godina,semestar;
		Object[] row = {"","","","",""};
		for(Predmet pr : p.getSpisPred()) {
			sifra = pr.getSifPred();
			naziv = pr.getNaziv();
			godina = pr.outGodIzv(pr.getGodIzv());
			semestar = ""+pr.getSemestar();
				
			row[0] = sifra;
			row[1] = naziv;
			row[2] = godina;
			row[3] = semestar;
				
			modelProf.addRow(row);
		}
	}
	
	//Metoda za izlistavanje predmeta : 
	
	public static void izlistajPredmete(ArrayList<String> foundSifP) {
		ArrayList<Predmet> listaPredmeta = controllerPredmet.getListaPredmeta();
		
		tableInitialize(inst, 0);
		
		String sifra,naziv,godina,semestar;
		int espb;
		Object[] row = {"", "",0, "", "", };
		for(Predmet pr : listaPredmeta) {
			for(String sp : foundSifP) {
				if(sp.equals(pr.getSifPred())) {
					sifra = pr.getSifPred();
					naziv = pr.getNaziv();
					espb = pr.getEspbBod();
					godina = pr.outGodIzv(pr.getGodIzv());
					semestar = ""+pr.getSemestar();
					
					row[0] = sifra;
					row[1] = naziv;
					row[2] = espb;
					row[3] = godina;
					row[4] = semestar;
					
					model.addRow(row);
				}
			}
		}
	}
}
