/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.gameoftrades.student23;

import io.gameoftrades.debug.Debuggable;
import io.gameoftrades.debug.Debugger;
import io.gameoftrades.model.algoritme.StedenTourAlgoritme;
import io.gameoftrades.model.kaart.Kaart;
import io.gameoftrades.model.kaart.Stad;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * @author JordainGijsbertha
 */
public class StedenTourAlgoritmeImpl implements StedenTourAlgoritme , Debuggable{

    SnelstePadAlgoritmeImpl impl1 = new SnelstePadAlgoritmeImpl();
    Debugger debug;
    @Override
    public List<Stad> bereken(Kaart kaart, List<Stad> steden) {
        
        
      debug.debugSteden(kaart, findNearest(kaart, steden));
      return findNearest(kaart, steden);      
    }
    
    public List<Stad> findNearest(Kaart kaart, List<Stad> list){
       
        Stad startstad = list.get(0); // eerste stad waar je begint
        
        ListIterator<Stad> iterator =list.listIterator();
        List<Stad> kortsteafstandenstad = new ArrayList<>();
      
        double vorigeafstand = Integer.MAX_VALUE;
        while (iterator.hasNext()) {            
            Stad vergelijkingstad = iterator.next();
            
            double afstand = startstad.getCoordinaat().afstandTot(vergelijkingstad.getCoordinaat());// vergelijkt aftsand van startstad en volgende stad
            
      for(Stad stad : list){
         
         if(afstand < vorigeafstand){
              
              vorigeafstand = afstand;
              
            }
         kortsteafstandenstad.add(stad);
     
            }
          if(list.size()==kortsteafstandenstad.size()){
              break;
          }
        }     

        return kortsteafstandenstad;
    }

    @Override
    public void setDebugger(Debugger debugger) {
        this.debug = debugger;
    }
}
