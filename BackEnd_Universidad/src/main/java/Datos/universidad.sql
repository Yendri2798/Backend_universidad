create table Carrera
(
    codigo_Carrera VARCHAR(12) not null,
    nombre         VARCHAR(45) not null,
    titulo         VARCHAR(45) not null,
    CONSTRAINT pkCodigo_Carrera PRIMARY KEY (codigo_Carrera)
);

CREATE
OR REPLACE PACKAGE types
AS
     TYPE ref_cursor IS REF CURSOR;
END;
/

create
or replace PROCEDURE insertaCarrera(cod in carrera.codigo_Carrera%TYPE, nom in carrera.nombre%TYPE,
tit in carrera.titulo%TYPE)
as
begin
insert into Carrera
values (cod, nom, tit);
end;
/

create
or replace PROCEDURE modificaCarrera(cod in carrera.codigo_Carrera%TYPE, nom in carrera.nombre%TYPE,
tit in carrera.titulo%TYPE)
as
begin
UPDATE Carrera
SET nombre = nom,
    titulo=tit
where codigo_Carrera = cod;
end;
/

create
or replace PROCEDURE eliminaCarrera(cod in carrera.codigo_Carrera%TYPE)
as
begin
DELETE
FROM Carrera
where codigo_Carrera = cod;
end;
/

CREATE
OR REPLACE FUNCTION buscarCarrera(idbuscar IN carrera.codigo_Carrera%TYPE)
RETURN Types.ref_cursor 
AS 
        carrera_cursor types.ref_cursor;
BEGIN
OPEN carrera_cursor FOR
SELECT codigo_Carrera, nombre, titulo
FROM Carrera
WHERE codigo_Carrera = idbuscar;
RETURN carrera_cursor;
END;
/

CREATE
OR REPLACE FUNCTION listarCarrera
RETURN Types.ref_cursor 
AS 
        carrera_cursor types.ref_cursor;
BEGIN
OPEN carrera_cursor FOR
SELECT codigo_Carrera, nombre, titulo
FROM Carrera;
RETURN carrera_cursor;
END;
/

/* CURSO */

create table curso
(
    codigo_Curso    VARCHAR(12) not null,
    nombre          VARCHAR(45) not null,
    creditos        INT         not null,
    horas_semanales INT         not null,
    carrera_codigo  VARCHAR(10) not null,
    CONSTRAINT pkCodigo_Curso PRIMARY KEY (codigo_Curso),
    CONSTRAINT fkCarrera_Codigo FOREIGN KEY (carrera_codigo) REFERENCES Carrera (codigo_Carrera)
);

CREATE
OR REPLACE PACKAGE types
AS
     TYPE ref_cursor IS REF CURSOR;
END;
/

create
or replace PROCEDURE insertaCurso(codCur in curso.codigo_Curso%TYPE, nom in curso.nombre%TYPE,
cred in curso.creditos%TYPE, hora in curso.horas_semanales%TYPE, carrera_cod in curso.carrera_codigo%TYPE)
as
begin
insert into Curso
values (codCur, nom, cred, hora, carrera_cod);
end;
/

create
or replace PROCEDURE modificaCurso(codCur in curso.codigo_Curso%TYPE, nom in curso.nombre%TYPE,
cred in curso.creditos%TYPE, hora in curso.horas_semanales%TYPE, carrera_cod in curso.carrera_codigo%TYPE)
as
begin
UPDATE Curso
SET nombre          = nom,
    creditos=cred,
    horas_semanales = hora,
    carrera_codigo  = carrera_cod
where codigo_Curso = codCur;
end;
/

create
or replace PROCEDURE eliminaCurso(codCur in curso.codigo_Curso%TYPE)
as
begin
DELETE
FROM Curso
where codigo_Curso = codCur;
end;
/

CREATE
OR REPLACE FUNCTION buscarCurso(idbuscar IN curso.codigo_Curso%TYPE)
RETURN Types.ref_cursor 
AS 
        curso_cursor types.ref_cursor;
BEGIN
OPEN curso_cursor FOR
SELECT codigo_Curso, nombre, creditos, horas_semanales, carrera_codigo
FROM Curso
WHERE codigo_Curso = idbuscar;
RETURN curso_cursor;
END;
/

