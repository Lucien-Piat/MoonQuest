package objets.pieces;

import java.awt.Color;

import objets.pieces.abstract_class.Piece;

public class Glace extends Piece {

    public Glace(Color player, String caseDepart){
        super("G", caseDepart);
        this.player = player ; 
    }

    public String move(){
        while (true) {
            String directionChoisie = askDirection(1);
            if (!passDirectionifValid(directionChoisie, 1).equals("invalid")){
                return directionChoisie; 
            }
        }
    }
}
