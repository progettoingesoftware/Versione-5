

import java.io.Serializable;
import java.time.DateTimeException;
import java.util.Vector;

import it.ing.sw.*;

import java.time.*;

/**
 * Questa classe permette una corretta gestione dell'uso dei menu'. E' essenzialmente suddivisa in tre parti:
 * 1 - Elenco delle costanti che costituiscono le intestazioni dei menu' e le varie opzioni che li compongono
 * 2 - Metodi ausiliari per la gestione delle funzionalita' basilari del software (iscrizione, accesso)
 * 3 - Metodo logicaMenu per la realizzazione delle connessioni tra i vari menu'
 */
public class GestoreMenu implements Serializable
{
	private static final long serialVersionUID = 1L;
	
    /**
	 * Metodo di interazione con l'utente per l'aggiunta di un nuovo fruitore all'elenco dei fruitori gia' presenti all'interno di af.
	 * Vengono effettuati dei controlli sulla correttezza della data di nascita inserita e sulla possibile presenza di fruitori gia' iscritti in possesso delle medesime credenziali indicate
	 * 
	 * Pre : af != null
	 * Pre : af.elenco != null
	 * 
	 * @param af : oggetto di tipo AnagraficaFruitori contenente l'elenco dei fruitori presenti ed i metodi per l'esecuzione dei vari controlli
	 */
	public void iscrizione(AnagraficaFruitori af)
	{
		String nome = "";
		String cognome = "";
		String use = "";
		String pwd = "";
		int giorno = 0;
		int mese = 0;
		int anno = 0;

		boolean end = true;
		
		boolean ins_nome = true;
		boolean ins_cognome = true;
		boolean ins_use = true;
		boolean ins_pwd = true;
		boolean ins_data = true;
		
		/**
		 * Ciclo globale per l'inserimento di un nuovo fruitore nel sistema in accordo con le condizioni indicate.
		 * E' possibile suddividere tale ciclo in 4 parti:
		 * 1 - Inserimento dei parametri richiesti
		 * 2 - Controllo sulla correttezza lessicale della data di nascita inserita
		 * 3 - Controlli sulle condizioni necessarie per l'iscrizione
		 * 4 - Completamento iscrizione o richiesta di perfezionamento della stessa
		 */
	    do
	    {
	    	/**
	    	 * Inserimento parametri
	    	 */
	    	if(ins_nome)
	    	{
				nome = InputDati.leggiStringaNonVuota(Costanti.INS_NOME);
	    	}
	    	
	    	if(ins_cognome)
	    	{
				cognome = InputDati.leggiStringaNonVuota(Costanti.INS_COGNOME);
	    	}
	    	
	    	if(ins_use)
	    	{
				use = InputDati.leggiStringaNonVuota(Costanti.INS_USERNAME);
	    	}
	    	
	    	if(ins_pwd)
	    	{
				pwd = InputDati.leggiStringaNonVuota(Costanti.INS_PASSWORD);
	    	}
	    	
			Fruitore f = null;	
			boolean exc = false;			
			end = true;
			
			/**
			 * Il controllo per la correttezza della data di nascita inserita viene gestito autonomamente dalla classe LocalDate.
			 * Nel caso in cui quest'ultima generi un'eccezione, e dunque la data inserita non sia lessicalmente corretta, viene modificata un'opportuna
			 * variabile booleana che impedisce la fuoriuscita dal ciclo do-while fintanto che non viene digitata una data valida
			 */
			while(!exc) {
			
				try 
				{
					if(ins_data)
					{
						giorno = InputDati.leggiIntero(Costanti.INS_GIORNO_NASCITA);
						mese = InputDati.leggiIntero(Costanti.INS_MESE_NASCITA);
						anno = InputDati.leggiIntero(Costanti.INS_ANNO_NASCITA);
					}
					
					f = new Fruitore(nome, cognome, anno, mese, giorno, use, pwd);
					
					exc = true;
				}
				catch(DateTimeException e)
				{
					System.out.println(Costanti.DATA_DI_NASCITA_ERRATA);
				}
				
			};
			
			ins_nome = false;
			ins_cognome = false;
			ins_use = false;
			ins_pwd = false;
			ins_data = false;

			/**
			 * I metodi di controllo verificano se non vi sono casi di omonimia tra diversi fruitori, se non vi sono casi di condivisione di username
			 * e se l'utente e' maggiorenne. In caso di inesattezze vengono reimpostati i parametri di inserimento e viene impedita la fuoriuscita dal ciclo globale
			 */
			if(af.verificaOmonimiaFruitori(f.getNome(), f.getCognome(), f.getDataDiNascita()) == true)
			{
				System.out.println(Costanti.ISCRIZIONE_NON_OK_OMONIMIA_FRUITORI);
				ins_nome = true;
				ins_cognome = true;
				ins_data = true;
				end = false;
			}
			
			if(af.verificaStessoUsername(f.getUsername()) == true)
			{
				System.out.println(Costanti.ISCRIZIONE_NON_OK_STESSO_USERNAME);
				ins_use = true;
				end = false;
			}
			
			if(Period.between(f.getDataDiNascita(), LocalDate.now()).getYears() < Costanti.MAGGIORE_ETA)
			{
				System.out.println(Costanti.ISCRIZIONE_NON_OK_MAGGIORE_ETA);
				ins_nome = true;
				ins_cognome = true;
				ins_data = true;
				end = false;
			}
			
			/**
			 * Se non sono stati segnalati errori, l'iscrizione si conclude con successo.
			 * Altrimenti, a meno che l'utente non esprima la volonta' di terminare l'operazione, si procede con le modifiche necessarie sui dati inseriti
			 */
			if(end)
			{
				af.aggiungiFruitore(f);
				System.out.println(Costanti.ISCRIZIONE_OK);
			}
			else
			{
	
				if(InputDati.leggiUpperChar(Costanti.RICHIESTA_PROSECUZIONE, "SN") == 'N')
				{
					end = true;				
					System.out.println(Costanti.ISCRIZIONE_NON_OK);
				}
				
			}
			
		}while(!end);
	    
	}
	
