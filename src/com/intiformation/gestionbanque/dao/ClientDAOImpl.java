package com.intiformation.gestionbanque.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.intiformation.gestionbanque.modele.Client;
import com.intiformation.gestionbanque.modele.Conseiller;


/**
 * Implementation de l'interface DAO pour les clients
 */
public class ClientDAOImpl implements IClientDAO {

	@Override
	public boolean add(Client t) {

		PreparedStatement ps = null;

		try {

			// 1. definition du contenu de la requete SQL avec ? pour prepared statement
			String requeteAjout = "INSERT INTO clients (nom, prenom, adresse, codePostal, ville, telephone, id_conseiller) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?)";

			// 2. creation preparedStatement
			ps = this.connection.prepareStatement(requeteAjout);

			//ps.setInt(1, t.getIdClient());
			ps.setString(1, t.getNom());
			ps.setString(2, t.getPrenom());
			ps.setString(3, t.getAdresse());
			ps.setString(4, t.getCodePostal());
			ps.setString(5, t.getVille());
			ps.setString(6, t.getTelephone());
			ps.setInt(7, t.getIdConseiller());

			// 3. exe de la requete et recup resultat (update car modif)
			int verifAjout = ps.executeUpdate();

			return verifAjout == 1;

		} catch (SQLException e) {
			System.out.println("...Erreur lors de l'ajout d'un objet Client de la bdd ...");
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} // end finally

		return false;
	}

	/**
	 * -----------------------------------------------------------------------------------------------
	 */
	@Override
	public boolean update(Client t) {
		PreparedStatement ps = null;

		try {

			// 1. definition du contenu de la requete SQL avec ? pour prepared statement
			String requeteUp = "UPDATE clients SET nom=?, prenom=?, adresse=?, codePostal=?, ville=?, telephone=?"
					+ " WHERE idClient=? ";

			// 2. creation preparedStatement
			ps = this.connection.prepareStatement(requeteUp);

			ps.setString(1, t.getNom());
			ps.setString(2, t.getPrenom());
			ps.setString(3, t.getAdresse());
			ps.setString(4, t.getCodePostal());
			ps.setString(5, t.getVille());
			ps.setString(6, t.getTelephone());
			ps.setInt(7, t.getIdClient());

			// 3. exe de la requete et recup resultat (update car modif)
			int verifUp = ps.executeUpdate();

			return verifUp == 1;

		} catch (SQLException e) {
			System.out.println("...Erreur lors de la modif d'un objet Client de la bdd ...");
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} // end finally

		return false;
	}

	/**
	 * -----------------------------------------------------------------------------------------------
	 */
	@Override
	public boolean delete(Integer id) {
		PreparedStatement ps = null;

		try {

			// 1. definition du contenu de la requete SQL avec ? pour prepared statement
			String requeteUpdateCC = "UPDATE comptes_courants set id_proprio =null where id_proprio=? " ;
			String requeteUpdateCE = "UPDATE comptes_epargnes set proprio_id =null where proprio_id=? " ;
			String requeteDel  = "DELETE FROM clients WHERE idClient=?" ;

			ps = this.connection.prepareStatement(requeteUpdateCC);
			ps.setInt(1, id);
			int verifupcc = ps.executeUpdate();
			
			ps = this.connection.prepareStatement(requeteUpdateCE);
			ps.setInt(1, id);
			int verifupce = ps.executeUpdate();
			
			// 2. creation preparedStatement
			ps = this.connection.prepareStatement(requeteDel);

			ps.setInt(1, id);


			// 3. exe de la requete et recup resultat (update car modif)
			int verifDel = ps.executeUpdate();

			return (verifDel == 1)&(verifupcc==1)&(verifupce == 1);

		} catch (SQLException e) {
			System.out.println("...Erreur lors de la suppression d'un objet Client de la bdd ...");
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} // end finally

		return false;
	}

