package controller;

import java.util.ArrayList;
import java.util.*;
import java.util.regex.Pattern;
import model.GlobalConstants;
import model.Predmet;
import model.Student;
import view.GlavniProzor;

public class ControllerPredmet {
	
	private ArrayList<Predmet> listaPredmeta;
	
	public ControllerPredmet() {
		listaPredmeta = new ArrayList<Predmet>();	
	}
	
	public ArrayList<Predmet> getListaPredmeta(){
		return this.listaPredmeta;
	}
	
	public boolean dodajPredmet(Predmet p) {
		if(!listaPredmeta.contains(p))
			for(Predmet pr : listaPredmeta)
				if(pr.getSifPred().equals(p.getSifPred()))
					return false;
		listaPredmeta.add(p);
		return true;
	}
	
	public Predmet nadjiPredmet(String sp) {
		Predmet ret = null;
		for(Predmet p : listaPredmeta)
			if(p.getSifPred().equals(sp))
				ret = p;
		return ret;
	}
	
	public void obrisiPredmet(String sp) {
		//Obrise se kod svih profesora prvo:
		GlavniProzor.getControllerProfesor().obrisiPredmetKodSvihProf(sp);		
		
		//Potom kod svih studenata :
		GlavniProzor.getControllerStudent().obrisiPredmetKodSvihStud(sp);
		
		//Potom iz konacne liste : 
		for(Predmet p : listaPredmeta) {
			if(p.getSifPred().equals(sp)) {
				listaPredmeta.remove(p);
				break;
			}
		}
	}

	public ArrayList<String> pretraziPred(String text) {
		ArrayList<String> foundSifPred = new ArrayList<String>();
		for(Predmet p : listaPredmeta)
			if(p.getNaziv().toLowerCase().indexOf(text) != -1)
				foundSifPred.add(p.getSifPred());
		return foundSifPred;
	}
	
	
	public void obrisiStudentaIzSvihListaPolozenih(String index) {
		for(Predmet p: listaPredmeta) 
			for(Student s: p.getListaPolozenih())
				if(s.getBrIndexa().equals(index)) { 
					p.getListaPolozenih().remove(s);
					break;
				}
	}
	
	public void obrisiStudentaIzSvihListaNepolozenih(String index) {
		for(Predmet p: listaPredmeta) 
			for(Student s: p.getListaNepolozenih())
				if(s.getBrIndexa().equals(index)) {
					p.getListaNepolozenih().remove(s);
					break;
				}
	}
	
	public void obrisiProfSaSvihPredmeta(String brLicKart) {
		for(Predmet p : listaPredmeta)
			if(p.getProf().getBrLicKart().equals(brLicKart))
				p.setProf(GlobalConstants.dummy);
	}
	
	//Napredna pretraga :
	public boolean advSrcTxt(String crit, ArrayList<Predmet> ret){
		int critType;
		String[] parts = crit.split(" ");
		if(parts[2].startsWith("/")) {
			if(!parts[2].endsWith("/"))
				return false;
			critType = 1;
			parts[2] = parts[2].substring(1,parts[2].length()-1);
			try {
				Pattern.compile(parts[2]);
			} catch (Exception e) {
				return false;
			}
		}
		else if(parts[2].startsWith("\"")) {
			if(!parts[2].endsWith("\""))
				return false;
			critType = 2;
			parts[2] = parts[2].substring(1,parts[2].length()-1);
		}
		else {
			return false;
		}
		
		if(parts[0].equalsIgnoreCase(GlobalConstants.advSearchSifTok1) || parts[0].equalsIgnoreCase(GlobalConstants.advSearchSifTok2)){
			if(critType == 1) {
				//sifra po regexu
				
				if(parts[1].equals("==")) {
					for(Predmet p : listaPredmeta)
						if(p.getSifPred().toLowerCase().matches(parts[2]))
							ret.add(p);
				}
				else if(parts[1].equals("!=")) {
					for(Predmet p : listaPredmeta)
						if(!p.getSifPred().toLowerCase().matches(parts[2]))
							ret.add(p);
				}
			}else {
				//sifra po imenu
				
				if(parts[1].equals("==")) {
					for(Predmet p : listaPredmeta)
						if(p.getSifPred().equalsIgnoreCase(parts[2]))
							ret.add(p);
				} 
				else if(parts[1].equals("!=")) {
					for(Predmet p : listaPredmeta)
						if(!p.getSifPred().equalsIgnoreCase(parts[2]))
							ret.add(p);
				}
			}
		}
		else if(parts[0].equalsIgnoreCase(GlobalConstants.advSearchNazTok)) {
			if(critType == 1) {
				//Naziv po regexu
				
				if(parts[1].equals("==")) {
					for(Predmet p : listaPredmeta)
						if(p.getNaziv().toLowerCase().matches(parts[2]))
							ret.add(p);
				}
				else if(parts[1].equals("!=")) {
					for(Predmet p : listaPredmeta)
						if(!p.getNaziv().toLowerCase().matches(parts[2]))
							ret.add(p);
				}
			}
			else {
				//Naziv po jednakosti
				
				if(parts[1].equals("==")) {
					for(Predmet p : listaPredmeta)
						if(p.getNaziv().equalsIgnoreCase(parts[2]))
							ret.add(p);
				}
				else if(parts[1].equals("!=")) {
					for(Predmet p : listaPredmeta)
						if(!p.getNaziv().equalsIgnoreCase(parts[2]))
							ret.add(p);
				}
			}
		}
		
		return true;
	}
	
