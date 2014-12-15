package me.vkonov.testone;

import java.util.ArrayList;

/**
 * Created by Венци on 15.12.2014 г..
 */
public class ProductDatabase {
    public static void generate() {
        for (int i = 0; i < 10; i++) {
            products.add(new Product("Product " + String.valueOf(i), "Product " + String.valueOf(i) + " Desc", i));
        }
    }

    private static ArrayList<Product> products = new ArrayList<>();
    public static ArrayList<Product> getAll(){
        return products;
    }
    public static void add(Product p){
        products.add(p);
    }
    public static void removeAt(int index){
        products.remove(index);
    }
}
