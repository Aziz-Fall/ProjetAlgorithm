import java.util.ArrayList;

public class Graph {

    private final static int NB_STATIONS = 376;
    private ArrayList<Station> mStations;
    private Bow[][] mNetWork; // la matrice qui représente tous les arcs.

    /**
     * Représent un graphe vide
     */
    public Graph(){
        mStations = new ArrayList<Station>(NB_STATIONS);
        mNetWork = initNetWork();
    }

    /**
     * Initialise tous les arcs et les poids avec des valeurs par défault.
     * @return la matrice représentant les arcs.
     */
    public Bow[][] initNetWork(){
        Bow[][] netWork = new Bow[NB_STATIONS][NB_STATIONS];
        for( int i = 0; i < NB_STATIONS; i++)
            for( int j = 0; j < NB_STATIONS; j++)
                netWork[i][j] = new Bow();

        return netWork;
    }

    /**
     * Modifie les stations de Métro.
     * @param stations stations de Métro
     */
    public void setStations(ArrayList<Station> stations) {
        mStations = stations;
    }

    /**
     * Modifie la matrice qui représente les arcs du graphe.
     * @param netWork matrice.
     */
    public void setNetWork(Bow[][] netWork) {
        mNetWork = netWork;
    }

    /**
     * Retourne le nombre de strations de Métro
     * @return NB_STATIONS
     */
    public static int getNbStations() {
        return NB_STATIONS;
    }

    /**
     * Retourne tous les stations
     * @return stations
     */
    public ArrayList<Station> getStations() {
        return mStations;
    }

    /**
     * Retourne la matrice d'arcs.
     * @return matrice
     */
    public Bow[][] getNetWork() {
        return mNetWork;
    }
}