	public boolean advSrcNum(String crit, ArrayList<Predmet> ret) {
		int num;
		
		String[] parts = crit.split(" ");
		
		try {
			num = Integer.parseInt(parts[2]);
		} catch(Exception e) {
			return false;
		}
		
		//Popunjavanje liste
		if(parts[0].equalsIgnoreCase(GlobalConstants.advSearchGodTok)) {
			if(parts[1].equals("==")) {
				for(Predmet p : listaPredmeta)
					if(p.getNumGodina() == num)
						ret.add(p);
			}
			else if(parts[1].equals("!=")) {
				for(Predmet p : listaPredmeta)
					if(p.getNumGodina() != num)
						ret.add(p);
			}
			else if(parts[1].equals(">")) {
				for(Predmet p : listaPredmeta)
					if(p.getNumGodina() > num)
						ret.add(p);
			}
			else if(parts[1].equals(">=")) {
				for(Predmet p : listaPredmeta)
					if(p.getNumGodina() >= num)
						ret.add(p);
			}
			else if(parts[1].equals("<")) {
				for(Predmet p : listaPredmeta)
					if(p.getNumGodina() < num)
						ret.add(p);
			}
			else if(parts[1].equals("<=")) {
				for(Predmet p : listaPredmeta)
					if(p.getNumGodina() <= num)
						ret.add(p);
			}
		}
		else if(parts[0].equalsIgnoreCase(GlobalConstants.advSearchESPBTok)) {
			if(parts[1].equals("==")) {
				for(Predmet p : listaPredmeta)
					if(p.getNumGodina() == num)
						ret.add(p);
			}
			else if(parts[1].equals("!=")) {
				for(Predmet p : listaPredmeta)
					if(p.getEspbBod() != num)
						ret.add(p);
			}
			else if(parts[1].equals(">")) {
				for(Predmet p : listaPredmeta)
					if(p.getEspbBod() > num)
						ret.add(p);
			}
			else if(parts[1].equals(">=")) {
				for(Predmet p : listaPredmeta)
					if(p.getEspbBod() >= num)
						ret.add(p);
			}
			else if(parts[1].equals("<")) {
				for(Predmet p : listaPredmeta)
					if(p.getEspbBod() < num)
						ret.add(p);
			}
			else if(parts[1].equals("<=")) {
				for(Predmet p : listaPredmeta)
					if(p.getEspbBod() <= num)
						ret.add(p);
			}
		}		
		return true;
	}
	
	public boolean advSrcSem(String crit, ArrayList<Predmet> ret) {
		String[] parts = crit.split(" ");
		
		if(parts[2].equalsIgnoreCase(GlobalConstants.advSearchSemPos2) || parts[2].equalsIgnoreCase(GlobalConstants.advSearchSemPos4)) {
			if(parts[1].equals("==")) {
				for(Predmet p : listaPredmeta)
					if(p.getSemestar() == Predmet.Semestar.LETNJI)
						ret.add(p);
			}
			else if(parts[1].equals("!=")) {
				for(Predmet p : listaPredmeta)
					if(p.getSemestar() != Predmet.Semestar.LETNJI)
						ret.add(p);
			}
		}
		else if(parts[2].equalsIgnoreCase(GlobalConstants.advSearchSemPos1) || parts[2].equalsIgnoreCase(GlobalConstants.advSearchSemPos3)) {
			if(parts[1].equals("==")) {
				for(Predmet p : listaPredmeta)
					if(p.getSemestar() == Predmet.Semestar.ZIMSKI)
						ret.add(p);
			}
			else if(parts[1].equals("!=")) {
				for(Predmet p : listaPredmeta)
					if(p.getSemestar() != Predmet.Semestar.ZIMSKI)
						ret.add(p);
			}
		}
		
		return true;
	}
	//Unija i presek kriterijuma :
	public ArrayList<Predmet> union(ArrayList<ArrayList<Predmet>> listU){
		Set<Predmet> set = new HashSet<Predmet>();
		
		for(ArrayList<Predmet> p : listU)
			set.addAll(p);
		
		return new ArrayList<Predmet>(set);
	}
	
	public ArrayList<Predmet> intersect(ArrayList<ArrayList<Predmet>> listU){
		Set<Predmet> set = new HashSet<Predmet>();
		set.addAll(listU.get(0));
		
		for(int i = 1; i < listU.size(); i++) {
			Set<Predmet> temp = new HashSet<Predmet>();
			temp.addAll(listU.get(i));
			
			set.retainAll(temp);
		}
		
		return new ArrayList<Predmet>(set);
	}

	public void setL(ArrayList<Predmet> pred) {
		listaPredmeta = pred;
		
	}
}
