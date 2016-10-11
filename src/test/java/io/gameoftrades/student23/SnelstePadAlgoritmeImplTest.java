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
import io.gameoftrades.model.kaart.Stad;
import io.gameoftrades.model.kaart.Terrein;
import io.gameoftrades.model.kaart.TerreinType;
import io.gameoftrades.model.lader.WereldLader;
import java.util.List;
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
    public void StadCheck() {
        Wereld wereld = wereldLader.laad("/kaarten/westeros-kaart.txt");
        PadImpl TestPad;
        TestPad = SnelstePadLader.aStarAlgoritme(wereld.getKaart(), Coordinaat.op(14, 16), Coordinaat.op(13, 45));
        
        assertEquals(TerreinType.STAD,  wereld.getKaart().getTerreinOp(TestPad.getPadCoordinaten().get(0)).getTerreinType());
        assertEquals(TerreinType.STAD,  wereld.getKaart().getTerreinOp(TestPad.getPadCoordinaten().get(TestPad.getPadCoordinaten().size() - 1)).getTerreinType());
    }
    
    @Test
    public void PathGCost(){
        Wereld wereld = wereldLader.laad("/kaarten/voorbeeld-kaart.txt");
        PadImpl TestPad;
        List<Stad> TestStadList = wereld.getSteden();
        
        TestPad = SnelstePadLader.aStarAlgoritme(wereld.getKaart(), TestStadList.get(0).getCoordinaat(), TestStadList.get(1).getCoordinaat());
        
        assertEquals(18, TestPad.getPathGValue());
    }
    
}
