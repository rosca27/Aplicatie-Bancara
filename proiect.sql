drop schema proiect_final;
create schema proiect_final;
use proiect_final;

create table if not exists utilizatori(
username varchar(50),
parola varchar(50),
id int auto_increment unique primary key,
cnp varchar(25),
nume varchar(35),
prenume varchar(35),
adresa varchar(50),
nr_telefon varchar(12),
e_mail varchar(30),
iban varchar(30),
nr_contract varchar(10),
nr_conturi int default 0,
nr_conturi_facturi int default 0);

create table if not exists administrator(
username varchar(50),
parola varchar(50),
id int auto_increment unique primary key,
id_utilizator int,
foreign key(id_utilizator) references utilizatori(id));

create table if not exists departament(
id int auto_increment primary key,
nume varchar(50));

create table if not exists sucursala(
id int auto_increment primary key,
nume varchar(50),
adresa varchar(50));

create table if not exists angajat(
id int auto_increment unique primary key,
username varchar(50),
parola varchar(50),
angajat_id int,
norma int,
salariul int,
sucursala_id int,
departament_id int,
iban_cont varchar(40),
suma_cont int,
foreign key(angajat_id) references utilizatori(id),
foreign key(departament_id) references departament(id),
foreign key(sucursala_id) references sucursala(id));


create table if not exists clienti(
id int auto_increment unique primary key,
username varchar(50),
parola varchar(50),
utilizator_id int,
adresa varchar(50),
data_nasterii date,
sursa_venit varchar(50),
tranzactii_online enum('DA','NU'),
foreign key(utilizator_id) references utilizatori(id));

create table if not exists cont(
id int auto_increment unique primary key,
suma int, 
client_id int,
t_cont enum('CURENT','ECONOMII','FACTURI'),
iban char(30),
card_facut char default '0',
depozit_facut char default '0');

create table if not exists intermediar(
id int auto_increment primary key,
id_client int,
id_cont int,
foreign key(id_client) references clienti(id),
foreign key(id_cont) references cont(id));

create table if not exists card(
id int auto_increment primary key,
nr_card varchar(30),
cont_id int,
cvv int,
adresa varchar(50),
nume varchar(30),
prenume varchar(30),
data_exp date,
foreign key(cont_id) references cont(id));

create table if not exists solicitare_card(
nr_card2 varchar(30),
cont_id2 int,
cvv2 int,
adresa2 varchar(50),
nume2 varchar(30),
prenume2 varchar(30),
data_exp2 date,
status_card enum ("NEAPROBAT","APROBAT") default "NEAPROBAT");

create table if not exists dobanzi(
dobanda int,
comision int,
luna_luna int);

create table if not exists facturi(
id int auto_increment unique primary key,
id_cont int,
furnizor varchar(35),
nr_factura varchar(35),
suma int,
foreign key(id_cont) references cont(id));

create table if not exists depozit(
id int unique auto_increment primary key,
id_cont int,
suma int,
data_depunerii date,
perioada int,
foreign key (id_cont) references cont(id));

create table if not exists plata_salarii(
id int unique primary key,
id_admin int,
id_cont int, 
foreign key (id_cont) references cont(id));

create table if not exists lista_iban(
id int unique primary key,
salarii_id int,
iban varchar(50),
foreign key(salarii_id) references plata_salarii(id));

create table if not exists transfer(
id int unique  auto_increment primary key,
expeditor varchar(40),
destinatar varchar(40),
suma int,
aprobat enum("NEAPROBAT","APROBAT","ERROR") default "NEAPROBAT",
descriere enum("DEPUNERE","TRANSFER"));

create table if not exists contacte(
id int auto_increment unique primary key, 
nume varchar(35),
prenume varchar(35),
iban varchar(35),
owner_id int,
utilizator_id int,
foreign key(utilizator_id) references utilizatori(id),
foreign key(owner_id) references utilizatori(id));

create table if not exists notificari(
mesaj varchar(200),
id_utilizator int);

create table if not exists cont_angajat(
suma int,
iban_angajat varchar(40),
angajat_id int);

drop role 'admin';
drop role 'angajat';
drop role 'client';
create role 'admin', 'angajat', 'client';

grant all on proiect_final.* to 'admin';
grant select on proiect_final.dobanzi to 'angajat';

delimiter //

