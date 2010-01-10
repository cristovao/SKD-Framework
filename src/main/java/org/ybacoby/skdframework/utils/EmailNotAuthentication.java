/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ybacoby.skdframework.utils;

/**
 *
 * @author cristovao
 */
public class EmailNotAuthentication extends Exception {

    public EmailNotAuthentication(Throwable thrwbl) {
        super(thrwbl);
    }

    public EmailNotAuthentication(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }

    public EmailNotAuthentication(String string) {
        super(string);
    }

    public EmailNotAuthentication() {
        super(I18nResources.EmailNotAuthentication.toString());
    }

}
