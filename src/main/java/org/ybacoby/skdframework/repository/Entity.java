/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ybacoby.skdframework.repository;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 *
 * @author cristovao
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Entity {
    String table() default "";
}
