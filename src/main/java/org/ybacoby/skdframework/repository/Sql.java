/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ybacoby.skdframework.repository;

import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 *
 * @author Cristovao
 */
public abstract class Sql {

    private Persistence entity;
    protected Class clasz;
    private StringBuilder construtor;

    /**
     * Construtor inicial que recebe um objeto como entidade
     * @param entity Usado para criacao, alteracao, pesquisa e delecao.
     */
    public Sql(Persistence entity) {
        this.entity = entity;
        this.construtor = new StringBuilder();
        this.clasz = entity.getClass();
    }

    /**
     * Construtor inicial que recebe uma classe para
     * trabalhar retorno de consultas
     * @param clasz
     */
    public Sql(Class clasz) {
        this.clasz = clasz;
        this.construtor = new StringBuilder();
    }

    /**
     * Comando SELECT do banco de dados
     * @return
     */
    public Sql select() {
        this.construtor.append("SELECT ");
        int i = 0;
        int total = this.clasz.getDeclaredFields().length;
        for (Field field : this.clasz.getDeclaredFields()) {
            if (i < total) {
                this.construtor.append(field.getName().toLowerCase()).append(", ");
            }
            i++;
        }
        this.construtor.delete(this.construtor.length() - 2, this.construtor.length());

        this.construtor.append(" ").append("FROM ").append(this.getTableName());


        return this;
    }

    private String getTableName() {
        Entity entityAnnotation = (Entity)this.clasz.getAnnotation(Entity.class);

        if (entityAnnotation == null) {
            return this.clasz.getSimpleName().toLowerCase();
        }
        return entityAnnotation.table();

    }

    protected Sql insert() throws IllegalArgumentException, IllegalAccessException {
        this.construtor.append("INSERT INTO ").append(this.getTableName()).append(" (");

        int iteracao = 0;
        int total = this.clasz.getDeclaredFields().length;
        for (Field field : this.clasz.getDeclaredFields()) {
            if (iteracao < total - 1) {
                this.construtor.append(field.getName()).append(", ");
            } else {
                this.construtor.append(field.getName());
            }
            iteracao++;
        }

        iteracao = 0;
        this.construtor.append(") VALUES (");

        for (Field field : this.clasz.getDeclaredFields()) {
            field.setAccessible(true);
            if (iteracao < total - 1) {
                this.construtor.append("'").append(field.get(this.entity)).append("', ");
            } else {
                this.construtor.append("'").append(field.get(this.entity)).append("'");
            }
            iteracao++;
        }

        this.construtor.append(")");

        return this;
    }

    protected Sql update() throws IllegalArgumentException, IllegalAccessException {
        this.construtor.append("UPDATE ").append(this.getTableName()).append(" SET ");

        Integer iteracao = 0;
        int total = this.clasz.getDeclaredFields().length;
        for (Field field : this.clasz.getDeclaredFields()) {
            field.setAccessible(true);
            if (iteracao < total - 1) {
                this.construtor.append(field.getName()).append(" = '").append(field.get(this.entity)).append("', ");
            } else {
                this.construtor.append(field.getName()).append(" = '").append(field.get(this.entity)).append("'");
            }

            iteracao++;
        }

        return this;
    }

    public Sql equalsIds() {
        Integer iteracao = 0;
        Integer total = this.getIdsValues().size();
        for (String id : this.getIdsColumns()) {
            if (iteracao < total - 1) {
                this.equals(id, this.getIdsValues().get(iteracao)).and();
            } else {
                this.equals(id, this.getIdsValues().get(iteracao));
            }
            iteracao++;
        }

        return this;
    }

    private ArrayList<String> getIdsColumns() {
        ArrayList<String> ids = new ArrayList<String>();

        for (Field field : this.clasz.getFields()) {
            PrimaryKey id = field.getAnnotation(PrimaryKey.class);
            if (!(id == null)) {
//                if (id.column() == null) {
                    ids.add(field.getName().toLowerCase());
//                } else {
//                    ids.add(id.column());
//                }
            }
        }

        return ids;
    }

    private ArrayList<String> getIdsValues() {
        ArrayList<String> ids = new ArrayList<String>();

        for (Field field : this.clasz.getFields()) {
            field.setAccessible(true);
            PrimaryKey id = field.getAnnotation(PrimaryKey.class);
            if (!(id == null)) {
                ids.add(field.toString());
            }
        }

        return ids;
    }

    protected Sql delete() {
        this.construtor.append("DELETE FROM ").append(this.getTableName()).append(" ");
        Integer iteracao = 0;
        Integer total = this.getIdsValues().size();
        for (String id : this.getIdsColumns()) {
            if (iteracao < total - 1) {
                this.where().equals(id, this.getIdsValues().get(iteracao)).and();
            } else {
                this.where().equals(id, this.getIdsValues().get(iteracao));
            }
            iteracao++;
        }
        return this;
    }

    public Sql where() {
        this.construtor.append(" WHERE ");
        return this;
    }

    public Sql equals(String coluna, String valor) {
        this.construtor.append(coluna).append(" = ").append("'").append(valor).append("'");
        return this;
    }

    public Sql different(String coluna, String valor) {
        this.construtor.append(coluna).append(" != ").append("'").append(valor).append("'");
        return this;
    }

    public Sql like(String coluna, String valor) {
        this.construtor.append(coluna).append(" LIKE ").append("'").append(valor).append("'");
        return this;
    }

    public Sql ilike(String coluna, String valor) {
        this.construtor.append(coluna).append(" LIKE ").append("'%").append(valor).append("%'");
        return this;
    }

    public Sql between(String coluna, String valor, String valor2) {
        this.construtor.append(coluna).append(" BETWEEN ").append("'").append(valor).append("'").append(" AND ").append("'").append(valor).append("' ");
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

    @Override
    public String toString() {
        String retorno = this.construtor.toString();
        return retorno+";";
    }
}
