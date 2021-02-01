package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controller.AddPredToProfListener;
import controller.Checker;
import controller.ControllerProfesor;
import controller.ProfesorFocusListeners;
import model.GlobalConstants;
import model.Profesor;

public class AddOrEditProfesor extends JPanel{
	
	private JTextField txtPrezime,txtIme,txtDrp,txtAdrStan,txtKonTel,txtEmail,txtAdrKanc,txtBrLicKart;
	
	private JComboBox<String> zvCombo, titCombo;
	
	private JLabel labPrezime,labIme,labDrp,labAdrStan,labKonTel,labEmail,labAdrKanc,labBrLicKart,labTitula,labZvanje;
	private Profesor p;
	public static JButton ok,cancel;
	private ControllerProfesor cp;
	private String ime,prz,drp,adrStan,konTel,email,adrKanc,brLic,tit,zva;
	private ErrorDialog er;
	
	public static int brPraznihPolja;
	public static int rowNumEdited;
	public static int currMode;
	
	public static AddOrEditProfesor inst;
	
	public AddOrEditProfesor(int mode, AddOrEditDialog d) {
		
		brPraznihPolja = -8;
		
		inst = this;
		currMode = mode;
		cp = GlavniProzor.getControllerProfesor();
		setLayout(new BorderLayout());
		setSize(400,500);
		
		JPanel gornji = new JPanel();
		gornji.setLayout(new BoxLayout(gornji, BoxLayout.Y_AXIS));
		
		txtPrezime = new JTextField();
		txtPrezime.setName(GlobalConstants.przLab);
		txtPrezime.setToolTipText(GlobalConstants.przImeToolTip);
		
		txtIme = new JTextField();
		txtIme.setName(GlobalConstants.imeLab);
		txtIme.setToolTipText(GlobalConstants.przImeToolTip);
		
		txtDrp = new JTextField();
		txtDrp.setName(GlobalConstants.drLab);
		txtDrp.setToolTipText(GlobalConstants.drpToolTip);
		
		txtAdrKanc = new JTextField();
		txtAdrKanc.setName(GlobalConstants.adrKancLab);
		txtAdrKanc.setToolTipText(GlobalConstants.adrToolTip);
		
		txtKonTel = new JTextField();
		txtKonTel.setName(GlobalConstants.konTelLab);
		txtKonTel.setToolTipText(GlobalConstants.konTelToolTip);  
		
		txtEmail = new JTextField();
		txtEmail.setName(GlobalConstants.emailLab);
		txtEmail.setToolTipText(GlobalConstants.emailToolTip);
		
		txtAdrStan = new JTextField();
		txtAdrStan.setName(GlobalConstants.adrStanLab);
		txtAdrStan.setToolTipText(GlobalConstants.adrToolTip);
		
		txtBrLicKart = new JTextField();
		txtBrLicKart.setName(GlobalConstants.brLicKartLab);
		txtBrLicKart.setToolTipText(GlobalConstants.brLicKartToolTip);
		
		zvCombo = new JComboBox<String>(GlobalConstants.zvanja);
		titCombo = new JComboBox<String>(GlobalConstants.titule);
		
		gornji.add(cp.createPanel(labIme, this.txtIme, GlobalConstants.imeLab));
		gornji.add(cp.createPanel(labPrezime, this.txtPrezime, GlobalConstants.przLab));
		gornji.add(cp.createPanel(labDrp, this.txtDrp, GlobalConstants.drLab));
		gornji.add(cp.createPanel(labAdrStan, this.txtAdrStan, GlobalConstants.adrStanLab));
		gornji.add(cp.createPanel(labKonTel, this.txtKonTel, GlobalConstants.konTelLab));
		gornji.add(cp.createPanel(labEmail, this.txtEmail, GlobalConstants.emailLab));
		gornji.add(cp.createPanel(labAdrKanc, this.txtAdrKanc, GlobalConstants.adrKancLab));
		gornji.add(cp.createPanel(labBrLicKart, this.txtBrLicKart, GlobalConstants.brLicKartLab));
		gornji.add(cp.createComboBox(labTitula, this.titCombo, GlobalConstants.titulaLab));
		gornji.add(cp.createComboBox(labZvanje, this.zvCombo, GlobalConstants.zvanjeLab));
		
		JPanel donji = new JPanel();
		ok = new JButton(GlobalConstants.btnOkName);
		ok.setEnabled(false);
		donji.add(ok);
		cancel = new JButton(GlobalConstants.btnCncName);
		donji.add(cancel);	
		
		
		if(mode == AddOrEditDialog.addMode) {
			add(gornji,BorderLayout.NORTH);
			add(donji,BorderLayout.SOUTH);
		}
		
		
		//Textfield listeneri :
		txtPrezime.addFocusListener(new ProfesorFocusListeners());
		txtIme.addFocusListener(new ProfesorFocusListeners());
		txtDrp.addFocusListener(new ProfesorFocusListeners());
		txtAdrKanc.addFocusListener(new ProfesorFocusListeners());
		txtAdrStan.addFocusListener(new ProfesorFocusListeners());
		txtKonTel.addFocusListener(new ProfesorFocusListeners());
		txtEmail.addFocusListener(new ProfesorFocusListeners());
		txtBrLicKart.addFocusListener(new ProfesorFocusListeners());
		
		if(currMode == AddOrEditDialog.editMode) {
			
			rowNumEdited = TabelaProfesora.inst.getSelectedRow();
			String editProfBrLic = (String)TabelaProfesora.inst.getValueAt(rowNumEdited, 0); 
			
			Profesor p = cp.nadjiProfesora(editProfBrLic);
			//Setting the text to selected prof:
			txtPrezime.setText(p.getPrezime());
			txtIme.setText(p.getIme());
			int day = p.getDrp().getDayOfMonth();
			int month = p.getDrp().getMonthValue();
			int year = p.getDrp().getYear();
			txtDrp.setText(day + "." + month + "." + year + ".");
			txtAdrKanc.setText(p.getAdrKanc());
			txtAdrStan.setText(p.getAdrStan());
			txtKonTel.setText(p.getKonTel());
			txtEmail.setText(p.getEmail());
			txtBrLicKart.setText(p.getBrLicKart());
			titCombo.setSelectedItem(p.getTitula());
			zvCombo.setSelectedItem(p.getZvanje());
			
			//Provera tacnosti polja : 
			numCorrectFields();
			if(brPraznihPolja == 0)
				ok.setEnabled(true);
			
			JTabbedPane tabovi = new JTabbedPane();
			add(tabovi);
			
			JPanel tabOsn = new JPanel();
			tabOsn.setLayout(new BorderLayout());
			tabOsn.add(gornji,BorderLayout.NORTH);
			tabOsn.add(donji,BorderLayout.SOUTH);
			tabovi.addTab(GlobalConstants.profEditTabOsnInf, tabOsn);
			
			
			//Tab predmeti :
			JPanel tabPrd = new JPanel();
			
			JButton dodajPred = new JButton(GlobalConstants.btnDodPred);
			dodajPred.addActionListener(new AddPredToProfListener(p));
			JButton uklPred = new JButton(GlobalConstants.btnUklPred);
			uklPred.addActionListener(new MyUkloniPredmetListener());
			JPanel northPom = new JPanel();
			northPom.setLayout(new BoxLayout(northPom, BoxLayout.X_AXIS));
			northPom.add(dodajPred);
			JSeparator sep = new JSeparator(SwingConstants.VERTICAL);
			sep.setMaximumSize(new Dimension(5,0));
			northPom.add(sep);
			northPom.add(uklPred);
			
			JPanel pom = new JPanel();
			pom.setLayout(new BorderLayout());
			pom.add(northPom, BorderLayout.NORTH);
			
			TabelaPredmeti tabelaPredmetaProf = new TabelaPredmeti(1);
			TabelaPredmeti.azurirajTabeluProf(p);
			
			JScrollPane listPane = new JScrollPane(tabelaPredmetaProf);
			listPane.setPreferredSize(new Dimension(350,370));
			pom.add(listPane,BorderLayout.SOUTH);
			
			JPanel seph = new JPanel();
			seph.setMaximumSize(new Dimension(350,5));
			pom.add(seph,BorderLayout.CENTER);
			
			tabPrd.add(pom);
			tabovi.add(GlobalConstants.profEditTabPrd, tabPrd);
		}
		
		
		//Button listeneri : 
		cancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				d.setVisible(false);
				brPraznihPolja = -8;
			}
		});
		
		ok.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ime = txtIme.getText().trim();
				prz = txtPrezime.getText().trim();
				drp = txtDrp.getText().trim();
				adrStan = txtAdrStan.getText().replaceAll(" +", " ");
				adrStan = adrStan.trim();
				konTel = txtKonTel.getText().trim();
				email = txtEmail.getText().trim();
				adrKanc = txtAdrKanc.getText().replaceAll(" +", " ");
				adrKanc = adrKanc.trim();
				brLic = txtBrLicKart.getText().trim();
				tit = (String)titCombo.getSelectedItem();
				zva = (String)zvCombo.getSelectedItem();
				
				DateTimeFormatter dtf;
				LocalDate drpp = null;
				boolean done = false;;
				
				for(int i = 0; i < GlobalConstants.regExDatePoss.length; i++) {
					try {
						dtf = DateTimeFormatter.ofPattern(GlobalConstants.regExDatePoss[i]);
						drpp = LocalDate.parse(drp, dtf);
						done = true;
						break;
					}catch(Exception ex) {
						done = false;
					}
					if(done)
						break;
				}
				
				
				d.setVisible(false);
				
				if(AddOrEditDialog.addMode == currMode) {
					p = new Profesor(prz,ime,drpp,adrStan, adrKanc, konTel, email, brLic, tit, zva);
					if(!cp.dodajProfesora(p)) 
						er = new ErrorDialog(GlobalConstants.errAddProf);
				}else {
					String editProfBrLic = (String)TabelaProfesora.inst.getValueAt(rowNumEdited, 0);
					Profesor ptemp = cp.nadjiProfesora(editProfBrLic);
					boolean checker = true;
					
					for(Profesor proft : GlavniProzor.getControllerProfesor().getListaProfesora()) {
						if(!proft.equals(ptemp))
							if(proft.getEmail().equals(email) || proft.getBrLicKart().equals(brLic))
								checker = false;						
					}
					
					if(!checker)
						er = new ErrorDialog(GlobalConstants.errEditProf);
					else {
						ptemp.setIme(ime);
						ptemp.setPrezime(prz);
						ptemp.setDrp(drpp);
						ptemp.setAdrStan(adrStan);
						ptemp.setKonTel(konTel);
						ptemp.setEmail(email);
						ptemp.setAdrKanc(adrKanc);
						ptemp.setBrLicKart(brLic);
						ptemp.setTitula(tit);
						ptemp.setZvanje(zva);
					}
					
				}
				
				TabelaProfesora.azurirajTabelu();
				
				GlavniProzor.serialize();
				
				brPraznihPolja = -8;
			}
		});
	}
	
	public void numCorrectFields() {
		if(Checker.isNameOrSurename(txtIme.getText()))
			brPraznihPolja++;
		if(Checker.isNameOrSurename(txtPrezime.getText()))
			brPraznihPolja++;
		if(Checker.isValidDate(txtDrp.getText(),0))
			brPraznihPolja++;
		if(Checker.isValidAdrress(txtAdrStan.getText()))
			brPraznihPolja++;
		if(Checker.isValidAdrress(txtAdrKanc.getText()))
			brPraznihPolja++;
		if(Checker.isValidEmail(txtEmail.getText()))
			brPraznihPolja++;
		if(Checker.isValidNumber(txtBrLicKart.getText(), 1))
			brPraznihPolja++;
		if(Checker.isValidNumber(txtKonTel.getText(), 0))
			brPraznihPolja++;		
	}
	
	class MyUkloniPredmetListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			int[] selectedIndexes = TabelaPredmeti.instProf.getSelectedRows();
			
			ArrayList<String> selectedPreds = new ArrayList<String>();
			String temp;
			for(int i : selectedIndexes) {
				temp = (String) TabelaPredmeti.instProf.getValueAt(i, 0);
				selectedPreds.add(temp);
			}
			
			String [] options = {GlobalConstants.yesOpt,GlobalConstants.noOpt};
			int code = JOptionPane.showOptionDialog(AddOrEditProfesor.inst, GlobalConstants.upitBrisanjePredKodProf, GlobalConstants.upitBrisanjePredTitle, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null);
			
			if(code == JOptionPane.YES_OPTION) {	
				String editProfBrLic = (String)TabelaProfesora.inst.getValueAt(rowNumEdited, 0);
				Profesor ptemp = cp.nadjiProfesora(editProfBrLic);
				cp.obrisiPredmeteKodProf(ptemp, selectedPreds);
				TabelaPredmeti.azurirajTabeluProf(ptemp);
				GlavniProzor.getControllerPredmet().obrisiProfSaSvihPredmeta(ptemp.getBrLicKart());
			}
		}
		
	}
}
