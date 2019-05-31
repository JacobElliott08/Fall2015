/*Calculates the total amount of the transactions of every vendor in the transaction table, and add the total amount to the vendor's current balance.
the program them displays

vendor numbers,
vendor names 
new balance*/
create or replace function p7() returns void as $$
declare
c1 cursor for 
(
	select * from customer
);


c_account varchar(2);
c_cname varchar(10);
c_province varchar(10);
c_cbalance float;
c_crlimit float;
c_charge float;

begin
	open c1;
	loop
		fetch c1 into c_account, c_cname, c_province, c_cbalance, c_crlimit;
		exit when not found;
		
		c_charge := 0;
		
		if(c_cbalance > c_crlimit)then
		
		c_charge :=  .10 * c_cbalance;
		
		end if;
		
		c_cbalance := c_cbalance + c_charge;
		
		update customer set cbalance = cbalance + c_charge  where account = c_account;
		
		raise notice 'Customer Account %',c_account;
		raise notice 'Customer Name %',c_cname;
		raise notice 'Customer Province %',c_province;
		raise notice 'Customer Balance %',c_cbalance;
		raise notice 'Customer Limit %',c_crlimit;
		raise notice ' ';
	end loop;

end;


$$ language plpgsql;
