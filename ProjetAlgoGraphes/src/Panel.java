import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Cette classe représente Le composant qui affiche le plan du métro et tous les bouttons qui représente une station de Métro
 * @version 0.9.0
 * @author FALL, HARAL, TAMARA
 */

public class Panel extends JPanel { ;

    /**
     * Constructeur par défault du composant Panel
     */
    public Panel(){
        this.setLayout(null);
    }

    @Override
    public void paintComponent(Graphics g) {
        try{
            Image img = ImageIO.read(new File("../metro.bmp"));
            g.drawImage(img, 0, 0, this);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
