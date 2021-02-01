package view;


import model.*;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.*;

import controller.ControllerStudent;
import controller.StudentFocusListeners;
import model.Predmet;
import view.TabelaStudenti.Index;

public class AddOrEditStudent extends JPanel {
	
	private Student student;
	private ControllerStudent controller;
	
	public static JTextField tIme, tPrezime, tDatRodj, tAdrStan, tBrTel, tEmail, tBrIndexa, tGodUpisa;
	private JLabel lIme, lPrezime, lDatRodj, lAdrStan, lBrTel, lEmail, lBrIndexa, lGodUpisa, lTrenutnaGod, lFinans;
	private JComboBox<String>  tTrenutnaGod, tFinans;
	private JLabel prosek, ukupnoEspb;
	JPanel polozeni;
	public static JButton potvrdi, odustani;
	private ErrorDialog err;
	
	public static AddOrEditStudent inst;
	
	public AddOrEditStudent(int mode, AddOrEditDialog dialog) {
		inst = this;
		controller = GlavniProzor.getControllerStudent();
		setLayout(new BorderLayout());
		if(mode == AddOrEditDialog.editMode)
			setPreferredSize(new Dimension(400,450));
				
		JPanel glavni = new JPanel();
		glavni.setLayout(new BoxLayout(glavni, BoxLayout.Y_AXIS));
		
		lIme = new JLabel(GlobalConstants.imeLab);
		tIme = new JTextField();
		tIme.setName(GlobalConstants.imeLab);
		tIme.setToolTipText(GlobalConstants.przImeToolTip);
		
		lPrezime = new JLabel(GlobalConstants.przLab);
		tPrezime = new JTextField();
		tPrezime.setName(GlobalConstants.przLab);
		tPrezime.setToolTipText(GlobalConstants.przImeToolTip);
		
		lDatRodj = new JLabel(GlobalConstants.drLab);
		tDatRodj = new JTextField();
		tDatRodj.setName(GlobalConstants.drLab);
		tDatRodj.setToolTipText(GlobalConstants.drpToolTip);
		
		lAdrStan = new JLabel(GlobalConstants.adrStanLab);
		tAdrStan = new JTextField();
		tAdrStan.setName(GlobalConstants.adrStanLab);
		tAdrStan.setToolTipText(GlobalConstants.adrToolTip);
		
		lBrTel = new JLabel(GlobalConstants.konTelLab);
		tBrTel = new JTextField();
		tBrTel.setName(GlobalConstants.konTelLab);
		tBrTel.setToolTipText(GlobalConstants.konTelToolTip);
		
		lEmail = new JLabel(GlobalConstants.emailLab);
		tEmail = new JTextField();
		tEmail.setName(GlobalConstants.emailLab);
		tEmail.setToolTipText(GlobalConstants.emailToolTip);
		
		lBrIndexa = new JLabel(GlobalConstants.indexLab);
		tBrIndexa = new JTextField();
		tBrIndexa.setName(GlobalConstants.indexLab);
		tBrIndexa.setToolTipText(GlobalConstants.brIndexaToolTip);
		
		lGodUpisa = new JLabel(GlobalConstants.upisLab);
		tGodUpisa = new JTextField();
		tGodUpisa.setName(GlobalConstants.upisLab);
		tGodUpisa.setToolTipText(GlobalConstants.godUpisaToolTip);
		
		lTrenutnaGod = new JLabel(GlobalConstants.trenutnaLab);
		String[] godStud = {GlobalConstants.AOESgod1, GlobalConstants.AOESgod2, GlobalConstants.AOESgod3, GlobalConstants.AOESgod4};
		tTrenutnaGod = new JComboBox<String>(godStud);
		
		lFinans = new JLabel(GlobalConstants.finansLab);
		String[] data = {GlobalConstants.AOESBudz, GlobalConstants.AOESSamof};
		tFinans = new JComboBox<String>(data);
		
		JLabel lab = new JLabel();
		lab.setPreferredSize(new Dimension(150, 25));
		
		glavni.add(createPanel(lIme, tIme));
		glavni.add(createPanel(lPrezime, tPrezime));
		glavni.add(createPanel(lDatRodj, tDatRodj));
		glavni.add(createPanel(lAdrStan, tAdrStan));
		glavni.add(createPanel(lBrTel, tBrTel));
		glavni.add(createPanel(lEmail, tEmail));
		glavni.add(createPanel(lBrIndexa, tBrIndexa));
		glavni.add(createPanel(lGodUpisa, tGodUpisa));
		glavni.add(createListPanel(lTrenutnaGod, tTrenutnaGod));
		glavni.add(createListPanel(lFinans, tFinans));
		glavni.add(lab);
		
		
		// Focus listeners
		tIme.addFocusListener(new StudentFocusListeners());
		tPrezime.addFocusListener(new StudentFocusListeners());
		tDatRodj.addFocusListener(new StudentFocusListeners());
		tAdrStan.addFocusListener(new StudentFocusListeners());
		tBrTel.addFocusListener(new StudentFocusListeners());
		tEmail.addFocusListener(new StudentFocusListeners());
		tBrIndexa.addFocusListener(new StudentFocusListeners());
		tGodUpisa.addFocusListener(new StudentFocusListeners());
		
		JPanel dugmad = new JPanel();
		
		potvrdi = new JButton(GlobalConstants.btnOkName);
		dugmad.add(potvrdi);
		
		odustani = new JButton(GlobalConstants.btnCncName);
		dugmad.add(odustani);
		
		if(mode == AddOrEditDialog.addMode) {
			add(glavni, BorderLayout.NORTH);
			add(dugmad,BorderLayout.SOUTH);
		}
		
		if(mode == AddOrEditDialog.editMode) {
				
			int selectedStudent = TabelaStudenti.table.getSelectedRow();
			String indexStudenta = ((Index) TabelaStudenti.table.getValueAt(selectedStudent, 0)).toString();
				
			student = controller.nadjiStudenta(indexStudenta);
				
			tIme.setText(student.getIme());
			tPrezime.setText(student.getPrezime());
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
			tDatRodj.setText(dtf.format(student.getDatumRodj()));
			tAdrStan.setText(student.getAdresaStan());
			tBrTel.setText(student.getKonTel());
			tEmail.setText(student.getEmail());
			tBrIndexa.setText(student.getBrIndexa());
			tGodUpisa.setText(student.getGodUpisa());
			tTrenutnaGod.setSelectedIndex(student.getTrenutnaGodStud() - 1);
			if(student.getStatus().equals("B"))
				tFinans.setSelectedItem(GlobalConstants.AOESBudz);
			else
				tFinans.setSelectedItem(GlobalConstants.AOESSamof);
				
			JPanel inf = new JPanel();
			inf.setLayout(new BorderLayout());
			inf.add(glavni,BorderLayout.NORTH);
			inf.add(dugmad, BorderLayout.SOUTH);
				
			polozeni = new JPanel();
			JPanel nepolozeni = new JPanel();
			nepolozeni.setLayout(new BoxLayout(nepolozeni, BoxLayout.Y_AXIS));
			//Nepolozeni :
			
			JButton dodajPredmet = new JButton(GlobalConstants.btnDodaj);
			dodajPredmet.addActionListener(new MyDodajPredListener());
			JButton obrisiPredmet = new JButton(GlobalConstants.btnObrisi);
			obrisiPredmet.addActionListener(new MyObrisiPredListener());
			JButton polaganjePredmeta = new JButton(GlobalConstants.btnPolaganje);
			polaganjePredmeta.addActionListener(new MyPolaganjeListener());
			
			JPanel topSep = new JPanel();
			topSep.setMaximumSize(new Dimension(5,5));
			nepolozeni.add(topSep);
			
			JPanel buttons = new JPanel();
			buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS));
			JSeparator btnSep1 = new JSeparator(SwingConstants.VERTICAL);
			btnSep1.setMaximumSize(new Dimension(20,0));
			JSeparator btnSep2 = new JSeparator(SwingConstants.VERTICAL);
			btnSep2.setMaximumSize(new Dimension(5,0));
			JSeparator btnSep3 = new JSeparator(SwingConstants.VERTICAL);
			btnSep3.setMaximumSize(new Dimension(5,0));
			JSeparator btnSep4 = new JSeparator(SwingConstants.VERTICAL);
			btnSep4.setMaximumSize(new Dimension(200,0));
			
