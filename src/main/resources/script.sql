CREATE DATABASE medical;

CREATE SCHEMA IF NOT EXISTS research;

-- Creating autoincrement sequences
CREATE SEQUENCE clinics_id_seq;
CREATE SEQUENCE statuses_id_seq;
CREATE SEQUENCE genders_id_seq;
CREATE SEQUENCE drug_types_id_seq;
CREATE SEQUENCE roles_id_seq;
CREATE SEQUENCE users_id_seq;
CREATE SEQUENCE drug_units_id_seq;
CREATE SEQUENCE patients_id_seq;
CREATE SEQUENCE visits_id_seq;

-- Creating tables
CREATE TABLE IF NOT EXISTS clinics
(
    id       BIGINT PRIMARY KEY UNIQUE NOT NULL DEFAULT nextval('clinics_id_seq'),
    name     text                      NOT NULL,
    address1 text                      NOT NULL,
    address2 text
);

CREATE TABLE IF NOT EXISTS statuses
(
    id   BIGINT PRIMARY KEY UNIQUE NOT NULL DEFAULT nextval('statuses_id_seq'),
    name text UNIQUE               NOT NULL
);

CREATE TABLE IF NOT EXISTS genders
(
    id   BIGINT PRIMARY KEY UNIQUE NOT NULL DEFAULT nextval('genders_id_seq'),
    name text UNIQUE               NOT NULL
);

CREATE TABLE IF NOT EXISTS drug_types
(
    id   BIGINT PRIMARY KEY UNIQUE NOT NULL DEFAULT nextval('drug_types_id_seq'),
    name text UNIQUE               NOT NULL
);

CREATE TABLE IF NOT EXISTS roles
(
    id   BIGINT PRIMARY KEY UNIQUE NOT NULL DEFAULT nextval('roles_id_seq'),
    name text UNIQUE               NOT NULL
);

CREATE TABLE IF NOT EXISTS users
(
    id       BIGINT PRIMARY KEY UNIQUE NOT NULL DEFAULT nextval('users_id_seq'),
    email    text UNIQUE               NOT NULL,
    password text                      NOT NULL,
    role_id  BIGINT                    NOT NULL,
    FOREIGN KEY (role_id) REFERENCES roles (id)
);

CREATE TABLE IF NOT EXISTS drug_units
(
    id           BIGINT PRIMARY KEY UNIQUE NOT NULL DEFAULT nextval('drug_units_id_seq'),
    name         text UNIQUE               NOT NULL,
    drug_type_id BIGINT                    NOT NULL,
    FOREIGN KEY (drug_type_id) REFERENCES drug_types (id)
);

CREATE TABLE IF NOT EXISTS clinics_drug_units
(
    clinic_id    BIGINT NOT NULL,
    drug_unit_id BIGINT NOT NULL,
    FOREIGN KEY (clinic_id) REFERENCES clinics (id),
    FOREIGN KEY (drug_unit_id) REFERENCES drug_units (id)
);

CREATE TABLE IF NOT EXISTS patients
(
    id            BIGINT PRIMARY KEY UNIQUE NOT NULL DEFAULT nextval('patients_id_seq'),
    name          text                      NOT NULL,
    last_name     text                      NOT NULL,
    date_of_birth date                      NOT NULL,
    initials      text                      NOT NULL,
    status_id     BIGINT                    NOT NULL,
    gender_id     BIGINT                    NOT NULL,
    drug_type_id  BIGINT                    NOT NULL,
    clinic_id     BIGINT                    NOT NULL,
    FOREIGN KEY (status_id) REFERENCES statuses (id),
    FOREIGN KEY (gender_id) REFERENCES genders (id),
    FOREIGN KEY (drug_type_id) REFERENCES drug_types (id),
    FOREIGN KEY (clinic_id) REFERENCES clinics (id)
);

