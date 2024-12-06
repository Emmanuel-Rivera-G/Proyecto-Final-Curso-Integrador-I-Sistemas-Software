-- Crear la tabla tipo_usuario
CREATE TABLE tipo_usuario (
    id_tipo_usuario SERIAL PRIMARY KEY,
    nombre_tipo_usuario VARCHAR(20) NOT NULL UNIQUE
);

-- Insertar tipos de usuario
INSERT INTO tipo_usuario (nombre_tipo_usuario) VALUES 
('Administrador'),
('Empleado');

-- Crear la tabla usuario
CREATE TABLE usuario (
    id_usuario SERIAL PRIMARY KEY,
    nombre_usuario VARCHAR(50) NOT NULL,
    apellido_usuario VARCHAR(50) NOT NULL,
    documento_usuario VARCHAR(12) NOT NULL,
    direccion_usuario VARCHAR(100) NOT NULL,
    telefono_usuario VARCHAR(15) NOT NULL,
    correo_usuario VARCHAR(50) NOT NULL UNIQUE,
    id_tipo_usuario INT NOT NULL REFERENCES tipo_usuario(id_tipo_usuario) ON DELETE RESTRICT,
    username VARCHAR(30) NOT NULL UNIQUE,
    password_usuario VARCHAR(32) NOT NULL
);

-- Insertar datos en la tabla usuario
INSERT INTO usuario (id_usuario, nombre_usuario, apellido_usuario, documento_usuario, direccion_usuario, telefono_usuario, correo_usuario, id_tipo_usuario, username, password_usuario) VALUES
(1, 'Juan', 'Pérez', '12345678', 'Av. Siempre Viva 123', '5551234', 'juan.perez@example.com', 1, 'juanp', 'pass123'),(2, 'Ana', 'García', '87654321', 'Calle Falsa 456', '5554321', 'ana.garcia@example.com', 2, 'anag', 'pass456'),(3, 'Carlos', 'Sánchez', '12344321', 'Av. Del Sol 789', '5556789', 'carlos.sanchez@example.com', 2, 'carloss', 'pass789'),(4, 'Laura', 'Fernández', '11223344', 'Calle Luna 321', '5559876', 'laura.fernandez@example.com', 1, 'lauraf', 'pass987'),(5, 'Miguel', 'Torres', '99887766', 'Av. Verde 456', '5554567', 'miguel.torres@example.com', 2, 'miguelt', 'pass654'),(6, 'Sofía', 'Martínez', '66778899', 'Calle Azul 789', '5553456', 'sofia.martinez@example.com', 2, 'sofiam', 'pass432'),(7, 'Pedro', 'López', '443677', 'Av. Roja 123', '5552345', 'pedro.lopez@example.com', 1, 'pedrol', 'pass1234'),(8, 'Lucía', 'Ramírez', '99887755', 'Calle Blanca 456', '5556789', 'lucia.ramirez@example.com', 2, 'luciar', 'pass567'),(9, 'Manuel', 'Gómez', '22334455', 'Av. Central 789', '5559876', 'manuel.gomez@example.com', 2, 'manuelg', 'pass890'),(10, 'Elena', 'Morales', '33445566', 'Calle Oeste 321', '5551234', 'elena.morales@example.com', 2, 'elenam', 'pass321'),(11, 'José', 'Ortiz', '44556677', 'Av. Sur 123', '5554321', 'jose.ortiz@example.com', 1, 'joseo', 'pass654'),(12, 'María', 'Jiménez', '55667788', 'Calle Norte 456', '5558765', 'maria.jimenez@example.com', 2, 'mariaj', 'pass987'),(13, 'Diego', 'Castro', '66554433', 'Av. Este 789', '5553456', 'diego.castro@example.com', 2, 'diegoc', 'pass456'),(14, 'Raquel', 'Pérez', '77889900', 'Calle Sur 123', '5552345', 'raquel.perez@example.com', 2, 'raquelp', 'pass123'),(15, 'Hugo', 'Suárez', '11224488', 'Av. Norte 456', '5557654', 'hugo.suarez@example.com', 2, 'hugos', 'pass789'),(16, 'admin', 'root', '99999999', 'Av.', '00000000', 'root@admin.pass', 1, '', ''),(17, 'Jesamin', 'Zeballos', '4654654', 'Calle 2', '4654', 'jesamin@gmail.com', 1, 'admin1', 'admin1'),(18, 'Juan', '', '', '', '', '', 1, '', ''),(19, 'ggg', '', '', '', '', '', 1, '', '');