			buttons.add(btnSep1);
			buttons.add(dodajPredmet);
			buttons.add(btnSep2);
			buttons.add(obrisiPredmet);
			buttons.add(btnSep3);
			buttons.add(polaganjePredmeta);
			buttons.add(btnSep4);
			
			nepolozeni.add(buttons);
			
			JPanel cenSep = new JPanel();
			cenSep.setMaximumSize(new Dimension(5,5));
			cenSep.setPreferredSize(new Dimension(5,5));
			nepolozeni.add(cenSep);
			
			TabelaPredmeti nepoStud = new TabelaPredmeti(2);
			
			JScrollPane scrPane = new JScrollPane(nepoStud);
			scrPane.setMaximumSize(new Dimension(350, 350));
			TabelaPredmeti.azurirajTabeluStudNepo(tBrIndexa.getText());
			
			JPanel centralni = new JPanel();
			centralni.setLayout(new BoxLayout(centralni, BoxLayout.X_AXIS));
			JPanel sepCen1 = new JPanel();
			sepCen1.setPreferredSize(new Dimension(15,0));
			
			JPanel sepCen2 = new JPanel();
			sepCen2.setPreferredSize(new Dimension(15,0));
			
			centralni.add(sepCen1);
			centralni.add(scrPane);
			centralni.add(sepCen2);
			
