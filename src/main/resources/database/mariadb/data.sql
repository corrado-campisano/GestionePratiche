 
INSERT INTO `ruoli` (`ruolo`) VALUES ('ADMIN'), ('USER'), ('MANAGER'), ('PROFESSIONISTA');

INSERT INTO `utenti` VALUES 
(1,'admin','$2a$10$XM50LLW.rmHL8qCbY00WYOjTZzTU8xL.TS08qY7UpaMzzi.vmMOZS','Corrado','Campisano','ADMIN',1),
(2,'marisa','$2a$10$f3iFK1oIwXjQD6RKHB9Ur.2ZUt/jbo5v.6sYFU4BQQ/I4ud6Fo2.G','Marisa','Checchia','MANAGER',1),
(3,'user','$2a$10$QPPbn6e6sNAsyi42hR2HieI78Hu2RjdK18XA/8snrKJOyh8Am/Y7a','sola','lettura','USER',1),
(4,'marioc','$2a$10$HDo6qWkTNGcTTkoDJUO4LO5BUgw9PviZTxbAJ2VcZmGIhsISPL5/m','Mario','Civile','PROFESSIONISTA',1),
(5,'sergiop','$2a$10$U/1RFnDXUK4fEWy0.HrlIuPqWbHs348367FRpNPv/32TM.pVyTM1i','Sergio','Penale','PROFESSIONISTA',1),
(6,'pierot','$2a$10$QFNZiAouu58gBb9dHvKY4OZU7i6EnWMwRa3qM7s/EkQC4LFTy04om','Piero','Tributario','PROFESSIONISTA',1),
(7,'luigic','$2a$10$0HUo3YzPyR6jx594FsggVONPPMKVcJSeZCOeMZ99j20v79UdzF42e','Luigi','Commerciale','PROFESSIONISTA',1);

INSERT INTO `cliente` (`ragione_sociale`,`codice_fiscale`)
VALUES ('Mario Rossi', 'RSSMRO60P23T430D');
INSERT INTO `cliente` (`ragione_sociale`,`codice_fiscale`)
VALUES ('Sergio Bianchi', 'BNCSRG58D13H501F');


INSERT INTO `stato_pratica` (`stato`) VALUES ('nuova');
INSERT INTO `stato_pratica` (`stato`) VALUES ('rigettata');
INSERT INTO `stato_pratica` (`stato`) VALUES ('in lavorazione');
INSERT INTO `stato_pratica` (`stato`) VALUES ('da notificare');
INSERT INTO `stato_pratica` (`stato`) VALUES ('da depositare');
INSERT INTO `stato_pratica` (`stato`) VALUES ('in scadenza');
INSERT INTO `stato_pratica` (`stato`) VALUES ('scaduta');
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


INSERT INTO `specializzazione` (`descrizione`)
VALUES ('avvocato civilista');
INSERT INTO `specializzazione` (`descrizione`)
VALUES ('avvocato penalista');
INSERT INTO `specializzazione` (`descrizione`)
VALUES ('avvocato tributarista');
INSERT INTO `specializzazione` (`descrizione`)
VALUES ('commercialista');


INSERT INTO `utente_specializzazione` VALUES (4,1),(5,2),(6,3),(7,4);


INSERT INTO `pratica` VALUES (1,'2022-01','cartella per TARI',1,1,1),(2,'2022-02','cartella per IMU',2,2,1);

