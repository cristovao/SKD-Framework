/**
 * 
 */
package org.ybacoby.skdframework;

/**
 * @author cristovao
 *
 */
public final class Email {

	/**
	 * O Email a ser usado
	 */
	private final String loginEmail;
	
	/**
	 * A senha de acesso do email
	 */
	private final String password;
	
	/**
	 * Constructor
	 * @param loginEmail O email para ser usado
	 */
	public Email(String loginEmail) {
		this.loginEmail = loginEmail;
		this.password = null;
	}

	/**
	 * Constructor
	 * @param loginEmail
	 * @param password
	 */
	public Email(String loginEmail, String password) {
		this.loginEmail = loginEmail;
		this.password = password;
	}
	
	/**
	 * Constructor
	 * @param password
	 * @param email
	 */
	public Email(String password, Email email) {
		this.loginEmail = email.loginEmail;
		this.password = password;
	}

	/**
	 * Verifica se o login e a senha estao autenticados
	 * @return true se estiverem autenticados, false se estiver ausente de senha
	 */
	public boolean isAuthentication() {
		if (this.loginEmail == null) return false;
		if (this.password == null) return false;
		if (this.loginEmail.isEmpty()) return false;
		if (this.password.isEmpty()) return false;
		return true;
	}

	/**
	 * @return the password
	 */
	public final String getPassword() {
		return password;
	}
	
	/**
	 * Serve para retornar apenas o nome do provedor de email,
	 * por exemplo se seu email for fulano@supermail.com.br, entao
	 * quando usar esse metodo ele so retornara apenas SUPERMAIL,
	 * neste caso o nome do provedor de email que voce esta usando.
	 * 
	 * @return O nome do provedor de email
	 */
	public String getEmailProvider() {
		String[] provider;
		
		// Realizando um regex para capturar apenas o provedor de email
		provider = this.loginEmail.split("@")[1].split("\\.");
		
		return provider[0].toUpperCase();
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		Email email = (Email)obj;
		if (!this.loginEmail.equals(email.loginEmail)) return false;
		if (!this.password.equals(email.password)) return false;
		return true;
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return this.loginEmail.hashCode()+this.password.hashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.loginEmail;
	}
}
