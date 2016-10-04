/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.gameoftrades.student23;

import io.gameoftrades.debug.Debuggable;
import io.gameoftrades.debug.Debugger;
import io.gameoftrades.model.algoritme.StedenTourAlgoritme;
import io.gameoftrades.model.kaart.Kaart;
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
        
        List<Stad> thelist  = new ArrayList(list);
     
        Stad startstad; // eerste stad waar je begint

        ListIterator<Stad> iterator = thelist.listIterator();

        List<Stad> kortsteafstandenstad = new ArrayList<>();

        startstad = thelist.get(0);
        while (iterator.hasNext()) {
          
             Map<Stad, Double> map = new HashMap<>();
            
            for (int i = 0; i < thelist.size(); i++) {
                Stad get = thelist.get(i);
                
                double afstand = startstad.getCoordinaat().afstandTot(get.getCoordinaat());
                map.put(get, afstand);
            }

             //checked de korste afstand
            Map.Entry<Stad, Double> minEntry = null;
            for (Map.Entry<Stad, Double> entry : map.entrySet()) {
                if (minEntry == null || entry.getValue() < minEntry.getValue()) {
                    minEntry = entry;
                }
                
                
            }
            //voeg korsteafstand stad toe 
            kortsteafstandenstad.add(startstad);
            thelist.remove(startstad);
            Stad closest = minEntry.getKey();
            startstad = closest;
          
            if (thelist.isEmpty()) {
                break;
            }
        }

        return kortsteafstandenstad;
    }

    @Override
    public void setDebugger(Debugger debugger) {
        this.debug = debugger;
    }
}
