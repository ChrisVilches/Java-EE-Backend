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

INSERT INTO `recreu`.`usuario` (`disponibilidad`, `correo`, `password`, `last_position_x`, `last_position_y`, `es_activo`, `primer_nombre`, `segundo_nombre`, `apellido_paterno`, `apellido_materno`, `intereses`, `fecha_nacimiento`, `sexo`, `numero_telefono`, `carrera_id`) VALUES (true, 'felo@usach.cl', 		'pass', '1', '2', true, 			'felipe', 'chris', 'vilches', 'cespedes', 'java, programar, etc', '1991-10-24', true, '123456', '1');
INSERT INTO `recreu`.`usuario` (`disponibilidad`, `correo`, `password`, `last_position_x`, `last_position_y`, `es_activo`, `primer_nombre`, `segundo_nombre`, `apellido_paterno`, `apellido_materno`, `intereses`, `fecha_nacimiento`, `sexo`, `numero_telefono`, `carrera_id`) VALUES (true, 'naty@usach.cl', 		'pass', '10', '20', true, 		'natalia', 'belen', 'perez', 'guerrero', 'caminar, dormir', '1991-05-23', true, '987654', '1');
INSERT INTO `recreu`.`usuario` (`disponibilidad`, `correo`, `password`, `last_position_x`, `last_position_y`, `es_activo`, `primer_nombre`, `segundo_nombre`, `apellido_paterno`, `apellido_materno`, `intereses`, `fecha_nacimiento`, `sexo`, `numero_telefono`, `carrera_id`) VALUES (true, 'gustavo@usach.cl', 		'pass', '11', '25', true, 		'gustavo', 'gaston', 'curifuta', 'curifuta', 'cocinar, ver TV', '1992-04-12', true, '156438', '2');
INSERT INTO `recreu`.`usuario` (`disponibilidad`, `correo`, `password`, `last_position_x`, `last_position_y`, `es_activo`, `primer_nombre`, `segundo_nombre`, `apellido_paterno`, `apellido_materno`, `intereses`, `fecha_nacimiento`, `sexo`, `numero_telefono`, `carrera_id`) VALUES (true, 'pipe@usach.cl', 		'pass', '100', '25', true, 		'felipe', 'ignacio', 'jara', 'ramirez', 'andar en bicicleta, escribir, hacer ejercicio', '1991-01-29', true, '159753', '2');
INSERT INTO `recreu`.`usuario` (`disponibilidad`, `correo`, `password`, `last_position_x`, `last_position_y`, `es_activo`, `primer_nombre`, `segundo_nombre`, `apellido_paterno`, `apellido_materno`, `intereses`, `fecha_nacimiento`, `sexo`, `numero_telefono`, `carrera_id`) VALUES (true, 'jenny@usach.cl', 		'pass', '125', '250', true, 		'jennifer', 'andrea', 'venegas', 'rannou', 'c++, pascal, python', '1992-07-23', true, '456789123', '3');
INSERT INTO `recreu`.`usuario` (`disponibilidad`, `correo`, `password`, `last_position_x`, `last_position_y`, `es_activo`, `primer_nombre`, `segundo_nombre`, `apellido_paterno`, `apellido_materno`, `intereses`, `fecha_nacimiento`, `sexo`, `numero_telefono`, `carrera_id`) VALUES (true, 'christopher@usach.cl', 	'pass', '12', '202', true, 		'christopher', 'benjamin', 'ovalle', 'diaz', 'angular, node.js, meteor, ruby on rails', '1992-01-10', true, '7537531', '4');
INSERT INTO `recreu`.`usuario` (`disponibilidad`, `correo`, `password`, `last_position_x`, `last_position_y`, `es_activo`, `primer_nombre`, `segundo_nombre`, `apellido_paterno`, `apellido_materno`, `intereses`, `fecha_nacimiento`, `sexo`, `numero_telefono`, `carrera_id`) VALUES (true, 'fernando@usach.cl', 	'pass', '152', '2200', true, 	'fernando', 'andres', 'rannou', 'iturbe', 'centos, fedora, ubuntu', '1968-05-24', true, '9638532', '4');
