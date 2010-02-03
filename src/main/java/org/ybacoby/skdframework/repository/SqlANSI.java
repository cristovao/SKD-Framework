/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ybacoby.skdframework.repository;

/**
 *
 * @author cristovao
 */
public final class SqlANSI extends Sql {

    public SqlANSI(Persistence entity) {
        super(entity);
    }

    public SqlANSI(Class clasz) {
        super(clasz);
    }
}
