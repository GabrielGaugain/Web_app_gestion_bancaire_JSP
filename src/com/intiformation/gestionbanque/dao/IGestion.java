package com.intiformation.gestionbanque.dao;

import java.sql.Connection;
import java.util.List;

import com.intiformation.gestionbanque.tool.DBConnection;

/**
 * interface g�n�rique de la couche DAO interface de base pour tout type de DAO.
 * L'interface declare les m�thodes � exposer dans la DAO les m�thodes sont
 * communes � tout type de DAO
 * 
 * @author gabri
 *
 */
public interface IGestion<T> {

	// recup d'une connexion vers la bdd via l'utilitaire ConnexionBdd
	public Connection connection = DBConnection.getInstance();

	// d�claration des m�thodes � exposer dans la DAO
	/**
	 * permet d'ajouter dans la bdd tout type d'objet.
	 * 
	 * @param t
	 *            : obj � ajouter
	 * @return true si ajout ok false sinon
	 */
	public boolean add(T t);

	/**
	 * permet de modifier dans la bdd tout type d'objet
	 * 
	 * @param t
	 *            : obj � modifier
	 * @return true si modif ok, sinon false
	 */
	public boolean update(T t);

	/**
	 * permet de supprimer de la bdd tout type d'obj
	 * 
	 * @param id
	 *            : cl� primaire de l'obj � supprimer
	 * @return true si suppression ok, sinon false
	 */
	public boolean delete(Integer id);

	/**
	 * permet de recup la liste d'objets dans la bdd
	 * 
	 * @return List des obj
	 */
	public List<T> getAll();

	/**
	 * permet de recup un obj par son id (cl� primaire)
	 * 
	 * @param id
	 *            : cl� primaire (integer)
	 * @return T : objet associ� � la cl� primaire
	 */
	public T getById(Integer id);

}// end interface
