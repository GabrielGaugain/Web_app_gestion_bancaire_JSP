package com.intiformation.gestionbanque.tool;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Utilitaire pouir recup une connection vers la bdd.
 * @author gabri
 *
 */
public class DBConnection {

	// infos de connection à la bdd
	private static final String DB_URL ="jdbc:mysql://localhost:3306/db_gestion_banque";
	private static final String DB_USER ="root";
	private static final String DB_PWD ="root";
	private static final String JDBC_DRIVER_CLASS = "com.mysql.jdbc.Driver";
	
	//instance de connection
	private static Connection connection = null;
	
	/**
	 * cotr en private pour interdire l'instanciation d'un obj de la classe
	 */
	private DBConnection() {	
	}// end ctor
	
	
	//méthode pour recup la connection à la bdd
	/**
	 * Permet de recup une connection vers la bdd.
	 * @return
	 */
	public static Connection getInstance() {
		
		if(connection == null) {
			try {
			// 1. chargement du pilote JDBC de MySQL
			Class.forName(JDBC_DRIVER_CLASS);
			
			// 2. recup de la connextino via la DriverManager
			connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PWD);
			
			
			}
			catch (ClassNotFoundException | SQLException e) {
				System.out.println("... (DBConnection) Erreur lors de la création de la connection vers la bdd....");
				e.printStackTrace();
			}
			
			
		}// end if{
		
		return connection;
	}// end getInstance()

}// end class
