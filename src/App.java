import objets.plateau.Plateau;
import objets.*;

public class App {
    public static void main(String[] args) throws Exception {
        Plateau board = new Plateau();
        System.out.println(board.findCaseWithName("A5").getIndice());
       

        board.display();
    }
}