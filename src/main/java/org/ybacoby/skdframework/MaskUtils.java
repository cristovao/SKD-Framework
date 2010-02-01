/**
 * 
 */
package org.ybacoby.skdframework;

import java.text.ParseException;

import javax.swing.JFormattedTextField;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

/**
 * @author cristovao
 * 
 */
public enum MaskUtils {

    CPF("###.###.###-##") {

        @Override
        public String format(String text) {
            String stringFormat = text;
            if (text.length() < 10) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
            stringFormat = text.replaceAll("\\.", "");
            stringFormat = stringFormat.replaceAll("-", "");
            String string1 = stringFormat.substring(0, 3);
            String string2 = stringFormat.substring(3, 6);
            String string3 = stringFormat.substring(6, 9);
            String string4 = stringFormat.substring(9, 11);
            return String.format("%s.%s.%s-%s", string1, string2, string3,
                    string4);
        }

        @Override
        public String removeFormat(String text) {
            text = text.replaceAll("\\.", "");
            text = text.replaceAll("-", "");
            return text;
        }
    },
    CEP("#####-###") {
        @Override
        public String format(String text) {
            if (text.length() < 7 || text.length() > 8) throw new IllegalArgumentException("i18n.ErrorMessage.NotZipString");
        text.replaceAll("-", "");
        String string1 = text.substring(0, 5);
        String string2 = text.substring(5);
        return String.format("%s-%s", string1, string2);
        }

        @Override
        public String removeFormat(String text) {
            return text.replaceAll("-", "");
        }
    }, CNPJ("##.###.###/####-##") {

        @Override
        public String format(String text) {
            String stringFormat = text;
            if (text.length() < 14) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
            stringFormat = text.replaceAll("\\.", "");
            stringFormat = stringFormat.replaceAll("-", "");
            stringFormat = stringFormat.replaceAll("/", "");
            String string1 = stringFormat.substring(0, 2);
            String string2 = stringFormat.substring(2, 5);
            String string3 = stringFormat.substring(5, 8);
            String string4 = stringFormat.substring(8, 12);
            String string5 = stringFormat.substring(12);
            return String.format("%s.%s.%s/%s-%s", string1, string2, string3,
                    string4, string5);
        }

        @Override
        public String removeFormat(String text) {
            text = text.replaceAll("\\.", "");
            text = text.replaceAll("-", "");
            text = text.replaceAll("/", "");
            return text;
        }
    }, INSCRICAO("##############") {

        @Override
        public String format(String text) {
            if (text.length() < 14) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
            return text;
        }

        @Override
        public String removeFormat(String text) {
            return text;
        }


    }, TELEFONE("(##) ####-####") {

        @Override
        public String format(String text) {
            String stringFormat = text;
            if (text.length() < 10) {
                return "";
            }
            stringFormat = text.replaceAll("\\(", "");
            stringFormat = stringFormat.replaceAll("\\)", "");
            stringFormat = stringFormat.replaceAll(" ", "");
            stringFormat = stringFormat.replaceAll("-", "");
            String string1 = stringFormat.substring(0, 2);
            String string2 = stringFormat.substring(2, 6);
            String string3 = stringFormat.substring(6);
            return String.format("(%s) %s-%s", string1, string2, string3);
        }

        @Override
        public String removeFormat(String text) {
            text = text.replaceAll("\\(", "");
            text = text.replaceAll("\\)", "");
            text = text.replaceAll(" ", "");
            return text.replaceAll("-", "");
        }
    };
    protected String validate;

    private MaskUtils(String mask) {
        this.validate = mask;
    }

    public MaskFormatter getMaskFormatter() throws ParseException {
        return new MaskFormatter(this.validate);
    }

    public DefaultFormatterFactory getDefaultFormatterFactory() throws ParseException {
        return new javax.swing.text.DefaultFormatterFactory(this.getMaskFormatter());
    }

    public JFormattedTextField getJFormattedTextField() throws ParseException {
        return new JFormattedTextField(this.getDefaultFormatterFactory());
    }

    abstract public String format(String text);

    abstract public String removeFormat(String text);

    @Override
    public String toString() {
        return this.validate;
    }
}