			nepolozeni.add(centralni);
			
			// Polozeni ispiti :
			polozeni.setLayout(new BoxLayout(polozeni, BoxLayout.Y_AXIS));
			JPanel btnPanel = new JPanel(new FlowLayout(10,15,10));
			JButton ponistiOcenu = new JButton(GlobalConstants.btnPonisti);
			ponistiOcenu.addActionListener(new MyPonistiOcenuListener());
			btnPanel.add(ponistiOcenu);
			polozeni.add(btnPanel);

			TabelaOcena ocene = new TabelaOcena(tBrIndexa.getText());
			JScrollPane scroll = new JScrollPane(ocene);
			scroll.setMaximumSize(new Dimension(350, 350));
			TabelaOcena.inst.updateTable(tBrIndexa.getText());
			JPanel tabelaPanel = new JPanel();
			tabelaPanel.setLayout(new BoxLayout(tabelaPanel, BoxLayout.X_AXIS));
			JPanel sep1 = new JPanel();
			sep1.setPreferredSize(new Dimension(15,0));

			JPanel sep2 = new JPanel();
			sep2.setPreferredSize(new Dimension(15,0));

			tabelaPanel.add(sep1);
			tabelaPanel.add(scroll);
			tabelaPanel.add(sep2);

			polozeni.add(tabelaPanel);

			JPanel labPanel = new JPanel();
			labPanel.setLayout(new BoxLayout(labPanel, BoxLayout.Y_AXIS));
			
			double zaokruzenProsek = Math.round(student.getProsecnaOcena() * 100.0) / 100.0;
			prosek = new JLabel(GlobalConstants.aOESProsOcLab + zaokruzenProsek);
			ukupnoEspb = new JLabel(GlobalConstants.aOESUkEspbLab + controller.izracunajUkupnoEspb(student.getBrIndexa()));
			labPanel.add(prosek);
			labPanel.add(ukupnoEspb);
			
