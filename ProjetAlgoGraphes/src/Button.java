import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Button extends JButton implements MouseListener {
    private Station mStation;
    private JLabel  mLabel;
    private boolean mClick;

    /**
     * Représente une Station de Metro
     * @param p coordonnées d'une station de Metro
     * @param station station de Metro
     */
    public Button(Point p, Station station){
        mStation = station;
        mClick   = true;
        mLabel   = new JLabel();
        this.setBounds((int)p.getX(), 700 - (int)p.getY(), 10, 10);
        this.addMouseListener(this);
        this.setText(Integer.toString(mStation.getNumberStation()));
    }


    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        if( mClick && this.getHeight() < 30 ){
            this.setBackground(Color.GREEN);
            this.setSize(new Dimension(20, 20));
        }
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        setToolTipText(this.mStation.getNameStation());
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        setToolTipText(this.mStation.getNameStation());
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

        setToolTipText(this.mStation.getNameStation());
    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {
    }
}
