-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Apr 15, 2020 at 08:57 PM
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
) ENGINE=InnoDB AUTO_INCREMENT=449 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

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
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `commandes`
--

INSERT INTO `commandes` (`id`, `utilisateur_id`, `valider`, `date`, `reference`, `commande`) VALUES
(3, 3, 1, '2019-02-27 04:29:56', 1, 'a:7:{s:3:\"tva\";a:1:{s:3:\"%10\";d:-5635.8;}s:7:\"produit\";a:1:{i:1;a:5:{s:2:\"id\";i:1;s:9:\"reference\";s:12:\"ikgohaaaaaaa\";s:8:\"quantite\";i:1;s:6:\"prixHT\";d:6262;s:7:\"prixTTC\";d:626.2;}}s:9:\"livraison\";a:8:{s:6:\"prenom\";s:3:\"aaa\";s:3:\"nom\";s:3:\"aaa\";s:9:\"telephone\";s:8:\"55323442\";s:7:\"adresse\";s:11:\"dzdaz@aa.dd\";s:2:\"cp\";s:5:\"32232\";s:5:\"ville\";s:5:\"zdazd\";s:4:\"pays\";s:4:\"sfax\";s:10:\"complement\";s:4:\"dzda\";}s:11:\"facturation\";a:8:{s:6:\"prenom\";s:3:\"aaa\";s:3:\"nom\";s:3:\"aaa\";s:9:\"telephone\";s:8:\"55323442\";s:7:\"adresse\";s:11:\"dzdaz@aa.dd\";s:2:\"cp\";s:5:\"32232\";s:5:\"ville\";s:5:\"zdazd\";s:4:\"pays\";s:4:\"sfax\";s:10:\"complement\";s:4:\"dzda\";}s:6:\"prixHT\";d:6262;s:7:\"prixTTC\";d:626.2;s:5:\"token\";s:40:\"27d79a5b8ae4010e7e6ebc264e1046c8552f7f25\";}'),
(5, 3, 1, '2020-02-27 04:54:11', 8, 'a:7:{s:3:\"tva\";a:1:{s:3:\"%10\";d:-540;}s:7:\"produit\";a:1:{i:2;a:5:{s:2:\"id\";i:2;s:9:\"reference\";s:4:\"azer\";s:8:\"quantite\";s:1:\"6\";s:6:\"prixHT\";d:100;s:7:\"prixTTC\";d:10;}}s:9:\"livraison\";a:8:{s:6:\"prenom\";s:3:\"aaa\";s:3:\"nom\";s:3:\"aaa\";s:9:\"telephone\";s:8:\"55323442\";s:7:\"adresse\";s:11:\"dzdaz@aa.dd\";s:2:\"cp\";s:5:\"32232\";s:5:\"ville\";s:5:\"zdazd\";s:4:\"pays\";s:4:\"sfax\";s:10:\"complement\";s:4:\"dzda\";}s:11:\"facturation\";a:8:{s:6:\"prenom\";s:3:\"aaa\";s:3:\"nom\";s:3:\"aaa\";s:9:\"telephone\";s:8:\"55323442\";s:7:\"adresse\";s:11:\"dzdaz@aa.dd\";s:2:\"cp\";s:5:\"32232\";s:5:\"ville\";s:5:\"zdazd\";s:4:\"pays\";s:4:\"sfax\";s:10:\"complement\";s:4:\"dzda\";}s:6:\"prixHT\";d:600;s:7:\"prixTTC\";d:60;s:5:\"token\";s:40:\"32928f8ff3c9fed2bcbf496a92da80337552bfeb\";}'),
(6, 3, 1, '2020-02-27 05:05:01', 9, 'a:7:{s:3:\"tva\";a:1:{s:3:\"%10\";d:-540;}s:7:\"produit\";a:1:{i:2;a:5:{s:2:\"id\";i:2;s:9:\"reference\";s:4:\"azer\";s:8:\"quantite\";s:1:\"6\";s:6:\"prixHT\";d:100;s:7:\"prixTTC\";d:10;}}s:9:\"livraison\";a:8:{s:6:\"prenom\";s:3:\"aaa\";s:3:\"nom\";s:3:\"aaa\";s:9:\"telephone\";s:8:\"55323442\";s:7:\"adresse\";s:11:\"dzdaz@aa.dd\";s:2:\"cp\";s:5:\"32232\";s:5:\"ville\";s:5:\"zdazd\";s:4:\"pays\";s:4:\"sfax\";s:10:\"complement\";s:4:\"dzda\";}s:11:\"facturation\";a:8:{s:6:\"prenom\";s:3:\"aaa\";s:3:\"nom\";s:3:\"aaa\";s:9:\"telephone\";s:8:\"55323442\";s:7:\"adresse\";s:11:\"dzdaz@aa.dd\";s:2:\"cp\";s:5:\"32232\";s:5:\"ville\";s:5:\"zdazd\";s:4:\"pays\";s:4:\"sfax\";s:10:\"complement\";s:4:\"dzda\";}s:6:\"prixHT\";d:600;s:7:\"prixTTC\";d:60;s:5:\"token\";s:40:\"6114103f2ea9746e7f1144e5a58fb0884e0a032e\";}'),
(9, 2, 1, '2020-02-27 11:30:08', 10, 'a:7:{s:3:\"tva\";a:1:{s:3:\"%10\";d:-22543.2;}s:7:\"produit\";a:1:{i:1;a:5:{s:2:\"id\";i:1;s:9:\"reference\";s:12:\"ikgohaaaaaaa\";s:8:\"quantite\";s:1:\"4\";s:6:\"prixHT\";d:6262;s:7:\"prixTTC\";d:626.2;}}s:9:\"livraison\";a:8:{s:6:\"prenom\";s:5:\"salem\";s:3:\"nom\";s:5:\"affes\";s:9:\"telephone\";s:8:\"55323442\";s:7:\"adresse\";s:21:\"affessalem@hotmail.fr\";s:2:\"cp\";s:4:\"3302\";s:5:\"ville\";s:4:\"sfax\";s:4:\"pays\";s:5:\"tunis\";s:10:\"complement\";s:7:\"mamamam\";}s:11:\"facturation\";a:8:{s:6:\"prenom\";s:5:\"salem\";s:3:\"nom\";s:5:\"affes\";s:9:\"telephone\";s:8:\"55323442\";s:7:\"adresse\";s:21:\"affessalem@hotmail.fr\";s:2:\"cp\";s:4:\"3302\";s:5:\"ville\";s:4:\"sfax\";s:4:\"pays\";s:5:\"tunis\";s:10:\"complement\";s:7:\"mamamam\";}s:6:\"prixHT\";d:25048;s:7:\"prixTTC\";d:2504.8;s:5:\"token\";s:40:\"0eccfac201919138baf8b18484ee97f7820ccfa7\";}'),
(10, 2, 1, '2020-02-27 12:46:20', 11, 'a:7:{s:3:\"tva\";a:1:{s:3:\"%10\";d:-90;}s:7:\"produit\";a:1:{i:2;a:5:{s:2:\"id\";i:2;s:9:\"reference\";s:4:\"azer\";s:8:\"quantite\";i:1;s:6:\"prixHT\";d:100;s:7:\"prixTTC\";d:10;}}s:9:\"livraison\";a:8:{s:6:\"prenom\";s:5:\"salem\";s:3:\"nom\";s:5:\"affes\";s:9:\"telephone\";s:8:\"55323442\";s:7:\"adresse\";s:21:\"affessalem@hotmail.fr\";s:2:\"cp\";s:4:\"3302\";s:5:\"ville\";s:4:\"sfax\";s:4:\"pays\";s:5:\"tunis\";s:10:\"complement\";s:7:\"mamamam\";}s:11:\"facturation\";a:8:{s:6:\"prenom\";s:5:\"salem\";s:3:\"nom\";s:5:\"affes\";s:9:\"telephone\";s:8:\"55323442\";s:7:\"adresse\";s:21:\"affessalem@hotmail.fr\";s:2:\"cp\";s:4:\"3302\";s:5:\"ville\";s:4:\"sfax\";s:4:\"pays\";s:5:\"tunis\";s:10:\"complement\";s:7:\"mamamam\";}s:6:\"prixHT\";d:100;s:7:\"prixTTC\";d:10;s:5:\"token\";s:40:\"ee3276531c9b2b76bb3fdee2d94d4a1be5069887\";}'),
(11, 2, 1, '2020-02-27 14:01:16', 12, 'a:7:{s:3:\"tva\";a:1:{s:3:\"%10\";d:-945;}s:7:\"produit\";a:2:{i:3;a:5:{s:2:\"id\";i:3;s:9:\"reference\";s:5:\"azert\";s:8:\"quantite\";s:1:\"5\";s:6:\"prixHT\";d:10;s:7:\"prixTTC\";d:1;}i:4;a:5:{s:2:\"id\";i:4;s:9:\"reference\";s:4:\"azer\";s:8:\"quantite\";s:2:\"10\";s:6:\"prixHT\";d:100;s:7:\"prixTTC\";d:10;}}s:9:\"livraison\";a:8:{s:6:\"prenom\";s:5:\"salem\";s:3:\"nom\";s:5:\"affes\";s:9:\"telephone\";s:8:\"55323442\";s:7:\"adresse\";s:21:\"affessalem@hotmail.fr\";s:2:\"cp\";s:4:\"3302\";s:5:\"ville\";s:4:\"sfax\";s:4:\"pays\";s:5:\"tunis\";s:10:\"complement\";s:7:\"mamamam\";}s:11:\"facturation\";a:8:{s:6:\"prenom\";s:5:\"salem\";s:3:\"nom\";s:5:\"affes\";s:9:\"telephone\";s:8:\"55323442\";s:7:\"adresse\";s:21:\"affessalem@hotmail.fr\";s:2:\"cp\";s:4:\"3302\";s:5:\"ville\";s:4:\"sfax\";s:4:\"pays\";s:5:\"tunis\";s:10:\"complement\";s:7:\"mamamam\";}s:6:\"prixHT\";d:1050;s:7:\"prixTTC\";d:105;s:5:\"token\";s:40:\"2eb374be279e40d9cfce66e102c01244d0c0966c\";}');

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
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `commentaire`
--

