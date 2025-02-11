package data;

import java.io.Serializable;

public class DataStorage implements Serializable {

    //Player Stats
    int maxLife;
    int life;
    int maxEnergy;
    int energy;
    int hasSoul;
    int mapPlayerX;
    int mapPlayerY;
    int currentMap;

    //Object on Map
    String mapObjectNames[][];
    int mapObjectWorldX[][];
    int mapObjectWorldY[][];


}
