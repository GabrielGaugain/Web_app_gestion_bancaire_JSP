package com.intiformation.gestionbanque.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.intiformation.gestionbanque.modele.Client;
import com.intiformation.gestionbanque.service.ClientServiceImpl;
import com.intiformation.gestionbanque.service.IClientService;



/**
 * serlvet opour la gestion des clients (CRUD du client).
 * @author gabri
 *
 */
@WebServlet(name="GestionClientServlet", 
			description="servlet de la gestion des clients",
			urlPatterns= {"/gestion-client-servlet"} )
public class GestionClientServlet  extends HttpServlet{

	
	private IClientService clientService;
	private int idCurrentConseiller;
	/**
	 * instanciation de la couche service
	 */
	@Override
	public void init() throws ServletException {
		clientService = new ClientServiceImpl();
		
	}//end init
		
	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		modifierClient(request, response);
	
	}//end doGet()

	private void modifierClient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		idCurrentConseiller = (int)request.getSession(true).getAttribute("id_conseil");
		
		// 1. recup du param "action" passé dans la requete
		String userAction = request.getParameter("action");
		
		// 2. test de la valeur du param
		switch(userAction) {
		

		// cas : modification d'un client 
		case "modif":
			// -> recup du parametre 'id-client' passé dans la requete 
			int clientId = Integer.parseInt(  request.getParameter("id-client") );
			
			//-> recup du client a mod dans la bdd via l'id client
			Client clientMod = clientService.findClientById(clientId);
			
			// -> sauvegarde du client (dans la session)
			request.setAttribute("client_modif", clientMod);
			
			// -> redirection vers la page 'modifier-client.jsp' pour afficher les infos du client dans une form
			request.getRequestDispatcher("/modifier-client.jsp").forward(request, response);
			
			break;
		
			
		case "supp":
			/**
			 * ATTENTION : étant donné que l'id client peut être une foreign key des tables comptes (compte associé à un client) il faut :
			 * 
			 * 			1) Soit d'abord modifier la table compte (ALTER TABLE comptes_courants DROP CONSTRAINT fk_id_proprio) 
			 * 			   avant de supp et la remettre une foit fini (ALTER TABLE comptes_courants ADD CONSTRAINT fk_id_proprio 
			 * 														   FOREIGN KEY (id_proprio) REFERENCES clients(id_client) )
			 * 			OU
			 * 
			 * 			2) Carrément supprimer les comptes associés d'abord étant donné que le client n'est plus à la banque !
			 * 
			 */
			// -> recup du parametre 'id-client' passé dans la requete 
			clientId = Integer.parseInt(  request.getParameter("id-client") );
			
			//-> recup du client a mod dans la bdd via l'id client
			boolean isDeleted = clientService.supprimerClient(clientId);
			
			// => sauvegarde de la liste comme attribut dans la requete
			request.setAttribute("liste_clients", clientService.findAllClientsByConseiller(idCurrentConseiller));
			
			// -> redirection vers la page 'modifier-client.jsp' pour afficher les infos du client dans une form
			request.getRequestDispatcher("/gestion-client.jsp").forward(request, response);
			
			break;			
			
		}
		
	}// end modifierClient()


	/**
	 * Traitement des requete HTTP en POST. <br/>
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		idCurrentConseiller = (int)request.getSession(true).getAttribute("id_conseil");
		
		// 1. recup de la valeur du parametre 'action' passé dans la requete 
		String clientAction = request.getParameter("action");
		//System.out.println("\t clientAction : "+clientAction);
		
	
		// 2. test de la valeur du param 'action'
		switch (clientAction) {
			
		//cas gotoaccueil -> on va à l'accueil de la partie gestionclient ('gestion-client.jsp')
		case "gotoaccueil":

			// 3.2.2.2. recup de la liste des clients dans la bdd
			List<Client> listeClientBdd = clientService.findAllClientsByConseiller(idCurrentConseiller);
			
			// 3.2.2.3. sauvegarde de la liste comme attribut dans la requete
			request.setAttribute("liste_clients", listeClientBdd);
			
			
			// propagation de la requete vers la page de gestion des clients (renvoi de la liste des clients)
			request.getRequestDispatcher("/gestion-client.jsp").forward(request, response);
			
			break;
			
			
		// => cas modification du client dans la bdd
		case "modification":
			
			
			
			// => recup des valeurs des param passés  dans la requete 
			String pNom = request.getParameter("p-nom");
			String pPrenom = request.getParameter("p-prenom");
			String pAdresse = request.getParameter("p-adresse");
			String pCodePostal = request.getParameter("p-cp");
			String pVille = request.getParameter("p-ville");
			String pTel = request.getParameter("p-tel");
			
			// => recup de la valeur du param de la requete 'p-idClient'
			int pClientId = Integer.parseInt(request.getParameter("p-idClient") );
			
			Client pClient = new Client(pClientId, pNom, pPrenom, pAdresse, pCodePostal, pVille, pTel);
						
			// => appel de la couche service pour la modif dans bdd
			clientService.modifierClient(pClient);
			
			// => recup de la nouvelle liste des clients dans la bdd via la couche service
			List<Client> nouvelleListeClientsBdd = clientService.findAllClientsByConseiller(idCurrentConseiller);
			
			// => sauvegarde de la liste comme attribut dans la requete
			request.setAttribute("liste_clients", nouvelleListeClientsBdd);
			
			// redirection vers accueil.jsp
			request.getRequestDispatcher("/gestion-client.jsp").forward(request, response);
			
			break;
			
		// => cas ajout d'un nouveau client dans la bdd	=> requet à partir de 'ajouter-client.jsp'
		case "ajout":
			
			// => recup des valeurs des params  de la requete (saisie du formulaire) et création du client associé
			String aNom = request.getParameter("p-nom");
			String aPrenom = request.getParameter("p-prenom");
			String aAdresse = request.getParameter("p-adresse");
			String aCodePostal = request.getParameter("p-cp");
			String aVille = request.getParameter("p-ville");
			String aTel = request.getParameter("p-tel");
			
			Client aClient = new Client( aNom, aPrenom, aAdresse, aCodePostal, aVille, aTel, idCurrentConseiller);
			
			// => appel de la couche service pour ajouter le client
			clientService.ajouterClient(aClient);
			
			// => recup de la nouvelle liste des clients de la bdd + sauvegarde de la liste dans la requete
			request.setAttribute("liste_clients", clientService.findAllClientsByConseiller(idCurrentConseiller) );
			
			request.getRequestDispatcher("/gestion-client.jsp").forward(request, response);
			
			break;
			
		default:
			
			System.out.println("requete pas prise en compte : "+clientAction);
			request.getRequestDispatcher("/accueil-conseil.jsp").forward(request, response);
			
			break;
		}// end switch
		
	}// end doPost()
	
	

}//end class
