select max(cool) from yelp_db.review;
select max(cool) from yelp_db.review;
select count(*) from yelp_db.review where text Like '%dirty%';
select count(*) from yelp_db.review where text Like '%dirty%';
select count(*) from yelp_db.review where text like '%clean%';
select count(*) from yelp_db.review where text like '%clean%'; 
select count(*) 
from yelp_db.business,yelp_db.review  
where yelp_db.business.state='NV' and yelp_db.review.business_id=yelp_db.business.id;
select count(*) 
from yelp_db.business,yelp_db.review  
where yelp_db.business.state='NV' and yelp_db.review.business_id=yelp_db.business.id;
select count(*) 
from yelp_db.business,yelp_db.review,yelp_db.yuser  
where yelp_db.business.state='NV' and yelp_db.yuser.cool>10 and yelp_db.review.business_id=yelp_db.business.id and yelp_db.yuser.id=yelp_db.review.user_id;
select count(*) 
from yelp_db.business,yelp_db.review,yelp_db.yuser  
where yelp_db.business.state='NV' and yelp_db.yuser.cool>10 and yelp_db.review.business_id=yelp_db.business.id and yelp_db.yuser.id=yelp_db.review.user_id;