create procedure create_account_client(in usernamee varchar(50), in parolaa varchar(50), in cnpp varchar(25), in numee varchar(25), 
in prenumee varchar(25), in adress varchar(50), in nr_telefonn varchar(12), in e_maill varchar(30), in ibann varchar(30), in nr_contractt varchar(10),
 in data_nasteriii date, in sursa_venitt varchar(50), in tranzactii_onlinee enum('DA', 'NU'))
begin 
	declare key_user INT;
	insert into utilizatori(username,parola,cnp, nume, prenume, adresa, nr_telefon, e_mail, iban, nr_contract)
    values(usernamee,parolaa,cnpp, numee, prenumee, adress, nr_telefonn, e_maill, ibann, nr_contractt);
    
    set key_user := (select id from utilizatori where cnp = cnpp);
    
    insert into clienti(username, parola, utilizator_id, adresa, data_nasterii, sursa_venit, tranzactii_online)
    values(usernamee, parolaa,key_user, adress, data_nasteriii, sursa_venitt, tranzactii_onlinee);
end; //

delimiter ;

delimiter //

create procedure create_account_angajat(in usernamee varchar(50), in parolaa varchar(50), in cnpp varchar(25), in numee varchar(25), 
in prenumee varchar(25), in adress varchar(50), in nr_telefonn varchar(12), in e_maill varchar(30), in ibann varchar(30), in nr_contractt varchar(10),
normaa int, salariuu int,in sucursalaa varchar(30),in departamentt varchar(30), in iban_contt varchar(40))
begin 
	declare key_user INT;
	insert into utilizatori(username, parola, cnp, nume, prenume, adresa, nr_telefon, e_mail, iban, nr_contract)
    values(usernamee, parolaa, cnpp, numee, prenumee, adress, nr_telefonn, e_maill, ibann, nr_contractt);
    
    set key_user := (select id from utilizatori where cnp = cnpp);
    
    
    insert into angajat(username,parola,angajat_id, norma, salariul,sucursala_id,departament_id,iban_cont,suma_cont)
    values(usernamee, parolaa,key_user, normaa, salariuu,(select id from sucursala where nume = sucursalaa), (select id from departament where nume = departamentt),iban_contt,0);
    
end; //
delimiter ;


delimiter //

create procedure create_account_admin(in usernamee varchar(50), in parolaa varchar(50), in cnpp varchar(25), in numee varchar(25), 
in prenumee varchar(25), in adress varchar(50), in nr_telefonn varchar(12), in e_maill varchar(30), in ibann varchar(30), in nr_contractt varchar(10))
begin 
	declare key_user INT;
	insert into utilizatori(username, parola, cnp, nume, prenume, adresa, nr_telefon, e_mail, iban, nr_contract)
    values(usernamee, parolaa, cnpp, numee, prenumee, adress, nr_telefonn, e_maill, ibann, nr_contractt);
    
    set key_user := (select id from utilizatori where cnp = cnpp);
    
    insert into administrator(username, parola, id_utilizator)
    values(usernamee, parolaa, key_user);
end; //

delimiter ;

delimiter //

create procedure plata_angajati(out k int)
begin
declare data_curenta date;
declare zi_curenta int;
declare max_id int;
declare i int default 1;
set data_curenta := curdate();
set zi_curenta := day(data_curenta);
if(zi_curenta = 15) then
set k = 1;
else
set k = 0;
end if;
if k = 1 then
set max_id = (select max(id) from angajat);
while(i <= max_id) do
update angajat
set suma_cont = suma_cont + salariul where id = i; 
set i = i +1 ;
end while;
end if;
end; //

delimiter ;


delimiter //

create procedure create_cont_client(in sumaa int, in tip_cont enum('CURENT', 'ECONOMII', 'FACTURI'), in ibann varchar(50), in usernamee varchar(50), in parolaa varchar(50))
begin
declare id_user int;
declare id_contt int;
declare unu int;
declare nr_facturare int;

set id_user := (select id from clienti where username = usernamee and parola = parolaa);
set unu := 1;
set nr_facturare := (select nr_conturi_facturi from utilizatori where username = usernamee and parola = parolaa);

if(tip_cont = 'ECONOMII')
then
set sumaa := floor(sumaa*1.05);
end if;