-- Crear la tabla categorias
CREATE TABLE categorias (
    id_categoria SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL UNIQUE,
    detalles VARCHAR(500)
);

-- Insertar datos en la tabla categorias
INSERT INTO categorias (id_categoria, nombre)
SELECT unnest(ARRAY[1, 2, 3, 4, 5, 6, 7, 8, 9]) AS id_categoria,
       unnest(ARRAY[
           'Granos y Harinas', 
           'Lácteos y Aceites', 
           'Productos de Limpieza', 
           'Detergentes', 
           'Bebidas', 
           'Panadería', 
           'Enlatados', 
           'Café', 
           'Cereales y Chocolates'
       ]) AS nombre
ON CONFLICT (id_categoria) DO NOTHING;

-- Crear la tabla productos
CREATE TABLE productos (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL UNIQUE,
    idCategoria INT NOT NULL REFERENCES categorias(id_categoria) ON DELETE RESTRICT,
    undMedida VARCHAR(50) NOT NULL,
    stock INT NOT NULL
);

CREATE TABLE productos (
    id SERIAL PRIMARY KEY, -- SERIAL equivale a AUTO_INCREMENT
    nombre VARCHAR(100) UNIQUE NOT NULL,
    id_categoria INT NOT NULL, -- idCategoria renombrado para consistencia con el estándar PostgreSQL
    und_medida VARCHAR(50) NOT NULL, -- Cambio a snake_case para las columnas
    stock INT NOT NULL,
    CONSTRAINT fk_categoria FOREIGN KEY (id_categoria) REFERENCES categorias (id_categoria) ON DELETE RESTRICT
);

-- Insertar datos en la tabla productos
INSERT INTO productos (nombre, id_categoria, und_medida, stock) VALUES
('Arroz Blanco', 1, 'kg', 120),
('Frijol Negro', 1, 'kg', 75),
('Aceite Vegetal', 2, 'litros', 60),
('Harina de Maíz', 1, 'kg', 90),
('Jabón de Barra', 3, 'unidades', 50),
('Detergente en Polvo', 4, 'kg', 40),
('Leche Entera', 5, 'litros', 30),
('Pan Integral', 6, 'unidades', 25),
('Sal de Mesa', 1, 'kg', 100),
('Azúcar Refinada', 1, 'kg', 150),
('Atún en Lata', 7, 'unidades', 80),
('Papel Higiénico', 3, 'unidades', 120),
('Fideos Spaghetti', 1, 'kg', 200),
('Café Molido', 8, 'kg', 50),
('Sardinas en Lata', 7, 'unidades', 70),
('Jugo de Naranja', 5, 'litros', 40),
('Cereal de Maíz', 9, 'unidades', 35),
('Levadura Seca', 1, 'kg', 80),
('Mantequilla', 2, 'kg', 45),
('Galletas Saladas', 6, 'unidades', 60),
('Queso Mozzarella', 2, 'kg', 40),
('Yogurt Natural', 5, 'litros', 30),
('Café Instantáneo', 8, 'unidades', 60),
('Chocolate en Polvo', 9, 'kg', 20),
('Papel Aluminio', 3, 'unidades', 25),
('Salsa de Tomate', 7, 'litros', 70),
('Mermelada de Fresa', 2, 'unidades', 50),
('Huevos Blancos', 6, 'unidades', 90),
('Mayonesa', 7, 'litros', 35),
('Avena en Hojuelas', 1, 'kg', 120),
('Vinagre Blanco', 2, 'litros', 60),
('Agua Mineral', 5, 'litros', 150),
('Jabón Líquido', 4, 'unidades', 80),
('Desodorante en Barra', 3, 'unidades', 45),
('Crema Dental', 3, 'unidades', 100),
('Cebolla Roja', 1, 'kg', 130),
('Pimiento Verde', 1, 'kg', 90),
('Papas Amarillas', 1, 'kg', 200),
('Ajo Entero', 1, 'kg', 60),
('Carne de Res', 2, 'kg', 50),
('Pollo Entero', 2, 'kg', 40),
('Jugo de Manzana', 5, 'litros', 25),
('Pasta de Dientes', 3, 'unidades', 75),
('Toallas de Papel', 3, 'unidades', 30),
('Shampoo', 3, 'litros', 80),
('Maíz', 2, 'kg', 230),
('Arroz', 2, 'kg', 120);


