package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;



public class Student implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public enum StatusStudenta {B, S}
	
	private String prezime;
	private String ime;
	private LocalDate datumRodj;			// datum rodjenja
	private String adresaStan;			// adresa stanovanja
	private String konTel;				// kontakt telefon
	private String email;
	private String brIndexa;			// broj indeksa
	private String godUpisa;			// godina upisa
	private int trenutnaGodStud;		// trenutna godina studija
	private StatusStudenta status;
	private double prosecnaOcena;
	private ArrayList<Ocena> polozeniIspiti;		// spisak polozenih ispita (spisak ocena)
	private ArrayList<Predmet> nepolozeniIspiti;	// spisak nepolozenih ispita
	
	// get i set metode
	
	public String getPrezime() {
		return prezime;
	}
	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	public LocalDate getDatumRodj() {
		return datumRodj;
	}
	public void setDatumRodj(LocalDate datumRodj) {
		this.datumRodj = datumRodj;
	}
	public String getAdresaStan() {
		return adresaStan;
	}
	public void setAdresaStan(String adresaStan) {
		this.adresaStan = adresaStan;
	}
	public String getKonTel() {
		return konTel;
	}
	public void setKonTel(String konTel) {
		this.konTel = konTel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getBrIndexa() {
		return brIndexa;
	}
	public void setBrIndexa(String brIndexa) {
		this.brIndexa = brIndexa;
	}
	public String getGodUpisa() {
		return godUpisa;
	}
	public void setGodUpisa(String godUpisa) {
		this.godUpisa = godUpisa;
	}
	public int getTrenutnaGodStud() {
		return trenutnaGodStud;
	}
	public void setTrenutnaGodStud(int trenutnaGodStud) {
		this.trenutnaGodStud = trenutnaGodStud;
	}
	public String getStatus() {
		if(status.equals(StatusStudenta.B))
			return "B";
		return "S";
		
	}
	public void setStatus(String status) {
		if(status.toUpperCase().equals("B") || status.equals("Budžet"))
			this.status = StatusStudenta.B;
		else
			this.status = StatusStudenta.S;
	}
	public double getProsecnaOcena() {
		return prosecnaOcena;
	}
	public void setProsecnaOcena(double posecnaOcena) {
		this.prosecnaOcena = posecnaOcena;
	}
	public ArrayList<Ocena> getPolozeniIspiti() {
		return polozeniIspiti;
	}
	public ArrayList<Predmet> getNepolozeniIspiti() {
		return nepolozeniIspiti;
	}	
	public void setPolozeniIspiti(ArrayList<Ocena> polozeniIspiti) {
		this.polozeniIspiti = polozeniIspiti;
	}
	public void setNepolozeniIspiti(ArrayList<Predmet> nepolozeniIspiti) {
		this.nepolozeniIspiti = nepolozeniIspiti;
	}
	
	// konstruktori
	public Student() {
		super();
		this.prezime = "";
		this.ime = "";
		this.datumRodj = LocalDate.MIN;
		this.adresaStan = "";
		this.konTel = "";
		this.email = "";
		this.brIndexa = "";
		this.godUpisa = "";
		this.trenutnaGodStud = 1;
		this.status = StatusStudenta.B;
		this.prosecnaOcena = 0.0;
		this.polozeniIspiti = new ArrayList<Ocena>();
		this.nepolozeniIspiti = new ArrayList<Predmet>();
	}
	public Student(String prezime, String ime, LocalDate datumRodj, String adresaStan, String konTel, String email,
			String brIndexa, String godUpisa, int trenutnaGodStud, String status, double posecnaOcena) {
		super();
		this.prezime = prezime;
		this.ime = ime;
		this.datumRodj = datumRodj;
		this.adresaStan = adresaStan;
		this.konTel = konTel;
		this.email = email;
		this.brIndexa = brIndexa;
		this.godUpisa = godUpisa;
		this.trenutnaGodStud = trenutnaGodStud;
		if(status.toUpperCase().equals("B") || status.equals("Budžet"))
			this.status = StatusStudenta.B;
		else
			this.status = StatusStudenta.S;
		this.prosecnaOcena = posecnaOcena;
		this.polozeniIspiti = new ArrayList<Ocena>();
		this.nepolozeniIspiti = new ArrayList<Predmet>();
	}
	
	@Override
	public String toString() {
		String out = "";
		
		out = this.getIme() + "|" + this.getPrezime() + "|" + this.getDatumRodj() + "|" + this.getAdresaStan() + "|" + this.getKonTel() + "|";
		out += this.getEmail() + "|" + this.getBrIndexa() + "|" + this.getGodUpisa() + "|" + this.getTrenutnaGodStud() + "|";
		out += this.getStatus() + "|" + this.getProsecnaOcena() + "|";
		for(Ocena o : this.getPolozeniIspiti())
			out += o.getPredmet().getSifPred() + "-" + o.getBrVrednost() + "-" + o.getDatumPolaganja() + ",";
		
		if(!this.getPolozeniIspiti().isEmpty())
			out = out.substring(0,out.length() - 1);
		
		out += "|";
		
		for(Predmet p : this.getNepolozeniIspiti())
			out += p.getSifPred() + ",";
		
		if(!this.getNepolozeniIspiti().isEmpty())
			out = out.substring(0,out.length() - 1);
		
		out += ";";
		return out;
	}
}
