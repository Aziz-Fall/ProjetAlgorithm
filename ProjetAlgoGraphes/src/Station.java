import java.awt.Point;
import java.util.ArrayList;

/**
 * Cette classe représente une station de métro
 * @version 0.9.0
 * @author FALL, HARAL, TAMARA
 */
public class Station {
    private String mNameStation;
    private int mNumberLine,
                mNumberStation;
    private Point mCoordinated;

    /**
     * Constructeur de la classe Station
     * @param nameStation le nom de la station
     * @param numberLine la ligne qui passe par cette station
     * @param numberStation le numéro de la station
     * @param coordinated les coordonnées de la stations.
     */
    public Station(String nameStation, int numberLine, int numberStation, Point coordinated){
        mNameStation = nameStation;
        mNumberLine  = numberLine;
        mNumberStation = numberStation;
        mCoordinated   = coordinated.getLocation();
    }

    /**
     * Retourne le nom de la station
     * @return nom
     */
    public String getNameStation() {
        return mNameStation;
    }

    /**
     * Retourne le numéro de ligne
     * @return ligne
     */
    public int getNumberLine() {
        return mNumberLine;
    }

    /**
     * Retourne  le numéro de la station.
     * @return numéro
     */
    public int getNumberStation() {
        return mNumberStation;
    }

    /**
     * Retourne les coordonnées de la station.
     * @return coordonnées.
     */
    public Point getCoordinated() {
        return mCoordinated;
    }

    @Override
    public String toString() {
        return "Station{" +
                "mNameStation='" + mNameStation + '\'' +
                ", mNumberLine=" + mNumberLine +
                ", mNumberStation=" + mNumberStation +
                ", mCoordinated=" + mCoordinated +
                '}';
    }
}
