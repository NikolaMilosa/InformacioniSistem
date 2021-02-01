package view;

import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import controller.ControllerStudent;
import model.GlobalConstants;
import model.Student;

public class TabelaStudenti extends JTable {
	
	static ControllerStudent controller;
	
	private static String[] cols = {GlobalConstants.tabStudInd, GlobalConstants.tabStudIme, GlobalConstants.tabStudPrez, GlobalConstants.tabStudGS, GlobalConstants.tabStudStat, GlobalConstants.tabStudPros};
	static DefaultTableModel model;
	public static TabelaStudenti table;
	
	public TabelaStudenti() {
		cols[0] = GlobalConstants.tabStudInd;
		cols[1] = GlobalConstants.tabStudIme;
		cols[2] = GlobalConstants.tabStudPrez;
		cols[3] = GlobalConstants.tabStudGS;
		cols[4] = GlobalConstants.tabStudStat;
		cols[5] = GlobalConstants.tabStudPros;
		table = this;
		controller = GlavniProzor.getControllerStudent();
		
		initializeTable(table);		

		updateTable();
	}
	
	public void initializeTable(TabelaStudenti table) {
		
		model = new DefaultTableModel(new Object[0][], cols) {
		Class<?>[] types = { Index.class, String.class, String.class, Integer.class, String.class, Double.class};
			
			@Override
            public Class<?> getColumnClass(int columnIndex) {
                return this.types[columnIndex];
            }
			
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		model.setColumnIdentifiers(cols);
		table.setModel(model);
		
		table.setRowHeight(35);
		table.setFont(table.getFont().deriveFont(16f));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setAutoCreateRowSorter(true);
		
		// Centriranje
		NasCellRenderer prikaz = new NasCellRenderer(NasCellRenderer.studentRenderer);
		for(Object o : cols) {
			table.getColumn(o).setCellRenderer(prikaz);
		}
	}
	
	public void updateTable() {
		ArrayList<Student> listaStudenata = controller.getListaStudenata();

		initializeTable(table);
		
		for(Student s : listaStudenata) {
			Object[] data = { new Index(s.getBrIndexa()), s.getIme(), s.getPrezime(), "", "", ""};
			data[3] = Integer.toString(s.getTrenutnaGodStud());;
			data[4] = s.getStatus();
			double zaokruzenProsek = Math.round(s.getProsecnaOcena() * 100.0) / 100.0;
			data[5] = zaokruzenProsek;
			
		    model.addRow(data);
		}
	}

	public void izlistajStudente(ArrayList<String> foundStudents) {
		ArrayList<Student> listaStudenata = controller.getListaStudenata();

		initializeTable(table);
		
		for(Student stud : listaStudenata) {
			for(String s : foundStudents) {
				if(stud.getBrIndexa().equals(s)) {
					Object[] data = { new Index(stud.getBrIndexa()), stud.getIme(), stud.getPrezime(), "", "", ""};
					data[3] = Integer.toString(stud.getTrenutnaGodStud());
					data[4] = stud.getStatus();
					double zaokruzenProsek = Math.round(stud.getProsecnaOcena() * 100.0) / 100.0;
					data[5] = zaokruzenProsek;
					
				    model.addRow(data);
				}
			}
		}
	}
	
	public class Index implements Comparable{
		String smer;
		Integer broj;
		Integer godina;
		
		public String getSmer() { return smer;}
		public Integer getBroj() {return broj;}
		public Integer getGodina() {return godina;}
		
		public Index(String index) {
			String parts[] = index.split("-");
			this.smer = parts[0];
			this.broj = Integer.parseInt(parts[1]);
			this.godina = Integer.parseInt(parts[2]);
		}
		
		@Override
		public String toString() {
			return smer + "-" + broj + "-" + godina;
		}
		@Override
		public int compareTo(Object o) {
			
			Index temp = (Index) o;
			if(this.getSmer().compareTo(temp.getSmer()) != 0)
				return this.getSmer().compareTo(temp.getSmer());
			else {
				if(this.getGodina().compareTo(temp.getGodina()) != 0)
					return this.getGodina().compareTo(temp.getGodina());
				else
					return this.getBroj().compareTo(temp.getBroj());
			}
		}
		
		
	}
}
