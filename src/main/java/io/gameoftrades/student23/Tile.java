/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.gameoftrades.student23;

import io.gameoftrades.model.kaart.Coordinaat;
import io.gameoftrades.model.kaart.Kaart;
import io.gameoftrades.model.kaart.Richting;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Daniel
 */
public class Tile{
    
    Kaart kaart;
    Coordinaat position;

    private Tile parent;
    private int gValue, FValue; 
    private int hValue;
    
    private Coordinaat eind;
    
    public Tile(Kaart kaart, Coordinaat position, Coordinaat Eind){
        this.position = position;
        this.kaart = kaart;
        this.eind = Eind;
        
        gValue = kaart.getTerreinOp(position).getTerreinType().getBewegingspunten();
        hValue = (Math.abs(eind.getX() - position.getX()) + Math.abs(eind.getY() - position.getY()));
        FValue = gValue + hValue;
    }
    
    public Coordinaat getCoordinaat(){
        return position;
    }
    
    public int getX(){
        return position.getX();
    }
    
    public int getY(){
        return position.getY();
    }

    public int getGValue(){
        return this.gValue;
    }
    
    public int getStartGValue(){
        int GStartValue =kaart.getTerreinOp(position).getTerreinType().getBewegingspunten();
        return GStartValue;
    }
    
    public void setGvalue(int gValue){
        this.gValue = gValue;
    }
    
    public int getHValue(){   
        return this.hValue;
    }
    
    public int getFValue() {
        FValue = gValue + hValue;
        return FValue;
    }

    public void setFValue(int FValue) {
        this.FValue = FValue;
    }

    public Tile getParent() {
        return parent;
    }

    public void setParent(Tile parent) {
        this.parent = parent;
    }
    
    @Override
    public boolean equals (Object o) {
        Tile temp = (Tile) o;
        
        if (o != null && temp.getCoordinaat().equals(this.getCoordinaat())){
            return true;
        }
        return false;
    }
}
