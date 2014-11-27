package me.vkonov.classassignmentthree;

import android.test.InstrumentationTestCase;

/**
 * Created by Венци on 24.11.2014 г..
 */
public class TestClass extends InstrumentationTestCase {

    public void test() throws Exception{
        int expected = 10;
        int reality = 1;
        assertEquals(expected, reality);
    }

}
