insert into course (id, name, created_date, last_updated_date)
values(10001l,'JPA in 50 Steps', sysdate(), sysdate());
insert into course (id, name, created_date, last_updated_date) 
values(10002l,'Spring in 50 Steps', sysdate(), sysdate());
insert into course (id, name, created_date, last_updated_date) 
values(10003l,'Spring Boot in 50 Steps', sysdate(), sysdate());

insert into student(id,name)
values(20001,'Ranga');
insert into student(id,name)
values(20002, 'Adam');
insert into student(id,name)
values(20003, 'Jane');

insert into passport(id,number)
values(40001, 'E123456');
insert into passport(id,number)
values(40002, 'E123457');
insert into passport(id,number)
values(40003, 'E123890');


insert into review(id, rating, description)
values(50001,'5', 'Great Course');
insert into review(id, rating, description)
values(50002,'4', 'Wonderful Course');
insert into review(id, rating, description)
values(50003,'5', 'Awesome Course');