package scenari;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import classi.LaboratorioAnalisi;
import utility.Constant;

public class ScenarioEsamiByNomePaziente {

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
		
		System.out.println("Inserire il nome di un paziente");
		String inNomePaziente = in.nextLine();
		
		LaboratorioAnalisi filterLaboratorioByNome = laboratorio.filterEsameByNomePaziente(inNomePaziente);
		
		if(filterLaboratorioByNome.getArrayEsami().isEmpty()) {
			System.out.println("Non sono stati trovati pazienti per il nome inserito.");
		} else {
			System.out.println("\t--- Esami del paziente inserito ---");
			filterLaboratorioByNome.printEsami();
		}
		
		in.close();
	}

}
