package objets.plateau;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import objets.pieces.abstract_class.Piece;

/**
 * Représente le plateau de jeu.
 */
public class PlateauGraphique extends JFrame {

    private Case[] cases;
    private final int boardSize = 17; // Permet de définir la taille du plateau
    private int totalCases = boardSize * boardSize;

    /**
     * Constructeur de la classe Plateau.
     * Initialise le plateau de jeu.
     */
    public PlateauGraphique() {
        cases = new Case[totalCases]; // Initialise les cases du plateau
        initialiseCase();
    }

    private void initialiseCase(){
        // Création de listes pour les noms des cases
        List<Character> lettreList = new ArrayList<>();
        char lettre = 'A';
        for (int i = 0; i < boardSize - 1; i++) {
            lettreList.add(lettre);
            lettre++;
        }

        // Cette boucle initialise les valeurs par défaut des cases
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
                // Si c'est la premiere ligne
                cases[index] = new Case(new Color(180, 169, 169), String.valueOf(lettreList.get(currentColumn - 1)),index);
                cases[index].setNameAsDisplay();
                cases[index].setSide();
            } else if (currentColumn == 0) {
                // Si c'est la premiere colonne
                cases[index] = new Case(new Color(180, 169, 169), String.valueOf(currentRow),index);
                cases[index].setNameAsDisplay();
                cases[index].setSide();
            } else {
                // Si la case est dans le plateau (on alterne les couleurs)
                if (colorToChoose) {
                    cases[index] = new Case(new Color(222, 184, 135), String.valueOf(lettreList.get(currentColumn - 1))+String.valueOf(currentRow),index);
                } else {
                    cases[index] = new Case(new Color(139, 69, 19), String.valueOf(lettreList.get(currentColumn - 1))+String.valueOf(currentRow),index);

                }
                colorToChoose = !colorToChoose; // Basculer la couleur pour la case suivante
            }
            currentColumn++;
        }
    }

    public Case[] getCases(){
        return cases;
    }

    public int getBoardSize(){
        return boardSize; 
    }

    public int getTotalCase(){
        return totalCases; 
    }
        
    /**
     * Affiche le plateau de jeu.
     */
    public void display() {
        super.setTitle("MoonQuest");
        super.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        Container contentPane = getContentPane();
        GridLayout gridLayout = new GridLayout(boardSize, boardSize);
        contentPane.setLayout(gridLayout);

        for (int index = 0; index < totalCases; index++) {
            cases[index].set();
            contentPane.add(cases[index]);
        }
        super.setSize(800,800);
        super.setLocationRelativeTo(null);
        super.setVisible(true);
    }

    public Case findCaseWithName(String name){
        for (Case currCase : cases){
            if (currCase.getName().equals(name)){return currCase;}
        }
        return cases[0]; 
    }


    public void updateDisplay(PlateauLogique plateau){
        for (Case currCase : cases){
            currCase.clearDisplay();
        }
        for (Piece piece : plateau.getPieces()){
            findCaseWithName(piece.getCurrCase()).updateDisplay(piece);
        }
    }


}