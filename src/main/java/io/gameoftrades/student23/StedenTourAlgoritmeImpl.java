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

    public StedenTourAlgoritmeImpl() {
        SnelstePimpl = new SnelstePadAlgoritmeImpl();
        StedenTourPad = new ArrayList();

    }

    @Override
    public List<Stad> bereken(Kaart kaart, List<Stad> steden) {
        List<Stad> tour = TourAlgoritme(kaart, steden);

        debug.debugSteden(kaart, tour);

        return tour;
    }

    public List<Stad> TourAlgoritme(Kaart kaart, List<Stad> list) {
        Map<Stad, Integer> KostenNaarStadMap = new HashMap<>();
        Map<List<Stad>, Integer> TotaalkostenAndereBeginstad = new HashMap<>();
        List<Stad> StadEnGvaluelist = new ArrayList<>();
        List<Stad> listVanSteden = new ArrayList<>();

        for (int j = 0; j < list.size(); j++) {
            int SomvanGvalues = 0;
            StadEnGvaluelist.clear();

            listVanSteden.addAll(list);
            Stad startstad = listVanSteden.get(j);
            listVanSteden.remove(j);

            StadEnGvaluelist.add(startstad);
            Boolean isdone = false;

            while (!isdone) {

                for (int i = 0; i < listVanSteden.size(); i++) {
                    Stad volgendestad = listVanSteden.get(i);

                    if (startstad.equals(volgendestad)) {
                        throw new IllegalArgumentException("Start stad en volgendestad are the same.");
                    }

                    pad = SnelstePimpl.aStarAlgoritme(kaart, startstad.getCoordinaat(), volgendestad.getCoordinaat());
                    int gvalue = pad.getPathGValue();
                    KostenNaarStadMap.put(volgendestad, gvalue);

                }

                startstad = MinGvalueCity(KostenNaarStadMap).getKey();
                SomvanGvalues = SomvanGvalues + MinGvalueCity(KostenNaarStadMap).getValue();
                StadEnGvaluelist.add(startstad);
                listVanSteden.remove(startstad);
                KostenNaarStadMap.clear();

                if (listVanSteden.isEmpty()) {
                    TotaalkostenAndereBeginstad.put(StadEnGvaluelist, SomvanGvalues);
                    isdone = true;
                }
            }
        }
        int totaleKosten = bestebeginstad(TotaalkostenAndereBeginstad).getValue();
        System.out.println("Dit is onze beste route en onze beste beginstad:" + totaleKosten);
        StedenTourPad = bestebeginstad(TotaalkostenAndereBeginstad).getKey();
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

    public List<Stad> getStedenTourPad() {
        return StedenTourPad;
    }

    @Override
    public void setDebugger(Debugger debugger) {
        this.debug = debugger;
    }

}
