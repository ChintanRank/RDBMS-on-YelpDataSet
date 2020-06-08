create or replace function geo_distance(lo1 binary_double, la1 binary_double, lo2 binary_double, la2 binary_double)
return binary_double 
IS  
distance binary_double;
BEGIN  
select sdo_geom.sdo_distance (
         sdo_geometry (2001, 4326, null, sdo_elem_info_array(1, 1, 1),
             sdo_ordinate_array(lo1,la1)),
	  	 sdo_geometry (
             2001, 4326, null, sdo_elem_info_array(1, 1, 1),
             sdo_ordinate_array(lo2,la2)), 1, 'unit=M') into distance from dual;	   
return distance;
END;  
/