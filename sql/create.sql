--  drop table ambulatory, company, employee, patient, service, visit;
--  drop domain jobs, provinces;

create domain jobs as varchar(30)
check(value in('dentista', 'segretario', 'infermiere','oculista', 'ortopedista', 'fisioterapista', 'cardiologo', 'dermatologo', 'oncologo', 'geriatra' ));

create domain provinces as char(2)
check(value in('AG','AL','AN','AO','AR','AP','AT','AV','BA','BT','BL','BN','BG','BI','BO','BZ','BS','BR','CA','CL','CB','CI','CE','CT','CZ','CH','CO','CS','CR','KR','CN','EN','FM','FE','FI','FG','FC','FR','GE','GO','GR','IM','IS','SP','AQ','LT','LE','LC','LI','LO','LU','MC','MN','MS','MT','ME','MI','MO','MB','NA','NO','NU','OT','OR','PD','PA','PR','PV','PG','PU','PE','PC','PI','PT','PN','PZ','PO','RG','RA','RC','RE','RI','RN','RM','RO','SA','VS','SS','SV','SI','SR','SO','TA','TE','TR','TO','OG','TP','TN','TV','TS','UD','VA','VE','VB','VC','VR','VV','VI','VT'));

-- "BL","PD","RO","TV","VE","VR","VI" forse nel dominio ci vanno solo queste

create table company(
    id varchar primary key,
    name varchar(50) not null,
    street varchar(50) not null,
    cap char(5) check(cap SIMILAR TO '[0-9]{5}') not null,
    city varchar(50) not null,
    province provinces not null
);


create table ambulatory(
    name varchar,
    company varchar references company(id),
    street varchar(50) not null,
    cap char(5) check(cap similar to '[0-9]{5}') not null,
    city varchar(50) not null,
    province provinces not null,
    contractDate date not null,
    description text not null,
    primary key(name, company)
);


create table patient(
    fiscalcode varchar primary key check(fiscalcode similar to '[A-Z]{6}[0-9]{2}[A-Z][0-9]{2}[A-Z][0-9]{3}[A-Z]'),
    healthcarecompany varchar references company(id),
	pin char(4) CHECK(pin SIMILAR TO '[0-9]{4}') NOT NULL,
    name varchar(50) not null,
    surname varchar(50) not null,
    birthdate date not null,
    birthplace varchar(50) not null,
    province provinces not null,
    email varchar(50) check(email like '%@%') not null
);

create table employee(
    fiscalcode varchar primary key check(fiscalcode similar to '[A-Z]{6}[0-9]{2}[A-Z][0-9]{2}[A-Z][0-9]{3}[A-Z]'),
    employeeCode varchar(9) check(employeeCode similar to 'JOBIT[0-9]{4}'),
	password varchar(30) NOT NULL,
    name varchar(50) not null,
    surname varchar(50) not null,
    job jobs not null,
    ambulatory varchar,
    company varchar,
    FOREIGN KEY(ambulatory, company) REFERENCES ambulatory(name, company)
);

create table service (
	ambulatory VARCHAR,
	company varchar,
	name varchar,
	FOREIGN KEY(ambulatory, company) REFERENCES ambulatory(name, company),
	PRIMARY KEY(ambulatory, company, name)
);

create table visit(
    patient varchar references patient,
	ambulatory VARCHAR,
	company varchar,
	serviceName varchar,
    doctor varchar references employee,
    date date,
	hour smallint check ( hour >= 8 AND hour <= 16 ),
    urgency varchar check(urgency in('bassa', 'media', 'alta')),
    regime varchar check(regime in('privata', 'rimborsata dal sistema sanitario','rimborsata da assicurazioni private')),
    result text,
	FOREIGN KEY(ambulatory, company, serviceName) REFERENCES service(ambulatory, company, name),
    primary key(patient, ambulatory, company, serviceName, date, hour)
)