	/**
	 * Metodo di interazione con l'utente per l'accesso al sistema.
	 * Vengono effettuati dei controlli sulla correttezza dello username e della password indicati
	 * 
	 * Pre : ag != null
	 * Pre : ag.elenco != null
	 * 
	 * @param ag : oggetto di tipo Anagrafica contenente l'elenco degli utenti presenti ed il metodo per l'accesso
	 * @return l'utente specificato dalle credenziali indicate
	 */
    public Utente accesso(Anagrafica ag)
    {
		String use = "";
		String pwd = "";
		boolean end = false;
		Utente ut = null;
		
	    do
	    {
			use = InputDati.leggiStringaNonVuota(Costanti.USERNAME);
			pwd = InputDati.leggiStringaNonVuota(Costanti.PASSWORD);

			/**
			 * Se viene effettivamente reperito l'utente indicato, l'accesso si conclude con successo.
			 * Altrimenti, a meno che l'utente non esprima la volonta' di terminare l'operazione, si procede con le modifiche necessarie sui dati inseriti
			 */
			if(ag.accedi(use, pwd))
			{
				ut = ag.getUtente(use, pwd);
				end = true;
			}
			else
			{
				System.out.println(Costanti.CREDENZIALI_ERRATE);
				 
				if(InputDati.leggiUpperChar(Costanti.RICHIESTA_PROSECUZIONE, "SN") == 'N')
				{
					end = true;
				}
					
			}
	
		}while(!end);
		
	    return ut;
	}
    
    /**
     * Metodo per l'aggiunta di una risorsa ad una (sotto)categoria dell'archivio
     * 
     * Pre: (op != null) && (arc != null) && (arc.getElencoCategorie().size != 0)
     * 
     * @param op: l'operatore che effettua l'aggiunta della risorsa
     * @param arc: l'archivio a cui aggiungere la risorsa
     */
    public void aggiungiRisorsa(Operatore op, Archivio arc)
    {
    	Categoria c = null;
    	SottoCategoria sc = null;
    	Risorsa nuovar = null;
    	     
    	System.out.printf(Costanti.CONTENUTO_ARC, arc.stampaElencoCategorie());
    	
        int num1 = InputDati.leggiIntero(Costanti.INS_NUMERO_CAT_AGGIUNTA_RISORSA, Costanti.NUM_MINIMO, (arc.getElencoCategorie()).size());
        c = (arc.getElencoCategorie()).get(num1-Costanti.NUM_MINIMO);
        
        if(c.getElencoSottoCategorie() == null)
        {
        	System.out.printf(Costanti.CAT_SENZA_SOTTO, c.getNome());
          	    
          	if(InputDati.leggiUpperChar(Costanti.INS_PROCEDERE_CAT, "SN") == 'S')
          	{
          		if((c.getNome()).equalsIgnoreCase("Libri"))  
          		{
          			nuovar = InserimentoRisorsa.inserisciLibro();
          		}
          		else if((c.getNome()).equalsIgnoreCase("Film"))  
          		{
          			nuovar = InserimentoRisorsa.inserisciFilm();
          		}
          		
        	    if(nuovar != null)
        	    {
          			if(c.getRisorsa(nuovar.getTitolo()) == null )
          			{
          				op.aggiungiRisorsaCategoria(nuovar, c);
          				System.out.println(Costanti.OP_SUCCESSO);
          			}
          			else
          				System.out.println(Costanti.OP_NO_SUCCESSO_AGGIUNTA_1);
          		}
          			
          	}
          	    
        }
        else if(c.getElencoSottoCategorie().size() == Costanti.VUOTO)
        {
        	System.out.printf(Costanti.CONTENUTO_ELENCO_SOTTO_VUOTO, c.getNome());
        }
        else
        {
        	System.out.printf(Costanti.CONTENUTO_CAT_SOTTO, c.getNome(), c.stampaElencoSottocategorie());
            	
            if(InputDati.leggiUpperChar(Costanti.INS_PROCEDERE_SOTTO, "SN") == 'S')
            {	 
            	int num2 = InputDati.leggiIntero(Costanti.INS_NUMERO_SOTTO_AGGIUNTA_RISORSA, Costanti.NUM_MINIMO, (c.getElencoSottoCategorie()).size());
            	sc = (c.getElencoSottoCategorie()).get(num2-Costanti.NUM_MINIMO);
        	    	    	    	    	    
            	if((c.getNome()).equalsIgnoreCase("Libri"))  
            	{
            		nuovar = InserimentoRisorsa.inserisciLibro();
            	}
            	else if((c.getNome()).equalsIgnoreCase("Film"))  
            	{
            		nuovar = InserimentoRisorsa.inserisciFilm();
            	}
            	
            	if(nuovar != null)
            	{
            	
            		if(!(c.verificaPresenza(nuovar.getTitolo())))
            		{       
      	    	   		if( (nuovar.getGenere()).equalsIgnoreCase(sc.getNome()) )
      	    	   		{
      	    	   			op.aggiungiRisorsaCategoria(nuovar, sc);
      	    	   			System.out.println(Costanti.OP_SUCCESSO);
      	    	   		}
      	    	   		else
      	    	   			System.out.println(Costanti.OP_NO_SUCCESSO_AGGIUNTA_2);
            		}
            		else
            			System.out.println(Costanti.OP_NO_SUCCESSO_AGGIUNTA_1);
            	}
            	
            }
        	    
        }
          	
    }
     
