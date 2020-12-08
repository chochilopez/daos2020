-- phpMyAdmin SQL Dump
-- version 4.9.7deb1
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost:3306
-- Tiempo de generación: 04-12-2020 a las 23:43:30
-- Versión del servidor: 8.0.22-0ubuntu0.20.10.2
-- Versión de PHP: 7.4.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `tp2daos2020`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ciudad`
--

CREATE TABLE `ciudad` (
  `id_ciudad` bigint NOT NULL,
  `actualizado` datetime(6) DEFAULT NULL,
  `borrado` datetime(6) DEFAULT NULL,
  `cp` bigint DEFAULT NULL,
  `creado` datetime(6) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `ciudad`
--

INSERT INTO `ciudad` (`id_ciudad`, `actualizado`, `borrado`, `cp`, `creado`, `nombre`) VALUES
(101, NULL, NULL, 3535, NULL, 'Salt Spring Island'),
(102, NULL, NULL, 7512, NULL, 'Hisar'),
(103, NULL, NULL, 6408, NULL, 'Carunchio'),
(104, NULL, NULL, 8144, NULL, 'Yurzhnouralsk'),
(105, NULL, NULL, 5554, NULL, 'Latur'),
(106, NULL, NULL, 4720, NULL, 'Sennariolo'),
(107, NULL, NULL, 5465, NULL, 'Tuscaloosa'),
(108, NULL, NULL, 9739, NULL, 'Lauder'),
(109, NULL, NULL, 8707, NULL, 'Governador Valadares'),
(110, NULL, NULL, 2698, NULL, 'Ophain-Bois-Seigneur-Isaac'),
(111, NULL, NULL, 2259, NULL, 'Villers-la-Tour'),
(112, NULL, NULL, 1869, NULL, 'Avennes'),
(113, NULL, NULL, 8355, NULL, 'Worcester'),
(114, NULL, NULL, 4783, NULL, 'Merthyr Tydfil'),
(115, NULL, NULL, 2097, NULL, 'Fort Collins'),
(116, NULL, NULL, 2054, NULL, 'Namyangju'),
(117, NULL, NULL, 8404, NULL, 'Rocca d\'Arazzo'),
(118, NULL, NULL, 8866, NULL, 'Gifhorn'),
(119, NULL, NULL, 1240, NULL, 'Tofield'),
(120, NULL, NULL, 5027, NULL, 'Warisoulx'),
(121, NULL, NULL, 6425, NULL, 'Sint-Katherina-Lombeek'),
(122, NULL, NULL, 4490, NULL, 'Sint-Pieters-Leeuw'),
(123, NULL, NULL, 7312, NULL, 'Bellingen'),
(124, NULL, NULL, 4507, NULL, 'Sagamu'),
(125, NULL, NULL, 9685, NULL, 'Plancenoit'),
(126, NULL, NULL, 2759, NULL, 'Florencia'),
(127, NULL, NULL, 7299, NULL, 'Gwadar'),
(128, NULL, NULL, 4648, NULL, 'Falmouth'),
(129, NULL, NULL, 4051, NULL, 'Barra do Corda'),
(130, NULL, NULL, 4278, NULL, 'Hoyerswerda'),
(131, NULL, NULL, 4655, NULL, 'Pomarico'),
(132, NULL, NULL, 2354, NULL, 'Terni'),
(133, NULL, NULL, 3174, NULL, 'Iquique'),
(134, NULL, NULL, 2213, NULL, 'Campomorone'),
(135, NULL, NULL, 2465, NULL, 'Honolulu'),
(136, NULL, NULL, 5767, NULL, 'Wadgassen'),
(137, NULL, NULL, 7095, NULL, 'Kessel'),
(138, NULL, NULL, 8614, NULL, 'Périgueux'),
(139, NULL, NULL, 2597, NULL, 'Chattanooga'),
(140, NULL, NULL, 7350, NULL, 'Sart-Eustache'),
(141, NULL, NULL, 1290, NULL, 'Champdani'),
(142, NULL, NULL, 7676, NULL, 'San Vicente del Caguán'),
(143, NULL, NULL, 6562, NULL, 'Dillenburg'),
(144, NULL, NULL, 5314, NULL, 'Carmen'),
(145, NULL, NULL, 4994, NULL, 'Dandenong'),
(146, NULL, NULL, 4413, NULL, 'Serralunga d\'Alba'),
(147, NULL, NULL, 3611, NULL, 'Cabras'),
(148, NULL, NULL, 5136, NULL, 'Gaithersburg'),
(149, NULL, NULL, 3769, NULL, 'Lesve'),
(150, NULL, NULL, 8210, NULL, 'Vreren'),
(151, NULL, NULL, 5667, NULL, 'Ambala Sadar'),
(152, NULL, NULL, 5492, NULL, 'Etroubles'),
(153, NULL, NULL, 8871, NULL, 'Deschambault'),
(154, NULL, NULL, 3810, NULL, 'Lodelinsart'),
(155, NULL, NULL, 4712, NULL, 'Etawah'),
(156, NULL, NULL, 4746, NULL, 'Beringen'),
(157, NULL, NULL, 5808, NULL, 'Desamparados'),
(158, NULL, NULL, 6342, NULL, 'Folx-les-Caves'),
(159, NULL, NULL, 5634, NULL, 'Yeongcheon'),
(160, NULL, NULL, 9918, NULL, 'Amravati'),
(161, NULL, NULL, 8019, NULL, 'Golspie'),
(162, NULL, NULL, 7121, NULL, 'Mokpo'),
(163, NULL, NULL, 4110, NULL, 'Lakeland County'),
(164, NULL, NULL, 9597, NULL, 'Leernes'),
(165, NULL, NULL, 4887, NULL, 'Cressa'),
(166, NULL, NULL, 8545, NULL, 'Hamburg'),
(167, NULL, NULL, 6191, NULL, 'Sant\'Agata Bolognese'),
(168, NULL, NULL, 1923, NULL, 'Sint-Pieters-Woluwe'),
(169, NULL, NULL, 8970, NULL, 'Solingen'),
(170, NULL, NULL, 3541, NULL, 'Reading'),
(171, NULL, NULL, 6982, NULL, 'Hualqui'),
(172, NULL, NULL, 9937, NULL, 'Bad Kreuznach'),
(173, NULL, NULL, 9777, NULL, 'Badajoz'),
(174, NULL, NULL, 7109, NULL, 'Levallois-Perret'),
(175, NULL, NULL, 5301, NULL, 'Sahiwal'),
(176, NULL, NULL, 3589, NULL, 'Itapipoca'),
(177, NULL, NULL, 5806, NULL, 'Laakdal'),
(178, NULL, NULL, 3476, NULL, 'Olcenengo'),
(179, NULL, NULL, 4477, NULL, 'Traun'),
(180, NULL, NULL, 9583, NULL, 'Toronto'),
(181, NULL, NULL, 7032, NULL, 'La Salle'),
(182, NULL, NULL, 6311, NULL, 'Whitchurch-Stouffville'),
(183, NULL, NULL, 7312, NULL, 'Port Coquitlam'),
(184, NULL, NULL, 3456, NULL, 'Yurzhnouralsk'),
(185, NULL, NULL, 6348, NULL, 'Chapecó'),
(186, NULL, NULL, 2425, NULL, 'Zandhoven'),
(187, NULL, NULL, 6698, NULL, 'Agen'),
(188, NULL, NULL, 9257, NULL, 'Cavaion Veronese'),
(189, NULL, NULL, 6199, NULL, 'Jerez de la Frontera'),
(190, NULL, NULL, 3685, NULL, 'Yellowhead County'),
(191, NULL, NULL, 4104, NULL, 'Dole'),
(192, NULL, NULL, 8045, NULL, 'Kasur'),
(193, NULL, NULL, 2033, NULL, 'Chiaromonte'),
(194, NULL, NULL, 7689, NULL, 'Autelbas'),
(195, NULL, NULL, 5521, NULL, 'Sukkur'),
(196, NULL, NULL, 9574, NULL, 'Khotkovo'),
(197, NULL, NULL, 6905, NULL, 'St. Austell'),
(198, NULL, NULL, 8950, NULL, 'Yekaterinburg'),
(199, NULL, NULL, 2837, NULL, 'Whakatane'),
(200, NULL, NULL, 3280, NULL, 'Preore'),
(201, NULL, NULL, 3116, '2020-12-04 19:21:07.370000', 'Crespo'),
(202, '2020-12-04 19:33:58.344000', '2020-12-04 19:35:13.604000', 12345, '2020-12-04 19:33:58.344000', 'CRESPO'),
(203, NULL, '2020-12-04 19:37:06.932000', 1234, '2020-12-04 19:25:10.509000', 'CRESPO'),
(204, NULL, '2020-12-04 19:37:08.596000', 1234, '2020-12-04 19:26:44.591000', 'CRESPO'),
(206, '2020-12-04 19:31:37.146000', '2020-12-04 19:39:40.634000', 12345, '2020-12-04 19:31:37.146000', 'CRESPO');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

CREATE TABLE `cliente` (
  `id_cliente` bigint NOT NULL,
  `actualizado` datetime(6) DEFAULT NULL,
  `apellido` varchar(255) DEFAULT NULL,
  `borrado` datetime(6) DEFAULT NULL,
  `creado` datetime(6) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `ciudad_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `cliente`
