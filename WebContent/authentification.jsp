<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%-- déclaration de la taglib core de la JSTL --%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
    
<!doctype html>
<html lang="fr">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="icon" href="/docs/4.0/assets/img/favicons/favicon.ico">

    <title>Page d'authentification</title>

     <!-- Bootstrap core CSS -->
    <link href="./assets/styles/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="./assets/styles/signin.css" rel="stylesheet">
    
    <!-- Lien vers font awesome 4.7.0-->
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css">
    
	 <!--  lien vers le style perso -->
	<link rel="stylesheet" href="./assets/styles/style.css">   
  </head>

  <body class="text-center">

  <!-- ======================================================================================= -->
  <!-- ===========Affichage des messages d'erreurs =========================================== -->
  <!-- ======================================================================================= -->  
  <!-- 
  		> affichage du contenu de la liste des messages sauvegardées dans la requete lors 
  		de la délégation : (ref: servlet AuthentifiactionServlet => étape 2.2.3.2)
  		
  			> attribut de la requete = request.setAttribute("messages_erreurs","listeMessagesErreurs")
   -->
	  <div>
	  		<ul>
	  			<c:forEach items="${messages_erreurs}" var="message">
	  			
	  				<li style="color:red;font-style:italic;">
	  					<c:out value="${message }"> </c:out>
	  				</li>
	  			
	  			</c:forEach>
	  		</ul>
	  </div>
  
  <!-- ======================================================================================= -->
  <!-- ===========Formulaire d'authentification ============================================== -->
  <!-- ======================================================================================= -->
  <!-- 
  		> A la soumission du formulaire (se connecter) :
  				-> invocation de la servlet 'AuthentificationServlet' via son url '/authentification_servlet' (ref attribut 'action' de <form>) = envoie d'une requete http en POST (method de <form>):
  						passage de 2 params :		 1.  p_identifiant (input identifiant)
  													 2.  p_mdp	(input mdp)
  				
   -->
    <form class="form-signin" method="POST" action="authentification_servlet" >
    
      <h1 class="fa fa-user-circle fa-5x" style="align-content: center;color:#E6E6E6; font-size:12em"></h1>
      
      <h3 class="h3 mb-3 font-weight-normal">Veuillez vous connecter</h3>
      
      <label for="inputEmail" class="sr-only">Identifiant</label>
      <input type="text" id="inputEmail" name="p_identifiant" class="form-control" placeholder="Identifiant" required autofocus>
      
      <label for="inputPassword" class="sr-only">Mot de passe</label>
      <input type="password" id="inputPassword" name="p_mdp" class="form-control" placeholder="Mot de passe" required>
      <div>
        <p>      
          <input type="radio" name="type_co" value="admin" checked> Administrateur 
          <input type="radio" name="type_co" value="conseil" style="margin-left:10px"> Conseiller
        </p>
      </div>
      <button class="btn btn-lg btn-primary btn-block" type="submit">Se connecter</button>
      <p class="mt-5 mb-3 text-muted">&copy; INTI 2020</p>
    </form>
  </body>
</html>
