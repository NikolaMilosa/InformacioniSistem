package view;

import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import controller.ControllerStudent;
import model.GlobalConstants;
import model.Ocena;

public class TabelaOcena extends JTable {

	static ControllerStudent controller;

	private static String[] cols = {GlobalConstants.tabOcenaSif, GlobalConstants.tabOcenaNaz, GlobalConstants.advSearchESPBTok, GlobalConstants.tabOcenaOce, GlobalConstants.tabOcenaDat};
	static DefaultTableModel model;
	public static TabelaOcena inst;


	public TabelaOcena(String index) {
		cols[0] = GlobalConstants.tabOcenaSif;
		cols[1] = GlobalConstants.tabOcenaNaz;
		cols[2] = GlobalConstants.advSearchESPBTok;
		cols[3] = GlobalConstants.tabOcenaOce;
		cols[4] = GlobalConstants.tabOcenaDat;
		
		inst = this;

		controller = GlavniProzor.getControllerStudent();

		initializeTable(inst);		

		updateTable(index);
	}

	public void initializeTable(TabelaOcena table) {

		model = new DefaultTableModel() {

			public boolean isCellEditable(int row, int col) {
				return false;
			}
		};

		model.setColumnIdentifiers(cols);
		table.setModel(model);

		table.setRowHeight(20);
		table.setAutoCreateRowSorter(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}

	public void updateTable(String index) {
		ArrayList<Ocena> listaOcena = controller.nadjiStudenta(index).getPolozeniIspiti();

		initializeTable(inst);

		for(Ocena o : listaOcena) {
			String[] data = { o.getPredmet().getSifPred(), o.getPredmet().getNaziv(), "", "", ""};
			data[2] = Integer.toString(o.getPredmet().getEspbBod());
			data[3] = Integer.toString(o.getBrVrednost());
			data[4] = o.getDatumPolaganja();
			
		    model.addRow(data);
		}
	}
}
