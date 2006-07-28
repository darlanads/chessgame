/*
 * @(#)CasaGUI.java 1.0 11/10/2005
 *
 * Darlan A. dos Santos -> darlan_ads@yahoo.com.br
 *
 * Copyright 2005 D & D Desenvolvimento Software.
 */
package com.darlan.xadrez.gui;

import java.awt.Color;

import javax.swing.JComponent;
import javax.swing.JLabel;

import com.darlan.util.CarregaImagemUtil;
import com.darlan.util.Global;
import com.darlan.xadrez.Casa;
import com.darlan.xadrez.Peca;

/**
 * Classe que representa a casa do xadrez.
 * 
 * @version 1.0 11/10/2005
 * @author Darlan A. dos Santos
 */
public class CasaGUI extends JLabel implements Casa{
	
	// atributos.
	private boolean ataquePreto = false, ataqueBranco = false;
	private boolean passante = false;
	private Color cor;
	private int x;
	private int y;
	private Peca peca = null;
	private JLabel bordaJLabel;
	
	/**
	 * Construtor da casa gráfica
	 * @param x Posição no tabuleiro. Abscissa
	 * @param y Posição no tabuleiro. Ordenada
	 * @param cor Cor da casa. Cores possíveis: Branca e Preta.
	 */
	public CasaGUI(int x, int y, Color cor)
	{
		this.x = x;
		this.y = y;
		this.cor = cor;
		bordaJLabel  = new JLabel(CarregaImagemUtil.createImageIcon(
								Global.IMG_CASA_BORDA));
		if (cor == Color.WHITE){
			this.setIcon(CarregaImagemUtil.createImageIcon(
					Global.IMG_CASA_BRANCA));
		}
		else
			this.setIcon(CarregaImagemUtil.createImageIcon(
					Global.IMG_CASA_PRETA));
		super.setBounds(50*(x),50*(y),50,50);
	}
	
	/**
	 * Adiciona a borda na casa.
	 */
	public void pintaBorda()
	{
		super.add(bordaJLabel).setBounds(0,0,50,50);
		super.repaint();
	}
	
	/**
	 * Remove a borda da casa.
	 */
	public void removeBorda()
	{
		for (int i = 0; i < super.getComponentCount(); i++)
			if (bordaJLabel==super.getComponent(i))
			{
				super.remove(getComponent(i));
			}
		super.repaint();
	}
	
	/**
	 * @see com.darlan.xadrez.Casa#getCor()
	 */
	public Color getCor()
	{
		return cor;
	}
	
	/**
	 * @see com.darlan.xadrez.Casa#setCor(Color)
	 */
	public void setCor(Color novaCor)
	{
		this.cor = novaCor;
	}
	
	/**
	 * @see com.darlan.xadrez.Casa#getPosicaoX()
	 */
	public int getPosicaoX()
	{
		return x;
	}
	
	/**
	 * @see com.darlan.xadrez.Casa#setPosicaoX(int)
	 */
	public void setPosicaoX(int novaAbcissa)
	{
		this.x = novaAbcissa;
	}
	
	/**
	 * @see com.darlan.xadrez.Casa#getPosicaoY()
	 */
	public int getPosicaoY()
	{
		return y;
	}
	
	/**
	 * @see com.darlan.xadrez.Casa#setPosicaoY(int)
	 */
	public void setPosicaoY(int novaOrdenada)
	{
		this.y = novaOrdenada;
	}
	
	/**
	 * @see com.darlan.xadrez.Casa#getPeca()
	 */
	public Peca getPeca()
	{
		return peca;
	}
	
	/**
	 * @see com.darlan.xadrez.Casa#setPeca(Peca)
	 */
	public void setPeca(Peca novaPeca)
	{
		if (novaPeca !=null){
			novaPeca.setPosicaoX(x);
			novaPeca.setPosicaoY(y);
			this.peca = novaPeca;
			super.add((JComponent)peca).setBounds(12,0,50,50);
			super.repaint();
		} else{
			peca = novaPeca;
		}
	}
	
	/**
	 * @see com.darlan.xadrez.Casa#removePeca()
	 */
	public void removePeca()
	{
		peca = null;
		super.removeAll();
		super.repaint();
	}
	
	/**
	 * @see com.darlan.xadrez.Casa#temPeca()
	 */
	public boolean temPeca()
	{
		return (peca!=null)?true:false;
	}
	
	/**
	 * @see com.darlan.xadrez.Casa#temPeca(Color) 
	 */
	public boolean temPeca(Color cor)
	{
		if (peca == null)
			return false;
		return (peca.getCor()==cor)?true:false;
	}
	
	/**
	 * Pergunta se a casa está sendo atacada por uma peça preta.
	 * @return boolean
	 */
	public boolean isAtaquePreto()
	{
		return ataquePreto;
	}
	
	/**
	 * Pergunta se a casa está sendo atacada por uma peça branca.
	 * @return boolean
	 */
	public boolean isAtaqueBranco()
	{
		return ataqueBranco;
	}
	
	/**
	 * Remove o status de ataque branco e preto. 
	 */
	public void removeAllAtaques()
	{
		ataquePreto = false;
		ataqueBranco = false;
	}
	
	/**
	 * Informa a casa quye ela está sendo atacada por uma peça preta.
	 * @param ataque boolean
	 */
	public void setAtaquePreto(boolean ataque)
	{
		ataquePreto = ataque;
	}
	
	/**
	 * Informa a casa que ela está sendo atacada por uma peça branca
	 * @param ataque boolean
	 */
	public void setAtaqueBranco(boolean ataque)
	{
		ataqueBranco = ataque;
	}
	
	/**
	 * @see com.darlan.xadrez.Casa#getPassante()
	 */
	public boolean getPassante()
	{
		return passante;
	}
	
	/**
	 * @see com.darlan.xadrez.Casa#setPassante(boolean)
	 */
	public void setPassante(boolean passante)
	{
		this.passante = passante;
	}
	
	/**
	 * Verifica se casa é igual.
	 * @param casa Casa
	 * @return boolean
	 */
	public boolean equals(Casa casa)
	{
		return (this.x==casa.getPosicaoX() 
				&& this.y == casa.getPosicaoY())?true:false;
	}
	
	/**
	 * Pega o String da casa. Que tem o seguinte modelo -> Casa = (x,y)
	 */
	public String toString()
	{
		return "Casa = ("+x+","+y+")";
	}
	
}
