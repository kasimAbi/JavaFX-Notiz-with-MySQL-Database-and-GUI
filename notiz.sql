-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Erstellungszeit: 24. Apr 2024 um 15:30
-- Server-Version: 10.1.38-MariaDB
-- PHP-Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Datenbank: `notiz`
--

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `t_notizen`
--

CREATE TABLE `t_notizen` (
  `id` int(10) UNSIGNED NOT NULL,
  `date` date NOT NULL,
  `notiz` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Daten für Tabelle `t_notizen`
--

INSERT INTO `t_notizen` (`id`, `date`, `notiz`) VALUES
(1, '2020-06-04', ''),
(2, '2020-06-11', 'öewhwfukdjn'),
(4, '2020-07-02', 'sakgudkjsahdlksahlds'),
(6, '2020-06-14', '12345lidsflksd'),
(7, '2020-06-08', '1872398132'),
(8, '2020-07-09', 'kasjhdfusagfudsagfdsagfkadsgfsadgfpiwauegf82p7ewefgpwaiugfipsaugfdargfea8gfeauzfbalgfbaiuwekjbdsfvjrwabldjkfnwrads'),
(9, '2020-07-12', 'kuwakegfbkjcdsbvliuz3rebjd'),
(10, '2020-06-02', 'kjdskjdnsf'),
(11, '2020-06-07', '19263812812'),
(12, '2020-06-27', 'ich liebe beyza'),
(13, '2020-06-29', 'asd');

--
-- Indizes der exportierten Tabellen
--

--
-- Indizes für die Tabelle `t_notizen`
--
ALTER TABLE `t_notizen`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT für exportierte Tabellen
--

--
-- AUTO_INCREMENT für Tabelle `t_notizen`
--
ALTER TABLE `t_notizen`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
