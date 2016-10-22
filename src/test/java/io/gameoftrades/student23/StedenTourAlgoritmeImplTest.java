/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.gameoftrades.student23;

import io.gameoftrades.model.Wereld;
import io.gameoftrades.model.kaart.Stad;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    public void zouMinimummoetenhalen() {
        Wereld wereld = wereldLader.laad("/kaarten/westeros-kaart.txt");
        List<Stad> TestStadList = wereld.getSteden();
        
        StedenTourAlgoritmeImpl tour = new StedenTourAlgoritmeImpl();
        tour.findNearest(wereld.getKaart(), TestStadList);
        
        tour.MinGvalueCity();
       
   
       
        

    }

}