if((tip_cont = 'FACTURI' and nr_facturare = 0) or (tip_cont = 'ECONOMII' or tip_cont = 'CURENT'))
then
if(tip_cont = 'FACTURI')
then
update utilizatori
set nr_conturi_facturi = (select sum(nr_conturi_facturi + unu) ) where username = usernamee and parolaa = parola;
end if;
insert into cont(suma, t_cont, iban)
values(sumaa, tip_cont, ibann);
set id_contt := (select id from cont where iban = ibann);
insert into intermediar(id_client,id_cont)
values(id_user, id_contt);
select id_user;
select id_contt;
update utilizatori
set nr_conturi = (select sum(nr_conturi + unu) ) where username = usernamee and parolaa = parola; 
end if;
end; //

delimiter ;

delimiter //

create procedure loginc(in userr varchar(50), in parolaa varchar(50),out count1 int)
begin
set count1 := (select count(username) from clienti where username = userr and parola = parolaa);
end; //

delimiter ;

delimiter //

create procedure logina(in userr varchar(30), in parolaa varchar(30),out count1 int)
begin
set count1 := (select count(username) from angajat where username = userr and parola = parolaa);
end; //

delimiter ;

delimiter //

create procedure loginadmin(in userr varchar(30), in parolaa varchar(30),out count1 int)
begin
set count1 := (select count(username) from administrator where username = userr and parola = parolaa);
end; //

delimiter ;

call create_account_admin('sergiu123', 'sergiu123', 5010927,'Sergiu', 'Rosca', 'Reghin', '0774564625', 'roscasergiu@yahoo.com', 'ROBT2346512', '66');
call create_account_admin('gabi123', 'gabi123', 5123478,'Gabi', 'Mihali', 'Reghin', '0774564625', 'roscasergiu@yahoo.com', 'ROBT7561753', '6');
call create_account_client('gavreaioan', 'gavrea100', 50100, 'Gavrea', 'Ioan', 'Mehedinti', '0742720', 'miha@yahoo.com', 'ROBT201010', '12314', '2000-03-12', 'restante', 'DA');
call create_account_client('andrei123', 'andrei100', 54580, 'Andrei', 'Popovici', 'Mures', '075684320', 'andrpopvici@yahoo.com', 'ROBT7895632', '65412', '2001-07-23', 'programator', 'DA');
call create_account_client('ionescu123', 'ionescu123', 50142563, 'Ionescu', 'Sebastian', 'Cluj-Napoca, centru', '0753478120', 'ionescu@yahoo.com', 'ROBT756255562', '412', '2002-06-16', 'Parinti', 'DA');

delimiter //

create procedure check1()
begin
declare k int;
call loginadmin('sergiu123','sergiu123',k);
select k;
end; //

delimiter ;

delimiter //

create procedure view_cont(in userr varchar(50), in parolaa varchar(50))
begin
select c.suma, c.t_cont, c.iban from cont as c join intermediar as i on i.id_cont = c.id join clienti as cl on i.id_client = cl.id where cl.username = userr and cl.parola = parolaa;
end; //

delimiter ;

delimiter //

create procedure view_cont_info(in ibann varchar(40))
begin
select suma, t_cont, iban from cont where iban = ibann;
end; //

delimter ;

delimiter //

create procedure lichidare_cont(in userr varchar(50), in parolaa varchar(50), in ibann varchar(30))
begin
declare id_cont int;
set id_cont := (select id from cont where iban = ibann);
delete from intermediar as i where id_cont = i.id_cont;
delete from cont where iban = ibann;

update utilizatori
set nr_conturi = nr_conturi - 1 where username = userr and parola = parolaa;
end; //

delimiter ; 

delimiter // 

create procedure vizualizare_depozite(in usernamee varchar(50), in parolaa varchar(50))
begin
select c.iban from cont as c join depozit as d on c.id = d.id_cont join intermediar as i on c.id = i.id_cont join clienti as c1 on c1.id = i.id_client where c1.username = usernamee and c1.parola = parolaa;
end; //

delimiter ;


delimiter // 

create procedure lichidare_depo(in ibann varchar(30))  
begin

declare suma_cont int;
declare suma_noua int;
declare id_contt int;
declare suma_depozit int;

set id_contt := (select id from cont where iban = ibann);

set suma_depozit := (select suma from depozit where id_cont = id_contt);
set suma_cont := (select suma from cont where iban = ibann);
set suma_noua := (select sum(suma_depozit + suma_cont));
select suma_noua;
update cont
set suma = suma_noua where id = id_contt;
delete from depozit where id_cont = id_contt;
update cont
set depozit_facut = '0' where iban = ibann;
end; //

delimiter ;

delimiter //

