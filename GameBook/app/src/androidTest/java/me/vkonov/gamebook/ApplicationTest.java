package me.vkonov.gamebook;

import android.app.Application;
import android.test.ApplicationTestCase;

import java.util.ArrayList;
import java.util.HashMap;


public class ApplicationTest extends ApplicationTestCase<Application> {

    public void testHeroInstance() {
        Hero h = Hero.getInstance();
        assertNotNull(h);
    }

    public void testHeroFields() {
        Hero h = Hero.getInstance();
        ArrayList<String> items = h.getItems();
        assertNotNull(items);
        assertEquals(items.size(), 1);
        assertTrue(items.contains(Constants.varGameItems.Key.toString()));

        HashMap<String, Integer> stats = h.getStats();
        assertNotNull(stats);
        assertEquals((int) stats.get(Constants.varHeroEnergy), 10);
        h.setFavour(5);
        assertEquals((int) stats.get(Constants.varHeroFavour), 5);
    }

    public void testChapters() {
        assertEquals(Constants.chapters.length, 9);
        for (ChapterData chapter : Constants.chapters) {
            assertNotNull(chapter);
        }

    }


    public ApplicationTest() {
        super(Application.class);
    }
}