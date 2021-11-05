CREATE TABLE IF NOT EXISTS gift_certificates (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `description` VARCHAR(100) NOT NULL,
  `price` DECIMAL(6,2) NOT NULL,
  `duration` INT NOT NULL,
  `create_date` TIMESTAMP NOT NULL,
  `last_update_date` TIMESTAMP NOT NULL,
  PRIMARY KEY (`id`));

CREATE TABLE IF NOT EXISTS tags (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`));

CREATE TABLE IF NOT EXISTS gift_certificates_tags (
  `gift_certificate_id` BIGINT NOT NULL,
  `tag_id` BIGINT NOT NULL,
  CONSTRAINT `fk_gift_certificate`
    FOREIGN KEY (`gift_certificate_id`)
    REFERENCES `gift_certificates` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_tag`
    FOREIGN KEY (`tag_id`)
    REFERENCES `tags` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

CREATE TABLE IF NOT EXISTS users (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`));

CREATE TABLE IF NOT EXISTS orders (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `date` TIMESTAMP NOT NULL,
  `cost` DECIMAL(6,2) NOT NULL,
  `user_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_orders_users1`
    FOREIGN KEY (`user_id`)
    REFERENCES `users` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

CREATE TABLE IF NOT EXISTS gift_certificates_orders (
  `gift_certificate_id` BIGINT NOT NULL,
  `order_id` BIGINT NOT NULL,
  `quantity` INT NOT NULL,
  CONSTRAINT `fk_gift_certificates_has_orders_gift_certificates1`
    FOREIGN KEY (`gift_certificate_id`)
    REFERENCES `gift_certificates` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_gift_certificates_has_orders_orders1`
    FOREIGN KEY (`order_id`)
    REFERENCES `orders` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

