package com.intiformation.gestionbanque.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.intiformation.gestionbanque.modele.Client;
import com.intiformation.gestionbanque.modele.Conseiller;


/**
 * Implementation de l'interface DAO de conseillers
 * @author gabri
 *
 */
public class ConseillerDAOImpl implements IConseillerDAO {

	/**
	 * -----------------------------------------------------------------------------------------------
	 */
	@Override
	public boolean add(Conseiller t) {
		PreparedStatement ps = null;

		try {

			// 1. definition du contenu de la requete SQL avec ? pour prepared statement
			String requeteAjout = "INSERT INTO conseillers (idConseiller, nom, prenom, mail, telephone, identifiant, mot_de_passe, id_admin ) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?,?)";

			// 2. creation preparedStatement
			ps = this.connection.prepareStatement(requeteAjout);

			ps.setInt(1, t.getIdConseiller());
			ps.setString(2, t.getNom());
			ps.setString(3, t.getPrenom());
			ps.setString(4, t.getMail());
			ps.setString(5, t.getTelephone());
			ps.setString(6, t.getIdentifiant());
			ps.setString(7, t.getMotDePasse());
			ps.setInt(8, t.getIdAdmin());
			// 3. exe de la requete et recup resultat (update car modif)
			int verifAjout = ps.executeUpdate();

			return verifAjout == 1;

		} catch (SQLException e) {
			System.out.println("...Erreur lors de l'ajout d'un objet Conseiller de la bdd ...");
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
	public boolean update(Conseiller t) {
		PreparedStatement ps = null;

		try {

			// 1. definition du contenu de la requete SQL avec ? pour prepared statement
			String requeteUp = "UPDATE conseillers SET nom=?, prenom=?, mail=?, telephone=?, identifiant=?,mot_de_passe=?"
					+ " WHERE idConseiller=? ";

			// 2. creation preparedStatement
			ps = this.connection.prepareStatement(requeteUp);

			ps.setString(1, t.getNom());
			ps.setString(2, t.getPrenom());
			ps.setString(3, t.getMail());
			ps.setString(4, t.getTelephone());
			ps.setString(5, t.getIdentifiant());
			ps.setString(6, t.getMotDePasse());
			ps.setInt(7, t.getIdConseiller());

			// 3. exe de la requete et recup resultat (update car modif)
			int verifUp = ps.executeUpdate();

			return verifUp == 1;

		} catch (SQLException e) {
			System.out.println("...Erreur lors de la modif d'un objet Conseiller de la bdd ...");
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
			String requeteDel = "DELETE FROM conseillers WHERE idConseiller=? ";

			// 2. creation preparedStatement
			ps = this.connection.prepareStatement(requeteDel);

			ps.setInt(1, id);

			// 3. exe de la requete et recup resultat (update car modif)
			int verifDel = ps.executeUpdate();

			return verifDel == 1;

		} catch (SQLException e) {
			System.out.println("...Erreur lors de la suppression d'un objet Conseiller de la bdd ...");
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
	public List<Conseiller> getAll() {
		PreparedStatement ps = null;
		ResultSet resultatRequete = null;

		try {
			// 1. définition du contenu de la requete SQL
			String requetGetAll = "SELECT * FROM conseillers";

			// 2. def de l'objet "Statement" qui permet d'envoyer les requetes vers la bdd
			ps = this.connection.prepareStatement(requetGetAll);
			
			// 3. Envoi et exe de la requette
			resultatRequete = ps.executeQuery();

			// 4. extraction des données du ResultSet

			Conseiller conseiller = null;
			List<Conseiller> listeConseillers = new ArrayList<>();

			while (resultatRequete.next()) {
				
				// 4.1 recup de l'id du type de chambre
				int conseillerId = resultatRequete.getInt(1);
				
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
				conseiller = new Conseiller(conseillerId, nom,prenom,mail,tel,identif,mdp);

				// 4.6 ajout du type de chambre à la liste
				listeConseillers.add(conseiller);

			} // end while

			// 5. renvoie de la liste
			return listeConseillers;

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
	public Conseiller getById(Integer id) {
		PreparedStatement ps =null;
		ResultSet resultSet =null;
		
		try {
			// 1. definition du contenue de la requette
			String requeteById = " SELECT * FROM conseillers WHERE idConseiller =?";
			
			// 2. statement		
			ps = this.connection.prepareStatement(requeteById);
			ps.setInt(1, id);
			// 3. result set -> envoie requete
			resultSet = ps.executeQuery();
			
			// 4. extraction des données
			
			// 4.1.  init tete de lecture avec next()
			resultSet.next();
			
			// 4.2. recup des données
			Conseiller conseiller = new Conseiller(resultSet.getInt(1),
							  					 resultSet.getString(2),
							  					 resultSet.getString(3),
							  					 resultSet.getString(4),
							  					 resultSet.getString(5),
							  					 resultSet.getString(6),
							  					 resultSet.getString(7)				
												);	
			
			return conseiller;
			
		} catch(SQLException e ) {
			System.out.println("... Erreur lors de la recup du conseiller via son id dans ConseillerDAOImpl...");
			e.printStackTrace();
		
		}finally {
			// 6. fermeture des ressources (connexion, ResultSet, Statement)
			try {
				if(resultSet != null ) {resultSet.close();}
				if(ps != null ) {ps.close();}
			} catch (SQLException e) {
				e.printStackTrace();
			}// end catch
			
		}// end finally

		return null;
	}

	
	/**
	 * -----------------------------------------------------------------------------------------------
	 */
	@Override
	public boolean isConseillerExists(String pIdentifiant, String pMotDePasse) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			// 1. contenu requete
			String requeteExists = "SELECT COUNT(idConseiller) FROM conseillers "
								 + "WHERE identifiant = ? AND mot_de_passe = ?";

			// 2. def de la requete JDBC
			ps = this.connection.prepareStatement(requeteExists);

			// 3. passage de param a la requete
			ps.setString(1, pIdentifiant);
			ps.setString(2, pMotDePasse);

			// 4. exe requete
			rs = ps.executeQuery();

			// 5. extraction des données du rs

			// 5.1. init de la tete de lectue
			rs.next();

			// 5.2. extraction
			int verif = rs.getInt(1);

			// 6 renvoi du resultat
			return (verif == 1);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} // end catch

		} // end finally
		return false;
	}


	/**
	 * ===============================================================================================================
	 */
	@Override
	public int getIdConseillerByLogin(String pIdentifiant, String pMotDePasse) {

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			// 1. contenu requete
			String requeteExists = "SELECT idConseiller FROM conseillers WHERE identifiant=? and mot_de_passe=?";
								

			// 2. def de la requete JDBC
			ps = this.connection.prepareStatement(requeteExists);

			// 3. passage de param a la requete
			ps.setString(1, pIdentifiant);
			ps.setString(2, pMotDePasse);
			
			// 4. exe requete
			rs = ps.executeQuery();
			
			// 5. recup id du resultSet
			while (rs.next()) {
				
				// 5.1 recup de l'id du type de chambre
				int conseillerId = rs.getInt(1);
				return conseillerId;
			}

			
			

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} // end catch

		} // end finally		
		
		
		return 0;
		
	}//end getIdConseillerByLogin

	
	/**
	 * ===============================================================================================================
	 *//*
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
	
	}//end getConseillerAllClients  */

	@Override
	public List<Conseiller> getConseillerAllConseiller(int id_admin) {
		PreparedStatement ps = null;
		ResultSet resultatRequete = null;

		try {
			// 1. définition du contenu de la requete SQL
			String requetGetAll = "select * from conseillers where id_admin=?";

			// 2. def de l'objet "Statement" qui permet d'envoyer les requetes vers la bdd
			ps = this.connection.prepareStatement(requetGetAll);
			ps.setInt(1, id_admin);
			
			// 3. Envoi et exe de la requette
			resultatRequete = ps.executeQuery();

			// 4. extraction des données du ResultSet
			// 4.0. initialisation de la variable temp conseiller pour stocker chaque conseiller stocké et de la liste finale
			Conseiller conseiller = null;
			List<Conseiller> listeConseillers = new ArrayList<>();

			while (resultatRequete.next()) {
				
				// 4.1 recup de l'id du type de chambre
				int conseillerId = resultatRequete.getInt(1);
				
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
				conseiller = new Conseiller(conseillerId, nom,prenom,mail,tel,identif,mdp);

				// 4.6 ajout du type de chambre à la liste
				listeConseillers.add(conseiller);

			} // end while

			// 5. renvoie de la liste
			return listeConseillers;

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
	}//end getConseillerAllConseiller

	
	
}// end classe
