/*Does not print no transaction for customers without transactions*/
create or replace function p4() returns void as $$
declare
c2 cursor for 
(select 
c.cname,
t.t_date,
t.amount,
v.vname

from customer c

join transaction t on t.account = c.account
join vendor v on v.vno = t.vno

where
t.t_date in(
	select MAX(t2.t_date) from transaction t2 	
	where t2.account = t.account
)
);

num_trans integer;
c_name char(10);
tran_date date;
tran_amount int;
v_name char(10);

begin
open c2; loop
fetch c2 into c_name, tran_date, tran_amount, v_name;
exit when not found;
raise notice 'Customer Name : %', c_name;
raise notice 'Transaction Date : %', tran_date;
raise notice 'Transaction Amount: %', tran_amount;
raise notice 'Vendor Name: %', v_name;
raise notice ' ';
end loop;
close c2;
end

$$ language plpgsql;
