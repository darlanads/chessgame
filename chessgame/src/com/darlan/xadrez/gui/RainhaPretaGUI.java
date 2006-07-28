/*
 * @(#)RainhaPretaGUI.java 1.0 11/10/2005
 *
 * Darlan A. dos Santos -> darlan_ads@yahoo.com.br
 *
 * Copyright 2005 D & D Desenvolvimento Software.
 */
package com.darlan.xadrez.gui;

import java.awt.Color;
import java.util.Vector;

import javax.swing.ImageIcon;

import com.darlan.util.Global;
import com.darlan.xadrez.Casa;
import com.darlan.xadrez.Peca;
import com.darlan.xadrez.PosicaoInvalidaException;
import com.darlan.xadrez.Tabuleiro;


/**
 * Classe que representa a rainha preta do xadrez.
 * 
 * @version 1.0 11/10/2005
 * @author Darlan A. dos Santos
 */
public class RainhaPretaGUI extends AbstractPecaGUI{
	
	/**
	* Construtor 
	* @param x Abcissa
	* @param y Ordenada
	*/
	public RainhaPretaGUI(int x, int y)
	{
		super(Global.IMG_RAINHA_PRETA);
		super.x = x;
		super.y = y;
		super.cor = Color.BLACK;
	}
	/**
	 * @see com.darlan.xadrez.gui.AbstractPecaGUI#move(Casa, Casa, Tabuleiro)
	 */	
	public void move(Casa casaOrigem, Casa casaDestino, Tabuleiro tabuleiro) 
			throws PosicaoInvalidaException
	{
		Vector casasPossiveis = this.getCasasEmAtaque(tabuleiro);		
		// Verifica se a casa destino está contida numas das possíveis casas de 
		// movimento.
		if (casasPossiveis.contains(casaDestino))
		{
			Peca pecaTemp = casaDestino.getPeca();
			casaOrigem.removePeca();
			casaDestino.removePeca();
			casaDestino.setPeca(this);
			TabuleiroGUI tabuleiroTemp = (TabuleiroGUI)tabuleiro;
			tabuleiroTemp.removeAllAtaques();
			tabuleiroTemp.atualizaAtaquePreto();
			tabuleiroTemp.atualizaAtaqueBranco();
			
			if (tabuleiroTemp.isChequeMateBranco()){
				((TabuleiroGUI)tabuleiro).mensagemFimJogo(
						"Cheque Mate! Preto Vence!");
			} else if (tabuleiroTemp.isChequePreto()){
				casaOrigem.setPeca(this);
				casaDestino.removePeca();
				casaDestino.setPeca(pecaTemp);
				// atualiza tabuleiro
				tabuleiroTemp.removeAllAtaques();
				tabuleiroTemp.atualizaAtaquePreto();
				tabuleiroTemp.atualizaAtaqueBranco();
				
				
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
		int cont = 1;
		
		while(tabuleiro.getCasa(x+cont, y+cont)!=null)
		{
			if (tabuleiro.getCasa(x+cont, y+cont).temPeca()){
				((CasaGUI)tabuleiro.getCasa(x+cont,y+cont))
								   .setAtaquePreto(true);
				break;
			}else
				((CasaGUI)tabuleiro.getCasa(x+cont,y+cont))
								   .setAtaquePreto(true);
			cont++;
		}
		
		// set ataque para diagonal inferior esquerda
		cont = 1;
		while(tabuleiro.getCasa(x-cont, y+cont)!=null) 
		{
			if (tabuleiro.getCasa(x-cont,y+cont).temPeca()){
				((CasaGUI)tabuleiro.getCasa(x-cont,y+cont))
								   .setAtaquePreto(true);
				break;
				
			}else 
				((CasaGUI)tabuleiro.getCasa(x-cont,y+cont))
								   .setAtaquePreto(true);
			cont++;
		}
		
		// set ataque para diagonal superior esquerda
		cont = 1;
		while(tabuleiro.getCasa(x-cont, y-cont)!=null)
		{
			if (tabuleiro.getCasa(x-cont,y-cont).temPeca()){
			
				((CasaGUI)tabuleiro.getCasa(x-cont,y-cont))
								   .setAtaquePreto(true);
				break;
			} else
				((CasaGUI)tabuleiro.getCasa(x-cont,y-cont))
								   .setAtaquePreto(true);
			cont++;
		}
		
		// set ataque para diagonal superior direita
		cont = 1;
		while(tabuleiro.getCasa(x+cont, y-cont)!=null)
		{
			if (tabuleiro.getCasa(x+cont,y-cont).temPeca()){
				((CasaGUI)tabuleiro.getCasa(x+cont,y-cont))
								   .setAtaquePreto(true);
				break;
			} else
				((CasaGUI)tabuleiro.getCasa(x+cont,y-cont))
								   .setAtaquePreto(true);
			cont++;
		}
		
		//verifica se pode mover para baixo
		cont = 1;
		while(tabuleiro.getCasa(x, y+cont)!=null)
		{
			if (tabuleiro.getCasa(x,y+cont).temPeca()){
				((CasaGUI)tabuleiro.getCasa(x,y+cont)).setAtaquePreto(true);
				break;
			} else 
				((CasaGUI)tabuleiro.getCasa(x,y+cont)).setAtaquePreto(true);
			cont++;
		}
		
		// verifica se pode mover para esquerda
		cont = 1;
		while(tabuleiro.getCasa(x-cont, y)!=null) 
		{
			if (tabuleiro.getCasa(x-cont,y).temPeca()){
				((CasaGUI)tabuleiro.getCasa(x-cont,y)).setAtaquePreto(true);
				break;
			} else
				((CasaGUI)tabuleiro.getCasa(x-cont,y)).setAtaquePreto(true);
			cont++;
		}
		
		// verifica se pode mover para direita
		cont = 1;
		while(tabuleiro.getCasa(x+cont, y)!=null)
		{
			if (tabuleiro.getCasa(x+cont,y).temPeca()){
				((CasaGUI)tabuleiro.getCasa(x+cont,y)).setAtaquePreto(true);
				break;
			} else
				((CasaGUI)tabuleiro.getCasa(x+cont,y)).setAtaquePreto(true);
				cont++;
		}
		
		// verifica se pode mover para cima
		cont = 1;
		while(tabuleiro.getCasa(x, y-cont)!=null)
		{
			if (tabuleiro.getCasa(x,y-cont).temPeca()){
				((CasaGUI)tabuleiro.getCasa(x,y-cont)).setAtaquePreto(true);
				break;
			} else
				((CasaGUI)tabuleiro.getCasa(x,y-cont)).setAtaquePreto(true);
			cont++;
		}
		
			
	}

	/**
	 * @see com.darlan.xadrez.gui.AbstractPecaGUI#getCasasVisiveis(Tabuleiro)
	 */
	public Vector getCasasVisiveis(Tabuleiro tabuleiro) {
		
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
			
				casasVisiveis.add((CasaGUI)tabuleiro.getCasa(x-cont,y-cont));;
					break;
			} else
				casasVisiveis.add((CasaGUI)tabuleiro.getCasa(x-cont,y-cont));;
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
				casasVisiveis.add((CasaGUI)tabuleiro.getCasa(x+cont,y-cont));;
			cont++;
		}
		//verifica se pode mover para baixo
		cont = 1;
		while(tabuleiro.getCasa(x, y+cont)!=null)
		{
			if (tabuleiro.getCasa(x,y+cont).temPeca()){
				casasVisiveis.add((CasaGUI)tabuleiro.getCasa(x,y+cont));
				break;
			} else 
				casasVisiveis.add((CasaGUI)tabuleiro.getCasa(x,y+cont));
			cont++;
		}
		
		// verifica se pode mover para esquerda
		cont = 1;
		while(tabuleiro.getCasa(x-cont, y)!=null) 
		{
			if (tabuleiro.getCasa(x-cont,y).temPeca()){
				casasVisiveis.add((CasaGUI)tabuleiro.getCasa(x-cont,y));
				break;
			} else
				casasVisiveis.add((CasaGUI)tabuleiro.getCasa(x-cont,y));
			cont++;
		}
		
		// verifica se pode mover para direita
		cont = 1;
		while(tabuleiro.getCasa(x+cont, y)!=null)
		{
			if (tabuleiro.getCasa(x+cont,y).temPeca()){
				casasVisiveis.add((CasaGUI)tabuleiro.getCasa(x+cont,y));
				break;
			} else
				casasVisiveis.add((CasaGUI)tabuleiro.getCasa(x+cont,y));
				cont++;
		}
		
		// verifica se pode mover para cima
		cont = 1;
		while(tabuleiro.getCasa(x, y-cont)!=null)
		{
			if (tabuleiro.getCasa(x,y-cont).temPeca()){
				casasVisiveis.add((CasaGUI)tabuleiro.getCasa(x,y-cont));
				break;
			} else
				casasVisiveis.add((CasaGUI)tabuleiro.getCasa(x,y-cont));
			cont++;
		}
		return casasVisiveis;
		
	}

