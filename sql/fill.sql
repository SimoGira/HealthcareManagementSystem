INSERT INTO company(id, name, street, cap, city, province)
VALUES
('cp01', 'company1', 'via marconi 17', '37060', 'Verona', 'VR'),
('cp02', 'company2', 'via spoto 5', '37030', 'Vicenza', 'VI'),
('cp03', 'company3', 'via gregorio 10', '37020', 'Padova', 'PD');

-- le password andrebbero salvate in un file.conf read-only root.
INSERT INTO patient (
fiscalcode, healthcarecompany, pin, name, surname, birthdate, birthplace, province, email )
VALUES
('MTBMHM93M51D251I', 'cp01', '3450', 'antonio', 'rossi', '05/05/1995', 'Verona', 'VR', 'antonio.rossi@gmail.com'),
('ZTHQCL68S66H117Q', 'cp01', '2534', 'pietro', 'smusi', '01/10/1978', 'Belluno', 'BL', 'pietro.smusi@gmail.com'),
('VBDDFU83H26L215K', 'cp02', '3312', 'salvatore', 'aranzulla', '01/10/1988', 'Bari', 'PD', 'salvatore.aranzulla@tiscali.it'),
('TBCHML93M63F492J', 'cp02', '3700', 'ciro', 'esposito', '12/05/1977', 'Napoli', 'TV', 'ciro.esposito@rocketmail.com'),
('VFXMBY77L30A057B', 'cp03', '9321', 'valerio', 'fittizio', '22/07/1971', 'Genova', 'VR', 'crescival@temp-mail.com'),
('DGPYGG88H63E398B', 'cp03', '4132', 'dante', 'alighieri', '05/09/1951', 'Firenze', 'VE', 'crescival@temp-mail.com'),
('CCLNQL28P46B570U', 'cp01', '0792', 'gianni', 'verdi', '05/04/2001', 'Verona', 'VR', 'gianni.verdi@gmail.com'),
('ZGZMXP52L08F208P', 'cp03', '4082', 'pierciangelo', 'modesti', '01/10/1968', 'Belluno', 'BL', 'pierciangelo.modesti@gmail.com'),
('THBHLF67E23M015A', 'cp01', '4905', 'salvatore', 'salvato', '07/02/1998', 'Bari', 'PD', 'salvatore.salvato@tiscali.it'),
('YZJDFH93S68M073Z', 'cp01', '7103', 'roberta', 'robertino', '12/05/1957', 'Napoli', 'TV', 'roberta.robertino@rocketmail.com'),
('QNCMSK99R51L375J', 'cp03', '4028', 'gianna', 'fittizia', '20/12/1944', 'Genova', 'VR', 'gianna.fittizia@coldmail.com'),
('TMMDRN87R08G018F', 'cp01', '1111', 'adriano', 'tumminelli', '8/10/87', 'Oldenico', 'VR', 'adriano.tumminelli@ingegneria.com'),
('CSTKRM90T12G605P', 'cp01', '2222', 'karim', 'castagnini', '8/10/89', 'Pietra Ligure', 'VR', 'karim.castagnini@ingegneria.com'),
('GRRSMN54C28A396D', 'cp01', '3333', 'simone', 'girardi', '8/10/88', 'Arguello', 'VR', 'simone.girardi@ingegneria.com'),
('LXLXLP51A53A303K', 'cp02', '8542', 'maria', 'mariotta', '05/09/1978', 'Firenze', 'VE', 'maria.mariotta@coldmail.com');


 INSERT INTO clinic (name, company, street, cap, city, province, contractDate, description)
 VALUES
 ('GENETICA MEDICA', 'cp01', 'via alighieri 8', '02235', 'Verona', 'VR', '12/12/1990', ' '),
 ('ANGIOEDEMA EREDITARIO ED ALTRE FORME DI ANGIOEDEMA', 'cp01', 'via alighieri 8', '02235', 'Verona', 'VR', '12/12/1990',' '),
 ('IMMUNODEFICIENZE CONGENITE E ACQUISITE (DIVISIONALE)', 'cp01', 'via alighieri 8', '02235', 'Verona', 'VR', '12/12/1990',' '),
 ('PATOLOGIA RICOSTRUTTIVA DELLA MAMMELLA', 'cp01', 'via alighieri 8', '02235', 'Verona', 'VR', '12/12/1990',' '),
 ('NEOPLASIE POLMONARI E TRATTO GASTROENTERICO', 'cp02', 'via alighieri 8', '02235', 'Verona', 'VR', '12/12/1990',' '),
 ('NEOPLASIE VARIE', 'cp02', 'via alighieri 8', '02235', 'Verona', 'VR', '12/12/1990',' '),
 ('AMBULATORIO CELIACHIA', 'cp03', 'via alighieri 8', '02235', 'Verona', 'VR', '12/12/1990',' '),
 ('SPECIALISTICA ENDOCRINOLOGICA', 'cp01', 'via alighieri 8', '02235', 'Verona', 'VR', '12/12/1990',' '),
 ('MALATTIE INFETTIVE', 'cp02', 'via cipolla 31', '02235', 'Verona', 'VR', '12/12/1990',' '),
 ('OCULISTICA', 'cp01', 'via leonardo 12', '02235', 'Verona', 'VR', '13/05/1990', ' '),
 ('OCULISTICA', 'cp02', 'via mincio 6', '02235', 'Verona', 'VR', '13/05/1991', ' ');


