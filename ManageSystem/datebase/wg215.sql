


create table wg215_user
(
	--id int primary key NOT NULL,
	username varchar(50) NOT NULL,
	passwd varchar(50) NOT NULL
)
select * from wg214_user
insert into wg214_user values('123', '123')
drop table wg214_user

 --物料表
 create table wg215_matter
 (
	matterid varchar(50) primary key NOT NULL,  --物料代码
	mattername varchar(50),  --物料名称
	spec varchar(50),		--规格型号
	measurement varchar(50),	--计量单位
	amount int,		--库存数量
	other varchar(50)	--备注
 )
 drop table wg215_simple
 select * from wg215_simple
 
 select * from wg215_matter
 
 drop table wg215_matter
 insert into wg215_matter values(1,'大米','包','斤',100,'无');
  insert into wg215_matter values(2,'大米','1','斤',100,'无');
	
	
	
 --人员表
 CREATE TABLE wg215_person
(
	no int primary key NOT NULL,
	name varchar(50) ,
	sex varchar(50) check(sex in ('男','女')) ,
	birthdate date ,
	idcard varchar(50) ,
	place varchar(50) ,
	address varchar(50) ,
	telphone varchar(50) 
)  


drop table 	wg215_person
	select * from wg215_person
insert into  wg215_person values (2044,'刘备','男','1994-06-01',12301,'河南','beijing',15555 ); 

--简单物料进出仓记录表
create table wg215_simple
(
	orderID varchar(50) primary key NOT NULL,  --订单号
	inoutDate date,		--进仓日期
	no varchar(50),			--人员代码
	remarks varchar(50),  --备注
	matterid varchar(50),  --物料代码
	inoutCount int,		--进仓数量
	state varchar(50)
)

--多物料表 
CREATE TABLE wg215_multiSame
(
	orderID varchar(50) primary key NOT NULL,
	inoutDate date,		--进仓日期
	no varchar(50),			--人员代码
)
select * from wg215_multiSame
select * from wg215_multi


CREATE TABLE wg215_multi
(
	orderID varchar(50),
	remarks VARCHAR(50),
	matterid VARCHAR(50) NOT NULL,
	inoutCount int,
	state VARCHAR(50)
)


drop table wg215_simple;

select * from wg215_simple;
select   CONVERT(varchar(12) , inoutDate, 23 ) 
Select CONVERT(varchar(100), inoutDate, 23) as inoutDate from wg215_simple

--多物料进出仓存储过程
if (EXISTS(select * from sys.objects where name='wg215_multiPro'))
drop proc wg215_multiPro
go
create proc wg215_multiPro 
(
	@orderID VARCHAR(50),
	@inoutDate date,
	@no VARCHAR(50),
	@remarks VARCHAR(50),
	@matterid VARCHAR(50),
	@inoutCount int,
	@state VARCHAR(50))
as
begin
begin TRANSACTION
if(EXISTS (select * from wg215_matter where matterid=@matterid)) begin
if(@state = '进仓') begin
update wg215_matter set amount += @inoutCount where matterid=@matterid
insert into wg215_multi 
values(@orderID, @remarks, @matterid, @inoutCount, @state)
if(@orderID not in(select orderID from wg215_multiSame)) begin
insert into wg215_multiSame values(@orderID, @inoutDate, @no)
end
end
if(@state = '出仓') begin
if(@inoutCount > (select amount from wg215_matter where matterid=@matterid)) begin
RAISERROR ('库存不足',16,1)
end
if(@inoutCount <= (select amount from wg215_matter where matterid=@matterid)) begin
update wg215_matter set amount -= @inoutCount where matterid=@matterid
insert into wg215_multi
values(@orderID, @remarks, @matterid, @inoutCount, @state)
if(@orderID not in(select orderID from wg215_multiSame)) begin
insert into wg215_multiSame values(@orderID, @inoutDate, @no)
end
end
end
end
if(not exists(select * from wg215_matter where matterid=@matterid)) begin
RAISERROR ('不存在该物料',16,1)
end
commit
end
go

exec wg215_multiPro '213', '2011-11-11', '14', 'weeq', '1', 100, '进仓'
select * from wg215_multi
select * from wg215_multiSame
TRUNCATE table wg215_multi
SELECT * from wg215_matter














--简单物料进出仓存储过程
if(exists(select * from sys.objects where name='wg215_simplePro'))
drop proc wg215_simplePro
go
create proc wg215_simplePro 
(
	@orderID varchar(50),
	@inoutDate date,
	@no varchar(50),
	@remarks varchar(50),
	@matterid varchar(50),
	@inoutCount int,
	@state varchar(50))
as
begin
begin transaction
if(not exists (select * from wg215_matter where matterid=@matterid)) begin 
raiserror('不存在该物料',16,1)
end
else begin
if(@state='进仓') begin   --进仓
insert into wg215_simple
values(@orderID,@inoutDate,@no,@remarks,@matterid,@inoutCount,@state)
update wg215_matter set amount+=@inoutCount where matterid=@matterid
end

if(@state='出仓')  --出仓
if((select amount from wg215_matter where
matterid=@matterid)>=@inoutCount) begin
update wg215_matter set amount-=@inoutCount where matterid=@matterid
insert into wg215_simple
values(@orderID,@inoutDate,@no,@remarks,@matterid,@inoutCount,@state)
end
--else begin
--raiserror('库存不足',16,1)
--end

end
commit
end
go


drop proc wg215_simplePro
exec wg215_simplePro '2018041710098','2018-2-5','1','ok','2',13,'进仓';

truncate table wg215_simple;
truncate table wg215_person;

select * from wg215_simple
select * from wg215_matter
select * from wg215_person
select amount from wg215_matter where matterid=1

SELECT
	* 
FROM
	wg215_person
select * from wg215_simple where 1=1 and inoutDate between '2018-02-01' and '2018-04-20'


