package cl.slash.minesave.ui;

import android.Manifest;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cl.slash.minesave.R;
import cl.slash.minesave.exception.NoPermissionError;
import cl.slash.minesave.obj.domain.MineWorld;
import cl.slash.minesave.toolkit.MineUtils;
import cl.slash.minesave.toolkit.PermissionUtils;
import cl.slash.minesave.ui.adapter.WorldAdapter;

/**
 * Created by waltercool on 9/13/16.
 */
public class MainActivity extends Activity {

    @BindView(R.id.activity_main_list)
    ListView listWorlds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.button)
    public void onButtonClick() {
        try {
            List<MineWorld> worlds = MineUtils.getInstance().listWorlds();
            listWorlds.setAdapter(new WorldAdapter(this, worlds));
        } catch (NoPermissionError e) {
            PermissionUtils.requestReadPermissions(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        }
    }
}