INSERT INTO `commentaire` (`id`, `id_publication`, `contenu`, `dateComnt`, `id_User`) VALUES
(2, 2, 'eeeee', '2020-02-26 03:34:43', 3),
(3, 2, 'aaaaa', '2020-02-26 03:34:50', 3),
(4, 2, 'ssss', '2020-02-26 03:35:19', 3),
(5, 2, 'ssss', '2020-02-26 03:36:16', 3),
(6, 2, 'ssss', '2020-02-26 03:36:40', 3),
(7, 2, 'ssss', '2020-02-26 03:38:39', 3),
(8, 2, 'ssss', '2020-02-26 03:39:03', 3),
(9, 2, 'ssss', '2020-02-26 03:39:26', 3),
(10, 2, 'ssss', '2020-02-26 03:39:42', 3),
(11, 1, 'ddzdz', '2020-02-26 11:20:44', 3),
(12, 1, 'zdzda', '2020-02-26 11:20:52', 3),
(13, 4, 'hellow world😃😃😃😃😃\r\n', '2020-02-26 22:30:03', 2),
(14, 5, '😞😞😞', '2020-02-27 12:56:21', 2),
(15, 7, 'zertyui', '2020-02-27 13:53:44', 2),
(16, 7, 'sdfghjkl', '2020-02-27 13:53:53', 2),
(23, 14, '🤑🤑🤑🤑🤑🤑🤑😞😞😞😞okokokoko', '2020-03-06 16:38:04', 6),
(24, 14, 'okokok😍😍🤑🤑🤑🤑', '2020-03-06 16:38:27', 6),
(25, 15, 'ahla 3aslema merci boucou😍😍😍 oui cest clair parfait \r\n', '2020-04-11 00:31:46', 3);

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

