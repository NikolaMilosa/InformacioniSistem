package controller;

import java.util.ArrayList;

import com.bpodgursky.jbool_expressions.Expression;
import com.bpodgursky.jbool_expressions.parsers.ExprParser;
import com.bpodgursky.jbool_expressions.rules.RuleSet;

import model.GlobalConstants;
import model.Predmet;
import model.Profesor;
import view.AdvSearDialog;
import view.ErrorDialog;
import view.GlavniProzor;
import view.TabelaPredmeti;

//predmeti = (sifra == '0' and naziv == '0')
//predmeti = (sifra == a or godina == 2 and (sifra == 3 and semestar == letnji))
//A | B & C & D

public class AdvSearchWorkPred {	
	
	ErrorDialog err;
	boolean hadError;
	Expression<String> exp;
	String[] collection;
	char i;
	
	ArrayList<Var> vars;
	String myExp;
	ArrayList<Predmet> solution;

	public AdvSearchWorkPred(String s) {
		hadError = false;
		i = 'A';
		myExp = "";
		vars = new ArrayList<Var>();
		
		
		s = s.trim();
		
		s = s.replaceAll("\\Q(\\E", " ( ");
		s = s.replaceAll("\\Q)\\E", " ) ");
		s = s.replaceAll("\\Q&&\\E", "and");
		s = s.replaceAll("\\Q||\\E", "or");
		s = s.replaceAll(" +", " ");
		
		collection = s.split(" ");
		
		//predmeti = (( sifra == /.*it.*/ or sifra == /.*p2.*/ ) and profesori == {ime == /.*Bran.*/ and zvanje == "Docent"})
		makeVars();
		
		if(!hadError) {
			try {
			exp = RuleSet.simplify(ExprParser.parse(myExp));	
			exp = RuleSet.toDNF(exp);
			myExp = exp.toString();
			} catch(Exception e) {
				hadError = true;
				err = new ErrorDialog(GlobalConstants.advSearchErrParse);
			}
		}
		//predmeti = (sifra == /.* ( p|P ) .*/  and godina >= 3 or espb > 12 and espb < 16)
		//Sredjivanje u pogodan oblik forme :
		
		if(!hadError) {
			myExp = myExp.replaceAll("\\Q(\\E", "");
			myExp = myExp.replaceAll("\\Q)\\E", "");
			myExp = myExp.replaceAll("\\Q & \\E", "");
			myExp = myExp.replaceAll("\\Q | \\E", "+");
			//System.out.println(myExp);
		}
		//Za svaku nadjenu promenljivu izvrsi njen upit :
		if(!hadError)
			executeVarQuerries();
			
		//Izvrsi ceo izraz :
		if(!hadError)
			executeExpression();
		
		if(!hadError) {
			ArrayList<String> foundSifs = new ArrayList<String>();
			for(Predmet p : solution)
				foundSifs.add(p.getSifPred());
			
			TabelaPredmeti.izlistajPredmete(foundSifs);
			AdvSearDialog.inst.setVisible(false);
		}
		
	}
	//Klasa promenljivih za predmete:
	class Var{
		char name;
		String value;
		ArrayList<Predmet> sol;
		
		public Var(char n, String v) {
			name = n;
			value = v;
			sol = new ArrayList<Predmet>();
		}
		
		public char getN() { return name; }
		public String getV() { return value; }
		public ArrayList<Predmet> getSol() {return sol;}
	}
	
