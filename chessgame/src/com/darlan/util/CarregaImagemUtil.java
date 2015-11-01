/*
 * @(#)CarregaImagemUtil.java 1.0 11/10/2005
 *
 * Darlan A. dos Santos -> darlan_ads@yahoo.com.br
 *
 * Copyright 2005 D & D Desenvolvimento Software.
 */
package com.darlan.util;

import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;

/**
 * Classe responsavel em carregar as imagens do jogo.
 * 
 * @version 1.0 11/10/2005
 * @author Darlan A. dos Santos
 */
public final class CarregaImagemUtil {

    /**
     * Metodo utilizado para pegar as imagens.
     * 
     * @param path
     * @param description
     * @return ImageIcon
     */
    public final static ImageIcon createImageIcon(String path) {
	Image imgURL = Toolkit.getDefaultToolkit().getImage(path);
	if (imgURL != null) {
	    return new ImageIcon(imgURL, "");
	} else {
	    return null;
	}
    }

}
