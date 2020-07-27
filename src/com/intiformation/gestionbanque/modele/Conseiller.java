package com.intiformation.gestionbanque.modele;

public class Conseiller {
	
	/* __________________________ props ____________________________ */
	private int idConseiller;
	private int idAdmin;
	private String nom;
	private String prenom;
	private String mail;
	private String telephone;
	private String identifiant;
	private String motDePasse;
	
	/* __________________________ ctors ____________________________ */
	public Conseiller() {
		
	}

	public Conseiller(int idConseiller, String nom, String prenom, String mail, String telephone, String identifiant,
			String motDePasse,int idAdmin) {

		this.idConseiller = idConseiller;
		this.idAdmin = idAdmin;
		this.nom = nom;
		this.prenom = prenom;
		this.mail = mail;
		this.telephone = telephone;
		this.identifiant = identifiant;
		this.motDePasse = motDePasse;
	}

	public Conseiller( String nom, String prenom, String mail, String telephone, String identifiant, String motDePasse, int idAdmin) {
		this.idAdmin = idAdmin;
		this.nom = nom;
		this.prenom = prenom;
		this.mail = mail;
		this.telephone = telephone;
		this.identifiant = identifiant;
		this.motDePasse = motDePasse;
	}
	
	public Conseiller(int idConseiller, String nom, String prenom, String mail, String telephone, String identifiant,
			String motDePasse) {

		this.idConseiller = idConseiller;
		this.nom = nom;
		this.prenom = prenom;
		this.mail = mail;
		this.telephone = telephone;
		this.identifiant = identifiant;
		this.motDePasse = motDePasse;
	}
	
	public Conseiller( String nom, String prenom, String mail, String telephone, String identifiant,
			String motDePasse) {
		this.nom = nom;
		this.prenom = prenom;
		this.mail = mail;
		this.telephone = telephone;
		this.identifiant = identifiant;
		this.motDePasse = motDePasse;
	}
	
	/* __________________________ meths ____________________________ */
	
	public int getIdConseiller() {
		return idConseiller;
	}

	public void setIdConseiller(int idConseiller) {
		this.idConseiller = idConseiller;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getIdentifiant() {
		return identifiant;
	}

	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	public int getIdAdmin() {
		return idAdmin;
	}

	public void setIdAdmin(int idAdmin) {
		this.idAdmin = idAdmin;
	}
	
	
	
	

}
