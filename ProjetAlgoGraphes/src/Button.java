import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Button extends JButton implements MouseListener {
    private Station mStation;
    private JLabel  mLabel;
    private boolean mClick;
    public Button(Point p, Station station){
        mStation = station;
        mClick   = true;
        mLabel   = new JLabel();
        this.setBounds((int)p.getX(), 700 - (int)p.getY(), 8, 8);
        this.addMouseListener(this);
        this.setText(Integer.toString(mStation.getNumberStation()));
    }

    public void setClick(boolean click) {
        mClick = click;
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        if( mClick ){
            this.setBackground(Color.GREEN);
            this.setSize(new Dimension(30, 30));
            System.out.println("On: " + mStation.getNameStation());
        }
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

        setToolTipText(this.mStation.getNameStation());
    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {
    }

    public Station getStation() {
        return mStation;
    }
}
