package io;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Vector;

import javax.swing.*;

import objets.pieces.abstract_class.Nuage;
import objets.pieces.abstract_class.Piece;
import objets.pieces.abstract_class.Vehicule;
import objets.plateau.PlateauLogique;

/**
 * Cette classe gère l'enregistrement et l'affichage des logs du jeu.
 */
public class Logger {
    private Vector<String> completeLog; // Liste contenant l'ensemble des logs
    private int turn, scorePlayer1, scorePlayer2; // Tour actuel et scores des joueurs
    private final JTextArea logTextArea;
    private boolean resteNuages; // Indique s'il reste des nuages sur le plateau

    /**
     * Constructeur de la classe Logger. Initialise les paramètres de base.
     */
    public Logger() {
        completeLog = new Vector<>();
        completeLog.add("LISTE DES COUPS :\n"); // Ajout d'une ligne initiale dans les logs
        turn = 1;
        scorePlayer1 = 0;
        scorePlayer2 = 0;
        resteNuages = false;

        logTextArea = new JTextArea();
        logTextArea.setEditable(false);
    }

    /**
     * Calcule le score des joueurs en parcourant le plateau logique.
     *
     * @param plateau_logique Le plateau logique du jeu.
     * @return Une chaîne représentant les scores des joueurs.
     */
    private String computeScore(PlateauLogique plateau_logique) {
        scorePlayer1 = 0;
        scorePlayer2 = 0;
        resteNuages = false;
        for (Piece piece : plateau_logique.getPieces()) {
            if ((!resteNuages) && (piece instanceof Nuage)) {
                resteNuages = true; // S'il reste des nuages sur le plateau, met l'indicateur à vrai
            }
            if (piece instanceof Vehicule) {
                if (piece.getPlayer().equals(Color.GREEN)) {
                    scorePlayer1 += ((Vehicule) piece).getCaptures();
                } else {
                    scorePlayer2 += ((Vehicule) piece).getCaptures();
                }
            }
        }
        return scorePlayer1 + "-" + scorePlayer2; // Retourne une chaîne de la forme
                                                  // "score_joueur1-score_joueur2"
    }

    /**
     * Obtient la dernière entrée dans les logs.
     *
     * @return La dernière entrée dans les logs.
     */
    public String getLastEntery() {
        return completeLog.elementAt(completeLog.size() - 1);
    }

    /**
     * Ajoute une nouvelle entrée dans les logs.
     *
     * @param turnLog Le log du tour actuel.
     * @param plateau_logique Le plateau logique du jeu.
     */
    public void newLogEntery(String turnLog, PlateauLogique plateau_logique) {
        computeScore(plateau_logique); // Calcule le score des joueurs
        String entry =
                String.valueOf(turn++) + ". " + turnLog + " - " + computeScore(plateau_logique);
        completeLog.add(entry); // Ajoute l'entrée dans les logs
        updateTextArea(); // Met à jour l'affichage des logs
    }

    /**
     * Met à jour le contenu de la zone d'affichage des logs.
     */
    public void updateTextArea() {
        StringBuilder logBuilder = new StringBuilder();
        for (String entry : completeLog) {
            logBuilder.append(entry).append("\n"); // Ajoute chaque entrée dans la zone d'affichage
        }
        logTextArea.setText(logBuilder.toString().trim()); // Met à jour le contenu de la zone
                                                           // d'affichage
    }

    /**
     * Affiche une fenêtre contenant les logs.
     */
    public void showLogWindow() {
        JFrame frame = new JFrame("Log"); // Crée une nouvelle fenêtre
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(new JScrollPane(logTextArea), BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        frame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(160, 500);
        frame.setVisible(true);
    }

    /**
     * Vérifie si la partie est terminée en fonction des conditions du jeu.
     *
     * @return true si la partie est terminée, sinon false.
     */
    public Boolean checkIfGameIsOver() {
        return (!resteNuages) || (scorePlayer1 > 15) || (scorePlayer2 > 15);
    }

    /**
     * Affiche les logs dans la console.
     */
    public void printLogInConsole() {
        for (String entry : completeLog) {
            System.out.println(entry); // Affiche chaque entrée dans la console
        }
    }
}
