create database colegio_hibernate;
use colegio_hibernate;

create table profesores(
id int primary key auto_increment,
nombre varchar(50),
dni varchar(20)
);

create table alumnos(
id int primary key auto_increment,
nombre varchar(50),
apellidos varchar(80),
fecha_nacimiento date
);

create table asignaturas(
id int primary key auto_increment,
nombre varchar(50),
departamento varchar(30),
horas_semanales int unsigned,
id_profesor int,
FOREIGN KEY (id_profesor) references profesores (id)
);

create table alumno_asignatura(
id_alumno int,
id_asignatura int,
primary key(id_alumno, id_asignatura),
FOREIGN KEY (id_alumno)  references alumnos(id),
FOREIGN KEY (id_asignatura)  references asignaturas(id)
);