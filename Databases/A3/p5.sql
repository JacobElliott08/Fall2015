/*Calculates the total amount of the transactions of every vendor in the transaction table, and add the total amount to the vendor's current balance.
the program them displays

vendor numbers,
vendor names 
new balance*/
create or replace function p5() returns void as $$
declare
c1 cursor for 
(
select v.vno, v.vname, v.Vbalance, t2.newAmount from vendor v

join
(
select sum(t.amount) newAmount,
t.vno as vno from transaction t
group by (t.vno)
) t2 on t2.vno = v.vno
);


vend_num varchar(2);
vend_name varchar(10);
vend_balance float;
vend_oldBalance float;

begin
	open c1;
	loop
		fetch c1 into vend_num, vend_name, vend_oldBalance vend_balance;
		exit when not found;
		
		update vendor set vbalance = vbalance + vend_balance where vno = vend_num;
		vend_balance := vend_balance + vend_oldBalance;

		raise notice 'Vendor Number %',vend_num;
		raise notice 'Vendor Name %',vend_name;
		raise notice 'New Vendor Balance %', vend_balance;
		raise notice ' ';

	end loop;

end;


$$ language plpgsql;