     /**
      * Metodo per la rimozione di una risorsa da una (sotto)categoria dell'archivio
      * 
      * Pre: (op != null) && (arc != null) && (arc.getElencoCategorie().size != 0)
      * 
      * @param op: l'operatore che effettua la rimozione della risorsa
      * @param arc: l'archivio da cui rimuovere la risorsa
      */
     public void rimuoviRisorsa(Operatore op, Archivio arc)
     {
        	Categoria c = null;
 	    SottoCategoria sc = null;
 	    Risorsa daEliminare = null;
 	    
 	    System.out.printf(Costanti.CONTENUTO_ARC, arc.stampaElencoCategorie());
 	    
 	    int num1 = InputDati.leggiIntero(Costanti.INS_NUMERO_CAT_RIMOZIONE_RISORSA, Costanti.NUM_MINIMO, (arc.getElencoCategorie()).size());
         c = (arc.getElencoCategorie()).get(num1-Costanti.NUM_MINIMO);
     	
         if(c.getElencoSottoCategorie() == null)
     	{
         	if((c.getElencoRisorse()).size() != 0)
         	{
         		System.out.printf(Costanti.CAT_SENZA_SOTTO, c.getNome());
         		System.out.printf(Costanti.CONTENUTO_CAT_RISORSA, c.getNome(), c.stampaElencoRisorse());

         		if(InputDati.leggiUpperChar(Costanti.INS_PROCEDERE_RISORSA, "SN") == 'S')
     	    	{
         			int num2 = InputDati.leggiIntero(Costanti.INS_NUMERO_RISORSA_RIMOZIONE, Costanti.NUM_MINIMO, (c.getElencoRisorse()).size());
     		    	daEliminare = (c.getElencoRisorse()).get(num2-Costanti.NUM_MINIMO);
     		    	op.rimuoviRisorsaCategoria(daEliminare, c);
             		System.out.println(Costanti.OP_SUCCESSO);
     	    	}

     	    } 
       	    else
     	    {
         		System.out.printf(Costanti.CONTENUTO_ELENCO_RISORSE_CAT_VUOTO, c.getNome());
     	    }
         
     	 }
         else if((c.getElencoSottoCategorie()).size() == Costanti.VUOTO)
         {
   	       	System.out.printf(Costanti.CONTENUTO_ELENCO_SOTTO_VUOTO, c.getNome());
         }
         else
         {
         	System.out.printf(Costanti.CONTENUTO_CAT_SOTTO, c.getNome(), c.stampaElencoSottocategorie());
       	       		
       	    if(InputDati.leggiUpperChar(Costanti.INS_PROCEDERE_SOTTO, "SN") == 'S')
       	    {
       	    	int num2 = InputDati.leggiIntero(Costanti.INS_NUMERO_SOTTO_RIMOZIONE_RISORSA, Costanti.NUM_MINIMO, (c.getElencoSottoCategorie()).size());
     	    	sc = (c.getElencoSottoCategorie()).get(num2-Costanti.NUM_MINIMO);
     	    	   
     	    	if(sc.getElencoRisorse().size() != Costanti.VUOTO)
     	    	{
     	           	System.out.printf(Costanti.CONTENUTO_SOTTO, sc.getNome(), sc.stampaElencoRisorse());
     	           	
     	      	    if(InputDati.leggiUpperChar(Costanti.INS_PROCEDERE_RISORSA, "SN") == 'S')
     	      	    {
     	      	    	int num3 = InputDati.leggiIntero(Costanti.INS_NUMERO_RISORSA_RIMOZIONE, Costanti.NUM_MINIMO, (sc.getElencoRisorse()).size());
         	    		daEliminare = (sc.getElencoRisorse()).get(num3-Costanti.NUM_MINIMO);
         	    		op.rimuoviRisorsaCategoria(daEliminare, sc);
         	           	System.out.println(Costanti.OP_SUCCESSO);
     	      	    }
     	    		
     	    	}
     	    	else
     	    		System.out.printf(Costanti.CONTENUTO_ELENCO_RISORSE_SOTTO_VUOTO, sc.getNome());
 	 
       	    }

       	    	 	   
         }   
   	    
     }
     