create procedure view_depo_info(in ibann varchar(40))
begin
select d.suma, d.perioada, d.data_depunerii from depozit as d join cont as c on d.id_cont = c.id where c.iban =  ibann;
end; //

delimiter ; 

delimiter //

create procedure create_depozit_client( in sumaa int, in perioadaa int, in ibann varchar(30))
begin
declare id_contt int;
set id_contt := (select id from cont where iban = ibann);
insert into depozit(id_cont,suma,data_depunerii,perioada)
values(id_contt,sumaa,'2021-10-11',perioadaa);
update cont
set depozit_facut = '1' where iban = ibann;
end; //

delimiter ;

delimiter //

create procedure solicitare_c(in nr_cardd varchar(30), in cvvv int, in usernamee varchar(30), in parolaa varchar(30), in ibann varchar(30))
begin
declare id_cont int;
declare numee varchar(50);
declare prenumee varchar(50);
declare data_expp date;

set id_cont := (select id from cont where iban = ibann);
set numee := (select nume from utilizatori where username = usernamee and parola = parolaa);
set prenumee := (select prenume from utilizatori where username = usernamee and parola = parolaa);
set data_expp := (select date_add(curdate(),interval 5 year));

insert into solicitare_card(nr_card2,cont_id2,cvv2,nume2,prenume2,data_exp2)
values(nr_cardd, id_cont, cvvv, numee, prenumee,data_expp);
update cont 
set card_facut = '1' where id = id_cont;
end; //

delimiter ;

delimiter //

create procedure aprobare_card(in numee varchar(40), in prenumee varchar(40), in nr_cardd varchar(40)) 
begin
declare cvvv int;
declare data_expp date;
declare cont_idd int;

set cvvv := (select cvv2 from solicitare_card where nume2 = numee and prenume2 = prenumee and nr_card2 = nr_cardd);
set data_expp := (select data_exp2 from solicitare_card where nume2 = numee and prenume2 = prenumee and nr_card2 = nr_cardd);
set cont_idd := (select cont_id2 from solicitare_card where nume2 = numee and prenume2 = prenumee and nr_card2 = nr_cardd);

update solicitare_card
set status_card = "APROBAT" where nume2 = numee and prenume2 = prenumee and nr_card2 = nr_cardd;

insert into card(nr_card,cont_id,cvv,nume,prenume,data_exp)
values(nr_cardd,cont_idd, cvvv,numee,prenumee,data_expp);
end; //

delimiter ; 

delimiter // 

create procedure card_f(in ibann1 varchar(40))
begin
select card_facut from cont where iban = ibann1;
end; //

create procedure depo_f(in ibann1 varchar(40))
begin
select depozit_facut from cont where iban = ibann1;
end; //
delimiter ;

delimiter //

create procedure nr_c(in usernamee varchar(50), in parolaa varchar(50))
begin
select nr_conturi from utilizatori where username = usernamee and parola = parolaa;
end;//
delimiter ; 

delimiter //

create procedure card_info(in usernamee varchar(50), in parolaa varchar(50), in ibann varchar(30))
begin
declare id_contt int;
declare id_cardd int;
set id_contt := (select id from cont where iban = ibann);
set id_cardd := (select id from card where cont_id = id_contt);
select c1.nr_card, c1.cvv, c1.nume, c1.prenume, c1.data_exp from card as c1 where id = id_cardd;
end; //

delimiter ;

delimiter // 

create procedure transferr2(in ibann1 varchar(40), in ibann2 varchar(40), in sumaa int)
begin

declare sumat int;
declare id_contt int;
declare id_clientt int;
declare id_utt int;
declare sumav int;
declare aprobatt varchar(40);
declare numee varchar(30);
declare prenumee varchar(30);

insert into transfer(expeditor, destinatar, suma)
values (ibann1,ibann2,sumaa);

set sumat := (select suma from cont where iban = ibann1);
update transfer
set descriere = "TRANSFER" where expeditor = ibann1 and destinatar = ibann2 and suma = sumaa;

if ibann1 = ibann2 then
update transfer
set descriere = "DEPUNERE" where expeditor = ibann1 and destinatar = ibann2 and suma = sumaa;
end if;
if sumat < sumaa and ibann1 != ibann2 then
update transfer
set aprobat = "ERROR" where expeditor = ibann1 and destinatar = ibann2 and suma = sumaa;
update transfer
set descriere = "TRANSFER" where expeditor = ibann1 and destinatar = ibann2 and suma = sumaa;
end if; 

