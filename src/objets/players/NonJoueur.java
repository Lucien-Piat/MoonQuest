package objets.players;

import objets.pieces.abstract_class.Nuage;
import objets.pieces.abstract_class.Piece;
import objets.plateau.Case;
import objets.plateau.PlateauGraphique;
import objets.plateau.PlateauLogique;

public class NonJoueur {
  public static void joue(PlateauLogique board){
    for (Piece currPiece : board.getPieces()){
      board.deplacePiece(currPiece.getCurrCase(),currPiece.move(),2,currPiece); 
    }
  }
}