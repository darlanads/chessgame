/*
 * @(#)BispoBrancoGUI.java 1.0 11/10/2005
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
 * Classe que representa o bispo branco do xadrez.
 * 
 * @version 1.0 11/10/2005
 * @author Darlan A. dos Santos
 */
public class BispoBrancoGUI extends AbstractPecaGUI{
	
	/**
 	* Construtor do bispo branco do xadrez.
 	* @param x abcissa
 	* @param y ordenada
 	*/
	public BispoBrancoGUI( int x, int y)
	{
		super(Global.IMG_BISPO_BRANCO);
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
		
		Vector casasTemp = this.getCasasEmAtaque(tabuleiro);
		
		// Verifica se a casa destino está contida numas das possíveis casas de 
		// movimento.
		if (casasTemp.contains(casaDestino))
		{	
			Peca pecaCasaDestino = casaDestino.getPeca();
			casaOrigem.removePeca();
			casaDestino.removePeca();
			casaDestino.setPeca(this);
			TabuleiroGUI tabuleiroTemp = (TabuleiroGUI)tabuleiro;
			tabuleiroTemp.removeAllAtaques();
			tabuleiroTemp.atualizaAtaqueBranco();
			tabuleiroTemp.atualizaAtaquePreto();
			if (tabuleiroTemp.isChequeMatePreto()){
				((TabuleiroGUI)tabuleiro).mensagemFimJogo(
						"Cheque Mate! Branco Vence!");
			} else if (tabuleiroTemp.isChequeBranco()){
				casaOrigem.removePeca();
				casaOrigem.setPeca(this);
				casaDestino.removePeca();
				casaDestino.setPeca(pecaCasaDestino);
				throw new PosicaoInvalidaException(
						"Em xeque possição inválida");
			}
			
		} else{
			throw new PosicaoInvalidaException("Bispo -> Posição inválida!");
		}
		
	}
	
	/**
	 * @see com.darlan.xadrez.gui.AbstractPecaGUI#setCasasEmAtaque(Tabuleiro)
	 */
	public void setCasasEmAtaque(Tabuleiro tabuleiro){
		// seta ataque para diagonal inferior direita
		Vector casasTemp = this.getCasasVisiveis(tabuleiro);
		Iterator it = casasTemp.iterator();
		while(it.hasNext()){
			((CasaGUI)it.next()).setAtaqueBranco(true);
		}
		
	}
	
	/**
	 * @see com.darlan.xadrez.gui.AbstractPecaGUI#getCasasVisiveis(Tabuleiro)
	 */
	public Vector getCasasVisiveis(Tabuleiro tabuleiro)
	{
		
		super.casasVisiveis = new Vector();
		// seta ataque para diagonal inferior direita
		int cont = 1;
		
		while(tabuleiro.getCasa(x+cont, y+cont)!=null)
		{
			if (tabuleiro.getCasa(x+cont, y+cont).temPeca()){
				casasVisiveis.add((CasaGUI)tabuleiro.getCasa(x+cont,y+cont));
				break;
			}else
				casasVisiveis.add((CasaGUI)tabuleiro.getCasa(x+cont,y+cont));
			cont++;
		}
		
		// set ataque para diagonal inferior esquerda
		cont = 1;
		while(tabuleiro.getCasa(x-cont, y+cont)!=null) 
		{
			if (tabuleiro.getCasa(x-cont,y+cont).temPeca()){
				casasVisiveis.add((CasaGUI)tabuleiro.getCasa(x-cont,y+cont));
				break;
				
			}else 
				casasVisiveis.add((CasaGUI)tabuleiro.getCasa(x-cont,y+cont));
			cont++;
		}
		
		// set ataque para diagonal superior esquerda
		cont = 1;
		while(tabuleiro.getCasa(x-cont, y-cont)!=null)
		{
			if (tabuleiro.getCasa(x-cont,y-cont).temPeca()){
			
				casasVisiveis.add((CasaGUI)tabuleiro.getCasa(x-cont,y-cont));
					break;
			} else
				casasVisiveis.add((CasaGUI)tabuleiro.getCasa(x-cont,y-cont));
			cont++;
		}
		
		// set ataque para diagonal superior direita
		cont = 1;
		while(tabuleiro.getCasa(x+cont, y-cont)!=null)
		{
			if (tabuleiro.getCasa(x+cont,y-cont).temPeca()){
				casasVisiveis.add((CasaGUI)tabuleiro.getCasa(x+cont,y-cont));
				break;
			} else
				casasVisiveis.add((CasaGUI)tabuleiro.getCasa(x+cont,y-cont));
			cont++;
		}
			
		return casasVisiveis;
	}
	
