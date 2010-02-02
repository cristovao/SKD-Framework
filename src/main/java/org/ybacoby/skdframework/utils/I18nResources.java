/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ybacoby.skdframework.utils;

import java.util.ResourceBundle;

/**
 *
 * @author cristovao
 */
public enum I18nResources {

    FailedLoaderEmailProvider("i18n.ErrorMessage.FailedLoaderEmailProvider"),
    EmailNotAuthentication("i18n.ErrorMessage.EmailNotAuthentication"),
    FailedSendEmailNullTo("i18n.ErrorMessage.FailedSendEmailNullTo"),
    NoCpfString("i18n.ErrorMessage.NoCpfString"),
    NoPhoneString("i18n.ErrorMessage.NoPhoneString"),
    NotZipString("i18n.ErrorMessage.NotZipString"),
    FailedSendEmailAuthentication("i18n.ErrorMessage.FailedSendEmailAuthentication");
    
    // BEGIN ATTRIBUTE CLASS
    private String i18n;
    private ResourceBundle resource = ResourceBundle.getBundle("I18nResource");
    // END ATTRIBUTE CLASS

    private I18nResources(String i18n) {
        this.i18n = i18n;
    }

    @Override
    public String toString() {
        return resource.getString(i18n);
    }
}
