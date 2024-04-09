package io;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import objets.pieces.abstract_class.Piece;
import objets.plateau.PlateauLogique;

/**
 * Représente le plateau de jeu graphique.
 */
public class PlateauGraphique extends JFrame {

    private Case[] cases; // Les cases du plateau de jeu
    private final int boardSize = 17; // Taille du plateau de jeu
    private int totalCases = boardSize * boardSize; // Nombre total de cases sur le plateau

    /**
     * Constructeur de la classe PlateauGraphique. Initialise le plateau de jeu graphique.
     */
    public PlateauGraphique() {
        cases = new Case[totalCases]; // Initialise les cases du plateau
        initialiseCase();
    }

    /**
     * Initialise les valeurs par défaut des cases
     */
    private void initialiseCase() {
        // Création de listes pour les noms des cases
        List<Character> lettreList = new ArrayList<>();
        char lettre = 'A';
        for (int i = 0; i < boardSize - 1; i++) {
            lettreList.add(lettre);
            lettre++;
        }

        int currentRow = 0;
        int currentColumn = 0;
        boolean colorToChoose = false;
        for (int index = 0; index < totalCases; index++) {

            // Gestion des mises à jour de lignes et de colonnes
            if (index % boardSize == 0 && index != 0) {
                currentRow++;
                currentColumn = 0;
                colorToChoose = !colorToChoose;
            }

            // Créer des cases en fonction de la position actuelle
            if (index == 0) {
                // Si c'est la case de coin
                cases[index] = new Case(new Color(50, 50, 50), "Coin", index);
            } else if (currentRow == 0) {
                // Si c'est la première ligne
                cases[index] = new Case(new Color(180, 169, 169),
                        String.valueOf(lettreList.get(currentColumn - 1)), index);
                cases[index].setNameAsDisplay();
                cases[index].setSide();
            } else if (currentColumn == 0) {
                // Si c'est la première colonne
                cases[index] =
                        new Case(new Color(180, 169, 169), String.valueOf(currentRow), index);
                cases[index].setNameAsDisplay();
                cases[index].setSide();
            } else {
                // Si la case est dans le plateau (on alterne les couleurs)
                if (colorToChoose) {
                    cases[index] = new Case(new Color(222, 184, 135),
                            String.valueOf(lettreList.get(currentColumn - 1))
                                    + String.valueOf(currentRow),
                            index);
                } else {
                    cases[index] = new Case(new Color(139, 69, 19),
                            String.valueOf(lettreList.get(currentColumn - 1))
                                    + String.valueOf(currentRow),
                            index);
                }
                colorToChoose = !colorToChoose; // Basculer la couleur pour la case suivante
            }
            currentColumn++;
        }
    }

    /**
     * Obtient les cases du plateau de jeu.
     *
     * @return Les cases du plateau de jeu.
     */
    public Case[] getCases() {
        return cases;
    }

    /**
     * Obtient la taille du plateau de jeu.
     *
     * @return La taille du plateau de jeu.
     */
    public int getBoardSize() {
        return boardSize;
    }

    /**
     * Obtient le nombre total de cases sur le plateau de jeu.
     *
     * @return Le nombre total de cases sur le plateau de jeu.
     */
    public int getTotalCase() {
        return totalCases;
    }

    /**
     * Affiche le plateau de jeu graphique.
     */
    public void display() {
        super.setTitle("MoonQuest");
        super.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        Container contentPane = getContentPane();
        GridLayout gridLayout = new GridLayout(boardSize, boardSize); // Crée un GridLayout pour
                                                                      // organiser les cases
        contentPane.setLayout(gridLayout);

        for (int index = 0; index < totalCases; index++) {
            cases[index].set(); // Configure les propriétés visuelles de chaque case
            contentPane.add(cases[index]); // Ajoute la case au panel
        }

        super.setSize(800, 800);
        super.setLocationRelativeTo(null);
        super.setVisible(true);
    }

    /**
     * Recherche une case avec le nom spécifié.
     *
     * @param name Le nom de la case à rechercher.
     * @return La case correspondante
     */
    public Case findCaseWithName(String name) {
        for (Case currCase : cases) {
            if (currCase.getName().equals(name)) {
                return currCase;
            }
        }
        return cases[0];
    }

    /**
     * Met à jour l'affichage du plateau de jeu en fonction du plateau logique spécifié.
     *
     * @param plateau Le plateau logique contenant les pièces à afficher sur le plateau graphique.
     */
    public void updateDisplay(PlateauLogique plateau) {
        for (Case currCase : cases) {
            currCase.clearDisplay();
        }
        for (Piece piece : plateau.getPieces()) {
            findCaseWithName(piece.getCurrCase()).updateDisplay(piece);
        }
    }
}
