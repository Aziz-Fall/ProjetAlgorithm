import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Cette classe représente L'algorithme de Dijsktra
 * @version 0.9.0
 * @author FALL, HARAL, TAMARA
 */
public class Dijkstra {
    
    private int mNumberLineBegin;
    private int mNumberLineEnd;
    private int mDuration;
    private int[] mParent;
    private ArrayList<Station> mStationsPath;

    private final static int sINFINITY    = 8888;
    private final static int sNO_TERMINUS = 9999;
    private final static int sNO_PARENT   = -1;
    private final static int sNB_STATIONS = 376;
    private final static int sVISITED     = 1;

    /**
     * Constructeur de la class Dijkstra initialisé avec la ligne de départ et la ligne d'arrivée.
     * @param numberLineBegin ligne de départ
     * @param numberLineEnd ligne de d'arrivée
     */
    public Dijkstra( int numberLineBegin, int numberLineEnd ){
        mNumberLineBegin = numberLineBegin;
        mNumberLineEnd   = numberLineEnd;
        mDuration        = 0;
        mParent          = new int[sNB_STATIONS];
        mStationsPath    = new ArrayList<Station>();
    }

    /**
     * Constructeur de la class Dijkstra par défaut.
     */
    public Dijkstra(){
        mDuration        = 0;
        mParent          = new int[sNB_STATIONS];
        mStationsPath    = new ArrayList<Station>();
    }

    /**
     * Modifie la ligne de départ
     * @param numberLineBegin ligne de départ
     */
    public void setNumberLineBegin(int numberLineBegin) {
        mNumberLineBegin = numberLineBegin;
    }

    /**
     * Modifie la ligne d'arrivée
     * @param numberLineEnd ligne d'arrivée
     */
    public void setNumberLineEnd(int numberLineEnd) {
        mNumberLineEnd = numberLineEnd;
    }

    /**
     * Retourne un ArrayList contenant les stations qui constituent le plus court chemin.
     * @return ArrayList du plus court chemin.
     */
    public ArrayList<Station> getStationsPath() {
        return mStationsPath;
    }

    /**
     * Retourne la ligne de départ de l'algorithme de Dijsktra.
     * @return ligne de départ
     */
    public int getNumberLineBegin() {
        return mNumberLineBegin;
    }

    /**
     * Retourne la ligne d'arrivée de l'algorithme de Dijsktra.
     * @return ligne d'arrivée
     */
    public int getNumberLineEnd() {
        return mNumberLineEnd;
    }

    /**
     * Retourne la duréé du plus court chemin
     * @return la duréé.
     */
    public int getDuration() {
        return mDuration;
    }

    /**
     * Retourne le tableau de parent des station de Métro traités dans l'algorithme de Dijsktra.
     * @return le tableau de parent
     */
    public int[] getParent() {
        return mParent;
    }

    /**
     * Modifie la duréé du plus court chemin
     * @param duration la durée
     */
    public void setDuration(int duration) {
        mDuration = duration;
    }

    /**
     * Modifie le tableau de parent des station de Métro traités dans l'algorithme de Dijsktra.
     * @param parent le tableau de parent
     */
    public void setParent(int[] parent) {
        mParent = parent;
    }

    /**
     * Modifie un ArrayList contenant les stations qui constituent le plus court chemin.
     * @param stationsPath
     */
    public void setStationsPath(ArrayList<Station> stationsPath) {
        mStationsPath = stationsPath;
    }

    /**
     * Déroule l'algorithme de dijsktra
     * @param g le graphe
     * @param d les données initialisées
     */
    public void getShortestPath(Graph g, Dijkstra d){
        int[] parent = new int[sNB_STATIONS], dist = new int[sNB_STATIONS], stationVisited = new int[sNB_STATIONS];
        initializations(dist, parent, stationVisited, g, d.getNumberLineBegin());
        stationVisited[0] = sVISITED;

        while ( !allNodesAreVisited(stationVisited) )
        {
            int stationToTrait        = index_min(stationVisited, dist);
            stationVisited[stationToTrait] = sVISITED;
            for( int i = 0; i < sNB_STATIONS; i++ )
                update(dist, parent, stationToTrait, i, g);
        }

        d.setParent(parent);

    }

    /**
     * Recherche l'index d'une station de métro qui a la plus petite valeur
     * @param node_visited tableau des stations de métro déjà visitées
     * @param dist tableau de distance
     * @return  index de la station qui a la plus petite valeur
     */
    private int index_min(int node_visited[], int dist[])
    {
        int min = sINFINITY, node = -1;
        for( int i = 0; i < sNB_STATIONS; i++)
        {
            if( node_visited[i] != sVISITED && dist[i] <= min )
            {
                min  = dist[i];
                node = i;
            }
        }
        return node;
    }

