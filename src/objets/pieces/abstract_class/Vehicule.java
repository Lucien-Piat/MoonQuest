package objets.pieces.abstract_class;

public abstract class Vehicule extends Piece {

    protected int captures; 

    public Vehicule(String toPrint,String caseDepart){
        super(toPrint, caseDepart); 
        this.captures = 0;
    }

    public void addCapture(){
        if (this.captures<3){
            this.captures++; 
        } 
    }

    public int getCaptures(){return this.captures;}

    @Override
    public boolean isAcitive(){
        if (this.captures>0){
            return true;
        }
        return false; 
    }

    @Override
    public String move(){
        int distance = 1;
        if (isAcitive()){distance=2;}
        while (true) {
            return askDirection(distance);
        }
    }
}
