package objets.pieces.abstract_class;

import java.awt.Color;

public abstract class Piece {

    protected String toPrint; 
    protected Color player = Color.BLACK;
    final String[] directions = {"nord", "nord_est", "est", "sud_est", "sud", "sud_ouest", "ouest", "nord_ouest"};

    public Piece(String toPrint){
        this.toPrint = toPrint;
        
    }
    
    public String getToPrint(){
        return this.toPrint;
    }
    public Color getPlayer(){
        return this.player;
    }

    public String move(){
        return "bouge_pas";
    }
    
}
