ALTER TABLE `crmfinanceira`.`cliente` 
ADD COLUMN `logradouro` VARCHAR(60) NULL AFTER `tipo_beneficio_id`,
ADD COLUMN `numero` VARCHAR(4) NULL AFTER `logradouro`,
ADD COLUMN `bairro` VARCHAR(60) NULL AFTER `numero`,
ADD COLUMN `complemento` VARCHAR(60) NULL AFTER `bairro`,
ADD COLUMN `cep` VARCHAR(9) NULL AFTER `complemento`,
ADD COLUMN `cidade` VARCHAR(60) NULL AFTER `cep`,
ADD COLUMN `estado` VARCHAR(60) NULL AFTER `cidade`;