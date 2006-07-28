/*
 * @(#)ReiPretoGUI.java 1.0 11/10/2005
 *
 * Darlan A. dos Santos -> darlan_ads@yahoo.com.br
 *
 * Copyright 2005 D & D Desenvolvimento Software.
 */
package com.darlan.xadrez.gui;

import java.awt.Color;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.ImageIcon;

import com.darlan.util.Global;
import com.darlan.xadrez.Casa;
import com.darlan.xadrez.Peca;
import com.darlan.xadrez.PosicaoInvalidaException;
import com.darlan.xadrez.Tabuleiro;


/**
 * Classe que representa a rei preto do xadrez.
 * 
 * @version 1.0 11/10/2005
 * @author Darlan A. dos Santos
 */
public class ReiPretoGUI extends AbstractPecaGUI{
	
	// atributo.
	private boolean movimentou = false;
	
	/**
	* Construtor 
	* @param x Abcissa
	* @param y Ordenada
	*/
	public ReiPretoGUI( int x, int y)
	{
		super(Global.IMG_REI_PRETO);
		super.x = x;
		super.y = y;
		super.cor = Color.BLACK;
	}
	
	public void move(Casa casaOrigem, Casa casaDestino, Tabuleiro tabuleiro) throws PosicaoInvalidaException
	{
		if ((x!=4 ) && (y!=0))
			movimentou = true;
		Vector casasPossiveis = new Vector();
		Vector casasTemp = new Vector();

		// Insere no vetor para logo abaixo, no while, verificar se a casa está disponível para mover.		
		casasTemp.add(tabuleiro.getCasa(x+1,y+1));
		casasTemp.add(tabuleiro.getCasa(x+1,y-1));
		casasTemp.add(tabuleiro.getCasa(x+1,y));
		casasTemp.add(tabuleiro.getCasa(x-1,y+1));
		casasTemp.add(tabuleiro.getCasa(x-1,y-1));
		casasTemp.add(tabuleiro.getCasa(x-1,y));
		casasTemp.add(tabuleiro.getCasa(x,y+1));
		casasTemp.add(tabuleiro.getCasa(x,y-1));
		// Possíveis posições.
		Casa casaTemp=null;
		Iterator it = casasTemp.iterator();
		while(it.hasNext()){
			casaTemp = (Casa)it.next();
			if (casaTemp!=null)
				if (!((CasaGUI)casaTemp).isAtaqueBranco() && !casaTemp.temPeca(Color.BLACK)){
					casasPossiveis.add(casaTemp);
				}	
		}
		
		// verifica se pode fazer roque do lado direito.	
		if ( (!isMovimentou()) &&
			 (casaDestino.getPosicaoY()==0) &&
			 (casaDestino.getPosicaoX()==6) &&
			 !((TabuleiroGUI)tabuleiro).isChequePreto()
			){
			Peca pecaTemp = tabuleiro.getCasa(7,0).getPeca();
			Casa casa6_0 = tabuleiro.getCasa(6,0);
			Casa casa5_0 = tabuleiro.getCasa(5,0);
			if ((pecaTemp!=null) &&
				(pecaTemp instanceof TorrePretaGUI) &&
				(!((TorrePretaGUI)pecaTemp).isMovimentou()) &&
				(casa6_0.getPeca()==null) &&
				!((CasaGUI)casa6_0).isAtaqueBranco() &&
				(casa5_0.getPeca()==null) &&
				!((CasaGUI)casa5_0).isAtaqueBranco()
				){
					casasPossiveis.add(tabuleiro.getCasa(6,0));
			  }
		}
		
		// verifica se pode fazer roque do lado esquerdo.
		if ( (!isMovimentou()) &&
			 (casaDestino.getPosicaoY()==0) &&
			 (casaDestino.getPosicaoX()==2) &&
			!((TabuleiroGUI)tabuleiro).isChequePreto()
			){
				Peca pecaTemp = tabuleiro.getCasa(0,0).getPeca();
				Casa casa2_0 = tabuleiro.getCasa(2,0);
				Casa casa3_0 = tabuleiro.getCasa(3,0);
				Casa casa1_0 = tabuleiro.getCasa(1,0);
				if ((pecaTemp!=null) &&
					(pecaTemp instanceof TorrePretaGUI) &&
					(!((TorrePretaGUI)pecaTemp).isMovimentou()) &&
					(casa2_0.getPeca()==null) &&
					!((CasaGUI)casa2_0).isAtaqueBranco() &&
					(casa3_0.getPeca()==null) &&
					!((CasaGUI)casa3_0).isAtaqueBranco() &&
					(casa1_0.getPeca()==null)
					){
						casasPossiveis.add(tabuleiro.getCasa(2,0));
					}
			}
		
		if (casasPossiveis.contains(casaDestino)){
			
			if (!isMovimentou() && (casaDestino.getPosicaoX()== 6) &&
				(casaDestino.getPosicaoY()==0))
			{
				casaOrigem.removePeca();
				casaDestino.setPeca(this);
				Peca torre = tabuleiro.getCasa(7,0).getPeca();
				tabuleiro.getCasa(5,0).setPeca(torre);
				tabuleiro.getCasa(7,0).removePeca();
				movimentou = true;
				TabuleiroGUI tabuleiroTemp = (TabuleiroGUI)tabuleiro;
				tabuleiroTemp.removeAllAtaques();
				tabuleiroTemp.atualizaAtaqueBranco();
				tabuleiroTemp.atualizaAtaquePreto();
			} else if (!isMovimentou() && (casaDestino.getPosicaoX()== 2) &&
					  (casaDestino.getPosicaoY()==0))
			{
				casaOrigem.removePeca();
				casaDestino.setPeca(this);
				Peca torre = tabuleiro.getCasa(0,0).getPeca();
				tabuleiro.getCasa(3,0).setPeca(torre);
				tabuleiro.getCasa(0,0).removePeca();
				movimentou = true;
				TabuleiroGUI tabuleiroTemp = (TabuleiroGUI)tabuleiro;
				tabuleiroTemp.removeAllAtaques();
				tabuleiroTemp.atualizaAtaqueBranco();
				tabuleiroTemp.atualizaAtaquePreto();
			}else{
				Peca pecaTemp = casaDestino.getPeca();
				casaOrigem.removePeca();
				casaDestino.removePeca();
				casaDestino.setPeca(this);
				movimentou = true;
				TabuleiroGUI tabuleiroTemp = (TabuleiroGUI)tabuleiro;
				tabuleiroTemp.removeAllAtaques();
				tabuleiroTemp.atualizaAtaqueBranco();
				tabuleiroTemp.atualizaAtaquePreto();
				if (tabuleiroTemp.isChequeBranco()){
					casaOrigem.setPeca(this);
					casaDestino.removePeca();
					casaDestino.setPeca(pecaTemp);
					tabuleiroTemp.removeAllAtaques();
					tabuleiroTemp.atualizaAtaqueBranco();
					tabuleiroTemp.atualizaAtaquePreto();
					throw new PosicaoInvalidaException("Rei branco em cheque");					
				}
				
			}
			
		} else {
			throw new PosicaoInvalidaException("Rei - > Posição inválida");
		}
		
		
	}
	
	
	public void setCasasEmAtaque(Tabuleiro tabuleiro)
	{
		Vector casasTemp = new Vector();

		// Insere no vetor para logo abaixo, no while, verificar se a casa está disponível para mover.		
		casasTemp.add(tabuleiro.getCasa(x+1,y+1));
		casasTemp.add(tabuleiro.getCasa(x+1,y-1));
		casasTemp.add(tabuleiro.getCasa(x+1,y));
		casasTemp.add(tabuleiro.getCasa(x-1,y+1));
		casasTemp.add(tabuleiro.getCasa(x-1,y-1));
		casasTemp.add(tabuleiro.getCasa(x-1,y));
		casasTemp.add(tabuleiro.getCasa(x,y+1));
		casasTemp.add(tabuleiro.getCasa(x,y-1));
		// Possíveis posições.
		Casa casaTemp=null;
		Iterator it = casasTemp.iterator();
		while(it.hasNext()){
			casaTemp = (Casa)it.next();
			if (casaTemp!=null)
			if (!((CasaGUI)casaTemp).isAtaqueBranco() && !casaTemp.temPeca(Color.BLACK)){			
				((CasaGUI)casaTemp).setAtaquePreto(true);	
			}
		}		
	}	
	

	
	public boolean isMovimentou()
	{
		return movimentou;
	}

