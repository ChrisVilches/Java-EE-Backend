--
--	Carrera
--
--

INSERT INTO `recreu`.`carrera` (`nombre_carrera`) VALUES ('Ingenieria civil en informatica');
INSERT INTO `recreu`.`carrera` (`carrera_id`, `nombre_carrera`) VALUES ('', 'Ingenieria ejecucion en informatica');
INSERT INTO `recreu`.`carrera` (`carrera_id`, `nombre_carrera`) VALUES ('', 'Ingeneria civil en obras civiles');
INSERT INTO `recreu`.`carrera` (`carrera_id`, `nombre_carrera`) VALUES ('', 'Pedagogia en Ingles');
INSERT INTO `recreu`.`carrera` (`carrera_id`, `nombre_carrera`) VALUES ('', 'Pedagogia en matematicas');
INSERT INTO `recreu`.`carrera` (`carrera_id`, `nombre_carrera`) VALUES ('', 'Periodismo');
INSERT INTO `recreu`.`carrera` (`carrera_id`, `nombre_carrera`) VALUES ('', 'Arquitectura');

--
--	Usuario
--
--

INSERT INTO `recreu`.`usuario` (`disponibilidad`, `correo`, `password`, `last_position_x`, `last_position_y`, `es_activo`, `primer_nombre`, `segundo_nombre`, `apellido_paterno`, `apellido_materno`, `intereses`, `fecha_nacimiento`, `sexo`, `numero_telefono`, `carrera_id`) VALUES (true, 'felo', 		'pass', '1', '2', true, 			'felipe', 'chris', 'vilches', 'cespedes', 'java, programar, etc', '1991-10-24', true, '123456', '1');
INSERT INTO `recreu`.`usuario` (`disponibilidad`, `correo`, `password`, `last_position_x`, `last_position_y`, `es_activo`, `primer_nombre`, `segundo_nombre`, `apellido_paterno`, `apellido_materno`, `intereses`, `fecha_nacimiento`, `sexo`, `numero_telefono`, `carrera_id`) VALUES (true, 'naty', 		'pass', '10', '20', true, 		'natalia', 'belen', 'perez', 'guerrero', 'caminar, dormir', '1991-05-23', true, '987654', '1');
INSERT INTO `recreu`.`usuario` (`disponibilidad`, `correo`, `password`, `last_position_x`, `last_position_y`, `es_activo`, `primer_nombre`, `segundo_nombre`, `apellido_paterno`, `apellido_materno`, `intereses`, `fecha_nacimiento`, `sexo`, `numero_telefono`, `carrera_id`) VALUES (true, 'gustavo', 		'pass', '11', '25', true, 		'gustavo', 'gaston', 'curifuta', 'curifuta', 'cocinar, ver TV', '1992-04-12', true, '156438', '2');
INSERT INTO `recreu`.`usuario` (`disponibilidad`, `correo`, `password`, `last_position_x`, `last_position_y`, `es_activo`, `primer_nombre`, `segundo_nombre`, `apellido_paterno`, `apellido_materno`, `intereses`, `fecha_nacimiento`, `sexo`, `numero_telefono`, `carrera_id`) VALUES (true, 'pipe', 		'pass', '100', '25', true, 		'felipe', 'ignacio', 'jara', 'ramirez', 'andar en bicicleta, escribir, hacer ejercicio', '1991-01-29', true, '159753', '2');
INSERT INTO `recreu`.`usuario` (`disponibilidad`, `correo`, `password`, `last_position_x`, `last_position_y`, `es_activo`, `primer_nombre`, `segundo_nombre`, `apellido_paterno`, `apellido_materno`, `intereses`, `fecha_nacimiento`, `sexo`, `numero_telefono`, `carrera_id`) VALUES (true, 'jenny', 		'pass', '125', '250', true, 		'jennifer', 'andrea', 'venegas', 'rannou', 'c++, pascal, python', '1992-07-23', true, '456789123', '3');
INSERT INTO `recreu`.`usuario` (`disponibilidad`, `correo`, `password`, `last_position_x`, `last_position_y`, `es_activo`, `primer_nombre`, `segundo_nombre`, `apellido_paterno`, `apellido_materno`, `intereses`, `fecha_nacimiento`, `sexo`, `numero_telefono`, `carrera_id`) VALUES (true, 'christopher', 	'pass', '12', '202', true, 		'christopher', 'benjamin', 'ovalle', 'diaz', 'angular, node.js, meteor, ruby on rails', '1992-01-10', true, '7537531', '4');
INSERT INTO `recreu`.`usuario` (`disponibilidad`, `correo`, `password`, `last_position_x`, `last_position_y`, `es_activo`, `primer_nombre`, `segundo_nombre`, `apellido_paterno`, `apellido_materno`, `intereses`, `fecha_nacimiento`, `sexo`, `numero_telefono`, `carrera_id`) VALUES (true, 'fernando', 	'pass', '152', '2200', true, 	'fernando', 'andres', 'rannou', 'iturbe', 'centos, fedora, ubuntu', '1968-05-24', true, '9638532', '4');




