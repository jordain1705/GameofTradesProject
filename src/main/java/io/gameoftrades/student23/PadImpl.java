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
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Daniel
 */
public class PadImpl implements Pad{
    private List<Coordinaat> padCoordinaten = new ArrayList();
    
    Kaart kaart;
    
    public PadImpl(Kaart kaart){
        this.kaart = kaart;
    }
    
    @Override
    public int getTotaleTijd() {
        return padCoordinaten.size();
    }

    @Override
    public Richting[] getBewegingen() {
       
        List<Richting> richtingList = new ArrayList();
        
        for (int i = 0; i < padCoordinaten.size() - 1; i++) {
            Coordinaat current = padCoordinaten.get(i);
            Coordinaat next = padCoordinaten.get(i + 1);
            richtingList.add(Richting.tussen(current, next));
        }
        
        Richting[] mogelijkeRichtingen = new Richting[richtingList.size()];
        
        for (int i = 0; i < richtingList.size(); i++) {
            mogelijkeRichtingen[i] = richtingList.get(i);
        }
        
        richtingList.removeAll(richtingList);
        return mogelijkeRichtingen;
    }

    @Override
    public Pad omgekeerd() {
        PadImpl reversePath = new PadImpl(kaart);
        List<Coordinaat> reverseList = padCoordinaten;
        
        Collections.reverse(reverseList);
        
        reversePath.setPadCoordinaten(reverseList);
        
        return reversePath;
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
