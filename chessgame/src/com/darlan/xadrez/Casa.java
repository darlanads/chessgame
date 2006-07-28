/*
 * @(#)Casa.java 1.0 11/10/2005
 *
 * Darlan A. dos Santos -> darlan_ads@yahoo.com.br
 *
 * Copyright 2005 D & D Desenvolvimento Software.
 */
package com.darlan.xadrez;

import java.awt.Color;

/**
 * Interface que representa a casa do tabuleiro.
 * 
 * @version 1.0 11/10/2005
 * @author Darlan A. dos Santos
 */
public interface Casa {
	
	/**
	 * Pega a abscissa da casa.
	 * @return int A posi��o abcissa da casa.
	 */
	public int getPosicaoX();
	
	/**
	 * Pega a ordenada da casa
	 * @return int A ordenada da casa.
	 */
	public int getPosicaoY();
	
	
	/**
	 * Pega a cor da casa
	 * @return int Retorna a cor da casa (Cor.BRANCO , Cor.PRETO)
	 */
	public Color getCor();
	
	/**
	 * Modifica a cor da casa
	 * @param novaCor
	 */
	public void setCor(Color novaCor);
	
	/**
	 * Modifica a abscissa da casa
	 * @param novaAbscissa
	 */
	public void setPosicaoX(int novaAbscissa);
	
	
	/**
	 * Modifica a ordenada da casa
	 * @param novaOrdenada
	 */
	public void setPosicaoY(int novaOrdenada);
	
	
	/**
	 * Modifica a pe�a que vai ocupar a casa
	 * @param novaPeca
	 */
	public void setPeca(Peca novaPeca);
	
	
	/**
	 * Remove a pe�a da casa
	 */
	public void removePeca();
	
	
	/**
	 * Pega a pe�a que est� ocupando a casa
	 * @return Peca Retorna a pe�a que est� ocupando a pe�a
	 */
	public Peca getPeca();
	
	
	/**
	 * Verifica se a casa tem pe�a 
	 * @return boolean.
	 */
	public boolean temPeca();

	/**
	 * Verifica se a casa tem uma pe�a da seguinte cor.
	 * @param cor Cor da pe�a
	 * @return boolean
	 */
	public boolean temPeca(Color cor);
	
	/**
	 * Verifica se a casa � igual. 
	 * @param casa A casa do tabuleiro.
	 * @return <code>true</code> se a casa for igual.
	 */	
	public boolean equals(Casa casa);
	
	/**
	 * Verifica se essa casa faz parte do passante.
	 * @return <code>true</code> se a casa faz parte do passante.
	 */
	public boolean getPassante();
	
	/**
	 * Seta essa casa como passante.
	 * @param passante <code>true</code> indica que a casa esta na zona do 
	 * passante.
	 */
	public void setPassante(boolean passante);

}