--
--	Categoria
--
--

INSERT INTO `recreu`.`categoria` (`categoria_id`, `nombre_categoria`) VALUES (1, 'Deporte');
INSERT INTO `recreu`.`categoria` (`categoria_id`, `nombre_categoria`) VALUES (2, 'Arte');
INSERT INTO `recreu`.`categoria` (`categoria_id`, `nombre_categoria`) VALUES (3, 'Cine');
INSERT INTO `recreu`.`categoria` (`categoria_id`, `nombre_categoria`) VALUES (4, 'Musica');
INSERT INTO `recreu`.`categoria` (`categoria_id`, `nombre_categoria`) VALUES (5, 'Teatro');
INSERT INTO `recreu`.`categoria` (`categoria_id`, `nombre_categoria`) VALUES (6, 'Danza');




--
--	Tipo
--
--


INSERT INTO `recreu`.`tipo` (`tipo_id`, `categoria_id`,`tipo`) VALUES (1, 1,'Atletismo');
INSERT INTO `recreu`.`tipo` (`tipo_id`, `categoria_id`,`tipo`) VALUES (2, 1,'Basquetbol');
INSERT INTO `recreu`.`tipo` (`tipo_id`, `categoria_id`,`tipo`) VALUES (3, 1,'Balonmano');
INSERT INTO `recreu`.`tipo` (`tipo_id`, `categoria_id`,`tipo`) VALUES (4, 1,'Futbol');
INSERT INTO `recreu`.`tipo` (`tipo_id`, `categoria_id`,`tipo`) VALUES (5, 1,'Futsal');
INSERT INTO `recreu`.`tipo` (`tipo_id`, `categoria_id`,`tipo`) VALUES (6, 1,'Natacion');
INSERT INTO `recreu`.`tipo` (`tipo_id`, `categoria_id`,`tipo`) VALUES (7, 1,'Pilates');
INSERT INTO `recreu`.`tipo` (`tipo_id`, `categoria_id`,`tipo`) VALUES (8, 1,'Judo');
INSERT INTO `recreu`.`tipo` (`tipo_id`, `categoria_id`,`tipo`) VALUES (9, 1,'Karate deportivo');
INSERT INTO `recreu`.`tipo` (`tipo_id`, `categoria_id`,`tipo`) VALUES (10, 1,'Running');
INSERT INTO `recreu`.`tipo` (`tipo_id`, `categoria_id`,`tipo`) VALUES (11, 1,'Tenis');
INSERT INTO `recreu`.`tipo` (`tipo_id`, `categoria_id`,`tipo`) VALUES (12, 1,'Voleibol');
INSERT INTO `recreu`.`tipo` (`tipo_id`, `categoria_id`,`tipo`) VALUES (13, 1,'Tenis de mesa');
INSERT INTO `recreu`.`tipo` (`tipo_id`, `categoria_id`,`tipo`) VALUES (14, 1,'Escalada Boulder');
INSERT INTO `recreu`.`tipo` (`tipo_id`, `categoria_id`,`tipo`) VALUES (15, 1,'Escalada deportiva');

