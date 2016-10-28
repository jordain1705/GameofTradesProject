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

<<<<<<< HEAD
    Debugger debug;
    private SnelstePadAlgoritmeImpl SnelstePimpl;
    private Map<Stad, Double> map ;
    
    Map<List<Stad>, Double> beginstadmap =new HashMap<>();
     
     
    private List<Stad> kortsteafstandenstad ;
   private  PadImpl pad;
=======
    private Debugger debug = new AsciiArtDebugger();
    private List<Stad> StedenTourPad;
    private final SnelstePadAlgoritmeImpl SnelstePimpl;
    private PadImpl pad;
    private static StedenTourAlgoritmeImpl firstinstance;
     int totaleKosten ;

    public int getTotaleKosten() {
        return totaleKosten;
    }
>>>>>>> master

    public StedenTourAlgoritmeImpl() {
        SnelstePimpl = new SnelstePadAlgoritmeImpl();
<<<<<<< HEAD
     
=======
        StedenTourPad = new ArrayList();

    }
    
    //Signleton
    public static synchronized StedenTourAlgoritmeImpl getInstance(){
        if (firstinstance==null){
            firstinstance= new StedenTourAlgoritmeImpl();
        }
        return firstinstance;
>>>>>>> master
    }

    @Override
    public List<Stad> bereken(Kaart kaart, List<Stad> steden) {
        List<Stad> tour = TourAlgoritme(kaart, steden);

<<<<<<< HEAD
        debug.debugSteden(kaart, findNearest(kaart, steden));
        return findNearest(kaart, steden);
    }

    public List<Stad> findNearest(Kaart kaart, List<Stad> list) {

        
       

       // Stad startstad = lijstvanSteden.get(0);//
       // double kosten = 0;//
       // kortsteafstandenstad.add(startstad);//
       // lijstvanSteden.remove(0);//
        
        for (int j = 0; j < list.size(); j++) {
            kortsteafstandenstad.clear();
      List<Stad> lijstvanSteden = new ArrayList(list);
       ListIterator<Stad> iterator = lijstvanSteden.listIterator();
            Stad startstad = lijstvanSteden.get(j);
            kortsteafstandenstad.add(startstad);
        double kosten = 0;
         lijstvanSteden.remove(j);

        while (iterator.hasNext()) {
=======
        debug.debugSteden(kaart, tour);
        

        return tour;
    }
>>>>>>> master

    public List<Stad> TourAlgoritme(Kaart kaart, List<Stad> list) {
        Map<Stad, Integer> KostenNaarStad = new HashMap<>();
        Map<List<Stad>, Integer> TotaalkostenAndereBeginstad = new HashMap<>();
        List<Stad> KostenStad = new ArrayList<>();
        List<Stad> lijstvanSteden = new ArrayList<>();

        for (int j = 0; j < list.size(); j++) {
            KostenStad.clear();
            lijstvanSteden.addAll(list);
            Stad startstad = lijstvanSteden.get(j);
            KostenStad.add(startstad);
            int Gkost = 0;
            lijstvanSteden.remove(j);
            Boolean isdone = false;
            while (!isdone) {

                for (int i = 0; i < lijstvanSteden.size(); i++) {
                    Stad volgendestad = lijstvanSteden.get(i);
                    pad = SnelstePimpl.aStarAlgoritme(kaart, startstad.getCoordinaat(), volgendestad.getCoordinaat());
                    int gvalue = pad.getPathGValue();
                    KostenNaarStad.put(volgendestad, gvalue);

                }
                startstad = MinGvalueCity(KostenNaarStad).getKey();
                Gkost = Gkost + MinGvalueCity(KostenNaarStad).getValue();
                KostenStad.add(startstad);
                lijstvanSteden.remove(startstad);
                KostenNaarStad.clear();

                if (lijstvanSteden.isEmpty()) {
                    TotaalkostenAndereBeginstad.put(KostenStad, Gkost);
                    isdone = true;
                }
            }
        }
        totaleKosten = bestebeginstad(TotaalkostenAndereBeginstad).getValue();
        System.out.println("Dit is de onze beste route en onze beste beginstad:" + totaleKosten);
        StedenTourPad = bestebeginstad(TotaalkostenAndereBeginstad).getKey();
        return StedenTourPad;
    }

<<<<<<< HEAD
            startstad = MinGvalueCity().getKey();
            kosten = kosten + MinGvalueCity().getValue();
            kortsteafstandenstad.add(startstad);
            lijstvanSteden.remove(startstad);
            map.clear();

            //Print kosten
            if (lijstvanSteden.isEmpty()) {
                System.out.println(kosten);
                List<Stad> bestekorste = kortsteafstandenstad;
                beginstadmap.put(bestekorste,kosten);
                
            }
        }
        
        
    }
        
        System.out.println("Dit is de beste route de beste kosten en  de beste beginstad :"+bestebeginstad().getValue());
        return bestebeginstad().getKey();
=======
    public Entry<Stad, Integer> MinGvalueCity(Map<Stad, Integer> map) {
        Map.Entry<Stad, Integer> minEntry = null;
        for (Map.Entry<Stad, Integer> entry : map.entrySet()) {
            if (minEntry == null || entry.getValue() < minEntry.getValue()) {
                minEntry = entry;
            }

        }
        return minEntry;
>>>>>>> master
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
   
     private Entry<List<Stad>, Double> bestebeginstad() {
        Entry<List<Stad>, Double> min = null;
        for (Entry<List<Stad>, Double> entry : beginstadmap.entrySet()) {
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
