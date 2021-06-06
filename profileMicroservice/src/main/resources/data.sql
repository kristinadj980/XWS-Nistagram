INSERT INTO AUTHORITY (id ,name) VALUES (1, 'ROLE_REGISTRED_USER');
INSERT INTO AUTHORITY (id ,name) VALUES (2, 'ROLE_REGISTRED_USER');

INSERT INTO PERSON(dtype,id,birth_date,email,enabled,gender,name,password,phone_number,surname,username)
VALUES ('Profile',100,'1998-12-12','pera@peric.com',1,0,'pera','$2a$10$p04fhHBotVfnA/7Mh78lReGRZQPO8wXlROplUX5eNjPtlbgBZ4Dte','0605666777','peric','pera');

INSERT INTO PERSON(dtype,id,birth_date,email,enabled,gender,name,password,phone_number,surname,username)
VALUES ('Profile',101,'1998-12-12','miki@peric.com',1,0,'pera','$2a$10$p04fhHBotVfnA/7Mh78lReGRZQPO8wXlROplUX5eNjPtlbgBZ4Dte','0605666777','peric','mika');


INSERT INTO USER_AUTHORITY (user_id,authority_id)
VALUES (100,1);
INSERT INTO USER_AUTHORITY (user_id,authority_id)
VALUES (101,2);