import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class PanelDrawPath extends JPanel {
    private static final int sWIDTH  = 725;
    private static final int sHEIGHT = 20;
    private ArrayList<Station> mPath;
    public PanelDrawPath(ArrayList<Station> path){
        mPath = path;
    }
    @Override
    public void paintComponent(Graphics g) {
        try{
            System.out.println("In repaint...");
            Image img = ImageIO.read(new File("../metro_bis.bmp"));
            g.drawImage(img, 0, 0, this);
        }catch (IOException e){
            e.printStackTrace();
        }

        Graphics2D g2 = (Graphics2D)g;
        g2.setStroke(new BasicStroke(4f));
        g2.setColor(Color.BLACK);

        for( int i = 0; i < mPath.size() - 1; i++ ){
            g2.draw(new Line2D.Double(mPath.get(i).getCoordinated().getX(),
                                      sWIDTH - mPath.get(i).getCoordinated().getY(),
                                      mPath.get(i + 1).getCoordinated().getX(),
                                      sWIDTH - mPath.get(i + 1).getCoordinated().getY()));
        }

        g.setColor(Color.BLUE);
        g.fillOval((int)mPath.get(0).getCoordinated().getX(),
                sWIDTH - (int)mPath.get(0).getCoordinated().getY(),
                sHEIGHT, sHEIGHT);
        g.setColor(Color.GREEN);
        g.fillOval((int)mPath.get(mPath.size() - 1).getCoordinated().getX(),
                sWIDTH - (int)mPath.get(mPath.size() - 1).getCoordinated().getY(),
                sHEIGHT, sHEIGHT);
    }
}
