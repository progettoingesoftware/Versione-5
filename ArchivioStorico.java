import java.io.Serializable;
import java.time.LocalDate;
import java.util.Vector;

public class ArchivioStorico implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private Vector <Fruitore> elencoStoricoIscrizioneFruitori;
	private Vector <Fruitore> elencoStoricoRinnovoIscrizioneFruitori;
	private Vector <Fruitore> elencoStoricoDecadenzaFruitori;
	
    private ArchivioPrestiti prestitiEProrogheStorici;
	
	private Vector <Risorsa> elencoRisorseRimosse;
	
	public ArchivioStorico()
	{
		elencoStoricoIscrizioneFruitori = new Vector <Fruitore> ();
		elencoStoricoRinnovoIscrizioneFruitori = new Vector <Fruitore> ();
		elencoStoricoDecadenzaFruitori = new Vector <Fruitore> ();
		elencoRisorseRimosse = new Vector <Risorsa> ();
		
 	   	prestitiEProrogheStorici = new ArchivioPrestiti();
	}
	
    public ArchivioPrestiti getPrestitiEProrogheStorici()
    {
    	return prestitiEProrogheStorici;
    }
	
	public void aggiungiIscrizioneFruitore(Fruitore f)
	{
		elencoStoricoIscrizioneFruitori.add(f);
	}
	
	public void aggiungiRinnovoIscrizioneFruitore(Fruitore f)
	{
		elencoStoricoRinnovoIscrizioneFruitori.add(f);
	}
	
	public void aggiungiDecadenzaFruitore(Fruitore f)
	{
		elencoStoricoDecadenzaFruitori.add(f);
	}
	
	//Registrazione prestiti
	
    public void registraProrogaPrestitoStorico(Prestito p)
    {
    	    for(int i = 0; i < prestitiEProrogheStorici.getElencoPrestiti().size(); i++)
    	    {
    	    	    Prestito pr = prestitiEProrogheStorici.getElencoPrestiti().get(i);
    	    	    
    	    	    if(pr.equals(p))
    	    	    {
    	    	    	   LocalDate nuovaDataScadenza = (p.getDataDiScadenzaPrestito()).plusDays((p.getCategoriaAssociata()).getNumeroGiorniRichiestaProroga());
    	    	    	   pr.setDataDiScadenzaPrestito(nuovaDataScadenza);
    	    	    	   p.setProrogaNonEffettuata(LocalDate.now());
    	    	    	   break;
    	    	    }
    	    }
    }
	
	public void aggiungiRisorsaRimossa(Risorsa r)
	{
		elencoRisorseRimosse.add(r);
	}
	
    public int numeroPrestitiPerAnno(int a)
    {
    	   int num = 0;
    	   
    	   for(int i = 0; i < prestitiEProrogheStorici.getElencoPrestiti().size(); i++)
    	   {
    		   Prestito p = prestitiEProrogheStorici.getElencoPrestiti().get(i);
    		   
    		   if(p.getDataDiInizioPrestito().getYear() == a)
    			    num++;
    	   }
    	   
    	   return num;
    }
    
    public int numeroProroghePerAnno(int a)
    {
    	   int num = 0;
    	   
    	   for(int i = 0; i < prestitiEProrogheStorici.getElencoPrestiti().size(); i++)
    	   {
    		   Prestito p = prestitiEProrogheStorici.getElencoPrestiti().get(i);
    		   
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
		Risorsa r = null;
		Vector <Prestito> elencoPrestiti = prestitiEProrogheStorici.getElencoPrestiti();
		Vector <Integer> elencoFrequenzePrestiti = new Vector <Integer>();
		
		int max = 0;
		   
		for(int i = 0; i < elencoPrestiti.size(); i++)
		{
			Prestito p = elencoPrestiti.get(i);
			
			if(p.getDataDiInizioPrestito().getYear() != anno)
			{
				elencoPrestiti.remove(i);
			}
		
		}
			
		for (int a = 0; a < elencoPrestiti.size(); a++) 
		{
			int num = 0;
			
			Prestito p = elencoPrestiti.get(a);
					
			for (int b = 0; b < elencoPrestiti.size(); b++)
			{
				if(elencoPrestiti.get(b).getRisorsaInPrestito().equals( p.getRisorsaInPrestito() ) )
					num++;
			}
					
			elencoFrequenzePrestiti.set(a, num); 				 
		}

		for (int i = 0; i < elencoFrequenzePrestiti.size(); i++) 
		{
			if( elencoFrequenzePrestiti.get(i) > max)
			{ 
				max = elencoFrequenzePrestiti.get(i); 
				r = elencoPrestiti.get(i).getRisorsaInPrestito(); 
			} 
				 
		}
   
	    return r.getTitolo();
	}
	
	public int numeroPrestitiPerFruitorePerAnno(Fruitore f, int a)
	{
	    	   int num = 0;
	    	   
	    	   for(int i = 0; i < prestitiEProrogheStorici.getElencoPrestiti().size(); i++)
	    	   {
	    		   Prestito p = prestitiEProrogheStorici.getElencoPrestiti().get(i);
	    		   
	    		   if((p.getFruitoreAssociato().equals(f)) && p.getDataDiInizioPrestito().getYear() == a)
	    		         num++;
	    	   }
	    	   
	    	   return num;
	 }
	
}