     /**
      * Metodo di interazione con l'utente, al quale permette di registrare un prestito se sono rispettate
      * delle condizioni. Se la registrazione del prestito avviene con successo, il prestito viene aggiunto all'archivio
      * dei prestiti
      * 
      * Pre: (f != null) && (arc != null) && (a != null) && (arc.getElencoCategorie().size != 0)
      * 
      * @param f: il fruitore che vuole effettuare la registrazione del prestito
      * @param arc: l'archivio delle risorse
      * @param a: l'archivio dei prestiti
      */
     public void registraPrestito(Fruitore f, Archivio arc, ArchivioPrestiti ap) 
     {
     	 Categoria c = null;
     	 SottoCategoria sc = null;
     	 Risorsa r = null;
     	 Prestito nuovo = null;
     	
     	 System.out.printf(Costanti.CONTENUTO_ARC, arc.stampaElencoCategorie());
     	 int num1 = InputDati.leggiIntero(Costanti.INS_NUMERO_CAT_PRESTITO, Costanti.NUM_MINIMO, (arc.getElencoCategorie()).size());
     	 c = (arc.getElencoCategorie()).get(num1-Costanti.NUM_MINIMO);

     	 if(c.getElencoSottoCategorie() == null)
     	 {
       	    if(c.getElencoRisorse().size() != Costanti.VUOTO)  
     	    {
       	    	     System.out.printf(Costanti.CONTENUTO_CAT_RISORSA, c.getNome(), c.stampaElencoRisorse());
       	    	     
       	    	     if(InputDati.leggiUpperChar(Costanti.INS_PROCEDERE_PRESTITO, "SN") == 'S')
       	    	     {
       	    	    	 int num = InputDati.leggiIntero(Costanti.INS_NUMERO_RISORSA_PRESTITO, Costanti.NUM_MINIMO, c.getElencoRisorse().size());
       	    	    	 r = c.getElencoRisorse().get(num-Costanti.NUM_MINIMO);
     	    	
       	    	    	 if(ap.controlloDisponibilitaRisorsa(r) && ap.controlloPerUlteriorePrestito(c, f))
       	    	    	 {
       	    	    		 nuovo = new Prestito(c, f, r);
       	    	    		 f.registraNuovoPrestito(ap, nuovo);
       	    	    		 System.out.println(Costanti.OP_SUCCESSO);
       	    	    	 }
       	    	    	 else if(!ap.controlloDisponibilitaRisorsa(r))
       	    	    		 System.out.println(Costanti.OP_NO_SUCCESSO_PRESTITO_1);
       	    	    	 else
       	    	    		 System.out.println(Costanti.OP_NO_SUCCESSO_PRESTITO_2);
       	    	     }

     	    } 
       	    else
       	    	    System.out.printf(Costanti.CONTENUTO_ELENCO_RISORSE_CAT_VUOTO, c.getNome());
     	 }
     	 else if((c.getElencoSottoCategorie()).size() == Costanti.VUOTO)
     	 {
     		System.out.printf(Costanti.CONTENUTO_ELENCO_SOTTO_VUOTO, c.getNome());
     	 }
     	 else
     	 {
     		System.out.printf(Costanti.CONTENUTO_CAT_SOTTO, c.getNome(), c.stampaElencoSottocategorie());
     		int num2 = InputDati.leggiIntero(Costanti.INS_NUMERO_SOTTO_PRESTITO, Costanti.NUM_MINIMO, (c.getElencoSottoCategorie()).size());
     	    sc = (c.getElencoSottoCategorie()).get(num2-Costanti.NUM_MINIMO);
     	    	    	    	    	    
     	    if((sc.getElencoRisorse()).size() != Costanti.VUOTO)
     	    {
 	            System.out.printf(Costanti.CONTENUTO_SOTTO, sc.getNome(), sc.stampaElencoRisorse());
  
     	    	if(InputDati.leggiUpperChar(Costanti.INS_PROCEDERE_PRESTITO, "SN") == 'S')
     	    	{
     	    		int num = InputDati.leggiIntero(Costanti.INS_NUMERO_RISORSA_PRESTITO, Costanti.NUM_MINIMO, sc.getElencoRisorse().size());
     	    		r = sc.getElencoRisorse().get(num-Costanti.NUM_MINIMO);
 	
     	    		if(ap.controlloDisponibilitaRisorsa(r) && ap.controlloPerUlteriorePrestito(c, f))
     	    		{
     	    			nuovo = new Prestito(c, f, r);
     	    			f.registraNuovoPrestito(ap, nuovo);
     	    			System.out.println(Costanti.OP_SUCCESSO);
     	    		}
     	    		else if(!ap.controlloDisponibilitaRisorsa(r))
     	    			System.out.println(Costanti.OP_NO_SUCCESSO_PRESTITO_1);
     	    		else
     	    			System.out.println(Costanti.OP_NO_SUCCESSO_PRESTITO_2);
     	    		
     	    	}
     	    	
	       }
     	   else
     		    System.out.printf(Costanti.CONTENUTO_ELENCO_RISORSE_SOTTO_VUOTO, sc.getNome());	     
     	}
       	    
     }
      
