package objets.pieces.abstract_class;

import java.awt.Color;

import javax.swing.*;


/**
 * La classe abstraite représentant une pièce de jeu.
 */
public abstract class Piece {

    protected String toPrint; // Cette variable représente la valeure à afficher sur le plateau
    protected String currCase; // Le nom de la case de la pièce
    protected Color player = Color.BLACK; // La couleur du joueur qui possède la pièce
    final static String[] directions =
            {"nord", "est", "sud", "ouest", "nord_ouest", "nord_est", "sud_est", "sud_ouest"};


    /**
     * Constructeur d'une nouvelle pièce.
     *
     * @param toPrint La représentation sous forme de chaîne de caractères de la pièce.
     * @param caseDepart La position initiale de la pièce.
     */
    public Piece(String toPrint, String caseDepart) {
        this.toPrint = toPrint;
        this.currCase = caseDepart;
    }

    /**
     * Récupère toutes les directions de déplacement possibles pour la pièce.
     *
     * @return Un tableau de chaînes de caractères représentant toutes les directions de déplacement
     *         possibles.
     */
    public static String[] getAllDirections() {
        return directions;
    }

    /**
     * Récupère la représentation sous forme de chaîne de caractères de la pièce.
     *
     * @return La représentation sous forme de chaîne de caractères de la pièce.
     */
    public String getToPrint() {
        return this.toPrint;
    }

    /**
     * Récupère la couleur du joueur qui possède la pièce.
     *
     * @return La couleur du joueur.
     */
    public Color getPlayer() {
        return this.player;
    }

    /**
     * Récupère la position actuelle de la pièce.
     *
     * @return La position actuelle de la pièce.
     */
    public String getCurrCase() {
        return this.currCase;
    }

    /**
     * Définit la nouvelle position de la pièce.
     *
     * @param newCase La nouvelle position de la pièce.
     */
    public void setNewCase(String newCase) {
        this.currCase = newCase;
    }

    /**
     * Demande à la pièce ou elle souhaite se déplacer.
     *
     * @return La case souhaitée.
     */
    public String move() {
        return "bouge_pas";
    }

    /**
     * Vérifie si la pièce est active.
     *
     * @return true si la pièce est active, false sinon.
     */
    public boolean isAcitive() {
        return false;
    }

    /**
     * Demande au joueur la direction de déplacement.
     *
     * @param distance La distance de déplacement.
     * @return La direction de déplacement choisie.
     */
    public String askDirection(int distance) {
        String[] options = {"⬆", "⮕", "⬇", "⬅"};
        String[] options_vol = {"⬆", "⮕", "⬇", "⬅", "⬉", "⬈", "⬊", "⬋"};
        if (distance == 2) {
            options = options_vol;
        }
        int choice = JOptionPane.showOptionDialog(null,
                "Ou votre piece en " + currCase + " doit elle aller ?", "Choix du déplacement",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options,
                options[0]);
        if (choice == 0) {
            return "nord";
        }
        if (choice == 1) {
            return "est";
        }
        if (choice == 2) {
            return "sud";
        }
        if (choice == 3) {
            return "ouest";
        }
        if (choice == 4) {
            return "nord_ouest";
        }
        if (choice == 5) {
            return "nord_est";
        }
        if (choice == 6) {
            return "sud_est";
        }
        if (choice == 7) {
            return "sud_ouest";
        }
        return "";
    }
}
