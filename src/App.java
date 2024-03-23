import objets.plateau.Plateau;
import objets.*;

public class App {
    public static void main(String[] args) throws Exception {
        Plateau board = new Plateau();
        board.getCases()[18].moveOneCase("up", 17);
    }
}