	/**
	 * @see com.darlan.xadrez.gui.AbstractPecaGUI#getCasasEmAtaque(Tabuleiro)
	 */
	public Vector getCasasEmAtaque(Tabuleiro tabuleiro)
	{
		// possíveis casas que o bispo pode ocupar.
		super.casasEmAtaque = new Vector();
		
		// verifica se pode mover para diagonal inferior direita
		int cont = 1;
		boolean encontrou = false;
		while(tabuleiro.getCasa(x+cont, y+cont)!=null)
		{
			if (tabuleiro.getCasa(x+cont,y+cont).getPeca()!=null){
				if (tabuleiro.getCasa(x+cont, y+cont).getPeca()
							 .getCor()==Color.BLACK){
					casasEmAtaque.add(tabuleiro.getCasa(x+cont,y+cont));
					break;
				}
				if (tabuleiro.getCasa(x+cont,y+cont).getPeca()
							 .getCor()==Color.WHITE){
					break;
				}
			}
			casasEmAtaque.add(tabuleiro.getCasa(x+cont,y+cont));
			cont++;
		}
		
		// verifica se pode mover para diagonal inferior esquerda
		cont = 1;
		while(tabuleiro.getCasa(x-cont, y+cont)!=null) 
		{
			if (tabuleiro.getCasa(x-cont,y+cont).getPeca()!=null){
				if (tabuleiro.getCasa(x-cont, y+cont).getPeca()
							 .getCor()==Color.BLACK){
					casasEmAtaque.add(tabuleiro.getCasa(x-cont,y+cont));
					break;
				}
				if (tabuleiro.getCasa(x-cont,y+cont).getPeca()
							 .getCor()==Color.WHITE){
					break;
				}
			}
			casasEmAtaque.add(tabuleiro.getCasa(x-cont,y+cont));
			cont++;
		}
		
		// verifica se pode mover para diagonal superior esquerda
		cont = 1;
		while(tabuleiro.getCasa(x-cont, y-cont)!=null)
		{
			if (tabuleiro.getCasa(x-cont,y-cont).getPeca()!=null){
			
				if (tabuleiro.getCasa(x-cont, y-cont).getPeca()
							 .getCor()==Color.BLACK){
					casasEmAtaque.add(tabuleiro.getCasa(x-cont,y-cont));
					break;
				}
				if (tabuleiro.getCasa(x-cont,y-cont).getPeca()
							 .getCor()==Color.WHITE){
					break;
				}
			}
			casasEmAtaque.add(tabuleiro.getCasa(x-cont,y-cont));
			cont++;
		}
		
		// verifica se pode mover para diagonal superior direita
		cont = 1;
		while(tabuleiro.getCasa(x+cont, y-cont)!=null)
		{
			if (tabuleiro.getCasa(x+cont,y-cont).getPeca()!=null){
			
				if (tabuleiro.getCasa(x+cont, y-cont).getPeca()
							 .getCor()==Color.BLACK){
					casasEmAtaque.add(tabuleiro.getCasa(x+cont,y-cont));
					break;
				}
				if (tabuleiro.getCasa(x+cont,y-cont).getPeca()
							 .getCor()==Color.WHITE){
					break;
				}
			}
			casasEmAtaque.add(tabuleiro.getCasa(x+cont,y-cont));
			cont++;
		}
		return casasEmAtaque;
			
	}
	
	/**
	 * @see com.darlan.xadrez.gui.AbstractPecaGUI#isVisivel(Tabuleiro, Casa)
	 */
	public boolean isVisivel(Tabuleiro tabuleiro, Casa casa){
		Vector casaTemp = getCasasVisiveis(tabuleiro);
		if (casaTemp.contains(casa)){
			return true;
		}
		return false;
	}
	
}
