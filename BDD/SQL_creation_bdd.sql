DROP TABLE Adresse;
DROP TABLE Acheteur;
DROP TABLE Vendeur;
CREATE TABLE Adresse(
	numRue int,
    nomRue varchar(255),
    codePostal int,
    ville varchar(255),
    pays varchar(255)
);
CREATE TABLE Vendeur(
	id int primary key,
    nom varchar(255),
    prenom varchar(255),
    pseudo varchar(255),
	numeroTel int,
    mail varchar(255),
    motdepasse varchar(255)
);
CREATE TABLE Acheteur(
	id int primary key,
    nom varchar(255),
    prenom varchar(255),
    pseudo varchar(255),
	numeroTel int,
    mail varchar(255),
    motdepasse varchar(255)
);