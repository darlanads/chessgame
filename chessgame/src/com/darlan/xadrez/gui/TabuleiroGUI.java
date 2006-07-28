/*
 * @(#)TabuleiroGUI.java 1.0 11/10/2005
 *
 * Darlan A. dos Santos -> darlan_ads@yahoo.com.br
 *
 * Copyright 2005 D & D Desenvolvimento Software.
 */
package com.darlan.xadrez.gui;

import java.awt.Color;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JComponent;
import javax.swing.JOptionPane;

import com.darlan.util.CarregaImagemUtil;
import com.darlan.util.Global;
import com.darlan.xadrez.Casa;
import com.darlan.xadrez.Peca;
import com.darlan.xadrez.Tabuleiro;

/**
 * Classe que representa o tabuleiro do xadrez.
 * 
 * @version 1.0 11/10/2005
 * @author Darlan A. dos Santos
 */
public class TabuleiroGUI extends JComponent implements Tabuleiro {
	
	// atributos.
	private boolean fimDeJogo = false;
	private CasaGUI[][] casas;
	 
	/**
	 * Constroi o tabuleiro 8x8 Casas
	 */
	public TabuleiroGUI()
	{
		super.setSize(400,400);
		casas = new CasaGUI[8][8];
		int controle = 0;
		for (int y=0; y<8; y++){
			for (int x=0; x<8; x++){
				if (x%2== controle)
					casas[x][y] = new CasaGUI(x,y,Color.WHITE);
				else
					casas[x][y] = new CasaGUI(x,y,Color.BLACK);
				add(casas[x][y]);	
			}
			if (controle == 0)
				controle = 1;
			else 
				controle = 0;
		}
		super.repaint();
	}
	
	/**
	 * Adiciona peças do jogo xadrez no tabueiro.
	 */
	public void adicionaPecasDoXadrez()
	{	
		// Peças pretas.
		casas[0][0].setPeca(new TorrePretaGUI(0,0));
		casas[1][0].setPeca(new CavaloPretoGUI(1,0));
		casas[2][0].setPeca(new BispoPretoGUI(2,0));
		casas[3][0].setPeca(new RainhaPretaGUI(3,0));
		casas[4][0].setPeca(new ReiPretoGUI(4,0));
		casas[5][0].setPeca(new BispoPretoGUI(5,0));
		casas[6][0].setPeca(new CavaloPretoGUI(6,0));
		casas[7][0].setPeca(new TorrePretaGUI(7,0));
		for (int x = 0; x < 8; x++)
			casas[x][1].setPeca(new PeaoPretoGUI(x,1));
			
		// Peças brancas.
		casas[0][7].setPeca(new TorreBrancaGUI(0,7));
		casas[1][7].setPeca(new CavaloBrancoGUI(1,7));
		casas[2][7].setPeca(new BispoBrancoGUI(2,7));
		casas[3][7].setPeca(new RainhaBrancaGUI(3,7));
		casas[4][7].setPeca(new ReiBrancoGUI(4,7));
		casas[5][7].setPeca(new BispoBrancoGUI(5,7));
		casas[6][7].setPeca(new CavaloBrancoGUI(6,7));
		casas[7][7].setPeca(new TorreBrancaGUI(7,7));
		for (int x = 0; x < 8; x++)
			casas[x][6].setPeca(new PeaoBrancoGUI(x,6));
	}
	
	/**
	 * @see com.darlan.xadrez.Tabuleiro#getCasa(int, int)
	 */
	public Casa getCasa(int x, int y)
	{
		CasaGUI casaTemp;
		for (int i = 0; i < super.getComponentCount(); i++){
			casaTemp = (CasaGUI)super.getComponent(i);
			if ( (casaTemp.getPosicaoX()==x) && (casaTemp.getPosicaoY()==y)){
				return casaTemp;
			}
		}
		return null;
	}

	/**
	 * Remove todas as indicações de ataque nas casas.
	 */
	public void removeAllAtaques()
	{
		for (int x = 0; x < 8; x++)
			for (int y = 0; y < 8; y++)
				((CasaGUI)this.getCasa(x,y)).removeAllAtaques();
	}
	
	/**
	 * Remove todas as indicações de ataque nas casas brancas.
	 */
	public void removeAllAtaqueBranco()
	{
		for (int x = 0; x < 8; x++)
			for (int y = 0; y < 8; y++)
				((CasaGUI)this.getCasa(x,y)).setAtaqueBranco(false);
	}

