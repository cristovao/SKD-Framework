/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ybacoby.skdframework;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Classe para representacao de estados com um conjunto
 * de cidades.
 * @author cristovao
 */
public final class Estado implements Comparable<Estado>, List<Cidade> {

    private List<Cidade> cidades;

    private final String uf;

    public Estado(String uf) {
        this.uf = uf;
        this.cidades = new ArrayList<Cidade>();
    }

    @Override
    public <T> T[] toArray(T[] arg0) {
        return cidades.toArray(arg0);
    }

    @Override
    public Object[] toArray() {
        return cidades.toArray();
    }

    @Override
    public List<Cidade> subList(int arg0, int arg1) {
        return cidades.subList(arg0, arg1);
    }

    @Override
    public int size() {
        return cidades.size();
    }

    @Override
    public Cidade set(int arg0, Cidade arg1) {
        return cidades.set(arg0, arg1);
    }

    @Override
    public boolean retainAll(Collection<?> arg0) {
        return cidades.retainAll(arg0);
    }

    @Override
    public boolean removeAll(Collection<?> arg0) {
        return cidades.removeAll(arg0);
    }

    @Override
    public Cidade remove(int arg0) {
        return cidades.remove(arg0);
    }

    @Override
    public boolean remove(Object arg0) {
        if (!(arg0 instanceof Cidade)) return false;
        return cidades.remove((Cidade)arg0);
    }

    @Override
    public ListIterator<Cidade> listIterator(int arg0) {
        return cidades.listIterator(arg0);
    }

    @Override
    public ListIterator<Cidade> listIterator() {
        return cidades.listIterator();
    }

    @Override
    public int lastIndexOf(Object arg0) {
        return cidades.lastIndexOf(arg0);
    }

    @Override
    public Iterator<Cidade> iterator() {
        return cidades.iterator();
    }

    @Override
    public boolean isEmpty() {
        return cidades.isEmpty();
    }

    @Override
    public int indexOf(Object arg0) {
        return cidades.indexOf(arg0);
    }

    @Override
    public Cidade get(int arg0) {
        return cidades.get(arg0);
    }

    public Cidade get(String arg0) {
        for (Cidade cidade:this.cidades) {
            if (cidade.toString().equals(arg0.toUpperCase())) return cidade;
        }
        return null;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Estado other = (Estado) obj;
        if (this.cidades != other.cidades && (this.cidades == null || !this.cidades.equals(other.cidades))) {
            return false;
        }
        if ((this.uf == null) ? (other.uf != null) : !this.uf.equals(other.uf)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + (this.cidades != null ? this.cidades.hashCode() : 0);
        hash = 67 * hash + (this.uf != null ? this.uf.hashCode() : 0);
        return hash;
    }

    

    @Override
    public boolean containsAll(Collection<?> arg0) {
        return cidades.containsAll(arg0);
    }

    @Override
    public boolean contains(Object arg0) {
        return cidades.contains((Cidade)arg0);
    }

    public boolean contains(String arg0) {
        for (Cidade cidade:this.cidades) {
            if (cidade.toString().equals(arg0.toUpperCase())) return true;
        }
        return false;
    }

    @Override
    public void clear() {
        cidades.clear();
    }

    @Override
    public void add(int arg0, Cidade arg1) {
        cidades.add(arg0, arg1);
    }

    @Override
    public boolean add(Cidade arg0) {
        return cidades.add(arg0);
    }

    public Cidade add(String arg0) {
        Cidade cidade = new Cidade(arg0.toUpperCase(), this);
        cidades.add(cidade);
        return cidade;
    }

    public void add(int arg0, String arg1) {
        cidades.add(arg0, new Cidade(arg1, this));
    }

    @Override
    public int compareTo(Estado arg0) {
        Estado estado = (Estado)arg0;
        int comparacao = this.uf.compareTo(estado.uf);
        return comparacao;
    }

    @Override
    public String toString() {
        return this.uf;
    }

    @Override
    public boolean addAll(Collection<? extends Cidade> c) {
        return this.cidades.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends Cidade> c) {
        return this.cidades.addAll(index, c);
    }


}
