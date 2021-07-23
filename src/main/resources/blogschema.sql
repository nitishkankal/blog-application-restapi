DROP TABLE IF EXISTS `photos`;
DROP TABLE IF EXISTS `posts`;
DROP TABLE IF EXISTS `users`;


CREATE TABLE `users`
(
    `id`         INT AUTO_INCREMENT PRIMARY KEY,
    `first_name` varchar(255),
    `last_name`  varchar(255),
    `username`   varchar(255) NOT NULL,
    `password`   varchar(255) NOT NULL,
    `email`      varchar(255) NOT NULL,
    `phone`      varchar(255)

) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8;

CREATE TABLE `posts`
(
    `id`         INT AUTO_INCREMENT PRIMARY KEY,
    `title`      varchar(255) NOT NULL,
    `body`       text         NOT NULL,
    `user_id`    INT                   DEFAULT NULL,
    `created_at` timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `updated_at` timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    KEY `user_post_id` (`user_id`),
    CONSTRAINT `user_post_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)

) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8;



CREATE TABLE `photos`
(
    `id`             INT AUTO_INCREMENT PRIMARY KEY,
    `title`          varchar(255) NOT NULL,
    `url`            varchar(255) NOT NULL,
    `post_photos_id` INT DEFAULT NULL,

    KEY `post_photos_id` (`post_photos_id`),
    CONSTRAINT `post_photos_id` FOREIGN KEY (`post_photos_id`) REFERENCES `posts` (`id`)

) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8;

