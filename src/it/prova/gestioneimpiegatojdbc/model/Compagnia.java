package it.prova.gestioneimpiegatojdbc.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Compagnia {
	
	private Long id; 
	private String ragioneSociale;
	private Integer fatturatoAnnuo; 
	private Date dataFondazione;
	private List<Impiegato> listaImpiegati = new ArrayList<Impiegato>();
	
	public Compagnia() {
		
	}
	
	public Compagnia(String ragioneSociale) {
		super();
		this.ragioneSociale = ragioneSociale;
	} 
	
	public Compagnia(String ragioneSociale, Integer fatturatoAnnuo) {
		super();
		this.ragioneSociale = ragioneSociale;
		this.fatturatoAnnuo = fatturatoAnnuo;
	} 
	
	public Compagnia(String ragioneSociale, Integer fatturatoAnnuo, Date dataFondazione) {
		super();
		this.ragioneSociale = ragioneSociale;
		this.fatturatoAnnuo = fatturatoAnnuo;
		this.dataFondazione = dataFondazione;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRagioneSociale() {
		return ragioneSociale;
	}

	public void setRagioneSociale(String ragioneSociale) {
		this.ragioneSociale = ragioneSociale;
	}

	public Integer getFatturatoAnnuo() {
		return fatturatoAnnuo;
	}

	public void setFatturatoAnnuo(Integer fatturatoAnnuo) {
		this.fatturatoAnnuo = fatturatoAnnuo;
	}

	public Date getDataFondazione() {
		return dataFondazione;
	}

	public void setDataFondazione(Date dataFondazione) {
		this.dataFondazione = dataFondazione;
	}
	
	public List<Impiegato> getListaImpiegati() {
		return listaImpiegati;
	}

	public void setListaImpiegati(List<Impiegato> listaImpiegati) {
		this.listaImpiegati = listaImpiegati;
	} 

	@Override
	public String toString() {
		
		String dateCreatedString = dataFondazione != null ? new SimpleDateFormat("dd/MM/yyyy").format(dataFondazione)
				: " N.D.";
		
		String idString = id != null ? id.toString() : " N.D.";
		
		return "Compagnia [id=" + idString + ", ragioneSociale=" + ragioneSociale + ", fatturatoAnnuo=" + fatturatoAnnuo
				+ ", dataFondazione=" + dateCreatedString + "]";
	}

	
	
	
	
	
	
	
	
	

}
