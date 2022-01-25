
-- SELECT, DELETE and DROP are in the end


insert into user_roles(role_name) values ("admin");
insert into user_roles(role_name) values ("client");


insert into users(login,password,role_id) values("admin1","qwe12345",1);
insert into users(login,password,role_id) values("admin2","user2",1);
insert into users(login,password,role_id) values("admin3","user2",1);
insert into users(login,password,role_id) values("ADMIN","ADMIN",1);

insert into users(login,password,role_id) values("user1","qwe12345",2);
insert into users(login,password,role_id) values("user2","user2",2);
insert into users(login,password,role_id) values("user3","system",2);
insert into users(login,password,role_id) values("user4","system",2);


insert into admins(user_id,name,photo) values(1,"ADMIN_1","");
insert into admins(user_id,name,photo) values(2,"Liza","");
insert into admins(user_id,name,photo) values(3,"Valera","");
insert into admins(user_id,name,photo) values(4,"BOSS","");

insert into all_clients(user_id, last_name, first_name, passport_id, date_of_birth, country, phone_number, email) 
	values(5, "Фурс", "Мария", "A46B3J754O", "2001-01-01", "Беларусь", "+3752911111111", "maria@mail.ru");
insert into all_clients(user_id, last_name, first_name, passport_id, date_of_birth, country, phone_number, email) 
	values(6, "Иван", "Иванов", "B46TE43", "2002-02-02", "Беларусь", "+375292222222", "ivan@gmail.com");
insert into all_clients(user_id, last_name, first_name, passport_id, date_of_birth, country, phone_number, email) 
	values(7, "Jack", "Smith", "QXCD23", "1980-10-20", "UK", "+90234211", "jack@gmail.com");
insert into all_clients(user_id, last_name, first_name, passport_id, date_of_birth, country, phone_number, email) 
	values(8, "Marco", "Russo", "I87I09", "1995-03-07", "Italia", "+675286132", "marco@gmail.com");
insert into all_clients(last_name, first_name, passport_id, date_of_birth, country, phone_number, email) 
	values("Александр", "Петров", "GHG34567", "1960-11-14", "Россия", "+80454321213", "alex@mail.ru");
insert into all_clients(last_name, first_name, passport_id, date_of_birth, country, phone_number, email) 
	values("Lucas", "Blanchet", "FR7654J", "1978-07-26", "France", "+2312435253", "lucas@mail.ru");
        
    
insert into blacklist(client_id, reason, since_date) values(3, "курение в комнате", "2020-12-12");
insert into blacklist(client_id, reason, since_date) values(2, "драка", "2021-03-10");

insert into regular_customers(client_id, since_date, discount, notes) values(5, "2021-10-10", 5, "заезд всегда после 20:00");
insert into regular_customers(client_id, since_date, discount, notes) values(1, "2019-05-20", 3, "2 бутылки воды");


insert into rooms(room_number, cost, capacity, gender, bathroom, notes) values(1,25,4,'ж',0,"с балконом, есть утюг, стиральная машинка");
insert into rooms(room_number, cost, capacity, gender, bathroom, notes) values(2,20,6,'ж',0,"с телевизором, есть утюг, стиральная машинка");
insert into rooms(room_number, cost, capacity, gender, bathroom, notes) values(3,25,5,'м',0,"с телевизором, есть утюг, стиральная машинка");
insert into rooms(room_number, cost, capacity, gender, bathroom, notes) values(4,20,7,'м',0,"с чайником, есть утюг, стиральная машинка");
insert into rooms(room_number, cost, capacity, gender, bathroom, notes) values(5,18,8,'мж',1,"с холодильником, есть утюг, стиральная машинка");
insert into rooms(room_number, cost, capacity, gender, bathroom, notes) values(6,18,8,'мж',1,"с холодильником, есть утюг, стиральная машинка");
insert into rooms(room_number, cost, capacity, gender, bathroom, notes) values(7,35,2,'',1,"с телевизором, есть утюг, стиральная машинка");
insert into rooms(room_number, cost, capacity, gender, bathroom, notes) values(8,35,2,'',1,"с телевизором и чайником, есть утюг, стиральная машинка");