     /**
      * Metodo di interazione con l'utente per la richiesta della proroga di una risorsa
      * 
      * Pre: (f != null) && (arc != null) && (ap != null) && (arc.getElencoCategorie().size != 0)
      * 
      * @param f: il fruitore che richiede la proroga
      * @param ap: l'archivio dei prestiti
      */
     public void richiediProroga(Fruitore f, ArchivioPrestiti ap)  
     { 
    	    if(ap.getPrestiti(f.getUsername()).size() != Costanti.VUOTO)
    	    {
    	       System.out.println(f.visualizzaPrestitiInCorso(ap));
    	
    	       int num = InputDati.leggiIntero(Costanti.INS_NUMERO_PRESTITO_PROROGA, Costanti.NUM_MINIMO, ap.getPrestiti(f.getUsername()).size());
    	       Prestito pr = ap.getElencoPrestiti().get(num-Costanti.NUM_MINIMO);
    	
    	       if(f.registraProrogaPrestito(pr))
    	       {
    	    	   System.out.println(Costanti.OP_SUCCESSO);
    	       }
    	       else
    	       {
    	    	   System.out.println(Costanti.OP_NO_SUCCESSO_PROROGA_1);
    	       }
    	    }
    	    else
    	    {
    		    System.out.println(Costanti.OP_NO_SUCCESSO_PROROGA_2);
    	    }
    	    
     }
    
     /**
      * Metodo di interazione con l'utente per la scelta della categoria a cui appartiene
      * la risorsa che si vuole cercare: in base alla categoria scelta il metodo invocher�
      * il metodo pi� specifico di ricerca in base alla categoria selezionata
      * 
      * Pre: (ut != null) && (arc != null) && (arc.getElencoCategorie().size != 0)
      * 
      * @param ut: l'utente che effettua la ricerca 
      * @param arc: l'archivio delle risorse
      * @return il vettore contenente le risorse che hanno soddisfatto la ricerca
      */
     public Vector <Risorsa> ricercaRisorsa(Utente ut, Archivio arc)
     {
        	Categoria c = null;
        	
    	 	System.out.printf(Costanti.CONTENUTO_ARC, arc.stampaElencoCategorie());
    	 	
    	    int num1 = InputDati.leggiIntero(Costanti.INS_NUMERO_CAT_RICERCA, Costanti.NUM_MINIMO, (arc.getElencoCategorie()).size());
    	    c = (arc.getElencoCategorie()).get(num1-Costanti.NUM_MINIMO);
    	 		
    	 	if(c.getNome().equalsIgnoreCase("Libri"))
 				return ricercaRisorsaLibri(ut, c);
    	 	else if(c.getNome().equalsIgnoreCase("Libri"))
 				return ricercaRisorsaFilm(ut, c);
    	 	else
    	 		return null;
     }
     
