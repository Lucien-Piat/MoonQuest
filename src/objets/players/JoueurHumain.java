package objets.players;

import java.awt.Color;
import java.util.Vector;

import javax.swing.*;

import objets.pieces.Glace;
import objets.pieces.abstract_class.Piece;
import objets.plateau.PlateauLogique;

/**
 * La classe JoueurHumain représente un joueur humain dans le jeu.
 */
public class JoueurHumain extends Joueur {

    private Color team; // La couleur de l'équipe du joueur
    private String couleur; // La couleur en texte (vert ou rouge) pour affichage

    /**
     * Constructeur de la classe JoueurHumain.
     *
     * @param team La couleur de l'équipe du joueur.
     */
    public JoueurHumain(Color team) {
        this.team = team;
        if (this.team.equals(Color.GREEN)) {
            this.couleur = "Vert";
        } else {
            this.couleur = "Rouge";
        }
    }

    /**
     * Demande au joueur de saisir le numéro de la case à jouer.
     *
     * @return Le numéro de la case saisie par le joueur.
     */
    public String askCase() {
        JTextField directionField = new JTextField(10);
        JLabel message = new JLabel("Donnez le numéro de la case à jouer");
        JPanel panel = new JPanel();
        panel.add(message);
        panel.add(directionField);
        int result = JOptionPane.showConfirmDialog(null, panel,
                "C'est votre tour joueur " + couleur, JOptionPane.OK_CANCEL_OPTION);
        String direction = null;
        if (result == JOptionPane.OK_OPTION) {
            direction = directionField.getText().trim();
        }
        return direction;
    }

    /**
     * Permet au joueur de choisir la distance de déplacement.
     *
     * @return La distance de déplacement choisie par le joueur.
     */
    private int selectDistance() {
        String[] options = {"Terrestre (1 case)", "Aérien (2 cases)"};
        int choice = JOptionPane.showOptionDialog(null,
                "Votre véhicule est activé, choisissez le déplacement :", "Choix du déplacement",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options,
                options[0]);
        if (choice == 1) {
            return 2;
        }
        return 1;
    }

    /**
     * Méthode représentant l'action de jouer pour un joueur humain.
     *
     * @param board Le plateau logique sur lequel le joueur joue.
     * @return Une chaîne représentant l'action effectuée par le joueur.
     */
    public String joue(PlateauLogique board) {
        String log, caseDestination = "";

        Vector<Piece> toDestroy = new Vector<>();
        String caseOrigine = null;
        boolean keepLooping = true;
        while (keepLooping) {
            caseOrigine = askCase(); // Demande au joueur de saisir le numéro de la case d'origine
            for (Piece currPiece : board.getPieces()) {
                if (currPiece.getCurrCase().equals(caseOrigine)) {
                    keepLooping = false; // Sort de la boucle si la case d'origine est valide
                }
            }
        }

        for (Piece currPiece : board.getPieces()) {
            if ((currPiece.getCurrCase().equals(caseOrigine))
                    && currPiece.getPlayer().equals(this.team)) {
                int distance = 1;
                if (currPiece.isAcitive()) {
                    distance = selectDistance(); // Choix de la distance si le véhicule est activé
                }
                // Déplacement de la pièce sur le plateau
                caseDestination = board.deplacePiece(caseOrigine, currPiece.move(), distance,
                        currPiece, currPiece instanceof Glace);
                // Sélection des pièces à détruire
                toDestroy = board.selectPiecesToDestroy(currPiece, caseDestination);
            }
        }
        // Destruction des pieces concernées (evite les pb de modif du vecteur dans la boucle)
        log = board.applyRules(toDestroy);
        log = board.spitOutLog(caseOrigine, caseDestination, log); // Génération du log
        return log; // Retourne le log généré
    }
}
