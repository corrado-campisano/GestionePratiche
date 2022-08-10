
INSERT INTO `users` (`username`,`password`,`role`,`enabled`)
VALUES ('marisa',
'$2a$10$f3iFK1oIwXjQD6RKHB9Ur.2ZUt/jbo5v.6sYFU4BQQ/I4ud6Fo2.G',
'ROLE_USER', 1);
 
INSERT INTO `users` (`username`,`password`,`role`,`enabled`)
VALUES ('admin',
'$2a$10$XM50LLW.rmHL8qCbY00WYOjTZzTU8xL.TS08qY7UpaMzzi.vmMOZS',
'ROLE_ADMIN', 1);




INSERT INTO `cliente` (`ragione_sociale`,`codice_fiscale`)
VALUES ('Mario Rossi', 'RSSMRO60P23T430D');

INSERT INTO `cliente` (`ragione_sociale`,`codice_fiscale`)
VALUES ('Sergio Bianchi', 'BNCSRG58D13H501F');


INSERT INTO `stato_pratica` (`stato`) VALUES ('nuova');
INSERT INTO `stato_pratica` (`stato`) VALUES ('in lavorazione');
INSERT INTO `stato_pratica` (`stato`) VALUES ('da depositare');
INSERT INTO `stato_pratica` (`stato`) VALUES ('in scadenza');
INSERT INTO `stato_pratica` (`stato`) VALUES ('in dibattimento');
INSERT INTO `stato_pratica` (`stato`) VALUES ('da fatturare');
INSERT INTO `stato_pratica` (`stato`) VALUES ('evasa');

INSERT INTO `tipo_pratica` (`tipo`) VALUES ('tributario primo grado');
INSERT INTO `tipo_pratica` (`tipo`) VALUES ('tributario appello');
INSERT INTO `tipo_pratica` (`tipo`) VALUES ('tributario cassazione');
INSERT INTO `tipo_pratica` (`tipo`) VALUES ('civile primo grado');
INSERT INTO `tipo_pratica` (`tipo`) VALUES ('civile appello');
INSERT INTO `tipo_pratica` (`tipo`) VALUES ('civile cassazione');
INSERT INTO `tipo_pratica` (`tipo`) VALUES ('penale primo grado');
INSERT INTO `tipo_pratica` (`tipo`) VALUES ('penale appello');
INSERT INTO `tipo_pratica` (`tipo`) VALUES ('penale cassazione');


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




INSERT INTO `professionista_specializzazione` (`professionista_id`,`specializzazione_id`)
VALUES (1, 1);

INSERT INTO `professionista_specializzazione` (`professionista_id`,`specializzazione_id`)
VALUES (2, 2);

INSERT INTO `professionista_specializzazione` (`professionista_id`,`specializzazione_id`)
VALUES (3, 3);

INSERT INTO `professionista_specializzazione` (`professionista_id`,`specializzazione_id`)
VALUES (4, 4);

