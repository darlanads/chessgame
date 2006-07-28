/*
 * @(#)CavaloBrancoGUI.java 1.0 11/10/2005
 *
 * Darlan A. dos Santos -> darlan_ads@yahoo.com.br
 *
 * Copyright 2005 D & D Desenvolvimento Software.
 */
package com.darlan.xadrez.gui;

import java.awt.Color;
import java.util.Iterator;
import java.util.Vector;

import com.darlan.util.Global;
import com.darlan.xadrez.Casa;
import com.darlan.xadrez.Peca;
import com.darlan.xadrez.PosicaoInvalidaException;
import com.darlan.xadrez.Tabuleiro;


/**
 * Classe que representa o cavalo branco do xadrez.
 * 
 * @version 1.0 11/10/2005
 * @author Darlan A. dos Santos
 */
public class CavaloBrancoGUI extends AbstractPecaGUI{
	
	/**
 	* Construtor
 	* @param x Abcissa
 	* @param y Ordenada
 	*/
	public CavaloBrancoGUI(int x, int y)
	{
		super(Global.IMG_CAVALO_BRANCO);
		super.x = x;
		super.y = y;
		super.cor = Color.WHITE;
	}
	
	/**
	 * @see com.darlan.xadrez.gui.AbstractPecaGUI#move(Casa, Casa, Tabuleiro)
	 */
	public void move(Casa casaOrigem, Casa casaDestino, Tabuleiro tabuleiro) 
			throws PosicaoInvalidaException
	{

		Vector temp = this.getCasasEmAtaque(tabuleiro);
		
		if (temp.contains(casaDestino))
		{
			Peca pecaCasaDestino = casaDestino.getPeca();
			casaOrigem.removePeca();
			casaDestino.removePeca();
			casaDestino.setPeca(this);
			TabuleiroGUI tabuleiroTemp = (TabuleiroGUI)tabuleiro;
			tabuleiroTemp.removeAllAtaques();
			tabuleiroTemp.atualizaAtaquePreto();
			tabuleiroTemp.atualizaAtaqueBranco();
			if (tabuleiroTemp.isChequeMatePreto()){
				((TabuleiroGUI)tabuleiro).mensagemFimJogo(
						"Cheque Mate! Branco Vence!");
			}else if (tabuleiroTemp.isChequeBranco()){
				casaOrigem.removePeca();
				casaOrigem.setPeca(this);
				casaDestino.removePeca();
				casaDestino.setPeca(pecaCasaDestino);
				throw new PosicaoInvalidaException(
						"Em xeque possição inválida");
			}
		}else{
			throw new PosicaoInvalidaException("Bispo -> Posição inválida!");
		}
		
	}
	
	/**
	 * @see com.darlan.xadrez.gui.AbstractPecaGUI#setCasasEmAtaque(Tabuleiro)
	 */
	public void setCasasEmAtaque(Tabuleiro tabuleiro)
	{
		Vector casasTemp = new Vector();
		// 8 jogadas do cavalo
		casasTemp.add(tabuleiro.getCasa(x-1,y-2));
		casasTemp.add(tabuleiro.getCasa(x+1,y-2));
		casasTemp.add(tabuleiro.getCasa(x-2,y+1));
		casasTemp.add(tabuleiro.getCasa(x-2,y-1));
		casasTemp.add(tabuleiro.getCasa(x+2,y+1));
		casasTemp.add(tabuleiro.getCasa(x+2,y-1));
		casasTemp.add(tabuleiro.getCasa(x-1,y+2));
		casasTemp.add(tabuleiro.getCasa(x+1,y+2));
		
		Iterator it = casasTemp.iterator();
		Casa casaTemp;
		while (it.hasNext()){
			casaTemp = (Casa)it.next();
			if (casaTemp!=null){
				((CasaGUI)casaTemp).setAtaqueBranco(true);
			}		
		}
	}

	/**
	 * @see com.darlan.xadrez.gui.AbstractPecaGUI#getCasasVisiveis(Tabuleiro)
	 */
	public Vector getCasasVisiveis(Tabuleiro tabuleiro) {
		super.casasVisiveis = new Vector();
		Vector casasTemp = new Vector();
		// 8 jogadas do cavalo
		casasTemp.add(tabuleiro.getCasa(x-1,y-2));
		casasTemp.add(tabuleiro.getCasa(x+1,y-2));
		casasTemp.add(tabuleiro.getCasa(x-2,y+1));
		casasTemp.add(tabuleiro.getCasa(x-2,y-1));
		casasTemp.add(tabuleiro.getCasa(x+2,y+1));
		casasTemp.add(tabuleiro.getCasa(x+2,y-1));
		casasTemp.add(tabuleiro.getCasa(x-1,y+2));
		casasTemp.add(tabuleiro.getCasa(x+1,y+2));
		
		Iterator it = casasTemp.iterator();
		Casa casaTemp;
		while (it.hasNext()){
			casaTemp = (Casa)it.next();
			if (casaTemp!=null){
				casasVisiveis.add(casaTemp);
			}		
		}
		return casasVisiveis;
	}

	/**
	 * @see com.darlan.xadrez.gui.AbstractPecaGUI#getCasasEmAtaque(Tabuleiro)
	 */
	public Vector getCasasEmAtaque(Tabuleiro tabuleiro) {
		super.casasEmAtaque = new Vector();
		Vector casasTemp = new Vector();
		// 8 jogadas do cavalo
		casasTemp.add(tabuleiro.getCasa(x-1,y-2));
		casasTemp.add(tabuleiro.getCasa(x+1,y-2));
		casasTemp.add(tabuleiro.getCasa(x-2,y+1));
		casasTemp.add(tabuleiro.getCasa(x-2,y-1));
		casasTemp.add(tabuleiro.getCasa(x+2,y+1));
		casasTemp.add(tabuleiro.getCasa(x+2,y-1));
		casasTemp.add(tabuleiro.getCasa(x-1,y+2));
		casasTemp.add(tabuleiro.getCasa(x+1,y+2));
		
		Iterator it = casasTemp.iterator();
		Casa casaTemp;
		while (it.hasNext()){
			casaTemp = (Casa)it.next();
			if (casaTemp!=null){
				if (casaTemp.temPeca() && (casaTemp.getPeca()
												   .getCor()==Color.BLACK)){
					casasEmAtaque.add(casaTemp);
				}else if(!casaTemp.temPeca()){
					casasEmAtaque.add(casaTemp);
				}
			}		
		}
		return casasEmAtaque;
	}

	/** 
	 * @see com.darlan.xadrez.gui.AbstractPecaGUI#isVisivel(Tabuleiro, Casa)
	 */
	public boolean isVisivel(Tabuleiro tabuleiro, Casa casa) {
		Vector temp = this.getCasasVisiveis(tabuleiro);
		if (temp.contains(casa))
			return true;
		return false;
	}
}