--
-- Dumping data for table `com_produit`
--

INSERT INTO `com_produit` (`id`, `contenu`, `idProduit`, `idClient`) VALUES
(2, 'hhhh', 2, 2),
(3, 'kk', 2, 2),
(4, 'nice', 4, 4),
(5, 'nice', 4, 4),
(6, 'nice object', 4, 4);

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
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `espece`
--

INSERT INTO `espece` (`idEspece`, `nomEspece`, `descriptionEspece`, `image`, `poids`, `type`, `zone`, `ville`, `idS`) VALUES
(16, 'sanglier', 'sanglier petit', '52026c35e198e59d4b16c3fd8ac63344.jpeg', '25 kg', 'sanglier', 'motagne', 'kef', 5),
(17, 'sanglier', 'sanglier grand', 'b2e28f796834b6b04ca289c70ed50d92.jpeg', '45 kg', 'sanglier', 'montagne', 'jandouba', 4),
(18, 'poisson', 'poisson rouge', '1262da2b3f6b3dfef9cd5dfeb502dac7.jpeg', '100 g', 'poisson', 'a la ligne', 'nabeul', 5),
(19, 'ha', 'a dzadaz', '7fb226ea0b5c164a0a261b7624686871.jpeg', '15', 'sza', 'dzdaz', 'dzda', 2),
(20, 'ha', 'ha', 'src/Image/image.png', 'ha', 'ha', 'ha', 'ha', 2),
(21, 'ha', 'ha', 'src/Image/image.png', 'ha', 'ha', 'haaa', 'ha', 2);

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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `evenement`
--

