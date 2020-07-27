<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%-- Import de la classe Client --%>
<%@page import="com.intiformation.gestionbanque.modele.Conseiller" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Page de modification du conseiller</title>

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
	<%-- ======= Affichage du button deconnexion (avec Font Awesome) ======== --%>
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
	<div class="deconnect">
		<form method="POST" action="gestion-conseiller-servlet">
			<input type="hidden" name="action" value="gotoaccueil"/>	
			<a class="fa fa-arrow-circle-left fa-2x"  href="javascript:;" onclick="parentNode.submit();" ></a> 	
		</form>
	</div>

	<%-- ==================================================================== --%>
	<%-- ======= Recup du Conseiller à modifier sauvegardé dans la requete======= --%>
	<%-- ==================================================================== --%>


	<h2>Ajout d'un nouveau conseiller</h2>
	<%-- ==================================================================== --%>
	<%-- ======= Formulaire pour modifier le conseiller ========================= --%>
	<%-- ==================================================================== --%>
	
	<form method="POST" action="gestion-conseiller-servlet">
		
		<table>
			<tr>
				<td>Nom :</td>
				<td><input type="text"  name="p-nom"/> </td>
			</tr>
			<tr>
				<td>Prenom :</td>
				<td><input type="text"  name="p-prenom"/> </td>
			</tr>
			<tr>
				<td>Mail :</td>
				<td><input type="text"  name="p-mail"/> </td>
			</tr>			
			<tr>
				<td>Téléphone :</td>
				<td><input type="text"  name="p-tel"/> </td>
			</tr>			
			<tr>
				<td>Identifiant :</td>
				<td><input type="text"  name="p-identifiant"/> </td>
			</tr>			
			<tr>
				<td>Mot de passe :</td>
				<td><input type="text"  name="p-mdp"/> </td>
			</tr>			
		
			<tr>
				<td><input type="hidden" value="ajout" name="action"/></td>
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