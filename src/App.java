import objets.plateau.Plateau;
import objets.*;

public class App {
    public static void main(String[] args) throws Exception {
        Plateau board = new Plateau();
        System.out.println(board.getCases()[50].moveOneCase("right", 17));
    }
}