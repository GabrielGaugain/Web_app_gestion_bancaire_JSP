package com.intiformation.gestionbanque.modele;

public class Administrateur {
	
	/* __________________________ props ____________________________ */
	private int idAdmin;
	private String nom;
	private String prenom;
	private String mail;
	private String telephone;
	private String identifiant;
	private String motDePasse;
	
	/* __________________________ ctors ____________________________ */
	public Administrateur() {
		
	}//end ctor vide

	public Administrateur(int idAdmin, String nom, String prenom, String mail, String telephone, String identifiant,
			String motDePasse) {
		super();
		this.idAdmin = idAdmin;
		this.nom = nom;
		this.prenom = prenom;
		this.mail = mail;
		this.telephone = telephone;
		this.identifiant = identifiant;
		this.motDePasse = motDePasse;
	}

	public Administrateur(String nom, String prenom, String mail, String telephone, String identifiant,
			String motDePasse) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.mail = mail;
		this.telephone = telephone;
		this.identifiant = identifiant;
		this.motDePasse = motDePasse;
	}

	/* __________________________ meths ____________________________ */
	public int getIdAdmin() {
		return idAdmin;
	}

	public void setIdAdmin(int idAdmin) {
		this.idAdmin = idAdmin;
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

	

}
