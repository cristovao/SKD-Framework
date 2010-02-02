/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ybacoby.skdframework.repository;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Utilizado para caso a classe que sera persistida
 * tenha um nome diferente do nome da tabela do banco
 * de dados, por nomenclatura do framework todas as
 * classes que sejam ausentes da annotation
 * Entity elas teram o seu nome de classe com todas
 * as letras em minusculo, para que assim sejam reconhecidas
 * no banco
 * @author cristovao
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Entity {
    String table();
}