CREATE
OR REPLACE FUNCTION buscarCursoCarrera(idbuscar IN curso.carrera_codigo%TYPE)
RETURN Types.ref_cursor 
AS 
        curso_cursor types.ref_cursor;
BEGIN
OPEN curso_cursor FOR
SELECT codigo_Curso, nombre, creditos, horas_semanales, carrera_codigo
FROM Curso
WHERE carrera_codigo = idbuscar;
RETURN curso_cursor;
END;
/

CREATE
OR REPLACE FUNCTION listarCurso
RETURN Types.ref_cursor 
AS 
        curso_cursor types.ref_cursor;
BEGIN
OPEN curso_cursor FOR
SELECT codigo_Curso, nombre, creditos, horas_semanales, carrera_codigo
FROM Curso;
RETURN curso_cursor;
END;
/

/* PROFESOR */

create table profesor
(
    cedula_Profesor VARCHAR(15) not null,
    nombre          VARCHAR(25) not null,
    primer_apellido VARCHAR(25) NOT NULL,
    telefono        INT         not null,
    email           VARCHAR(60) not null,
    CONSTRAINT pkCedula_Profesor PRIMARY KEY (cedula_Profesor)
);

CREATE
OR REPLACE PACKAGE types
AS
     TYPE ref_cursor IS REF CURSOR;
END;
/

create
or replace PROCEDURE insertaProfesor(ced in profesor.cedula_Profesor%TYPE, nom in profesor.nombre%TYPE,
apel in profesor.primer_apellido%TYPE,tel in profesor.telefono%TYPE, correo in profesor.email%TYPE)
as
begin
insert into profesor
values (ced, nom, apel, tel, correo);
end;
/

create
or replace PROCEDURE modificaProfesor(ced in profesor.cedula_Profesor%TYPE, nom in profesor.nombre%TYPE,
apel in profesor.primer_apellido%TYPE,tel in profesor.telefono%TYPE, correo in profesor.email%TYPE)
as
begin
UPDATE profesor
SET nombre          = nom,
    primer_apellido = apel,
    telefono        = tel,
    email           = correo
where cedula_Profesor = ced;
end;
/

create
or replace PROCEDURE eliminaProfesor(ced in profesor.cedula_Profesor%TYPE)
as
begin
DELETE
FROM profesor
where cedula_Profesor = ced;
end;
/

CREATE
OR REPLACE FUNCTION buscarProfesor(idbuscar IN profesor.cedula_Profesor%TYPE)
RETURN Types.ref_cursor 
AS 
        profe_cursor types.ref_cursor;
BEGIN
OPEN profe_cursor FOR
SELECT cedula_Profesor, nombre, primer_apellido, telefono, email
FROM profesor
WHERE cedula_Profesor = idbuscar;
RETURN profe_cursor;
END;
/

CREATE
OR REPLACE FUNCTION listarProfesor
RETURN Types.ref_cursor 
AS 
        profe_cursor types.ref_cursor;
BEGIN
OPEN profe_cursor FOR
SELECT cedula_Profesor, nombre, primer_apellido, telefono, email
FROM profesor;
RETURN profe_cursor;
END;
/

/* ALUMNO */

create table alumno
(
    cedula_Alumno    VARCHAR(15) NOT NULL,
    nombre           varchar(20) NOT NULL,
    primer_apellido  varchar(20) not null,
    telefono         INT         not null,
    email            varchar(50) not null,
    fecha_Nacimiento DATE        not null,
    carrera_codigo   varchar(10),
    CONSTRAINT pkCedula_Alumno PRIMARY KEY (cedula_Alumno),
    CONSTRAINT fkCarrera_Cod FOREIGN KEY (carrera_Codigo) REFERENCES carrera (codigo_Carrera)
);

CREATE
OR REPLACE PACKAGE types
AS
     TYPE ref_cursor IS REF CURSOR;
END;
/

create
or replace PROCEDURE insertaAlumno(ced in alumno.cedula_alumno%TYPE, nom in alumno.nombre%TYPE,
apel in alumno.primer_apellido%TYPE,tel in alumno.telefono%TYPE, email in alumno.email%TYPE, fecha in alumno.fecha_nacimiento%TYPE,
carrera_cod in alumno.carrera_codigo%TYPE)
as
begin
insert into Alumno
values (ced, nom, apel, tel, email, fecha, carrera_cod);
end;
/

