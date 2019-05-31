
create or replace function p6() returns void as $$
declare
c1 cursor for 
(
select v.vno, v.vname, v.vbalance from vendor v
);


vend_num varchar(2);
vend_name varchar(10);
vend_balance float;
vend_char float;


begin
	open c1;
	loop
		fetch c1 into vend_num, vend_name, vend_balance;
		exit when not found;
		
		vend_char :=  .04 * vend_balance;
		update vendor set vbalance = vbalance - vend_char  where vno = vend_num;
		vend_balance := vend_balance - vend_char;
		
		raise notice 'Vendor Number %',vend_num;
		raise notice 'Vendor Name %',vend_name;
		raise notice 'New Vendor Balance %',vend_balance;
		raise notice 'Vendor Charged %',vend_char;
		raise notice ' ';
	end loop;

end;


$$ language plpgsql;
