INSERT INTO company(id, name, street, cap, city, province) 
VALUES 
('cp01', 'company1', 'via marconi 17', '37060', 'Verona', 'VR'),
('cp02', 'company2', 'via spoto 5', '37030', 'Vicenza', 'VI'),
('cp03', 'company3', 'via gregorio 10', '37020', 'Padova', 'PA');

INSERT INTO patient (
fiscalcode, healthcarecompany, name, surname, birthdate, birthplace, province, email )
VALUES
('MTBMHM93M51D251I', 'cp01', 'antonio', 'rossi', '05/05/1995', 'Verona', 'VR', 'antonio.rossi@gmail.com'),
('ZTHQCL68S66H117Q', 'cp01', 'pietro', 'smusi', '01/10/1978', 'Belluno', 'BL', 'pietro.smusi@gmail.com'),
('VBDDFU83H26L215K', 'cp02', 'salvatore', 'aranzulla', '01/10/1988', 'Bari', 'PA', 'salvatore.aranzulla@tiscali.it'),
('TBCHML93M63F492J', 'cp02', 'ciro', 'esposito', '12/05/1977', 'Napoli', 'TR', 'ciro.esposito@rocketmail.com'),
('VFXMBY77L30A057B', 'cp03', 'valerio', 'fittizio', '22/07/1971', 'Genova', 'VR', 'crescival@temp-mail.com'),
('DGPYGG88H63E398B', 'cp03', 'dante', 'alighieri', '05/09/1951', 'Firenze', 'VE', 'crescival@temp-mail.com');
 

 INSERT INTO ambulatory (name, company, street, cap, city, province, contractDate, description)
 VALUES
 ('GENETICA MEDICA', 'cp01', 'via alighieri 8', '02235', 'Verona', 'VR', '12/12/1990', ' '),
 ('ANGIOEDEMA EREDITARIO ED ALTRE FORME DI ANGIOEDEMA', 'cp01', 'via alighieri 8', '02235', 'Verona', 'VR', '12/12/1990',' '),
 ('IMMUNODEFICIENZE CONGENITE E ACQUISITE (DIVISIONALE)', 'cp01', 'via alighieri 8', '02235', 'Verona', 'VR', '12/12/1990',' '),
 ('PATOLOGIA RICOSTRUTTIVA DELLA MAMMELLA', 'cp01', 'via alighieri 8', '02235', 'Verona', 'VR', '12/12/1990',' '),
 ('NEOPLASIE POLMONARI E TRATTO GASTROENTERICO', 'cp02', 'via alighieri 8', '02235', 'Verona', 'VR', '12/12/1990',' '),
 ('NEOPLASIE VARIE', 'cp02', 'via alighieri 8', '02235', 'Verona', 'VR', '12/12/1990',' '),
 ('AMBULATORIO CELIACHIA', 'cp03', 'via alighieri 8', '02235', 'Verona', 'VR', '12/12/1990',' ');
 
INSERT INTO services( ambulatory, company, name )
VALUES
('GENETICA MEDICA', 'cp01', 'visita oncologica'),
('GENETICA MEDICA', 'cp01', 'visita geriatrica'),
('ANGIOEDEMA EREDITARIO ED ALTRE FORME DI ANGIOEDEMA', 'cp02', 'visita angiodema');

 