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
import org.ybacoby.skdframework.InscricaoEstadual;
import org.ybacoby.skdframework.Money;
import org.ybacoby.skdframework.Rg;
import org.ybacoby.skdframework.Telephone;

/**
 *
 * @author cristovao
 */
public final class Repository {

    private final String driver;
    private final String url;
    private final String usuario;
    private final String senha;
    private Connection conexao;
    private Statement statement;

    /**
     * Constructor para permissao de acesso no banco de dados, no caso
     * deste framework, como se trabalha com mais de um banco de dados
     * entao tambem trabalhasse com mais de uma conexao, de toda forma
     * o framework em si nunca dara acesso para o usuario abrir ou mesmo
     * fechar uma transacao, ja que se for para permitir algo assim, entao
     * a necessidade de um construtor seria inutil, por esse motivo as
     * opcoes de abrir e fechar o banco para cada transacao sao transparentes
     * aos usuarios do sistema em si, essa forma foi escolhida pelo fato
     * do framework ter como objetivo ser simples, por tal razao qual seria
     * a necessidade de um usuario ter que ter permissao de abrir e fechar o
     * banco de dados, se o mesmo so quer salvar, deletar e pesquisar?
     * @param driver O nome do driver que devera ser executado, requisito do JDBC
     * @param url O caminho onde esta o banco de dados;
     * @param usuario O login do usuario para acesso ao banco de dados diretamente
     * @param senha A senha do usuario para acesso ao banco de dados diretamente
     */
    public Repository(String driver, String url, String usuario, String senha) {
        this.driver = driver;
        this.senha = senha;
        this.url = url;
        this.usuario = usuario;
    }

    /**
     * A necessidade de usuario abrir e fechar conexoes do banco
     * eh uma pratica desnecessaria e desaconselhada, deixando assim
     * para que o proprio sistemas cuide disso.
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    private Repository open() throws SQLException, ClassNotFoundException {
        Class.forName(this.driver);
        conexao = DriverManager.getConnection(url, usuario, senha);
        statement = conexao.createStatement();
        return this;
    }

    /**
     * A necessidade de usuario abrir e fechar conexoes do banco
     * eh uma pratica desnecessaria e desaconselhada, deixando assim
     * para que o proprio sistemas cuide disso.
     * @throws SQLException
     */
    private Repository close() throws SQLException {
        statement.close();
        conexao.close();
        return this;
    }

    /**
     * Este comando sera unicamente para ser utilizado para salvar objetos
     * no banco de dados, tendo como unica necessidade apenas indicar pela
     * annotation de acesso as chaves primarias da tabela na classe que
     * sera entidade do sistema, ou mesmo modelo.
     * <br/>
     * No caso caso a entidade tenha algum conteudo nas suas chaves primarias
     * entao o proprio framework realizara a opcao de update automaticamente,
     * somente realizando a operacao de insert caso o conteudo de suas
     * chaves primarias esteja ausente ou caso o framework nao tenha
     * encontrado o objeto no banco de dados.
     * <br/>
     * Assim nao sera necessario fazer com que o usuario tenha de realizar
     * malabarismos desnecessarios, apenas preencher os dados no objeto e
     * mandar salvar.
     * @param entity O objeto que devera ser persistido no banco de dados
     * @throws SQLException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws ClassNotFoundException
     */
    public Repository save(Object entity) throws SQLException, IllegalArgumentException, IllegalAccessException, ClassNotFoundException, SQLException, InstantiationException {
        this.open();
        Sql sql = new SqlANSI(entity);
        if (!sql.haveWhereEqualsIds()) {
            statement.executeUpdate(sql.insert().toString());
        } else if (this.query(sql.select().whereEqualsIds()).isEmpty()) {
            statement.executeUpdate(new SqlANSI(entity).insert().toString());
        } else {
            statement.executeUpdate(new SqlANSI(entity).update().where().whereEqualsIds().toString());
        }
        this.close();
        return this;
    }

    /**
     * Aqui realiza a funcao de delecao da entidade no banco de dados,
     * entratanto para que seja efetiva essa delecao o objeto
     * deve ser inicialmente carregado com suas devidas chaves primarias
     * com conteudo, para assim ser utilizado este metodo, caso o objeto
     * nao seja encontrado no banco de dados, o mesmo nao sera deletado.
     * @param entity O objeto que deseja excluir do banco de dados
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public Repository delete(Object entity) throws SQLException, ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        this.open();
        Sql sql = new SqlANSI(entity);
        if (sql.haveWhereEqualsIds()) {
            if (!this.query(sql.select().whereEqualsIds()).isEmpty()) {
                statement.executeUpdate(new SqlANSI(entity).delete().toString());
            }
        }
        this.close();
        return this;
    }

    /**
     * Neste incrivel e facil metodo sera onde sera feito criterios de buscas,
     * neste caso, sera aqui onde toda pesquisa sera feita, necessitando apenas
     * do builder Sql para que possa assim criar os comandos sqls que serao
     * utilizados, sendo o processo bastante simples e direto, com esse comando
     * sera onde podera retornar sempre um ArrayList de todos os objetos
     * utilizados como resultados dos criterios de pesquisa.
     * @param sql Builder onde sera utilizado o comando
     * @return
     * @throws SQLException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws ClassNotFoundException
     */
    public ArrayList<Object> criteriaQuery(Sql sql) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        this.open();
        ArrayList<Object> entitys = this.query(sql);
        this.close();
        return entitys;
    }

    private ArrayList<Object> query(Sql sql) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
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
                } else if (field.getType().equals(InscricaoEstadual.class)) {
                    field.set(entity, new InscricaoEstadual(resultset.getString(field.getName())));
                } else if (field.getType().equals(Telephone.class)) {
                    field.set(entity, new Telephone(resultset.getString(field.getName())));
                }
            }

            entitys.add(entity);
        }
        return entitys;
    }
}
