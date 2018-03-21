package logica_5;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Vector;

/**
 * Questa classe rappresenta il modello di un Fruitore
 */
public class Fruitore extends Utente implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private LocalDate dataDiNascita;             
    private LocalDate dataDiIscrizione;		
    private LocalDate dataDiScadenza;
      
    public static final String DESCRIZIONE_FRUITORE = "\nNome: %s\nCognome: %s\nUsername: %s\nPassword: %s\nData di nascita: %s\nData di iscrizione: %s\nData di scadenza: %s\n";
    public static final int TERMINE_SCADENZA = 5;
    public static final int DIECI_GIORNI = 10;
    public static final String FORMATO_DATA = "dd/MM/yyyy";
    public static final String NO_PRESTITI_ATTIVI = "Al momento non ci sono prestiti\n";
    public static final String PRESTITI_ATTIVI = "Elenco dei prestiti attivi:\n";
    
    /**
     * Metodo costruttore della classe Fruitore
     * 
     * @post : dataDiScadenza == dataDiIscrizione.plusYears(TERMINE_SCADENZA)
     * 
     * @param n : nome del fruitore
     * @param c : cognome del fruitore
     * @param an : anno di nascita del fruitore
	 * @param mn : mese di nascita del fruitore (in numero)
	 * @param gn : giorno di nascita del fruitore (in numero)
     * @param u : username del fruitore
     * @param p : password del fruitore
     */     
    public Fruitore(String n, String c, int an, int mn, int gn, String u, String p)
    {
   	     super(n, c, u, p);
   	     this.dataDiNascita = LocalDate.of(an, mn, gn);
   	     
   	     /**
   	      * L'attributo dataDiIscrizione assume il valore restituito dal metodo now() della classe LocalDate;
   	      * tale valore e' costituito dalla data in cui viene effettuata tale invocazione ottenuta attraverso l'orologio di sistema
   	      */
   	     this.dataDiIscrizione = LocalDate.now();
   	     
   	     /**
   	      * L'attributo dataDiScadenza assume il valore indicato dalla data di iscrizione incrementata di un periodo di 5 anni
   	      */
   	     this.dataDiScadenza = dataDiIscrizione.plusYears(TERMINE_SCADENZA);
    }
    
    /**
     * Metodi get per il ritorno dei vari attributi della classe Fruitore
     * @return i vari attributi della classe Fruitore
     */
    public LocalDate getDataDiNascita()
    {
   	     return dataDiNascita;
    }
   
    public LocalDate getDataDiIscrizione()
    {
   	     return dataDiIscrizione;
    }
    
    public LocalDate getDataDiScadenza()
    {
   	     return dataDiScadenza;
    }
    
    /**
     * Metodo set per la modifica della data di scadenza del servizio
     * 
     * @pre : nuovads.isAfter(dataDiScadenza)
     * @post : dataDiScadenza == nuovads.plusYears(TERMINE_SCADENZA)
     * 
     * @param nuovads : data di scadenza aggiornata
     */
    public void setDataDiScadenza(LocalDate nuovads)
    {
   	 	dataDiScadenza = nuovads;
    }
    
    /**
     * Metodo che consente al fruitore di rinnovare l'iscrizione. La verifica della data avviene mediante due if concatenati:
     * il primo verifica che la data corrente preceda quella di scadenza indicata per lo specifico fruitore;
	 * il secondo verifica che la data corrente succeda quella di scadenza (indicata per lo specifico fruitore) diminuita di un periodo di 10 giorni
     * @param af: l'anagrafica fruitore
     * @return true se il fruitore puo' effettuare l'iscrizione
     */
    public boolean rinnovaIscrizione()
    {
      	if((LocalDate.now().isBefore(dataDiScadenza))) 
		{
			LocalDate ld = dataDiScadenza.minusDays(DIECI_GIORNI);
			
			if((LocalDate.now().equals(ld)) || (LocalDate.now().isAfter(ld))) 
			{
				setDataDiScadenza(dataDiScadenza.plusYears(Fruitore.TERMINE_SCADENZA));
				return true;
			}
		}

		return false;
    }
    
    /**
     * Metodo che ritorna una stringa contenente i prestiti relativi al fruitore
     * 
     * @pre: ap != null
     * 
     * @param ap: l'archivio dei prestiti
     * @return la stringa dei prestiti relativi al fruitore
     */
    public String visualizzaPrestitiInCorso(ArchivioPrestiti ap)
    {
    	    Vector <Prestito> ris = new Vector <Prestito> ();
    	    StringBuffer r = new StringBuffer();
    	    ris = ap.getPrestiti(this.getUsername());
    	    
    	    if(ris.size() == 0)
    	    	    r.append(NO_PRESTITI_ATTIVI);
    	    else
    	    {
    	        r.append(PRESTITI_ATTIVI);
    	    	    for(int i = 0; i < ris.size(); i++)
    	    	    {
    	    		   Prestito p = ris.get(i);
    	   		   r.append(i+1 + ")" + p.toString() +"\n");
    	    	    }
    	    }
    	    
      	return r.toString();
    }
    
    /**
     * Metodo che permette al fruitore di registrare un nuovo prestito
     * 
     * @pre: (ap != null) && (p != null)
     * @post: p in ap
     * 
     * @param ap: l'archivio prestiti in cui registrare il nuovo prestito
     * @param p: il nuovo prestito
     */
    public void registraNuovoPrestito(ArchivioPrestiti ap, Prestito p)
    {
      	ap.aggiungiPrestito(p);
    }
    
    /**
    * Metodo che permette al fruitore di registrare la proroga di un prestito
    * 
    * @pre: p != null
    *
    * @param p: il prestito di cui registrare la proroga
    */
    public boolean registraProrogaPrestito(Prestito p)
    {
    	    return p.prorogaPrestito();
    }
    
    /**
     * Metodo toString() per la creazione di una stringa descrittiva contenente i vari attributi dell'oggetto Fruitore
     * @return la stringa descrittiva
     */
    public String toString()
    {
      	StringBuffer ris = new StringBuffer();
      	DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMATO_DATA);
    	    
      	ris.append(String.format(DESCRIZIONE_FRUITORE, getNome(), getCognome(), getUsername(), getPassword(), dataDiNascita.format(formatter), dataDiIscrizione.format(formatter), dataDiScadenza.format(formatter)));
        return ris.toString();
    } 
    
    
}
