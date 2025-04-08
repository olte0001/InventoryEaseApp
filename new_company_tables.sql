SET search_path TO companyX;

CREATE TABLE roles (
    role_id SERIAL PRIMARY KEY,
    role_name VARCHAR(45)
);

INSERT INTO companyX.roles (role_name)
VALUES (HANDLER);

INSERT INTO companyX.roles (role_name)
VALUES (MANAGER);

CREATE TABLE users (
    user_id SERIAL PRIMARY KEY,
    first_name VARCHAR(45),
    last_name VARCHAR(45),
    password VARCHAR(255),
    role_id INT REFERENCES ROLES(role_id)
);

CREATE TABLE location (
    location_id SERIAL PRIMARY KEY,
    location_name VARCHAR(100)
);

CREATE TABLE users_location (
    user_id INT REFERENCES USERS(user_id),
    location_id INT REFERENCES LOCATION(location_id),
    PRIMARY KEY (user_id, location_id)
);

CREATE TABLE locator (
    locator_id SERIAL PRIMARY KEY,
    storage1 VARCHAR(100),
    storage2 VARCHAR(100),
    location_id INT REFERENCES LOCATION(location_id)
);

CREATE TABLE supplier (
    supplier_id SERIAL PRIMARY KEY,
    supplier_name VARCHAR(100),
    address VARCHAR(255),
    phone_no INT
);

CREATE TABLE product (
    product_id SERIAL PRIMARY KEY,
    product_name VARCHAR(100),
    can_expire BOOLEAN,
    total_quantity INT,
    threshold_min INT
);

CREATE TABLE product_supplier (
    product_id INT REFERENCES PRODUCT(product_id),
    supplier_id INT REFERENCES SUPPLIER(supplier_id),
    PRIMARY KEY (product_id, supplier_id)
);

CREATE TABLE item (
    item_id VARCHAR(100) PRIMARY KEY,
    item_quantity INT,
    received_date TIMESTAMP,
    expiration_date TIMESTAMP,
    locator_id INT REFERENCES LOCATOR(locator_id),
    supplier_id INT REFERENCES SUPPLIER(supplier_id),
    product_id INT REFERENCES PRODUCT(product_id)
);

CREATE TABLE consumedItem (
    consumed_item_id VARCHAR(100) PRIMARY KEY,
    received_date TIMESTAMP,
    expiration_date TIMESTAMP,
    locator_id INT REFERENCES LOCATOR(locator_id),
    supplier_id INT REFERENCES SUPPLIER(supplier_id),
    product_id INT REFERENCES PRODUCT(product_id)
);

CREATE TABLE transaction (
    trans_id SERIAL PRIMARY KEY,
    trans_type VARCHAR(45),
    trans_quantity INT,
    item_sn VARCHAR(100),
    trans_date TIMESTAMP,
    comments VARCHAR(255),
    supplier_id INT REFERENCES SUPPLIER(supplier_id),
    product_id INT REFERENCES PRODUCT(product_id),
    locator_id INT REFERENCES LOCATOR(locator_id)
);

CREATE TABLE alert (
    alert_id SERIAL PRIMARY KEY,
    item_sn VARCHAR(100),
    has_expired BOOLEAN,
    low_stock BOOLEAN,
    alert_date TIMESTAMP
);
