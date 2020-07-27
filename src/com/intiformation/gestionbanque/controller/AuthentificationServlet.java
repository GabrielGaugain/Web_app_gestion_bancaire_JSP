package com.intiformation.gestionbanque.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.intiformation.gestionbanque.modele.*;
import com.intiformation.gestionbanque.service.*;


/**
 * servlet (controleur) pour la gestion de l'authentification de l'utilisateur. <br/>
 * Invoquée via l'URL :	
 * 				http://localhost:8080/gestion_clients/authentification_servlet/
 * 				<---- serveur------->/<-url app web->/<-------url servlet----->
 * @author gabri
 *
 */
public class AuthentificationServlet  extends HttpServlet{

	/** 
	 * Traitement de la requete envoyé après la soumission du formulaire  de la page 'authentification.jsp'. <br/>
	 * 		-> cette requete à comme url : http://localhost:8080/gestion_clients/authentification_servlet/
	 * 		-> elle a 2 parametres : p_identifiant // valeur : saisie du champ (ref attribut 'name' de <input type='text'>
	 * 								 p_mdp  // valeur : saisie du champ (ref attribut 'name' de <input type='password'>
	 * 
	 * @param request : la requete envoyée par le client à partir de 'authentification.jsp'
	 * @param response : la réponse à renvoyer au client
	 */		
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		// 3.2.1. instanciation des  couches service
		ConseillerServiceImpl conseillerService = new ConseillerServiceImpl();
		IAdministrateurService administrateurService = new AdministrateurServiceImpl();	
		
		//--------------------------------------------------------------------------------		
		// 1. Recup des param de la requete (saisie du formulaire de 'authentification.jsp')
		//--------------------------------------------------------------------------------
		
		// 1.1. recup du param p_identifiant
		String identifiantUser = request.getParameter("p_identifiant");
		
		// 1.2. recup du param p_mdp
		String mdpUser = request.getParameter("p_mdp");	
		
		// 1.3. recup du type de connection (admin vs conseiller)
		String typeCo = request.getParameter("type_co");
		
		
		//--------------------------------------------------------------------------------
		// 2. Vérif des valeurs des params de la requete
		//--------------------------------------------------------------------------------
		
		// 2.1. verif si les valeurs sont null
		if(identifiantUser == null || mdpUser==null || typeCo==null) {
			
			// 2.1.1. redirection vers la page du formulaire 'authentification.jsp'
			// -> redirection = délégation
			RequestDispatcher rd = request.getRequestDispatcher("/authentification.jsp");
			rd.forward(request, response);
			
			
		}// end if
		
		// 2.2. Saisie obligatoire (meme si deja mis sur le jsp, au cas ou ça tombe) -> verif si les valeurs sont vides
		// 2.2.1. définition d'une liste qui va stocker les messages d'erreurs
		List<String> listeMessagesErreurs = new ArrayList<>();
			
		// 2.2.2. verif des valeurs
		if ("".equals(identifiantUser)) {
			listeMessagesErreurs.add("L'indentifiant est obligatoire");
		}
		if ("".equals(mdpUser)) {
			listeMessagesErreurs.add("Le mot de passe est obligatoire");
		}
		
		// 2.2.3. Verif des erreurs
		if(listeMessagesErreurs.size()>0) {
			
			// -> il existe des erreurs dans la liste 
			
			// 2.2.3.1 sauvegarde de la liste comme attribut dans la requete
			request.setAttribute("messages_erreurs", listeMessagesErreurs);
			
			// 2.2.3.2. redirection vers la page du formulaire
			RequestDispatcher rd = request.getRequestDispatcher("/authentification.jsp");
			rd.forward(request, response);
		
		}// end if
		
		
		
		//--------------------------------------------------------------------------------
		// 3. Authentification de l'utilisateur											--
		//--------------------------------------------------------------------------------
		
