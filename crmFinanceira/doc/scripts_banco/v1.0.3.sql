ALTER TABLE `crmfinanceira`.`cliente` 

CHANGE COLUMN `fontePagadora` `fonte_pagadora` VARCHAR(255) NOT NULL ,
CHANGE COLUMN `numeroBeneficio` `numero_beneficio` VARCHAR(30) NOT NULL,

ADD COLUMN `banco` VARCHAR(45) NULL DEFAULT '' AFTER `estado`,

ADD COLUMN `numero_agencia` VARCHAR(10) NULL DEFAULT '' AFTER `banco`,
ADD COLUMN `numero_conta` VARCHAR(12) NULL DEFAULT '' AFTER `numero_agencia`,
ADD COLUMN `tipo_conta` VARCHAR(45) NULL DEFAULT '' AFTER `numero_conta`,
ADD COLUMN `telefone_recados` VARCHAR(45) DEFAULT '' NULL AFTER `tipo_conta`,
ADD COLUMN `senha_hiscom` VARCHAR(45) NULL DEFAULT '' AFTER `telefone_recados`;