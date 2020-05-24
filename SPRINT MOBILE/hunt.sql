-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: May 24, 2020 at 06:13 AM
-- Server version: 10.4.10-MariaDB
-- PHP Version: 7.3.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `hunt`
--

-- --------------------------------------------------------

--
-- Table structure for table `categorie`
--

DROP TABLE IF EXISTS `categorie`;
CREATE TABLE IF NOT EXISTS `categorie` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `description` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `image` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=450 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `categorie`
--

INSERT INTO `categorie` (`id`, `nom`, `description`, `image`) VALUES
(1, 'ookaaaaa', 'ooo', '4cca8bde8d512ef9334bce601598cf82.jpeg'),
(2, 'azerty', 'azerty', 'rafik.jpg'),
(3, 'dzadaz', 'dzdaz', '22222'),
(447, 'hey', 'aaa', 'aaa'),
(448, 'hey', 'aaa', 'aaa');

-- --------------------------------------------------------

--
-- Table structure for table `commandes`
--

DROP TABLE IF EXISTS `commandes`;
CREATE TABLE IF NOT EXISTS `commandes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `utilisateur_id` int(11) DEFAULT NULL,
  `valider` tinyint(1) NOT NULL,
  `date` datetime NOT NULL,
  `reference` int(11) NOT NULL,
  `commande` longtext COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '(DC2Type:array)',
  PRIMARY KEY (`id`),
  KEY `IDX_35D4282CFB88E14F` (`utilisateur_id`)
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `commandes`
--

INSERT INTO `commandes` (`id`, `utilisateur_id`, `valider`, `date`, `reference`, `commande`) VALUES
(55, 2, 1, '2020-05-24 05:39:46', 89100987, 'a:7:{s:3:\"tva\";a:1:{s:2:\"%1\";d:0;}s:7:\"produit\";a:2:{i:1;a:5:{s:2:\"id\";i:9;s:9:\"reference\";s:2:\"ab\";s:8:\"quantite\";s:1:\"2\";s:6:\"prixHT\";d:21;s:7:\"prixTTC\";d:21;}i:2;a:5:{s:2:\"id\";i:10;s:9:\"reference\";s:12:\"produit test\";s:8:\"quantite\";s:1:\"2\";s:6:\"prixHT\";d:999999;s:7:\"prixTTC\";d:999999;}}s:9:\"livraison\";a:8:{s:6:\"prenom\";s:4:\"test\";s:3:\"nom\";s:4:\"test\";s:9:\"telephone\";s:4:\"3333\";s:7:\"adresse\";s:2:\"st\";s:2:\"cp\";s:4:\"test\";s:5:\"ville\";s:4:\"test\";s:4:\"pays\";s:2:\"ss\";s:10:\"complement\";s:3:\"sss\";}s:11:\"facturation\";a:8:{s:6:\"prenom\";s:4:\"test\";s:3:\"nom\";s:4:\"test\";s:9:\"telephone\";s:4:\"3333\";s:7:\"adresse\";s:2:\"st\";s:2:\"cp\";s:4:\"test\";s:5:\"ville\";s:4:\"test\";s:4:\"pays\";s:2:\"ss\";s:10:\"complement\";s:3:\"sss\";}s:6:\"prixHT\";d:1000020;s:7:\"prixTTC\";d:1000020;s:5:\"token\";s:40:\"58f995582826e0e74169d0d33010fdbf468724b2\";}'),
(56, 2, 1, '2020-05-24 05:41:29', 30782072, 'a:7:{s:3:\"tva\";a:1:{s:2:\"%1\";d:0;}s:7:\"produit\";a:1:{i:1;a:5:{s:2:\"id\";i:9;s:9:\"reference\";s:2:\"ab\";s:8:\"quantite\";s:1:\"1\";s:6:\"prixHT\";d:21;s:7:\"prixTTC\";d:21;}}s:9:\"livraison\";a:8:{s:6:\"prenom\";s:4:\"test\";s:3:\"nom\";s:4:\"test\";s:9:\"telephone\";s:4:\"3333\";s:7:\"adresse\";s:2:\"st\";s:2:\"cp\";s:4:\"test\";s:5:\"ville\";s:4:\"test\";s:4:\"pays\";s:2:\"ss\";s:10:\"complement\";s:3:\"sss\";}s:11:\"facturation\";a:8:{s:6:\"prenom\";s:4:\"test\";s:3:\"nom\";s:4:\"test\";s:9:\"telephone\";s:4:\"3333\";s:7:\"adresse\";s:2:\"st\";s:2:\"cp\";s:4:\"test\";s:5:\"ville\";s:4:\"test\";s:4:\"pays\";s:2:\"ss\";s:10:\"complement\";s:3:\"sss\";}s:6:\"prixHT\";d:21;s:7:\"prixTTC\";d:21;s:5:\"token\";s:40:\"58f995582826e0e74169d0d33010fdbf468724b2\";}'),
(57, 2, 1, '2020-05-24 05:46:04', 76070527, 'a:7:{s:3:\"tva\";a:1:{s:2:\"%1\";d:0;}s:7:\"produit\";a:1:{i:1;a:5:{s:2:\"id\";i:10;s:9:\"reference\";s:12:\"produit test\";s:8:\"quantite\";s:1:\"1\";s:6:\"prixHT\";d:999999;s:7:\"prixTTC\";d:999999;}}s:9:\"livraison\";a:8:{s:6:\"prenom\";s:4:\"test\";s:3:\"nom\";s:4:\"test\";s:9:\"telephone\";s:4:\"3333\";s:7:\"adresse\";s:2:\"st\";s:2:\"cp\";s:4:\"test\";s:5:\"ville\";s:4:\"test\";s:4:\"pays\";s:2:\"ss\";s:10:\"complement\";s:3:\"sss\";}s:11:\"facturation\";a:8:{s:6:\"prenom\";s:4:\"test\";s:3:\"nom\";s:4:\"test\";s:9:\"telephone\";s:4:\"3333\";s:7:\"adresse\";s:2:\"st\";s:2:\"cp\";s:4:\"test\";s:5:\"ville\";s:4:\"test\";s:4:\"pays\";s:2:\"ss\";s:10:\"complement\";s:3:\"sss\";}s:6:\"prixHT\";d:999999;s:7:\"prixTTC\";d:999999;s:5:\"token\";s:40:\"58f995582826e0e74169d0d33010fdbf468724b2\";}'),
(58, 2, 1, '2020-05-24 05:47:57', 19345657, 'a:7:{s:3:\"tva\";a:1:{s:2:\"%1\";d:0;}s:7:\"produit\";a:1:{i:1;a:5:{s:2:\"id\";i:9;s:9:\"reference\";s:2:\"ab\";s:8:\"quantite\";s:1:\"1\";s:6:\"prixHT\";d:21;s:7:\"prixTTC\";d:21;}}s:9:\"livraison\";a:8:{s:6:\"prenom\";s:4:\"test\";s:3:\"nom\";s:4:\"test\";s:9:\"telephone\";s:4:\"3333\";s:7:\"adresse\";s:2:\"st\";s:2:\"cp\";s:4:\"test\";s:5:\"ville\";s:4:\"test\";s:4:\"pays\";s:2:\"ss\";s:10:\"complement\";s:3:\"sss\";}s:11:\"facturation\";a:8:{s:6:\"prenom\";s:4:\"test\";s:3:\"nom\";s:4:\"test\";s:9:\"telephone\";s:4:\"3333\";s:7:\"adresse\";s:2:\"st\";s:2:\"cp\";s:4:\"test\";s:5:\"ville\";s:4:\"test\";s:4:\"pays\";s:2:\"ss\";s:10:\"complement\";s:3:\"sss\";}s:6:\"prixHT\";d:21;s:7:\"prixTTC\";d:21;s:5:\"token\";s:40:\"58f995582826e0e74169d0d33010fdbf468724b2\";}');

-- --------------------------------------------------------

--
-- Table structure for table `commentaire`
--

DROP TABLE IF EXISTS `commentaire`;
CREATE TABLE IF NOT EXISTS `commentaire` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_publication` int(11) DEFAULT NULL,
  `contenu` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `dateComnt` datetime NOT NULL,
  `id_User` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_67F068BCA6816575` (`id_User`),
  KEY `IDX_67F068BCB72EAA8E` (`id_publication`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `commentaire`
--

INSERT INTO `commentaire` (`id`, `id_publication`, `contenu`, `dateComnt`, `id_User`) VALUES
(15, 7, 'zertyui', '2020-02-27 13:53:44', 2),
(23, 14, '🤑🤑🤑🤑🤑🤑🤑😞😞😞😞okokokoko', '2020-03-06 16:38:04', 6),
(24, 14, 'okokok😍😍🤑🤑🤑🤑', '2020-03-06 16:38:27', 6),
(25, 15, 'ahla 3aslema merci boucou😍😍😍 oui cest clair parfait \r\n', '2020-04-11 00:31:46', 3),
(26, 41, 'commentaire', '2020-05-18 09:36:11', 2),
(30, 41, 'aaaa', '2020-05-24 03:09:01', 2),
(35, 44, 'xxxx', '2020-05-24 03:30:15', 2),
(36, 44, 'mhm', '2020-05-24 03:31:22', 2),
(37, 44, 'dzadaz', '2020-05-24 03:57:21', 2);

-- --------------------------------------------------------

--
-- Table structure for table `comments`
--

DROP TABLE IF EXISTS `comments`;
CREATE TABLE IF NOT EXISTS `comments` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idevent` int(11) NOT NULL,
  `comment` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `com_produit`
