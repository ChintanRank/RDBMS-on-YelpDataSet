select Z.name,Z.stars,Z.review_count 
from (select X.stars,X.review_count,X.name,A.listing,A.address,ST_Distance_Sphere( point(A.longitude,A.latitude), point(X.longitude,X.latitude)) as Dis 
	from yelp_db.apartments A,(select * 
		from yelp_db.business B,yelp_db.category C 
		where B.city='Las Vegas' and state='NV' and B.id=C.business_id and C.category='Restaurants' and B.review_count>=10)X  
	where  A.listing=25  )Z  
where Z.Dis<=200 ;