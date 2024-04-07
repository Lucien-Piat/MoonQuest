package objets.players;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import objets.pieces.abstract_class.Piece;
import objets.plateau.PlateauLogique;

public class JoueurIA extends Joueur {

    private int numeroJoueur;
    private Color team; 

    public JoueurIA(Color team, int numeroJoueur){
        this.team = team;
        this.numeroJoueur = numeroJoueur;
    }

    private Piece selectRandomPiece(PlateauLogique board) {
        List<Piece> ownPieces = new ArrayList<>();
      
        for (Piece piece : board.getPieces()) {
          if (piece.getPlayer().equals(team)) {
            ownPieces.add(piece);
          }
        }
      
        Random random = new Random();
        int randomIndex = random.nextInt(ownPieces.size());
        return ownPieces.get(randomIndex);
      }
    
    private String selectRandomDirection(int distance){
        Random random = new Random();
        int randomIndex = random.nextInt(3);
        if (distance == 1){randomIndex = random.nextInt(Piece.getAllDirections().length);}
        return Piece.getAllDirections()[randomIndex];
    }


    public void joue(PlateauLogique board){
        waitForConfirmation("C'est le tour du joueur IA " + numeroJoueur);
        Piece randomPiece = selectRandomPiece(board); 
        int distance = 1;
        if (randomPiece.isAcitive()){distance=2;}
        board.deplacePiece(randomPiece.getCurrCase(),selectRandomDirection(distance),distance,randomPiece,randomPiece.isGlace());
                    return;
    }
}

   
  
