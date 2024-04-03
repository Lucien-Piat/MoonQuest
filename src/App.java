import objets.plateau.PlateauGraphique;
import objets.plateau.PlateauLogique;

public class App {
    public static void main(String[] args) throws Exception {
        PlateauGraphique board = new PlateauGraphique();
        PlateauLogique plateau_logique = new PlateauLogique(board);
        plateau_logique.PrintAllCases();

    }
}