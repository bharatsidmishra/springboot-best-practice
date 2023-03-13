insert into user_details (id,birth_date,name)
values (10001,current_date(),'sidd');
insert into user_details (id,birth_date,name)
values (10002,current_date(),'Rahul');
insert into user_details (id,birth_date,name)
values (10003,current_date(),'Bharat');

insert into post (id,description,user_id)
values(20001, 'I want to learn Aws', 10001);

insert into post (id,description,user_id)
values(20002, 'I want to learn devops', 10001);

insert into post (id,description,user_id)
values(20003, 'I want to learn Multicloud', 10002);

insert into post (id,description,user_id)
values(20004, 'I want to learn Architect', 10002);