--

INSERT INTO `cliente` (`id_cliente`, `actualizado`, `apellido`, `borrado`, `creado`, `nombre`, `ciudad_id`) VALUES
(1, NULL, 'Manning', NULL, NULL, 'Wilma', 167),
(2, NULL, 'Chapman', NULL, NULL, 'Charles', 160),
(3, NULL, 'Hobbs', NULL, NULL, 'Amanda', 129),
(4, NULL, 'Daniels', NULL, NULL, 'Chester', 154),
(5, NULL, 'Everett', NULL, NULL, 'Clio', 179),
(6, NULL, 'William', NULL, NULL, 'Adrienne', 182),
(7, NULL, 'Dodson', NULL, NULL, 'Constance', 197),
(8, NULL, 'Harrell', NULL, NULL, 'Alfonso', 179),
(9, NULL, 'Gilbert', NULL, NULL, 'Amber', 107),
(10, NULL, 'Hale', NULL, NULL, 'Ursula', 121),
(11, NULL, 'Sharp', NULL, NULL, 'Scott', 180),
(12, NULL, 'Terrell', NULL, NULL, 'Rhona', 124),
(13, NULL, 'Forbes', NULL, NULL, 'Kevin', 153),
(14, NULL, 'Dickerson', NULL, NULL, 'Lars', 102),
(15, NULL, 'Boyer', NULL, NULL, 'Summer', 163),
(16, NULL, 'Obrien', NULL, NULL, 'Kiona', 135),
(17, NULL, 'Holder', NULL, NULL, 'Carlos', 147),
(18, NULL, 'Valentine', NULL, NULL, 'Baxter', 115),
(19, NULL, 'Gaines', NULL, NULL, 'Kieran', 172),
(20, NULL, 'Moran', NULL, NULL, 'Merrill', 117),
(21, NULL, 'Boyd', NULL, NULL, 'Grant', 101),
(22, NULL, 'Oliver', NULL, NULL, 'Porter', 179),
(23, NULL, 'Hayes', NULL, NULL, 'Inez', 110),
(24, NULL, 'George', NULL, NULL, 'Fleur', 170),
(25, NULL, 'Howard', NULL, NULL, 'Orlando', 111),
(26, NULL, 'Bauer', NULL, NULL, 'Carla', 116),
(27, NULL, 'Dominguez', NULL, NULL, 'Naomi', 122),
(28, NULL, 'Sullivan', NULL, NULL, 'Stephanie', 149),
(29, NULL, 'Whitley', NULL, NULL, 'Ainsley', 178),
(30, NULL, 'Stone', NULL, NULL, 'Maris', 115),
(31, NULL, 'Kemp', NULL, NULL, 'Marny', 116),
(32, NULL, 'Wells', NULL, NULL, 'Kyra', 160),
(33, NULL, 'Ray', NULL, NULL, 'Zenia', 184),
(34, NULL, 'Cortez', NULL, NULL, 'Karen', 178),
(35, NULL, 'Boyd', NULL, NULL, 'Marsden', 190),
(36, NULL, 'Mclaughlin', NULL, NULL, 'Benjamin', 158),
(37, NULL, 'Cabrera', NULL, NULL, 'Steel', 149),
(38, NULL, 'Huff', NULL, NULL, 'Kasper', 109),
(39, NULL, 'Dale', NULL, NULL, 'Cameron', 144),
(40, NULL, 'Finley', NULL, NULL, 'Nelle', 197),
(41, NULL, 'Mccall', NULL, NULL, 'Basil', 180),
(42, NULL, 'Wheeler', NULL, NULL, 'Cooper', 190),
(43, NULL, 'Finley', NULL, NULL, 'Charde', 102),
(44, NULL, 'Boone', NULL, NULL, 'Hamish', 153),
(45, NULL, 'Pollard', NULL, NULL, 'Brendan', 115),
(46, NULL, 'Santiago', NULL, NULL, 'Blossom', 116),
(47, NULL, 'Cochran', NULL, NULL, 'Caldwell', 174),
(48, NULL, 'Banks', NULL, NULL, 'Linda', 195),
(49, NULL, 'Miles', NULL, NULL, 'Aaron', 183),
(50, NULL, 'Howe', NULL, NULL, 'Dillon', 170),
(51, NULL, 'Waller', NULL, NULL, 'Cruz', 107),
(52, NULL, 'Reilly', NULL, NULL, 'Clio', 128),
(53, NULL, 'Kline', NULL, NULL, 'Sybill', 155),
(54, NULL, 'Faulkner', NULL, NULL, 'Kylynn', 172),
(55, NULL, 'House', NULL, NULL, 'Lucian', 160),
(56, NULL, 'Downs', NULL, NULL, 'Ulysses', 134),
(57, NULL, 'Chen', NULL, NULL, 'Hadley', 137),
(58, NULL, 'Doyle', NULL, NULL, 'Herman', 140),
(59, NULL, 'Bates', NULL, NULL, 'Maggy', 113),
(60, NULL, 'Marks', NULL, NULL, 'Brittany', 184),
(61, NULL, 'Conway', NULL, NULL, 'Yen', 113),
(62, NULL, 'Vargas', NULL, NULL, 'Lara', 102),
(63, NULL, 'Meadows', NULL, NULL, 'TaShya', 175),
(64, NULL, 'Ferrell', NULL, NULL, 'Ulysses', 126),
(65, NULL, 'Montgomery', NULL, NULL, 'Gil', 172),
(66, NULL, 'Knapp', NULL, NULL, 'Juliet', 164),
(67, NULL, 'Rose', NULL, NULL, 'Marsden', 145),
(68, NULL, 'Nielsen', NULL, NULL, 'Wesley', 159),
(69, NULL, 'Buck', NULL, NULL, 'Jonah', 163),
(70, NULL, 'Galloway', NULL, NULL, 'Miriam', 117),
(71, NULL, 'William', NULL, NULL, 'Alfreda', 179),
(72, NULL, 'Davenport', NULL, NULL, 'Kim', 164),
(73, NULL, 'Moon', NULL, NULL, 'Indira', 109),
(74, NULL, 'Gonzalez', NULL, NULL, 'Gillian', 161),
(75, NULL, 'Roman', NULL, NULL, 'Tara', 104),
(76, NULL, 'Hanson', NULL, NULL, 'Josiah', 157),
(77, NULL, 'Savage', NULL, NULL, 'Leah', 192),
(78, NULL, 'Robbins', NULL, NULL, 'Byron', 152),
(79, NULL, 'Pollard', NULL, NULL, 'Zoe', 116),
(80, NULL, 'Brooks', NULL, NULL, 'Zeus', 112),
(81, NULL, 'Valentine', NULL, NULL, 'Haviva', 116),
(82, NULL, 'Page', NULL, NULL, 'Gavin', 133),
(83, NULL, 'Carrillo', NULL, NULL, 'Madeson', 161),
(84, NULL, 'Klein', NULL, NULL, 'Hayden', 191),
(85, NULL, 'Mercado', NULL, NULL, 'Honorato', 135),
(86, NULL, 'Spence', NULL, NULL, 'Hedda', 105),
(87, NULL, 'Bryant', NULL, NULL, 'Mallory', 119),
(88, NULL, 'Mcfarland', NULL, NULL, 'Yuli', 182),
(89, NULL, 'Mccarty', NULL, NULL, 'Marshall', 121),
(90, NULL, 'Ellison', NULL, NULL, 'Alvin', 180),
(91, NULL, 'Moreno', NULL, NULL, 'Melanie', 183),
(92, NULL, 'Yates', NULL, NULL, 'Ray', 111),
(93, NULL, 'Woodard', NULL, NULL, 'Deborah', 116),
(94, NULL, 'Contreras', NULL, NULL, 'Mercedes', 111),
(95, NULL, 'Poole', NULL, NULL, 'Courtney', 115),
(96, NULL, 'Lindsay', NULL, NULL, 'Kenyon', 195),
(97, NULL, 'Bennett', NULL, NULL, 'Hyatt', 145),
(98, NULL, 'Merrill', NULL, NULL, 'Camilla', 123),
(99, NULL, 'Key', NULL, NULL, 'Coby', 127),
(100, NULL, 'Owen', NULL, NULL, 'Signe', 105),
(101, NULL, 'Lopez', NULL, '2020-12-04 20:34:08.228000', 'Gaston', 201),
(102, '2020-12-04 20:39:23.479000', 'Pepon', NULL, '2020-12-04 20:39:23.478000', 'Pepito se mudo a crespo', 201);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pedido`
--

CREATE TABLE `pedido` (
  `id_pedido` bigint NOT NULL,
  `actualizado` datetime(6) DEFAULT NULL,
  `borrado` datetime(6) DEFAULT NULL,
  `creado` datetime(6) DEFAULT NULL,
  `total` double DEFAULT NULL,
  `cliente_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `pedido`