create
or replace PROCEDURE modificaAlumno(ced in alumno.cedula_alumno%TYPE, nom in alumno.nombre%TYPE,
apel in alumno.primer_apellido%TYPE,tel in alumno.telefono%TYPE, email in alumno.email%TYPE, fecha in alumno.fecha_nacimiento%TYPE,
carrera_cod in alumno.carrera_codigo%TYPE)
as
begin
UPDATE Alumno
SET nombre           = nom,
    primer_apellido  = apel,
    telefono         = tel,
    email            = email,
    fecha_nacimiento = fecha,
    carrera_codigo   = carrera_cod
where cedula_Alumno = ced;
end;
/

create
or replace PROCEDURE eliminaAlumno(ced in alumno.cedula_alumno%TYPE)
as
begin
DELETE
FROM Alumno
where cedula_alumno = ced;
end;
/

CREATE
OR REPLACE FUNCTION buscarAlumno(idbuscar IN alumno.cedula_alumno%TYPE)
RETURN Types.ref_cursor 
AS 
        alumno_cursor types.ref_cursor;
BEGIN
OPEN alumno_cursor FOR
SELECT cedula_alumno, nombre, primer_apellido, telefono, email, fecha_nacimiento, carrera_codigo
FROM Alumno
WHERE cedula_alumno = idbuscar;
RETURN alumno_cursor;
END;
/

CREATE
OR REPLACE FUNCTION listarAlumno
RETURN Types.ref_cursor 
AS 
        alumno_cursor types.ref_cursor;
BEGIN
OPEN alumno_cursor FOR
SELECT cedula_alumno, nombre, primer_apellido, telefono, email, fecha_nacimiento, carrera_codigo
FROM Alumno;
RETURN alumno_cursor;
END;
/

/* CICLO */

create table ciclo
(
    annio              Int        not null,
    numero             varchar(8) not null,
    fecha_Incio        DATE       not null,
    fecha_Finalizacion DATE       not null,
    CONSTRAINT pkAnnioNumero PRIMARY KEY (annio, numero)
);

CREATE
OR REPLACE PACKAGE types
AS
     TYPE ref_cursor IS REF CURSOR;
END;
/

create
or replace PROCEDURE insertaCiclo(an in ciclo.annio%TYPE, nume in ciclo.numero%TYPE,fecha_Ini in ciclo.fecha_Incio%TYPE,
fecha_Final in ciclo.fecha_Finalizacion%TYPE)
as
begin
insert into ciclo
values (an, nume, fecha_Ini, fecha_Final);
end;
/

create
or replace PROCEDURE modificaCiclo(anni in ciclo.annio%TYPE, numer in ciclo.numero%TYPE,fechaInicio in ciclo.fecha_Incio%TYPE,
fechaFin in ciclo.fecha_finalizacion%TYPE)
as
begin
UPDATE Ciclo
SET numero             = numer,
    fecha_Incio        = TO_DATE(fechaInicio, 'dd-MM-yyyy'),
    fecha_finalizacion = TO_DATE(fechaFin, 'dd-MM-yyyy')
where annio = anni;
end;
/

create
or replace PROCEDURE eliminaCiclo(anni in ciclo.annio%TYPE, nume in ciclo.numero%TYPE)
as
begin
DELETE
FROM Ciclo
where annio = anni
  and numero = nume;
end;
/

CREATE
OR REPLACE FUNCTION buscarCiclo(idbuscar IN ciclo.annio%TYPE)
RETURN Types.ref_cursor 
AS 
        ciclo_cursor types.ref_cursor;
BEGIN
OPEN ciclo_cursor FOR
SELECT annio, numero, fecha_Incio, fecha_Finalizacion
FROM Ciclo
WHERE annio = idbuscar;
RETURN ciclo_cursor;
END;
/

CREATE
OR REPLACE FUNCTION listarCiclo
RETURN Types.ref_cursor 
AS 
        ciclo_cursor types.ref_cursor;
BEGIN
OPEN ciclo_cursor FOR
SELECT annio, numero, fecha_Incio, fecha_Finalizacion
FROM ciclo;
RETURN ciclo_cursor;
END;
/

/* GRUPO */

