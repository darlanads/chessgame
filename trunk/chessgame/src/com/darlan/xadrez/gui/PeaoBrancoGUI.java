/*
 * @(#)PeaoBrancoGUI.java 1.0 11/10/2005
 *
 * Darlan A. dos Santos -> darlan_ads@yahoo.com.br
 *
 * Copyright 2005 D & D Desenvolvimento Software.
 */
package com.darlan.xadrez.gui;


import java.awt.Color;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import com.darlan.util.Global;
import com.darlan.xadrez.Casa;
import com.darlan.xadrez.Peca;
import com.darlan.xadrez.PosicaoInvalidaException;
import com.darlan.xadrez.Tabuleiro;


/**
 * Classe que representa o peão branco do xadrez.
 * 
 * @version 1.0 11/10/2005
 * @author Darlan A. dos Santos
 */
public class PeaoBrancoGUI extends AbstractPecaGUI{
	
	/**
	 * PeaoBranco
	 * @param x Abscissa
	 * @param y Ordenada
	 */
	public PeaoBrancoGUI(int x, int y)
	{
		super(Global.IMG_PEAO_BRANCO);
		this.x = x;
		this.y = y;
		this.cor = Color.WHITE;

	}
	
	/**
	 * @see com.darlan.xadrez.gui.AbstractPecaGUI#move(Casa, Casa, Tabuleiro)
	 */
	public void move(Casa casaOrigem, Casa casaDestino, Tabuleiro tabuleiro) 
			throws PosicaoInvalidaException
	{
		
		Vector casasPossiveisAtaque = this.getCasasEmAtaque(tabuleiro);
		Vector casasPossiveisVisiveis = this.getCasasVisiveis(tabuleiro);
		
        // Verifica se a casa destino está contida numas das possíveis casas de 
		// movimento.		
		if (casasPossiveisAtaque.contains(casaDestino)
				||(casasPossiveisVisiveis.contains(casaDestino))){
			// Se o a peça inicio pulando uma casa, set a casa pulada como 
			// passante.
			if ((casaOrigem.getPosicaoY()-casaDestino.getPosicaoY())==2)
				((CasaGUI)tabuleiro.getCasa(x,y-1)).setPassante(true);
			
			// SE O PEÃO PODE FAZER PASSANTE.
			if ((casaOrigem.getPosicaoY()==3)&& (casaDestino.getPassante())){
				// pega a casa em que está o peão que sofrerá passante.
				Casa casaTemp = tabuleiro.getCasa(casaDestino.getPosicaoX(),
						casaDestino.getPosicaoY()+1);
				// pega o peão que sofre passante.
				Peca pecaTemp = casaTemp.getPeca();
				casaOrigem.removePeca();
				// remove o peão que sofreu passante.
				casaTemp.removePeca();
				casaDestino.setPeca(this);
				// atualiza o tabuleiro.
				TabuleiroGUI tabuleiroTemp = (TabuleiroGUI)tabuleiro;
				tabuleiroTemp.removeAllAtaques();
				tabuleiroTemp.atualizaAtaquePreto();
				tabuleiroTemp.atualizaAtaqueBranco();
				// verifica se o movimento que fiz causou reque mate no 
				// adversário.
				if (tabuleiroTemp.isChequeMatePreto()){
					((TabuleiroGUI)tabuleiro).mensagemFimJogo(
							"Cheque Mate! Branco Vence!");					
				// verifica se o movimento que fiz deixou o meu rei em cheque.
				}else if (tabuleiroTemp.isChequeBranco()){
					// volta ao estado anterior.
					casaTemp.setPeca(pecaTemp);
					casaDestino.removePeca();
					casaOrigem.setPeca(this);
					// atualiza tabuleiro
					tabuleiroTemp.removeAllAtaques();
					tabuleiroTemp.atualizaAtaquePreto();
					tabuleiroTemp.atualizaAtaqueBranco();
					
					throw new PosicaoInvalidaException(
							"Em xeque possição inválida");					
				}
								
			// VERIFICA SE A PEÇA SERÁ PROMOVIDA
			} else if (casaDestino.getPosicaoY()==0){
				Peca pecaTemp = casaDestino.getPeca();
				casaOrigem.removePeca();
				casaDestino.removePeca();
				casaDestino.setPeca(this);
				casaDestino.removePeca();
				casaDestino.setPeca(getPromocao());
				TabuleiroGUI tabuleiroTemp = (TabuleiroGUI)tabuleiro;
				tabuleiroTemp.removeAllAtaques();
				tabuleiroTemp.atualizaAtaquePreto();
				tabuleiroTemp.atualizaAtaqueBranco();
				// verifica se as peças brancas venceram o jogo
				if (tabuleiroTemp.isChequeMatePreto()){
					((TabuleiroGUI)tabuleiro).mensagemFimJogo(
							"Cheque Mate! Branco Vence!");					
				// se o movimento que fiz deixou meu rei em xeque.
				}else if (tabuleiroTemp.isChequeBranco()){
					// volta ao estado anterior
					casaOrigem.setPeca(this);
					casaDestino.removePeca();
					casaDestino.setPeca(pecaTemp);
					tabuleiroTemp.removeAllAtaques();
					tabuleiroTemp.atualizaAtaquePreto();
					tabuleiroTemp.atualizaAtaqueBranco();
					
					throw new PosicaoInvalidaException(
							"Em xeque possição inválida");					
				}
			// MOVE O PEÃO
			} else{
				Peca pecaTemp = casaDestino.getPeca();
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
				// se o movimento que fiz deixou o meu rei em exque.
				} else if (tabuleiroTemp.isChequeBranco()){
					// volta ao estado anterior
					casaOrigem.setPeca(this);
					casaDestino.removePeca();
					casaDestino.setPeca(pecaTemp);
					tabuleiroTemp.removeAllAtaques();
					tabuleiroTemp.atualizaAtaquePreto();
					tabuleiroTemp.atualizaAtaqueBranco();
					
					throw new PosicaoInvalidaException(
							"Em xeque possição inválida");					
				}
			}
			movePassante(tabuleiro);
			
		} else
			throw new PosicaoInvalidaException("Posicao invalida");
				
	}
	
	/*
	 * Pega as opções para o passante.
	 */
	private Peca getPromocao()
	{
		Object[] possiveis = {"Torre", "Cavalo", "Bispo","Dama"};
				String s;
				do{
					s = (String)JOptionPane.showInputDialog(
									this,
									"Escolha a peça:",
									"darlan_ads@yahoo.com.br",
									JOptionPane.PLAIN_MESSAGE,
									null,
									possiveis,
									"Torre");
				} while (s==null);
		
				if (s.equals("Torre"))
					return new TorreBrancaGUI(x,y); 
				if (s.equals("Cavalo"))
					return new CavaloBrancoGUI(x,y);
				if (s.equals("Bispo"))
					return new BispoBrancoGUI(x,y);
				return new RainhaBrancaGUI(x,y);

	}
	
	/**
	 * Informa ao tabuleiro quais casas o peão está atacando
	 * @param tabuleiro
	 */
	public void setCasasEmAtaque(Tabuleiro tabuleiro){
		Casa casaTemp = tabuleiro.getCasa(x-1,y-1);
		if (casaTemp!=null)
			((CasaGUI)casaTemp).setAtaqueBranco(true);
		casaTemp = tabuleiro.getCasa(x+1,y-1);
		if (casaTemp!=null)
			((CasaGUI)casaTemp).setAtaqueBranco(true);
			
	}
	
	/*
	 * Indica que a casa não pode mais receber passante.
	 */
	private void movePassante(Tabuleiro tabuleiro)
	{
		for (int x = 0; x < 8 ; x++)
			tabuleiro.getCasa(x,2).setPassante(false);
	}

	/**
	 * @see com.darlan.xadrez.gui.AbstractPecaGUI#getCasasVisiveis(Tabuleiro)
	 */
	public Vector getCasasVisiveis(Tabuleiro tabuleiro) {
		super.casasVisiveis = new Vector();
		// Verifica se pode mover para cima
		Casa casaTemp = tabuleiro.getCasa(x, y -1);
		if (casaTemp!=null){
			if (!casaTemp.temPeca() ){
				casasVisiveis.add(casaTemp);
				// Verifica se pode mover duas casa para cima.
				if ((y==6) && (tabuleiro.getCasa(x,y-2)!=null) 
						&& !(tabuleiro.getCasa(x,y-2).temPeca())){
					casasVisiveis.add(tabuleiro.getCasa(x,y-2));
				}		
			}
		}
		return casasVisiveis;
	}

	/**
	 * @see com.darlan.xadrez.gui.AbstractPecaGUI#getCasasEmAtaque(Tabuleiro)
	 */
	public Vector getCasasEmAtaque(Tabuleiro tabuleiro) {
		super.casasEmAtaque = new Vector();
		
		//Verifica se pode mover para cima e para esquerda.
		Casa casaTemp = tabuleiro.getCasa(x-1,y-1);	
		if (casaTemp!=null)
			if (casaTemp.temPeca(Color.BLACK))
			{
				casasEmAtaque.add(casaTemp);
			//	verifica se é passante.
			} else if (((CasaGUI)casaTemp).getPassante() 
					&& (casaTemp.getPeca()==null)){
				casasEmAtaque.add(casaTemp);
		}
		
		// Verifica se pode mover para a cima e para direita.
		casaTemp = tabuleiro.getCasa(x+1,y-1);
		if (casaTemp!=null){
			if (casaTemp.temPeca(Color.BLACK)){
				casasEmAtaque.add(casaTemp);
			// verifica se é passante.
			} else if (((CasaGUI)casaTemp).getPassante() 
					&& (casaTemp.getPeca()==null)){
				casasEmAtaque.add(casaTemp);
			}
		}
		return casasEmAtaque;
	}

	/**
	 * @see com.darlan.xadrez.gui.AbstractPecaGUI#isVisivel(Tabuleiro, Casa)
	 */
	public boolean isVisivel(Tabuleiro tabuleiro, Casa casa) {
		Vector tempVisiveis = this.getCasasVisiveis(tabuleiro);
		Vector tempAtaque = this.getCasasEmAtaque(tabuleiro);
		if (tempVisiveis.contains(casa) || tempAtaque.contains(casa))
			return true;
		return false;
	}

}
