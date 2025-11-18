-- Crea la base de datos si no existe
CREATE DATABASE IF NOT EXISTS joystick_mxdb;
-- Selecciona la base de datos
USE joystick_mxdb;
-- Elimina la base de datos
DROP DATABASE IF EXISTS joystick_mxdb;
-- Selecciona clientes
select * from usuarios as u
inner join clientes as c
where u.idusuario = c.idusuario;
-- Selecciona administradores
select * from usuarios as u
inner join administradores as a
where u.idusuario = a.idusuario;
-- Obtiene videojuegos
select * from videojuegos;