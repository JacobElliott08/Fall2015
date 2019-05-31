create or replace function p2(name char) returns void as $$
declare
c2 cursor for 
(select 
 c.account,
 c.cname,
 c.province

 from customer c

 join transaction t on t.account = c.account
 join vendor v on v.vno = t.vno

 where v.vname like name);

acc char(10);
num_trans integer;
c_account char(2);
c_name char(10);
c_prov char(10);

begin
open c2; loop
fetch c2 into c_account, c_name, c_prov;
exit when not found;
raise notice 'Customer Account : %', c_account;
raise notice 'Customer Name :  %', c_name;
raise notice 'Prov: %', c_prov;
raise notice ' ';
end loop;
close c2;
end

$$ language plpgsql;
