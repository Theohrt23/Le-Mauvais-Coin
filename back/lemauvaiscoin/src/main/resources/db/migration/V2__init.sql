-- create users

INSERT INTO `le_mauvais_coin`.`user` (`role`, `id`, `join_date`, `mail`, `name`, `password`, `surname`, `username`, `address`, `city`, `zip_code`)
VALUES ('ROLE_USER', NULL, '2022-10-14', 'theoo.huret@gmail.com', 'Huret', '$2a$10$3TWRkMJ092hw/sXx./9cW.dWI8DV.SPeFv6UXrojJPhH4KB0dFI1e', 'Théo', 'stompar', NULL, NULL, NULL);

INSERT INTO `le_mauvais_coin`.`user` (`role`, `id`, `join_date`, `mail`, `name`, `password`, `surname`, `username`, `address`, `city`, `zip_code`)
VALUES ('ROLE_USER', NULL, '2022-10-14', 'lucas.zielinski@gmail.com', 'Zielinski', '$2a$10$3TWRkMJ092hw/sXx./9cW.dWI8DV.SPeFv6UXrojJPhH4KB0dFI1e', 'Lucas', 'lucasz', NULL, NULL, NULL);

INSERT INTO `le_mauvais_coin`.`user` (`role`, `id`, `join_date`, `mail`, `name`, `password`, `surname`, `username`, `address`, `city`, `zip_code`)
VALUES ('ROLE_USER', NULL, '2022-10-14', 'pierre.menes@gmail.com', 'Menes', '$2a$10$3TWRkMJ092hw/sXx./9cW.dWI8DV.SPeFv6UXrojJPhH4KB0dFI1e', 'Pierre', 'pierrot', NULL, NULL, NULL);

INSERT INTO `le_mauvais_coin`.`user` (`role`, `id`, `join_date`, `mail`, `name`, `password`, `surname`, `username`, `address`, `city`, `zip_code`)
VALUES ('ROLE_USER', NULL, '2022-10-14', 'louis.dubois@gmail.com', 'Dubois', '$2a$10$3TWRkMJ092hw/sXx./9cW.dWI8DV.SPeFv6UXrojJPhH4KB0dFI1e', 'Louis', 'loulou', NULL, NULL, NULL);

-- create admin

INSERT INTO `le_mauvais_coin`.`user` (`role`, `id`, `join_date`, `mail`, `name`, `password`, `surname`, `username`, `address`, `city`, `zip_code`)
VALUES ('ROLE_ADMIN', NULL, '2022-10-14', 'admin.admin@gmail.com', 'Admin', '$2a$10$q2nuDB2jjgL3GZh/yvds6.P0Fsp4HkgTj9i0HyPKywT4g4WWOpgwi', 'Admin', 'jesuisadmin', NULL, NULL, NULL);

-- create company

INSERT INTO `le_mauvais_coin`.`user` (`role`, `id`, `join_date`, `mail`, `name`, `password`, `surname`, `username`, `address`, `city`, `zip_code`)
VALUES ('ROLE_COMPANY', NULL, '2022-10-14', 'theo.huret@gmail.com', 'HURET_ENTREPRISE', '$2a$10$3TWRkMJ092hw/sXx./9cW.dWI8DV.SPeFv6UXrojJPhH4KB0dFI1e', 'Théo', 'theo_entreprise', '1 place Geneviere', 'Lille', 59000);

INSERT INTO `le_mauvais_coin`.`user` (`role`, `id`, `join_date`, `mail`, `name`, `password`, `surname`, `username`, `address`, `city`, `zip_code`)
VALUES ('ROLE_COMPANY', NULL, '2022-10-14', 'lucaszielinskibts@gmail.com', 'ZIELINSKI_ENTREPRISE', '$2a$10$3TWRkMJ092hw/sXx./9cW.dWI8DV.SPeFv6UXrojJPhH4KB0dFI1e', 'Lucas', 'lucas_entreprise', '7 rue de lesperance' , 'Lille', 59000);

INSERT INTO `le_mauvais_coin`.`user` (`role`, `id`, `join_date`, `mail`, `name`, `password`, `surname`, `username`, `address`, `city`, `zip_code`)
VALUES ('ROLE_COMPANY', NULL, '2022-10-14', 'leroy.merlin@gmail.com', 'LEROY MERLIN', '$2a$10$3TWRkMJ092hw/sXx./9cW.dWI8DV.SPeFv6UXrojJPhH4KB0dFI1e', 'Merlin', 'merlin', '8 rue general ford', 'Lille', 59000);

