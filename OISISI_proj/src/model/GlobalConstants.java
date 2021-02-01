package model;

import java.io.File;

public class GlobalConstants {
	//Some default sizes :
	public static int aoedw = 400;  //addOrEditWidth
	public static int aoedh = 500;  //addOrEditHeight
	
	//Prof dummy:
	public static Profesor dummy = new Profesor();
	
	//Image usage :
	public static String menuBarImg;
	public static String addImg = "images" + File.separator + "add_button.png";
	public static String closeImg = "images" + File.separator + "close_button.png";
	public static String editImg = "images" + File.separator + "edit_button.png";
	public static String delImg = "images" + File.separator + "bin_button.png";
	public static String helpImg = "images" + File.separator + "help_button.png";
	public static String aboutImg = "images" + File.separator + "about_button.png";
	public static String srcImg = "images" + File.separator + "search_button.png";
	public static String toolBarImg = "images" + File.separator + "Tool_Bar.png";
	public static String advSearImg = "images" + File.separator + "adv_search.png";
	public static String searchFieldImg = "images" + File.separator + "search_field.png";
	public static String languageImg = "images" + File.separator + "language_image.png";
	public static String serbianImg = "images" + File.separator + "serbian_img.png";
	public static String englishImg = "images" + File.separator + "english_img.png";
	
	//Regex :
	public static String regExNameOrSurename = "\\p{L}+";
	public static String regExAddress = "([1-9][0-9]{0,3}[\\s]*)?[\\p{L}]+([\\s]*[\\p{L}]+)*([\\s]*[0-9]{0,4})?[\\s]*[,][\\s]*([\\p{L}]+)([\\s]*([\\p{L}])+)*([\\s]*[,][\\s]*[\\p{L}]+[\\s]*[0-9]+)?";
	public static String regExLicKart = "[0-9]+";
	public static String regExNumber = "[0-9]{3}[/][0-9]{3,4}[-][0-9]{3,4}";
	public static String regExEmail = "[a-zA-Z0-9]+([\\.][a-zA-Z0-9]+)*@((\\bgmail.com\\b)|(\\byahoo.com\\b)|(\\buns.ac.rs\\b)|(\\bhotmail.com\\b)|(\\bmailinator.com\\b))";
	public static String regExTitOrMaj = "[a-z]+([\\s][a-z]+)*";
	public static String regExBrIndexa = "[a-zA-Z]{2}[-][0-9]{1,3}[-][2]{1}[0]{1}[0,1,2]{1}[0-9]{1}";
	public static String regGodUpisa = "[2]{1}[0]{1}[0,1,2]{1}[0-9]{1}";
	public static String regExNazivPred = "([\\p{L}]+[\\s]{0,1})+[0-9]{0,1}";
	public static String regExEspb = "[1-9]{1}[0-9]{0,1}";
	public static String[] regExDatePoss = {"dd.MM.yyyy.", "dd.MM.yyyy", "d.MM.yyyy.", "d.MM.yyyy", "dd.M.yyyy.", "dd.M.yyyy", "d.M.yyyy.", "d.M.yyyy"};
	
	//Possible marks :
	public static String[] ocene = {"6","7","8","9","10"};
	
	public static String[] zvanja = {"Saradnik u nastavi","Asistent", "Asistent sa doktoratom", "Docent", "Vanredni profesor", "Redovni profesor", "Profesor emeritus"};
	public static String[] titule = {"BSc", "MSc", "Magistar", "Doktor nauka", "Profesor dr."};
	
	//Konstante vrednosti polja :
	public static String advSearchSemPos1 = "\"zimski\"";
	public static String advSearchSemPos2 = "\"letnji\"";
	public static String advSearchSemPos3 = "zimski";
	public static String advSearchSemPos4 = "letnji";
		
	//Imena tokena u advSearchWorkPred :
	public static String advSearchPredTok = "predmeti";
	public static String advSearchProfTok = "profesori";
	public static String advSearchSifTok1 = "sifra";
	public static String advSearchSifTok2 = "šifra";
	public static String advSearchNazTok = "naziv";
	public static String advSearchESPBTok = "espb";
	public static String advSearchGodTok = "godina";
	public static String advSearchSemTok = "semestar";
			
	public static String advSearchImeTok = "ime";
	public static String advSearchPrezTok = "prezime";
	public static String advSearchTitTok = "titula";
	public static String advSearchZvaTok = "zvanje";
	
	//AddOrEditStudent : 
	public static String AOESgod1 = "I (prva)"; 
	public static String AOESgod2 = "II (druga)";
	public static String AOESgod3 = "III (treća)";
	public static String AOESgod4 = "IV (četvrta)";
			
