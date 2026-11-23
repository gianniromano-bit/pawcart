INSERT INTO brand (name, country, website) VALUES
 ('Royal Canin','France','https://www.royalcanin.com'),
 ('Kong','USA','https://www.kongcompany.com'),
 ('Hartz','USA','https://www.hartz.com'),
 ('Orijen','Canada','https://www.orijen.ca'),
 ('Kaytee','USA','https://www.kaytee.com');

INSERT INTO category (name) VALUES ('Food'),('Toys'),('Grooming'),('Health'),('Accessories');

INSERT INTO distribution_centre (name, address, city, province) VALUES
 ('GTA Hub','100 Warehouse Rd','Mississauga','ON'),
 ('East Hub','25 Depot St','Ottawa','ON'),
 ('West Hub','880 Logistics Way','Calgary','AB');

INSERT INTO item (name, description, sku, price, pet_type, image_url, brand_id, category_id, created_at) VALUES
 ('Dry Dog Food 10kg','Adult dry formula for medium breeds','RC-DOG-10',59.99,'DOG','',1,1,CURRENT_TIMESTAMP),
 ('Classic Chew Toy','Durable red rubber toy','KG-DOG-01',14.99,'DOG','',2,2,CURRENT_TIMESTAMP),
 ('Cat Flea Treatment','Monthly spot-on treatment','HZ-CAT-05',24.99,'CAT','',3,4,CURRENT_TIMESTAMP),
 ('Grain-Free Cat Food 5kg','Chicken and fish recipe','OR-CAT-05',44.99,'CAT','',4,1,CURRENT_TIMESTAMP),
 ('Parrot Seed Mix 2kg','Nutritious seed blend','KT-BRD-02',17.49,'BIRD','',5,1,CURRENT_TIMESTAMP),
 ('Hamster Exercise Wheel','Silent spinner wheel','KT-SML-01',12.99,'SMALL_ANIMAL','',5,2,CURRENT_TIMESTAMP),
 ('Slicker Grooming Brush','Gentle de-tangling brush','HZ-DOG-07',11.99,'DOG','',3,3,CURRENT_TIMESTAMP),
 ('Adjustable Nylon Collar','Reflective collar, medium','KG-DOG-09',9.99,'DOG','',2,5,CURRENT_TIMESTAMP);

INSERT INTO inventory (quantity, reorder_level, last_updated, item_id, centre_id) VALUES
 (42,10,CURRENT_TIMESTAMP,1,1),(18,10,CURRENT_TIMESTAMP,1,2),
 (8,10,CURRENT_TIMESTAMP,2,1),(30,5,CURRENT_TIMESTAMP,3,2),
 (25,10,CURRENT_TIMESTAMP,4,1),(5,8,CURRENT_TIMESTAMP,5,3),
 (60,15,CURRENT_TIMESTAMP,6,1),(33,10,CURRENT_TIMESTAMP,7,2),
 (70,20,CURRENT_TIMESTAMP,8,3);
