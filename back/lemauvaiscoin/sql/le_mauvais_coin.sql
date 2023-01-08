-- phpMyAdmin SQL Dump
-- version 4.2.7.1
-- http://www.phpmyadmin.net
--
-- Client :  localhost
-- Généré le :  Lun 26 Septembre 2022 à 13:06
-- Version du serveur :  5.6.20-log
-- Version de PHP :  5.4.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données :  `le_mauvais_coin`
--

-- --------------------------------------------------------

--
-- Structure de la table `advertisements`
--

CREATE TABLE IF NOT EXISTS `advertisements` (
`id` int(11) NOT NULL,
  `title` varchar(150) NOT NULL,
  `description` varchar(500) NOT NULL,
  `contract_type` varchar(25) NOT NULL,
  `salary` varchar(50) DEFAULT NULL,
  `work_period` varchar(50) DEFAULT NULL,
  `creation_date` datetime NOT NULL,
  `teleworking` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `companies`
--

CREATE TABLE IF NOT EXISTS `companies` (
`id` int(11) NOT NULL,
  `name` varchar(25) NOT NULL,
  `email` varchar(50) NOT NULL,
  `join_date` date NOT NULL,
  `adress` varchar(50) NOT NULL,
  `city` varchar(30) NOT NULL,
  `zip_code` varchar(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `interaction`
--

CREATE TABLE IF NOT EXISTS `interaction` (
`id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `advertisement_id` int(11) NOT NULL,
  `message` varchar(500) NOT NULL,
  `message_date` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
`id` int(11) NOT NULL,
  `name` varchar(25) NOT NULL,
  `surname` varchar(25) NOT NULL,
  `email` varchar(50) NOT NULL,
  `role` enum('ROLE_ADMIN','ROLE_USER','ROLE_COMPANY','') NOT NULL,
  `password` varchar(70) NOT NULL,
  `join_date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- Index pour les tables exportées
--

--
-- Index pour la table `advertisements`
--
ALTER TABLE `advertisements`
 ADD PRIMARY KEY (`id`);

--
-- Index pour la table `companies`
--
ALTER TABLE `companies`
 ADD PRIMARY KEY (`id`), ADD UNIQUE KEY `email` (`email`);

--
-- Index pour la table `interaction`
--
ALTER TABLE `interaction`
 ADD PRIMARY KEY (`id`), ADD KEY `fk_interaction_user_id` (`user_id`), ADD KEY `fk_interaction_advertisement_id` (`advertisement_id`);

--
-- Index pour la table `user`
--
ALTER TABLE `user`
 ADD PRIMARY KEY (`id`), ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `advertisements`
--
ALTER TABLE `advertisements`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `companies`
--
ALTER TABLE `companies`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `interaction`
--
ALTER TABLE `interaction`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `user`
--
ALTER TABLE `user`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `interaction`
--
ALTER TABLE `interaction`
ADD CONSTRAINT `fk_interaction_advertisement_id` FOREIGN KEY (`advertisement_id`) REFERENCES `advertisements` (`id`),
ADD CONSTRAINT `fk_interaction_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
