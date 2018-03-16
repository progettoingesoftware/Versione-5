package interazione_5;

import java.io.Serializable;
import java.util.Vector;

import dominio_5.*;
import logica_5.*;

/**
 * Qusta classe contiene dei metodi per la creazione della struttura preimpostata di alcuni aspetti del sistema
 */
public class StrutturaSistema implements Serializable
{
	private static final long serialVersionUID = 1L;

	/**
	 * Metodo per la creazione e l'aggiunta di 5 operatori preimpostati all'elenco degli operatori presente in AnagraficaOperatori
	 * 
	 * Pre : ao.elenco != null
	 * 
	 * @param ao : l'istanza della classe AnagraficaOperatore a cui aggiungere gli operatori
	 */
	public static void aggiuntaOperatoriPreimpostati(AnagraficaOperatori ao)
	{
		Operatore primo = new Operatore("Stefano", "Metelli", "ste", "161095");
		Operatore secondo = new Operatore("Alba", "Pasini", "sum56", "33alb33");
		Operatore terzo = new Operatore("Marco", "Bellini", "mark4", "starwars2");
		Operatore quarto = new Operatore("Fabio", "Piccinelli", "picci", "fighter118");
		Operatore quinto = new Operatore("Ottavia", "Lauretti", "oct4565", "ppla210");
		
		ao.getElenco().addElement(primo);
		ao.getElenco().addElement(secondo);
		ao.getElenco().addElement(terzo);
		ao.getElenco().addElement(quarto);
		ao.getElenco().addElement(quinto);
	}
	
	/**
	 * Metodo per la creazione della struttura base dell'archivio in merito alla categoria Libro
	 * 
	 * Pre: arc != null
	 * 
	 * @param arc: l'istanza della classe Archivio di cui creare la struttura
	 */
	public static void creazioneStrutturaArchivioLibri(Archivio arc)
	{
		final int DURATA_PRESTITO_L = 30;
		final int DURATA_PROROGA_L = 30;
		final int RICHIESTA_PROROGA_L = 3;
		final int MAX_PRESTITI_L = 3;
		
		Categoria c1 = new Categoria("Libri", DURATA_PRESTITO_L, DURATA_PROROGA_L, RICHIESTA_PROROGA_L, MAX_PRESTITI_L);
		arc.aggiungiCategoria(c1);
	    c1.inizializzaElencoSottoCategorie();
	    
	    SottoCategoria s1 = new SottoCategoria("Didattica");
	    SottoCategoria s2 = new SottoCategoria("Classici");
	    SottoCategoria s3 = new SottoCategoria("Fantasy");
	    SottoCategoria s4 = new SottoCategoria("Per ragazzi");
	    SottoCategoria s5 = new SottoCategoria("Gialli");
	    c1.aggiungiSottoCategoria(s1);
	    c1.aggiungiSottoCategoria(s2);
	    c1.aggiungiSottoCategoria(s3);
	    c1.aggiungiSottoCategoria(s4);
	    c1.aggiungiSottoCategoria(s5);
	    
	    Vector <String> aut1 = new Vector <String> ();
	    aut1.add("Antoine de Saint_Exupery");
	    Vector <String> aut2 = new Vector <String> ();
	    aut2.add("J.R.R. Tolkien");
	    Vector <String> aut3 = new Vector <String> ();
	    aut3.add("George Orwell");
	    Vector <String> aut4 = new Vector <String> ();
	    aut4.add("E.Gamma");
	    aut4.add("R.Helm");
	    aut4.add("R.Johnson");
	    aut4.add("J.Vlissides");
	    
	    Libro l1 = new Libro("Il piccolo principe", 10, "Per ragazzi", 2015, "italiano", aut1, 137, "Newton Compton");
	    Libro l2 = new Libro("Il signore degli anelli", 5, "Fantasy",2017, "italiano", aut2, 1264, "Bompiani");
	    Libro l3 = new Libro("Animal Farm", 8, "Classici", 2008, "english", aut3, 112, "Penguin Books");
	    Libro l4 = new Libro("Design Patterns", 1, "Didattica", 2002, "italiano", aut4, 395, "Pearson");
	    s1.aggiungiRisorsa(l4);
	    s2.aggiungiRisorsa(l3);
	    s3.aggiungiRisorsa(l2);
	    s4.aggiungiRisorsa(l1);
	}
	
	/**
	 * Metodo per la creazione della struttura dell'archivio in merito alla categoria Film
	 * 
	 * Pre: arc != null
	 * 
	 * @param arc: l'istanza della classe Archivio di cui creare la struttura
	 */
	public static void creazioneStrutturaArchivioFilm(Archivio arc)
	{
		final int DURATA_PRESTITO_F = 15;
		final int DURATA_PROROGA_F = 5;
		final int RICHIESTA_PROROGA_F = 2;
		final int MAX_PRESTITI_F = 2;
		
		Categoria c2 = new Categoria("Film", DURATA_PRESTITO_F, DURATA_PROROGA_F, RICHIESTA_PROROGA_F, MAX_PRESTITI_F);
		arc.aggiungiCategoria(c2);
	    c2.inizializzaElencoSottoCategorie();
		
	    SottoCategoria s6 = new SottoCategoria("Horror");
		SottoCategoria s7 = new SottoCategoria("Commedia");
		SottoCategoria s8 = new SottoCategoria("Animazione");
		SottoCategoria s9 = new SottoCategoria("Drammatico");
		SottoCategoria s10 = new SottoCategoria("Fantascienza");   
		c2.aggiungiSottoCategoria(s6);
		c2.aggiungiSottoCategoria(s7);
		c2.aggiungiSottoCategoria(s8);
		c2.aggiungiSottoCategoria(s9);
		c2.aggiungiSottoCategoria(s10);
		    
		Vector <String> att1 = new Vector <String> ();
		att1.add("Jack Nicholson");
		att1.add("Shelley Duvall");
		att1.add("Danny Lloyd");
		att1.add("Scatman Crothers");
		Vector <String> att2 = new Vector <String> ();
		att2.add("Ben Burtt");
		att2.add("Elissa Knight");
		att2.add("Jeff Garlin");
		Vector <String> att3 = new Vector <String> ();
		att3.add("Harrison Ford");
	    att3.add("Rutger Hauer");
		att3.add("Sean Young");
		att3.add("Edward James Olmos");
		    
		Film f1 = new Film("Shining", 2, "Horror", 1980, "italiano", "Stanley Kubrick", att1, 145);
		Film f2 = new Film("WALL-E", 3, "Animazione", 2008, "italiano", "Andrew Stanton", att2, 100);
		Film f3 = new Film("Blade Runner", 2, "Fantascienza", 1982, "italiano", "Ridley Scott", att3, 120);
		s6.aggiungiRisorsa(f1);
		s7.aggiungiRisorsa(f2);
		s8.aggiungiRisorsa(f2);
		s10.aggiungiRisorsa(f3);
	}
	
}
