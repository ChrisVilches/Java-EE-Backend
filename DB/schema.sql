SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `recreu` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `recreu` ;

-- -----------------------------------------------------
-- Table `recreu`.`carrera`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `recreu`.`carrera` (
  `carrera_id` INT NOT NULL AUTO_INCREMENT,
  `nombre_carrera` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`carrera_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `recreu`.`usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `recreu`.`usuario` (
  `usuario_id` INT NOT NULL AUTO_INCREMENT,
  `disponibilidad` TINYINT(1) NOT NULL DEFAULT true,
  `correo` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_update` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `last_position_x` FLOAT NULL,
  `last_position_y` FLOAT NULL,
  `es_activo` TINYINT(1) NOT NULL DEFAULT true,
  `primer_nombre` VARCHAR(45) NOT NULL,
  `segundo_nombre` VARCHAR(45) NULL,
  `apellido_paterno` VARCHAR(45) NULL,
  `apellido_materno` VARCHAR(45) NULL,
  `intereses` TEXT NULL,
  `fecha_nacimiento` DATE NOT NULL,
  `sexo` TINYINT(1) NOT NULL DEFAULT true,
  `numero_telefono` VARCHAR(15) NULL,
  `url_facebook` VARCHAR(128) NULL,
  `url_instagram` VARCHAR(128) NULL,
  `url_twitter` VARCHAR(128) NULL,
  `carrera_id` INT NULL,
  `es_administrador` TINYINT(1) NOT NULL DEFAULT false,
  PRIMARY KEY (`usuario_id`),
  UNIQUE INDEX `correo_UNIQUE` (`correo` ASC),
  INDEX `fk_usuario_1_idx` (`carrera_id` ASC),
  CONSTRAINT `fk_usuario_1`
    FOREIGN KEY (`carrera_id`)
    REFERENCES `recreu`.`carrera` (`carrera_id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
ENGINE = InnoDB
PACK_KEYS = DEFAULT;


-- -----------------------------------------------------
-- Table `recreu`.`categoria`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `recreu`.`categoria` (
  `categoria_id` INT NOT NULL AUTO_INCREMENT,
  `nombre_categoria` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`categoria_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `recreu`.`tipo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `recreu`.`tipo` (
  `tipo_id` INT NOT NULL AUTO_INCREMENT,
  `categoria_id` INT NOT NULL,
  `tipo` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`tipo_id`),
  INDEX `fk_tipo_actividad_1_idx` (`categoria_id` ASC),
  CONSTRAINT `fk_tipo_actividad_1`
    FOREIGN KEY (`categoria_id`)
    REFERENCES `recreu`.`categoria` (`categoria_id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `recreu`.`actividad`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `recreu`.`actividad` (
  `actividad_id` INT NOT NULL AUTO_INCREMENT,
  `tipo_id` INT NOT NULL,
  `titulo_actividad` VARCHAR(50) NOT NULL,
  `cuerpo_actividad` VARCHAR(1028) NOT NULL,
  `requerimientos_actividad` VARCHAR(128) NULL,
  `ubicacion_actividad_x` FLOAT NULL,
  `ubicacion_actividad_y` FLOAT NULL,
  `fecha_inicio` DATETIME NOT NULL,
  `duracion_estimada` TIME NULL,
  `es_activo` TINYINT(1) NOT NULL DEFAULT true,
  `personas_maximas` INT NULL,
  PRIMARY KEY (`actividad_id`),
  INDEX `fk_actividad_1_idx` (`tipo_id` ASC),
  CONSTRAINT `fk_actividad_1`
    FOREIGN KEY (`tipo_id`)
    REFERENCES `recreu`.`tipo` (`tipo_id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `recreu`.`reporte`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `recreu`.`reporte` (
  `reporte_id` INT NOT NULL AUTO_INCREMENT,
  `usuario_reportado_id` INT NOT NULL,
  `usuario_reportador_id` INT NOT NULL,
  `actividad_id` INT NOT NULL,
  `administrador_id` INT NOT NULL,
  `motivo_reporte` VARCHAR(2056) NULL,
  `fecha_reporte` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `fue_revisado` TINYINT(1) NOT NULL,
  PRIMARY KEY (`reporte_id`),
  INDEX `fk_reporte_1_idx` (`actividad_id` ASC),
  INDEX `fk_reporte_2_idx` (`usuario_reportado_id` ASC),
  INDEX `fk_reporte_3_idx` (`usuario_reportador_id` ASC),
  INDEX `fk_reporte_4_idx` (`administrador_id` ASC),
  CONSTRAINT `fk_reporte_1`
    FOREIGN KEY (`actividad_id`)
    REFERENCES `recreu`.`actividad` (`actividad_id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `fk_reporte_2`
    FOREIGN KEY (`usuario_reportado_id`)
    REFERENCES `recreu`.`usuario` (`usuario_id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `fk_reporte_3`
    FOREIGN KEY (`usuario_reportador_id`)
    REFERENCES `recreu`.`usuario` (`usuario_id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `fk_reporte_4`
    FOREIGN KEY (`administrador_id`)
    REFERENCES `recreu`.`usuario` (`usuario_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `recreu`.`calificacion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `recreu`.`calificacion` (
  `calificacion_id` INT NOT NULL AUTO_INCREMENT,
  `usuario_calificado_id` INT NOT NULL,
  `usuario_calificador_id` INT NOT NULL,
  `actividad_id` INT NOT NULL,
  `detalle` VARCHAR(1024) NOT NULL,
  `fecha_calificacion` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`calificacion_id`),
  INDEX `fk_calificacion_1_idx` (`usuario_calificado_id` ASC),
  INDEX `fk_calificacion_2_idx` (`usuario_calificador_id` ASC),
  INDEX `fk_calificacion_3_idx` (`actividad_id` ASC),
  CONSTRAINT `fk_calificacion_1`
    FOREIGN KEY (`usuario_calificado_id`)
    REFERENCES `recreu`.`usuario` (`usuario_id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `fk_calificacion_2`
    FOREIGN KEY (`usuario_calificador_id`)
    REFERENCES `recreu`.`usuario` (`usuario_id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `fk_calificacion_3`
    FOREIGN KEY (`actividad_id`)
    REFERENCES `recreu`.`actividad` (`actividad_id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `recreu`.`comentario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `recreu`.`comentario` (
  `comentario_id` INT NOT NULL AUTO_INCREMENT,
  `usuario_id` INT NOT NULL,
  `actividad_id` INT NOT NULL,
  `cuerpo` VARCHAR(1024) NOT NULL,
  `fecha_comentario` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `es_activo` TINYINT(1) NOT NULL DEFAULT true,
  PRIMARY KEY (`comentario_id`),
  INDEX `fk_comentario_1_idx` (`usuario_id` ASC),
  INDEX `fk_comentario_2_idx` (`actividad_id` ASC),
  CONSTRAINT `fk_comentario_1`
    FOREIGN KEY (`usuario_id`)
    REFERENCES `recreu`.`usuario` (`usuario_id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `fk_comentario_2`
    FOREIGN KEY (`actividad_id`)
    REFERENCES `recreu`.`actividad` (`actividad_id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `recreu`.`ban`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `recreu`.`ban` (
  `ban_id` INT NOT NULL AUTO_INCREMENT,
  `usuario_id` INT NOT NULL,
  `actividad_id` INT NOT NULL,
  `motivo_ban` VARCHAR(128) NOT NULL,
  PRIMARY KEY (`ban_id`),
  INDEX `fk_Ban_1_idx` (`usuario_id` ASC),
  INDEX `fk_Ban_2_idx` (`actividad_id` ASC),
  CONSTRAINT `fk_Ban_1`
    FOREIGN KEY (`usuario_id`)
    REFERENCES `recreu`.`usuario` (`usuario_id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Ban_2`
    FOREIGN KEY (`actividad_id`)
    REFERENCES `recreu`.`actividad` (`actividad_id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `recreu`.`participacion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `recreu`.`participacion` (
  `participacion_id` INT NOT NULL AUTO_INCREMENT,
  `usuario_id` INT NOT NULL,
  `actividad_id` INT NOT NULL,
  `es_organizador` TINYINT(1) NOT NULL DEFAULT false,
  `es_activo` TINYINT(1) NOT NULL DEFAULT true,
  PRIMARY KEY (`participacion_id`),
  INDEX `fk_participacion_1_idx` (`usuario_id` ASC),
  INDEX `fk_participacion_2_idx` (`actividad_id` ASC),
  CONSTRAINT `fk_participacion_1`
    FOREIGN KEY (`usuario_id`)
    REFERENCES `recreu`.`usuario` (`usuario_id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `fk_participacion_2`
    FOREIGN KEY (`actividad_id`)
    REFERENCES `recreu`.`actividad` (`actividad_id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