create table grupo
(
    numero_Grupo     INT         NOT NULL,
    horario          VARCHAR(30) NOT NULL,
    campos_Restantes INT         NOT NULL,
    capacidad_Maxima INT         NOT NULL,
    ciclo_annio      INT         NOT NULL, /*foranea*/
    curso_codigo     VARCHAR(10) NOT NULL,
    profesor_cedula  VARCHAR(15) NOT NULL,
    CONSTRAINT pkNumero_Grupo PRIMARY KEY (numero_Grupo, ciclo_annio),
    CONSTRAINT fkCurso_Codigo FOREIGN KEY (curso_codigo) REFERENCES Curso (codigo_Curso),
    CONSTRAINT fkProfesor_cedula FOREIGN KEY (profesor_cedula) REFERENCES Profesor (cedula_Profesor)
);

ALTER TABLE Grupo
    ADD numero_ciclo varchar(8);

ALTER TABLE Grupo
    ADD CONSTRAINT fk_CicloNumero
        FOREIGN KEY (ciclo_annio, numero_ciclo)
            REFERENCES Ciclo (annio, numero);

CREATE
OR REPLACE PACKAGE types
AS
     TYPE ref_cursor IS REF CURSOR;
END;
/

create
or replace PROCEDURE insertaGrupo(num1 in grupo.numero_grupo%TYPE, hora in grupo.horario%TYPE,
campos in grupo.campos_Restantes%TYPE,capacidad in grupo.capacidad_Maxima%TYPE, ciclo1 in grupo.ciclo_annio%TYPE,
curs in grupo.curso_codigo%TYPE, prof in grupo.profesor_cedula%TYPE, num_ciclo in grupo.numero_ciclo%TYPE)
as
begin
insert into Grupo
values (num1, hora, campos, capacidad, ciclo1, curs, prof, num_ciclo);
end;
/

create
or replace PROCEDURE modificaGrupo(num1 in grupo.numero_grupo%TYPE, hora in grupo.horario%TYPE,
campos in grupo.campos_Restantes%TYPE,capacidad in grupo.capacidad_Maxima%TYPE, ciclo1 in grupo.ciclo_annio%TYPE,
curs in grupo.curso_codigo%TYPE, prof in grupo.profesor_cedula%TYPE, num_ciclo in grupo.numero_ciclo%TYPE)
as
begin
UPDATE grupo
SET horario          = hora,
    campos_Restantes = campos,
    capacidad_Maxima = capacidad,
    ciclo_annio      = ciclo1,
    curso_codigo     = curs,
    profesor_cedula  = prof,
    numero_ciclo     = num_ciclo
where numero_grupo = num1;
end;
/

create
or replace PROCEDURE eliminaGrupo(num1 in grupo.numero_grupo%TYPE)
as
begin
DELETE
FROM Grupo
where numero_grupo = num1;
end;
/

CREATE
OR REPLACE FUNCTION buscarGrupo(idbuscar IN grupo.numero_grupo%TYPE)
RETURN Types.ref_cursor 
AS 
        grupo_cursor types.ref_cursor;
BEGIN
OPEN grupo_cursor FOR
SELECT numero_grupo,
       horario,
       campos_Restantes,
       capacidad_Maxima,
       ciclo_annio,
       curso_codigo,
       profesor_cedula,
       numero_ciclo
FROM grupo
WHERE numero_grupo = idbuscar;
RETURN grupo_cursor;
END;
/

CREATE
OR REPLACE FUNCTION listarGrupo
RETURN Types.ref_cursor 
AS 
        grupo_cursor types.ref_cursor;
BEGIN
OPEN grupo_cursor FOR
SELECT numero_grupo,
       horario,
       campos_Restantes,
       capacidad_Maxima,
       ciclo_annio,
       curso_codigo,
       profesor_cedula,
       numero_ciclo
FROM grupo;
RETURN grupo_cursor;
END;
/

create
or replace PROCEDURE modificaGrupoCamposRestantes(
restante in grupo.campos_restantes%TYPE, numGrupo in grupo.numero_grupo%TYPE)
as
begin
UPDATE Grupo
SET campos_restantes=campos_restantes - restante
where numero_grupo = numGrupo;
end;
/


create table nota_alumno
(
    id_Curso      varchar(15) not null,
    nota          int         not null,
    alumno_cedula varchar(15),
    CONSTRAINT fkAlumnoCed FOREIGN KEY (alumno_cedula) REFERENCES Alumno (cedula_Alumno)
);

