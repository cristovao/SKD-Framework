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
import java.util.Currency;
import java.util.Locale;
import org.ybacoby.skdframework.Cep;
import org.ybacoby.skdframework.Cnpj;
import org.ybacoby.skdframework.Date;
import org.ybacoby.skdframework.Money;
import org.ybacoby.skdframework.Rg;

/**
 *
 * @author cristovao
 */
public class Repository {

    private final String driver;
    private final String url;
    private final String usuario;
    private final String senha;
    private Connection conexao;
    private Statement statement;

    public static Repository getInstance(String driver, String url, String usuario, String senha) {
        return new Repository(driver, url, usuario, senha);
    }

    private Repository(String driver, String url, String usuario, String senha) {
        this.driver = driver;
        this.senha = senha;
        this.url = url;
        this.usuario = usuario;
    }

    private Repository open() throws SQLException, ClassNotFoundException {
        Class.forName(this.driver);
        conexao = DriverManager.getConnection(url, usuario, senha);
        statement = conexao.createStatement();
        return this;
    }

    private Repository close() throws SQLException {
        statement.close();
        conexao.close();
        return this;
    }

    public Repository save(Object entity) throws SQLException, IllegalArgumentException, IllegalAccessException, ClassNotFoundException {
        this.open();
        Sql sql = new SqlANSI(entity);
        statement.executeUpdate(sql.insert().toString());
        this.close();
        return this;
    }

    public Repository delete(Object entity) throws SQLException, ClassNotFoundException {
        this.open();
        Sql sql = new SqlANSI(entity);
        statement.executeUpdate(sql.delete().toString());
        this.close();
        return this;
    }

    public ArrayList<Object> criteriaQuery(Sql sql) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        this.open();
        ArrayList<Object> entitys = null;
        entitys = new ArrayList<Object>();
        ResultSet resultset = statement.executeQuery(sql.toString());

        while (resultset.next()) {
            Object entity = null;

            entity = (Object) sql.clasz.newInstance();

            for (Field field : sql.clasz.getDeclaredFields()) {
                field.setAccessible(true);
                if (field.getType().equals(String.class)) {
                    field.set(entity, resultset.getString(field.getName()));
                } else if (field.getType().equals(Integer.class)) {
                    field.set(entity, resultset.getInt(field.getName()));
                } else if (field.getType().equals(Float.class)) {
                    field.set(entity, resultset.getFloat(field.getName()));
                } else if (field.getType().equals(Double.class)) {
                    field.set(entity, resultset.getDouble(field.getName()));
                } else if (field.getType().equals(Money.class)) {
                    field.set(entity, new Money(resultset.getLong(field.getName()), Currency.getInstance(Locale.US)));
                } else if (field.getType().equals(Rg.class)) {
                    field.set(entity, new Rg(resultset.getString(field.getName())));
                } else if (field.getType().equals(Cep.class)) {
                    field.set(entity, new Cep(resultset.getString(field.getName())));
                } else if (field.getType().equals(Cnpj.class)) {
                    field.set(entity, new Cnpj(resultset.getString(field.getName())));
                } else if (field.getType().equals(Date.class)) {
                    field.set(entity, new Date(resultset.getDate(field.getName())));
                }
            }

            entitys.add(entity);
        }
        this.close();
        return entitys;
    }
}
