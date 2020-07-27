<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%-- Import de la classe Client --%>
<%@page import="com.intiformation.gestionbanque.modele.CompteEpargne" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Page de Dépot du compte courant</title>

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
	<%-- ======= Recup du client à modifier sauvegardé dans la requete======= --%>
	<%-- ==================================================================== --%>

	<%
		CompteEpargne ccUpdate = (CompteEpargne)request.getAttribute("compte_depot");
	%>

	<%-- ==================================================================== --%>
	<%-- ======= Formulaire pour modifier le client ========================= --%>
	<%-- ==================================================================== --%>

	
	<form method="POST" action="gestion-compte-servlet">
		
		<table>
			<tr>
				<td>Solde du compte :</td>
				<td><p > <%= ccUpdate.getSoldeCompte() %>  </p> </td>
			</tr>
	
			<tr>
				<td>Entrer la valeur à déposer :</td>
				<td><input type="text"  name = "p-depot" /> </td>
			</tr>			
		
		
		
			<%-- passage de l'id du client comme parametre de la requete dans un champ caché --%>
			<tr>
				<td><input type="hidden" value="<%= ccUpdate.getNumeroCompte() %>" name="p-numCompte"/></td>
			</tr>
			<%-- passage du param action dans la requete. sa valeur est 'modification' --%>
			<tr>
				<td><input type="hidden" value="update-depotCE" name="action"/></td>
			</tr>			
				
			<tr>
				<td></td>
				<td><input type="submit" value="Retirer"/> </td>
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