create table consejero
(
    alumno_Cedula   VARCHAR(15) NOT NULL,
    profesor_cedula VARCHAR(15) NOT NULL,
    CONSTRAINT fkAlumCed FOREIGN KEY (alumno_Cedula) REFERENCES Alumno (cedula_Alumno),
    CONSTRAINT fkProfCed FOREIGN KEY (profesor_cedula) REFERENCES Profesor (cedula_Profesor),
    CONSTRAINT pkConsejero PRIMARY KEY (alumno_Cedula, profesor_cedula)
);

create table Prematricula
(
    idPre         INT         NOT NULL,
    estado        VARCHAR(10),
    alumno_cedula varchar(15) not null,
    grupo_num     Int         not null,
    grupo_ciclo   Int         not null,
    CONSTRAINT pkIdPre PRIMARY KEY (idPre),
    CONSTRAINT fkAlCed FOREIGN KEY (alumno_cedula) REFERENCES Alumno (cedula_Alumno),
    CONSTRAINT fkGrupoNumCiclo FOREIGN KEY (grupo_num, grupo_ciclo) REFERENCES Grupo (numero_Grupo, ciclo_annio)
);

CREATE SEQUENCE pretmat_seq INCREMENT BY 1 START WITH 1;

CREATE
OR REPLACE TRIGGER pretmat_Increment
BEFORE INSERT ON Prematricula 
FOR EACH ROW

BEGIN
SELECT pretmat_seq.NEXTVAL
INTO :new.idPre
FROM dual;
END;
/


create table Analisis
(
    consejero_Alumno   VARCHAR(15) not null,
    consejero_Profesor Varchar(15) not null,
    prematricula_id    Int         not null,
    CONSTRAINT pkAnalisis PRIMARY KEY (consejero_Alumno, consejero_Profesor, prematricula_id),
    CONSTRAINT fkConsejoAlum FOREIGN KEY (consejero_Alumno) REFERENCES Alumno (cedula_Alumno),
    CONSTRAINT fkConsejoPro FOREIGN KEY (consejero_Profesor) REFERENCES Profesor (cedula_Profesor),
    CONSTRAINT fkpremat FOREIGN KEY (prematricula_id) REFERENCES Prematricula (idPre)
);

create table Matricula
(
    prematric_id  int not null,
    alumno_cedula varchar(15),
    CONSTRAINT fkprematId FOREIGN KEY (prematric_id) REFERENCES Prematricula (idPre),
    CONSTRAINT fkAlumnoCedula FOREIGN KEY (alumno_cedula) REFERENCES Alumno (cedula_Alumno)
);

create table Login
(
    cedula  VARCHAR(12) not null,
    contrase�a VARCHAR(50) not null,
    rol     VARCHAR(15) not null,
    CONSTRAINT pkLogin PRIMARY KEY (cedula)
);

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

/*Actualizaci�n al 13-04-2022*/

/*NOTA*/

create
or replace PROCEDURE insertaNota(idCurso in nota_alumno.id_curso%TYPE, note in nota_alumno.nota%TYPE,
ced_alum in nota_alumno.alumno_cedula%TYPE)
as
begin
insert into nota_alumno
values (idCurso, note, ced_alum);
end;
/

create
or replace PROCEDURE modificaNota(idCurso in nota_alumno.id_curso%TYPE, note in nota_alumno.nota%TYPE,
ced_alum in nota_alumno.alumno_cedula%TYPE)
as
begin
UPDATE nota_alumno
SET nota = note
where alumno_cedula = ced_alum
  and id_curso = idCurso;
end;
/

CREATE
OR REPLACE FUNCTION buscarNota(idbuscar IN nota_alumno.alumno_cedula%TYPE)
RETURN Types.ref_cursor 
AS 
        nota_cursor types.ref_cursor;
BEGIN
OPEN nota_cursor FOR
SELECT alumno_cedula, nota, id_Curso
FROM nota_alumno
WHERE alumno_cedula = idbuscar;
RETURN nota_cursor;
END;
/


/*Consejero*/

create
or replace PROCEDURE insertaConsejero(alumCed in consejero.alumno_cedula%TYPE,
profeCed in consejero.profesor_cedula%TYPE)
as
begin
insert into Consejero
values (alumCed, profeCed);
end;
/

