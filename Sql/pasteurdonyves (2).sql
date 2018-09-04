-- phpMyAdmin SQL Dump
-- version 4.7.9
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Sep 04, 2018 at 07:50 AM
-- Server version: 10.1.31-MariaDB
-- PHP Version: 7.2.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `pasteurdonyves`
--

-- --------------------------------------------------------

--
-- Table structure for table `actualite`
--

CREATE TABLE `actualite` (
  `idactualite` int(11) NOT NULL,
  `titre` varchar(45) NOT NULL,
  `message` text NOT NULL,
  `date` datetime DEFAULT NULL,
  `image_ref` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `actualite`
--

INSERT INTO `actualite` (`idactualite`, `titre`, `message`, `date`, `image_ref`) VALUES
(1, 'Pasteur Don Yves rend visite aux orphelins', 'Voici le contenue de la visite du pasteur Don \nYves Kisukulu à l\'orphelinat dont il a récement fait la visite avec quelques fidèles de son église.', '2018-08-14 00:58:55', 'images/01.jpg'),
(2, 'Pasteur Don Yves rend visite aux orphelins', 'Voici le contenue de la visite du pasteur Don \nYves Kisukulu à l\'orphelinat dont il a récement fait la visite avec quelques fidèles de son église.', '2018-08-14 09:27:32', 'images/02.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `meditation`
--

CREATE TABLE `meditation` (
  `idmeditation` int(11) NOT NULL,
  `titre` varchar(60) NOT NULL,
  `soustitre` text NOT NULL,
  `message` text NOT NULL,
  `date` datetime NOT NULL,
  `image_ref` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `meditation`
--

INSERT INTO `meditation` (`idmeditation`, `titre`, `soustitre`, `message`, `date`, `image_ref`) VALUES
(1, 'L\'obeissance', 'L\'obeissance vaut mieux que les sacrifices', 'La volonté de Dieu à notre égard étant bien connue dans certaines circonstances dans lesquelles nous pouvons nous trouver, obéir à cette volonté implicitement et sans aucun calcul des conséquences, est-ce un devoir pour nous ? En d’autres termes : l’obéissance, dans l’ordre moral, doit-elle précéder la manifestation de la bénédiction divine, ou bien faut-il attendre, avant d’obéir, la manifestation de cette bénédiction ? C’est là une question de conscience, dont la solution est singulièrement liée avec les intérêts et la condition de l’Église de Dieu dans le moment actuel. Plusieurs ont remarqué, sans doute, au moins en partie, à quel point s’est répandu, au milieu des chrétiens, le principe que la bénédiction doit précéder l’obéissance, et que, faute de cela, on est dispensé d’obéir.', '2018-08-19 04:34:55', 'images/03.jpg'),
(2, 'Le niveau de risque dans la vie des Patriarches', 'Le livre de la Genèse peut être divisé en deux parties : l’histoire primitive (chapitres 1 à 11) et le récit des patriarches (12-50) ', 'Tous les patriarches étaient des semi-nomades. Ils croyaient tous en Elohim (Dieu), et Dieu se révélait à eux sous le nom d’El Shaddai signifiant « Dieu Tout-Puissant » ou « Dieu des grandes montagnes » ou « Dieu des mamelles » (Genèse 17.1,28.3; 35.11,43.14, 48.3). Dieu se révéla en particulier à Abraham en tant qu’El Elyon, qui signifie « Dieu Très-Haut » (Genèse 14.17-24) et El ‘Olam qui veut dire « Dieu Éternel » (Genèse 21.33). En passant, je reconnais que ces noms pourraient être des noms géographiques différents pour la même divinité. Mon père a l’habitude de commencer à prier de la manière suivante : « Dieu d’Abraham, Dieu d’Isaac, Dieu de Jacob … ». Mais ils sont différents, quant au niveau de risque qu’ils ont pris pour Dieu.\r\n\r\nNous verrons dans cette série de quatre enseignements, qui d’entre eux avait la volonté de tout donner pour la cause du Royaume de Dieu.\r\n\r\n', '2018-08-20 19:07:55', 'images/puit.jpg'),
(3, 'La peur de l’échec', 'La peur est l\'une des armes les plus redoutables que l\'ennemi utilise ', 'La peur est l\'une des armes les plus redoutables que l\'ennemi utilise pour paralyser et avorter la destinée des enfants de Dieu. La peur est l\'une des plus grandes causes d\'échec dans la société :peur d\'échouer, peur du prix à payer, peur de donner, peur des maladies, peur de tomber dans le péché, peur du diable, peur des critiques, des oppositions, peur de la pauvreté ou peur de la prospérité.', '2018-08-20 03:58:40', 'images/fear.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `predication`
--

CREATE TABLE `predication` (
  `idpredication` int(11) NOT NULL,
  `url` varchar(80) NOT NULL,
  `texte` text NOT NULL,
  `texte_entier` text NOT NULL,
  `theme` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `predication`
--

INSERT INTO `predication` (`idpredication`, `url`, `texte`, `texte_entier`, `theme`) VALUES
(1, 'oSbk0s-zImw', 'Ce message parle de la toute puissance de la présence de Dieu, et de la façon dont nous pouvons nous emparer de cette puissance ', 'Nous sommes à l\'heure de la rapidité et de la possession immédiate. Ainsi, bon nombre de nos contemporains, lisent-ils cette promesse d\'actes 1:8 en se limitant à la première partie du verset. Leur unique préoccupation est de recevoir et de manifester la puissance merveilleuse promise par Dieu pour témoigner avec efficacité. Ils désirent guérir les cœurs et les corps, libérer les captifs et délivrer les prisonniers. Ils veulent ainsi glorifier le nom de leur Sauveur qui est aussi le nôtre. C\'est, du reste, tout à fait le sens premier de cette promesse de Jésus, faite à chacun de ses disciples. Aucun chrétien né de nouveau n\'échappe d\'ailleurs à ce saint désir. Il me faut cependant remarquer que le verset cité en référence ne s\'arrête pas à ces mots : \"Vous recevrez une puissance\", comme s\'il s\'agissait d\'une intervention magique issue du Dieu tout puissant, ou une promesse inconditionnelle faite à chaque lecteur à travers les âges. Il est cependant très important de remarquer que cette puissance, qui vient directement de Dieu, ne peut être assimilée à une influence impersonnelle (aujourd\'hui, on dirait \"magique\"). La suite du verset nous apporte d\'ailleurs toutes les précisions nécessaires : \"Le Saint Esprit survenant sur vous\".', 'La puissance de Dieu'),
(2, 'Haccjr8JmiE', 'Quand la présence de l’Eternel est au milieu de nous, alors personne ne peut nous nuire. Mais sans elle, nous sommes sans secours, réduits à néant. Laissons toutes les nations de ce monde placer leur confiance dans leurs puissantes armées, dans leurs chars de bronze, dans leurs soldats entraînés, et leurs nouvelles armes. Nous, nous plaçons notre confiance dans la puissance manifeste de notre Dieu', '<div style=\"text-align:justify\" >\r\n<p>\r\nDepuis le commencement, l’Éternel a tout mis en œuvre afin de préserver la communion avec sa créature. La dispensation de la loi en est le parfait exemple. Sous cette dispensation, l’Éternel demanda à Moïse de construire un tabernacle, c\'est-à-dire un sanctuaire dans lequel il pourrait entrer en relation avec lui. \r\nLe tabernacle de Moïse fut une construction de toute beauté. Quand on considère les matériaux et les différentes matières premières utilisées pour réaliser les nombreux ustensiles, le mobilier, les structures, les tissus, les vêtements du sacrificateur… On comprend alors l’importance que l’Éternel accorda à cette tente de la rencontre. Moïse reçut l’ordre de réaliser tous ces éléments et instruments, avec de l\'or, des pierres précieuses et des tissus de grande valeur. \r\nToutes ces exigences traduisent bien la grande valeur que l’Éternel attribue au fait de rencontrer l’homme. Car aux yeux du Seigneur, l’homme est le couronnement de sa création. Dieu créa les animaux selon leurs espèces, mais il créa l’homme à sa ressemblance. \r\nLe tabernacle et ses nombreux éléments étaient tous des types de Christ ou du Saint-Esprit. Une lumière éclairait l’intérieur de ce temple, et cela 24h/24. Elle symbolisait la présence de Dieu. Ce point est d’une extrême importance car si Dieu n’avait pas été présent dans le tabernacle, toutes ces fonctions lévitiques auraient été vaines et inutiles. \r\nLa recherche de la présence de Dieu devrait être au cœur de la foi chrétienne. Trop de chrétiens parlent théoriquement de la présence de Dieu mais ils en ignorent la réalité. Comprenez-vous que le Saint-Esprit voudrait illuminer quotidiennement votre cœur de la présence éclatante de Christ ? Cette présence, vous devriez la vivre quotidiennement. Est-ce le cas pour vous ? N’oubliez pas, dans le temple, la lumière brillait jour et nuit !\r\n</p>\r\n</div>', 'La présence de Dieu');

-- --------------------------------------------------------

--
-- Table structure for table `theme`
--

CREATE TABLE `theme` (
  `idtheme` int(11) NOT NULL,
  `titre` text NOT NULL,
  `image_ref` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `theme`
--

INSERT INTO `theme` (`idtheme`, `titre`, `image_ref`) VALUES
(1, 'Prière', 'images/01.jpg'),
(2, 'Foi', 'images/foi.jpg'),
(3, 'Pardon', 'images/Pardon.jpg'),
(4, 'Saint-Esprit', 'images/holy.jpg');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `actualite`
--
ALTER TABLE `actualite`
  ADD PRIMARY KEY (`idactualite`);

--
-- Indexes for table `meditation`
--
ALTER TABLE `meditation`
  ADD PRIMARY KEY (`idmeditation`);

--
-- Indexes for table `predication`
--
ALTER TABLE `predication`
  ADD PRIMARY KEY (`idpredication`);

--
-- Indexes for table `theme`
--
ALTER TABLE `theme`
  ADD PRIMARY KEY (`idtheme`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `actualite`
--
ALTER TABLE `actualite`
  MODIFY `idactualite` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `meditation`
--
ALTER TABLE `meditation`
  MODIFY `idmeditation` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `predication`
--
ALTER TABLE `predication`
  MODIFY `idpredication` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
