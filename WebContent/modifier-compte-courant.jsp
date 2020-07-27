<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%-- Import de la classe Client --%>
<%@page import="com.intiformation.gestionbanque.modele.CompteCourant" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Page de modification du compte courant</title>

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
			<%-- v�rif de la p�sence de l'attribut de session 'isLogged' --%>
			<c:if test="${ not empty isLogged}">
				<%-- l'utilisateur est d�j� co => affichage du boutton se d�connecter --%>
				<%--
					> au click sur le lien se d�connecter, on va envoyer une requete http au serveur 
					  pour demander au serveur de faire deconnecter l'utilisateur
					  
					  	-> m�thode de requete doGet()
						
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
		<form method="POST" action="gestion-compte-servlet">
			<input type="hidden" name="action" value="gotoaccueil"/>	
			<a class="fa fa-arrow-circle-left fa-2x"  href="javascript:;" onclick="parentNode.submit();" ></a> 	
		</form>
	</div>

	<%-- ==================================================================== --%>
	<%-- ======= Recup du client � modifier sauvegard� dans la requete======= --%>
	<%-- ==================================================================== --%>
	<%--
			> la requete est envoy�e apr�s la d�l�gation dans 'GestionClientServlet'
			
				-> attribut de requete : request.setAttribute("client_modif",clientMod);
	
	 --%>
	<%
		CompteCourant ccUpdate = (CompteCourant)request.getAttribute("compte_modif");
	%>

	<%-- ==================================================================== --%>
	<%-- ======= Formulaire pour modifier le client ========================= --%>
	<%-- ==================================================================== --%>
	<%--
			> a la soumission du formulaire (click sur le boutton Modifier)
			
				-> envoi d'une requete HTTP en POST (ref : l'attribut method de <form>)
				
				-> envoi de la requete vers la servlet 'GestionClientServlet'
				   ayant l'url '/gestion-client-servlet' (ref : l'attribut action de <form>)
				   
				-> passage des valeurs de sinput comme parametres de la requete (ref : attribut name des <input type="text" /> )
				   
				-> 7 params � passer � la requete 
					p-nom/ p-prenom/p-adresse/p-cp/p-ville/p-tel/p-idClient
				   
			> au niveau de la servlet 'GestionClientServlet' :
			
					-> requete HTTP en POST => le traitement se fera dans la m�thode doPost() de la servlet
	 --%>
	
	<form method="POST" action="gestion-compte-servlet">
		
		<table>
			<tr>
				<td>Solde du compte :</td>
				<td><input type="text" value="<%= ccUpdate.getSoldeCompte() %>" name="p-solde"/> </td>
			</tr>
			<tr>
				<td>D�couvert Autoris� :</td>
				<td><input type="text" value="<%=ccUpdate.getDecouvertAutorise()%>" name = "p-decouvert" /> </td>
			</tr>
			<tr>
				<td>Ref�rence Client :</td>
				<td><input type="text" value="<%= ccUpdate.getIdTitulaire() %>" name = "p-titulaire" /> </td>
			</tr>			
		
		
		
			<%-- passage de l'id du client comme parametre de la requete dans un champ cach� --%>
			<tr>
				<td><input type="hidden" value="<%= ccUpdate.getNumeroCompte() %>" name="p-numCompte"/></td>
			</tr>
			<%-- passage du param action dans la requete. sa valeur est 'modification' --%>
			<tr>
				<td><input type="hidden" value="modification-compte-courant" name="action"/></td>
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