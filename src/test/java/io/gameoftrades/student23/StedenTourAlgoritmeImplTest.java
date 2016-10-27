/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.gameoftrades.student23;

import io.gameoftrades.model.kaart.Coordinaat;
import io.gameoftrades.model.kaart.Stad;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author JordainGijsbertha
 */
public class StedenTourAlgoritmeImplTest {

    SnelstePadAlgoritmeImpl SnelstePimpl;
    WereldLaderImpl wereldLader;
    StedenTourAlgoritmeImpl tour;

    @Before
    public void init() {
        SnelstePimpl = new SnelstePadAlgoritmeImpl();
        wereldLader = new WereldLaderImpl();
        tour = new StedenTourAlgoritmeImpl();
    }

    @Test
    public void zouMinimumMinGvalueCitymoetenhalen() {
        Stad eerststad = new Stad(Coordinaat.op(2, 4), "Den haag");
        Stad tweedestad = new Stad(Coordinaat.op(5, 5), "Rotterdam");
        Stad derdestad = new Stad(Coordinaat.op(7, 5), "Delft");
        Stad vierdestad = new Stad(Coordinaat.op(9, 5), "Utrecht");
        Stad vijfdestad = new Stad(Coordinaat.op(20, 5), "walibi");
        Map<Stad, Integer> map = new HashMap<>();
        
        int lowestGValue = 22;

        map.put(eerststad, lowestGValue);
        map.put(tweedestad, 40);
        map.put(derdestad, 55);
        map.put(vierdestad, 66);
        map.put(vijfdestad, 89);
        
        int vergelijkendevalue = tour.MinGvalueCity(map).getValue();
        assertEquals(lowestGValue, vergelijkendevalue);

    }

    @Test
    public void zouMinimumbestebeginstadmoetenhalen() {

        List<Stad> StadList = new ArrayList<>();
        StadList.add(new Stad(Coordinaat.op(2, 4), "Den haag"));
        StadList.add(new Stad(Coordinaat.op(7, 5), "Delft"));

        List<Stad> StadList2 = new ArrayList<>();
        StadList2.add(new Stad(Coordinaat.op(4, 6), "Rotterdam"));
        StadList2.add(new Stad(Coordinaat.op(9, 5), "Utrecht"));

        Map<List<Stad>, Integer> map = new HashMap<>();
  int lowestTotaleKosten = 10;
        map.put(StadList, 10);
        map.put(StadList2, 20);

        int vergelijkdelowestTotaleKosten = tour.bestebeginstad(map).getValue();
        assertEquals(lowestTotaleKosten, vergelijkdelowestTotaleKosten);

    }
 
}
