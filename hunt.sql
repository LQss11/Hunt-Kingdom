-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Jun 17, 2020 at 08:13 PM
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
) ENGINE=InnoDB AUTO_INCREMENT=457 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `categorie`
--

INSERT INTO `categorie` (`id`, `nom`, `description`, `image`) VALUES
(453, 'Clothes', 'This category will contain all kind of clothes required for either beginner or veteran hunters that could allow them to stay protected and wear attractive hunting clothes', 'c.jpg'),
(456, 'Tools', 'In this category you\'ll be able to find all kind of required tools such as hunting tools, electric tools etc. It will also provide a certain amoun of help for all hunters.', 't.jpg');

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
) ENGINE=InnoDB AUTO_INCREMENT=70 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `commandes`
--

INSERT INTO `commandes` (`id`, `utilisateur_id`, `valider`, `date`, `reference`, `commande`) VALUES
(68, 103, 1, '2020-06-17 12:12:13', 1, 'a:7:{s:3:\"tva\";a:1:{s:3:\"%10\";d:-220.5;}s:7:\"produit\";a:1:{i:23;a:5:{s:2:\"id\";i:23;s:9:\"reference\";s:28:\"Rocky Men 16 Inch Snake Boot\";s:8:\"quantite\";i:1;s:6:\"prixHT\";d:245;s:7:\"prixTTC\";d:24.5;}}s:9:\"livraison\";a:8:{s:6:\"prenom\";s:5:\"Salem\";s:3:\"nom\";s:5:\"Affes\";s:9:\"telephone\";s:8:\"55323442\";s:7:\"adresse\";s:19:\"Sfax rue tunis klm4\";s:2:\"cp\";s:4:\"3301\";s:5:\"ville\";s:4:\"Sfax\";s:4:\"pays\";s:5:\"Tunis\";s:10:\"complement\";s:42:\"Is this website well secured and uses SSL?\";}s:11:\"facturation\";a:8:{s:6:\"prenom\";s:5:\"Salem\";s:3:\"nom\";s:5:\"Affes\";s:9:\"telephone\";s:8:\"55323442\";s:7:\"adresse\";s:19:\"Sfax rue tunis klm4\";s:2:\"cp\";s:4:\"3301\";s:5:\"ville\";s:4:\"Sfax\";s:4:\"pays\";s:5:\"Tunis\";s:10:\"complement\";s:42:\"Is this website well secured and uses SSL?\";}s:6:\"prixHT\";d:245;s:7:\"prixTTC\";d:24.5;s:5:\"token\";s:40:\"58257209f0f872c5ba897aa625d3117527471ebe\";}'),
(69, 101, 1, '2020-06-17 12:20:14', 2, 'a:7:{s:3:\"tva\";a:1:{s:3:\"%10\";d:-382.5;}s:7:\"produit\";a:2:{i:22;a:5:{s:2:\"id\";i:22;s:9:\"reference\";s:31:\"Mossy Oak Cotton Mill Pants Men\";s:8:\"quantite\";i:1;s:6:\"prixHT\";d:75;s:7:\"prixTTC\";d:7.5;}i:25;a:5:{s:2:\"id\";i:25;s:9:\"reference\";s:16:\"Genesis Mini Bow\";s:8:\"quantite\";i:1;s:6:\"prixHT\";d:350;s:7:\"prixTTC\";d:35;}}s:9:\"livraison\";a:8:{s:6:\"prenom\";s:5:\"Salem\";s:3:\"nom\";s:5:\"Affes\";s:9:\"telephone\";s:8:\"55323442\";s:7:\"adresse\";s:19:\"Sfax rue tunis klm4\";s:2:\"cp\";s:4:\"3301\";s:5:\"ville\";s:4:\"Sfax\";s:4:\"pays\";s:5:\"Tunis\";s:10:\"complement\";s:42:\"Is this website well secured and uses SSL?\";}s:11:\"facturation\";a:8:{s:6:\"prenom\";s:5:\"Salem\";s:3:\"nom\";s:5:\"Affes\";s:9:\"telephone\";s:8:\"55323442\";s:7:\"adresse\";s:19:\"Sfax rue tunis klm4\";s:2:\"cp\";s:4:\"3301\";s:5:\"ville\";s:4:\"Sfax\";s:4:\"pays\";s:5:\"Tunis\";s:10:\"complement\";s:42:\"Is this website well secured and uses SSL?\";}s:6:\"prixHT\";d:425;s:7:\"prixTTC\";d:42.5;s:5:\"token\";s:40:\"856949564579dee63f46af5376c6e7645907e68d\";}');

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
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `commentaire`
--

