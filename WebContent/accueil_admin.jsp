<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%-- taglist core de la JSTL --%>    
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Page d'accueil - gestion conseillers</title>

<!-- link vers bootstrap -->
<link rel="stylesheet" href="./assets/styles/bootstrap.min.css">

<!-- Lien vers font awesome 4.7.0-->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css">

<!--  lien vers le style perso -->
<link rel="stylesheet" href="./assets/styles/style.css">

<style>
	th{
		padding-top:10px;
		padding-bottom: 10px;
	}
	td{
		padding-top:10px;
		padding-bottom: 10px;
	}	
	
	.deconnectAdmin{		
		position:absolute;
		top: 30px;
		right :20px;
		width:5%;
		margin-top:0;
		}
		
	.deconnectAdmin a{
		text-decoration: none;
		color: #E6E6E6;
		}	
	
</style>
</head>
<body>

	<div class="fa fa-user-circle-o fa-4x" style="float:left;margin-left: 6%;margin-top:0px;margin-right: 0"></div>
	<div style="width: 80%; margin-left:10%;"><h1>Bienvenue dans notre application de gestion des conseillers</h1></div>
	
	<%-- ==================================================================== --%>
	<%-- ======= Affichage du button deconnexion ============================ --%>
	<%-- ==================================================================== --%>
	<div class="deconnectAdmin" >
			<%-- vérif de la pésence de l'attribut de session 'isLogged' --%>
			<c:if test="${ not empty isLogged}">
				 <a class="fa fa-sign-out fa-3x" href="authentification_servlet?destroy=true"></a>
			</c:if>
	</div>

	<br/><br/><br/>
	<%-- ======================================================================== --%>
	<%-- ======= Affichage de la liste des conseillers ========================== --%>
	<%-- ======================================================================== --%>
	
	 <%-- lien pour ajouter un client --%>
	 <a class="fa fa-plus-square" href="ajouter-conseiller.jsp" style="float:right;color:#E6E6E6;margin-right:20px"> Ajouter un conseiller</a>
	 <br/>
	 
	 
	 <table border="1" width="80%" align="center">
	 	<!--  header de la table -->
	 	<thead >
	 		<tr>
	 			<th>ID conseiller </th>
	 			<th>Nom </th>
	 			<th>Prénom </th>
	 			<th>Mail</th>
	 			<th>Téléphone</th>
	 			<th>Identifiant</th>
	 			<th>Mot de passe</th>
	 		</tr>


	 	</thead>
	 	
	 	<tbody>
	 		<c:forEach items="${liste_conseillers}" var="clt">
	 			<tr>
	 				<td>${clt.idConseiller}</td>
	 				<td>${clt.nom }</td>
	 				<td>${clt.prenom }</td>
	 				<td>${clt.mail }</td>
	 				<td>${clt.telephone }</td>
	 				<td>${clt.identifiant }</td>
	 				<td>${clt.motDePasse }</td>
	 				
			 		<%-- Colonne (lien) pour la modif du client --%>
			 		<%--
			 				> au click sur le lien modifier :
			 				
			 					-> envoi d'une requete HTTP GET vers la servlet 'GestionClientServlet' ayant l'url '/gestion-client-servlet'
			 					
			 					-> passage de 2 params:
			 							- 1er param 'action' avec la valeur 'modif'
			 							- 2eme param 'id-client' avec la valeur l'id du client selectionné
			 							
			 					-> dans la servlet 'GestionClientServlet' 
			 					
			 						-> requete HTTP en GET => le traitement de  la requete se fera dans la méthode doGet() de la servlet
			 		 --%>	 				
	 				<td>
	 					<a class="fa fa-pencil-square-o" href="gestion-conseiller-servlet?action=modif&id-conseiller=${clt.idConseiller }"> modifier</a>
	 				</td>
					<td>
	 					<a class="fa fa-trash" href="gestion-conseiller-servlet?action=supp&id-conseiller=${clt.idConseiller }"> supprimer</a>
	 				</td>
	 				
	 			</tr>
	 		
	 		</c:forEach> 	
	 	</tbody>	 	
	 	
	 </table>
	
	 <footer>
        <p>
            Copyright &copy; INTI-FORMATION 2020

        </p>
    </footer><!-- end footer-->

</body>
</html>