-- Poblar tabla categoria
INSERT INTO categoria (id_categoria, nombre) VALUES 
(1, 'Electrónica'),
(2, 'Hogar'),
(3, 'Ropa'),
(4, 'Juguetes'),
(5, 'Deportes'),
(6, 'Muebles');

-- Poblar tabla producto
INSERT INTO producto (id_producto,nombre, descripcion, precio, stock, estado, id_categoria) VALUES 
(1, 'Smart TV 4K de 55 pulgadas', 'Televisor de gran calidad y sonido', 7000000.00, 20, 'Disponible', 1),
(2, 'Playstation 5 Pro', 'Consola moderna de gran potencia', 8000000.00, 6, 'Disponible', 1),
(3, 'Nintendo Switch', 'Consola moderna de gran potencia', 500000.00, 2, 'Disponible', 1),
(4, 'Xbox Series S', 'Consola moderna de gran potencia', 600000.00, 4, 'Disponible', 1),
(5, 'Smart TV de 50 pulgadas', 'Televisor de gran calidad y sonido', 7000000.00, 20, 'Disponible', 1),
(6, 'Refrigerador de 400L', 'Refrigerador de bajo consumo', 450000.00, 10, 'Disponible', 2),
(7, 'Licuadora de 2L', 'Licuadora de alta potencia y con buen motor', 60000.00, 15, 'Disponible', 2),
(8, 'Hervidor eléctrico de 3L', 'Hervidor de acero inoxidable y de buena calidad', 25000.00, 30, 'Disponible', 2),
(9, 'Microondas', 'Microondas de alta potencia y permite descongelar productos', 80000.00, 20, 'Disponible', 2),
(10, 'Robot aspiradora', 'Producto inteligente que limpia el hogar', 120000.00, 8, 'Disponible', 2),
(11, 'Polera', 'Polera básica talla M de color blanco', 12000.00, 50, 'Disponible', 3),
(12, 'Jeans mezclilla', 'Jeans tiro bajo y muy ancho', 25000.00, 40, 'Disponible', 3),
(13, 'Chaqueta deportiva', 'Chaqueta perfecta para el ejercicio y talla L', 45000.00, 15, 'Disponible', 3),
(14, 'Zapatillas de running', 'Zapatillas diseñadas con tecnología especial para correr, talla 42', 60000.00, 20, 'Disponible', 3),
(15, 'Vestido ligero', 'Vestido perfecto para épocas de altas temperaturas', 30000.00, 10, 'Disponible', 3),
(16, 'Lego', 'Set de construcción de lego', 45000.00, 25, 'Disponible', 4),
(17, 'Muñeca', 'Muñeca con sonidos y luces', 30000.00, 18, 'Disponible', 4),
(18, 'Auto a control remoto', 'Coche eléctrico con control de alta duración', 40000.00, 10, 'Disponible', 4),
(19, 'Puzzle', 'Rompecabezas de 1000 piezas y hecho con materiales de alta calidad', 15000.00, 50, 'Disponible', 4),
(20, 'Drone', 'Drone con cámara básica e intuitivo para menores de edad', 60000.00, 12, 'Disponible', 4),
(21, 'Pelota de fútbol', 'Pelota de fútbol de la Euro 2024', 25000.00, 30, 'Disponible', 5),
(22, 'Bicicleta', 'Bicicleta con suspensión completa, ideal para las montañas', 350000.00, 5, 'Disponible', 5),
(23, 'Pesas', 'Set de pesas con discos intercambiables hasta 20 kilos', 80000.00, 10, 'Disponible', 5),
(24, 'Malla para voleibol', 'Malla de nylon resistente', 30000.00, 8, 'Disponible', 5),
(25, 'Skateboard', 'Tabla de skate profesional con buen agarre', 70000.00, 12, 'Disponible', 5),
(26, 'Sofá 3 plazas', 'Sofá cómodo y elegante', 250000.00, 3, 'Disponible', 6),
(27, 'Mesa de comedor', 'Mesa de madera para 6 personas, no incluye las sillas', 180000.00, 5, 'Disponible', 6),
(28, 'Cama King Size', 'Cama amplia y confortable', 350000.00, 2, 'Disponible', 6),
(29, 'Silla ergonómica', 'Silla ideal para oficina', 70000.00, 15, 'Disponible', 6),
(30, 'Estantería de madera', 'Estantería armable y espaciosa', 90000.00, 10, 'Disponible', 6);


-- Poblar tabla cliente
INSERT INTO cliente (id_cliente, nombre, direccion, email, telefono) VALUES 
(1, 'Byron Brito', 'Calle 123', 'byron.brito@gmail.com', '123456789'),
(2, 'Thomas Oyanedel', 'Avenida los palotes 3', 'thomas.oyanedel@gmail.com', '987654321'),
(3, 'Isidora Riffo', 'Pasaje feliz 1', 'isidora.riffo@gmail.com', '456789123'),
(4, 'Benjamin Cassone', 'Calle eao 231', 'benjamin.cassone@gmail.com', '789123456'),
(5, 'Andrea Bustamante', 'Condominio buena vida 2', 'andrea.bustamante@gmail.com', '321654987');

-- Poblar tabla orden
INSERT INTO orden (id_orden, fecha_orden, estado, id_cliente, total) VALUES 
(1, '2024-11-01 10:00:00', 'Pagada', 1, 500000.00),
(2, '2024-11-02 15:30:00', 'Enviada', 2, 400000.00),
(3, '2024-11-05 18:45:00', 'Pendiente', 3, 50000.00),
(4, '2024-11-10 12:20:00', 'Enviada', 5, 100000.00),
(5, '2024-11-12 17:30:00', 'Enviada', 5, 300000.00),
(6, '2024-11-20 18:00:00', 'Pagada', 1, 80000.00);

-- Poblar tabla detalle_orden
INSERT INTO detalle_orden (id_detalle, id_orden, id_producto, cantidad, precio_unitario) VALUES 
(1, 1, 1, 2, 7000000.00),
(2, 2, 2, 1, 8000000.00),
(3, 2, 3, 1, 500000.00),
(4, 3, 4, 1, 600000.00),
(5, 4, 21, 4, 25000.00),
(6, 5, 22, 1, 350000.00),
(7, 6, 5, 2, 7000000.00);