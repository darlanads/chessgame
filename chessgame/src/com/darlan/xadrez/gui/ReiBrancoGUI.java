/*
 * @(#)ReiBrancoGUI.java 1.0 11/10/2005
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
 * Classe que representa a rei branco do xadrez.
 * 
 * @version 1.0 11/10/2005
 * @author Darlan A. dos Santos
 */
public class ReiBrancoGUI extends AbstractPecaGUI{
	
	// atributo.
	private boolean movimentou = false;
	
	/**
	* Construtor 
	* @param x Abcissa
	* @param y Ordenada
	*/
	public ReiBrancoGUI( int x, int y)
	{
		super(Global.IMG_REI_BRANCO);
		super.x = x;
		super.y = y;
		super.cor = Color.WHITE;
	}
	
	/**
	 * @see com.darlan.xadrez.gui.AbstractPecaGUI#move(Casa, Casa, Tabuleiro)
	 */
	public void move(Casa casaOrigem, Casa casaDestino, Tabuleiro tabuleiro) 
			throws PosicaoInvalidaException {
		
		if ((x!=4 ) && (y!=7))
			movimentou = true;
		Vector casasPossiveis = new Vector();
		Vector casasTemp = new Vector();

		// Insere no vetor para logo abaixo, no while, verificar se a casa está 
		// disponível para mover.		
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
				if (!((CasaGUI)casaTemp).isAtaquePreto() 
						&& !casaTemp.temPeca(Color.WHITE)){
					casasPossiveis.add(casaTemp);
				}	
		}
		
		// verifica se pode fazer roque do lado esquerdo.	
		if ( (!isMovimentou()) &&
		     (casaDestino.getPosicaoY()==7) &&
		     (casaDestino.getPosicaoX()==6) &&
			!((TabuleiroGUI)tabuleiro).isChequeBranco()
		    ){
		    Peca pecaTemp = tabuleiro.getCasa(7,7).getPeca();
		    Casa casa6_7 = tabuleiro.getCasa(6,7);
		    Casa casa5_7 = tabuleiro.getCasa(5,7);
		    if ((pecaTemp!=null) &&
		        (pecaTemp instanceof TorreBrancaGUI) &&
		        (!((TorreBrancaGUI)pecaTemp).isMovimentou()) &&
		        (casa6_7.getPeca()==null) &&
		        !((CasaGUI)casa6_7).isAtaquePreto() &&
				(casa5_7.getPeca()==null) &&
				!((CasaGUI)casa5_7).isAtaquePreto()
		        ){
		        	casasPossiveis.add(tabuleiro.getCasa(6,7));
		      }
		}
		
		// verifica se pode fazer roque do lado direito.
		if ( (!isMovimentou()) &&
			 (casaDestino.getPosicaoY()==7) &&
			 (casaDestino.getPosicaoX()==2) &&
			!((TabuleiroGUI)tabuleiro).isChequeBranco()
			){
				Peca pecaTemp = tabuleiro.getCasa(0,7).getPeca();
				Casa casa2_7 = tabuleiro.getCasa(2,7);
				Casa casa3_7 = tabuleiro.getCasa(3,7);
				Casa casa1_7 = tabuleiro.getCasa(1,7);
				if ((pecaTemp!=null) &&
					(pecaTemp instanceof TorreBrancaGUI) &&
					(!((TorreBrancaGUI)pecaTemp).isMovimentou()) &&
					(casa2_7.getPeca()==null) &&
					!((CasaGUI)casa2_7).isAtaquePreto() &&
					(casa3_7.getPeca()==null) &&
					!((CasaGUI)casa3_7).isAtaquePreto() &&
					(casa1_7.getPeca()==null)
					){
						casasPossiveis.add(tabuleiro.getCasa(2,7));
				    }
			}
		
