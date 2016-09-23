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
    public double G = 0;
    
    @Override
    public Pad bereken(Kaart kaart, Coordinaat start, Coordinaat eind) {
        Pad testPad = new PadImpl();
        
        List<Tile> openList = new ArrayList(); 
        List<Tile> closedList = new ArrayList();
        
        Tile startTile = new Tile(kaart, start);
        startTile.setGvalue(0);
        openList.add(startTile);
        
        Boolean isNotDone = false;
        
        
        
        while(!isNotDone){
            System.out.println("Step:");
            Tile selectedTile = calcuLowestFTile(openList, eind);
            openList.remove(selectedTile);
            closedList.add(selectedTile);
            
            if(selectedTile.getCoordinaat().equals(eind)){
                isNotDone = true;
                System.out.println("Found end");
                shortestPath(start, closedList);
            } else {
                List<Coordinaat> closeListCoordinaat = new ArrayList();
                List<Coordinaat> openListCoordinaat = new ArrayList();

                for (int i = 0; i < closedList.size(); i++) {
                    closeListCoordinaat.add(closedList.get(i).getCoordinaat());
                }

                for (int i = 0; i < openList.size(); i++) {
                    openListCoordinaat.add(openList.get(i).getCoordinaat());
                }

                List<Tile> selectedTileNB = selectedTile.getAllNeighbours();

                for (Tile optionTile : selectedTileNB) {
                    if(!closeListCoordinaat.contains(optionTile.getCoordinaat())){
                        if(!openListCoordinaat.contains(optionTile.getCoordinaat())){
                            openList.add(optionTile);
                            optionTile.setParent(selectedTile);
                        } else {
                            double ExistingGvalue = selectedTile.getGValue() + optionTile.getGValue();
                            if(ExistingGvalue < optionTile.getGValue()){
                                optionTile.setParent(selectedTile);
                                optionTile.setGvalue(ExistingGvalue);
                                G += optionTile.getGValue();
                                optionTile.setFValue(optionTile.getHValue(eind) + optionTile.getGValue());
                            }
                        }
                    }
                }
                System.out.println("OpenList");
                for (Tile optionTile : openList){
                    System.out.print(optionTile.getCoordinaat());
                    System.out.println("");
                }
                
                System.out.println("ClosedList");
                for (Tile optionTile : closedList){
                    System.out.print(optionTile.getCoordinaat());
                    System.out.println("");
                }
            }
            
        }
        return testPad;
    }

    private Tile calcuLowestFTile(List<Tile> openList, Coordinaat eind){
        
        if(!openList.isEmpty()){
            Tile lowestFTile = openList.get(0);
            for (Tile tile1 : openList) {
                G += tile1.getGValue();
                tile1.setFValue(tile1.getHValue(eind) + G + tile1.getGValue());
                if(tile1.getFValue() < lowestFTile.getFValue()){
                    lowestFTile = tile1;
                }
            }
            
            lowestFTile.setGvalue(G);
            return lowestFTile;
        } else {
            return null;
        }
    }
    
    private void shortestPath(Coordinaat start, List<Tile> closedList){
        
        List<Coordinaat> correctPath = new ArrayList();
        System.out.println("ClosedList");
        
        Tile selectTile = closedList.get(closedList.size() - 1);
        Boolean startFound = false;
        
        
        while(!startFound){
            if(selectTile.getParent() != null){
                if(!selectTile.getParent().getCoordinaat().equals(start)){
                    System.out.println(selectTile.getParent().getCoordinaat());
                    selectTile = selectTile.getParent();
                } else {
                    startFound = true;
                }
                
            }
        }
        
        Boolean startNotFound = false;
        Tile checkTile = closedList.get(0);
        /*
        while(!startNotFound){
            if(!checkTile.getParent().getCoordinaat().equals(start)){
                
            }
        }*/
    }
    
    @Override
    public void setDebugger(Debugger debugger) {
        this.debug = debugger;
    }
}
