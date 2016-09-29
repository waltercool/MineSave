package cl.slash.minesave.ui;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import cl.slash.minesave.R;
import cl.slash.minesave.exception.NoPermissionError;
import cl.slash.minesave.obj.constants.WorldStorageLocation;
import cl.slash.minesave.obj.domain.MineWorld;
import cl.slash.minesave.toolkit.MineUtils;
import cl.slash.minesave.toolkit.PermissionUtils;
import cl.slash.minesave.toolkit.StorageUtils;
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

    @Override
    protected void onStart() {
        super.onStart();

        try {
            List<MineWorld> worlds = MineUtils.getInstance().listWorlds();
            listWorlds.setAdapter(new WorldAdapter(this, worlds));
        } catch (NoPermissionError e) {
            PermissionUtils.requestReadPermissions(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        }
    }

    @OnItemClick(R.id.activity_main_list)
    public void onWorldClick(AdapterView<?> parent, View view, int pos) {
        UploadWorld uploadWorld = new UploadWorld();
        uploadWorld.execute(((WorldAdapter)parent.getAdapter()).getItem(pos));
    }

    public class UploadWorld extends AsyncTask<MineWorld, Void, Boolean> {
        private ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(MainActivity.this);
            dialog.show();
        }

        @Override
        protected Boolean doInBackground(MineWorld... params) {
            StorageUtils storageUtils = new StorageUtils();
            return storageUtils.saveInCloud(MainActivity.this,
                    params[0],
                    WorldStorageLocation.BOX);
        }

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            dialog.dismiss();
            if(result)
                Toast.makeText(MainActivity.this, "OK", Toast.LENGTH_SHORT).show();
        }
    }
}
