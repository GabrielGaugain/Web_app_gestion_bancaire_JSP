package com.intiformation.gestionbanque.presentation;

import com.intiformation.gestionbanque.service.*;
import com.intiformation.gestionbanque.dao.*;
import com.intiformation.gestionbanque.tool.SaisieTool;

public class AppGestionComptes {

	public static void main(String[] args) {

		GestionClient gestionClient = new GestionClient();
		GestionCompte gestionCompte = new GestionCompte();
		boolean authentification = false;

		while (authentification == false) {

			System.out.println("PORTAIL D'AUTHENTIFICATION CONSEILLER :");
			System.out.println("---------------------------------------");

			String identifiant = SaisieTool.lireString("\t >Veuillez saisir votre identifiant :");
			String mdp = SaisieTool.lireString("\t >Veuillez saisir votre mot de passe :");

			// verif si le conseiller à la droit de se connecter
			/**
			 * le droit de se connecter = l'existence du conseiller dans bdd
			 */
			// -> instanciation du service
			IConseillerService conseillerService = new ConseillerServiceImpl();

			if (!conseillerService.isConseillerExists(identifiant, mdp)) {
				System.out.println(
						"\nERREUR LORS DE L'AUTHENTIFICATION DU CONSEILLER : mot de passe ou identifiant incorrect.");
				System.out.println(
						"---------------------------------------------------------------------------------------.\n");
				
				int reconnection = SaisieTool.lireInt("Voulez-vous essayer de vous reconnecter ? (1 pour oui / 0 pour non) \n" );
				if (reconnection != 1) {
					break;
				}
				
			} else {
				authentification = conseillerService.isConseillerExists(identifiant, mdp);

			} // end if

		} // end while
			// -> verif de l'existence de l'utilisateur dans la bdd via le service

		if (authentification) {
			// ------ l'utilisateur existe dans bdd ----------------//
			int choice = 0;

			while (choice != 3) {
				System.out.println("TABLEAU DE BORD CONSEILLER :");
				System.out.println("\t [1] - Gestion Clients");
				System.out.println("\t [2] - Gestion Comptes");
				System.out.println("\t [3] - Quitter ");

				choice = SaisieTool.lireInt("Choix de gestion ?\n");

				// 1. Gestion clients--------------------------------------------------
				if (choice == 1) {

					gestionClient.creerMenu();

				} // endif

				// 2. Gestion Comptes ------------------------------------------------
				if (choice == 2) {

					gestionCompte.creerMenu();

				} // endif

				// 3. Quitter -------------------------------------------------------------
				if (choice == 3) {
					// On quitte la boucle while -> fin de l'appli
					System.out.println("AU REVOIR ...");
					break;
				} // end if

			} // end while

		} else {
			// ----- Conseiller n'existe pas ou mdp incorrect -> pas le droit de se co
			System.out.println("\t ACCES NON AUTHORISEE, AU REVOIR ...");

		} // end if

	}// end main

}// end classe
