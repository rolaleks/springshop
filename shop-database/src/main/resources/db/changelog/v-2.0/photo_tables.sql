CREATE TABLE `photos_raw` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content_type` varchar(64) NOT NULL,
  `name` varchar(255) NOT NULL,
  `data` longblob NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB;

GO

CREATE TABLE `product_raw_photos` (
  `product_id` bigint(20) NOT NULL,
  `photo_id` bigint(20) NOT NULL,
  KEY `FKh3amnci4cl7xcl1al140xw79e` (`product_id`),
  KEY `FKloucf8ggy74nmdej2jmvttvi4` (`photo_id`),
  CONSTRAINT `FKh3amnci4cl7xcl1al140xw79e` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`),
  CONSTRAINT `FKloucf8ggy74nmdej2jmvttvi4` FOREIGN KEY (`photo_id`) REFERENCES `photos_raw` (`id`)
) ENGINE=InnoDB

GO

CREATE TABLE `photos_path` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `path` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB;

GO

CREATE TABLE `product_path_photos` (
  `product_id` bigint(20) NOT NULL,
  `photo_id` bigint(20) NOT NULL,
  KEY `FKh3amnci4cl1xcl1al140xw79e` (`product_id`),
  KEY `FKloucf8ggy71nmdej2jmvttvi4` (`photo_id`),
  CONSTRAINT `FKh3amnci4cl1xcl1al140xw79e` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`),
  CONSTRAINT `FKloucf8ggy71nmdej2jmvttvi4` FOREIGN KEY (`photo_id`) REFERENCES `photos_path` (`id`)
) ENGINE=InnoDB