set id_contt :=( select c.id from cont as c where c.iban = ibann1);
set id_clientt := (select c.id from clienti as c join intermediar as i on c.id = i.id_client where i.id_cont = id_contt);
set sumav := (select suma from transfer where expeditor = ibann1 and destinatar = ibann2 and suma = sumaa); 
set numee := (select username from clienti where id = id_clientt);
set prenumee := (select parola from clienti where id = id_clientt);
set id_utt :=(select id from utilizatori where username = numee and parola = prenumee);
set aprobatt := (select aprobat from transfer where expeditor = ibann1 and destinatar = ibann2 and suma = sumaa);
if aprobatt = "ERROR" then
insert into notificari
values(concat(concat(concat(concat('Transferul cu suma de: ',sumaa),' de lei spre '),ibann1),' nu s-a realizat'),id_utt);
end if;

end; //

delimiter ;

delimiter //

create procedure transferr(in ibann1 varchar(40), in ibann2 varchar(40), in sumaa int)
begin
declare id1 varchar(40);
declare id2 varchar(40);
declare sub1 varchar(30);
declare sub2 varchar(30);
declare statust varchar(30);
set sub1 := substring(ibann1,1,4);
set sub2 := substring(ibann2,1,4);
set id1 := (select id from cont where iban = ibann1);
set id2 := (select id from cont where iban = ibann2);
set statust := (select descriere from transfer where expeditor = ibann1 and destinatar = ibann2 and suma = sumaa);
if statust = "TRANSFER" then
if sub1 != sub2 then
set sumaa := sumaa * 0.99;
end if;
UPDATE cont 
SET 
    suma = suma - sumaa
WHERE
    id = id1;
UPDATE cont 
SET 
    suma = suma + sumaa
WHERE
    id = id2;
else
UPDATE cont 
SET 
    suma = suma + sumaa
WHERE
    id = id2;
end if;
end; //

delimiter ;

delimiter //

create procedure aprobare_t(in expeditorr varchar(40), in destinatarr varchar(40), in sumaa int)
begin
declare sumav int;
declare aprobatt2 varchar(30);
declare aprobatt varchar(30);
declare id_contt int;
declare id_clientt int;
declare id_utt int;
declare numee varchar(30);
declare prenumee varchar(30);
set id_contt :=( select c.id from cont as c where c.iban = expeditorr);
set id_clientt := (select c.id from clienti as c join intermediar as i on c.id = i.id_client where i.id_cont = id_contt);
set sumav := (select suma from transfer where expeditor = expeditorr and destinatar = destinatarr and suma = sumaa); 
set numee := (select username from clienti where id = id_clientt);
set prenumee := (select parola from clienti where id = id_clientt);
set id_utt :=(select id from utilizatori where username = numee and parola = prenumee);
set aprobatt := (select aprobat from transfer where expeditor = expeditorr and destinatar = destinatarr and suma = sumav);

if sumav = sumaa then
if aprobatt = "NEAPROBAT" then
update transfer 
set aprobat = "APROBAT"  where expeditor = expeditorr and destinatar = destinatarr and suma = sumaa;
set aprobatt2 := (select aprobat from transfer where expeditor = expeditorr and destinatar = destinatarr and suma = sumaa);
if aprobatt2 = "APROBAT" then
call transferr(expeditorr,destinatarr,sumaa);
insert into notificari(mesaj,id_utilizator)
values(concat(concat(concat('Ati transferat cu succes suma de ',sumaa),' de lei spre '),expeditorr),id_utt);
end if;
end if;
end if;

if aprobatt = "ERROR" then
insert into notificari
values(concat(concat(concat(concat('Transferul cu suma de: ',sumaa),' de lei spre '),expeditorr),' nu s-a realizat'),id_utt);
end if;
end; //

delimiter ;

delimiter //

create procedure depunere(in ibann varchar(40), in sumaa int)
begin
call transferr2(ibann,ibann,sumaa);
end; //

delimiter ; 


