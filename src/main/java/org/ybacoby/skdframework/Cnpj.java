/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ybacoby.skdframework;

/**
 *
 * @author cristovao
 */
public final class Cnpj extends I18nAllSystem implements DocumentState<Cnpj> {

    private final String cnpjNumber;

    public Cnpj(String cnpjNumber) {
        if (cnpjNumber == null) {
            new IllegalArgumentException(this.getResource("i18n.ErrorMessage.NoCpfString"));
        }
        if (cnpjNumber.length() < 10) {
            new IllegalArgumentException(this.getResource("i18n.ErrorMessage.NoCpfString"));
        }
        cnpjNumber = cnpjNumber.replaceAll("\\.", "");
        cnpjNumber = cnpjNumber.replaceAll("-", "");
        cnpjNumber = cnpjNumber.replaceAll("/", "");
        this.cnpjNumber = cnpjNumber;
    }

    @Override
    public Integer getValue() {
        return Integer.getInteger(this.cnpjNumber);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cnpj other = (Cnpj) obj;
        if ((this.cnpjNumber == null) ? (other.cnpjNumber != null) : !this.cnpjNumber.equals(other.cnpjNumber)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + (this.cnpjNumber != null ? this.cnpjNumber.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return MaskUtils.CNPJ.format(cnpjNumber);
    }

    @Override
    public int compareTo(Cnpj o) {
        return this.cnpjNumber.compareTo(o.cnpjNumber);
    }

    
}
