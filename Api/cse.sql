-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 26, 2020 at 08:58 AM
-- Server version: 10.4.13-MariaDB
-- PHP Version: 7.4.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `cse`
--

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE `student` (
  `id` int(10) NOT NULL,
  `name` varchar(50) NOT NULL,
  `Id_no` int(7) NOT NULL,
  `reg_no` int(5) NOT NULL,
  `pass` varchar(20) NOT NULL,
  `sessioN` varchar(9) NOT NULL,
  `batch` varchar(20) NOT NULL,
  `contact` varchar(11) NOT NULL,
  `email` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `student`
--

INSERT INTO `student` (`id`, `name`, `Id_no`, `reg_no`, `pass`, `sessioN`, `batch`, `contact`, `email`) VALUES
(27, 'Sangita das', 1402007, 111, 'shangita', '2014-2015', '12th Batch', '01515255863', 'sangita@gmail.com'),
(31, 'Putul', 1402049, 5444, 'putul', '2014-2015', '11th Batch', '01521438885', 'putul@gmail.com'),
(32, 'sangita', 1402007, 13133, 'sangita', '2014-2015', '12th Batch', '01521438885', 'sangita@gmail.com'),
(41, 'Rafyee', 1402060, 5409, 'rafyee', '2014-2015', '12th Batch', '01521438885', 'rafi@gmail.com');

-- --------------------------------------------------------

--
-- Table structure for table `teacher`
--

CREATE TABLE `teacher` (
  `id` int(11) NOT NULL,
  `t_name` varchar(50) NOT NULL,
  `t_post` varchar(20) NOT NULL,
  `t_faculty` varchar(30) NOT NULL,
  `t_dept` varchar(100) NOT NULL,
  `t_email` varchar(20) NOT NULL,
  `t_contact` varchar(12) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `teacher`
--

INSERT INTO `teacher` (`id`, `t_name`, `t_post`, `t_faculty`, `t_dept`, `t_email`, `t_contact`) VALUES
(2, 'Sajal Saha', 'Lecturer', 'Computer Science and Engineeri', 'Department of Computer and Communication Engineering', 'sajalsaha@pstu.ac.bd', '01754698756'),
(4, 'Galib Sir', 'Associate Professor', 'Computer Science and Engineeri', 'Department of Computer Science and Information Technology', 'galibsir@gmail.com', '01754698756'),
(5, 'Chinmay Sir', 'Associate Professor', 'Computer Science and Engineeri', 'Department of Computer Science and Information Technology', 'chinmaysir@gmail.com', '01754698756');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `teacher`
--
ALTER TABLE `teacher`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `student`
--
ALTER TABLE `student`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=51;

--
-- AUTO_INCREMENT for table `teacher`
--
ALTER TABLE `teacher`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