CREATE TABLE IF NOT EXISTS visits
(
    id           BIGINT PRIMARY KEY UNIQUE NOT NULL DEFAULT nextval('visits_id_seq'),
    last_visit   date,
    drug_unit_id BIGINT                    NOT NULL,
    patient_id   BIGINT                    NOT NULL,
    FOREIGN KEY (drug_unit_id) REFERENCES drug_units (id),
    FOREIGN KEY (patient_id) REFERENCES patients (id)
);

-- Updating autoincrement sequence
ALTER SEQUENCE clinics_id_seq
    OWNED BY clinics.id;

ALTER SEQUENCE statuses_id_seq
    OWNED BY statuses.id;

ALTER SEQUENCE genders_id_seq
    OWNED BY genders.id;

ALTER SEQUENCE drug_types_id_seq
    OWNED BY drug_types.id;

ALTER SEQUENCE roles_id_seq
    OWNED BY roles.id;

ALTER SEQUENCE users_id_seq
    OWNED BY users.id;

ALTER SEQUENCE drug_units_id_seq
    OWNED BY drug_units.id;

ALTER SEQUENCE patients_id_seq
    OWNED BY patients.id;

ALTER SEQUENCE visits_id_seq
    OWNED BY visits.id;

-- Filling tables with data
INSERT INTO drug_types(name)
VALUES ('Placebo');

INSERT INTO drug_types(name)
VALUES ('Paracetamol');

INSERT INTO drug_types(name)
VALUES ('Ibuprofen');

INSERT INTO clinics(name, address1)
VALUES ('San Jose St. Bonaventure Hospital', 'San Jose, California, U.S');

INSERT INTO clinics(name, address1, address2)
VALUES ('Harlem Hospital Center', 'East Bruceburgh, WY 42964-5354', 'Suite 995 1451 Mosciski Expressway');

INSERT INTO clinics(name, address1, address2)
VALUES ('Kirby Medical Center', 'Hilllbury, MI 01123', '710 Daron Island');

INSERT INTO clinics(name, address1, address2)
VALUES ('Johns Hopkins Bayview Medical Center', 'East Rashad, NC 77383-9653', 'Suite 934 51476 Oberbrunner Villages');

INSERT INTO clinics(name, address1, address2)
VALUES ('McKenzie County Healthcare Systems', 'Port Lashonda, IL 62268-3961', 'Suite 242 61696 Sipes Parks');

INSERT INTO clinics(name, address1, address2)
VALUES ('Randolph Hospital', 'Auerton, MO 38072', '010 Jeffrey Branch');

INSERT INTO drug_units(name, drug_type_id)
VALUES ('Sugar', 1);

INSERT INTO drug_units(name, drug_type_id)
VALUES ('Glycerin', 1);

INSERT INTO drug_units(name, drug_type_id)
VALUES ('Ibuclin', 2);

INSERT INTO drug_units(name, drug_type_id)
VALUES ('Nurofen', 3);

INSERT INTO genders(name)
VALUES ('MALE');

INSERT INTO genders(name)
VALUES ('FEMALE');

INSERT INTO statuses(name)
VALUES ('Screened');

INSERT INTO statuses(name)
VALUES ('Randomized');

INSERT INTO statuses(name)
VALUES ('EarlyCompleted');

INSERT INTO statuses(name)
VALUES ('Completed');

INSERT INTO patients(name, last_name, date_of_birth, initials, status_id, gender_id, drug_type_id, clinic_id)
VALUES ('Kaley', 'Bode', date '1972-01-18', 'KB', 1, 2, 1, 1);

INSERT INTO patients(name, last_name, date_of_birth, initials, status_id, gender_id, drug_type_id, clinic_id)
VALUES ('Christoper', 'Wiegand', date '1947-04-28', 'CW', 2, 1, 1, 2);

INSERT INTO patients(name, last_name, date_of_birth, initials, status_id, gender_id, drug_type_id, clinic_id)
VALUES ('Toya', 'Maggio', date '1973-06-02', 'TM', 3, 2, 2, 1);

INSERT INTO patients(name, last_name, date_of_birth, initials, status_id, gender_id, drug_type_id, clinic_id)
VALUES ('Judson', 'Green', date '1976-04-19', 'JG', 4, 1, 2, 2);

