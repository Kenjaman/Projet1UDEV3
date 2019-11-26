package mynetflixjsp.modele;

import java.sql.Date;

public class Serie {

	private Integer id;
	private String nom;
	private String nomoriginal;
	private Date anneeparution;	
	private String synopsys;
	private Integer idstatut;
	private Integer idpaysorigine;
	
	public Serie(Integer id, String nom, String nomoriginal, Date anneeparution, String synopsys, Integer idstatut, Integer idpaysorigine) {
		this.id = id;
		this.nom = nom;
		this.nomoriginal = nomoriginal;
		this.anneeparution = anneeparution;
		this.synopsys = synopsys;
		this.idstatut = idstatut;
		this.idpaysorigine = idpaysorigine;
	}
	
	public Integer getId() {
		return id;
	}
	
	public String getNom() {
		return nom;
	}
	
	public String getNomoriginal() {
		return nomoriginal;
	}
	
	public Date getAnneeparution() {
		return anneeparution;
	}
	
	public String getSynopsys() {
		return synopsys;
	}
	
	public Integer getIdstatut() {
		return idstatut;
	}
	
	public Integer getIdpaysorigine() {
		return idpaysorigine;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public void setNomoriginal(String nomoriginal) {
		this.nomoriginal = nomoriginal;
	}
	
	public void setAnneeparution(Date anneeparution) {
		this.anneeparution = anneeparution;
	}
	
	public void setSynopsys(String synopsys) {
		this.synopsys = synopsys;
	}
	
	public void setIdstatut(Integer idstatut) {
		this.idstatut = idstatut;
	}
	
	public void setIdpaysorigine(Integer idpaysorigine) {
		this.idpaysorigine = idpaysorigine;
	}
	
}
