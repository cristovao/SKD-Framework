/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ybacoby.skdframework;

import java.awt.*;

/**
 * Esta classe facilta a utilização da classe GridBagConstraints
 *
 */

public class GBC extends GridBagConstraints {

   /**
    * Constrói um GBC com uma determinada posição de gradex e gradey e todos os outros valores de restrição
    * de saco grande definidos como padrão;
    * @param gridx a posição de gridx
    * @param gridy a posição de gridy
    */

   public GBC( int gridx, int gridy ){
      this.gridx = gridx;
      this.gridy = gridy;
   }

   /**
    * Contrói um GBC com um gridx, gridy, largura de grade, altura de grade e todos os outros valores de restrição
    * de saco grande definidos como padrão;
    * @param gridx a posição de gridx
    * @param gridy a posição de gridy
    * @param gridwidth a celula se espalha na direção x
    * @param gridheight a celula se espalha na direção y
    */

   public GBC( int gridx, int gridy, int gridwidth, int gridheight ){
      this.gridx = gridx;
      this.gridy = gridy;
      this.gridwidth = gridwidth;
      this.gridheight = gridheight;
   }

   /**
    * Define a âncora;
    * @param anchor o valor da âncora
    * @return devolve este objeto para modificações
    */

   public GBC setAnchor( int anchor ){
      this.anchor = anchor;
      return this;
   }

   /**
    * Define a direção de preenchimento;
    * @param fill direção de preenchimento
    * @return retorna este objeto para modificações
    */

   public GBC setFill( int fill ){
      this.fill = fill;
      return this;
   }

   /**
    * Define os pesos das celulas;
    * @param weightx peso da celula na direção x
    * @param weighty peso da celula na direção y
    * @return retorna este objeto para modificações
    */

   public GBC setWeight( double weightx, double weighty ){
      this.weightx = weightx;
      this.weighty = weighty;
      return this;
   }

   /**
    * Define o espaço das celulas;
    * @param distance espaçamento a ser utilizado em todas as direções
    * @return retorna este objeto para modificações
    */

   public GBC setInsets( int distance ){
      this.insets = new Insets( distance, distance, distance, distance );
      return this;
   }

   /**
    * Define o espaço das celulas;
    * @param top o espaçamento a ser usado na parte superior
    * @param left o espaçamento a ser usado na parte à esquerda
    * @param bottom o espaçamento a ser usado na parte inferior
    * @param right o espaçamento a ser usado na parte à direita
    * @return este objeto para modificações
    */

   public GBC setInsets( int top, int left, int bottom, int right ){
      this.insets = new Insets( top, left, bottom, right );
      return this;
   }

   /**
    * Define o preenchimento interno;
    * @param ipadx preenchimento interno na direção x
    * @param ipady preenchimento interno na direção y
    * @return este objeto para modificações
    */

   public GBC setIpad( int ipadx, int ipady ){
      this.ipadx = ipadx;
      this.ipady = ipady;
      return this;
   }

   public static GridBagLayout getLayout() {
       return new GridBagLayout();
   }
}

