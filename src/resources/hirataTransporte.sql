CREATE DATABASE hirataTransporte;

USE hirataTransporte;

CREATE TABLE usuarios (
    idUsuario INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    rol VARCHAR(30) NOT NULL
);

CREATE TABLE conductores (
    idConductor INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    licencia VARCHAR(50),
    telefono VARCHAR(20),
    idUsuario INT,
    FOREIGN KEY (idUsuario) REFERENCES usuarios(idUsuario)
);

CREATE TABLE camiones (
    idCamion INT AUTO_INCREMENT PRIMARY KEY,
    marca VARCHAR(50) NOT NULL,
    modelo VARCHAR(50) NOT NULL,
    anio INT,
    kilometrajeTotal INT DEFAULT 0,
    kilometrajeUltimoMantenimiento INT DEFAULT 0,
    idConductor INT,
    FOREIGN KEY (idConductor) REFERENCES conductores(idConductor)
);

CREATE TABLE registroKilometraje (
    idRegistro INT AUTO_INCREMENT PRIMARY KEY,
    idCamion INT NOT NULL,
    kilometrajeRecorrido INT NOT NULL,
    fechaRegistro DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (idCamion) REFERENCES camiones(idCamion)
);

CREATE TABLE mantenimiento (
    idMantenimiento INT AUTO_INCREMENT PRIMARY KEY,
    idCamion INT NOT NULL,
    tipoMantenimiento VARCHAR(100),
    descripcion TEXT,
    fecha DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (idCamion) REFERENCES camiones(idCamion)
);

CREATE TABLE tecnicos (
    idTecnico INT AUTO_INCREMENT PRIMARY KEY,
    usuario VARCHAR(50) UNIQUE NOT NULL,
    clave VARCHAR(30) NOT NULL,
    nombreReal VARCHAR(100) NOT NULL,
    rut VARCHAR(10) NOT NULL,
    telefono VARCHAR (15)    
);

CREATE TABLE equipos (
    idEquipo INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    tipo VARCHAR(50), -- PC, impresora, etc
    marca VARCHAR(50),
    modelo VARCHAR(50),
    estado VARCHAR(50) DEFAULT 'ACTIVO' 
);

CREATE TABLE mantenimientoEquipos (
    idMantenimiento INT AUTO_INCREMENT PRIMARY KEY,
    idEquipo INT NOT NULL,
    idTecnico INT NOT NULL,
    tipoMantenimiento ENUM('PREVENTIVO', 'CORRECTIVO') NOT NULL,
    descripcion TEXT NOT NULL,
    observaciones TEXT,
    fecha DATETIME DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (idEquipo) REFERENCES equipos(idEquipo),
    FOREIGN KEY (idTecnico) REFERENCES tecnicos(idTecnico)
);

CREATE TABLE software (
    idSoftware INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    version VARCHAR(50)
);

CREATE TABLE actualizacionSoftware (
    idActualizacion INT AUTO_INCREMENT PRIMARY KEY,
    idEquipo INT NOT NULL,
    idSoftware INT NOT NULL,
    fecha DATETIME DEFAULT CURRENT_TIMESTAMP,
    descripcion TEXT,

    FOREIGN KEY (idEquipo) REFERENCES equipos(idEquipo),
    FOREIGN KEY (idSoftware) REFERENCES software(idSoftware)
);

CREATE TABLE inventarioPiezas (
    idPieza INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,     
    cantidad INT NOT NULL,
    tipo ENUM('DISCO', 'MEMORIA', 'FUENTE', 'PERIFERICO', 'OTRO') NOT NULL,
    estado ENUM('BUEN ESTADO', 'EN REPARACIÓN', 'FUERA DE SERVICIO') DEFAULT 'BUEN ESTADO',
    descripcion TEXT
);

CREATE TABLE movimientoPiezas (
    idMovimiento INT AUTO_INCREMENT PRIMARY KEY,
    idPieza INT NOT NULL,
    tipoMovimiento ENUM('ENTRADA', 'SALIDA') NOT NULL,
    cantidad INT NOT NULL,
    fecha DATETIME DEFAULT CURRENT_TIMESTAMP,
    descripcion TEXT,

    FOREIGN KEY (idPieza) REFERENCES inventarioPiezas(idPieza)
);

USE hirataTransporte;