INSERT INTO `recreu`.`tipo` (`tipo_id`, `categoria_id`,`tipo`) VALUES (16, 2,'Pintura');
INSERT INTO `recreu`.`tipo` (`tipo_id`, `categoria_id`,`tipo`) VALUES (17, 2,'Grabado');
INSERT INTO `recreu`.`tipo` (`tipo_id`, `categoria_id`,`tipo`) VALUES (18, 4,'Guitarra funcional');
INSERT INTO `recreu`.`tipo` (`tipo_id`, `categoria_id`,`tipo`) VALUES (19, 2,'Escultura');
INSERT INTO `recreu`.`tipo` (`tipo_id`, `categoria_id`,`tipo`) VALUES (20, 4,'Guitarra clásica');
INSERT INTO `recreu`.`tipo` (`tipo_id`, `categoria_id`,`tipo`) VALUES (21, 5,'Teatro');
INSERT INTO `recreu`.`tipo` (`tipo_id`, `categoria_id`,`tipo`) VALUES (22, 2,'Cerámica');
INSERT INTO `recreu`.`tipo` (`tipo_id`, `categoria_id`,`tipo`) VALUES (23, 5,'Expresión corporal');
INSERT INTO `recreu`.`tipo` (`tipo_id`, `categoria_id`,`tipo`) VALUES (24, 4,'Impostación de la voz cantada');
INSERT INTO `recreu`.`tipo` (`tipo_id`, `categoria_id`,`tipo`) VALUES (25, 4,'Iniciación al canto coral');
INSERT INTO `recreu`.`tipo` (`tipo_id`, `categoria_id`,`tipo`) VALUES (26, 2,'Dibujo básico');
INSERT INTO `recreu`.`tipo` (`tipo_id`, `categoria_id`,`tipo`) VALUES (27, 2,'Redaccion');
INSERT INTO `recreu`.`tipo` (`tipo_id`, `categoria_id`,`tipo`) VALUES (28, 3,'Cine siempre cine');
INSERT INTO `recreu`.`tipo` (`tipo_id`, `categoria_id`,`tipo`) VALUES (29, 6,'Bailes populares');
INSERT INTO `recreu`.`tipo` (`tipo_id`, `categoria_id`,`tipo`) VALUES (30, 2,'Batik');
INSERT INTO `recreu`.`tipo` (`tipo_id`, `categoria_id`,`tipo`) VALUES (31, 4,'Música andina');
INSERT INTO `recreu`.`tipo` (`tipo_id`, `categoria_id`,`tipo`) VALUES (32, 2,'Fotorgrafia alternativa');
INSERT INTO `recreu`.`tipo` (`tipo_id`, `categoria_id`,`tipo`) VALUES (33, 6,'Ballroom');
INSERT INTO `recreu`.`tipo` (`tipo_id`, `categoria_id`,`tipo`) VALUES (34, 6,'Danza flamenca');
INSERT INTO `recreu`.`tipo` (`tipo_id`, `categoria_id`,`tipo`) VALUES (35, 4,'Musica de violeta parra');
INSERT INTO `recreu`.`tipo` (`tipo_id`, `categoria_id`,`tipo`) VALUES (36, 3,'Apreciación del cine de terror');
INSERT INTO `recreu`.`tipo` (`tipo_id`, `categoria_id`,`tipo`) VALUES (37, 2,'Arte y reciclaje');
INSERT INTO `recreu`.`tipo` (`tipo_id`, `categoria_id`,`tipo`) VALUES (38, 4,'Agrupaciones populares');
INSERT INTO `recreu`.`tipo` (`tipo_id`, `categoria_id`,`tipo`) VALUES (39, 5,'Dramaturgia corporal');
INSERT INTO `recreu`.`tipo` (`tipo_id`, `categoria_id`,`tipo`) VALUES (40, 4,'Curso inicial de violin');
INSERT INTO `recreu`.`tipo` (`tipo_id`, `categoria_id`,`tipo`) VALUES (41, 6,'Tango');



