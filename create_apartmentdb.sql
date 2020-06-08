create table apartments as (select * from yelp_db.apartments);


create table rented_apartments as (SELECT listing as id,neighborhood,address,city,state,postal_code,latitude,longitude FROM apartments WHERE 1=0);

Alter table apartments ADD Constraint pk_listing Primary key(listing);

Alter table rented_apartments Add Constraint pk_id primary key(id);

