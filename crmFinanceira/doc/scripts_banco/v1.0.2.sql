/* Observacoes no cliente e CPF para validacao*/
ALTER TABLE `crmfinanceira`.`cliente` 
CHANGE COLUMN `cpf` `cpf` VARCHAR(14) NOT NULL ,
CHANGE COLUMN `observacoes` `observacoes` TEXT NULL DEFAULT NULL ;
/* Observacoes no contato */
ALTER TABLE `crmfinanceira`.`contato` 
CHANGE COLUMN `observacoes` `observacoes` TEXT NULL DEFAULT NULL ;
