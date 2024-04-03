package objets.players;

import objets.plateau.Case;
import objets.plateau.Plateau;
import objets.pieces.abstract_class.Nuage;
import objets.pieces.abstract_class.Piece;

public class NonJoueur {
    public static void joue(Plateau board) {
    for (Case caseOnBoard : board.getCases()) { 
        if (caseOnBoard.getContenu().get(0) instanceof Nuage) {
          String direction = caseOnBoard.getContenu().get(0).move();
          board.deplacePiece(caseOnBoard, direction, 2, caseOnBoard.getContenu().get(0)); // Simplified argument
        }
      }
    }
}