		if (casasPossiveis.contains(casaDestino)){
			
			if (!isMovimentou() && (casaDestino.getPosicaoX()== 6) &&
				(casaDestino.getPosicaoY()==7))
			{
				casaOrigem.removePeca();
				casaDestino.setPeca(this);
				Peca torre = tabuleiro.getCasa(7,7).getPeca();
				tabuleiro.getCasa(5,7).setPeca(torre);
				tabuleiro.getCasa(7,7).removePeca();
				TabuleiroGUI tabuleiroTemp = (TabuleiroGUI)tabuleiro;
				tabuleiroTemp.removeAllAtaques();
				tabuleiroTemp.atualizaAtaqueBranco();
				tabuleiroTemp.atualizaAtaquePreto();
				
			} else if (!isMovimentou() && (casaDestino.getPosicaoX()== 2) &&
					  (casaDestino.getPosicaoY()==7))
			{
				casaOrigem.removePeca();
				casaDestino.setPeca(this);
				Peca torre = tabuleiro.getCasa(0,7).getPeca();
				tabuleiro.getCasa(3,7).setPeca(torre);
				tabuleiro.getCasa(0,7).removePeca();
				TabuleiroGUI tabuleiroTemp = (TabuleiroGUI)tabuleiro;
				tabuleiroTemp.removeAllAtaques();
				tabuleiroTemp.atualizaAtaqueBranco();
				tabuleiroTemp.atualizaAtaquePreto();
				
			}else{
				Peca pecaTemp = casaDestino.getPeca();
				casaOrigem.removePeca();
				casaDestino.removePeca();
				casaDestino.setPeca(this);
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
	
	/**
	 * @see com.darlan.xadrez.gui.AbstractPecaGUI#setCasasEmAtaque(Tabuleiro)
	 */
	public void setCasasEmAtaque(Tabuleiro tabuleiro)
	{
		Vector casasTemp = new Vector();

		// Insere no vetor para logo abaixo, no while, verificar se a casa está 
		// disponível para mover.		
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
			if (casaTemp!=null){		
				if (!((CasaGUI)casaTemp).isAtaquePreto() 
						&& !casaTemp.temPeca(Color.WHITE)){
					//casasTemp.add(casaTemp);
					((CasaGUI)casaTemp).setAtaqueBranco(true);
				}
			}				
			//		
		}		
	}
	
	/**
	 * Verifica se o rei já se movimento.
	 * @return <code>true</code> indica que o rei já realizou algum movimento.
	 */
	public boolean isMovimentou()
	{
		return movimentou;
	}

	/**
	 * @see com.darlan.xadrez.gui.AbstractPecaGUI#getCasasVisiveis(Tabuleiro)
	 */
	public Vector getCasasVisiveis(Tabuleiro tabuleiro) {
		casasVisiveis = new Vector();
		Vector casasTemp = new Vector();

		// Insere no vetor para logo abaixo, no while, verificar se a casa está 
		// disponível para mover.		
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

	/**
	 * @see com.darlan.xadrez.gui.AbstractPecaGUI#getCasasEmAtaque(Tabuleiro)
	 */
	public Vector getCasasEmAtaque(Tabuleiro tabuleiro) {
		super.casasEmAtaque = new Vector();
		Vector casasTemp = new Vector();

		// Insere no vetor para logo abaixo, no while, verificar se a casa está 
		// disponível para mover.		
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
				if (!((CasaGUI)casaTemp).isAtaquePreto() 
						&& !casaTemp.temPeca(Color.WHITE)){
					casasEmAtaque.add(casaTemp);
				}	
		}
		return casasEmAtaque;
	}

	/**
	 * @see com.darlan.xadrez.gui.AbstractPecaGUI#isVisivel(Tabuleiro, Casa)
	 */
	public boolean isVisivel(Tabuleiro tabuleiro, Casa casa) {
		casasVisiveis = this.getCasasVisiveis(tabuleiro);
		if (casasVisiveis.contains(casa))
			return true;
		return false;
	}

}
