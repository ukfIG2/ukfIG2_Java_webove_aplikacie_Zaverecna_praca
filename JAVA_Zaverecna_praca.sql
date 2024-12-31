-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Dec 31, 2024 at 03:27 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `JAVA_Zaverecna_praca`
--
CREATE DATABASE IF NOT EXISTS `JAVA_Zaverecna_praca` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_slovak_ci;
USE `JAVA_Zaverecna_praca`;

-- --------------------------------------------------------

--
-- Table structure for table `conferences`
--

CREATE TABLE `conferences` (
  `id` int(11) NOT NULL,
  `name_of_conference` tinytext NOT NULL,
  `date_of_conference` date DEFAULT NULL,
  `state_of_conference` enum('PREPARING','REGISTERING','REGISTERING_ENDED','ENDED') NOT NULL DEFAULT 'PREPARING',
  `comment` text DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_slovak_ci;

--
-- Dumping data for table `conferences`
--

INSERT INTO `conferences` (`id`, `name_of_conference`, `date_of_conference`, `state_of_conference`, `comment`, `created_at`, `updated_at`) VALUES
(31, 'Konferencia 1', '2025-03-12', 'PREPARING', 'Konferencia 1', '2024-12-31 00:00:42', '2024-12-31 00:00:42'),
(32, 'Konferencia 2', '2025-04-18', 'PREPARING', 'Konferencia 2 aktualizovana', '2024-12-31 00:01:42', '2024-12-31 00:02:05');

-- --------------------------------------------------------

--
-- Table structure for table `notes`
--

CREATE TABLE `notes` (
  `id` int(11) NOT NULL,
  `name_of_note` tinytext NOT NULL,
  `note` text DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `conference_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_slovak_ci;

-- --------------------------------------------------------

--
-- Table structure for table `presentations`
--

CREATE TABLE `presentations` (
  `id` int(11) NOT NULL,
  `name_of_presentation` tinytext NOT NULL,
  `start_at` time DEFAULT NULL,
  `end_at` time DEFAULT NULL,
  `short_description` tinytext DEFAULT NULL,
  `long_description` text DEFAULT NULL,
  `capacity` int(11) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `stage_id` int(11) NOT NULL,
  `comment` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_slovak_ci;

--
-- Dumping data for table `presentations`
--

INSERT INTO `presentations` (`id`, `name_of_presentation`, `start_at`, `end_at`, `short_description`, `long_description`, `capacity`, `created_at`, `updated_at`, `stage_id`, `comment`) VALUES
(16, 'Prezentacia 1', '11:11:00', '22:22:00', NULL, 'Prezentacia 1', 11, '2024-12-31 00:26:46', '2024-12-31 00:26:46', 16, 'Prezentacia 1'),
(17, 'Prezentacia 2', '03:33:00', '04:44:00', NULL, 'Prezentacia 2', 22, '2024-12-31 00:27:06', '2024-12-31 00:27:06', 16, 'Prezentacia 2'),
(18, 'Prezentacia 3', '05:55:00', '06:06:00', NULL, 'Prezentacia 3', 33, '2024-12-31 00:27:54', '2024-12-31 00:27:54', 17, 'Prezentacia 3'),
(19, 'Prezentacia 4', '06:06:00', '07:07:00', NULL, 'Prezentacia 4', 44, '2024-12-31 00:28:43', '2024-12-31 00:28:43', 17, 'Prezentacia 4'),
(20, 'Prezentacia 5', '07:07:00', '08:08:00', NULL, 'Prezentacia 5', 55, '2024-12-31 00:41:44', '2024-12-31 00:41:44', 18, 'Prezentacia 5'),
(21, 'Prezentacia 6', '08:08:00', '09:09:00', NULL, 'Prezentacia 6', 66, '2024-12-31 00:42:05', '2024-12-31 00:42:05', 18, 'Prezentacia 6'),
(22, 'Prezentacia 7', '09:09:00', '10:10:00', NULL, 'Prezentacia 7', 77, '2024-12-31 00:42:31', '2024-12-31 00:42:31', 19, 'Prezentacia 7'),
(23, 'Prezentacia 8', '11:11:00', '12:12:00', NULL, 'Prezentacia 8', 88, '2024-12-31 00:43:06', '2024-12-31 00:43:06', 19, 'Prezentacia 8');

-- --------------------------------------------------------

--
-- Table structure for table `presentations_has_participants`
--

CREATE TABLE `presentations_has_participants` (
  `id` int(11) NOT NULL,
  `presentation_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_slovak_ci;

--
-- Dumping data for table `presentations_has_participants`
--

INSERT INTO `presentations_has_participants` (`id`, `presentation_id`, `user_id`, `created_at`, `updated_at`) VALUES
(19, 17, 27, '2024-12-31 01:31:58', '2024-12-31 01:31:58'),
(20, 19, 27, '2024-12-31 01:32:01', '2024-12-31 01:32:01'),
(21, 21, 27, '2024-12-31 01:32:08', '2024-12-31 01:32:08'),
(22, 22, 27, '2024-12-31 01:32:11', '2024-12-31 01:32:11');

-- --------------------------------------------------------

--
-- Table structure for table `presentations_has_speakers`
--

CREATE TABLE `presentations_has_speakers` (
  `id` int(11) NOT NULL,
  `presentation_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_slovak_ci;

--
-- Dumping data for table `presentations_has_speakers`
--

INSERT INTO `presentations_has_speakers` (`id`, `presentation_id`, `user_id`, `created_at`, `updated_at`) VALUES
(6, 16, 29, '2024-12-31 00:44:57', '2024-12-31 00:44:57'),
(7, 17, 30, '2024-12-31 00:49:40', '2024-12-31 00:49:40'),
(8, 18, 32, '2024-12-31 00:51:25', '2024-12-31 00:51:25'),
(9, 19, 33, '2024-12-31 00:51:31', '2024-12-31 00:51:31'),
(10, 20, 33, '2024-12-31 00:52:32', '2024-12-31 00:52:32'),
(11, 21, 32, '2024-12-31 00:52:32', '2024-12-31 00:52:32'),
(12, 22, 30, '2024-12-31 00:53:02', '2024-12-31 00:53:02'),
(13, 23, 29, '2024-12-31 00:53:02', '2024-12-31 00:53:02');

-- --------------------------------------------------------

--
-- Table structure for table `sponsors`
--

CREATE TABLE `sponsors` (
  `id` int(11) NOT NULL,
  `name_of_sponsor` tinytext NOT NULL,
  `url` tinytext DEFAULT NULL,
  `image` tinytext DEFAULT NULL,
  `comment` text DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_slovak_ci;

--
-- Dumping data for table `sponsors`
--

INSERT INTO `sponsors` (`id`, `name_of_sponsor`, `url`, `image`, `comment`, `created_at`, `updated_at`) VALUES
(10, 'Sponzor 1', 'https://sponzor1.sponzor1', 'https://sponzor1.sponzor1', 'https://sponzor1.sponzor1', '2024-12-31 00:09:03', '2024-12-31 00:09:03'),
(11, 'Sponzor 2', 'https://sponzor2.sponzor2', 'https://sponzor2.sponzor2', 'https://sponzor2.sponzor2', '2024-12-31 00:09:48', '2024-12-31 00:09:48'),
(12, 'Sponzor 3', 'https://sponzor3.sponzor3', 'https://sponzor3.sponzor3', 'https://sponzor3.sponzor3', '2024-12-31 00:10:31', '2024-12-31 00:10:31'),
(13, 'Sponzor 4', 'https://sponzor4.sponzor4', 'https://sponzor4.sponzor4', 'https://sponzor4.sponzor4', '2024-12-31 00:10:48', '2024-12-31 00:10:48');

-- --------------------------------------------------------

--
-- Table structure for table `sponsors_has_conferences`
--

CREATE TABLE `sponsors_has_conferences` (
  `id` int(11) NOT NULL,
  `sponsors_id` int(11) NOT NULL,
  `conferences_id` int(11) NOT NULL,
  `comment` text DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_slovak_ci;

--
-- Dumping data for table `sponsors_has_conferences`
--

INSERT INTO `sponsors_has_conferences` (`id`, `sponsors_id`, `conferences_id`, `comment`, `created_at`, `updated_at`) VALUES
(7, 10, 31, '', '2024-12-31 00:10:59', '2024-12-31 00:10:59'),
(8, 11, 31, '', '2024-12-31 00:11:04', '2024-12-31 00:11:04'),
(9, 12, 32, '', '2024-12-31 00:11:08', '2024-12-31 00:11:50'),
(10, 13, 32, '', '2024-12-31 00:11:13', '2024-12-31 00:11:13');

-- --------------------------------------------------------

--
-- Table structure for table `stages`
--

CREATE TABLE `stages` (
  `id` int(11) NOT NULL,
  `name_of_stage` tinytext NOT NULL,
  `comment` text DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `conference_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_slovak_ci;

--
-- Dumping data for table `stages`
--

INSERT INTO `stages` (`id`, `name_of_stage`, `comment`, `created_at`, `updated_at`, `conference_id`) VALUES
(16, 'Scena 31.1', 'Scena 31.1', '2024-12-31 00:04:34', '2024-12-31 00:05:32', 31),
(17, 'Scena 31.2', 'Scena 31.2', '2024-12-31 00:05:07', '2024-12-31 00:05:40', 31),
(18, 'Scena 32.1', 'Scena 32.1', '2024-12-31 00:06:05', '2024-12-31 00:06:20', 32),
(19, 'Scena 32.2', 'Scena 32.2', '2024-12-31 00:06:37', '2024-12-31 00:06:37', 32);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `email` tinytext NOT NULL,
  `title_before_name` tinytext DEFAULT NULL,
  `first_name` tinytext NOT NULL,
  `last_name` tinytext NOT NULL,
  `title_after_name` tinytext DEFAULT NULL,
  `password` varchar(60) NOT NULL,
  `phone_number` varchar(30) DEFAULT NULL,
  `photo` tinytext DEFAULT NULL,
  `comment` text DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `role` enum('ROLE_admin','ROLE_registered_visitor','ROLE_speaker') NOT NULL DEFAULT 'ROLE_registered_visitor'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_slovak_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `email`, `title_before_name`, `first_name`, `last_name`, `title_after_name`, `password`, `phone_number`, `photo`, `comment`, `created_at`, `updated_at`, `role`) VALUES
(27, 'Pouzivatel1@Pouzivatel1.Pouzivatel', NULL, 'Pouzivatel1', 'Pouzivatel1', NULL, '$2a$10$n4LIFAT1MaUaKFBUDSpe8.A2csbH07gArAGQibiHftahsyCE02glW', '', NULL, 'Pouzivatel1', '2024-12-31 00:16:53', '2024-12-31 00:18:04', 'ROLE_registered_visitor'),
(28, 'Pouzivatel2@Pouzivatel2.Pouzivatel', '', 'Pouzivatel2', 'Pouzivatel2', '', '$2a$10$Mp8YM9adAwNT5q91E56TruVuQ55G2XPHBmPb/ruZa2R7AyMOmykRq', '', '', 'Pouzivatel2', '2024-12-31 00:17:51', '2024-12-31 00:17:51', 'ROLE_registered_visitor'),
(29, 'Pouzivatel3@Pouzivatel3.Pouzivatel', NULL, 'Pouzivatel3', 'Pouzivatel3', NULL, '$2a$10$uCU6GqKeqq13hEutglGxk.bExy9gIWuIosUofzEZoGt/MMEm57idu', '', NULL, 'Pouzivatel3', '2024-12-31 00:19:24', '2024-12-31 00:23:45', 'ROLE_speaker'),
(30, 'Pouzivatel4@Pouzivatel4.Pouzivatel', NULL, 'Pouzivatel4', 'Pouzivatel4', NULL, '$2a$10$p9zKZY.LzDDWuFqj1cWVsuN75/oIIiOuzcSuGR18CCoRfXmnWAX/W', '', NULL, 'Pouzivatel4', '2024-12-31 00:20:21', '2024-12-31 00:23:48', 'ROLE_speaker'),
(31, 'Pouzivatel5@Pouzivatel5.Pouzivatel', NULL, 'Pouzivatel5', 'Pouzivatel5', NULL, '$2a$10$fncRrvmGpmF4gcFP9MwAEOE0hvBEXIDpTZLoLhVx7OFFPlAjEq3/G', '', NULL, 'Pouzivatel5', '2024-12-31 00:20:50', '2024-12-31 01:01:17', 'ROLE_registered_visitor'),
(32, 'Pouzivatel6@Pouzivatel6.Pouzivatel', NULL, 'Pouzivatel6', 'Pouzivatel6', NULL, '$2a$10$jD3oHGzJUo7CR6MlPmuRXO6jq27n3D24QFunBfNlBHMQ90omZTxjS', '', NULL, 'Pouzivatel6', '2024-12-31 00:21:12', '2024-12-31 00:24:00', 'ROLE_speaker'),
(33, 'Pouzivatel7@Pouzivatel7.Pouzivatel', NULL, 'Pouzivatel7', 'Pouzivatel7', NULL, '$2a$10$jD3oHGzJUo7CR6MlPmuRXO6jq27n3D24QFunBfNlBHMQ90omZTxjS', '', NULL, 'Pouzivatel6', '2024-12-31 00:21:12', '2024-12-31 00:24:42', 'ROLE_speaker'),
(35, 'ivan@ivan.ivan', NULL, 'Ivan', 'Ivan', NULL, '$2a$10$OfTxPa2JDxYF8Qc9hVZkVeUsm7fW2ClVsiuWgnFH1wYl8hv1ktXT2', '', NULL, 'Ivan123456789', '2024-12-31 01:05:56', '2024-12-31 01:06:56', 'ROLE_admin');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `conferences`
--
ALTER TABLE `conferences`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `notes`
--
ALTER TABLE `notes`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_notes_conferences` (`conference_id`);

--
-- Indexes for table `presentations`
--
ALTER TABLE `presentations`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_presentations_stages` (`stage_id`);

--
-- Indexes for table `presentations_has_participants`
--
ALTER TABLE `presentations_has_participants`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_presentations_has_participants_presentations` (`presentation_id`),
  ADD KEY `fk_presentations_has_participants_users` (`user_id`);

--
-- Indexes for table `presentations_has_speakers`
--
ALTER TABLE `presentations_has_speakers`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_presentations_has_speakers_presentations` (`presentation_id`),
  ADD KEY `fk_presentations_has_speakers_users` (`user_id`);

--
-- Indexes for table `sponsors`
--
ALTER TABLE `sponsors`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `sponsors_has_conferences`
--
ALTER TABLE `sponsors_has_conferences`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_sponsors_has_conferences_conferences` (`conferences_id`),
  ADD KEY `fk_sponsors_has_conferences_sponsors` (`sponsors_id`);

--
-- Indexes for table `stages`
--
ALTER TABLE `stages`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_stages_conferences` (`conference_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`) USING HASH;

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `conferences`
--
ALTER TABLE `conferences`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=34;

--
-- AUTO_INCREMENT for table `notes`
--
ALTER TABLE `notes`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `presentations`
--
ALTER TABLE `presentations`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT for table `presentations_has_participants`
--
ALTER TABLE `presentations_has_participants`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT for table `presentations_has_speakers`
--
ALTER TABLE `presentations_has_speakers`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `sponsors`
--
ALTER TABLE `sponsors`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `sponsors_has_conferences`
--
ALTER TABLE `sponsors_has_conferences`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `stages`
--
ALTER TABLE `stages`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=36;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `notes`
--
ALTER TABLE `notes`
  ADD CONSTRAINT `fk_notes_conferences` FOREIGN KEY (`conference_id`) REFERENCES `conferences` (`id`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Constraints for table `presentations`
--
ALTER TABLE `presentations`
  ADD CONSTRAINT `fk_presentations_stages` FOREIGN KEY (`stage_id`) REFERENCES `stages` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `presentations_has_participants`
--
ALTER TABLE `presentations_has_participants`
  ADD CONSTRAINT `fk_presentations_has_participants_presentations` FOREIGN KEY (`presentation_id`) REFERENCES `presentations` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_presentations_has_participants_users` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `presentations_has_speakers`
--
ALTER TABLE `presentations_has_speakers`
  ADD CONSTRAINT `fk_presentations_has_speakers_presentations` FOREIGN KEY (`presentation_id`) REFERENCES `presentations` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_presentations_has_speakers_users` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `sponsors_has_conferences`
--
ALTER TABLE `sponsors_has_conferences`
  ADD CONSTRAINT `fk_sponsors_has_conferences_conferences` FOREIGN KEY (`conferences_id`) REFERENCES `conferences` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_sponsors_has_conferences_sponsors` FOREIGN KEY (`sponsors_id`) REFERENCES `sponsors` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `stages`
--
ALTER TABLE `stages`
  ADD CONSTRAINT `fk_stages_conferences` FOREIGN KEY (`conference_id`) REFERENCES `conferences` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