INSERT INTO `commentaire` (`id`, `id_publication`, `contenu`, `dateComnt`, `id_User`) VALUES
(40, 50, 'Would be so thankful if anyone respond asap 😁😁😁😍  thanks.\r\n', '2020-06-17 12:45:17', 103),
(41, 50, 'Well you can start by looking at what pleases you exactly and also it depends on ur budget😁  thanks.\r\n', '2020-06-17 12:47:54', 102);

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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `com_produit`
--

INSERT INTO `com_produit` (`id`, `contenu`, `idProduit`, `idClient`) VALUES
(8, 'Wow looks fancy!!', 19, 102),
(9, 'Wow looks fancy!!', 19, 102);

-- --------------------------------------------------------

--
-- Table structure for table `espece`
--

DROP TABLE IF EXISTS `espece`;
CREATE TABLE IF NOT EXISTS `espece` (
  `idEspece` int(11) NOT NULL AUTO_INCREMENT,
  `nomEspece` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `descriptionEspece` varchar(1000) COLLATE utf8_unicode_ci NOT NULL,
  `image` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `poids` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `type` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `zone` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `ville` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `idS` int(11) DEFAULT NULL,
  PRIMARY KEY (`idEspece`),
  KEY `IDX_1A2A1B14BB48750` (`idS`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `espece`
--

INSERT INTO `espece` (`idEspece`, `nomEspece`, `descriptionEspece`, `image`, `poids`, `type`, `zone`, `ville`, `idS`) VALUES
(48, 'Deer', 'Deer are the hoofed ruminant mammals forming the family Cervidae.', '259716fc4dcd4dbebb696c3ecead3969.jpeg', '200Kg', 'Cervinae', 'In the Forests', 'Tunis', 26),
(51, 'Pig', 'Pigs include domestic pigs and their ancestor, the common Eurasian wild boar (Sus scrofa), along with other species.', 'eaf696e4281b2fbf2234b7da73e2ff5b.jpeg', '350Kg', 'Suidae', 'Jungle And forest', 'Tunis', 27),
(52, 'Camel', 'Camels have long been domesticated and, as livestock, they provide food (milk and meat) and textiles (fiber and felt from hair).', 'b56bdacd7e0b799a59c3d2ab7e173f94.jpeg', '600Kg', 'Camelids', 'Sahara', 'Tozeur', 27);

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
  `description` varchar(3000) COLLATE utf8mb4_unicode_ci NOT NULL,
  `duree` int(11) NOT NULL,
  `image` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `evenement`
--

INSERT INTO `evenement` (`id`, `nom`, `type`, `prix`, `nbrplace`, `date`, `place`, `description`, `duree`, `image`) VALUES
(17, 'The Hunting Kings', 'Hunting in groups', 30, 20, '2020-06-26 00:00:00', 'Ain Draham', 'In this Event you will be able to meet many people from all around Tunisia that has same interest seeking to become one of the top hunters and win the prize of this even.\r\nPrize will be 200 TND.\r\nEach team must contain at maximum 3 members.\r\ntop hunts wins.', 6, '81049e556baf5b930b22cade96bba3fc.jpeg'),
(20, 'Archer Pros', 'Formation', 45, 35, '2020-06-29 00:00:00', 'Tunis-Lac1', 'We are going to teach you all the important techniques about Bows which model to buy, how to practice it, pros and cons and how to become a professional bow hunter in a short period of time.\r\nDo not hesitate, join us now you are welcome.', 4, 'acbdc86677b0ff47e0681f3905b42238.png');

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
) ENGINE=InnoDB AUTO_INCREMENT=147 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `feedback`
--

INSERT INTO `feedback` (`id`, `description`, `rate`, `date`, `idU`) VALUES
(143, '', 5, '2020-06-16 00:00:00', 2),
(144, 'I really like this website i enjoy it all the time thank you for such a great product.', 5, '2020-06-04 00:00:00', 103),
(145, 'What a bad Website improve it a little at least ....!!!', 1, '2020-06-01 00:00:00', 101),
(146, 'Keep up the good work', 4, '2020-06-17 11:08:05', 102);

-- --------------------------------------------------------

--
-- Table structure for table `inscription`
--

DROP TABLE IF EXISTS `inscription`;
CREATE TABLE IF NOT EXISTS `inscription` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idevent` int(11) DEFAULT NULL,
  `iduser` int(11) DEFAULT NULL,
  `rate` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  KEY `IDX_5E90F6D6EDAB66BE` (`idevent`),
  KEY `IDX_5E90F6D65E5C27E9` (`iduser`)
) ENGINE=InnoDB AUTO_INCREMENT=128 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `notifiable_entity`
--

INSERT INTO `notifiable_entity` (`id`, `identifier`, `class`) VALUES
(6, '102', 'MainBundle\\Entity\\User');

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
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `notifiable_notification`
--

INSERT INTO `notifiable_notification` (`id`, `notification_id`, `notifiable_entity_id`, `seen`) VALUES
(12, 12, 6, 0);

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
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `notification`
--

INSERT INTO `notification` (`id`, `date`, `subject`, `message`, `link`) VALUES
(12, '2020-06-17 12:44:43', 'nouvelle publication', 'This a notification.', NULL);

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
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `produit`
--

INSERT INTO `produit` (`id`, `categorie`, `nom`, `quantite`, `description`, `prix`, `idFournisseur`, `etatPromo`, `garantie`, `image`, `tvaId`) VALUES
(19, 453, 'Brand Clothing Tactical Jacket', 120, 'A fancy jacket with acceptable price, available in multiple colors.', 50, 0, 0, 0, 'Brand Clothing Tactical Jacket.jpg', 1),
(22, 453, 'Mossy Oak Cotton Mill Pants Men', 62, 'A pant that will keep you warm even in the coldest fields, comfy and resonable price.', 75, 0, 1, 1, 'Mossy Oak Cotton Mill Pants Men.jpg', 1),
(23, 453, 'Rocky Men 16 Inch Snake Boot', 11, 'Suitable boot for daily hunting usage, comfortable clothing.', 245, 0, 0, 0, 'Rocky Men 16 Inch Snake Boot.jpg', 1),
(24, 456, 'Brown Wood Knife', 200, 'Sharpest knifes.', 20, 0, 0, 0, 'Brown Wood Knife.jpg', 1),
(25, 456, 'Genesis Mini Bow', 19, 'Highest Bow quality get it now!!!', 350, 0, 0, 2, 'Genesis Mini Bow.jpg', 1),
(26, 456, 'Bow Shafts', 100, 'Premium pack of 10 bow shafts.', 90, 0, 0, 1, 'pack 10 shafts pack.JPG', 1),
(27, 456, 'Pocket Knife Toucans D2 Blade G10 Handle Folding', 70, 'Simple folding knife for everyday use.', 70, 0, 0, 1, 'Pocket Knife Toucans D2 Blade G10 Handle Folding.jpg', 1);

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
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `promotion`
--

INSERT INTO `promotion` (`id`, `dateFin`, `dateDebut`, `prix`, `pourcentage`, `active`, `idProduit`) VALUES
(13, '2020-09-17 00:00:00', '2020-06-17 00:00:00', 37.5, 50, 1, 22);

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
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `publication`
--

INSERT INTO `publication` (`id`, `type`, `contenu`, `datePublication`, `id_User`) VALUES
(50, 'd701e77239fcdb820a9c2ff3a04123c0.jpeg', 'Hello everyone I have been trying to learn how collect as much hunting tools as possible, i don\'t know where to start can anybody help please.', '2020-06-17 12:44:42', 103);

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
  `description` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `date` datetime NOT NULL,
  `etat` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `idU` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_CE606404A2D72265` (`idU`)
) ENGINE=InnoDB AUTO_INCREMENT=162 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `reclamation`
--

