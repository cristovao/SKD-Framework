/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ybacoby.skdframework.utils;

/**
 *
 * @author cristovao
 */
public class FailedLoaderEmailProvider extends Exception {

    public FailedLoaderEmailProvider(Throwable thrwbl) {
        super(thrwbl);
    }

    public FailedLoaderEmailProvider(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }

    public FailedLoaderEmailProvider(String string) {
        super(string);
    }

    public FailedLoaderEmailProvider() {
        super(I18nResources.FailedLoaderEmailProvider.toString());
    }

}
