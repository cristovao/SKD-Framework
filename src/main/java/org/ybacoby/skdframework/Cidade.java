/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ybacoby.skdframework;

/**
 * Classe que representa a cidade, indicando ainda seu estado
 * @author cristovao
 */
public final class Cidade implements Comparable<Cidade>{

    private final String name;

    private final Estado estado;

    public Cidade(String name, Estado estado) {
        this.name = name;
        this.estado = estado;
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public boolean equals(Object arg0) {
        if (!(arg0 instanceof Cidade)) return false;
        Cidade cidade = ((Cidade)arg0);
        return this.name.equals(cidade.name) && this.estado.equals(cidade.estado)?true:false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 59 * hash + (this.estado != null ? this.estado.hashCode() : 0);
        return hash;
    }

    public Estado getEstado() {
        return estado;
    }

    @Override
    public int compareTo(Cidade arg0) {
        Cidade cidade = (Cidade)arg0;
        int comparacao = this.name.compareTo(cidade.name);
        return comparacao;
    }
}
