-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Jan 14, 2019 at 12:36 PM
-- Server version: 5.6.34-log
-- PHP Version: 7.2.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `sokoban`
--

-- --------------------------------------------------------

--
-- Table structure for table `sauvegarde`
--

CREATE TABLE `sauvegarde` (
  `idSave` int(11) NOT NULL,
  `idUser` int(11) NOT NULL,
  `sauvegarde` text,
  `score` int(11) DEFAULT NULL,
  `niveau` int(11) DEFAULT NULL,
  `nbrPierre` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sauvegarde`
--

INSERT INTO `sauvegarde` (`idSave`, `idUser`, `sauvegarde`, `score`, `niveau`, `nbrPierre`) VALUES
(18, 24, '######  ##### |#    #  #   # |# 0  #### 0 # |# 0     @0  # |#  #######0 # |####   ### ###|       #     #|       #0    #|       # 0   #|      ## 0   #|      #*0 0  #|      ########|              |', 10, 0, 10),
(19, 25, NULL, NULL, NULL, NULL),
(20, 26, '######  ##### |#    #  #   # |# 0  #### 0 # |# 0 @    0  # |#  #######0 # |####   ### ###|       #     #|       #0    #|       # 0   #|      ## 0   #|      #*0 0  #|      ########|', 0, 0, 10);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `idUser` int(11) NOT NULL,
  `Pseudo` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`idUser`, `Pseudo`) VALUES
(24, 'Delal'),
(25, 'Delal2'),
(26, 'Christian');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `sauvegarde`
--
ALTER TABLE `sauvegarde`
  ADD PRIMARY KEY (`idSave`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`idUser`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `sauvegarde`
--
ALTER TABLE `sauvegarde`
  MODIFY `idSave` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;
--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `idUser` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;