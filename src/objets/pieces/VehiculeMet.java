package objets.pieces;

import java.awt.Color;

import objets.pieces.abstract_class.Vehicule;

public class VehiculeMet extends Vehicule {

    public VehiculeMet(Color player){
        super("Vm"); 
        this.player = player;
    }
}