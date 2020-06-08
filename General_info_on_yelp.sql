select neighborhood, count(neighborhood), avg(stars) 
from yelp_db.business B 
where  B.city='Las Vegas' and B.name like '%Hookah%' and B.neighborhood>='A' 
group by neighborhood 
having count(neighborhood)>=5 ;



select B.name
from yelp_db.yuser Y,yelp_db.review R,yelp_db.business B 
where R.user_id=Y.id and B.id=R.business_id and Y.name like 'Macaroni' and B.review_count<=3;



select B.name,Y.name
     from yelp_db.business B,yelp_db.review R,yelp_db.yuser Y 
     where B.neighborhood='The Strip' and B.city='Las Vegas' and B.name like 'Z%' and R.user_id=Y.id and B.id=R.business_id  and R.stars In (select max(stars) from yelp_db.review); 



select extract(year from Y.Yelping_since) As since, count(yelping_since) 
from yelp_db.yuser Y 
group by extract(year from Y.Yelping_since);



select Stddev(latitude) 
from yelp_db.business B 
where B.city='Las vegas';



select Stddev(latitude) 
from yelp_db.business B 
where B.city='Las vegas';