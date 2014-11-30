package me.vkonov.gamebook;

import java.util.ArrayList;
import java.util.HashMap;


public class Hero {

    private String name;
    private ArrayList<String> items;
    private HashMap<String, Integer> stats;
    private static Hero instance = new Hero();

    private Hero() {
        initialize();
    }

    public void initialize() {
        items = new ArrayList<String>();
        items.add(Constants.varGameItems.Key.toString());
        stats = new HashMap<String, Integer>();
        name = "";
        stats.put(Constants.varHeroEnergy, 1);
        stats.put(Constants.varHeroFavour, 3); // No idea for ranges yet
        stats.put(Constants.varHeroStrength, 10);
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

    public void setItems(ArrayList<String> items) {
        this.items = items;
    }

    public void setFavour(int value) {
        stats.put(Constants.varHeroFavour, value);
    }

    public void setEnergy(int value) {
        stats.put(Constants.varHeroEnergy, value);
    }

    public void setStrength(int value) {
        stats.put(Constants.varHeroStrength, value);
    }

    public HashMap<String, Integer> getStats() {
        return stats;
    }

}