	public static String AOESBudz = "Budžet";
	public static String AOESSamof = "Samofinansiranje";
	
	public static String prdNemaProf, imeLab,przLab,drLab,adrKancLab,adrStanLab,konTelLab,brLicKartLab,emailLab,titulaLab,zvanjeLab;
	public static String indexLab,upisLab,trenutnaLab,finansLab;
	public static String sifraLab,nazivLab,godIzvLab,semestarLab,espbLab,profLab,ocenaLab,datLab;
	
	public static String przImeToolTip,drpToolTip,adrToolTip,konTelToolTip,emailToolTip,brLicKartToolTip,titZvToolTip,brIndexaToolTip,godUpisaToolTip;
	public static String srchProfToolTip,srchPredToolTip,srchStudToolTip,nazivPredToolTip,espbToolTip,profToolTip;
	
	public static String obvPolje,errAddProf,errEditProf,errAddStud,errAddPred,upitZatvaranjeMF,upitZatvaranjeTitle,yesOpt,noOpt;
	public static String upitBrisanjePred,upitSklanjanjeProfTitle,upitSklanjanjeProfSaPred,upitBrisanjePredTitle,dodavanjePredProfDialog,dodavanjeProfPredDialog;
	public static String predmetiTekst,upitBrisanjeProf,upitBrisanjeProfTitle,btnPonisti,upitBrisanjePredKodStud,greskaPriIzboruPredmeta,upitBrisanjeStud;
	public static String upitBrisanjeStudTitle,greskaPriIzboruOcene,upitPonistavenjeOceneTitle,upitPonistavenjeOcene,upitBrisanjePredKodProf;
	
	public static String btnDodaj,btnObrisi,btnPolaganje,addBtnToolTipTxt,editBtnToolTipTxt,delBtnToolTipTxt,srchBtnToolTipTxt,srchFieldToolTipTxt;
	public static String advSearchToolTip,menuFile,menuNew,menuClose,menuEdit,menuDelete,menuHelp,menuAbout,menuAdvanced,menuLanguage,menuSearch;
	
	public static String menuSerbian,menuEnglish,mfName,tabStudentName,tabProfesorName,tabPredmetName,profAdd,profEdit,studAdd,editStud,predAdd;
	public static String predEdit,errName,unosOcene,spr,npr,espb,god,sem,btnOkName,btnCncName,btnDodPred,btnUklPred;
	
	public static String profEditTabOsnInf,profEditTabPrd,errAdvSearProf,advTitlePred,labAdvSear;
	
	public static String advSearchToolTipPred,advSearchErrParse,advSearchErrBegin,advSearchErrTokNotFound,advSearchErrRelOpsNS,advSearchErrGodEspb;
	public static String advSearchErrSemOps,advSearchErrSemVals,advSearchErrSingleExps,advSearchErrParseProf,advSearchErrProfBegin,advSearchErrProfTokNotFound;
	public static String advSearchErrProfRelOps,advSearchErrProfSingleExps;
	
	public static String predmetErrGod,predGodPrva,predGodDruga,predGodTreca,predGodCetvrta,aboutLab1,aboutLab2,aboutLab4,aboutLab5,aboutLab6,aboutLab8;
	public static String aboutLab9,aboutLab10,aboutTitle,aOESProsOcLab,aOESUkEspbLab,aOESTabInfo,aOESTabPolozeni,aOESTabNepolozeni;
	