	public void makeVars() {
		
		if(!collection[0].equalsIgnoreCase(GlobalConstants.advSearchPredTok) || !collection[1].equals("=")) {
			err = new ErrorDialog(GlobalConstants.advSearchErrBegin);
			hadError = true;
			return;
		}
		else {
			int k = 2;
			do {
				if(collection[k].equals("(")) {
					myExp += " " + collection[k] + " ";
					k++;
				}
				else if(collection[k].equals(")")) {
					myExp += " " + collection[k] + " ";
					k++;
				}
				else if(collection[k].equalsIgnoreCase(GlobalConstants.advSearchProfTok)) { 
					int helper = k;
					myExpProf = "";
					do {
						myExpProf += collection[helper] + " ";
						if(collection[helper].endsWith("}"))
							break;
						helper++;
					}while(helper < collection.length);
					//Dodaj da se izvrsi kod i da se napravi var ovako :
					
					Var temp = new Var(i,myExpProf);
					
					resolveProfesor();
					if(hadError)
						break;
					
					if(collection[k+1].equals("==")) {
						for(Predmet p : GlavniProzor.getControllerPredmet().getListaPredmeta())
							for(Profesor pr : psolution)
								if(p.getProf().equals(pr))
									temp.getSol().add(p);
					} else if(collection[k+1].equals("!=")) {
						if(psolution.size() != 0) {
							for(Predmet p : GlavniProzor.getControllerPredmet().getListaPredmeta())
								for(Profesor pr : psolution)
									if(!p.getProf().equals(pr))
										temp.getSol().add(p);
						} else {
							for(Predmet p : GlavniProzor.getControllerPredmet().getListaPredmeta())
								temp.getSol().add(p);
						}
					}
					
					//predmeti = (profesori == {ime = "0"})
					vars.add(temp);
					
					myExp += " " + i++ + " ";
					
					k = helper + 1;
					
				}
				else if(collection[k].equalsIgnoreCase(GlobalConstants.advSearchSifTok1) || collection[k].equalsIgnoreCase(GlobalConstants.advSearchSifTok2)) {
					hadError = !createVar(collection[k].toLowerCase(), collection[k+1], collection[k+2]);
					k+=3;
				}
				else if(collection[k].equalsIgnoreCase(GlobalConstants.advSearchNazTok)) {
					hadError = !createVar(collection[k].toLowerCase(), collection[k+1], collection[k+2]);
					k+=3;
				}
				else if(collection[k].equalsIgnoreCase(GlobalConstants.advSearchESPBTok)) {
					hadError = !createVar(collection[k].toLowerCase(), collection[k+1], collection[k+2]);
					k+=3;
				}
				else if(collection[k].equalsIgnoreCase(GlobalConstants.advSearchGodTok)) {
					hadError = !createVar(collection[k].toLowerCase(), collection[k+1], collection[k+2]);
					k+=3;
				}
				else if(collection[k].equalsIgnoreCase(GlobalConstants.advSearchSemTok)) {
					hadError = !createVar(collection[k].toLowerCase(), collection[k+1], collection[k+2]);
					k+=3;
				} 
				else if(collection[k].equalsIgnoreCase("and")) {
					myExp += " & ";
					k++;
				}
				else if(collection[k].equalsIgnoreCase("or")) {
					myExp += " | ";
					k++;
				}
				else {
					err = new ErrorDialog(GlobalConstants.advSearchErrTokNotFound);
					hadError = true;
					break;
				}
				if(hadError) 
					break;
				
			}while(k < collection.length);
		}
	}
	