INSERT INTO `reclamation` (`id`, `type`, `ido`, `sujet`, `description`, `date`, `etat`, `idU`) VALUES
(150, 'Bug', 0, 'Min problem', 'I would like to know why i always encounter the error as the i try to minimize my screen*Which Browser are you using-ROLE_ADMIN-**And when this happened exactly-ROLE_ADMIN-*', '2020-02-01 00:00:00', 'Done', 2),
(152, 'WebService', 0, 'refresh error', 'The Website Keeps on Bugging all the time whenever i click on refresh*We are taking this in considiration-ROLE_ADMIN-*', '2020-06-10 00:00:00', 'Accepted', 101),
(154, 'Product', 25, 'Price', 'This Product is too expensive can you reduce its price it s unbelievable', '2020-06-16 00:00:00', 'Rejected', 101),
(155, 'WebService', 0, 'Mobile', 'I tried to use huntkingdom on my mobilephone seems a bit different.*Thank you for answering please.-ROLE_CLIENT-*', '2020-06-17 10:48:56', 'Pending', 103),
(161, 'Product', 25, 'Delivery', 'Hello I recently moved to France and i would like to be able to buy products from here it s impossible...', '2020-04-22 00:00:00', 'Accepted', 103);

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
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `saison`
--

INSERT INTO `saison` (`idSaison`, `nom`, `periode`, `mode`) VALUES
(26, 'Spring', 'March-June', 'Safe.'),
(27, 'Summer', 'June-September', 'Hot and safe for hunting');

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
) ENGINE=InnoDB AUTO_INCREMENT=104 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `username`, `username_canonical`, `email`, `email_canonical`, `enabled`, `salt`, `password`, `last_login`, `confirmation_token`, `password_requested_at`, `roles`, `firstname`, `lastname`, `phone`, `date`, `image`) VALUES
(2, 'aa', 'aa', 'aa@aa.aa', 'aa@aa.aa', 1, NULL, '$2y$13$ZrQCKxaSt9Ib7/PEOaN4Iu3b7CPBbKrFxfs8/8O8KK3CuCycHTQVi', '2020-06-16 02:28:43', NULL, NULL, 'a:1:{i:0;s:11:\"ROLE_CLIENT\";}', 'aa', 'aa', '24318657', '2020-04-08 00:00:00', '5abff9055a6dbab219ff4bc38f508537.jpeg'),
(3, 'bb', 'bb', 'bb@bb.bb', 'bb@bb.bb', 1, NULL, '$2y$13$V73lnorJ6uNj1D1EMtNn1OaZbDkXaCcySu/JZmex3bqkuEkmOO0Fy', '2020-05-17 02:55:51', NULL, NULL, 'a:1:{i:0;s:10:\"ROLE_ADMIN\";}', 'bb', 'bb', '47', '0000-00-00 00:00:00', '5abff9055a6dbab219ff4bc38f508537.jpeg'),
(101, 'LQss', 'lqss', 'affessalem@hotmail.fr', 'affessalem@hotmail.fr', 1, NULL, '$2y$13$mPCi0JBhORVwmhmxmDumYeU0EnznqGHh3OToUHgEGF9A1F4vHsARq', NULL, 'TpEP0_Cjls74CTbbCEXkx99axZK-sF_INY6axcfO7hs', NULL, 'a:1:{i:0;s:11:\"ROLE_CLIENT\";}', 'Affes', 'Salem', '55323442', '2020-06-17 07:58:18', '45c077c802f104065a5dcbe9036d881a.jpeg'),
(102, 'admin', 'admin', 'admin@esprit.tn', 'admin@esprit.tn', 1, NULL, '$2y$13$dnVVFt6k8hjBPoz6YUyq..loltC0U6hOhtVZHMuKw/KxTYWyxrRAC', '2020-06-17 11:06:54', '4N924Hf7A4e1hCDnKK4WwMvzhERr76UnYDPK6feI0r0', NULL, 'a:1:{i:0;s:10:\"ROLE_ADMIN\";}', 'Admin', 'Admin', '99000000', '2020-06-17 08:02:49', '7876ace0b1d4b4bd02fb7b586ba5ec94.jpeg'),
(103, 'Chagra', 'chagra', 'chagra@esprit.tn', 'chagra@esprit.tn', 1, NULL, '$2y$13$HMb33TEmtxyD0dOdrA840OzDWsHIavB5m9.S94fziKxfqZ0iP42l2', '2020-06-17 20:07:39', 'tX-vIHCs-ixQBQcJ2vx-2kwKiv6VHp613Nsw1u_3buA', NULL, 'a:1:{i:0;s:11:\"ROLE_CLIENT\";}', 'Mohamed', 'Chakroun', '55332266', '2020-06-17 10:46:15', '72a0f18a5619ae45836b1c4699960403.jpeg');

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
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `utilisateurs_adresses`
--

