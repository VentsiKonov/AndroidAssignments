package me.vkonov.gamebook;

import android.content.Intent;
import android.test.ActivityUnitTestCase;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

/**
 * Created by Венци on 30.11.2014 г..
 */
public class ActivityTest extends ActivityUnitTestCase<Story> {

    Story story;

    public ActivityTest(Class<Story> activityClass) {
        super(activityClass);
    }


    @Override
    protected void setUp() throws Exception {
        super.setUp();

        Intent i = new Intent(getInstrumentation().getTargetContext(), Story.class);
        startActivity(i, null, null);
        story = getActivity();
    }

    public void testLayout() {
        View beginBtn = story.findViewById(R.id.beginGame);
        assertNotNull(beginBtn);
        EditText name = (EditText) story.findViewById(R.id.nameField);
        assertNotNull(name);
        assertEquals("", name.getText().toString());
        name.setText("asd");
        beginBtn.performClick();

        Spinner choice = (Spinner) story.findViewById(R.id.userChoice);
        assertNotNull(choice);
    }
}