	public boolean createVar(String col, String exp, String val) { 
		
		Var temp;
		String s = col + " " +  exp + " " + val;
		if(col.equalsIgnoreCase(GlobalConstants.advSearchSifTok1) || col.equalsIgnoreCase(GlobalConstants.advSearchSifTok2) || col.equalsIgnoreCase(GlobalConstants.advSearchNazTok)) {
			if(!exp.equals("==") && !exp.equals("!=")) {
				err = new ErrorDialog(GlobalConstants.advSearchErrRelOpsNS);
				return false;
			}
			temp = new Var(i,s);
			myExp += " " + i++ + " ";
			vars.add(temp);
		}
		else if(col.equalsIgnoreCase(GlobalConstants.advSearchESPBTok) || col.equalsIgnoreCase(GlobalConstants.advSearchGodTok)) {
			if(!checkExp(exp)) {
				err = new ErrorDialog(GlobalConstants.advSearchErrGodEspb);
				return false;
			}
			temp = new Var(i,s);
			myExp += " " + i++ + " ";
			vars.add(temp);
		}
		else if(col.equalsIgnoreCase(GlobalConstants.advSearchSemTok)) {
			if(!exp.equals("==") && !exp.equals("!=")) {
				err = new ErrorDialog(GlobalConstants.advSearchErrSemOps);
				return false;
			}
			if(!(val.equalsIgnoreCase(GlobalConstants.advSearchSemPos1) || val.equalsIgnoreCase(GlobalConstants.advSearchSemPos2) || val.equalsIgnoreCase(GlobalConstants.advSearchSemPos3) || val.equalsIgnoreCase(GlobalConstants.advSearchSemPos4))) {
				err = new ErrorDialog(GlobalConstants.advSearchErrSemVals);
				return false;
			}
			temp = new Var(i,s);
			myExp += " " + i++ + " ";
			vars.add(temp);
		}
		else {
			return false;
		}
		
		return true;
	}
	
	public boolean checkExp(String exp) {
		switch(exp) {
		case "==":
		case "!=":
		case "<":
		case ">":
		case "<=":
		case ">=":
			return true;
		default : return false;
		}	
	}
	
	public void executeVarQuerries() {
		boolean noErrors = true;
		for(Var v : vars) {
			if(v.getV().toLowerCase().startsWith(GlobalConstants.advSearchSifTok1) || v.getV().toLowerCase().startsWith(GlobalConstants.advSearchSifTok2) || v.getV().toLowerCase().startsWith(GlobalConstants.advSearchNazTok))
				noErrors &= GlavniProzor.getControllerPredmet().advSrcTxt(v.getV(), v.getSol());
			else if(v.getV().toLowerCase().startsWith(GlobalConstants.advSearchESPBTok) || v.getV().toLowerCase().startsWith(GlobalConstants.advSearchGodTok))
				noErrors &= GlavniProzor.getControllerPredmet().advSrcNum(v.getV(), v.getSol());
			else if(v.getV().toLowerCase().startsWith(GlobalConstants.advSearchSemTok))
				noErrors &= GlavniProzor.getControllerPredmet().advSrcSem(v.getV(), v.getSol());
			else if(v.getV().toLowerCase().startsWith(GlobalConstants.advSearchProfTok))
				noErrors &= true;
			else
				noErrors &= false;
		}
		if(!noErrors) {
			err = new ErrorDialog(GlobalConstants.advSearchErrSingleExps);
			hadError = true;
		}
	}
	
	//Nalazi promenljive po njihovom imenu tj slovu :
	public Var getVbyName(char a) {
		for(Var v : vars)
			if(v.getN() == a)
				return v;
		return null;
	}
	
	public void executeExpression() {
		String[] toInter = myExp.split("\\+");
		ArrayList<ArrayList<Predmet>> toDo = new ArrayList<ArrayList<Predmet>>();
		ArrayList<ArrayList<Predmet>> toOr = new ArrayList<ArrayList<Predmet>>();
		for(String s : toInter) {
			for(char c : s.toCharArray()) 
					toDo.add(getVbyName(c).getSol());
			toOr.add(GlavniProzor.getControllerPredmet().intersect(toDo));
			toDo = new ArrayList<ArrayList<Predmet>>();
		}
		
		solution = GlavniProzor.getControllerPredmet().union(toOr);
	}
	
	//Nadalje sredjivanje ako postoji profesor deo:
	String myExpProf;
	String[] profCollection;
	char ip;
	boolean hadErrorProf;
	
	Expression<String> expProf;
	
	ArrayList<PVar> pvars;
	
	ArrayList<Profesor> psolution;
	
