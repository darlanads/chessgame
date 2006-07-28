/*
 * @(#)PosicaoInvalidaException.java 1.0 11/10/2005
 *
 * Darlan A. dos Santos -> darlan_ads@yahoo.com.br
 *
 * Copyright 2005 D & D Desenvolvimento Software.
 */
package com.darlan.xadrez;

/**
 * Classe de exe��o que � lan�ada quando uma pe�a realiza um movimento inv�lido.
 * 
 * @version 1.0 11/10/2005
 * @author Darlan A. dos Santos
 */
public class PosicaoInvalidaException extends Exception {

	/**
	 * Construtor com uma mensagem.
	 * @param mensagem a mensagem da exce��o.
	 */
	public PosicaoInvalidaException(String mensagem){
		super(mensagem);
	}

}