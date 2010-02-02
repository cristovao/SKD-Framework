/**
 * 
 */
package org.ybacoby.skdframework;

import org.ybacoby.skdframework.utils.I18nResources;

/**
 * Serve para trabalhar com numeros de telefone
 * 
 * @author cristovao
 *
 */
public final class Telephone implements ITelephone {

    private final String phone;
    private boolean empty;

    public Telephone(String phone) {
        if (phone.length() < 9) {
            this.empty = true;
            new IllegalArgumentException(I18nResources.NoPhoneString.toString());
        }

        phone = phone.replaceAll("\\(", "");
        phone = phone.replaceAll("\\)", "");
        phone = phone.replaceAll(" ", "");
        phone = phone.replaceAll("-", "");
        if (phone.length() < 9) {
            this.empty = true;
            new IllegalArgumentException(I18nResources.NoPhoneString.toString());
        }
        this.phone = phone;
        this.empty = false;
    }

    /* (non-Javadoc)
     * @see br.com.ybacoby.kya.utils.ITelephone#getDDD()
     */
    @Override
    public Integer getDDD() {
        if (this.phone.length() >= 10) {
            return Integer.parseInt(this.phone.substring(0, 3));
        }
        return null;
    }

    /* (non-Javadoc)
     * @see br.com.ybacoby.kya.utils.ITelephone#getDDI()
     */
    @Override
    public Integer getDDI() {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see br.com.ybacoby.kya.utils.ITelephone#getPhone()
     */
    @Override
    public Integer getValue() {
        return Integer.getInteger(this.phone);
    }

    @Override
    public Integer getOperator() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if ((obj == null) || (obj.getClass() != this.getClass())) {
            return false;
        }

        Telephone phoneTemp = (Telephone) obj;
        return this.phone.equals(phoneTemp.phone);
    }

    @Override
    public int hashCode() {
        Integer hash = 7;
        hash = 31 * hash + (this.phone == null ? 0 : this.phone.hashCode());
        return hash;
    }

    @Override
    public String toString() {
        if (this.phone.isEmpty()) return "";
        return MaskUtils.TELEFONE.format(this.phone);
    }

    @Override
    public int compareTo(ITelephone arg0) {
        return this.toString().compareTo(arg0.toString());
    }

    public boolean isEmpty() {
        return this.empty;
    }
}
