create or replace function p3(account_ char, cname_ char, province_ char, crlimit_ int) returns void as $$
declare

c1 cursor for 
(
	select * from customer c where c.account = account_
);

c_account varchar(2);
c_cname varchar(10);
c_province varchar(10);
c_cbalance float;
c_crlimit float;

begin

insert into customer values (account_, cname_, province_, 0, crlimit_); 

loop
		fetch c1 into c_account, c_cname, c_province, c_cbalance, c_crlimit;
		exit when not found;
		
		raise notice 'Customer Account %',c_account;
		raise notice 'Customer Name %',c_cname;
		raise notice 'Customer Province %',c_province;
		raise notice 'Customer Balance %',c_cbalance;
		raise notice 'Customer Limit %',c_crlimit;
		raise notice ' ';
	end loop;

end

$$ language plpgsql;
