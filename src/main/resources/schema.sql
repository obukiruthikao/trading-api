create table TradeHistory(
	Id int auto_increment primary key,
    Timestamp varchar(30) not null,
    StockTicker varchar(10) not null,
    BuyorSell varchar(5) not null,
    Price double not null,
    Volume int not null,
    StatusCode int not null,
    CustomerId int not null
);


create table Portfolio(
	Id int auto_increment primary key,
    StockTicker varchar(10) not null,
    Volume int not null
);

insert into TradeHistory(Timestamp, StockTicker, BuyorSell, Price, Volume, StatusCode, CustomerID) values ("26-08-2021", "APPL", "Buy", 148.14, 5, 0, 1);
;
insert into Portfolio(StockTicker,Volume) values ("APPL",10);
insert into Portfolio(StockTicker,Volume) values ("CITI",15);