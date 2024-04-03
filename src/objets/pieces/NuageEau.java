package objets.pieces;

import java.awt.Color;

import objets.pieces.abstract_class.Nuage;

public class NuageEau extends Nuage {

    public NuageEau(String caseDepart){
        super("No", caseDepart); 
        this.player = Color.BLUE;   
    }
}