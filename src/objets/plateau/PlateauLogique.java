package objets.plateau;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;

import io.PlateauGraphique;

import java.awt.Color;

import objets.pieces.*;
import objets.pieces.abstract_class.*;

/**
 * La classe PlateauLogique représente le plateau de jeu logique. Elle implémente toutes les régles
 * et gére le dépplacement.
 */
public class PlateauLogique {

    private Vector<Piece> Pieces;
    private int boardSize;

    /**
     * Constructeur de la classe PlateauLogique.
     * 
     * @param plateauGraphique Le plateau graphique associé.
     */
    public PlateauLogique(PlateauGraphique plateauGraphique) {
        this.Pieces = new Vector<>();
        initialisePieces(plateauGraphique);
        boardSize = plateauGraphique.getBoardSize();
    }

    /**
     * Renvoie la liste des pièces sur le plateau.
     * 
     * @return La liste des pièces.
     */
    public Vector<Piece> getPieces() {
        return Pieces;
    }

    /**
     * Génère une liste de nombres aléatoires uniques dans une plage spécifiée.
     * 
     * @param count Le nombre de nombres à générer.
     * @param min La valeur minimale possible.
     * @param max La valeur maximale possible.
     * @return Une liste de nombres aléatoires uniques dans la plage spécifiée.
     */
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

