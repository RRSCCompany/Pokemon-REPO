package tools;

import java.awt.Image;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.Border;

public class ManipulaImagem {

    public ManipulaImagem() {
    }
    public ImageIcon criaIcon(String caminho,int altura,int largura){
        ImageIcon icon = new ImageIcon();
        try{
            icon = new ImageIcon(getClass().getResource(caminho)); 
            Image img = icon.getImage(); 
            Image newimg = img.getScaledInstance(altura, largura, java.awt.Image.SCALE_SMOOTH); 
            icon = new ImageIcon(newimg); 
            
        }catch(Exception err){
            System.out.println("Caminho da imagem incorreto!");
        }
        return icon;
    }
    public JButton insereBotao(ImageIcon icon,String toolTip){
        JButton btTemp = new JButton();
        btTemp.setMargin(new Insets(0, 0, 0, 0)); 
        btTemp.setIcon(icon); 
        btTemp.setContentAreaFilled(false);
        Border emptyBorder = BorderFactory.createEmptyBorder();
        btTemp.setBorder(emptyBorder);
        btTemp.setToolTipText(toolTip);
        return btTemp;
    }
 
}
