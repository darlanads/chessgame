/*
 * @(#)AppXadrez.java 1.0 11/10/2005
 *
 * Darlan A. dos Santos -> darlan_ads@yahoo.com.br
 *
 * Copyright 2005 D & D Desenvolvimento Software.
 */
package com.darlan.xadrez.gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import com.darlan.util.CarregaImagemUtil;
import com.darlan.util.Global;
import com.darlan.xadrez.PosicaoInvalidaException;
import com.darlan.xadrez.Tabuleiro;

/**
 * Classe que contem o main para executar o jogo de xadrez.
 * 
 * @version 1.0 11/10/2005
 * @author Darlan A. dos Santos
 */
public class AppXadrez extends JFrame{
	
	// atributos.
	private Color vezDeJogar = Color.WHITE;
	private Vector casas = new Vector();
	private Container c;
	private JMenuBar menuBar;
	private JMenu jogo, sobre;
	private JMenuItem novo, sair, sobreJogo;
	private TabuleiroGUI tabuleiro;
	private AppXadrez xadrez=this;
	
	/**
	 * Construtor
	 * @param mensagem Mensagem.
	 */
	public AppXadrez(String mensagem)
	{
		
		super(mensagem);
		super.setResizable(false);
		
		// cria a barra de menu.
		menuBar = new JMenuBar();
		
		// cria o subMenu novo
		novo = new JMenuItem("Novo");
		novo.setMnemonic('N');
		novo.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent event){
					xadrez.inciaJogo();
				}
			}
		);
		// cria subMenu Sair
		sair = new JMenuItem("Sair");
		sair.setMnemonic('S');
		sair.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent event){
					System.exit(0);
				}
			}
		);
		
		// cria subMenu Sobre Jogo
		sobreJogo = new JMenuItem("Jogo");
		sobreJogo.setMnemonic('J');
		sobreJogo.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent event)
				{
					JOptionPane.showMessageDialog(xadrez,
						"Jogo desenvolvido por mim Darlan A. dos Santos,\n "+
						"se deseja o código fonte deste jogo, envie um\n"+
						"e-mail para darlan_ads@yahoo.com.br \n\n"+
						"\tJogo desenvolvido quando estudante da\n"+
						" graduação (UFCG) na disciplina Programação II,\n"+
						"da qual posteriormente fui monitor."+
						"\n\n Meu site: http://darlanads.cjb.net","XADREZ",
						JOptionPane.WARNING_MESSAGE,
						CarregaImagemUtil.createImageIcon(Global.IMG_DARLAN));
				}
			}
		);
		
		// cria o menu Jogo
		jogo = new JMenu("Jogo");
		jogo.setMnemonic('J');
		jogo.add(novo);
		jogo.add(sair);
		
		// cria o menu Sobre
		sobre = new JMenu("Sobre");
		sobre.setMnemonic('S');
		sobre.add(sobreJogo);
		
		
		menuBar.add(jogo);
		menuBar.add(sobre);

		super.setJMenuBar(menuBar);
		
		c = getContentPane();		
		tabuleiro = new TabuleiroGUI();
		tabuleiro.adicionaPecasDoXadrez();
		tabuleiro.addMouseListener(
			new MouseAdapter(){
				private int x;
				private int y;
				public void mouseClicked(MouseEvent event){
					try{
					x = event.getX();
					y = event.getY();
					
					CasaGUI casaTemp = (CasaGUI)event.getComponent()
							.getComponentAt(x,y);
	
					// se clicou numa peça pela primeira vez
					//if(!tabuleiro.isChequeMateBranco())
					if (!tabuleiro.fimDeJogo()){
					
						if ((casaTemp.getPeca()!=null) && (casas.isEmpty()) 
								&& (vezDeJogar==casaTemp.getPeca().getCor())){
							casaTemp.pintaBorda(); 
							casas.add(casaTemp);
							// se clicou numa casa diferente da anterior e 
							// clicou pela segunda vez
						} else if ((!casas.contains(casaTemp))
								&& !(casas.isEmpty())){
							Iterator it = casas.iterator();
							CasaGUI temp = (CasaGUI)it.next();
							try{
								temp.getPeca().move(temp,casaTemp, tabuleiro);
								casas.removeAllElements();
								mudaVezDeJogar();
								if (vezDeJogar==Color.BLACK)
									movePassantePreto(tabuleiro);
								else
									movePassanteBranco(tabuleiro);
							} catch(PosicaoInvalidaException e){
								temp.removeBorda();
								casas.removeAllElements();
							}
							
						}
					}
					} catch (Exception e)
					{
						System.out.println("Clicou fora do tabuleiro!");
						System.out.println(e.getMessage());
					}
				}
				
			}
		);
		c.add(tabuleiro);					 
		
	}

	/**
	 * Inicia o jogo.
	 */
	public void inciaJogo()
	{
		vezDeJogar = Color.WHITE;
		tabuleiro.removeAllPecas();
		tabuleiro.adicionaPecasDoXadrez();
	}
	
	/*
	 * Realiza a jogada passante.
	 */
	private void movePassanteBranco(Tabuleiro tabuleiro)
	{
		for (int x = 0; x < 8 ; x++)
			tabuleiro.getCasa(x,5).setPassante(false);
	}
	
	/*
	 * Realiza a jogada passante.
	 */
	private void movePassantePreto(Tabuleiro tabuleiro)
	{
		for (int x = 0; x < 8 ; x++)
			tabuleiro.getCasa(x,2).setPassante(false);
	}
	
	/**
	 * Muda a vez do jogodor.
	 */
	public void mudaVezDeJogar()
	{
		if (vezDeJogar == Color.BLACK){
			vezDeJogar = Color.WHITE;
		}
		else{
			vezDeJogar = Color.BLACK;
		}
	}							
	
	/**
	 * Método main, inicia o jogo.
	 * @param args Não é necessário ser passado nenhum argumento.
	 */
	public static void main(String args[])
	{
		AppXadrez test = new AppXadrez("Darlan A. dos Santos");
		test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		test.setSize(407,450);
		test.setVisible(true);
		
	}
	
}