		//-> verif si la liste es tvide
		if (listeMessagesErreurs.isEmpty()) {
			//-------------liste vide => pas d'erreurs -------------------//
			
			// 3.2. Verif si l'utilisateur existe dans la bdd avec l'id et le mdp saisi
			switch(typeCo){
			
			case  "admin":

				
				// 3.2.2. verif
				if( administrateurService.isAdministrateurExists(identifiantUser, mdpUser) ) {
					// ------- l'administrateur existe dans la bdd ----------//
					
					// 3.2.2.1. connection de l'admin via la session
					
					// ================================================================ //
					// ====GESTION DE LA SESSION ADMINISTRATEUR ======================= //
					// ================================================================ //

					//  recup de la session associée à l'utilisateur
					HttpSession session = request.getSession(true);
				
					// param de verif qu'une session existe
					session.setAttribute("isLogged", "true");
				
					// sauvegarde de l'id de l'admin (recup via service) dans la session
					int idAdmin = administrateurService.findIdAdminByLogin(identifiantUser, mdpUser);					
					session.setAttribute("id_admin", idAdmin);
					
					
					// 3.2.2.2. recup de la liste des clients dans la bdd
					List<Conseiller> listeConseillerBdd = conseillerService.findAllConseillerByAdmin(idAdmin);
					
					// 3.2.2.3. sauvegarde de la liste comme attribut dans la requete
					request.setAttribute("liste_conseillers", listeConseillerBdd);
					
					// 3.2.2.2. redirection vers 'accueil.jsp'
					request.getRequestDispatcher("/accueil_admin.jsp").forward(request, response);
					break;
							
				}else {
					// ----- l'admin n'existe pas dans la bdd -----//
					listeMessagesErreurs.add("Identité non valide");
					request.setAttribute("messages_erreurs", listeMessagesErreurs);
					
					request.getRequestDispatcher("/authentification.jsp").forward(request, response);
					break;
				}// end else
				
				
			case  "conseil":
				
				// 3.2.2. verif
				if( conseillerService.isConseillerExists(identifiantUser, mdpUser) ) {
					// ------- l'administrateur existe dans la bdd ----------//
					
					// 3.2.2.1. connection de l'admin via la session
					
					// ================================================================ //
					// ====GESTION DE LA SESSION ADMINISTRATEUR ======================= //
					// ================================================================ //

					//  recup de la session associée à l'utilisateur
					HttpSession session = request.getSession(true);
					
					// param de verif qu'une session existe
					session.setAttribute("isLogged", "true");
					
					// sauvegarde de l'id de l'admin (recup via service) dans la session
					int idConseil = conseillerService.findIdConseillerByLogin(identifiantUser, mdpUser);					
					session.setAttribute("id_conseil", idConseil);

					// 3.2.2.2. redirection vers 'accueil.jsp'
					request.getRequestDispatcher("/accueil-conseil.jsp").forward(request, response);
					break;
							
				}else {
					// ----- le conseiller n'existe pas dans la bdd -----//
					listeMessagesErreurs.add("Identité non valide");
					request.setAttribute("messages_erreurs", listeMessagesErreurs);
					
					request.getRequestDispatcher("/authentification.jsp").forward(request, response);
					break;
				}// end else
				
			}// end switch
		}// end if
		
		
		
		
	}// end doPost()

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*==============================================================*/
		/*========= Deconnection de l'utilisateur ======================*/
		/*==============================================================*/
		/**
		 * Deconnexion = destruction de la session (de l'id de session) => destruction de l'objet HttpSession
		 */
		
		// 1. recup de la session associée à l'utilisateur
		/**
		 * > getSession(): recup de la session
		 * 
		 * 		-> false => si la session n'existe pas , ne la crée pas
		 */
		HttpSession session = request.getSession(false);
		
		// 2. deconnexion de l'utilisateur
		
		// 2.1.1= recup du param de la requete 'destroy'
		String destroySession = request.getParameter("destroy");
		
		// 2.2. test de la valeur du param => si true , deconnexion
		if ("true".equals(destroySession)) {
			
			// 2.2.1. destruction de la session avec la méthode invalidate()
			session.invalidate();
			request.setAttribute("action", null);
			
		}// end if
		
		// 3. redirection vers la page du formulaire 'authentification.jsp'
		request.getRequestDispatcher("/authentification.jsp").forward(request, response);
		
	}//end doGet()

	
	
	
}// end class
