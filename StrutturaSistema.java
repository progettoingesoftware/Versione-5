package interazione_5;

import java.io.Serializable;
import java.util.Vector;

import dominio_5.*;
import logica_5.*;

/**
 * Questa classe contiene dei metodi per la creazione della struttura preimpostata di alcuni aspetti del sistema
 */
public class StrutturaSistema implements Serializable
{
	private static final long serialVersionUID = 1L;

	/**
	 * Metodo per la creazione e l'aggiunta di 5 operatori preimpostati all'elenco degli operatori presente in AnagraficaOperatori
	 * 
	 * @pre : ao != null
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
	 * @pre: arc != null
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
	    SottoCategoria s6 = new SottoCategoria("Romanzi");
	    c1.aggiungiSottoCategoria(s1);
	    c1.aggiungiSottoCategoria(s2);
	    c1.aggiungiSottoCategoria(s3);
	    c1.aggiungiSottoCategoria(s4);
	    c1.aggiungiSottoCategoria(s5);
	    c1.aggiungiSottoCategoria(s6);
	    
	    Vector <String> aut1 = new Vector <String> ();
	    aut1.add("Antoine de Saint_Exupéry");
	    Vector <String> aut2 = new Vector <String> ();
	    aut2.add("J.R.R. Tolkien");
	    Vector <String> aut3 = new Vector <String> ();
	    aut3.add("George Orwell");
	    Vector <String> aut4 = new Vector <String> ();
	    aut4.add("E.Gamma");
	    aut4.add("R.Helm");
	    aut4.add("R.Johnson");
	    aut4.add("J.Vlissides");
	    Vector <String> aut5 = new Vector <String> ();
	    aut5.add("Cay Horstmann");
	    Vector <String> aut6 = new Vector <String> ();
	    aut6.add("Arthur Conan Doyle");
	    
	    Libro l1 = new Libro("Il piccolo principe", 10, "Per ragazzi", 2015, "italiano", aut1, 137, "Newton Compton");
	    Libro l2 = new Libro("Il signore degli anelli", 5, "Fantasy",2017, "italiano", aut2, 1264, "Bompiani");
	    Libro l3 = new Libro("Animal Farm", 8, "Classici", 2008, "english", aut3, 112, "Penguin Books");
	    Libro l4 = new Libro("Design Patterns", 1, "Didattica", 2002, "italiano", aut4, 395, "Pearson");
	    Libro l5 = new Libro("Concetti di informatica e fondamenti di Java", 2, "Didattica", 2016, "italiano", aut5, 806, "Apogeo Education");
	    Libro l6 = new Libro("Le avventure di Sherlock Holmes", 2, "Gialli", 2015, "italiano", aut6, 278, "Newton Compton");
	    s1.aggiungiRisorsa(l4);
	    s1.aggiungiRisorsa(l5);
	    s2.aggiungiRisorsa(l3);
	    s3.aggiungiRisorsa(l2);
	    s4.aggiungiRisorsa(l1);
	    s5.aggiungiRisorsa(l6);
	}
	
	/**
	 * Metodo per la creazione della struttura dell'archivio in merito alla categoria Film
	 * 
	 * @pre: arc != null
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
		SottoCategoria s11 = new SottoCategoria("Thriller");   
		c2.aggiungiSottoCategoria(s6);
		c2.aggiungiSottoCategoria(s7);
		c2.aggiungiSottoCategoria(s8);
		c2.aggiungiSottoCategoria(s9);
		c2.aggiungiSottoCategoria(s10);
		c2.aggiungiSottoCategoria(s11);
		    
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
		Vector <String> att4 = new Vector <String> ();
		att4.add("Bill Skarsgard");
		att4.addElement("Jaeden Lieberher");
		Vector <String> att5 = new Vector <String> ();
		att5.add("Marlon Brando");
		att5.addElement("Al Pacino");
		Vector <String> att6 = new Vector <String> ();
		att6.add("Matthew McConaughey");
		att6.addElement("Anne Hathaway");
		    
		Film f1 = new Film("Shining", 2, "Horror", 1980, "italiano", "Stanley Kubrick", att1, 145);
		Film f2 = new Film("WALL-E", 3, "Animazione", 2008, "italiano", "Andrew Stanton", att2, 100);
		Film f3 = new Film("Blade Runner", 2, "Fantascienza", 1982, "italiano", "Ridley Scott", att3, 120);
		Film f4 = new Film("It", 3, "Horror", 2017, "italiano", "Andy Muschietti", att4, 130);
		Film f5 = new Film("Il padrino", 2, "Drammatico", 1972, "italiano", "Francis Ford Coppola", att5, 180);	
		Film f6 = new Film("Interstellar", 2, "Fantascienza", 2014, "italiano", "Christopher Nolan", att6, 150);	
		s6.aggiungiRisorsa(f1);
		s6.aggiungiRisorsa(f4);
		s8.aggiungiRisorsa(f2);
		s9.aggiungiRisorsa(f5);
		s10.aggiungiRisorsa(f3);
		s10.aggiungiRisorsa(f6);
	}
	
}