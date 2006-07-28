/*
 * @(#)AbstractPecaGUI.java 1.0 11/10/2005
 *
 * Darlan A. dos Santos -> darlan_ads@yahoo.com.br
 *
 * Copyright 2005 D & D Desenvolvimento Software.
 */
package com.darlan.xadrez.gui;


import java.awt.Color;
import java.util.Vector;

import javax.swing.JLabel;

import com.darlan.util.CarregaImagemUtil;
import com.darlan.xadrez.Casa;
import com.darlan.xadrez.Peca;
import com.darlan.xadrez.PosicaoInvalidaException;
import com.darlan.xadrez.Tabuleiro;

/**
 * Classe abstrata que representa a peça do xadrez.
 * 
 * @version 1.0 11/10/2005
 * @author Darlan A. dos Santos
 */
public abstract class AbstractPecaGUI extends JLabel implements Peca{
	
	// atributos
	protected Vector casasVisiveis;
	protected Vector casasEmAtaque;
	protected Color cor;
	protected int x;
	protected int y;

	/**
	 * Construtor.
	 * @param path Caminho da imagem.
	 */
	protected AbstractPecaGUI(String path){
		super.setIcon(CarregaImagemUtil.createImageIcon(path));
	}
	
	/**
	 * @see com.darlan.xadrez.Peca#getCor()
	 */
	public Color getCor()
	{
		return cor;
	}
	
	/**
	 * @see com.darlan.xadrez.Peca#setCor(Color)
	 */
	public void setCor(Color novaCor)
	{
		cor = novaCor;
	}
	
	/**
	 * @see com.darlan.xadrez.Peca#getPosicaoX()
	 */
	public int getPosicaoX()
	{
		return x;
	}
	
	/**
	 * @see com.darlan.xadrez.Peca#setPosicaoX(int) 
	 */
	public void setPosicaoX(int novaAbcissa)
	{
		this.x = novaAbcissa;
	}
	
	/**
	 * @see com.darlan.xadrez.Peca#getPosicaoY()
	 */
	public int getPosicaoY()
	{
		return y;
	}
	
	/**
	 * @see com.darlan.xadrez.Peca#setPosicaoY(int)
	 */
	public void setPosicaoY(int novaOrdenada)
	{
		this.y = novaOrdenada;
	}
	
	/**
	 * @see com.darlan.xadrez.Peca#move(Casa, Casa, Tabuleiro)
	 */
	public abstract void move(Casa casaOrigem, Casa casaDestino, 
			Tabuleiro tabuleiro) throws PosicaoInvalidaException;
	
	/**
	 * @see com.darlan.xadrez.Peca#getCasasVisiveis(Tabuleiro)
	 */
	public abstract Vector getCasasVisiveis(Tabuleiro tabuleiro);
	
	/**
	 * @see com.darlan.xadrez.Peca#getCasasEmAtaque(Tabuleiro)
	 */
	public abstract Vector getCasasEmAtaque(Tabuleiro tabuleiro);
	
	/**
	 * @see com.darlan.xadrez.Peca#setCasasEmAtaque(Tabuleiro)
	 */
	public abstract void setCasasEmAtaque(Tabuleiro tabuleiro);
	
	/**
	 * @see com.darlan.xadrez.Peca#isVisivel(Tabuleiro, Casa)
	 */
	public abstract boolean isVisivel(Tabuleiro tabuleiro, Casa casa);
	
}
