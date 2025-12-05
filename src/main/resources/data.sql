-- Archivo: init.sql
-- Este script se ejecuta automaticamente al iniciar Spring Boot.

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
-- Asegurate de guardar este archivo como UTF-8 si usas tildes
INSERT INTO finca (id, nombre_propietario, telefono, direccion, departamento_id, municipio_id)
VALUES (1, 'Juan Perez', '3105550001', 'Calle Falsa 123', 1, 101);

-- 4. REINICIAR CONTADORES DE ID (IMPORTANTE PARA POSTGRESQL)
-- Esto evita errores cuando intentes crear registros nuevos desde la App.
-- Nota: Si tus tablas se llaman diferente o usan mayusculas, ajusta los nombres aqui.
SELECT setval(pg_get_serial_sequence('departamento', 'id'), coalesce(max(id),0) + 1, false) FROM departamento;
SELECT setval(pg_get_serial_sequence('municipio', 'id'), coalesce(max(id),0) + 1, false) FROM municipio;
SELECT setval(pg_get_serial_sequence('finca', 'id'), coalesce(max(id),0) + 1, false) FROM finca;