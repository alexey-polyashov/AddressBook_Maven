-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Хост: 127.0.0.1:3306
-- Время создания: Янв 14 2024 г., 01:00
-- Версия сервера: 8.0.30
-- Версия PHP: 7.2.34

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База данных: `mikron`
--

-- --------------------------------------------------------

--
-- Структура таблицы `department`
--

CREATE TABLE `department` (
  `id` bigint NOT NULL,
  `code` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `data_type` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT 'DEP',
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `head_id` bigint DEFAULT NULL,
  `parent_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Дамп данных таблицы `department`
--

INSERT INTO `department` (`id`, `code`, `data_type`, `name`, `head_id`, `parent_id`) VALUES
(1, '200000', 'DEP', 'Завод', 1, NULL),
(2, '209757', 'DEP', 'Дирекция по ИТ и цифровизации', 2, 1),
(3, '215160', 'DEP', 'Дирекция по маркетингу', NULL, 1),
(4, '209761', 'DEP', 'Дирекция по ИТ и цифровизации, Отдел автоматизации производственных и технологических процессов', 3, 2),
(5, '209759', 'DEP', 'Дирекция по ИТ и цифровизации, Отдел разработки и поддержки 1С', 5, 2),
(6, '209758', 'DEP', 'Дирекция по ИТ и цифровизации, Отдел системного администрирования', 10, 2),
(7, '209760', 'DEP', 'Дирекция по ИТ и цифровизации, Отдел технической поддержки пользователей', 14, 2),
(8, '209761-2', 'DEP', 'Дирекция по ИТ и цифровизации, Отдел автоматизации производственных и технологических процессов, Группа автоматизации производственного оборудования', 4, 4),
(9, '215160-1', 'DEP', 'Дирекция по маркетингу, Группа внутренней аналитики', 20, 3),
(10, '200010-03', 'DEP', 'АУП заместителя генерального директора по операционной деятельности', NULL, 1),
(11, '201400', 'DEP', 'Цех 1', 28, 1),
(12, '201401', 'DEP', 'Цех 1, АУП', NULL, 11),
(13, '201411', 'DEP', 'Цех 1, Группа обеспечения производства', NULL, 11),
(14, '201420', 'DEP', 'Цех 1, Группа обслуживания оборудования 100/150', NULL, 11),
(15, '201421', 'DEP', 'Цех 1, Группа обслуживания оборудования 200', NULL, 11),
(16, '201419', 'DEP', 'Цех 1, Группа подготовки производства', NULL, 11),
(17, '201412', 'DEP', 'Цех 1, Отдел изготовления фотошаблонов (ОИФШ)', NULL, 11);

-- --------------------------------------------------------

--
-- Структура таблицы `department_seq`
--

CREATE TABLE `department_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Дамп данных таблицы `department_seq`
--

INSERT INTO `department_seq` (`next_val`) VALUES
(101);

-- --------------------------------------------------------

--
-- Структура таблицы `full_text_index`
--

CREATE TABLE `full_text_index` (
  `id` bigint NOT NULL,
  `data_type` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `presentation` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `text` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `uid` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Структура таблицы `full_text_index_seq`
--

CREATE TABLE `full_text_index_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Дамп данных таблицы `full_text_index_seq`
--

INSERT INTO `full_text_index_seq` (`next_val`) VALUES
(1);

-- --------------------------------------------------------

--
-- Структура таблицы `person_entity`
--

CREATE TABLE `person_entity` (
  `id` bigint NOT NULL,
  `data_type` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT 'P',
  `email` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `full_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `phone_number` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `position` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `tab_number` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `department_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Дамп данных таблицы `person_entity`
--

INSERT INTO `person_entity` (`id`, `data_type`, `email`, `full_name`, `phone_number`, `position`, `tab_number`, `department_id`) VALUES
(1, 'P', 'sekretari3@mikron.ru', 'Хасьянова Гюльнара Шамильевна', '4000', 'Генеральный директор', '034800', 1),
(2, 'P', 'avinnikov@mikron.ru', 'Винников Александр Алексеевич', NULL, 'Директор по информационным технологиям и цифровизации', '035480', 2),
(3, 'P', 'oivashchenko@mikron.ru', 'Иващенко Олег Валерьевич', '4162', 'Заместитель начальника отдела', '030808', 4),
(4, 'P', 'aaglukhov@mikron.ru', 'Глухов Алексей Александрович', NULL, 'Руководитель группы', '031228', 8),
(5, 'P', 'elyutsius@mikron.ru', 'Люциус Евгений Сергеевич', '41-68', 'Начальник отдела', '034092', 5),
(6, 'P', 'idzhanmamedov@mikron.ru', 'Джанмамедов Искандер Александрович', NULL, 'Старший программист', '035431', 5),
(7, 'P', 'tkilina@mikron.ru', 'Килина Татьяна Анатольевна', '4203', 'Программист', '034067', 5),
(8, 'P', 'akovtun@mikron.ru', 'Ковтун Алексей Сергеевич', NULL, 'Ведущий инженер-программист', '035042', 5),
(9, 'P', 'apolyashov@mikron.ru', 'Поляшов Алексей Анатольевич', '4202', 'Ведущий программист', '034950', 5),
(10, 'P', 'daleksandrov@mikron.ru', 'Александров Дмитрий Владимирович', NULL, 'Начальник отдела-системный архитектор', '035196', 6),
(11, 'P', 'kmakeev@mikron.ru', 'Макеев Константин Алексеевич', '4489', 'Системный администратор', '034279', 6),
(12, 'P', 'sutniashvili@mikron.ru', 'Утниашвили Сергей Владимирович', NULL, 'Старший системный администратор информационных систем безопасности', '035466', 6),
(13, 'P', 'nshenshin@mikron.ru', 'Шеньшин Николай Викторович', '4064', 'Системный администратор', '034073', 6),
(14, 'P', 'smuratov@mikron.ru', 'Муратов Сергей Николаевич', '40-40', 'Начальник отдела', '035337', 7),
(15, 'P', 'ebalobaeva@mikron.ru', 'Балобаева Екатерина Дмитриевна', '4610', 'Специалист', '034884', 7),
(16, 'P', 'sivachenkov@mikron.ru', 'Иваченков Сергей Михайлович', '4611', 'Старший специалист технической поддержки', '034879', 7),
(17, 'P', 'dklevtsov@mikron.ru', 'Клевцов Дмитрий Васильевич', '4607', 'Старший специалист технической поддержки', '035371', 7),
(18, 'P', 'mkozina@mikron.ru', 'Козина Марина Адольфовна', '1049', 'Оператор электронно-вычислительных машин', '014995', 7),
(19, 'P', 'vlosipov@mikron.ru', 'Осипов Владимир Владимирович', '4554', 'Специалист', '031720', 7),
(20, 'P', 'vgafiyatulina@mikron.ru', 'Гафиятулина Виктория Тахировна', '4534', 'Руководитель группы', '034612', 9),
(21, 'P', 'echugunov@mikron.ru', 'Чугунов Евгений Юрьевич', '4565', 'Менеджер по маркетингу', '034402', 3),
(22, 'P', 'kabagyan@mikron.ru', 'Абагян Карина Сергеевна', '4015', 'Директор по стратегическому развитию', '029793', 10),
(23, 'P', 'abarkhotkin@mikron.ru', 'Бархоткин Андрей Вячеславович', NULL, 'Менеджер проекта', '035249', 10),
(24, 'P', 'vkladova@mikron.ru', 'Кладова Виктория Андреевна', NULL, 'Специалист по проектам', '035085', 10),
(25, 'P', 'akorshak@mikron.ru', 'Коршак Алексей Борисович', '4403', 'Руководитель направления стратегических коммуникаций', '034019', 10),
(26, 'P', 'llichmanova@mikron.ru', 'Личманова Людмила Владимировна', '4402', 'Специалист по взаимодействию с отраслевым сообществом', '032443', 10),
(27, 'P', 'ichukhnov@mikron.ru', 'Чухнов Иван Александрович', '4022', 'Специалист по стратегическим проектам', '034235', 10),
(28, 'P', 'afeklistov@mikron.ru', 'Феклистов Алексей Петрович', '4311', 'Начальник цеха', '032249', 11);

-- --------------------------------------------------------

--
-- Структура таблицы `person_entity_seq`
--

CREATE TABLE `person_entity_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Дамп данных таблицы `person_entity_seq`
--

INSERT INTO `person_entity_seq` (`next_val`) VALUES
(1);

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `department`
--
ALTER TABLE `department`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_8ginee11c6lp4st3l24gb7rv6` (`head_id`),
  ADD KEY `FKmgsnnmudxrwqidn4f64q8rp4o` (`parent_id`);

--
-- Индексы таблицы `full_text_index`
--
ALTER TABLE `full_text_index`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `person_entity`
--
ALTER TABLE `person_entity`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKkhext1jur2e90s7kpnqewhm3l` (`department_id`);

--
-- Ограничения внешнего ключа сохраненных таблиц
--

--
-- Ограничения внешнего ключа таблицы `department`
--
ALTER TABLE `department`
  ADD CONSTRAINT `FKma2th1g6rvgtlxcaqrkwgye76` FOREIGN KEY (`head_id`) REFERENCES `person_entity` (`id`),
  ADD CONSTRAINT `FKmgsnnmudxrwqidn4f64q8rp4o` FOREIGN KEY (`parent_id`) REFERENCES `department` (`id`);

--
-- Ограничения внешнего ключа таблицы `person_entity`
--
ALTER TABLE `person_entity`
  ADD CONSTRAINT `FKkhext1jur2e90s7kpnqewhm3l` FOREIGN KEY (`department_id`) REFERENCES `department` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
