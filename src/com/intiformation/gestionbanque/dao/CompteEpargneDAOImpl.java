package com.intiformation.gestionbanque.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.intiformation.gestionbanque.modele.CompteEpargne;

public class CompteEpargneDAOImpl implements ICompteEpargneDAO{

	@Override
	public boolean add(CompteEpargne t) {

		PreparedStatement ps = null;

		try {

			String requeteAdd = "INSERT INTO comptes_epargnes (num_CE, solde,taux_interets)"
					+ "VALUES (?, ?, ?)";

			ps = this.connection.prepareStatement(requeteAdd);

			ps.setInt(1, t.getNumeroCompte());
			ps.setDouble(2, t.getSoldeCompte());
			ps.setDouble(3, t.getTauxInterets());
		

			int verifAjout = ps.executeUpdate();

			return verifAjout == 1;

		} catch (SQLException e) {
			System.out.println("...Erreur lors de l'ajout d'un compte Epargne (couche DAO)...");
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

	}// end add

	@Override
	public boolean update(CompteEpargne t) {

		PreparedStatement ps = null;

		try {

			String requeteAdd = "UPDATE comptes_epargnes SET solde=?, taux_interets=? "
					+ "WHERE num_CE=?";

			ps = this.connection.prepareStatement(requeteAdd);

			ps.setDouble(1, t.getSoldeCompte());
			ps.setDouble(2, t.getTauxInterets());
			//ps.setInt(3, t.getIdTitulaire());
			ps.setInt(3, t.getNumeroCompte());

			int verifMofid = ps.executeUpdate();

			return verifMofid == 1;

		} catch (SQLException e) {
			System.out.println("...Erreur lors de la modif d'un compte Epargne (couche DAO)...");
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

	@Override
	public boolean delete(Integer id) {

		PreparedStatement ps = null;

		try {

			String requeteAdd = "DELETE FROM comptes_epargnes WHERE num_CE=?";

			ps = this.connection.prepareStatement(requeteAdd);
			ps.setInt(1, id);

			int verifMofid = ps.executeUpdate();

			return verifMofid == 1;

		} catch (SQLException e) {
			System.out.println("...Erreur lors de la suppression d'un compte Epargne (couche DAO)...");
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

	@Override
	public List<CompteEpargne> getAll() {

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			String requeteAll = "SELECT * from comptes_epargnes";

			ps = this.connection.prepareStatement(requeteAll);

			rs = ps.executeQuery();

			List<CompteEpargne> listeCompteEpargne = new ArrayList<>();
			CompteEpargne compte = null;

			while (rs.next()) {

				int numCompte = rs.getInt(1);
				double solde = rs.getDouble(2);
				double taux = rs.getDouble(3);
				int idProp = rs.getInt(4);

				compte = new CompteEpargne(numCompte, idProp, solde, taux);
				listeCompteEpargne.add(compte);

			} // end while

			return listeCompteEpargne;

		} catch (SQLException e) {
			System.out.println("...Erreur lors de la recup et affichage des compte Epargne (couche DAO)...");
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		} // end finally

		return null;
	}// end getAll

	@Override
	public CompteEpargne getById(Integer id) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			String requeteAll = "SELECT * from comptes_epargnes WHERE num_CE = ?";

			ps = this.connection.prepareStatement(requeteAll);
			ps.setInt(1, id);
			
			rs = ps.executeQuery();
			rs.next();
			
			int numCompte = rs.getInt(1);
			double solde = rs.getDouble(2);
			double decouvert = rs.getDouble(3);
			int idProp = rs.getInt(4);

			CompteEpargne compte = new CompteEpargne(numCompte, idProp, solde, decouvert);

			return compte;

		} catch (SQLException e) {
			System.out.println("...Erreur lors de la recup et affichage d'un compte Epargne par son id (couche DAO)...");
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		} // end finally

		return null;

	}

	@Override
	public CompteEpargne getByOwnerId(Integer id) {
		
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			String requeteAll = "SELECT * from comptes_epargnes WHERE proprio_id = ?";

			ps = this.connection.prepareStatement(requeteAll);
			ps.setInt(1, id);
			
			rs = ps.executeQuery();
			rs.next();
			
			int numCompte = rs.getInt(1);
			double solde = rs.getDouble(2);
			double decouvert = rs.getDouble(3);
			int idProp = rs.getInt(4);

			CompteEpargne compte = new CompteEpargne(numCompte, idProp, solde, decouvert);

			return compte;

		} catch (SQLException e) {
			System.out.println("...Erreur lors de la recup et affichage d'un compte Epargne par l'id de son proprio (couche DAO)...");
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		} // end finally

		return null;
	}

	@Override
	public boolean linkToOwner(Integer num_compte, Integer idClient) {
		PreparedStatement ps = null;

		try {

			String requeteAdd = "UPDATE comptes_epargnes SET proprio_id=? "
					+ "WHERE num_CE=?";

			ps = this.connection.prepareStatement(requeteAdd);

			ps.setInt(1, idClient);
			ps.setInt(2, num_compte);

			int verifMofid = ps.executeUpdate();

			return verifMofid == 1;

		} catch (SQLException e) {
			System.out.println("...Erreur lors de l'attribution du compte épargne (couche DAO)...");
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
	}//end linkToOwner


}
