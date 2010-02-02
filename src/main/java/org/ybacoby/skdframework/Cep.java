/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ybacoby.skdframework;

import org.ybacoby.skdframework.utils.I18nResources;

/**
 * Classe que serve para tratar de ceps, neste caso
 * como um tipo, ja que o mesmo nem representa um
 * numero e nem mesmo uma string, entao o cep
 * sera referenciado como um tipo unico, sendo
 * tratado de forma diferenciada para cada situacao
 * @author cristovao
 */
public final class Cep implements DocumentState<Cep> {

    private final String zipNumber;

    /**
     * Constructor
     * @param zip A string com ou sem a formatacao padrao de um
     * cep, neste caso XXXXX-XXX
     */
    public Cep(String zip) {
        if (zip.length() < 7 || zip.length() > 8) throw new IllegalArgumentException(I18nResources.NotZipString.toString());
        zip.replaceAll("-", "");
        this.zipNumber = zip;
    }

    @Override
    public boolean equals(Object arg0) {
        if (!(arg0 instanceof Cep)) return false;
        Cep cep = (Cep)arg0;
        return this.zipNumber.equals(cep.zipNumber);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + (this.zipNumber != null ? this.zipNumber.hashCode() : 0);
        return hash;
    }
    
    @Override
    public String toString() {
        return String.format("%s-%s", this.zipNumber.substring(0, 5), this.zipNumber.substring(5));
    }

    @Override
    public int compareTo(Cep arg0) {
        return this.toString().compareTo(arg0.toString());
    }

    @Override
    public Integer getValue() {
        return Integer.parseInt(this.zipNumber);
    }

}
