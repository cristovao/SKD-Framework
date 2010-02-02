/**
 * 
 */
package org.ybacoby.skdframework;

import org.ybacoby.skdframework.utils.I18nResources;

/**
 * Classe para gerenciar CPFs
 * 
 * @author cristovao
 *
 */
public final class Cpf implements DocumentState<Cpf> {

    private final String cpfNumber;

    public Cpf(String cpf) {
        if (cpf == null) {
            new IllegalArgumentException(I18nResources.NoCpfString.toString());
        }
        if (cpf.length() < 10) {
            new IllegalArgumentException(I18nResources.NoCpfString.toString());
        }
        cpf = cpf.replaceAll("\\.", "");
        cpf = cpf.replaceAll("-", "");
        this.cpfNumber = cpf;
    }

    @Override
    public Integer getValue() {
        return Integer.getInteger(this.cpfNumber);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if ((obj == null) || (obj.getClass() != this.getClass())) {
            return false;
        }

        Cpf cpf = (Cpf) obj;
        return this.cpfNumber.equals(cpf.cpfNumber);
    }

    @Override
    public int hashCode() {
        Integer hash = 7;
        hash = 31 * hash + (this.cpfNumber == null ? 0 : this.cpfNumber.hashCode());
        return hash;
    }

    @Override
    public String toString() {
        return MaskUtils.CPF.format(cpfNumber);
    }

    @Override
    public int compareTo(Cpf o) {
        return this.cpfNumber.compareTo(o.cpfNumber);
    }
}
