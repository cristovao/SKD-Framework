/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ybacoby.skdframework.utils;

/**
 *
 * @author cristovao
 */
public class FailedSendEmailAuthentication extends Exception {

    public FailedSendEmailAuthentication(Throwable thrwbl) {
        super(thrwbl);
    }

    public FailedSendEmailAuthentication(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }

    public FailedSendEmailAuthentication(String string) {
        super(string);
    }

    public FailedSendEmailAuthentication() {
        super(I18nResources.FailedSendEmailAuthentication.toString());
    }

}
