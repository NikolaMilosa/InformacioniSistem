package controller;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Ocena;
import model.Predmet;
import model.Student;
import view.GlavniProzor;
import view.TabelaStudenti;

public class ControllerStudent {
	
	//Sluzi za upravljanje listom studenata
	
	ArrayList<Student> listaStudenti;
		
	public ControllerStudent() {
		listaStudenti = new ArrayList<Student>();
	}
		
	public ArrayList<Student> getListaStudenata() {
		return this.listaStudenti;
	}
		
	public boolean dodajStudenta(Student s) {
		if(listaStudenti.isEmpty()) {
			listaStudenti.add(s);
			return true;
		}	
		for(Student student: listaStudenti) {
			if(student.getBrIndexa().equals(s.getBrIndexa())) {
				return false;
			}			
		}
		listaStudenti.add(s);
		return true;
	}
	
	public Student nadjiStudenta(String index) {
		Student ret = null;
		for(Student s : listaStudenti)
			if(s.getBrIndexa().equals(index))
				ret = s;
		return ret;
	}
	
	public void obrisiPredmetKodSvihStud(String spr) {
		for(Student s : listaStudenti) {
			for(Ocena o : s.getPolozeniIspiti()) 
				if(o.getPredmet().getSifPred().equals(spr)) {
					s.getPolozeniIspiti().remove(o);
					
					double d = 0;
					for(Ocena op : s.getPolozeniIspiti())
						d += op.getBrVrednost();
					
					if(s.getPolozeniIspiti().size() == 0)
						s.setProsecnaOcena(0.0);
					else
						s.setProsecnaOcena(d/s.getPolozeniIspiti().size());
					
					TabelaStudenti.table.updateTable();
					
					break;
				}
			for(Predmet p : s.getNepolozeniIspiti())
				if(p.getSifPred().equals(spr)) {
					s.getNepolozeniIspiti().remove(p);
					break;
				}
		}
	}
	
	public int izracunajUkupnoEspb(String index) {
		Student student = nadjiStudenta(index);
		int espb = 0;
		for(Ocena o : student.getPolozeniIspiti()) {
			espb += o.getPredmet().getEspbBod();
		}
		return espb;
	}
	
	public JPanel createListPanel(JLabel label, JComboBox<String> text) {
		JPanel panel = new JPanel();
		
		label.setPreferredSize(new Dimension(150, 25));
		panel.add(label);
		
		text.setPreferredSize(new Dimension(200, 25));
		panel.add(text);
		
		return panel;
	}
	
	public void obrisiStudenta(String index) {
		for(Student s : listaStudenti)
			if(s.getBrIndexa().equals(index)) {
				listaStudenti.remove(s);
				break;
			}
	}
	
	public boolean proveriPred(Student s, Predmet p) {
        for(Ocena o : s.getPolozeniIspiti())
            if(o.getPredmet().equals(p))
                return true;

        for(Predmet pred : s.getNepolozeniIspiti())
            if(pred.equals(p))
                return true;
        
        switch(p.getGodIzv()) {
        case PRVA: break;
        case DRUGA: if(s.getTrenutnaGodStud() < 2) 
        				return true; 
        			break;
        case TRECA: if(s.getTrenutnaGodStud() < 3) 
						return true; 
					break;
		default: if(s.getTrenutnaGodStud() < 4) 
						return true; 
					break;
        }
        
        return false;
    }
	
	public void dodajNepolozenePredmete(ArrayList<String> newPreds, Student stud) {
        for(String s : newPreds) {
        	Predmet p = GlavniProzor.getControllerPredmet().nadjiPredmet(s);
        	if(p != null) {
        		stud.getNepolozeniIspiti().add(p);
        		p.getListaNepolozenih().add(stud);
        	}
        }
    }
	
	public void dodajNepolozenPredmet(String sifra, Student stud) {
		Predmet pred = GlavniProzor.getControllerPredmet().nadjiPredmet(sifra);
		if(pred != null) {
			stud.getNepolozeniIspiti().add(pred);
        	pred.getListaNepolozenih().add(stud);
		}
    }
	
	public void obrisiPredmet(String predIds, Student s) {
		for(Predmet p : s.getNepolozeniIspiti()) {
			if(p.getSifPred().equals(predIds)) {
				s.getNepolozeniIspiti().remove(p);
                p.getListaNepolozenih().remove(s);
                break;
             }
        }
    }
	
	public void sracunajProsecnuOcenu(Student s) {
		double sum = 0;
		int counter = 0;
		for(Ocena o : s.getPolozeniIspiti()) {
			counter++;
			sum += o.getBrVrednost();
		}
		double d = 0;
		if(counter != 0)
			d = sum/counter;
		else
			d = 0;
		
		s.setProsecnaOcena(d);
	}

	public void upisiOcenuStudentu(Student s, Ocena o) {
		Predmet p = o.getPredmet();
		s.getNepolozeniIspiti().remove(p);
		o.getPredmet().getListaNepolozenih().remove(s);
		s.getPolozeniIspiti().add(o);
		o.getPredmet().getListaPolozenih().add(s);

		sracunajProsecnuOcenu(s);
	}
	
	@SuppressWarnings("unlikely-arg-type")
	public void obrisiOcenuIzListePolozenih(String sifra, Student s) {
		for(Ocena o : s.getPolozeniIspiti()) {
			if(o.getPredmet().getSifPred().equals(sifra)) {
				s.getPolozeniIspiti().remove(o);
				GlavniProzor.getControllerPredmet().nadjiPredmet(sifra).getListaPolozenih().remove(s.getBrIndexa());
				break;
			}
		}
	}

	public ArrayList<String> pretraziStud(String[] parts) {
		ArrayList<String> foundBrIndexa = new ArrayList<String>();
		if(parts.length == 1) {
			for(Student stud : listaStudenti) {
				if(stud.getPrezime().toLowerCase().indexOf(parts[0]) != -1)
					foundBrIndexa.add(stud.getBrIndexa());
			}
		}
		
		if(parts.length == 2) {
			for(Student stud : listaStudenti)
				if(stud.getPrezime().toLowerCase().indexOf(parts[0]) != -1)
					if(stud.getIme().toLowerCase().indexOf(parts[1]) != -1)
						foundBrIndexa.add(stud.getBrIndexa());
		}
		
		if(parts.length == 3) {
			for(Student stud : listaStudenti)
				if(stud.getPrezime().toLowerCase().indexOf(parts[0]) != -1)
					if(stud.getIme().toLowerCase().indexOf(parts[1]) != -1)
						if(stud.getBrIndexa().toLowerCase().indexOf(parts[2]) != -1)
							foundBrIndexa.add(stud.getBrIndexa());
		}
		
		return foundBrIndexa;
		
	}

	public void setL(ArrayList<Student> stud) {
		listaStudenti = stud;
		
	}
}
