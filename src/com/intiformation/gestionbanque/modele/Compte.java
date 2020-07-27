package com.intiformation.gestionbanque.modele;

import com.intiformation.gestionbanque.tool.SaisieTool;

/**
 * classe de base (super classe) pour tout type de compte.
 * 
 * @author INTIFORMATION
 *
 */
public class Compte {

	/* _________________ props ____________________ */
	private int numeroCompte;
	private int idTitulaire;
	private double soldeCompte;


	/* _________________ ctors ____________________ */
	/**
	 * ctor vide
	 */
	public Compte() {
	}

	/**
	 * ctor charg�
	 * 
	 * @param numeroCompte
	 * @param nomTitulaire
	 * @param soldeCompte
	 */
	public Compte(int numeroCompte, int idTitulaire, double soldeCompte) {
		this.numeroCompte = numeroCompte;
		this.idTitulaire = idTitulaire;
		this.soldeCompte = soldeCompte;

	}
	
	public Compte(int numeroCompte, double soldeCompte) {
		this.numeroCompte = numeroCompte;
		this.idTitulaire = 0;
		this.soldeCompte = soldeCompte;

	}


	/* _________________ m�thodes _________________ */

	

	/**
	 * permet de d�poser de l'argent sur tout type de compte
	 */
	public void deposerArgent(double sommeAdeposer) {
		
		/*//////////// AVANT L'UTILITAIRE SaisieTool////////////////////////////*/
		/*
		// 1. affichage d'un message � l'utilisateur pour saisir la somme � d�poser
		System.out.println("Veuillez saisir la somme � d�poser sur le compte : ");

		// 2. r�cup de la somme saisie par l'utilisateur au clavier via la classe
		// Scanner
		// 2.1. cr�ation d'un objet Scanner
		Scanner scanner = new Scanner(System.in);

		// 2.2. r�cup de la somme saisie via le scanner
		double sommeAdeposer = scanner.nextDouble();
		*/
		
		/*//////////// AVEC L'UTILITAIRE SaisieTool////////////////////////////*/
		if (sommeAdeposer==0) {
			// 1) affichage d'un message + 2)r�cup de la somme 
			sommeAdeposer = SaisieTool.lireDouble("Veuillez saisir la somme � d�poser sur le compte :");

		}
		
		// 3. ajout de la somme au solde
		soldeCompte = sommeAdeposer + soldeCompte;

		// 4. affichage d'un message de d�pot � l'utilisateur
		System.out.println("\nInfos D�pots -------------------------------------------------");
		System.out.println("\t > Somme d�pos�e : " + sommeAdeposer + " �");
		System.out.println("\t > Nouveau Solde : " + soldeCompte + " �");
		System.out.println("----------------------------------------------------------------\n");

	}// end deposerArgent()

	/**
	 * permet de retirer de l'argent � partir de n'importe quel type de compte
	 * jusqu'au niveau autoris� par le d�couvert.
	 */
	public double retirerArgent(double sommeAretier) {
		
		/*//////////// AVANT L'UTILITAIRE SaisieTool////////////////////////////*/
		/*
		// 1. affichage d'un message � l'utilisateur pour saisir la somme � retirer 
		System.out.println("Veuillez saisir la somme � retirer du compte : ");
		
		// 2. r�cup de la saisie via le Scanner 
		// 2.1. l'objet Scanner 
		Scanner scanner = new Scanner(System.in);
		// 2.2. r�cup de la somme saisie 
		double sommeAretier = scanner.nextDouble();
		*/
		
		/*//////////// AVEC L'UTILITAIRE SaisieTool////////////////////////////*/
		if (sommeAretier==0) {
			// 1) message et 2)r�cup de la sasie  
			sommeAretier = SaisieTool.lireDouble("Veuillez saisir la somme � retirer du compte : ");

		}
		
		// 3. r�cup du niveau autoris� par le d�couvert 
		double decouvertAutorise = this.getSeuil();
		
		// 4. retrait 
		if (soldeCompte - sommeAretier > decouvertAutorise) {
			//-> cas 1 : possibilit� de retrait
			
			// 4.1. retrait de la somme du compte 
			soldeCompte = soldeCompte - sommeAretier;
			
			// 4.2. message 
			System.out.println("\nInfos Retrait ----------------------------------------------");
			System.out.println("\t > Retrait : " + sommeAretier + " �");
			System.out.println("\t > Nouveau solde : " + soldeCompte + " �");
			System.out.println("--------------------------------------------------------------\n");
			return sommeAretier;
			
		} else {
			//-> cas 2 : interdiction de retrait 
			
			// 4.1.bis. message 
			System.out.println("\n---------------------------------------------------------------");
			System.out.println("\t GRAVE : Vous n'�tes pas autoris� � faire ce retrait");
			System.out.println("-----------------------------------------------------------------\n");
			
		}//end else 
		return 0;
		
	}// end retirerArgent()
	
	/**
	 * renvoie le niveau autoris� par le d�couvert.
	 * @return
	 */
	public double getSeuil() {
		return 0;
	}//end getSeuille()

	/**
	 * permet de consulter l'�tat de de n'importe quel type de compte. consulter =
	 * afficher les infos du compte
	 */
	public void consulterEtatCompte() {

		System.out.println("\nINFOS COMPTE --------------------------------------------");
		System.out.println("\t > Titulaire : " + this.idTitulaire);
		System.out.println("\t > Num�ro de compte : " + this.numeroCompte);
		System.out.println("\t > Solde : " + this.soldeCompte + " �");

	}// end consulterEtatCompte()

	/* _________________ getters/setters _________________ */

	public int getNumeroCompte() {
		return numeroCompte;
	}

	public void setNumeroCompte(int numeroCompte) {
		this.numeroCompte = numeroCompte;
	}

	public int getIdTitulaire() {
		return idTitulaire;
	}

	public void setIdTitulaire(int idTitulaire) {
		this.idTitulaire = idTitulaire;
	}

	public double getSoldeCompte() {
		return soldeCompte;
	}

	public void setSoldeCompte(double soldeCompte) {
		this.soldeCompte = soldeCompte;
	}


}// end class
