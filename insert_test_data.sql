-- Replace "schema" by the name of the schema in which you are inserting this data. 

/*
Name         password
John Doe    password123
Alice Wong	Alicepassword123
Bob Smith	Bobpassword123
Carol Lee	Carolpassword123
David Chan	Davidpassword123
Eve Kim	    Evepassword123
*/
-- insert users
INSERT INTO company1.users (user_id, first_name, last_name, password, role_id)
VALUES (1, 'John', 'Doe', '$2b$12$OPTKjBBAdHzZ2PvbR4EAy.LS8KyNJLSu32RrzkCb8SH0BT/3SrOVq', 1);

INSERT INTO company1.users (user_id, first_name, last_name, password, role_id)
VALUES (2, 'Alice', 'Wong', '$2b$12$LVtMWa.tmtwctwA4EnuXx./yOxYR4BC9tkvYD4TyKtBqyHoyPy/SO', 1);

INSERT INTO company1.users (user_id, first_name, last_name, password, role_id)
VALUES (3, 'Carol', 'Lee', '$2b$12$TCFar8VA765m.Xmv1cvWDeOR1roNI277v.MCxsM6SGhFOULqre1r.', 2);

INSERT INTO company1.users (user_id, first_name, last_name, password, role_id)
VALUES (4, 'Eve', 'Kim', '$2b$12$sXElKWcEZ.1F.Tr71v6FuO7dbKD2KA2gpeMPuPvsR6NILT.CMsEaK', 2);

--insert location
INSERT INTO company1.location (location_id, location_name) VALUES (1, 'Main Warehouse');
INSERT INTO company1.location (location_id, location_name) VALUES (2, 'Secondary Storage');
INSERT INTO company1.location (location_id, location_name) VALUES (3, 'Cold Room');

--insert supplier
INSERT INTO company1.supplier (supplier_id, supplier_name, address, phone_no) 
VALUES 
(1, 'SupplierA', '123 Street', 123456789),
(2, 'SupplierB', '456 Avenue', 987654321);

--insert product
INSERT INTO company1.product (product_id, product_name, can_expire, total_quantity, threshold_min) 
VALUES 
(1, 'Milk', TRUE, 100, 20),
(2, 'Rice', FALSE, 500, 100);

--insert product_supplier
INSERT INTO company1.product_supplier (product_id, supplier_id) VALUES (1, 1);
INSERT INTO company1.product_supplier (product_id, supplier_id) VALUES (1, 2);
INSERT INTO company1.product_supplier (product_id, supplier_id) VALUES (2, 2);

--insert locator
INSERT INTO company1.locator (locator_id, storage1, storage2, location_id) 
VALUES 
(1, 'Shelf A', 'Row 1', 1),
(2, 'Shelf B', 'Row 2', 2),
(3, 'Freezer 1', 'Cold', 3);

--insert item
INSERT INTO company1.item (item_id, item_quantity, received_date, expiration_date, locator_id, supplier_id, product_id)
VALUES 
('ITEM001', 41, '2024-03-07', null, 1, 1, 2),
('ITEM002', 23, '2024-03-25', '2024-05-28', 1, 2, 1),
('ITEM003', 83, '2024-03-12', '2024-06-25', 1, 1, 1);


--insert consumeditem
INSERT INTO company1.consumeditem (consumed_item_id, received_date, expiration_date, locator_id, supplier_id, product_id)
VALUES 
('CITEM001', '2024-03-20', '2024-05-21', 2, 2, 2),
('CITEM002', '2024-03-17', '2024-06-03', 2, 2, 2);


--insert transaction
INSERT INTO company1.transaction (
    trans_type, trans_quantity, item_sn, trans_date, comments, supplier_id, product_id, locator_id
)
VALUES (
    'IN', 20, 'ITEM001', NOW(), 'Initial stock', 1, 1, 1
);


--insert alert
INSERT INTO company1.alert (item_sn, has_expired, low_stock, alert_date)
VALUES ('ITEM001', FALSE, TRUE, NOW());


--insert users_location
INSERT INTO company1.users_location (user_id, location_id) 
VALUES 
(1, 1),
(1, 2),
(2, 2);
