
INSERT INTO `users` (`username`,`password`,`role`,`enabled`)
VALUES ('namhm',
'$2a$10$XptfskLsT1l/bRTLRiiCgejHqOpgXFreUnNUa35gJdCr2v2QbVFzu',
'ROLE_USER', 1);
 
INSERT INTO `users` (`username`,`password`,`role`,`enabled`)
VALUES ('admin',
'$2a$10$zxvEq8XzYEYtNjbkRsJEbukHeRx3XS6MDXHMu8cNuNsRfZJWwswDy',
'ROLE_ADMIN', 1);




INSERT INTO `cliente` (`ragione_sociale`,`codice_fiscale`)
VALUES ('Mario Rossi', 'MRORSS60P23T430D');

INSERT INTO `cliente` (`ragione_sociale`,`codice_fiscale`)
VALUES ('Sergio Bianchi', 'SRGBNC58D13H501F');




INSERT INTO `professionista` (`nome`,`cognome`)
VALUES ('Mario', 'Civile');

INSERT INTO `professionista` (`nome`,`cognome`)
VALUES ('Sergio', 'Penale');

INSERT INTO `professionista` (`nome`,`cognome`)
VALUES ('Marco', 'Tributario');

INSERT INTO `professionista` (`nome`,`cognome`)
VALUES ('Antonio', 'Commerciale');




INSERT INTO `specializzazione` (`descrizione`)
VALUES ('avvocato civilista');

INSERT INTO `specializzazione` (`descrizione`)
VALUES ('avvocato penalista');

INSERT INTO `specializzazione` (`descrizione`)
VALUES ('avvocato tributarista');

INSERT INTO `specializzazione` (`descrizione`)
VALUES ('commercialista');





