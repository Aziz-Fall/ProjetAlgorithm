import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class Path extends JFrame {
    private final static int sWIDTH       = 700;
    private final static int sHeight      = 725;

    private PanelDrawPath mPanelDrawPath;
    private JButton mButtonClose;
    private JPanel mPanelButton;

    private boolean mCloseWindow;

    public Path(ArrayList<Station> path, String title){
        this.setName(title);
        this.setSize(new Dimension(sWIDTH, sHeight));
        this.setResizable(false);
        this.setUndecorated(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(Window.EXIT_ON_CLOSE);

        mButtonClose        = new JButton("Close Window");
        mPanelButton        = new JPanel();
        mButtonClose.setBackground(Color.RED);
        mButtonClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                mButtonClose.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent mouseEvent) {
                        mButtonClose.setBackground(Color.GREEN);
                        setCloseWindow(true);
                        closeWindow();
                    }

                    @Override
                    public void mousePressed(MouseEvent mouseEvent) { }

                    @Override
                    public void mouseReleased(MouseEvent mouseEvent) { }

                    @Override
                    public void mouseEntered(MouseEvent mouseEvent) { }

                    @Override
                    public void mouseExited(MouseEvent mouseEvent) { }
                });
            }
        });
        mPanelButton.add(mButtonClose);
        mPanelDrawPath = new PanelDrawPath(path);

        this.setLayout(new BorderLayout());
        this.getContentPane().add(mButtonClose, BorderLayout.SOUTH);
        this.getContentPane().add(mPanelDrawPath, BorderLayout.CENTER);
        this.setVisible(true);
    }

    private void setCloseWindow(boolean closeWindow) {
        mCloseWindow = closeWindow;
    }

    private void closeWindow(){
        if( mCloseWindow ) {
            this.dispose();
        }
    }
}
