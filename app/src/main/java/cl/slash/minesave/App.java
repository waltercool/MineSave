package cl.slash.minesave;

import android.app.Application;

import cl.slash.minesave.toolkit.MineUtils;

/**
 * Created by waltercool on 9/13/16.
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        MineUtils.init(this);
    }
}
