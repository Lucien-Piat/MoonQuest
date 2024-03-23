package objets.pieces.abstract_class;

public abstract class Piece {

    protected String toPrint; 

    public Piece(String toPrint){
        this.toPrint = toPrint;
    }
    
    public String getToPrint(){
        return this.toPrint;
    }
    
}
