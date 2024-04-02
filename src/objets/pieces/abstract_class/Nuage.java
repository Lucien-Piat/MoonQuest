package objets.pieces.abstract_class;

import java.util.Random;

public class Nuage extends Piece {

    final String[] directions = {"nord", "nord_est", "est", "sud_est", "sud", "sud_ouest", "ouest", "nord_ouest"};

    public Nuage(String toPrint){
        super(toPrint); 
    }

    public String move(){
        Random random = new Random();
        if (random.nextInt(5) == 0) { // 0 to 4 (inclusive)
            Random random2 = new Random();
            int randomIndex = random2.nextInt(directions.length);
            return directions[randomIndex];
        }else return "bouge_pas";
    } 
}
