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

/**
 *
 * @author Daniel
 */
public class SnelstePadAlgoritmeImpl implements SnelstePadAlgoritme, Debuggable {

    private Debugger debug = new AsciiArtDebugger();
    PadImpl Pad;

    @Override
    public Pad bereken(Kaart kaart, Coordinaat start, Coordinaat eind) {
        Pad = new PadImpl(kaart, start, eind);

        double PathGValue = 0;

        List<Tile> openList = new ArrayList();
        List<Tile> closedList = new ArrayList();

        Tile startTile = new Tile(kaart, start);
        //startTile.setGvalue(PathGValue);
        openList.add(startTile);

        Boolean isdone = false;

        while (!isdone) {
            //for (int x = 0; x < 10; x++) {
            //System.out.println("Step: " + x);
            Tile selectedTile = calcuLowestFTile(openList, eind, PathGValue);
            //PathGValue += selectedTile.getGValue();
            //System.out.println("G:" + PathGValue);
            openList.remove(selectedTile);
            closedList.add(selectedTile);

            if (selectedTile.getCoordinaat().equals(eind)) {
                isdone = true;
                System.out.println("Found end");
                shortestPath(start, eind,closedList);
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
                    if (!closeListCoordinaat.contains(optionTile.getCoordinaat())) {
                        if (!openListCoordinaat.contains(optionTile.getCoordinaat())) {
                            openList.add(optionTile);
                            optionTile.setParent(selectedTile);
                            optionTile.setGvalue(PathGValue + optionTile.getGValue());
                        } else {
                            double ExistingGvalue = selectedTile.getHValue(eind) + selectedTile.getGValue();
                            if (optionTile.getGValue() > ExistingGvalue) {
                                optionTile.setParent(selectedTile);
                                optionTile.setGvalue(ExistingGvalue);
                                //optionTile.setFValue(optionTile.getHValue(eind) + PathGValue + optionTile.getGValue());
                            }
                        }
                    }
                }
                /*
                System.out.println("OpenList");
                for (Tile optionTile : openList){
                    System.out.print(optionTile.getCoordinaat());
                    System.out.println("");
                }*/

 /*System.out.println("ClosedList");
                for (Tile optionTile : closedList){
                    System.out.print(optionTile.getCoordinaat());
                    System.out.println("");
                }*/
            }
        }

        debug.debugPad(kaart, start, Pad);

        return Pad;
    }

    private Tile calcuLowestFTile(List<Tile> openList, Coordinaat eind, double PathGValue) {

        if (!openList.isEmpty()) {
            Tile lowestFTile = openList.get(0);
            for (Tile tile1 : openList) {
                //tile1.setFValue(tile1.getHValue(eind) + PathGValue + tile1.getGValue());
                if (tile1.getFValue() < lowestFTile.getFValue()) {
                    lowestFTile = tile1;
                }

            }
            return lowestFTile;
        } else {
            return null;
        }
    }

    private void shortestPath(Coordinaat start, Coordinaat Eind, List<Tile> closedList) {

        List<Coordinaat> correctPath = new ArrayList();
        //System.out.println("ParentList");

        Tile selectTile = closedList.get(closedList.size() - 1);
        Boolean startFound = false;
        
        correctPath.add(Eind);
        
        while (!startFound) {
            if (selectTile.getParent() != null) {
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
        Pad.setPadCoordinaten(correctPath);

    }

    @Override
    public void setDebugger(Debugger debugger) {
        this.debug = debugger;
    }
}
