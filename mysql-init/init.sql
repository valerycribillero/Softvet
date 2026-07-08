-- ============================================================
-- Se ejecuta automáticamente la PRIMERA vez que se crea el
-- contenedor de MySQL (gracias al volumen montado en
-- /docker-entrypoint-initdb.d en el docker-compose.yml).
-- ============================================================

CREATE DATABASE IF NOT EXISTS autenticacion;
CREATE DATABASE IF NOT EXISTS pagos;
CREATE DATABASE IF NOT EXISTS examenes;
CREATE DATABASE IF NOT EXISTS recetas;
CREATE DATABASE IF NOT EXISTS reviews;
CREATE DATABASE IF NOT EXISTS notificaciones;
CREATE DATABASE IF NOT EXISTS servicios;
CREATE DATABASE IF NOT EXISTS ficha;
CREATE DATABASE IF NOT EXISTS reservas;