INSERT INTO `utilisateurs_adresses` (`id`, `utilisateur_id`, `nom`, `prenom`, `telephone`, `adresse`, `cp`, `pays`, `ville`, `complement`) VALUES
(17, 102, 'Affes', 'Salem', '55323442', 'Sfax rue tunis klm4', '3301', 'Tunis', 'Sfax', 'Is this website well secured and uses SSL?');

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
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `commandes`
--
ALTER TABLE `commandes`
  ADD CONSTRAINT `FK_35D4282CFB88E14F` FOREIGN KEY (`utilisateur_id`) REFERENCES `user` (`id`) ON DELETE CASCADE;

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
  ADD CONSTRAINT `FK_1A2A1B14BB48750` FOREIGN KEY (`idS`) REFERENCES `saison` (`idSaison`) ON DELETE CASCADE;

--
-- Constraints for table `feedback`
--
ALTER TABLE `feedback`
  ADD CONSTRAINT `FK_D2294458A2D72265` FOREIGN KEY (`idU`) REFERENCES `user` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `inscription`
--
ALTER TABLE `inscription`
  ADD CONSTRAINT `FK_5E90F6D65E5C27E9` FOREIGN KEY (`iduser`) REFERENCES `user` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `FK_5E90F6D6EDAB66BE` FOREIGN KEY (`idevent`) REFERENCES `evenement` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `notifiable_notification`