	/**
	 * Remove todas as indicações de ataque nas casas pretas.
	 */
	public void removeAllAtaquePreto()
	{
		for (int x = 0; x < 8; x++)
			for (int y = 0; y < 8; y++)
				((CasaGUI)this.getCasa(x,y)).setAtaquePreto(false);
	}	
	
	/**
	 * Atualiza todas as casas que estão sendo atacadas pelas peças pretas.
	 */
	public void atualizaAtaquePreto(){
		Casa casaTemp;
		for (int x = 0; x<8; x++){
			for (int y = 0; y < 8; y++){
				casaTemp = this.getCasa(x,y);
				if ((casaTemp.getPeca()!=null) 
						&& (casaTemp.getPeca().getCor()==Color.BLACK))
					casaTemp.getPeca().setCasasEmAtaque(this);	
			}
		}
	}
	
	/**
	 * Atualiza todas as casas que estão sendo atacadas pelas peças brancas.
	 */
	public void atualizaAtaqueBranco(){
		Casa casaTemp;
		for (int x = 0; x<8; x++){
			for (int y = 0; y < 8; y++){
				casaTemp = this.getCasa(x,y);
				if ((casaTemp.getPeca()!=null) 
						&& (casaTemp.getPeca().getCor()==Color.WHITE))
					casaTemp.getPeca().setCasasEmAtaque(this);	
			}
		}
	}

	/**
	 * Verifica se o rei preto está em cheque.
	 * @return <code>true</code> indica que o rei preto está em cheque.
	 */
	public boolean isChequePreto()
	{
		for (int x = 0; x < 8 ; x++)
			for (int y = 0; y < 8; y++)
				if ( (this.getCasa(x,y).getPeca() instanceof ReiPretoGUI) &&
					 ((CasaGUI)this.getCasa(x,y)).isAtaqueBranco()){
					return true;
				}
		return false;	
		
	}
	
	/**
	 * Verifica se o rei branco está em cheque.
	 * @return <code>true</code> indica que o rei branco está em cheque.
	 */	
	public boolean isChequeBranco()
	{
		for (int x = 0; x < 8 ; x++)
			for (int y = 0; y < 8; y++)
				if ( (this.getCasa(x,y).getPeca() instanceof ReiBrancoGUI) &&
					 ((CasaGUI)this.getCasa(x,y)).isAtaquePreto()){
					return true;
				}
		return false;	
		
	}
	
	/**
	 * Verifica se as peças brancas sofreram cheque mate.
	 * @return boolean
	 */
	public boolean isChequeMateBranco()
	{
		ReiBrancoGUI reiBranco = null;
		CasaGUI casaDoReiTemp = null;
		CasaGUI casaDoRei = null;
		// Pega o rei branco no tabuleiro.
		for (int x = 0; x < 8 ; x++)
			for (int y = 0; y < 8; y++){
				casaDoReiTemp = (CasaGUI)this.getCasa(x,y);
				if ( (casaDoReiTemp.getPeca() instanceof ReiBrancoGUI)){
					casaDoRei = casaDoReiTemp;
					reiBranco = (ReiBrancoGUI)casaDoRei.getPeca();
				}
			}
			
		// verifica se o rei está sendo atacado 
		if (casaDoRei.isAtaquePreto()){
			// se o rei não pode mover-se 
			if (reiBranco.getCasasEmAtaque(this).size()==0){
				// Veririca se o cavalo adversário dá cheque mate.
				if (this.cavaloDaChequeMate(reiBranco)){
					return true;
				}
				
				Vector pecasQueAtacam = this.pecasQueAtacamRei(reiBranco);
				// Verifica se mais de uma peça está atacando o rei.
				if (pecasQueAtacam.size()>=2){
					return true;
				} else{
					Peca peca = (Peca)pecasQueAtacam.get(0);
					CasaGUI casaTemp = (CasaGUI)this.getCasa(peca.getPosicaoX(),
							peca.getPosicaoY());
					// verifica se a peça que está atacando o rei também está 
					// sendo atacada.
					if (casaTemp.isAtaqueBranco())
						return false;
					// verifica se uma peça branca impede que a peça preta der 
					// cheque mate.
					if (pecaBrancaImpedeCheque((Peca)pecasQueAtacam.get(0), 
							reiBranco)){
						return false;
					}else{
						return true;
					}	
				} // fim else
			} // fim do if(reiBranco.getCasasEmAtaque(this).size()==0)
		}
		return false;
	}

