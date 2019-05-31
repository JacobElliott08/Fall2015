DROP TABLE IF EXISTS vendor cascade;
CREATE TABLE VENDOR (
	Vno varchar(2) primary key, 
	Vname varchar(30), 
	City varchar(30), 
	Vbalance float);

DROP TABLE IF EXISTS customer cascade;
CREATE TABLE CUSTOMER (
	Account varchar(2) primary key, 
	Cname varchar(30), 
	Province varchar(4), 
	Cbalance float, 
	Crlimit int);


DROP TABLE IF EXISTS transaction cascade;
CREATE TABLE TRANSACTION (
	Tno varchar(2) primary key, 
	Vno varchar(2) references VENDOR(Vno), 
	Account varchar(2) references CUSTOMER(Account), 
	T_Date date, 
	Amount float);


INSERT INTO VENDOR VALUES ('V1','Sears','Toronto',200.00);
INSERT INTO VENDOR VALUES ('V2','WalMart','Waterloo',671.05);
INSERT INTO VENDOR VALUES ('V3','Esso','Windsor',0.00);
INSERT INTO VENDOR VALUES ('V4','Esso','Waterloo',225.00);


INSERT INTO CUSTOMER VALUES ('A1','Smith','ONT',2515.00,2000);
INSERT INTO CUSTOMER VALUES ('A2','Jones','BC',2014.00,2500);
INSERT INTO CUSTOMER VALUES ('A3','Doc','ONT',150.00,1000);

INSERT INTO TRANSACTION VALUES ('T1','V2','A1','2015-07-15',1325.00);
INSERT INTO TRANSACTION VALUES ('T2','V2','A3','2014-12-16',1900.00);
INSERT INTO TRANSACTION VALUES ('T3','V3','A1','2015-09-01',2500.00);
INSERT INTO TRANSACTION VALUES ('T4','V4','A2','2015-03-20',1613.00);
INSERT INTO TRANSACTION VALUES ('T5','V4','A3','2015-07-31',3312.00);