INSERT INTO `evenement` (`id`, `nom`, `type`, `prix`, `nbrplace`, `date`, `place`, `description`, `duree`, `image`) VALUES
(2, 'aa', 'Formation', 11, 11, '2020-02-27 00:00:00', 'kkkk', 'aaa', 10, 'b6d51d9b744a387af030a94929f74915.jpeg'),
(3, 'aa', 'Formation', 11, 11, '2020-02-01 00:00:00', 'kkkk', 'aaa', 10, '0636691ab6c8e31f6447630af3e1a740.jpeg'),
(4, 'aaa', 'Formation', 51, 15, '2020-02-28 00:00:00', 'tunis', 'aaaa1', 22, '76b40dfde23901a4dd781aa062111de5.jpeg'),
(5, 'dzadaz', 'Formation', 12, 21, '2015-01-01 00:00:00', 'pkpfedze', '>dzakdazidjoazdazpjdazojdopazkda', 212, '71849c4179e7723397d9cda76937225e.jpeg');

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
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `feedback`
--

INSERT INTO `feedback` (`id`, `description`, `rate`, `date`, `idU`) VALUES
(1, 'pp', 3, '2020-03-26 20:34:39', 3),
(2, 'pp', 3, '2020-03-26 20:37:19', 3),
(3, 'pp', 3, '2020-03-26 20:37:30', 3),
(4, 'pp', 3, '2020-03-26 20:37:53', 3),
(5, 'JAVA', 2, '2020-03-29 00:00:00', 1),
(10, 'test', 4, '2020-04-11 00:00:00', 3),
(11, 'hellow222', 200, '2020-04-12 00:00:00', 3),
(12, 'dzdazdaz', 2222, '2020-04-13 00:00:00', 3),
(13, 'abc', 9, '2020-04-13 00:00:00', 10),
(14, 'test feedback', 4, '2020-04-15 00:00:00', 7),
(15, '', 2, '2020-04-15 00:00:00', 7),
(16, 'hello', 2, '2020-04-15 00:00:00', 7),
(38, 'aaa', 3, '2020-04-15 00:00:00', 3),
(45, 'ddddd', 3, '2020-04-15 00:00:00', 1),
(50, 'dddd', 3, '2020-04-15 00:00:00', 1),
(51, 'aaaa', 4, '2020-04-15 00:00:00', 5),
(52, 'aaaaaaaddddddd', 5, '2020-04-15 00:00:00', 5),
(53, 'merci', 4, '2020-04-15 00:00:00', 2);

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
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `inscription`
--

INSERT INTO `inscription` (`id`, `idevent`, `iduser`) VALUES
(1, 3, 3),
(2, 3, 3);

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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `produit`
--

INSERT INTO `produit` (`id`, `categorie`, `nom`, `quantite`, `description`, `prix`, `idFournisseur`, `etatPromo`, `garantie`, `image`, `tvaId`) VALUES
(2, 2, 'azer', -43, 'azerty', 100, 0, 0, 5, 'rafik.jpg', 1),
(3, 1, 'azert', 5, 'dsqfg', 10, 0, 0, 1, 'bg.jpg', 1),
(4, 1, 'azer', 5, 'ezryu', 100, 0, 1, 1, 'index.jpg', 1),
(5, 3, 'aa', 14, 'dzdazda', 15, 11, 15, 15, '551', 1);

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `promotion`
--

