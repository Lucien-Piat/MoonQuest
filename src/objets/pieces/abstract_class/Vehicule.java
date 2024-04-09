package objets.pieces.abstract_class;

/**
 * La classe abstraite Vehicule
 */
public abstract class Vehicule extends Piece {

    protected int captures; // Le nombre de pièces capturées par le véhicule

    /**
     * Constructeur d'un nouveau véhicule.
     *
     * @param toPrint La représentation sous forme de chaîne de caractères du véhicule.
     * @param caseDepart La position initiale du véhicule.
     */
    public Vehicule(String toPrint, String caseDepart) {
        super(toPrint, caseDepart);
        this.captures = 0;
    }

    /**
     * Ajoute une capture au véhicule. La capacité de capture est limitée à 3.
     */
    public void addCapture() {
        if (this.captures < 3) {
            this.captures++;
        }
    }

    /**
     * Obtient le nombre de captures effectuées par le véhicule.
     *
     * @return Le nombre de captures.
     */
    public int getCaptures() {
        return this.captures;
    }

    /**
     * Vérifie si le véhicule est actif, c'est-à-dire s'il a effectué des captures.
     *
     * @return true si le véhicule est actif, false sinon.
     */
    @Override
    public boolean isAcitive() {
        return this.captures > 0;
    }

    /**
     * Effectue le mouvement du véhicule. Le véhicule peut se déplacer d'une ou deux cases en
     * fonction de son activité.
     *
     * @return La direction de déplacement choisie.
     */
    @Override
    public String move() {
        int distance = isAcitive() ? 2 : 1;
        return askDirection(distance);
    }
}