delimiter // 

	create procedure add_contact(in usernamee varchar(35), in parolaa varchar(35), in ibann varchar(20)) 
	begin
	declare id_user int;
	declare id_owner int;
	declare id_client int;
	declare numee varchar(35);
	declare prenumee varchar(35);

	set id_owner := (select id from utilizatori where username = usernamee and parola = parolaa);
	set id_client := (select cl.id from clienti as cl join intermediar as i on i.id_client = cl.id join cont as c on c.id = i.id_cont where c.iban = ibann);
	set id_user := (select u.id from utilizatori as u join clienti as c on c.utilizator_id = u.id where c.id = id_client); 
	set numee := (select nume from utilizatori where id = id_user);
	set prenumee := (select prenume from utilizatori where id = id_user);
	insert into contacte(nume, prenume, iban, owner_id, utilizator_id)
	values(numee, prenumee, ibann, id_owner, id_user);
	end; //

	delimiter ;


delimiter //

create procedure find_iban(in ibann varchar(40), out k int)
begin
if (select id from cont where iban = ibann) then
set k := 1;
else
set k := 0;
end if;
end; //
delimiter ;

delimiter //

create procedure check2()
begin
declare k int;
call find_iban('ROING87qwert1',k);
select k;
end; //
delimiter ;

delimiter //

create procedure view_contacte(in usernamee varchar(40), in parolaa varchar(40))
begin
declare id_u int;
set id_u := (select id from utilizatori where username = usernamee and parola = parolaa);
select nume, prenume from contacte where owner_id  = id_u;
end; //
delimiter ;

delimiter //

create procedure get_iban_c(in ibann varchar(40))
begin
select nume, prenume from contacte where iban = ibann;
end; //

delimiter ; 

delimiter //

create procedure get_c(in numee varchar(50), in prenumee varchar(50))
begin
select iban from contacte where nume = numee and prenume = prenumee;
end; //

delimiter ;

delimiter //

create procedure search(in numee varchar(40))
begin
select u.nume, u.prenume from utilizatori as u join clienti as c on u.id = c.utilizator_id where locate(numee,nume) > 0 or locate(numee,prenume) > 0;
end; //

delimiter ;

delimiter //

create procedure plata_factura(in usernamee varchar(40), in parolaa varchar(40), in furnizorr varchar(35), in nr_facturaa varchar(15), in sumaa int)
begin
declare id_cont_facturi int;
declare id_clientt int;
declare id_clientt2 int;
declare sold_cont int;

set id_clientt := (select id from clienti where username = usernamee and parola = parolaa);
set id_clientt2 := (select id from utilizatori where username = usernamee and parola = parolaa);
set id_cont_facturi := (select c.id from cont as c join intermediar as i on i.id_cont = c.id join clienti as cl on cl.id = i.id_client where cl.id = id_clientt and c.t_cont = 'FACTURI');

set sold_cont := (select suma from cont where id = id_cont_facturi);

if (sumaa < sold_cont) 
then
insert into facturi(id_cont, furnizor, nr_factura, suma)
values(id_cont_facturi, furnizorr, nr_facturaa, sumaa);
update cont 
set suma = suma - sumaa
where id = id_cont_facturi;
select "Plata efectuata cu succes" message;
insert into notificari(mesaj,id_utilizator)
values(concat(concat('Ati platit o factura in valoare de ' ,sumaa),' de lei'),id_clientt2);
else 
select "Suma facturii prea mare!" message;
end if;
select null message;
end; //

delimiter ;

delimiter // 

create procedure view_admin_info(in usernamee varchar(40), in parolaa varchar(40))
begin

select nume, prenume, cnp, nr_telefon, adresa, e_mail, username, parola, nr_contract from utilizatori where username = usernamee and parola = parolaa;

end; //

delimiter ; 

delimiter //

create procedure view_client_info(in usernamee varchar(40), in parolaa varchar(40))
begin

select u.nume, u.prenume, u.cnp, u.adresa, u.nr_telefon, u.e_mail, c.data_nasterii, c.sursa_venit, c.tranzactii_online, u.username, u.parola from utilizatori as u join clienti as c on c.utilizator_id = u.id where u.username = usernamee and u.parola = parolaa;

end; //

delimiter ;

delimiter //

create procedure view_angajat_info(in usernamee varchar(40), in parolaa varchar(40))
begin

declare departament varchar(40);
declare sucursala varchar(40);
declare id_sucursala int;
declare id_departament int;

set id_sucursala := (select sucursala_id from angajat where username = usernamee and parola = parolaa);
set id_departament := (select departament_id from angajat where username = usernamee and parola = parolaa);

set sucursala := (select nume from sucursala where id = id_sucursala);
set departament := (select nume from departament where id = id_departament);

