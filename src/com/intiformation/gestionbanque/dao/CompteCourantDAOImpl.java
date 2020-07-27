package com.intiformation.gestionbanque.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.intiformation.gestionbanque.modele.CompteCourant;

public class CompteCourantDAOImpl implements ICompteCourantDAO {

	@Override
	public boolean add(CompteCourant t) {

		PreparedStatement ps = null;

		try {

			String requeteAdd = "INSERT INTO comptes_courants (num_CC, solde, decouvert_autorise)"
					+ "VALUES (?, ?, ?)";

			ps = this.connection.prepareStatement(requeteAdd);

			ps.setInt(1, t.getNumeroCompte());
			ps.setDouble(2, t.getSoldeCompte());
			ps.setDouble(3, t.getDecouvertAutorise());
		

			int verifAjout = ps.executeUpdate();

			return verifAjout == 1;

		} catch (SQLException e) {
			System.out.println("...Erreur lors de l'ajout d'un compte courant (couche DAO)...");
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
	public boolean update(CompteCourant t) {

		PreparedStatement ps = null;

		try {

			String requeteAdd = "UPDATE comptes_courants SET solde=?, decouvert_autorise=? "
					+ "WHERE num_CC=?";

			ps = this.connection.prepareStatement(requeteAdd);

			ps.setDouble(1, t.getSoldeCompte());
			ps.setDouble(2, t.getDecouvertAutorise());
			//ps.setInt(3, t.getIdTitulaire());
			ps.setInt(3, t.getNumeroCompte());

			int verifMofid = ps.executeUpdate();

			return verifMofid == 1;

		} catch (SQLException e) {
			System.out.println("...Erreur lors de la modif d'un compte courant (couche DAO)...");
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

			String requeteAdd = "DELETE FROM comptes_courants WHERE num_CC=?";

			ps = this.connection.prepareStatement(requeteAdd);
			ps.setInt(1, id);

			int verifMofid = ps.executeUpdate();

			return verifMofid == 1;

		} catch (SQLException e) {
			System.out.println("...Erreur lors de la suppression d'un compte courant (couche DAO)...");
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
	public List<CompteCourant> getAll() {

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			String requeteAll = "SELECT * from comptes_courants";

			ps = this.connection.prepareStatement(requeteAll);

			rs = ps.executeQuery();

			List<CompteCourant> listeCompteCourant = new ArrayList<>();
			CompteCourant compte = null;

			while (rs.next()) {

				int numCC = rs.getInt(1);
				double solde = rs.getDouble(2);
				double decouvert = rs.getDouble(3);
				int idProp = rs.getInt(4);

				compte = new CompteCourant(numCC, idProp, solde, decouvert);
				listeCompteCourant.add(compte);

			} // end while

			return listeCompteCourant;

		} catch (SQLException e) {
			System.out.println("...Erreur lors de la recup et affichage des compte courant (couche DAO)...");
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
	public CompteCourant getById(Integer id) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			String requeteAll = "SELECT * from comptes_courants WHERE num_CC = ?";

			ps = this.connection.prepareStatement(requeteAll);
			ps.setInt(1, id);
			
			rs = ps.executeQuery();
			rs.next();
			
			int numCC = id;
			double solde = rs.getDouble(2);
			double decouvert = rs.getDouble(3);
			int idProp = rs.getInt(4);

			CompteCourant compte = new CompteCourant(numCC, idProp, solde, decouvert);

			return compte;

		} catch (SQLException e) {
			System.out.println("...Erreur lors de la recup et affichage d'un compte courant par son id (couche DAO)...");
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
	public CompteCourant getByOwnerId(Integer id) {
		
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			String requeteAll = "SELECT * from comptes_courants WHERE id_proprio = ?";

			ps = this.connection.prepareStatement(requeteAll);
			ps.setInt(1, id);
			
			rs = ps.executeQuery();
			rs.next();
			
			int numCC = rs.getInt(1);
			double solde = rs.getDouble(2);
			double decouvert = rs.getDouble(3);
			int idProp = rs.getInt(4);

			CompteCourant compte = new CompteCourant(numCC, idProp, solde, decouvert);

			return compte;

		} catch (SQLException e) {
			System.out.println("...Erreur lors de la recup et affichage d'un compte courant par l'id de son proprio (couche DAO)...");
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

			String requeteAdd = "UPDATE comptes_courants SET id_proprio=? "
					+ "WHERE num_CC=?";

			ps = this.connection.prepareStatement(requeteAdd);

			ps.setInt(1, idClient);
			ps.setInt(2, num_compte);

			int verifMofid = ps.executeUpdate();

			return verifMofid == 1;

		} catch (SQLException e) {
			System.out.println("...Erreur lors de l'attribution du compte courant (couche DAO)...");
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
