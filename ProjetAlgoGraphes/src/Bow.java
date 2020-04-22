public class Bow {
    private int mWeight; // Le poids d'un arc
    private int mTerminus; // le terminus de la ligne qui passe par cet arc.

    private final static int NOT_TERMINUS = 9999;
    private final static int NOT_BOW      = 8888;

    /**
     * Cette classe représente un arc du graphe.
     */
    public Bow(){
        mWeight   = NOT_BOW;
        mTerminus = NOT_TERMINUS;
    }

    /**
     * Retourn le poids d'un arc.
     * @return poids
     */
    public int getWeight() {
        return mWeight;
    }

    /**
     * Retourn le terminus d'une ligne
     * @return terminus
     */
    public int getTerminus() {
        return mTerminus;
    }

    /**
     * Modifie le poids d'un arc
     * @param weight
     */
    public void setWeight(int weight) {
        mWeight = weight;
    }

    /**
     * Modifier le terminus
     * @param terminus
     */
    public void setTerminus(int terminus) {
        mTerminus = terminus;
    }

    @Override
    public String toString() {
        return "Bow{" +
                "mWeight=" + mWeight +
                ", mTerminus=" + mTerminus +
                '}';
    }
}
