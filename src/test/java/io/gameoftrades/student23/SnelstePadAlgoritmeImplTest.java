/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.gameoftrades.student23;

import io.gameoftrades.model.Wereld;
import io.gameoftrades.model.algoritme.SnelstePadAlgoritme;
import io.gameoftrades.model.kaart.Coordinaat;
import io.gameoftrades.model.kaart.Pad;
import io.gameoftrades.model.lader.WereldLader;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
/**
 *
 * @author danie_000
 */
public class SnelstePadAlgoritmeImplTest {
            
    private SnelstePadAlgoritmeImpl SnelstePadLader;
    private WereldLader wereldLader;

    @Before
    public void init() {
        SnelstePadLader = new SnelstePadAlgoritmeImpl();
        wereldLader = new WereldLaderImpl();
    }

    @Test
    public void eindPuntCheck() {
        Wereld wereld = wereldLader.laad("/kaarten/westeros-kaart.txt");
        PadImpl TestPad;
        TestPad = SnelstePadLader.aStarAlgoritme(wereld.getKaart(), Coordinaat.op(15, 17), Coordinaat.op(14, 46));
        
        assertTrue();
        
        TestPad.getPadCoordinaten();
    }
    
}