	/**
	 * Verifica se uma peça branca impede o cheque mate no seu rei. 
	 * @param pecaAdversaria
	 * @param reiBranco
	 * @return boolean 
	 */	
	private boolean pecaBrancaImpedeCheque(Peca pecaAdversaria, 
			ReiBrancoGUI reiBranco) {
		
		int xRei = reiBranco.getPosicaoX();
		int yRei = reiBranco.getPosicaoY();
		if (pecaAdversaria.getPosicaoX() < xRei &&
			pecaAdversaria.getPosicaoY() < yRei ){
			xRei--;
			yRei--;
			while (pecaAdversaria.getPosicaoX() < xRei){
				if (((CasaGUI)getCasa(xRei,yRei)).isAtaqueBranco()){
					return true;
				}
				xRei--;
				yRei--;
			}
		} else 	if ((pecaAdversaria.getPosicaoX() < xRei) &&
					 pecaAdversaria.getPosicaoY() == yRei){
			xRei--;
			while (pecaAdversaria.getPosicaoX() < xRei){
				if (((CasaGUI)getCasa(xRei,yRei)).isAtaqueBranco()){
					return true;
				}
				xRei--;				
			}
		} else if ((pecaAdversaria.getPosicaoX() < xRei) &&
					pecaAdversaria.getPosicaoY() > yRei){
			xRei--;
			yRei++;
			while(pecaAdversaria.getPosicaoX() < xRei){
				if (((CasaGUI)getCasa(xRei,yRei)).isAtaqueBranco()){
					return true;
				}
				xRei--;
				yRei++;					
			}
		} else if ((pecaAdversaria.getPosicaoX() == xRei) &&
				   (pecaAdversaria.getPosicaoY() > yRei)){
			yRei++;
			while(pecaAdversaria.getPosicaoY() > yRei){
				if (((CasaGUI)getCasa(xRei,yRei)).isAtaqueBranco()){
					return true;
				}
				yRei++;				
			}
		} else if ((pecaAdversaria.getPosicaoX() > xRei) &&
				   (pecaAdversaria.getPosicaoY() > yRei)){
			xRei++;
			yRei++;
			while(pecaAdversaria.getPosicaoX() > xRei){
				if (((CasaGUI)getCasa(xRei,yRei)).isAtaqueBranco()){
					return true;
				}
				xRei++;
				yRei++;								
			}
		} else if ((pecaAdversaria.getPosicaoX() > xRei) &&
				   (pecaAdversaria.getPosicaoY() == yRei)){
			xRei++;
			while(pecaAdversaria.getPosicaoX() > xRei){
				if (((CasaGUI)getCasa(xRei,yRei)).isAtaqueBranco()){
					return true;
				}
				xRei++;				
			}
		} else if ((pecaAdversaria.getPosicaoX() > xRei) &&
				   (pecaAdversaria.getPosicaoY() < yRei)){
			xRei++;
			yRei--;
			while (pecaAdversaria.getPosicaoX() > xRei){
				if (((CasaGUI)getCasa(xRei,yRei)).isAtaqueBranco()){
					return true;
				}
				xRei++;
				yRei--;				
			}
		} else if ((pecaAdversaria.getPosicaoX() == xRei) &&
				   (pecaAdversaria.getPosicaoY() < yRei)){
			yRei--;
			while(pecaAdversaria.getPosicaoY()<yRei){
				if (((CasaGUI)getCasa(xRei,yRei)).isAtaqueBranco()){
					return true;
				}
				yRei--;				
			}
		}
		return false;
	}
	
