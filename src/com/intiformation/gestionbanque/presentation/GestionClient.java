package com.intiformation.gestionbanque.presentation;

import com.intiformation.gestionbanque.service.*;

import java.util.List;

import com.intiformation.gestionbanque.dao.*;
import com.intiformation.gestionbanque.modele.*;
import com.intiformation.gestionbanque.tool.SaisieTool;

/**
 * Permet la gestion des clients via menus-choix-opérations
 * 
 * @author gabri
 *
 */
public class GestionClient {

	public IClientService clientService;

	public GestionClient() {
		clientService = new ClientServiceImpl();
	}// end ctor

	public void creerMenu() {

		int choixOp = 0;

		while (choixOp != 6) {

			System.out.println("\t [1] - Ajouter un client ");
			System.out.println("\t [2] - Supprimer un client");
			System.out.println("\t [3] - Modifier un client");
			System.out.println("\t [4] - Afficher la liste des clients");
			System.out.println("\t [5] - Chercher un client");
			System.out.println("\n\t [6] - Quitter\n");

			// lecture du choix
			choixOp = SaisieTool.lireInt("Choisir le type d'opération ?");

			switch (choixOp) {

			// choix 1: ajout d'un client
			case 1:
				// Lecture clavier de chacun des attributs
				
					String nom = SaisieTool.lireString("- Nom du client :");
					String prenom = SaisieTool.lireString("- Prenom du client");
					String adresse = SaisieTool.lireString("- Adresse du client");
					String cp = SaisieTool.lireString("- Code Postal du client");
					String ville = SaisieTool.lireString("- Ville du client");
					String tel = SaisieTool.lireString("- Téléphone du client");
					Client newClient = new Client(nom, prenom, adresse, cp, ville, tel);
					boolean verif =clientService.ajouterClient(newClient);
					if (!verif) {
						System.out.println("\n----------------------------------");
						System.out.println("ERREUR LORS DE L'AJOUT D'UN CLIENT");
						System.out.println("----------------------------------\n");

					}
				
				break;

			// choix 2: suppresion d'un client
			case 2:
				// lecture clavier de l'id

				int id = SaisieTool.lireInt("Entrer l'identifiant du client à supprimer");
				verif = clientService.supprimerClient(id);
				if (!verif) {
					System.out.println("\n-----------------------------------------");
					System.out.println("ERREUR LORS DE LA SUPPRESSION D'UN CLIENT");
					System.out.println("-----------------------------------------\n");

				}


				break;

			// choix 3: modif d'un client
			case 3:

				// lecture clavier du client à modifier
				id = SaisieTool.lireInt("ID du client à modifier :");
				nom = SaisieTool.lireString("- Nom du client :");
				prenom = SaisieTool.lireString("- Prenom du client");
				adresse = SaisieTool.lireString("- Adresse du client");
				cp = SaisieTool.lireString("- Code Postal du client");
				ville = SaisieTool.lireString("- Ville du client");
				tel = SaisieTool.lireString("- Téléphone du client");
				Client client = new Client(id, nom, prenom, adresse, cp, ville, tel);
				verif = clientService.modifierClient(client);
				if (!verif) {
					System.out.println("\n-----------------------------------");
					System.out.println("ERREUR LORS DE LA MODIF D'UN CLIENT");
					System.out.println("-----------------------------------\n");

				}
				break;

			// choix 4: affichage des clients
			case 4:
				try {

					List<Client> listeClients = clientService.findAllClients();
					System.out.println(" Liste Des Clients ||||||||||||||||||||||||||||||||||||||||");
					listeClients.forEach(c -> System.out.println(c));
					System.out.println("|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||\n");
				} catch (Exception e) {
					System.out.println("\n--------------------------------------------------");
					System.out.println("ERREUR LORS DE L'AFFICHAGE DE LA LISTE DES CLIENTS");
					System.out.println("--------------------------------------------------\n");

				}
				break;

			// choix 5: recherche d'un client
			case 5:
				try {
					id = SaisieTool.lireInt("Entrer l'identifiant du client recherché :");
					client = clientService.findClientById(id);
					System.out.println("Client |||||||||||||||||||||||||||||||||||||||||||");
					System.out.println(client);
					System.out.println("|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||\n");
				} catch (Exception e) {
					System.out.println("\n----------------------------------------------");
					System.out.println("ERREUR LORS DE LA RECUP D'UN CLIENT PAR SON ID");
					System.out.println("----------------------------------------------\n");

				}

				break;

			// choix 6: retour au menu principal (quitter)
			case 6:
				break;

			}// end switch

		} // end while

	}// end creerMenu

}// end GestionClient
