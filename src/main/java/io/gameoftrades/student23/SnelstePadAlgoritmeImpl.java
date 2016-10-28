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
import io.gameoftrades.model.kaart.Richting;
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
        PadImpl CorrectPath = aStarAlgoritme(kaart, start, eind);

        debug.debugPad(kaart, start, CorrectPath);

        return CorrectPath;
    }

    public PadImpl aStarAlgoritme(Kaart kaart, Coordinaat start, Coordinaat eind) {
        Pad = new PadImpl(kaart);
        
        if(start.equals(eind)){
            throw new IllegalArgumentException("Start coordinate and End coordinate are the same.");
        }

        List<Tile> openList = new ArrayList();
        List<Tile> closedList = new ArrayList();

        Tile startTile = new Tile(kaart, start, eind);
        startTile.setGvalue(0);
        openList.add(startTile);

        Boolean isdone = false;

        Tile selectedTile;

        while (!isdone) {
            selectedTile = calculateLowestFTile(openList);
            openList.remove(selectedTile);
            closedList.add(selectedTile);

            if (selectedTile.getCoordinaat().equals(eind)) {
                setShortestPath(start, eind, closedList);
                break;
            } else {
                List<Tile> selectedTileNB = getAllNeighbours(kaart, selectedTile, eind);

                for (int i = 0; i < selectedTileNB.size(); i++) {
                    Tile tileNB = selectedTileNB.get(i);
                    if (!closedList.contains(tileNB)) {
                        if (openList.contains(tileNB)) {
                            for (Tile t : openList) {
                                if (tileNB.equals(t)) {
                                    int calculateCost = selectedTile.getGValue() + t.getStartGValue();

                                    if (calculateCost < t.getGValue()) {
                                        t.setParent(selectedTile);
                                        t.setGvalue(selectedTile.getGValue() + t.getStartGValue());
                                    }
                                }
                            }
                        } else {
                            tileNB.setParent(selectedTile);
                            tileNB.setGvalue(selectedTile.getGValue() + tileNB.getStartGValue());
                            openList.add(tileNB);
                        }
                    }
                }

            }
        }

        return Pad;
    }

    private List<Tile> getAllNeighbours(Kaart kaart, Tile selectedTile, Coordinaat eind) {
        List<Tile> neighbours = new ArrayList();

        Richting[] mogelijkeRichtingen = kaart.getTerreinOp(selectedTile.getCoordinaat()).getMogelijkeRichtingen();

        for (int i = 0; i < mogelijkeRichtingen.length; i++) {

            neighbours.add(new Tile(kaart, selectedTile.getCoordinaat().naar(mogelijkeRichtingen[i]), eind));
        }
        return neighbours;
    }

    private Tile calculateLowestFTile(List<Tile> openList) {

        if (!openList.isEmpty()) {
            Tile lowestFTile = openList.get(0);
            for (Tile tile1 : openList) {
                if (tile1.getFValue() < lowestFTile.getFValue()) {
                    lowestFTile = tile1;
                }
            }
            return lowestFTile;
        } else {
            throw new IllegalArgumentException("No valid path found");
            //return null;
        }
    }

    private void setShortestPath(Coordinaat start, Coordinaat Eind, List<Tile> closedList) {

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
        //System.out.println(PathGValue);
        Pad.setPadCoordinaten(correctPath);
    }

    @Override
    public void setDebugger(Debugger debugger) {
        this.debug = debugger;
    }
}