	/**
	 * @see com.darlan.xadrez.gui.AbstractPecaGUI#getCasasEmAtaque(Tabuleiro)
	 */
	public Vector getCasasEmAtaque(Tabuleiro tabuleiro) {
		// possíveis casas que o bispo pode ocupar.
		super.casasEmAtaque = new Vector();
		
		// verifica se pode mover para diagonal inferior direita
		int cont = 1;
		boolean encontrou = false;
		while(tabuleiro.getCasa(x+cont, y+cont)!=null)
		{
			if (tabuleiro.getCasa(x+cont,y+cont).getPeca()!=null){
				if (tabuleiro.getCasa(x+cont, y+cont).getPeca()
													 .getCor()==Color.WHITE){
					casasEmAtaque.add(tabuleiro.getCasa(x+cont,y+cont));
					break;
				}
				if (tabuleiro.getCasa(x+cont,y+cont).getPeca()
													.getCor()==Color.BLACK){
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
													 .getCor()==Color.WHITE){
					casasEmAtaque.add(tabuleiro.getCasa(x-cont,y+cont));
					break;
				}
				if (tabuleiro.getCasa(x-cont,y+cont).getPeca()
													.getCor()==Color.BLACK){
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
													 .getCor()==Color.WHITE){
					casasEmAtaque.add(tabuleiro.getCasa(x-cont,y-cont));
					break;
				}
				if (tabuleiro.getCasa(x-cont,y-cont).getPeca()
													.getCor()==Color.BLACK){
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
													 .getCor()==Color.WHITE){
					casasEmAtaque.add(tabuleiro.getCasa(x+cont,y-cont));
					break;
				}
				if (tabuleiro.getCasa(x+cont,y-cont).getPeca()
													.getCor()==Color.BLACK){
					break;
				}
			}
			casasEmAtaque.add(tabuleiro.getCasa(x+cont,y-cont));
			cont++;
		}
		// verifica se pode mover para baixo
		cont = 1;
		while(tabuleiro.getCasa(x, y+cont)!=null)
		{
			if (tabuleiro.getCasa(x,y+cont).getPeca()!=null){
				if (tabuleiro.getCasa(x, y+cont).getPeca()
												.getCor()==Color.WHITE){
					casasEmAtaque.add(tabuleiro.getCasa(x,y+cont));
					break;
				}
				if (tabuleiro.getCasa(x,y+cont).getPeca()
											   .getCor()==Color.BLACK){
					break;
				}
			}
			casasEmAtaque.add(tabuleiro.getCasa(x,y+cont));
			cont++;
		}
		
		// verifica se pode mover para esquerda
		cont = 1;
		while(tabuleiro.getCasa(x-cont, y)!=null) 
		{
			if (tabuleiro.getCasa(x-cont,y).getPeca()!=null){
				if (tabuleiro.getCasa(x-cont, y).getPeca()
												.getCor()==Color.WHITE){
					casasEmAtaque.add(tabuleiro.getCasa(x-cont,y));
					break;
				}
				if (tabuleiro.getCasa(x-cont,y).getPeca()
											   .getCor()==Color.BLACK){
					break;
				}
			}
			casasEmAtaque.add(tabuleiro.getCasa(x-cont,y));
			cont++;
		}
		
		// verifica se pode mover para direita
		cont = 1;
		while(tabuleiro.getCasa(x+cont, y)!=null)
		{
			if (tabuleiro.getCasa(x+cont,y).getPeca()!=null){
			
				if (tabuleiro.getCasa(x+cont, y).getPeca()
												.getCor()==Color.WHITE){
					casasEmAtaque.add(tabuleiro.getCasa(x+cont,y));
					break;
				}
				if (tabuleiro.getCasa(x+cont,y).getPeca()
											   .getCor()==Color.BLACK){
					break;
				}
			}
			casasEmAtaque.add(tabuleiro.getCasa(x+cont,y));
			cont++;
		}
		
		// verifica se pode mover para cima
		cont = 1;
		while(tabuleiro.getCasa(x, y-cont)!=null)
		{
			if (tabuleiro.getCasa(x,y-cont).getPeca()!=null){
			
				if (tabuleiro.getCasa(x, y-cont).getPeca()
												.getCor()==Color.WHITE){
					casasEmAtaque.add(tabuleiro.getCasa(x,y-cont));
					break;
				}
				if (tabuleiro.getCasa(x,y-cont).getPeca()
											   .getCor()==Color.BLACK){
					break;
				}
			}
			casasEmAtaque.add(tabuleiro.getCasa(x,y-cont));
			cont++;
		}
		return casasEmAtaque;
		

	}

	/**
	 * @see com.darlan.xadrez.gui.AbstractPecaGUI#isVisivel(Tabuleiro, Casa)
	 */
	public boolean isVisivel(Tabuleiro tabuleiro, Casa casa) {
		Vector casaTemp = getCasasVisiveis(tabuleiro);
		if (casaTemp.contains(casa)){
			return true;
		}
		return false;
	}
	
}
