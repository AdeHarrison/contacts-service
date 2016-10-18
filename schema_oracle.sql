-- as system
--CREATE USER contacts_owner IDENTIFIED BY contacts_owner;
--GRANT CONNECT TO contacts_owner;
--GRANT ALL PRIVILEGES TO contacts_owner;

-- as contacts_owner
DROP TABLE contact CASCADE CONSTRAINTS;
DROP TABLE address CASCADE CONSTRAINTS;
DROP TABLE address_type CASCADE CONSTRAINTS;
DROP TABLE contact_address CASCADE CONSTRAINTS;

CREATE TABLE contact (
        contact_id NUMBER(5) PRIMARY KEY,
        first_name VARCHAR2(20) NOT NULL,
        last_name VARCHAR2(20) NOT NULL
);

CREATE TABLE address (
        address_id NUMBER(5) PRIMARY KEY,
        address_type_id NUMBER(5) NOT NULL,
        address_line_1 VARCHAR2(20) NOT NULL,
        address_line_2 VARCHAR2(20) NULL,
        city VARCHAR2(20) NOT NULL,
        postcode VARCHAR2(20) NOT NULL
);

CREATE TABLE address_type (
        address_type_id NUMBER(5) PRIMARY KEY,
        description VARCHAR2(20)
);

CREATE TABLE contact_address (
        contact_id NUMBER(5) NOT NULL,
        address_id NUMBER(5) NOT NULL,
	
        PRIMARY KEY(contact_id, address_id)
);

ALTER TABLE address ADD CONSTRAINT fk_address_address_type FOREIGN KEY(address_type_id) REFERENCES address_type(address_type_id);

ALTER TABLE contact_address ADD CONSTRAINT fk_contact FOREIGN KEY(contact_id) REFERENCES contact(contact_id);
ALTER TABLE contact_address ADD CONSTRAINT fk_address FOREIGN KEY(address_id) REFERENCES address(address_id);


INSERT INTO address_type (address_type_id, description) VALUES (1, 'HOME');
INSERT INTO address_type (address_type_id, description) VALUES (2, 'WORK');
INSERT INTO address_type (address_type_id, description) VALUES (3, 'CORRESPONDENCE');

INSERT INTO contact(contact_id, first_name, last_name) VALUES (1, 'cfn1', 'cln1');
INSERT INTO contact(contact_id, first_name, last_name) VALUES (2, 'cfn2', 'cln2');
INSERT INTO contact(contact_id, first_name, last_name) VALUES (3, 'cfn3', 'cln3');

INSERT INTO address (address_id, address_type_id, address_line_1, address_line_2, city,postcode) VALUES (1, 1, 'cfn1 Address1.1', 'cfn1 Address2.1', 'cfn1 City.1', 'cfn1 Postcode.1');

INSERT INTO address (address_id, address_type_id, address_line_1, city,postcode) VALUES (2, 1, 'cfn2 Address1.1', 'cfn2 City.1', 'cfn2 Postcode.1');
INSERT INTO address (address_id, address_type_id, address_line_1, city,postcode) VALUES (3, 2, 'cfn2 Address1.2', 'cfn2 City.2', 'cfn2 Postcode.2');

INSERT INTO address (address_id, address_type_id, address_line_1, address_line_2, city,postcode) VALUES (4, 1, 'cfn3 Address1.1', 'cfn3 Address2.1', 'cfn3 City.1', 'cfn3 Postcode.1');
INSERT INTO address (address_id, address_type_id, address_line_1, address_line_2, city,postcode) VALUES (5, 2, 'cfn3 Address1.2', 'cfn3 Address2.2', 'cfn3 City.2', 'cfn3 Postcode.1');
INSERT INTO address (address_id, address_type_id, address_line_1, address_line_2, city,postcode) VALUES (6, 3, 'cfn3 Address1.3', 'cfn3 Address2.3', 'cfn3 City.3', 'cfn3 Postcode.1');

INSERT INTO contact_address (contact_id, address_id) VALUES (1,1);
INSERT INTO contact_address (contact_id, address_id) VALUES (2,1);
INSERT INTO contact_address (contact_id, address_id) VALUES (2,2);
INSERT INTO contact_address (contact_id, address_id) VALUES (3,1);
INSERT INTO contact_address (contact_id, address_id) VALUES (3,2);
INSERT INTO contact_address (contact_id, address_id) VALUES (3,3);
