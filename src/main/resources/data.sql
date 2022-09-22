#------------------------------------------------------------
#        Script MySQL.
#------------------------------------------------------------


#------------------------------------------------------------
# Table: user
#------------------------------------------------------------

CREATE TABLE user(
        user_id    Int  Auto_increment  NOT NULL ,
        password   Varchar (250) NOT NULL ,
        first_name Varchar (250) NOT NULL ,
        last_name  Varchar (250) NOT NULL ,
        amount     Int NOT NULL ,
        email      Varchar (250) NOT NULL,
        is_activate boolean
	,CONSTRAINT user_AK UNIQUE (email)
	,CONSTRAINT user_PK PRIMARY KEY (user_id)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: transactions
#------------------------------------------------------------

CREATE TABLE transactions(
        transaction_id Int  Auto_increment  NOT NULL ,
        beneficiary_id Int NOT NULL ,
        description    Varchar (250) NOT NULL ,
        amount         Int NOT NULL ,
        date           Date NOT NULL ,
        commission     Int NOT NULL ,
        user_id        Int NOT NULL
	,CONSTRAINT transactions_PK PRIMARY KEY (transaction_id)

	,CONSTRAINT transactions_user_FK FOREIGN KEY (user_id) REFERENCES user(user_id)
)ENGINE=InnoDB;



#------------------------------------------------------------
# Table: role
#------------------------------------------------------------

CREATE TABLE role(
        role_id   Int  Auto_increment  NOT NULL ,
        role_name Varchar (50) NOT NULL
	,CONSTRAINT role_PK PRIMARY KEY (role_id)
)ENGINE=InnoDB;

Insert into role (role_id, role_name)
values (1, 'USER'),(2, 'ADMIN');



#------------------------------------------------------------
# Table: friend
#------------------------------------------------------------

CREATE TABLE friend(
        user_id        Int NOT NULL ,
        user_id_friend Int NOT NULL
	,CONSTRAINT friend_PK PRIMARY KEY (user_id,user_id_friend)

	,CONSTRAINT friend_user_FK FOREIGN KEY (user_id) REFERENCES user(user_id)
	,CONSTRAINT friend_user0_FK FOREIGN KEY (user_id_friend) REFERENCES user(user_id)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: users_roles
#------------------------------------------------------------

CREATE TABLE users_roles(
        role_id Int NOT NULL ,
        user_id Int NOT NULL
	,CONSTRAINT users_roles_PK PRIMARY KEY (role_id,user_id)
	,CONSTRAINT users_roles_user0_FK FOREIGN KEY (user_id) REFERENCES user(user_id)
	,CONSTRAINT users_roles_role_FK FOREIGN KEY (role_id) REFERENCES role(role_id)
	
)ENGINE=InnoDB;