--

DROP TABLE IF EXISTS `com_produit`;
CREATE TABLE IF NOT EXISTS `com_produit` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `contenu` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `idProduit` int(11) DEFAULT NULL,
  `idClient` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_17BEAAE8391C87D5` (`idProduit`),
  KEY `IDX_17BEAAE8A455ACCF` (`idClient`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `espece`
--

DROP TABLE IF EXISTS `espece`;
CREATE TABLE IF NOT EXISTS `espece` (
  `idEspece` int(11) NOT NULL AUTO_INCREMENT,
  `nomEspece` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `descriptionEspece` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `image` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `poids` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `type` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `zone` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `ville` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `idS` int(11) DEFAULT NULL,
  PRIMARY KEY (`idEspece`),
  KEY `IDX_1A2A1B14BB48750` (`idS`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `espece`
--

INSERT INTO `espece` (`idEspece`, `nomEspece`, `descriptionEspece`, `image`, `poids`, `type`, `zone`, `ville`, `idS`) VALUES
(16, 'sanglier', 'sanglier petit', '5abff9055a6dbab219ff4bc38f508537.jpeg', '25 kg', 'sanglier', 'motagne', 'kef', 5),
(17, 'sanglier', 'sanglier grand', '5abff9055a6dbab219ff4bc38f508537.jpeg', '45 kg', 'sanglier', 'montagne', 'jandouba', 4),
(18, 'poisson', 'poisson rouge', 'whiteHeart.jpg', '100 g', 'poisson', 'a la ligne', 'nabeul', 5),
(19, 'ha', 'a dzadaz', '5abff9055a6dbab219ff4bc38f508537.jpeg', '15', 'sza', 'dzdaz', 'dzda', 2),
(20, 'ha', 'ha', '5abff9055a6dbab219ff4bc38f508537.jpeg', 'ha', 'ha', 'ha', 'ha', 2),
(21, 'ha', 'ha', '5abff9055a6dbab219ff4bc38f508537.jpeg', 'ha', 'ha', 'haaa', 'ha', 2),
(29, 'a', 'hello', 'whiteHeart.jpg', '88', 'hello', 'hello', 'hello', 2),
(30, 'espece', 'espece', '5ec254f3c7610profImg.png', '22', 'espece', 'espece', 'espece', 2),
(36, 'xx', 'xx', '5ec9ce33649f2espImg.png', '77', 'xx', 'xx', 'xx', 19);

-- --------------------------------------------------------

--
-- Table structure for table `evenement`
--

DROP TABLE IF EXISTS `evenement`;
CREATE TABLE IF NOT EXISTS `evenement` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `type` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `prix` double NOT NULL,
  `nbrplace` int(11) NOT NULL,
  `date` datetime NOT NULL,
  `place` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `description` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `duree` int(11) NOT NULL,
  `image` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `evenement`
--

INSERT INTO `evenement` (`id`, `nom`, `type`, `prix`, `nbrplace`, `date`, `place`, `description`, `duree`, `image`) VALUES
(2, 'aa', 'Formation', 11, 101, '2020-02-27 00:00:00', 'kkkk', 'aaa', 10, 'whiteHeart.jpg'),
(5, 'dzadaz', 'Formation', 12, 15, '2015-01-01 00:00:00', 'pkpfedze', '>dzakdazidjoazdazpjdazojdopazkda', 212, '5ec02e9241240profImg.png'),
(9, 'adazda', 'dzdaz', 2, 1, '2020-05-27 00:00:00', 'dzdzad5za1d5za', '5dz1ad5za1z', 15, 'whiteHeart.jpg'),
(10, 'adazda', 'dzdaz', 2, 9, '2020-05-27 00:00:00', 'dzdzad5za1d5za', '5dz1ad5za1z', 15, '5ec02e9241240profImg.png'),
(11, 'dzdza', 'Type', 14, 13, '2020-05-04 00:00:00', 'czadaz', 'ii', 14, 'whiteHeart.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `feedback`
--

DROP TABLE IF EXISTS `feedback`;
CREATE TABLE IF NOT EXISTS `feedback` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `rate` int(11) NOT NULL,
  `date` datetime NOT NULL,
  `idU` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_D2294458A2D72265` (`idU`)
) ENGINE=InnoDB AUTO_INCREMENT=131 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `feedback`
--

