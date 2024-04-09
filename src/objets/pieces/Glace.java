package objets.pieces;

import java.awt.Color;

import objets.pieces.abstract_class.Piece;

public class Glace extends Piece {

    public Glace(Color player, String caseDepart) {
        super("G", caseDepart);
        this.player = player;
    }

    public String move() {
        while (true) {
            return askDirection(1);
        }
    }
}