INSERT INTO equipos(nombre, tipo, marca, modelo, estado) VALUES
('PC Oficina 1', 'PC', 'Dell', 'Optiplex 7090', 'ACTIVO'),
('PC Oficina 2', 'PC', 'HP', 'EliteDesk 800', 'ACTIVO'),
('Impresora Recepción', 'IMPRESORA', 'HP', 'LaserJet Pro M404', 'ACTIVO'),
('TV Sala Reuniones', 'TV', 'Samsung', 'Smart TV 55"', 'INACTIVO'),
('Servidor Principal', 'PC', 'Lenovo', 'ThinkSystem SR650', 'ACTIVO');

INSERT INTO tecnicos(usuario, clave, nombreReal, rut, telefono) VALUES
('Adrian', 'Adrian', 'Adrián Lezana', '190011186', '9956'),
('Cristian', 'Cristian', 'Cristian Villaverde', '28855007k', '696969');

INSERT INTO usuarios (username, password, rol) VALUES
('admin', 'admin123', 'ADMIN'),
('operador1', 'op123', 'OPERADOR'),
('transportista1', 'trans123', 'CONDUCTOR');

INSERT INTO conductores (nombre, licencia, telefono, idUsuario) VALUES
('Juan Pérez', 'B123456', '912345678', 1),
('María González', 'A987654', '923456789', 2),
('Pedro Rojas', 'C456789', '934567890', 3);

INSERT INTO camiones (marca, modelo, anio, kilometrajeTotal, idConductor) VALUES
('Volvo', 'FH16', 2020, 120000, 1),
('Scania', 'R500', 2019, 95000, 2),
('Mercedes-Benz', 'Actros', 2021, 60000, 3);

INSERT INTO MantenimientoEquipos (idEquipo, idTecnico, tipoMantenimiento, descripcion, observaciones) VALUES
(1, 1, 'PREVENTIVO', 'Limpieza interna y revisión general', 'Equipo en buen estado'),
(2, 2, 'CORRECTIVO', 'Cambio de disco duro', 'Se reemplazó por SSD'),
(3, 1, 'PREVENTIVO', 'Limpieza de rodillos', 'Mejora en impresión'),
(4, 2, 'CORRECTIVO', 'Reparación de pantalla', 'Pantalla reemplazada'),
(5, 2, 'PREVENTIVO', 'Revisión de sistema y ventilación', 'Temperatura normal');

INSERT INTO software (nombre, version) VALUES
('Windows 10', '22H2'),
('Microsoft Office', '365'),
('Google Chrome', '120'),
('Adobe Reader', '2024.001'),
('Antivirus Avast', '23.10');

INSERT INTO actualizacionSoftware (idEquipo, idSoftware, descripcion) VALUES
(1, 1, 'Actualización de sistema operativo'),
(1, 3, 'Actualización del navegador'),
(2, 2, 'Actualización de Office'),
(3, 4, 'Actualización de lector PDF'),
(5, 5, 'Actualización de antivirus');

INSERT INTO inventarioPiezas (nombre, tipo, cantidad, estado, descripcion) VALUES
('Disco Duro SSD 1TB', 'MEMORIA', 10, 'BUEN ESTADO', 'SSD para equipos de oficina'),
('Memoria RAM 8GB DDR4', 'MEMORIA', 15, 'BUEN ESTADO', 'RAM estándar para PCs'),
('Fuente de Poder 500W', 'FUENTE', 8, 'BUEN ESTADO', 'Fuentes ATX genéricas'),
('Mouse USB', 'PERIFERICO', 20, 'BUEN ESTADO', 'Mouse óptico básico'),
('Teclado USB', 'PERIFERICO', 12, 'EN REPARACION', 'Algunos con teclas defectuosas');

INSERT INTO movimientoPiezas (idPieza, tipoMovimiento, cantidad, descripcion) VALUES
-- PIEZA 1 (SSD) ? stock final 10
(1, 'ENTRADA', 12, 'Compra inicial'),
(1, 'SALIDA', 2, 'Instalación en equipos'),

-- PIEZA 2 (RAM) ? stock final 15
(2, 'ENTRADA', 20, 'Compra inicial'),
(2, 'SALIDA', 5, 'Reemplazo en PCs'),

-- PIEZA 3 (Fuente) ? stock final 8
(3, 'ENTRADA', 10, 'Ingreso inicial'),
(3, 'SALIDA', 2, 'Reparaciones'),

-- PIEZA 4 (Mouse) ? stock final 20
(4, 'ENTRADA', 25, 'Compra masiva'),
(4, 'SALIDA', 5, 'Entrega a usuarios'),

-- PIEZA 5 (Teclado) ? stock final 12
(5, 'ENTRADA', 15, 'Ingreso inicial'),
(5, 'SALIDA', 3, 'Defectuosos retirados');