--

INSERT INTO `pedido` (`id_pedido`, `actualizado`, `borrado`, `creado`, `total`, `cliente_id`) VALUES
(1, NULL, NULL, NULL, 0, 55),
(2, NULL, NULL, NULL, 0, 39),
(3, NULL, NULL, NULL, 0, 35),
(4, NULL, NULL, NULL, 0, 84),
(5, NULL, NULL, NULL, 0, 58),
(6, NULL, NULL, NULL, 0, 69),
(7, NULL, NULL, NULL, 0, 82),
(8, NULL, NULL, NULL, 0, 1),
(9, NULL, NULL, NULL, 0, 41),
(10, NULL, NULL, NULL, 0, 14),
(11, NULL, NULL, NULL, 0, 76),
(12, NULL, NULL, NULL, 0, 74),
(13, NULL, NULL, NULL, 0, 80),
(14, NULL, NULL, NULL, 0, 97),
(15, NULL, NULL, NULL, 0, 31),
(16, NULL, NULL, NULL, 0, 9),
(17, NULL, NULL, NULL, 0, 71),
(18, NULL, NULL, NULL, 0, 19),
(19, NULL, NULL, NULL, 0, 18),
(20, NULL, NULL, NULL, 0, 87),
(21, NULL, NULL, NULL, 0, 30),
(22, NULL, NULL, NULL, 0, 56),
(23, NULL, NULL, NULL, 0, 16),
(24, NULL, NULL, NULL, 0, 60),
(25, NULL, NULL, NULL, 0, 73),
(26, NULL, NULL, NULL, 0, 58),
(27, NULL, NULL, NULL, 0, 73),
(28, NULL, NULL, NULL, 0, 55),
(29, NULL, NULL, NULL, 0, 84),
(30, NULL, NULL, NULL, 0, 75),
(31, NULL, NULL, NULL, 0, 99),
(32, NULL, NULL, NULL, 0, 71),
(33, NULL, NULL, NULL, 0, 76),
(34, NULL, NULL, NULL, 0, 48),
(35, NULL, NULL, NULL, 0, 39),
(36, NULL, NULL, NULL, 0, 17),
(37, NULL, NULL, NULL, 0, 99),
(38, NULL, NULL, NULL, 0, 13),
(39, NULL, NULL, NULL, 0, 45),
(40, NULL, NULL, NULL, 0, 2),
(41, NULL, NULL, NULL, 0, 4),
(42, NULL, NULL, NULL, 0, 19),
(43, NULL, NULL, NULL, 0, 87),
(44, NULL, NULL, NULL, 0, 77),
(45, NULL, NULL, NULL, 0, 76),
(46, NULL, NULL, NULL, 0, 58),
(47, NULL, NULL, NULL, 0, 35),
(48, NULL, NULL, NULL, 0, 13),
(49, NULL, NULL, NULL, 0, 22),
(50, NULL, NULL, NULL, 0, 55),
(51, NULL, NULL, NULL, 0, 22),
(52, NULL, NULL, NULL, 0, 45),
(53, NULL, NULL, NULL, 0, 36),
(54, NULL, NULL, NULL, 0, 50),
(55, NULL, NULL, NULL, 0, 94),
(56, NULL, NULL, NULL, 0, 70),
(57, NULL, NULL, NULL, 0, 14),
(58, NULL, NULL, NULL, 0, 76),
(59, NULL, NULL, NULL, 0, 18),
(60, NULL, NULL, NULL, 0, 95),
(61, NULL, NULL, NULL, 0, 53),
(62, NULL, NULL, NULL, 0, 71),
(63, NULL, NULL, NULL, 0, 49),
(64, NULL, NULL, NULL, 0, 63),
(65, NULL, NULL, NULL, 0, 67),
(66, NULL, NULL, NULL, 0, 65),
(67, NULL, NULL, NULL, 0, 16),
(68, NULL, NULL, NULL, 0, 73),
(69, NULL, NULL, NULL, 0, 30),
(70, NULL, NULL, NULL, 0, 96),
(71, NULL, NULL, NULL, 0, 8),
(72, NULL, NULL, NULL, 0, 31),
(73, NULL, NULL, NULL, 0, 83),
(74, NULL, NULL, NULL, 0, 19),
(75, NULL, NULL, NULL, 0, 23),
(76, NULL, NULL, NULL, 0, 56),
(77, NULL, NULL, NULL, 0, 40),
(78, NULL, NULL, NULL, 0, 38),
(79, NULL, NULL, NULL, 0, 15),
(80, NULL, NULL, NULL, 0, 33),
(81, NULL, NULL, NULL, 0, 31),
(82, NULL, NULL, NULL, 0, 39),
(83, NULL, NULL, NULL, 0, 29),
(84, NULL, NULL, NULL, 0, 23),
(85, NULL, NULL, NULL, 0, 32),
(86, NULL, NULL, NULL, 0, 5),
(87, NULL, NULL, NULL, 0, 65),
(88, NULL, NULL, NULL, 0, 40),
(89, NULL, NULL, NULL, 0, 50),
(90, NULL, NULL, NULL, 0, 23),
(91, NULL, NULL, NULL, 0, 75),
(92, NULL, NULL, NULL, 0, 63),
(93, NULL, NULL, NULL, 0, 92),
(94, NULL, NULL, NULL, 0, 48),
(95, NULL, NULL, NULL, 0, 47),
(96, NULL, NULL, NULL, 0, 62),
(97, NULL, NULL, NULL, 0, 88),
(98, NULL, NULL, NULL, 0, 43),
(99, NULL, NULL, NULL, 0, 86),
(100, NULL, NULL, NULL, 0, 63),
(101, '2020-12-04 21:36:35.924000', NULL, '2020-12-04 21:36:35.924000', NULL, NULL),
(103, '2020-12-04 21:49:22.496000', NULL, '2020-12-04 21:49:22.493000', 999.99, 56);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pedido_producto`
--

CREATE TABLE `pedido_producto` (
  `id_pedido_producto` bigint NOT NULL,
  `actualizado` datetime(6) DEFAULT NULL,
  `borrado` datetime(6) DEFAULT NULL,
  `cantidad` int DEFAULT NULL,
  `creado` datetime(6) DEFAULT NULL,
  `total` double DEFAULT NULL,
  `pedido_id` bigint DEFAULT NULL,
  `producto_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `pedido_producto`
