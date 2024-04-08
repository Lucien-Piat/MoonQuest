package objets.players;

import java.awt.Color;
import java.util.Vector;

import objets.pieces.abstract_class.Piece;
import objets.plateau.PlateauLogique;

public class JoueurHumain extends Joueur {

    private int numeroJoueur;
    private Color team; 

    public JoueurHumain(Color team, int numeroJoueur){
        this.team = team;
        this.numeroJoueur = numeroJoueur;
    }

    public String joue(PlateauLogique board){
        String log, caseDestination = "";
        waitForConfirmation("C'est le tour du joueur " + numeroJoueur);
        Vector<Piece> toDestroy = new Vector<>();
        while (true){
            String caseOrigine = askCase();
            for (Piece currPiece : board.getPieces()){
                if ((currPiece.getCurrCase().equals(caseOrigine)) &&  currPiece.getPlayer().equals(this.team)){
                    int distance = 1;
                    if (currPiece.isAcitive()){distance=2;}
                    caseDestination = board.deplacePiece(caseOrigine,currPiece.move(),distance,currPiece,currPiece.isGlace());
                    toDestroy = board.selectPiecesToDestroy(currPiece, caseDestination);
                }
            }
            log = board.applyRules(toDestroy); 
            log = board.spitOutLog(caseOrigine, caseDestination, log); 
            waitForConfirmation("Case invalide");
            return log; 
        }
    }
}
