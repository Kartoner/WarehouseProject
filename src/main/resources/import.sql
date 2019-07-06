DELETE FROM ROLE;

INSERT INTO ROLE (NAME) values ('Admin');

INSERT INTO ROLE (NAME) values ('Customer');

INSERT INTO ROLE (NAME) values ('Employee');

DELETE FROM USER;

INSERT INTO USER (USERNAME, PASSWORD, USER_ROLE_ID, FIRST_NAME, LAST_NAME, ADDRESS, EMAIL, PHONE_NUMBER) VALUES ('admin', '179AD45C6CE2CB97CF1029E212046E81', 1, 'Admin', 'Admin', 'Test Address 1/3', 'admin@example.com', '000111222');

INSERT INTO USER (USERNAME, PASSWORD, USER_ROLE_ID, FIRST_NAME, LAST_NAME, ADDRESS, EMAIL, PHONE_NUMBER) VALUES ('testEmployee', '179AD45C6CE2CB97CF1029E212046E81', 2, 'Jan', 'Kowalski', 'Test Address 1/1', 'test1@example.com', '111222333');

INSERT INTO USER (USERNAME, PASSWORD, USER_ROLE_ID, FIRST_NAME, LAST_NAME, ADDRESS, EMAIL, PHONE_NUMBER) VALUES ('testCustomer', '841C4221C2E7E0CEFBC0392A35222512', 3, 'Piotr', 'Nowak', 'Test Address 1/2', 'test2@example.com', '444555666');

DELETE FROM WAREHOUSE_ITEM;

INSERT INTO WAREHOUSE_ITEM (ITEM_NAME, ITEM_TYPE, ITEM_DESCRIPTION, QUANTITY, PRICE) VALUES ('Bottled Water', 'Food', 'Bottled Water', 20, 1.50);

INSERT INTO WAREHOUSE_ITEM (ITEM_NAME, ITEM_TYPE, ITEM_DESCRIPTION, QUANTITY, PRICE) VALUES ('Chicken', 'Food', 'Chicken', 55, 3.75);

INSERT INTO WAREHOUSE_ITEM (ITEM_NAME, ITEM_TYPE, ITEM_DESCRIPTION, QUANTITY, PRICE) VALUES ('Leather Jacket', 'Clothes', 'Leather Jacket', 15, 25.0);

INSERT INTO WAREHOUSE_ITEM (ITEM_NAME, ITEM_TYPE, ITEM_DESCRIPTION, QUANTITY, PRICE) VALUES ('TV', 'Electronics', 'TV', 5, 250.0);

INSERT INTO WAREHOUSE_ITEM (ITEM_NAME, ITEM_TYPE, ITEM_DESCRIPTION, QUANTITY, PRICE) VALUES ('Plastic Chair', 'Furniture', 'Plastic Chair', 40, 12.0);

DELETE FROM DELIVERY;

INSERT INTO DELIVERY (EMPLOYEE_ACCEPTING, CUSTOMER_ORDERING, DELIVERY_ADDRESS, DELIVERY_STATUS, OVERALL_PRICE, IS_PAID) VALUES ('1|Jan Kowalski|Employee', '2|Piotr Nowak|Customer', 'Test Address 1/2', 'Completed', 65.0, TRUE);

DELETE FROM DELIVERY_ITEM;

INSERT INTO DELIVERY_ITEM (DELIVERY_ID, ITEM_DATA_ID, ITEM_NAME, ITEM_TYPE, PRICE, QUANTITY) VALUES (1, 1, 'Bottled Water', 'Food', 1.50, 10);

INSERT INTO DELIVERY_ITEM (DELIVERY_ID, ITEM_DATA_ID, ITEM_NAME, ITEM_TYPE, PRICE, QUANTITY) VALUES (1, 3, 'Leather Jacket', 'Clothes', 25.0, 2);