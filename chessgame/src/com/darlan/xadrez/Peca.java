/*
 * @(#)Peca.java 1.0 11/10/2005
 *
 * Darlan A. dos Santos -> darlan_ads@yahoo.com.br
 *
 * Copyright 2005 D & D Desenvolvimento Software.
 */
package com.darlan.xadrez;

import java.awt.*;
import java.util.*;


/**
 * Interface que representa a peça do tabuleiro.
 * 
 * @version 1.0 11/10/2005
 * @author Darlan A. dos Santos
 */
public interface Peca{
	
	/**
	 * Modifica a Cor da peça
	 * @param novaCor
	 */
	public void setCor(Color novaCor);
	
	/**
	 * Pega a cor da peça
	 * @return retorna a cor da peça.
	 */
	public Color getCor();
	
	/**
	 * Pega a posição abcissa da peça no tabuleiro
	 * @return int
	 */
	public int getPosicaoX();
	
	/**
	 * Pega a posição ordenada da peça no tabuleiro.
	 * @return int
	 */
	public int getPosicaoY();
		
	/**
	 * Modifica a abcissa da peça
	 * @param novaAbscissa
	 */
	public void setPosicaoX(int novaAbscissa);
	
	/**
	 * Modifica a ordenada da peça
	 * @param novaOrdenada
	 */
	public void setPosicaoY(int novaOrdenada);

	/**
	 * Informa ao tabuleiro quais casas a peça está atacando
	 * @param tabuleiro
	 */
	public void setCasasEmAtaque(Tabuleiro tabuleiro);
	
	/**
	 * Pega do tabuleiro as casa que são visiveis a peça
	 * @param tabuleiro
	 * @return Vector Casas visiveis
	 */
	public Vector getCasasVisiveis(Tabuleiro tabuleiro);
	
	/**
	 * Pega as casa que a peça está atacando.
	 * @param tabuleiro
	 * @return Vector Retorna as casa que a peça está atacando.
	 */
	public Vector getCasasEmAtaque(Tabuleiro tabuleiro);
	
	/**
	 * Verifica se a casa é visível a peça no tabuleiro.
	 * @param tabuleiro
	 * @param casa
	 * @return boolean
	 */
	public boolean isVisivel(Tabuleiro tabuleiro, Casa casa);

	/**
	 * Move a peça para uma nova posição.
	 * @param casaOrigem Casa em que a peça está localizada.
	 * @param casaDestino Case em que a peça será movida.
	 * @throws PosicaoInvalidaException
	 */
	public void move(Casa casaOrigem, Casa casaDestino, Tabuleiro tabuleiro) throws PosicaoInvalidaException;

}
