/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ybacoby.skdframework.utils;

/**
 *
 * @author cristovao
 */
public class FailedSendEmailNullTo extends Exception {

    public FailedSendEmailNullTo(Throwable thrwbl) {
        super(thrwbl);
    }

    public FailedSendEmailNullTo(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }

    public FailedSendEmailNullTo(String string) {
        super(string);
    }

    public FailedSendEmailNullTo() {
        super(I18nResources.FailedSendEmailNullTo.toString());
    }

}