	/**
	 * -----------------------------------------------------------------------------------------------
	 */
	@Override
	public List<Client> getAll() {
		PreparedStatement ps = null;
		ResultSet resultatRequete = null;

		try {
			// 1. définition du contenu de la requete SQL
			String requetGetAll = "SELECT * FROM clients";

			// 2. def de l'objet "Statement" qui permet d'envoyer les requetes vers la bdd
			ps = this.connection.prepareStatement(requetGetAll);

			// 3. Envoi et exe de la requette
			resultatRequete = ps.executeQuery();

			// 4. extraction des données du ResultSet

			Client Client = null;
			List<Client> listeClients = new ArrayList<>();

			while (resultatRequete.next()) {
				// 4.1 recup de l'id du type de chambre
				int ClientId = resultatRequete.getInt(1);
				int ConseillerId = resultatRequete.getInt(1);

				// 4.2 recup du nombre de lit
				String nom = resultatRequete.getString(2);

				// 4.3 recup du type de lit
				String prenom = resultatRequete.getString(3);

				// 4.5 recup de la description de la chambre
				String mail = resultatRequete.getString(4);
				String tel = resultatRequete.getString(5);
				String identif = resultatRequete.getString(6);
				String mdp = resultatRequete.getString(7);

				// 4.5 ajout des données à une instance TypeChambre
				Client = new Client(ClientId, nom, prenom, mail, tel, identif, mdp);

				// 4.6 ajout du type de chambre à la liste
				listeClients.add(Client);

			} // end while

			// 5. renvoie de la liste
			return listeClients;

		} catch (SQLException e) {
			System.out.println("...Erreur lors de la lecture et de la collect de la table  ...");
			e.printStackTrace();

		} finally {

			// 6. fermeture des ressources (connexion, ResultSet, Statement)
			try {
				if (resultatRequete != null) {
					resultatRequete.close();
				}
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		} // end finally

		return null;
	}// end getAll()

	/**
	 * -----------------------------------------------------------------------------------------------
	 */
	@Override
	public Client getById(Integer id) {
		PreparedStatement ps = null;
		ResultSet resultSet = null;

		try {
			// 1. definition du contenue de la requette
			String requeteById = " SELECT * FROM Clients WHERE idClient =?";

			// 2. statement
			ps = this.connection.prepareStatement(requeteById);
			ps.setInt(1, id);
			// 3. result set -> envoie requete
			resultSet = ps.executeQuery();

			// 4. extraction des données

			// 4.1. init tete de lecture avec next()
			resultSet.next();

			// 4.2. recup des données
			Client Client = new Client(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
					resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7));

			return Client;

		} catch (SQLException e) {
			System.out.println("... Erreur lors de la recup du Client via son id dans ClientDAOImpl...");
			e.printStackTrace();

		} finally {
			// 6. fermeture des ressources (connexion, ResultSet, Statement)
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} // end catch

		} // end finally

		return null;
	}//end getById

	@Override
	public List<Client> getConseillerAllClients(int id_conseiller) {
		PreparedStatement ps = null;
		ResultSet resultatRequete = null;

		try {
			// 1. définition du contenu de la requete SQL
			String requetGetAll = "SELECT * FROM clients WHERE id_conseiller=?";

			// 2. def de l'objet "Statement" qui permet d'envoyer les requetes vers la bdd
			ps = this.connection.prepareStatement(requetGetAll);
			ps.setInt(1, id_conseiller);
			// 3. Envoi et exe de la requette
			resultatRequete = ps.executeQuery();

			// 4. extraction des données du ResultSet

			Conseiller conseiller = null;
			List<Client> listeClients = new ArrayList<>();

			while (resultatRequete.next()) {
				
				// 4.1 recup de l'id du type de chambre
				int clientId = resultatRequete.getInt(1);
				
				// 4.2 recup du nombre de lit
				String nom = resultatRequete.getString(2);
				
				// 4.3 recup du type de lit
				String prenom = resultatRequete.getString(3);

				// 4.5 recup de la description de la chambre
				String adresse = resultatRequete.getString(4);
				String codePostal = resultatRequete.getString(5);
				String ville = resultatRequete.getString(6);
				String tel = resultatRequete.getString(7);
				
				// 4.5 ajout des données à une instance TypeChambre
				Client client = new Client(clientId, nom,prenom,adresse,codePostal, ville, tel);

				// 4.6 ajout du type de chambre à la liste
				listeClients.add(client);

			} // end while

			// 5. renvoie de la liste
			return listeClients;

		} catch (SQLException e) {
			System.out.println("...Erreur lors de la lecture et de la collect de la table  ...");
			e.printStackTrace();

		} finally {

			// 6. fermeture des ressources (connexion, ResultSet, Statement)
			try {
				if (resultatRequete != null) {
					resultatRequete.close();
				}
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		} // end finally

		return null;
	
	}//end getConseillerAllClients

}
