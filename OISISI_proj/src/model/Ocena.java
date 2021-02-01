package model;

import java.io.Serializable;

public class Ocena implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Student polozioIspit;
	private Predmet predmet;
	private int brVrednost;
	private String datumPolaganja;
	
	// get i set metode 
	
	public Student getPolozioIspit() {
		return polozioIspit;
	}
	public void setPolozioIspit(Student polozioIspit) {
		this.polozioIspit = polozioIspit;
	}
	public Predmet getPredmet() {
		return predmet;
	}
	public void setPredmet(Predmet predmet) {
		this.predmet = predmet;
	}
	public int getBrVrednost() {
		return brVrednost;
	}
	public void setBrVrednost(int brVrednost) {
		if(brVrednost >= 6 && brVrednost <= 10)
			this.brVrednost = brVrednost;
		else 
			this.brVrednost = 6;
	}
	public String getDatumPolaganja() {
		return datumPolaganja;
	}
	public void setDatumPolaganja(String datumPolaganja) {
		this.datumPolaganja = datumPolaganja;
	}
	
	// konstruktori 
	
	public Ocena() {
		super();
		this.polozioIspit = new Student();
		this.predmet = new Predmet();
		this.brVrednost = 6;
		this.datumPolaganja = "";
	}
	
	public Ocena(Student polozioIspit, Predmet predmet, int brVrednost, String datumPolaganja) {
		super();
		this.polozioIspit = polozioIspit;
		this.predmet = predmet;
		if(brVrednost >= 6 && brVrednost <= 10)
			this.brVrednost = brVrednost;
		else
			this.brVrednost = 6;
		this.datumPolaganja = datumPolaganja;
	}
	@Override
	public String toString() {
		String out = "Polozio Ispit: " + polozioIspit.getBrIndexa() + "\n";
		out += "Predmet: " + predmet.getNaziv() + "\n";
		out += "Brojna vrednost: " + brVrednost + "\n";
		out	+= "Datum polaganja: " + datumPolaganja + "\n";
		return out;
	}
	
	
	
}
