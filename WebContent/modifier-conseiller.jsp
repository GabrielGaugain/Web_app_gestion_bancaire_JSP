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

	<h2>Modification d'un Conseileler</h2>
	<%-- ==================================================================== --%>
	<%-- ======= Recup du Conseiller à modifier sauvegardé dans la requete======= --%>
	<%-- ==================================================================== --%>
	<%--
			> la requete est envoyée après la délégation dans 'GestionConseillerServlet'
			
				-> attribut de requete : request.setAttribute("conseiller_modif",conseillerMod);
	
	 --%>
	<%
		Conseiller conseillerUpdate = (Conseiller)request.getAttribute("conseiller_modif");
	%>

	<%-- ==================================================================== --%>
	<%-- ======= Formulaire pour modifier le conseiller ========================= --%>
	<%-- ==================================================================== --%>
	
	<form method="POST" action="gestion-conseiller-servlet">
		
		<table>
			<tr>
				<td>Nom :</td>
				<td><input type="text" value="<%= conseillerUpdate.getNom() %>" name="p-nom"/> </td>
			</tr>
			<tr>
				<td>Prenom :</td>
				<td><input type="text" value="<%=conseillerUpdate.getPrenom()%>" name = "p-prenom" /> </td>
			</tr>
			<tr>
				<td>Mail :</td>
				<td><input type="text" value="<%= conseillerUpdate.getMail() %>" name = "p-mail" /> </td>
			</tr>			
			<tr>
				<td>Téléphone :</td>
				<td><input type="text" value="<%= conseillerUpdate.getTelephone() %>" name = "p-tel" /> </td>
			</tr>			
			<tr>
				<td>Identifiant :</td>
				<td><input type="text" value="<%= conseillerUpdate.getIdentifiant()  %> " name = "p-identifiant"/> </td>
			</tr>			
			<tr>
				<td>Mot de passe :</td>
				<td><input type="text" value="<%= conseillerUpdate.getMotDePasse() %> " name = "p-mdp"/> </td>
			</tr>			
		
			<%-- passage de l'id du conseiller comme parametre de la requete dans un champ caché --%>
			<tr>
				<td><input type="hidden" value="<%= conseillerUpdate.getIdConseiller() %>" name="p-idConseiller"/></td>
			</tr>
			<%-- passage du param action dans la requete. sa valeur est 'modification' --%>
			<tr>
				<td><input type="hidden" value="modification" name="action"/></td>
			</tr>			
				
			<tr>
				<td></td>
				<td><input type="submit" value="Modifier"/> </td>
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