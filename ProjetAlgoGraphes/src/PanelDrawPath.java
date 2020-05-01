import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Cette classe représente Le composant qui affiche le plan du métro et
 * trace le plus court chemin entre deux stations de métro sélectionnées.
 * @version 0.9.0
 * @author FALL, HARAL, TAMARA
 */
public class PanelDrawPath extends JPanel {
    private static final int sHEIGHT  = 700;
    private ArrayList<Station> mPath;


    /**
     * Constructeur du composant PanelDrawPath.
     * @param path ArrayList de station.
     */
    public PanelDrawPath(ArrayList<Station> path){
        mPath = path;
    }

    @Override
    public void paintComponent(Graphics g) {
        try{
            Image img = ImageIO.read(new File("../metro.bmp"));
            g.drawImage(img, 0, 0, this);
        }catch (IOException e){
            e.printStackTrace();
        }

        Graphics2D g2 = (Graphics2D)g;
        g2.setStroke(new BasicStroke(4f));
        g2.setColor(Color.BLACK);

        for( int i = 0; i < mPath.size() - 1; i++ ){
            g2.draw(new Line2D.Double(mPath.get(i).getCoordinated().getX(),
                                      sHEIGHT - mPath.get(i).getCoordinated().getY(),
                                      mPath.get(i + 1).getCoordinated().getX(),
                                      sHEIGHT - mPath.get(i + 1).getCoordinated().getY()));
        }

        Button b1 = new Button(mPath.get(0).getCoordinated(), mPath.get(0));
        Button b2 = new Button(mPath.get(mPath.size() - 1).getCoordinated(), mPath.get(mPath.size() - 1));
        b1.setBackground(Color.GREEN);
        b2.setBackground(Color.BLUE);
        b1.setSize(new Dimension(30, 30));
        b2.setSize(new Dimension(30, 30));
        this.add(b1);
        this.add(b2);
    }
}
