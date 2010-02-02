/**
 * 
 */
package org.ybacoby.skdframework.email;

import java.io.File;
import java.util.List;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;

import org.ybacoby.skdframework.Email;
import org.ybacoby.skdframework.utils.FailedSendEmailAuthentication;

/**
 * @author cristovao
 * Classe abstrata para envio de emails para diversos provedores de emails
 */
abstract class Provider implements IProvider {

    private Email email;
    private Boolean removeFileSend = false;
    private transient File file;

    /**
     * Constructor
     * @param representante
     */
    public Provider(Email email) {
        super();
        this.email = email;
    }

    /**
     * @see br.com.cristo.pedido.email.IEmail#sendMail(String file, List<String> emails, String subject, String message)
     */
    @Override
    public void sendMail(String file, List<Email> emails, String subject, String message) throws FailedSendEmailAuthentication, EmailException, FailedSendEmailAuthentication {
        MultiPartEmail multEmail = new MultiPartEmail();
        if (emails == null) {
            throw new FailedSendEmailAuthentication();
        } else if (emails.isEmpty()) {
            throw new FailedSendEmailAuthentication();
        }
        multEmail = this.optionsMail(multEmail);
        if (this.isAuthentication()) {
            multEmail.setAuthentication(this.email.toString(), this.email.getPassword());
        } else {
            throw new FailedSendEmailAuthentication();
        }
        for (Email stringMail : emails) {
            multEmail.addTo(stringMail.toString());
        }
        multEmail.setFrom(this.email.toString());
        multEmail.setSubject(subject);
        multEmail.setMsg(message);

        // add the attachment
        if (!(file == null)) {
            multEmail.attach(addFileToMail(file));
        }

        // Enviando o email
        multEmail.send();

        // Deletando arquivo temporario gerado
        if (!(file == null) && this.removeFileSend) {
            this.file.delete();
        }
    }

    /**
     * @see br.com.cristo.pedido.email.IEmail#sendMail(List<String> emails, String subject, String message)
     */
    @Override
    public void sendMail(List<Email> emails, String subject, String message) throws FailedSendEmailAuthentication, EmailException, FailedSendEmailAuthentication {
        this.sendMail(null, emails, subject, message);
    }

    /**
     * Utilizado para definir caracteristicas especificas de um provedor de email, neste caso
     * podendo definir caracteristica para servidores GMAIL, TERRA, YAHOO, HOTMAIL, entre outros
     * @param email Email da superclasse, que utiliza deste command
     * @return Um email para a superclasse que utiliza deste command
     */
    abstract protected MultiPartEmail optionsMail(MultiPartEmail email);

    /**
     * Serve para adicionar um arquivo ao cliente de email
     * @param file Arquivo a ser adicionado
     * @return Arquivo pronto para anexar ao email
     * @throws PedidoException
     */
    protected EmailAttachment addFileToMail(String file) {

        this.file = new File(file);

        EmailAttachment attachment = new EmailAttachment();
        attachment.setPath(this.file.getPath()); // Obtem o caminho do arquivo
        attachment.setDisposition(EmailAttachment.ATTACHMENT);
        attachment.setDescription("File");
        attachment.setName(this.file.getName());

        return attachment;
    }

    /**
     * @see br.com.cristo.pedido.email.IEmail#isAuthentication()
     */
    @Override
    public boolean isAuthentication() {
        return this.email.isAuthentication();
    }

    /**
     * @param removeFileSend the removeFileSend to set
     */
    @Override
    public void setRemoveFileSend(Boolean removeFileSend) {
        this.removeFileSend = removeFileSend;
    }
}
