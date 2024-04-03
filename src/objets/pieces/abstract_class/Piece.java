package objets.pieces.abstract_class;

import java.awt.Color;

public abstract class Piece {

    protected String toPrint; 
    protected Color player = Color.BLACK;
    final String[] directions = {"nord", "nord_est", "est", "sud_est", "sud", "sud_ouest", "ouest", "nord_ouest"};
    protected String currCase; 

    public Piece(String toPrint, String caseDepart){
        this.toPrint = toPrint;    
        this.currCase = caseDepart; 
    }
    
    public String getToPrint(){
        return this.toPrint;
    }
    public Color getPlayer(){
        return this.player;
    }
    public String getCurrCase(){
        return this.currCase;
    }
    public void setNewCase(String newCase){
        this.currCase=newCase;
    }
    public String move(){
        return "bouge_pas";
    }
    
}
