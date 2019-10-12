package scenari;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.Date;
import java.util.Scanner;

import classi.LaboratorioAnalisi;
import utility.Constant;

public class ScenarioPazientiByNomeEsameAndData {

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
		
		LaboratorioAnalisi laboratorio = new LaboratorioAnalisi(inEsame, inPaziente);
		
		System.out.println("Inserire il nome di un esame");
		String inNomeEsame = in.nextLine();
		
		System.out.println("Inserire una data da cercare");
		String inDataString = in.nextLine();
		Date inData = null;
		try {
			inData = Constant.DATA.parse(inDataString);
		} catch(ParseException e) {
			System.err.println("Eccezione Parse catturata.");
			System.err.println("Sara' inserita la data odierna.");
			inData = new Date();
		}
		
		LaboratorioAnalisi filterLaboratorio = laboratorio.filterPazienteByNomeEsameAndData(inNomeEsame, inData);
		
		if(filterLaboratorio.getHashPazienti().isEmpty())
			System.out.println("Non sono stati trovati dei pazienti corrispondenti ai dati inseriti.");
		else {
			System.out.println("\t--- Pazienti relativi ai dati inseriti ---");
			filterLaboratorio.printPazienti();
		}
			
		in.close();
	}

}