INSERT INTO service( clinic, company, name, regime )
VALUES
('GENETICA MEDICA', 'cp01', 'visita oncologica', 'privata'),
('GENETICA MEDICA', 'cp01', 'visita geriatrica', 'privata'),
('ANGIOEDEMA EREDITARIO ED ALTRE FORME DI ANGIOEDEMA', 'cp01', 'visita angiodema', 'privata'),
('IMMUNODEFICIENZE CONGENITE E ACQUISITE (DIVISIONALE)', 'cp01', 'visita polmonare', 'privata'),
('PATOLOGIA RICOSTRUTTIVA DELLA MAMMELLA', 'cp01', 'ricostruzione tessuti', 'privata'),
('NEOPLASIE POLMONARI E TRATTO GASTROENTERICO', 'cp02', 'visita gastroenterica', 'privata'),
('NEOPLASIE POLMONARI E TRATTO GASTROENTERICO', 'cp02', 'visita polmonare', 'privata'),
('OCULISTICA', 'cp01', 'visita oculistica fotorecettori', 'privata'),
('OCULISTICA', 'cp02', 'visita oculistica generica', 'privata'),
('NEOPLASIE POLMONARI E TRATTO GASTROENTERICO', 'cp02', 'visita cervello','rimborsata dal sistema sanitario');



INSERT INTO employee(fiscalcode, employeeCode,password, name, surname, job, clinic, company)
VALUES
('RFCVCV85A42E952E', 'JOBIT0001', 'cane', 'enrico', 'gregorio', 'dermatologo', 'GENETICA MEDICA', 'cp01'),
('NDRMSN85A42E952E', 'JOBIT0002', 'gatto', 'andrea', 'masini', 'oncologo', 'GENETICA MEDICA', 'cp01'),
('MRCNRR85A42E952E', 'JOBIT0003', 'occhio', 'marco', 'nero', 'oculista', 'NEOPLASIE POLMONARI E TRATTO GASTROENTERICO', 'cp02'),
('PCCDFG85A42E952E', 'JOBIT0004', 'asd', 'piero', 'verdi', 'dentista', 'NEOPLASIE POLMONARI E TRATTO GASTROENTERICO', 'cp02'),
('CMBCRL74R10L781B', 'JOBIT0005', 'ingegneria', 'carlo', 'combi', 'oculista', 'OCULISTICA', 'cp01'),
('CSBCRL74R10L781B', 'JOBIT0006', 'pelo', 'carolo', 'casabassa', 'oculista', 'OCULISTICA', 'cp01');

