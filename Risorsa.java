package dominio_5;

import java.io.Serializable;

/**
 * Questa classe rappresenta il modello di una risorsa
 */
public abstract class Risorsa implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	protected String titolo;
	private int numLicenze;
	private String genere;
	private int annoPub;
	private String lingua;
    
	/**
	 * Metodo costruttore della classe Risora
	 * @param t: titolo con cui identificare la risorsa
	 * @param lic: numero delle licenze associate alla risorsa
	 * @param g: genere della risorsa
	 * @param ap: anno di pubblicazione della risorsa
	 * @param l: lingua della risorsa
	 */
    public Risorsa(String t, int lic, String g, int ap, String l)
    {
    	    this.titolo = t;
    	    this.numLicenze = lic;
    	    this.genere = g;
    	    this.annoPub = ap;
    	    this.lingua = l;
    }
    
    /**
     * Metodi get per il ritorno dei vari attributi della classe Risorsa
     * @return i vari attributi della classe Risorsa
     */
    public String getTitolo()
    {
    	   return titolo;
    }
    
    public int getNumLicenze() 
    {
    	   return numLicenze;
    }
    
    public String getGenere()
    {
    	    return genere;
    }
    
    public int getAnnoPub()
    {
    	    return annoPub;
    }
    
    public String getLingua()
    {
    	    return lingua;
    }
    
    /**
     * Metodo astratto toString() per la descrizione di una risorsa
     */
    public abstract String toString();
    
}
