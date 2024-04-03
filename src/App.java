import objets.plateau.Plateau;
import objets.players.NonJoueur;
import objets.*;

public class App {
    public static void main(String[] args) throws Exception {
        Plateau board = new Plateau();
        board.display();
        NonJoueur.joue(board);

    }
}