package logica_5;

import java.io.Serializable;
import java.util.Vector;

import dominio_5.*;

/**
 * Questa classe rappresenta il modello di un Utente
 */
public class Utente implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private String nome;
    private String cognome;
    private String username;
    private String password;
    
    /**
     * Metodo costruttore della classe Utente
     * @param n : nome dell'utente
     * @param c : cognome dell'utente
     * @param u : username dell'utente
     * @param p : password dell'utente
     */
    public Utente(String n, String c, String u, String p)
    {
    	this.nome = n;
        this.cognome = c;
        this.username = u;
        this.password = p;
    }
    
    /**
     * Metodi get per il ritorno dei vari attributi della classe Utente
     * @return i vari attributi della classe Utente
     */
    public String getNome()
    {
   	     return nome;
    }
    
    public String getCognome()
    {
   	     return cognome;
    }
    
    public String getUsername()
    {
   	     return username;
    }
    
    public String getPassword()
    {
   	     return password;
    }
    
    /**
     * Metodo che permette all'utente di effettuare la ricerca di una risorsa in base alla categoria
     * 
     * @pre: (c != null) && (o != null)
     * 
     * @param c: la categoria delle risorse da cercare
     * @param o: il generico oggetto che rappresenta quello che l'utente ha digitato con lo scopo di cercarlo nella categoria
     * @param r: stringa che specifica in base a quale parametro avviene la ricerca
     * @return il vettore con all'interno le risorse che hanno soddisfatto la ricerca
     */
    public Vector <Risorsa> ricercaRisorse(Categoria c, Object o, String r)
    {
       	 return c.ricercaRisorse(o, r);
    }
    
    /**
     * Metodo che permette all'utente di sapere se una risorsa è diponibile o no
     *
     * @pre: (ap != null) && (r != null)
     * 
     * @param ap: l'archivio dei prestiti
     * @param r: la risorsa di cui valutare la disponibilità
     * @return true se la risorsa r è disponibile
     */
    public boolean valutazioneDisponibilita(ArchivioPrestiti ap, Risorsa r)
    {
         return	ap.controlloDisponibilitaRisorsa(r);
    }
    
}