     /**
      * Metodo di interazione con l'utente per la ricerca di un libro secondo diverse opzioni
      * di ricerca
      * 
      * Pre: (ut != null) && (c != null)
      * 
      * @param ut: l'utente che effettua la ricerca
      * @param c: la categoria delle risorse di cui si sta effettuando la ricerca
      * @return il vettore contenente le risorse che hanno soddisfatto la ricerca
      */
     public Vector <Risorsa> ricercaRisorsaLibri(Utente ut, Categoria c)
     {
    	    int numScelta = InputDati.leggiIntero(Costanti.AVVIO_RICERCA_RISORSE, Costanti.NUM_MINIMO, Costanti.NUM_MASSIMO_RICERCA);
    	    Object o = null;
    	    String s = "";
    	
    	    switch(numScelta)
    	    {
 	    	   case(1): o = InputDati.leggiStringa(Costanti.INS_TITOLO_RISORSA);
 	    			    s = Costanti.RIC_PER_TITOLO;
 	    			    break;
 	    		
 	    	   case(2): o = InputDati.leggiStringa(Costanti.INS_AUTORE_LIBRO); 
 	    		        s = Costanti.RIC_PER_AUTORE_I;
 	    		        break;
 	       
 	    	   case(3): o = InputDati.leggiStringa(Costanti.INS_GENERE_RISORSA);
 	    		        s = Costanti.RIC_PER_GENERE;
 	    		        break;
 	    	   
 	    	   case(4): o = InputDati.leggiIntero(Costanti.INS_ANNOPUB_RISORSA);
 	    	   			s = Costanti.RIC_PER_ANNOPUB;
 	    	   			break;
 	    		
 	    	   case(5): o = InputDati.leggiStringa(Costanti.INS_CASAED_LIBRO);
 	    	   			s = Costanti.RIC_PER_CASAED;
 	    	   			break;
    	    }  
 	    
    	    return ut.ricercaRisorse(c, o, s);
     }
    
     /**
      * Metodo di interazione con l'utente per la ricerca di un film secondo diverse opzioni
      * di ricerca
      * 
      * Pre: (ut != null) && (c != null)
      * 
      * @param ut: l'utente che effettua la ricerca
      * @param c: la categoria delle risorse di cui si sta effettuando la ricerca
      * @return il vettore contenente le risorse che hanno soddisfatto la ricerca
      */
     public Vector <Risorsa> ricercaRisorsaFilm(Utente ut, Categoria c)
     {
    	    int numScelta = InputDati.leggiIntero(Costanti.AVVIO_RICERCA_FILM, Costanti.NUM_MINIMO, Costanti.NUM_MASSIMO_RICERCA);
    	    Object o = null;
    	    String s = "";
    	
    	    switch(numScelta)
    	    {
    	    	case(1): o = InputDati.leggiStringa(Costanti.INS_TITOLO_RISORSA);
    	    			 s = Costanti.RIC_PER_TITOLO;
 	    			     break;
 	    		
    	    	case(2): o = InputDati.leggiStringa(Costanti.INS_REGISTA_FILM); 
 	    		         s = Costanti.RIC_PER_REGISTA;
 	    		         break;
 	       
    	    	case(3): o = InputDati.leggiStringa(Costanti.INS_ATTORE_FILM);
 	    		         s = Costanti.RIC_PER_ATTORE_I;
 	                     break;
 	    	   
    	    	case(4): o = InputDati.leggiIntero(Costanti.INS_ANNOPUB_RISORSA);
 	                 	 s = Costanti.RIC_PER_ANNOPUB;
 	                 	 break;
 	    		
    	    	case(5): o = InputDati.leggiStringa(Costanti.INS_GENERE_RISORSA);
    	    			 s = Costanti.RIC_PER_GENERE;
    	    			 break;
    	    }  
 	    
    	    return ut.ricercaRisorse(c, o, s);
     }
     
    /**
     * Metodo per la creazione di una stringa descrittiva delle risorse che sono state trovate mediante 
     * una ricerca
     * 
     * Pre: elencoRisorse != null
     * 
     * @param elencoRisorse: il vettore contenente le risorse, risultato dalla ricerca, da stampare
     * @return la stringa desrittiva delle risorse
     */
    public String ricercaRisorsaFormatoStringa(Vector <Risorsa> elencoRisorse)
    {
   	    StringBuffer ris = new StringBuffer();
   	    ris.append(Costanti.INTESTAZIONE_RICERCA_RISORSE);
   	    
   	    if(elencoRisorse.size() == Costanti.VUOTO)
   	    	    ris.append(Costanti.RICERCA_VUOTA);
   	    
 		for(int i = 0; i < elencoRisorse.size(); i++)
 		{
 			Risorsa r = elencoRisorse.get(i);
 			ris.append("\t\t" + (i+1) + ")" + r.toString());
 		}
 		
 		return ris.toString();
    }
    
   
   /**
    * Metodo di interazione con l'utente per la valutazione della disponibilit� di una risorsa in archivio
    * 
    * Pre: (ut != null) && (arc != null) && (ap != null)
    * 
    * @param ut: l'utente che effettua la valutazione
    * @param arc: l'archivio delle risorse
    * @param ap: l'archivio dei prestiti
    * @return true se la risorsa scelta per la valutazione e' disponibile
    */
   public boolean valutazioneDisponibilita(Utente ut, Archivio arc, ArchivioPrestiti ap)
   {
	    Vector<Risorsa> risorseTrovate = ricercaRisorsa(ut, arc);
     	System.out.println(ricercaRisorsaFormatoStringa(risorseTrovate));
   	
     	int num = InputDati.leggiIntero(Costanti.RICHIESTA_DIGITAZIONE_VALUTAZIONE, Costanti.NUM_MINIMO, risorseTrovate.size());
   	
     	if(ap.controlloDisponibilitaRisorsa(risorseTrovate.get(num-Costanti.NUM_MINIMO)))
   		    return true;
     	else
   		    return false;
   }
   