	/**
	 * Verifica se uma peça preta impede o cheque mate no seu rei. 
	 * @param pecaAdversaria
	 * @param reiPreto
	 * @return boolean 
	 */	
	private boolean pecaPretaImpedeCheque(Peca pecaAdversaria, 
			ReiPretoGUI reiPreto) {
		
		int xRei = reiPreto.getPosicaoX();
		int yRei = reiPreto.getPosicaoY();
		if (pecaAdversaria.getPosicaoX() < xRei &&
			pecaAdversaria.getPosicaoY() < yRei ){
			xRei--;
			yRei--;
			while (pecaAdversaria.getPosicaoX() < xRei){
				if (((CasaGUI)getCasa(xRei,yRei)).isAtaquePreto()){
					return true;
				}
				xRei--;
				yRei--;
			}
		} else 	if ((pecaAdversaria.getPosicaoX() < xRei) &&
					 pecaAdversaria.getPosicaoY() == yRei){
			xRei--;
			while (pecaAdversaria.getPosicaoX() < xRei){
				if (((CasaGUI)getCasa(xRei,yRei)).isAtaquePreto()){
					return true;
				}
				xRei--;				
			}
		} else if ((pecaAdversaria.getPosicaoX() < xRei) &&
					pecaAdversaria.getPosicaoY() > yRei){
			xRei--;
			yRei++;
			while(pecaAdversaria.getPosicaoX() < xRei){
				if (((CasaGUI)getCasa(xRei,yRei)).isAtaquePreto()){
					return true;
				}
				xRei--;
				yRei++;					
			}
		} else if ((pecaAdversaria.getPosicaoX() == xRei) &&
				   (pecaAdversaria.getPosicaoY() > yRei)){
			yRei++;
			while(pecaAdversaria.getPosicaoY() > yRei){
				if (((CasaGUI)getCasa(xRei,yRei)).isAtaquePreto()){
					return true;
				}
				yRei++;				
			}
		} else if ((pecaAdversaria.getPosicaoX() > xRei) &&
				   (pecaAdversaria.getPosicaoY() > yRei)){
			xRei++;
			yRei++;
			while(pecaAdversaria.getPosicaoX() > xRei){
				if (((CasaGUI)getCasa(xRei,yRei)).isAtaquePreto()){
					return true;
				}
				xRei++;
				yRei++;								
			}
		} else if ((pecaAdversaria.getPosicaoX() > xRei) &&
				   (pecaAdversaria.getPosicaoY() == yRei)){
			xRei++;
			while(pecaAdversaria.getPosicaoX() > xRei){
				if (((CasaGUI)getCasa(xRei,yRei)).isAtaquePreto()){
					return true;
				}
				xRei++;				
			}
		} else if ((pecaAdversaria.getPosicaoX() > xRei) &&
				   (pecaAdversaria.getPosicaoY() < yRei)){
			xRei++;
			yRei--;
			while (pecaAdversaria.getPosicaoX() > xRei){
				if (((CasaGUI)getCasa(xRei,yRei)).isAtaquePreto()){
					return true;
				}
				xRei++;
				yRei--;				
			}
		} else if ((pecaAdversaria.getPosicaoX() == xRei) &&
				   (pecaAdversaria.getPosicaoY() < yRei)){
			yRei--;
			while(pecaAdversaria.getPosicaoY()<yRei){
				if (((CasaGUI)getCasa(xRei,yRei)).isAtaquePreto()){
					return true;
				}
				yRei--;				
			}
		}
		return false;
	}

	/*
	 * Pega todas as peças que estão atacando o rei.
	 */
	private Vector pecasQueAtacamRei(Peca rei)
	{
		Vector pecas = new Vector();
		Peca pecaTemp;
		for (int x = 0; x < 8; x ++){
			for (int y = 0; y < 8; y ++){
				pecaTemp = getCasa(x,y).getPeca();
				if (pecaTemp!=null && pecaTemp.getCor()==Color.BLACK){
					if (pecaTemp.getCasasEmAtaque(this).contains(
							this.getCasa(rei.getPosicaoX(),rei.getPosicaoY()))){
						pecas.add(pecaTemp);
					}
				} else if (pecaTemp!=null && pecaTemp.getCor()==Color.WHITE){
					if (pecaTemp.getCasasEmAtaque(this).contains(
							this.getCasa(rei.getPosicaoX(),rei.getPosicaoY()))){
						pecas.add(pecaTemp);
					}
				}
			}
		}
		
		return (Vector)pecas.clone();
	}
	
