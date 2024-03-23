package objets.plateau;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import objets.pieces.abstract_class.Piece; 


/**
 * Représente le plateau de jeu.
 */
public class Plateau extends JFrame {

    private Case[] cases;
    private final int boardSize = 17; // Permet de définir la taille du plateau
    private int totalCases = boardSize * boardSize;

    /**
     * Constructeur de la classe Plateau.
     * Initialise le plateau de jeu.
     */
    public Plateau() {
        cases = new Case[totalCases]; // Initialise les cases du plateau

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

            // Gérer les mises à jour de lignes et de colonnes
            if (index % boardSize == 0 && index != 0) {
                currentRow++;
                currentColumn = 0;
                colorToChoose = !colorToChoose;
            }

            // Créer des cases en fonction de la position actuelle
            if (index == 0) {
                // Si c'est la case de coin
                cases[index] = new Case(new Color(50, 50, 50), "Coin");

            } else if (currentRow == 0) {
                // Si c'est la premiere ligne
                cases[index] = new Case(new Color(180, 169, 169), String.valueOf(lettreList.get(currentColumn - 1)));
                cases[index].setNameAsDisplay();
                cases[index].setSide();
            } else if (currentColumn == 0) {
                // Si c'est la premiere colonne
                cases[index] = new Case(new Color(180, 169, 169), String.valueOf(currentRow));
                cases[index].setNameAsDisplay();
                cases[index].setSide();
            } else {
                // Si la case est dans le plateau (on alterne les couleurs)
                if (colorToChoose) {
                    cases[index] = new Case(new Color(222, 184, 135), String.valueOf(lettreList.get(currentColumn - 1))+String.valueOf(currentRow));
                } else {
                    cases[index] = new Case(new Color(139, 69, 19), String.valueOf(lettreList.get(currentColumn - 1))+String.valueOf(currentRow));

                }
                colorToChoose = !colorToChoose; // Basculer la couleur pour la case suivante
            }
            currentColumn++;
        }
    }

    public Case[] getCases(){
        return cases;
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
        super.setSize(700,700);
        super.setLocationRelativeTo(null);
        super.setVisible(true);
    }

    public void addItemToCase(String caseName,Piece piece){
        for (int i = 0; i < cases.length; i++) {
            if (cases[i].getName().equals(caseName)) {
                cases[i].addPiece(piece);
                return; 
            }
        }
    }

    public void removeItemFromCase(String caseName,String objectName){
        for (int i = 0; i < cases.length; i++) {
            if (cases[i].getName().equals(caseName)) {
                for (Piece piece : cases[i].getContenu()){
                    if (piece.getToPrint() == objectName){
                        cases[i].getContenu().remove(piece);
                    }
                }
                return; 
            }
        }
    }    
}