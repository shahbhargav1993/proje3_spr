DROP TABLE SELLER IF EXISTS;
CREATE TABLE SELLER (
    seller_id varchar(255) primary key,
    seller_name varchar(255) not null
);

DROP TABLE PRODUCTS IF EXISTS;
CREATE TABLE PRODUCTS (
    product_id varchar(255) primary key,
    product_name varchar(255) not null,
    price  NUMERIC(25, 2) not null,
    seller_name varchar(255) not null,
    s_seller_id varchar(255) references SELLER(seller_id)
);
