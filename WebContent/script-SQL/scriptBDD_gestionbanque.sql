
drop database db_gestion_banque;
-- Creation de la bdd
create database db_gestion_banque;

use  db_gestion_banque;

-- Table 'conseillers'

create table administrateur( idAdmin integer auto_increment,
							 nom varchar(50),
                             prenom varchar(50),
                             mail varchar(50),
                             telephone varchar(50),
                             identifiant varchar(60),
                             mot_de_passe varchar(100),
	constraint pk_admin primary key (idAdmin)
);



create table conseillers( idConseiller integer auto_increment,
						  nom varchar(60),
                          prenom varchar(60),
                          mail varchar(60),
                          telephone varchar(10),
                          identifiant varchar(60),
                          mot_de_passe varchar(60),
                          id_admin integer,
	constraint pk_conseiller primary key (idConseiller),
    constraint fk_admin foreign key (id_admin) references administrateur(idAdmin)
);



-- Table 'clients'
create table clients( idClient integer auto_increment,
					  nom varchar(60),
                      prenom varchar(60),
                      adresse varchar(100),
                      codePostal varchar(5),
                      ville varchar(60),
                      telephone varchar(10),
                      id_conseiller integer,
	constraint pk_client primary key (idClient),
    constraint fk_conseiller foreign key (id_conseiller) references conseillers(idConseiller)
    
);



-- Table 'comptes'

create table comptes_courants( num_CC integer,
							  solde decimal(20,2),
                              decouvert_autorise decimal(5,2) default 100.00 ,
                              id_proprio integer,
	constraint pk_cc primary key (num_CC,decouvert_autorise),
	constraint fk_id_proprio foreign key (id_proprio) references clients(idClient) 
);




create table comptes_epargnes( num_CE integer,
							   solde decimal(20,2),
                               taux_interets decimal(5,4) default 0.03,
                               proprio_id integer default 0,
	constraint pk_compte_epargne primary key (num_CE,taux_interets) ,
    constraint fk_proprio_id foreign key (proprio_id) references clients(idClient)
);


-- creation d'un conseiller
insert into administrateur( nom, prenom, mail, telephone, identifiant, mot_de_passe) values  ('Gaugain','Gabriel','admin@gmail.com','0606060606','admin','admin');
insert into conseillers ( nom, prenom, mail, telephone, identifiant, mot_de_passe, id_admin) values ('Gaugain','Gabriel','admin@gmail.com','0606060606','GaugainG','password',1);
insert into conseillers ( nom, prenom, mail, telephone, identifiant, mot_de_passe, id_admin) values ('Dutronc','Jacqueline','jacquiedudu@hotmail.com','0625649653','JDT','1234',1);

insert into clients(nom, prenom, adresse, codePostal, ville , telephone) values ('test','test','rue du test','00000','testville','0000000000');



select * from clients;

select * from conseillers;
-- Requetes 
select * from comptes_courants;

select * from comptes_epargnes;

select * from comptes_courants where num_Compte=156984510;


update comptes_courants set id_proprio=15 where num_CC=31843;
update comptes_epargnes set proprio_id=15 where num_CE=6465198;



update comptes_courants set id_proprio =null where id_proprio=12;
update comptes_epargnes set proprio_id =null where proprio_id=12;
delete from clients where idClient=11;



update comptes_courants SET solde=4800 , decouvert_authorise=200, id_proprio=1
where num_Compte=1;





select idAdmin from administrateur where identifiant='admin' and mot_de_passe='admin';
select idConseiller from conseillers where identifiant='GaugainG' and mot_de_passe='123';


select * from conseillers where id_admin=1;
SELECT * FROM clients WHERE id_conseiller=1;

insert into clients(nom, prenom, adresse, codePostal, ville , telephone, id_conseiller) values ('test','test','rue du test','00000','testville','0000000000',1);
