package G7Netflix.modele;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Episode {

	private Integer id;
	private Integer numero;
	private String titre;
	private String titreOriginal;
	private Integer duree;
	private String resume;
	private Date dateReal;
	private Date primodiffusion;
	private Integer idPublic;
	private Integer idStatut;
	private Integer idSaison;



	public Episode(Integer numero, String titre, String titreOriginal, Integer duree, String resume, Date dateReal,
			Date primodiffusion, Integer idPublic, Integer idStatut, Integer idSaison) {
		super();
		this.numero = numero;
		this.titre = titre;
		this.titreOriginal = titreOriginal;
		this.duree = duree;
		this.resume = resume;
		this.dateReal = dateReal;
		this.primodiffusion = primodiffusion;
		this.idPublic = idPublic;
		this.idStatut = idStatut;
		this.idSaison = idSaison;
	}
	public Episode(Integer id, Integer numero, String titre, String titreOriginal, Integer duree, String resume,
			Date dateReal, Date primodiffusion, Integer idPublic, Integer idStatut, Integer idSaison) {
		this(numero, titre, titreOriginal,duree,resume,dateReal,primodiffusion, idPublic, idStatut, idSaison);
		this.id = id;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public String getTitreOriginal() {
		return titreOriginal;
	}
	public void setTitreOriginal(String titreOriginal) {
		this.titreOriginal = titreOriginal;
	}
	public Integer getDuree() {
		return duree;
	}
	public void setDuree(Integer duree) {
		this.duree = duree;
	}
	public String getResume() {
		return resume;
	}
	public void setResume(String resume) {
		this.resume = resume;
	}
	public Date getDateReal() {
		return dateReal;
	}
	public void setDateReal(Date dateReal) {
		this.dateReal = dateReal;
	}
	public Date getPrimodiffusion() {
		return primodiffusion;
	}
	public void setPrimodiffusion(Date primodiffusion) {
		this.primodiffusion = primodiffusion;
	}
	public Integer getIdPublic() {
		return idPublic;
	}
	public void setIdPublic(Integer idPublic) {
		this.idPublic = idPublic;
	}
	public Integer getIdStatut() {
		return idStatut;
	}
	public void setIdStatut(Integer idStatut) {
		this.idStatut = idStatut;
	}
	public Integer getIdSaison() {
		return idSaison;
	}
	public void setIdSaison(Integer idSaison) {
		this.idSaison = idSaison;
	}




}
