-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : jeu. 28 déc. 2023 à 10:19
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
  `nomChauffeur` varchar(20) NOT NULL,
  `Prenom` varchar(20) NOT NULL,
  `dateDeNaissance` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Déchargement des données de la table `chauffeurs`
--

INSERT INTO `chauffeurs` (`numChauffeur`, `nomChauffeur`, `Prenom`, `dateDeNaissance`) VALUES
(1, 'Dupont', 'Jean', '1980-01-15'),
(2, 'Martin', 'Sophie', '1975-07-20'),
(3, 'Dubois', 'Paul', '1985-04-05'),
(4, 'Lefebvre', 'Isabelle', '1990-11-10'),
(5, 'Perrin', 'François', '1978-03-25'),
(6, 'Dufresne', 'Marie', '1982-08-30'),
(7, 'Leroy', 'Jacques', '1984-06-15'),
(8, 'Girard', 'Anne', '1986-02-07'),
(9, 'Fontaine', 'Luc', '1988-09-12'),
(10, 'Moreau', 'Céline', '1987-12-18'),
(11, 'Dumas', 'Thierry', '1981-10-22'),
(12, 'Gagné', 'Caroline', '1977-05-02'),
(13, 'Tremblay', 'Robert', '1983-09-03'),
(14, 'Bertrand', 'Émilie', '1979-07-28'),
(15, 'Poirier', 'Philippe', '1989-04-14'),
(16, 'Bouchard', 'Nathalie', '1991-03-08'),
(17, 'Lavoie', 'Louis', '1985-11-12'),
(18, 'Gauthier', 'Julie', '1984-01-23'),
(19, 'Beaulieu', 'Michel', '1976-06-17'),
(20, 'Roberts', 'Linda', '1980-12-30');

-- --------------------------------------------------------

--
-- Structure de la table `client`
--

CREATE TABLE `client` (
  `idClient` int(11) NOT NULL,
  `nomClient` varchar(20) NOT NULL,
  `tel` varchar(20) NOT NULL,
  `email` varchar(30) NOT NULL,
  `motDePasse` varchar(20) NOT NULL,
  `connecté` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- --------------------------------------------------------

--
-- Structure de la table `entreprise`
--

CREATE TABLE `entreprise` (
  `numEntreprise` int(11) NOT NULL,
  `NomEntreprise` varchar(20) NOT NULL,
  `idClient` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

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
  `idClient` int(11) NOT NULL,
  `idVoiture` int(11) NOT NULL,
  `StatutLocation` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- --------------------------------------------------------

--
-- Structure de la table `marque`
--

CREATE TABLE `marque` (
  `idMarque` int(11) NOT NULL,
  `nomMarque` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- --------------------------------------------------------

--
-- Structure de la table `paiement`
--

CREATE TABLE `paiement` (
  `idPaiement` int(11) NOT NULL,
  `Montant` decimal(10,0) NOT NULL,
  `dateDePaiement` date NOT NULL,
  `idLocation` int(11) NOT NULL,
  `typeDePaiement` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- --------------------------------------------------------

--
-- Structure de la table `particuliers`
--

CREATE TABLE `particuliers` (
  `idParticulier` int(11) NOT NULL,
  `Prénom` varchar(20) NOT NULL,
  `dateDeNaissance` date NOT NULL,
  `Métier` varchar(20) NOT NULL,
  `idClient` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- --------------------------------------------------------

--
-- Structure de la table `vendeur`
--

CREATE TABLE `vendeur` (
  `idVendeur` int(11) NOT NULL,
  `Nom` varchar(20) NOT NULL,
  `Prenom` varchar(20) NOT NULL,
  `adresse` varchar(20) NOT NULL,
  `tel` varchar(20) NOT NULL,
  `dateDeNaissance` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

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
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Déchargement des données de la table `voituredeluxe`
--

INSERT INTO `voituredeluxe` (`idVoiture`, `Couleur`, `immatriculation`, `marque`, `prixLocation`, `lien_img`) VALUES
(1, 'Red', 'BNL130', 'BENTLEY CONTINENTAL GTC', 550, '../imagesv/bnl.jpg'),
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
(23, 'Vert', 'YZA123', 'Porsche Panamera', 1300, '../imagesv/prp.jpg'),
(24, 'Jaune', 'PQR123', 'Lamborghini Urus', 1400, '../imagesv/lamu.jpg'),
(25, 'Gris', 'JKL456', 'Audi RS7', 950, '../imagesv/rs7.jpg'),
(26, 'Red', 'BGC260', 'Bugatti Chiron', 550, '../imagesv/Bc.jpg');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `chauffeurs`
--
ALTER TABLE `chauffeurs`
  ADD PRIMARY KEY (`numChauffeur`);

--
-- Index pour la table `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`idClient`);

--
-- Index pour la table `entreprise`
--
ALTER TABLE `entreprise`
  ADD PRIMARY KEY (`numEntreprise`);

--
-- Index pour la table `location`
--
ALTER TABLE `location`
  ADD PRIMARY KEY (`idLocation`,`idClient`);

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
  ADD PRIMARY KEY (`idParticulier`);

--
-- Index pour la table `vendeur`
--
ALTER TABLE `vendeur`
  ADD PRIMARY KEY (`idVendeur`);

--
-- Index pour la table `voituredeluxe`
--
ALTER TABLE `voituredeluxe`
  ADD PRIMARY KEY (`idVoiture`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `chauffeurs`
--
ALTER TABLE `chauffeurs`
  MODIFY `numChauffeur` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT pour la table `client`
--
ALTER TABLE `client`
  MODIFY `idClient` int(11) NOT NULL AUTO_INCREMENT;

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
-- AUTO_INCREMENT pour la table `voituredeluxe`
--
ALTER TABLE `voituredeluxe`
  MODIFY `idVoiture` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
