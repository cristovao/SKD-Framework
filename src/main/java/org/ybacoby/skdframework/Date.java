/**
 * 
 */
package org.ybacoby.skdframework;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @author cristovao
 *
 * Como eu queria que a data fosse persistida no banco de objetos eu extendi o tipo Date
 * de java, pois nao tinha como guardar a data do sistema, um detalhe importante eh que o
 * NeoDatis eh incapaz de persistir objetos do tipo SimpleDateFormat, motivo este que ainda
 * desconheco o que me obriga a tornar o mesmo transient, justamente para evitar de ser
 * persistido no banco, o mesmo vale para Calendar, ja que preciso do mes e do ano do pedido.
 */
public class Date extends java.util.Date {

	private static final long serialVersionUID = 1L;
	
	private long timer;
	
	private transient SimpleDateFormat sdfData;
	
	private transient Calendar calendario;
	
	private String date;

	/**
	 * Constructor
	 */
	public Date() {
		super();
		this.timer = super.getTime();
		this.sdfData = new SimpleDateFormat("dd/MM/yyyy");
		this.date = this.sdfData.format(this);
	}

	/**
	 * Constructor
	 * @param date
	 */
	public Date(long date) {
		super(date);
		this.timer = super.getTime();
		this.date = this.sdfData.format(this);
	}

	/**
	 * @see java.util.Date#getTime()
	 */
	@Override
	public long getTime() {
		return this.timer;
	}

	/**
	 * @see java.util.Date#setTime(long)
	 */
	@Override
	public void setTime(long time) {
		this.timer = time;
		super.setTime(time);
		this.date = this.sdfData.format(this);
	}

	/**
	 * Retorna a data no formato dd/mm/yyyy
	 */
	@Override
	public String toString() {
		return this.date;
	}
	
	private void loadCalendar() {
		this.calendario = Calendar.getInstance();
		this.calendario.setTime(this);
	}

	/* (non-Javadoc)
	 * @see java.util.Date#getDay()
	 */
	@Override
	public int getDay() {
		this.loadCalendar();
		return this.calendario.get(Calendar.DAY_OF_MONTH);
	}

	/* (non-Javadoc)
	 * @see java.util.Date#getMonth()
	 */
	@Override
	public int getMonth() {
		this.loadCalendar();
		return this.calendario.get(Calendar.MONTH);
	}

	/* (non-Javadoc)
	 * @see java.util.Date#getYear()
	 */
	@Override
	public int getYear() {
		this.loadCalendar();
		return this.calendario.get(Calendar.YEAR);
	}

	
}
