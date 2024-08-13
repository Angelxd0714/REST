-- Insertar categorías
INSERT INTO category (name, description)VALUES('Electronics', 'Devices and gadgets');

INSERT INTO marker (name)VALUES('BrandA');

-- Insertar productos
INSERT INTO product (nombre, precio,descripcion, imagen, category_id, marker_id)VALUES('Smartphone', 299.99, 'Latest model smartphone with high resolution camera.', 'https://via.placeholder.com/150', 1, 1);
