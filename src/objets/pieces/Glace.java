package objets.pieces;

import java.awt.Color;

import objets.pieces.abstract_class.Piece;

public class Glace extends Piece {

    public Glace(Color player){
        super("G");
        this.player = player ; 
    }
}