	/*
	 * Verifica se o cavalo está dando cheque mate.
	 */
	private boolean cavaloDaChequeMate(Peca reiBranco)
	{
		Vector casasTemp = new Vector();
		casasTemp.add(this.getCasa(reiBranco.getPosicaoX()-1,
				reiBranco.getPosicaoY()-2));
		casasTemp.add(this.getCasa(reiBranco.getPosicaoX()+1,
				reiBranco.getPosicaoY()-2));
		casasTemp.add(this.getCasa(reiBranco.getPosicaoX()-2,
				reiBranco.getPosicaoY()+1));
		casasTemp.add(this.getCasa(reiBranco.getPosicaoX()-2,
				reiBranco.getPosicaoY()-1));
		casasTemp.add(this.getCasa(reiBranco.getPosicaoX()+2,
				reiBranco.getPosicaoY()+1));
		casasTemp.add(this.getCasa(reiBranco.getPosicaoX()+2,
				reiBranco.getPosicaoY()-1));
		casasTemp.add(this.getCasa(reiBranco.getPosicaoX()-1,
				reiBranco.getPosicaoY()+2));
		casasTemp.add(this.getCasa(reiBranco.getPosicaoX()+1,
				reiBranco.getPosicaoY()+2));
		Iterator it = casasTemp.iterator();
		Casa casaTemp;
		while (it.hasNext()){
			casaTemp = (Casa)it.next();
			if (reiBranco.getCor()==Color.WHITE){
				if (casaTemp!=null && casaTemp.getPeca()!=null 
						&& (casaTemp.getPeca() instanceof CavaloPretoGUI)){
					if (!((CasaGUI)casaTemp).isAtaqueBranco())
						return true;
							
				}
			} else{
				if (casaTemp!=null && casaTemp.getPeca()!=null 
						&& (casaTemp.getPeca() instanceof CavaloBrancoGUI)){
					if (!((CasaGUI)casaTemp).isAtaquePreto())
						return true;
							
				}
				
			}
		}
		return false;		
	}
	
	/**
	 * Verifica se o rei preto sofreu cheque mate.
	 * @return <code>true</code> o rei preto sofreu cheque mate.
	 */
	public boolean isChequeMatePreto()
	{
		ReiPretoGUI reiPreto = null;
		CasaGUI casaDoReiTemp = null;
		CasaGUI casaDoRei = null;
		// Pega o rei branco no tabuleiro.
		for (int x = 0; x < 8 ; x++)
			for (int y = 0; y < 8; y++){
				casaDoReiTemp = (CasaGUI)this.getCasa(x,y);
				if ( (casaDoReiTemp.getPeca() instanceof ReiPretoGUI)){
					casaDoRei = casaDoReiTemp;
					reiPreto = (ReiPretoGUI)casaDoRei.getPeca();
				}
			}
			
		// verifica se o rei está sendo atacado 
		if (casaDoRei.isAtaqueBranco()){
			// se o rei não pode mover-se 
			if (reiPreto.getCasasEmAtaque(this).size()==0){
				// Veririca se o cavalo adversário dá cheque mate.
				if (this.cavaloDaChequeMate(reiPreto)){
					return true;
				}
				
				Vector pecasQueAtacam = this.pecasQueAtacamRei(reiPreto);
				// Verifica se mais de uma peça está atacando o rei.
				if (pecasQueAtacam.size()>=2){
					return true;
				} else{
					Peca peca = (Peca)pecasQueAtacam.get(0);
					CasaGUI casaTemp = (CasaGUI)this.getCasa(peca.getPosicaoX(),
							peca.getPosicaoY());
					// verifica se a peça que está atacando o rei também está 
					// sendo atacada.
					if (casaTemp.isAtaquePreto())
						return false;
					// verifica se uma peça branca impede que a peça preta der 
					// cheque mate.
					if (pecaPretaImpedeCheque((Peca)pecasQueAtacam.get(0), 
							reiPreto)){
						return false;
					}else{
						return true;
					}	
				} // fim else
			} // fim do if(reiBranco.getCasasEmAtaque(this).size()==0)
		}
		return false;
	}
	
	/**
	 * Remove todas as peças do tabuleiro.
	 */
	public void removeAllPecas()
	{
		this.removeAllAtaques();
		for (int x = 0; x < 8; x++)
			for (int y = 0; y < 8; y++){
				if (getCasa(x,y).getPeca()!=null)
					getCasa(x,y).removePeca();
			}
	}
	
	/**
	 * Fim do jogo.
	 * @return <code>true</code> indica que o jogo acabou.
	 */
	public boolean fimDeJogo()
	{
		return fimDeJogo;
	}
	
	/**
	 * Mensagem do fim de jogo.
	 * @param mensagem do fim do jogo.
	 */
	public void mensagemFimJogo(String mensagem) {
		
		JOptionPane.showMessageDialog(this,mensagem,
				"darlan_ads@yahoo.com.br",JOptionPane.INFORMATION_MESSAGE,
				CarregaImagemUtil.createImageIcon(Global.IMG_DARLAN));
		fimDeJogo = true;
		super.enable(false);
		super.repaint();
	}
	
}
