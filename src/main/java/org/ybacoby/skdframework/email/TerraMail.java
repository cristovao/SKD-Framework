/*
    akaru  -  br.com.ybacoby.akaru.email.TerraMail.java
                             -------------------
    begin                : 26/05/2009
    copyright            : (C) 2008 by cristovao
    email                : cristovao.wollieson@gmail.com
 ***************************************************************************/

/*
 *   This program is free software; you can redistribute it and/or modify  *
 *   it under the terms of the GNU General Public License as published by  *
 *   the Free Software Foundation; either version 2 of the License, or     *
 *   (at your option) any later version.                                   *
 *                                                                         *
 *   This program is distributed in the hope that it will be useful, but   *
 *   WITHOUT ANY WARRANTY; without even the implied warranty of            *
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU     *
 *   General Public License for more details.                              *
 *                                                                         *
 *   You should have received a copy of the GNU General Public License     *
 *   along with this program; if not, write to the                         *
 *   Free Software Foundation, Inc., 59 Temple Place, Suite 330, Boston,   *
 *   MA  02111-1307 USA                                                    *
 ***************************************************************************/

/**
 * br.com.ybacoby.akaru.email.TerraMail.java
 *
 *
 * @author cristovao
 */
package org.ybacoby.skdframework.email;

import org.apache.commons.mail.MultiPartEmail;
import org.ybacoby.skdframework.Email;

/**
 * Classe responsavel pelos envios de emails, utilizando o Provedor Terra
 * @author cristovao 
 */
final class TerraMail extends Provider {

	/**
	 * Membro estatico para o nome do provedor de email,
	 * por padronizacao de chamada este membro possui um
	 * nome obrigatorio PROVIDER_NAME
	 */
	public static final String PROVIDER_NAME = "TERRA";
	
	/**
	 * Constructor
	 * @param representante
	 */
	public TerraMail(Email email) {
		super(email);
	}

	@Override
	protected MultiPartEmail optionsMail(MultiPartEmail email) {
		email.setHostName("smtp.rec.terra.com.br");
		//email.setDebug(true);
		return email;
	}

}
