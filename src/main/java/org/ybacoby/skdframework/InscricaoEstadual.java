/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ybacoby.skdframework;

import org.ybacoby.skdframework.utils.I18nResources;

/**
 *
 * @author cristovao
 */
public class InscricaoEstadual implements DocumentState<InscricaoEstadual> {

    private final String inscricaoNumber;

    public InscricaoEstadual(String inscricaoNumber) {
        if (inscricaoNumber == null) {
            new IllegalArgumentException(I18nResources.NoCpfString.toString());
        }
        if (inscricaoNumber.length() < 14) {
            new IllegalArgumentException(I18nResources.NoCpfString.toString());
        }
        this.inscricaoNumber = inscricaoNumber;
    }

    @Override
    public Integer getValue() {
        return Integer.parseInt(inscricaoNumber);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final InscricaoEstadual other = (InscricaoEstadual) obj;
        if ((this.inscricaoNumber == null) ? (other.inscricaoNumber != null) : !this.inscricaoNumber.equals(other.inscricaoNumber)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + (this.inscricaoNumber != null ? this.inscricaoNumber.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return this.inscricaoNumber;
    }

    @Override
    public int compareTo(InscricaoEstadual o) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
