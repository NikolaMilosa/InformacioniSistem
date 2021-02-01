package model;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import view.GlavniProzor;

public class DataBaseClass implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8688220035647563544L;
	
	private ArrayList<Predmet> listaPredmeta;
	private ArrayList<Profesor> listaProfesora;
	private ArrayList<Student> listaStudenti;
	
	public DataBaseClass() {
		listaPredmeta = new ArrayList<Predmet>();
		listaProfesora = new ArrayList<Profesor>();
		listaStudenti = new ArrayList<Student>();
	}
	
	public void setLPred(ArrayList<Predmet> t) {
		listaPredmeta = t;
	}
	
	public ArrayList<Predmet> getPred(){
		return listaPredmeta;
	}
	
	public void setLProf(ArrayList<Profesor> t) {
		listaProfesora = t;
	}
	
	public ArrayList<Profesor> getProf(){
		return listaProfesora;
	}
	
	public void setLStud(ArrayList<Student> t) {
		listaStudenti = t;
	}
	
	public ArrayList<Student> getStud(){
		return listaStudenti;
	}
	
	public DataBaseClass getInst() {
		return this;
	}
	
	public void serialize() throws IOException {
		File f = new File("resources" + File.separator + "newDB.txt");
		f.delete();
		f.createNewFile();
		try {
			FileOutputStream fos = new FileOutputStream(f);
			ObjectOutputStream o = new ObjectOutputStream(new BufferedOutputStream(fos));
			
			listaPredmeta = GlavniProzor.getControllerPredmet().getListaPredmeta();
			listaProfesora = GlavniProzor.getControllerProfesor().getListaProfesora();
			listaStudenti = GlavniProzor.getControllerStudent().getListaStudenata();
			
			o.writeObject(getInst());
			
			o.close();
			fos.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deserialize() throws FileNotFoundException, IOException{

        File f = new File("resources" + File.separator + "newDB.txt");
        try(FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(fis));) {

            DataBaseClass temp = new DataBaseClass();

            temp = (DataBaseClass) ois.readObject();

            GlavniProzor.getControllerPredmet().setL(temp.getPred());
            GlavniProzor.getControllerProfesor().setL(temp.getProf());
            GlavniProzor.getControllerStudent().setL(temp.getStud());

            ois.close();
            fis.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}