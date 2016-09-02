package io.gameoftrades.student23;

import io.gameoftrades.model.Wereld;
import io.gameoftrades.model.lader.WereldLader;
import java.util.Scanner;

public class WereldLaderImpl implements WereldLader {

    @Override
    public Wereld laad(String resource) {
        //
        // Gebruik this.getClass().getResourceAsStream(resource) om een resource van het classpath te lezen.
        //
        // Kijk in src/test/resources voor voorbeeld kaarten.
        //
        // TODO Laad de wereld!
        //
        
        
        return null;
    }
    
    public void MapTesting(String resource){
        Scanner in = new Scanner(this.getClass().getResourceAsStream(resource));
        
        //System.out.println(in.next());
        
        //Wereld world = new Wereld();
        
        String[] Line = in.nextLine().split(",");
        
        int w = Integer.parseInt(Line[0]);
        int h = Integer.parseInt(Line[1]);
        
        System.out.println(w + " " + h);
        
        for (int i = 0; i < h; i++) {
                System.out.println(in.next());
            
        }
    }

}
