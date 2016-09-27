package io.gameoftrades.student23;

import io.gameoftrades.model.Wereld;
import io.gameoftrades.model.kaart.Coordinaat;
import io.gameoftrades.model.kaart.Kaart;
import io.gameoftrades.model.kaart.Stad;
import io.gameoftrades.model.kaart.Terrein;
import io.gameoftrades.model.kaart.TerreinType;
import io.gameoftrades.model.lader.WereldLader;
import io.gameoftrades.model.markt.Handel;
import io.gameoftrades.model.markt.HandelType;
import io.gameoftrades.model.markt.Handelswaar;
import io.gameoftrades.model.markt.Markt;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
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

        Scanner in = new Scanner(this.getClass().getResourceAsStream(resource));

        String[] Line = in.nextLine().split(",");

        int w = Integer.parseInt(Line[0].trim());//10
        int h = Integer.parseInt(Line[1].trim());//10
        LinkedList<String> terreinLines = new LinkedList();

        //vult terein array met De werekleijke lijnen
        for (int i = 0; i < h; i++) {
            terreinLines.add(in.nextLine().trim());

            if (terreinLines.get(i).matches(".*\\d.*")) {//check als letters niet gelijk zijn aan wat in de programma zit
                throw new IllegalArgumentException("Invalid Terrein value");
            }
        }

        int numberCity = Integer.parseInt(in.nextLine().trim());

        //volgende stap
        //vullt citys
        LinkedList<String> cityLines = new LinkedList();
        String[] cityName = new String[numberCity]; //vier city namen. daarom Arrays
        int[] cityX = new int[numberCity];//vier x coordinaten
        int[] cityY = new int[numberCity];//vier y coordinaten

        for (int i = 0; i < numberCity; i++) {
            String cityLine = in.nextLine().trim();
            cityLines.add(cityLine);

            for (String city : cityLines) {
                String[] tempCity = city.split(",");
                cityX[i] = Integer.parseInt(tempCity[0]);
                cityY[i] = Integer.parseInt(tempCity[1]);
                cityName[i] = tempCity[2];
            }
        }

        //vult number trades
        int numberTrades = Integer.parseInt(in.nextLine().trim());

        LinkedList<String> tradesLines = new LinkedList();
        String[] tradeCityName = new String[numberTrades];
        String[] tradeType = new String[numberTrades];
        String[] tradeGoods = new String[numberTrades];
        int[] tradePrice = new int[numberTrades];

        for (int i = 0; i < numberTrades; i++) {
            tradesLines.add(in.nextLine().trim());

            for (String trades : tradesLines) {
                String[] tempTrades = trades.split(",");
                tradeCityName[i] = tempTrades[0];
                if (tempTrades[1].equals("VRAAGT") || tempTrades[1].equals("BIEDT")) {

                    tradeType[i] = tempTrades[1];
                } else {
                    throw new IllegalArgumentException("Invalid HandelsType value");
                }
                tradeGoods[i] = tempTrades[2];
                tradePrice[i] = Integer.parseInt(tempTrades[3]);

            }
        }

        List<Stad> steden = new ArrayList();
        List<Handel> handels = new ArrayList();

        //Coordinaat coordinaat = null;
        for (int i = 0; i < numberCity; i++) {
            if (cityX[i] == 0 && cityY[i] == 0) {
                throw new IllegalArgumentException("Invalid coordinate for city " + cityName[i]);
            } else {
                steden.add(new Stad(Coordinaat.op(cityX[i], cityY[i]), cityName[i]));
            }

        }

        for (int i = 0; i < numberTrades; i++) {
            //maakt handel. steden zitten nu in handel alles wat er bij hoort.
            handels.add(new Handel(GetCity(tradeCityName[i], steden), HandelType.valueOf(tradeType[i]), new Handelswaar(tradeGoods[i]), tradePrice[i]));
        }

        Kaart kaart = new Kaart(w, h);
        Markt markt = new Markt(handels);
        Terrein[][] terrein = new Terrein[w][h];

        String[][] mapCharacters = new String[w][h];

        //maakt 2d array met CHARACTERS Z,b,s etc. Niet werkelijke zee, berg
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                String[] temp = terreinLines.get(i)/*tereinZZZGZZ*/.split("");
                if (temp.length < w) {
                    throw new IllegalArgumentException("Invalid width value");
                } else {
                    mapCharacters[j][i] = temp[j];//// als i= O is is temp[4] = G
                }
            }
        }

        for (int i = 0; i < h; i++) {// CHARACTERS omzetten naar werelijke Zee berg Grasland
            for (int j = 0; j < w; j++) {
                terrein[j][i] = new Terrein(kaart, Coordinaat.op(j, i), TerreinType.valueOf(getTerreinType(mapCharacters[j][i])));
            }
        }

        //alles klaar returend nu e world
        Wereld world = new Wereld(kaart, steden, markt);

        return world;
    }

    public void MapTesting(String resource) {
        Scanner in = new Scanner(this.getClass().getResourceAsStream(resource));

        while (in.hasNext()) {
            String temp = in.nextLine();
            System.out.println(temp);
        }

    }

    private Stad GetCity(String cityName, List<Stad> steden) {
        for (int j = 0; j < steden.size(); j++) {
            if (steden.get(j).getNaam().equals(cityName)) {
                return steden.get(j);
            }
        }

        return null;
    }

    private String getTerreinType(String mapChar) {
        String terreinType = "";

        switch (mapChar) {
            case "Z":
                terreinType = "ZEE";
                break;
            case "R":
                terreinType = "BERG";
                break;
            case "B":
                terreinType = "BOS";
                break;
            case "G":
                terreinType = "GRASLAND";
                break;
            case "S":
                terreinType = "STAD";
                break;
        }

        return terreinType;
    }
}
