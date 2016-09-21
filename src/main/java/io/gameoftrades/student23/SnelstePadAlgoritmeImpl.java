/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.gameoftrades.student23;

import io.gameoftrades.debug.AsciiArtDebugger;
import io.gameoftrades.debug.Debugger;
import io.gameoftrades.model.algoritme.SnelstePadAlgoritme;
import io.gameoftrades.model.kaart.Coordinaat;
import io.gameoftrades.model.kaart.Kaart;
import io.gameoftrades.model.kaart.Pad;
import java.util.ArrayList;
import java.util.List;
import io.gameoftrades.debug.Debuggable;

/**
 *
 * @author Daniel
 */
public class SnelstePadAlgoritmeImpl implements SnelstePadAlgoritme,Debuggable {
    private Debugger debug = new AsciiArtDebugger();
    
    @Override
    public Pad bereken(Kaart kaart, Coordinaat start, Coordinaat eind) {
        Pad testPad = new PadImpl();
        
        List<Tile> openList = new ArrayList(); 
        List<Tile> closedList = new ArrayList();
        
        Tile startTile = new Tile(kaart, start);
        Tile endTile = new Tile(kaart, eind);
        startTile.setGvalue(0);
        closedList.add(startTile);
        boolean endIsnotFound = false;
        Tile selectedTile = closedList.get(0);
        
        //closedList.add(endTile);
        
        while(!endIsnotFound){
            List<Tile> selectedTileNB = selectedTile.getAllNeighbours();
            
            for (Tile tile1 : closedList) {
                if(tile1.getCoordinaat().equals(eind)){
                    endIsnotFound = true;
                } else {
                    System.out.println("end not found");
                }
            }
            
            for (int i = 0; i < selectedTileNB.size(); i++) {
                if(!openList.contains(selectedTileNB.get(i)) && !closedList.contains(selectedTileNB.get(i))){
                    openList.addAll(selectedTileNB);
                    selectedTileNB.get(i).setParent(selectedTile);
                }
            }
            double F = 0;     
            for (Tile optionTile : selectedTileNB) {
                F = selectedTile.getHValue(eind) + optionTile.getGValue();
                optionTile.setFValue(F);
            }

            Tile lowestFTile = selectedTileNB.get(0);
            for (Tile optionTile : selectedTileNB) {
                if(optionTile.getFValue() < lowestFTile.getFValue()){
                    lowestFTile = optionTile;
                }
            }

            closedList.add(lowestFTile);
            selectedTile = lowestFTile;
        }
        
        
        return testPad;
    }

    private void startCalculate(Tile currentTile){
        
    }
    
    @Override
    public void setDebugger(Debugger debugger) {
        this.debug = debugger;
    }
}
