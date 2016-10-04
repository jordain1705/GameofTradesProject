/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.gameoftrades.student23;

import io.gameoftrades.model.algoritme.SnelstePadAlgoritme;
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
            
    private SnelstePadAlgoritme snelPadLader;
    private WereldLader wereldLader;

    @Before
    public void init() {
        snelPadLader = new SnelstePadAlgoritmeImpl();
        wereldLader = new WereldLaderImpl();
    }

    @Test
    public void Test() {
        
    }
    
}
