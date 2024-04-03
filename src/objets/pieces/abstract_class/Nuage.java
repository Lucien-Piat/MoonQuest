package objets.pieces.abstract_class;

import java.util.Random;

public class Nuage extends Piece {

    private Boolean hasPlayed;

    public Nuage(String toPrint, String caseDepart){
        super(toPrint, caseDepart); 
        this.hasPlayed=false;   
    }

    public Boolean getHasPlayed(){
        return this.hasPlayed;
    }

    public void switchHasPlay(){
        this.hasPlayed = !hasPlayed; 
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
