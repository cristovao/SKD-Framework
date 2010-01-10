/**
 * 
 */
package org.ybacoby.skdframework.email;

import org.ybacoby.skdframework.Email;
import org.ybacoby.skdframework.utils.EmailNotAuthentication;
import org.ybacoby.skdframework.utils.FailedLoaderEmailProvider;

/**
 * Fabrica que trabalha com envio de emails, neste caso com anexo ou sem anexo
 * de forma que abstrai a preocupacao com o tipo de provedor de email a ser
 * utilizado, neste caso o processo de manutencao fica simplificado para a
 * utilizacao de um novo provedor de email.
 * 
 * @author cristovao
 *
 */
public class Factory {

	private Email email;
	
	public Factory setEmail(Email email) {
		this.email = email;
		return this;
	}

        public Factory setEmail(String email, String password) {
            this.email = new Email(email ,password);
            return this;
        }
	
	public IProvider build() throws EmailNotAuthentication, FailedLoaderEmailProvider  {
		if (this.email == null) throw new EmailNotAuthentication();
		if (!this.email.isAuthentication()) throw new EmailNotAuthentication();
		if (this.email.getEmailProvider().equals(TerraMail.PROVIDER_NAME))
			return new TerraMail(this.email);
		else if (this.email.getEmailProvider().equals(GoogleMail.PROVIDER_NAME))
			return new GoogleMail(this.email);
		else 
			throw new FailedLoaderEmailProvider();
	}

}