select u.nume, u.prenume, u.cnp, u.adresa, u.nr_telefon, u.e_mail, a.norma, a.salariul, sucursala, departament, u.username, u.parola from utilizatori as u join angajat as a on a.angajat_id = u.id where u.username = usernamee and u.parola = parolaa; 

end; //

delimiter ;

delimiter //

create procedure get_suma(in usernamee varchar(40), in parolaa varchar(40))
begin

select suma_cont from angajat where username = usernamee and parola = parolaa;

end; //

delimiter ;

delimiter //

create procedure tip_angajat(in usernamee varchar(50), in parolaa varchar(50), out k int)
begin
declare departamentt int;
set departamentt := (select departament_id from angajat where username = usernamee and parola = parolaa);
set k := departamentt;
end; //

delimiter ;

delimiter //

create procedure check3(in numee varchar(40))
begin

select id from departament where nume = numee;

end; //

delimiter //

create procedure nume_prenume(in numee varchar(40), in prenumee varchar(40))
begin

select username, parola from utilizatori where nume = numee and prenume = prenumee;
end; //

delimiter ;

delimiter //

create procedure get_mesaj(in usernamee varchar(40), in parolaa varchar(40))
begin

declare idd int;
set idd := (select id from utilizatori where username = usernamee and parola = parolaa);

select mesaj from notificari where id_utilizator = idd;

end; //

delimiter ;

delimiter //

create procedure get_idd(in usernamee varchar(40), in parolaa varchar(40))
begin
select id from utilizatori where username = usernamee and parola = parolaa;
end; //

delimiter ;


delimiter //

create procedure insert_mesaj(in iddd int)
begin
insert into notificari(mesaj,id_utilizator)
values(concat("Date schimbate pe data de:",curdate()),iddd);
end; //

delimiter ;

delimiter //

create event auto_lichidare
ON schedule every 1 minute starts curdate()
on completion preserve

do begin
declare sumaa int;
declare idd int;
set sumaa =(select suma from depozit where perioada = timestampdiff(month, data_depunerii, curdate()));
set idd := (select id_cont from  depozit where perioada = timestampdiff(month, data_depunerii, curdate()));
update cont
set suma = suma+sumaa where id = idd;
delete from depozit
where perioada = timestampdiff(month, data_depunerii, curdate());
end; //

delimiter ;

delimiter //
create procedure addTableAuxiliar() 
begin
create table if not exists Auxiliar(
id int,
value1 int,
value2 int
);
end; //

delimiter ;

delimiter //

create procedure dropTableAuxiliar()
begin
drop table auxiliar;
end; //

delimiter ;

delimiter //

create procedure get_day()
begin
declare curr_date date;
declare zi_curr int;
set curr_date = curdate();
set zi_curr = day(curdate());
select zi_curr;
end; //

delimiter ;

delimiter //

create procedure addForeignKey() 
begin
alter table auxiliar 
add constraint FK_Auxiliar
foreign key (value2) references utilizatori(id);
end; //

delimiter ;

delimiter //

create procedure dropForeignKey()
begin
alter table auxiliar
drop foreign key  FK_Auxiliar;
end; //

delimiter ;

delimiter //

create procedure addPrimaryKey()
begin
alter table auxiliar
add primary key(id);
end; //

delimiter ;

delimiter //

create procedure dropPrimaryKey()
begin
alter table auxiliar
drop primary key;
end; //

delimiter ;

delimiter //

create procedure insertTable()
begin
insert into auxiliar(id,value1,value2)
values(1,2,3);
end; //

delimiter ;

delimiter //

create procedure deletefromTable()
begin
delete from auxiliar where id = 1;
end; //

delimiter ;

create table if not exists versiuneCurenta (
	nrVersiuneCurenta int unique primary key
);


create table if not exists versiuni (
		nrVersiune int unique auto_increment primary key,
        numeProcedura varchar(40),
        numeInversa varchar(40)
);

insert into  versiuneCurenta(nrVersiuneCurenta)
value(0);

insert into versiuni(nrVersiune, numeProcedura, numeInversa)
values(1, 'call addTableAuxiliar();', 'call dropTableAuxiliar();');

insert into versiuni(nrVersiune, numeProcedura, numeInversa)
values(2, 'call addForeignKey();', 'call dropForeignKey();');

insert into versiuni(nrVersiune, numeProcedura, numeInversa)
values(3, 'call addPrimaryKey();', 'call dropPrimaryKey();');