	/* (non-Javadoc)
	 * @see com.darlan.xadrez.gui.AbstractPecaGUI#getCasasVisiveis(com.darlan.xadrez.Tabuleiro)
	 */
	public Vector getCasasVisiveis(Tabuleiro tabuleiro) {
		casasVisiveis = new Vector();
		Vector casasTemp = new Vector();

		// Insere no vetor para logo abaixo, no while, verificar se a casa está disponível para mover.		
		casasTemp.add(tabuleiro.getCasa(x+1,y+1));
		casasTemp.add(tabuleiro.getCasa(x+1,y-1));
		casasTemp.add(tabuleiro.getCasa(x+1,y));
		casasTemp.add(tabuleiro.getCasa(x-1,y+1));
		casasTemp.add(tabuleiro.getCasa(x-1,y-1));
		casasTemp.add(tabuleiro.getCasa(x-1,y));
		casasTemp.add(tabuleiro.getCasa(x,y+1));
		casasTemp.add(tabuleiro.getCasa(x,y-1));
		// Possíveis posições.
		Casa casaTemp=null;
		Iterator it = casasTemp.iterator();
		while(it.hasNext()){
			casaTemp = (Casa)it.next();
			if (casaTemp!=null)
				casasVisiveis.add((CasaGUI)casaTemp);	
		}
		return casasVisiveis;
	}