    /**
     * Initialise les données de l'algorithme de Dijsktra.
     * @param dist tableau de distances
     * @param parent tableau de parent
     * @param stationVisited station visitée
     * @param g le graphe
     * @param begin station de départ
     */
    private void initializations(int[] dist, int[] parent, int[] stationVisited, Graph g, int begin)
    {
        for( int i = 0; i < sNB_STATIONS; i++)
        {
            parent[i] = ( g.getNetWork()[begin][i].getWeight() == sINFINITY ) ? sNO_PARENT : begin;
            stationVisited[i] = 0;
            dist[i] = g.getNetWork()[begin][i].getWeight();
        }
        dist[begin]   = 0;
        parent[begin] = sNO_PARENT;
    }

    /**
     * Améliore la distance entre deux stations de métro.
     * @param dist tableau de distances
     * @param parent tableau de parents
     * @param t station visitée
     * @param k station voisine
     * @param g graphe
     */
    public void update(int dist[], int parent[], int t, int k, Graph g)
    {
        if( dist[k] > dist[t] + g.getNetWork()[t][k].getWeight() )
        {
            dist[k]   = dist[t] + g.getNetWork()[t][k].getWeight();
            parent[k] = t;
        }
    }

    /**
     * Vérifie si tous les stations sont visitées.
     * @param stationVisited tableau contenant les stations visitées.
     * @return boolean
     */
    private boolean allNodesAreVisited(int stationVisited[])
    {
        for( int i = 0; i < sNB_STATIONS; i++)
            if( stationVisited[i] != sVISITED )
                return false;
        return true;
    }

    /**
     * Affiche le plus court chemin.
     * @param d le resultat de l'algorithme de Dijsktra
     * @param g le graphe
     */
    public void printPath(Dijkstra d, Graph g){
        int e = d.getNumberLineEnd();
        //Récupétation des chemins
        while(e != d.getNumberLineBegin())
        {
            d.getStationsPath().add(g.getStations().get(e));
            int tmp = e;
            e = d.getParent()[e];
            d.setDuration(d.getDuration() + g.getNetWork()[tmp][e].getWeight());
        }
        d.getStationsPath().add(g.getStations().get(e));

        //Inversion du liste de chemin.
        Collections.reverse(d.getStationsPath());

        ArrayList<Station> s = new ArrayList<Station>();
        s.add(d.getStationsPath().get(0));
        for( int i = 1; i < d.getStationsPath().size() - 1; i++ ){
            if( d.getStationsPath().get(i).getNumberLine() != d.getStationsPath().get(i + 1).getNumberLine() ){
                s.add(d.getStationsPath().get(i));
                s.add(d.getStationsPath().get(i + 1));
            }

        }
        s.add(d.getStationsPath().get(d.getStationsPath().size() - 1));

        //Traçage du chemin.
        System.out.println("Vous êtes: " + d.getStationsPath().get(0).getNameStation());
        if( 2 < d.getStationsPath().size() ){
            int x1 = d.getStationsPath().get(1).getNumberStation(), y1 = d.getStationsPath().get(2).getNumberStation();
            int numStation = g.getNetWork()[x1][y1].getTerminus();
            if( numStation == sNO_TERMINUS )
                numStation = x1;
            if( numStation == 30 ){
                System.out.print("- Prenez la ligne 3bis ");
            }else if( numStation == 70 ){
                System.out.print("- Prenez la ligne 7bis ");
            }else {
                System.out.print("- Prenez la ligne " + d.getStationsPath().get(0).getNumberLine());
            }
            System.out.println(" direction " + g.getStations().get(numStation).getNameStation());
        }
        else {
            int x1 = d.getStationsPath().get(0).getNumberStation(), y1 = d.getStationsPath().get(1).getNumberStation();
            int numStation = g.getNetWork()[x1][y1].getTerminus();
            System.out.print("- Prenez la ligne " + d.getStationsPath().get(0).getNumberLine());
            System.out.println(" direction " + g.getStations().get(numStation).getNameStation());
        }

        for( int i = 2; i < s.size() - 1; i += 2 ){
            int x = s.get(i).getNumberStation(),
                    y = s.get(i + 1).getNumberStation();
            int station = g.getNetWork()[x][y].getTerminus();
            System.out.print("- A " + g.getStations().get(x).getNameStation() );
             if( station == 30 ){
                System.out.print(" prenez la ligne 3bis ");
            }else if( station == 70 ){
                System.out.print(" prenez la ligne 7bis ");
            }else {
                System.out.print(" prenez la ligne " + g.getStations().get(x).getNumberLine());
            }
            if( station == sNO_TERMINUS ){
                station = y;
            }
                System.out.println(" direction " + g.getStations().get(station).getNameStation());
        }

        System.out.println("Vous devriez arriver à " + g.getStations().get(d.getNumberLineEnd()).getNameStation() + " dans " + getTime(d.getDuration()));
    }

    /**
     * Convertit la durée du trajectoire en Heure , minites.
     * @param times la durée
     * @return une chaine de caractére contenant les données converties.
     */
    private String getTime(int times){
        int h = times / 3600, min = (times % 3600)/60, se = (times % 3600)%60;
        String s = "";
        s += h +" H " + min +" min " + se + " s.";
        return s;
    }
}