INSERT INTO `feedback` (`id`, `description`, `rate`, `date`, `idU`) VALUES
(130, 'test', 4, '2020-05-24 04:02:48', 2);

-- --------------------------------------------------------

--
-- Table structure for table `inscription`
--

DROP TABLE IF EXISTS `inscription`;
CREATE TABLE IF NOT EXISTS `inscription` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idevent` int(11) DEFAULT NULL,
  `iduser` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_5E90F6D6EDAB66BE` (`idevent`),
  KEY `IDX_5E90F6D65E5C27E9` (`iduser`)
) ENGINE=InnoDB AUTO_INCREMENT=89 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `inscription`
--

INSERT INTO `inscription` (`id`, `idevent`, `iduser`) VALUES
(80, 9, 88),
(83, 10, 2),
(87, 2, 2);

-- --------------------------------------------------------

--
-- Table structure for table `mail`
--

DROP TABLE IF EXISTS `mail`;
CREATE TABLE IF NOT EXISTS `mail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `prenom` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `phone` int(11) NOT NULL,
  `from` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `text` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `notifiable_entity`
--

DROP TABLE IF EXISTS `notifiable_entity`;
CREATE TABLE IF NOT EXISTS `notifiable_entity` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `identifier` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `class` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `notifiable_entity`
--

INSERT INTO `notifiable_entity` (`id`, `identifier`, `class`) VALUES
(1, '2', 'MainBundle\\Entity\\User'),
(2, '4', 'MainBundle\\Entity\\User'),
(3, '6', 'MainBundle\\Entity\\User'),
(4, '3', 'MainBundle\\Entity\\User');

-- --------------------------------------------------------

--
-- Table structure for table `notifiable_notification`
--

DROP TABLE IF EXISTS `notifiable_notification`;
CREATE TABLE IF NOT EXISTS `notifiable_notification` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `notification_id` int(11) DEFAULT NULL,
  `notifiable_entity_id` int(11) DEFAULT NULL,
  `seen` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_ADCFE0FAEF1A9D84` (`notification_id`),
  KEY `IDX_ADCFE0FAC3A0A4F8` (`notifiable_entity_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `notifiable_notification`
--

INSERT INTO `notifiable_notification` (`id`, `notification_id`, `notifiable_entity_id`, `seen`) VALUES
(1, 1, 1, 0),
(2, 2, 1, 0),
(3, 3, 2, 0),
(4, 4, 2, 0),
(5, 5, 2, 0),
(6, 6, 2, 0),
(7, 7, 2, 0),
(8, 8, 2, 0),
(9, 9, 3, 0),
(10, 10, 4, 0);

-- --------------------------------------------------------

--
-- Table structure for table `notification`
--

DROP TABLE IF EXISTS `notification`;
CREATE TABLE IF NOT EXISTS `notification` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` datetime NOT NULL,
  `subject` varchar(4000) COLLATE utf8mb4_unicode_ci NOT NULL,
  `message` varchar(4000) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `link` varchar(4000) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `notification`
--

INSERT INTO `notification` (`id`, `date`, `subject`, `message`, `link`) VALUES
(1, '2020-02-27 13:01:47', 'nouvelle publication', 'This a notification.', NULL),
(2, '2020-02-27 13:52:51', 'nouvelle publication', 'This a notification.', NULL),
(3, '2020-02-29 14:21:14', 'nouvelle publication', 'This a notification.', NULL),
(4, '2020-02-29 15:02:03', 'nouvelle publication', 'This a notification.', NULL),
(5, '2020-02-29 19:19:09', 'nouvelle publication', 'This a notification.', NULL),
(6, '2020-02-29 19:19:38', 'nouvelle publication', 'This a notification.', NULL),
(7, '2020-02-29 19:19:48', 'nouvelle publication', 'This a notification.', NULL),
(8, '2020-02-29 19:35:07', 'nouvelle publication', 'This a notification.', NULL),
(9, '2020-03-06 16:37:44', 'nouvelle publication', 'This a notification.', NULL),
(10, '2020-04-11 00:31:16', 'nouvelle publication', 'This a notification.', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `produit`
--

DROP TABLE IF EXISTS `produit`;
CREATE TABLE IF NOT EXISTS `produit` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `categorie` int(11) DEFAULT NULL,
  `nom` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `quantite` int(11) NOT NULL,
  `description` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `prix` double NOT NULL,
  `idFournisseur` int(11) DEFAULT NULL,
  `etatPromo` int(11) NOT NULL,
  `garantie` int(11) NOT NULL,
  `image` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `tvaId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_29A5EC27497DD634` (`categorie`),
  KEY `IDX_29A5EC27A655BE48` (`tvaId`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `produit`
--

INSERT INTO `produit` (`id`, `categorie`, `nom`, `quantite`, `description`, `prix`, `idFournisseur`, `etatPromo`, `garantie`, `image`, `tvaId`) VALUES
(9, 447, 'ab', 6, 'dza', 21, 0, 0, 2, 'icon.png', 1),
(10, 2, 'produit test', 5, 'description produit test', 999999, 0, 1, 99999, 'whiteHeart.jpg', 1),
(11, 2, 'produit test 2', 14, 'description produit test 2', 999999, 0, 0, 99999, 'icon.png', 1);

-- --------------------------------------------------------

--
-- Table structure for table `promotion`
--

DROP TABLE IF EXISTS `promotion`;
CREATE TABLE IF NOT EXISTS `promotion` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dateFin` datetime NOT NULL DEFAULT current_timestamp(),
  `dateDebut` datetime NOT NULL DEFAULT current_timestamp(),
  `prix` double NOT NULL DEFAULT 0,
  `pourcentage` int(11) NOT NULL,
  `active` int(11) NOT NULL DEFAULT 0,
  `idProduit` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_C11D7DD1391C87D5` (`idProduit`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `promotion`
--

INSERT INTO `promotion` (`id`, `dateFin`, `dateDebut`, `prix`, `pourcentage`, `active`, `idProduit`) VALUES
(4, '2020-05-13 00:00:00', '2020-05-06 00:00:00', -2220107780, 222111, 1, 10);

-- --------------------------------------------------------

--
-- Table structure for table `publication`
--

DROP TABLE IF EXISTS `publication`;
CREATE TABLE IF NOT EXISTS `publication` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `contenu` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `datePublication` datetime NOT NULL,
  `id_User` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_AF3C6779A6816575` (`id_User`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `publication`
--

INSERT INTO `publication` (`id`, `type`, `contenu`, `datePublication`, `id_User`) VALUES
(7, '64c057eb0348d9abfa14a67899fc6041.jpeg', 'azertyuio', '2020-02-27 13:52:51', 2),
(11, '85aa0432f1563dc3b96e8f2f916851f5.jpeg', 'hello my name is idk am posting this idk why', '2020-02-29 19:19:37', 4),
(14, '9a3c9ebfdd080c1c25d956a19c73a982.jpeg', 'hello world this is my post', '2020-03-06 16:37:43', 6),
(15, 'c1e4b15e1b657d757ebd44dee9494762.jpeg', 'ahla forum', '2020-04-11 00:31:16', 3),
(16, '5ec1d4f25415bprofImg.png', 'test', '2020-05-18 00:21:06', 2),
(41, '5ec256a8886d9profImg.png', 'publication', '2020-05-18 09:34:32', 87),
(44, '5ec9ea3b5a33dpubImg.png', 'xxxx', '2020-05-24 03:30:03', 2);

-- --------------------------------------------------------

--
-- Table structure for table `rating`
--

DROP TABLE IF EXISTS `rating`;
CREATE TABLE IF NOT EXISTS `rating` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `note` int(11) NOT NULL,
  `idProduit` int(11) DEFAULT NULL,
  `idClient` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_D8892622391C87D5` (`idProduit`),
  KEY `IDX_D8892622A455ACCF` (`idClient`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `reclamation`
--

DROP TABLE IF EXISTS `reclamation`;
CREATE TABLE IF NOT EXISTS `reclamation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `ido` int(11) NOT NULL,
  `sujet` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `description` varchar(5000) COLLATE utf8_unicode_ci NOT NULL,
  `date` datetime NOT NULL,
  `etat` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `idU` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_CE606404A2D72265` (`idU`)
) ENGINE=InnoDB AUTO_INCREMENT=135 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `reclamation`
--

INSERT INTO `reclamation` (`id`, `type`, `ido`, `sujet`, `description`, `date`, `etat`, `idU`) VALUES
(70, 'Bug', 0, 'aaaa', 'DescriptionTest*Message1By Client X-ROLE_CLIENT-**Second message by client-ROLE_CLIENT-**third message by Admin-ROLE_ADMIN-**message 4 client-ROLE_CLIENT-**test-ROLE_CLIENT-**test2-ROLE_CLIENT-**test3-ROLE_CLIENT-**test4-ROLE_CLIENT-**-ROLE_CLIENT-**test-ROLE_ADMIN-**test-ROLE_ADMIN-**commentaire final-ROLE_ADMIN-*', '2020-04-14 01:32:30', 'Refus', 10),
(129, 'WebService', 0, 'reclamation 2', 'aaaaaaaxxxxx', '2020-05-18 06:44:51', 'Pending', 2),
(130, 'WebService', 0, 'rec', 'aa', '2020-05-18 09:29:07', 'Pending', 87),
(131, 'Produit', 9, 'aaa', 'aaa', '2020-05-18 09:29:39', 'Pending', 87),
(132, 'Other', 0, 'aa', 'aa', '2020-05-24 00:16:07', 'Pending', 2),
(133, 'Bug', 0, 'xx', 'xx', '2020-05-24 04:03:45', 'Pending', 2),
(134, 'Produit', 10, 'test', 'test', '2020-05-24 04:47:15', 'Pending', 2);

-- --------------------------------------------------------

--
-- Table structure for table `saison`
--

DROP TABLE IF EXISTS `saison`;
CREATE TABLE IF NOT EXISTS `saison` (
  `idSaison` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `periode` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `mode` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`idSaison`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `saison`
--

INSERT INTO `saison` (`idSaison`, `nom`, `periode`, `mode`) VALUES
(2, 'a', 'novembre decembre janiver', 'desert'),
(4, 'eteaaa', 'azed', 'aze'),
(5, 'printemps', 'sda', 'zsddd'),
(6, 'dzdza', 'aaa', 'aa'),
(7, 'dzdza', 'aaa', 'aa'),
(9, 'dzad', 'dzdaz', 'dzdaz'),
(19, 'Saison1', 'Saison1', 'Saison1'),
(21, 'Saison2', 'Saison2', 'Saison2'),
(23, 'tes', 'tes', 'tes');

-- --------------------------------------------------------

--
-- Table structure for table `tva`
--

DROP TABLE IF EXISTS `tva`;
CREATE TABLE IF NOT EXISTS `tva` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `multiplicate` double NOT NULL,
  `nom` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `valeur` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `tva`
--

INSERT INTO `tva` (`id`, `multiplicate`, `nom`, `valeur`) VALUES
(1, 10, '10', 10);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(180) COLLATE utf8_unicode_ci NOT NULL,
  `username_canonical` varchar(180) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(180) COLLATE utf8_unicode_ci NOT NULL,
  `email_canonical` varchar(180) COLLATE utf8_unicode_ci NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  `salt` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `last_login` datetime DEFAULT NULL,
  `confirmation_token` varchar(180) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password_requested_at` datetime DEFAULT NULL,
  `roles` longtext COLLATE utf8_unicode_ci NOT NULL COMMENT '(DC2Type:array)',
  `firstname` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `lastname` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `phone` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `date` datetime NOT NULL,
  `image` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UNIQ_8D93D649A0D96FBF` (`email_canonical`),
  UNIQUE KEY `UNIQ_8D93D64992FC23A8` (`username_canonical`),
  UNIQUE KEY `UNIQ_8D93D649C05FB297` (`confirmation_token`)
) ENGINE=InnoDB AUTO_INCREMENT=89 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `username`, `username_canonical`, `email`, `email_canonical`, `enabled`, `salt`, `password`, `last_login`, `confirmation_token`, `password_requested_at`, `roles`, `firstname`, `lastname`, `phone`, `date`, `image`) VALUES
(1, 'ilyessss', 'ilyessss', 'mohamedilyess.aouini@esprit.tn', 'mohamedilyess.aouini@esprit.tn', 0, NULL, '$2y$13$Ji5ZuIf9Qhl1xLHOWscqiOTlg3C4irtbITbjwzIP7evpweEdWkTzG', '2020-02-20 18:47:55', NULL, NULL, 'a:1:{i:0;s:11:\"ROLE_CLIENT\";}', 'Ilyess', 'Aouini', '287', '0000-00-00 00:00:00', ''),
(2, 'aa', 'aa', 'aa@aa.aa', 'aa@aa.aa', 1, NULL, '$2y$13$ZrQCKxaSt9Ib7/PEOaN4Iu3b7CPBbKrFxfs8/8O8KK3CuCycHTQVi', '2020-05-17 21:11:42', NULL, NULL, 'a:1:{i:0;s:11:\"ROLE_CLIENT\";}', 'aa', 'aa', '24318657', '2020-04-08 00:00:00', '5abff9055a6dbab219ff4bc38f508537.jpeg'),
(3, 'bb', 'bb', 'bb@bb.bb', 'bb@bb.bb', 1, NULL, '$2y$13$V73lnorJ6uNj1D1EMtNn1OaZbDkXaCcySu/JZmex3bqkuEkmOO0Fy', '2020-05-17 02:55:51', NULL, NULL, 'a:1:{i:0;s:10:\"ROLE_ADMIN\";}', 'bb', 'bb', '47', '0000-00-00 00:00:00', '5abff9055a6dbab219ff4bc38f508537.jpeg'),
(4, 'cc', 'cc', 'affessalem@hotmail.fr', 'affessalem@hotmail.fr', 1, NULL, '$2y$13$m6f7DQ/SSHNLd9hVKUMMD.ppBI24HM4.xamr01a4FHosOQNwxgVoW', '2020-03-02 22:10:37', 'tF9ppy6GjvmRGzkmPUM-U4vcBLVk1iuavKnK1BhIP4c', NULL, 'a:1:{i:0;s:11:\"ROLE_CLIENT\";}', 'Salem', 'Affes', '47', '2020-02-29 11:41:12', ''),
(5, 'dd', 'dd', 'dd@dd.dd', 'dd@dd.dd', 1, NULL, '$2y$13$ZArw1Kl7gEl/CFTVJT6rsO7nh2evboQwMk8jdAUqJFnICyiV0brJi', '2020-05-03 23:06:16', NULL, NULL, 'a:1:{i:0;s:11:\"ROLE_CLIENT\";}', 'dd', 'dd', '5151', '2020-03-06 15:44:07', 'C:\\wamp64\\tmp\\phpB93B.tmp'),
(6, 'ee', 'ee', 'ee@ee.ee', 'ee@ee.ee', 1, NULL, '$2y$13$JHgjH8QCxYTyHIy5WgdDmet4O2d6nUZ20Isjcb.Ocoyp0qgQ0aKMK', '2020-03-06 16:36:57', 'Ss7Et597wWtwR_9-JY0qwRoL8-4YbrzFogvzlG462UU', NULL, 'a:1:{i:0;s:11:\"ROLE_CLIENT\";}', 'ee', 'ee', '47', '2020-03-06 16:34:08', '90bea63b89162457b498d292219a3508.jpeg'),
(7, 'ff', 'ff', 'ff@ff.ff', 'ff@ff.ff', 1, NULL, '$2y$13$KEX2w1hix9uwrOE88YuTpOT3QQTb7V2a2iputQgtfOaz39JUUXQbO', '2020-05-18 04:55:57', 'JaEcajclV-IjZxD8WREneBVzrM9bllp5BHGQDJkn-fQ', NULL, 'a:1:{i:0;s:10:\"ROLE_ADMIN\";}', 'ff', 'ff', '55323442', '2020-03-06 16:49:31', '5abff9055a6dbab219ff4bc38f508537.jpeg'),
(10, 'bbb', 'bbb', 'bbb@esprit.tn', 'bbb', 1, NULL, '$2y$10$Sxv352zhsa2BJhk2Ro459.bd/0JF0oUrFeilfdCogCbF2S51YbQ8W', '2020-05-26 00:00:00', NULL, NULL, 'a:1:{i:0;s:11:\"ROLE_CLIENT\";}', 'bbb', 'bbb', '24318657', '2020-04-16 00:00:00', 'C:\\Users\\LQss\\Desktop\\joke\\bg.jpg'),
(13, 'admin', 'admin', 'admin@esprit.tn', 'admin@esprit.tn', 0, NULL, '$2y$10$dHKMlegS2gSIJ2vDadqxl.VotgBS6Mk79bRaJ9b5obICIehjLN1BK', '2020-05-19 00:00:00', NULL, NULL, 'a:1:{i:0;s:11:\"ROLE_CLIENT\";}', 'okok', 'okok', '222', '2020-04-15 00:00:00', 'change photo'),
(18, 'www', 'www', 'www@esprit.tn', 'www@esprit.tn', 1, NULL, '$2y$10$HW.sYv.g/m8Q0XtDsxzWWeXDZAycNnzUk9rSEUIsCh43yGplIOZVG', '2020-05-20 00:00:00', NULL, NULL, 'a:1:{i:0;s:11:\"ROLE_CLIENT\";}', 'www', 'www', '111', '2020-04-21 00:00:00', 'ZPdFNkGh.jpg'),
(19, 'LQss', 'LQss', 'ssss@esprit.tn', 'ssss@esprit.tn', 1, NULL, '$2y$10$WsIbE2ox6pmoCW6vN/d0cuymCMdPxvI8wx6Oq9gnI86kThIBv6giy', '2020-04-21 00:00:00', NULL, NULL, 'a:1:{i:0;s:11:\"ROLE_CLIENT\";}', 'Salem', 'Affes', '55323442', '2020-04-15 00:00:00', '7cSsXE8r.jpg'),
(20, 'aw', 'aw', 'aw@aw.aw', 'aw@aw.aw', 0, NULL, '$2y$13$yOEDDNrBRNAPBAz07BJn2eh.bHJ5nbYwHkHXK/LD0z7euPTruVRs.', '2020-05-12 00:00:00', 'RNqVnoKn2p0EIKn_DwUpetFa6S2yWdoS_6ETyRrTz74', NULL, 'a:1:{i:0;s:11:\"ROLE_CLIENT\";}', 'aw', 'aw', '55323442', '2020-05-03 05:11:03', '57e2f4cbbb64e408806214e5db861371.jpeg'),
(38, 'w', 'w', 'w', 'w', 1, NULL, '$2y$10$pOnmzxYhIkeqbScd4HZqBelYx87yZb3UlsyBf/T9CEPAMQtLvdo4e', '2020-05-04 02:02:51', NULL, NULL, 'a:1:{i:0;s:11:\"ROLE_CLIENT\";}', 'w', 'w', 'w', '2020-05-04 02:02:51', 'add'),
(39, 'user1', 'user1', 'abc@hotmail.fr', 'abc@hotmail.fr', 1, NULL, '$2y$10$OZnYnH.igWcuS4pPMHL8Y.JpDUC8vLxUw4QMVUq7G2LcqefGZRzcq', '2020-05-09 16:41:09', NULL, NULL, 'a:1:{i:0;s:11:\"ROLE_CLIENT\";}', 'test', 'test', '2222222', '2020-05-09 16:41:09', 'file://C:/Users/LQss/AppData/Local/Temp/temp1772732844558594545s..jpg'),
(40, 'ww', 'ww', 'ww', 'ww', 1, NULL, '$2y$10$AsD6S0Nc0AS.CDeR03ZSvu5UjCvVa6zQd2S5OluhOS4hhHTHlfCK.', '2020-05-09 17:06:55', NULL, NULL, 'a:1:{i:0;s:11:\"ROLE_CLIENT\";}', 'ww', 'ww', 'ww', '2020-05-09 17:06:55', 'temp5589154992501337924s..jpg'),
(61, 'oo', 'oo', 'oo', 'oo', 1, NULL, '$2y$10$D2qAAAbo4v/h6T1CxWqJdem8bjXmWFV2RjmfMM8xlzwzgh5AvzPGG', '2020-05-14 10:21:01', NULL, NULL, 'a:1:{i:0;s:11:\"ROLE_CLIENT\";}', 'oo', 'oo', '5555', '2020-05-14 10:21:01', 'temp4470342115908123676s..jpg'),
(67, 'abc11', 'abc11', 'abc11', 'abc11', 1, NULL, 'abc11', '2020-05-14 12:13:17', NULL, NULL, 'a:1:{i:0;s:11:\"ROLE_CLIENT\";}', 'abc11', 'abc11', 'abc11', '2020-05-14 12:13:17', 'mob.j'),
(81, 'raf', 'raf', 'raf', 'raf', 1, NULL, '$2y$10$pRyMKRsJxyV8u2MMLUsQ9u/JiY1.kqWa3Hp2wcT81cuyfbubqfhKC', '2020-05-16 15:44:09', NULL, NULL, 'a:1:{i:0;s:11:\"ROLE_CLIENT\";}', 'raf', 'raf', '5555', '2020-05-16 15:44:09', '5ec00a49d8eb2profImg.png'),
(82, 'dzadaz', 'dzadaz', 'dzdazd', 'dzdazd', 1, NULL, '$2y$10$.XzVHgXw/KB5kM2tTPBo3u2rAKKoeBBSEn7/vuqfN.3QHtm9zJPxi', '2020-05-16 18:18:58', NULL, NULL, 'a:1:{i:0;s:11:\"ROLE_CLIENT\";}', 'dza', 'daz', 'daz', '2020-05-16 18:18:58', '5ec02e9241240profImg.png'),
(83, 'aaa', 'aaa', 'affessakel', 'affessakel', 1, NULL, '$2y$10$qzgUZWsSwRTIW2ACzwLP9O/LZijWSwKkmQ4efbWBbrZBLTt.htlqi', '2020-05-16 19:52:30', NULL, NULL, 'a:1:{i:0;s:11:\"ROLE_CLIENT\";}', 'aa', 'aa', 'aaaaa', '2020-05-16 19:52:30', '5ec0447eb2cb3profImg.png'),
(85, 'lq', 'lq', 'la@la.la', 'la@la.la', 1, NULL, '$2y$10$6C0VlQWVPLCQGdJeSL0sSO.K.bbKJyZvhzYGNkqAYavlnkKxc/OK2', '2020-05-16 20:48:20', NULL, NULL, 'a:1:{i:0;s:11:\"ROLE_CLIENT\";}', 'la', 'la', '22552255', '2020-05-16 20:48:20', '5ec0519488f56profImg.png'),
(86, 'heyy', 'heyy', 'heyy@heyy.heyy', 'heyy@heyy.heyy', 1, NULL, '$2y$10$QTe5dZe9.CkL1HK0GM30a.8/t4Git10/deZy0ZyHHSYuJiCC5bVkG', '2020-05-16 23:22:27', NULL, NULL, 'a:1:{i:0;s:11:\"ROLE_CLIENT\";}', 'heyy', 'heyy', '55222332', '2020-05-16 23:22:27', '5ec075b34e699profImg.png'),
(87, 'eeee', 'eeee', 'eeee@eeee.eeee', 'eeee@eeee.eeee', 1, NULL, '$2y$10$KJ59BJ4f3rkxdQoUm3K3D.vYMpb4jt40aeEQLCc4qJGxg9GpFoZMe', '2020-05-18 09:22:25', NULL, NULL, 'a:1:{i:0;s:11:\"ROLE_CLIENT\";}', 'eeee', 'eeee', '24318657', '2020-05-18 09:22:25', '5ec253d12535fprofImg.png'),
(88, 'xaxaxa', 'xaxaxa', 'xaxaxa@xaxaxa.xaxaxa', 'xaxaxa@xaxaxa.xaxaxa', 1, NULL, '$2y$10$OsKTChDH847xagOdY0yxbecEk17byjhGvLjjF/QiY7UbKOnvz7CeK', '2020-05-24 01:41:18', NULL, NULL, 'a:1:{i:0;s:11:\"ROLE_CLIENT\";}', 'xaxaxa', 'xaxaxa', '22222222', '2020-05-24 01:41:18', '5ec9d0bebef2bprofImg.png');

-- --------------------------------------------------------

--
-- Table structure for table `utilisateurs_adresses`
--

DROP TABLE IF EXISTS `utilisateurs_adresses`;
CREATE TABLE IF NOT EXISTS `utilisateurs_adresses` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `utilisateur_id` int(11) DEFAULT NULL,
  `nom` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `prenom` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `telephone` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `adresse` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `cp` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `pays` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `ville` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `complement` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_F4167640FB88E14F` (`utilisateur_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `utilisateurs_adresses`
--

INSERT INTO `utilisateurs_adresses` (`id`, `utilisateur_id`, `nom`, `prenom`, `telephone`, `adresse`, `cp`, `pays`, `ville`, `complement`) VALUES
(2, 3, 'aaa', 'aaa', '55323442', 'dzdaz@aa.dd', '32232', 'sfax', 'zdazd', 'dzda'),
(6, 18, 'XXXXXX', 'XXXXXX', '515', 'XXXXXXXXXXXXXXXXX', '2222', 'XXXXX', 'XXXXX', 'XXXXXX'),
(7, 18, 'XXX', 'AAA', '15151', 'dzdazad', '111', 'TEST', 'TTTT', 'CC'),
(8, 2, 'test', 'test', 'test', 'test', '3333', 'st', 'ss', 'sss'),
(10, 87, 'add', 'add', 'add', 'add@add.a', '5554', 'add', 'add', 'add'),
(11, 2, 'aa', 'aa', '222222', 'aa@aa.aa', '444', 't', 'c', 'ccc');

-- --------------------------------------------------------

--
-- Table structure for table `whishlist`
--

DROP TABLE IF EXISTS `whishlist`;
CREATE TABLE IF NOT EXISTS `whishlist` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idProduit` int(11) DEFAULT NULL,
  `idClient` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_2E936C6D391C87D5` (`idProduit`),
  KEY `IDX_2E936C6DA455ACCF` (`idClient`)
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `whishlist`
--

INSERT INTO `whishlist` (`id`, `idProduit`, `idClient`) VALUES
(59, 10, 87),
(60, 10, 2);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `commandes`
--
ALTER TABLE `commandes`
  ADD CONSTRAINT `FK_35D4282CFB88E14F` FOREIGN KEY (`utilisateur_id`) REFERENCES `user` (`id`);

--
-- Constraints for table `commentaire`
--
ALTER TABLE `commentaire`
  ADD CONSTRAINT `FK_67F068BCA6816575` FOREIGN KEY (`id_User`) REFERENCES `user` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `FK_67F068BCB72EAA8E` FOREIGN KEY (`id_publication`) REFERENCES `publication` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `com_produit`
--
ALTER TABLE `com_produit`
  ADD CONSTRAINT `FK_17BEAAE8391C87D5` FOREIGN KEY (`idProduit`) REFERENCES `produit` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `FK_17BEAAE8A455ACCF` FOREIGN KEY (`idClient`) REFERENCES `user` (`id`);

--
-- Constraints for table `espece`
--
ALTER TABLE `espece`
  ADD CONSTRAINT `FK_1A2A1B14BB48750` FOREIGN KEY (`idS`) REFERENCES `saison` (`idSaison`);

--
-- Constraints for table `feedback`
--
ALTER TABLE `feedback`
  ADD CONSTRAINT `FK_D2294458A2D72265` FOREIGN KEY (`idU`) REFERENCES `user` (`id`);

--
-- Constraints for table `inscription`
--
ALTER TABLE `inscription`
  ADD CONSTRAINT `FK_5E90F6D65E5C27E9` FOREIGN KEY (`iduser`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_5E90F6D6EDAB66BE` FOREIGN KEY (`idevent`) REFERENCES `evenement` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `notifiable_notification`
--
ALTER TABLE `notifiable_notification`
  ADD CONSTRAINT `FK_ADCFE0FAC3A0A4F8` FOREIGN KEY (`notifiable_entity_id`) REFERENCES `notifiable_entity` (`id`),
  ADD CONSTRAINT `FK_ADCFE0FAEF1A9D84` FOREIGN KEY (`notification_id`) REFERENCES `notification` (`id`);

--
-- Constraints for table `produit`
--
ALTER TABLE `produit`
  ADD CONSTRAINT `FK_29A5EC27497DD634` FOREIGN KEY (`categorie`) REFERENCES `categorie` (`id`),
  ADD CONSTRAINT `FK_29A5EC27A655BE48` FOREIGN KEY (`tvaId`) REFERENCES `tva` (`id`);

--
-- Constraints for table `promotion`
--
ALTER TABLE `promotion`
  ADD CONSTRAINT `FK_C11D7DD1391C87D5` FOREIGN KEY (`idProduit`) REFERENCES `produit` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `publication`
--
ALTER TABLE `publication`
  ADD CONSTRAINT `FK_AF3C6779A6816575` FOREIGN KEY (`id_User`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `rating`
--
ALTER TABLE `rating`
  ADD CONSTRAINT `FK_D8892622391C87D5` FOREIGN KEY (`idProduit`) REFERENCES `produit` (`id`),
  ADD CONSTRAINT `FK_D8892622A455ACCF` FOREIGN KEY (`idClient`) REFERENCES `user` (`id`);

--
-- Constraints for table `reclamation`
--
ALTER TABLE `reclamation`
  ADD CONSTRAINT `FK_CE606404A2D72265` FOREIGN KEY (`idU`) REFERENCES `user` (`id`);

--
-- Constraints for table `utilisateurs_adresses`
--
ALTER TABLE `utilisateurs_adresses`
  ADD CONSTRAINT `FK_F4167640FB88E14F` FOREIGN KEY (`utilisateur_id`) REFERENCES `user` (`id`);

--
-- Constraints for table `whishlist`
--
ALTER TABLE `whishlist`
  ADD CONSTRAINT `FK_2E936C6D391C87D5` FOREIGN KEY (`idProduit`) REFERENCES `produit` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `FK_2E936C6DA455ACCF` FOREIGN KEY (`idClient`) REFERENCES `user` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
