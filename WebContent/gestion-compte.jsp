<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%-- taglist core de la JSTL --%>    
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Gestion des comptes</title>

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
	    	margin: 10px 10px 30px 30px;
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

		#line{
			margin-top : 10px;
			margin-bottom : 30px;
			margin-left : 5%;
			height : 2px;
			width :25%;
			
			background:#E6E6E6; 
		}
    </style>
    
</head>
<body>

	<h1>Gestion des comptes (epargnes/courants)</h1>

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


	 <!-- ========================================================================================================== -->	 
	 <!-- ================================= COmptes Courants ======================================================= -->
	 <!-- ========================================================================================================== -->
	   
	   
	 <div class="container-fluid" style="padding: 50px 20px; " >
	 
	 	<h3 style="margin-left:5%;text-transform: uppercase">Liste des comptes courants</h3>
	 	
	 	<div id="line"></div> 
	 	<a class="fa fa-plus" href="ajouter-compte-courant.jsp" style="float:right;color:#E6E6E6">Ajouter compte courant</a>
	 	<br/>
	 	
	 	<!--  Boucle for each sur les comptes courants =========================================== -->
	 	<c:forEach items="${liste_CC_Bdd}" var="clt" varStatus="nb_vignette" >
	 	
	 	
	 	<c:if test="${nb_vignette.index % 3 == 0 }"><div class="row" style="margin:0 25px"> </c:if> 
	 	

	 	
	 	
	 	<div class="col-md-12 col-lg-4">
		 	<div class="class vignette">
	
	
	            <div class="dropdown" style="float: right;margin-right: 10px;">
	               
	                    <button class="btn btn-secondary dropdown-toggle" type="button" id="deroulantb" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><i class="fa fa-toggle-down fa-2x"></i></button>
	                    <div class="dropdown-menu" aria-labelledby="deroulantb">
	                        <a class="dropdown-item" href="gestion-compte-servlet?action=modifCC&id-compte=${clt.numeroCompte }" >Modifier</a>
	                        <a class="dropdown-item" href="gestion-compte-servlet?action=suppCC&id-compte=${clt.numeroCompte }">Supprimer</a>
	                        <a class="dropdown-item" href="gestion-compte-servlet?action=attrib&typeCompte=courant&id-compte=${clt.numeroCompte }">Attribuer le compte</a>
	                        <a class="dropdown-item" href="gestion-compte-servlet?action=depotCC&id-compte=${clt.numeroCompte }">Effectuer un dépot</a>
	                        <a class="dropdown-item" href="gestion-compte-servlet?action=retirerCC&id-compte=${clt.numeroCompte }">Retirer argent</a>
							<a class="dropdown-item" href="gestion-compte-servlet?action=virer&id-compte=${clt.numeroCompte }&type-compte=courant">Virement</a>
	                    </div>
	             </div>   
            
	          
	            <br><br>
	
	            <div class="class fa fa-money fa-3x" style="margin: 5px 10px ;">
		            <h5 style="margin-top:15px"> <b > Numéro de compte :  </b>    		${clt.numeroCompte} </h5>
		            <h5> <b > Solde du compte :  </b>          	${clt.soldeCompte } </h5>
		            <h5> <b > Découvert autorisé : </b>        	${clt.decouvertAutorise } </h5>
		            <h5> <b > Reference propriétaire :  </b>    ${clt.idTitulaire } </h5>
	
	        	</div>
	        	
	        	
	        </div>
        </div>
        
		<c:if test="${(nb_vignette.index+1)%3 == 0 }"></div> </c:if> 
        <c:if test="${ ( (nb_vignette.index+1)%3 != 0)&&( nb_vignette.count==liste_CC_Bdd.size() )  }"> </div> </c:if> 
       
        
        </c:forEach> 
        

	
	<br/>
	
	


	<!-- =================== Petite séparation entre les deux listes de compte ===================================== -->
	<!-- 								<div class="separation"></div> 												-->	
	<!-- ========================================================================================================== -->
	<br/><br/><br/>
	
	
	 <!-- ========================================================================================================== -->
	 <!-- ================================= COmptes Epargnes ======================================================= -->	
	 <!-- ========================================================================================================== -->

	 	
	 <div id="container-fluid" style="padding: 20px 20px ">
	 
		<h3 style="margin-left:5%;margin-top:30px;text-transform: uppercase">Liste des comptes épargnes</h3>
		<div id="line"></div>
		<a class="fa fa-plus" href="ajouter-compte-epargne.jsp" style="float:right;color:#E6E6E6">Ajouter compte epargne</a> 
		<br/>	
	 	
	 	<!--  Boucle for each sur les comptes courants =========================================== -->	 	
		 <c:forEach items="${liste_CE_Bdd}" var="clt" varStatus="nb_vignettes">
		 
		 
		 <c:if test="${nb_vignettes.index % 3 == 0 }"><div class="row"> </c:if> 
	 	
	 	 <div class="col-md-12 col-lg-4">
		 	<div class="class vignette">
	
	
	            <div class="dropdown" style="float: right;margin-right: 10px;">
	               
	                    <button class="btn btn-secondary dropdown-toggle" type="button" id="deroulantb" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><i class="fa fa-toggle-down fa-2x"></i></button>
	                    <div class="dropdown-menu" aria-labelledby="deroulantb">
	                        <a class="dropdown-item" href="gestion-compte-servlet?action=modifCE&id-compte=${clt.numeroCompte }" >Modifier</a>
	                        <a id="supprimer" class="dropdown-item" href="gestion-compte-servlet?action=suppCE&id-compte=${clt.numeroCompte }">Supprimer</a>
	                        <a class="dropdown-item" href="gestion-compte-servlet?action=attrib&typeCompte=epargne&id-compte=${clt.numeroCompte }">Attribuer le compte</a>
	                        <a class="dropdown-item" href="gestion-compte-servlet?action=depotCE&id-compte=${clt.numeroCompte }">Effectuer un dépot</a>
	                        <a class="dropdown-item" href="gestion-compte-servlet?action=retirerCE&id-compte=${clt.numeroCompte }">Retirer argent</a>
							<a class="dropdown-item" href="gestion-compte-servlet?action=virer&id-compte=${clt.numeroCompte}&type-compte=epargne">Virement</a>
	                    </div>
	                
	            </div>
	            <br><br>
	
	            <div class="class fa fa-money fa-3x" style="margin: 5px  10px ">
	            	
		            <h5 style="margin-top:10px"> <b > Numéro de compte :  </b>    		${clt.numeroCompte} </h5>
		            <h5> <b > Solde du compte :  </b>         	${clt.soldeCompte } </h5>
		            <h5> <b > Taux d'intéret : </b>        		${clt.tauxInterets } </h5>
		            <h5> <b > Reference propriétaire :  </b>    ${clt.idTitulaire } </h5>
	
	        	</div>
	        </div>
	    </div>
	        
        
	    <c:if test="${(nb_vignettes.index+1)%3 == 0 }"></div> </c:if> 
	    <c:if test="${ ( (nb_vignette.index+1)%3 != 0)&&( nb_vignette.count==liste_CE_Bdd.size() )  }"> </div> </c:if>  
	    
	    </c:forEach> 		
	


	</div>
	
	 <footer>
        <p>
            Copyright &copy; INTI-FORMATION 2020

        </p>
    </footer><!-- end footer-->
	 


	<!--  ////////////////////////////////////////// END HTML ///////////////////////////////////////////////////////// -->


	<!--  scripts nécessaires -->
	<script src="./assets/scripts/jquery-3.4.1.js"></script>
    <script src="./assets/scripts/popper.min.js"></script>
    <script src="./assets/scripts/bootstrap.js"></script>
</body>

</html>