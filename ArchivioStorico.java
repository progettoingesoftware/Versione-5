package logica_5;

import java.io.Serializable;
import java.util.Vector;

import dominio_5.Risorsa;
import utility.Costanti;

public class ArchivioStorico implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private AnagraficaFruitori iscrizioniFruitoriStoriche;
	private AnagraficaFruitori rinnovoIscrizioniFruitoriStorici;
	private AnagraficaFruitori decadenzeFruitoriStoriche;
   
	private ArchivioPrestiti prestitiStorici;
    private ArchivioPrestiti prestitiConProrogheStoriche;
	
	private Vector <Risorsa> elencoRisorseRimosse;
	
	public ArchivioStorico()
	{
		this.iscrizioniFruitoriStoriche = new AnagraficaFruitori();
		this.rinnovoIscrizioniFruitoriStorici = new AnagraficaFruitori();
		this.decadenzeFruitoriStoriche = new AnagraficaFruitori();
		
		this.prestitiStorici = new ArchivioPrestiti();	
 	   	this.prestitiConProrogheStoriche = new ArchivioPrestiti();
 	   	
		elencoRisorseRimosse = new Vector <Risorsa> ();
	}
	
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
    
	public void aggiungiRisorsaRimossa(Risorsa r)
	{
		elencoRisorseRimosse.add(r);
	}
	
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
    
    public int numeroProroghePerAnno(int a)
    {
    	   int num = 0;
    	   
    	   for(int i = 0; i < prestitiStorici.getElencoPrestiti().size(); i++)
    	   {
    		   Prestito p = prestitiStorici.getElencoPrestiti().get(i);
    		   
    		   if(!(p.getProrogaNonEffettuata()))
    		   {
    			   if(p.getDataProrogaEffettuata().getYear() == a)
    				     num++;	   
    		   }
    	   }
    	   
    	   return num;
    }
	
	public String getRisorsaPiuRichiesta(int anno)
	{
		Risorsa ris = null;
		int max = 0;
		Vector <Prestito> elencoPrestiti = new Vector <Prestito> ();
	
		for(int i = 0; i < prestitiStorici.getElencoPrestiti().size(); i++)
		{
			Prestito p = prestitiStorici.getElencoPrestiti().get(i);
			
			if(p.getDataDiInizioPrestito().getYear() == anno)
			{
				elencoPrestiti.add(p);
			}
		}
			
		if(elencoPrestiti.size() != Costanti.VUOTO)
		{
		    for(int a = 0; a < elencoPrestiti.size(); a++) 
		    { 
			    int num = 0;
			    Risorsa r1 = elencoPrestiti.get(a).getRisorsaInPrestito();
			    Risorsa r2 = null;
					
			    for(int b = a+1; b < elencoPrestiti.size(); b++)
			    {
			    	r2 = elencoPrestiti.get(b).getRisorsaInPrestito();
			    	if(r2.equals(r1))
			    		num++;
			    }
			
			    if(num > max)
			    {
			    	ris = r1;
			    	max = num;
			    }
			    
		   }
           return ris.getTitolo();
		
		}
		else
			return "";
	}
	
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
