package classi;

import java.io.PrintStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import utility.Constant;

public class Paziente {
	
	public Paziente(String codiceFiscale, String nome, String cognome, Date dataNascita, String luogoNascita) {
		this.codiceFiscale = codiceFiscale;
		this.nome = nome;
		this.cognome = cognome;
		this.dataNascita = dataNascita;
		this.luogoNascita = luogoNascita;
		this.arrayEsami = new ArrayList<Esame>();
	}
	
	public String getCodiceFiscale() {
		return codiceFiscale;
	}
	public String getNome() {
		return nome;
	}
	public String getCognome() {
		return cognome;
	}
	public Date getDataNascita() {
		return dataNascita;
	}
	public String getLuogoNascita() {
		return luogoNascita;
	}
	public ArrayList<Esame> getArrayEsami() {
		return arrayEsami;
	}
	
	public void addEsame(Esame inEsame) {
		arrayEsami.add(inEsame);
	}
	
	public static Paziente read(Scanner in) {
		
		if(!in.hasNextLine()) return null;
		String codiceFiscale = in.nextLine();
		
		if(!in.hasNextLine()) return null;
		String nome = in.nextLine();
		
		if(!in.hasNextLine()) return null;
		String cognome = in.nextLine();
		
		if(!in.hasNextLine()) return null;
		String dataNascitaString = in.nextLine();
		Date dataNascita = null;
		try {
			dataNascita = Constant.DATA.parse(dataNascitaString);
		} catch(ParseException e) {
			System.err.println("Eccezione Parse catturata.");
			System.err.println("Sara' inserita la data odierna.");
			dataNascita = new Date();
		}
		
		if(!in.hasNextLine()) return null;
		String luogoNascita = in.nextLine();
		
		return new Paziente(codiceFiscale, nome, cognome, dataNascita, luogoNascita);
		
	}

	public void print(PrintStream ps) {
		System.out.println("--- Paziente ---");
		ps.println(codiceFiscale);
		ps.println(nome);
		ps.println(cognome);
		ps.println(Constant.DATA.format(dataNascita));
		ps.println(luogoNascita);
		System.out.println("");
	}
	
	public void printEsami(PrintStream ps) {
		for(Esame e : arrayEsami)
			e.print(System.out);
		System.out.println("");
	}

	private String codiceFiscale;
	private String nome;
	private String cognome;
	private Date dataNascita;
	private String luogoNascita;
	private ArrayList<Esame> arrayEsami;
	
}
