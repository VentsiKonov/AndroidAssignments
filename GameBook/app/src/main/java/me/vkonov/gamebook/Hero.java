package me.vkonov.gamebook;

import java.util.ArrayList;
import java.util.HashMap;


public class Hero {

    private String name;
    private ArrayList<String> items;
    private HashMap<String, Integer> stats;
    private static Hero instance = new Hero();

    private Hero() {
        items = new ArrayList<String>();
        stats = new HashMap<String, Integer>();
        name = "";
        stats.put(Constants.varHeroEnergy, 10);
    }

    public void setName(String name) {
        this.name = name;
    }

    public static Hero getInstance() {
        return instance;
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getItems() {
        return items;
    }

    public HashMap<String, Integer> getStats() {
        return stats;
    }

}
