package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import model.*;

public class AddPredToProfDialog extends JDialog{
	
	Profesor currProf;
	JPanel wholeDialog,north,south,east,west;
	JLabel predText;
	JScrollPane center;
	JButton potvrdi,odustani;
	JList<String> listaPredmeta;
	DefaultListModel<String> model;
	AddPredToProfDialog inst;
	
	public AddPredToProfDialog(Profesor pr) {
		inst = this;
		currProf = pr;
		this.setResizable(false);
		this.setSize(320,250);
		this.setModal(true);
		this.setLocationRelativeTo(AddOrEditProfesor.inst);
		this.setTitle(GlobalConstants.dodavanjePredProfDialog);
		
		wholeDialog = new JPanel();
		wholeDialog.setLayout(new BorderLayout());
		
		north = new JPanel();
		north.setPreferredSize(new Dimension(320,20));
		north.setLayout(new BoxLayout(north, BoxLayout.X_AXIS));
		north.add(Box.createRigidArea(new Dimension(10,0)));
		predText = new JLabel();
		predText.setText(GlobalConstants.predmetiTekst);
		north.add(predText);
		wholeDialog.add(north,BorderLayout.NORTH);
		
		south = new JPanel();
		south.setPreferredSize(new Dimension(300,40));
		south.setLayout(new BoxLayout(south, BoxLayout.X_AXIS));
		potvrdi = new JButton();
		potvrdi.addActionListener(new MyPotvrdiListener());
		potvrdi.setText(GlobalConstants.btnOkName);
		odustani = new JButton();
		odustani.setText(GlobalConstants.btnCncName);
		odustani.addActionListener(new MyOdustaniListener());
		
		
		south.add(Box.createRigidArea(new Dimension(110,0)));
		south.add(potvrdi);
		south.add(Box.createRigidArea(new Dimension(10,0)));
		south.add(odustani);
		wholeDialog.add(south,BorderLayout.SOUTH);
		
		east = new JPanel();
		east.setSize(new Dimension(5,20));
		west = new JPanel();
		west.setSize(new Dimension(5,20));
		wholeDialog.add(east, BorderLayout.EAST);
		wholeDialog.add(west, BorderLayout.WEST);
		
		listaPredmeta = new JList<String>();
		model = new DefaultListModel<String>();
		String row;
		for(Predmet p : GlavniProzor.getControllerPredmet().getListaPredmeta()) {
			if(!currProf.getSpisPred().contains(p)) {
				row = p.getSifPred() + " - " + p.getNaziv();
				model.addElement(row);
			}
		}
		listaPredmeta.setModel(model);
		
		center = new JScrollPane(listaPredmeta);
		center.setMaximumSize(new Dimension(300,300));
		wholeDialog.add(center,BorderLayout.CENTER);
		this.add(wholeDialog);
		this.setVisible(true);
	}
	
	class MyOdustaniListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			inst.setVisible(false);		
		}
	}
	
	class MyPotvrdiListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			int selectedValues[] = listaPredmeta.getSelectedIndices();
			ArrayList<String> selectedPredIds = new ArrayList<String>();
			String temp[];
			for(int i : selectedValues) {
				temp = model.get(i).split(" ");
				selectedPredIds.add(temp[0]);
			}
			
			GlavniProzor.getControllerProfesor().dodajVisePredmetaProf(currProf.getBrLicKart(), selectedPredIds);
			
			TabelaPredmeti.azurirajTabeluProf(currProf);
			
			GlavniProzor.serialize();
			
			inst.setVisible(false);
		}
		
	}
}
