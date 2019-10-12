package test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Scanner;

import classi.Esame;
import classi.LaboratorioAnalisi;
import classi.Paziente;
import utility.Constant;

public class TestCollegamentoLaboratorioAnalisi {

	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		Scanner inEsame = null;
		Scanner inPaziente = null;
		
		try {
			File fp = new File(Constant.FILE_ESAMI);
			inEsame = new Scanner(fp);
		} catch(FileNotFoundException e) {
			System.err.println("Eccezione FileNotFound catturata.");
			System.err.println("Il file " + Constant.FILE_ESAMI + "non e' stato trovato.");
		}
		
		try {
			File fp = new File(Constant.FILE_PAZIENTI);
			inPaziente = new Scanner(fp);
		} catch(FileNotFoundException e) {
			System.err.println("Eccezione FileNotFound catturata.");
			System.err.println("Il file " + Constant.FILE_PAZIENTI + "non e' stato trovato.");
		}
		
		in.close();
		
		LaboratorioAnalisi laboratorioAnalisi = new LaboratorioAnalisi(inEsame, inPaziente);
		
		System.out.println("\tContenuto del file " + Constant.FILE_ESAMI);
		laboratorioAnalisi.printEsami();
		
		System.out.println("\tContenuto del file " + Constant.FILE_PAZIENTI);
		laboratorioAnalisi.printPazienti();
		
		System.out.println("\tContenuto array esami");
		ArrayList<Esame> arrayEsami = laboratorioAnalisi.getArrayEsami();
		for(Esame e : arrayEsami)
			e.print(System.out);
		
		System.out.println("\tContenuto hash pazienti");
		LinkedHashMap<String, Paziente> hashPazienti = laboratorioAnalisi.getHashPazienti();
		for(Paziente p : hashPazienti.values())
			p.print(System.out);
		
	}

}
