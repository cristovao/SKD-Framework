/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ybacoby.skdframework.utils;

/**
 *
 * @author cristovao
 */
public abstract class Validate {

    protected boolean containsOnlyNumbers(String str) {

        // It can't contain only numbers if it's null or empty...
        if (str == null || str.length() == 0) {
            return false;
        }

        for (int i = 0; i < str.length(); i++) {

            // If we find a non-digit character we return false.
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }

        return true;
    }
}