-- Crear la tabla entradas
CREATE TABLE entradas (
    id_entrada SERIAL PRIMARY KEY,
    id_productos INT NOT NULL REFERENCES productos(id),
    nombre_productos VARCHAR(100) NOT NULL,
    descripcion_operacion VARCHAR(255),
    cantidad INT NOT NULL,
    precio_unitario NUMERIC(10, 2) NOT NULL,
    total NUMERIC(10, 2) NOT NULL,
    fecha TIMESTAMP NOT NULL
);

-- Insertar datos en la tabla entradas (sin id_proveedor ni id_usuario)
INSERT INTO entradas (id_entrada, id_productos, nombre_productos, descripcion_operacion, cantidad, precio_unitario, total, fecha) VALUES
(1, 1, 'Arroz Blanco', 'Compra inicial de producto', 50, 1.20, 60.00, '2024-11-19'),
(2, 2, 'Frijol Negro', 'Reabastecimiento semanal', 30, 2.50, 75.00, '2024-11-19'),
(3, 3, 'Aceite Vegetal', 'Reabastecimiento mensual', 20, 5.00, 100.00, '2024-11-18'),
(4, 4, 'Harina de Maíz', 'Ajuste de inventario', 40, 1.00, 40.00, '2024-11-18'),
(5, 5, 'Jabón de Barra', 'Oferta especial', 25, 0.80, 20.00, '2024-11-17'),
(6, 6, 'Detergente en Polvo', 'Compra mayorista', 10, 3.00, 30.00, '2024-11-17'),
(7, 7, 'Leche Entera', 'Pedido urgente', 15, 4.00, 60.00, '2024-11-16'),
(8, 8, 'Pan Integral', 'Reposición diaria', 35, 1.50, 52.50, '2024-11-16'),
(9, 9, 'Sal de Mesa', 'Compra por temporada', 20, 0.50, 10.00, '2024-11-15');


-- Crear la tabla salidas
CREATE TABLE Salidas (
    id_salida SERIAL PRIMARY KEY,
    id_producto INT NOT NULL REFERENCES productos(id),
    cantidad INT NOT NULL,
    valor_unitario NUMERIC(10, 2) NOT NULL,
    valor_total NUMERIC(10, 2) NOT NULL,
    fecha_salida TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    id_usuario INT NOT NULL REFERENCES usuario(id_usuario)
);

CREATE TABLE salidas (
    id_salida SERIAL PRIMARY KEY, -- SERIAL para auto-incremento
    id_producto INT NOT NULL, 
    cantidad INT NOT NULL,
    valor_unitario NUMERIC(10, 2) NOT NULL, 
    valor_total NUMERIC(10, 2) NOT NULL,
    fecha_salida TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, -- Valor por defecto con CURRENT_TIMESTAMP
    id_usuario INT NOT NULL, -- Referencia al usuario responsable
    CONSTRAINT fk_producto FOREIGN KEY (id_producto) REFERENCES productos(id), -- Clave foránea a productos
    CONSTRAINT fk_usuario FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario) -- Clave foránea a usuario
);


-- Insertar datos en la tabla salidas
INSERT INTO salidas (id_salida, id_producto, cantidad, valor_unitario, valor_total, fecha_salida, id_usuario) VALUES
(1, 1, 20, 1.20, 24.00, '2024-11-20 10:00:00', 1), -- Venta parcial de "Arroz Blanco"
(2, 2, 15, 2.50, 37.50, '2024-11-20 12:00:00', 1), -- Venta de "Frijol Negro"
(3, 3, 10, 5.00, 50.00, '2024-11-19 15:00:00', 2), -- Ajuste de "Aceite Vegetal"
(4, 4, 20, 1.00, 20.00, '2024-11-19 16:00:00', 3), -- Uso de "Harina de Maíz" en otro proceso
(5, 5, 10, 0.80, 8.00, '2024-11-18 09:00:00', 1), -- Venta promocional de "Jabón de Barra"
(6, 6, 5, 3.00, 15.00, '2024-11-18 11:00:00', 2), -- Venta de "Detergente en Polvo"
(7, 7, 5, 4.00, 20.00, '2024-11-17 14:00:00', 2), -- Consumo interno de "Leche Entera"
(8, 8, 25, 1.50, 37.50, '2024-11-17 15:30:00', 3), -- Venta de "Pan Integral"
(9, 9, 10, 0.50, 5.00, '2024-11-16 10:00:00', 1); -- Venta por temporada de "

