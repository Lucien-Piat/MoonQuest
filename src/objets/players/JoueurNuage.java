package objets.players;

import objets.pieces.abstract_class.Nuage;
import objets.pieces.abstract_class.Piece;

import objets.plateau.PlateauLogique;

public class JoueurNuage extends Joueur{

  public JoueurNuage(){
    
  }

  public void joue(PlateauLogique board){
    waitForConfirmation("C'est le tour des Nuages");
    for (Piece currPiece : board.getPieces()){
      if (currPiece instanceof Nuage){
        board.deplacePiece(currPiece.getCurrCase(),currPiece.move(),2,currPiece, false); 
      }
    }
  }

}