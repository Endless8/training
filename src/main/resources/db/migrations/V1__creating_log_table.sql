CREATE TABLE IF NOT EXISTS `log` (
    `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `message` varchar(255),
    `date` timestamp
);