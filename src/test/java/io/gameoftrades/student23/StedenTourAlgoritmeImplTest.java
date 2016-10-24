/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.gameoftrades.student23;

import io.gameoftrades.model.Wereld;
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
        Wereld wereld = wereldLader.laad("/kaarten/westeros-kaart.txt");
        List<Stad> StadList = wereld.getSteden();
        Map<Stad, Integer> map = new HashMap<>();

        map.put(StadList.get(1), 2);
        map.put(StadList.get(2), 4);
        map.put(StadList.get(3), 5);

        int x = tour.MinGvalueCity(map).getValue();
        assertEquals(2, x);

    }

    @Test
    public void zouMinimumbestebeginstadmoetenhalen() {
        Wereld wereld = wereldLader.laad("/kaarten/westeros-kaart.txt");
        Wereld wereld2 = wereldLader.laad("/kaarten/voorbeeld-kaart.txt");
        List<Stad> StadList = wereld.getSteden();
        List<Stad> StadList2 = wereld2.getSteden();

        Map<List<Stad>, Integer> map = new HashMap<>();

        map.put(StadList, 10);
        map.put(StadList2, 20);

        int x = tour.bestebeginstad(map).getValue();
        assertEquals(10, x);

    }

    @Test
    public void Gaatalleen1stadLangs() {
        Wereld wereld = wereldLader.laad("/kaarten/westeros-kaart.txt");

        List<Stad> StadList = new ArrayList<>(wereld.getSteden());
        Stad eerste = StadList.get(0);
        StadList.remove(0);
        assertEquals(StadList.contains(eerste), false);

    }

}