CREATE
OR REPLACE FUNCTION buscarConsejeroProfe(idbuscar IN consejero.profesor_cedula%TYPE)
RETURN Types.ref_cursor 
AS 
        consejero_cursor types.ref_cursor;
BEGIN
OPEN consejero_cursor FOR
SELECT alumno_Cedula, profesor_cedula
FROM Consejero
WHERE profesor_cedula = idbuscar;
RETURN consejero_cursor;
END;
/

CREATE
OR REPLACE FUNCTION buscarConsejeroAlumno(idbuscar IN consejero.alumno_Cedula%TYPE)
RETURN Types.ref_cursor 
AS 
        consejero_cursor types.ref_cursor;
BEGIN
OPEN consejero_cursor FOR
SELECT alumno_Cedula, profesor_cedula
FROM Consejero
WHERE profesor_cedula = idbuscar;
RETURN consejero_cursor;
END;
/

/*Prematricula*/

create
or replace PROCEDURE insertaPrematricula(
estado in prematricula.estado%TYPE, cedAlum in prematricula.alumno_cedula%TYPE,
numGrupo in prematricula.grupo_num%TYPE, grupoCiclo in prematricula.grupo_ciclo%TYPE)
as
begin
insert into Prematricula(estado, alumno_cedula, grupo_num, grupo_Ciclo)
values (estado, cedAlum, numGrupo, grupociclo);
end;
/

create
or replace PROCEDURE modificaPrematricula(idP in prematricula.idpre%TYPE,
est in prematricula.estado%TYPE)
as
begin
UPDATE Prematricula
SET estado = est
where idpre = idP;
end;
/

create
or replace PROCEDURE eliminaPrematricula(idP in prematricula.idpre%TYPE)
as
begin
DELETE
FROM Prematricula
where idpre = idP;
end;
/

CREATE
OR REPLACE FUNCTION buscarPrematricula(idbuscar IN prematricula.alumno_cedula%TYPE)
RETURN Types.ref_cursor 
AS 
        prematricula_cursor types.ref_cursor;
BEGIN
OPEN prematricula_cursor FOR
SELECT idpre, estado, alumno_cedula, grupo_num, grupo_ciclo
FROM Prematricula
WHERE alumno_cedula = idbuscar;
RETURN prematricula_cursor;
END;
/

/*An�lisis*/

create
or replace PROCEDURE insertaAnalisis(cedAlum in analisis.consejero_Alumno%TYPE,
cedProf in analisis.consejero_profesor%TYPE, id_Pret in analisis.prematricula_id%TYPE)
as
begin
insert into Analisis
values (cedAlum, cedProf, id_Pret);
end;
/

create
or replace PROCEDURE eliminaAnalisis(idPret in analisis.prematricula_id%TYPE)
as
begin
DELETE
FROM Analisis
where prematricula_id = idPret;
end;
/

CREATE
OR REPLACE FUNCTION buscarAnalisis(idbuscar IN analisis.consejero_profesor%TYPE)
RETURN Types.ref_cursor 
AS 
        analisis_cursor types.ref_cursor;
BEGIN
OPEN analisis_cursor FOR
SELECT consejero_alumno, consejero_profesor, prematricula_id
FROM Analisis
WHERE consejero_profesor = idbuscar;
RETURN analisis_cursor;
END;
/

/*Matricula*/

create
or replace PROCEDURE insertaMatricula(idPremat in matricula.prematric_id%TYPE,
cedAlum in matricula.alumno_cedula%TYPE)
as
begin
insert into Matricula
values (idPremat, cedAlum);
end;
/

/*INSERCI�N DE DATOS PROFESOR*/

insert into profesor(cedula_profesor,nombre,primer_apellido,telefono,email) values ('402510369','Isabella','Hernandez', 85895623,'isahernandez13@gmail.com');
insert into profesor(cedula_profesor, nombre, primer_apellido, telefono, email)
values ('705210486', 'Randall', 'Porras', 85897612, 'randallporras@gmail.com');
insert into profesor(cedula_profesor, nombre, primer_apellido, telefono, email)
values ('301240598', 'Fiorella', 'Perez', 88968532, 'fiorellap@gmail.com');
insert into profesor(cedula_profesor, nombre, primer_apellido, telefono, email)
values ('705620365', 'Sonia', 'Sanchez', 86595623, 'ssanchez@gmail.com');
insert into profesor(cedula_profesor, nombre, primer_apellido, telefono, email)
values ('201230258', 'Maria', 'Gomez', 22456589, 'mgomez23@gmail.com');
insert into profesor(cedula_profesor, nombre, primer_apellido, telefono, email)
values ('102580456', 'Cristina', 'Ramirez', 87895632, 'cristinaram12@gmail.com');