--

INSERT INTO `pedido_producto` (`id_pedido_producto`, `actualizado`, `borrado`, `cantidad`, `creado`, `total`, `pedido_id`, `producto_id`) VALUES
(1, NULL, NULL, 20, NULL, 0, 71, 13),
(2, NULL, NULL, 16, NULL, 0, 96, 82),
(3, NULL, NULL, 12, NULL, 0, 82, 26),
(4, NULL, NULL, 21, NULL, 0, 4, 16),
(5, NULL, NULL, 26, NULL, 0, 66, 93),
(6, NULL, NULL, 12, NULL, 0, 74, 12),
(7, NULL, NULL, 25, NULL, 0, 32, 7),
(8, NULL, NULL, 21, NULL, 0, 76, 36),
(9, NULL, NULL, 21, NULL, 0, 52, 18),
(10, NULL, NULL, 26, NULL, 0, 48, 38),
(11, NULL, NULL, 11, NULL, 0, 53, 14),
(12, NULL, NULL, 22, NULL, 0, 34, 49),
(13, NULL, NULL, 24, NULL, 0, 32, 18),
(14, NULL, NULL, 26, NULL, 0, 86, 48),
(15, NULL, NULL, 8, NULL, 0, 58, 70),
(16, NULL, NULL, 25, NULL, 0, 83, 35),
(17, NULL, NULL, 27, NULL, 0, 42, 2),
(18, NULL, NULL, 23, NULL, 0, 70, 61),
(19, NULL, NULL, 19, NULL, 0, 69, 80),
(20, NULL, NULL, 16, NULL, 0, 96, 55),
(21, NULL, NULL, 27, NULL, 0, 67, 54),
(22, NULL, NULL, 12, NULL, 0, 7, 98),
(23, NULL, NULL, 19, NULL, 0, 57, 60),
(24, NULL, NULL, 30, NULL, 0, 91, 69),
(25, NULL, NULL, 2, NULL, 0, 65, 78),
(26, NULL, NULL, 8, NULL, 0, 96, 8),
(27, NULL, NULL, 17, NULL, 0, 40, 63),
(28, NULL, NULL, 18, NULL, 0, 89, 87),
(29, NULL, NULL, 6, NULL, 0, 38, 69),
(30, NULL, NULL, 15, NULL, 0, 16, 26),
(31, NULL, NULL, 30, NULL, 0, 82, 71),
(32, NULL, NULL, 15, NULL, 0, 20, 64),
(33, NULL, NULL, 3, NULL, 0, 6, 32),
(34, NULL, NULL, 16, NULL, 0, 36, 40),
(35, NULL, NULL, 25, NULL, 0, 57, 7),
(36, NULL, NULL, 9, NULL, 0, 70, 59),
(37, NULL, NULL, 26, NULL, 0, 49, 26),
(38, NULL, NULL, 4, NULL, 0, 46, 73),
(39, NULL, NULL, 5, NULL, 0, 84, 17),
(40, NULL, NULL, 14, NULL, 0, 42, 79),
(41, NULL, NULL, 7, NULL, 0, 68, 23),
(42, NULL, NULL, 26, NULL, 0, 5, 89),
(43, NULL, NULL, 28, NULL, 0, 60, 65),
(44, NULL, NULL, 5, NULL, 0, 34, 57),
(45, NULL, NULL, 26, NULL, 0, 94, 94),
(46, NULL, NULL, 5, NULL, 0, 22, 96),
(47, NULL, NULL, 15, NULL, 0, 27, 98),
(48, NULL, NULL, 30, NULL, 0, 10, 43),
(49, NULL, NULL, 20, NULL, 0, 77, 3),
(50, NULL, NULL, 9, NULL, 0, 91, 24),
(51, NULL, NULL, 15, NULL, 0, 18, 16),
(52, NULL, NULL, 6, NULL, 0, 91, 84),
(53, NULL, NULL, 17, NULL, 0, 14, 9),
(54, NULL, NULL, 9, NULL, 0, 68, 1),
(55, NULL, NULL, 18, NULL, 0, 83, 30),
(56, NULL, NULL, 4, NULL, 0, 1, 56),
(57, NULL, NULL, 16, NULL, 0, 47, 11),
(58, NULL, NULL, 30, NULL, 0, 70, 71),
(59, NULL, NULL, 15, NULL, 0, 6, 47),
(60, NULL, NULL, 5, NULL, 0, 80, 93),
(61, NULL, NULL, 12, NULL, 0, 55, 11),
(62, NULL, NULL, 16, NULL, 0, 51, 59),
(63, NULL, NULL, 18, NULL, 0, 56, 75),
(64, NULL, NULL, 24, NULL, 0, 26, 97),
(65, NULL, NULL, 15, NULL, 0, 35, 81),
(66, NULL, NULL, 15, NULL, 0, 46, 7),
(67, NULL, NULL, 10, NULL, 0, 52, 22),
(68, NULL, NULL, 4, NULL, 0, 61, 68),
(69, NULL, NULL, 18, NULL, 0, 29, 53),
(70, NULL, NULL, 28, NULL, 0, 92, 89),
(71, NULL, NULL, 20, NULL, 0, 94, 67),
(72, NULL, NULL, 24, NULL, 0, 12, 18),
(73, NULL, NULL, 8, NULL, 0, 78, 10),
(74, NULL, NULL, 24, NULL, 0, 4, 39),
(75, NULL, NULL, 4, NULL, 0, 6, 52),
(76, NULL, NULL, 2, NULL, 0, 40, 99),
(77, NULL, NULL, 19, NULL, 0, 58, 80),
(78, NULL, NULL, 7, NULL, 0, 40, 94),
(79, NULL, NULL, 13, NULL, 0, 53, 89),
(80, NULL, NULL, 15, NULL, 0, 50, 77),
(81, NULL, NULL, 13, NULL, 0, 59, 81),
(82, NULL, NULL, 6, NULL, 0, 89, 51),
(83, NULL, NULL, 3, NULL, 0, 9, 35),
(84, NULL, NULL, 9, NULL, 0, 7, 40),
(85, NULL, NULL, 27, NULL, 0, 64, 58),
(86, NULL, NULL, 17, NULL, 0, 9, 76),
(87, NULL, NULL, 12, NULL, 0, 45, 35),
(88, NULL, NULL, 17, NULL, 0, 88, 99),
(89, NULL, NULL, 22, NULL, 0, 49, 27),
(90, NULL, NULL, 9, NULL, 0, 57, 36),
(91, NULL, NULL, 6, NULL, 0, 74, 62),
(92, NULL, NULL, 21, NULL, 0, 52, 58),
(93, NULL, NULL, 9, NULL, 0, 6, 68),
(94, NULL, NULL, 26, NULL, 0, 68, 87),
(95, NULL, NULL, 18, NULL, 0, 19, 39),
(96, '2020-12-04 23:38:20.472000', NULL, 1, '2020-12-04 23:38:20.469000', 222, 46, 89),
(97, NULL, NULL, 10, NULL, 0, 1, 7),
(98, NULL, NULL, 20, NULL, 0, 56, 68),
(99, NULL, NULL, 26, NULL, 0, 55, 75),
(100, NULL, NULL, 17, NULL, 0, 87, 54);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `producto`
--