INSERT INTO patients(name, last_name, date_of_birth, initials, status_id, gender_id, drug_type_id, clinic_id)
VALUES ('Henry', 'Brown', date '1986-05-09', 'HB', 2, 1, 3, 1);

INSERT INTO patients(name, last_name, date_of_birth, initials, status_id, gender_id, drug_type_id, clinic_id)
VALUES ('Meri', 'Kihn', date '1955-07-10', 'MK', 4, 2, 3, 2);

INSERT INTO visits(last_visit, drug_unit_id, patient_id)
VALUES (date '2020-11-13', 1, 7);

INSERT INTO visits(last_visit, drug_unit_id, patient_id)
VALUES (date '2020-11-20', 1, 7);

INSERT INTO visits(last_visit, drug_unit_id, patient_id)
VALUES (date '2020-11-13', 1, 8);

INSERT INTO visits(last_visit, drug_unit_id, patient_id)
VALUES (date '2020-11-20', 1, 8);

INSERT INTO visits(last_visit, drug_unit_id, patient_id)
VALUES (date '2020-11-13', 1, 9);

INSERT INTO visits(last_visit, drug_unit_id, patient_id)
VALUES (date '2020-11-13', 1, 10);

INSERT INTO visits(last_visit, drug_unit_id, patient_id)
VALUES (date '2020-11-13', 1, 11);

INSERT INTO visits(last_visit, drug_unit_id, patient_id)
VALUES (date '2020-11-13', 1, 12);

INSERT INTO visits(last_visit, drug_unit_id, patient_id)
VALUES (date '2020-11-20', 1, 11);

INSERT INTO visits(last_visit, drug_unit_id, patient_id)
VALUES (date '2020-11-20', 1, 12);

INSERT INTO visits(last_visit, drug_unit_id, patient_id)
VALUES (date '2020-11-27', 1, 11);

INSERT INTO visits(last_visit, drug_unit_id, patient_id)
VALUES (date '2020-11-27', 1, 12);

INSERT INTO clinics_drug_units(clinic_id, drug_unit_id)
VALUES (1, 1);

INSERT INTO clinics_drug_units(clinic_id, drug_unit_id)
VALUES (1, 3);

INSERT INTO clinics_drug_units(clinic_id, drug_unit_id)
VALUES (2, 1);

INSERT INTO clinics_drug_units(clinic_id, drug_unit_id)
VALUES (2, 3);

-- Queries
-- Choose patients with only one visit.
SELECT p.id,
       name,
       last_name,
       date_of_birth,
       initials,
       status_id,
       gender_id,
       drug_type_id,
       clinic_id
FROM patients p
         JOIN visits v on p.id = v.patient_id
GROUP BY p.id
HAVING count(patient_id) = 1;

-- Choose patients who completed all visits.
SELECT * FROM patients
WHERE status_id = 4;

-- Choose clinics with patients who performed at least one visit.


-- Choose clinics with no patients.


-- Choose clinics with patients who completed all visits.


-- Show the number of visits performed at each clinic.


-- Show the number of drugs of each type.


-- Show the number of drugs of what type were dispensed.


-- Show the number of patients of each status.
SELECT statuses.name, count(*) number_of_patients FROM patients
JOIN statuses on patients.status_id = statuses.id
GROUP BY statuses.name;

-- Select drug units of the drug type that was not dispensed for any patient.


-- Add Expiration Date to the drug units and fill that column.
ALTER TABLE drug_units
    ADD expiration_date date NOT NULL DEFAULT date '2025-01-01';

-- Select the expired drug units.
SELECT * FROM drug_units
WHERE expiration_date < now();

-- Write a SQL function to count the required number of drug units to complete the rest patients' visits at the specified clinic.
-- Write a SQL function that will randomly choose the drug type and assign it to the specified patient.
-- Write a SQL function that will randomly choose available drug unit of the specified drug type and assign it to the specified patient.