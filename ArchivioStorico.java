package logica_5;

import java.io.Serializable;

import java.util.Vector;

import dominio_5.Risorsa;
import utility.Costanti;

/**
 * Questa classe rappresenta il modello di un ArchivioStorico
 */
public class ArchivioStorico implements Serializable
{
    private static final long serialVersionUID = 1L;
	
	private AnagraficaFruitori iscrizioniFruitoriStoriche;
	private AnagraficaFruitori rinnovoIscrizioniFruitoriStorici;
	private AnagraficaFruitori decadenzeFruitoriStoriche;
    private ArchivioPrestiti prestitiStorici;
    private ArchivioPrestiti prestitiConProrogheStoriche;
	private Vector <Risorsa> elencoRisorseRimosse;
	
	/**
	 * Metodo costruttore della classe ArchivioStorico
	 * 
	 * @post: (iscrizioniFruitoriStoriche != null) && (rinnovoIscrizioniFruitoriStorici != null)
	 *        && (decadenzeFruitoriStoriche != null) && (prestitiStorici != null) 
	 *        && (prestitiConProrogheStoriche != null) && (elencoRisorseRimosse != null)
	 */
	public ArchivioStorico()
	{
		iscrizioniFruitoriStoriche = new AnagraficaFruitori();
		rinnovoIscrizioniFruitoriStorici = new AnagraficaFruitori();
		decadenzeFruitoriStoriche = new AnagraficaFruitori();
 	   	prestitiStorici = new ArchivioPrestiti();
 	    prestitiConProrogheStoriche = new ArchivioPrestiti();
		elencoRisorseRimosse = new Vector <Risorsa> ();
	}
	
	/**
	 * Metodi get per il ritorno dei vari attributi della classe ArchivioStorico
	 * @return i vari attributi della classe ArchivioStorico
	 */
    public AnagraficaFruitori getIscrizioniFruitoriStoriche()
    {
      	return iscrizioniFruitoriStoriche;
    }
    
    public AnagraficaFruitori getRinnovoIscrizioniFruitoriStorici()
    {
    	return rinnovoIscrizioniFruitoriStorici;
    }
    
    public AnagraficaFruitori getDecadenzeFruitoriStoriche()
    {
    	return decadenzeFruitoriStoriche;
    }
	
    public ArchivioPrestiti getPrestitiStorici()
    {
        return prestitiStorici;
    }
    
    public ArchivioPrestiti getPrestitiConProrogheStoriche()
    {
        return prestitiConProrogheStoriche;
    }
    
    public Vector<Risorsa> getElencoRisorseRimosse()
    {
      	return elencoRisorseRimosse;
    }
	
    /**
     * Metodo che permette l'aggiunta di una risorsa al vettore elencoRisorseRimosse
     * 
     * @pre: r != null
     * @post: elencoRisorseRimosse.contains(r)
     * 
     * @param r: la risorsa da aggiungere
     */
	public void aggiungiRisorsaRimossa(Risorsa r)
	{
		elencoRisorseRimosse.add(r);
	}
	
	/**
	 * Metodo che conta il numero di prestiti registrati durante l'anno a
	 * 
	 * @pre: prestitiStorici != null
	 * 
	 * @param a: l'anno di cui si vuole effettuare il conteggio dei prestiti
	 * @return il numero di prestiti che sono stati registrati nell'anno a
	 */
    public int numeroPrestitiPerAnno(int a)
    {
    	   int num = 0;
    	   
    	   for(int i = 0; i < prestitiStorici.getElencoPrestiti().size(); i++)
    	   {
    		   Prestito p = prestitiStorici.getElencoPrestiti().get(i);
    		   
    		   if(p.getDataDiInizioPrestito().getYear() == a)
    			    num++;
    	   }
    	   
    	   return num;
    }
    
    /**
	 * Metodo che conta il numero di proroghe registrate durante l'anno a
	 * 
	 * @pre: prestitiConProrogheStoriche != null
	 * 
	 * @param a: l'anno di cui si vuole effettuare il conteggio delle proroghe
	 * @return il numero di proroghe che sono state registrate nell'anno a
	 */
    public int numeroProroghePerAnno(int a)
    {
    	   int num = 0;
    	   
    	   for(int i = 0; i < prestitiConProrogheStoriche.getElencoPrestiti().size(); i++)
    	   {
    		   Prestito p = prestitiConProrogheStoriche.getElencoPrestiti().get(i);
    		   
    		   if(!(p.getProrogaNonEffettuata()))
    		   {
    			   if(p.getDataProrogaEffettuata().getYear() == a)
    				     num++;	   
    		   }
    	   }
    	   
    	   return num;
    }
	
    /**
     * Metodo che restituisce il titolo della risorsa che è stata oggetto del
     * maggior numero di prestiti durante l'anno a
     * 
     * @pre: prestitiStorici != null
     * 
     * @param a: l'anno di cui si vuole effettuare il conteggio
     * @return il titolo della risorsa con più prestiti durante l'anno a 
     */
	public String getRisorsaPiuRichiesta(int a)
	{
		Risorsa ris = null;
		int max = 0;
		Vector <Prestito> elencoPrestiti = new Vector <Prestito> ();
		
		for(int i = 0; i < prestitiStorici.getElencoPrestiti().size(); i++)
		{
			Prestito p = prestitiStorici.getElencoPrestiti().get(i);
			
			if(p.getDataDiInizioPrestito().getYear() == a)
			{
				elencoPrestiti.add(p);
			}
		}
			
		if(elencoPrestiti.size() != Costanti.VUOTO)
		{
		   for(int j = 0; j < elencoPrestiti.size(); j++) 
		   { 
			 int num = 0;
			 Risorsa r1 = elencoPrestiti.get(j).getRisorsaInPrestito();
			 Risorsa r2 = null;
					
			 for(int k = j+1; k < elencoPrestiti.size(); k++)
			 {
				r2 = elencoPrestiti.get(k).getRisorsaInPrestito();
				if(r2.equals(r1))
					  num++;
			 }
			
			 if(num > max)
			 {
			    ris = r1;
			    max = num;
			 }
			 
			 if(max == 0)
			 {
			    ris = r1;
			 }
			 
		   }
           return ris.getTitolo();
		}
		else
			return "";
	}
	
	/**
     * Metodo che dato un fruitore f e un anno a conta il numero dei prestiti effettuati
     * dal fruitore f durante l'anno a
     * 
     * @pre: (prestitiStorici != null) && (f != null)
     * 
     * @param f: il fruitore di cui si vuole effettuare il conteggio
     * @param a: l'anno di cui si vuole effettuare il conteggio
     * @return il numero di prestiti effettuati dal fruitore f nell'anno a 
     */
	public int numeroPrestitiPerFruitorePerAnno(Fruitore f, int a)
	{
	    	int num = 0;
	    	   
	    	for(int i = 0; i < prestitiStorici.getElencoPrestiti().size(); i++)
	    	{
	    		 Prestito p = prestitiStorici.getElencoPrestiti().get(i);
	    		   
	    		 if((p.getFruitoreAssociato().equals(f)) && p.getDataDiInizioPrestito().getYear() == a)
	    		       num++;
	    	}
	    	   
	    	return num;
	 }
	
}