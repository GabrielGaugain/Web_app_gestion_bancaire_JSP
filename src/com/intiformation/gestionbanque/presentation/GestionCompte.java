package com.intiformation.gestionbanque.presentation;

import com.intiformation.gestionbanque.service.*;

import java.util.List;

import com.intiformation.gestionbanque.dao.*;
import com.intiformation.gestionbanque.modele.CompteCourant;
import com.intiformation.gestionbanque.modele.CompteEpargne;
import com.intiformation.gestionbanque.tool.SaisieTool;

/**
 * Permet la gestion des comptes via menu-choix op�rations
 * @author gabri
 *
 */
public class GestionCompte {

	
	public  ICompteCourantService compteCourantService;
	public  ICompteEpargneService compteEpargneService;
	
	public GestionCompte(){
		compteCourantService = new CompteCourantServiceImpl();
		compteEpargneService = new CompteEpargneServiceImpl();
	}// end ctor
	
	
	public void creerMenu() {

		int choixOp = 0;
		int choix_compte=0;
		
		while (choixOp != 11) {
			System.out.println("\t [1] - Ajouter un compte ");
			System.out.println("\t [2] - Supprimer un compte");
			System.out.println("\t [3] - Modifier un compte");
			System.out.println("\t [4] - Afficher tous les comptes");
			System.out.println("\t [5] - Chercher un compte par son id");
			System.out.println("\t [6] - Chercher un compte par l'id de son propri�taire");
			System.out.println("\t [7] - Attribuer un compte � un client ");
			System.out.println("\t [8] - Retirer de l'argent d'un compte");
			System.out.println("\t [9] - D�poser de l'argent sur un compte");
			System.out.println("\t [10] - Virement compte � compte");
			System.out.println("\n\t [11] - Quitter\n");
			
			choixOp = SaisieTool.lireInt("Choisir le type d'op�ration ?");

			switch( choixOp) {
			
			// choix 1: ajout d'un compte---------------------------------------------------------------------------
			case 1:
				while (choix_compte !=3) {
					System.out.println("\t [1] - Ajouter un compte courant ");
					System.out.println("\t [2] - Ajouter un compte epargne");
					System.out.println("\n\t [3] - Quitter");
					
					choix_compte= SaisieTool.lireInt("Faire un choix ?");
					
					if (choix_compte==1) {
						
						int numC = SaisieTool.lireInt("- Numero de compte :");
						double solde = SaisieTool.lireDouble("- Solde du compte :");
						double decouvert = SaisieTool.lireDouble("- Decouvert autoris� du compte (saisir 0 pour montant par d�faut de 100 euros) :");
						if (decouvert==0) {decouvert = 100;}
						CompteCourant compte = new CompteCourant(numC, solde, decouvert);
						compteCourantService.ajouterCompteCourant(compte);
						System.out.println("===========================================================");
						System.out.println("\t => INFO : Le compte courant � �t� ajout� avec succ�s ");
						System.out.println("===========================================================\n");
						break;
					}
					
					if (choix_compte==2) {
						
						int numC = SaisieTool.lireInt("- Numero de compte :");
						double solde = SaisieTool.lireDouble("- Solde du compte :");
						double taux = SaisieTool.lireDouble("- Taux d'int�rets du compte (0 pour taux par d�faut de 3%) :");
						if (taux ==0) {taux = 0.03;}
						CompteEpargne compte = new CompteEpargne(numC, solde, taux);
						compteEpargneService.ajouterCompteEpargne(compte);
						System.out.println("===========================================================");
						System.out.println("\t => INFO : Le compte �pargne � �t� ajout� avec succ�s ");
						System.out.println("===========================================================\n");
						break;
						
					}
					if (choix_compte==3) {
						break;
					}

					
				}//end while
				
				break;
				
			// choix 2: suppresion d'un compte----------------------------------------------------------------------
			case 2:
				while (choix_compte !=3) {
					System.out.println("\t [1] - Supprimer un compte courant ");
					System.out.println("\t [2] - Supprimer un compte epargne");
					System.out.println("\n\t [3] - Quitter");
					
					choix_compte= SaisieTool.lireInt("Faire un choix ?");
					
					if (choix_compte==1) {
						
						int numC = SaisieTool.lireInt("- Numero de compte :");

						compteCourantService.supprimerCompteCourant(numC);
						System.out.println("===========================================================");
						System.out.println("\t => INFO : Le compte courant � �t� supprim� avec succ�s ");
						System.out.println("===========================================================\n");
						break;
					}
					
					if (choix_compte==2) {
						
						int numC = SaisieTool.lireInt("- Numero de compte :");
						
						compteEpargneService.supprimerCompteEpargne(numC);
						System.out.println("===========================================================");
						System.out.println("\t => INFO : Le compte �pargne � �t� ajout� avec succ�s ");
						System.out.println("===========================================================\n");
						break;
						
					}
					if (choix_compte==3) {
						break;
					}

					
				}//end while
				
				break;
				
			// choix 3: modif d'un compte-------------------------------------------------------------------------
			case 3:
				
				while (choix_compte !=3) {
					System.out.println("\t [1] - Modifier un compte courant ");
					System.out.println("\t [2] - Modifier un compte epargne");
					System.out.println("\n\t [3] - Quitter");
					
					choix_compte= SaisieTool.lireInt("Faire un choix ?");
					
					if (choix_compte==1) {
						
						int numC = SaisieTool.lireInt("- Numero de compte � modifier :");
						double solde = SaisieTool.lireDouble("- Nouveau Solde du compte :");
						double decouvert = SaisieTool.lireDouble("- Nouveau Decouvert autoris� du compte (saisir 0 pour montant par d�faut de 100 euros) :");
						int idProprio = SaisieTool.lireInt("- Nouveau Propri�taire du compte :");
						
						if (decouvert==0) {decouvert = 100;}
						CompteCourant compte = new CompteCourant(numC, idProprio, solde, decouvert);
						compteCourantService.modifierCompteCourant(compte);
						System.out.println("===========================================================");
						System.out.println("\t => INFO : Le compte courant � �t� modifi� avec succ�s ");
						System.out.println("===========================================================\n");
						break;
					}
					
					if (choix_compte==2) {
						
						int numC = SaisieTool.lireInt("- Numero de compte � modifier :");
						double solde = SaisieTool.lireDouble("- Nouveau solde du compte :");
						double taux = SaisieTool.lireDouble("- Nouveau taux d'int�rets du compte (0 pour taux par d�faut de 3%) :");
						int idProprio = SaisieTool.lireInt("- Nouveau Propri�taire du compte :");
						
						if (taux ==0) {taux = 0.03;}
						CompteEpargne compte = new CompteEpargne(numC, idProprio, solde, taux);
						compteEpargneService.modifierCompteEpargne(compte);
						System.out.println("===========================================================");
						System.out.println("\t => INFO : Le compte �pargne � �t� modifi� avec succ�s ");
						System.out.println("===========================================================\n");
						break;
						
					}
					if (choix_compte==3) {
						break;
					}

					
				}//end while

				break;
				
			
			// choix 4: affichage des comptes---------------------------------------------------------------------------
			case 4:
				List<CompteCourant> listeCC = compteCourantService.findAllCompteCourants();
				List<CompteEpargne> listeCE = compteEpargneService.findAllCompteEpargnes();
				System.out.println(" Liste Des Comptes ========================================");
				System.out.println("\n-> Comptes Courants : -------------------------------------");
				listeCC.forEach(c-> System.out.println(c) );
				System.out.println("\n-> Comptes Epargnes : -------------------------------------");
				listeCE.forEach(c-> System.out.println(c) );
				System.out.println("===========================================================\n");
				break;
			
				
			// choix 5: recherche d'un compte (par id)-------------------------------------------------------------------
			case 5:

				while (choix_compte !=3) {
					System.out.println("\t [1] - Chercher un compte courant ");
					System.out.println("\t [2] - Chercher un compte epargne");
					System.out.println("\n\t [3] - Quitter");
					
					choix_compte= SaisieTool.lireInt("Faire un choix ?");
					
					if (choix_compte==1) {
						
						int numC = SaisieTool.lireInt("- Numero de compte cherch� :");

						CompteCourant compte = compteCourantService.findCompteCourantById(numC);
						System.out.println("===========================================================");
						System.out.println(compte);
						System.out.println("===========================================================\n");
						break;
					}
					
					if (choix_compte==2) {
						
						int numC = SaisieTool.lireInt("- Numero de compte cherch� :");
						
						CompteEpargne compte =compteEpargneService.findCompteEpargneById(numC);
						System.out.println("===========================================================");
						System.out.println(compte);
						System.out.println("===========================================================\n");
						break;
						
					}
					if (choix_compte==3) {
						break;
					}

					
				}//end while
				
				
				break;
				
			// choix 6: recherche d'un compte (par idProprio)------------------------------------------------------------
			case 6:
				
				while (choix_compte !=3) {
					System.out.println("\t [1] - Chercher un compte courant par l'identifiant de son propri�taire ");
					System.out.println("\t [2] - Chercher un compte epargne par l'identifiant de son propri�taire");
					System.out.println("\n\t [3] - Quitter");
					
					choix_compte= SaisieTool.lireInt("Faire un choix ?");
					
					if (choix_compte==1) {
						
						int id = SaisieTool.lireInt("- Identifiant du propri�taire :");

						CompteCourant compte = compteCourantService.findCompteCourantByOwnerId(id);
						System.out.println("===========================================================");
						System.out.println(compte);
						System.out.println("===========================================================\n");
						break;
					}
					
					if (choix_compte==2) {
						
						int id = SaisieTool.lireInt("- Identifiant du propri�taire :");
						
						CompteEpargne compte =compteEpargneService.findCompteEpargneByOwnerId(id);
						System.out.println("===========================================================");
						System.out.println(compte);
						System.out.println("===========================================================\n");
						break;
						
					}
					if (choix_compte==3) {
						break;
					}

					
				}//end while				
				
				break;
				
			// choix 7: attribution compte a un client
			case 7:
				
				while (choix_compte !=3) {
					System.out.println("\t [1] - Attribuer un compte courant � un propri�taire ");
					System.out.println("\t [2] - Attribuer un compte epargne � un propri�taire");
					System.out.println("\n\t [3] - Quitter");
					
					choix_compte= SaisieTool.lireInt("Faire un choix ?");
					
					if (choix_compte==1) {
						
						int numC = SaisieTool.lireInt("- Numero de compte � attribuer :");
						CompteCourant compte = compteCourantService.findCompteCourantById(numC);
										
						int id = SaisieTool.lireInt("- Identifiant du propri�taire � associer au compte :");
						compte.setIdTitulaire(id);
						
						// mise a jour du compte dans la bdd
						compteCourantService.modifierCompteCourant(compte);
						
						
						System.out.println("===========================================================");
						System.out.println(compte);
						System.out.println("===========================================================\n");
						break;
					}
					
					if (choix_compte==2) {
						
						int numC = SaisieTool.lireInt("- Numero de compte � attribuer :");
						CompteEpargne compte = compteEpargneService.findCompteEpargneById(numC);
	
						
						int id = SaisieTool.lireInt("- Identifiant du propri�taire � associer au compte :");
						compte.setIdTitulaire(id); 
						// mise a jour du compte dans la bdd
						compteEpargneService.modifierCompteEpargne(compte);

						System.out.println("===========================================================");
						System.out.println(compte);
						System.out.println("===========================================================\n");
						break;
						
					}
					if (choix_compte==3) {
						break;
					}
				
				}//end while								
				
				break;
				
			// choix 8: retirer argent d'un compte
			case 8:
				
				while (choix_compte !=3) {
					System.out.println("\t [1] - Retirer de l'argent d'un compte courant");
					System.out.println("\t [2] - Retirer de l'argent d'un compte �pargne");
					System.out.println("\n\t [3] - Quitter");
					
					choix_compte= SaisieTool.lireInt("Faire un choix ?");
					
					if (choix_compte==1) {
						
						int numC = SaisieTool.lireInt("- Numero du compte :");
						CompteCourant compte = compteCourantService.findCompteCourantById(numC);										
						compte.retirerArgent(0);
						
						// on update le compte � la bdd
						compteCourantService.modifierCompteCourant(compte);
						
						System.out.println("===========================================================");
						System.out.println("\t Retrait r�ussi : le montant � �t� r�tir� au solde.");
						System.out.println("===========================================================\n");
						break;
					}
					
					if (choix_compte==2) {
						
						int numC = SaisieTool.lireInt("- Numero de compte :");
						CompteEpargne compte = compteEpargneService.findCompteEpargneById(numC);
						compte.retirerArgent(0);
						
						// update du compte � la bdd
						compteEpargneService.modifierCompteEpargne(compte);
						
						System.out.println("===========================================================");
						System.out.println("\t Retrait r�ussi : le montant � �t� r�tir� au solde.");
						System.out.println("===========================================================\n");
						break;
						
					}
					if (choix_compte==3) {
						break;
					}
				
				}//end while												
				
				break;
				
			// choix 9: d�poser argent sur un compte
			case 9:
				
				while (choix_compte !=3) {
					System.out.println("\t [1] - D�poser de l'argent sur un compte courant");
					System.out.println("\t [2] - D�poser de l'argent sur un compte �pargne");
					System.out.println("\n\t [3] - Quitter");
					
					choix_compte= SaisieTool.lireInt("Faire un choix ?");
					
					if (choix_compte==1) {
						
						int numC = SaisieTool.lireInt("- Numero du compte :");
						CompteCourant compte = compteCourantService.findCompteCourantById(numC);										
						compte.deposerArgent(0);
						
						// on update le compte � la bdd
						compteCourantService.modifierCompteCourant(compte);
						
						System.out.println("===========================================================");
						System.out.println("\t D�pot r�ussi : le montant � �t� ajout� au solde.");
						System.out.println("===========================================================\n");
						break;
					}
					
					if (choix_compte==2) {
						
						int numC = SaisieTool.lireInt("- Numero de compte :");
						CompteEpargne compte = compteEpargneService.findCompteEpargneById(numC);
						compte.deposerArgent(0);
						
						// update du compte � la bdd
						compteEpargneService.modifierCompteEpargne(compte);
						
						System.out.println("===========================================================");
						System.out.println("\t D�pot r�ussi : le montant � �t� ajout� au solde.");
						System.out.println("===========================================================\n");
						break;
						
					}
					if (choix_compte==3) {
						break;
					}
				
				}//end while												
				
				break;
				
			
			// choix 10: virement compte � compte
			case 10:
				
				int choix_compte1=0;
				int choix_compte2=0;
				
				while (choix_compte1 !=3) {
					System.out.println("\t [1] - Virer de l'argent d'un compte courant");
					System.out.println("\t [2] - Virer de l'argent d'un compte �pargne");
					System.out.println("\n\t [3] - Quitter");
					
					choix_compte1= SaisieTool.lireInt("Faire un choix ?");
					
					if (choix_compte1==1) {
						
						
						int numDebiteur = SaisieTool.lireInt("- Numero du compte d�biteur :");
						double montant = SaisieTool.lireDouble("- Veuillez saisir le montant � virer du compte n� "+numDebiteur );
						
						CompteCourant compte1 = compteCourantService.findCompteCourantById(numDebiteur);	
						compte1.retirerArgent(montant);
						

						
						while (choix_compte2 !=3) {
							System.out.println("\t [1] - Virer de l'argent sur un compte courant");
							System.out.println("\t [2] - Virer de l'argent sur un compte �pargne");

							choix_compte2= SaisieTool.lireInt("\nFaire un choix ?");
							
							if (choix_compte2==1) {
								int numReceveur = SaisieTool.lireInt("- Numero du compte receveur :");
								CompteCourant compte2 = compteCourantService.findCompteCourantById(numReceveur);
								compte2.deposerArgent(montant);
								compteCourantService.modifierCompteCourant(compte2);
								break;
							}
							else if (choix_compte2==2) {
								int numReceveur = SaisieTool.lireInt("- Numero du compte receveur :");
								CompteEpargne compte2 = compteEpargneService.findCompteEpargneById(numReceveur);
								compte2.deposerArgent(montant);
								compteEpargneService.modifierCompteEpargne(compte2);
								break;
							}
							else {
								System.out.println("Warning : Vous n'avez pas entr� un choix correct -> entrez 1 ou 2.");
							}
		
						}// end while
						
						// on update de la bdd
						compteCourantService.modifierCompteCourant(compte1);
						
						System.out.println("===========================================================");
						System.out.println("\t Virement r�ussi : le montant � �t� vir�.");
						System.out.println("===========================================================\n");
						break;
					}// end if 
					
					if (choix_compte1==2) {
						
						int numDebiteur = SaisieTool.lireInt("- Numero du compte d�biteur :");
						double montant = SaisieTool.lireDouble("- Veuillez saisir le montant � virer du compte n� "+numDebiteur );
						
						CompteEpargne compte1 = compteEpargneService.findCompteEpargneById(numDebiteur);	
						compte1.retirerArgent(montant);

						while (choix_compte2 !=3) {
							System.out.println("\t [1] - Virer de l'argent sur un compte courant");
							System.out.println("\t [2] - Virer de l'argent sur un compte �pargne");
							
							choix_compte2= SaisieTool.lireInt("\nFaire un choix ?");
							
							if (choix_compte2==1) {
								int numReceveur = SaisieTool.lireInt("- Numero du compte receveur :");
								CompteCourant compte2 = compteCourantService.findCompteCourantById(numReceveur);
								compte2.deposerArgent(montant);
								compteCourantService.modifierCompteCourant(compte2);
								break;
							}
							else if (choix_compte2==2) {
								int numReceveur = SaisieTool.lireInt("- Numero du compte receveur :");
								CompteEpargne compte2 = compteEpargneService.findCompteEpargneById(numReceveur);
								compte2.deposerArgent(montant);
								compteEpargneService.modifierCompteEpargne(compte2);
								break;
							}
							else {
								System.out.println("Warning : Vous n'avez pas entr� un choix correct -> entrez 1 ou 2.");
							}
							
		
						}// end while
						
						// update du compte � la bdd
						compteEpargneService.modifierCompteEpargne(compte1);
						
						System.out.println("===========================================================");
						System.out.println("\t Virement r�ussi : le montant � �t� vir�.");
						System.out.println("===========================================================\n");
						break;
						
					}// end if
					
					if (choix_compte1==3) {
						break;
					}
				
				}//end while												
				
				break;
				
			// choix 11: retour menu principal (quitter)
			case 11:
				break;
				
				
			}// end switch
			
		} // end while

	}// end creerMenu

}// end GestionCompte
