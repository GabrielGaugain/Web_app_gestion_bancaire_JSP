package com.intiformation.gestionbanque.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.intiformation.gestionbanque.modele.Conseiller;
import com.intiformation.gestionbanque.service.AdministrateurServiceImpl;
import com.intiformation.gestionbanque.service.ConseillerServiceImpl;
import com.intiformation.gestionbanque.service.IAdministrateurService;
import com.intiformation.gestionbanque.service.IConseillerService;

@WebServlet(name="GestionConseillerServlet", 
			description="servlet de la gestion des conseiller",
			urlPatterns= {"/gestion-conseiller-servlet"} )
public class GestionConseillerServlet extends HttpServlet{

	private IConseillerService conseillerService;
	private int idCurrentAdmin;
	/**
	 * instanciation de la couche service
	 */
	@Override
	public void init() throws ServletException {
		conseillerService = new ConseillerServiceImpl();

	}//end init
		
	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		modifierConseiller(request, response);
	
	}//end doGet()

	private void modifierConseiller(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/**
		 * <pre> 
		 * 
		 * 		traitement de la requete HTTP GET envoyée au click sur le lien 'modifier' des accueil.jsp
		 * 		-----------------------------------------------------------------------------------------
		 * 
		 * 		> url  de la requete:
		 * 			
		 * 			http://localhost:8080/gestion_Conseillers/gestion-Conseiller-servlet?action=modif&id-Conseiller=1
		 * </pre>
		 */
		 idCurrentAdmin = (int)request.getSession(true).getAttribute("id_admin");
		
		// 1. recup du param "action" passé dans la requete
		String userAction = request.getParameter("action");
		
		// 2. test de la valeur du param
		switch(userAction) {
		

		// cas : modification d'un Conseiller 
		case "modif":
			// -> recup du parametre 'id-Conseiller' passé dans la requete 
			int conseillerId = Integer.parseInt(  request.getParameter("id-conseiller") );
			
			//-> recup du Conseiller a mod dans la bdd via l'id Conseiller
			Conseiller conseillerMod = conseillerService.findConseillerById(conseillerId);
			
			// -> sauvegarde du Conseiller (dans la session)
			request.setAttribute("conseiller_modif", conseillerMod);
			
			// -> redirection vers la page 'modifier-Conseiller.jsp' pour afficher les infos du Conseiller dans une form
			request.getRequestDispatcher("/modifier-conseiller.jsp").forward(request, response);
			
			break;
		
			
		case "supp":
			/**
			 * ATTENTION : étant donné que l'id Conseiller peut être une foreign key des tables comptes (compte associé à un Conseiller) il faut :
			 * 
			 * 			1) Soit d'abord modifier la table compte (ALTER TABLE comptes_courants DROP CONSTRAINT fk_id_proprio) 
			 * 			   avant de supp et la remettre une foit fini (ALTER TABLE comptes_courants ADD CONSTRAINT fk_id_proprio 
			 * 														   FOREIGN KEY (id_proprio) REFERENCES Conseillers(id_Conseiller) )
			 * 			OU
			 * 
			 * 			2) Carrément supprimer les comptes associés d'abord étant donné que le Conseiller n'est plus à la banque !
			 * 
			 */
			// -> recup du parametre 'id-Conseiller' passé dans la requete 
			conseillerId = Integer.parseInt(  request.getParameter("id-conseiller") );
			
			//-> recup du Conseiller a mod dans la bdd via l'id Conseiller
			boolean isDeleted = conseillerService.supprimerConseiller(conseillerId);

			// => sauvegarde de la liste comme attribut dans la requete
			request.setAttribute("liste_conseillers", conseillerService.findAllConseillerByAdmin(idCurrentAdmin) );
			
			// -> redirection vers la page 'modifier-Conseiller.jsp' pour afficher les infos du Conseiller dans une form
			request.getRequestDispatcher("/accueil_admin.jsp").forward(request, response);
			
			break;			
			
		}
		
	}// end modifierConseiller()


	/**
	 * Traitement des requete HTTP en POST. <br/>
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/**
		 * <pre> 
		 * 
		 * 		traitement de la requete HTTP POST envoyée à la soumission de la form de 'modifier-Conseiller.jsp'
		 * 		----------------------------------------------------------------------------------------------
		 * 
		 * 		> url  de la requete:	http://localhost:8080/app_web_gestion_Conseillers/gestion-Conseiller-servlet
		 * 
		 * 		> parametres passés dans la requete :
		 * 				> saisie dans les champs : p_nom / p-prenom / p-cp / p-ville / p-tel
		 * 				> l'id du Conseiller : p-idConseiller
		 * 				> le param 'action' ayant la valeur 'modification'
		 *
		 * 		traitement de la requete HTTP POST envoyée à la soumission de la form de 'ajouter-Conseiller.jsp'
		 * 		----------------------------------------------------------------------------------------------
		 * 
		 * 		> url  de la requete:	http://localhost:8080/app_web_gestion_Conseillers/gestion-Conseiller-servlet
		 * 
		 * 		> parametres passés dans la requete :
		 * 				> saisie dans les champs : p_nom / p-prenom / p-cp / p-ville / p-tel
		 * 				> le param 'action' ayant la valeur 'ajout'	
		 * </pre>
		 */
		// 1. recup de la valeur du parametre 'action' passé dans la requete 
		String conseillerAction = request.getParameter("action");
		//System.out.println("\t ConseillerAction : "+ConseillerAction);
		idCurrentAdmin = (int)request.getSession(true).getAttribute("id_admin");
	
		// 2. test de la valeur du param 'action'
		switch (conseillerAction) {
			
		//cas gotoaccueil -> on va à l'accueil de la partie gestionConseiller ('gestion-Conseiller.jsp')
		case "gotoaccueil":

			// 3.2.2.2. recup de la liste des Conseillers dans la bdd
			List<Conseiller> listeConseillerBdd = conseillerService.findAllConseillerByAdmin(idCurrentAdmin);
			
			// 3.2.2.3. sauvegarde de la liste comme attribut dans la requete
			request.setAttribute("liste_conseillers", listeConseillerBdd);

			
			// propagation de la requete vers la page de gestion des Conseillers (renvoi de la liste des Conseillers)
			request.getRequestDispatcher("/accueil_admin.jsp").forward(request, response);
			
			break;
			
			
		// => cas modification du Conseiller dans la bdd
		case "modification":
			
			
			
			// => recup des valeurs des param passés  dans la requete 
			String pNom = request.getParameter("p-nom");
			String pPrenom = request.getParameter("p-prenom");
			String pMail = request.getParameter("p-mail");
			String pTel = request.getParameter("p-tel");
			String pIdentifiant = request.getParameter("p-identifiant");
			String pMdp = request.getParameter("p-mdp");
			
			
			// => recup de la valeur du param de la requete 'p-idConseiller'
			int pConseillerId = Integer.parseInt(request.getParameter("p-idConseiller") );
			
			Conseiller pConseiller = new Conseiller(pConseillerId, pNom, pPrenom, pMail, pTel, pIdentifiant, pMdp);
						
			// => appel de la couche service pour la modif dans bdd
			conseillerService.modifierConseiller(pConseiller);
			
			// => recup de la nouvelle liste des Conseillers dans la bdd via la couche service
			List<Conseiller> nouvelleListeConseillersBdd = conseillerService.findAllConseillerByAdmin(idCurrentAdmin);
			
			// => sauvegarde de la liste comme attribut dans la requete
			request.setAttribute("liste_conseillers", nouvelleListeConseillersBdd);
			
			// redirection vers accueil.jsp
			request.getRequestDispatcher("/accueil_admin.jsp").forward(request, response);
			
			break;
			
		// => cas ajout d'un nouveau Conseiller dans la bdd	=> requet à partir de 'ajouter-Conseiller.jsp'
		case "ajout":
			
			// => recup des valeurs des params  de la requete (saisie du formulaire) et création du Conseiller associé
			String aNom = request.getParameter("p-nom");
			String aPrenom = request.getParameter("p-prenom");
			String aMail = request.getParameter("p-mail");
			String aTel = request.getParameter("p-tel");
			String aIdentifiant = request.getParameter("p-identifiant");
			String aMdp = request.getParameter("p-mdp");
			
			Conseiller aConseiller = new Conseiller( aNom, aPrenom, aMail,aTel, aIdentifiant, aMdp, idCurrentAdmin);
			
			// => appel de la couche service pour ajouter le Conseiller
			conseillerService.ajouterConseiller(aConseiller);
			
			// => recup de la nouvelle liste des Conseillers de la bdd + sauvegarde de la liste dans la requete
			request.setAttribute("liste_conseillers", conseillerService.findAllConseillerByAdmin(idCurrentAdmin) );
			
			request.getRequestDispatcher("/accueil_admin.jsp").forward(request, response);
			
			break;
			
		default:
			
			System.out.println("requete pas prise en compte : "+conseillerAction);
			request.getRequestDispatcher("/accueil_admin.jsp").forward(request, response);
			
			break;
		}// end switch
		
	}// end doPost()
	
	
	
	
}
