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
import objets.pieces.abstract_class.Nuage;
import objets.pieces.abstract_class.Piece;
import objets.pieces.abstract_class.Vehicule;

public class PlateauLogique {

    private Vector<Piece> Pieces;
    private int boardSize ;

    public PlateauLogique(PlateauGraphique plateauGraphique){
        this.Pieces = new Vector<>();
        initialisePieces(plateauGraphique);
        boardSize = plateauGraphique.getBoardSize();

    }

    public Vector<Piece> getPieces(){
      return Pieces; 
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
            if (((index >= 18 && index <= 33) || (index >= 52 && index <= 85)) && (index % 2 == 0) && (index!= 68)){
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

    private String findRightChar(String currentChar, int way) {
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
      
    private String moveOneCase(String direction, String caseOrigineNom) {
        String letterChar = caseOrigineNom.substring(0, 1);
        String numChar = caseOrigineNom.substring(1);
        switch (direction) {
          case "up":
            return letterChar + findRightChar(numChar, -1);
          case "down":
            return letterChar + findRightChar(numChar, 1);
          case "left":
            return findRightChar(letterChar, -1) + numChar;
          case "right":
            return findRightChar(letterChar, 1) + numChar;
          default:
            return "";
        }
    }
  
    public String moveOneCaseAllDirections(String caseOrigineName, String direction, int boardSize){
      String destination = caseOrigineName;
      if (direction.equals("nord")){
        destination = moveOneCase("up",  destination);
      }
      if (direction.equals("nord_est")){
        destination = moveOneCase("right", destination);
        destination = moveOneCase("up", destination);
      }
      if (direction.equals("est")){
        destination = moveOneCase("right", destination);
      }
      if (direction.equals("sud_est")){
        destination = moveOneCase("right", destination);
        destination = moveOneCase("down", destination);
      }
      if (direction.equals("sud")){
        destination = moveOneCase("down", destination);
      }
      if (direction.equals("sud_ouest")){
        destination = moveOneCase("left", destination);
        destination = moveOneCase("down", destination);
      }
      if (direction.equals("ouest")){
        destination = moveOneCase("left", destination);
      }
      if (direction.equals("nord_ouest")){
        destination = moveOneCase("left", destination);
        destination = moveOneCase("up", destination);
      } 
      return destination; 
    }
    

    public String deplacePiece(String caseOrigine, String direction, int distance, Piece pieceToMove, Boolean isGlace){
        String destination = caseOrigine;
        destination = moveOneCaseAllDirections(caseOrigine ,direction, boardSize); 
        if (checkForGlace(destination)|| isGlace){
            if (distance == 2){
                destination = moveOneCaseAllDirections(destination, direction, boardSize);
                if (checkForGlace(destination) || isGlace){
                    pieceToMove.setNewCase(destination);
                    return destination; 
                }else{
                    if(!(pieceToMove instanceof Nuage)){
                      System.out.println("Console : Glace présente en "+destination+" pas de mouvement");
                    }
                    return ""; 
                }
            }else{
                pieceToMove.setNewCase(destination);
                return destination; 
            }
        }
        if(!(pieceToMove instanceof Nuage)){
          System.out.println("Console : Glace présente en "+destination+" pas de mouvement");
        }
        return ""; 
    }




  public Vector<Piece> selectPiecesToDestroy(Piece pieceArrivante, String caseDestination){
    // Si un nuage arrive 
    Vector<Piece> toDestroy = new Vector<>(); 
    if (pieceArrivante.getToPrint().substring(0, 1).equals("N")){
      for (Piece piece : Pieces){
        if((piece.getCurrCase().equals(caseDestination)) && (!piece.equals(pieceArrivante))){
          if(piece instanceof Vehicule){
            toDestroy.add(piece);
            System.out.println("Console : Destruction en "+caseDestination);
          }
        }
      }
    }
    return toDestroy;
  }
  
  public String applyRules(Vector<Piece> toDestroy){
    for (Piece pieceToDestroy : toDestroy){
      Pieces.remove(pieceToDestroy); 
    }
    if (toDestroy.size() > 0){return " x ";}return " . ";
  }

  public String spitOutLog(String caseOrigine, String caseDestination, String applyRulesLog){
    return caseOrigine + " - " + caseDestination + applyRulesLog;
  }
}
