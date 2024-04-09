package objets.players;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import objets.pieces.Glace;
import objets.pieces.abstract_class.Piece;
import objets.plateau.PlateauLogique;

/**
 * La classe JoueurIA représente un joueur contrôlé par l'ordinateur dans le jeu.
 */
public class JoueurIA extends Joueur {

    private Color team; // La couleur de l'équipe du joueur IA

    /**
     * Constructeur de la classe JoueurIA.
     *
     * @param team La couleur de l'équipe du joueur IA.
     */
    public JoueurIA(Color team) {
        this.team = team;
    }

    /**
     * Sélectionne une pièce de façon aléatoire parmi celles appartenant à l'équipe du joueur IA.
     *
     * @param board Le plateau logique sur lequel se trouve les pièces.
     * @return Une pièce sélectionnée aléatoirement.
     */
    private Piece selectRandomPiece(PlateauLogique board) {
        List<Piece> ownPieces = new ArrayList<>();

        for (Piece piece : board.getPieces()) {
            if (piece.getPlayer().equals(team)) {
                ownPieces.add(piece);
                // Ajoute la pièce à la liste si elle appartient à l'équipe du joueur IA
            }
        }

        Random random = new Random();
        int randomIndex = random.nextInt(ownPieces.size()); // Sélectionne un indice aléatoire
        return ownPieces.get(randomIndex); // Retourne la pièce sélectionnée
    }

    /**
     * Sélectionne une direction de façon aléatoire.
     *
     * @param distance La distance de déplacement.
     * @return Une direction sélectionnée aléatoirement.
     */
    private String selectRandomDirection(int distance) {
        Random random = new Random();
        int randomIndex = random.nextInt(3);
        if (distance == 2) {
            randomIndex = random.nextInt(Piece.getAllDirections().length);
        }
        return Piece.getAllDirections()[randomIndex];
    }

    /**
     * Méthode représentant l'action de jouer pour un joueur IA.
     *
     * @param board Le plateau logique sur lequel le joueur IA joue.
     * @return Une chaîne représentant l'action effectuée par le joueur IA.
     */
    public String joue(PlateauLogique board) {
        String log = ""; // Initialise le log

        // Si le jeu n'est pas en mode rapide, attend une confirmation avant de jouer
        if (!rapide) {
            waitForConfirmation("C'est le tour du joueur IA ");
        }

        Piece randomPiece = selectRandomPiece(board); // Sélectionne une pièce aléatoire
        int distance = 1;
        if (randomPiece.isAcitive()) {
            distance = 2; // Si la pièce est active, la distance de déplacement est de 2
        }
        // La case d'origine est celle de la pièce sélectionnée
        String caseOrigine = randomPiece.getCurrCase();
        // Déplace la pièce de façon aléatoire
        String caseDestination = board.deplacePiece(caseOrigine, selectRandomDirection(distance),
                distance, randomPiece, randomPiece instanceof Glace);
        // Applique les règles du jeu
        log = board.applyRules(board.selectPiecesToDestroy(randomPiece, caseDestination));
        // Génère le log de l'action effectuée
        log = board.spitOutLog(caseOrigine, caseDestination, log);
        return log;
    }
}
