ALTER TABLE user_devices ADD uid varchar(255) NOT NULL;

INSERT INTO user_devices (userId, password, uid) VALUES ('test1@gmail.com', 'pass', '1');
INSERT INTO user_devices (userId, password, uid) VALUES ('test2@gmail.com', 'pass', '2');
INSERT INTO user_devices (userId, password, uid) VALUES ('test3@gmail.com', 'pass', '3');
