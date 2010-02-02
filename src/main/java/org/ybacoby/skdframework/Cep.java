/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ybacoby.skdframework;

/**
 * Classe que serve para tratar de ceps
 * @author cristovao
 */
public final class Cep implements DocumentState<Cep> {

    private final String firstZipNumber;
    private final String lastZipNumber;

    public Cep(String zip) {
        if (zip.length() < 7 || zip.length() > 8) throw new IllegalArgumentException("i18n.ErrorMessage.NotZipString");
        zip.replaceAll("-", "");
        this.firstZipNumber = zip.substring(0, 5);
        this.lastZipNumber = zip.substring(5);
    }

    @Override
    public boolean equals(Object arg0) {
        if (!(arg0 instanceof Cep)) return false;
        Cep cep = (Cep)arg0;
        return this.firstZipNumber.equals(cep.firstZipNumber) && this.lastZipNumber.equals(cep.lastZipNumber)?true:false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + (this.firstZipNumber != null ? this.firstZipNumber.hashCode() : 0);
        hash = 37 * hash + (this.lastZipNumber != null ? this.lastZipNumber.hashCode() : 0);
        return hash;
    }
    
    @Override
    public String toString() {
        return String.format("%s-%s", this.firstZipNumber, this.lastZipNumber);
    }

    @Override
    public int compareTo(Cep arg0) {
        return this.toString().compareTo(arg0.toString());
    }

    @Override
    public Integer getValue() {
        StringBuilder string = new StringBuilder();
        string.append(this.firstZipNumber).append(lastZipNumber);
        return Integer.parseInt(string.toString());
    }

}
