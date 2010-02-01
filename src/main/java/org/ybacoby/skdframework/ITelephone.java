/**
 * 
 */
package org.ybacoby.skdframework;

/**
 * Para uso das principais instrucoes de uma comunicacao de
 * telefone.
 * 
 * @author cristovao
 *
 */
public interface ITelephone extends Comparable<ITelephone>{

	public Integer getDDD();
	
	public Integer getDDI();
	
	public Integer getValue();
	
	public Integer getOperator();
}
