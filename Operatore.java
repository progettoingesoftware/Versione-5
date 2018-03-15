package it.ing.sw.v4.p1;

import java.io.Serializable;

import it.ing.sw.Costanti;
import it.ing.sw.InputDati;
import it.ing.sw.v4.p2.Archivio;
import it.ing.sw.v4.p2.Categoria;
import it.ing.sw.v4.p2.Risorsa;

/**
 * Questa classe rappresenta il modello di un Operatore
 */
public class Operatore extends Utente implements Serializable
{
	private static final long serialVersionUID = 1L;

	/**
     * Metodo costruttore della classe Operatore
     * @param n : nome dell'operatore
     * @param c : cognome dell'operatore
     * @param u : username dell'operatore
     * @param p : password dell'operatore
     */
    public Operatore(String n, String c, String u, String p)
    {
   	     super(n, c, u, p);
    }
    
    /**
     * Metodo che permette la visualizzazione dell'elenco di Fruitori con relative caratteristiche
     * 
     * Pre : e != null
     * 
     * @param e : l'oggetto indicato dove e' depositato il Vector di Fruitori su cui poter invocare il metodo toString()
     * @return la stringa descrittiva dell'anagrafica dei fruitori
     */
    public String visualizzaElencoFruitori(AnagraficaFruitori e)
    {
   	     return e.toString();
    }
    
    /**
     * Metodo che permette l'aggiunta di una risorsa ad una (sotto)categoria dell'archivio
     * 
     * Pre: (r != null) && (s != null) && !(r in s) 
     * Post: r in s
     * 
     * @param r: la risorsa da aggiungere
     * @param s: la (sotto)categoria a cui aggiungere la risorsa
     */
    public void aggiungiRisorsaCategoria(Risorsa r, Categoria s)
    {
    	    s.aggiungiRisorsa(r);
    }
    
    /**
     * Metodo che permette la rimozione di una risorsa da una (sotto)categoria dell'archivio
     * 
     * Pre: (r != null) && (s != null) && (r in s)
     * Post: !(r in s)
     * 
     * @param r: la risorsa da rimuovere
     * @param s: la (sotto)categoria da cui rimuovere la risorsa
     */
    public void rimuoviRisorsaCategoria(Risorsa r, Categoria s)
    {
    	    s.rimuoviRisorsa(r);
    }
    
    /**
     * Metodo che consente la visualizzazione del contenuto dell'archivio
     * 
     * Pre: a != null
     * 
     * @param a: l'archivio da visualizzare
     * @return la stringa descrittiva del contenuto dell'archivio
     */
    public String visualizzaArchivio(Archivio a)
    {
    	     return a.toString();
    }
    
    public int visualizzaPrestitiPerAnno(ArchivioStorico as, int anno)
    {
    	     return as.numeroPrestitiPerAnno(anno);
    }
    
    public int visualizzaProroghePerAnno(ArchivioStorico as, int anno)
    {
	     	return as.numeroProroghePerAnno(anno);
    }
    
    public String visualizzaRisorsaPiuRichiesta(ArchivioStorico as, int anno)
    {
    	
    	     return as.getRisorsaPiuRichiesta(anno);
    }
    
    public int visualizzaNumeroPrestitiPerFruitorePerAnno(ArchivioStorico as, Fruitore f, int anno)
    {
    	     return as.numeroPrestitiPerFruitorePerAnno(f, a);
    }
    
}