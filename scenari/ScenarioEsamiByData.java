package scenari;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.Date;
import java.util.Scanner;

import classi.LaboratorioAnalisi;
import exception.EsameNotFoundException;
import utility.Constant;

public class ScenarioEsamiByData {

	public static void main(String[] args) throws EsameNotFoundException {

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

		System.out.println("Inserire la data di un esame da cercare.");
		String inDataString = in.nextLine();
		Date inData = null;
		try {
			inData = Constant.DATA.parse(inDataString);
		}
		catch(ParseException e) {
			System.err.println("Eccezione ParseException lanciata");
			System.err.println("Verra' assegnata la data di sistema");
			inData = new Date();
		}
		
		LaboratorioAnalisi filterLaboratorio = laboratorio.filterEsamiByData(inData);
		
		if(filterLaboratorio.getArrayEsami().isEmpty()) {
			System.out.println("Non sono stati trovati esami che si svolti nella data inserita");
		} else {
			System.out.println("\t--- Esami eseguiti nella data inserita ---");
			filterLaboratorio.printEsami();
		}
		
		in.close();
	}

}
