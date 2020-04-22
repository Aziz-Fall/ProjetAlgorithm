import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window extends JFrame implements ActionListener{

    private final static int sWIDTH       = 700;
    private final static int sHeight      = 700;
    private final static int sNB_STATIONS = 376;

    public Panel mPanel                         = new Panel();
    public Button[] mButtons                    = new Button[sNB_STATIONS];
    private Graph mGraph                        = new Graph();
    private DataCollector mDataCollector        = new DataCollector();
    private JPanel mMainContainer               = new JPanel();
    private JButton mResetButton                = new JButton("Reset");
    private JPanel mContainerResetButton        = new JPanel();
    private ButtonListenerReset mButtonListenerReset = new ButtonListenerReset();
    private PanelDrawPath mPanelDrawLine;
    private int mNbClick;
    private Dijkstra mDijkstra;
    private int mDeb, mFin;

    public Window(String name){
        mGraph = mDataCollector.recoverDataGraph(mGraph, "../metro_bis.txt");
        this.setName(name);
        this.setSize(new Dimension(sWIDTH, sHeight));
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(Window.EXIT_ON_CLOSE);
        this.setLayout(null);
        mDeb = mFin = -1;
        mNbClick        = 0;
        mDijkstra       = new Dijkstra();
        Point p = new Point(335,  675 );
        Point p2 = new Point( 222, 111);
        for( int i = 0; i < sNB_STATIONS; i++ ){
            mButtons[i] = new Button(mGraph.getStations().get(i).getCoordinated(), mGraph.getStations().get(i));
            mPanel.add(mButtons[i]);
            mButtons[i].addActionListener(this);
        }
        this.setContentPane(mPanel);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if( mNbClick == 0 ){
            mDeb = Integer.parseInt(actionEvent.getActionCommand());
            mDijkstra.setNumberLineBegin(mDeb);
            System.out.println("NBclik : " + mNbClick + " deb: " + mDeb);
            setNbClick(getNbClick() + 1);
        }
        else if( mNbClick == 1 ){
            mFin = Integer.parseInt(actionEvent.getActionCommand());
            mDijkstra.setNumberLineEnd(mFin);
            System.out.println("NBclik : " + mNbClick + " fin: " + mFin);
            setNbClick(getNbClick() + 1);
            if( mNbClick >= 2 ){
                mDijkstra.getShortestPath(mGraph, mDijkstra);
                mDijkstra.printPath(mDijkstra, mGraph);
                setNbClick(0);
                this.dispose();
                Path p = new Path(mDijkstra.getStationsPath(), "Chemin");
            }
        }
    }

    class ButtonListenerReset implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            mPanel.repaint();
        }
    }

    private void setNbClick(int nbClick) {
        mNbClick = nbClick;
    }

    private int getNbClick() {
        return mNbClick;
    }

    public static void main(String[] args) {
        Window window = new Window("Métro Parisiens");
    }

}
