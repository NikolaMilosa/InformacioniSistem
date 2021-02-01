package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.ControllerPredmet;
import controller.PredmetFocusListeners;
import model.GlobalConstants;
import model.Predmet;
import model.Predmet.GodIzv;
import model.Predmet.Semestar;
import model.Profesor;

public class AddOrEditPredmet extends JPanel {

	private Predmet predmet;
	private ControllerPredmet controller;
	
	private JLabel lSifra, lNaziv, lGodIzv, lSemestar, lEspb, lProf;
	public static JTextField tSifra, tNaziv, tEspb, tProf;
	private JComboBox<String> tGodIzv, tSemestar;
	public static JButton plus, minus, potvrdi, odustani;
	private ErrorDialog err;
	private AddProfToPredDialog addProf;
	
	public static AddOrEditPredmet inst;
	
	public AddOrEditPredmet(int mode, AddOrEditDialog dialog) {
		inst = this;
		
		controller = GlavniProzor.getControllerPredmet();
		setLayout(new BorderLayout());
		
		JPanel glavni = new JPanel();
		glavni.setLayout(new BoxLayout(glavni, BoxLayout.Y_AXIS));
		
		lSifra = new JLabel(GlobalConstants.sifraLab);
		tSifra = new JTextField();
		tSifra.setName(GlobalConstants.sifraLab);
		
		lNaziv = new JLabel(GlobalConstants.nazivLab);
		tNaziv = new JTextField();
		tNaziv.setName(GlobalConstants.nazivLab);
		tNaziv.setToolTipText(GlobalConstants.nazivPredToolTip);
		
		lGodIzv = new JLabel(GlobalConstants.godIzvLab);
		String[] godIzv = {"1", "2", "3", "4"};
		tGodIzv = new JComboBox<String>(godIzv);
		
		lSemestar = new JLabel(GlobalConstants.semestarLab);
		String[] semestar = {GlobalConstants.advSearchSemPos3, GlobalConstants.advSearchSemPos4};
		tSemestar = new JComboBox<String>(semestar);
		
		lEspb = new JLabel(GlobalConstants.espbLab);
		tEspb = new JTextField();
		tEspb.setName(GlobalConstants.espbLab);
		tEspb.setToolTipText(GlobalConstants.espbToolTip);
		
		lProf = new JLabel(GlobalConstants.profLab);
		tProf = new JTextField();
		tProf.setName(GlobalConstants.profLab);
		tProf.setToolTipText(GlobalConstants.profToolTip);
		tProf.setEditable(false);
		
		plus = new JButton("+");
		minus = new JButton("-");
		minus.setEnabled(false);
		
		JLabel lab = new JLabel();
		lab.setPreferredSize(new Dimension(150, 25));
		
		glavni.add(createPanel(lSifra, tSifra));
		glavni.add(createPanel(lNaziv, tNaziv));
		glavni.add(createListPanel(lGodIzv, tGodIzv));
		glavni.add(createListPanel(lSemestar, tSemestar));
		glavni.add(createPanel(lEspb, tEspb));
		glavni.add(createButtonPanel(lProf, tProf, plus, minus));
		glavni.add(lab);
		
		add(glavni, BorderLayout.NORTH);
		
		// Focus listeners
		tSifra.addFocusListener(new PredmetFocusListeners());
		tNaziv.addFocusListener(new PredmetFocusListeners());
		tEspb.addFocusListener(new PredmetFocusListeners());
		
        JPanel dugmad = new JPanel();
		
		potvrdi = new JButton(GlobalConstants.btnOkName);
		potvrdi.setEnabled(false);
		dugmad.add(potvrdi);
		
		odustani = new JButton(GlobalConstants.btnCncName);
		dugmad.add(odustani);
		
		add(dugmad,BorderLayout.SOUTH);
		
		if(mode == AddOrEditDialog.addMode) {
			tProf.setText(GlobalConstants.prdNemaProf);
		}
		
		
		if(mode == AddOrEditDialog.editMode) {
			
			int selectedRow = TabelaPredmeti.inst.getSelectedRow();
			if(selectedRow != -1) {
				String sifraSelectedPred = (String) TabelaPredmeti.inst.getValueAt(selectedRow, 0);
				predmet = controller.nadjiPredmet(sifraSelectedPred);
				
				AddProfToPredDialog.prof = predmet.getProf();
				
				tSifra.setText(predmet.getSifPred());
				tSifra.setEditable(false);
				tNaziv.setText(predmet.getNaziv());
				
				tGodIzv.setSelectedItem(predmet.getNumGodinaEdit());
				tSemestar.setSelectedItem(predmet.getStrSem());
				
				String espb = String.valueOf(predmet.getEspbBod());
				tEspb.setText(espb);
				
				Profesor prof = predmet.getProf();
				if(prof.getBrLicKart().equals(""))
					tProf.setText(GlobalConstants.prdNemaProf);
				else
					tProf.setText(prof.getIme() + " " + prof.getPrezime());
				if(!tProf.getText().equals(GlobalConstants.prdNemaProf)) {
					plus.setEnabled(false);
					minus.setEnabled(true);
				}

			}
		}
		
		odustani.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dialog.setVisible(false);
			}
			
		});
		
		
		potvrdi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String sifra = tSifra.getText().trim();
				String naziv = tNaziv.getText().trim().substring(0,1).toUpperCase() + tNaziv.getText().trim().substring(1);
				
				GodIzv god;
				
				switch((String) tGodIzv.getSelectedItem()) {
					case "1" : god = GodIzv.PRVA; break;
					case "2" : god = GodIzv.DRUGA; break;
					case "3" : god = GodIzv.TRECA; break;
					default:   god = GodIzv.CETVRTA; break;
				}
			
				Semestar sem = Semestar.ZIMSKI;
				
				if((String) tSemestar.getSelectedItem() != GlobalConstants.advSearchSemPos3)
					sem = Semestar.LETNJI;
				
				int espbBodovi = Integer.parseInt(tEspb.getText().trim());
				
				dialog.setVisible(false);
				
				if(mode == AddOrEditDialog.addMode) {
					predmet = new Predmet();
					predmet.setSifPred(sifra); 
					predmet.setNaziv(naziv);
					predmet.setGodIzv(god);
					predmet.setSemestar(sem);
					predmet.setEspbBod(espbBodovi);
					
					if(tProf.getText().equals(GlobalConstants.prdNemaProf))
						predmet.setProf(GlobalConstants.dummy);
					else {
						predmet.setProf(AddProfToPredDialog.prof);
						predmet.getProf().getSpisPred().add(predmet);
					}
					/*
					if(prof != null)
						predmet.setProf(prof);
					*/
					if(!controller.dodajPredmet(predmet))
						err = new ErrorDialog(GlobalConstants.errAddPred);
				
				} else {
					predmet.setNaziv(naziv);
					predmet.setGodIzv(god);
					predmet.setSemestar(sem);
					predmet.setEspbBod(espbBodovi);
					if(!tProf.getText().equals(GlobalConstants.prdNemaProf)) {
						//GlavniProzor.getControllerProfesor().obrisiPredmetKodSvihProf(predmet.getSifPred());
                        predmet.setProf(AddProfToPredDialog.prof);
                        GlavniProzor.getControllerProfesor().dodajProfPred(AddProfToPredDialog.prof,predmet);
					}
					else {
						GlavniProzor.getControllerProfesor().obrisiPredmetKodProf(predmet.getProf(), predmet);
						predmet.setProf(GlobalConstants.dummy);
					}
					
				}
				
				TabelaPredmeti.azurirajTabelu();
				
				GlavniProzor.serialize();
			}
		});
		
		plus.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				addProf = new AddProfToPredDialog();
				addProf.setVisible(true);
				
			}
		});
		
		minus.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String [] options = {GlobalConstants.yesOpt,GlobalConstants.noOpt};
				int code = JOptionPane.showOptionDialog(inst, GlobalConstants.upitSklanjanjeProfSaPred, GlobalConstants.upitSklanjanjeProfTitle, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null);
				
				if(code == JOptionPane.YES_OPTION) {				
					tProf.setText(GlobalConstants.prdNemaProf);
					plus.setEnabled(true);
					minus.setEnabled(false);
					
				}
			}
		});
	}	
	
	public JPanel createPanel(JLabel label, JTextField text) {
		JPanel panel = new JPanel();
		
		label.setPreferredSize(new Dimension(100, 25));
		panel.add(label);
		
		text.setPreferredSize(new Dimension(250, 25));
		panel.add(text);
		
		return panel;
	}
	
	public JPanel createListPanel(JLabel label, JComboBox<String> text) {
		JPanel panel = new JPanel();
		
		label.setPreferredSize(new Dimension(100, 25));
		panel.add(label);
		
		text.setPreferredSize(new Dimension(250, 25));
		panel.add(text);
		
		return panel;
	}
	
	public JPanel createButtonPanel(JLabel label, JTextField text, JButton p, JButton m) {
		JPanel panel = new JPanel();
		
		label.setPreferredSize(new Dimension(100, 25));
		panel.add(label);
		
		text.setPreferredSize(new Dimension(150, 25));
		panel.add(text);
		
		p.setPreferredSize(new Dimension(45,25));
		m.setPreferredSize(new Dimension(45,25));
		panel.add(p);
		panel.add(m);
		
		return panel;
	}
}
