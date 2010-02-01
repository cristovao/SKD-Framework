/**
 * 
 */
package org.ybacoby.skdframework;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Currency;

/**
 * Classe usada para calculos relativos a dinheiro
 *
 * @author cristovao
 *
 */
public class Money {

    /**
     * Refere-se ao pais em codigo iso do qual sera utilizado
     * a moeda, neste caso focado calculos, de forma que as
     * operacoes usadas sejam diferentes para determinadas
     * moedas, um caso seria que $1.00 != R$1,00
     */
    private final Currency currency;

    /**
     * Aqui representa a quantia em sua unidade minima, neste caso
     * os centavos, que quando eh visualizado sera convertido
     * utilizando a base do currency para tratamento de seus dados
     */
    private final Long amount;

    /**
     * Constructor que recebe alguns valores bases, considerando a
     * quantidade em uma string e o tipo da moeda.
     * @param amount Recebe a quantia em uma string considerando o valor sendo do tipo long
     * @param currency O tipo de moeda que sera usado, podendo ser dolar, real, peso etc
     */
    public Money(String amount, Currency currency) {
        this.currency = currency;
        this.amount = Long.parseLong(amount) * (long) Math.pow(10, currency.getDefaultFractionDigits());
    }

    /**
     * Constructor que recebe alguns valores bases, considerando a
     * quantidade em uma string e o tipo da moeda.
     * @param amount Recebe a quantia em uma string considerando o valor sendo do tipo long
     * @param currencyCode Aqui recebe o codigo da moeda em sua norma ISO
     */
    public Money(String amount, String currencyCode) {
        this(amount, Currency.getInstance(currencyCode));
    }

    /**
     * Constructor que recebe alguns valores bases, considerando a
     * quantidade em uma string e o tipo da moeda.
     * @param amount A quantidade estipulada do tipo Long (um numero superior ao Integer)
     * @param currency O tipo de moeda que sera usado, podendo ser dolar, real, peso etc
     */
    public Money(Long amount, Currency currency) {
        this.currency = currency;
        this.amount = amount;
    }

    /**
     * Constructor que recebe os valores de base, neste caso ele trabalha
     * convertendo valores Double em longos para trabalhar melhor com a moeda
     * neste caso, permitindo receber valores que estejam mais proximos a moeda
     * em si do que receber diretamente valores longos de moedas, o que simplifica
     * um pouco mais o uso relevante tido pelo valor da moeda
     * @param amountDouble Aqui recebe o valor em double para tratar melhor com a moeda em especifico
     * @param currency O tipo de moeda que sera usado, podendo ser dolar, real, peso etc
     */
    public Money(Double amountDouble, Currency currency) {
        this.currency = currency;
        this.amount = ((Double)(amountDouble*Math.pow(10, currency.getDefaultFractionDigits()))).longValue();
    }

    /**
     * Constructor que recebe os valores de base, neste caso ele trabalha
     * convertendo valores String em Double para depois gerar os valores
     * longos para trabalhar melhor com a moeda
     * neste caso, permitindo receber valores que estejam mais proximos a moeda
     * em si do que receber diretamente valores longos de moedas, o que simplifica
     * um pouco mais o uso relevante tido pelo valor da moeda
     * @param currency
     * @param amountDouble
     */
    public Money(Currency currency, String amountDouble) {
        BigDecimal numero = new BigDecimal(amountDouble);
        this.currency = currency;
        this.amount = numero.multiply(new BigDecimal(Math.pow(10, currency.getDefaultFractionDigits()))).longValue();
    }

    public Money compoundInterest(Double rates, Integer periods) {
        Double valor = Math.pow(1 + rates, periods);
        return new Money(this.amount*valor.longValue(), currency);
    }

    public Money discount(Double percent) {
        Double value = this.amount*percent;
        return new Money(this.amount-value.longValue(), this.currency);
    }

    public Integer divide(Money other) {
        if (!this.hasSameCurrency(other)) {
            throw new IllegalArgumentException("Cannot sum money in different currencies");
        }
        int valor = (int)(this.amount/((Money)other).amount);
        return this.amount%((Money)other).amount > 0?valor+1:valor;
    }

    public Boolean hasSameCurrency(Money other) {
        return this.currency.equals(other.currency);
    }

    public Money impost(Double percenty) {
        Double value = this.amount*percenty;
        return new Money(this.amount+value.longValue(), this.currency);
    }

    public Money minus(Money other) {
        if (!this.hasSameCurrency(other)) {
            throw new IllegalArgumentException("Cannot sum money in different currencies");
        }
        return new Money(this.amount-((Money)other).amount, this.currency);
    }

    public Money multiply(Number scalar) {
        return new Money(this.amount*scalar.intValue(), this.currency);
    }

    public Money plus(Money other) {
        if (!this.hasSameCurrency(other)) {
            throw new IllegalArgumentException("Cannot sum money in different currencies");
        }
        return new Money(this.amount+other.amount, this.currency);
    }

    public Money simpleAmount(Double rates, Integer periods) {
        Double valor = (rates*periods)/100;
        return new Money(this.amount*(1+valor.longValue()), currency);
    }

    public Double simpleInterest(Double rates, Integer periods) {
        return (this.amount*rates*periods)/100;
    }

    public Money[] distribute(int n) {
        Money[] money = new Money[n];
        long valor = this.amount/n;
        long resto = this.amount%n;
        money[0] = new Money(valor+resto, currency);
        for(int i=1; i<n;i++) money[i] = new Money(valor, currency);
        return money;
    }

    public Money[] distributeEqually(int n) {
        Money[] money = new Money[2];
        long valor = this.amount/n;
        long resto = this.amount%n;
        money[0] = new Money(valor, currency);
        money[1] = new Money(resto, currency);
        return money;
    }

    public Double doubleValue() {
        return amount/Math.pow(10, currency.getDefaultFractionDigits());
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("0.00");
        Double resultado = amount/Math.pow(10, currency.getDefaultFractionDigits());
        return df.format(resultado);
    }
}
