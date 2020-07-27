<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%-- taglist core de la JSTL --%>    
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//FR" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Page d'accueil - gestion comptes/clients</title>

<!-- link vers bootstrap -->
<link rel="stylesheet" href="./assets/styles/bootstrap.min.css">

<!-- Lien vers font awesome 4.7.0-->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css">

<!--  lien vers le style perso -->
<link rel="stylesheet" href="./assets/styles/style.css">

<style> 

	.vignette a{
		
		text-decoration:none;
		color: black;
		text-align: center;
		opacity : 100%;
		
	}
	
	.vignette h2{
		margin-top:35%;
		font-size: 3.2em;
		z-index: 2;
		color:white;
	}
	
	
	.vignette img{
		z-index: 1;
		opacity: 35%;
	    position: absolute;
	    top: 0;
	    left: 10%;
	    max-width: 85%;
	    height: 85%;
	    transition: all 0.3s ease-out; 
	    border: 1px ridge #E6E6E6; 
	}
	
	.vignette img:hover{
		opacity: 100%;	
		transform: scale(1.15);
	}

</style>

</head>
<body>


	<%-- ==================================================================== --%>
	<%-- ======= Affichage du button deconnexion ============================ --%>
	<%-- ==================================================================== --%>
	<div class="deconnect" style="float:right;">
			<%-- vérif de la pésence de l'attribut de session 'isLogged' --%>
			<c:if test="${ not empty isLogged}">

				 <a class="fa fa-power-off fa-2x" href="authentification_servlet?destroy=true"></a>
			</c:if>
	</div>
	
	
	<%-- ==================================================================== --%>
	<%-- ======= Affichage du menu de l'appli pour conseiller =============== --%>
	<%-- ==================================================================== --%>	
	
	<h1>Bienvenue dans notre application de gestion pour les conseillers</h1>
	

	<%--
		> Contrairement à l'administrateur, le conseiller a plusieurs options => gérer des comptes ou des clients, effectuer des virements etc
				=> nécessité d'un menu pour choisir gestion client / gestion comptes
	 --%>
	 
	 
	 <div class="container-fluid" style="margin-top:100px;" >
	 	<div class="row" style="height:50rem">
	 	
	 	<!--  Form p -->		 
		<form class="col-6 vignette"  method="POST" action="gestion-client-servlet">
		
			<!--  input pour avoir le param action set to gotoaccueil correspondant à une redirection vers gestion-clients.jsp -->
			<input type="hidden" name="action" value="gotoaccueil"/>
			
            <a class="gestion-clients"  href="javascript:;" onclick="parentNode.submit();">
              
              <img src="./assets/images/bg_client.jpg" alt="CLIENTS" />
              	
               <div class="container" id="user">
	               <h2>Gestion clients</h2>
	               <div class="fa fa-user fa-5x" style="color:white"></div>
               </div>
            </a>
        </form>
  
  
  
        <form class="col-6 vignette"  method="POST" action="gestion-compte-servlet">
        	
        	<input type="hidden" name="action" value="gotoaccueil"/>
        
            <a class="gestion-comptes"  href="javascript:;" onclick="parentNode.submit();" >
            	
                <img src="./assets/images/bg_comptes.jpg" alt="COMPTES" />
                
                <div class="text-img">
                    <h2 >Gestion comptes</h2>
                    <div class="fa fa-money fa-5x" style="color:white"></div>
                </div>
            </a>
        </form>
		
		 
		</div><!-- end row -->
	 </div><!-- end container-fluid -->
	 
	 
	 <footer>
        <p>
            Copyright &copy; INTI-FORMATION 2020

        </p>
    </footer><!-- end footer-->
	 
	 
	 
	 
</body>
</html>