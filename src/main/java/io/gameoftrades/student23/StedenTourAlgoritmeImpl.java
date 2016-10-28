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
import java.util.Map;
import java.util.Map.Entry;

/**
 *
 * @author JordainGijsbertha
 */
public class StedenTourAlgoritmeImpl implements StedenTourAlgoritme, Debuggable {

    private Debugger debug = new AsciiArtDebugger();
    private List<Stad> StedenTourPad;
    private final SnelstePadAlgoritmeImpl SnelstePimpl;
    private PadImpl pad;
    private static StedenTourAlgoritmeImpl firstinstance;

    public StedenTourAlgoritmeImpl() {
        SnelstePimpl = new SnelstePadAlgoritmeImpl();
        StedenTourPad = new ArrayList();

    }

    //Singleton
    public static synchronized StedenTourAlgoritmeImpl getInstance() {
        if (firstinstance == null) {

            firstinstance = new StedenTourAlgoritmeImpl();

        }
        return firstinstance;
    }

    @Override
    public List<Stad> bereken(Kaart kaart, List<Stad> steden) {
        List<Stad> tour = TourAlgoritme(kaart, steden);

        debug.debugSteden(kaart, tour);

        return tour;
    }

    public List<Stad> TourAlgoritme(Kaart kaart, List<Stad> list) {
        Map<Stad, Integer> KostenNaarStad = new HashMap<>();
        Map<List<Stad>, Integer> TotaalkostenAndereBeginstad = new HashMap<>();
        List<Stad> KostenStad = new ArrayList<>();
        List<Stad> lijstvanSteden = new ArrayList<>(list);
        
        List<Stad> closedlist = new ArrayList<>();
        int totaleGKosten;
        for (int j = 0; j < list.size(); j++) {
            Stad startstad = lijstvanSteden.get(j);
            KostenStad.add(startstad);
            closedlist.add(startstad);
            int Gkosten = 0;
            Boolean isdone = false;
            while (!isdone) {

                for (int i = 0; i < lijstvanSteden.size(); i++) {
                    Stad volgendestad = lijstvanSteden.get(i);
                    if (closedlist.contains(volgendestad)) {
                        System.out.println("skipped");
                    }
                    else{
                    pad = SnelstePimpl.aStarAlgoritme(kaart, startstad.getCoordinaat(), volgendestad.getCoordinaat());
                    int gvalue = pad.getPathGValue();
                    KostenNaarStad.put(volgendestad, gvalue);
                    }
                }
                startstad = MinGvalueCity(KostenNaarStad).getKey();
                Gkosten = Gkosten + MinGvalueCity(KostenNaarStad).getValue();
                KostenStad.add(startstad);
                closedlist.add(startstad);
                KostenNaarStad.clear();

                if (KostenStad.size()==21) {
                    TotaalkostenAndereBeginstad.put(KostenStad, Gkosten);
                    KostenStad.clear();
                    closedlist.clear();
                    isdone = true;
                }
            }
        }

        totaleGKosten = bestebeginstad(TotaalkostenAndereBeginstad).getValue();
        StedenTourPad = bestebeginstad(TotaalkostenAndereBeginstad).getKey();
        System.out.println("Dit is de onze beste route en onze beste beginstad:" + totaleGKosten);
        return StedenTourPad;
    }

    public Entry<Stad, Integer> MinGvalueCity(Map<Stad, Integer> map) {
        Map.Entry<Stad, Integer> minEntry = null;
        for (Map.Entry<Stad, Integer> entry : map.entrySet()) {
            if (minEntry == null || entry.getValue() < minEntry.getValue()) {
                minEntry = entry;
            }

        }
        return minEntry;
    }

    public Entry<List<Stad>, Integer> bestebeginstad(Map<List<Stad>, Integer> map) {
        Map.Entry<List<Stad>, Integer> minEntry = null;
        for (Entry<List<Stad>, Integer> entry : map.entrySet()) {
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