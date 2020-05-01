import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Cette classe représente La fenêtre principale qui affiche le plan du métro et
 * tous les buttons qui représente une station de métro
 * @version 0.9.0
 * @author FALL, HARAL, TAMARA
 */
public class Window extends JFrame implements ActionListener{

    private final static int sWIDTH       = 700;
    private final static int sHeight      = 700;
    private final static int sNB_STATIONS = 376;

    public Panel mPanel                         = new Panel();
    public Button[] mButtons                    = new Button[sNB_STATIONS];
    private Graph mGraph                        = new Graph();
    private DataCollector mDataCollector        = new DataCollector();
    private int mNbClick;
    private Dijkstra mDijkstra;
    private int mDeb, mFin;

    /**
     * Constructeur de la class
     * @param name le nom de la fenêtre.
     */
    public Window(String name){
        mGraph = mDataCollector.recoverDataGraph(mGraph, "../metro.txt");
        this.setName(name);
        this.setSize(new Dimension(sWIDTH, sHeight));
        this.setResizable(false);
        this.setUndecorated(true);
        //this.setLocationRelativeTo(null);
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
            setNbClick(getNbClick() + 1);
        }
        else if( mNbClick == 1 ){
            mFin = Integer.parseInt(actionEvent.getActionCommand());
            mDijkstra.setNumberLineEnd(mFin);
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

    /**
     * Modifie l'attribut mNbClick qui représente le nombre de click effectué par l'utilisateur.
     * @param nbClick le nombre de click
     */
    private void setNbClick(int nbClick) {
        mNbClick = nbClick;
    }

    /**
     * Retourne le nombre de clic effectué par l'utilisateur.
     * @return nbClick.
     */
    private int getNbClick() {
        return mNbClick;
    }


    public static void main(String[] args) {
        Window window = new Window("Métro Parisiens");
    }

}
