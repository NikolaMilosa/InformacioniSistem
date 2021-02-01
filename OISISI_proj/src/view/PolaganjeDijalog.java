package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.Checker;
import model.GlobalConstants;
import model.Ocena;
import model.Predmet;
import model.Student;

public class PolaganjeDijalog extends JDialog{

	JLabel sif, naz, oc, dat;
	JTextField txtSif, txtNaz, txtDat;
	JComboBox comOc;

	JPanel north,east,west,south;

	JButton ok, cancel;

	Predmet pred;

	public PolaganjeDijalog(Predmet p) {
		pred = p;
		this.setTitle(GlobalConstants.unosOcene);
		this.setModal(true);
		this.setSize(new Dimension(300,400));
		this.setResizable(false);
		this.setLocationRelativeTo(AddOrEditStudent.inst);

		this.setLayout(new BorderLayout());

		north = new JPanel();
		north.setPreferredSize(new Dimension(2,20));

		this.add(north,BorderLayout.NORTH);

		west = new JPanel();
		west.setPreferredSize(new Dimension(10,5));

		this.add(west, BorderLayout.WEST);

		east = new JPanel();
		east.setPreferredSize(new Dimension(10,5));

		this.add(east, BorderLayout.EAST);

		ok = new JButton(GlobalConstants.btnOkName);
		ok.setEnabled(false);
		ok.addActionListener(new MyPotvrdiListener());
		cancel = new JButton(GlobalConstants.btnCncName);
		cancel.addActionListener(new MyOdustaniListener());

		south = new JPanel();
		south.add(ok);
		south.add(cancel);

		this.add(south,BorderLayout.SOUTH);

		JPanel center = new JPanel();
		center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));

		//Inicijalizacija : 
		sif = new JLabel();
		txtSif = new JTextField();
		naz = new JLabel();
		txtNaz = new JTextField();
		oc = new JLabel();
		comOc = new JComboBox<String>(GlobalConstants.ocene);
		dat = new JLabel();
		txtDat = new JTextField();
		txtDat.addFocusListener(new DateFocListener());

		JPanel helper1 = createPanel(sif, txtSif, GlobalConstants.sifraLab);
		txtSif.setEditable(false);
		txtSif.setText(p.getSifPred());

		center.add(helper1);

		JPanel helper2 = createPanel(naz, txtNaz, GlobalConstants.nazivLab);
		txtNaz.setEditable(false);
		txtNaz.setText(p.getNaziv());

		center.add(helper2);

		JPanel helper3 = createComboBox(oc, comOc, GlobalConstants.ocenaLab);

		center.add(helper3);

		JPanel helper4 = createPanel(dat, txtDat, GlobalConstants.datLab);

		center.add(helper4);

		this.add(center,BorderLayout.CENTER);

	}

	public JPanel createPanel(JLabel lab, JTextField txt, String ime) {
		JPanel ret = new JPanel();
		Dimension lblDims = new Dimension(50,25);
		lab = new JLabel(ime);
		lab.setPreferredSize(lblDims);
		ret.add(lab);

		Dimension txtDims = new Dimension(150,25);
		txt.setPreferredSize(txtDims);
		ret.add(txt);

		return ret;
	}

	public JPanel createComboBox(JLabel lab, JComboBox<String> txt, String ime) {
		JPanel ret = new JPanel();
		Dimension lblDims = new Dimension(50,25);
		lab = new JLabel(ime);
		lab.setPreferredSize(lblDims);
		ret.add(lab);

		Dimension txtDims = new Dimension(150,25);
		txt.setPreferredSize(txtDims);
		ret.add(txt);

		return ret;
	}

	public PolaganjeDijalog getter() {
		return this;
	}

	public int getSelectedOc() {
		return Integer.parseInt((String)comOc.getSelectedItem());
	}

	public String getTypedDate() {
		return txtDat.getText();
	}

	public void enableOk() {
		ok.setEnabled(true);
	}

	public void disableOk() {
		ok.setEnabled(false);
	}

	class MyOdustaniListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {

			getter().setVisible(false);

		}

	}

	class MyPotvrdiListener implements ActionListener{

		@SuppressWarnings("static-access")
		@Override
		public void actionPerformed(ActionEvent e) {

			Student currStudent = AddOrEditStudent.inst.getCurrStudent();
			String typedDate = getTypedDate();


			Ocena oc = new Ocena(currStudent, pred, getSelectedOc(), typedDate);
			GlavniProzor.getControllerStudent().upisiOcenuStudentu(currStudent, oc);

			TabelaPredmeti.instStudNepo.azurirajTabeluStudNepo(currStudent.getBrIndexa());
			TabelaStudenti.table.updateTable();
			TabelaOcena.inst.updateTable(currStudent.getBrIndexa());
			
			getter().setVisible(false);

			GlavniProzor.serialize();

			//VAZNO : postaviti update u panelu polozeni ispiti za prosek da ispisuje dobro
			AddOrEditStudent.inst.updateEspbAndPros(currStudent);

		}

	}

	class DateFocListener implements FocusListener{

		@Override
		public void focusGained(FocusEvent e) {
			JTextField f = (JTextField) e.getComponent();
			f.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		}

		@Override
		public void focusLost(FocusEvent e) {
			JTextField f = (JTextField) e.getComponent();

			String input = f.getText();

			if(!Checker.isValidDate(input,2)) {
				disableOk();
				f.setBorder(BorderFactory.createLineBorder(Color.RED));
			} else {
				enableOk();
				f.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			}
		}

	}
}