insert into versiuni(nrVersiune, numeProcedura, numeInversa)
values(4, 'call insertTable();', 'call deletefromTable();');


delimiter //

create procedure bringVersion(in versionn int) 
begin
declare currentVersion int;
declare queryName varchar(40);

set currentVersion := (select nrVersiuneCurenta from versiuneCurenta);

while currentVersion > versionn  do
    set @queryName = (select numeInversa from versiuni where nrVersiune = currentVersion);
    select @queryName;
    prepare myQuery from @queryName;
    execute myQuery;
    set currentVersion = currentVersion - 1;
end while;

while currentVersion < versionn do
	set currentVersion = currentVersion + 1;
    set @queryName = (select numeProcedura from versiuni where nrVersiune = currentVersion);
    prepare myQuery from @queryName;
    execute myQuery;
end while;

update versiuneCurenta
set nrVersiuneCurenta = versionn;
end; //

delimiter ;

call bringVersion(1);
call bringVersion(2);
call bringVersion(3);
call bringVersion(4);
call bringVersion(1);

insert into sucursala(nume)
values
('Arad'),
('Brasov'),
('Bucuresti'),
('Cluj-Napoca'),
('Targu Mures');

insert into departament(nume)
values
('HR'),
('IT'),
('Functionar');

set sql_safe_updates = 0;
call create_account_angajat('alexandrei', 'doruclean', '5010745567123', 'Alexa', 'Andrei', 'Bucegi 30', '0745015823', 'alexandrei@yahoo.com', 'ROBT413745039', '123402', 8, 2800, 'Cluj-Napoca', 'IT','ROBT54621432');
call create_account_angajat('mircea123', 'celbatran', '5021452148965', 'Mircea', 'Mare', 'Mures 35', '0768642571', 'mirceamare@yahoo.com', 'ROBT65701453', '145621', 8, 2000, 'Targu Mures', 'Functionar','ROBT63214587');
call create_account_angajat('paul4321', 'paul1234', '50423654785', 'Paul', 'Petrescu', 'Arges,nr 14', '0735631745', 'paulpetrescu@yahoo.com', 'ROBT56241235', '23', 4, 3500, 'Brasov', 'HR','ROBT84532145');
call create_cont_client(300,'CURENT','ROBT574123123', 'gavreaioan','gavrea100');
call create_cont_client(650,'ECONOMII','ROBT412323512', 'gavreaioan','gavrea100');
call create_cont_client(0,'ECONOMII','ROING125731356', 'gavreaioan','gavrea100');
call create_cont_client(250, 'FACTURI', 'ROBT133455677', 'gavreaioan', 'gavrea100');
call create_cont_client(100,'ECONOMII','ROING87654821', 'andrei123','andrei100');
call create_cont_client(300,'FACTURI','ROING21463289', 'andrei123','andrei100');
call create_depozit_client(300,3,'ROBT574123123');
call transferr2('ROING87654821','ROBT133455677',900);
call aprobare_t('ROING87654821','ROBT133455677',100);
call depunere('ROBT412323512',300);
call transferr2('ROING87654821','ROBT133455677',100);
call aprobare_t('ROING87654821','ROBT133455677',100);
#call check1();
#call view_cont('gavreaioan','gavrea100');
call vizualizare_depozite('gavreaioan','gavrea100');
#call lichidare_depo('ROBT574123123');
#call view_cont('gavreaioan','gavrea100');
#call check2();
call solicitare_c('124656',123,'gavreaioan','gavrea100','ROBT574123123');
#call aprobare_card('Gavrea','Ioan', '124656');
#call card_f('ROBT574123123');
#call depo_f('ROBT412323512');
call add_contact('gavreaioan', 'gavrea100', 'ROBT574123123');
#call add_contact('gavreaioan', 'gavrea100', 'ROING87654821');
#call add_contact('gavreaioan', 'gavrea100', 'ROBT7561753');
#call view_contacte('gavreaioan', 'gavrea100');
#call get_iban_c('ROING87654821');
#call plata_factura('gavreaioan', 'gavrea100', 'Electrica', '1234', 100);
#call plata_factura('gavreaioan', 'gavrea100', 'RDS-RCS', '23245', 300);
#select nume, prenume from utilizatori;
#call view_client_info('gavreaioan', 'gavrea100');
#call view_angajat_info('alexandrei', 'doruclean');
#call tip_angajat('alexandrei','doruclean');
#call plata_angajati();
call get_day();