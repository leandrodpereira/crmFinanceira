ALTER TABLE `crmfinanceira`.`cliente` 
CHANGE COLUMN `cpf` `cpf` VARCHAR(14) NOT NULL ,
CHANGE COLUMN `observacoes` `observacoes` TEXT NULL DEFAULT NULL ;