			JPanel buttomPanel = new JPanel(new FlowLayout(10,260,10));
			buttomPanel.add(labPanel);
			polozeni.add(buttomPanel);

				
			JTabbedPane tabs = new JTabbedPane();
			tabs.addTab(GlobalConstants.aOESTabInfo, inf);
			tabs.addTab(GlobalConstants.aOESTabPolozeni, polozeni);
			tabs.addTab(GlobalConstants.aOESTabNepolozeni, nepolozeni);
			add(tabs);
			
		}
		
		StudentFocusListeners.enableOrDisableButton();
		
		odustani.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dialog.setVisible(false);
			}
		});
		
		potvrdi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String ime = tIme.getText().trim().substring(0,1).toUpperCase() + tIme.getText().trim().substring(1).toLowerCase();
				String prezime = tPrezime.getText().trim().substring(0,1).toUpperCase() + tPrezime.getText().trim().substring(1).toLowerCase();
				
				DateTimeFormatter dtf;
				LocalDate datRodj = null;
				boolean done = false;;
				
				for(int i = 0; i < GlobalConstants.regExDatePoss.length; i++) {
					try {
						dtf = DateTimeFormatter.ofPattern(GlobalConstants.regExDatePoss[i]);
						datRodj = LocalDate.parse(tDatRodj.getText().trim(), dtf);
						done = true;
						break;
					}catch(Exception ex) {
						done = false;
					}
					if(done)
						break;
				}
				
				String adresa = tAdrStan.getText().trim().substring(0,1).toUpperCase() + tAdrStan.getText().trim().substring(1);
				adresa = adresa.replaceAll(" +", " ");
				String konTel = tBrTel.getText().trim();
				String email = tEmail.getText().trim();
				
				String smer = tBrIndexa.getText().trim().split("-")[0];
				String broj = tBrIndexa.getText().trim().split("-")[1];
				String god = tBrIndexa.getText().trim().split("-")[2];
				if(!broj.equals("0")) {
					int brNula = 0;
					for(int i = 0; i < broj.length(); i++) {
						if(broj.charAt(i) == '0')
							brNula++;
						else
							break;
					}
					broj = broj.substring(brNula);
				}
				smer = smer.toLowerCase();
				String index = smer + "-" + broj + "-" + god;
				
				String godUpisa = tGodUpisa.getText().trim();
				int trenutnaGod;
				switch((String) tTrenutnaGod.getSelectedItem()) {
					case "I (prva)" : trenutnaGod = 1; break;
					case "II (druga)" : trenutnaGod = 2; break;
					case "III (treća)" : trenutnaGod = 3; break;
					default : trenutnaGod = 4;
				}
				
				String finans = (String)(tFinans.getSelectedItem());
				
				dialog.setVisible(false);
				
				if(mode == AddOrEditDialog.addMode) {
					double prosek = 0.0;
					student = new Student(prezime, ime, datRodj, adresa, konTel, email, index, godUpisa, trenutnaGod, finans, prosek);
					if(!controller.dodajStudenta(student))
						err = new ErrorDialog(GlobalConstants.errAddStud);
				}
				
				if(mode == AddOrEditDialog.editMode) {
					student.setIme(ime);
					student.setPrezime(prezime);
					student.setDatumRodj(datRodj);
					student.setAdresaStan(adresa);
					student.setKonTel(konTel);
					student.setEmail(email);
					student.setGodUpisa(godUpisa);
					student.setTrenutnaGodStud(trenutnaGod);
					student.setStatus(finans);
					
					if(!index.equals(student.getBrIndexa()))
						if(controller.nadjiStudenta(index) != null)
							err = new ErrorDialog(GlobalConstants.errAddStud);
						else
							student.setBrIndexa(index);
					else
						student.setBrIndexa(index);
					
				}
				
				TabelaStudenti.table.updateTable();
				
				GlavniProzor.serialize();
			}
		});
		
	}
	
	public JPanel createPanel(JLabel label, JTextField text) {
		JPanel panel = new JPanel();
		
		label.setPreferredSize(new Dimension(150, 25));
		panel.add(label);
		
		text.setPreferredSize(new Dimension(200, 25));
		panel.add(text);
		
		return panel;
	}
	
	public JPanel createListPanel(JLabel label, JComboBox<String> text) {
		JPanel panel = new JPanel();
		
		label.setPreferredSize(new Dimension(150, 25));
		panel.add(label);
		
		text.setPreferredSize(new Dimension(200, 25));
		panel.add(text);
		
		return panel;
	}
	
	public void updateEspbAndPros(Student student) {
		double zaokruzenProsek = Math.round(student.getProsecnaOcena() * 100.0) / 100.0;
		prosek.setText(GlobalConstants.aOESProsOcLab + zaokruzenProsek);
		ukupnoEspb.setText(GlobalConstants.aOESUkEspbLab + controller.izracunajUkupnoEspb(student.getBrIndexa()));
	}
	
	//Polaganje listener :
	class MyPolaganjeListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {

			int selectedIndex = TabelaPredmeti.instStudNepo.getSelectedRow();
			ErrorDialog err;

			if(selectedIndex == -1)
				err = new ErrorDialog(GlobalConstants.greskaPriIzboruPredmeta);
			else {
				String temp = (String) TabelaPredmeti.instStudNepo.getValueAt(selectedIndex, 0);
				Predmet p = GlavniProzor.getControllerPredmet().nadjiPredmet(temp);
				PolaganjeDijalog pd = new PolaganjeDijalog(p);
				pd.setVisible(true);

			}
		}
	}
	
	class MyDodajPredListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			DodajPredStudentu dps = new DodajPredStudentu(student); 
			dps.setVisible(true);
		}
	}
	
	class MyObrisiPredListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			int selectedIndex = TabelaPredmeti.instStudNepo.getSelectedRow();
			
			if(selectedIndex == -1) {
				err = new ErrorDialog(GlobalConstants.greskaPriIzboruPredmeta);
				return;
			}
			
			String temp = (String) TabelaPredmeti.instStudNepo.getValueAt(selectedIndex, 0);
			
			String [] options = {GlobalConstants.yesOpt,GlobalConstants.noOpt};
			int code = JOptionPane.showOptionDialog(AddOrEditStudent.inst, GlobalConstants.upitBrisanjePredKodStud, GlobalConstants.upitBrisanjePredTitle, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null);
			
			if(code == JOptionPane.YES_OPTION) {	
				GlavniProzor.getControllerStudent().obrisiPredmet(temp, student);
				TabelaPredmeti.azurirajTabeluStudNepo(student.getBrIndexa());
			}
		}
	}
	
	class MyPonistiOcenuListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			int selectedIndex = TabelaOcena.inst.getSelectedRow();
			
			if(selectedIndex == -1) {
				err = new ErrorDialog(GlobalConstants.greskaPriIzboruOcene);
				return;
			}
			
			String temp = (String) TabelaOcena.inst.getValueAt(selectedIndex, 0);
			
			String [] options = {GlobalConstants.yesOpt,GlobalConstants.noOpt};
			int code = JOptionPane.showOptionDialog(AddOrEditStudent.inst, GlobalConstants.upitPonistavenjeOcene, GlobalConstants.upitPonistavenjeOceneTitle, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null);
			
			if(code == JOptionPane.YES_OPTION) {
				GlavniProzor.getControllerStudent().obrisiOcenuIzListePolozenih(temp, student);
				TabelaOcena.inst.updateTable(student.getBrIndexa());
				GlavniProzor.getControllerStudent().dodajNepolozenPredmet(temp, student);
				TabelaPredmeti.azurirajTabeluStudNepo(student.getBrIndexa());
				GlavniProzor.getControllerStudent().sracunajProsecnuOcenu(student);
				updateEspbAndPros(student);
				TabelaStudenti.table.updateTable();
			}
		}
	}

	public Student getCurrStudent() {
		return student;
	}
	
}