	public void resolveProfesor() {
		hadErrorProf = false;
		ip = 'A';
		pvars = new ArrayList<PVar>();
		
		myExpProf = myExpProf.replaceAll("\\Q(\\E", " ( ");
		myExpProf = myExpProf.replaceAll("\\Q)\\E", " ) ");
		myExpProf = myExpProf.replaceAll("\\Q{\\E", " ( ");
		myExpProf = myExpProf.replaceAll("\\Q}\\E", " ) ");
		myExpProf = myExpProf.replaceAll("\\Q&&\\E", "and");
		myExpProf = myExpProf.replaceAll("\\Q||\\E", "or");
		myExpProf = myExpProf.replaceAll(" +", " ");
		
		
		profCollection = myExpProf.split(" ");
		
		//predmeti = (profesori == {ime == "0"})
		
		
		makePVars();
		
		if(!hadErrorProf) {
			try {
			expProf = RuleSet.simplify(ExprParser.parse(myExpProf));	
			expProf = RuleSet.toDNF(expProf);
			myExpProf = expProf.toString();
			} catch(Exception e) {
				hadErrorProf = true;
				err = new ErrorDialog(GlobalConstants.advSearchErrParseProf);
			}
		}
		
		
		//Sredjivanje u pogodan oblik forme :
		
		if(!hadErrorProf) {
			myExpProf = myExpProf.replaceAll("\\Q(\\E", "");
			myExpProf = myExpProf.replaceAll("\\Q)\\E", "");
			myExpProf = myExpProf.replaceAll("\\Q & \\E", "");
			myExpProf = myExpProf.replaceAll("\\Q | \\E", "+");
			//System.out.println(myExpProf);
		}
		
		//Za svaku nadjenu promenljivu izvrsi njen upit :
		if(!hadErrorProf)
			executePVarQuerries();
		
		//Izvrsi ceo izraz :
		if(!hadErrorProf)
			executePExpression();
		
		hadError = hadErrorProf;
	}
	
	//Klasa promenljivih za profesore :
	class PVar{
		char name;
		String value;
		ArrayList<Profesor> sol;
		
		public PVar(char n, String v) {
			name = n;
			value = v;
			sol = new ArrayList<Profesor>();
		}
		
		public char getN() { return name; }
		public String getV() { return value; }
		public ArrayList<Profesor> getSol() {return sol;}
	}
	
	public void makePVars() {
		myExpProf = "";
		if(!profCollection[0].toLowerCase().equals(GlobalConstants.advSearchProfTok) || !(profCollection[1].equals("==") || profCollection[1].equals("!="))) {
			err = new ErrorDialog(GlobalConstants.advSearchErrProfBegin);
			hadErrorProf = true;
			return;
		}
		else {
			int k = 2;
			do {
				if(profCollection[k].equals("(")) {
					myExpProf += " " + profCollection[k] + " ";
					k++;
				}
				else if(profCollection[k].equals(")")) {
					myExpProf += " " + profCollection[k] + " ";
					k++;
				}
				else if(profCollection[k].equalsIgnoreCase(GlobalConstants.advSearchImeTok)) {
					hadErrorProf = !createPVar(profCollection[k].toLowerCase(), profCollection[k+1], profCollection[k+2]);
					k+=3;
				}
				else if(profCollection[k].equalsIgnoreCase(GlobalConstants.advSearchPrezTok)) {
					hadErrorProf = !createPVar(profCollection[k].toLowerCase(), profCollection[k+1], profCollection[k+2]);
					k+=3;
				}
				else if(profCollection[k].equalsIgnoreCase(GlobalConstants.advSearchTitTok)) {
					int temp = k + 2;
					boolean correct = false;
					
					do {
						if(profCollection[temp].matches(".*\"$")) {
							correct = true;
							break;
						}
						temp++;						
						//predmeti = (profesori == {titula == "doktor nauka"})
					}while(temp < profCollection.length);
					
					if(!correct) {
						break;
					}
					String val = "";
					for(int i = 2; i <= temp - k; i++)
						val += " " + profCollection[k+i];
					
					val = val.substring(1);
					hadErrorProf = !createPVar(profCollection[k].toLowerCase(), profCollection[k+1], val);
					k = temp + 1;
				}
				else if(profCollection[k].equalsIgnoreCase(GlobalConstants.advSearchZvaTok)) {
					int temp = k + 2;
					boolean correct = false;
					do {
						if(profCollection[temp].matches(".*\"$")) {
							correct = true;
							break;
						}
						temp++;						
						//predmeti = (profesori == {zvanje == "saradnik u nastavi" or zvanje == "asistent" or zvanje == "asistent sa doktoratom" || zvanje == "docent" OR zvanje == "vanredni profesor" || zvanje == "redovni profesor" || zvanje == "profesor emeritus"})
					}while(temp < profCollection.length);
					if(!correct) {
						break;
					}
					String val = "";
					for(int i = 2; i <= temp - k; i++)
						val += " " + profCollection[k+i];
					
					val = val.substring(1);
					hadErrorProf = !createPVar(profCollection[k].toLowerCase(), profCollection[k+1], val);
					k = temp + 1;
				} 
				else if(profCollection[k].equalsIgnoreCase("and")) {
					myExpProf += " & ";
					k++;
				}
				else if(profCollection[k].equalsIgnoreCase("or")) {
					myExpProf += " | ";
					k++;
				}
				else {
					err = new ErrorDialog(GlobalConstants.advSearchErrProfTokNotFound);
					hadErrorProf = true;
					break;
				}
				if(hadErrorProf) 
					break;
				
			}while(k < profCollection.length);
		}
	}
	
