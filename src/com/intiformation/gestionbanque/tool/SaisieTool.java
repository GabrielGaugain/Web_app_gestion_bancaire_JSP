package com.intiformation.gestionbanque.tool;

import java.util.Scanner;



/**
 * interface utilitaire qui permet de récupérer la saisie de 
 * l'utilisateur au clavier sous différents format. 
 * @author INTIFORMATION
 *
 */
public interface SaisieTool {

	/**
	 * récup de la saisie de l'utilisateur au clavier via 
	 * la classe Scanner en String.
	 * @return la saisie 
	 */
	public static String lireString() {
		
		// 1. var = saisie de l'utilisateur 
		String ligneLue = null; 
		
		// 2. création d'un objet Scanner 
		Scanner scanner = new Scanner(System.in);
		
		// 3. récup de la saisie de l'utilisateur via le scanner 
		ligneLue = scanner.nextLine();
		
		// 4. renvoi de la saisie 
		return ligneLue;
		
	}//end lireString()
	
	
	/**
	 * récup de la saisie de l'utilisateur au clavier via en String.
	 * @param pMessage : le message à afficher à l'utilisateur avant la récup de la saisie 
	 * @return
	 */
	public static String lireString(String pMessage) {
		
		// 1. affichage du message 
		System.out.println("\n" + pMessage);
		
		// 2. récuo de la sasie via la méthode lireString()
		return lireString();
		
	}//end lireString avec message 
	
	/**
	 * récup de la saisie de l'utilisateur au clavier en int.
	 * @return
	 */
	public static int lireInt() {
		
		// récup de la saisie en String via la méthode lireString() et conversion de la 
		// sasie en int => la conversion se fait via la méthode parseInt() de la classe Integer
		
		// 1. récup de la saisie en String 
		String saisieEnString = lireString();
		
		// 2. conversion de la saisie de String vers int via la méthode parseInt() de la classe Integer
		int saisieEnInt = Integer.parseInt(saisieEnString);
		
		// 3. renvoi de la saisie en int 
		return saisieEnInt;
		
		// simplification des étapes 1) 2) et 3)
		/*
		return Integer.parseInt(lireString());
		*/
		
	}//end lireInt()
	
	/**
	 * récup de la saisie de l'utilisateur au clavier en int avec un message 
	 * @param pMessage le message à afficher avant la récup 
	 * @return
	 */
	public static int lireInt(String pMessage) {
		
		System.out.println("\n " + pMessage);
		
		return lireInt();
		
	}//end  lireInt
	
	/**
	 * récup de la saisie de l'utilisateur au clavier en double 
	 * @return
	 */
	public static double lireDouble() {
		// récup de la sasie en String + conversion en double 
		// avec parseDouble() de la classe Double 
		return Double.parseDouble(lireString());
	}//end lireDouble
	
	/**
	 * récup de la saisie de l'utilisateur au clavier en double avec un message 
	 * @param pMessage le message à afficher avant la récup 
	 * @return
	 */
	public static double lireDouble(String pMessage) {
		System.out.println("\n " + pMessage);
		return lireDouble();
	}//end lireDouble
	

}//end interface 










