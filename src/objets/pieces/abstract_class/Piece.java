package objets.pieces.abstract_class;

import java.awt.Color;

public abstract class Piece {

    protected String toPrint; 
    protected Color player = Color.BLACK;

    public Piece(String toPrint){
        this.toPrint = toPrint;
        
    }
    
    public String getToPrint(){
        return this.toPrint;
    }
    public Color getPlayer(){
        return this.player;
    }
    
}