INSERT INTO `promotion` (`id`, `dateFin`, `dateDebut`, `prix`, `pourcentage`, `active`, `idProduit`) VALUES
(2, '2020-02-27 15:22:22', '2020-02-29 00:00:00', 0, 20, 1, 4);

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
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `publication`
--

INSERT INTO `publication` (`id`, `type`, `contenu`, `datePublication`, `id_User`) VALUES
(1, '959b825f18fa7612b32ba2d11931e3a5.jpeg', 'omg', '2020-02-24 23:39:49', 3),
(2, 'a6cc6b08cda32ca9cd0dc44c38470473.jpeg', 'omg', '2020-02-24 23:40:41', 3),
(3, 'faf3feda231112340192b454f505ddd6.jpeg', 'ohhh', '2020-02-26 22:07:43', 2),
(4, '6ac561bff851ab67674074027b5febcb.jpeg', 'cccccccc', '2020-02-26 22:29:16', 2),
(5, 'fe51b0a754578b7b29d5772c594b162e.jpeg', 'k,k', '2020-02-27 12:52:18', 2),
(7, '64c057eb0348d9abfa14a67899fc6041.jpeg', 'azertyuio', '2020-02-27 13:52:51', 2),
(11, '85aa0432f1563dc3b96e8f2f916851f5.jpeg', 'hello my name is idk am posting this idk why', '2020-02-29 19:19:37', 4),
(14, '9a3c9ebfdd080c1c25d956a19c73a982.jpeg', 'hello world this is my post', '2020-03-06 16:37:43', 6),
(15, 'c1e4b15e1b657d757ebd44dee9494762.jpeg', 'ahla forum', '2020-04-11 00:31:16', 3);

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
) ENGINE=InnoDB AUTO_INCREMENT=80 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `reclamation`
--

