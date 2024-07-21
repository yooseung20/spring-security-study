CREATE TABLE IF NOT EXISTS `sia_security`.`users` (
                                                `id` INT NOT NULL AUTO_INCREMENT,
                                                `username` VARCHAR(100) NOT NULL,
    `password` VARCHAR(100) NOT NULL,
    `enabled` INT NOT NULL,
    PRIMARY KEY (`id`)
    );

CREATE TABLE IF NOT EXISTS `sia_security`.`authorities` (
                                                      `id` INT NOT NULL AUTO_INCREMENT,
                                                      `username` VARCHAR(100) NOT NULL,
    `authority` VARCHAR(100) NOT NULL,
    PRIMARY KEY (`id`)
    );