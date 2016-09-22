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
        
        List<Coordinaat> openList = new ArrayList(); 
        List<Coordinaat> closedList = new ArrayList();
        
        Tile startTile = new Tile(kaart, start);
        Tile endTile = new Tile(kaart, eind);
        startTile.setGvalue(0);
        closedList.add(startTile.getCoordinaat());
        boolean endIsnotFound = false;
        Tile selectedTile = startTile;
        
        //closedList.add(endTile);
        for (int x = 0; x < 10; x++) {
            List<Tile> selectedTileNB = selectedTile.getAllNeighbours();

        for (Coordinaat tile1 : closedList) {
            if(tile1.equals(eind)){
                endIsnotFound = true;
            } else {
                //System.out.println("end not found");
            }
        }

        for (int i = 0; i < selectedTileNB.size(); i++) {
                System.out.print("Before" + selectedTileNB.get(i).getCoordinaat());
            }
            System.out.println("");
        
        
        for (int i = 0; i < selectedTileNB.size(); i++) {
            if(!openList.contains(selectedTileNB.get(i).getCoordinaat()) && !closedList.contains(selectedTileNB.get(i).getCoordinaat())){
                openList.add(selectedTileNB.get(i).getCoordinaat());
                System.out.println("Niet in open en close");
                selectedTileNB.get(i).setParent(selectedTile);
            } else if (closedList.contains(selectedTileNB.get(i).getCoordinaat())){
                System.out.println("Zit in closedList");
                System.out.println("Remove:" + selectedTileNB.get(i).getCoordinaat());
                selectedTileNB.remove(i);   
            }
        }
        
            for (int i = 0; i < selectedTileNB.size(); i++) {
                System.out.print("After" + selectedTileNB.get(i).getCoordinaat());
            }
            System.out.println("");
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

        closedList.add(lowestFTile.getCoordinaat());
        openList.remove(lowestFTile.getCoordinaat());
        selectedTile = lowestFTile;
        //}
            System.out.println("ClosedList:");

        for (int i = 0; i < closedList.size(); i++) {

            System.out.println(closedList.get(i));
        }
            System.out.println("Openlist:");
        for (int i = 0; i < openList.size(); i++) {

            System.out.println(openList.get(i));
        }
        
            System.out.println("step" + x);
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
