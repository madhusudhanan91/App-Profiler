package appprofiler.appprofilerv1;

import android.app.Activity;
import android.app.Instrumentation;
import android.app.ListActivity;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.ListView;
import android.widget.TextView;

import junit.framework.TestCase;

/**
 * Created by soory_000 on 11/25/2015.
 */public class AP_MenuClickTest extends ActivityInstrumentationTestCase2<AP_Menu> {

    private Activity activity;
    private ListView lv;
    private ActivityMain contextInfoActivity;
    private ApplicationListDataSniff contextInfoActivity1;


    private TextView tvInfo;

    public AP_MenuClickTest() {
        super(AP_Menu.class);
    }


    @Override
    protected void setUp() throws Exception {
        super.setUp();
        activity =  getActivity();
        lv = (ListView) activity.findViewById(android.R.id.list);

    }

    public void testCase1() {
        assertNotNull(activity);
        assertNotNull(lv);
    }

    public void testCase2() {

        Instrumentation instrumentation = getInstrumentation();
        Instrumentation.ActivityMonitor monitor = instrumentation.addMonitor(ActivityMain.class.getName(), null, false);
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                lv.performItemClick(lv, 0, 0);
            }
        });

        Activity currentActivity = getInstrumentation().waitForMonitor(monitor);
        contextInfoActivity = (ActivityMain) currentActivity;

        assertNotNull(contextInfoActivity);
        tvInfo = (TextView) contextInfoActivity.findViewById(R.id.Labelcheck);

        assertNotNull(tvInfo);
        assertEquals("Usage Monitor", tvInfo.getText().toString());

    }


    @Override
    protected void tearDown() throws Exception {
        super.tearDown();

        activity = null;
        lv = null;
        tvInfo = null;
        contextInfoActivity = null;
    }
}