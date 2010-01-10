/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ybacoby.skdframework.repository;

import java.lang.reflect.Field;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author cristovao
 */
public class StoredDomain {

    private final String driver;
    private final String url;
    private final String usuario;
    private final String senha;
    private transient boolean conectado = false;
    private Connection conexao;
    private Statement statement;

    public static StoredDomain getInstance(String driver, String url, String usuario, String senha) {
        return new StoredDomain(driver, url, usuario, senha);
    }
    private StoredDomain(String driver, String url, String usuario, String senha) {
        this.driver = driver;
        this.senha = senha;
        this.url = url;
        this.usuario = usuario;
    }

    public StoredDomain open() throws SQLException {
        conexao = DriverManager.getConnection(url, usuario, senha);
        statement = conexao.createStatement();
        this.conectado = true;
        return this;
    }

    public StoredDomain close() throws SQLException {
        statement.close();
        conexao.close();
        this.conectado = false;
        return this;
    }

    public boolean isOpen() {
        return conectado;
    }

    public StoredDomain stored(Entity entity) throws SQLException, IllegalArgumentException, IllegalAccessException {
        Sql sql = new Sql(entity);
        if (entity.getId() == -1) {
            statement.executeUpdate(sql.insert().construir());
        } else {
            statement.executeUpdate(sql.update().construir());
        }
        return this;
    }

    public StoredDomain delete(Entity entity) throws SQLException {
        Sql sql = new Sql(entity);
        if (!(entity.getId() == -1)) {
            statement.executeUpdate(sql.delete().construir());
        }
        return this;
    }

    public ArrayList<Entity> query(Class clasz, Sql sql) throws SQLException, InstantiationException, IllegalAccessException {
        ArrayList<Entity> entitys = null;
        entitys = new ArrayList<Entity>();
        ResultSet resultset = statement.executeQuery(sql.construir());

        while (resultset.next()) {
            Entity entity = null;

            entity = (Entity) clasz.newInstance();

            for (Field field : clasz.getDeclaredFields()) {
                field.setAccessible(true);
                if (field.getType().equals(String.class)) {
                    field.set(entity, resultset.getString(field.getName()));
                } else if (field.getType().equals(Integer.class)) {
                    field.set(entity, resultset.getInt(field.getName()));
                } else if (field.getType().equals(Float.class)) {
                    field.set(entity, resultset.getFloat(field.getName()));
                } else if (field.getType().equals(Double.class)) {
                    field.set(entity, resultset.getDouble(field.getName()));
                }
            }

            entity.setId(resultset.getInt(entity.id));
            entitys.add(entity);
        }
        return entitys;
    }
}
