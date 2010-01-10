/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ybacoby.skdframework.email;

import java.util.List;
import org.apache.commons.mail.EmailException;
import org.ybacoby.skdframework.Email;
import org.ybacoby.skdframework.utils.FailedSendEmailAuthentication;

/**
 *
 * @author cristovao
 */
public interface IProvider {

    /**
     * @see br.com.cristo.pedido.email.IEmail#isAuthentication()
     */
    boolean isAuthentication();

    /**
     * @see br.com.cristo.pedido.email.IEmail#sendMail(String file, List<String> emails, String subject, String message)
     */
    void sendMail(String file, List<Email> emails, String subject, String message) throws FailedSendEmailAuthentication, EmailException, FailedSendEmailAuthentication;

    /**
     * @see br.com.cristo.pedido.email.IEmail#sendMail(List<String> emails, String subject, String message)
     */
    void sendMail(List<Email> emails, String subject, String message) throws FailedSendEmailAuthentication, EmailException, FailedSendEmailAuthentication;

    /**
     * @param removeFileSend the removeFileSend to set
     */
    void setRemoveFileSend(Boolean removeFileSend);

}
