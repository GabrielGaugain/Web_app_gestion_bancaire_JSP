package com.intiformation.gestionbanque.modele;

/**
 * classe fille de Compte.
 * 
 * @author INTIFORMATION
 *
 */
public class CompteCourant extends Compte {

	/* __________________________ props ____________________________ */
	// découvert autorisé par la banque
	private double decouvertAutorise;

	/* __________________________ ctors ____________________________ */
	/**
	 * ctor vide
	 */
	public CompteCourant() {

	}

	/**
	 * ctor chargé
	 */
	public CompteCourant(int numeroCompte, int idTitulaire, double soldeCompte, double decouvertAutorise) {
		super(numeroCompte, idTitulaire, soldeCompte);
		this.decouvertAutorise = decouvertAutorise;
	}// end ctor chargé

	public CompteCourant(int numeroCompte, int idTitulaire, double soldeCompte) {
		super(numeroCompte, idTitulaire, soldeCompte);
		this.decouvertAutorise = 100;
	}// end ctor chargé
	
	public CompteCourant(int numeroCompte, double soldeCompte) {
		super(numeroCompte, soldeCompte);
		this.decouvertAutorise = 100;
	}// end ctor chargé
	
	public CompteCourant(int numeroCompte, double soldeCompte, double decouvert) {
		super(numeroCompte, soldeCompte);
		this.decouvertAutorise = decouvert;
	}// end ctor chargé
	
	/* __________________________ méthodes _________________________ */

	/**
	 * redéfinition de la méthode de classe Compte
	 */
	@Override
	public void consulterEtatCompte() {
		super.consulterEtatCompte();
		System.out.println("\t > Découvert autorisé : " + this.decouvertAutorise + " €");
		System.out.println("-----------------------------------------------------------------\n");
	}// end consulterEtatCompte
	
	/**
	 * redéfinition de la méthode de Compte.
	 * renvoie le découvert autorisé par la banque
	 */
	@Override
	public double getSeuil() {
		return decouvertAutorise;
	}//end getSeuil()
	

	/* __________________________ getters/setters __________________ */

	

	public double getDecouvertAutorise() {
		return decouvertAutorise;
	}

	public void setDecouvertAutorise(double decouvertAutorise) {
		this.decouvertAutorise = decouvertAutorise;
	}

	@Override
	public String toString() {
		return "\t> CompteCourant n° "+ super.getNumeroCompte()
				+" - solde : "+super.getSoldeCompte()
				+" - ID Titulaire : "+ super.getIdTitulaire() 
				+" - decouvertAutorise : " +this.decouvertAutorise;
	}

	
}// end class
