package io.gameoftrades.student23;

import io.gameoftrades.model.Handelaar;
import io.gameoftrades.model.Wereld;
import io.gameoftrades.model.algoritme.HandelsplanAlgoritme;
import io.gameoftrades.model.algoritme.SnelstePadAlgoritme;
import io.gameoftrades.model.algoritme.StedenTourAlgoritme;
import io.gameoftrades.model.kaart.Kaart;
import io.gameoftrades.model.kaart.Terrein;
import io.gameoftrades.model.lader.WereldLader;

/**
 * Welkom bij Game of Trades! 
 * 
 * Voordat er begonnen kan worden moet eerst de 'studentNN' package omgenoemd worden
 * zodat iedere groep zijn eigen namespace heeft. Vervang de NN met je groep nummer.
 * Dus als je in groep 3 zit dan wordt de packagenaam 'student03' en ben je in groep
 * 42 dan wordt de package naam 'student42'.
 * 
 * Om te controleren of je het goed hebt gedaan is er de ProjectSanityTest die je kan draaien.
 * 
 */
public class HandelaarImpl implements Handelaar {

    /**
     * Opdracht 1, zie ook de handige test-set in WereldLaderImplTest.
     */
    @Override
    public WereldLader nieuweWereldLader() {
     
        
        return new WereldLaderImpl();
    }

    /**
     * Opdracht 2
     */
    @Override
    public SnelstePadAlgoritme nieuwSnelstePadAlgoritme() {
        // TODO Auto-generated method stub
        
        /*
        REMEMBER: SNELSTE PAD BELANGRIJKST NIET KORTSTE
        
We begin the search by doing the following:
    1. Begin at the starting point A and add it to an “open list” of squares to be considered. The open list is kind of like a shopping list. 
        Right now there is just one item on the list, but we will have more later. It contains squares that might fall along the path you want to take, 
        but maybe not. Basically, this is a list of squares that need to be checked out.   
        
    2. Look at all the reachable or walkable squares adjacent to the starting point, ignoring squares with walls, water, or other illegal terrain. 
        Add them to the open list, too. For each of these squares, save point A as its “parent square”. 
        This parent square stuff is important when we want to trace our path. It will be explained more later.   
        
    3. Drop the starting square A from your open list, and add it to a “closed list” of squares that you don’t need to look at again for now. 
        */
        WereldLaderImpl lader = new WereldLaderImpl();
        Wereld a = lader.laad("/kaarten/voorbeeld-kaart.txt");
        
        Terrein[][] terrein = lader.getTerrein();
        
        
        
        for (int i = 0; i < terrein.length; i++) {
           for (int j = 0; j < terrein[i].length; j++) {             
              System.out.println(terrein[i][j].toString());
                
            }
            
        }
       
        
        return null;
    }

    /**
     * Opdracht 3
     */
    @Override
    public StedenTourAlgoritme nieuwStedenTourAlgoritme() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * Opdracht 4
     */
    @Override
    public HandelsplanAlgoritme nieuwHandelsplanAlgoritme() {
        // TODO Auto-generated method stub
        return null;
    }
}
