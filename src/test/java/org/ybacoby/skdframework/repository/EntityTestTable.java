/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ybacoby.skdframework.repository;

/**
 *
 * @author cristovao
 */
@Entity(table = "Test")
public class EntityTestTable {

    @Id
    private Integer id;
    private String nome;

    public EntityTestTable() {
        this.id = 10;
        this.nome = "Funciona";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public static class PersonTest {

        @Id
        private Integer id;
        private String nome;

        public PersonTest() {
            this.id = 10;
            this.nome = "Funciona";
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }
    }
}
