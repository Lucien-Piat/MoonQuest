package objets.players;

import java.awt.Color;

import objets.pieces.abstract_class.Piece;
import objets.plateau.PlateauLogique;

public class JoueurHumain extends Joueur {

    private int numeroJoueur;
    private Color team; 

    public JoueurHumain(Color team, int numeroJoueur){
        this.team = team;
        this.numeroJoueur = numeroJoueur;
    }

    public void joue(PlateauLogique board){
        waitForConfirmation("C'est le tour du joueur " + numeroJoueur);
        while (true){
            String caseOrigine = askCase();
            for (Piece currPiece : board.getPieces()){
                if ((currPiece.getCurrCase().equals(caseOrigine)) &&  currPiece.getPlayer().equals(this.team)){
                    int distance = 1;
                    if (currPiece.isAcitive()){distance=2;}
                    board.deplacePiece(caseOrigine,currPiece.move(),distance,currPiece,currPiece.isGlace());
                    return;
                }
            }
            waitForConfirmation("Case invalide");
        }
    }
}
