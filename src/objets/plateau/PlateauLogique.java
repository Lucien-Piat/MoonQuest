package objets.plateau;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;

import java.awt.Color;

import objets.pieces.Glace;
import objets.pieces.NuageEau;
import objets.pieces.NuageMet;
import objets.pieces.VehiculeEau;
import objets.pieces.VehiculeMet;
import objets.pieces.abstract_class.Piece;

public class PlateauLogique {

    private Vector<Piece> Pieces; 

    public PlateauLogique(PlateauGraphique plateauGraphique){
        this.Pieces = new Vector<>();
        initialisePieces(plateauGraphique);
    }

    public void PrintAllCases(){
        for (Piece piece : Pieces){
            System.out.println(piece.getCurrCase());
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

    private void initialiseNuages(PlateauGraphique plateauGraphique){
        List<Integer> pos = generateUniqueRandomNumbers(30, 86, 220);
        int i = 0; 
        for (int number : pos) {
            Piece currPiece;
            if (i <= 15) {
              currPiece = new NuageEau(plateauGraphique.getCases()[number].getName());
            } else {
              currPiece = new NuageMet(plateauGraphique.getCases()[number].getName());
            }
            Pieces.add(currPiece);
            i++;
        }
    }

    private void initialisePieces(PlateauGraphique plateauGraphique){
        initialiseNuages(plateauGraphique);
        Piece vehicleToAdd = new VehiculeEau(Color.BLACK,"none");
        for (int index = 0; index < plateauGraphique.getTotalCase() ; index++){
            if (((index >= 18 && index <= 33) || (index >= 52 && index <= 85)) && (index % 2 == 0)){
                Pieces.add(new Glace(Color.RED, plateauGraphique.getCases()[index].getName()));
            }
            if (((index >= 222 && index <= 254) || (index >= 272 && index <= 288)) && (index % 2 != 0)){
                Pieces.add(new Glace(Color.GREEN, plateauGraphique.getCases()[index].getName()));
            }
            if ((index >= 35 && index <= 51) && (index % 2 == 0)) {
                if (vehicleToAdd instanceof VehiculeEau){
                    Pieces.add(new VehiculeEau(Color.RED, plateauGraphique.getCases()[index].getName()));
                    vehicleToAdd = new VehiculeMet(Color.BLACK, "none");
                }else{
                    Pieces.add(new VehiculeMet(Color.RED, plateauGraphique.getCases()[index].getName()));
                    vehicleToAdd = new VehiculeEau(Color.BLACK, "none");  
                }
            }
            if (index == 100){vehicleToAdd = new VehiculeMet(Color.BLACK, "none");}
            if ((index >= 256 && index <= 271) && (index % 2 != 0)) {
                if (vehicleToAdd instanceof VehiculeEau){
                    Pieces.add(new VehiculeEau(Color.GREEN, plateauGraphique.getCases()[index].getName()));
                    vehicleToAdd = new VehiculeMet(Color.BLACK, "none");
                }else{
                    Pieces.add(new VehiculeMet(Color.GREEN, plateauGraphique.getCases()[index].getName()));
                    vehicleToAdd = new VehiculeEau(Color.BLACK,"none");  
                }
            }
        }  
    }


    private Boolean checkForGlace(String caseNameToCheck){
        for (Piece piece : Pieces){
            if ((piece.getToPrint().equals("G")) && (piece.getCurrCase().equals(caseNameToCheck))){
                return false;
            }
        }
        return true;
    }

    private String findRightChar(String currentChar, int boardSize, int way) {
        String[] alphabetArray = new String[boardSize - 1];
        String[] numArray = new String[boardSize - 1];
        char startChar = 'A';
        int startInt = 1;
        for (int i = 0; i < boardSize - 1; i++) {
          alphabetArray[i] = String.valueOf(startChar++);
          numArray[i] = String.valueOf(startInt++);
        }
        int currentIndex = 0;
        if (Character.isLetter(currentChar.charAt(0))) {
          for (int y = 0; y < boardSize - 1; y++) {
            if (alphabetArray[y].equals(currentChar)) {
              currentIndex = y;
              break;
            }
          }
        } else {
          for (int y = 0; y < boardSize - 1; y++) {
            if (numArray[y].equals(currentChar)) {
              currentIndex = y;
              break;
            }
          }
        }
        int newIndex = (currentIndex + way + boardSize - 1) % (boardSize - 1); 
        return Character.isLetter(currentChar.charAt(0)) ? alphabetArray[newIndex] : numArray[newIndex];
    }
      
    private String moveOneCase(String direction, int boardSize, String caseOrigineNom) {
        String letterChar = caseOrigineNom.substring(0, 1);
        String numChar = caseOrigineNom.substring(1);
        switch (direction) {
          case "up":
            return letterChar + findRightChar(numChar, boardSize, -1);
          case "down":
            return letterChar + findRightChar(numChar, boardSize, 1);
          case "left":
            return findRightChar(letterChar, boardSize, -1) + numChar;
          case "right":
            return findRightChar(letterChar, boardSize, 1) + numChar;
          default:
            return "";
        }
    }
  
    public String moveOneCaseAllDirections(String caseOrigineName, String direction, int boardSize){
      String destination = caseOrigineName;
      if (direction.equals("nord")){
        destination = moveOneCase("up", boardSize, destination);
      }
      if (direction.equals("nord_est")){
        destination = moveOneCase("right", boardSize, destination);
        destination = moveOneCase("up", boardSize, destination);
      }
      if (direction.equals("est")){
        destination = moveOneCase("right", boardSize, destination);
      }
      if (direction.equals("sud_est")){
        destination = moveOneCase("right", boardSize, destination);
        destination = moveOneCase("down", boardSize, destination);
      }
      if (direction.equals("sud")){
        destination = moveOneCase("down", boardSize, destination);
      }
      if (direction.equals("sud_ouest")){
        destination = moveOneCase("left", boardSize, destination);
        destination = moveOneCase("down", boardSize, destination);
      }
      if (direction.equals("ouest")){
        destination = moveOneCase("left", boardSize, destination);
      }
      if (direction.equals("nord_ouest")){
        destination = moveOneCase("left", boardSize, destination);
        destination = moveOneCase("up", boardSize, destination);
      } 
      return destination; 
    }
    

    public void deplacePiece(String caseOrigine, String direction, int distance, Piece pieceToMove, int boardSize){
        String destination = caseOrigine;

        destination = moveOneCaseAllDirections(caseOrigine ,direction, boardSize); 
        if (checkForGlace(destination)){
            if (distance == 2){
                destination = moveOneCaseAllDirections(destination, direction, boardSize);
                if (checkForGlace(destination)){
                    pieceToMove.setNewCase(destination);
                }else{
                    System.out.println("Glace présente, déplacement non autorisé");
                    return;
                }
            }else{
                pieceToMove.setNewCase(destination);
            }
        }
        System.out.println("Glace présente, déplacement non autorisé");
        return; 
    }
}
