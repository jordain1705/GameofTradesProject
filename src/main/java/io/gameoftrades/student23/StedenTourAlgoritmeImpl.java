/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.gameoftrades.student23;

import io.gameoftrades.debug.Debuggable;
import io.gameoftrades.debug.Debugger;
import io.gameoftrades.model.algoritme.SnelstePadAlgoritme;
import io.gameoftrades.model.algoritme.StedenTourAlgoritme;
import io.gameoftrades.model.kaart.Kaart;
import io.gameoftrades.model.kaart.Pad;
import io.gameoftrades.model.kaart.Stad;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

/**
 *
 * @author JordainGijsbertha
 */
public class StedenTourAlgoritmeImpl implements StedenTourAlgoritme, Debuggable {

    SnelstePadAlgoritmeImpl impl1 = new SnelstePadAlgoritmeImpl();
    Debugger debug;

    @Override
    public List<Stad> bereken(Kaart kaart, List<Stad> steden) {

        debug.debugSteden(kaart, findNearest(kaart, steden));
        return findNearest(kaart, steden);
    }

    public List<Stad> findNearest(Kaart kaart, List<Stad> list) {
        List<Stad> thelist = new ArrayList(list);
        List<Stad> kortsteafstandenstad = new ArrayList<>();

        SnelstePadAlgoritme snel = new SnelstePadAlgoritmeImpl();

        //   pad.getPathGValue();
        Stad startstad; // eerste stad waar je begint

        ListIterator<Stad> iterator = list.listIterator();

        startstad = thelist.get(0);
        boolean isdone = true;
        while (iterator.hasNext()) {
            

            Map<Stad, Double> map = new HashMap<>();

            for (int i = 1; i < thelist.size(); i++) {
                Stad get = thelist.get(i);
               PadImpl pad = (PadImpl) snel.bereken(kaart, startstad.getCoordinaat(), get.getCoordinaat());
                double gvalue = pad.getPathGValue();
               

                map.put(get, gvalue);
            }

            //checked de korste afstand
            Map.Entry<Stad, Double> minEntry = null;
            for (Map.Entry<Stad, Double> entry : map.entrySet()) {
                if (minEntry == null || entry.getValue() <= minEntry.getValue()) {
                    minEntry = entry;
                }

            }
            
            //voeg korsteafstand stad toe 
            kortsteafstandenstad.add(startstad);
            System.out.println(kortsteafstandenstad.size()+"bkjsjvhbjhsbvjhdbvjhbjdhfbvjhbhbfvhfvhbfbhdbhdfhbjvbhjvfbhjvbhdfbhbhbhv");
            thelist.remove(startstad);
            Stad closest = minEntry.getKey();
            startstad = closest;
            

        
        }
        
        
        //debug.debugSteden(kaart, kortsteafstandenstad);
        for (Stad s : kortsteafstandenstad) {
            System.out.println(s.getNaam());
            
        }
        return kortsteafstandenstad;
        
    }

    @Override
    public void setDebugger(Debugger debugger) {
        this.debug = debugger;
    }
}
