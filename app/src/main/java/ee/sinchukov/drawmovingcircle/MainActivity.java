package ee.sinchukov.drawmovingcircle;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends Activity {

    static private final String PREFS_NAME = "sharedPrefsFile";
    static private final String PREFS_KEY_CIRCLE_X = "circle_x";
    static private final String PREFS_KEY_CIRCLE_Y = "circle_y";
    static private final int DEFAULT_CIRCLE_X = 70;
    static private final int DEFAULT_CIRCLE_Y = 70;

    DrawScene scene;

    private int start_x;
    private int start_y;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences storeData = getSharedPreferences(PREFS_NAME,0); // 0 means only for this app

       start_x = storeData.getInt(PREFS_KEY_CIRCLE_X,DEFAULT_CIRCLE_Y);
       start_y = storeData.getInt(PREFS_KEY_CIRCLE_Y,DEFAULT_CIRCLE_Y);

        scene = new DrawScene(this, start_x, start_y);
        setContentView(scene);
    }

    @Override
    protected void onStop() {
        super.onStop();
        this.saveRates();


    }

    protected void saveRates() {
        SharedPreferences storeData = getSharedPreferences(PREFS_NAME,0); // 0 means only for this app
        SharedPreferences.Editor storeDataEditor = storeData.edit();
      storeDataEditor.putInt(PREFS_KEY_CIRCLE_X, scene.getter_X());
      storeDataEditor.putInt(PREFS_KEY_CIRCLE_Y,scene.getter_Y());
        storeDataEditor.commit();
    }

}