insert into images(img_path, room_number) values("images/1.jpg",1);
insert into images(img_path, room_number) values("images/1_2.jpg",1);
insert into images(img_path, room_number) values("images/2.jpg",2);
insert into images(img_path, room_number) values("images/2_2.jpg",2);
insert into images(img_path, room_number) values("images/2_3.jpg",2);
insert into images(img_path, room_number) values("images/3.jpg",3);
insert into images(img_path, room_number) values("images/3_2.jpg",3);
insert into images(img_path, room_number) values("images/4.jpg",4);
insert into images(img_path, room_number) values("images/4_2.jpg",4);
insert into images(img_path, room_number) values("images/4_3.jpg",4);
insert into images(img_path, room_number) values("images/7.jpg",7);
insert into images(img_path, room_number) values("images/7_2.jpg",7);


insert into booking(user_id, room_number, from_date, to_date, guests_count, approved, paid) 
	values( 5, 1, "2021-12-20", "2021-12-23", 2, 0, 0); 
insert into booking(user_id, room_number, from_date, to_date, guests_count, approved, approve_date, paid) 
	values( 6, 4, "2021-12-20", "2021-12-30", 1, 1, "2021-12-06", 1); 
insert into booking(user_id, room_number, from_date, to_date, guests_count, approved, approve_date, paid) 
	values( 8, 5, "2021-12-18", "2021-12-19", 4, 0, "2021-12-05", 0); 
    
    
insert into all_stays(client_id, room_number, from_date, to_date, notes) 
	values(1,8,"2021-07-10","2021-07-14","не хватило полотенца");
insert into all_stays(client_id, room_number, from_date, to_date, notes) 
	values(4,5,"2021-09-05","2021-09-14","все понравилось");
insert into all_stays(client_id, room_number, from_date, to_date, notes) 
	values(5,3,"2019-01-13","2019-01-15","все понравилось");
insert into all_stays(client_id, room_number, from_date, to_date, notes) 
	values(6,3,"2019-01-13","2019-01-15","все понравилось");




-- select * from user_roles;
-- select * from users;
-- select * from admins;
-- select * from all_clients;
-- select * from blacklist;
-- select * from regular_customers;
-- select * from rooms;
-- select * from images;
-- select * from booking;
-- select * from all_stays;


-- select * from users inner join user_roles On users.role_id = user_roles.role_id;
-- select * from admins inner join users On users.user_id = admins.user_id;
-- select * from all_clients inner join users On users.user_id = all_clients.user_id;
-- select * from blacklist inner join all_clients On blacklist.client_id = all_clients.client_id;
-- select * from regular_customers inner join all_clients On regular_customers.client_id = all_clients.client_id;
-- select * from rooms inner join images on rooms.room_number = images.room_number;
-- select * from booking inner join rooms on rooms.room_number = booking.room_number 
-- 			inner join users on users.user_id = booking.user_id;
-- select * from all_stays inner join rooms on rooms.room_number = all_stays.room_number 
-- 			inner join all_clients on all_clients.client_id = all_stays.client_id;




-- delete from user_roles where role_id between 1 and 1000;
-- delete from users where user_id between 1 and 1000;
-- delete from admins where user_id between 1 and 1000;
-- delete from all_clients where client_id between 1 and 1000;
-- delete from blacklist where client_id between 1 and 1000;
-- delete from regular_customers where client_id between 1 and 1000;
-- delete from booking where booking_id between 1 and 1000;
-- delete from all_stays where stay_id between 1 and 1000;
-- delete from rooms where room_number between 1 and 1000;
-- delete from images where img_id between 1 and 1000;




-- drop table admins;
-- drop table blacklist;
-- drop table regular_customers;
-- drop table booking;
-- drop table images;
-- drop table all_stays;
-- drop table rooms;
-- drop table all_clients;
-- drop table users;
-- drop table user_roles;