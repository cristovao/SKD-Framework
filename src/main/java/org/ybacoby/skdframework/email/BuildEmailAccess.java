/**
 * 
 */
package org.ybacoby.skdframework.email;

import org.ybacoby.skdframework.Email;
import org.ybacoby.skdframework.utils.EmailNotAuthentication;

/**
 * @author cristovao
 *
 */
public final class BuildEmailAccess {

	private String login;
	
	private String password;

	/**
	 * @param login the login to set
	 */
	public final BuildEmailAccess setLogin(String login) {
		this.login = login;
		return this;
	}

	/**
	 * @param password the password to set
	 */
	public final BuildEmailAccess setPassword(String password) {
		this.password = password;
		return this;
	}
	
	public final Email build() throws EmailNotAuthentication {
		if (!this.isAuthenticationLogin()) throw new EmailNotAuthentication();
		if (!this.isAuthenticationPassword()) throw new EmailNotAuthentication();
		return new Email(this.login, this.password);
	}
	
	public boolean isAuthenticationLogin() {
		if (this.login == null) return false;
		if (this.login.isEmpty()) return false;
		return true;
	}
	
	public boolean isAuthenticationPassword() {
		if (this.password == null) return false;
		if (this.password.isEmpty()) return false;
		return true;
	}
}
