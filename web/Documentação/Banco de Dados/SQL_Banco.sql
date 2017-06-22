-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`Unidade`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Unidade` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `descricao` VARCHAR(45) NOT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '')
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Usuario` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `nome` VARCHAR(120) NOT NULL COMMENT '',
  `sobrenome` VARCHAR(45) NOT NULL COMMENT '',
  `login` VARCHAR(45) NOT NULL COMMENT '',
  `senha` VARCHAR(256) NOT NULL COMMENT '',
  `ativo` BIT NOT NULL COMMENT '',
  `foto` BLOB NOT NULL COMMENT '',
  `id_unidade` INT NOT NULL COMMENT '',
  UNIQUE INDEX `login_UNIQUE` (`login` ASC)  COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '',
  UNIQUE INDEX `id_UNIQUE` (`id` ASC)  COMMENT '',
  INDEX `fk_Usuario_Unidade1_idx` (`id_unidade` ASC)  COMMENT '',
  CONSTRAINT `fk_Usuario_Unidade1`
    FOREIGN KEY (`id_unidade`)
    REFERENCES `mydb`.`Unidade` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Funcao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Funcao` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `descricao` VARCHAR(45) NOT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '')
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Usuario_Funcao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Usuario_Funcao` (
  `id_usuario` INT NOT NULL COMMENT '',
  `id_funcao` INT NOT NULL COMMENT '',
  PRIMARY KEY (`id_usuario`, `id_funcao`)  COMMENT '',
  INDEX `fk_Usuario_has_Funcao_Funcao1_idx` (`id_funcao` ASC)  COMMENT '',
  INDEX `fk_Usuario_has_Funcao_Usuario_idx` (`id_usuario` ASC)  COMMENT '',
  CONSTRAINT `fk_Usuario_has_Funcao_Usuario`
    FOREIGN KEY (`id_usuario`)
    REFERENCES `mydb`.`Usuario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Usuario_has_Funcao_Funcao1`
    FOREIGN KEY (`id_funcao`)
    REFERENCES `mydb`.`Funcao` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Curso`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Curso` (
  `codigo` INT NOT NULL COMMENT '',
  `nome` VARCHAR(45) NOT NULL COMMENT '',
  PRIMARY KEY (`codigo`)  COMMENT '')
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Usuario_Curso`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Usuario_Curso` (
  `id_usuario` INT NOT NULL COMMENT '',
  `id_curso` INT NOT NULL COMMENT '',
  PRIMARY KEY (`id_usuario`, `id_curso`)  COMMENT '',
  INDEX `fk_Usuario_has_Curso_Curso1_idx` (`id_curso` ASC)  COMMENT '',
  INDEX `fk_Usuario_has_Curso_Usuario1_idx` (`id_usuario` ASC)  COMMENT '',
  CONSTRAINT `fk_Usuario_has_Curso_Usuario1`
    FOREIGN KEY (`id_usuario`)
    REFERENCES `mydb`.`Usuario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Usuario_has_Curso_Curso1`
    FOREIGN KEY (`id_curso`)
    REFERENCES `mydb`.`Curso` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Disciplina`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Disciplina` (
  `codigo` INT NOT NULL COMMENT '',
  `descricao` VARCHAR(45) NOT NULL COMMENT '',
  PRIMARY KEY (`codigo`)  COMMENT '')
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Arquivo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Arquivo` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `descricao` VARCHAR(200) NOT NULL COMMENT '',
  `arquivo` BLOB NOT NULL COMMENT '',
  `extensao` VARCHAR(45) NOT NULL COMMENT '',
  `nivel` ENUM('Básico', 'Intermediário', 'Avançado') NOT NULL COMMENT '',
  `tipo` ENUM('Pré-Aula', 'Pós-Aula', 'Durante', 'Leitura') NOT NULL COMMENT '',
  `visibilidade` ENUM('Público', 'Privado') NOT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '')
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Disciplina_Arquivo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Disciplina_Arquivo` (
  `id_disciplina` INT NOT NULL COMMENT '',
  `id_arquivo` INT NOT NULL COMMENT '',
  PRIMARY KEY (`id_disciplina`, `id_arquivo`)  COMMENT '',
  INDEX `fk_Disciplina_has_Arquivo_Arquivo1_idx` (`id_arquivo` ASC)  COMMENT '',
  INDEX `fk_Disciplina_has_Arquivo_Disciplina1_idx` (`id_disciplina` ASC)  COMMENT '',
  UNIQUE INDEX `Arquivo_id_UNIQUE` (`id_arquivo` ASC)  COMMENT '',
  CONSTRAINT `fk_Disciplina_has_Arquivo_Disciplina1`
    FOREIGN KEY (`id_disciplina`)
    REFERENCES `mydb`.`Disciplina` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Disciplina_has_Arquivo_Arquivo1`
    FOREIGN KEY (`id_arquivo`)
    REFERENCES `mydb`.`Arquivo` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Periodo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Periodo` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `codigo` VARCHAR(100) NOT NULL COMMENT '',
  `id_usuario` INT NOT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '',
  INDEX `fk_Periodo_Usuario1_idx` (`id_usuario` ASC)  COMMENT '',
  CONSTRAINT `fk_Periodo_Usuario1`
    FOREIGN KEY (`id_usuario`)
    REFERENCES `mydb`.`Usuario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Periodo_Disciplina`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Periodo_Disciplina` (
  `id_periodo` INT NOT NULL COMMENT '',
  `codigo_disciplina` INT NOT NULL COMMENT '',
  PRIMARY KEY (`id_periodo`, `codigo_disciplina`)  COMMENT '',
  INDEX `fk_Periodo_has_Disciplina_Disciplina1_idx` (`codigo_disciplina` ASC)  COMMENT '',
  INDEX `fk_Periodo_has_Disciplina_Periodo1_idx` (`id_periodo` ASC)  COMMENT '',
  CONSTRAINT `fk_Periodo_has_Disciplina_Periodo1`
    FOREIGN KEY (`id_periodo`)
    REFERENCES `mydb`.`Periodo` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Periodo_has_Disciplina_Disciplina1`
    FOREIGN KEY (`codigo_disciplina`)
    REFERENCES `mydb`.`Disciplina` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
