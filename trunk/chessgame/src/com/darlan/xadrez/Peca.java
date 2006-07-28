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
 * Interface que representa a pe�a do tabuleiro.
 * 
 * @version 1.0 11/10/2005
 * @author Darlan A. dos Santos
 */
public interface Peca{
	
	/**
	 * Modifica a Cor da pe�a
	 * @param novaCor
	 */
	public void setCor(Color novaCor);
	
	/**
	 * Pega a cor da pe�a
	 * @return retorna a cor da pe�a.
	 */
	public Color getCor();
	
	/**
	 * Pega a posi��o abcissa da pe�a no tabuleiro
	 * @return int
	 */
	public int getPosicaoX();
	
	/**
	 * Pega a posi��o ordenada da pe�a no tabuleiro.
	 * @return int
	 */
	public int getPosicaoY();
		
	/**
	 * Modifica a abcissa da pe�a
	 * @param novaAbscissa
	 */
	public void setPosicaoX(int novaAbscissa);
	
	/**
	 * Modifica a ordenada da pe�a
	 * @param novaOrdenada
	 */
	public void setPosicaoY(int novaOrdenada);

	/**
	 * Informa ao tabuleiro quais casas a pe�a est� atacando
	 * @param tabuleiro
	 */
	public void setCasasEmAtaque(Tabuleiro tabuleiro);
	
	/**
	 * Pega do tabuleiro as casa que s�o visiveis a pe�a
	 * @param tabuleiro
	 * @return Vector Casas visiveis
	 */
	public Vector getCasasVisiveis(Tabuleiro tabuleiro);
	
	/**
	 * Pega as casa que a pe�a est� atacando.
	 * @param tabuleiro
	 * @return Vector Retorna as casa que a pe�a est� atacando.
	 */
	public Vector getCasasEmAtaque(Tabuleiro tabuleiro);
	
	/**
	 * Verifica se a casa � vis�vel a pe�a no tabuleiro.
	 * @param tabuleiro
	 * @param casa
	 * @return boolean
	 */
	public boolean isVisivel(Tabuleiro tabuleiro, Casa casa);

	/**
	 * Move a pe�a para uma nova posi��o.
	 * @param casaOrigem Casa em que a pe�a est� localizada.
	 * @param casaDestino Case em que a pe�a ser� movida.
	 * @throws PosicaoInvalidaException
	 */
	public void move(Casa casaOrigem, Casa casaDestino, Tabuleiro tabuleiro) throws PosicaoInvalidaException;

}
