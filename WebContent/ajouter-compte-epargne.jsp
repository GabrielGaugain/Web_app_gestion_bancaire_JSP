<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%-- Import de la classe Client --%>
<%@page import="com.intiformation.gestionbanque.modele.Client" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Page d'ajout d'un compte</title>

<!-- link vers bootstrap -->
<link rel="stylesheet" href="./assets/styles/bootstrap.min.css">

<!-- Lien vers font awesome 4.7.0-->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css">

<!--  lien vers le style perso -->
<link rel="stylesheet" href="./assets/styles/style.css">

</head>
<body>


	<br/>
	<%-- ==================================================================== --%>
	<%-- ======= Affichage du button deconnexion ============================ --%>
	<%-- ==================================================================== --%>
	<div class="deconnect" style="float:right;">
			<%-- vérif de la pésence de l'attribut de session 'isLogged' --%>
			<c:if test="${ not empty isLogged}">
				<%-- l'utilisateur est déjà co => affichage du boutton se déconnecter --%>
				<%--
					> au click sur le lien se déconnecter, on va envoyer une requete http au serveur 
					  pour demander au serveur de faire deconnecter l'utilisateur
					  
					  	-> méthode de requete doGet()
						
						-> envoi de la requete vers la servlet 'AuthentificationServlet'
						   ayant l'url '/authentification_servlet'
						   
						-> passage de param 'destroy' avec la valeur 'true'
						
					> la requete part ver sla servlet 'AuthentificationServlet'
					
						->  envoi d'un param		
				 --%>
				 <a class="fa fa-power-off fa-2x" href="authentification_servlet?destroy=true"></a>
			</c:if>
	</div>
	<div class="deconnect"><a class="fa fa-arrow-circle-left fa-2x" href="accueil-conseil.jsp"></a></div>

	<%-- ==================================================================== --%>
	<%-- ======= Formulaire d'ajout d'un nouveau client ===================== --%>
	<%-- ==================================================================== --%>

	<h2>Ajout d'un nouveau compte epargne</h2>

	<%--
			> a la soumission du formulaire (click sur le boutton Ajouter)
			
				-> envoi d'une requete HTTP en POST (ref : l'attribut method de <form>)
				
				-> envoi de la requete vers la servlet 'GestionClientServlet'
				   ayant l'url '/gestion-client-servlet' (ref : l'attribut action de <form>)
				   
				-> passage des valeurs de sinput comme parametres de la requete (ref : attribut name des <input type="text" /> )
				   
				-> 7 params à passer à la requete 
					p-nom/ p-prenom/p-adresse/p-cp/p-ville/p-tel / action
				   
			> au niveau de la servlet 'GestionClientServlet' :
			
					-> requete HTTP en POST => le traitement se fera dans la méthode doPost() de la servlet
	 --%>
	
	
	
	<form method="POST" action="gestion-compte-servlet">
		
		<table>
			<tr>
				<td>Numero du compte :</td>
				<td><input type="text"  name="p-numCompte"/> </td>
			</tr>		
			<tr>
				<td>Solde du compte :</td>
				<td><input type="text"  name="p-solde"/> </td>
			</tr>
			<tr>
				<td>Taux d'interets :</td>
				<td><input type="text"  name = "p-decouvert" /> </td>
			</tr>
			<tr>
				<td>Reférence Client :</td>
				<td><input type="text"  name = "p-titulaire" /> </td>
			</tr>			
		
		
			
			<%-- passage du param 'action' dans la requete. sa valeur est 'ajout' --%>
			<tr>
				<td><input type="hidden" value="ajout-compte-epargne" name="action"/></td>
			</tr>			
				
			<tr>
				<td></td>
				<td><input type="submit" value="Ajouter"/> </td>
			</tr>					
		</table>
	
	</form>
	 <footer>
        <p>
            Copyright &copy; INTI-FORMATION 2020

        </p>
    </footer><!-- end footer-->
</body>
</html>