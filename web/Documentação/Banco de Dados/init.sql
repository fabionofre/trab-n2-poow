/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Onofre
 * Created: 21/06/2017
 */

use mydb;

alter table usuario drop column foto;
insert into unidade (id, descricao) values (1, "UNINORTE");
insert into unidade (id, descricao) values (2, "IESACRE");
insert into curso (codigo, nome) values (1, "Sistemas de Informação");
insert into curso (codigo, nome) values (2, "Redes");
insert into funcao (id, descricao) values (1, "Aluno");
insert into funcao (id, descricao) values (2, "Professor");


