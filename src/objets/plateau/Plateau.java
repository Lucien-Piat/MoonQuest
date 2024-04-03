package objets.plateau;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;

import objets.pieces.Glace;
import objets.pieces.NuageEau;
import objets.pieces.NuageMet;
import objets.pieces.VehiculeEau;
import objets.pieces.VehiculeMet;
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
        initialiseCase();
        initialisePieces();
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

    private void initialisePieces(){
        initialiseNuages();
        Piece vehicleToAdd = new VehiculeEau(Color.BLACK);
        for (int index = 0; index < totalCases; index++){
            if (((index >= 18 && index <= 33) || (index >= 52 && index <= 85)) && (index % 2 == 0)){
                cases[index].addPiece(new Glace(Color.RED));
            }
            if (((index >= 222 && index <= 254) || (index >= 272 && index <= 288)) && (index % 2 != 0)){
                cases[index].addPiece(new Glace(Color.GREEN));
            }
            if ((index >= 35 && index <= 51) && (index % 2 == 0)) {
                if (vehicleToAdd instanceof VehiculeEau){
                    cases[index].addPiece(new VehiculeEau(Color.RED));
                    vehicleToAdd = new VehiculeMet(Color.BLACK);
                }else{
                    cases[index].addPiece(new VehiculeMet(Color.RED));
                    vehicleToAdd = new VehiculeEau(Color.BLACK);  
                }
            }
            if (index == 100){vehicleToAdd = new VehiculeMet(Color.BLACK);}
            if ((index >= 256 && index <= 271) && (index % 2 != 0)) {
                if (vehicleToAdd instanceof VehiculeEau){
                    cases[index].addPiece(new VehiculeEau(Color.GREEN));
                    vehicleToAdd = new VehiculeMet(Color.BLACK);
                }else{
                    cases[index].addPiece(new VehiculeMet(Color.GREEN));
                    vehicleToAdd = new VehiculeEau(Color.BLACK);  
                }
            }
        }  
    }

    private void initialiseNuages(){
        List<Integer> pos = generateUniqueRandomNumbers(30, 86, 220);
        int i = 0; 
        for (int number : pos) {
            if (i <= 15){cases[number].addPiece(new NuageEau());}
            else{cases[number].addPiece(new NuageMet());}
            i++;    
        }
    }

    private List<Integer> generateUniqueRandomNumbers(int count, int min, int max) {
        List<Integer> numbers = new ArrayList<>();
        Random random = new Random();
      
        while (numbers.size() < count) {
          int randomNumber;
          do {
            randomNumber = random.nextInt(max - min + 1) + min;
          } while (randomNumber % 17 == 0 || numbers.contains(randomNumber));
          numbers.add(randomNumber);
        }
        return numbers;
      }

    public Case[] getCases(){
        return cases;
    }

    public int getBoardSize(){
        return boardSize; 
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

    private Case findCaseWithName(String name){
        for (Case currCase : cases){
            if (currCase.getName().equals(name)){return currCase;}
        }
        return cases[0]; 
    }

    private void addItemToCase(Case curr_case ,Piece piece){
        curr_case.addPiece(piece);
    }

    private void removeItemFromCase(Case curr_case, Piece piece){
        curr_case.getContenu().remove(piece);
    } 

    private Boolean checkForGlace(Case toCheck){
        for (Piece piece : toCheck.getContenu()){
            if (piece.getToPrint().equals("G")){
                return false;
            }
        }
        return true;
    }

    public void deplacePiece(Case origineCase, String direction, int distance, Piece currentPiece){
        Case destination = origineCase;
        destination = findCaseWithName(origineCase.moveOneCaseAllDirections(direction, boardSize)); 
        if (checkForGlace(destination)){
            if (distance == 2){
                destination = findCaseWithName(destination.moveOneCaseAllDirections(direction, boardSize));
                if (checkForGlace(destination)){
                    addItemToCase(destination ,currentPiece);
                    removeItemFromCase(origineCase, currentPiece);
                }else{
                    System.out.println("Glace présente, déplacement non autorisé");
                    return;
                }
            }else{
                addItemToCase(destination ,currentPiece);
                removeItemFromCase(origineCase, currentPiece);
            }
        }
        System.out.println("Glace présente, déplacement non autorisé");
        return; 
    }
}