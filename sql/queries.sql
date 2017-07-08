-- ottiene visite relative a una certa data e ambulatorio (employee)
SELECT P.name, P.surname, V.serviceName, V.urgency, V.hour
FROM visit V JOIN paziente P ON V.patient = P.fiscalCode
WHERE V.date = ? AND V.company = ? AND V.ambulatory = ?;

-- PRENOTA UNa visita
INSERT INTO visit (patient, ambulatory, company, doctor, date, hour, urgency, regime, result)
VALUES
(?, ?, ?, ?, ?, ?, ?, ?, NULL);

-- inserimento ambulatorio
INSERT INTO ambulatory (name, company, street, cap, city, province, contractDate, description)
 VALUES
 (?, ?, ?, ?, ?, ?, ?, ?);
 
-- aggiornamento ambulatorio
UPDATE ambulatory
SET street = ?, cap = ?, city = ?, province = ?, contractDate = ?, description = ?
WHERE name = ? AND company = ?

-- selezionato un paziente ritorna tutte le visite passate
SELECT date, hour, serviceName, regime, urgency
FROM visit
WHERE patient = ? AND result NOT IS NULL

-- per ogni clinica
SELECT * FROM ambulatory

-- modifica il risultato di una visita (impiegato)
UPDATE visit
SET result = ?
WHERE patient = ? AND ambulatory = ? AND company = ? AND serviceName = ?


