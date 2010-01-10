/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ybacoby.skdframework.repository;

import java.lang.reflect.Field;

/**
 *
 * @author Fabio
 */
public final class Sql
{
    private Entity entity;
    private StringBuilder construtor;

    public Sql(Entity entity) {
        this.entity = entity;
        this.construtor = new StringBuilder();
    }

    public Sql select() {
        int i = 0;
        int total = this.entity.columnsId().length;
        for (String id : this.entity.columnsId()) {
            if (i < total-1) {
                this.construtor.append("SELECT ").append(id).append(", ");
            } else {
                this.construtor.append("SELECT ").append(id);
            }
            i++;
        }

        i = 0;
        total = this.entity.getClass().getDeclaredFields().length;
        for (Field field : this.entity.getClass().getDeclaredFields()) {
            if (i < total-1) {
                this.construtor.append(field.getName()).append(", ");
            } else {
                this.construtor.append(field.getName()).append(" ");
            }
            i++;
        }

        this.construtor.append("FROM ").append(entity.getTable())
                .append(" ");

        return this;
    }

    public Sql insert() throws IllegalArgumentException, IllegalAccessException {
        this.construtor.append("INSERT INTO ").append(this.entity.getTable()).append(" (");

        int iteracao = 0;
        int total = this.entity.getClass().getDeclaredFields().length;
        for (Field field : this.entity.getClass().getDeclaredFields()) {
            if (iteracao < total-1) {
                this.construtor.append(field.getName()).append(", ");
            } else {
                this.construtor.append(field.getName()).append(" ");
            }
            iteracao++;
        }

        iteracao = 0;
        this.construtor.append(") VALUES (");

        for (Field field : this.entity.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            if (iteracao < total-1) {
                this.construtor.append(" '").append(field.get(this.entity)).append("', ");
            } else {
                this.construtor.append(" '").append(field.get(this.entity)).append("' ");
            }
            iteracao++;
        }

        this.construtor.append(")");

        return this;
    }

    public Sql update() throws IllegalArgumentException, IllegalAccessException {
        this.construtor.append("UPDATE ").append(this.entity.getTable()).append(" SET ");

        Integer iteracao = 0;
        int total = this.entity.getClass().getDeclaredFields().length;
        for (Field field : this.entity.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            if (iteracao < total-1) {
                this.construtor.append(field.getName()).append(" = '")
                        .append(field.get(this.entity)).append("', ");
            } else {
                this.construtor.append(field.getName()).append(" = '")
                        .append(field.get(this.entity)).append("' ");
            }
            
            iteracao++;
        }

        this.where().igual(this.entity.id, this.entity.getId().toString());

        return this;
    }

    public Sql delete() {
        this.construtor.append("DELETE FROM ").append(this.entity.getTable())
                .append(" ");
        this.where().igual(this.entity.id, this.entity.getId().toString());
        return this;
    }

    public Sql where() {
        this.construtor.append("WHERE ");
        return this;
    }

    public Sql igual(String coluna, String valor) {
        this.construtor.append(coluna).append(" = ").append("'")
                .append(valor).append("' ");
        return this;
    }

    public Sql diferente(String coluna, String valor) {
        this.construtor.append(coluna).append(" != ").append("'")
                .append(valor).append("' ");
        return this;
    }

    public Sql like(String coluna, String valor) {
        this.construtor.append(coluna).append(" LIKE ").append("'%")
                .append(valor).append("%' ");
        return this;
    }

    public Sql ilike(String coluna, String valor) {
        this.construtor.append(coluna).append(" LIKE ").append("'")
                .append(valor).append("' ");
        return this;
    }

    public Sql between(String coluna, String valor, String valor2) {
        this.construtor.append(coluna).append(" BETWEEN ").append("'")
                .append(valor).append("'")
                .append(" AND ").append("'").append(valor).append("' ");
        return this;
    }

    public Sql and() {
        this.construtor.append(" AND ");
        return this;
    }

    public Sql or() {
        this.construtor.append(" OR ");
        return this;
    }

    public String construir() {
        return this.construtor.toString();
    }
}
