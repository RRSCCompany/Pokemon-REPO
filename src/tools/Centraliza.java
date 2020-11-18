/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;

/**
 *
 * @author eu mesmo
 */
public class Centraliza {
    public void Centraliza(){
        
    }
    
    public void centralizaComponente(Component c) { 
        Dimension ds = Toolkit.getDefaultToolkit().getScreenSize();//tamanho da tela e guardado num Dimension
        Dimension dw = c.getSize();//tamanho do component guardado num Dimension
        c.setLocation((ds.width - dw.width) / 2, (ds.height - dw.height) / 2);//seta o ponto superior esquerdo do componente que ficará no centro
    }
    
    public void centralizaFilho(Component pai, Component filho){//entra com component pai e component filho
        Dimension d_pai = pai.getSize();//tamanho do pai guardado num Dimension
        Dimension d_filho = filho.getSize();//tamanho do filho guardado num Dimension
        
        int d_pai_largura = d_pai.width;
        int d_pai_altura = d_pai.height;
        
        int d_filho_largura = d_filho.width;
        int d_filho_altura = d_filho.height;
        
        Point p_pai = pai.getLocationOnScreen();//guarda o ponto top-left do pai
        //variáveis para guardar os pontos x,y top-left do filho
        int x_filho;
        int y_filho;
        
        if(d_pai_largura>=d_filho_largura){//largura do filho é menor que a do pai
            x_filho = (p_pai.x + ((d_pai_largura - d_filho_largura)/2));
        }else{//largura do filho é maior que a do pai
            x_filho = (p_pai.x - ((d_pai_largura - d_filho_largura)/2));
        }
        
        if(d_pai_altura>=d_filho_altura){//altura do filho é menor que a do pai
            y_filho = (p_pai.y + ((d_pai_altura - d_filho_altura)/2));
        }else{//altura do filho é maior que a do pai
            y_filho = (p_pai.y + ((d_pai_altura - d_filho_altura)/2));
        }
        
        filho.setLocation(x_filho, y_filho);//seta o ponto top-left(superior esquerdo)
    }
    
    
}