INSERT INTO `reclamation` (`id`, `type`, `ido`, `sujet`, `description`, `date`, `etat`, `idU`) VALUES
(10, 'WebService', 1, 'ss', 'DescriptionTest*Message1By Client X-ROLE_CLIENT-**Second message by client-ROLE_CLIENT-**third message by Admin-ROLE_ADMIN-**message 4 client-ROLE_CLIENT-**DescriptionTest-ROLE_CLIENT-**more comments-ROLE_ADMIN-*', '2020-02-26 01:31:12', 'Accepted', 10),
(21, 'Produit', 1, 'a', 'a*hello-ROLE_ADMIN-**aaaaa-ROLE_ADMIN-**aaaaaaaaaa-ROLE_ADMIN-*', '2020-02-26 03:33:09', 'Accepted', 3),
(23, 'Produit', 1, 'okok', 'dzdazdaz', '2020-02-26 10:23:31', 'Pending', 3),
(24, 'commentaire', 12, 'okok', 'okok', '2020-02-26 11:21:16', 'Pending', 3),
(28, 'commentaire', 14, 'dazdaz', 'dazda*dzadza-ROLE_ADMIN-**salut-ROLE_CLIENT-*', '2020-02-27 12:56:38', 'Pending', 2),
(29, 'Bug', 0, 'frfr', 'frfr', '2020-02-27 14:07:47', 'Pending', 2),
(31, 'WebService', 0, 'k,kn', 'ojo', '2020-02-29 11:02:52', 'Pending', 3),
(32, 'WebService', 0, 'k,kn', 'ojo', '2020-02-29 11:03:02', 'Pending', 3),
(33, 'Bug', 0, 'k,kn', 'ojo', '2020-02-29 11:03:08', 'Pending', 3),
(34, 'Bug', 0, 'k,kn', 'ojo', '2020-02-29 11:03:14', 'Accepted', 3),
(35, 'WebService', 0, 'aa', 'aaaa', '2020-02-29 13:01:19', 'Pending', 4),
(36, 'WebService', 0, ',jj', 'vygy', '2020-02-29 13:02:03', 'Pending', 4),
(37, 'Bug', 0, 'this website sucks', 'thank you for understanding', '2020-02-29 19:59:41', 'Pending', 4),
(38, 'WebService', 0, 'aaa', 'dzddza', '2020-03-01 00:05:04', 'Pending', 3),
(39, 'Bug', 12, 'test', 'TEST', '2020-03-09 06:11:00', 'TEST', 1),
(40, 'Bug', 12, 'test', 'TEST', '2020-03-09 06:11:00', 'TEST', 1),
(41, 'Bug', 12, 'test', 'TEST', '2020-03-09 06:11:00', 'TEST', 1),
(42, 'Bug', 12, 'test', 'TEST', '2020-03-09 06:11:00', 'TEST', 1),
(43, 'Bug', 12, 'test', 'TEST', '2020-03-09 06:11:00', 'TEST', 10),
(44, 'Bug', 12, 'test', 'TEST', '2020-03-09 06:11:00', 'TEST', 1),
(45, 'Bug', 12, 'test', 'TEST', '2020-03-09 06:11:00', 'TEST', 1),
(46, 'Bug', 12, 'test', 'TEST', '2020-03-09 06:11:00', 'TEST', 1),
(47, 'Bug', 12, 'test', 'TEST', '2020-03-09 06:11:00', 'TEST', 1),
(50, 'omg', 0, 'omg', 'JA', '2020-03-28 00:00:00', 'test', 2),
(51, 'GUI', 0, 'GUI', 'GUI', '2020-04-07 00:00:00', 'Pending', 3),
(52, 'test', 0, 'test', 'test', '2020-04-08 00:00:00', 'Pending', 3),
(55, 'ya rabbi', 0, 'hellow', 'ouh', '2020-04-08 00:00:00', 'Pending', 3),
(58, 'aa', 0, 'daaaa', 'aadad', '2020-04-09 00:00:00', 'Pending', 3),
(59, 'WebService', 0, 'adzdza', 'aaaa', '2020-04-11 00:35:05', 'Pending', 3),
(62, 'hello ', 0, 'merci', 'dzodkazodkoazdkoazpdkazodaz', '2020-04-13 00:00:00', 'Pending', 3),
(63, 'test', 0, 'test', 'test	', '2020-04-13 00:00:00', 'Pending', 3),
(64, 'test', 0, 'tetstst', 'tttt', '2020-04-13 00:00:00', 'Pending', 10),
(67, 'WebService', 0, 'This is a complaint just to test our application', 'Hello World!Hello World!Hello World!Hello World!Hello World!Hello World!Hello World!Hello World!Hello World!Hello World!Hello World!Hello World!Hello World!', '2020-04-13 00:00:00', 'Pending', 10),
(69, 'zz', 0, 'zz', 'zz*abc-ROLE_CLIENT-**aa-ROLE_CLIENT-**-ROLE_CLIENT-**aaxx-ROLE_CLIENT-**hello-ROLE_ADMIN-**hello2-ROLE_ADMIN-*', '2020-04-14 00:00:00', 'Pending', 10),
(70, 'Bug', 0, 'aaaa', 'DescriptionTest*Message1By Client X-ROLE_CLIENT-**Second message by client-ROLE_CLIENT-**third message by Admin-ROLE_ADMIN-**message 4 client-ROLE_CLIENT-**test-ROLE_CLIENT-**test2-ROLE_CLIENT-**test3-ROLE_CLIENT-**test4-ROLE_CLIENT-**-ROLE_CLIENT-**test-ROLE_ADMIN-**test-ROLE_ADMIN-*', '2020-04-14 01:32:30', 'Pending', 10),
(71, 'aa', 0, 'aa', 'aa', '2020-04-15 00:00:00', 'Pending', 7),
(72, 'aaaaaaaaaaaa', 0, 'aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa', 'aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa', '2020-04-15 00:00:00', 'Pending', 7),
(73, 'aaaaaaaaa', 0, 'aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa', 'aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabbbbaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabbbb*merco-ROLE_CLIENT-**message 2-ROLE_CLIENT-*', '2020-04-15 00:00:00', 'Pending', 7),
(74, 'Produit', 0, 'test', 'test*hello -ROLE_CLIENT-*', '2020-04-15 00:00:00', 'Pending', 7),
(76, 'Test', 0, 'Test', 'Test*bonjour-ROLE_CLIENT-*', '2020-04-15 00:00:00', 'Pending', 5),
(79, 'WebService', 0, 'This is just for test', 'merci ', '2020-04-15 00:00:00', 'Pending', 5);

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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `saison`
--