/*select * from profesor;*/

/*INSERCI�N DE DATOS CARRERA*/

insert into carrera(codigo_carrera, nombre, titulo)
values ('INGE500', 'Ingenieria en Sistemas', 'Licenciatura');
insert into carrera(codigo_carrera, nombre, titulo)
values ('CONTA700', 'Contabilidad', 'Licenciatura');
insert into carrera(codigo_carrera, nombre, titulo)
values ('ADMIN400', 'Administracion de empresas', 'Maestria');
insert into carrera(codigo_carrera, nombre, titulo)
values ('ENFE800', 'Enfermeria', 'Bachillerato');
insert into carrera(codigo_carrera, nombre, titulo)
values ('INGE300', 'Ingenieria electronica', 'Licenciatura');

/*select * from carrera;*/

/*INSERCI�N DE DATOS CURSO*/

insert into curso(codigo_curso, nombre, creditos, horas_semanales, carrera_codigo)
values ('EIF400', 'Programacion IV', 4, 6, 'INGE500');
insert into curso(codigo_curso, nombre, creditos, horas_semanales, carrera_codigo)
values ('CONT701', 'Contabilidad 1', 3, 5, 'CONTA700');
insert into curso(codigo_curso, nombre, creditos, horas_semanales, carrera_codigo)
values ('ADM402', 'Administracion 1', 4, 6, 'ADMIN400');
insert into curso(codigo_curso, nombre, creditos, horas_semanales, carrera_codigo)
values ('ENF805', 'Laboratorio 1', 4, 7, 'ENFE800');
insert into curso(codigo_curso, nombre, creditos, horas_semanales, carrera_codigo)
values ('ING301', 'Fundamentos', 4, 6, 'INGE300');

/*select * from curso;*/

/*INSERCI�N DE DATOS ALUMNO*/

insert into alumno(cedula_alumno, nombre, primer_apellido, telefono, email, fecha_nacimiento, carrera_codigo)
values ('502510369', 'Oscar', 'Mendez', 87952630, 'omendez12@gmail.com', '01/06/1998', 'INGE500');
insert into alumno(cedula_alumno, nombre, primer_apellido, telefono, email, fecha_nacimiento, carrera_codigo)
values ('201520896', 'Gianna', 'Del Campo', 87989623, 'gdelvalle56@gmail.com', '13/05/2000', 'CONTA700');
insert into alumno(cedula_alumno, nombre, primer_apellido, telefono, email, fecha_nacimiento, carrera_codigo)
values ('102560369', 'Alhanna', 'Gonzalez', 22000202, 'agonzalez13@gmail.com', '20/10/1995', 'ENFE800');
insert into alumno(cedula_alumno, nombre, primer_apellido, telefono, email, fecha_nacimiento, carrera_codigo)
values ('302130596', 'Jose', 'Castillo', 27650022, 'jcastillo52@gmail.com', '04/02/1985', 'ADMIN400');
insert into alumno(cedula_alumno, nombre, primer_apellido, telefono, email, fecha_nacimiento, carrera_codigo)
values ('603210596', 'Manuel', 'Masis', 84858689, 'manumasis23@gmail.com', '01/06/1980', 'INGE300');

/*select* from alumno;*/


/*INSERCI�N DE DATOS CICLO*/

insert into ciclo(annio, numero, fecha_incio, fecha_finalizacion)
values (2023, 'I Ciclo', '01/03/2023', '01/07/2023');
insert into ciclo(annio, numero, fecha_incio, fecha_finalizacion)
values (2023, 'II Ciclo', '01/08/2023', '01/12/2023');
insert into ciclo(annio, numero, fecha_incio, fecha_finalizacion)
values (2024, 'I Ciclo', '01/03/2024', '01/07/2024');
insert into ciclo(annio, numero, fecha_incio, fecha_finalizacion)
values (2024, 'II Ciclo', '01/03/2024', '01/07/2024');
insert into ciclo(annio, numero, fecha_incio, fecha_finalizacion)
values (2025, 'I Ciclo', '01/03/2025', '01/07/2025');
insert into ciclo(annio, numero, fecha_incio, fecha_finalizacion)
values (2025, 'II Ciclo', '01/08/2025', '01/12/2025');

