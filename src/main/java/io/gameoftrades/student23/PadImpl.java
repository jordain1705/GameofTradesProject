/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.gameoftrades.student23;

import io.gameoftrades.model.kaart.Coordinaat;
import io.gameoftrades.model.kaart.Kaart;
import io.gameoftrades.model.kaart.Pad;
import io.gameoftrades.model.kaart.Richting;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Daniel
 */
public class PadImpl implements Pad{
    private List<Coordinaat> padCoordinaten = new ArrayList();
    
    Kaart kaart;
    Coordinaat Start, Eind;
    
    
    public PadImpl(Kaart kaart, Coordinaat Start, Coordinaat Eind){
        this.kaart = kaart;
        this.Start = Start;
        this.Eind = Eind;
    }
    
    @Override
    public int getTotaleTijd() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Richting[] getBewegingen() {
        Richting[] mogelijkeRichtingen = new Richting[padCoordinaten.size()];
        List<Richting> mogelijkeRichtingen2 = new ArrayList();
        
        //Collections.reverse(padCoordinaten);
        
        for (Coordinaat r : padCoordinaten) {
            System.out.println(r);
        }
        
        
        mogelijkeRichtingen[0] = Richting.NOORD;
        return mogelijkeRichtingen;
    }

    @Override
    public Pad omgekeerd() {
        PadImpl temp = new PadImpl(kaart, padCoordinaten.get(0), padCoordinaten.get(padCoordinaten.size() - 1));
        
        return temp;
    }

    @Override
    public Coordinaat volg(Coordinaat start) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     *
     * @return
     */
    public List<Coordinaat> getPadCoordinaten() {
        return padCoordinaten;
    }

    public void setPadCoordinaten(List<Coordinaat> padCoordinaten) {
        this.padCoordinaten = padCoordinaten;
    }
    
    public void newMethod(){
        
    }
}
