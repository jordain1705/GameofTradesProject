/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.gameoftrades.student23;

import io.gameoftrades.model.kaart.Coordinaat;
import io.gameoftrades.model.kaart.Pad;
import io.gameoftrades.model.kaart.Richting;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Daniel
 */
public class PadImpl implements Pad{

    private List<Coordinaat> padCoordinaten = new ArrayList();
    
    public List<Coordinaat> getPadCoordinaten() {
        return padCoordinaten;
    }

    public void setPadCoordinaten(List<Coordinaat> padCoordinaten) {
        this.padCoordinaten = padCoordinaten;
    }
    
    @Override
    public int getTotaleTijd() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Richting[] getBewegingen() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Pad omgekeerd() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Coordinaat volg(Coordinaat start) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
