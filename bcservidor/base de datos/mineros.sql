-- phpMyAdmin SQL Dump
-- version 5.2.1-5.fc41
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Dec 12, 2024 at 02:53 PM
-- Server version: 10.11.10-MariaDB
-- PHP Version: 8.3.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `cadenasb`
--

-- --------------------------------------------------------

--
-- Table structure for table `mineros`
--

CREATE TABLE `mineros` (
  `id` int(11) NOT NULL,
  `idmin` varchar(200) NOT NULL,
  `tiempo` int(11) NOT NULL DEFAULT 0,
  `cantidadcli` int(11) NOT NULL DEFAULT 0,
  `numerogrupo` int(11) NOT NULL DEFAULT 0,
  `procentaje` int(11) NOT NULL DEFAULT 0,
  `totalsobregrp` int(11) NOT NULL DEFAULT 0,
  `hashanterior` varchar(200) DEFAULT NULL,
  `rangoNonceIni` varchar(220) DEFAULT NULL,
  `rangoNonceFin` varchar(220) DEFAULT NULL,
  `nunCeros` varchar(110) DEFAULT NULL,
  `contenido` varchar(1000) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;


--
-- Indexes for dumped tables
--

--
-- Indexes for table `mineros`
--
ALTER TABLE `mineros`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `mineros`
--
ALTER TABLE `mineros`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
