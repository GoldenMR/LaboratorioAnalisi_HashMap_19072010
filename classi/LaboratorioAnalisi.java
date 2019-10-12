package classi;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Scanner;

import exception.EsameNotFoundException;
import exception.PazienteNotFoundException;

public class LaboratorioAnalisi {

	public LaboratorioAnalisi(Scanner inEsame, Scanner inPaziente) {

		arrayEsami = new ArrayList<Esame>();
		Esame esame = Esame.read(inEsame);
		while(esame != null) {
			arrayEsami.add(esame);
			esame = Esame.read(inEsame);
		}

		hashPazienti = new LinkedHashMap<String, Paziente>();
		Paziente paziente = Paziente.read(inPaziente);
		while(paziente != null) {
			hashPazienti.put(paziente.getCodiceFiscale(), paziente);
			paziente = Paziente.read(inPaziente);
		}

		for(Esame esameTemp : arrayEsami) {
			Paziente pazienteTemp = null;
			try {
				pazienteTemp = this.searchPazienteByCodiceFiscale(esameTemp.getCodiceFiscale());
				pazienteTemp.addEsame(esameTemp);
				esameTemp.setPaziente(pazienteTemp);
			} catch(PazienteNotFoundException e) {
				System.err.println("Eccezione PazienteNotFound catturata.");
				System.err.println("Il collegamento con il paziente non e' stato effettuato.");
			}
		}

	}

	private LaboratorioAnalisi(ArrayList<Esame> arrayEsami, LinkedHashMap<String, Paziente> hashPazienti) {
		this.arrayEsami = arrayEsami;
		this.hashPazienti = hashPazienti;
	}

	public ArrayList<Esame> getArrayEsami() {
		return arrayEsami;
	}

	public LinkedHashMap<String, Paziente> getHashPazienti() {
		return hashPazienti;
	}

	public Paziente searchPazienteByCodiceFiscale(String inCodiceFiscale) throws PazienteNotFoundException {
		Paziente pazienteTemp = hashPazienti.get(inCodiceFiscale);
		if(pazienteTemp != null) {
			return pazienteTemp;
		}
		else
			throw new PazienteNotFoundException("Il paziente con codice fiscale " + inCodiceFiscale + "non e' stato trovato.");
	}

	public Esame searchEsameByCodiceFiscale(String inCodiceFiscale) throws EsameNotFoundException {
		int i = 0;
		boolean flag = false;
		Esame esameTemp = null;

		while(!flag && i < arrayEsami.size()) {
			if(arrayEsami.get(i).getCodiceFiscale().equalsIgnoreCase(inCodiceFiscale)) {
				esameTemp = arrayEsami.get(i);
				flag = true;
			}
			else
				i++;
		}

		if(esameTemp != null)
			return esameTemp;
		else
			throw new EsameNotFoundException("L'esame relativo al codice fiscale " + inCodiceFiscale + "non e' stato trovato.");
		
	}

	//	1• data una data, stampi tutti gli esami eseguiti in quella data con la relativa quantità;
	public LaboratorioAnalisi filterEsamiByData(Date inData) {
		ArrayList<Esame> filterArrayEsami = new ArrayList<Esame>();
		LinkedHashMap<String, Paziente> filterHashPazienti = new LinkedHashMap<String, Paziente>();

		for(Esame esameTemp : arrayEsami) {
			if(esameTemp.getDataEsame().equals(inData)) {
				filterArrayEsami.add(esameTemp);
				filterHashPazienti.put(esameTemp.getPaziente().getCodiceFiscale(), esameTemp.getPaziente());
			}
		}

		if(filterArrayEsami.isEmpty())
			return null;
		else
			return new LaboratorioAnalisi(filterArrayEsami, filterHashPazienti);
		
	}

	//	2• dato il nome di un paziente stampi tutte le analisi, con la relativa data, fatte dal paziente;
	public LaboratorioAnalisi filterEsameByNomePaziente(String inNomePaziente) {
		ArrayList<Esame> filterArrayEsami = new ArrayList<Esame>();
		LinkedHashMap<String, Paziente> filterHashPazienti = new LinkedHashMap<String, Paziente>();

		for(Esame esameTemp : arrayEsami) {
			if(esameTemp.getPaziente().getNome().equalsIgnoreCase(inNomePaziente)) {
				filterArrayEsami.add(esameTemp);
				filterHashPazienti.put(esameTemp.getPaziente().getCodiceFiscale(), esameTemp.getPaziente());
			}
		}

		if(filterArrayEsami.isEmpty())
			return null;
		else
			return new LaboratorioAnalisi(filterArrayEsami, filterHashPazienti);
		
	}

	//	3• dato un tipo di analisi ed una data, stampi l’elenco dei pazienti che ha eseguito L’analisi data nella data data.
	public LaboratorioAnalisi filterPazienteByNomeEsameAndData(String inNomeEsame, Date inData) {
		ArrayList <Esame> filterArrayEsami = new ArrayList<Esame>();
		LinkedHashMap<String, Paziente> filterHashPazienti = new LinkedHashMap<String, Paziente>();
		
		for(Esame esameTemp : arrayEsami) {
			if(esameTemp.getNomeEsame().equalsIgnoreCase(inNomeEsame) && esameTemp.getDataEsame().equals(inData)) {
				filterArrayEsami.add(esameTemp);
				filterHashPazienti.put(esameTemp.getPaziente().getCodiceFiscale(), esameTemp.getPaziente());
			}
		}

		if(filterHashPazienti.isEmpty()) {
			return null;
		}
		else
			return new LaboratorioAnalisi(arrayEsami, filterHashPazienti);

	}

	public void printEsami() {
		for(Esame e : arrayEsami)
			e.print(System.out);
		System.out.println("");
	}

	public void printPazienti() {
		for(Paziente p : hashPazienti.values())
			p.print(System.out);
		System.out.println("");
	}

	private ArrayList<Esame> arrayEsami;
	private LinkedHashMap<String, Paziente> hashPazienti;

}
