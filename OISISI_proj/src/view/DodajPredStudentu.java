package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import model.GlobalConstants;
import model.Predmet;
import model.Student;

public class DodajPredStudentu extends JDialog{
	
	JList<String> listaPredmeta;
	JPanel north, center, west, east, south;
	DefaultListModel<String> model;
	
	JButton potvrdi,odustani;
	
	JScrollPane jsp;
	
	Student stud;
	
	public DodajPredStudentu(Student s) {
		stud = s;

		this.setSize(300,300);
		this.setModal(true);
		this.setResizable(false);
		this.setLocationRelativeTo(AddOrEditStudent.inst);
		this.setTitle(GlobalConstants.predAdd);
		
		this.setLayout(new BorderLayout());
		
		north = new JPanel();
		north.setPreferredSize(new Dimension(5,5));
		this.add(north,BorderLayout.NORTH);
		
		west = new JPanel();
		west.setPreferredSize(new Dimension(5,5));
		this.add(west,BorderLayout.WEST);
		
		east = new JPanel();
		east.setPreferredSize(new Dimension(5,5));
		this.add(east,BorderLayout.EAST);
		
		potvrdi = new JButton(GlobalConstants.btnOkName);
		potvrdi.addActionListener(new MyPotvrdiListener());
		odustani = new JButton(GlobalConstants.btnCncName);
		odustani.addActionListener(new MyOdustaniListener());
		
		south = new JPanel();
		south.add(potvrdi);
		south.add(odustani);
		this.add(south,BorderLayout.SOUTH);
		
		listaPredmeta = new JList<String>();
		model = new DefaultListModel<String>();
		String row;
		for(Predmet p : GlavniProzor.getControllerPredmet().getListaPredmeta()) {
			if(!GlavniProzor.getControllerStudent().proveriPred(s, p)) {
				row = p.getSifPred() + " - " + p.getNaziv() + " - " + p.getSemestar();
				model.addElement(row);
			}
		}
		
		listaPredmeta.setModel(model);
		
		center = new JPanel();
		jsp = new JScrollPane(listaPredmeta);
		jsp.setPreferredSize(new Dimension(270,200));
		center.add(jsp);
		
		this.add(center,BorderLayout.CENTER);
	}
	
	class MyPotvrdiListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			int selectedValues[] = listaPredmeta.getSelectedIndices();
			ArrayList<String> selectedPredIds = new ArrayList<String>();
			String temp[];
			for(int i : selectedValues) {
				temp = model.get(i).split(" - ");
				selectedPredIds.add(temp[0]);
			}
			
			GlavniProzor.getControllerStudent().dodajNepolozenePredmete(selectedPredIds, stud);
			
			TabelaPredmeti.azurirajTabeluStudNepo(stud.getBrIndexa());
			
			//Dodaj serijalizaciju
			GlavniProzor.serialize();
			
			getter().setVisible(false);
		}
	}
	
	public DodajPredStudentu getter() {
		return this;
	}
	
	class MyOdustaniListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			getter().setVisible(false);
		}
		
	}
	
}