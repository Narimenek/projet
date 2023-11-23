-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : jeu. 23 nov. 2023 à 17:56
-- Version du serveur : 10.4.27-MariaDB
-- Version de PHP : 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `luxedrive`
--

-- --------------------------------------------------------

--
-- Structure de la table `chauffeurs`
--

CREATE TABLE `chauffeurs` (
  `numChauffeur` int(11) NOT NULL,
  `nomChauffeur` varchar(20) DEFAULT NULL,
  `Prenom` varchar(20) DEFAULT NULL,
  `dateDeNaissance` date DEFAULT NULL,
  `idUtilisateur` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Déchargement des données de la table `chauffeurs`
--

INSERT INTO `chauffeurs` (`numChauffeur`, `nomChauffeur`, `Prenom`, `dateDeNaissance`, `idUtilisateur`) VALUES
(1, 'Dupont', 'Jean', '1980-01-15', NULL),
(2, 'Martin', 'Sophie', '1975-07-20', NULL),
(3, 'Dubois', 'Paul', '1985-04-05', NULL),
(4, 'Lefebvre', 'Isabelle', '1990-11-10', NULL),
(5, 'Perrin', 'François', '1978-03-25', NULL),
(6, 'Dufresne', 'Marie', '1982-08-30', NULL),
(7, 'Leroy', 'Jacques', '1984-06-15', NULL),
(8, 'Girard', 'Anne', '1986-02-07', NULL),
(9, 'Fontaine', 'Luc', '1988-09-12', NULL),
(10, 'Moreau', 'Céline', '1987-12-18', NULL),
(11, 'Dumas', 'Thierry', '1981-10-22', NULL),
(12, 'Gagné', 'Caroline', '1977-05-02', NULL),
(13, 'Tremblay', 'Robert', '1983-09-03', NULL),
(14, 'Bertrand', 'Émilie', '1979-07-28', NULL),
(15, 'Poirier', 'Philippe', '1989-04-14', NULL),
(16, 'Bouchard', 'Nathalie', '1991-03-08', NULL),
(17, 'Lavoie', 'Louis', '1985-11-12', NULL),
(18, 'Gauthier', 'Julie', '1984-01-23', NULL),
(19, 'Beaulieu', 'Michel', '1976-06-17', NULL),
(20, 'Roberts', 'Linda', '1980-12-30', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `client`
--

CREATE TABLE `client` (
  `idClient` int(11) NOT NULL,
  `nomClient` varchar(20) DEFAULT NULL,
  `tel` varchar(20) DEFAULT NULL,
  `email` varchar(20) DEFAULT NULL,
  `motDePasse` varchar(20) DEFAULT NULL,
  `idUtilisateur` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `disponibilite`
--

CREATE TABLE `disponibilite` (
  `id_voiture` int(11) DEFAULT NULL,
  `date_disponible` date NOT NULL,
  `disponible` varchar(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `disponibilite`
--

INSERT INTO `disponibilite` (`id_voiture`, `date_disponible`, `disponible`) VALUES
(1, '2024-01-01', 'oui'),
(1, '2024-01-02', 'non'),
(1, '2024-01-03', 'oui'),
(1, '2024-01-04', 'non'),
(1, '2024-01-05', 'oui'),
(1, '2024-01-06', 'non'),
(1, '2024-01-07', 'oui'),
(1, '2024-01-08', 'non'),
(1, '2024-01-09', 'oui'),
(1, '2024-01-10', 'non'),
(1, '2024-01-11', 'oui'),
(1, '2024-01-12', 'non'),
(1, '2024-01-13', 'oui'),
(1, '2024-01-14', 'non'),
(1, '2024-01-15', 'oui'),
(1, '2024-01-16', 'non'),
(1, '2024-01-17', 'oui'),
(1, '2024-01-18', 'non'),
(1, '2024-01-19', 'oui'),
(1, '2024-01-20', 'non'),
(1, '2024-01-21', 'oui'),
(1, '2024-01-22', 'non'),
(1, '2024-01-23', 'oui'),
(1, '2024-01-24', 'non'),
(1, '2024-01-25', 'oui'),
(1, '2024-01-26', 'non'),
(1, '2024-01-27', 'oui'),
(1, '2024-01-28', 'non'),
(1, '2024-01-29', 'oui'),
(1, '2024-01-30', 'non'),
(1, '2024-01-31', 'oui'),
(2, '2024-01-01', 'oui'),
(2, '2024-01-02', 'non'),
(2, '2024-01-03', 'oui'),
(2, '2024-01-04', 'non'),
(2, '2024-01-05', 'oui'),
(2, '2024-01-06', 'non'),
(2, '2024-01-07', 'oui'),
(2, '2024-01-08', 'non'),
(2, '2024-01-09', 'oui'),
(2, '2024-01-10', 'non'),
(2, '2024-01-11', 'oui'),
(2, '2024-01-12', 'non'),
(2, '2024-01-13', 'oui'),
(2, '2024-01-14', 'non'),
(2, '2024-01-15', 'oui'),
(2, '2024-01-16', 'non'),
(2, '2024-01-17', 'oui'),
(2, '2024-01-18', 'non'),
(2, '2024-01-19', 'oui'),
(2, '2024-01-20', 'non'),
(2, '2024-01-21', 'oui'),
(2, '2024-01-22', 'non'),
(2, '2024-01-23', 'oui'),
(2, '2024-01-24', 'non'),
(2, '2024-01-25', 'oui'),
(2, '2024-01-26', 'non'),
(2, '2024-01-27', 'oui'),
(2, '2024-01-28', 'non'),
(2, '2024-01-29', 'oui'),
(2, '2024-01-30', 'non'),
(2, '2024-01-31', 'oui'),
(3, '2024-01-01', 'oui'),
(3, '2024-01-02', 'non'),
(3, '2024-01-03', 'oui'),
(3, '2024-01-04', 'non'),
(3, '2024-01-05', 'oui'),
(3, '2024-01-06', 'non'),
(3, '2024-01-07', 'oui'),
(3, '2024-01-08', 'non'),
(3, '2024-01-09', 'oui'),
(3, '2024-01-10', 'non'),
(3, '2024-01-11', 'oui'),
(3, '2024-01-12', 'non'),
(3, '2024-01-13', 'oui'),
(3, '2024-01-14', 'non'),
(3, '2024-01-15', 'oui'),
(3, '2024-01-16', 'non'),
(3, '2024-01-17', 'oui'),
(3, '2024-01-18', 'non'),
(3, '2024-01-19', 'oui'),
(3, '2024-01-20', 'non'),
(3, '2024-01-21', 'oui'),
(3, '2024-01-22', 'non'),
(3, '2024-01-23', 'oui'),
(3, '2024-01-24', 'non'),
(3, '2024-01-25', 'oui'),
(3, '2024-01-26', 'non'),
(3, '2024-01-27', 'oui'),
(3, '2024-01-28', 'non'),
(3, '2024-01-29', 'oui'),
(3, '2024-01-30', 'non'),
(3, '2024-01-31', 'oui'),
(4, '2024-01-01', 'oui'),
(4, '2024-01-02', 'non'),
(4, '2024-01-03', 'oui'),
(4, '2024-01-04', 'non'),
(4, '2024-01-05', 'oui'),
(4, '2024-01-06', 'non'),
(4, '2024-01-07', 'oui'),
(4, '2024-01-08', 'non'),
(4, '2024-01-09', 'oui'),
(4, '2024-01-10', 'non'),
(4, '2024-01-11', 'oui'),
(4, '2024-01-12', 'non'),
(4, '2024-01-13', 'oui'),
(4, '2024-01-14', 'non'),
(4, '2024-01-15', 'oui'),
(4, '2024-01-16', 'non'),
(4, '2024-01-17', 'oui'),
(4, '2024-01-18', 'non'),
(4, '2024-01-19', 'oui'),
(4, '2024-01-20', 'non'),
(4, '2024-01-21', 'oui'),
(4, '2024-01-22', 'non'),
(4, '2024-01-23', 'oui'),
(4, '2024-01-24', 'non'),
(4, '2024-01-25', 'oui'),
(4, '2024-01-26', 'non'),
(4, '2024-01-27', 'oui'),
(4, '2024-01-28', 'non'),
(4, '2024-01-29', 'oui'),
(4, '2024-01-30', 'non'),
(4, '2024-01-31', 'oui'),
(5, '2024-01-01', 'non'),
(5, '2024-01-02', 'oui'),
(5, '2024-01-03', 'non'),
(5, '2024-01-04', 'oui'),
(5, '2024-01-05', 'non'),
(6, '2024-01-01', 'oui'),
(6, '2024-01-02', 'non'),
(6, '2024-01-03', 'oui'),
(6, '2024-01-04', 'non'),
(6, '2024-01-05', 'oui'),
(7, '2024-01-01', 'non'),
(7, '2024-01-02', 'oui'),
(7, '2024-01-03', 'non'),
(7, '2024-01-04', 'oui'),
(7, '2024-01-05', 'non'),
(8, '2024-01-01', 'oui'),
(8, '2024-01-02', 'non'),
(8, '2024-01-03', 'oui'),
(8, '2024-01-04', 'non'),
(8, '2024-01-05', 'oui'),
(9, '2024-01-01', 'non'),
(9, '2024-01-02', 'oui'),
(9, '2024-01-03', 'non'),
(9, '2024-01-04', 'oui'),
(9, '2024-01-05', 'non'),
(10, '2024-01-01', 'oui'),
(10, '2024-01-02', 'non'),
(10, '2024-01-03', 'oui'),
(10, '2024-01-04', 'non'),
(10, '2024-01-05', 'oui'),
(11, '2024-01-01', 'non'),
(11, '2024-01-02', 'oui'),
(11, '2024-01-03', 'non'),
(11, '2024-01-04', 'oui'),
(11, '2024-01-05', 'non'),
(12, '2024-01-01', 'oui'),
(12, '2024-01-02', 'non'),
(12, '2024-01-03', 'oui'),
(12, '2024-01-04', 'non'),
(12, '2024-01-05', 'oui'),
(13, '2024-01-01', 'non'),
(13, '2024-01-02', 'oui'),
(13, '2024-01-03', 'non'),
(13, '2024-01-04', 'oui'),
(13, '2024-01-05', 'non'),
(14, '2024-01-01', 'oui'),
(14, '2024-01-02', 'non'),
(14, '2024-01-03', 'oui'),
(14, '2024-01-04', 'non'),
(14, '2024-01-05', 'oui'),
(15, '2024-01-01', 'non'),
(15, '2024-01-02', 'oui'),
(15, '2024-01-03', 'non'),
(15, '2024-01-04', 'oui'),
(15, '2024-01-05', 'non'),
(16, '2024-01-01', 'oui'),
(16, '2024-01-02', 'non'),
(16, '2024-01-03', 'oui'),
(16, '2024-01-04', 'non'),
(16, '2024-01-05', 'oui'),
(17, '2024-01-01', 'non'),
(17, '2024-01-02', 'oui'),
(17, '2024-01-03', 'non'),
(17, '2024-01-04', 'oui'),
(17, '2024-01-05', 'non'),
(18, '2024-01-01', 'oui'),
(18, '2024-01-02', 'non'),
(18, '2024-01-03', 'oui'),
(18, '2024-01-04', 'non'),
(18, '2024-01-05', 'oui'),
(19, '2024-01-01', 'non'),
(19, '2024-01-02', 'oui'),
(19, '2024-01-03', 'non'),
(19, '2024-01-04', 'oui'),
(19, '2024-01-05', 'non'),
(20, '2024-01-01', 'oui'),
(20, '2024-01-02', 'non'),
(20, '2024-01-03', 'oui'),
(20, '2024-01-04', 'non'),
(20, '2024-01-05', 'oui'),
(21, '2024-01-01', 'non'),
(21, '2024-01-02', 'oui'),
(21, '2024-01-03', 'non'),
(21, '2024-01-04', 'oui'),
(21, '2024-01-05', 'non'),
(22, '2024-01-01', 'oui'),
(22, '2024-01-02', 'non'),
(22, '2024-01-03', 'oui'),
(22, '2024-01-04', 'non'),
(22, '2024-01-05', 'oui'),
(23, '2024-01-01', 'non'),
(23, '2024-01-02', 'oui'),
(23, '2024-01-03', 'non'),
(23, '2024-01-04', 'oui'),
(23, '2024-01-05', 'non'),
(24, '2024-01-01', 'oui'),
(24, '2024-01-02', 'non'),
(24, '2024-01-03', 'oui'),
(24, '2024-01-04', 'non'),
(24, '2024-01-05', 'oui'),
(25, '2024-01-01', 'non'),
(25, '2024-01-02', 'oui'),
(25, '2024-01-03', 'non'),
(25, '2024-01-04', 'oui'),
(25, '2024-01-05', 'non'),
(26, '2024-01-01', 'oui'),
(26, '2024-01-02', 'non'),
(26, '2024-01-03', 'oui'),
(26, '2024-01-04', 'non'),
(26, '2024-01-05', 'oui'),
(27, '2024-01-01', 'non'),
(27, '2024-01-02', 'oui'),
(27, '2024-01-03', 'non'),
(27, '2024-01-04', 'oui'),
(27, '2024-01-05', 'non'),
(28, '2024-01-01', 'oui'),
(28, '2024-01-02', 'non'),
(28, '2024-01-03', 'oui'),
(28, '2024-01-04', 'non'),
(28, '2024-01-05', 'oui'),
(29, '2024-01-01', 'non'),
(29, '2024-01-02', 'oui'),
(29, '2024-01-03', 'non'),
(29, '2024-01-04', 'oui'),
(29, '2024-01-05', 'non'),
(30, '2024-01-01', 'oui'),
(30, '2024-01-02', 'non'),
(30, '2024-01-03', 'oui'),
(30, '2024-01-04', 'non'),
(30, '2024-01-05', 'oui'),
(31, '2024-01-01', 'non'),
(31, '2024-01-02', 'oui'),
(31, '2024-01-03', 'non'),
(31, '2024-01-04', 'oui'),
(31, '2024-01-05', 'non'),
(32, '2024-01-01', 'oui'),
(32, '2024-01-02', 'non'),
(32, '2024-01-03', 'oui'),
(32, '2024-01-04', 'non'),
(32, '2024-01-05', 'oui'),
(33, '2024-01-01', 'non'),
(33, '2024-01-02', 'oui'),
(33, '2024-01-03', 'non'),
(33, '2024-01-04', 'oui'),
(33, '2024-01-05', 'non'),
(34, '2024-01-01', 'oui'),
(34, '2024-01-02', 'non'),
(34, '2024-01-03', 'oui'),
(34, '2024-01-04', 'non'),
(34, '2024-01-05', 'oui'),
(35, '2024-01-01', 'non'),
(35, '2024-01-02', 'oui'),
(35, '2024-01-03', 'non'),
(35, '2024-01-04', 'oui'),
(35, '2024-01-05', 'non'),
(36, '2024-01-01', 'oui'),
(36, '2024-01-02', 'non'),
(36, '2024-01-03', 'oui'),
(36, '2024-01-04', 'non'),
(36, '2024-01-05', 'oui'),
(37, '2024-01-01', 'non'),
(37, '2024-01-02', 'oui'),
(37, '2024-01-03', 'non'),
(37, '2024-01-04', 'oui'),
(37, '2024-01-05', 'non'),
(38, '2024-01-01', 'oui'),
(38, '2024-01-02', 'non'),
(38, '2024-01-03', 'oui'),
(38, '2024-01-04', 'non'),
(38, '2024-01-05', 'oui'),
(39, '2024-01-01', 'non'),
(39, '2024-01-02', 'oui'),
(39, '2024-01-03', 'non'),
(39, '2024-01-04', 'oui'),
(39, '2024-01-05', 'non'),
(40, '2024-01-01', 'oui'),
(40, '2024-01-02', 'non'),
(40, '2024-01-03', 'oui'),
(40, '2024-01-04', 'non'),
(40, '2024-01-05', 'oui'),
(41, '2024-01-01', 'non'),
(41, '2024-01-02', 'oui'),
(41, '2024-01-03', 'non'),
(41, '2024-01-04', 'oui'),
(41, '2024-01-05', 'non');

-- --------------------------------------------------------

--
-- Structure de la table `entreprise`
--

CREATE TABLE `entreprise` (
  `numEntreprise` int(11) NOT NULL,
  `NomEntreprise` varchar(20) DEFAULT NULL,
  `idClient` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `location`
--

CREATE TABLE `location` (
  `idLocation` int(11) NOT NULL,
  `dateDebut` date NOT NULL,
  `dateFin` date NOT NULL,
  `avecChauffeur` tinyint(1) NOT NULL,
  `numChauffeur` varchar(20) NOT NULL,
  `idClient` varchar(20) NOT NULL,
  `idVoiture` int(11) NOT NULL,
  `StatutLocation` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `marque`
--

CREATE TABLE `marque` (
  `idMarque` int(11) NOT NULL,
  `nomMarque` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `paiement`
--

CREATE TABLE `paiement` (
  `idPaiement` int(11) NOT NULL,
  `Montant` decimal(10,2) DEFAULT NULL,
  `dateDePaiement` date DEFAULT NULL,
  `idLocation` int(11) DEFAULT NULL,
  `typeDePaiement` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `particuliers`
--

CREATE TABLE `particuliers` (
  `idParticulier` int(11) NOT NULL,
  `Prénom` varchar(20) DEFAULT NULL,
  `dateDeNaissance` date DEFAULT NULL,
  `Métier` varchar(20) DEFAULT NULL,
  `idClient` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `utilisateurs`
--

CREATE TABLE `utilisateurs` (
  `idUtilisateur` int(11) NOT NULL,
  `email_utilisateur` varchar(255) NOT NULL,
  `mdp` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `vendeur`
--

CREATE TABLE `vendeur` (
  `idVendeur` int(11) NOT NULL,
  `Nom` varchar(20) DEFAULT NULL,
  `Prenom` varchar(20) DEFAULT NULL,
  `adresse` varchar(20) DEFAULT NULL,
  `tel` varchar(20) DEFAULT NULL,
  `dateDeNaissance` date DEFAULT NULL,
  `idUtilisateur` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `voituredeluxe`
--

CREATE TABLE `voituredeluxe` (
  `idVoiture` int(11) NOT NULL,
  `Couleur` varchar(10) NOT NULL,
  `immatriculation` varchar(10) NOT NULL,
  `marque` varchar(30) NOT NULL,
  `prixLocation` float NOT NULL,
  `lien_img` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Déchargement des données de la table `voituredeluxe`
--

INSERT INTO `voituredeluxe` (`idVoiture`, `Couleur`, `immatriculation`, `marque`, `prixLocation`, `lien_img`) VALUES
(1, 'Blanc', 'BNL130', 'BENTLEY CONTINENTAL GTC', 1300, '../imagesv/bnl.jpg'),
(2, 'Jaune', 'LML2126', 'LAMBORGHINI HURACAN SPYDER', 1310, '../imagesv/laml.jpg'),
(3, 'Rouge', 'MCL100', 'MCLAREN MP4 12C', 1000, '../imagesv/mclr.jpg'),
(4, 'Noir', 'AST110', 'ASTON MARTIN VANQUISH VOLANTE', 1100, '../imagesv/as.jpg'),
(5, 'Rouge', 'FR102', 'FERRARI 458 SPECIALE', 1020, '../imagesv/fr.jpg'),
(6, 'Blanc', 'MS100', 'MASERATI GRANTURISMO S', 1000, '../imagesv/ms.jpg'),
(7, 'Jaune', 'FRR110', 'FERRARI 488 GTB', 1100, '../imagesv/gb.jpg'),
(8, 'Marron', 'BMW269', 'BMW I8', 1120, '../imagesv/Bmw.jpg'),
(9, 'Bleu', 'AUD820', 'AUDI R8 SPYDER', 1050, '../imagesv/ar8v.jpg'),
(10, 'Blanc', 'RLS150', 'ROLLS ROYCE DROPHEAD', 1500, '../imagesv/rl.jpg'),
(11, 'Orange', 'MCL650', 'MCLAREN 650S SPIDER', 1000, '../imagesv/mcl.jpg'),
(12, 'Blanc', 'VLR500', 'RANGE ROVER VELAR', 500, '../imagesv/vl.jpg'),
(13, 'Rouge', 'FRS812', 'FERRARI 812 SUPERFAST', 1030, '../imagesv/fr.jpg'),
(14, 'Bleu fancé', 'MAS400', 'MASERATI LIVANTE', 400, '../imagesv/msl.jpg'),
(15, 'Gris', 'PRS911', 'PORSCHE 911 TURBO CABRIOLET', 1200, '../imagesv/prl.jpg'),
(16, 'Noir', 'MRS350', 'MERCEDES CLASSE E', 350, '../imagesv/me.png'),
(17, 'Noir', 'MS450', 'MERCEDES CLASSE S 600', 450, '../imagesv/ms.png'),
(18, 'Noir', 'MRS500', 'MERCEDES CLASSE S 400', 400, '../imagesv/m500.png'),
(19, 'Gris', 'MRv650', 'MERCEDES CLASSE V ', 650, '../imagesv/mv.png'),
(20, 'Noir', 'RGR500', 'RANGE ROVER SPORT', 500, '../imagesv/vr.jpeg'),
(21, 'Bleu', 'BNT550', 'BENTLEY FLYING SPUR', 550, '../imagesv/bf.png'),
(22, 'Noir', 'RLS900', 'ROLLS ROYCE PHANTOM ', 900, '../imagesv/rs.png'),
(30, 'Vert', 'YZA123', 'Porsche Panamera', 1300, '../imagesv/prp.jpg'),
(24, 'Jaune', 'PQR123', 'Lamborghini Urus', 1400, '../imagesv/lamu.jpg'),
(25, 'Gris', 'JKL456', 'Audi RS7', 950, '../imagesv/rs7.jpg'),
(26, 'Bleu', 'BGC260', 'Bugatti Chiron', 2600, '../imagesv/Bc.jpg');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `chauffeurs`
--
ALTER TABLE `chauffeurs`
  ADD PRIMARY KEY (`numChauffeur`),
  ADD KEY `idUtilisateur` (`idUtilisateur`);

--
-- Index pour la table `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`idClient`),
  ADD KEY `idUtilisateur` (`idUtilisateur`);

--
-- Index pour la table `entreprise`
--
ALTER TABLE `entreprise`
  ADD PRIMARY KEY (`numEntreprise`),
  ADD KEY `idClient` (`idClient`);

--
-- Index pour la table `location`
--
ALTER TABLE `location`
  ADD PRIMARY KEY (`idLocation`);

--
-- Index pour la table `marque`
--
ALTER TABLE `marque`
  ADD PRIMARY KEY (`idMarque`);

--
-- Index pour la table `paiement`
--
ALTER TABLE `paiement`
  ADD PRIMARY KEY (`idPaiement`);

--
-- Index pour la table `particuliers`
--
ALTER TABLE `particuliers`
  ADD PRIMARY KEY (`idParticulier`),
  ADD KEY `idClient` (`idClient`);

--
-- Index pour la table `utilisateurs`
--
ALTER TABLE `utilisateurs`
  ADD PRIMARY KEY (`idUtilisateur`);

--
-- Index pour la table `vendeur`
--
ALTER TABLE `vendeur`
  ADD PRIMARY KEY (`idVendeur`),
  ADD KEY `idUtilisateur` (`idUtilisateur`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `chauffeurs`
--
ALTER TABLE `chauffeurs`
  MODIFY `numChauffeur` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT pour la table `entreprise`
--
ALTER TABLE `entreprise`
  MODIFY `numEntreprise` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `location`
--
ALTER TABLE `location`
  MODIFY `idLocation` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `marque`
--
ALTER TABLE `marque`
  MODIFY `idMarque` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `paiement`
--
ALTER TABLE `paiement`
  MODIFY `idPaiement` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `particuliers`
--
ALTER TABLE `particuliers`
  MODIFY `idParticulier` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `vendeur`
--
ALTER TABLE `vendeur`
  MODIFY `idVendeur` int(11) NOT NULL AUTO_INCREMENT;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `chauffeurs`
--
ALTER TABLE `chauffeurs`
  ADD CONSTRAINT `chauffeurs_ibfk_1` FOREIGN KEY (`idUtilisateur`) REFERENCES `utilisateurs` (`idUtilisateur`);

--
-- Contraintes pour la table `client`
--
ALTER TABLE `client`
  ADD CONSTRAINT `client_ibfk_1` FOREIGN KEY (`idUtilisateur`) REFERENCES `utilisateurs` (`idUtilisateur`);

--
-- Contraintes pour la table `entreprise`
--
ALTER TABLE `entreprise`
  ADD CONSTRAINT `entreprise_ibfk_1` FOREIGN KEY (`idClient`) REFERENCES `client` (`idClient`);

--
-- Contraintes pour la table `particuliers`
--
ALTER TABLE `particuliers`
  ADD CONSTRAINT `particuliers_ibfk_1` FOREIGN KEY (`idClient`) REFERENCES `client` (`idClient`);

--
-- Contraintes pour la table `vendeur`
--
ALTER TABLE `vendeur`
  ADD CONSTRAINT `vendeur_ibfk_1` FOREIGN KEY (`idUtilisateur`) REFERENCES `utilisateurs` (`idUtilisateur`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
