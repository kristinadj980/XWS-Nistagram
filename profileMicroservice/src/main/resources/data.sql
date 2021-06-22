INSERT INTO AUTHORITY (id ,name) VALUES (1, 'ROLE_REGISTRED_USER');
INSERT INTO AUTHORITY (id ,name) VALUES (2, 'ROLE_ADMINISTRATOR');

INSERT INTO PERSON(dtype,id,birth_date,email,enabled,gender,name,password,phone_number,surname,username,biography, profile_status, allowed_messages, allowed_tags)
VALUES ('Profile',100,'1998-12-12','pera@peric.com',1,0,'pera','$2a$10$p04fhHBotVfnA/7Mh78lReGRZQPO8wXlROplUX5eNjPtlbgBZ4Dte','0605666777','peric','pera', '"Can I be excused for the rest of my life?" by SpongeBob', 0, false, false);

INSERT INTO PERSON(dtype,id,birth_date,email,enabled,gender,name,password,phone_number,surname,username, biography, profile_status, allowed_messages, allowed_tags)
VALUES ('Profile',101,'1998-12-12','miki@peric.com',1,0,'pera','$2a$10$p04fhHBotVfnA/7Mh78lReGRZQPO8wXlROplUX5eNjPtlbgBZ4Dte','0605666777','peric','mika', '"The inner machinations of my mind are an enigma." by Patrick', 1, false, false);

INSERT INTO PERSON(dtype,id,birth_date,email,enabled,gender,name,password,phone_number,surname,username, profile_status, allowed_messages, allowed_tags)
VALUES ('Profile',102,'1998-12-12','ana@anic.com',1,0,'ana','$2a$10$p04fhHBotVfnA/7Mh78lReGRZQPO8wXlROplUX5eNjPtlbgBZ4Dte','0605666777','anic','ana', 1, false, false);

INSERT INTO PERSON(dtype,id,birth_date,email,enabled,gender,name,password,phone_number,surname,username, profile_status, allowed_messages, allowed_tags)
VALUES ('Profile',103,'1998-12-12','milka@milic.com',1,0,'milka','$2a$10$p04fhHBotVfnA/7Mh78lReGRZQPO8wXlROplUX5eNjPtlbgBZ4Dte','0605666777','milic','milka', 0, false, false);

INSERT INTO PERSON(dtype,id,birth_date,email,enabled,gender,name,password,phone_number,surname,username, profile_status, allowed_messages, allowed_tags)
VALUES ('Administrator',104,'1998-12-12','admin@admin.com',1,0,'marko','$2a$10$p04fhHBotVfnA/7Mh78lReGRZQPO8wXlROplUX5eNjPtlbgBZ4Dte','0605666777','milic','admin', 0, false, false);

INSERT INTO PERSON(dtype,id,birth_date,email,enabled,gender,name,password,phone_number,surname,username, profile_status, allowed_messages, allowed_tags)
VALUES ('Profile',105,'1998-12-12','anabela@gmail.com',1,0,'anabela','$2a$10$p04fhHBotVfnA/7Mh78lReGRZQPO8wXlROplUX5eNjPtlbgBZ4Dte','0605666777','anic','anabela', 1, false, false);

INSERT INTO PERSON(dtype,id,birth_date,email,enabled,gender,name,password,phone_number,surname,username, profile_status, allowed_messages, allowed_tags)
VALUES ('Profile',106,'1998-12-12','anastasija@gmail.com',1,0,'anastasija','$2a$10$p04fhHBotVfnA/7Mh78lReGRZQPO8wXlROplUX5eNjPtlbgBZ4Dte','0605666777','anic','anastasija', 1, false, false);


INSERT INTO USER_AUTHORITY (user_id,authority_id)
VALUES (100,1);
INSERT INTO USER_AUTHORITY (user_id,authority_id)
VALUES (101,1);
INSERT INTO USER_AUTHORITY (user_id,authority_id)
VALUES (102,1);
INSERT INTO USER_AUTHORITY (user_id,authority_id)
VALUES (103,1);
INSERT INTO USER_AUTHORITY (user_id,authority_id)
VALUES (104,2);
INSERT INTO USER_AUTHORITY (user_id,authority_id)
VALUES (105,1);
INSERT INTO USER_AUTHORITY (user_id,authority_id)
VALUES (106,1);

