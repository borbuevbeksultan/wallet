INSERT INTO user(email) VALUES ('test@email.com');
INSERT INTO wallet(user) VALUES (1);
INSERT INTO balance(wallet, amount, currency) VALUES (1, 0, 'USD');