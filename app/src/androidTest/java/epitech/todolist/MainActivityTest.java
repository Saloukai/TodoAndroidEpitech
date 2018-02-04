package epitech.todolist;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.ListView;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {
    MainActivity activity;

    public MainActivityTest() {
        super(MainActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        activity = getActivity();
    }

    @SmallTest
    public void testListTaskViewNotNull() {
        ListView listView = (ListView)activity.findViewById(R.id.list_task);
        assertNotNull(listView);
    }

    @SmallTest
    public void testButtonAddTaskViewNotNull() {
        FloatingActionButton floatingActionButton = (FloatingActionButton)activity.findViewById(R.id.fab);
        assertNotNull(floatingActionButton);
    }
}
