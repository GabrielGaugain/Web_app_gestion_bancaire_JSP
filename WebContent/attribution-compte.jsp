<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%-- taglist core de la JSTL --%>    
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Selection du propriétaire</title>

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
    
	.selected{
			
		border: 5px ridge white;
		background: black;
		transform: scale(1.15);
			
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
	<h1>Attribution d'un compte</h1>

	<%-- ==================================================================== --%>
	<%-- ======= Affichage du button deconnexion (avec Font Awesome) ======== --%>
	<%-- ==================================================================== --%>
	<div class="deconnect" style="float:right;">
			<%-- vérif de la pésence de l'attribut de session 'isLogged' --%>
			<c:if test="${ not empty isLogged}">
				 <a class="fa fa-power-off fa-2x" href="authentification_servlet?destroy=true"></a>
			</c:if>
	</div>
	<div class="deconnect"><a class="fa fa-arrow-circle-left fa-2x" href="gestion-compte.jsp"></a></div>

	<form id="formulaire" method="POST" action="gestion-compte-servlet">
		<input type="hidden" name="action" value="attribution"/>
		
		<input type="hidden"  name="p-idcompte" value="${id_compte}"/> 
		<input type="hidden"  name="p-typecompte" value="${type_compte}"/> 
	
	
		<%-- ==================================================================== --%>
		<%-- ======= Affichage de la liste des clients ========================== --%>
		<%-- ==================================================================== --%>
		<%--
			> recup de la liste des clients sauvegardées dans la requete lors 
			  de la délégation ( au moment de la connection de l'utilisateur => ref AuthentificationServlet - étape 3.1.2.3.)
			  
			> l'attribut de requete pour la liste : request.setAttribute("liste_clients",listeClientsBdd);
		 --%>
		 
		
	    <div class="container-fluid" style="padding: 50px 20px">
	    	
	    
	    	<br/>
	    	
		 	<c:forEach items="${liste_clients}" var="clt" varStatus="nb_vignette">
		 	
		 	<c:if test="${nb_vignette.index % 3 == 0 }"><div class="row" style="margin:0 25px"> </c:if> 
		 	<div class="col-md-12 col-lg-4">
		        <div class="vignette" id="${clt.idClient }">
				
		            <br><br>
		
		            <div class="fa fa-user fa-3x" style="margin: 5px 0 10px 0;"></div>
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
    
   	<br/>
   		<input type="submit" value="attribuer" style="margin-left:47%"/>
	<br/>
	
	</form>

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

    <script type="text/javascript">
		$(document).ready(function(){

			$("div.vignette").click(function(){



				
                if( $(this).hasClass("selected") ){

    				// au click on deselectionne toutes les div select et les input ajoutées associées
                    var vignettes = document.getElementsByClassName('selected');
                    while(vignettes.length){

                        while (vignettes[0].getElementsByTagName("input").length){
                            vignettes[0].getElementsByTagName("input")[0].remove(); 
                        }

                        
                        vignettes[0].classList.remove('selected');
                    }                    
                    
                    $(this).removeClass("selected");
                    
                }
	            else{
		            
					// au click on deselectionne toutes les div select et les input ajoutées associées
	                var vignettes = document.getElementsByClassName('selected');
	                while(vignettes.length){

	                    while (vignettes[0].getElementsByTagName("input").length){
	                        vignettes[0].getElementsByTagName("input")[0].remove(); 
	                    }

	                    
	                    vignettes[0].classList.remove('selected');
	                }
                    var idClient = document.createElement('input');
                    idClient.type = "hidden";
                    idClient.value= this.id;
                    idClient.name = "p-id-client";

                    this.appendChild(idClient);
                   
                        
	                $(this).addClass("selected");

	                
	            }
    
			
			});

		});
	</script>

</body>
</html>