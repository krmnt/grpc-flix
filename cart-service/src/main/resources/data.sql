DROP TABLE IF EXISTS cart;
CREATE TABLE cart AS SELECT * FROM CSVREAD('classpath:cart.csv');