/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ybacoby.skdframework.repository;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Aqui sera onde acontecera toda magica com o uso de SQL,
 * neste caso com este builder, podera assim fazer suas
 * consultas sql sem a necessidade de usar diretamente o
 * SQL, considerando o fato de tambem permitir as colunas
 * da tabela no banco de dados sejam automaticamente detectadas
 * sem a necessidade de dizer quais sao essas colunas numa consulta
 * select ou mesmo num insert e update.
 * @author Cristovao
 */
public abstract class Sql {

    private Object entity;
    protected Class clasz;
    private StringBuilder construtor;

    /**
     * Construtor inicial que recebe um objeto como entidade
     * @param entity Usado para criacao, alteracao, pesquisa e delecao.
     */
    public Sql(Object entity) {
        this.construtor = new StringBuilder();
        if (entity instanceof Class) {
            this.clasz = (Class) entity;
        } else {
            this.entity = entity;
            this.clasz = entity.getClass();
        }
    }

    /**
     * Comando SELECT do banco de dados
     */
    public Sql select() {
        this.construtor.append("SELECT ");
        int i = 0;
        int total = this.clasz.getDeclaredFields().length;
        for (Field field : this.clasz.getDeclaredFields()) {
            if (i < total) {
                this.construtor.append(field.getName()).append(", ");
            }
            i++;
        }
        this.construtor.delete(this.construtor.length() - 2, this.construtor.length());

        this.construtor.append(" ").append("FROM ").append(this.getTableName());


        return this;
    }

    private String getTableName() {
        Entity entityAnnotation = (Entity) this.clasz.getAnnotation(Entity.class);

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
            field.setAccessible(true);
            if (!(field.get(entity) == null)) {
                if (iteracao < total - 1) {
                    this.construtor.append(field.getName()).append(", ");
                } else {
                    this.construtor.append(field.getName());
                }
            }
            iteracao++;
        }

        iteracao = 0;
        this.construtor.append(") VALUES (");

        for (Field field : this.clasz.getDeclaredFields()) {
            field.setAccessible(true);
            if (!(field.get(entity) == null)) {
                if (iteracao < total - 1) {
                    this.construtor.append("'").append(field.get(this.entity)).append("', ");
                } else {
                    this.construtor.append("'").append(field.get(this.entity)).append("'");
                }
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
            if (!(field.get(entity) == null)) {
                if (iteracao < total - 1) {
                    this.construtor.append(field.getName()).append(" = '").append(field.get(this.entity)).append("', ");
                } else {
                    this.construtor.append(field.getName()).append(" = '").append(field.get(this.entity)).append("'");
                }
            }
            iteracao++;
        }

        return this;
    }

    /**
     * Verifica se o objeto tem CHAVES PRIMARIAS
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    public boolean haveWhereEqualsIds() throws IllegalArgumentException, IllegalAccessException {
        Integer total = this.getIdsValues().size();
        return total > 0 ? true : false;
    }

    /**
     * Verifica se tem chaves primarias, ja gerando uma
     * consulta com a clausula WHERE e com os principais
     * campos de chaves primarias com seus devidos conteudos
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    public Sql whereEqualsIds() throws IllegalArgumentException, IllegalAccessException {
        Integer iteracao = 0;
        Integer total = this.getIdsValues().size();
        if (total > 0) {
            this.where();
        }
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

    private ArrayList<String> getIdsColumns() throws IllegalArgumentException {
        ArrayList<String> ids = new ArrayList<String>();

        for (Field field : this.clasz.getDeclaredFields()) {
            PrimaryKey id = field.getAnnotation(PrimaryKey.class);
            field.setAccessible(true);
            try {
                if (!(id == null) && !(field.get(entity) == null)) {
                    ids.add(field.getName());
                }
            } catch (IllegalAccessException ex) {
                Logger.getLogger(Sql.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return ids;
    }

    private ArrayList<String> getIdsValues() throws IllegalArgumentException, IllegalAccessException {
        ArrayList<String> ids = new ArrayList<String>();

        for (Field field : this.clasz.getDeclaredFields()) {
            field.setAccessible(true);
            PrimaryKey id = field.getAnnotation(PrimaryKey.class);
            if (!(id == null) && !(field.get(entity) == null)) {
                ids.add(field.get(entity).toString());
            }
        }

        return ids;
    }

    protected Sql delete() throws IllegalArgumentException, IllegalAccessException {
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
        return retorno + ";";
    }
}
