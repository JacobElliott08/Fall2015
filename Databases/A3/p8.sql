create or replace function p8(e_tno char, e_vno char, e_account char, e_amount float) returns void as $$

declare
		c1 cursor for 
		(
			select * from transaction t
			where t.tno = e_tno
		);
		
		c2 cursor for
		(
			select * from customer c
			where c.account = e_account
		);
		
		c3 cursor for 
		(
			select * from vendor v
			where v.vno = e_vno
		);

		
		curtime date;
		
		c_account varchar(2);
		c_cname varchar(10);
		c_province varchar(10);
		c_cbalance float;
		c_crlimit float;
		
		t_tno varchar(2);
		t_vno varchar(2);
		t_account varchar(2);
		t_t_date date;
		t_amount float;
		
		v_vno varchar(2);
		v_vname varchar(10);
		v_city varchar(10);
		v_vbalance float;
		
		
		

begin
	curtime := CURRENT_DATE;
	insert into transaction values (e_tno, e_vno, e_account, curtime, e_amount);
	update vendor set vbalance = vbalance + e_amount;
	update customer set cbalance = cbalance + e_amount;
	
	open c1;
	loop
		fetch c1 into t_tno, t_vno, t_account,t_t_date,t_amount;
		exit when not found;
		
		raise notice 'Transaction Number %',t_tno;
		raise notice 'Transaction Vendor Number %',t_vno;
		raise notice 'Transaction Account %',t_account;
		raise notice 'Transaction Date %',t_t_date;
		raise notice 'Transaction Amount%',t_amount;
		raise notice ' '
	end loop;
	
	
	open c2;
	loop
		fetch c2 into c_account, c_cname, c_province, c_cbalance, c_crlimit;
		exit when not found;
		
		raise notice 'Customer Account %',c_account;
		raise notice 'Customer Name %',c_cname;
		raise notice 'Customer Province %',c_province;
		raise notice 'Customer Balance %',c_cbalance;
		raise notice ' '
	end loop;
	

	open c3;
	loop
		fetch c3 into v_vno, v_vname, v_city, v_vbalance;
		exit when not found;
		
		raise notice 'Vendor Number %',v_vno;
		raise notice 'Vendor Name %',v_vname;
		raise notice 'Vendor City %',v_city;
		raise notice 'Vendor Balance %',v_vbalance;
		raise notice ' '
		
	end loop;

end

$$ language plpgsql;