CREATE TABLE `producto` (
  `id_producto` bigint NOT NULL,
  `actualizado` datetime(6) DEFAULT NULL,
  `borrado` datetime(6) DEFAULT NULL,
  `creado` datetime(6) DEFAULT NULL,
  `marca` varchar(255) DEFAULT NULL,
  `modelo` varchar(255) DEFAULT NULL,
  `precio` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `producto`
--

INSERT INTO `producto` (`id_producto`, `actualizado`, `borrado`, `creado`, `marca`, `modelo`, `precio`) VALUES
(1, NULL, NULL, NULL, 'ornare.', 'lectus quis', 27493),
(2, NULL, NULL, NULL, 'Maecenas', 'vehicula risus.', 96110),
(3, NULL, NULL, NULL, 'nisi', 'Cras', 15669),
(4, NULL, NULL, NULL, 'augue.', 'scelerisque dui.', 47331),
(5, NULL, NULL, NULL, 'Duis', 'ut, pellentesque', 28646),
(6, NULL, NULL, NULL, 'sociis', 'sit', 14603),
(7, NULL, NULL, NULL, 'vestibulum', 'Nulla facilisis.', 47852),
(8, NULL, NULL, NULL, 'non', 'est,', 23930),
(9, NULL, NULL, NULL, 'libero', 'nec tellus.', 57144),
(10, NULL, NULL, NULL, 'non', 'mauris.', 72118),
(11, NULL, NULL, NULL, 'Nullam', 'consequat', 4703),
(12, NULL, NULL, NULL, 'Nunc', 'Mauris blandit', 96396),
(13, NULL, NULL, NULL, 'mi.', 'Curae;', 86403),
(14, NULL, NULL, NULL, 'magnis', 'Donec', 96848),
(15, NULL, NULL, NULL, 'Suspendisse', 'Nullam', 38209),
(16, NULL, NULL, NULL, 'tempor', 'lectus', 13152),
(17, NULL, NULL, NULL, 'Aliquam', 'nec luctus', 61088),
(18, NULL, NULL, NULL, 'commodo', 'at sem', 33893),
(19, NULL, NULL, NULL, 'et', 'sem,', 67455),
(20, NULL, NULL, NULL, 'rutrum', 'nec,', 9245),
(21, NULL, NULL, NULL, 'congue.', 'aliquet', 90066),
(22, NULL, NULL, NULL, 'vitae', 'Aenean eget', 81549),
(23, NULL, NULL, NULL, 'semper', 'Donec', 70499),
(24, NULL, NULL, NULL, 'nostra,', 'orci', 36786),
(25, NULL, NULL, NULL, 'elementum,', 'ipsum.', 14133),
(26, NULL, NULL, NULL, 'vitae,', 'libero. Integer', 78846),
(27, NULL, NULL, NULL, 'parturient', 'Vivamus', 62272),
(28, NULL, NULL, NULL, 'nulla.', 'Quisque', 31371),
(29, NULL, NULL, NULL, 'mollis.', 'id', 46219),
(30, NULL, NULL, NULL, 'vitae', 'Vestibulum', 59442),
(31, NULL, NULL, NULL, 'tincidunt', 'consectetuer euismod', 69987),
(32, NULL, NULL, NULL, 'justo', 'Quisque', 60689),
(33, NULL, NULL, NULL, 'neque', 'Sed eu', 22466),
(34, NULL, NULL, NULL, 'facilisis', 'scelerisque,', 99176),
(35, NULL, NULL, NULL, 'amet', 'eu dui.', 30642),
(36, NULL, NULL, NULL, 'convallis', 'et netus', 46862),
(37, NULL, NULL, NULL, 'dignissim', 'felis purus', 90521),
(38, NULL, NULL, NULL, 'luctus', 'sem ut', 28298),
(39, NULL, NULL, NULL, 'vulputate', 'Sed eget', 7909),
(40, NULL, NULL, NULL, 'Aliquam', 'purus ac', 3666),
(41, NULL, NULL, NULL, 'aptent', 'odio. Aliquam', 40101),
(42, NULL, NULL, NULL, 'euismod', 'mauris. Morbi', 37675),
(43, NULL, NULL, NULL, 'enim', 'vel arcu.', 97286),
(44, NULL, NULL, NULL, 'Sed', 'tristique', 34682),
(45, NULL, NULL, NULL, 'urna', 'in', 67888),
(46, NULL, NULL, NULL, 'odio', 'mi', 57803),
(47, NULL, NULL, NULL, 'nisl.', 'hendrerit neque.', 32155),
(48, NULL, NULL, NULL, 'pharetra.', 'Maecenas', 63622),
(49, NULL, NULL, NULL, 'Praesent', 'purus sapien,', 14137),
(50, NULL, NULL, NULL, 'cubilia', 'ante.', 31297),
(51, NULL, NULL, NULL, 'vel,', 'erat', 49120),
(52, NULL, NULL, NULL, 'magnis', 'sapien,', 88957),
(53, NULL, NULL, NULL, 'justo.', 'imperdiet ornare.', 94341),
(54, NULL, NULL, NULL, 'ut', 'Nulla', 45181),
(55, NULL, NULL, NULL, 'in', 'dolor. Quisque', 89307),
(56, NULL, NULL, NULL, 'ac', 'turpis', 62067),
(57, NULL, NULL, NULL, 'Proin', 'ante ipsum', 60594),
(58, NULL, NULL, NULL, 'enim.', 'vulputate, nisi', 10635),
(59, NULL, NULL, NULL, 'in', 'scelerisque', 82254),
(60, NULL, NULL, NULL, 'hendrerit.', 'eros.', 24066),
(61, NULL, NULL, NULL, 'In', 'accumsan', 68051),
(62, NULL, NULL, NULL, 'convallis', 'cursus. Nunc', 53508),
(63, NULL, NULL, NULL, 'ornare,', 'nibh lacinia', 89275),
(64, NULL, NULL, NULL, 'nibh.', 'In at', 87831),
(65, NULL, NULL, NULL, 'risus.', 'metus.', 36683),
(66, NULL, NULL, NULL, 'amet', 'vel quam', 80134),
(67, NULL, NULL, NULL, 'auctor', 'odio, auctor', 42656),
(68, NULL, NULL, NULL, 'Morbi', 'Nunc lectus', 77311),
(69, NULL, NULL, NULL, 'nec,', 'fames', 10043),
(70, NULL, NULL, NULL, 'Curabitur', 'Mauris', 28419),
(71, NULL, NULL, NULL, 'commodo', 'purus ac', 24600),
(72, NULL, NULL, NULL, 'mi', 'natoque penatibus', 61651),
(73, NULL, NULL, NULL, 'dignissim.', 'semper', 73966),
(74, NULL, NULL, NULL, 'id', 'pulvinar arcu', 68748),
(75, NULL, NULL, NULL, 'ut,', 'hymenaeos. Mauris', 2754),
(76, NULL, NULL, NULL, 'Aenean', 'sodales at,', 61370),
(77, NULL, NULL, NULL, 'lacinia', 'volutpat nunc', 9396),
(78, NULL, NULL, NULL, 'Donec', 'id', 69356),
(79, NULL, NULL, NULL, 'erat', 'velit eu', 85383),
(80, NULL, NULL, NULL, 'Duis', 'cursus', 82433),
(81, NULL, NULL, NULL, 'ornare', 'purus.', 15918),
(82, NULL, NULL, NULL, 'ipsum', 'et netus', 93758),
(83, NULL, NULL, NULL, 'eget', 'ipsum', 67130),
(84, NULL, NULL, NULL, 'felis.', 'libero', 31759),
(85, NULL, NULL, NULL, 'Sed', 'Pellentesque', 15966),
(86, NULL, NULL, NULL, 'ipsum.', 'vel arcu', 63526),
(87, NULL, NULL, NULL, 'Nunc', 'diam nunc,', 65513),
(88, NULL, NULL, NULL, 'auctor', 'amet lorem', 40648),
(89, NULL, NULL, NULL, 'magnis', 'luctus et', 2348),
(90, NULL, NULL, NULL, 'molestie', 'eu erat', 4074),
(91, NULL, NULL, NULL, 'ultrices,', 'et magnis', 60289),
(92, NULL, NULL, NULL, 'convallis', 'elit.', 82556),
(93, NULL, NULL, NULL, 'tristique', 'Sed molestie.', 31380),
(94, NULL, NULL, NULL, 'enim', 'sem.', 11532),
(95, NULL, NULL, NULL, 'molestie', 'molestie', 16787),
(96, NULL, NULL, NULL, 'nec,', 'accumsan convallis,', 74193),
(97, NULL, NULL, NULL, 'sit', 'parturient montes,', 25254),
(98, NULL, NULL, NULL, 'Mauris', 'mauris', 92891),
(99, NULL, NULL, NULL, 'dictum', 'ac turpis', 5980),
(100, NULL, NULL, NULL, 'aliquet', 'elit,', 37686),
(101, '2020-12-04 22:41:23.720000', NULL, '2020-12-04 22:41:23.720000', 'Tramontina Plus', 'Serrucho', 250.5);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `ciudad`
--
ALTER TABLE `ciudad`
  ADD PRIMARY KEY (`id_ciudad`);

--
-- Indices de la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`id_cliente`),
  ADD KEY `FK1le6orj8lrio9vg2jdayn5kqy` (`ciudad_id`);

