package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import controller.Checker;
import controller.ControllerProfesor;
import model.GlobalConstants;
import model.Profesor;

public class AddProfToPredDialog extends JDialog {

	private ControllerProfesor controller;
	private JList<String> list;
	private JButton potvrdi, odustani;
	
	public static Profesor prof;
	
	public AddProfToPredDialog() {
		
		controller = GlavniProzor.getControllerProfesor();
		
		setResizable(false);
		setSize(320,250);
		setModal(true);
		setLocationRelativeTo(AddOrEditPredmet.inst);
		setTitle(GlobalConstants.dodavanjeProfPredDialog);
		
		JPanel glavni = new JPanel();
		glavni.setLayout(new BoxLayout(glavni, BoxLayout.Y_AXIS));
		
		ArrayList<Profesor> profesori = controller.getListaProfesora();
		ArrayList<String> listaBrLic = new ArrayList<String>();
		
		String[] data  =  new String[profesori.size()];
		
		int i = 0;
		for(Profesor prof : profesori) {
			data[i] = prof.getIme() + " " + prof.getPrezime() + " " + prof.getEmail();
			listaBrLic.add(i,prof.getBrLicKart());
			i++;
		}
		
		list = new JList<String>(data); 
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setVisibleRowCount(-1);
		
		JScrollPane scroll = new JScrollPane(list);
		scroll.setMaximumSize(new Dimension(300,200));
		glavni.add(scroll);
		add(glavni, BorderLayout.CENTER);
		
		JPanel dugmad = new JPanel();
		
		potvrdi = new JButton(GlobalConstants.btnOkName);
		dugmad.add(potvrdi);
		
		odustani = new JButton(GlobalConstants.btnCncName);
		dugmad.add(odustani);
		
		add(dugmad,BorderLayout.SOUTH);
		
		odustani.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
			
		});
		
		potvrdi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(list.getSelectedIndex() != -1) {
					int index = list.getSelectedIndex();
					
					prof = controller.nadjiProfesora(listaBrLic.get(index));
					AddOrEditPredmet.tProf.setText(prof.getIme() + " " + prof.getPrezime());
					
					setVisible(false);
					AddOrEditPredmet.tProf.setBorder(BorderFactory.createLineBorder(Color.BLACK));
					AddOrEditPredmet.minus.setEnabled(true);
					AddOrEditPredmet.plus.setEnabled(false);
					boolean enableButton = true;
					if(!Checker.isValidNamePred(AddOrEditPredmet.tNaziv.getText()) || AddOrEditPredmet.tNaziv.getText().trim().equals("")) {
						AddOrEditPredmet.tNaziv.setBorder(BorderFactory.createLineBorder(Color.RED));
						AddOrEditPredmet.tNaziv.setForeground(Color.RED);
						enableButton = false;
					}
					
					if(!Checker.isValidEspb(AddOrEditPredmet.tEspb.getText()) || AddOrEditPredmet.tEspb.getText().trim().equals("")) {
						AddOrEditPredmet.tEspb.setBorder(BorderFactory.createLineBorder(Color.RED));
						AddOrEditPredmet.tEspb.setForeground(Color.RED);
						enableButton = false;
					}

					AddOrEditPredmet.potvrdi.setEnabled(enableButton);
				}
			}
			
		});
		
	}
	
}
