package com.intiformation.gestionbanque.modele;

/**
 * classe d�riv�e de Compte.
 * 
 * @author INTIFORMATION
 *
 */
public class CompteEpargne extends Compte {

	/* __________________________ props ____________________________ */
	// -> props sp�cifiques au Compte �pargne
	private double tauxInterets; // donne le taux d'int�r�ts du compte

	/* __________________________ ctors ____________________________ */
	/**
	 * ctor vide 
	 */
	public CompteEpargne() {
	}
	
	/**
	 * ctor charg�
	 */
	public CompteEpargne(int numeroCompte, int idTitulaire, double soldeCompte,double tauxInterets) {
		super(numeroCompte, idTitulaire, soldeCompte);
		this.tauxInterets = tauxInterets;
	}//end ctor charg� 

	public CompteEpargne(int numeroCompte, int idTitulaire, double soldeCompte) {
		super(numeroCompte, idTitulaire, soldeCompte );
		this.tauxInterets = 0.03;
	}//end ctor charg� 

	public CompteEpargne(int numeroCompte, double soldeCompte) {
		super(numeroCompte, soldeCompte );
		this.tauxInterets = 0.03;
	}//end ctor charg� 

	public CompteEpargne(int numeroCompte, double soldeCompte, double taux) {
		super(numeroCompte, soldeCompte );
		this.tauxInterets = taux;
	}//end ctor charg� 

	
	/* __________________________ m�thodes __________________________ */
	/**
	 * permet de calculer les int�r�ts acquis et les ajoute au solde du compte.
	 */
	public void calculerInteretsAcquis() {

		// 1. calcul du solde avec les taux d'int�r�ts
		double nouveauSolde = this.getSoldeCompte() * (1 + tauxInterets);

		// 2. affectation du nouveau solde (mise � jour du solde)
		this.setSoldeCompte(nouveauSolde);

	}// end calculerInteretsAcquis()
	
	
	/**
	 * red�finition de la m�thode de Compte. 
	 * permet d'afficher les infos du compte �pargne avec le taux. 
	 */
	@Override
	public void consulterEtatCompte() {
		super.consulterEtatCompte();
		System.out.println("\t > Taux d'int�rpets : " + this.tauxInterets);
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
		return "\t> CompteEpargne n� "+ super.getNumeroCompte()
				+" - solde : "+super.getSoldeCompte()
				+" - ID Titulaire : "+ super.getIdTitulaire() 
				+" - taux d'interet : " +this.tauxInterets;
	}
	
	
}// end class
