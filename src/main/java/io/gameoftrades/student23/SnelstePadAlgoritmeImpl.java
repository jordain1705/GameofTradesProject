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
import java.util.Collections;
import java.util.HashMap;

/**
 *
 * @author Daniel
 */
public class SnelstePadAlgoritmeImpl implements SnelstePadAlgoritme, Debuggable {

    private Debugger debug = new AsciiArtDebugger();
    PadImpl Pad;
    

    @Override
    public Pad bereken(Kaart kaart, Coordinaat start, Coordinaat eind) {
        PadImpl CorrectPath = aStarAlgoritme(kaart, start, eind);
        
        debug.debugPad(kaart, start, CorrectPath);
        
        
        return CorrectPath;
    }
    
    public PadImpl aStarAlgoritme(Kaart kaart, Coordinaat start, Coordinaat eind){
        Pad = new PadImpl(kaart);
        
        List<Tile> openList = new ArrayList();
        List<Tile> closedList = new ArrayList();
        
        List<Coordinaat> closeListCoordinaat = new ArrayList();
        List<Coordinaat> openListCoordinaat = new ArrayList();
        
        Tile startTile = new Tile(kaart, start);
        startTile.setGvalue(0);
        openList.add(startTile);

        Boolean isdone = false;
        
         Tile selectedTile;

        while (!isdone) {
            selectedTile = calcuLowestFTile(openList);
            openList.remove(selectedTile);
            closedList.add(selectedTile);

            if (selectedTile.getCoordinaat().equals(eind)) {
                isdone = true;
                shortestPath(kaart, start, eind,closedList);
            } else {
                
                for (int i = 0; i < closedList.size(); i++) {
                    closeListCoordinaat.add(closedList.get(i).getCoordinaat());
                }

                for (int i = 0; i < openList.size(); i++) {
                openListCoordinaat.add(openList.get(i).getCoordinaat());
                }

                List<Tile> selectedTileNB = selectedTile.getAllNeighbours();

                for (int i = 0; i < selectedTileNB.size(); i++) {
                    Tile tileNB = selectedTileNB.get(i);
                   // if (!closeListCoordinaat.contains(tileNB.getCoordinaat())) {
                        if (!openListCoordinaat.contains(tileNB.getCoordinaat())) {
                            openList.add(tileNB);
                            tileNB.setParent(selectedTile);
                            tileNB.setGvalue(selectedTile.getGValue() + tileNB.getStartGValue());
                            tileNB.setFValue(tileNB.getGValue() + tileNB.getHValue(eind));
                        } else {
                            int ExistingGvalue = selectedTile.getFValue();
                            
//                            if (tileNB.getGValue() < ExistingGvalue + tileNB.getStartGValue()) {
                            if (tileNB.getGValue() > selectedTile.getGValue() + tileNB.getStartGValue()) {
                                tileNB.setParent(selectedTile);
                                
                                tileNB.setGvalue(selectedTile.getGValue() + tileNB.getStartGValue());
                                tileNB.setFValue(tileNB.getGValue() + tileNB.getHValue(eind));
                                
                                
                            }
                            
                            
                            //debug.debugCoordinaten(kaart, closeListCoordinaat);
                        }
                   // }
                }
                
            }
        }
        
        
        
        return Pad;
    }
   
 
    private Tile calcuLowestFTile(List<Tile> openList) {

        if (!openList.isEmpty()) {
            Tile lowestFTile = openList.get(0);
            for (Tile tile1 : openList) {
                if (tile1.getFValue() < lowestFTile.getFValue()) {
                    lowestFTile = tile1;
                }

            }
            return lowestFTile;
        } else {
            return null;
        }
    }

    private void shortestPath(Kaart kaart, Coordinaat start, Coordinaat Eind, List<Tile> closedList) {

        List<Coordinaat> correctPath = new ArrayList();

        Tile selectTile = closedList.get(closedList.size() - 1);
        Boolean startFound = false;
        
        int PathGValue = closedList.get(closedList.size() - 1).getGValue();
        
        correctPath.add(Eind);
        
        while (!startFound) {
            if (selectTile.getParent() != null) {
                //PathGValue += selectTile.getGValue();
                
                if (!selectTile.getParent().getCoordinaat().equals(start)) {
                    correctPath.add(selectTile.getParent().getCoordinaat());
                    selectTile = selectTile.getParent();
                } else {
                    startFound = true;
                }
                
                
            }
        }
        
        
        
        correctPath.add(start);
        
        Collections.reverse(correctPath);
        Pad.setPathGValue(PathGValue);
        System.out.println(PathGValue);
        Pad.setPadCoordinaten(correctPath);
    }

    @Override
    public void setDebugger(Debugger debugger) {
        this.debug = debugger;
    }
}
