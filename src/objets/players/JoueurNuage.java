package objets.players;

import java.util.Vector;

import objets.pieces.abstract_class.Nuage;
import objets.pieces.abstract_class.Piece;
import objets.plateau.PlateauLogique;

/**
 * La classe JoueurNuage représente un joueur contrôlant les nuages dans le jeu.
 */
public class JoueurNuage extends Joueur {

    /**
     * Constructeur de la classe JoueurNuage.
     */
    public JoueurNuage() {
        
    }

    /**
     * Méthode représentant l'action de jouer pour le joueur Nuage.
     *
     * @param board Le plateau logique sur lequel le joueur Nuage joue.
     * @return Une chaîne représentant l'action effectuée par le joueur Nuage.
     */
    public String joue(PlateauLogique board) {
        String log = ""; 

        // Si le jeu n'est pas en mode rapide, attend une confirmation avant de jouer
        if (!rapide) {
            waitForConfirmation("C'est le tour des Nuages");
        }

        Vector<Piece> toDestroy = new Vector<>(); // Initialise une liste des pièces à détruire
        for (Piece currPiece : board.getPieces()) {
            if (currPiece instanceof Nuage) { // Si la pièce est un nuage
                // Déplace le nuage de 2 cases vers une direction aléatoire
                String caseDestination = board.deplacePiece(currPiece.getCurrCase(),
                        currPiece.move(), 2, currPiece, false);
                // Sélectionne les pièces à détruire à l'arrivée du nuage
                toDestroy.addAll(board.selectPiecesToDestroy(currPiece, caseDestination));
            }
        }
        board.applyRules(toDestroy); // Applique les règles du jeu pour les pièces détruites
        return log; // On ne log pas les nuages pour alleger les informations
    }
}
