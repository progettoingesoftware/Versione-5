package logica_5;

import java.io.Serializable;
import java.util.*;

/**
 * Questa classe rappresenta il modello di una Anagrafica
 */
public class Anagrafica implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	protected Vector <Utente> elenco;
    
    /**
     * Metodo costruttore della classe Anagrafica
     */
    public Anagrafica()
    {
    	   elenco = new Vector <Utente> ();
    }
    
    public Vector <Utente> getElenco()
    {
    	return elenco;
    }
    
    /**
     * Metodo che restituisce l'utente avente lo username e la password uguali a quelli passati come parametri 
     * altrimenti null
     * @param u : username dell'utente
     * @param p : password dell'utente
     * @return l'utente con username u e password p altrimenti null
     */
    public Utente getUtente(String u, String p)
    {
      	for(int i = 0; i < elenco.size(); i++)
	    {
	    	  Utente ut = elenco.get(i);
	    	  
	    	  if((ut.getUsername()).equals(u) && (ut.getPassword().equals(p)))
	    			   return ut;
	    }
	    
	    return null;
    }
    
    /**
     * Metodo che verifica se l'utente, identificato tramite lo username e la password, sia presente nell'elenco
     * 
     * @pre : elenco != null
     * 
     * @param u : username dell'utente
     * @param p : password dell'utente
     * @return boolean : true se l'utente e' gia' presente nell'elenco
     */
    public boolean accedi(String u, String p)
    {
    	    for(int i = 0; i < elenco.size(); i++)
    	    {
    	    	  Utente ut = elenco.get(i);
    	    	  
    	    	  if((ut.getUsername()).equals(u) && (ut.getPassword().equals(p)))
    	    			   return true;
    	    }
    	    
    	    return false;
    }
    
}
