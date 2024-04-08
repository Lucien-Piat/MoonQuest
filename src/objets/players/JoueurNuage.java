package objets.players;

import java.util.Vector;

import objets.pieces.abstract_class.Nuage;
import objets.pieces.abstract_class.Piece;

import objets.plateau.PlateauLogique;

public class JoueurNuage extends Joueur{

  public JoueurNuage(){
    
  }

  public String joue(PlateauLogique board){
    String log = ""; 
    //waitForConfirmation("C'est le tour des Nuages");
    Vector<Piece> toDestroy = new Vector<>();
    for (Piece currPiece : board.getPieces()){
      if (currPiece instanceof Nuage){
        String caseDestination = board.deplacePiece(currPiece.getCurrCase(),currPiece.move(),2,currPiece, false); 
        toDestroy.addAll(board.selectPiecesToDestroy(currPiece, caseDestination));
      }
    }
    board.applyRules(toDestroy); 
    return log;
  }
}