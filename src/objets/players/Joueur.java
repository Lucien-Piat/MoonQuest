package objets.players;

import javax.swing.*;

import objets.plateau.PlateauLogique;

/**
 * La classe abstraite Joueur représente un joueur dans le jeu.
 */
public abstract class Joueur {

    /**
     * Permet de faire une partie rapide (pas de fenêtres de dialogue)
     */
    static Boolean rapide = false;

    /**
     * Passe les joueurs en partie rapide
     */
    public static void setRapide() {
        rapide = !rapide;
    }

    /**
     * Affiche un message d'attente de confirmation à l'utilisateur.
     *
     * @param message Le message à afficher.
     */
    protected void waitForConfirmation(String message) {
        JOptionPane.showMessageDialog(null, message, "Attention !",
                JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Méthode abstraite représentant l'action de jouer pour un joueur.
     *
     * @param board Le plateau logique sur lequel le joueur joue.
     * @return La décision du joueur.
     */
    public String joue(PlateauLogique board) {
        return ""; // Par défaut, le joueur ne fait rien
    }
}
