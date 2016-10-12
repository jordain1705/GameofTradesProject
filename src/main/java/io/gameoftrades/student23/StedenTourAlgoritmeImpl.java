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
import java.util.Map.Entry;

/**
 *
 * @author JordainGijsbertha
 */
public class StedenTourAlgoritmeImpl implements StedenTourAlgoritme, Debuggable {

    Debugger debug;
    private SnelstePadAlgoritmeImpl SnelstePimpl;
    private Map<Stad, Double> map ;
    private List<Stad> kortsteafstandenstad ;
   private  PadImpl pad;

    public StedenTourAlgoritmeImpl() {
        kortsteafstandenstad = new ArrayList<>();
        map= new HashMap<>();
        SnelstePimpl = new SnelstePadAlgoritmeImpl();
    }

    @Override
    public List<Stad> bereken(Kaart kaart, List<Stad> steden) {

        debug.debugSteden(kaart, findNearest(kaart, steden));
        return findNearest(kaart, steden);
    }

    public List<Stad> findNearest(Kaart kaart, List<Stad> list) {

        List<Stad> lijstvanSteden = new ArrayList(list);
        ListIterator<Stad> iterator = lijstvanSteden.listIterator();

        Stad startstad = lijstvanSteden.get(0); // eerste stad waar je begint
        double kosten = 0;
        kortsteafstandenstad.add(startstad);
        lijstvanSteden.remove(0);

        while (iterator.hasNext()) {

            for (int i = 0; i < lijstvanSteden.size(); i++) {
                Stad get = lijstvanSteden.get(i);
                pad = SnelstePimpl.aStarAlgoritme(kaart, startstad.getCoordinaat(), get.getCoordinaat());
                double gvalue = pad.getPathGValue();
                map.put(get, gvalue);
            }

            startstad = MinGvalueCity().getKey();
            kosten = kosten + MinGvalueCity().getValue();
            kortsteafstandenstad.add(startstad);
            lijstvanSteden.remove(startstad);
            map.clear();

            //Print kosten
            if (lijstvanSteden.isEmpty()) {
                System.out.println(kosten);
            }
        }

        return kortsteafstandenstad;
    }

    private Entry<Stad, Double> MinGvalueCity() {
        Entry<Stad, Double> min = null;
        for (Entry<Stad, Double> entry : map.entrySet()) {
            if (min == null || min.getValue() > entry.getValue()) {
                min = entry;
            }
        }
        return min;
    }

    @Override
    public void setDebugger(Debugger debugger) {
        this.debug = debugger;
    }
}
