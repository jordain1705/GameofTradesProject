/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.gameoftrades.student23;

import io.gameoftrades.debug.AsciiArtDebugger;
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

    private Debugger debug = new AsciiArtDebugger();
    private List<Stad> StedenTourPad;
    private SnelstePadAlgoritmeImpl SnelstePimpl;
    private Map<Stad, Integer> map;

    Map<List<Stad>, Integer> beginstadmap = new HashMap<>();

    private List<Stad> kortsteafstandenstad;
    private PadImpl pad;

    public StedenTourAlgoritmeImpl() {
        kortsteafstandenstad = new ArrayList<>();
        map = new HashMap<>();
        SnelstePimpl = new SnelstePadAlgoritmeImpl();

    }

    @Override
    public List<Stad> bereken(Kaart kaart, List<Stad> steden) {
        List<Stad> tour = findNearest(kaart, steden);

        debug.debugSteden(kaart, tour);

        return tour;
    }

    public List<Stad> findNearest(Kaart kaart, List<Stad> list) {

        for (int j = 0; j < list.size(); j++) {
            kortsteafstandenstad.clear();
            List<Stad> lijstvanSteden = new ArrayList();
            lijstvanSteden.addAll(list);
            Stad startstad = lijstvanSteden.get(j);
            kortsteafstandenstad.add(startstad);
            int kosten = 0;
            lijstvanSteden.remove(j);
            Boolean isdone = false;
            while (!isdone) {

                for (int i = 0; i < lijstvanSteden.size(); i++) {
                    Stad get = lijstvanSteden.get(i);
                    pad = SnelstePimpl.aStarAlgoritme(kaart, startstad.getCoordinaat(), get.getCoordinaat());
                    int gvalue = pad.getPathGValue();
                    map.put(get, gvalue);

                }

                startstad = MinGvalueCity().getKey();
                kosten = kosten + MinGvalueCity().getValue();
                kortsteafstandenstad.add(startstad);
                lijstvanSteden.remove(startstad);
                map.clear();

                //Print kosten
                if (lijstvanSteden.isEmpty()) {
                    List<Stad> bestekorste = kortsteafstandenstad;
                    System.out.println(kosten);
                    beginstadmap.put(bestekorste, kosten);
                    isdone = true;
                }
            }
        }
        System.out.println("Dit is de beste route de beste kosten en  de beste beginstad :" + bestebeginstad().getValue());
        StedenTourPad = bestebeginstad().getKey();
        return StedenTourPad;
    }

    public Entry<Stad, Integer> MinGvalueCity() {
        Map.Entry<Stad, Integer> minEntry = null;
        for (Map.Entry<Stad, Integer> entry : map.entrySet()) {
            if (minEntry == null || entry.getValue() < minEntry.getValue()) {
                minEntry = entry;
            }

        }
        return minEntry;
    }

    public Entry<List<Stad>, Integer> bestebeginstad() {
        Map.Entry<List<Stad>, Integer> minEntry = null;
        for (Entry<List<Stad>, Integer> entry : beginstadmap.entrySet()) {
            if (minEntry == null || entry.getValue() < minEntry.getValue()) {
                minEntry = entry;
            }
        }
        return minEntry;
    }

    @Override
    public void setDebugger(Debugger debugger) {
        this.debug = debugger;
    }
}
