package dominio_5;

import java.io.Serializable;
import java.util.Vector;

/**
 * Questa classe rappresenta il modello di una SottoCategoria
 */
public class SottoCategoria extends Categoria implements Serializable
{
	private static final long serialVersionUID = 1L;

	private String nomeSottoC;
    
	public static final String DESCRIZIONE_SOTTOCATEGORIA = "Nome sottocategoria: %s\n\t";
	public static final String ELENCO_RISORSE_VUOTO = "Al momento non sono presenti risorse\n";
	public static final String INTESTAZIONE_RISORSE = "Risorse in essa contenute:\n";
    
    /**
     * Metodo costruttore della classe SottoCategoria. A differenza della superclasse, l'attributo elencoRisorse, che 
     * viene ereditato da essa, e' inizializzato nel costruttore
     * 
     * @post: elencoRisorse != null
     * 
     * @param ns : nome della sottocategoria
     */
    public SottoCategoria(String ns)
    {
    	    super();
    	    this.nomeSottoC= ns;
    	    elencoRisorse = new Vector <Risorsa> ();
    }
    
    /**
     * Metodo get che restituisce il nome della sottocategoria
     * @return il nome della sottocategoria
     */
    public String getNome()
    {
    	    return nomeSottoC;
    }
    
    /**
     * Metodo toString() per la creazione di una stringa descrittiva contenente i vari attributi dell'oggetto SottoCategoria
     * 
     * @pre: elencoRisorse != null
     * 
     * @return la stringa descrittiva della sottocategoria
     */
    public String toString()
    {
 	   StringBuffer ris = new StringBuffer();
 	   ris.append(String.format(DESCRIZIONE_SOTTOCATEGORIA, nomeSottoC));
 	   
 	   if(elencoRisorse.size() == 0)
 		   	ris.append(ELENCO_RISORSE_VUOTO);
 	   else
 	   {
		   	ris.append(INTESTAZIONE_RISORSE);

 	 	   for(int i = 0; i < elencoRisorse.size(); i++)
 	 	   {
 	 		   Risorsa r = elencoRisorse.get(i);
 	 		   ris.append("\t\t" + (i+1) + ")"+ r.toString());
 	 	   }
 	 	   
 	   }
 	   
 	   return ris.toString();
    }
    
}