   public String sceltaInformazione(Operatore o, ArchivioStorico as) /// vedere se � possibile costruire un men� g
   {
	    int numScelta = InputDati.leggiIntero(Costanti.SCELTA_INFORMAZIONE, Costanti.NUM_MINIMO, Costanti.NUM_MASSIMO_RICERCA);
	    int anno = 0;
	    Fruitore f = null;
	    String s = "";
	    
	    switch(numScelta)
	    {
	    	case 1 : anno = InputDati.leggiIntero(Costanti.INS_ANNO_RICHIESTO);
	    			 s += o.visualizzaPrestitiPerAnno(as, anno);
	    			     break;
	    		
	    	case 2 : anno = InputDati.leggiIntero(Costanti.INS_ANNO_RICHIESTO);
	    			s += o.visualizzaProroghePerAnno(as, anno);
	    			break;
	       
	    	case 3 : anno = InputDati.leggiIntero(Costanti.INS_ANNO_RICHIESTO);
					s += o.visualizzaRisorsaPiuRichiesta(as, anno);
					break;
	    	   
	    	case 4: anno = InputDati.leggiIntero(Costanti.INS_ANNO_RICHIESTO);
	    			f = InputDati.leggiStringa(Costanti.INS_FRUITORE_RICHIESTO);
	    			
	    			/// dove ricercare l'oggetto fruitore??
	    			s += o.visualizzaNumeroPrestitiPerFruitorePerAnno(as, f, anno);
	                 	 break;
	    }  
	    
	    return s;
	    
   }
   
   public void controlloScadenze(AnagraficaFruitori af, ArchivioPrestiti ap)
   {
   		af.decadenzaFruitore();
   		ap.scadenzaPrestito();
   }
   