	/* (non-Javadoc)
	 * @see com.darlan.xadrez.gui.AbstractPecaGUI#getCasasEmAtaque(com.darlan.xadrez.Tabuleiro)
	 */
	public Vector getCasasEmAtaque(Tabuleiro tabuleiro) {
		super.casasEmAtaque = new Vector();
		Vector casasTemp = new Vector();

		// Insere no vetor para logo abaixo, no while, verificar se a casa está disponível para mover.		
		casasTemp.add(tabuleiro.getCasa(x+1,y+1));
		casasTemp.add(tabuleiro.getCasa(x+1,y-1));
		casasTemp.add(tabuleiro.getCasa(x+1,y));
		casasTemp.add(tabuleiro.getCasa(x-1,y+1));
		casasTemp.add(tabuleiro.getCasa(x-1,y-1));
		casasTemp.add(tabuleiro.getCasa(x-1,y));
		casasTemp.add(tabuleiro.getCasa(x,y+1));
		casasTemp.add(tabuleiro.getCasa(x,y-1));
		// Possíveis posições.
		Casa casaTemp=null;
		Iterator it = casasTemp.iterator();
		while(it.hasNext()){
			casaTemp = (Casa)it.next();
			if (casaTemp!=null)
				if (!((CasaGUI)casaTemp).isAtaqueBranco() && !casaTemp.temPeca(Color.BLACK)){
					casasEmAtaque.add(casaTemp);
				}	
		}
		return casasEmAtaque;
	}

	/* (non-Javadoc)
	 * @see com.darlan.xadrez.gui.AbstractPecaGUI#isVisivel(com.darlan.xadrez.Tabuleiro, com.darlan.xadrez.Casa)
	 */
	public boolean isVisivel(Tabuleiro tabuleiro, Casa casa) {
		casasVisiveis = this.getCasasVisiveis(tabuleiro);
		if (casasVisiveis.contains(casa))
			return true;
		return false;
	}
}
