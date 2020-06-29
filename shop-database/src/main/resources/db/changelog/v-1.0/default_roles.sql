INSERT INTO `users` (`name`, `password`)
VALUE ('admin', '$2y$12$zubJQkOA2v7sgmHtc0DZ9eskF6XW1.3eQ41n5xY9YVcTwcYgZuB4C');

GO

INSERT INTO `roles` ('name') VALUE
('ROLE_ADMIN'), ('ROLE_GUEST');

