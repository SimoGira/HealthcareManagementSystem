-- ottiene visite relative a una certa data e ambulatorio (employee)
SELECT P.name, P.surname, V.serviceName, V.urgency, V.hour
FROM visit V JOIN paziente P ON V.patient = P.fiscalCode
WHERE V.date = ? AND V.company = ? AND V.clinic = ?;

-- PRENOTA UNa visita
INSERT INTO visit (patient, clinic, company, doctor, date, hour, urgency, regime, result)
VALUES
(?, ?, ?, ?, ?, ?, ?, ?, NULL);

-- inserimento ambulatorio
INSERT INTO clinic (name, company, street, cap, city, province, contractDate, description)
 VALUES
 (?, ?, ?, ?, ?, ?, ?, ?);
 
-- aggiornamento ambulatorio
UPDATE clinic
SET street = ?, cap = ?, city = ?, province = ?, contractDate = ?, description = ?
WHERE name = ? AND company = ?

-- selezionato un paziente ritorna tutte le visite passate
SELECT date, hour, serviceName, regime, urgency
FROM visit
WHERE patient = ? AND result NOT IS NULL

-- per ogni clinica
SELECT * FROM clinic

-- modifica il risultato di una visita (impiegato)
UPDATE visit
SET result = ?
WHERE patient = ? AND clinic = ? AND company = ? AND serviceName = ?


SELECT EXTRACT(DAY FROM(date)) as day , COUNT(*) as cnt FROM visit WHERE clinic = 'GENETICA MEDICA' AND company = 'cp01' AND NOT result IS NULL GROUP BY date;