	public boolean createPVar(String col, String exp, String val) {
		
		PVar temp;
		String s = col + " " +  exp + " " + val;
		if(col.equalsIgnoreCase(GlobalConstants.advSearchImeTok) || col.equalsIgnoreCase(GlobalConstants.advSearchPrezTok) || col.equalsIgnoreCase(GlobalConstants.advSearchTitTok) || col.equalsIgnoreCase(GlobalConstants.advSearchZvaTok)) {
			if(!exp.equals("==") && !exp.equals("!=")) {
				err = new ErrorDialog(GlobalConstants.advSearchErrProfRelOps);
				return false;
			}
			temp = new PVar(ip,s);
			myExpProf += " " + ip++ + " ";
			pvars.add(temp);
		}
		else {
			return false;
		}
		
		return true;
	}
	
	public void executePVarQuerries() {
		boolean noErrors = true;
		for(PVar v : pvars) {
			if(v.getV().toLowerCase().startsWith(GlobalConstants.advSearchImeTok) || v.getV().toLowerCase().startsWith(GlobalConstants.advSearchPrezTok) || v.getV().toLowerCase().startsWith(GlobalConstants.advSearchTitTok) || v.getV().toLowerCase().startsWith(GlobalConstants.advSearchZvaTok))
				noErrors &= GlavniProzor.getControllerProfesor().advSrcTxt(v.getV(), v.getSol());
			else	
				noErrors &= false;
		}
		if(!noErrors) {
			err = new ErrorDialog(GlobalConstants.advSearchErrProfSingleExps);
			hadErrorProf = true;
		}
	}
	
	public PVar getPVbyName(char a) {
		for(PVar v : pvars)
			if(v.getN() == a)
				return v;
		return null;
	}
	
	public void executePExpression() {
		String[] toInter = myExpProf.split("\\+");
		ArrayList<ArrayList<Profesor>> toDo = new ArrayList<ArrayList<Profesor>>();
		ArrayList<ArrayList<Profesor>> toOr = new ArrayList<ArrayList<Profesor>>();
		for(String s : toInter) {
			for(char c : s.toCharArray())
				toDo.add(getPVbyName(c).getSol());
			toOr.add(GlavniProzor.getControllerProfesor().intersect(toDo));
			toDo = new ArrayList<ArrayList<Profesor>>();
		}
		
		psolution = GlavniProzor.getControllerProfesor().union(toOr);
	}
}
