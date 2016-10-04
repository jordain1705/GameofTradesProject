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
    List<Tile> neighbours = new ArrayList();
    private Tile parent;
    private int gValue, FValue; 
    private int hValue = 0;
    
    public Tile(Kaart kaart, Coordinaat position){
        this.position = position;
        this.kaart = kaart;
        gValue = kaart.getTerreinOp(position).getTerreinType().getBewegingspunten();
        FValue = gValue + hValue;
    }
    
    public Tile(Coordinaat position){
        this.position = position;        
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
    
    public void setGvalue(int gValue){
        this.gValue = gValue;
    }
    
    public int getHValue(Coordinaat c){   
        hValue = (Math.abs(position.getX() - c.getX()) + Math.abs(position.getY() - c.getY()));
        return hValue;
    }
    
    public int getFValue() {
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
    
    public List<Tile>getAllNeighbours(){
        Richting[] mogelijkeRichtingen = kaart.getTerreinOp(position).getMogelijkeRichtingen();

        for (int i = 0; i < mogelijkeRichtingen.length; i++) {
             neighbours.add(new Tile(kaart, position.naar(mogelijkeRichtingen[i])));
        }
        return neighbours;
    }

    

    
}
