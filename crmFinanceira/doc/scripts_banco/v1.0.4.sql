ALTER TABLE `crmfinanceira`.`cliente` 
 
CHANGE COLUMN `fonte_pagadora` `fonte_pagadora` VARCHAR(255) NULL DEFAULT ' ',
CHANGE COLUMN `numero_beneficio` `matricula` VARCHAR(255) NULL DEFAULT '0' ,
CHANGE COLUMN `tipo_beneficio_id` `tipo_beneficio_id` BIGINT(20) NULL,
CHANGE COLUMN `senha_hiscom` `senha` VARCHAR(45) NULL DEFAULT '',

ADD COLUMN `emprestimo_pessoal` TINYINT(1) NULL DEFAULT '0' AFTER `senha`;