INSERT INTO `le_mauvais_coin`.`user` (`role`, `id`, `join_date`, `mail`, `name`, `password`, `surname`, `username`, `address`, `city`, `zip_code`)
VALUES ('ROLE_COMPANY', NULL, '2022-10-14', 'ikea.ikea@gmail.com', 'IKEA', '$2a$10$3TWRkMJ092hw/sXx./9cW.dWI8DV.SPeFv6UXrojJPhH4KB0dFI1e', 'Ikea', 'ikea', '36 rue des Jardins', 'Lille', 59000);

-- create offers

INSERT INTO `le_mauvais_coin`.`offer` (`id`, `contract_type`, `creation_date`, `description`, `salary`, `teleworking`, `title`, `work_period`)
VALUES (NULL, 'CDD', '2022-10-14', 'Recherche stagiaire dev web', '300 euros net', b'000', 'Stage dev web', '3 mois');

INSERT INTO `le_mauvais_coin`.`offer` (`id`, `contract_type`, `creation_date`, `description`, `salary`, `teleworking`, `title`, `work_period`)
VALUES (NULL, 'CDD', '2022-10-14', 'Recherche stagiaire design meuble', '900 euros net', b'000', 'Stage design meuble', '7 mois');

INSERT INTO `le_mauvais_coin`.`offer` (`id`, `contract_type`, `creation_date`, `description`, `salary`, `teleworking`, `title`, `work_period`)
VALUES (NULL, 'CDI', '2022-10-14', 'conseiller de vente avec compétences', '3500 euros net', b'000', 'Poste de manager', '5j/semaine');

INSERT INTO `le_mauvais_coin`.`offer` (`id`, `contract_type`, `creation_date`, `description`, `salary`, `teleworking`, `title`, `work_period`)
VALUES (NULL, 'CDD', '2022-10-14', 'Recherche développeur compétent en Cobol', '3000 euros net', b'000', 'Poste développeur fullstack', '5j/semaine');

INSERT INTO `le_mauvais_coin`.`offer` (`id`, `contract_type`, `creation_date`, `description`, `salary`, `teleworking`, `title`, `work_period`)
VALUES (NULL, 'CDI', '2022-10-14', 'stagiaire angular/spring', '600 euros net', b'000', 'Stage dev web fullstack', '4 mois');

INSERT INTO `le_mauvais_coin`.`offer` (`id`, `contract_type`, `creation_date`, `description`, `salary`, `teleworking`, `title`, `work_period`)
VALUES (NULL, 'CDD', '2022-10-14', 'Recherche une personne qualifier niveau UX pour réaliser des maquettes de projet', '2500 euros net', b'000', 'Poste UX', '3j/semaine');

-- link offer to is company 6 7 8 9

INSERT INTO `le_mauvais_coin`.`fp_offer_company_assoc` (`company_id`, `offer_id`) VALUES ('6', '1');
INSERT INTO `le_mauvais_coin`.`fp_offer_company_assoc` (`company_id`, `offer_id`) VALUES ('9', '2');
INSERT INTO `le_mauvais_coin`.`fp_offer_company_assoc` (`company_id`, `offer_id`) VALUES ('8', '3');
INSERT INTO `le_mauvais_coin`.`fp_offer_company_assoc` (`company_id`, `offer_id`) VALUES ('6', '4');
INSERT INTO `le_mauvais_coin`.`fp_offer_company_assoc` (`company_id`, `offer_id`) VALUES ('7', '5');
INSERT INTO `le_mauvais_coin`.`fp_offer_company_assoc` (`company_id`, `offer_id`) VALUES ('7', '6');

-- create applications

INSERT INTO `le_mauvais_coin`.`application` (`id`, `body`, `mail`, `name`, `offer_id`, `subject`, `surname`, `user_id`) VALUES (NULL, 'This is a body', '', NULL, '1', 'This is a subject', NULL, '1');
INSERT INTO `le_mauvais_coin`.`application` (`id`, `body`, `mail`, `name`, `offer_id`, `subject`, `surname`, `user_id`) VALUES (NULL, 'This is an other body', 'pierre.lucas@gmail.com', 'Thomas', '2', 'This is an other subject', 'Pierre', NULL);
