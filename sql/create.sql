
create domain jobs as varchar(30)
check(value in('dentista', 'segretario', 'infermiere','oculista', 'ortopedista', 'fisioterapista', 'cardiologo', 'dermatologo', 'oncologo', 'geriatra' ));

create domain provinces as char(2)
check(value in('BL','PD','RO','TV','VE','VR','VI'));

create table company(
    id varchar primary key,
    name varchar(50) not null,
    street varchar(50) not null,
    cap char(5) check(cap SIMILAR TO '[0-9]{5}') not null,
    city varchar(50) not null,
    province provinces not null
);


create table clinic(
    name varchar,
    company varchar references company(id),
    street varchar(50) not null,
    cap char(5) check(cap similar to '[0-9]{5}') not null,
    city varchar(50) not null,
    province provinces not null,
    contractdate date not null,
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
    employeeCode varchar(9) check(employeeCode similar to 'EMP[0-9]{4}'),
	password varchar(30) NOT NULL,
    name varchar(50) not null,
    surname varchar(50) not null,
    job jobs not null,
    clinic varchar,
    company varchar,
    FOREIGN KEY(clinic, company) REFERENCES clinic(name, company)
);

create table service (
	clinic VARCHAR,
	company varchar,
	name varchar,
    regime varchar check(regime in('privata', 'rimborsata dal sistema sanitario','rimborsata da assicurazioni private')),
	FOREIGN KEY(clinic, company) REFERENCES clinic(name, company),
	PRIMARY KEY(clinic, company, name)
);

create table visit(
    patient varchar references patient,
	clinic VARCHAR,
	company varchar,
	serviceName varchar,
    doctor varchar references employee,
    date date,
	hour smallint check ( hour >= 8 AND hour < 16 ),
    urgency varchar check(urgency in('bassa', 'media', 'alta')),
    result text,
	FOREIGN KEY(clinic, company, serviceName) REFERENCES service(clinic, company, name),
    primary key(patient, clinic, company, serviceName, date, hour)
)



