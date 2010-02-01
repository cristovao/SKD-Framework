/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ybacoby.skdframework;

/**
 *
 * @author cristovao
 */
public class Endereco {

    private Cep cep;
    private Cidade cidade;
    private String bairro;
    private String logradouro;

    public Endereco setBairro(String bairro) {
        this.bairro = bairro;
        return this;
    }

    public Endereco setCidade(Cidade cidade) {
        this.cidade = cidade;
        return this;
    }

    public Endereco setCep(Cep cep) {
        this.cep = cep;
        return this;
    }

    public Endereco setLogradouro(String logradouro) {
        this.logradouro = logradouro;
        return this;
    }

    public String getBairro() {
        return bairro;
    }

    public Cep getCep() {
        return cep;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public Estado getEstado() {
        return this.cidade.getEstado();
    }

    public String getCidadeName() {
        return this.cidade.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Endereco other = (Endereco) obj;
        if (this.cep != other.cep && (this.cep == null || !this.cep.equals(other.cep))) {
            return false;
        }
        if (this.cidade != other.cidade && (this.cidade == null || !this.cidade.equals(other.cidade))) {
            return false;
        }
        if ((this.bairro == null) ? (other.bairro != null) : !this.bairro.equals(other.bairro)) {
            return false;
        }
        if ((this.logradouro == null) ? (other.logradouro != null) : !this.logradouro.equals(other.logradouro)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + (this.cep != null ? this.cep.hashCode() : 0);
        hash = 37 * hash + (this.cidade != null ? this.cidade.hashCode() : 0);
        hash = 37 * hash + (this.bairro != null ? this.bairro.hashCode() : 0);
        hash = 37 * hash + (this.logradouro != null ? this.logradouro.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append(this.logradouro).append(", ").append(this.bairro)
                .append(", ").append(this.cidade.toString()).append(", ")
                .append(this.getEstado()).append(", ").append(this.cep.toString());
        return string.toString();
    }

}
