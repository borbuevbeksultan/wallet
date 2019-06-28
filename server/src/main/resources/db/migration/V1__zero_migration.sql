CREATE TABLE user (
  id int AUTO_INCREMENT PRIMARY KEY,
  email varchar(255)
);

CREATE TABLE wallet (
  id int AUTO_INCREMENT PRIMARY KEY,
  user int,
  FOREIGN KEY (user) REFERENCES user(id)
);

CREATE TABLE balance (
  id int AUTO_INCREMENT PRIMARY KEY,
  wallet int,
  amount decimal(19, 9),
  currency varchar(55),
  FOREIGN KEY (wallet) REFERENCES wallet(id)
);