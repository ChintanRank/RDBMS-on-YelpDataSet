Create or Replace procedure delete_listing(listing_par number) 
IS
Begin
	Insert into rented_apartments (Select  * From apartments Where listing = listing_par);
	Delete from apartments Where listing = listing_par;
End;
/

Execute delete_listing(167);