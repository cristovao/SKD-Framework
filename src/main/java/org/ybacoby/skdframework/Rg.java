/**
 * 
 */
package org.ybacoby.skdframework;

/**
 * @author cristovao
 *
 */
public final class Rg extends I18nAllSystem implements DocumentState<Rg> {

    private final String number;

    public Rg(String rg) throws IllegalArgumentException {
        if (rg == null) {
            throw new IllegalArgumentException(this.getResource("i18n.ErrorMessage.NoRgString"));
        } else if (rg.length() < 6 || !this.containsOnlyNumbers(rg)) {
            throw new IllegalArgumentException(this.getResource("i18n.ErrorMessage.NoRgString"));
        }

        this.number = rg;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if ((obj == null) || (obj.getClass() != this.getClass())) {
            return false;
        }

        Rg rg = (Rg) obj;
        return this.number.equals(rg.number);
    }

    @Override
    public int hashCode() {
        Integer hash = 7;
        hash = 31 * hash + (this.number == null ? 0 : this.number.hashCode());
        return hash;
    }

    @Override
    public Integer getValue() {
        return Integer.getInteger(this.number);
    }

    @Override
    public String toString() {
        return this.number;
    }

    @Override
    public int compareTo(Rg o) {
        return this.number.compareTo(o.number);
    }
}
