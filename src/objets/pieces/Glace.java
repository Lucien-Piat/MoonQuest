package objets.pieces;

import java.awt.Color;

import objets.pieces.abstract_class.Piece;

/**
 * La classe Glace.
 */
public class Glace extends Piece {

    /**
     * Constructeur d'une nouvelle pièce Glace.
     *
     * @param player La couleur du joueur propriétaire de la pièce.
     * @param caseDepart La position initiale de la pièce Glace.
     */
    public Glace(Color player, String caseDepart) {
        super("G", caseDepart);
        this.player = player;
    }

    /**
     * Effectue le mouvement de la pièce Glace. La Glace peut se déplacer d'une case dans une
     * direction quelconque.
     *
     * @return La direction de déplacement choisie.
     */
    public String move() {
        while (true) {
            return askDirection(1);
        }
    }
}
