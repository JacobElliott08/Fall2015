create or replace function p1(name char) returns void as $$
declare
c2 cursor for 
(select 
 v.vname,
 t.t_date,
 t.amount
 from customer c

 join transaction t on t.account = c.account
 join vendor v on v.vno = t.vno

 where c.cname like name);
 
 
acc char(10);
num_trans integer;
vendor_name char(10);
date_ date;
cust_amount real;

begin
	open c2;
	loop
		fetch c2 into vendor_name, date_, cust_amount;
		exit when not found;
		raise notice 'Vendor: %', vendor_name;
		raise notice 'date: %', date_;
		raise notice 'Amount: %', cust_amount;	
		raise notice ' ';
	end loop;
	close c2;
end

$$ language plpgsql;
