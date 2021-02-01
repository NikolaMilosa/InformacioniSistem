package model;

import java.io.Serializable;
//importi :
import java.util.ArrayList;

//enum :

public class Predmet implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public enum Semestar {LETNJI,ZIMSKI};
	public enum GodIzv {PRVA,DRUGA,TRECA,CETVRTA};
	
	private String sifPred; //sifra predmeta
	private String naziv;    //naziv predmeta
	private Semestar semestar; 
	private GodIzv godIzv;   //godina izvodjenja
	private Profesor prof;   //predmetni profesor
	private int espbBod;    //espb bodovi
	
	private ArrayList<Student> listaPolozenih; //lista studenata koji su polozili
	private ArrayList<Student> listaNepolozenih; //lista studenata koji nisu polozili
	
	//auto generisani geteri i seteri za polja :
	
	public String getSifPred() {
		return sifPred;
	}
	public void setSifPred(String sif_pred) {
		this.sifPred = sif_pred;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public Semestar getSemestar() {
		return semestar;
	}
	public void setSemestar(Semestar semestar) {
		this.semestar = semestar;
	}
	public GodIzv getGodIzv() {
		return godIzv;
	}
	public void setGodIzv(GodIzv godIzv) {
		this.godIzv = godIzv;
	}
	public Profesor getProf() {
		return prof;
	}
	public void setProf(Profesor prof) {
		this.prof = prof;
	}
	public int getEspbBod() {
		return espbBod;
	}
	public void setEspbBod(int espbBod) {
		this.espbBod = espbBod;
	}
	
	public ArrayList<Student> getListaPolozenih() {
		return listaPolozenih;
	}
	public ArrayList<Student> getListaNepolozenih() {
		return listaNepolozenih;
	}
	
	//konstruktori :
	
	public Predmet() {
		this.sifPred = "";
		this.naziv = "";
		this.semestar = Semestar.LETNJI;
		this.godIzv = GodIzv.PRVA;
		this.prof = new Profesor();
		this.espbBod = 0;
		this.listaNepolozenih = new ArrayList<Student>();
		this.listaPolozenih = new ArrayList<Student>();
	}
	public Predmet(String s_p, String n, String sem,
				   int gi,Profesor p,int eb) {
		this.sifPred = s_p;
		this.naziv = n;
		if(sem.toLowerCase().equals(GlobalConstants.advSearchSemPos4))
			this.semestar = Semestar.LETNJI;
		else if(sem.toLowerCase().equals(GlobalConstants.advSearchSemPos3))
			this.semestar = Semestar.ZIMSKI;
		else
			System.out.println(GlobalConstants.predmetErrGod);
		gi -= 1;
		if(gi >= 0 && gi <= 3) {
			//kastovanje ako je u opsegu postojecih godina
			//GodIzv.values() vraca niz a [gi] je indeksiranje niza
			this.godIzv = GodIzv.values()[gi]; 
		}
		this.prof = p;
		this.espbBod = eb;
		this.listaNepolozenih = new ArrayList<Student>();
		this.listaPolozenih = new ArrayList<Student>();
	}

	
	public String outGodIzv(GodIzv g) {
		String out = "";
		
		switch(g) {
		case PRVA : out = GlobalConstants.predGodPrva; break;                                   
		case DRUGA : out = GlobalConstants.predGodDruga; break;                                  
		case TRECA : out = GlobalConstants.predGodTreca; break;                                  
		case CETVRTA : out = GlobalConstants.predGodCetvrta; break;                                
		}
		
		return out;
	}
	
	public String toString() {
		String out = "";
		
		out = this.getSifPred() + "|" + this.getNaziv() + "|";
		if(this.getSemestar() == Semestar.LETNJI)
			out += "LETNJI";
		else
			out += "ZIMSKI";
		
		out += "|";
		out += this.outGodIzv(this.getGodIzv()) + "|" + this.getProf().getBrLicKart() + "|" + this.getEspbBod() + "|";
		for(Student s : this.getListaPolozenih())
			out += s.getBrIndexa() + ",";
		
		if(!this.getListaPolozenih().isEmpty())
			out = out.substring(0,out.length() - 1);
		
		out += "|";
		
		for(Student s : this.getListaNepolozenih())
			out += s.getBrIndexa() + ",";
		
		if(!this.getListaNepolozenih().isEmpty())
			out = out.substring(0,out.length() - 1);
		
		out += ";";
		
		return out;
	}
	
	//Help metoda za godinu izvodjenja :
	public int getNumGodina() {
		switch(this.getGodIzv()) {
		case PRVA : return 1;
		case DRUGA : return 2;
		case TRECA : return 3;
		default : return 4;
		}
	}
	
	public String getNumGodinaEdit() {
		switch(this.getGodIzv()) {
		case PRVA : return "1";
		case DRUGA : return "2";
		case TRECA : return "3";
		default : return "4";
		}
	}
	
	public String getStrSem() {
		if(this.semestar == Semestar.LETNJI)
			return GlobalConstants.advSearchSemPos4;
		return GlobalConstants.advSearchSemPos3;
	}
}
