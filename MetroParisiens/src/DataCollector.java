import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class DataCollector {

    private static final char sSUMMIT           = 'V';
    private static final char sSTOPPED          = 'E';
    private static final String sSEPARATOR      = " ";

    /**
     * Initialiser le graphe
     * @param g le graphe
     * @param nameFile le nom du fichier contenant les données du graphe
     * @return le graphe g initialisé
     */
    public Graph recoverDataGraph(Graph g, String nameFile){
        try {
            File f                = new File(nameFile);
            BufferedReader buffer = new BufferedReader( new FileReader(f) );
            String readedLine     = "";
            readedLine = buffer.readLine();
            while( readedLine != null ){
                if( readedLine.charAt(0) == sSUMMIT ){
                    g.getStations().add( splitLine(readedLine) );
                }
                else if( readedLine.charAt(0) == sSTOPPED ) {
                    String[] s;
                    s          = readedLine.split(sSEPARATOR, 6);
                    int x      = Integer.parseInt(s[1]);
                    int y      = Integer.parseInt(s[2]);
                    int weight = Integer.parseInt(s[3]);
                    int dir1   = Integer.parseInt(s[4]);
                    int dir2   = Integer.parseInt(s[5]);
                    //ligne
                    g.getNetWork()[x][y].setTerminus(dir1);
                    g.getNetWork()[x][y].setWeight(weight);
                    //Colonne
                    g.getNetWork()[y][x].setTerminus(dir2);
                    g.getNetWork()[y][x].setWeight(weight);
                }
                readedLine = buffer.readLine();
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        return g;
    }

    /**
     * Découpe une ligne de caractéres par une espace et initialise une station
     * @param readedLine la ligne a découpé
     * @return une Station
     */
    public Station splitLine(String readedLine){
        String[] s;

        s = readedLine.split(sSEPARATOR, 6);

        int number = Integer.parseInt(s[1]);
        int line   = Integer.parseInt(s[2]);
        int     x  = Integer.parseInt(s[3]);
        int     y  = Integer.parseInt(s[4]);

        return new Station(s[5], line, number, new Point(x, y));
    }
}
