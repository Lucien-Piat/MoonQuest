package objets.pieces.abstract_class;

import java.util.Random;

/**
 * La classe Nuage représente un type de pièce de jeu.
 */
public class Nuage extends Piece {

    private Boolean hasPlayed; // Indique si le nuage a déjà joué lors du tour actuel

    /**
     * Constructeur d'un nouveau nuage.
     *
     * @param toPrint La représentation sous forme de chaîne de caractères du nuage.
     * @param caseDepart La position initiale du nuage.
     */
    public Nuage(String toPrint, String caseDepart) {
        super(toPrint, caseDepart);
        this.hasPlayed = false;
    }

    /**
     * Obtient l'état indiquant si le nuage a déjà joué lors du tour actuel.
     *
     * @return true si le nuage a déjà joué, false sinon.
     */
    public Boolean getHasPlayed() {
        return this.hasPlayed;
    }

    /**
     * Inverse l'état indiquant si le nuage a déjà joué lors du tour actuel.
     */
    public void switchHasPlay() {
        this.hasPlayed = !hasPlayed;
    }

    /**
     * Effectue le déplacement du nuage. Le nuage peut se déplacer aléatoirement dans une direction
     * ou ne pas bouger.
     *
     * @return La direction de déplacement choisie ou "bouge_pas" si le nuage ne bouge pas.
     */
    @Override
    public String move() {
        Random random = new Random();
        if (random.nextInt(5) == 0) {
            Random random2 = new Random();
            int randomIndex = random2.nextInt(directions.length);
            return directions[randomIndex];
        } else {
            return "bouge_pas";
        }
    }
}
