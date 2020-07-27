package com.intiformation.gestionbanque.tool;

import java.util.Scanner;



/**
 * interface utilitaire qui permet de r�cup�rer la saisie de 
 * l'utilisateur au clavier sous diff�rents format. 
 * @author INTIFORMATION
 *
 */
public interface SaisieTool {

	/**
	 * r�cup de la saisie de l'utilisateur au clavier via 
	 * la classe Scanner en String.
	 * @return la saisie 
	 */
	public static String lireString() {
		
		// 1. var = saisie de l'utilisateur 
		String ligneLue = null; 
		
		// 2. cr�ation d'un objet Scanner 
		Scanner scanner = new Scanner(System.in);
		
		// 3. r�cup de la saisie de l'utilisateur via le scanner 
		ligneLue = scanner.nextLine();
		
		// 4. renvoi de la saisie 
		return ligneLue;
		
	}//end lireString()
	
	
	/**
	 * r�cup de la saisie de l'utilisateur au clavier via en String.
	 * @param pMessage : le message � afficher � l'utilisateur avant la r�cup de la saisie 
	 * @return
	 */
	public static String lireString(String pMessage) {
		
		// 1. affichage du message 
		System.out.println("\n" + pMessage);
		
		// 2. r�cuo de la sasie via la m�thode lireString()
		return lireString();
		
	}//end lireString avec message 
	
	/**
	 * r�cup de la saisie de l'utilisateur au clavier en int.
	 * @return
	 */
	public static int lireInt() {
		
		// r�cup de la saisie en String via la m�thode lireString() et conversion de la 
		// sasie en int => la conversion se fait via la m�thode parseInt() de la classe Integer
		
		// 1. r�cup de la saisie en String 
		String saisieEnString = lireString();
		
		// 2. conversion de la saisie de String vers int via la m�thode parseInt() de la classe Integer
		int saisieEnInt = Integer.parseInt(saisieEnString);
		
		// 3. renvoi de la saisie en int 
		return saisieEnInt;
		
		// simplification des �tapes 1) 2) et 3)
		/*
		return Integer.parseInt(lireString());
		*/
		
	}//end lireInt()
	
	/**
	 * r�cup de la saisie de l'utilisateur au clavier en int avec un message 
	 * @param pMessage le message � afficher avant la r�cup 
	 * @return
	 */
	public static int lireInt(String pMessage) {
		
		System.out.println("\n " + pMessage);
		
		return lireInt();
		
	}//end  lireInt
	
	/**
	 * r�cup de la saisie de l'utilisateur au clavier en double 
	 * @return
	 */
	public static double lireDouble() {
		// r�cup de la sasie en String + conversion en double 
		// avec parseDouble() de la classe Double 
		return Double.parseDouble(lireString());
	}//end lireDouble
	
	/**
	 * r�cup de la saisie de l'utilisateur au clavier en double avec un message 
	 * @param pMessage le message � afficher avant la r�cup 
	 * @return
	 */
	public static double lireDouble(String pMessage) {
		System.out.println("\n " + pMessage);
		return lireDouble();
	}//end lireDouble
	

}//end interface 