INSERT INTO `saison` (`idSaison`, `nom`, `periode`, `mode`) VALUES
(2, 'a', 'novembre decembre janiver', 'desert'),
(4, 'eteaaa', 'azed', 'aze'),
(5, 'printemps', 'sda', 'zsddd'),
(6, 'dzdza', 'aaa', 'aa'),
(7, 'dzdza', 'aaa', 'aa'),
(9, 'dzad', 'dzdaz', 'dzdaz');

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
  `username_canonical` varchar(180) COLLATE utf8_unicode_ci DEFAULT NULL,
  `email` varchar(180) COLLATE utf8_unicode_ci NOT NULL,
  `email_canonical` varchar(180) COLLATE utf8_unicode_ci DEFAULT NULL,
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
  `date` datetime DEFAULT NULL,
  `image` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UNIQ_8D93D649A0D96FBF` (`email_canonical`),
  UNIQUE KEY `UNIQ_8D93D64992FC23A8` (`username_canonical`),
  UNIQUE KEY `UNIQ_8D93D649C05FB297` (`confirmation_token`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `username`, `username_canonical`, `email`, `email_canonical`, `enabled`, `salt`, `password`, `last_login`, `confirmation_token`, `password_requested_at`, `roles`, `firstname`, `lastname`, `phone`, `date`, `image`) VALUES
(1, 'ilyessss', 'ilyessss', 'mohamedilyess.aouini@esprit.tn', 'mohamedilyess.aouini@esprit.tn', 0, NULL, '$2y$13$Ji5ZuIf9Qhl1xLHOWscqiOTlg3C4irtbITbjwzIP7evpweEdWkTzG', '2020-02-20 18:47:55', NULL, NULL, 'a:1:{i:0;s:11:\"ROLE_CLIENT\";}', 'Ilyess', 'Aouini', NULL, '0000-00-00 00:00:00', ''),
(2, 'aa', 'aa', 'aa@aa.aa', 'aa@aa.aa', 1, NULL, '$2y$13$ZrQCKxaSt9Ib7/PEOaN4Iu3b7CPBbKrFxfs8/8O8KK3CuCycHTQVi', '2020-04-14 01:31:09', NULL, NULL, 'a:1:{i:0;s:11:\"ROLE_CLIENT\";}', 'aa', 'aa', NULL, '0000-00-00 00:00:00', ''),
(3, 'bb', 'bb', 'bb@bb.bb', 'bb@bb.bb', 1, NULL, '$2y$13$V73lnorJ6uNj1D1EMtNn1OaZbDkXaCcySu/JZmex3bqkuEkmOO0Fy', '2020-04-14 01:31:19', NULL, NULL, 'a:1:{i:0;s:10:\"ROLE_ADMIN\";}', 'bb', 'bb', NULL, '0000-00-00 00:00:00', ''),
(4, 'cc', 'cc', 'affessalem@hotmail.fr', 'affessalem@hotmail.fr', 1, NULL, '$2y$13$m6f7DQ/SSHNLd9hVKUMMD.ppBI24HM4.xamr01a4FHosOQNwxgVoW', '2020-03-02 22:10:37', 'tF9ppy6GjvmRGzkmPUM-U4vcBLVk1iuavKnK1BhIP4c', NULL, 'a:1:{i:0;s:11:\"ROLE_CLIENT\";}', 'Salem', 'Affes', NULL, '2020-02-29 11:41:12', ''),
(5, 'dd', 'dd', 'dd@dd.dd', 'dd@dd.dd', 1, NULL, '$2y$13$ZArw1Kl7gEl/CFTVJT6rsO7nh2evboQwMk8jdAUqJFnICyiV0brJi', NULL, 'byK_-Nn8EHU2VAvfdkqL7EsSnf7irCZ7MKGq8JmuOQ4', NULL, 'a:1:{i:0;s:11:\"ROLE_CLIENT\";}', 'dd', 'dd', NULL, '2020-03-06 15:44:07', 'C:\\wamp64\\tmp\\phpB93B.tmp'),
(6, 'ee', 'ee', 'ee@ee.ee', 'ee@ee.ee', 1, NULL, '$2y$13$JHgjH8QCxYTyHIy5WgdDmet4O2d6nUZ20Isjcb.Ocoyp0qgQ0aKMK', '2020-03-06 16:36:57', 'Ss7Et597wWtwR_9-JY0qwRoL8-4YbrzFogvzlG462UU', NULL, 'a:1:{i:0;s:11:\"ROLE_CLIENT\";}', 'ee', 'ee', NULL, '2020-03-06 16:34:08', '90bea63b89162457b498d292219a3508.jpeg'),
(7, 'ff', 'ff', 'ff@ff.ff', 'ff@ff.ff', 1, NULL, '$2y$13$KEX2w1hix9uwrOE88YuTpOT3QQTb7V2a2iputQgtfOaz39JUUXQbO', '2020-03-14 10:57:18', 'JaEcajclV-IjZxD8WREneBVzrM9bllp5BHGQDJkn-fQ', NULL, 'a:1:{i:0;s:10:\"ROLE_ADMIN\";}', 'ff', 'ff', '55323442', '2020-03-06 16:49:31', '5abff9055a6dbab219ff4bc38f508537.jpeg'),
(10, 'bbb', 'bbb', 'bbb@esprit.tn', NULL, 1, NULL, '$2y$10$Sxv352zhsa2BJhk2Ro459.bd/0JF0oUrFeilfdCogCbF2S51YbQ8W', NULL, NULL, NULL, 'a:1:{i:0;s:11:\"ROLE_CLIENT\";}', NULL, 'bbb', '24318657', '2020-04-16 00:00:00', 'C:\\Users\\LQss\\Desktop\\joke\\bg.jpg'),
(12, 'ddd', NULL, 'ddd@ddd.ddd', NULL, 1, NULL, 'ddd', NULL, NULL, NULL, 'a:1:{i:0;s:11:\"ROLE_CLIENT\";}', NULL, 'ddd', '333', '2020-04-15 00:00:00', 'change photo'),
(13, 'admin', NULL, 'admin@esprit.tn', NULL, 0, NULL, '$2y$10$dHKMlegS2gSIJ2vDadqxl.VotgBS6Mk79bRaJ9b5obICIehjLN1BK', NULL, NULL, NULL, 'a:1:{i:0;s:11:\"ROLE_CLIENT\";}', NULL, 'okok', '222', '2020-04-15 00:00:00', 'change photo'),
(15, 'xxx', NULL, 'xxx@esprit.tn', NULL, 0, NULL, '$2y$10$vU1s6IRYe3z6h7eGUPsBW.n8VlFQD8cVj9cFgiHugnKF53o3ESr8a', NULL, NULL, NULL, 'a:1:{i:0;s:11:\"ROLE_CLIENT\";}', NULL, 'xxx', '22', '2020-04-01 00:00:00', 'SourcePackages/Image/back.jpg');

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `utilisateurs_adresses`
--

INSERT INTO `utilisateurs_adresses` (`id`, `utilisateur_id`, `nom`, `prenom`, `telephone`, `adresse`, `cp`, `pays`, `ville`, `complement`) VALUES
(1, 2, 'affes', 'salem', '55323442', 'affessalem@hotmail.fr', '3302', 'tunis', 'sfax', 'mamamam'),
(2, 3, 'aaa', 'aaa', '55323442', 'dzdaz@aa.dd', '32232', 'sfax', 'zdazd', 'dzda');

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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `whishlist`
--

INSERT INTO `whishlist` (`id`, `idProduit`, `idClient`) VALUES
(3, 2, 2),
(4, 4, 4);

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
  ADD CONSTRAINT `FK_67F068BCA6816575` FOREIGN KEY (`id_User`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FK_67F068BCB72EAA8E` FOREIGN KEY (`id_publication`) REFERENCES `publication` (`id`);

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
  ADD CONSTRAINT `FK_5E90F6D65E5C27E9` FOREIGN KEY (`iduser`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FK_5E90F6D6EDAB66BE` FOREIGN KEY (`idevent`) REFERENCES `evenement` (`id`);

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
  ADD CONSTRAINT `FK_AF3C6779A6816575` FOREIGN KEY (`id_User`) REFERENCES `user` (`id`);

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