	public static String helpLab1,helpLab2,helpLab4,helpLab5,helpLab6,helpLab7,helpLab8,helpLab9,helpLab10,helpLab11,helpLab12,helpLab13,helpLab14;
	public static String helpLab15,helpLab16,helpLab17,helpLab19,helpLab20,helpLab21,helpLab22,helpLab23,helpLab24,helpLab26,helpLab27,helpLab28;
	public static String helpLab29,helpLab30,helpLab31,helpTitle,tabOcenaSif,tabOcenaNaz,tabOcenaOce,tabOcenaDat,tabStudInd,tabStudIme,tabStudPrez;
	public static String tabStudGS,tabStudStat,tabStudPros,brLicKartCol,imeCol,przCol,titulaCol,zvanjeCol;

	
	public static void setLangSerbian() {
		
		menuBarImg = "images" +  File.separator + "Menu_Bar_srpski.png";
		
		//Tekst predmeta koji nemaju profesora :
		prdNemaProf = "Nema profesora";
		
		//Imena labela :
		imeLab = "Ime* ";
		przLab = "Prezime* ";
		drLab = "Datum rođenja* "; 
		adrKancLab = "Adresa kancelarije* ";
		adrStanLab = "Adresa stanovanja* ";
		konTelLab = "Kontakt telefon* ";
		brLicKartLab = "Broj lične karte* ";
		emailLab = "Email* ";
		titulaLab = "Titula* ";
		zvanjeLab = "Zvanje* ";
		
		indexLab = "Broj indeksa* ";
		upisLab = "Godina upisa* ";
		trenutnaLab = "Trenutna godina studija* ";
		finansLab = "Način finansiranja* ";
		
		sifraLab = "Šifra* ";
		nazivLab = "Naziv* ";
		godIzvLab = "Godina*";
		semestarLab = "Semestar* ";
		espbLab = "ESPB* ";
		profLab = "Profesor";
		ocenaLab = "Ocena*";
		datLab = "Datum*";
		
		//TextField tooltips : 
		przImeToolTip = "Samo slova su dozvoljena";
		drpToolTip = "dd.MM.yyyy format";
		adrToolTip = "Ime ulice (broj zgrade), Grad";
		konTelToolTip = "<html>Samo brojevi su dozvoljeni <br> Format : xxx/yyy(y)-zzz(z) </html>";
		emailToolTip = "<html>Standardni email format : ...@domen <br> Podržani domeni : gmail.com, hotmail.com, yahoo.com, uns.ac.rs, mailinator.com</html>";
		brLicKartToolTip = "<html>Samo brojevi su dozvoljni <br> tacno 9 cifara obavezno</html>";
		titZvToolTip = "Dozvoljeno je jedna ili više reči";
		brIndexaToolTip = "<html>SS-xxx-yyyy format, SS-smer, xxx-broj (najmanje 1 cifra)<br>yyyy-godina upisa (godine od 2000.)</html>";
		godUpisaToolTip = "yyyy format, godine od 2000.";
		
		srchProfToolTip = "<html>Format : \"prezime\" \"ime\"<br>Pretraga je case insensitive</html>";
		srchPredToolTip = "<html>Format : \"naziv\"<br>Pretraga je case insensitive</html>";
		srchStudToolTip =  "<html>Format : \"prezime\" \"ime\" \"broj indeksa\"<br>Pretraga je case insensitive</html>";
		
		nazivPredToolTip = "Mora početi slovom, može sadržati i jednu cifru";
		espbToolTip = "1 ili 2 cifre, prva cifra ne sme biti 0";
		profToolTip = "Odaberite profesora klikom na dugme '+'";
		
		//Mešani tekst :
		obvPolje = "Obavezno polje!";
		errAddProf = "Neuspešno dodavanje profesora!";
		errEditProf = "Neuspešna izmena profesora!";
		errAddStud = "Neuspešno dodavanje studenta, postoji student sa istim brojem indeksa!";
		errAddPred = "Neuspešno dodavanje predmeta, postoji predmet sa istom šifrom!";
		upitZatvaranjeMF = "Da li ste sigurni da želite da zatvorite aplikaciju?";
		upitZatvaranjeTitle = "Zatvaranje aplikacije";
		yesOpt = "Da";
		noOpt = "Ne";
		upitBrisanjePred = "Da li ste sigurni da želite da obrišete izabrani predmet?";
		upitSklanjanjeProfTitle = "Uklanjanje profesora";
		upitSklanjanjeProfSaPred = "Da li ste sigurni da želite da uklonite izabranog profesora sa predmeta?";
		upitBrisanjePredTitle = "Uklanjanje predmeta";
		dodavanjePredProfDialog = "Dodaj predmet";
		dodavanjeProfPredDialog = "Odaberi profesora";
		predmetiTekst = "Predmeti :";
		upitBrisanjeProf = "Da li ste sigurni da želite da obrišete izabranog profesora?";
		upitBrisanjeProfTitle = "Brisanje profesora";
		btnPonisti = "Poništi ocenu";
		upitBrisanjePredKodStud = "Da li ste sigurni da želite da uklonite predmet?";
		greskaPriIzboruPredmeta = "Niste izabrali ni jedan predmet";
		upitBrisanjeStud = "Da li ste sigurni da želite da obrišete izabranog studenta?";
		upitBrisanjeStudTitle = "Brisanje studenta";
		greskaPriIzboruOcene = "Niste izabrali ni jednu ocenu";
		upitPonistavenjeOceneTitle = "Poništavanje ocene";
		upitPonistavenjeOcene = "Da li ste sigurni da želite da poništite ocenu?";
		upitBrisanjePredKodProf = "Da li ste sigurni?";
		btnDodaj = "Dodaj";
		btnObrisi = "Obriši";
		btnPolaganje = "Polaganje";
		
		//Button tooltips :
		addBtnToolTipTxt = "Dodaj";
		editBtnToolTipTxt = "Izmeni";
		delBtnToolTipTxt = "Obriši";
		srchBtnToolTipTxt = "Pretraži";
		srchFieldToolTipTxt = "";
		advSearchToolTip = "Napredna pretraga";
		
		//Menu stvari :
		menuFile = "File"; 
		menuNew = "Dodaj";
		menuClose = "Zatvori";
		menuEdit = "Izmeni";
		menuDelete = "Obriši";
		menuHelp = "Pomoć";
		menuAbout = "O aplikaciji";
		menuAdvanced = "Napredno";
		menuLanguage = "Izaberi jezik";
		menuSearch = "Pretraži";
		menuSerbian = "Srpski";
		menuEnglish = "Engleski";
		
		//Frame names :
		mfName = "Studentska služba";
		tabStudentName = "Studenti";
		tabProfesorName = "Profesori";
		tabPredmetName = "Predmeti";
		profAdd = "Dodavanje profesora";
		profEdit = "Izmeni profesora";
		studAdd = "Dodavanje studenta";
		editStud = "Izmeni studenta";
		predAdd = "Dodavanje predmeta";
		predEdit = "Izmeni predmet";
		errName = "Greška";
		unosOcene = "Unos ocene";
		
		//Column names predmet :
		spr = "Šifra predmeta";
		npr = "Naziv predmeta";
		espb = "ESPB";
		god = "Godina";
		sem = "Semestar";
		
		//Button text :
		btnOkName = "Potvrdi";
		btnCncName = "Odustani";
		btnDodPred = "Dodaj predmet";
		btnUklPred = "Ukloni predmet";
		
		//Edit tab names :
		profEditTabOsnInf = "Informacije";
		profEditTabPrd = "Predmeti";
		
		//Error za naprednu pretragu :
		errAdvSearProf = "Nije omogućena napredna pretraga";
		
		//Naslov napredne pretrage predmeta :
		advTitlePred = "Napredna pretraga predmeta";
		
		labAdvSear = "Relacioni izraz :"; 
		
		//Tooltip za dodatni zadatak predmeta :
		advSearchToolTipPred = "<html>Početak iskaza mora biti formata \"predmeti = (...)\" <br>Lista podržanih polja za pretragu kod "
				+ "predmeta : <br>  *sifra - po vrednosti (izmedju \"\") i po regex-u (između / /)<br>  *naziv - po vrednosti (između \"\") i po regex-u(između / /)<br>"
				+ "  *godina - po vrednosti<br>  *semestar - letnji/zimski<br>  *espb bodovima - po vrednosti<br>  *profesorima - profesori se unose"
				+ " u upit u obliku \"profesori == {...}\"<br>   polja podržana kod profesora :<br>    *ime - po vrednosti (između \"\") i po regex-u (između / /)<br>"
				+ "    *prezime - po vrednosti (između \" \") i po regex-u (između / /)<br>    *titula - jedna od titula iz ponuđenih<br>    *zvanje "
				+ "- jedno iz ponuđenih </html>";
		
		advSearchErrParse = "Nije uspelo parsiranje izraza";
		advSearchErrBegin = "Iskaz ne počinje korektno";
		advSearchErrTokNotFound = "Nije spojeno ni sa jednim tokenom, proverite izraz";
		advSearchErrRelOpsNS = "Tipovi sifra i naziv mogu imati relacione operatore !=/==";
		advSearchErrGodEspb = "Nije validan operator za tip ESPB/godina";
		advSearchErrSemOps = "Tip semestar ne prihvata zadate relacione operatore";
		advSearchErrSemVals = "Tip semestar ne prihvata zadatu vrednost";
		advSearchErrSingleExps = "Neuspešno izvršavanje pojedinačnih upita";
		advSearchErrParseProf = "Nije uspelo parsiranje izraza u delu profesori";
		advSearchErrProfBegin = "Iskaz kod profesora ne počinje korektno";
		advSearchErrProfTokNotFound = "Nije spojeno ni sa jednim tokenom, proverite izraz";
		advSearchErrProfRelOps = "Tipovi za profesora mogu imati relacione operatore !=/==";
		advSearchErrProfSingleExps = "Neuspešno izvršavanje pojedinačnih upita profesora";
		
		predmetErrGod = "Nepostojeci semestar! [letnji,zimski]";
		
		//Godine predmeta :
		predGodPrva = "(1) - PRVA";
		predGodDruga = "(2) - DRUGA";
		predGodTreca = "(3) - TREĆA";
		predGodCetvrta = "(4) - ČETVRTA";
		
		//About dialog :
		aboutLab1 = "\t Aplikacija \"Studentska služba\"";
		aboutLab2 = "\t Verzija: 1.0";
		aboutLab4 = "\t Aplikacija \"Studentska služba\" omogućava evidenciju studenata, profesora i predmeta,\t ";
		aboutLab5 = "\t kako bi olakšala rad šalterskim radnicima studentske službe Fakulteta tehničkih nauka,\t ";
		aboutLab6 = "\t Univerziteta u Novom Sadu.";
		aboutLab8 = "\t Autori: Anica Đukić i Nikola Milosavljević";
		aboutLab9 = "\t Studenti 3. godine smera \"Računarstvo i automatika\" Fakulteta tehničkih nauka,";
		aboutLab10 = "\t Univerziteta u Novom Sadu.";
		
		aboutTitle = "O aplikaciji";
		
		aOESProsOcLab = "Prosečna ocena: ";
		aOESUkEspbLab = "Ukupno ESPB: ";
		aOESTabInfo = "Informacije";
		aOESTabPolozeni = "Položeni";
		aOESTabNepolozeni = "Nepoloženi";
		
		//Help dialog :
		helpLab1 = "\t Aplikacija \"Studentska služba\" se sastoji od Menu bara, Tool bara i Status bara.\n";
		helpLab2 = "\t Menu bar se sastoji od sledećih stavki:\n";
		helpLab4 = "\t 1. File (Alt + F)\n";
		helpLab5 = "\t Dodaj (Alt + N, Ctrl + N) - Dodavanje novog entiteta u sistem (studenta, profesora ili predmeta).\n";
		helpLab6 = "\t Zatvori (Alt + C, Ctrl + C) - Zatvaranje aplikacije.\n";
		helpLab7 = "\t 2. Izmeni (Alt + E)\n";
		helpLab8 = "\t Izmeni (Alt + E, Ctrl + E) - Izmena postojećeg entiteta (studenta, profesora ili predmeta).\n";
		helpLab9 = "\t Obriši (Alt + D, Ctrl + D) - Brisanje postojećeg entiteta (studenta, profesora ili predmeta).\n";
		helpLab10 = "\t 3. Pomoć (Alt + H)\n";
		helpLab11 = "\t Pomoć (Alt + H, Ctrl + H) -  Prikaz informacija neophodnih za korišćenje aplikacije.\n";
		helpLab12 = "\t O aplikaciji (Alt + A, Ctrl + A) -  Prikaz verzije aplikacije, kao i kratak opis iste. Nakon toga sledi sažeta biografija autora.\n";
		helpLab13 = "\t 4. Napredno (Alt + A) - Napredne opcije";
		helpLab14 = "\t Izaberi jezik (Alt + L) - Omogućava izbor jednog od jezika (Srpski - {Alt + S, Ctrl + R}, Engleski - {Alt + E, Ctrl + L})";
		helpLab15 = "\t Pretraži (Alt + S, Ctrl + S) - Omogućava naprednu pretragu entiteta preko uslovnih iskaza";
		helpLab16 = "\t Svaka stavka menija ima svoju ikonicu koja se nalazi odmah pored njenog naziva.\n ";
		helpLab17 = "\t Neke od tih ikonica se nalaze i u Tool baru:\n";
		helpLab19 = "\t 1. Dodaj (Ctrl + N) \n";
		helpLab20 = "\t 2. Izmeni (Ctrl + E) \n";
		helpLab21 = "\t 3. Obriši (Ctrl + D)\n";
		helpLab22 = "\t 4. Napredna pretraga (Ctrl + S)\n";
		helpLab23 = "\t Njihovim izborom se ostvaruju iste funkcionalnosti kao i izborom stavki menu bara sa istim imenom.";
		helpLab24 = "\t U Tool baru se takođe nalazi polje za pretragu entiteta:";
		helpLab26 = "\t Pozicioniranjem na odgovarajući tab (Studenti, Profesori, Predmeti) omogućena je pretraga:";
		helpLab27 = "<html> <&ensp> Studenata: Unošenjem jedne ili dve ili tri reči, gde je prva obavezna i odnosi se na deo prezimena studenta, "
				+ "<br> druga, odnosno treća reč su opcione i odnose se na deo imena, odnosno broja indeksa studenta.</hmtl>";
		helpLab28 = "<html> <&ensp> Profesora: Unošenjem jedne ili dve reči, gde je prva obavezna i odnosi se na deo prezimena profesora,"
				+ "<br> druga reč je opciona i odnosi se na deo imena profesora.</hmtl>";
		helpLab29 = "<html> <&ensp> Predmeta: Unošenjem jedne reči koja se odnosi na deo naziva predmeta.</hmtl>";
		helpLab30 = "\t Status bar se nalazi na dnu glavnog prozora i sastoji od imena aplikacije i prikaza trenutnog vremena i datuma.";
		helpLab31 = "\t Kako se aplikacija bude dalje razvijala tako će ovaj Help dijalog biti naknadno proširen.";
		helpTitle = "Pomoć";
		
		//Tabela ocena :
		tabOcenaSif = "Šifra predmeta";
		tabOcenaNaz = "Naziv predmeta";
		tabOcenaOce = "Ocena";
		tabOcenaDat = "Datum";
		
		//Tabela studenti :
		tabStudInd = "Indeks";
		tabStudIme = "Ime";
		tabStudPrez = "Prezime";
		tabStudGS = "Godina studija";
		tabStudStat = "Status";
		tabStudPros = "Prosek";
		
		//Titula profesori :
		brLicKartCol = "Broj lične karte";
		imeCol = "Ime";
		przCol = "Prezime";
		titulaCol = "Titula";
		zvanjeCol = "Zvanje";
	} 
	