--
ALTER TABLE `notifiable_notification`
  ADD CONSTRAINT `FK_ADCFE0FAC3A0A4F8` FOREIGN KEY (`notifiable_entity_id`) REFERENCES `notifiable_entity` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `FK_ADCFE0FAEF1A9D84` FOREIGN KEY (`notification_id`) REFERENCES `notification` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `produit`
--
ALTER TABLE `produit`
  ADD CONSTRAINT `FK_29A5EC27497DD634` FOREIGN KEY (`categorie`) REFERENCES `categorie` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `FK_29A5EC27A655BE48` FOREIGN KEY (`tvaId`) REFERENCES `tva` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `promotion`
--
ALTER TABLE `promotion`
  ADD CONSTRAINT `FK_C11D7DD1391C87D5` FOREIGN KEY (`idProduit`) REFERENCES `produit` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `publication`
--
ALTER TABLE `publication`
  ADD CONSTRAINT `FK_AF3C6779A6816575` FOREIGN KEY (`id_User`) REFERENCES `user` (`id`) ON DELETE CASCADE;

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
  ADD CONSTRAINT `FK_CE606404A2D72265` FOREIGN KEY (`idU`) REFERENCES `user` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `utilisateurs_adresses`
--
ALTER TABLE `utilisateurs_adresses`
  ADD CONSTRAINT `FK_F4167640FB88E14F` FOREIGN KEY (`utilisateur_id`) REFERENCES `user` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `whishlist`
--
ALTER TABLE `whishlist`
  ADD CONSTRAINT `FK_2E936C6D391C87D5` FOREIGN KEY (`idProduit`) REFERENCES `produit` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `FK_2E936C6DA455ACCF` FOREIGN KEY (`idClient`) REFERENCES `user` (`id`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
