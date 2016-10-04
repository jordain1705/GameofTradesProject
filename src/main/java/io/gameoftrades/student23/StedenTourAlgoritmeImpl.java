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
import io.gameoftrades.model.kaart.Stad;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 *
 * @author JordainGijsbertha
 */
public class StedenTourAlgoritmeImpl implements StedenTourAlgoritme, Debuggable {

    Debugger debug;

    @Override
    public List<Stad> bereken(Kaart kaart, List<Stad> steden) {

        debug.debugSteden(kaart, findNearest(kaart, steden));
        return findNearest(kaart, steden);
    }

    public List<Stad> findNearest(Kaart kaart, List<Stad> list) {

        List<Stad> thelist = new ArrayList(list);
        SnelstePadAlgoritme impl1 = new SnelstePadAlgoritmeImpl();
        Stad startstad; // eerste stad waar je begint

        ListIterator<Stad> iterator = thelist.listIterator();
        double kosten = 0;

        List<Stad> kortsteafstandenstad = new ArrayList<>();
        Map<Stad, Double> map;
        startstad = thelist.get(0);
        kortsteafstandenstad.add(startstad);
        thelist.remove(0);
        while (iterator.hasNext()) {

            map = new HashMap<Stad, Double>();

            for (int i = 0; i < thelist.size(); i++) {
                Stad get = thelist.get(i);

                //double afstand = startstad.getCoordinaat().afstandTot(get.getCoordinaat());
                if (startstad.getCoordinaat() == get.getCoordinaat()) {
                    System.out.println("bo mama");
                }
                PadImpl pad = (PadImpl) impl1.bereken(kaart, startstad.getCoordinaat(), get.getCoordinaat());
                double gvalue = pad.getPathGValue();
                
                map.put(get, gvalue);
            }

            //checked de korste afstand
            Entry<Stad, Double> min = null;
            for (Entry<Stad, Double> entry : map.entrySet()) {
                if (min == null || min.getValue() > entry.getValue()) {
                    min = entry;
                }
            }

            //voeg korsteafstand stad toe 
            Stad closest = min.getKey();
            startstad = closest;
            kosten = kosten + min.getValue();
            kortsteafstandenstad.add(startstad);
            thelist.remove(startstad);
            if (thelist.isEmpty()) {
                System.out.println(kosten);
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