    /**
     * Initialise les nuages sur le plateau.
     * 
     * @param plateauGraphique Le plateau graphique associé.
     */
    private void initialiseNuages(PlateauGraphique plateauGraphique) {
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

    /**
     * Initialise toutes les pièces sur le plateau.
     * 
     * @param plateauGraphique Le plateau graphique associé.
     */
    private void initialisePieces(PlateauGraphique plateauGraphique) {
        initialiseNuages(plateauGraphique);
        Piece vehicleToAdd = new VehiculeEau(Color.BLACK, "none");
        for (int index = 0; index < plateauGraphique.getTotalCase(); index++) {
            if (((index >= 18 && index <= 33) || (index >= 52 && index <= 85)) && (index % 2 == 0)
                    && (index != 68)) {
                Pieces.add(new Glace(Color.RED, plateauGraphique.getCases()[index].getName()));
            }
            if (((index >= 222 && index <= 254) || (index >= 272 && index <= 288))
                    && (index % 2 != 0)) {
                Pieces.add(new Glace(Color.GREEN, plateauGraphique.getCases()[index].getName()));
            }
            if ((index >= 35 && index <= 51) && (index % 2 == 0)) {
                if (vehicleToAdd instanceof VehiculeEau) {
                    Pieces.add(new VehiculeEau(Color.RED,
                            plateauGraphique.getCases()[index].getName()));
                    vehicleToAdd = new VehiculeMet(Color.BLACK, "none");
                } else {
                    Pieces.add(new VehiculeMet(Color.RED,
                            plateauGraphique.getCases()[index].getName()));
                    vehicleToAdd = new VehiculeEau(Color.BLACK, "none");
                }
            }
            if (index == 100) {
                vehicleToAdd = new VehiculeMet(Color.BLACK, "none");
            }
            if ((index >= 256 && index <= 271) && (index % 2 != 0)) {
                if (vehicleToAdd instanceof VehiculeEau) {
                    Pieces.add(new VehiculeEau(Color.GREEN,
                            plateauGraphique.getCases()[index].getName()));
                    vehicleToAdd = new VehiculeMet(Color.BLACK, "none");
                } else {
                    Pieces.add(new VehiculeMet(Color.GREEN,
                            plateauGraphique.getCases()[index].getName()));
                    vehicleToAdd = new VehiculeEau(Color.BLACK, "none");
                }
            }
        }
    }


    /**
     * Vérifie si une case contient déjà une glace.
     * 
     * @param caseNameToCheck Le nom de la case à vérifier.
     * @return True si la case ne contient pas de glace, sinon False.
     */
    private Boolean checkForGlace(String caseNameToCheck) {
        for (Piece piece : Pieces) {
            if ((piece instanceof Glace) && (piece.getCurrCase().equals(caseNameToCheck))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Trouve le caractère correct à droite ou à gauche du caractère spécifié.
     * 
     * @param currentChar le caractère actuel
     * @param way la direction dans laquelle rechercher (-1 pour la gauche, 1 pour la droite)
     * @return le caractère correct après le décalage
     */
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
        return Character.isLetter(currentChar.charAt(0)) ? alphabetArray[newIndex]
                : numArray[newIndex];
    }

    /**
     * Déplace une case dans une direction spécifiée.
     * 
     * @param direction la direction du déplacement ("up", "down", "left", "right")
     * @param caseOrigineNom le nom de la case d'origine
     * @return le nom de la case après le déplacement
     */
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

    /**
     * Déplace une case dans toutes les directions spécifiées.
     * 
     * @param caseOrigineName le nom de la case d'origine
     * @param direction la direction du déplacement
     * @param boardSize la taille du plateau
     * @return le nom de la case après le déplacement dans toutes les directions
     */
    public String moveOneCaseAllDirections(String caseOrigineName, String direction,
            int boardSize) {
        String destination = caseOrigineName;
        if (direction.equals("nord")) {
            destination = moveOneCase("up", destination);
        }
        if (direction.equals("nord_est")) {
            destination = moveOneCase("right", destination);
            destination = moveOneCase("up", destination);
        }
        if (direction.equals("est")) {
            destination = moveOneCase("right", destination);
        }
        if (direction.equals("sud_est")) {
            destination = moveOneCase("right", destination);
            destination = moveOneCase("down", destination);
        }
        if (direction.equals("sud")) {
            destination = moveOneCase("down", destination);
        }
        if (direction.equals("sud_ouest")) {
            destination = moveOneCase("left", destination);
            destination = moveOneCase("down", destination);
        }
        if (direction.equals("ouest")) {
            destination = moveOneCase("left", destination);
        }
        if (direction.equals("nord_ouest")) {
            destination = moveOneCase("left", destination);
            destination = moveOneCase("up", destination);
        }
        return destination;
    }

    /**
     * Déplace une pièce sur le plateau vers une case spécifiée.
     * 
     * @param caseOrigine le nom de la case d'origine
     * @param direction la direction du mouvement
     * @param distance la distance à parcourir (1 ou 2)
     * @param pieceToMove la pièce à déplacer
     * @param isGlace true si la case de destination est une glace, sinon false
     * @return le nom de la case de destination après le déplacement
     */
    public String deplacePiece(String caseOrigine, String direction, int distance,
            Piece pieceToMove, Boolean isGlace) {
        String destination = caseOrigine;
        destination = moveOneCaseAllDirections(caseOrigine, direction, boardSize);
        if (checkForGlace(destination) || isGlace || (distance == 1)) {
            if (distance == 2) {
                destination = moveOneCaseAllDirections(destination, direction, boardSize);
                if (checkForGlace(destination) || isGlace || (distance == 1)) {
                    pieceToMove.setNewCase(destination);
                    return destination;
                } else {
                    if (!(pieceToMove instanceof Nuage)) {
                        System.out.println(
                                "Console : Glace présente en " + destination + " pas de mouvement");
                    }
                    return "";
                }
            } else {
                pieceToMove.setNewCase(destination);
                return destination;
            }
        }
        if (!(pieceToMove instanceof Nuage)) {
            System.out.println("Console : Glace présente en " + destination + " pas de mouvement");
        }
        return "";
    }

    /**
     * Sélectionne les pièces à détruire en fonction de la pièce arrivante et de sa destination.
     * 
     * @param pieceArrivante la pièce qui arrive sur la case de destination
     * @param caseDestination le nom de la case de destination
     * @return une liste des pièces à détruire
     */
    public Vector<Piece> selectPiecesToDestroy(Piece pieceArrivante, String caseDestination) {
        // Si un nuage arrive
        Vector<Piece> toDestroy = new Vector<>();
        if (pieceArrivante instanceof Nuage) {
            for (Piece piece : Pieces) {
                if ((piece.getCurrCase().equals(caseDestination))
                        && (!piece.equals(pieceArrivante))) {
                    if (piece instanceof Vehicule) {
                        toDestroy.add(piece);
                        System.out.println("Console : Destruction en " + caseDestination);
                    }
                }
            }
        }

        // Si une glace arrive
        if (pieceArrivante instanceof Glace) {
            for (Piece piece : Pieces) {
                if ((piece.getCurrCase().equals(caseDestination)) && (!piece.equals(pieceArrivante))
                        && (!piece.getPlayer().equals(pieceArrivante.getPlayer()))) {
                    toDestroy.add(piece);
                    System.out.println("Console : Destruction en " + caseDestination);
                }
            }
        }

        // Si un vehicule arrive
        if (pieceArrivante instanceof Vehicule) {
            for (Piece piece : Pieces) {
                if ((piece.getCurrCase().equals(caseDestination)) && (!piece.equals(pieceArrivante))
                        && (!piece.getPlayer().equals(pieceArrivante.getPlayer()))) {
                    if (piece instanceof Nuage) {
                        if (piece.getToPrint().substring(1, 2)
                                .equals(pieceArrivante.getToPrint().substring(1, 2))) {
                            toDestroy.add(piece);
                            ((Vehicule) pieceArrivante).addCapture();
                            System.out.println("Console : Capture en " + caseDestination);
                        } else {
                            toDestroy.add(pieceArrivante);
                            System.out.println("Console : Nuage d'un mauvais type. Destruction en "
                                    + caseDestination);
                        }
                    }
                    if (piece instanceof Glace) {
                        toDestroy.add(pieceArrivante);
                        System.out.println("Console : Destruction en " + caseDestination);
                    }
                }
            }
        }
        return toDestroy;
    }

    /**
     * Applique les règles du jeu en détruisant les pièces spécifiées.
     * 
     * @param toDestroy une liste des pièces à détruire
     * @return une chaîne de caractères indiquant le résultat de l'application des règles (" x " si
     *         des pièces ont été détruites, sinon " . ")
     */
    public String applyRules(Vector<Piece> toDestroy) {
        for (Piece pieceToDestroy : toDestroy) {
            Pieces.remove(pieceToDestroy);
        }
        if (toDestroy.size() > 0) {
            return " x ";
        }
        return " . ";
    }

    /**
     * Génère un journal des mouvements effectués sur le plateau.
     * 
     * @param caseOrigine le nom de la case d'origine du mouvement
     * @param caseDestination le nom de la case de destination du mouvement
     * @param applyRulesLog le résultat de l'application des règles (chaîne de caractères)
     * @return une chaîne de caractères représentant le journal des mouvements effectués
     */
    public String spitOutLog(String caseOrigine, String caseDestination, String applyRulesLog) {
        return caseOrigine + " - " + caseDestination + applyRulesLog;
    }
}
