--Eliminado elementos
DELETE
FROM LOGIN;
--Eliminando Tabla
DROP TABLE Login CASCADE CONSTRAINTS;

--Creando Tabla Login
create table Login
(
    cedula  VARCHAR(12) not null,
    contrase�a VARCHAR(50) not null,
    rol     VARCHAR(15) not null,
    CONSTRAINT pkLogin PRIMARY KEY (cedula)
);

--Insertando Usuarios
INSERT
ALL
    INTO LOGIN
VALUES ('304760577', '123', 'Alumno')
INTO LOGIN
VALUES ('435435643', '123', 'Alumno')

SELECT *
FROM dual;

--Funciones
CREATE
OR REPLACE PACKAGE types
AS
    TYPE ref_cursor IS REF CURSOR;
END;
/

CREATE
OR REPLACE FUNCTION obtenerLogin(idbuscar IN login.cedula%TYPE)
    RETURN Types.ref_cursor
AS
    login_cursor types.ref_cursor;
BEGIN
OPEN login_cursor FOR
SELECT cedula, contrase�a, rol
FROM Login
WHERE cedula = idbuscar;
RETURN login_cursor;
END;
/


