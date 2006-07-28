/*
 * @(#)Tabuleiro.java 1.0 11/10/2005
 *
 * Darlan A. dos Santos -> darlan_ads@yahoo.com.br
 *
 * Copyright 2005 D & D Desenvolvimento Software.
 */
package com.darlan.xadrez;

/**
 * Interface que representa o tabuleiro do jogo.
 * 
 * @version 1.0 11/10/2005
 * @author Darlan A. dos Santos
 */
public interface Tabuleiro {
	
	/**
	 * Dada as coordenadas o tabuleiro retorna o casa.
	 * @param x Abcissa.
	 * @param y Ordenada.
	 * @return retorna a casa possicionada nas coordenadas.
	 */
	public Casa getCasa(int x, int y);
	
}
