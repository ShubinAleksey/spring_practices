-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Хост: 127.0.0.1:3306
-- Время создания: Дек 06 2022 г., 00:30
-- Версия сервера: 5.7.39
-- Версия PHP: 7.2.34

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База данных: `ClothesShop`
--

-- --------------------------------------------------------

--
-- Структура таблицы `checks`
--

CREATE TABLE `checks` (
  `uid` bigint(20) NOT NULL,
  `itn` varchar(10) DEFAULT NULL,
  `check_number` int(11) NOT NULL,
  `date_of_seal` datetime(6) DEFAULT NULL,
  `order_uid` bigint(20) DEFAULT NULL,
  `user_uid` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `checks`
--

INSERT INTO `checks` (`uid`, `itn`, `check_number`, `date_of_seal`, `order_uid`, `user_uid`) VALUES
(3, '1234567890', 45678, '2022-12-15 00:00:00.000000', 2, 3);

-- --------------------------------------------------------

--
-- Структура таблицы `colors_product`
--

CREATE TABLE `colors_product` (
  `uid` bigint(20) NOT NULL,
  `color_name` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `colors_product`
--

INSERT INTO `colors_product` (`uid`, `color_name`) VALUES
(2, 'Красный'),
(3, 'Синий'),
(4, 'Черный'),
(5, 'Фиолетовый');

-- --------------------------------------------------------

--
-- Структура таблицы `delivers`
--

CREATE TABLE `delivers` (
  `uid` bigint(20) NOT NULL,
  `amount` int(11) NOT NULL,
  `date_delivery` datetime(6) DEFAULT NULL,
  `price_delivery` int(11) NOT NULL,
  `products_uid` bigint(20) DEFAULT NULL,
  `suppliers_uid` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `delivers`
--

INSERT INTO `delivers` (`uid`, `amount`, `date_delivery`, `price_delivery`, `products_uid`, `suppliers_uid`) VALUES
(3, 5, '2022-11-10 00:00:00.000000', 3456, 3, 3);

-- --------------------------------------------------------

--
-- Структура таблицы `inventory_controls`
--

CREATE TABLE `inventory_controls` (
  `uid` bigint(20) NOT NULL,
  `cost` double NOT NULL,
  `date` datetime(6) DEFAULT NULL,
  `document_name` varchar(20) DEFAULT NULL,
  `invoice_number` int(11) DEFAULT NULL,
  `warehouse` varchar(20) DEFAULT NULL,
  `deliveries_uid` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `inventory_controls`
--

INSERT INTO `inventory_controls` (`uid`, `cost`, `date`, `document_name`, `invoice_number`, `warehouse`, `deliveries_uid`) VALUES
(1, 6543, '2022-11-10 00:00:00.000000', 'Отчет \"Контроль\"', 3434324, 'Склад \"Еголенко\"', 3);

-- --------------------------------------------------------

--
-- Структура таблицы `orders`
--

CREATE TABLE `orders` (
  `uid` bigint(20) NOT NULL,
  `amount` int(11) NOT NULL,
  `order_name` varchar(50) DEFAULT NULL,
  `purchase_date` datetime(6) DEFAULT NULL,
  `product_uid` bigint(20) DEFAULT NULL,
  `user_uid` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `orders`
--

INSERT INTO `orders` (`uid`, `amount`, `order_name`, `purchase_date`, `product_uid`, `user_uid`) VALUES
(2, 4, 'Покупка маек', '2022-12-14 00:00:00.000000', 3, 3);

-- --------------------------------------------------------

--
-- Структура таблицы `products`
--

CREATE TABLE `products` (
  `uid` bigint(20) NOT NULL,
  `product_name` varchar(50) DEFAULT NULL,
  `product_price` int(11) NOT NULL,
  `color_product_uid` bigint(20) DEFAULT NULL,
  `size_product_uid` bigint(20) DEFAULT NULL,
  `type_product_uid` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `products`
--

INSERT INTO `products` (`uid`, `product_name`, `product_price`, `color_product_uid`, `size_product_uid`, `type_product_uid`) VALUES
(3, 'Майка \"Улет\"', 6789, 2, 2, 3);

-- --------------------------------------------------------

--
-- Структура таблицы `sizes_product`
--

CREATE TABLE `sizes_product` (
  `uid` bigint(20) NOT NULL,
  `size_name` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `sizes_product`
--

INSERT INTO `sizes_product` (`uid`, `size_name`) VALUES
(2, 'Средний'),
(3, 'Большой'),
(5, 'Малый');

-- --------------------------------------------------------

--
-- Структура таблицы `subdivisions`
--

CREATE TABLE `subdivisions` (
  `uid` bigint(20) NOT NULL,
  `subdivision_name` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `subdivisions`
--

INSERT INTO `subdivisions` (`uid`, `subdivision_name`) VALUES
(1, 'Раздел \"Контроля товаров\"'),
(2, 'Раздел \"Продажи атрибутики\"'),
(3, 'Раздел \"Составления отчетов\"');

-- --------------------------------------------------------

--
-- Структура таблицы `subdivision_reporting`
--

CREATE TABLE `subdivision_reporting` (
  `uid` bigint(20) NOT NULL,
  `commentary` varchar(100) DEFAULT NULL,
  `document_name` varchar(40) DEFAULT NULL,
  `document_type` varchar(30) DEFAULT NULL,
  `subdivision_uid` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `subdivision_reporting`
--

INSERT INTO `subdivision_reporting` (`uid`, `commentary`, `document_name`, `document_type`, `subdivision_uid`) VALUES
(1, 'Хороший', 'Отчет \"Контроля товаров\"', 'Месяц', 1);

-- --------------------------------------------------------

--
-- Структура таблицы `suppliers`
--

CREATE TABLE `suppliers` (
  `uid` bigint(20) NOT NULL,
  `itn` varchar(10) DEFAULT NULL,
  `psrn` varchar(13) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `legal_address` varchar(100) DEFAULT NULL,
  `suppliers_name` varchar(50) DEFAULT NULL,
  `telephone` varchar(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `suppliers`
--

INSERT INTO `suppliers` (`uid`, `itn`, `psrn`, `email`, `legal_address`, `suppliers_name`, `telephone`) VALUES
(3, '1234567890', '1234567890123', 'expresssupp2@mail.ru', 'ул. Доминико д. 75', 'Поставщик \"Экспресс\"', '88906784523');

-- --------------------------------------------------------

--
-- Структура таблицы `types_product`
--

CREATE TABLE `types_product` (
  `uid` bigint(20) NOT NULL,
  `type_name` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `types_product`
--

INSERT INTO `types_product` (`uid`, `type_name`) VALUES
(3, 'Шорты'),
(5, 'Кроссовки'),
(6, 'Майки');

-- --------------------------------------------------------

--
-- Структура таблицы `users`
--

CREATE TABLE `users` (
  `uid` bigint(20) NOT NULL,
  `active` bit(1) NOT NULL,
  `date_of_birthday` date DEFAULT NULL,
  `middle_name` varchar(50) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `surname` varchar(50) DEFAULT NULL,
  `telephone` varchar(11) DEFAULT NULL,
  `username` varchar(50) DEFAULT NULL,
  `subdivision_uid` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `users`
--

INSERT INTO `users` (`uid`, `active`, `date_of_birthday`, `middle_name`, `name`, `password`, `surname`, `telephone`, `username`, `subdivision_uid`) VALUES
(3, b'1', '2022-11-11', 'Иванович', 'Иван', '$2a$08$rybF.S99lk2uvnumwpVdhetxN1VDiWZ1KFqMUaOBFOIInEgO/askG', 'Иванов', '88905673412', 'IVAN', NULL),
(4, b'1', '2022-11-17', 'Иванович2', 'Иван2', '$2a$08$DmRZdbSBtCCgIbsMO0xmO.XOzZmNWBja51.2lW/JZHatIbwl8k7Ly', 'Иванов2', '88908765432', 'IVAN2', NULL);

-- --------------------------------------------------------

--
-- Структура таблицы `user_role`
--

CREATE TABLE `user_role` (
  `user_id` bigint(20) NOT NULL,
  `roles` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `user_role`
--

INSERT INTO `user_role` (`user_id`, `roles`) VALUES
(3, 'USER'),
(3, 'ADMIN'),
(3, 'CASHIER'),
(3, 'PURCHASER'),
(3, 'MERCHANDISER'),
(4, 'USER');

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `checks`
--
ALTER TABLE `checks`
  ADD PRIMARY KEY (`uid`),
  ADD KEY `FKb890uttrlxym3g4x3y1pg260b` (`order_uid`),
  ADD KEY `FKmxtsymfjy4njdsk15cvwrgff1` (`user_uid`);

--
-- Индексы таблицы `colors_product`
--
ALTER TABLE `colors_product`
  ADD PRIMARY KEY (`uid`);

--
-- Индексы таблицы `delivers`
--
ALTER TABLE `delivers`
  ADD PRIMARY KEY (`uid`),
  ADD KEY `FKsjadxiwxy84hs3cbqgi9j3u0b` (`products_uid`),
  ADD KEY `FKkce8a9vpu9kn0y58e899hh6e4` (`suppliers_uid`);

--
-- Индексы таблицы `inventory_controls`
--
ALTER TABLE `inventory_controls`
  ADD PRIMARY KEY (`uid`),
  ADD KEY `FKfqfvxm2htv3vtp9dyv7de3e4w` (`deliveries_uid`);

--
-- Индексы таблицы `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`uid`),
  ADD KEY `FKrjkrngqgw1upsc0tu5cnx4fin` (`product_uid`),
  ADD KEY `FKon51wujskgdxa141vq4oo7565` (`user_uid`);

--
-- Индексы таблицы `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`uid`),
  ADD KEY `FKtoyyv8p6r39vwe52ixhul90o8` (`color_product_uid`),
  ADD KEY `FKdmsl7q8wev26ynluqxmkcq11q` (`size_product_uid`),
  ADD KEY `FKdfykvg3smbi0noyphkxjaoy8u` (`type_product_uid`);

--
-- Индексы таблицы `sizes_product`
--
ALTER TABLE `sizes_product`
  ADD PRIMARY KEY (`uid`);

--
-- Индексы таблицы `subdivisions`
--
ALTER TABLE `subdivisions`
  ADD PRIMARY KEY (`uid`);

--
-- Индексы таблицы `subdivision_reporting`
--
ALTER TABLE `subdivision_reporting`
  ADD PRIMARY KEY (`uid`),
  ADD KEY `FKr4tln4pc40vmkhinjd3qnt7j3` (`subdivision_uid`);

--
-- Индексы таблицы `suppliers`
--
ALTER TABLE `suppliers`
  ADD PRIMARY KEY (`uid`);

--
-- Индексы таблицы `types_product`
--
ALTER TABLE `types_product`
  ADD PRIMARY KEY (`uid`);

--
-- Индексы таблицы `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`uid`),
  ADD KEY `FKnpy5flumhuq03jlj0ljdbyuis` (`subdivision_uid`);

--
-- Индексы таблицы `user_role`
--
ALTER TABLE `user_role`
  ADD KEY `FKj345gk1bovqvfame88rcx7yyx` (`user_id`);

--
-- AUTO_INCREMENT для сохранённых таблиц
--

--
-- AUTO_INCREMENT для таблицы `checks`
--
ALTER TABLE `checks`
  MODIFY `uid` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT для таблицы `colors_product`
--
ALTER TABLE `colors_product`
  MODIFY `uid` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT для таблицы `delivers`
--
ALTER TABLE `delivers`
  MODIFY `uid` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT для таблицы `inventory_controls`
--
ALTER TABLE `inventory_controls`
  MODIFY `uid` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT для таблицы `orders`
--
ALTER TABLE `orders`
  MODIFY `uid` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT для таблицы `products`
--
ALTER TABLE `products`
  MODIFY `uid` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT для таблицы `sizes_product`
--
ALTER TABLE `sizes_product`
  MODIFY `uid` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT для таблицы `subdivisions`
--
ALTER TABLE `subdivisions`
  MODIFY `uid` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT для таблицы `subdivision_reporting`
--
ALTER TABLE `subdivision_reporting`
  MODIFY `uid` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT для таблицы `suppliers`
--
ALTER TABLE `suppliers`
  MODIFY `uid` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT для таблицы `types_product`
--
ALTER TABLE `types_product`
  MODIFY `uid` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT для таблицы `users`
--
ALTER TABLE `users`
  MODIFY `uid` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Ограничения внешнего ключа сохраненных таблиц
--

--
-- Ограничения внешнего ключа таблицы `checks`
--
ALTER TABLE `checks`
  ADD CONSTRAINT `FKb890uttrlxym3g4x3y1pg260b` FOREIGN KEY (`order_uid`) REFERENCES `orders` (`uid`),
  ADD CONSTRAINT `FKmxtsymfjy4njdsk15cvwrgff1` FOREIGN KEY (`user_uid`) REFERENCES `users` (`uid`);

--
-- Ограничения внешнего ключа таблицы `delivers`
--
ALTER TABLE `delivers`
  ADD CONSTRAINT `FKkce8a9vpu9kn0y58e899hh6e4` FOREIGN KEY (`suppliers_uid`) REFERENCES `suppliers` (`uid`),
  ADD CONSTRAINT `FKsjadxiwxy84hs3cbqgi9j3u0b` FOREIGN KEY (`products_uid`) REFERENCES `products` (`uid`);

--
-- Ограничения внешнего ключа таблицы `inventory_controls`
--
ALTER TABLE `inventory_controls`
  ADD CONSTRAINT `FKfqfvxm2htv3vtp9dyv7de3e4w` FOREIGN KEY (`deliveries_uid`) REFERENCES `delivers` (`uid`);

--
-- Ограничения внешнего ключа таблицы `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `FKon51wujskgdxa141vq4oo7565` FOREIGN KEY (`user_uid`) REFERENCES `users` (`uid`),
  ADD CONSTRAINT `FKrjkrngqgw1upsc0tu5cnx4fin` FOREIGN KEY (`product_uid`) REFERENCES `products` (`uid`);

--
-- Ограничения внешнего ключа таблицы `products`
--
ALTER TABLE `products`
  ADD CONSTRAINT `FKdfykvg3smbi0noyphkxjaoy8u` FOREIGN KEY (`type_product_uid`) REFERENCES `types_product` (`uid`),
  ADD CONSTRAINT `FKdmsl7q8wev26ynluqxmkcq11q` FOREIGN KEY (`size_product_uid`) REFERENCES `sizes_product` (`uid`),
  ADD CONSTRAINT `FKtoyyv8p6r39vwe52ixhul90o8` FOREIGN KEY (`color_product_uid`) REFERENCES `colors_product` (`uid`);

--
-- Ограничения внешнего ключа таблицы `subdivision_reporting`
--
ALTER TABLE `subdivision_reporting`
  ADD CONSTRAINT `FKr4tln4pc40vmkhinjd3qnt7j3` FOREIGN KEY (`subdivision_uid`) REFERENCES `subdivisions` (`uid`);

--
-- Ограничения внешнего ключа таблицы `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `FKnpy5flumhuq03jlj0ljdbyuis` FOREIGN KEY (`subdivision_uid`) REFERENCES `subdivisions` (`uid`);

--
-- Ограничения внешнего ключа таблицы `user_role`
--
ALTER TABLE `user_role`
  ADD CONSTRAINT `FKj345gk1bovqvfame88rcx7yyx` FOREIGN KEY (`user_id`) REFERENCES `users` (`uid`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