    /**
     * Vengono inizialmente creati i vari menu' con le relative intestazioni ed opzioni. 
     * In seguito l'andamento del programma e' scandito attraverso l'aggiornamento della variabile letteraMenu e l'uso di switch-case innestati,
     * in cui il primo livello (contraddistinto dalle variabili letterali) indica gli specifici menu', mentre il secondo livello (evidenziato
     * dall'uso della variabile intera 'scelta') indica le opzioni relative ad ogni menu' e le operazioni che vengono indi svolte
     * 
     * Pre : af != null
     * Pre : ao != null
     * Pre : arc != null
     * Pre : ap != null
     * Pre : as != null
     * 
     * @param af : oggetto di tipo AnagraficaFruitori
     * @param ao : oggetto di tipo AnagraficaOperatori
     * @param arc : oggetto di tipo Archivio
     * @param ap : oggetto di tipo ArchivioPrestiti
     * @param as: oggetto di tipo ArchivioStorico
     */
    public void logicaMenu(AnagraficaFruitori af, AnagraficaOperatori ao, Archivio arc, ArchivioPrestiti ap, ArchivioStorico as)
    {
		Menu a = new Menu(Costanti.INTESTAZIONE_A, Costanti.OPZIONI_A);
		Menu b = new Menu(Costanti.INTESTAZIONE_B, Costanti.OPZIONI_B);
		Menu c = new Menu(Costanti.INTESTAZIONE_C, Costanti.OPZIONI_C);
		Menu d = new Menu(Costanti.INTESTAZIONE_D, Costanti.OPZIONI_D_345);
		Menu e = new Menu(Costanti.INTESTAZIONE_E, Costanti.OPZIONI_E);
		Menu f = new Menu(Costanti.INTESTAZIONE_F, Costanti.OPZIONI_F_5);

      	boolean esci = false;
      	char letteraMenu =  'a';
        int scelta = 0;
        
        Fruitore attualef = null;
        Operatore attualeop = null;
       
        System.out.println(Costanti.SALUTO_INIZIALE);
          
        do
        {

           	switch(letteraMenu)
    	    {
    	      	case('a'):
    	        {
    	      		scelta = a.scegli();
	        	     
    	    		switch(scelta)
    	    		{
	        	    	case 1: letteraMenu = 'b';
	        	                break;
  	        	
	        	        case 2: letteraMenu = 'e';
  	                    		break;
  	                    		
	        	        case 3: esci = true;
	        	        		break;
	        	    }
    	    		    
    	    		controlloScadenze(af, ap);
    	    		break;
    	        }
    	          
				case ('b'): 
				{
					scelta = b.scegli();

					switch (scelta) 
					{
						case 1: iscrizione(af);
								letteraMenu = 'a';
								break;

						case 2: letteraMenu = 'c';
								break;

						case 3: letteraMenu = 'a';
								break;
					}

    	    		controlloScadenze(af, ap);
					break;
				}
    	          
				case ('c'): 
				{
					scelta = c.scegli();

					switch (scelta) 
					{
						case 1: attualef = (Fruitore) accesso(af);

								if (attualef != null) 
								{
									letteraMenu = 'd';
								} 
								else 
								{
									System.out.println(Costanti.ERRORE);
									letteraMenu = 'c';
								}

								break;

						case 2: letteraMenu = 'b';
								break;
					}

    	    		controlloScadenze(af, ap);
					break;
				}
    	          
				case ('d'): 
				{
					scelta = d.scegli();

					switch (scelta) 
					{
						case 1: if (attualef.rinnovaIscrizione())
									System.out.println(Costanti.RINNOVO_OK);
								else
									System.out.println(Costanti.RINNOVO_NON_OK);

								letteraMenu = 'd';
								break;

						case 2: System.out.println(attualef.toString());
	        	         		letteraMenu = 'd';
	        	         		break;
	                
						case 3: System.out.println(attualef.visualizzaPrestitiInCorso(ap));
	        		        	letteraMenu = 'd';
	        		        	break;
	        		
						case 4: registraPrestito(attualef, arc, ap);  
	        		        	letteraMenu = 'd';
	        		        	break;
	        		
						case 5: richiediProroga(attualef, ap);    
	        	         		letteraMenu = 'd';
	        	         		break;
	        		
						case 6: System.out.println(ricercaRisorsaFormatoStringa(ricercaRisorsa(attualef, arc)));
	        		        	letteraMenu = 'd';
	        		        	break;
	        
						case 7: if (valutazioneDisponibilita(attualef, arc, ap))
								{	
									System.out.println(Costanti.RISORSA_DISPONIBILE);
								}
								else
								{
									System.out.println(Costanti.RISORSA_NON_DISPONIBILE);
								}
								
								letteraMenu = 'd';
								break;
	        	
						case 8: letteraMenu = 'a';
	        		        	attualef = null;
	        		        	break;
					}

    	    		controlloScadenze(af, ap);
					break;
				}
    	        
				case ('e'): 
				{
					scelta = e.scegli();

					switch (scelta) 
					{
						case 1: attualeop = (Operatore) accesso(ao);

								if (attualeop != null) 
								{
									letteraMenu = 'f';
								}
								else 
								{
									System.out.println(Costanti.ERRORE);
									letteraMenu = 'e';
								}

								break;

						case 2: letteraMenu = 'a';
								break;
					}

    	    		controlloScadenze(af, ap);
					break;
				}

				case ('f'):
				{
					scelta = f.scegli();

					switch (scelta)
					{
						case 1: System.out.println(attualeop.visualizzaElencoFruitori(af));
								letteraMenu = 'f';
								break;

	        	     	case 2: System.out.println(attualeop.visualizzaArchivio(arc));
	     	        			letteraMenu = 'f';
	     	        			break;
	     	        		
	        	     	case 3: aggiungiRisorsa(attualeop, arc);
     	     	        		letteraMenu = 'f';
     	     	        		break;
     	     		
	        	     	case 4: rimuoviRisorsa(attualeop, arc);
     	     	        		letteraMenu = 'f';
     	     	        		break;
     	     	    
	        	     	case 5: System.out.println(ricercaRisorsaFormatoStringa(ricercaRisorsa(attualeop, arc)));
	     	        			letteraMenu = 'f';
	     	        			break;
     	     
	        	     	case 6: if(valutazioneDisponibilita(attualeop, arc, ap))
	        	     			{
	        	     				System.out.println(Costanti.RISORSA_DISPONIBILE);
	        	     			}
     	     					else
     	     					{
     	     						System.out.println(Costanti.RISORSA_NON_DISPONIBILE);
     	     					}
	        	     	
     	     					letteraMenu = 'f';
     	     					break;
     	     					
	        	     	case 7: System.out.println(sceltaInformazione(attualeop, as));
	     						letteraMenu = 'f';
	        	     			break;
     	     	    
	        	     	case 8: letteraMenu = 'a';
     	        		    	attualeop = null;
     	        		    	break;
					}

    	    		controlloScadenze(af, ap);
					break;
				}
    	        
    	    }
    	      
       }while(!esci);   
       
       System.out.println(Costanti.SALUTO_FINALE);       
    }
     
}