INSERT INTO visit (patient, clinic, company, serviceName, doctor, date, hour, urgency, regime, result)
VALUES
('MTBMHM93M51D251I', 'GENETICA MEDICA', 'cp01', 'visita oncologica', 'RFCVCV85A42E952E', '01/01/2017', 10, 'bassa', 'privata', 'Nessuna prescrizione'),
('ZTHQCL68S66H117Q','ANGIOEDEMA EREDITARIO ED ALTRE FORME DI ANGIOEDEMA', 'cp01', 'visita angiodema', 'RFCVCV85A42E952E', '05/05/2017', 8, 'alta', 'rimborsata dal sistema sanitario', 'prescrizione cetirizina'),
('VBDDFU83H26L215K','GENETICA MEDICA', 'cp01', 'visita geriatrica', 'NDRMSN85A42E952E', '05/05/2017', 15, 'alta', 'rimborsata dal sistema sanitario', 'prescrizione esercizi fisici'),
('VBDDFU83H26L215K','IMMUNODEFICIENZE CONGENITE E ACQUISITE (DIVISIONALE)', 'cp01', 'visita polmonare', 'NDRMSN85A42E952E', '13/07/2017', 13, 'media', 'rimborsata dal sistema sanitario', 'Nessuna prescrizione'),
('VBDDFU83H26L215K','IMMUNODEFICIENZE CONGENITE E ACQUISITE (DIVISIONALE)', 'cp01', 'visita polmonare', 'NDRMSN85A42E952E', '13/07/2015', 11, 'media', 'rimborsata dal sistema sanitario', 'Nessuna prescrizione'),
('VBDDFU83H26L215K','IMMUNODEFICIENZE CONGENITE E ACQUISITE (DIVISIONALE)', 'cp01', 'visita polmonare', 'NDRMSN85A42E952E', '13/07/2012', 9, 'bassa', 'rimborsata dal sistema sanitario', 'Nessuna prescrizione'),
('TMMDRN87R08G018F','OCULISTICA', 'cp01', 'visita oculistica fotorecettori','CMBCRL74R10L781B', '11/07/2017', 11, 'alta', 'privata', NULL),
('CSTKRM90T12G605P','OCULISTICA', 'cp01', 'visita oculistica fotorecettori','CMBCRL74R10L781B', '11/07/2017', 8, 'alta', 'privata', NULL),
('GRRSMN54C28A396D','OCULISTICA', 'cp01', 'visita oculistica fotorecettori','CMBCRL74R10L781B', '11/07/2017', 12, 'alta', 'privata', NULL),
('DGPYGG88H63E398B','OCULISTICA', 'cp01', 'visita oculistica fotorecettori','CSBCRL74R10L781B', '12/01/2016', 9, 'media', 'rimborsata dal sistema sanitario', 'prescrizione collirio'),
('DGPYGG88H63E398B','OCULISTICA', 'cp01', 'visita oculistica fotorecettori','CSBCRL74R10L781B', '13/06/2016', 10, 'media', 'rimborsata dal sistema sanitario', 'prescrizione collirio'),
('DGPYGG88H63E398B','OCULISTICA', 'cp01', 'visita oculistica fotorecettori','CSBCRL74R10L781B', '14/01/2017', 15, 'media', 'privata', NULL),
('YZJDFH93S68M073Z','OCULISTICA', 'cp02', 'visita oculistica generica','CSBCRL74R10L781B', '15/06/2017', 12, 'media', 'privata', NULL),
('MTBMHM93M51D251I', 'GENETICA MEDICA', 'cp01', 'visita oncologica', 'RFCVCV85A42E952E', '01/03/2017', 8, 'bassa', 'privata', NULL),
('MTBMHM93M51D251I', 'GENETICA MEDICA', 'cp01', 'visita oncologica', 'RFCVCV85A42E952E', '01/03/2017', 9, 'bassa', 'privata', NULL),
('MTBMHM93M51D251I', 'GENETICA MEDICA', 'cp01', 'visita oncologica', 'RFCVCV85A42E952E', '01/03/2017', 10, 'bassa', 'privata', NULL),
('MTBMHM93M51D251I', 'GENETICA MEDICA', 'cp01', 'visita oncologica', 'RFCVCV85A42E952E', '01/03/2017', 11, 'bassa', 'privata', NULL),
('MTBMHM93M51D251I', 'GENETICA MEDICA', 'cp01', 'visita oncologica', 'RFCVCV85A42E952E', '01/03/2017', 12, 'bassa', 'privata', NULL),
('MTBMHM93M51D251I', 'GENETICA MEDICA', 'cp01', 'visita oncologica', 'RFCVCV85A42E952E', '01/03/2017', 13, 'bassa', 'privata', NULL),
('MTBMHM93M51D251I', 'GENETICA MEDICA', 'cp01', 'visita oncologica', 'RFCVCV85A42E952E', '01/03/2017', 14, 'bassa', 'privata', NULL),
('MTBMHM93M51D251I', 'GENETICA MEDICA', 'cp01', 'visita oncologica', 'RFCVCV85A42E952E', '01/03/2017', 15, 'bassa', 'privata', NULL);