/*select * from ciclo;*/


/*INSERCI�N DE DATOS GRUPO*/

insert into grupo(numero_grupo, horario, campos_restantes, capacidad_maxima, ciclo_annio, curso_codigo, profesor_cedula,
                  numero_ciclo)
values (1, 'martes-viernes', 10, 30, 2023, 'EIF400', '402510369', 'I Ciclo');
insert into grupo(numero_grupo, horario, campos_restantes, capacidad_maxima, ciclo_annio, curso_codigo, profesor_cedula,
                  numero_ciclo)
values (2, 'lunes-jueves', 8, 25, 2023, 'CONT701', '705210486', 'II Ciclo');
insert into grupo(numero_grupo, horario, campos_restantes, capacidad_maxima, ciclo_annio, curso_codigo, profesor_cedula,
                  numero_ciclo)
values (3, 'sabado', 2, 30, 2024, 'ADM402', '301240598', 'I Ciclo');
insert into grupo(numero_grupo, horario, campos_restantes, capacidad_maxima, ciclo_annio, curso_codigo, profesor_cedula,
                  numero_ciclo)
values (4, 'miercoles', 5, 15, 2024, 'ENF805', '705620365', 'II Ciclo');
insert into grupo(numero_grupo, horario, campos_restantes, capacidad_maxima, ciclo_annio, curso_codigo, profesor_cedula,
                  numero_ciclo)
values (5, 'sabado', 4, 15, 2025, 'ING301', '201230258', 'I Ciclo');

/*select * from grupo;*/


/*INSERCI�N DE DATOS NOTA*/

insert into nota_alumno(id_curso, nota, alumno_cedula)
values ('EIF400', 100, '502510369');
insert into nota_alumno(id_curso, nota, alumno_cedula)
values ('CONT701', 90, '201520896');
insert into nota_alumno(id_curso, nota, alumno_cedula)
values ('ADM402', 85, '102560369');
insert into nota_alumno(id_curso, nota, alumno_cedula)
values ('ENF805', 45, '302130596');
insert into nota_alumno(id_curso, nota, alumno_cedula)
values ('ING301', 95, '603210596');

/*select* from nota_alumno;*/

/*INSERCI�N DE DATOS CONSEJERO*/

insert into consejero(alumno_cedula, profesor_cedula)
values ('502510369', '402510369');
insert into consejero(alumno_cedula, profesor_cedula)
values ('201520896', '705210486');
insert into consejero(alumno_cedula, profesor_cedula)
values ('102560369', '301240598');

/*select* from consejero;*/

/*INSERCI�N DE DATOS PREMATRICULA*/

insert into prematricula(idpre, estado, alumno_cedula, grupo_num, grupo_ciclo)
values (1, 'pendiente', '502510369', 1, 2023);
insert into prematricula(idpre, estado, alumno_cedula, grupo_num, grupo_ciclo)
values (2, 'pendiente', '201520896', 2, 2023);
insert into prematricula(idpre, estado, alumno_cedula, grupo_num, grupo_ciclo)
values (3, 'pendiente', '102560369', 3, 2024);

select *
from prematricula;

/*INSERCI�N DE DATOS ANALISIS*/

insert into analisis(consejero_alumno, consejero_profesor, prematricula_id)
values ('502510369', '402510369', 1);
insert into analisis(consejero_alumno, consejero_profesor, prematricula_id)
values ('201520896', '705210486', 5);
insert into analisis(consejero_alumno, consejero_profesor, prematricula_id)
values ('102560369', '301240598', 6);

/*select* from analisis;*/

/*INSERCI�N DE DATOS MATRICULA*/

insert into matricula(prematric_id, alumno_cedula)
values (1, '502510369');
insert into matricula(prematric_id, alumno_cedula)
values (5, '201520896');
insert into matricula(prematric_id, alumno_cedula)
values (6, '102560369');

/*select * from matricula;*/