	public static void setLangEnglish() {
		//Tekst predmeta koji nemaju profesora :
		menuBarImg = "images" +  File.separator + "Menu_Bar.png";
		
		prdNemaProf = "Not assigned";
				
		//Imena labela :
		imeLab = "Name* ";
		przLab = "Surname* ";
		drLab = "Date of birth* "; 
		adrKancLab = "Work address* ";
		adrStanLab = "Residence address* ";
		konTelLab = "Phone number* ";
		brLicKartLab = "Identifactional number* ";
		emailLab = "Email* ";
		titulaLab = "Title* ";
		zvanjeLab = "Metier* ";
				
		indexLab = "Student ID* ";
		upisLab = "Enrollment year* ";
		trenutnaLab = "Current year of study* ";
		finansLab = "Way of financing* ";
				
		sifraLab = "Subject ID* ";
		nazivLab = "Subject name* ";
		godIzvLab = "Year*";
		semestarLab = "Semester* ";
		espbLab = "ESPB* ";
		profLab = "Professor";
		ocenaLab = "Grade*";
		datLab = "Date*";
				
		//TextField tooltips : 
		przImeToolTip = "Only letters are allowed";
		drpToolTip = "dd.MM.yyyy format";
		adrToolTip = "<html>Address is consisted of street name and the number of the building<br> followed by a comma and then the name of the city</html>";
		konTelToolTip = "<html>Only numbers are allowed<br> Format : xxx/yyy(y)-zzz(z)</html>";
		emailToolTip = "<html>Standard email format : ...@domain <br>Supported domains : gmail.com, hotmail.com, yahoo.com, uns.ac.rs</html>";
		brLicKartToolTip = "<html>Only numbers are allowed<br>Exactly 9 figures</html>";
		titZvToolTip = "One or more words is allowed";
		brIndexaToolTip = "<html>SS-xxx-yyyy format, SS-orientation, xxx-number (atleast 1 figure)<br>yyyy-year of enrollment (years from 2000.)</html>";
		godUpisaToolTip = "yyyy format, years from 2000.";
		
		srchProfToolTip = "<html>Format : \"surname\" \"name\"<br>Search is case insensitive</html>";
		srchPredToolTip = "<html>Format : \"name\"<br>Search is case insensitive</html>";
		srchStudToolTip =  "<html>Format : \"surname\" \"name\" \"student ID\"<br>Search is case insensitive</html>";
		
		nazivPredToolTip = "It has to begin with a letter and can contain one figure";
		espbToolTip = "1 or 2 figures, first figure cannot be 0";
		profToolTip = "To choose a professor click on the '+' button";
		
		//Mešani tekst :
		obvPolje = "Required field!";
		errAddProf = "Unsuccessful adding of a professor!";
		errEditProf = "Unsuccessful editing of a professor!";
		errAddStud = "Unsuccessful adding of a student, the student with that ID already exists!";
		errAddPred = "Unsuccessful adding of a subject, the subject with that ID already exists!";
		upitZatvaranjeMF = "Do you want to close the application?";
		upitZatvaranjeTitle = "Application closing";
		yesOpt = "Yes";
		noOpt = "No";
		upitBrisanjePred = "Delete the selected subject?";
		upitSklanjanjeProfTitle = "Professor deleting";
		upitSklanjanjeProfSaPred = "Delete the selecetd professor from subject?";
		upitBrisanjePredTitle = "Subject removing";
		dodavanjePredProfDialog = "Add subject";
		dodavanjeProfPredDialog = "Choose the professor";
		predmetiTekst = "Subjects :";
		upitBrisanjeProf = "Delete the selected professor?";
		upitBrisanjeProfTitle = "Professor deleting";
		btnPonisti = "Cancel the grade";
		upitBrisanjePredKodStud = "Remove the selected subject?";
		greskaPriIzboruPredmeta = "You haven't chosen any subject";
		upitBrisanjeStud = "Delete the selected student?";
		upitBrisanjeStudTitle = "Student deleting";
		greskaPriIzboruOcene = "You haven't chosen any grade";
		upitPonistavenjeOceneTitle = "Grade canceling";
		upitPonistavenjeOcene = "Cancel the selected grade?";
		upitBrisanjePredKodProf = "Remove the selected subject(s)?";
		btnDodaj = "Add";
		btnObrisi = "Delete";
		btnPolaganje = "Add grade";
		
		//Button tooltips :
		addBtnToolTipTxt = "New";
		editBtnToolTipTxt = "Edit";
		delBtnToolTipTxt = "Delete";
		srchBtnToolTipTxt = "Search";
		srchFieldToolTipTxt = "";
		advSearchToolTip = "Advanced search";
		
		//Menu stvari :
		menuFile = "File"; 
		menuNew = "New";
		menuClose = "Close";
		menuEdit = "Edit";
		menuDelete = "Delete";
		menuHelp = "Help";
		menuAbout = "About";
		menuAdvanced = "Advanced";
		menuLanguage = "Language";
		menuSearch = "Search";
		menuSerbian = "Serbian";
		menuEnglish = "English";
		
		//Frame names :
		mfName = "Student service";
		tabStudentName = "Students";
		tabProfesorName = "Professors";
		tabPredmetName = "Subjects";
		profAdd = "Add professor";
		profEdit = "Edit professor";
		studAdd = "Add student";
		editStud = "Edit student";
		predAdd = "Add subject";
		predEdit = "Edit subject";
		errName = "Error";
		unosOcene = "Grading";
		
		//Column names predmet :
		spr = "Subject ID";
		npr = "Subject name";
		espb = "ESPB";
		god = "Year";
		sem = "Semester";
		
		//Button text :
		btnOkName = "Confirm";
		btnCncName = "Cancel";
		btnDodPred = "Add subject";
		btnUklPred = "Delete subject";
		
		//Edit tab names :
		profEditTabOsnInf = "Informations";
		profEditTabPrd = "Subjects";
		
		//Error za naprednu pretragu :
		errAdvSearProf = "Advanced search isn't implemented for that entity!";
		
		//Naslov napredne pretrage predmeta :
		advTitlePred = "Advanced search of subjects";
				
		labAdvSear = "Relational expression:"; 
		
		//Tooltip za dodatni zadatak predmeta :
		advSearchToolTipPred = "<html>The beggining of the expression must be formatted : \"predmeti = (...)\" <br>List of supported fields for search of subjects :"
				+ " <br>  *sifra - by value (between \"\") and by regex (between / /)<br>  *naziv - by value (between \"\") and by regex (between / /)<br>"
				+ "  *godina - by value<br>  *semestar - letnji/zimski<br>  *espb - by value<br>  *profesori - professors are inputted in format"
				+ " \"profesori == {...}\"<br>   fields supported for professors :<br>    *ime - by value (between \"\") and by regex (between / /)<br>"
				+ "    *prezime - by value (between \" \") and by regex (between / /)<br>    *titula - on of titles in professor add/edit dialog<br>    *zvanje "
				+ "- on of metiers in professor add/edit dialog</html>";
				
		advSearchErrParse = "Parsing of the expression unsuccessful";
		advSearchErrBegin = "The expression begining doesn't meet the required format";
		advSearchErrTokNotFound = "Something hasn't matched with any tokens, check the expression";
		advSearchErrRelOpsNS = "Types 'sifra' and 'naziv' can have relational operators : != / ==";
		advSearchErrGodEspb = "Operator isn't valid for types 'ESPB'/'godina'";
		advSearchErrSemOps = "Type 'semestar' doesn't accept written relational operators";
		advSearchErrSemVals = "Type 'semestar' doesn't accept written value";
		advSearchErrSingleExps = "Unsuccesful execution of individual subexpressions";
		advSearchErrParseProf = "Parsing of expression for subtype 'profesori' unsuccessful";
		advSearchErrProfBegin = "Expression begining for subtype 'profesori' doesn't meet the required format";
		advSearchErrProfTokNotFound = "Something hasn't matched with any tokens, check the expression";
		advSearchErrProfRelOps = "Types for 'profesori' can only have != / == operators";
		advSearchErrProfSingleExps = "Unsuccessful execution of individual subexpression for subtype 'profesori'";
		
		predmetErrGod = "Non-existant semester! [letnji,zimski]";
		
		//Godine predmeta :
		predGodPrva = "(1) - FIRST";
		predGodDruga = "(2) - SECOND";
		predGodTreca = "(3) - THIRD";
		predGodCetvrta = "(4) - FORTH";
				
		//About dialog :
		aboutLab1 = "\t Application \"Student service\"";
		aboutLab2 = "\t Version: 1.0";
		aboutLab4 = "\t Applicatoon \"Student service\" allows keeping record of students, professors and subjects \t ";
		aboutLab5 = "\t in order to facilitate the work of tellers of Faculty of Tehnical science's student service \t ";
		aboutLab6 = "\t University of Novi Sad.";
		aboutLab8 = "\t Authors: Anica Đukić and Nikola Milosavljević";
		aboutLab9 = "\t Students of 3rd year, orientation \"Računarstvo i automatika\" Faculty of Technical sciences,";
		aboutLab10 = "\t University of Novi Sad.";
		
		aboutTitle = "About";
		aOESProsOcLab = "Avarage grade: ";
		aOESUkEspbLab = "Total ESPB: ";
		aOESTabInfo = "Information";
		aOESTabPolozeni = "Passed subjects";
		aOESTabNepolozeni = "Other subjects";
		
		//Help dialog :
		helpLab1 = "\t Application \"Studentska služba\" is consisted of Menubar, Toolbar and Statusbar.\n";
		helpLab2 = "\t Menubar is consisted of the following :\n";
		helpLab4 = "\t 1. File (Alt + F)\n";
		helpLab5 = "\t New (Alt + N, Ctrl + N) - Creating the new entity for the system (student, professor or subject).\n";
		helpLab6 = "\t Close (Alt + C, Ctrl + C) - Closing the application.\n";
		helpLab7 = "\t 2. Edit (Alt + E)\n";
		helpLab8 = "\t Edit (Alt + E, Ctrl + E) - Editing the existing entity (student, professor or subject).\n";
		helpLab9 = "\t Delete (Alt + D, Ctrl + D) - Deleting the existing entity (student, professor or subject).\n";
		helpLab10 = "\t 3. Help (Alt + H)\n";
		helpLab11 = "\t Help (Alt + H, Ctrl + H) -  Displaying the information required for application usage.\n";
		helpLab12 = "\t About (Alt + A, Ctrl + A) -  Displaying the application version, and providing with the short description, followed with the short biography of the authors.\n";
		helpLab13 = "\t 4. Advanced (Alt + A) - Advanced options";
		helpLab14 = "\t Language (Alt + L) - Allows to switch between desired languages (Serbian - {Alt + S, Ctrl + R}, English - {Alt + E, Ctrl + L})";
		helpLab15 = "\t Search (Alt + S, Ctrl + S) - Supports advanced search of entities using expressions";
		helpLab16 = "\t Every menu item has its icon which is located right next to its name.\n ";
		helpLab17 = "\t Some of those icons can be found in the Toolbar:\n";
		helpLab19 = "\t 1. New (Ctrl + N) \n";
		helpLab20 = "\t 2. Edit (Ctrl + E) \n";
		helpLab21 = "\t 3. Delete (Ctrl + D)\n";
		helpLab22 = "\t 4. Search (Ctrl + S)\n";
		helpLab23 = "\t Their usage results grants same results as using the menu items with the same name.";
		helpLab24 = "\t In the Toolbar a field for regular entity search can be found:";
		helpLab26 = "\t Selected the desired tab (Students, Professors, Subjects) a search can be performed:";
		helpLab27 = "<html> <&ensp> Students: Inputing one,two or three words, where the first is required and resembles the student surname, "
				+ "<br> second, or third word areoptional and are linked with the name, or the student ID.</hmtl>";
		helpLab28 = "<html> <&ensp> Professors: Inputing one or two words, where the first is required and is refering to professor's surname,"
				+ "<br> second word is optional and resembles the name of a certain professor.</hmtl>";
		helpLab29 = "<html> <&ensp> Subject: Inputing one word which resembles a part of the subject name.</hmtl>";
		helpLab30 = "\t Statusbar is located in the bottom of the main frame and is made of the application name and has the displaying of date and time.";
		helpLab31 = "\t With the further progress of this application this help dialog will become more complex.";
		helpTitle = "Help";
		
		//Tabela ocena :
		tabOcenaSif = "Subject ID";
		tabOcenaNaz = "Subject name";
		tabOcenaOce = "Grade";
		tabOcenaDat = "Date";
		
		//Tabela studenti :
		tabStudInd = "Student ID";
		tabStudIme = "Name";
		tabStudPrez = "Surname";
		tabStudGS = "Year of study";
		tabStudStat = "Status";
		tabStudPros = "Average grade";
		
		//Titula profesori :
		brLicKartCol = "Professor ID";
		imeCol = "Name";
		przCol = "Surname";
		titulaCol = "Title";
		zvanjeCol = "Metier";
	}
}
