package org.ybacoby.skdframework.repository;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Assert;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class RepositoryTest
{
    @Test
    public void testSqlSelect() {

        Sql sql = new SqlANSI(new EntityTestTable.PersonTest());
        sql.select();

        Assert.assertEquals("SELECT id, nome FROM persontest;", sql.toString());
    }

    @Test
    public void testSqlSelectTable() {

        Sql sql = new SqlANSI(new EntityTestTable());
        sql.select();

        Assert.assertEquals("SELECT id, nome FROM Test;", sql.toString());
    }

    @Test
    public void testSqlSelectWhere() {

        Sql sql = new SqlANSI(new EntityTestTable());
        sql.select().where().equals("nome", "legal");

        Assert.assertEquals("SELECT id, nome FROM Test WHERE nome = 'legal';", sql.toString());
    }

    @Test
    public void testSqlInsert() {

        Sql sql = new SqlANSI(new EntityTestTable());
        try {
            sql.insert();
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(RepositoryTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(RepositoryTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        Assert.assertEquals("INSERT INTO Test (id, nome) VALUES ('10', 'Funciona');", sql.toString());
    }

    @Test
    public void testSqlUpdate() {

        Sql sql = new SqlANSI(new EntityTestTable());
        try {
            sql.update();
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(RepositoryTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(RepositoryTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        Assert.assertEquals("UPDATE Test SET id = '10', nome = 'Funciona';", sql.toString());
    }
}
