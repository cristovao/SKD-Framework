/**
 * 
 */
package org.ybacoby.skdframework.email;

import org.apache.commons.mail.MultiPartEmail;
import org.ybacoby.skdframework.Email;

/**
 * Classe destinada a permitir uso do GMail
 * @author cristovao
 *
 */
final class GoogleMail extends Provider {

	/**
	 * Membro estatico para o nome do provedor de email,
	 * por padronizacao de chamada este membro possui um
	 * nome obrigatorio PROVIDER_NAME
	 */
	public static final String PROVIDER_NAME = "GMAIL";
	
	/**
	 * 
	 * Constructor
	 * @param representante
	 */
	public GoogleMail(Email email) {
		super(email);
	}

	/**
	 * @see br.com.ybacoby.akaru.email.Email#optionsMail(org.apache.commons.mail.MultiPartEmail)
	 */
	@Override
	protected MultiPartEmail optionsMail(MultiPartEmail email) {
		email.setHostName("smtp.gmail.com");
		email.setSSL(true);
		return email;
	}

}
