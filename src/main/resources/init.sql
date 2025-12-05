-- Archivo: data.sql
-- Este script se ejecuta automáticamente al iniciar Spring Boot.

-- 1. DEPARTAMENTOS
INSERT INTO departamento (id, nombre) VALUES (1, 'Departamento A');
INSERT INTO departamento (id, nombre) VALUES (2, 'Departamento B');
INSERT INTO departamento (id, nombre) VALUES (3, 'Departamento C');

-- 2. MUNICIPIOS
-- Municipios para el Departamento A (id=1)
INSERT INTO municipio (id, nombre, departamento_id) VALUES (101, 'Municipio A-1', 1);
INSERT INTO municipio (id, nombre, departamento_id) VALUES (102, 'Municipio A-2', 1);

-- Municipios para el Departamento B (id=2)
INSERT INTO municipio (id, nombre, departamento_id) VALUES (201, 'Municipio B-1', 2);
INSERT INTO municipio (id, nombre, departamento_id) VALUES (202, 'Municipio B-2', 2);

-- 3. DATOS DE PRUEBA DE FINCA
INSERT INTO finca (id, nombre_propietario, telefono, direccion, departamento_id, municipio_id)
VALUES (1, 'Juan Pérez', '3105550001', 'Calle Falsa 123', 1, 101);