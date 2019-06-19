CREATE DATABASE registro_homicidas;
USE registro_homicidas;

CREATE TABLE homicidas(
id int primary key auto_increment,
apodo varchar(30) not null,
arma varchar(30) not null,
asesino_serie boolean not null default false,
annos_carcel int
);

CREATE TABLE victimas(
id int primary key auto_increment,
nombre varchar(30) not null,
genero_masculino boolean not null,
fecha_defuncion date not null,
causa_muerte varchar(100),
id_homicida int,
FOREIGN KEY (id_homicida) REFERENCES homicidas(id)
);

INSERT INTO `registro_homicidas`.`victimas` (`nombre`, `genero_masculino`, `fecha_defuncion`, `causa_muerte`) VALUES ('Fernando', 'true', '2019-01-21', 'acceso datos');
INSERT INTO `registro_homicidas`.`victimas` (`nombre`, `genero_masculino`, `fecha_defuncion`, `causa_muerte`) VALUES ('Maria', 'false', '2018-05-06', 'cuchillo');
INSERT INTO `registro_homicidas`.`victimas` (`nombre`, `genero_masculino`, `fecha_defuncion`, `causa_muerte`) VALUES ('Jorge', 'true', '2019-03-03', 'pistola');
INSERT INTO `registro_homicidas`.`victimas` (`nombre`, `genero_masculino`, `fecha_defuncion`, `causa_muerte`) VALUES ('Laura', 'false', '2017-03-07', 'atropello');

INSERT INTO `registro_homicidas`.`homicidas` (`apodo`, `arma`, `asesino_serie`, `annos_carcel`) VALUES ('el coleta', 'navaja', 'false', '5');
INSERT INTO `registro_homicidas`.`homicidas` (`apodo`, `arma`, `asesino_serie`, `annos_carcel`) VALUES ('paquito', 'fusil', 'true', '1');
INSERT INTO `registro_homicidas`.`homicidas` (`apodo`, `arma`, `asesino_serie`, `annos_carcel`) VALUES ('el Gorbe', 'machete', 'true', '0');
INSERT INTO `registro_homicidas`.`homicidas` (`apodo`, `arma`, `asesino_serie`, `annos_carcel`) VALUES ('el pistolas', 'pistola', 'false', '21');
