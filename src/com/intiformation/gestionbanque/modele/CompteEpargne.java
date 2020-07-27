package com.intiformation.gestionbanque.modele;

/**
 * classe dérivée de Compte.
 * 
 * @author INTIFORMATION
 *
 */
public class CompteEpargne extends Compte {

	/* __________________________ props ____________________________ */
	// -> props spécifiques au Compte épargne
	private double tauxInterets; // donne le taux d'intérêts du compte

	/* __________________________ ctors ____________________________ */
	/**
	 * ctor vide 
	 */
	public CompteEpargne() {
	}
	
	/**
	 * ctor chargé
	 */
	public CompteEpargne(int numeroCompte, int idTitulaire, double soldeCompte,double tauxInterets) {
		super(numeroCompte, idTitulaire, soldeCompte);
		this.tauxInterets = tauxInterets;
	}//end ctor chargé 

	public CompteEpargne(int numeroCompte, int idTitulaire, double soldeCompte) {
		super(numeroCompte, idTitulaire, soldeCompte );
		this.tauxInterets = 0.03;
	}//end ctor chargé 

	public CompteEpargne(int numeroCompte, double soldeCompte) {
		super(numeroCompte, soldeCompte );
		this.tauxInterets = 0.03;
	}//end ctor chargé 

	public CompteEpargne(int numeroCompte, double soldeCompte, double taux) {
		super(numeroCompte, soldeCompte );
		this.tauxInterets = taux;
	}//end ctor chargé 

	
	/* __________________________ méthodes __________________________ */
	/**
	 * permet de calculer les intérêts acquis et les ajoute au solde du compte.
	 */
	public void calculerInteretsAcquis() {

		// 1. calcul du solde avec les taux d'intérêts
		double nouveauSolde = this.getSoldeCompte() * (1 + tauxInterets);

		// 2. affectation du nouveau solde (mise à jour du solde)
		this.setSoldeCompte(nouveauSolde);

	}// end calculerInteretsAcquis()
	
	
	/**
	 * redéfinition de la méthode de Compte. 
	 * permet d'afficher les infos du compte épargne avec le taux. 
	 */
	@Override
	public void consulterEtatCompte() {
		super.consulterEtatCompte();
		System.out.println("\t > Taux d'intérpets : " + this.tauxInterets);
		System.out.println("--------------------------------------------------------------\n");
	}//end consulterEtatCompte()
	

	/* __________________________ getters/setters _________________ */
	
	public double getTauxInterets() {
		return tauxInterets;
	}

	public void setTauxInterets(double tauxInterets) {
		this.tauxInterets = tauxInterets;
	}

	@Override
	public String toString() {
		return "\t> CompteEpargne n° "+ super.getNumeroCompte()
				+" - solde : "+super.getSoldeCompte()
				+" - ID Titulaire : "+ super.getIdTitulaire() 
				+" - taux d'interet : " +this.tauxInterets;
	}
	
	
}// end class
