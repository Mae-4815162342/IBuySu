/* ALTER TABLE Adresse
ADD id, ADD PK_id primary key (id);
ALTER TABLE Acheteur
ADD adresse,ADD FK_adresse FOREIGN KEY (adresse) references Adresse(id) */

select * from Categorie
