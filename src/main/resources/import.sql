-- Insertar categorías
INSERT INTO category (name, description)VALUES('Electronics', 'Devices and gadgets');
INSERT INTO category (name, description)VALUES('Electronics', 'Devices and gadgets');
INSERT INTO category (name, description)VALUES('Clothing', 'Apparel and accessories');
INSERT INTO category (name, description)VALUES('Books', 'Various genres of books');

-- Insertar marcadores
INSERT INTO marker (name)VALUES('BrandA');
INSERT INTO marker (name)VALUES('BrandB');
INSERT INTO marker (name)VALUES('BrandC');
-- Insertar productos
INSERT INTO product (nombre, precio,descripcion, imagen, category_id, marker_id)VALUES('Smartphone', 299.99, 'Latest model smartphone with high resolution camera.', 'https://via.placeholder.com/150', 1, 1);
INSERT INTO product (nombre, precio, descripcion, imagen, category_id, marker_id)VALUES('Laptop', 799.99, 'High performance laptop with 16GB RAM and 512GB SSD.', 'https://via.placeholder.com/150', 1, 2);
INSERT INTO product (nombre, precio, descripcion, imagen, category_id, marker_id)VALUES('T-shirt', 19.99, 'Comfortable cotton t-shirt available in various colors.', 'https://via.placeholder.com/150', 2, 3);
INSERT INTO product (nombre, precio, descripcion, imagen, category_id, marker_id)VALUES('Novel', 9.99, 'A captivating novel from a popular author.', 'https://via.placeholder.com/150', 3, 1);