package com.intiformation.gestionbanque.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.intiformation.gestionbanque.modele.Administrateur;
import com.intiformation.gestionbanque.modele.Conseiller;
import com.intiformation.gestionbanque.modele.Administrateur;

public class AdministrateurDAOImpl implements IAdministrateurDAO{

	/**
	 * -----------------------------------------------------------------------------------------------
	 */
	@Override
	public boolean add(Administrateur t) {
		PreparedStatement ps = null;

		try {

			// 1. definition du contenu de la requete SQL avec ? pour prepared statement
			String requeteAjout = "INSERT INTO administrateur (idAdmin, nom, prenom, mail, telephone, identifiant, mot_de_passe ) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?)";

			// 2. creation preparedStatement
			ps = this.connection.prepareStatement(requeteAjout);

			ps.setInt(1, t.getIdAdmin());
			ps.setString(2, t.getNom());
			ps.setString(3, t.getPrenom());
			ps.setString(4, t.getMail());
			ps.setString(5, t.getTelephone());
			ps.setString(6, t.getIdentifiant());
			ps.setString(7, t.getMotDePasse());

			// 3. exe de la requete et recup resultat (update car modif)
			int verifAjout = ps.executeUpdate();

			return verifAjout == 1;

		} catch (SQLException e) {
			System.out.println("...Erreur lors de l'ajout d'un objet Administrateur de la bdd ...");
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
	public boolean update(Administrateur t) {
		PreparedStatement ps = null;

		try {

			// 1. definition du contenu de la requete SQL avec ? pour prepared statement
			String requeteUp = "UPDATE administrateur SET nom=?, prenom=?, mail=?, telephone=?, identifiant=?,mot_de_passe=?"
					+ " WHERE idAdmin=? ";

			// 2. creation preparedStatement
			ps = this.connection.prepareStatement(requeteUp);

			ps.setString(1, t.getNom());
			ps.setString(2, t.getPrenom());
			ps.setString(3, t.getMail());
			ps.setString(4, t.getTelephone());
			ps.setString(5, t.getIdentifiant());
			ps.setString(6, t.getMotDePasse());
			ps.setInt(7, t.getIdAdmin());

			// 3. exe de la requete et recup resultat (update car modif)
			int verifUp = ps.executeUpdate();

			return verifUp == 1;

		} catch (SQLException e) {
			System.out.println("...Erreur lors de la modif d'un objet Administrateur de la bdd ...");
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
			String requeteDel = "DELETE FROM administrateur WHERE idAdmin=? ";

			// 2. creation preparedStatement
			ps = this.connection.prepareStatement(requeteDel);

			ps.setInt(1, id);

			// 3. exe de la requete et recup resultat (update car modif)
			int verifDel = ps.executeUpdate();

			return verifDel == 1;

		} catch (SQLException e) {
			System.out.println("...Erreur lors de la suppression d'un objet Administrateur de la bdd ...");
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
	public List<Administrateur> getAll() {
		PreparedStatement ps = null;
		ResultSet resultatRequete = null;

		try {
			// 1. définition du contenu de la requete SQL
			String requetGetAll = "SELECT * FROM administrateur";

			// 2. def de l'objet "Statement" qui permet d'envoyer les requetes vers la bdd
			ps = this.connection.prepareStatement(requetGetAll);

			// 3. Envoi et exe de la requette
			resultatRequete = ps.executeQuery();

			// 4. extraction des données du ResultSet

			Administrateur Administrateur = null;
			List<Administrateur> listeAdministrateurs = new ArrayList<>();

			while (resultatRequete.next()) {
				// 4.1 recup de l'id du type de chambre
				int AdministrateurId = resultatRequete.getInt(1);

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
				Administrateur = new Administrateur(AdministrateurId, nom,prenom,mail,tel,identif,mdp);

				// 4.6 ajout du type de chambre à la liste
				listeAdministrateurs.add(Administrateur);

			} // end while

			// 5. renvoie de la liste
			return listeAdministrateurs;

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
	public Administrateur getById(Integer id) {
		PreparedStatement ps =null;
		ResultSet resultSet =null;
		
		try {
			// 1. definition du contenue de la requette
			String requeteById = " SELECT * FROM administrateur WHERE idAdmin =?";
			
			// 2. statement		
			ps = this.connection.prepareStatement(requeteById);
			ps.setInt(1, id);
			// 3. result set -> envoie requete
			resultSet = ps.executeQuery();
			
			// 4. extraction des données
			
			// 4.1.  init tete de lecture avec next()
			resultSet.next();
			
			// 4.2. recup des données
			Administrateur Administrateur = new Administrateur(resultSet.getInt(1),
							  					 resultSet.getString(2),
							  					 resultSet.getString(3),
							  					 resultSet.getString(4),
							  					 resultSet.getString(5),
							  					 resultSet.getString(6),
							  					 resultSet.getString(7)				
												);	
			
			return Administrateur;
			
		} catch(SQLException e ) {
			System.out.println("... Erreur lors de la recup de l'admin via son id dans AdministrateurDAOImpl...");
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
	public boolean isAdminExists(String pIdentifiant, String pMotDePasse) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			// 1. contenu requete
			String requeteExists = "SELECT COUNT(idAdmin) FROM administrateur "
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
	 * ==============================================================================================================
	 */
	@Override
	public int getIdAdminByLogin(String pIdentifiant, String pMotDePasse) {
		
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			// 1. contenu requete
			String requeteExists = "SELECT idAdmin FROM administrateur WHERE identifiant=? and mot_de_passe=?";
								

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
				int clientId = rs.getInt(1);
				return clientId;
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
	}//end getIdAdminByLogin

	
	/**
	 * ==============================================================================================================
	 */
	/*@Override
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
	*/
	
	
}//end class
