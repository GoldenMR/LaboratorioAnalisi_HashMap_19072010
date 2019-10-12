package classi;

import java.io.PrintStream;
import java.text.ParseException;
import java.util.Date;
import java.util.Scanner;

import utility.Constant;

public class Esame {
	
	public Esame(String codiceFiscale, String nomeEsame, Date dataEsame) {
		this.codiceFiscale = codiceFiscale;
		this.nomeEsame = nomeEsame;
		this.dataEsame = dataEsame;
		this.paziente = null;
	}
	
	public Paziente getPaziente() {
		return paziente;
	}
	public void setPaziente(Paziente paziente) {
		this.paziente = paziente;
	}
	public String getCodiceFiscale() {
		return codiceFiscale;
	}
	public String getNomeEsame() {
		return nomeEsame;
	}
	public Date getDataEsame() {
		return dataEsame;
	}
	
	public static Esame read(Scanner in) {
		
		if(!in.hasNextLine()) return null;
		String codiceFiscale = in.nextLine();
		
		if(!in.hasNextLine()) return null;
		String nomeEsame = in.nextLine();
		
		if(!in.hasNextLine()) return null;
		String dataEsameString = in.nextLine();
		Date dataEsame = null;
		try {
			dataEsame = Constant.DATA.parse(dataEsameString);
		} catch(ParseException e) {
			System.err.println("Eccezione Parse catturata.");
			System.err.println("Sara' inserita la data odierna.");
			dataEsame = new Date();
		}
		
		return new Esame(codiceFiscale, nomeEsame, dataEsame);
		
	}

	public void print(PrintStream ps) {
		System.out.println("--- Esame ---");
		ps.println(codiceFiscale);
		ps.println(nomeEsame);
		ps.println(Constant.DATA.format(dataEsame));
		System.out.println("");
	}

	private String codiceFiscale;
	private String nomeEsame;
	private Date dataEsame;
	private Paziente paziente;
	
}
