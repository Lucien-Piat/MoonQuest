package objets.players;

import objets.plateau.Plateau;
import objets.pieces.abstract_class.Piece;

public class NonJoueur {
    public void Joue(Plateau board){
        for (int i = 0; i < board.getCases().length; i++){
            for (Piece piece : board.getCases()[i].getContenu()){

            }
        } 
    }
}