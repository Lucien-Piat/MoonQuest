package objets.pieces;

import java.awt.Color;

import objets.pieces.abstract_class.Nuage;

public class NuageEau extends Nuage {

    public NuageEau(){
        super("No"); 
        this.player = Color.BLUE;   
    }
}