--
--	Actividad
--
--



INSERT INTO `recreu`.`actividad` (`actividad_id`, `tipo_id`, `titulo_actividad`, `cuerpo_actividad`, `requerimientos_actividad`, `ubicacion_actividad_x`, `ubicacion_actividad_y`, `fecha_inicio`, `duracion_estimada`, `es_activo`)  VALUES ('1', '1', 'Titulo actividad 1', 'Cuerpo actividad 1', 'Requerimientos actividad 1', '8', '50', '2016-05-02 05:10:07', '02:00:10', true);
INSERT INTO `recreu`.`actividad` (`actividad_id`, `tipo_id`, `titulo_actividad`, `cuerpo_actividad`, `requerimientos_actividad`, `ubicacion_actividad_x`, `ubicacion_actividad_y`, `fecha_inicio`, `duracion_estimada`, `es_activo`)  VALUES ('2', '7', 'Titulo actividad 2', 'Cuerpo actividad 2', 'Requerimientos actividad 2', '80', '50', '2016-07-02 05:10:07', '02:00:10', true);
INSERT INTO `recreu`.`actividad` (`actividad_id`, `tipo_id`, `titulo_actividad`, `cuerpo_actividad`, `requerimientos_actividad`, `ubicacion_actividad_x`, `ubicacion_actividad_y`, `fecha_inicio`, `duracion_estimada`, `es_activo`)  VALUES ('3', '12', 'Titulo actividad 3', 'Cuerpo actividad 3', 'Requerimientos actividad 3', '85', '50', '2016-05-02 05:10:07', '02:00:10', true);
INSERT INTO `recreu`.`actividad` (`actividad_id`, `tipo_id`, `titulo_actividad`, `cuerpo_actividad`, `requerimientos_actividad`, `ubicacion_actividad_x`, `ubicacion_actividad_y`, `fecha_inicio`, `duracion_estimada`, `es_activo`)  VALUES ('4', '8', 'Titulo actividad 4', 'Cuerpo actividad 4', 'Requerimientos actividad 4', '8', '50', '2016-03-02 05:10:07', '02:00:10', true);
INSERT INTO `recreu`.`actividad` (`actividad_id`, `tipo_id`, `titulo_actividad`, `cuerpo_actividad`, `requerimientos_actividad`, `ubicacion_actividad_x`, `ubicacion_actividad_y`, `fecha_inicio`, `duracion_estimada`, `es_activo`)  VALUES ('5', '13', 'Titulo actividad 5', 'Cuerpo actividad 5', 'Requerimientos actividad 5', '58', '560', '2016-05-02 05:10:07', '02:00:10', true);
INSERT INTO `recreu`.`actividad` (`actividad_id`, `tipo_id`, `titulo_actividad`, `cuerpo_actividad`, `requerimientos_actividad`, `ubicacion_actividad_x`, `ubicacion_actividad_y`, `fecha_inicio`, `duracion_estimada`, `es_activo`)  VALUES ('6', '20', 'Titulo actividad 6', 'Cuerpo actividad 6', 'Requerimientos actividad 6', '86', '50', '2016-02-02 05:10:07', '02:00:10', true);
INSERT INTO `recreu`.`actividad` (`actividad_id`, `tipo_id`, `titulo_actividad`, `cuerpo_actividad`, `requerimientos_actividad`, `ubicacion_actividad_x`, `ubicacion_actividad_y`, `fecha_inicio`, `duracion_estimada`, `es_activo`)  VALUES ('7', '35', 'Titulo actividad 7', 'Cuerpo actividad 7', 'Requerimientos actividad 7', '8', '508', '2016-05-02 05:10:07', '02:00:10', true);
INSERT INTO `recreu`.`actividad` (`actividad_id`, `tipo_id`, `titulo_actividad`, `cuerpo_actividad`, `requerimientos_actividad`, `ubicacion_actividad_x`, `ubicacion_actividad_y`, `fecha_inicio`, `duracion_estimada`, `es_activo`)  VALUES ('8', '2', 'Titulo actividad 8', 'Cuerpo actividad 8', 'Requerimientos actividad 8', '8', '50', '2016-05-02 05:10:07', '02:00:10', true);
INSERT INTO `recreu`.`actividad` (`actividad_id`, `tipo_id`, `titulo_actividad`, `cuerpo_actividad`, `requerimientos_actividad`, `ubicacion_actividad_x`, `ubicacion_actividad_y`, `fecha_inicio`, `duracion_estimada`, `es_activo`)  VALUES ('9', '2', 'Titulo actividad 9', 'Cuerpo actividad 9', 'Requerimientos actividad 9', '68', '5850', '2016-01-02 05:10:07', '02:00:10', true);
INSERT INTO `recreu`.`actividad` (`actividad_id`, `tipo_id`, `titulo_actividad`, `cuerpo_actividad`, `requerimientos_actividad`, `ubicacion_actividad_x`, `ubicacion_actividad_y`, `fecha_inicio`, `duracion_estimada`, `es_activo`)  VALUES ('10', '3', 'Titulo actividad 10', 'Cuerpo actividad 10', 'Requerimientos actividad 10', '58', '50', '2016-05-02 05:10:07', '02:00:10', true);
INSERT INTO `recreu`.`actividad` (`actividad_id`, `tipo_id`, `titulo_actividad`, `cuerpo_actividad`, `requerimientos_actividad`, `ubicacion_actividad_x`, `ubicacion_actividad_y`, `fecha_inicio`, `duracion_estimada`, `es_activo`)  VALUES ('11', '12', 'Titulo actividad 11', 'Cuerpo actividad 11', 'Requerimientos actividad 11', '86', '550', '2016-10-02 05:10:07', '02:00:10', true);
INSERT INTO `recreu`.`actividad` (`actividad_id`, `tipo_id`, `titulo_actividad`, `cuerpo_actividad`, `requerimientos_actividad`, `ubicacion_actividad_x`, `ubicacion_actividad_y`, `fecha_inicio`, `duracion_estimada`, `es_activo`)  VALUES ('12', '35', 'Titulo actividad 12', 'Cuerpo actividad 12', 'Requerimientos actividad 12', '8', '520', '2016-01-02 05:10:07', '02:00:10', true);
INSERT INTO `recreu`.`actividad` (`actividad_id`, `tipo_id`, `titulo_actividad`, `cuerpo_actividad`, `requerimientos_actividad`, `ubicacion_actividad_x`, `ubicacion_actividad_y`, `fecha_inicio`, `duracion_estimada`, `es_activo`)  VALUES ('13', '40', 'Titulo actividad 13', 'Cuerpo actividad 13', 'Requerimientos actividad 13', '318', '520', '2016-05-02 05:10:07', '02:00:10', true);
INSERT INTO `recreu`.`actividad` (`actividad_id`, `tipo_id`, `titulo_actividad`, `cuerpo_actividad`, `requerimientos_actividad`, `ubicacion_actividad_x`, `ubicacion_actividad_y`, `fecha_inicio`, `duracion_estimada`, `es_activo`)  VALUES ('14', '20', 'Titulo actividad 14', 'Cuerpo actividad 14', 'Requerimientos actividad 14', '28', '50', '2016-05-02 05:10:07', '02:00:10', true);
INSERT INTO `recreu`.`actividad` (`actividad_id`, `tipo_id`, `titulo_actividad`, `cuerpo_actividad`, `requerimientos_actividad`, `ubicacion_actividad_x`, `ubicacion_actividad_y`, `fecha_inicio`, `duracion_estimada`, `es_activo`)  VALUES ('15', '2', 'Titulo actividad 15', 'Cuerpo actividad 15', 'Requerimientos actividad 15', '83', '250', '2016-05-02 05:10:07', '02:00:10', true);
INSERT INTO `recreu`.`actividad` (`actividad_id`, `tipo_id`, `titulo_actividad`, `cuerpo_actividad`, `requerimientos_actividad`, `ubicacion_actividad_x`, `ubicacion_actividad_y`, `fecha_inicio`, `duracion_estimada`, `es_activo`)  VALUES ('16', '1', 'Titulo actividad 16', 'Cuerpo actividad 16', 'Requerimientos actividad 16', '8', '510', '2016-05-02 05:10:07', '02:00:10', true);
INSERT INTO `recreu`.`actividad` (`actividad_id`, `tipo_id`, `titulo_actividad`, `cuerpo_actividad`, `requerimientos_actividad`, `ubicacion_actividad_x`, `ubicacion_actividad_y`, `fecha_inicio`, `duracion_estimada`, `es_activo`)  VALUES ('17', '3', 'Titulo actividad 17', 'Cuerpo actividad 17', 'Requerimientos actividad 17', '28', '50', '2016-05-02 05:10:07', '02:00:10', true);
INSERT INTO `recreu`.`actividad` (`actividad_id`, `tipo_id`, `titulo_actividad`, `cuerpo_actividad`, `requerimientos_actividad`, `ubicacion_actividad_x`, `ubicacion_actividad_y`, `fecha_inicio`, `duracion_estimada`, `es_activo`)  VALUES ('18', '7', 'Titulo actividad 18', 'Cuerpo actividad 18', 'Requerimientos actividad 18', '83', '50', '2016-05-02 05:10:07', '02:00:10', true);
INSERT INTO `recreu`.`actividad` (`actividad_id`, `tipo_id`, `titulo_actividad`, `cuerpo_actividad`, `requerimientos_actividad`, `ubicacion_actividad_x`, `ubicacion_actividad_y`, `fecha_inicio`, `duracion_estimada`, `es_activo`)  VALUES ('19', '12', 'Titulo actividad 19', 'Cuerpo actividad 19', 'Requerimientos actividad 19', '8', '520', '2016-05-02 05:10:07', '02:00:10', true);
INSERT INTO `recreu`.`actividad` (`actividad_id`, `tipo_id`, `titulo_actividad`, `cuerpo_actividad`, `requerimientos_actividad`, `ubicacion_actividad_x`, `ubicacion_actividad_y`, `fecha_inicio`, `duracion_estimada`, `es_activo`)  VALUES ('20', '8', 'Titulo actividad 20', 'Cuerpo actividad 20', 'Requerimientos actividad 20', '82', '510', '2016-05-02 05:10:07', '02:00:10', true);
INSERT INTO `recreu`.`actividad` (`actividad_id`, `tipo_id`, `titulo_actividad`, `cuerpo_actividad`, `requerimientos_actividad`, `ubicacion_actividad_x`, `ubicacion_actividad_y`, `fecha_inicio`, `duracion_estimada`, `es_activo`)  VALUES ('21', '18', 'Titulo actividad 21', 'Cuerpo actividad 21', 'Requerimientos actividad 21', '83', '50', '2016-05-02 05:10:07', '02:00:10', true);
INSERT INTO `recreu`.`actividad` (`actividad_id`, `tipo_id`, `titulo_actividad`, `cuerpo_actividad`, `requerimientos_actividad`, `ubicacion_actividad_x`, `ubicacion_actividad_y`, `fecha_inicio`, `duracion_estimada`, `es_activo`)  VALUES ('22', '19', 'Titulo actividad 22', 'Cuerpo actividad 22', 'Requerimientos actividad 22', '18', '502', '2016-05-02 05:10:07', '02:00:10', true);








