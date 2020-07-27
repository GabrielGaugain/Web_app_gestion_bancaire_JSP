<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%-- taglist core de la JSTL --%>    
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Gestion des clients</title>

<!-- link vers bootstrap -->
<link rel="stylesheet" href="./assets/styles/bootstrap.min.css">

<!-- Lien vers font awesome 4.7.0-->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css">

<!--  lien vers le style perso -->
<link rel="stylesheet" href="./assets/styles/style.css">
    <style>
    /*
    .vignette{
   		float:left;
    	margin: 10px 10px 10px 10px;
        padding-top: 10px;
        text-align: center;

        border: 1px ridge #E6E6E6;
        width: 30%;
        height: 25rem;
        
    }*/
	.vignette{
	    min-width: 80%;
	    height: 95%;
	    margin-top: 20px;
	    margin-bottom: 10px;
	    margin-left : 10%;
	    transition: all 0.3s;
	    position: relative;
	    padding: 10px 10px;
	    text-align: center;
	    border: 1px ridge #E6E6E6;
	    width: 30%;
	    height: 25rem;
        
 	}
    .vignette b{
        font-style: italic;
    }
    
    .dropdown-toggle{
        background: #424242;
        border: 0;
        padding: 0;
        height: 2em;
        width: 2em;
    }

    .dropdown-toggle::after{
        display: none;
    }

    </style>
    
</head>

<body>

	<h1>Gestion des clients</h1>

	<%-- ==================================================================== --%>
	<%-- ======= Affichage du button deconnexion (avec Font Awesome) ======== --%>
	<%-- ==================================================================== --%>
	<div class="deconnect" style="float:right;">
			<%-- vérif de la pésence de l'attribut de session 'isLogged' --%>
			<c:if test="${ not empty isLogged}">
				 <a class="fa fa-power-off fa-2x" href="authentification_servlet?destroy=true"></a>
			</c:if>
	</div>
	<div class="deconnect"><a class="fa fa-arrow-circle-left fa-2x" href="accueil-conseil.jsp"></a></div>




	<%-- ==================================================================== --%>
	<%-- ======= Affichage de la liste des clients ========================== --%>
	<%-- ==================================================================== --%>
	<%--
		> recup de la liste des clients sauvegardées dans la requete lors 
		  de la délégation ( au moment de la connection de l'utilisateur => ref AuthentificationServlet - étape 3.1.2.3.)
		  
		> l'attribut de requete pour la liste : request.setAttribute("liste_clients",listeClientsBdd);
	 --%>
	 
	
    <div class="container-fluid" style="padding: 50px 20px">
    	
    	<a class="fa fa-plus" href="ajouter-client.jsp" style="float:right;color:#E6E6E6">Ajouter un client</a>
    	<br/>
    	
	 	<c:forEach items="${liste_clients}" var="clt" varStatus="nb_vignette">
	 	
	 	<c:if test="${nb_vignette.index % 3 == 0 }"><div class="row" style="margin:0 25px"> </c:if> 
	 	<div class="col-md-12 col-lg-4">
	        <div class="class vignette">
			
	
	            <div class="dropdown" style="float: right;margin-right: 10px;">
	               
	                    <button class="btn btn-secondary dropdown-toggle" type="button" id="deroulantb" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><i class="fa fa-toggle-down fa-2x"></i></button>
	                    <div class="dropdown-menu" aria-labelledby="deroulantb">
	                        <a class="dropdown-item" href="gestion-client-servlet?action=modif&id-client=${clt.idClient }" >Modifier</a>
	                        <a id="supprimer" class="dropdown-item" href="gestion-client-servlet?action=supp&id-client=${clt.idClient }">Supprimer</a>
	
	                    </div>
	                
	            </div>
	            <br><br>
	
	            <div class="class fa fa-user fa-3x" style="margin: 5px 0 10px 0;"></div>
	            <h5> <b > Id client :  </b>    ${clt.idClient} </h5>
	            <h5> <b > Nom :  </b>          ${clt.nom } </h5>
	            <h5> <b > Prenom : </b>        ${clt.prenom } </h5>
	            <h5> <b > Adresse :  </b>      ${clt.adresse } </h5>
	            <h5> <b > Code postal :  </b>  ${clt.codePostal }    </h5>
	            <h5> <b > Ville :  </b>        ${clt.ville }  </h5>   
	            <h5> <b > Téléphone :  </b>    ${clt.telephone }   </h5>           
	
	        </div>
        
        </div>
        
		<c:if test="${(nb_vignette.index+1)%3 == 0 }"></div> </c:if> 
        <c:if test="${ ( (nb_vignette.index+1)%3 != 0)&&( nb_vignette.count == liste_clients.size() )  }"> </div> </c:if>         
        
        </c:forEach> 
    </div>
    
   


	<button    onclick="YesOrNo()"   >Bonjour je suis un bouton test</button>


	<div > 
		<p id="testButton" > ${someValue} </p> 
		  <p id="demo"></p>
		  <p>${someValue} => valeur </p>
	</div>
	

	 <footer>
        <p>
            Copyright &copy; INTI-FORMATION 2020

        </p>
    </footer><!-- end footer-->
	 


	<!--  ////////////////////////////////////////// END HTML ///////////////////////////////////////////////////////// -->
   <script>
        function YesOrNo() {
          var txt;
          if (confirm("Le client peut possèder des comptes. Voulez vous supprimer les comptes associé ? ")) {
            txt = "Oui (suppression comptes)";
          } else {
            txt = "Non (garder comptes)";
          }
          
         
          document.getElementById("testButton").innerHTML = txt;
          
          
        }
    </script>   
	
	<!--  scripts nécessaires -->
	<script src="./assets/scripts/jquery-3.4.1.js"></script>
    <script src="./assets/scripts/popper.min.js"></script>
    <script src="./assets/scripts/bootstrap.js"></script>
    
</body>
</html>