--
-- Indices de la tabla `pedido`
--
ALTER TABLE `pedido`
  ADD PRIMARY KEY (`id_pedido`),
  ADD KEY `FK30s8j2ktpay6of18lbyqn3632` (`cliente_id`);

--
-- Indices de la tabla `pedido_producto`
--
ALTER TABLE `pedido_producto`
  ADD PRIMARY KEY (`id_pedido_producto`),
  ADD KEY `FK7uyg0ynfe4wadl7ha9bmtynvm` (`pedido_id`),
  ADD KEY `FKl9lfd6a7bi0o5qn2f3epfbpin` (`producto_id`);

--
-- Indices de la tabla `producto`
--
ALTER TABLE `producto`
  ADD PRIMARY KEY (`id_producto`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `ciudad`
--
ALTER TABLE `ciudad`
  MODIFY `id_ciudad` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=207;

--
-- AUTO_INCREMENT de la tabla `cliente`
--
ALTER TABLE `cliente`
  MODIFY `id_cliente` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=103;

--
-- AUTO_INCREMENT de la tabla `pedido`
--
ALTER TABLE `pedido`
  MODIFY `id_pedido` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=104;

--
-- AUTO_INCREMENT de la tabla `pedido_producto`
--
ALTER TABLE `pedido_producto`
  MODIFY `id_pedido_producto` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=101;

--
-- AUTO_INCREMENT de la tabla `producto`
--
ALTER TABLE `producto`
  MODIFY `id_producto` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=103;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD CONSTRAINT `FK1le6orj8lrio9vg2jdayn5kqy` FOREIGN KEY (`ciudad_id`) REFERENCES `ciudad` (`id_ciudad`);

--
-- Filtros para la tabla `pedido`
--
ALTER TABLE `pedido`
  ADD CONSTRAINT `FK30s8j2ktpay6of18lbyqn3632` FOREIGN KEY (`cliente_id`) REFERENCES `cliente` (`id_cliente`);

--
-- Filtros para la tabla `pedido_producto`
--
ALTER TABLE `pedido_producto`
  ADD CONSTRAINT `FK7uyg0ynfe4wadl7ha9bmtynvm` FOREIGN KEY (`pedido_id`) REFERENCES `pedido` (`id_pedido`),
  ADD CONSTRAINT `FKl9lfd6a7bi0o5qn2f3epfbpin` FOREIGN KEY (`producto_id`) REFERENCES `producto` (`id_producto`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
