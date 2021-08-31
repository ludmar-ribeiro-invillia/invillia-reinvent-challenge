create database challenge;
use challenge;

CREATE TABLE User
(
User_Id int AUTO_INCREMENT PRIMARY KEY,
Name VARCHAR(50) NOT NULL
);

CREATE TABLE Shopping_cart
(
Shopping_Cart_Id int AUTO_INCREMENT PRIMARY KEY,
User_Id int NOT NULL,
FOREIGN KEY (User_Id) references User (User_Id)
);

CREATE TABLE Product
(
SKU int AUTO_INCREMENT PRIMARY KEY ,
Name VARCHAR(50) NOT NULL,
Price DOUBLE NOT NULL
);

CREATE TABLE Shopping_cart_Item
(
Shopping_cart_item_id int AUTO_INCREMENT PRIMARY KEY,
SKU int NOT NULL,
Shopping_Cart_Id int NOT NULL,
Quantity INT NOT NULL,
FOREIGN KEY (SKU) references Product (SKU),
FOREIGN KEY (Shopping_Cart_Id) references Shopping_cart (Shopping_Cart_Id)
);

