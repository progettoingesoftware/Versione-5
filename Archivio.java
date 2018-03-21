package logica_5;

import java.io.Serializable;
import java.util.Vector;

import dominio_5.Categoria;

/**
 * Questa classe rappresenta il modello di un Archivio
 */
public class Archivio implements Serializable
{  
	private static final long serialVersionUID = 1L;

	private Vector <Categoria> elencoCategorie;
	
	public static final String DESCRIZIONE_ARCHIVIO = "\nL'archivio presenta il seguente contenuto:\n";
	
	/**
	 * Metodo costruttore della classe Archivio
	 * 
	 * @post: elencoCategorie != null
	 */
	public Archivio()
	{
		elencoCategorie = new Vector <Categoria> ();
	}
	
	/**
	 * Metodo get della classe Archivio
	 * @return l'attributo elencoCategorie della classe Archivio
	 */
	public Vector <Categoria> getElencoCategorie()
	{
		return elencoCategorie;
	}
		
	/**
	 * Metodo per l'aggiunta di una categoria all'archivio, viene invocato al momento della creazione della struttura
	 * dell'archivio nel Main
	 * 
	 * @pre: c != null
	 * @post: elencoCategorie.contains(c)
	 * 
	 * @param c: la categoria da aggiungere
	 */
	public void aggiungiCategoria(Categoria c)
	{
		elencoCategorie.add(c);
	}
	
	/**
	 * Metodo per la semplice stampa dell'elenco dei nomi delle categorie presenti in archivio
	 * 
	 * @pre: elencoCategorie != null
	 * 
	 * @return la stringa con l'elenco dei nomi delle categorie dell'archivio
	 */
	public String stampaElencoCategorie()
	{
		StringBuffer ris = new StringBuffer();
		   
		for(int i = 0; i < elencoCategorie.size(); i++)
		{
			   Categoria c = elencoCategorie.get(i);
			   ris.append(i+1 + ")" + c.getNome() + "\n");
		}
		   
	    return ris.toString();
	}
	
	/**
     * Metodo toString() per la creazione di una stringa descrittiva del contenuto dell'archivio
     * 
     * @pre: elencoCategorie != null
     * 
     * @return la stringa descrittiva dell'archivio
     */
	public String toString()
	{
		StringBuffer ris = new StringBuffer();
		ris.append(DESCRIZIONE_ARCHIVIO);
		
		for(int i = 0; i < elencoCategorie.size(); i++)
		{
			Categoria c = elencoCategorie.get(i);
			ris.append(i+1 + ")" + c.toString());
		}
		
		return ris.toString();
	}
	
}
