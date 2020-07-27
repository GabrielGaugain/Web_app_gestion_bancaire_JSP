package com.intiformation.gestionbanque.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.intiformation.gestionbanque.modele.*;
import com.intiformation.gestionbanque.service.*;



@WebServlet(name="GestionCompteServlet", 
description="servlet de la gestion des comptes",
urlPatterns= {"/gestion-compte-servlet"} )
public class GestionCompteServlet extends HttpServlet{

	
	private ICompteEpargneService compteEpService;
	private ICompteCourantService compteCoService;
	private IClientService clientService;
	private int idCurrentConseiller;
	/**
	 * instanciation de la couche service
	 */
	@Override
	public void init() throws ServletException {
		compteCoService = new CompteCourantServiceImpl();
		compteEpService = new CompteEpargneServiceImpl();
		clientService = new ClientServiceImpl();
		
	}//end init
		
	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		modifierCompte(request, response);
	
	}//end doGet()

	private void modifierCompte(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/**
		 * <pre> 
		 * 
		 * 		traitement de la requete HTTP GET envoyée au click sur le lien 'modifier' des accueil.jsp
		 * 		-----------------------------------------------------------------------------------------
		 * 
		 * 		> url  de la requete:
		 * 			
		 * 			http://localhost:8080/gestion_comptes/gestion-compte-servlet?action=modif&id-compte=1
		 * </pre>
		 */
		idCurrentConseiller = (int)request.getSession(true).getAttribute("id_conseil");
		
		// 1. recup du param "action" passé dans la requete
		String userAction = request.getParameter("action");
		
		// 2. test de la valeur du param
		switch(userAction) {
		

		// cas : modification d'un compte 
		case "modifCC":
			// -> recup du parametre 'id-compte' passé dans la requete 
			int compteIdCC = Integer.parseInt(  request.getParameter("id-compte") );
			
			//-> recup du compte a mod dans la bdd via l'id compte
			CompteCourant compteModCC = compteCoService.findCompteCourantById(compteIdCC);
			
			// -> sauvegarde du compte (dans la session)
			request.setAttribute("compte_modif", compteModCC);
			
			// -> redirection vers la page 'modifier-compte.jsp' pour afficher les infos du compte dans une form
			request.getRequestDispatcher("/modifier-compte-courant.jsp").forward(request, response);
			
			break;
			
		case "modifCE":
			// -> recup du parametre 'id-compte' passé dans la requete 
			int compteIdCE = Integer.parseInt(  request.getParameter("id-compte") );
			
			//-> recup du compte a mod dans la bdd via l'id compte
			CompteEpargne compteModCE = compteEpService.findCompteEpargneById(compteIdCE);
			
			// -> sauvegarde du compte (dans la session)
			request.setAttribute("compte_modif", compteModCE);
			
			// -> redirection vers la page 'modifier-compte.jsp' pour afficher les infos du compte dans une form
			request.getRequestDispatcher("/modifier-compte-epargne.jsp").forward(request, response);
			
			break;	
		
			
		case "suppCC":

			// -> recup du parametre 'id-compte' passé dans la requete 
			compteIdCC = Integer.parseInt(  request.getParameter("id-compte") );
			
			//-> recup du compte a mod dans la bdd via l'id compte
			boolean isDeletedCC = compteCoService.supprimerCompteCourant(compteIdCC);
								
			// => sauvegarde des liste comme attribut dans la requete
			request.setAttribute("liste_CC_Bdd", compteCoService.findAllCompteCourants());
			request.setAttribute("liste_CE_Bdd", compteEpService.findAllCompteEpargnes());
			
			// -> redirection vers la page 'modifier-compte.jsp' pour afficher les infos du compte dans une form
			request.getRequestDispatcher("gestion-compte.jsp").forward(request, response);
			
			break;			
			
		
		case "suppCE":

			// -> recup du parametre 'id-compte' passé dans la requete 
			compteIdCE = Integer.parseInt(  request.getParameter("id-compte") );
			
			//-> recup du compte a mod dans la bdd via l'id compte
			boolean isDeletedCE = compteEpService.supprimerCompteEpargne(compteIdCE);
					
			// => sauvegarde des  listes comme attribut dans la requete
			request.setAttribute("liste_CE_Bdd", compteEpService.findAllCompteEpargnes());
			request.setAttribute("liste_CC_Bdd", compteCoService.findAllCompteCourants());

			
			// -> redirection vers la page 'modifier-compte.jsp' pour afficher les infos du compte dans une form
			request.getRequestDispatcher("gestion-compte.jsp.jsp").forward(request, response);
			
			break;			
		
			
			
		case "depotCC":
			// -> recup du parametre 'id-compte' passé dans la requete 
			int compteIdCCdep = Integer.parseInt(  request.getParameter("id-compte") );
			
			//-> recup du compte a mod dans la bdd via l'id compte
			CompteCourant compteDepCC = compteCoService.findCompteCourantById(compteIdCCdep);
			
			// -> sauvegarde du compte (dans la session)
			request.setAttribute("compte_depot", compteDepCC);
			
			// -> redirection vers la page 'modifier-compte.jsp' pour afficher les infos du compte dans une form
			request.getRequestDispatcher("/depot-compte-courant.jsp").forward(request, response);
			
			break;
	
		case "retirerCC":
			// -> recup du parametre 'id-compte' passé dans la requete 
			int compteIdCCret = Integer.parseInt(  request.getParameter("id-compte") );
			
			//-> recup du compte a mod dans la bdd via l'id compte
			CompteCourant compteRetCC = compteCoService.findCompteCourantById(compteIdCCret);
			
			// -> sauvegarde du compte (dans la session)
			request.setAttribute("compte_retrait", compteRetCC);
			
			
			// -> redirection vers la page 'modifier-compte.jsp' pour afficher les infos du compte dans une form
			request.getRequestDispatcher("/retrait-compte-courant.jsp").forward(request, response);
			
			break;
			
			
		case "depotCE":
			// -> recup du parametre 'id-compte' passé dans la requete 
			int compteIdCEdep = Integer.parseInt(  request.getParameter("id-compte") );
			
			//-> recup du compte a mod dans la bdd via l'id compte
			CompteEpargne compteDepCE = compteEpService.findCompteEpargneById(compteIdCEdep);
			
			// -> sauvegarde du compte (dans la session)
			request.setAttribute("compte_depot", compteDepCE);
			
			// -> redirection vers la page 'modifier-compte.jsp' pour afficher les infos du compte dans une form
			request.getRequestDispatcher("/depot-compte-epargne.jsp").forward(request, response);
			
			break;
	
		case "retirerCE":
			// -> recup du parametre 'id-compte' passé dans la requete 
			int compteIdCEret = Integer.parseInt(  request.getParameter("id-compte") );
			
			//-> recup du compte a mod dans la bdd via l'id compte
			CompteEpargne compteRetCE = compteEpService.findCompteEpargneById(compteIdCEret);
			
			// -> sauvegarde du compte (dans la session)
			request.setAttribute("compte_retrait", compteRetCE);
			
			// -> redirection vers la page 'modifier-compte.jsp' pour afficher les infos du compte dans une form
			request.getRequestDispatcher("/retrait-compte-epargne.jsp").forward(request, response);
			
			break;
			
			
		case "virer":
			
			int id_Compte = Integer.parseInt(  request.getParameter("id-compte") );
			String type_compte = request.getParameter("type-compte");
			
			System.out.println(id_Compte+" - "+type_compte);
			
			request.setAttribute("id_compte_emetteur", id_Compte);
			request.setAttribute("type_compte_emetteur", type_compte);
			
			request.setAttribute("liste_CE_Bdd", compteEpService.findAllCompteEpargnes());
			request.setAttribute("liste_CC_Bdd", compteCoService.findAllCompteCourants());
			request.getRequestDispatcher("/selection-compte-virement.jsp").forward(request, response);
			break;
		
		
		// cas : modification d'un compte 
		case "attrib":
			// -> recup du parametre 'id-compte' passé dans la requete 
			int IdCompte = Integer.parseInt(  request.getParameter("id-compte") );
			String typeCompte = request.getParameter("typeCompte");
			
			request.setAttribute("id_compte", IdCompte);
			request.setAttribute("type_compte", typeCompte);
			
			request.setAttribute("liste_clients", clientService.findAllClientsByConseiller(idCurrentConseiller) );
			
			
			// -> redirection vers la page 'modifier-compte.jsp' pour afficher les infos du compte dans une form
			request.getRequestDispatcher("/attribution-compte.jsp").forward(request, response);
			
			break;	
			
			
		}//end switch
	
	}// end modifiercompte()


	/**
	 * Traitement des requete HTTP en POST. <br/>
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 1. recup de la valeur du parametre 'action' passé dans la requete 
		String compteAction = request.getParameter("action");
		//System.out.println("\t compteAction : "+compteAction);
		
	
		// 2. test de la valeur du param 'action'
		switch (compteAction) {
			
		//cas gotoaccueil -> on va à l'accueil de la partie gestioncompte ('gestion-compte.jsp')
		case "gotoaccueil":

			// 3.2.2.2. recup de la liste des comptes courants dans la bdd
			compteCoService = new CompteCourantServiceImpl();
			List<CompteCourant> listeCCBdd = compteCoService.findAllCompteCourants();
			
			// 3.2.2.3. sauvegarde de la liste comme attribut dans la requete
			request.setAttribute("liste_CC_Bdd", listeCCBdd);			
			
			
			// 3.2.2.4. recup de la liste des compte epargnes dans la bdd
			compteEpService = new CompteEpargneServiceImpl();
			List<CompteEpargne> listeCEBdd = compteEpService.findAllCompteEpargnes();
			
			// 3.2.2.5. sauvegarde de la liste comme attribut dans la requete
			request.setAttribute("liste_CE_Bdd", listeCEBdd);
			
			// propagation de la requete vers la page de gestion des comptes (renvoi de la liste des comptes)
			request.getRequestDispatcher("/gestion-compte.jsp").forward(request, response);
			
			break;
			
			
		// => cas modification du compte dans la bdd
		case "modification-compte-courant":
			
			
			
			// => recup des valeurs des param passés  dans la requete 
			double pSoldeCC = Double.parseDouble(request.getParameter("p-solde"));
			double pDecouvertCC = Double.parseDouble(request.getParameter("p-decouvert"));
			//int pIdProprio = Integer.parseInt(request.getParameter("p-proprio"));
	
			
			// => recup de la valeur du param de la requete 'p-idcompte'
			int pNumCompteCC = Integer.parseInt(request.getParameter("p-numCompte") );
			
			CompteCourant pcompteCC = new CompteCourant(pNumCompteCC, pSoldeCC, pDecouvertCC);
						
			// => appel de la couche service pour la modif dans bdd
			compteCoService.modifierCompteCourant(pcompteCC);
			
			// => recup de la nouvelle liste des comptes dans la bdd via la couche service
			List<CompteCourant> nouvelleListecomptesCCBdd = compteCoService.findAllCompteCourants();
			
			// => sauvegarde de la liste comme attribut dans la requete
			request.setAttribute("liste_CC_Bdd", nouvelleListecomptesCCBdd);
			request.setAttribute("liste_CE_Bdd", compteEpService.findAllCompteEpargnes() );
						
			// redirection vers accueil.jsp
			request.getRequestDispatcher("/gestion-compte.jsp").forward(request, response);
			
			break;
			
		case "modification-compte-epargne":
			
			
			
			// => recup des valeurs des param passés  dans la requete 
			double pSoldeCE = Double.parseDouble(request.getParameter("p-solde"));
			double ptauxCE = Double.parseDouble(request.getParameter("p-taux"));
			//int pIdProprio = Integer.parseInt(request.getParameter("p-proprio"));
	
			
			// => recup de la valeur du param de la requete 'p-idcompte'
			int pNumCompteCE = Integer.parseInt(request.getParameter("p-numCompte") );
			
			CompteEpargne pcompteCE = new CompteEpargne(pNumCompteCE, pSoldeCE, ptauxCE);
						
			// => appel de la couche service pour la modif dans bdd
			compteEpService.modifierCompteEpargne(pcompteCE);
			
			// => recup de la nouvelle liste des comptes dans la bdd via la couche service
			List<CompteEpargne> nouvelleListecomptesCEBdd = compteEpService.findAllCompteEpargnes();
						
			// => sauvegarde de la liste comme attribut dans la requete
			request.setAttribute("liste_CE_Bdd", nouvelleListecomptesCEBdd);
			request.setAttribute("liste_CC_Bdd", compteCoService.findAllCompteCourants() );
			
			// redirection vers accueil.jsp
			request.getRequestDispatcher("/gestion-compte.jsp").forward(request, response);
			
			break;
			
		// => cas ajout d'un nouveau compte dans la bdd	=> requet à partir de 'ajouter-compte.jsp'
		case "ajout-compte-courant":
			
			// => recup des valeurs des params  de la requete (saisie du formulaire) et création du compte associé
			double aSoldeCC = Double.parseDouble(request.getParameter("p-solde"));
			double aDecouvertCC = Double.parseDouble(request.getParameter("p-decouvert"));
			//int aIdProprio = Integer.parseInt(request.getParameter("p-proprio"));
	
			
			// => recup de la valeur du param de la requete 'p-idCompte'
			int aNumCompteCC = Integer.parseInt(request.getParameter("p-numCompte") );
			
			CompteCourant acompteCC = new CompteCourant(aNumCompteCC, aSoldeCC, aDecouvertCC);
			
			// => appel de la couche service pour ajouter le compte
			compteCoService.ajouterCompteCourant(acompteCC);
			
			List<CompteCourant> newListeCCBdd = compteCoService.findAllCompteCourants();
			
			// => recup de la nouvelle liste des comptes de la bdd + sauvegarde de la liste dans la requete
			request.setAttribute("liste_CC_Bdd", newListeCCBdd );
			request.setAttribute("liste_CE_Bdd", compteEpService.findAllCompteEpargnes() );
			
			request.getRequestDispatcher("/gestion-compte.jsp").forward(request, response);
			
			break;
			
		case "ajout-compte-epargne":
			
			// => recup des valeurs des params  de la requete (saisie du formulaire) et création du compte associé
			double aSoldeCE = Double.parseDouble(request.getParameter("p-solde"));
			double aDecouvertCE = Double.parseDouble(request.getParameter("p-decouvert"));
			//int aIdProprioCE = Integer.parseInt(request.getParameter("p-proprio"));
	
			
			// => recup de la valeur du param de la requete 'p-idcompte'
			int aNumCompteCE = Integer.parseInt(request.getParameter("p-numCompte") );
			
			CompteEpargne acompteCE = new CompteEpargne(aNumCompteCE, aSoldeCE, aDecouvertCE);
			
			// => appel de la couche service pour ajouter le compte
			compteEpService.ajouterCompteEpargne(acompteCE);
		
			// => recup de la nouvelle liste des comptes de la bdd + sauvegarde de la liste dans la requete
			request.setAttribute("liste_CE_Bdd", compteEpService.findAllCompteEpargnes() );
			request.setAttribute("liste_CC_Bdd", compteCoService.findAllCompteCourants() );
			
			request.getRequestDispatcher("/gestion-compte.jsp").forward(request, response);
			
			break;
			
			
		case "update-retraitCC" :
			
			// => recup des valeurs des param passés  dans la requete 
			double pRetraitCC = Double.parseDouble(request.getParameter("p-retrait"));	
			
			// => recup de la valeur du param de la requete 'p-idcompte'
			int pNumCompteCCret = Integer.parseInt(request.getParameter("p-numCompte") );
			
			
			CompteCourant pcompteCCret = compteCoService.findCompteCourantById(pNumCompteCCret);
			pcompteCCret.setSoldeCompte( pcompteCCret.getSoldeCompte() - pRetraitCC );
			compteCoService.modifierCompteCourant(pcompteCCret);
						
			// => appel de la couche service pour la modif dans bdd
			compteCoService.modifierCompteCourant(pcompteCCret);
			
			// => recup de la nouvelle liste des comptes dans la bdd via la couche service
			List<CompteCourant> nouvelleListecomptesCCBddret = compteCoService.findAllCompteCourants();
			
			// => sauvegarde de la liste comme attribut dans la requete
			request.setAttribute("liste_CC_Bdd", nouvelleListecomptesCCBddret);
			request.setAttribute("liste_CE_Bdd", compteEpService.findAllCompteEpargnes() );
						
			// redirection vers accueil.jsp
			request.getRequestDispatcher("/gestion-compte.jsp").forward(request, response);
			
			break;			
			
			
		case "update-depotCC":
			// => recup des valeurs des param passés  dans la requete 
			double pDepotCC = Double.parseDouble(request.getParameter("p-depot"));	
			
			// => recup de la valeur du param de la requete 'p-idcompte'
			int pNumCompteCCdep = Integer.parseInt(request.getParameter("p-numCompte") );
			
			
			CompteCourant pcompteCCdep = compteCoService.findCompteCourantById(pNumCompteCCdep);
			pcompteCCdep.setSoldeCompte( pcompteCCdep.getSoldeCompte() + pDepotCC );
			compteCoService.modifierCompteCourant(pcompteCCdep);
						
			// => appel de la couche service pour la modif dans bdd
			compteCoService.modifierCompteCourant(pcompteCCdep);
			
			// => recup de la nouvelle liste des comptes dans la bdd via la couche service
			List<CompteCourant> nouvelleListecomptesCCBdddep = compteCoService.findAllCompteCourants();
			
			// => sauvegarde de la liste comme attribut dans la requete
			request.setAttribute("liste_CC_Bdd", nouvelleListecomptesCCBdddep);
			request.setAttribute("liste_CE_Bdd", compteEpService.findAllCompteEpargnes() );
						
			// redirection vers accueil.jsp
			request.getRequestDispatcher("/gestion-compte.jsp").forward(request, response);
						
			break;

			
		case "update-retraitCE" :
			
			// => recup des valeurs des param passés  dans la requete 
			double pRetraitCE = Double.parseDouble(request.getParameter("p-retrait"));	
			
			// => recup de la valeur du param de la requete 'p-idcompte'
			int pNumCompteCEret = Integer.parseInt(request.getParameter("p-numCompte") );
			
			
			CompteEpargne pcompteCEret = compteEpService.findCompteEpargneById(pNumCompteCEret);
			
			pcompteCEret.setSoldeCompte( pcompteCEret.getSoldeCompte() - pRetraitCE );
						
			// => appel de la couche service pour la modif dans bdd
			compteEpService.modifierCompteEpargne(pcompteCEret);
			
			// => recup de la nouvelle liste des comptes dans la bdd via la couche service
			List<CompteEpargne> nouvelleListecomptesCEBddret = compteEpService.findAllCompteEpargnes();
			
			// => sauvegarde de la liste comme attribut dans la requete
			request.setAttribute("liste_CE_Bdd", nouvelleListecomptesCEBddret);
			request.setAttribute("liste_CC_Bdd", compteCoService.findAllCompteCourants() );
						
			// redirection vers accueil.jsp
			request.getRequestDispatcher("/gestion-compte.jsp").forward(request, response);
						
			break;
			
		case "update-depotCE":
			// => recup des valeurs des param passés  dans la requete 
			double pDepotCE = Double.parseDouble(request.getParameter("p-depot"));	
			
			// => recup de la valeur du param de la requete 'p-idcompte'
			int pNumCompteCEdep = Integer.parseInt(request.getParameter("p-numCompte") );
			
			
			CompteEpargne pcompteCEdep = compteEpService.findCompteEpargneById(pNumCompteCEdep);
			pcompteCEdep.setSoldeCompte( pcompteCEdep.getSoldeCompte() + pDepotCE );
						
			// => appel de la couche service pour la modif dans bdd
			compteEpService.modifierCompteEpargne(pcompteCEdep);
			
			// => recup de la nouvelle liste des comptes dans la bdd via la couche service
			List<CompteEpargne> nouvelleListecomptesCEBdddep = compteEpService.findAllCompteEpargnes();
			
			// => sauvegarde de la liste comme attribut dans la requete
			request.setAttribute("liste_CE_Bdd", nouvelleListecomptesCEBdddep);
			request.setAttribute("liste_CC_Bdd", compteCoService.findAllCompteCourants() );
						
			// redirection vers accueil.jsp
			request.getRequestDispatcher("/gestion-compte.jsp").forward(request, response);
						
			break;
				
			
		case "virement":
			
			// Recupération de l'emetteur
			int idEmetteur = Integer.parseInt(request.getParameter("p-idcompte-emetteur") );
			String typeEmetteur = (String)request.getParameter("p-typecompte-emetteur") ;
			double montant = Double.parseDouble(request.getParameter("p-montant") );

			// recup du compte receveur
			
			int idReceveur = Integer.parseInt(request.getParameter("p-num-receveur") );
			String typeReceveur = (String)request.getParameter("p-type-receveur") ;	
			
			System.out.println("Virement de "+montant+" $ du compte " + idEmetteur+" ("+typeEmetteur+") vers le cmpte "+idReceveur+ " ("+typeReceveur+") ");
			
			// Nouveau solde (retrait des sous ) de l'emetteur
			
			if (typeEmetteur.equals("courant")) {
				CompteCourant compteEmetteur = compteCoService.findCompteCourantById(idEmetteur);
				double ecart = compteEmetteur.getSoldeCompte() - montant ;
				if (ecart > - compteEmetteur.getDecouvertAutorise()  ) { 
						compteEmetteur.setSoldeCompte( ecart );
						compteCoService.modifierCompteCourant(compteEmetteur);
					
					  }//end if
				
			}else {
				CompteEpargne compteEmetteur = compteEpService.findCompteEpargneById(idEmetteur);
				double ecart = compteEmetteur.getSoldeCompte() - montant ;
				if (ecart > 0  ) { 
						compteEmetteur.setSoldeCompte( ecart );
						compteEpService.modifierCompteEpargne(compteEmetteur);
						
				}//end if
			}//end else
			
			// Nouveau solde (retrait des sous ) de l'emetteur
			if (typeReceveur.equals("courant")) {
				CompteCourant compteReceveur = compteCoService.findCompteCourantById(idReceveur);
				compteReceveur.setSoldeCompte( compteReceveur.getSoldeCompte() + montant );
				compteCoService.modifierCompteCourant(compteReceveur);
						
			
			}else {
				CompteEpargne compteReceveur = compteEpService.findCompteEpargneById(idReceveur);
				compteReceveur.setSoldeCompte( compteReceveur.getSoldeCompte() + montant );
				compteEpService.modifierCompteEpargne(compteReceveur);
							
			}				
			
			
			// redirection et set de la requete
			request.setAttribute("liste_CC_Bdd", compteCoService.findAllCompteCourants() );
			request.setAttribute("liste_CE_Bdd", compteEpService.findAllCompteEpargnes() );
			request.getRequestDispatcher("/gestion-compte.jsp").forward(request, response);
			
			//System.out.println("requete pas prise en compte : "+compteAction +" -> à implementer");
			break;
			
		case "attribution":
			
			int idCompte = Integer.parseInt(request.getParameter("p-idcompte") );
			String typeCompte= (String)request.getParameter("p-typecompte") ;			
			
			int idClient = Integer.parseInt(request.getParameter("p-id-client") );
			
			if (typeCompte.equals("courant")) {
				compteCoService.attribuerCompteCourant(idCompte, idClient);
				
			}else {
				compteEpService.attribuerCompteEpargne(idCompte, idClient);
				
			}//end else
			
			// redirection et set de la requete
			request.setAttribute("liste_CC_Bdd", compteCoService.findAllCompteCourants() );
			request.setAttribute("liste_CE_Bdd", compteEpService.findAllCompteEpargnes() );			
			request.getRequestDispatcher("/gestion-compte.jsp").forward(request, response);
			
			//System.out.println("requete pas prise en compte : "+compteAction +" -> à implementer");
			
			break;
			
		default:
			
			System.out.println("requete pas prise en compte : "+compteAction);
			request.getRequestDispatcher("/accueil-conseil.jsp").forward(request, response);
			
			break;
		}// end switch
		
	}// end doPost()
	
	
	
	
}// end class
