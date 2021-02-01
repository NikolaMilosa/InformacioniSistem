package model;

import java.io.Serializable;
import java.time.LocalDate;
//importi : 
import java.util.ArrayList;

public class Profesor implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String prezime;
	private String ime;
	private LocalDate drp;                    //datum rodjenja prof
	private String adrStan;               //adresa stanovanja
	private String konTel;                //kontakt telefon
	private String email;
	private String adrKanc;               //adresa kancelarije
	private String brLicKart;            //broj licne karte
	private String titula;
	private String zvanje;
	private ArrayList<Predmet> spisPred; //spisak predmeta
	
	//auto generisani geteri i seteri za polja :
	
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

	public LocalDate getDrp() {
		return drp;
	}

	public void setDrp(LocalDate drp) {
		this.drp = drp;
	}

	public String getAdrStan() {
		return adrStan;
	}

	public void setAdrStan(String adrStan) {
		this.adrStan = adrStan;
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

	public String getAdrKanc() {
		return adrKanc;
	}

	public void setAdrKanc(String adrKanc) {
		this.adrKanc = adrKanc;
	}

	public String getBrLicKart() {
		return brLicKart;
	}

	public void setBrLicKart(String brLicKart) {
		this.brLicKart = brLicKart;
	}

	public String getTitula() {
		return titula;
	}

	public void setTitula(String titula) {
		this.titula = titula;
	}

	public String getZvanje() {
		return zvanje;
	}

	public void setZvanje(String zvanje) {
		this.zvanje = zvanje;
	}

	public ArrayList<Predmet> getSpisPred() {
		return spisPred;
	}

	public void setSpisPred(ArrayList<Predmet> spisPred) {
		this.spisPred = spisPred;
	}
	
	//konstruktori :
	public Profesor() {
		this.prezime = "";
		this.ime = "";
		this.drp = LocalDate.MIN;
		this.adrStan = "";
		this.adrKanc = "";
		this.konTel = "";
		this.email = "";
		this.brLicKart = "";
		this.titula = "";
		this.zvanje = "";
		this.spisPred = new ArrayList<Predmet>();
	}
	
	public Profesor(String pr, String im, LocalDate dr, String ads,
			String adk, String kt, String em, String blk, String tit, String zv) {
		String temp;
		String[] parts;
		
		//Sredjivanje izgleda karaktera :
		pr = pr.toLowerCase();
		temp = pr.substring(0,1);
		temp = temp.toUpperCase();
		pr = temp + pr.substring(1);
		
		im = im.toLowerCase();
		temp = im.substring(0,1);
		temp = temp.toUpperCase();
		im = temp + im.substring(1);
		
		ads = ads.replaceAll(" +", " ");
		parts = ads.split(" ");
		ads = "";
		for(String s : parts) {
			s = s.toLowerCase();
			temp = s.substring(0,1);
			temp = temp.toUpperCase();
			s = temp + s.substring(1);
			if(parts.length != 1)
				ads += " " + s;
			else
				ads = s;
		}
		ads = ads.substring(1,ads.length());
		
		adk = adk.replaceAll(" +", " ");
		parts = adk.split(" ");
		adk = "";
		for(String s : parts) {
			s = s.toLowerCase();
			temp = s.substring(0,1);
			temp = temp.toUpperCase();
			s = temp + s.substring(1);
			if(parts.length != 1)
				adk += " " + s;
			else
				adk = s;
		}
		adk = adk.substring(1,adk.length());
		
		this.prezime = pr;
		this.ime = im;
		this.drp = dr;
		this.adrStan = ads;
		this.adrKanc = adk;
		this.konTel = kt;
		this.email = em;
		this.brLicKart = blk;
		this.titula = tit;
		this.zvanje = zv;
		this.spisPred = new ArrayList<Predmet>();
	}
	
	public String toString() {
		String out = "";
		//pretvaranje u osnovna polja
		out = this.getIme() + "|" + this.getPrezime() + "|" + this.getDrp().getDayOfMonth() + "." + this.getDrp().getMonthValue() + "." +this.getDrp().getYear() + "|" + this.getAdrStan() + "|" + this.getKonTel() + "|";
		out += this.getEmail() + "|" + this.getAdrKanc() + "|" + this.getBrLicKart() + "|" + this.getTitula() + "|" + this.getZvanje() + "|";
		for(Predmet p : this.getSpisPred())
			out += p.getSifPred() + ",";
		
		if(!this.getSpisPred().isEmpty())
			out = out.substring(0,out.length() - 1);
		
		out += ";";	
		return out;
	}	
}
