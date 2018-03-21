package logica_5;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import dominio_5.*;

public class Prestito implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private LocalDate dataDiInizioPrestito;
	private LocalDate dataDiScadenzaPrestito;
	private Categoria categoriaAssociata;
	private Fruitore fruitoreAssociato;
	private Risorsa risorsaInPrestito;
	private boolean prorogaNonEffettuata;
	private LocalDate dataProrogaEffettuata;
	
    public static final String DESCRIZIONE_PRESTITO = "Categoria della risorsa in prestito: %s\nRisorsa presa in prestito:\n\t\t%s\nData inizio prestito: %s\nData scadenza prestito: %s\nProroga gia' effettuata: %s\n";
	
	/**
	 * Metodo costruttore della classe Prestito
	 * 
	 * @pre: (c != null) && (f != null) && (r != null)
	 * @post: dataDiScadenzaPrestito == dataDiInizioPrestito.plusDays(categoriaAssociata.getNumeroMaxGiorniPrestito())
	 * 
	 * @param c: la categoria della risorsa in prestito
	 * @param f: il fruitore che ha in prestito la risorsa
	 * @param r: la risorsa in prestito
	 */
	public Prestito(Categoria c, Fruitore f, Risorsa r)
	{
		this.dataDiInizioPrestito = LocalDate.now();
		this.categoriaAssociata = c;
		this.dataDiScadenzaPrestito = dataDiInizioPrestito.plusDays(categoriaAssociata.getNumeroMaxGiorniPrestito());
		this.fruitoreAssociato = f;
		this.risorsaInPrestito = r;
		this.prorogaNonEffettuata = true;
	}
	
	/**
	 * Metodi get per il ritorno dei vari attributi della classe Prestito
	 * @return i vari attributi della classe Prestito
	 */
	public LocalDate getDataDiInizioPrestito()
	{
		return dataDiInizioPrestito;
	}
	
	public LocalDate getDataDiScadenzaPrestito()
	{
		return dataDiScadenzaPrestito;
	}
	
	public Categoria getCategoriaAssociata()
	{
		return categoriaAssociata;
	}
	
	public Fruitore getFruitoreAssociato()
	{
		return fruitoreAssociato;
	}
	
	public Risorsa getRisorsaInPrestito()
	{
		return risorsaInPrestito;
	}
	
	public boolean getProrogaNonEffettuata()
	{
		return prorogaNonEffettuata;
	}
	
	public LocalDate getDataProrogaEffettuata()
	{
		return dataProrogaEffettuata;
	}
	
	/**
	 * Metodi set per modificare i vari attributi della classe Prestito
	 */
	public void setProrogaNonEffettuata(LocalDate ld)
	{
		prorogaNonEffettuata = false;
		this.dataProrogaEffettuata = ld;
	}
	
	public void setDataDiScadenzaPrestito(LocalDate nuovads)
	{
		dataDiScadenzaPrestito = nuovads;
	}
	
	/**
     * Metodo che verifica se le condizioni per effettuare la proroga di un prestito siano soddisfatte o no
     * @return true se le condizioni per effettuare la proroga del prestito sono soddisfatte
     */
   public boolean prorogaPrestito()
   {
   	   /**
  	 	* Il primo if verifica che non sia già stata effettuata la proroga per il prestito
  	 	* Il secondo if verifica che la proroga sia richiesta nel corretto intervallo di tempo
  	 	*/
       if(prorogaNonEffettuata)
  	    {
       	    if((LocalDate.now().isBefore(dataDiScadenzaPrestito)))
  	 		{
      	 		LocalDate ld2 = dataDiScadenzaPrestito.minusDays(categoriaAssociata.getNumeroGiorniRichiestaProroga());
  	 			
  	 			if((LocalDate.now().equals(ld2)) || (LocalDate.now().isAfter(ld2)))
      	 		{
  	 				setDataDiScadenzaPrestito(dataDiScadenzaPrestito.plusDays(categoriaAssociata.getNumeroMaxGiorniProroga()));
      	 			setProrogaNonEffettuata(LocalDate.now());
  	 				return true;
  	 				
      	 		}
      	    }
  	 	}
	    return false;
   }


	/**
     * Metodo toString() per la creazione di una stringa descrittiva di un prestito
     * @return la stringa descrittiva del prestito
     */
   public String toString()
   {
       StringBuffer ris = new StringBuffer();
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Fruitore.FORMATO_DATA);
 	
	    String perProroga = "no";
 	    if(!prorogaNonEffettuata)
 		      perProroga = "si";
 	
 	    ris.append(String.format(DESCRIZIONE_PRESTITO, categoriaAssociata.getNome(), risorsaInPrestito.toString(), dataDiInizioPrestito.format(formatter), dataDiScadenzaPrestito.format(formatter), perProroga));
       return ris.toString();

   } 
    
}
